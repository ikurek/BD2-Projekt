package Model;

public class Player {
    private int id;
    private String name;
    private String surname;
    private String team;
    private int rank;

    public Player(int id, String name, String surname, String team, int rank) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.team = team;
        this.rank = rank;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getTeam() {
        return team;
    }

    public int getRank() {
        return rank;
    }
}
