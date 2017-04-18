package rocks.fede.rankingBot;

import java.util.*;
import java.util.stream.Stream;

/**
 * Created by fede on 09/04/17.
 */
public class Ranking {

    private String name;
    private Map<String,Double> ranking;

    public Ranking(String name) {
        this.name = name;
        this.ranking = new HashMap<>();
    }

    public String getRankingName() {
        return this.name;
    }

    public void setPlayerRanking(String playerName, double ranking) {
        this.ranking.put(playerName, ranking);
    }

    public List<String> createTeam(String player0) {
        List<String> team = Arrays.asList(player0);
        if (! this.ranking.containsKey(player0)) this.ranking.put(player0, ScoreBot.TOP_RANK/2);
        return team;
    }

    public List<String> createTeam(String player0, String player1) {
        List<String> team = Arrays.asList(player0, player1);
        if (! this.ranking.containsKey(player0)) this.ranking.put(player0, ScoreBot.TOP_RANK/2);
        if (! this.ranking.containsKey(player1)) this.ranking.put(player1, ScoreBot.TOP_RANK/2);
        return team;
    }

    public double getTeamRank(List<String> team) {
        return this.ranking.entrySet().stream()
                .filter( entry -> team.stream().anyMatch( team_player -> team_player.equalsIgnoreCase(entry.getKey()) ))
                .mapToDouble(p -> p.getValue())
                .average()
                .getAsDouble();
    }

    private void updatePlayerRank(String playerName, double suggestedRankAdjustment) {
        double sndAdjustment = ScoreBot.sndRankAdjustment(this.ranking.get(playerName), suggestedRankAdjustment);
        this.ranking.put(playerName, this.ranking.get(playerName) + sndAdjustment);
    }

    private void updateTeamRanking(List<String> team, double suggestedRankAdjustment) {
        team.stream().forEach(player -> updatePlayerRank(player, suggestedRankAdjustment));
    }

    public void updateRankings(List<String> team0, int team0_score, List<String> team1, int team1_score) {
        double team0RankAdjustment = ScoreBot.getRankAdjustment(getTeamRank(team0), team0_score, getTeamRank(team1), team1_score);
        updateTeamRanking(team0, team0RankAdjustment);
        updateTeamRanking(team1, -team0RankAdjustment);
    }

    public void newMatch(String player0_0, int team0_score, String player1_0, int team1_score) {
        List<String> team0 = createTeam(player0_0);
        List<String> team1 = createTeam(player1_0);
        updateRankings(team0, team0_score, team1, team1_score);
    }

    public void newMatch(String player0_0, String player0_1, int team0_score, String player1_0, int team1_score) {
        List<String> team0 = createTeam(player0_0, player0_1);
        List<String> team1 = createTeam(player1_0);
        updateRankings(team0, team0_score, team1, team1_score);
    }

    public void newMatch(String player0_0, int team0_score, String player1_0, String player1_1, int team1_score) {
        newMatch(player1_0, player1_1, team1_score, player0_0, team0_score);
    }

    public void newMatch(String player0_0, String player0_1, int team0_score, String player1_0, String player1_1, int team1_score) {
        List<String> team0 = createTeam(player0_0, player0_1);
        List<String> team1 = createTeam(player1_0, player1_1);
        updateRankings(team0, team0_score, team1, team1_score);
    }

    public void printRanking() {
        System.out.println(getRankingName());
        this.ranking.entrySet().stream()
                .sorted( (entry1, entry2) -> Double.compare(entry2.getValue(), entry1.getValue()) )
                .forEach( entry -> System.out.println( entry.getKey() + " - " + entry.getValue()));
    }
}
