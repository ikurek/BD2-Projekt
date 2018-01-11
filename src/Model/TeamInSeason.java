package Model;

public class TeamInSeason {

    private int id;
    private int seasonID;
    private int teamID;
    private String growth;
    private int matches;
    private int matchesWon;
    private int matchesLost;
    private int goals;
    private int goalsLost;
    private int ladder;

    public TeamInSeason(int id, int seasonID, int teamID, String growth, int matches, int matchesWon, int matchesLost, int goals, int goalsLost, int ladder) {
        this.id = id;
        this.seasonID = seasonID;
        this.teamID = teamID;
        this.growth = growth;
        this.matches = matches;
        this.matchesWon = matchesWon;
        this.matchesLost = matchesLost;
        this.goals = goals;
        this.goalsLost = goalsLost;
        this.ladder = ladder;
    }

    public TeamInSeason(int seasonID, int teamID, String growth, int matches, int matchesWon, int matchesLost, int goals, int goalsLost, int ladder) {
        this.seasonID = seasonID;
        this.teamID = teamID;
        this.growth = growth;
        this.matches = matches;
        this.matchesWon = matchesWon;
        this.matchesLost = matchesLost;
        this.goals = goals;
        this.goalsLost = goalsLost;
        this.ladder = ladder;
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

    public int getTeamID() {
        return teamID;
    }

    public void setTeamID(int teamID) {
        this.teamID = teamID;
    }

    public String getGrowth() {
        return growth;
    }

    public void setGrowth(String growth) {
        this.growth = growth;
    }

    public int getMatches() {
        return matches;
    }

    public void setMatches(int matches) {
        this.matches = matches;
    }

    public int getMatchesWon() {
        return matchesWon;
    }

    public void setMatchesWon(int matchesWon) {
        this.matchesWon = matchesWon;
    }

    public int getMatchesLost() {
        return matchesLost;
    }

    public void setMatchesLost(int matchesLost) {
        this.matchesLost = matchesLost;
    }

    public int getGoals() {
        return goals;
    }

    public void setGoals(int goals) {
        this.goals = goals;
    }

    public int getGoalsLost() {
        return goalsLost;
    }

    public void setGoalsLost(int goalsLost) {
        this.goalsLost = goalsLost;
    }

    public int getLadder() {
        return ladder;
    }

    public void setLadder(int ladder) {
        this.ladder = ladder;
    }
}
