package rocks.fede.rankingBot;

/**
 * Created by fede on 09/04/17.
 */
public class ScoreBot {

    public static final int TOP_SCORE = 100;

    public static double getUpdatedRank(double teamRank, double opponentsRank, int teamScore, int opponentScore) {
        //TODO return updated Player rank that reflects latest match results.
        return 0;
    };

    public static double getExpectationFactor(double teamRank, double opponentsRank) {
        return Math.abs(teamRank - opponentsRank) / ScoreBot.TOP_SCORE;
    }
}
