package rocks.fede.rankingBot;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

/**
 * Created by fede on 18/04/17.
 */
public interface StorageBackend {

    public void newRanking(String rankingName) throws IOException;
    public void persistMatch(String rankingName, String dateTime, List<String> team0, int team0_score, List<String> team1, int team1_score) throws IOException;
    public void deleteMatch(String rankingName, String dateTime) throws IOException;
    public Iterator<String> getPersistedMatches(String rankingName) throws IOException;
}
