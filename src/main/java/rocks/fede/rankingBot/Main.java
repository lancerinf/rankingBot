package rocks.fede.rankingBot;

/**
 * Created by fede on 09/04/17.
 */
public class Main {

    public static void main(String[] args){
        double myRank = 50;
        double opponentRank = 20;
        int TOP_SCORE = 25;
        int predictedResult = ScoreBot.predictLowerScore(myRank,opponentRank,TOP_SCORE);
        System.out.println("With my team at rank " + myRank + " and opponent at rank " + opponentRank + " the predicted result is " + TOP_SCORE + " - " + predictedResult);

//        double base = -3.0;
//        double currentStep;
//        for (int x = 0; x <= 60; x++) {
//            currentStep = base + (x/10.0);
//            System.out.println("SND (" + Math.round(currentStep*100)/100.00 + ") = " + ScoreBot.sndVal(currentStep));
//        }

        System.out.println("differenceAmplificationFactor = " + ScoreBot.getDifferenceAmplificationFactor(myRank,opponentRank) );
    }
}
