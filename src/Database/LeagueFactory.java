package Database;

import Model.League;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class LeagueFactory {

    private final Connection connection = DatabaseConnection.getInstance().getConnection();

    /**
     * Queries database for all leagues
     *
     * @return ArrayList of [League] object, containing all leagues stored in PostgreSQL database
     */
    public ArrayList<League> getLeagues() {

        ArrayList<League> listOfLeagues = new ArrayList<>();

        try {
            PreparedStatement sqlStatement = connection.prepareStatement("SELECT * FROM leagues");
            ResultSet sqlStatementResult = sqlStatement.executeQuery();
            while (sqlStatementResult.next()) {
                listOfLeagues.add(new League(
                        sqlStatementResult.getInt("id"),
                        sqlStatementResult.getString("name")
                ));
            }

            sqlStatement.closeOnCompletion();

            return listOfLeagues;

        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }

        return listOfLeagues;
    }

    /**
     * Stores league in PostgreSQL database
     *
     * @param league [League] object to be stored in database
     */
    public void addLeague(League league) {
        try {

            PreparedStatement sqlStatement = connection.prepareStatement("INSERT INTO leagues(name) VALUES(?)");
            sqlStatement.setString(1, league.getName());
            sqlStatement.executeUpdate();
            sqlStatement.closeOnCompletion();

        } catch (SQLException sqlException) {

            sqlException.printStackTrace();

        }
    }

    /**
     * Queries SQL for countries, using country name
     *
     * @param leagueName name of league to find
     * @return Null if not found, ArrayList<League> object if found
     */
    public ArrayList<League> findLeagueByName(String leagueName) {

        ArrayList<League> listOfLeagues = new ArrayList<>();

        try {
            PreparedStatement sqlStatement = connection.prepareStatement("SELECT * FROM leagues WHERE name = ?;");
            sqlStatement.setString(1, leagueName);
            ResultSet sqlStatementResult = sqlStatement.executeQuery();

            while (sqlStatementResult.next()) {
                listOfLeagues.add(new League(
                        sqlStatementResult.getInt("id"),
                        sqlStatementResult.getString("name")
                ));
            }

            return listOfLeagues;

        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }

        return null;
    }

    /**
     * Removes entity from table /leagues/
     *
     * @param league [League] object to remove from database
     */
    public void deleteLeague(League league) {
        try {
            PreparedStatement sqlStatement = connection.prepareStatement(
                    "DELETE " +
                            "FROM leagues " +
                            "WHERE id = ? AND name = ?;");
            sqlStatement.setInt(1, league.getId());
            sqlStatement.setString(2, league.getName());
            sqlStatement.executeUpdate();
            sqlStatement.closeOnCompletion();

        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
    }
}
