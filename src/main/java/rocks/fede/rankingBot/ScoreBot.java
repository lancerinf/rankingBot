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


    public static double getPredictedScoreDiff(double teamRank, double opponentsRank, int topScore) {
        return ((teamRank - opponentsRank)*(ScoreBot.getDifferenceAmplificationFactor(teamRank, opponentsRank))/ ScoreBot.TOP_SCORE)*topScore;
    }

    public static double getProportionalScoreDifference(int actualScoreDiff, double predictedScoreDiff, int maxScore) {
        return ((double) actualScoreDiff - predictedScoreDiff) / (double) maxScore;
    }

    public static double getRankAdjustment(double teamRank, int teamScore, double opponentsRank, int opponentScore) {
        double predictedScoreDiff = ScoreBot.getPredictedScoreDiff(teamRank, opponentsRank, Math.max(teamScore,opponentScore));
        int matchScoreDiff = teamScore - opponentScore;
        return (ScoreBot.TOP_SCORE/5) * ScoreBot.getProportionalScoreDifference(matchScoreDiff, predictedScoreDiff, Math.max(teamScore, opponentScore));
    };

    public static double getDifferenceAmplificationFactor(double teamRank, double opponentsRank) {
        double normalizedRankDiff = ((teamRank - opponentsRank)/ScoreBot.TOP_SCORE)*ScoreBot.BOOST_NORMAL_EXPANSION;
        return (1.0 + Math.abs(sndVal(normalizedRankDiff)*ScoreBot.BOOST_AMPLIFICATION));
    }

    public static double sndRankAdjustment(double currentRank, double suggestedAdjustment) {
        return suggestedAdjustment * ScoreBot.sndVal(ScoreBot.BOOST_NORMAL_EXPANSION * (currentRank - (ScoreBot.TOP_SCORE/2)) / ScoreBot.TOP_SCORE);
    }

    public static double sndVal(double z) {
        return Math.pow(Math.E,(-(0.5)*Math.pow(z,2)))/Math.sqrt(Math.PI*2);
    }
}
