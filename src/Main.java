import Database.DatabaseFactory;
import Model.Team;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    private static DatabaseFactory databaseFactory = new DatabaseFactory();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("BD2-Projekt early");

        while (true) {
            System.out.println("\n\nSelect action:");
            System.out.println("1. Add team to SQL");
            System.out.println("2. Show teams in SQL");
            System.out.print("Selection: ");

            switch (scanner.nextInt()) {
                case 1:

                    int id;
                    String name;
                    int players;

                    System.out.print("id: ");
                    id = scanner.nextInt();
                    System.out.print("name: ");
                    name = scanner.next();
                    System.out.print("players: ");
                    players = scanner.nextInt();

                    databaseFactory.addTeam(new Team(id, name, players));
                    System.out.println("Added new team to database");
                    break;

                case 2:
                    ArrayList<Team> listOfTeams = databaseFactory.getTeams();
                    System.out.println("Retrieved from SQL:");
                    for (Team team : listOfTeams) {
                        System.out.println(team.getId() + " | " + team.getName() + " | " + team.getPlayers());
                    }
                    break;
            }
        }
    }
}
