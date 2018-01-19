package GUI;

import Data.SystemData;
import Model.MatchInSeason;
import Model.Season;
import Model.Team;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class SeasonMatchListener implements ActionListener {
    static JList<MatchInSeason> listMatch;
    static JScrollPane przewiS;
    static JScrollPane przewiM;
    static ArrayList<MatchInSeason> matchesS;

    @SuppressWarnings("unchecked")
    public void actionPerformed(ActionEvent zdarzenie) {
        MainFrame.dataP.removeAll();
        MainFrame.dataP.revalidate();
        MainFrame.dataP.repaint();
        MainFrame.dataP.setLayout(new FlowLayout());
        ((FlowLayout) MainFrame.dataP.getLayout()).setVgap(0);

        MainFrame.list = new JList(SystemData.getSeasons().toArray(new Season[SystemData.getSeasons().size()]));
        MainFrame.list.setVisibleRowCount(12);

        MainFrame.list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        MainFrame.list.addListSelectionListener(new SeasonListListener());

        MainFrame.list.setCellRenderer(new SeasonListCellRenderer());

        MainFrame.area = new JTextArea();
        MainFrame.area.setBorder(BorderFactory.createMatteBorder(0, 3, 0, 3, Color.black));
        //------------------------
        przewiS = new JScrollPane(MainFrame.list);
        przewiS.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        przewiS.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        przewiS.setBorder(BorderFactory.createMatteBorder(0, 3, 0, 3, Color.black));
        przewiS.setPreferredSize(new Dimension(250, 392));

        MainFrame.dataP.add(przewiS);
        MainFrame.dataP.validate();
        MainFrame.dataP.repaint();
    }

    class MatchListListener implements ListSelectionListener {
        MatchInSeason option;
        Team team1;
        Team team2;

        public void valueChanged(ListSelectionEvent event) {
            if (!event.getValueIsAdjusting()) {
                option = SeasonMatchListener.listMatch.getSelectedValue();
                for (int i = 0; i < SystemData.getTeams().size(); i++) {
                    if (SystemData.getTeams().get(i).getName() == option.getTeam1()) {
                        team1 = SystemData.getTeams().get(i);
                    }
                    if (SystemData.getTeams().get(i).getName() == option.getTeam2()) {
                        team2 = SystemData.getTeams().get(i);
                    }
                }
                System.out.println("Wybrano mecz: " + team1.getName() + " kontra " + team2.getName() + "\nWynik: " + option.getGoals1() + " : " + option.getGoals2());
                MainFrame.dataP.removeAll();
                MainFrame.dataP.add(BorderLayout.CENTER, MainFrame.area);
                MainFrame.area.setText("Mecz: " + team1.getName() + " kontra " + team2.getName() + "\nWynik: " + option.getGoals1() + " : " + option.getGoals2());
                MainFrame.area.setPreferredSize(new Dimension(380, 392));
                SeasonMatchListener.przewiS.setPreferredSize(new Dimension(180, 392));
                MainFrame.dataP.add(SeasonMatchListener.przewiS);
                MainFrame.dataP.add(MainFrame.area);
                MainFrame.dataP.revalidate();
                MainFrame.dataP.repaint();
                team1 = null;
                team2 = null;
                //matchesS = null;
            }
        }
    }

    class SeasonListListener implements ListSelectionListener {
        Season option;

        public void valueChanged(ListSelectionEvent event) {
            if (!event.getValueIsAdjusting()) {
                option = (Season) MainFrame.list.getSelectedValue();
                System.out.println("Wybrano sezon: " + option.getName());
                MainFrame.dataP.removeAll();
                SeasonMatchListener.matchesS = new ArrayList<MatchInSeason>();
                for (int i = 0; i < SystemData.getMatches().size(); i++) {
                    if (SystemData.getMatches().get(i).getSeasonID() == option.getId()) {
                        matchesS.add(SystemData.getMatches().get(i));
                    }
                }
                listMatch = new JList(matchesS.toArray(new MatchInSeason[matchesS.size()]));
                listMatch.setVisibleRowCount(12);
                listMatch.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
                listMatch.addListSelectionListener(new MatchListListener());
                listMatch.setCellRenderer(new MatchListCellRenderer());
                przewiM = new JScrollPane(listMatch);
                przewiM.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
                przewiM.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
                przewiM.setBorder(BorderFactory.createMatteBorder(0, 3, 0, 3, Color.black));
                przewiM.setPreferredSize(new Dimension(250, 392));
                MainFrame.dataP.add(SeasonMatchListener.przewiS);
                MainFrame.dataP.add(SeasonMatchListener.przewiM);
                MainFrame.dataP.revalidate();
                MainFrame.dataP.repaint();
            }
        }
    }

    //Klasa wewnetrzna obslugujaca pokazanie elementow na liscie
    @SuppressWarnings("serial")
    public class SeasonListCellRenderer extends DefaultListCellRenderer {
        public Component getListCellRendererComponent(
                JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
            JLabel label = (JLabel) super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);

            Season help = (Season) value;
            label.setText(help.getName());

            return label;

        }
    }

    public class MatchListCellRenderer extends DefaultListCellRenderer {
        public Component getListCellRendererComponent(
                JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
            JLabel label = (JLabel) super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
            Team team1 = null;
            Team team2 = null;
            MatchInSeason help = (MatchInSeason) value;
            for (int i = 0; i < SystemData.getTeams().size(); i++) {
                if (SystemData.getTeams().get(i).getName() == help.getTeam1()) {
                    team1 = SystemData.getTeams().get(i);
                }
                if (SystemData.getTeams().get(i).getName() == help.getTeam2()) {
                    team2 = SystemData.getTeams().get(i);
                }
            }
            label.setText(team1.getName() + " : " + team2.getName());
            return label;

        }
    }
}