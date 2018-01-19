package GUI;

import Data.SystemData;
import Model.Country;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CountriesListener implements ActionListener {

    public void actionPerformed(ActionEvent zdarzenie) {
        //obszar testowy
        MainFrame.dataP.removeAll();
        MainFrame.dataP.revalidate();
        MainFrame.dataP.repaint();
        MainFrame.dataP.setLayout(new BorderLayout());
        MainFrame.list = new JList(SystemData.getCountries().toArray(new Country[SystemData.getCountries().size()]));
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
		JLabel filters = new JLabel();
		Font filterF = new Font("Arial", Font.BOLD, 20);
		filters.setFont(filterF);
		filters.setText("Filtry: Ranking FIFa");
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
        Country option;

        public void valueChanged(ListSelectionEvent event) {
            if (!event.getValueIsAdjusting()) {
                option = (Country) MainFrame.list.getSelectedValue();
                System.out.println("Wybrano kraj: " + option.toString());
                MainFrame.area.setText("Kraj: " + option.getName() + "\n");

            }
        }
    }

    //Klasa wewnetrzna obslugujaca pokazanie elementow na liscie
    public class ExampleListCellRenderer extends DefaultListCellRenderer {
        public Component getListCellRendererComponent(
                JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
            JLabel label = (JLabel) super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);

            Country name = (Country) value;
            label.setText(name.getName());

            return label;

        }
    }
}
