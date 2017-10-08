package Database;

import Model.Team;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DatabaseFactory {

    private Connection connection = DatabaseConnection.getInstance().getConnection();

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
}
