import Database.DatabaseConnection;
import Factory.PlayerFactory;
import Factory.TeamFactory;
import Model.Player;
import Model.Team;

import java.util.ArrayList;
import java.util.Scanner;

class Main {

    private static final DatabaseConnection DATABASE_CONNECTION = DatabaseConnection.getInstance();
    private static final TeamFactory TEAM_FACTORY = new TeamFactory();
    private static final PlayerFactory PLAYER_FACTORY = new PlayerFactory();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("BD2-Projekt alpha");

        while (true) {
            System.out.println("\nSelect action:");
            System.out.println("1. Add team");
            System.out.println("2. Show teams");
            System.out.println("3. Find team");
            System.out.println("4. Delete team");
            System.out.println("5. Edit team");
            System.out.println("6. Add player");
            System.out.println("7. Show players");
            System.out.println("8. Find player");
            System.out.println("9. Delete player");
            System.out.println("10. Edit player");
            System.out.println("99. Recreate database");
            System.out.println("0. Exit");
            System.out.print("Selection: ");

            switch (scanner.nextInt()) {

                case 0:

                    System.exit(0);

                    break;

                case 99:

                    DATABASE_CONNECTION.rebuildDatabase();

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

                    TEAM_FACTORY.addTeam(new Team(0, name, country, league, rank));
                    System.out.println("Added new team to database");
                    break;

                case 2:

                    ArrayList<Team> listOfTeams = TEAM_FACTORY.getTeams();
                    System.out.println("Retrieved from SQL:");
                    for (Team team : listOfTeams) {
                        System.out.println(team.getId() + " | " + team.getName() + " | " + team.getCountry() + " | " + team.getLeague() + " | " + team.getRank());

                    }

                    break;

                case 3:

                    String nameToFind;
                    System.out.print("name: ");
                    nameToFind = scanner.next();
                    listOfTeams = TEAM_FACTORY.findTeamByName(nameToFind);
                    if (listOfTeams.isEmpty()) {
                        System.out.println("No team found for given name :-(");
                    } else {
                        for (Team team : listOfTeams) {
                            System.out.println(team.getId() + " | " + team.getName() + " | " + team.getCountry() + " | " + team.getLeague() + " | " + team.getRank());
                        }
                    }

                    break;

                case 4:

                    String nameToDelete;
                    System.out.print("name: ");
                    nameToDelete = scanner.next();
                    TEAM_FACTORY.deleteTeam(TEAM_FACTORY.findTeamByName(nameToDelete).get(0));
                    break;

                case 5:

                    System.out.println("Select team to edit:");
                    listOfTeams = TEAM_FACTORY.getTeams();
                    int index = 0;
                    for (Team teamInList : listOfTeams) {
                        System.out.println(index + ". " + teamInList.getId() + " | " + teamInList.getName() + " | " + teamInList.getCountry() + " | " + teamInList.getLeague() + " | " + teamInList.getRank());
                        index++;
                    }
                    System.out.print("Selection: ");
                    Team team = listOfTeams.get(scanner.nextInt());

                    System.out.print("New name: ");
                    name = scanner.next();
                    System.out.print("New country: ");
                    country = scanner.next();
                    System.out.print("New league: ");
                    league = scanner.next();
                    System.out.print("New rank: ");
                    rank = scanner.nextInt();

                    TEAM_FACTORY.updateTeam(team, new Team(0, name, country, league, rank));

                    break;

                case 6:
                    String surname;
                    String teamName;

                    System.out.print("name: ");
                    name = scanner.next();
                    System.out.print("surname: ");
                    surname = scanner.next();
                    System.out.print("team: ");
                    teamName = scanner.next();
                    System.out.print("rank: ");
                    rank = scanner.nextInt();

                    PLAYER_FACTORY.addPlayer(new Player(0, name, surname, teamName, rank));
                    System.out.println("Added new player to database");
                    break;

                case 7:

                    ArrayList<Player> listOfPlayers = PLAYER_FACTORY.getPlayers();
                    System.out.println("Retrieved from SQL:");
                    for (Player player : listOfPlayers) {
                        System.out.println(player.getId() + " | " + player.getName() + " | " + player.getSurname() + " | " + player.getTeam() + " | " + player.getRank());
                    }

                    break;

                case 8:

                    String surnameToFind;
                    System.out.print("name: ");
                    nameToFind = scanner.next();
                    System.out.println("surname: ");
                    surnameToFind = scanner.next();
                    listOfPlayers = PLAYER_FACTORY.findPlayerByName(nameToFind, surnameToFind);
                    if (listOfPlayers.isEmpty()) {
                        System.out.println("No player found for given name :-(");
                    } else {
                        for (Player player : listOfPlayers) {
                            System.out.println(player.getId() + " | " + player.getName() + " | " + player.getSurname() + " | " + player.getTeam() + " | " + player.getRank());
                        }
                    }

                    break;

                case 9:

                    System.out.print("name: ");
                    nameToDelete = scanner.next();
                    System.out.print("surname: ");
                    surname = scanner.next();
                    PLAYER_FACTORY.deletePlayer(PLAYER_FACTORY.findPlayerByName(nameToDelete, surname).get(0));
                    break;

                case 10:

                    System.out.println("Select team to edit:");
                    listOfPlayers = PLAYER_FACTORY.getPlayers();
                    index = 0;
                    for (Player playerInList : listOfPlayers) {
                        System.out.println(index + ". " + playerInList.getId() + " | " + playerInList.getName() + " | " + playerInList.getSurname() + " | " + playerInList.getTeam() + " | " + playerInList.getRank());
                        index++;
                    }
                    System.out.print("Selection: ");
                    Player player = listOfPlayers.get(scanner.nextInt());

                    System.out.print("New name: ");
                    name = scanner.next();
                    System.out.print("New surname: ");
                    surname = scanner.next();
                    System.out.print("New team: ");
                    teamName = scanner.next();
                    System.out.print("New rank: ");
                    rank = scanner.nextInt();

                    PLAYER_FACTORY.updatePlayer(player, new Player(player.getId(), name, surname, teamName, rank));

                    break;
            }
        }
    }
}
