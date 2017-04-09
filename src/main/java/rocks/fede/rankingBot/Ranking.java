package rocks.fede.rankingBot;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

/**
 * Created by fede on 09/04/17.
 */
public class Ranking {

    private String name;
    private List<Player> players;

    public Ranking(String name) {
        this.name = name;
        this.players = new ArrayList<Player>();
    }

    public String getRankingName() {
        return this.name;
    }

    private Player getPlayer(String playerName) {
        return players.stream()
                .filter(player -> player.getName().equalsIgnoreCase(playerName))
                .findFirst()
                .orElse(null);
    }

    public void newMatch(Match match) {
        updatePlayersList(match);
        updateTeamScore(match.getTeam0(), match.getTeam1(), match.getTeam0Score(), match.getTeam1Score());
        updateTeamScore(match.getTeam1(), match.getTeam0(), match.getTeam1Score(), match.getTeam0Score());
    }

    private void updateTeamScore(String[] team, String[] opponentTeam, int score, int opponentScore) {
        Stream.of(team).forEach(playerName -> {
            this.getPlayer(playerName).updateScore(ScoreBot.getUpdatedRank(getTeamRank(team),
                            getTeamRank(opponentTeam),
                            score,
                            opponentScore));
        });
    }

    private void updatePlayersList(Match match){
        updateTeamList(match.getTeam0());
        updateTeamList(match.getTeam1());
    }

    private void updateTeamList(String[] team){
        Stream.of(team).forEach(playerName -> {
            if ( players.stream().noneMatch(x -> x.getName().equalsIgnoreCase(playerName)) ) {
                this.players.add(new Player(playerName));
            }
        });
    }

    public double getTeamRank(String[] team) {
        return Stream.of(team)
                .map(playerName -> this.players.stream()
                        .filter(player -> player.getName().equalsIgnoreCase(playerName))
                        .findFirst()
                        .orElse(null))
                .mapToDouble(Player::getScore)
                .average()
                .getAsDouble();
    }
}
