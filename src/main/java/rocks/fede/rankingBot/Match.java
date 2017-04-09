package rocks.fede.rankingBot;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by fede on 09/04/17.
 */
public class Match {

    private String matchType;
    private int team0_score;
    private int team1_score;
    private List<String> team0 = new ArrayList<>();
    private List<String> team1 = new ArrayList<>();

    public Match(String team0_player0, String team1_player0, int team0_score, int team1_score) {
        this.matchType = "1v1";
        this.team0_score = team0_score;
        this.team1_score = team1_score;
        this.team0.add(team0_player0);
        this.team1.add(team1_player0);
    };
    public Match(String team0_player0, String team0_player1, String team1_player0, int team0_score, int team1_score) {
        this.matchType = "2v1";
        this.team0_score = team0_score;
        this.team1_score = team1_score;
        this.team0.add(team0_player0);
        this.team0.add(team0_player1);
        this.team1.add(team1_player0);
    };
    public Match(String team0_player0, String team0_player1, String team1_player0, String team1_player1, int team0_score, int team1_score) {
        this.matchType = "2v2";
        this.team0_score = team0_score;
        this.team1_score = team1_score;
        this.team0.add(team0_player0);
        this.team0.add(team0_player1);
        this.team1.add(team1_player0);
        this.team1.add(team1_player1);
    };

    public int getTeam0Score(){
        return this.team0_score;
    }
    public int getTeam1Score() { return this.team1_score; }
    public String getMatchType(){
        return this.matchType;
    }
    public String[] getTeam0() { return this.team0.toArray(new String[this.team0.size()]); }
    public String[] getTeam1() { return this.team1.toArray(new String[this.team1.size()]); }
}
