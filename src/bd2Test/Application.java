package bd2Test;

import Data.SystemData;
import Database.DatabaseConnection;
import GUI.LoginFrame;
import GUI.MainFrame;

public class Application {
    static DatabaseConnection connection;
    SystemData systemData;
    MainFrame gui;
    LoginFrame login;

    public Application() {
        connection = null;
        //login = new LoginFrame();
        //while(!login.setConnection()) {
        //
        //}
        systemData = new SystemData(null);
        systemData.uploadToDB();
        gui = new MainFrame(systemData);
    }

    public static void main(String[] args) {
        Application app = new Application();

    }

    public static DatabaseConnection getConnection() {
        return connection;
    }

    public static void setConnection(DatabaseConnection newCon) {
        connection = newCon;
    }

    public static DatabaseConnection getConnection1() {
        return connection;
    }

}
