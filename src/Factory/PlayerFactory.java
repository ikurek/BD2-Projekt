package Factory;

import Database.DatabaseConnection;
import Model.Player;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PlayerFactory {

    private final Connection connection = DatabaseConnection.getInstance().getConnection();

    /**
     * Queries database for all players
     *
     * @return ArrayList of [Player] object, containing all teams stored in PostgreSQL database
     */
    public ArrayList<Player> getPlayers() {

        ArrayList<Player> listOfPlayers = new ArrayList<>();

        try {
            PreparedStatement sqlStatement = connection.prepareStatement("SELECT * FROM players");
            ResultSet sqlStatementResult = sqlStatement.executeQuery();
            while (sqlStatementResult.next()) {
                listOfPlayers.add(new Player(
                        sqlStatementResult.getInt("id"),
                        sqlStatementResult.getString("name"),
                        sqlStatementResult.getString("surname"),
                        sqlStatementResult.getString("team"),
                        sqlStatementResult.getInt("rank")
                ));
            }

            sqlStatement.closeOnCompletion();

            return listOfPlayers;

        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }

        return listOfPlayers;
    }

    /**
     * Stores team in PostgreSQL database
     *
     * @param player [Player] object to be stored in database
     */
    public void addPlayer(Player player) {
        try {

            PreparedStatement sqlStatement = connection.prepareStatement("INSERT INTO players(name, surname, team, rank) VALUES(?,?,?,?)");
            sqlStatement.setString(1, player.getName());
            sqlStatement.setString(2, player.getSurname());
            sqlStatement.setString(3, player.getTeam());
            sqlStatement.setInt(4, player.getRank());
            sqlStatement.executeUpdate();
            sqlStatement.closeOnCompletion();

        } catch (SQLException sqlException) {

            sqlException.printStackTrace();

        }
    }

    /**
     * Queries SQL for team, using player name
     *
     * @param playerName    1st name of player
     * @param playerSurname 2nd name of player
     * @return Null if not found, ArrayList<Player> object if found
     */
    public ArrayList<Player> findPlayerByName(String playerName, String playerSurname) {

        ArrayList<Player> listOfPlayers = new ArrayList<>();

        try {
            PreparedStatement sqlStatement = connection.prepareStatement("SELECT * FROM players WHERE name = ? AND surname = ?;");
            sqlStatement.setString(1, playerName);
            sqlStatement.setString(2, playerSurname);
            ResultSet sqlStatementResult = sqlStatement.executeQuery();

            while (sqlStatementResult.next()) {
                listOfPlayers.add(new Player(
                        sqlStatementResult.getInt("id"),
                        sqlStatementResult.getString("name"),
                        sqlStatementResult.getString("surname"),
                        sqlStatementResult.getString("team"),
                        sqlStatementResult.getInt("rank"))
                );
            }

            return listOfPlayers;

        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }

        return null;
    }

    /**
     * Removes entity from table /players/
     *
     * @param player [Player] object to remove from database
     */
    public void deletePlayer(Player player) {
        try {
            PreparedStatement sqlStatement = connection.prepareStatement(
                    "DELETE " +
                            "FROM players " +
                            "WHERE id = ? AND name = ? AND surname = ?;");
            sqlStatement.setInt(1, player.getId());
            sqlStatement.setString(2, player.getName());
            sqlStatement.setString(3, player.getSurname());
            sqlStatement.executeUpdate();
            sqlStatement.closeOnCompletion();

        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
    }

    /**
     * Updates entity in SQL using it's name and [Team] object
     *
     * @param oldPlayer [Player] object that will be replaced with new data
     * @param newPlayer [Player] object containing data, that will be used to update entity
     */
    public void updatePlayer(Player oldPlayer, Player newPlayer) {
        try {
            PreparedStatement sqlStatement = connection.prepareStatement(
                    "UPDATE teams " +
                            "SET name = ?, surname = ?, team = ?, rank = ?  " +
                            "WHERE id = ?;");
            sqlStatement.setString(1, newPlayer.getName());
            sqlStatement.setString(2, newPlayer.getSurname());
            sqlStatement.setString(3, newPlayer.getTeam());
            sqlStatement.setInt(4, newPlayer.getRank());
            sqlStatement.setInt(5, oldPlayer.getId());
            sqlStatement.executeUpdate();
            sqlStatement.closeOnCompletion();

        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
    }
}
