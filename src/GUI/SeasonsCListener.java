package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SeasonsCListener implements ActionListener {
    public void actionPerformed(ActionEvent zdarzenie) {
        MainFrame.dataP.removeAll();
        MainFrame.dataP.revalidate();
        MainFrame.dataP.repaint();
        JButton seasonPB = new JButton("Pilkarz - mecz");
        JButton seasonMB = new JButton("Mecz - sezon");
        seasonPB.setAlignmentX(Component.CENTER_ALIGNMENT);
        seasonMB.setAlignmentX(Component.CENTER_ALIGNMENT);
        seasonPB.addActionListener(new SeasonPlayerListener());
        seasonMB.addActionListener(new SeasonMatchListener());
        MainFrame.dataP.setLayout(new BoxLayout(MainFrame.dataP, BoxLayout.PAGE_AXIS));
        MainFrame.dataP.add(Box.createVerticalStrut(70));
        MainFrame.dataP.add(seasonPB);
        MainFrame.dataP.add(Box.createVerticalStrut(70));
        MainFrame.dataP.add(seasonMB);
    }
}