package Database;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    private static DatabaseConnection INSTANCE;
    private static Connection connection = null;

    public DatabaseConnection() {

        try {
            Class.forName("org.postgresql.Driver");

            connection = DriverManager.getConnection(
                    "jdbc:postgresql://127.0.0.1:5432/BD2",
                    "igor",
                    "");
            System.out.println("Successfully connected to database!");

        } catch (ClassNotFoundException e) {

            e.printStackTrace();
            System.out.append("No PostgreSQL library found, include library in project directory.");

        } catch (SQLException e) {

            e.printStackTrace();
            System.out.append("Database login failed! Check Your username and/or password.");

        }
    }

    public static DatabaseConnection getInstance() {
        if (INSTANCE == null) {
            synchronized (DatabaseConnection.class) {
                if (INSTANCE == null) {
                    INSTANCE = new DatabaseConnection();
                    System.out.println("Database connection instance not found, created new one.");
                }
            }
        } else {
            System.out.println("Database connection instance found.");
        }

        return INSTANCE;
    }

    public Connection getConnection() {
        return connection;
    }


}
