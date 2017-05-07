package rocks.fede.rankingBot;

import java.io.IOException;
import java.util.*;
import java.util.stream.Stream;

/**
 * Created by fede on 09/04/17.
 */
public class Ranking {

    private String name;
    private StorageBackend backend;
    private Map<String,Double> ranking;

    public Ranking(String name, StorageBackend backend) throws IOException {
        this.name = name;
        this.backend = backend;
        this.ranking = new HashMap<>();
        backend.newRanking(name);
    }

    public void createTeam(Map<String, Double> ranking, List<String> team) {
        team.stream().forEach(player -> { if (! ranking.containsKey(player)) ranking.put(player, ScoreBot.TOP_RANK/2);});
    }

    public double getTeamRank(Map<String, Double> ranking, List<String> team) {
        return ranking.entrySet().stream()
                .filter( entry -> team.stream().anyMatch( team_player -> team_player.equalsIgnoreCase(entry.getKey()) ))
                .mapToDouble(p -> p.getValue())
                .average()
                .getAsDouble();
    }

    private void updatePlayerRank(Map<String, Double> ranking, String playerName, double suggestedRankAdjustment) {
        double sndAdjustment = ScoreBot.sndRankAdjustment(ranking.get(playerName), suggestedRankAdjustment);
        ranking.put(playerName, ranking.get(playerName) + sndAdjustment);
    }

    public void updateRankings(Map<String, Double> ranking, List<String> team0, int team0_score, List<String> team1, int team1_score) {
        createTeam(ranking, team0);
        createTeam(ranking, team1);
        double team0RankAdjustment = ScoreBot.getRankAdjustment(getTeamRank(ranking, team0), team0_score, getTeamRank(ranking, team1), team1_score);
        team0.stream().forEach(player -> updatePlayerRank(ranking, player, team0RankAdjustment));
        team1.stream().forEach(player -> updatePlayerRank(ranking, player, - team0RankAdjustment));
    }

    public void newMatch(String dateTime, List<String> team0, int team0_score, List<String> team1, int team1_score) throws IOException {
        backend.persistMatch(name, dateTime, team0, team0_score, team1, team1_score);
        updateRankings(ranking, team0, team0_score, team1, team1_score);
    }

    public void rebuildRanking() {
        Map<String,Double> newRanking = new HashMap<>();
        try {
            Iterator<String> matches = backend.getPersistedMatches(name);
            String match;
            while (matches.hasNext()) {
                match = matches.next();
                String[] fields = match.split(";");
                List<String> team0 = Arrays.asList(fields[1].split(","));
                int team0_score = Integer.parseInt(fields[2]);
                List<String> team1 = Arrays.asList(fields[3].split(","));
                int team1_score = Integer.parseInt(fields[4]);
                updateRankings(newRanking,team0,team0_score,team1,team1_score);
            }
            this.ranking = newRanking;
        } catch (IOException e) {
            System.out.println("There was an error while rebuilding the ranking:");
            e.printStackTrace();
        }
    }

    public void printRanking() {
        System.out.println(name);
        ranking.entrySet().stream()
                .sorted( (entry1, entry2) -> Double.compare(entry2.getValue(), entry1.getValue()) )
                .forEach( entry -> System.out.println( entry.getKey() + " - " + entry.getValue()));
    }
}
