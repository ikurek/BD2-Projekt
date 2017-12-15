package Factory;

import Database.DatabaseConnection;
import Model.TeamInSeason;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class TeamInSeasonFactory {

    private final Connection connection = DatabaseConnection.getInstance().getConnection();

    public ArrayList<TeamInSeason> getTeamsInSeason() {

        ArrayList<TeamInSeason> listOfTeamsInSeason = new ArrayList<>();

        try {
            PreparedStatement sqlStatement = connection.prepareStatement("SELECT * FROM season_teams");
            ResultSet sqlStatementResult = sqlStatement.executeQuery();
            while (sqlStatementResult.next()) {
                listOfTeamsInSeason.add(new TeamInSeason(
                        sqlStatementResult.getInt("id"),
                        sqlStatementResult.getInt("season_id"),
                        sqlStatementResult.getInt("team_id"),
                        sqlStatementResult.getString("growth"),
                        sqlStatementResult.getInt("matches"),
                        sqlStatementResult.getInt("matches_won"),
                        sqlStatementResult.getInt("matches_lost"),
                        sqlStatementResult.getInt("goals"),
                        sqlStatementResult.getInt("goals_lost"),
                        sqlStatementResult.getInt("ladder")
                ));
            }

            sqlStatement.closeOnCompletion();

            return listOfTeamsInSeason;

        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }

        return listOfTeamsInSeason;
    }

    public void addTeamInSeason(TeamInSeason teamInSeason) {
        try {

            PreparedStatement sqlStatement = connection.prepareStatement("INSERT INTO season_teams(season_id, team_id, growth, matches, matches_won, matches_lost, goals, goals_lost, ladder) VALUES(?,?,?,?,?,?,?,?,?)");
            sqlStatement.setInt(1, teamInSeason.getSeasonID());
            sqlStatement.setInt(2, teamInSeason.getTeamID());
            sqlStatement.setString(3, teamInSeason.getGrowth());
            sqlStatement.setInt(4, teamInSeason.getMatches());
            sqlStatement.setInt(5, teamInSeason.getMatchesWon());
            sqlStatement.setInt(6, teamInSeason.getMatchesLost());
            sqlStatement.setInt(7, teamInSeason.getGoals());
            sqlStatement.setInt(8, teamInSeason.getGoalsLost());
            sqlStatement.setInt(9, teamInSeason.getLadder());

            sqlStatement.executeUpdate();
            sqlStatement.closeOnCompletion();

        } catch (SQLException sqlException) {

            sqlException.printStackTrace();

        }
    }

    public void deleteTeamInSeason(TeamInSeason teamInSeason) {

        try {
            PreparedStatement sqlStatement = connection.prepareStatement(
                    "DELETE " +
                            "FROM season_teams " +
                            "WHERE id = ?;");
            sqlStatement.setInt(1, teamInSeason.getId());
            sqlStatement.executeUpdate();
            sqlStatement.closeOnCompletion();

        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
    }


    public void updateTeamInSeason(TeamInSeason oldTeam, TeamInSeason newTeam) {
        try {
            PreparedStatement sqlStatement = connection.prepareStatement(
                    "UPDATE season_teams " +
                            "SET season_id = ?, team_id = ?, growth = ?, matches = ?, matches_won = ?, matches_lost = ?, goals = ?, goals_lost = ?, ladder = ?  " +
                            "WHERE id = ?;");
            sqlStatement.setInt(1, newTeam.getSeasonID());
            sqlStatement.setInt(2, newTeam.getTeamID());
            sqlStatement.setString(3, newTeam.getGrowth());
            sqlStatement.setInt(4, newTeam.getMatches());
            sqlStatement.setInt(5, newTeam.getMatchesWon());
            sqlStatement.setInt(6, newTeam.getMatchesLost());
            sqlStatement.setInt(7, newTeam.getGoals());
            sqlStatement.setInt(8, newTeam.getGoalsLost());
            sqlStatement.setInt(9, newTeam.getLadder());
            sqlStatement.setInt(10, oldTeam.getId());

            sqlStatement.executeUpdate();
            sqlStatement.closeOnCompletion();

        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
    }

}
