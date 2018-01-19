package GUI;

import Data.SystemData;
import Database.DatabaseConnection;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class MainFrame {
    static JFrame frame; //okno glowne
    static JPanel textP; //panel z tekstem nazwy app
    static JPanel mainP; //panel glowny
    static JPanel mMenuP; //panel glowny menu
    static JPanel menuP; //panel z buttonami menu
    static JPanel exitP; //panel z button exit
    static JPanel dataP; //panel ktory bedzie sie zmienial w zaleznosci od wyboru
    static JPanel imageP; //panel z grafika
    static JWindow loadingFrame; //okno z ladowaniem app
    static FlowLayout obok = new FlowLayout(FlowLayout.LEADING, 0, 0);
    static JList list;
    static JTextArea area;
    static JTextField user;
    static JTextField password;
    static boolean loopBool;

    static DatabaseConnection connection;
    static SystemData data;

    public MainFrame(SystemData sysData) {
        data = sysData;
        createUIFrame();
        fillPanels();
    }

    public static void createUIFrame() {
        System.out.println("Hello application!");
        //Application app = new Application();
        loadingFrame = new JWindow();
        try {
            loadingFrame.getContentPane().add(
                    new JLabel("", new ImageIcon("xd.jpg"), SwingConstants.CENTER));
        } catch (Exception e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        loadingFrame.setBounds(0, 0, 500, 333);
        loadingFrame.setLocationRelativeTo(null);
        loadingFrame.setVisible(true);
        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        loadingFrame.setVisible(false);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        double width = screenSize.getWidth();
        double height = screenSize.getHeight();
        JFrame frame = new JFrame();
        //---------------------------------------------------------------------------
        //frame.getContentPane().add(BorderLayout.CENTER, new JLabel("Welcome"));
        frame.setLocation(((int) (width / 2) - 400), ((int) (height / 2) - 250));
        frame.setVisible(true);
        frame.setSize(new Dimension(800, 500));
        frame.setResizable(false);
        //frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        //MainFrame.entranceClip.play();
        //---------------------------------------------------------------------------
        //panel z tekstem na gorze
        mainP = new JPanel();
        mainP.setPreferredSize(new Dimension(800, 500));
        ((FlowLayout) mainP.getLayout()).setVgap(0);
        //---------------------------------------------------------------------------
        //panel z tekstem na gorze
        textP = new OpaquePanel();
        textP.setPreferredSize(new Dimension(800, 50));
        ((FlowLayout) textP.getLayout()).setVgap(0);
        //---------------------------------------------------------------------------
        //tekst w panelu glownym
        JLabel text = new JLabel();
        text.setFont(new Font("Serif", Font.PLAIN, 30));
        text.setText("Football database application Kurek&Baszak v.1");
        text.setAlignmentX(Component.CENTER_ALIGNMENT);
        text.setAlignmentY(Component.BOTTOM_ALIGNMENT);

        textP.add(text);
        //---------------------------------------------------------------------------
        //panel z grafika i opcjami
        imageP = new PanelBackground();
        imageP.setPreferredSize(new Dimension(800, 450));

        imageP.setLayout(obok);
        ((FlowLayout) imageP.getLayout()).setVgap(0);
        //---------------------------------------------------------------------------
        //panel z przyciskamui menu
        mMenuP = new OpaquePanel();
        mMenuP.setPreferredSize(new Dimension(200, 412));
        ((FlowLayout) mMenuP.getLayout()).setVgap(0);
        mMenuP.setBorder(BorderFactory.createLineBorder(Color.black, 10));

        //---------------------------------------------------------------------------
        //panel przyciskow menu
        menuP = new OpaquePanel();
        menuP.setPreferredSize(new Dimension(200, 350));
        ((FlowLayout) menuP.getLayout()).setVgap(0);

        menuP.setLayout(new BoxLayout(menuP, BoxLayout.PAGE_AXIS));
        //---------------------------------------------------------------------------
        //panel wyjscia
        exitP = new OpaquePanel();
        exitP.setPreferredSize(new Dimension(200, 100));
        ((FlowLayout) exitP.getLayout()).setVgap(0);

        //---------------------------------------------------------------------------
        //panel danych
        dataP = new OpaquePanel();
        dataP.setPreferredSize(new Dimension(600, 412));
        ((FlowLayout) dataP.getLayout()).setVgap(0);
        dataP.setBorder(BorderFactory.createLineBorder(Color.black, 10));
        dataP.setLayout(new BorderLayout());
        //lista do wyboru oraz obszary wypisywania zawartosci

        //przycisk wyjscia
        MenuButton exitB = new MenuButton();
        exitB.setText("Wyjdz");
        exitB.setPreferredSize(new Dimension(100, 30));
        exitB.addActionListener(new ExitListener());

        exitP.add(BorderLayout.CENTER, exitB); //przypisanie przycisku wyjscia
        mMenuP.add(BorderLayout.NORTH, menuP); //przydzielenie miejsca panelowi z przyciskami menu
        mMenuP.add(BorderLayout.SOUTH, exitP);
        imageP.add(mMenuP);
        imageP.add(dataP);
        mainP.add(BorderLayout.NORTH, textP);
        mainP.add(BorderLayout.SOUTH, imageP);

        frame.getContentPane().add(BorderLayout.CENTER, mainP);
        //frame.getContentPane().add(BorderLayout.CENTER, imageP);
        SwingUtilities.updateComponentTreeUI(frame);
        loadingFrame.dispose();
        //System.out.println("Wysokosc glownego panelu:"+mainP.getPreferredSize());
        //System.out.println("Wysokosc glownego okna:"+frame.getPreferredSize());
        //System.out.println("Wysokosc panelu image:"+imageP.getPreferredSize());
    }

    public static void fillPanels() {
        //przycisk pilkarzy
        MenuButton playersB = new MenuButton();
        playersB.setText("Pilkarze");
        playersB.setPreferredSize(new Dimension(100, 30));
        playersB.setAlignmentX(Component.CENTER_ALIGNMENT);
        playersB.addActionListener(new PlayersListener());
        //---------------------------------------------------------------------------
        //przycisk druzyn
        MenuButton teamsB = new MenuButton();
        teamsB.setText("Druzyny");
        teamsB.setPreferredSize(new Dimension(100, 30));
        teamsB.setAlignmentX(Component.CENTER_ALIGNMENT);
        teamsB.addActionListener(new TeamsListener());
        //---------------------------------------------------------------------------
        //przycisk lig
        MenuButton leaguesB = new MenuButton();
        leaguesB.setText("Ligi");
        leaguesB.setPreferredSize(new Dimension(100, 30));
        leaguesB.setAlignmentX(Component.CENTER_ALIGNMENT);
        leaguesB.addActionListener(new LeaguesListener());
        //---------------------------------------------------------------------------
        //przycisk kraji
        MenuButton countriesB = new MenuButton();
        countriesB.setText("Kraje");
        countriesB.setPreferredSize(new Dimension(100, 30));
        countriesB.setAlignmentX(Component.CENTER_ALIGNMENT);
        countriesB.addActionListener(new CountriesListener());
        //---------------------------------------------------------------------------
        //przycisk sezonow
        MenuButton seasonsB = new MenuButton();
        seasonsB.setText("Sezony");
        seasonsB.setPreferredSize(new Dimension(100, 30));
        seasonsB.setAlignmentX(Component.CENTER_ALIGNMENT);
        seasonsB.addActionListener(new SeasonsListener());
        //---------------------------------------------------------------------------
        //przycisk sezonow
        MenuButton seasonsCB = new MenuButton();
        seasonsCB.setText("Sezony - powi�zania");
        seasonsCB.setPreferredSize(new Dimension(100, 30));
        seasonsCB.setAlignmentX(Component.CENTER_ALIGNMENT);
        seasonsCB.addActionListener(new SeasonsCListener());
        //---------------------------------------------------------------------------
        //przycisk sezonow
        MenuButton adminS = new MenuButton();
        adminS.setText("Zglos blad");
        adminS.setPreferredSize(new Dimension(100, 30));
        adminS.setAlignmentX(Component.CENTER_ALIGNMENT);
        adminS.addActionListener(new Report());

        menuP.add(Box.createVerticalStrut(15));
        menuP.add(playersB);
        menuP.add(Box.createVerticalStrut(20));
        menuP.add(teamsB);
        menuP.add(Box.createVerticalStrut(20));
        menuP.add(leaguesB);
        menuP.add(Box.createVerticalStrut(20));
        menuP.add(countriesB);
        menuP.add(Box.createVerticalStrut(20));
        menuP.add(seasonsB);
        menuP.add(Box.createVerticalStrut(20));
        menuP.add(seasonsCB);
        menuP.add(Box.createVerticalStrut(20));
        menuP.add(adminS);
        mainP.revalidate();
        mainP.repaint();
    }
}

//Druzyna listener

//Kraje listener

//Ligi listener

//Sezony Listener

//sluchacz powiazan sezonow


//sluchacz wyjscia
class ExitListener implements ActionListener {
    public void actionPerformed(ActionEvent zdarzenie) {
        System.exit(0);
    }
}

//sluchacz powrotu do Menu
class RepaintListener implements ActionListener {
    public void actionPerformed(ActionEvent zdarzenie) {
        MainFrame.dataP.removeAll();
        MainFrame.dataP.revalidate();
        MainFrame.dataP.repaint();
    }
}

//panel wyszukiwania po wybranych filtrach
class OpaquePanel extends JPanel {
    @Override
    public void paintComponent(Graphics g) {
        this.setOpaque(false);
    }
}

//Klasa definijaca tlo okienka glownego
class PanelBackground extends JPanel {
    private BufferedImage image;


    public PanelBackground() {
        super();
        //this.setLayout(new FlowLayout());
        //source path of image on my disc
        File imageFile = new File("xd2.jpg");
        try {
            image = ImageIO.read(imageFile);
            //image.getScaledInstance(1000, 400, Image.SCALE_DEFAULT);
        } catch (IOException e) {
            System.err.println("Blad odczytu obrazka");
            e.printStackTrace();
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.drawImage(image, 0, 0, this);
    }
}

class MenuButton extends JButton {
    public MenuButton() {
        super();
        this.setPreferredSize(new Dimension(100, 50));
    }
}
