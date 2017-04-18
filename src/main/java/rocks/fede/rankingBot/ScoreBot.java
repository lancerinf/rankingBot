package rocks.fede.rankingBot;

/**
 * Created by fede on 09/04/17.
 */
public class ScoreBot {

    // TOP_RANK is the maximum theoretical rank that a player can tend to.
    public static final double TOP_RANK = 100;

    // SND_VALUE_EXPANSION is tipically used to project a ratio with values in [0,1] onto a value [0,3] of which we
    // calculate the SND value, with the understanding that SND(3) = 99.7%.
    public static final double SND_VALUE_EXPANSION = 3.0;

    // SND_RANGE_ADJUSTMENT is used to bring SND values from range [0,Math.sqrt(2*Math.PI)] to range [0,1].
    public static final double SND_RANGE_ADJUSTMENT = 1.0 / Math.sqrt(2*Math.PI);


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
        double normalizedRankDiff = ((teamRank - opponentsRank) / ScoreBot.TOP_RANK) * ScoreBot.SND_VALUE_EXPANSION;
        return 1.0 + (sndVal(normalizedRankDiff) - sndVal(ScoreBot.SND_VALUE_EXPANSION)) / ScoreBot.SND_RANGE_ADJUSTMENT;
    }

    public static double sndRankAdjustment(double currentRank, double suggestedAdjustment) {
        double normalRank = ((currentRank - ScoreBot.TOP_RANK/2) / (ScoreBot.TOP_RANK/2)) * ScoreBot.SND_VALUE_EXPANSION;
        return suggestedAdjustment * (ScoreBot.sndVal(normalRank) - ScoreBot.sndVal(ScoreBot.SND_VALUE_EXPANSION)) / ScoreBot.SND_RANGE_ADJUSTMENT;
    }

    public static double sndVal(double z) {
        return Math.pow(Math.E,(-(0.5)*Math.pow(z,2)))/Math.sqrt(Math.PI*2);
    }
}
