package GUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginListener implements ActionListener {
    JTextField pas;
    JTextField use;

    public LoginListener() {

    }

    public void actionPerformed(ActionEvent event) {
        pas = LoginFrame.returnTextPF();
        use = LoginFrame.returnTextUF();
        //Application.setConnection(new DatabaseConnection(pas.getText(),use.getText()));
        //MainFrame.checkConnection();
        if (pas.getText().equals("pas") && use.getText().equals("pas")) {
            LoginFrame.setLoopBool(false);
        }
    }
}