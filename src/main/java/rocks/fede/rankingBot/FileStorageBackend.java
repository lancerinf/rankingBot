package rocks.fede.rankingBot;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.util.Iterator;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by fede on 18/04/17.
 */
public class FileStorageBackend implements StorageBackend {

    private static final String rankingFolder = System.getProperty("user.dir") + "/rankings";

    private static String stripSpaces(String rankingName) {
        return rankingName.replaceAll("\\W+","_");
    }

    public FileStorageBackend() throws IOException {
        Path rankingFilesDirectoryPath = Paths.get(FileStorageBackend.rankingFolder);
        if (Files.notExists(rankingFilesDirectoryPath)) Files.createDirectory(rankingFilesDirectoryPath);
    }

    private static String formatCSVEntry(String dateTime, List<String> team0, int team0_score, List<String> team1, int team1_score) {
        return dateTime + ";" + String.join(",", team0) + ";" + team0_score + ";" + String.join(",",team1) + ";" + team1_score + System.lineSeparator();
    }

    @Override
    public void newRanking(String rankingName) throws IOException {
        Path rankingFilePath = Paths.get(FileStorageBackend.rankingFolder + "/" + FileStorageBackend.stripSpaces(rankingName) + ".csv");
        if (Files.notExists(rankingFilePath)) {
            Files.createFile(rankingFilePath);
        }
    }

    @Override
    public void persistMatch(String rankingName, String dateTime, List<String> team0, int team0_score, List<String> team1, int team1_score) throws IOException {
        Path rankingFilePath = Paths.get(FileStorageBackend.rankingFolder + "/" + FileStorageBackend.stripSpaces(rankingName) + ".csv");
        if (Files.exists(rankingFilePath)) {
            String formattedCSVEntry = FileStorageBackend.formatCSVEntry(dateTime, team0, team0_score, team1, team1_score);
            Files.write(rankingFilePath, formattedCSVEntry.getBytes(), StandardOpenOption.APPEND);
        }
        else {
            throw new IOException("File " + rankingFilePath + " not found");
        }
    }

    @Override
    public void deleteMatch(String rankingName, String dateTime) throws IOException {
        Path rankingFilePath = Paths.get(FileStorageBackend.rankingFolder + "/" + FileStorageBackend.stripSpaces(rankingName) + ".csv");
        if (Files.exists(rankingFilePath)) {
            Path tempFilePath = Paths.get(FileStorageBackend.rankingFolder + "/" + FileStorageBackend.stripSpaces(rankingName) + "_tmp.csv");
            Files.write(tempFilePath, Files.newBufferedReader(rankingFilePath).lines().sequential().filter(entry -> ! entry.startsWith(dateTime)).collect(Collectors.toList()),StandardOpenOption.CREATE_NEW);
            Files.move(tempFilePath, rankingFilePath, StandardCopyOption.REPLACE_EXISTING);
        }
        else {
            throw new IOException("File " + rankingFilePath + " not found");
        }
    }

    @Override
    public Iterator getPersistedMatches(String rankingName) throws IOException {
        Path rankingFilePath = Paths.get(FileStorageBackend.rankingFolder + "/" + FileStorageBackend.stripSpaces(rankingName) + ".csv");
        if (Files.exists(rankingFilePath)) {
            return Files.newBufferedReader(rankingFilePath).lines().iterator();
        }
        else {
            throw new IOException("File " + rankingFilePath + " not found");
        }
    }
}
