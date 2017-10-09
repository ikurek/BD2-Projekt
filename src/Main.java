import Database.DatabaseFactory;
import Model.Team;

import java.util.ArrayList;
import java.util.Scanner;

class Main {

    private static final DatabaseFactory databaseFactory = new DatabaseFactory();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("BD2-Projekt early");

        while (true) {
            System.out.println("\n\nSelect action:");
            System.out.println("1. Add team to SQL");
            System.out.println("2. Show teams in SQL");
            System.out.println("3. Find team by name");
            System.out.println("4. Delete team by name");
            System.out.println("5. Edit team");
            System.out.println("0. Exit");
            System.out.print("Selection: ");

            switch (scanner.nextInt()) {

                case 0:

                    System.exit(0);
                    break;

                case 1:

                    String name;
                    String country;
                    String league;
                    int rank;


                    System.out.print("name: ");
                    name = scanner.next();
                    System.out.print("country: ");
                    country = scanner.next();
                    System.out.print("league: ");
                    league = scanner.next();
                    System.out.print("rank: ");
                    rank = scanner.nextInt();

                    databaseFactory.addTeam(new Team(0, name, country, league, rank));
                    System.out.println("Added new team to database");
                    break;

                case 2:

                    ArrayList<Team> listOfTeams = databaseFactory.getTeams();
                    System.out.println("Retrieved from SQL:");
                    for (Team team : listOfTeams) {
                        System.out.println(team.getId() + " | " + team.getName() + " | " + team.getCountry() + " | " + team.getLeague() + " | " + team.getRank());

                    }

                    break;

                case 3:

                    String nameToFind;
                    System.out.print("name: ");
                    nameToFind = scanner.next();
                    Team team = databaseFactory.findTeamByName(nameToFind);
                    if (team != null) {
                        System.out.println(team.getId() + " | " + team.getName() + " | " + team.getCountry() + " | " + team.getLeague() + " | " + team.getRank());
                    } else {
                        System.out.println("No team found for given name :-(");
                    }
                    break;

                case 4:

                    String nameToDelete;
                    System.out.print("name: ");
                    nameToDelete = scanner.next();
                    databaseFactory.deleteTeamByName(nameToDelete);
                    break;

                case 5:

                    System.out.println("Select team to edit:");
                    listOfTeams = databaseFactory.getTeams();
                    int index = 0;
                    for (Team teamInList : listOfTeams) {
                        System.out.println(index + ". " + teamInList.getId() + " | " + teamInList.getName() + " | " + teamInList.getCountry() + " | " + teamInList.getLeague() + " | " + teamInList.getRank());
                        index++;
                    }
                    System.out.print("Selection: ");
                    team = listOfTeams.get(scanner.nextInt());

                    System.out.print("New name: ");
                    name = scanner.next();
                    System.out.print("New country: ");
                    country = scanner.next();
                    System.out.print("New league: ");
                    league = scanner.next();
                    System.out.print("New rank: ");
                    rank = scanner.nextInt();

                    databaseFactory.updateTeam(team.getName(), new Team(0, name, country, league, rank));

                    break;

            }
        }
    }
}
