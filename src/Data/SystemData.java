package Data;

import Database.DatabaseConnection;
import Factory.*;
import Model.*;

import java.sql.Date;
import java.util.ArrayList;

public class SystemData {

    static ArrayList<Country> listOfCountries = new ArrayList<Country>();
    static ArrayList<League> listOfLeagues = new ArrayList<League>();
    static ArrayList<MatchInSeason> listOfMatches = new ArrayList<MatchInSeason>();
    static ArrayList<Player> listOfPlayers = new ArrayList<Player>();
    static ArrayList<Season> listOfSeasons = new ArrayList<Season>();
    static ArrayList<Team> listOfTeams = new ArrayList<Team>();
    static ArrayList<PlayerInSeason> listOfPlayersInSeason = new ArrayList<PlayerInSeason>();
    DatabaseConnection connection;

    public SystemData(DatabaseConnection conn) {
        if (conn != null) {
            connection = conn;
            System.out.println("Utworzylismy system danych");
            listOfCountries = CountryFactory.getCountries(connection.getConnection());
            listOfLeagues = LeagueFactory.getLeagues(connection.getConnection());
            listOfMatches = MatchInSeasonFactory.getMatchesInSeason(connection.getConnection());
            listOfPlayers = PlayerFactory.getPlayers(connection.getConnection());
            listOfPlayersInSeason = PlayerInSeasonFactory.getPlayersInSeason(connection.getConnection());
            listOfSeasons = SeasonFactory.getSeasons(connection.getConnection());
            listOfTeams = TeamFactory.getTeams(connection.getConnection());
        } else {
            Country count = new Country(1, "Polska");
            listOfCountries.add(count);

            League legue = new League(1, "Ekstraklasa");
            listOfLeagues.add(legue);

            Team team1 = new Team(1, "Slask wroclaw", "Polska", "Ekstraklasa", 3);
            Team team2 = new Team(2, "Legia warszawa", "Polska", "Ekstraklasa", 2);
            Team team3 = new Team(3, "FC Kodrim", "Uganda", "Uganda National League", 1);
            listOfTeams.add(team1);
            listOfTeams.add(team2);
            listOfTeams.add(team3);

            Player player1 = new Player(1, "Milosz", "Marynata", "Slask wroclaw", 4);
            Player player2 = new Player(2, "Kacper", "Garcarz", "Slask wroclaw", 5);
            Player player3 = new Player(3, "Piotr", "Nosal", "Slas wroclaw", 3);
            Player player4 = new Player(4, "Jasiu", "Stepien", "Legia warszawa", 4);
            Player player5 = new Player(5, "Krzysztof", "Prajs", "Legia warszawa", 5);
            Player player6 = new Player(6, "Julek", "Hnatow", "Legia warszawa", 3);
            listOfPlayers.add(player1);
            listOfPlayers.add(player2);
            listOfPlayers.add(player3);
            listOfPlayers.add(player4);
            listOfPlayers.add(player5);
            listOfPlayers.add(player6);

            Season sezon = new Season(1, "Sezon Letni", new Date(2018, 1, 5), new Date(2018, 1, 8), "Slask wroclaw");
            listOfSeasons.add(sezon);

            MatchInSeason mecz = new MatchInSeason(1, 1, "Slask wroclaw", "Legia warszawa", 5, 1);
            listOfMatches.add(mecz);

            PlayerInSeason pS1 = new PlayerInSeason(1, 1, 1, 3, 4, 6, 1);
            PlayerInSeason pS2 = new PlayerInSeason(2, 1, 2, 1, 4, 2, 1);
            PlayerInSeason pS3 = new PlayerInSeason(3, 1, 3, 2, 2, 6, 1);
            PlayerInSeason pS4 = new PlayerInSeason(4, 1, 4, 6, 6, 6, 1);
            PlayerInSeason pS5 = new PlayerInSeason(5, 1, 5, 3, 7, 7, 1);
            PlayerInSeason pS6 = new PlayerInSeason(6, 1, 6, 2, 1, 6, 1);
            listOfPlayersInSeason.add(pS1);
            listOfPlayersInSeason.add(pS2);
            listOfPlayersInSeason.add(pS3);
            listOfPlayersInSeason.add(pS4);
            listOfPlayersInSeason.add(pS5);
            listOfPlayersInSeason.add(pS6);
        }
    }

    public static ArrayList<Country> getCountries() {
        return listOfCountries;
    }

    public static ArrayList<League> getLeagues() {
        return listOfLeagues;
    }

    public static ArrayList<MatchInSeason> getMatches() {
        return listOfMatches;
    }

    public static ArrayList<Player> getPlayers() {
        return listOfPlayers;
    }

    public static ArrayList<PlayerInSeason> getPlayersInSeason() {
        return listOfPlayersInSeason;
    }

    public static ArrayList<Season> getSeasons() {
        return listOfSeasons;
    }

    public static ArrayList<Team> getTeams() {
        return listOfTeams;
    }

    public void uploadToDB() {
        // upload players
        CountryFactory countryFactory = new CountryFactory();
        LeagueFactory leagueFactory = new LeagueFactory();
        MatchInSeasonFactory matchInSeasonFactory = new MatchInSeasonFactory();
        PlayerFactory playerFactory = new PlayerFactory();
        PlayerInSeasonFactory playerInSeasonFactory = new PlayerInSeasonFactory();
        SeasonFactory seasonFactory = new SeasonFactory();
        TeamFactory teamFactory = new TeamFactory();

        for (Country country : listOfCountries) {
            countryFactory.addCountry(country);
        }

        for (League league : listOfLeagues) {
            leagueFactory.addLeague(league);
        }

        for (MatchInSeason matchInSeason : listOfMatches) {
            matchInSeasonFactory.addMatchInSeason(matchInSeason);
        }

        for (Player player : listOfPlayers) {
            playerFactory.addPlayer(player);
        }

        for (PlayerInSeason playerInSeason : listOfPlayersInSeason) {
            playerInSeasonFactory.addPlayerInSeason(playerInSeason);
        }

        for (Season season : listOfSeasons) {
            seasonFactory.addSeason(season);
        }

        for (Team team : listOfTeams) {
            teamFactory.addTeam(team);
        }

    }
}
