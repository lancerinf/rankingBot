package rocks.fede.rankingBot;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

/**
 * Created by fede on 09/04/17.
 */
public class Ranking {

    public static final int TOP_SCORE = 100;
    private String name;
    private List<Player> players;

    public Ranking(String name) {
        this.name = name;
        this.players = new ArrayList<Player>();
    }

    public void newMatch(Match match) {
        updatePlayerList(match.getTeam0());
        updatePlayerList(match.getTeam1());
        
    }

    private void updatePlayerList(String[] team){
        Stream.of(team).forEach(playerName -> {
            if ( players.stream().noneMatch(x -> x.getName().equalsIgnoreCase(playerName)) ) {
                this.players.add(new Player(playerName));
            }
        });
    }

    public double getTeamAvgScore(String[] team) {
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
