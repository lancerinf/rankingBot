package rocks.fede.rankingBot;

/**
 * Created by fede on 09/04/17.
 */
public class ScoreBot {

    public static final int TOP_SCORE = 100;

    public static double getUpdatedRank(double teamRank, double opponentsRank, int teamScore, int opponentScore) {
        //TODO return suggested rank adjustment to reflect latest results.
        return 0;
    };

    public static int predictScoreDifference(double teamRank, double opponentsRank, int topScore) {
        int predictedDifference = (int) Math.abs(((teamRank - opponentsRank)/ ScoreBot.TOP_SCORE)*topScore);
        if (predictedDifference == 0) return 1;
        else return predictedDifference;
    }

    public static double getExpectationFactor(double teamRank, double opponentsRank) {
        return (teamRank - opponentsRank) / ScoreBot.TOP_SCORE;
    }
}
