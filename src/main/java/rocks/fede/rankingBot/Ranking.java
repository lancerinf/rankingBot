package rocks.fede.rankingBot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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

    private double getPlayerRanking(String playerName) {
        return this.ranking.get(playerName);
    }

    public void newMatch(String player0_0, int team0_score, String player1_0, int team1_score) {
        List<String> team0 = new ArrayList<>();
        List<String> team1 = new ArrayList<>();
        team0.add(player0_0);
        team1.add(player1_0);
        updatePlayersList(team0);
        updatePlayersList(team1);
        //TODO update players score to account for new match
    }

    public void newMatch(String player0_0, String player0_1, int team0_score, String player1_0, int team1_score) {
        List<String> team0 = new ArrayList<>();
        List<String> team1 = new ArrayList<>();
        team0.add(player0_0);
        team0.add(player0_1);
        team1.add(player1_0);
        updatePlayersList(team0);
        updatePlayersList(team1);
        //TODO update players score to account for new match
    }

    public void newMatch(String player0_0, String player0_1, int team0_score, String player1_0, String player1_1, int team1_score) {
        List<String> team0 = new ArrayList<>();
        List<String> team1 = new ArrayList<>();
        team0.add(player0_0);
        team0.add(player0_1);
        team1.add(player1_0);
        team1.add(player1_1);
        updatePlayersList(team0);
        updatePlayersList(team1);
        //TODO update players score to account for new match
    }

    private void updateScores(String[] team, String[] opponentTeam, int score, int opponentScore) {
        //TODO implement Ranking.updateScores method
        //Stream.of(team).forEach(playerName -> );
    }

    private void updatePlayersList(List<String> playerNames){
        playerNames.stream().forEach(playerName -> {
            if (! this.ranking.containsKey(playerName)) this.ranking.put(playerName, ScoreBot.TOP_SCORE/2);
        });
    }

    public void printRanking() {
        System.out.println(this.getRankingName());
        this.ranking.entrySet().stream()
                .sorted( (entry1, entry2) -> Double.compare(entry1.getValue(),entry2.getValue()) )
                .forEach( entry -> System.out.println( entry.getKey() + " - " + entry.getValue()));
    }


    public double getTeamRank(String[] team) {
        return this.ranking.entrySet().stream()
                .filter( entry -> Stream.of(team).anyMatch( team_player -> team_player.equalsIgnoreCase(entry.getKey()) ))
                .mapToDouble(p -> p.getValue())
                .average()
                .getAsDouble();
    }
}
