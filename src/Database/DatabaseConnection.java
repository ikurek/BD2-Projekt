package Database;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * This singleton class stores connection to PostgreSQL database
 * Do not create new objects of this class, use getInstance() method instead!
 */
class DatabaseConnection {

    private static DatabaseConnection INSTANCE;
    private static Connection connection = null;

    /**
     * Constructor creates and keeps alive database connection
     * Connection is later stored in instance of this class
     */
    private DatabaseConnection() {

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

    /**
     * This method is a thread-safe Java singleton handler
     * It detects if new object should be created, or should it be retrieved from already existing instance
     *
     * @return an object of type [DatabaseConnection]
     */
    static DatabaseConnection getInstance() {
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

    /**
     * Required by database factory to call statements
     *
     * @return [Connection] object created by this class constructor
     */
    Connection getConnection() {
        return connection;
    }


}
