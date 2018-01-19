package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Report implements ActionListener {
    static JButton send;
    static String whichTable;
    static String description;
    static JComboBox comboBox;

    Report() {
        send = null;
        whichTable = new String();
        description = new String();
        comboBox = null;
    }

    public void actionPerformed(ActionEvent zdarzenie) {
        MainFrame.dataP.removeAll();
        MainFrame.dataP.revalidate();
        MainFrame.dataP.repaint();
        MainFrame.dataP.setLayout(new BoxLayout(MainFrame.dataP, BoxLayout.PAGE_AXIS));
        //((FlowLayout)MainFrame.dataP.getLayout()).setVgap(0);

        JPanel textPanel = new JPanel();
        textPanel.setOpaque(false);
        textPanel.setPreferredSize(new Dimension(400, 100));
        textPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JPanel cB = new JPanel();
        cB.setOpaque(false);
        cB.setPreferredSize(new Dimension(100, 50));
        cB.setAlignmentX(Component.CENTER_ALIGNMENT);

        Font fontF = new Font("Serif", Font.BOLD, 20);

        JLabel chooseMenu = new JLabel();
        chooseMenu.setFont(fontF);
        chooseMenu.setForeground(Color.RED);
        chooseMenu.setText("Wybierz ktorej kategorii dotyczy blad:");
        chooseMenu.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel write = new JLabel();
        write.setFont(fontF);
        write.setForeground(Color.RED);
        write.setText("Opisz blad:");
        write.setAlignmentX(Component.CENTER_ALIGNMENT);

        send = new JButton("Wyslij");
        send.addActionListener(new SendListener());
        send.setAlignmentX(Component.CENTER_ALIGNMENT);

        String[] modes = {"Kraje",
                "Ligi",
                "Mecze",
                "Pilkarze",
                "PilkarzeWSezonie",
                "Sezony",
                "Druzyny"};

        @SuppressWarnings("unchecked")
        JComboBox<String> comboBox = new JComboBox<String>(modes);
        //comboBox.setSelectedIndex(2);
        //comboBox.setSelectedItem("Mecze");
        comboBox.addActionListener(new MenuListener());

        MainFrame.area = new JTextArea();
        MainFrame.area.setBorder(BorderFactory.createMatteBorder(0, 3, 0, 3, Color.black));

        cB.add(comboBox);
        textPanel.add(BorderLayout.CENTER, MainFrame.area);
        MainFrame.area.setColumns(30);
        MainFrame.area.setRows(10);
        MainFrame.area.resize(0, 0);
        MainFrame.dataP.add(chooseMenu);
        MainFrame.dataP.add(cB);
        MainFrame.dataP.add(write);
        MainFrame.dataP.add(textPanel);
        //MainFrame.dataP.add(Box.createVerticalStrut(20));
        MainFrame.dataP.add(send);
        MainFrame.dataP.validate();
        MainFrame.dataP.repaint();
    }

    class MenuListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            JComboBox combo = (JComboBox) e.getSource();
            String newMode = (String) combo.getSelectedItem();
            if (newMode != null) {
                //newMode = new String((String)Report.comboBox.getSelectedItem());
                if (newMode.equals("Kraje")) {
                    Report.whichTable = new String("Country");
                    System.out.println(Report.whichTable);
                }
                if (newMode.equals("Ligi")) {
                    Report.whichTable = new String("League");
                    System.out.println(Report.whichTable);
                }
                if (newMode.equals("Mecze")) {
                    Report.whichTable = "MatchInSeason";
                    System.out.println(Report.whichTable);
                }
                if (newMode.equals("Pilkarze")) {
                    Report.whichTable = "Player";
                    System.out.println(Report.whichTable);
                }
                if (newMode.equals("PilkarzeWSezonie")) {
                    Report.whichTable = "PlayerInSeason";
                    System.out.println(Report.whichTable);
                }
                if (newMode.equals("Sezony")) {
                    Report.whichTable = "Season";
                    System.out.println(Report.whichTable);
                }
                if (newMode.equals("Druzyny")) {
                    Report.whichTable = new String("Team");
                    System.out.println(Report.whichTable);
                }
            }
        }

    }


    public class SendListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            Report.description = MainFrame.area.getText();
            System.out.print(MainFrame.area.getText());
            if (Report.whichTable.equals("") || Report.description.equals("")) {
                JFrame blad = new JFrame();
                blad.setLocationRelativeTo(null);
                blad.setVisible(true);
                blad.setSize(new Dimension(800, 500));
                blad.setResizable(false);
                Font fontF = new Font("Serif", Font.BOLD, 40);
                JLabel warning = new JLabel("Zle uzupelniles pola!");
                warning.setFont(fontF);
                blad.add(BorderLayout.CENTER, warning);
                blad.pack();
            } else {
                //przesylanie do bazy
            }
        }
    }
}
