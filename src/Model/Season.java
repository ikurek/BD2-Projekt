package Model;


import java.sql.Date;

public class Season {

    private int id;
    private String name;
    private Date openDate;
    private Date closeDate;
    private String winner;

    public Season(int id, String name, Date openDate, Date closeDate, String winner) {
        this.id = id;
        this.name = name;
        this.openDate = openDate;
        this.closeDate = closeDate;
        this.winner = winner;
    }

    public Season(String name, Date openDate, Date closeDate, String winner) {
        this.name = name;
        this.openDate = openDate;
        this.closeDate = closeDate;
        this.winner = winner;
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

    public Date getOpenDate() {
        return openDate;
    }

    public void setOpenDate(Date openDate) {
        this.openDate = openDate;
    }

    public Date getCloseDate() {
        return closeDate;
    }

    public void setCloseDate(Date closeDate) {
        this.closeDate = closeDate;
    }

    public String getWinner() {
        return winner;
    }

    public void setWinner(String winner) {
        this.winner = winner;
    }

}
