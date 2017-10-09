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
                        sqlStatementResult.getInt("id"),
                        sqlStatementResult.getString("name"),
                        sqlStatementResult.getString("country"),
                        sqlStatementResult.getString("league"),
                        sqlStatementResult.getInt("rank")
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

            PreparedStatement sqlStatement = connection.prepareStatement("INSERT INTO teams(name, country, league, rank) VALUES(?,?,?,?)");
            sqlStatement.setString(1, team.getName());
            sqlStatement.setString(2, team.getCountry());
            sqlStatement.setString(3, team.getLeague());
            sqlStatement.setInt(4, team.getRank());
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
            PreparedStatement sqlStatement = connection.prepareStatement("SELECT * FROM teams WHERE name = ?;");
            sqlStatement.setString(1, teamName);
            ResultSet sqlStatementResult = sqlStatement.executeQuery();

            if (sqlStatementResult.next()) {
                team = new Team(
                        sqlStatementResult.getInt("id"),
                        sqlStatementResult.getString("name"),
                        sqlStatementResult.getString("country"),
                        sqlStatementResult.getString("league"),
                        sqlStatementResult.getInt("rank")
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
            PreparedStatement sqlStatement = connection.prepareStatement(
                    "DELETE " +
                            "FROM teams " +
                            "WHERE name = ?;");
            sqlStatement.setString(1, teamName);
            sqlStatement.executeUpdate();
            sqlStatement.closeOnCompletion();

        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
    }

    /**
     * Updates entity in SQL using it's name and [Team] object
     *
     * @param teamname Name of the team that will be updated
     * @param team     [Team] object containing data, that will be used to update entity
     */
    public void updateTeam(String teamname, Team team) {
        try {
            PreparedStatement sqlStatement = connection.prepareStatement(
                    "UPDATE teams " +
                            "SET name = ?, country = ?, league = ?, rank = ?  " +
                            "WHERE name = ?;");
            sqlStatement.setString(1, team.getName());
            sqlStatement.setString(2, team.getCountry());
            sqlStatement.setString(3, team.getLeague());
            sqlStatement.setInt(4, team.getRank());
            sqlStatement.setString(5, teamname);
            sqlStatement.executeUpdate();
            sqlStatement.closeOnCompletion();

        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
    }
}
