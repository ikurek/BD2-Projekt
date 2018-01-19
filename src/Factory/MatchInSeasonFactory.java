package Factory;

import Database.DatabaseConnection;
import Model.MatchInSeason;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class MatchInSeasonFactory {

    private final Connection connection = DatabaseConnection.getInstance().getConnection();

    public ArrayList<MatchInSeason> getMatchesInSeason() {

        ArrayList<MatchInSeason> listOfMatchesInSeason = new ArrayList<>();

        try {
            PreparedStatement sqlStatement = connection.prepareStatement("SELECT * FROM season_matches");
            ResultSet sqlStatementResult = sqlStatement.executeQuery();
            while (sqlStatementResult.next()) {
                listOfMatchesInSeason.add(new MatchInSeason(
                        sqlStatementResult.getInt("id"),
                        sqlStatementResult.getInt("season_id"),
                        sqlStatementResult.getString("team1"),
                        sqlStatementResult.getString("team2"),
                        sqlStatementResult.getInt("goals1"),
                        sqlStatementResult.getInt("goals2")
                ));
            }

            sqlStatement.closeOnCompletion();

            return listOfMatchesInSeason;

        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }

        return listOfMatchesInSeason;
    }

    public static ArrayList<MatchInSeason> getMatchesInSeason(Connection connection) {

        ArrayList<MatchInSeason> listOfMatchesInSeason = new ArrayList<>();

        try {
            PreparedStatement sqlStatement = connection.prepareStatement("SELECT * FROM season_matches");
            ResultSet sqlStatementResult = sqlStatement.executeQuery();
            while (sqlStatementResult.next()) {
                listOfMatchesInSeason.add(new MatchInSeason(
                        sqlStatementResult.getInt("id"),
                        sqlStatementResult.getInt("season_id"),
                        sqlStatementResult.getString("team1"),
                        sqlStatementResult.getString("team2"),
                        sqlStatementResult.getInt("goals1"),
                        sqlStatementResult.getInt("goals2")
                ));
            }

            sqlStatement.closeOnCompletion();

            return listOfMatchesInSeason;

        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }

        return listOfMatchesInSeason;
    }

    public void addMatchInSeason(MatchInSeason matchInSeason) {
        try {

            PreparedStatement sqlStatement = connection.prepareStatement("INSERT INTO season_matches(season_id, team1, team2, goals1, goals2) VALUES(?,?,?,?,?)");
            sqlStatement.setInt(1, matchInSeason.getSeasonID());
            sqlStatement.setString(2, matchInSeason.getTeam1());
            sqlStatement.setString(3, matchInSeason.getTeam2());
            sqlStatement.setInt(4, matchInSeason.getGoals1());
            sqlStatement.setInt(5, matchInSeason.getGoals2());
            sqlStatement.executeUpdate();
            sqlStatement.closeOnCompletion();

        } catch (SQLException sqlException) {

            sqlException.printStackTrace();

        }
    }

    public ArrayList<MatchInSeason> findMatchByTeam(String team) {

        ArrayList<MatchInSeason> listOfMatchesInSeason = new ArrayList<>();

        try {
            PreparedStatement sqlStatement = connection.prepareStatement("SELECT * FROM season_matches WHERE team1 = ? OR team2 = ?;");
            sqlStatement.setString(1, team);
            ResultSet sqlStatementResult = sqlStatement.executeQuery();

            while (sqlStatementResult.next()) {
                listOfMatchesInSeason.add(new MatchInSeason(
                        sqlStatementResult.getInt("id"),
                        sqlStatementResult.getInt("season_id"),
                        sqlStatementResult.getString("team1"),
                        sqlStatementResult.getString("team2"),
                        sqlStatementResult.getInt("goals1"),
                        sqlStatementResult.getInt("goals2")
                ));
            }

            return listOfMatchesInSeason;

        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }

        return null;
    }

    public void deleteMatchInSeason(MatchInSeason matchInSeason) {

        try {
            PreparedStatement sqlStatement = connection.prepareStatement(
                    "DELETE " +
                            "FROM season_matches " +
                            "WHERE id = ?;");
            sqlStatement.setInt(1, matchInSeason.getId());
            sqlStatement.executeUpdate();
            sqlStatement.closeOnCompletion();

        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
    }

    public void updateMatchInSeason(MatchInSeason oldMatch, MatchInSeason newMatch) {
        try {
            PreparedStatement sqlStatement = connection.prepareStatement(
                    "UPDATE teams " +
                            "SET season_id = ?, team1 = ?, team2 = ?, goals1 = ?, goals2 = ?  " +
                            "WHERE id = ?;");
            sqlStatement.setInt(1, newMatch.getSeasonID());
            sqlStatement.setString(2, newMatch.getTeam1());
            sqlStatement.setString(3, newMatch.getTeam2());
            sqlStatement.setInt(4, newMatch.getGoals1());
            sqlStatement.setInt(5, newMatch.getGoals2());
            sqlStatement.setInt(6, oldMatch.getId());
            sqlStatement.executeUpdate();
            sqlStatement.closeOnCompletion();

        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
    }

}