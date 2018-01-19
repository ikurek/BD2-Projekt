package GUI;

import Data.SystemData;
import Model.Season;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SeasonsListener implements ActionListener {

    public void actionPerformed(ActionEvent zdarzenie) {
        //obszar testowy
        //createSeasonsPanel(MainFrame.);
        MainFrame.dataP.removeAll();
        MainFrame.dataP.revalidate();
        MainFrame.dataP.repaint();
        MainFrame.dataP.setLayout(new BorderLayout());
        MainFrame.list = new JList(SystemData.getSeasons().toArray(new Season[SystemData.getSeasons().size()]));
        MainFrame.list.setVisibleRowCount(12);
        MainFrame.list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        MainFrame.list.addListSelectionListener(new PlayerListListener());
        MainFrame.list.setCellRenderer(new ExampleListCellRenderer());
        MainFrame.area = new JTextArea(5, 34);
        MainFrame.area.setBorder(BorderFactory.createMatteBorder(0, 3, 0, 3, Color.BLACK));
        //MainFrame.area.setPreferredSize(new Dimension(150,300));
        JScrollPane przewi = new JScrollPane(MainFrame.list);
        przewi.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        przewi.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        przewi.setPreferredSize(new Dimension(200, 270));
        //------------------------
        //panel z dostepnymi filtrami
		/*MainFrame.filterP = new OpaquePanel();
		MainFrame.filterP.setLayout(new FlowLayout());
		MainFrame.filterP.setPreferredSize(new Dimension(600,30));
		Font fontF = new Font("Serif",Font.BOLD,20);
		JLabel filters = new JLabel();
		filters.setFont(fontF);
		filters.setText("Filtry: Gospodarz");
		JTextField rankA = new JTextField("<--->",5);
		JButton filterSearch = new JButton("Szukaj");
		
		MainFrame.filterP.add(filters);
		MainFrame.filterP.add(rankA);
		MainFrame.filterP.add(filterSearch);
		
		MainFrame.dataP.add(BorderLayout.NORTH,MainFrame.filterP);*/
        MainFrame.dataP.add(BorderLayout.WEST, przewi);
        MainFrame.dataP.add(BorderLayout.EAST, MainFrame.area);
        MainFrame.dataP.validate();
        MainFrame.dataP.repaint();
        //MainFrame.imageP.add(MainFrame.dataP);
        //MainFrame.dataP.repaint();
    }

    class PlayerListListener implements ListSelectionListener {
        Season option;

        public void valueChanged(ListSelectionEvent event) {
            if (!event.getValueIsAdjusting()) {
                option = (Season) MainFrame.list.getSelectedValue();
                System.out.println("Wybrano sezon: " + option.getName());
                MainFrame.area.setText("Nazwa: " + option.getName() + "\n" + "Data rozpoczecia: " + option.getOpenDate() + "\n" + "Data zakonczenia: " + option.getCloseDate() + "\nI miejsce: " + option.getWinner());

            }
        }
    }

    //Klasa wewnetrzna obslugujaca pokazanie elementow na liscie
    public class ExampleListCellRenderer extends DefaultListCellRenderer {
        public Component getListCellRendererComponent(
                JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
            JLabel label = (JLabel) super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);

            Season help = (Season) value;
            label.setText(help.getName());

            return label;

        }
    }
}