package Factory;

import Database.DatabaseConnection;
import Model.Country;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CountryFactory {

    private final Connection connection = DatabaseConnection.getInstance().getConnection();

    /**
     * Queries database for all countries
     *
     * @return ArrayList of [Country] object, containing all countries stored in PostgreSQL database
     */
    public ArrayList<Country> getCountries() {

        ArrayList<Country> listOfCountries = new ArrayList<>();

        try {
            PreparedStatement sqlStatement = connection.prepareStatement("SELECT * FROM countries");
            ResultSet sqlStatementResult = sqlStatement.executeQuery();
            while (sqlStatementResult.next()) {
                listOfCountries.add(new Country(
                        sqlStatementResult.getInt("id"),
                        sqlStatementResult.getString("name")
                ));
            }

            sqlStatement.closeOnCompletion();

            return listOfCountries;

        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }

        return listOfCountries;
    }

    public static ArrayList<Country> getCountries(Connection connection) {

        ArrayList<Country> listOfCountries = new ArrayList<>();

        try {
            PreparedStatement sqlStatement = connection.prepareStatement("SELECT * FROM countries");
            ResultSet sqlStatementResult = sqlStatement.executeQuery();
            while (sqlStatementResult.next()) {
                listOfCountries.add(new Country(
                        sqlStatementResult.getInt("id"),
                        sqlStatementResult.getString("name")
                ));
            }

            sqlStatement.closeOnCompletion();

            return listOfCountries;

        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }

        return listOfCountries;
    }

    /**
     * Stores country in PostgreSQL database
     *
     * @param country [Country] object to be stored in database
     */
    public void addCountry(Country country) {
        try {

            PreparedStatement sqlStatement = connection.prepareStatement("INSERT INTO countries(name) VALUES(?)");
            sqlStatement.setString(1, country.getName());
            sqlStatement.executeUpdate();
            sqlStatement.closeOnCompletion();

        } catch (SQLException sqlException) {

            sqlException.printStackTrace();

        }
    }

    /**
     * Queries SQL for countries, using country name
     *
     * @param countryName name of country to find
     * @return Null if not found, ArrayList<Country> object if found
     */
    public ArrayList<Country> findCountryByName(String countryName) {

        ArrayList<Country> listOfCountries = new ArrayList<>();

        try {
            PreparedStatement sqlStatement = connection.prepareStatement("SELECT * FROM countries WHERE name = ?;");
            sqlStatement.setString(1, countryName);
            ResultSet sqlStatementResult = sqlStatement.executeQuery();

            while (sqlStatementResult.next()) {
                listOfCountries.add(new Country(
                        sqlStatementResult.getInt("id"),
                        sqlStatementResult.getString("name")
                ));
            }

            return listOfCountries;

        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }

        return null;
    }

    /**
     * Removes entity from table /countries/
     *
     * @param country [Country] object to remove from database
     */
    public void deleteCountry(Country country) {
        try {
            PreparedStatement sqlStatement = connection.prepareStatement(
                    "DELETE " +
                            "FROM countries " +
                            "WHERE id = ? AND name = ?;");
            sqlStatement.setInt(1, country.getId());
            sqlStatement.setString(2, country.getName());
            sqlStatement.executeUpdate();
            sqlStatement.closeOnCompletion();

        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
    }
}
