package Database;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static org.junit.Assert.assertNotNull;

@RunWith(JUnit4.class)
public class DatabaseConnectionTest {

    private static DatabaseConnection INSTANCE;
    private static Connection connection = null;


    @Test
    public void getInstance() {

        try {
            Class.forName("org.postgresql.Driver");

            connection = DriverManager.getConnection(
                    "jdbc:postgresql://127.0.0.1:5432/BD",
                    "igor",
                    "");
            assertNotNull("Błąd połączenia", connection);

        } catch (ClassNotFoundException e) {

            e.printStackTrace();
            assertNotNull("Brak Sterownika", connection);
        } catch (SQLException e) {

            e.printStackTrace();
            assertNotNull("Złe dane", connection);

        }
    }
}