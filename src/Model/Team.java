package Model;

/**
 * This is a basic data class for Team
 * Java representation to SQL table /teams/ entity
 */
public class Team {

    private int id = 0;
    private String name;
    private String country;
    private String league;
    private int rank;

    public Team(int id, String name, String country, String league, int rank) {
        this.id = id;
        this.name = name;
        this.country = country;
        this.league = league;
        this.rank = rank;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCountry() {
        return country;
    }

    public String getLeague() {
        return league;
    }

    public int getRank() {
        return rank;
    }
}
