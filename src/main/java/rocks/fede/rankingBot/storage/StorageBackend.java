package rocks.fede.rankingBot.storage;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

/**
 * Created by fede on 18/04/17.
 */
public interface StorageBackend {

    public static final int MAX_LAST_MATCHES_NUMBER = 10;

    public void newRanking(String rankingName) throws IOException;
    public void persistMatch(String rankingName, String dateTime, List<String> team0, int team0_score, List<String> team1, int team1_score) throws IOException;
    public void deleteMatch(String rankingName, String dateTime) throws IOException;
    public List<String> getLastMatches(String rankingName, int lastMatchesNumber) throws IOException;
    public Iterator<String> getPersistedMatches(String rankingName) throws IOException;
}
