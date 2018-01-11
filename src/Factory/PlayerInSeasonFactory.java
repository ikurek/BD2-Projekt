package Factory;

import Database.DatabaseConnection;
import Model.PlayerInSeason;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PlayerInSeasonFactory {

    private final Connection connection = DatabaseConnection.getInstance().getConnection();

    /**
     * Queries database for all players
     *
     * @return ArrayList of [Player] object, containing all teams stored in PostgreSQL database
     */
    public ArrayList<PlayerInSeason> getPlayersInSeason() {

        ArrayList<PlayerInSeason> listOfPlayers = new ArrayList<>();

        try {
            PreparedStatement sqlStatement = connection.prepareStatement("SELECT * FROM season_players");
            ResultSet sqlStatementResult = sqlStatement.executeQuery();
            while (sqlStatementResult.next()) {
                listOfPlayers.add(new PlayerInSeason(
                        sqlStatementResult.getInt("id"),
                        sqlStatementResult.getInt("season_id"),
                        sqlStatementResult.getInt("player_id"),
                        sqlStatementResult.getInt("goals"),
                        sqlStatementResult.getInt("assists"),
                        sqlStatementResult.getInt("fouls"),
                        sqlStatementResult.getInt("cards"),
                        sqlStatementResult.getInt("rating")
                ));
            }

            sqlStatement.closeOnCompletion();

            return listOfPlayers;

        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }

        return null;
    }


    public void addPlayerInSeason(PlayerInSeason playerInSeason) {
        try {

            PreparedStatement sqlStatement = connection.prepareStatement("INSERT INTO season_players(season_id, player_id, goals, assists, fouls, cards, rating) VALUES(?,?,?,?,?,?,?)");
            sqlStatement.setInt(1, playerInSeason.getSeasonID());
            sqlStatement.setInt(2, playerInSeason.getPlayerID());
            sqlStatement.setInt(3, playerInSeason.getGoals());
            sqlStatement.setInt(4, playerInSeason.getAssists());
            sqlStatement.setInt(5, playerInSeason.getFouls());
            sqlStatement.setInt(6, playerInSeason.getCards());
            sqlStatement.setInt(7, playerInSeason.getRate());

            sqlStatement.executeUpdate();
            sqlStatement.closeOnCompletion();

        } catch (SQLException sqlException) {

            sqlException.printStackTrace();

        }
    }


    public void deletePlayer(PlayerInSeason playerInSeason) {
        try {
            PreparedStatement sqlStatement = connection.prepareStatement(
                    "DELETE " +
                            "FROM season_players " +
                            "WHERE id = ?;");
            sqlStatement.setInt(1, playerInSeason.getId());
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
    public void updatePlayerInSeason(PlayerInSeason oldPlayer, PlayerInSeason newPlayer) {
        try {
            PreparedStatement sqlStatement = connection.prepareStatement(
                    "UPDATE season_players " +
                            "SET season_id = ?, player_id = ?, goals = ?, assists = ?, fouls = ?, cards = ?, rating = ?  " +
                            "WHERE id = ?;");
            sqlStatement.setInt(1, newPlayer.getSeasonID());
            sqlStatement.setInt(2, newPlayer.getPlayerID());
            sqlStatement.setInt(3, newPlayer.getGoals());
            sqlStatement.setInt(4, newPlayer.getAssists());
            sqlStatement.setInt(5, newPlayer.getFouls());
            sqlStatement.setInt(6, newPlayer.getCards());
            sqlStatement.setInt(7, newPlayer.getRate());
            sqlStatement.setInt(8, oldPlayer.getId());
            sqlStatement.executeUpdate();
            sqlStatement.closeOnCompletion();

        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
    }

}
