
package ohtu;

public class Player {
    private String name;
    private String nationality;
    private int assists;
    private int goals;
    private int penalties;
    private String team;
    private int games;

    public void setName(String name) {
        this.name = name;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public void setAssists(int assists) {
        this.assists = assists;
    }

    public void setGoals(int goals) {
        this.goals = goals;
    }

    public void setTeam(String team) {
        this.team = team;
    }

    public void setPenalties(int penalties) {
        this.penalties = penalties;
    }

    public void setGames(int games) {
        this.games = games;
    }

    public int getGames() {
        return games;
    }

    public String getTeam() {
        return team;
    }

    public int getPenalties() {
        return penalties;
    }

    public int getGoals() {
        return goals;
    }

    public int getAssists() {
        return assists;
    }

    public String getNationality() {
        return nationality;
    }

    public String getName() {
        return name;
    }

    public int getPoints() {
        return goals+assists;
    }

    @Override
    public String toString() {
        
       return String.format("%-20s",name) + " " + team + " " + String.format("%2d",goals) + " + " 
       + String.format("%2d",assists) + " = " + getPoints();
    }
      
}
