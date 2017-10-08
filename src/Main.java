import Database.DatabaseConnection;
import Model.Team;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        System.out.println("Running main function...");
        DatabaseConnection databaseConnection = DatabaseConnection.getInstance();

        ArrayList<Team> listOfTeams = databaseConnection.getTeams();
        System.out.println("Retrieved from SQL:");
        for (Team team : listOfTeams) {
            System.out.println(team.getId() + " | " + team.getName() + " | " + team.getPlayers());
        }
    }
}
