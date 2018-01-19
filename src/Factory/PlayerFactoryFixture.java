package Factory;

import Model.Player;

public class PlayerFactoryFixture {

    public String query;
    public int id;
    public String name;
    public String surname;
    public String team;
    public int rank;

    public PlayerFactoryFixture() {

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

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getTeam() {
        return team;
    }

    public void setTeam(String team) {
        this.team = team;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public String query() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public void execute() {
        Player player = new Player(id, name, surname, team, rank);

        query = "INSERT INTO players(name, surname, team, rank) VALUES("
                + player.getName() + ", "
                + player.getSurname() + ", "
                + player.getTeam() + ", "
                + player.getRank() + ");";

    }


}
