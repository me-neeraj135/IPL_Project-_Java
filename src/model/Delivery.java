package model;

public class Delivery {

    private String matchId;
    private String bowlingTeam;
    private String bowler;
    private int extraRuns;
    private int totalRuns;
    private int wideRuns;
    private int noballRuns;
    private final String batsman;
    private final int batsmanRuns;


    public Delivery(String matchId, String bowlingTeam, String bowler, int extraRuns, int totalRuns, int wideRuns, int noballRuns, String batsman, int batsmanRuns) {
        this.matchId = matchId;
        this.bowlingTeam = bowlingTeam;
        this.bowler = bowler;
        this.extraRuns = extraRuns;
        this.totalRuns = totalRuns;
        this.wideRuns = wideRuns;
        this.noballRuns = noballRuns;
        this.batsman = batsman;
        this.batsmanRuns = batsmanRuns;
    }




    public String getMatchId() {
        return matchId;
    }

    ;

    public String getBowlingTeam() {
        return bowlingTeam;
    }

    ;

    public String getBowler() {
        return bowler;
    }

    ;

    public int getExtraRuns() {
        return extraRuns;
    }

    public int getTotalRuns() {
        return totalRuns;
    }

    public int getWideRuns() {
        return wideRuns;
    }

    public int getNoballRuns() {
        return noballRuns;
    }

    public String getBatsman() {
        return batsman;
    }

    public int getBatsmanRuns() {
        return batsmanRuns;
    }

}
