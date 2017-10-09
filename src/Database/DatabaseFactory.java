package Database;

import Model.Team;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Class allows to add and retrieve database data easily
 * And cast them to objects or collections
 */
public class DatabaseFactory {

    private final Connection connection = DatabaseConnection.getInstance().getConnection();

    /**
     * Queries database for all teams
     *
     * @return ArrayList of [Team] object, containing all teams stored in PostgreSQL database
     */
    public ArrayList<Team> getTeams() {

        ArrayList<Team> listOfTeamsFromSQL = new ArrayList<>();

        try {
            PreparedStatement sqlStatement = connection.prepareStatement("SELECT * FROM teams");
            ResultSet sqlStatementResult = sqlStatement.executeQuery();
            while (sqlStatementResult.next()) {
                listOfTeamsFromSQL.add(new Team(
                        sqlStatementResult.getInt("team_id"),
                        sqlStatementResult.getString("team_name"),
                        sqlStatementResult.getInt("team_players")
                ));
            }

            sqlStatement.closeOnCompletion();

            return listOfTeamsFromSQL;

        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }

        return listOfTeamsFromSQL;
    }

    /**
     * Stores team in PostgreSQL database
     *
     * @param team [Team] object to be stored in database
     */
    public void addTeam(Team team) {
        try {

            PreparedStatement sqlStatement = connection.prepareStatement("INSERT INTO teams VALUES(?,?,?)");
            sqlStatement.setInt(1, team.getId());
            sqlStatement.setString(2, team.getName());
            sqlStatement.setInt(3, team.getPlayers());
            sqlStatement.executeUpdate();
            sqlStatement.closeOnCompletion();

        } catch (SQLException sqlException) {

            sqlException.printStackTrace();

        }
    }

    /**
     * Queries SQL for team, using team name
     *
     * @param teamName Team name as String
     * @return Null if not found, [Team] object if found
     */
    public Team findTeamByName(String teamName) {

        Team team;

        try {
            PreparedStatement sqlStatement = connection.prepareStatement("SELECT * FROM teams WHERE team_name = ?;");
            sqlStatement.setString(1, teamName);
            ResultSet sqlStatementResult = sqlStatement.executeQuery();

            if (sqlStatementResult.next()) {
                team = new Team(
                        sqlStatementResult.getInt("team_id"),
                        sqlStatementResult.getString("team_name"),
                        sqlStatementResult.getInt("team_players")
                );
                sqlStatement.closeOnCompletion();
                return team;

            }

        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }

        return null;
    }

    /**
     * Removes entity from database using teamName
     *
     * @param teamName name of the team to remove
     */
    public void deleteTeamByName(String teamName) {
        try {
            PreparedStatement sqlStatement = connection.prepareStatement("DELETE FROM teams WHERE team_name = ?;");
            sqlStatement.setString(1, teamName);
            sqlStatement.executeUpdate();

        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
    }
}
