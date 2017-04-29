package rocks.fede.rankingBot;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

/**
 * Created by fede on 09/04/17.
 */
public class Main {

    public static void main(String[] args) {
        try {
            StorageBackend fileStorage = new FileStorageBackend();
            Ranking overall = new Ranking("Overall ranking", fileStorage);

            List<String> team0 = Arrays.asList("federico", "sofia");
            List<String> team1 = Arrays.asList("andrea", "mirella");
            List<String> teamFede = Arrays.asList("federico");
            List<String> teamSofi = Arrays.asList("sofia");
            List<String> teamMirella = Arrays.asList("mirella");
            List<String> teamAndrea = Arrays.asList("andrea");

            for (int i = 0; i <= 5; i++) {
                overall.newMatch(getInstant(), teamFede, 10, teamSofi, 4);
                Thread.sleep(10);
            }

            overall.rebuildRanking();
            overall.printRanking();

//        for (int i = 0; i < 1; i++) {
//            overall.newMatch("federico" , 5, "sofia", 10);
//            overall.printRanking();
//        }
//        overall.newMatch("federico" , 7, "sofia", 10);
//        overall.newMatch("federico" , 10, "sofia", 0);
//        overall.newMatch("federico" , 10, "sofia", 5);
//        overall.newMatch("federico" , 10, "sofia", 2);
//        overall.newMatch("federico" , 10, "sofia", 7);
//        overall.newMatch("federico" ,"andrea", 6, "sofia", 10);
//        overall.newMatch("federico" , 5, "sofia","mirella", 10);
//        overall.newMatch("federico" ,"sofia", 10, "andrea","mirella", 4);
//        overall.newMatch("federico" , 4, "andrea","mirella", 10);
//        overall.printRanking();

//        List<String> team0 = Arrays.asList("federico", "sofia");
//        List<String> team1 = Arrays.asList("andrea", "mirella");

        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static String getInstant() {
        return "T" + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd-HHmmssSSS"));
    }
}
