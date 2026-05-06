package model;

public class Match {
    private String id;
    private String season;
    private String winner;

//    private String player_of_match;
//    private String date;
//    private String team1;
//    private String team2;
//    private String toss_winner;


    public Match(String id, String season, String winner) {

        this.id = id;
        this.season = season;
        this.winner = winner;


    }

    public String getId() {
        return id;
    }



    public String getSeason() {
        return season;
    }

    public String getWinner() {
        return winner;
    }
}
