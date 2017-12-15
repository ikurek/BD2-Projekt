package Factory;

import Database.DatabaseConnection;
import Model.Season;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class SeasonFactory {

    private final Connection connection = DatabaseConnection.getInstance().getConnection();

    /**
     * Queries database for all players
     *
     * @return ArrayList of [Player] object, containing all teams stored in PostgreSQL database
     */
    public ArrayList<Season> getSeasons() {

        ArrayList<Season> listOfSeasons = new ArrayList<>();

        try {
            PreparedStatement sqlStatement = connection.prepareStatement("SELECT * FROM seasons");
            ResultSet sqlStatementResult = sqlStatement.executeQuery();
            while (sqlStatementResult.next()) {
                listOfSeasons.add(new Season(
                        sqlStatementResult.getInt("id"),
                        sqlStatementResult.getString("name"),
                        sqlStatementResult.getDate("open_date"),
                        sqlStatementResult.getDate("close_date"),
                        sqlStatementResult.getString("winner")
                ));
            }

            sqlStatement.closeOnCompletion();

            return listOfSeasons;

        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }

        return listOfSeasons;
    }


    public void addSeason(Season season) {
        try {

            PreparedStatement sqlStatement = connection.prepareStatement("INSERT INTO seasons(name, open_date, close_date, winner) VALUES(?,?,?,?)");
            sqlStatement.setString(1, season.getName());
            sqlStatement.setDate(2, season.getOpenDate());
            sqlStatement.setDate(3, season.getCloseDate());
            sqlStatement.setString(4, season.getWinner());
            sqlStatement.executeUpdate();
            sqlStatement.closeOnCompletion();

        } catch (SQLException sqlException) {

            sqlException.printStackTrace();

        }
    }


    public void updateSeason(Season oldSeason, Season newSeason) {
        try {
            PreparedStatement sqlStatement = connection.prepareStatement(
                    "UPDATE teams " +
                            "SET name = ?, open_date = ?, close_date = ?, winner = ?  " +
                            "WHERE id = ?;");
            sqlStatement.setString(1, newSeason.getName());
            sqlStatement.setDate(2, newSeason.getOpenDate());
            sqlStatement.setDate(3, newSeason.getCloseDate());
            sqlStatement.setString(4, newSeason.getWinner());
            sqlStatement.setInt(5, oldSeason.getId());
            sqlStatement.executeUpdate();
            sqlStatement.closeOnCompletion();

        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
    }
}
