package rocks.fede.rankingBot;

/**
 * Created by fede on 09/04/17.
 */
public class ScoreBot {

    // TOP_RANK is the maximum theoretical rank that a player can tend to.
    public static final double TOP_RANK = 100;

    // MAX_RANK_ADJUSTMENT is the maximum rank adjustment per game.
    public static final double MAX_RANK_ADJUSTMENT = TOP_RANK/10;

    // SND_VALUE_EXPANSION is used to project values in [0,1] onto [0,SND_VALUE_EXPANSION] of which we calculate the
    // SND value, knowing that values close to SND_VALUE_EXPANSION will be very low compared to values in 0.
    public static final double SND_VALUE_EXPANSION = 2.33;

    // SND_RANGE_ADJUSTMENT is used to bring SND values from range [0,1/Math.sqrt(2*Math.PI)] to range [0,1].
    public static final double SND_RANGE_ADJUSTMENT = Math.sqrt(2*Math.PI);


    public static double getRankAdjustment(double teamRank, double teamScore, double opponentsRank, double opponentScore) {
        double predictedScoreSurplus = ((teamRank - opponentsRank) / TOP_RANK) * Math.max(teamScore,opponentScore);
        double actualScoreSurplus = teamScore - opponentScore;
        return MAX_RANK_ADJUSTMENT * ( actualScoreSurplus - predictedScoreSurplus) / Math.max(teamScore,opponentScore);
    };

    public static double sndRankAdjustment(double currentRank, double suggestedAdjustment) {
        double normalRank = ((currentRank - TOP_RANK/2) / (TOP_RANK/2)) * SND_VALUE_EXPANSION;
        return suggestedAdjustment * (sndVal(normalRank) - sndVal(SND_VALUE_EXPANSION)) * SND_RANGE_ADJUSTMENT;
    }

    public static double sndVal(double z) {
        return Math.pow(Math.E,(-(0.5)*Math.pow(z,2)))/Math.sqrt(Math.PI*2);
    }
}
