package Model;

public class PlayerInSeason {

    private int id;
    private int seasonID;
    private int playerID;
    private int goals;
    private int assists;
    private int fouls;
    private int cards;
    private int rate;

    public PlayerInSeason(int id, int seasonID, int playerID, int goals, int assists, int fouls, int cards, int rate) {
        this.id = id;
        this.seasonID = seasonID;
        this.playerID = playerID;
        this.goals = goals;
        this.assists = assists;
        this.fouls = fouls;
        this.cards = cards;
        this.rate = rate;
    }

    public PlayerInSeason(int seasonID, int playerID, int goals, int assists, int fouls, int cards, int rate) {
        this.seasonID = seasonID;
        this.playerID = playerID;
        this.goals = goals;
        this.assists = assists;
        this.fouls = fouls;
        this.cards = cards;
        this.rate = rate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSeasonID() {
        return seasonID;
    }

    public void setSeasonID(int seasonID) {
        this.seasonID = seasonID;
    }

    public int getPlayerID() {
        return playerID;
    }

    public void setPlayerID(int playerID) {
        this.playerID = playerID;
    }

    public int getGoals() {
        return goals;
    }

    public void setGoals(int goals) {
        this.goals = goals;
    }

    public int getAssists() {
        return assists;
    }

    public void setAssists(int assists) {
        this.assists = assists;
    }

    public int getFouls() {
        return fouls;
    }

    public void setFouls(int fouls) {
        this.fouls = fouls;
    }

    public int getCards() {
        return cards;
    }

    public void setCards(int cards) {
        this.cards = cards;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }
}