package rocks.fede.rankingBot;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

/**
 * Created by fede on 09/04/17.
 */
public class Main {

    public static void main(String[] args){
//        double myRank = 50;
//        double opponentRank = 20;
//        int TOP_RANK = 25;
//        int predictedResult = TOP_RANK - (int) ScoreBot.getPredictedScoreDiff(myRank,opponentRank,TOP_RANK);
//        System.out.println("With my team at rank " + myRank + " and opponent at rank " + opponentRank + " the predicted result is " + TOP_RANK + " - " + predictedResult);

//        Ranking overall = new Ranking("Overall ranking");
//        for (int i = 0; i < 10; i++) {
//            overall.newMatch("federico" , 10, "sofia", 5);
//        }
//        overall.newMatch("federico" , 10, "sofia", 7);
//        overall.newMatch("federico" , 10, "andrea", 7);
//        overall.newMatch("federico" ,"andrea", 6, "sofia", 10);
//        overall.newMatch("federico" , 5, "sofia","mirella", 10);
//        overall.newMatch("federico" ,"andrea", 10, "sofia","mirella", 4);
//        overall.printRanking();

        List<String> team0 = Arrays.asList("federico", "sofia");
        List<String> team1 = Arrays.asList("andrea", "mirella");

        try {
            StorageBackend backendHandle = new FileStorageBackend();
//            backendHandle.newRanking("overall");
//            backendHandle.persistMatch("overall", getInstant(), team0,10, team1, 1 );
//            Thread.sleep(100);
//            backendHandle.persistMatch("overall", getInstant(), team0,10, team1, 2 );
//            Thread.sleep(100);
//            backendHandle.persistMatch("overall", getInstant(), team0,10, team1, 3 );
//            Thread.sleep(100);
//            backendHandle.persistMatch("overall", getInstant(), team0,10, team1, 4 );
//            backendHandle.deleteMatch("overall", "T20170419-002004855");
//            backendHandle.deleteMatch("overall", "T20170419-001935419");
            backendHandle.getPersistedMatches("overall").forEachRemaining(System.out::println);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String getInstant() {
        return "T" + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd-HHmmssSSS"));
    }
}
