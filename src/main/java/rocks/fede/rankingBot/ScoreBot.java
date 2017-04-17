package rocks.fede.rankingBot;

/**
 * Created by fede on 09/04/17.
 */
public class ScoreBot {

    public static final double TOP_RANK = 100;

    // BOOST_NORMAL_EXPANSION is used to project a ratio onto a SND.
    public static final double BOOST_NORMAL_EXPANSION = 4.0;

    // BOOST_AMPLIFICATION is used to add some weight to the getDifferenceAmplificationFactor method.
    public static final double BOOST_AMPLIFICATION = 3.0;

    // RANK_ADJUSTMENT_NORMAL_EXPANSION is used to change the suggested rank adjustment between 100% to 10% of the SND value in 0.
    public static final double RANK_ADJUSTMENT_NORMAL_EXPANSION = 3;

    // RANK_ADJUSTMENT_NORMALIZATION_FACTOR is used to bring SND values in range [0,1] instead of [0,0.4].
    public static final double RANK_ADJUSTMENT_NORMALIZATION_FACTOR = 1.0 / Math.sqrt(2*Math.PI);


    public static double getPredictedScoreDiff(double teamRank, double opponentsRank, int topScore) {
        return ((teamRank - opponentsRank)*(ScoreBot.getDifferenceAmplificationFactor(teamRank, opponentsRank))/ ScoreBot.TOP_RANK)*topScore;
    }

    public static double getProportionalScoreDifference(int actualScoreDiff, double predictedScoreDiff, int maxScore) {
        return ((double) actualScoreDiff - predictedScoreDiff) / (double) maxScore;
    }

    public static double getRankAdjustment(double teamRank, int teamScore, double opponentsRank, int opponentScore) {
        double predictedScoreDiff = ScoreBot.getPredictedScoreDiff(teamRank, opponentsRank, Math.max(teamScore,opponentScore));
        int matchScoreDiff = teamScore - opponentScore;
        return (ScoreBot.TOP_RANK/10) * ScoreBot.getProportionalScoreDifference(matchScoreDiff, predictedScoreDiff, Math.max(teamScore, opponentScore));
    };

    public static double getDifferenceAmplificationFactor(double teamRank, double opponentsRank) {
        double normalizedRankDiff = ((teamRank - opponentsRank) / ScoreBot.TOP_RANK) * ScoreBot.BOOST_NORMAL_EXPANSION;
        return (1.0 + (sndVal(normalizedRankDiff) - sndVal(ScoreBot.BOOST_NORMAL_EXPANSION)) * ScoreBot.BOOST_AMPLIFICATION);
    }

    public static double sndRankAdjustment(double currentRank, double suggestedAdjustment) {
        double normalRank = ((currentRank - ScoreBot.TOP_RANK/2) / (ScoreBot.TOP_RANK/2)) * ScoreBot.RANK_ADJUSTMENT_NORMAL_EXPANSION;
        return suggestedAdjustment * (ScoreBot.sndVal(normalRank) - ScoreBot.sndVal(ScoreBot.RANK_ADJUSTMENT_NORMAL_EXPANSION)) / ScoreBot.RANK_ADJUSTMENT_NORMALIZATION_FACTOR;
    }

    public static double sndVal(double z) {
        return Math.pow(Math.E,(-(0.5)*Math.pow(z,2)))/Math.sqrt(Math.PI*2);
    }
}
