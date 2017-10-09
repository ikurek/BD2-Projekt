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
public class TeamFactory {

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
     * @return Null if not found, ArrayList<Team> object if found
     */
    public ArrayList<Team> findTeamByName(String teamName) {

        ArrayList<Team> listOfTeams = new ArrayList<>();

        try {
            PreparedStatement sqlStatement = connection.prepareStatement("SELECT * FROM teams WHERE name = ?;");
            sqlStatement.setString(1, teamName);
            ResultSet sqlStatementResult = sqlStatement.executeQuery();

            while (sqlStatementResult.next()) {
                listOfTeams.add(new Team(
                        sqlStatementResult.getInt("id"),
                        sqlStatementResult.getString("name"),
                        sqlStatementResult.getString("country"),
                        sqlStatementResult.getString("league"),
                        sqlStatementResult.getInt("rank"))
                );
            }

            sqlStatement.closeOnCompletion();
            return listOfTeams;

        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }

        return null;
    }

    /**
     * Removes entity from table /teams/
     *
     * @param team [Team] object to be removed
     */
    public void deleteTeam(Team team) {
        try {
            PreparedStatement sqlStatement = connection.prepareStatement(
                    "DELETE " +
                            "FROM teams " +
                            "WHERE id = ?;");
            sqlStatement.setInt(1, team.getId());
            sqlStatement.executeUpdate();
            sqlStatement.closeOnCompletion();

        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
    }

    /**
     * Updates entity in SQL using it's name and [Team] object
     *
     * @param oldTeam [Team] object that will be updated
     * @param newTeam [Team] object containing data, that will be used to update entity
     */
    public void updateTeam(Team oldTeam, Team newTeam) {
        try {
            PreparedStatement sqlStatement = connection.prepareStatement(
                    "UPDATE teams " +
                            "SET name = ?, country = ?, league = ?, rank = ?  " +
                            "WHERE id = ?;");
            sqlStatement.setString(1, newTeam.getName());
            sqlStatement.setString(2, newTeam.getCountry());
            sqlStatement.setString(3, newTeam.getLeague());
            sqlStatement.setInt(4, newTeam.getRank());
            sqlStatement.setInt(5, oldTeam.getId());
            sqlStatement.executeUpdate();
            sqlStatement.closeOnCompletion();

        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
    }
}
