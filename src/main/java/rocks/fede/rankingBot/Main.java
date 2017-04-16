package rocks.fede.rankingBot;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by fede on 09/04/17.
 */
public class Main {

    public static void main(String[] args){
        double myRank = 50;
        double opponentRank = 20;
        int TOP_SCORE = 25;
        int predictedResult = TOP_SCORE - (int) ScoreBot.getPredictedScoreDiff(myRank,opponentRank,TOP_SCORE);
        System.out.println("With my team at rank " + myRank + " and opponent at rank " + opponentRank + " the predicted result is " + TOP_SCORE + " - " + predictedResult);

        Ranking overall = new Ranking("Overall ranking");
        overall.newMatch("federico" , 10, "sofia", 5);
        overall.newMatch("federico" , 10, "andrea", 5);
        overall.newMatch("federico" , 10, "sofia", 2);
        overall.newMatch("federico" , "andrea", 10, "sofia", 2);
        overall.newMatch("federico" , 10, "sofia", 2);
        overall.newMatch("federico" , 10, "sofia", 2);
        overall.printRanking();
        overall.newMatch("federico" , 2, "sofia", 10);
        overall.printRanking();
    }
}
