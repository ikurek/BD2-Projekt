package Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * This singleton class stores connection to PostgreSQL database
 * Do not create new objects of this class, use getInstance() method instead!
 */
public class DatabaseConnection {

    private static DatabaseConnection INSTANCE;
    private static Connection connection = null;

    /**
     * Constructor creates and keeps alive database connection
     * Connection is later stored in instance of this class
     */
    public DatabaseConnection() {

        try {
            Class.forName("org.postgresql.Driver");

            connection = DriverManager.getConnection(
                    "jdbc:postgresql://127.0.0.1:5432/BD",
                    "igor",
                    "");
            System.out.println("Successfully connected to database!");

            rebuildDatabase();

        } catch (ClassNotFoundException e) {

            e.printStackTrace();
            System.out.append("No PostgreSQL library found, include library in project directory.");

        } catch (SQLException e) {

            e.printStackTrace();
            System.out.append("Database login failed! Check Your username and/or password.");

        }
    }

    public DatabaseConnection(String password, String username) {

        try {
            Class.forName("org.postgresql.Driver");

            connection = DriverManager.getConnection(
                    "jdbc:postgresql://127.0.0.1:5432/BD",
                    username,
                    password);
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
    public static DatabaseConnection getInstance() {
        if (INSTANCE == null) {
            synchronized (DatabaseConnection.class) {
                if (INSTANCE == null) {
                    INSTANCE = new DatabaseConnection();
                    System.out.println("Database connection instance not found, created new one.");
                }
            }
        }

        return INSTANCE;
    }

    /**
     * Required by database factory to call statements
     *
     * @return [Connection] object created by this class constructor
     */
    public Connection getConnection() {
        return connection;
    }

    /**
     * This method drops all tables in database and recreates them
     * Removes all entries from database
     */
    public void rebuildDatabase() {

        System.out.println("Rebuilding database...");

        try {

            PreparedStatement dropStatement = connection.prepareStatement(
                    "DROP TABLE IF EXISTS teams; " +
                            "DROP TABLE IF EXISTS players; " +
                            "DROP TABLE IF EXISTS countries; " +
                            "DROP TABLE IF EXISTS leagues;" +
                            "DROP TABLE IF EXISTS seasons;" +
                            "DROP TABLE IF EXISTS season_teams;" +
                            "DROP TABLE IF EXISTS season_players;" +
                            "DROP TABLE IF EXISTS season_matches;");

            dropStatement.execute();

            PreparedStatement createStatement = connection.prepareStatement(
                    "CREATE TABLE IF NOT EXISTS teams (id SERIAL, name TEXT, country TEXT, league TEXT, rank INTEGER); " +
                            "CREATE TABLE IF NOT EXISTS players (id SERIAL, name TEXT, surname TEXT, team TEXT, rank INTEGER); " +
                            "CREATE TABLE IF NOT EXISTS countries (id SERIAL, name TEXT); " +
                            "CREATE TABLE IF NOT EXISTS leagues (id SERIAL, name TEXT);" +
                            "CREATE TABLE IF NOT EXISTS seasons(id SERIAL, name TEXT, open_date DATE, close_date DATE, winner TEXT);" +
                            "CREATE TABLE IF NOT EXISTS season_teams(id SERIAL, season_id INTEGER, team_id INTEGER, growth TEXT, matches INTEGER, matches_won INTEGER, matches_lost INTEGER, goals INTEGER, goals_lost INTEGER, ladder INTEGER);" +
                            "CREATE TABLE IF NOT EXISTS season_players(id SERIAL, season_id INTEGER, player_id INTEGER, goals INTEGER, assists INTEGER, fouls INTEGER, cards INTEGER, rating INTEGER);" +
                            "CREATE TABLE IF NOT EXISTS season_matches(id SERIAL, season_id INTEGER, team1 TEXT, team2 TEXT, goals1 TEXT, goals2 TEXT);");
            createStatement.execute();


        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }

        System.out.println("Database rebuild finished!");

    }


}