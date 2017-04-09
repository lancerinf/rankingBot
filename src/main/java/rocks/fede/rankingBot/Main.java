package rocks.fede.rankingBot;

/**
 * Created by fede on 09/04/17.
 */
public class Main {

    public static void main(String[] args){
        double myRank = 85;
        double opponentRank = 15;
        int TOP_SCORE = 5;
        int predictedResult = TOP_SCORE - ScoreBot.predictScoreDifference(myRank,opponentRank,TOP_SCORE);
        int manualPreResult = (int) (TOP_SCORE - Math.abs((myRank - opponentRank)/ ScoreBot.TOP_SCORE)*TOP_SCORE);
        System.out.println("With my team at rank " + myRank + " and opponent at rank " + opponentRank + " the predicted result is " + TOP_SCORE + " - " + predictedResult);
        System.out.println("With my team at rank " + myRank + " and opponent at rank " + opponentRank + " the manually predicted result is " + TOP_SCORE + " - " + manualPreResult);

    }
}
