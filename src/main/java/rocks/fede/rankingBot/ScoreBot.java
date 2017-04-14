package rocks.fede.rankingBot;

/**
 * Created by fede on 09/04/17.
 */
public class ScoreBot {

    public static final double TOP_SCORE = 100;

    // BOOST_NORMAL_EXPANSION is used to project a ratio onto a SND.
    public static final double BOOST_NORMAL_EXPANSION = 4.0;

    // BOOST_AMPLIFICATION is used to add some weight to the getDifferenceAmplificationFactor method.
    public static final double BOOST_AMPLIFICATION = 3.0;


    public static int getPredictedLowerScore(double teamRank, double opponentsRank, int topScore) {
        int predictedDifference = (int) Math.abs(((teamRank - opponentsRank)*(ScoreBot.getDifferenceAmplificationFactor(teamRank, opponentsRank))/ ScoreBot.TOP_SCORE)*topScore);
        if (predictedDifference == 0) predictedDifference = 1;
        return topScore - predictedDifference;
    }

    public static double getNormalizedLoserPerformance(int winnerScore, int loserScore, int predictedLoserScore) {
        return ((double) loserScore - (double) predictedLoserScore) / (double) winnerScore;
    }

    public static double getUpdatedRank(double teamRank, double opponentsRank, int teamScore, int opponentScore) {
        int winnerScore = Math.max(teamScore, opponentScore);
        int loserScore = Math.min(teamScore, opponentScore);
        int predictedLoserScore = ScoreBot.getPredictedLowerScore(teamRank, opponentsRank, Math.max(teamScore,opponentScore));
        double normalizedLoserPerformance = ScoreBot.getNormalizedLoserPerformance(winnerScore, loserScore, predictedLoserScore);

        //TODO return suggested rank adjustment to reflect latest results.
        return 0;
    };

    /**
     *
     * @param teamRank
     * @param opponentsRank
     * @return Returns a multiplier to be used in "predictScoreDifference". This multiplier adds some difference when
     *  the difference in ranking is little and adds close to nothing when the difference in ranking is high.
     */
    public static double getDifferenceAmplificationFactor(double teamRank, double opponentsRank) {
        double normalizedRankDiff = ((teamRank - opponentsRank)/ScoreBot.TOP_SCORE)*ScoreBot.BOOST_NORMAL_EXPANSION;
        return (1.0 + Math.abs(sndVal(normalizedRankDiff)*ScoreBot.BOOST_AMPLIFICATION));
    }

    public static double sndVal(double x) {
        return Math.pow(Math.E,(-(0.5)*Math.pow(x,2)))/Math.sqrt(Math.PI*2);
    }
}
