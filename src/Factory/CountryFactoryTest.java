package Factory;

import Database.DatabaseConnection;
import Model.Country;
import org.junit.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class CountryFactoryTest {

    private final Connection connection = DatabaseConnection.getInstance().getConnection();

    @Test
    public void getCountries() {

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

        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }

        assertNotNull("Nie udało sie pobrać danych", listOfCountries);
        assertTrue("Brak Danych", listOfCountries.size() > 0);
    }

    @Test
    public void findCountryByName() {

        ArrayList<Country> listOfCountries = new ArrayList<>();
        String countryName = "Ciastko";

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

            assertNotNull("Lista nie istnieje", listOfCountries);
            assertTrue("Lista jest pusta", listOfCountries.isEmpty());

        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }

    }
}