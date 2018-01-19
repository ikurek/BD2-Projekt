package GUI;

import bd2Test.Application;

import javax.swing.*;
import java.awt.*;
import java.util.concurrent.TimeUnit;

public class LoginFrame {

    static JFrame frame;
    static JPanel loginP;
    static JTextField user;
    static JTextField password;
    static boolean connectLoop;

    public LoginFrame() {
        frame = null;
        user = null;
        password = null;
        setGUI();
        connectLoop = true;
    }

    public static void setGUI() {
        frame = new JFrame();
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        double width = screenSize.getWidth();
        double height = screenSize.getHeight();
        //---------------------------------------------------------------------------
        //frame.getContentPane().add(BorderLayout.CENTER, new JLabel("Welcome"));
        frame.setLocation(((int) (width / 2) - 400), ((int) (height / 2) - 250));
        frame.setVisible(true);
        //frame.setSize(new Dimension(300,150));
        frame.setResizable(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        loginP = new JPanel();
        loginP.setOpaque(true);

        Font fontF = new Font("Serif", Font.BOLD, 40);
        JLabel userL = new JLabel();
        JLabel pasL = new JLabel();
        userL.setFont(fontF);
        pasL.setFont(fontF);
        userL.setText("Nazwa uzytkownika:");
        pasL.setText("Haslo:");
        userL.setForeground(Color.RED);
        pasL.setForeground(Color.RED);
        userL.setAlignmentX(Component.CENTER_ALIGNMENT);
        pasL.setAlignmentX(Component.CENTER_ALIGNMENT);

        JPanel panel1 = new OpaquePanel();
        panel1.setPreferredSize(new Dimension(30, 50));
        panel1.setAlignmentX(Component.CENTER_ALIGNMENT);
        JPanel panel2 = new OpaquePanel();
        panel2.setPreferredSize(new Dimension(30, 50));
        panel2.setAlignmentX(Component.CENTER_ALIGNMENT);

        loginP.setLayout(new BoxLayout(loginP, BoxLayout.PAGE_AXIS));
        user = new JTextField(10);
        user.setAlignmentX(Component.CENTER_ALIGNMENT);
        password = new JTextField(10);
        //password.setPreferredSize(new Dimension(60,30));
        password.setAlignmentX(Component.CENTER_ALIGNMENT);
        JButton login = new JButton("Zaloguj");
        login.setAlignmentX(Component.CENTER_ALIGNMENT);
        login.addActionListener(new LoginListener());
        panel1.add(user);
        panel2.add(password);
        loginP.add(Box.createVerticalStrut(20));
        loginP.add(userL);
        loginP.add(panel1);
        loginP.add(Box.createVerticalStrut(20));
        loginP.add(pasL);
        loginP.add(panel2);
        loginP.add(Box.createVerticalStrut(20));
        loginP.add(login);
        //dataP.add(Box.createVerticalStrut(100));
        loginP.revalidate();
        loginP.repaint();
        frame.add(loginP);
        frame.pack();


    }

    public static boolean setConnection() {
        while (connectLoop) {
            try {
                TimeUnit.MILLISECONDS.sleep(500);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            return false;
        }
        password = null;
        loginP.removeAll();
        loginP.revalidate();
        loginP.repaint();
        user = null;
        loginP = null;
        frame.setVisible(false);
        frame.removeAll();
        frame = null;
        return true;
    }

    public static JTextField returnTextPF() {
        return password;
    }

    public static JTextField returnTextUF() {
        return user;
    }

    public static void setLoopBool(boolean statement) {
        connectLoop = statement;
    }

    public static void checkConnection() {
        if (Application.getConnection().getConnection() == null) {
            JFrame blad = new JFrame();
            //---------------------------------------------------------------------------
            //frame.getContentPane().add(BorderLayout.CENTER, new JLabel("Welcome"));
            blad.setLocationRelativeTo(null);
            blad.setVisible(true);
            blad.setSize(new Dimension(800, 500));
            blad.setResizable(false);
            Font fontF = new Font("Serif", Font.BOLD, 40);
            JLabel warning = new JLabel("Nie udalo sie polaczyc z baza!");
            warning.setFont(fontF);
            blad.add(BorderLayout.CENTER, warning);
            blad.pack();
        } else {
            setLoopBool(false);
        }
    }
}
