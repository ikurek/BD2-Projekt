package Model;

/**
 * This is a basic data class for Team
 * Java representation to SQL table /teams/ entity
 */
public class Team {

    private int id = 0;
    private String name;
    private int players;

    public Team(int id, String name, int players) {
        this.id = id;
        this.name = name;
        this.players = players;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPlayers() {
        return players;
    }

    public void setPlayers(int players) {
        this.players = players;
    }
}
