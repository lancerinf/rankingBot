package rocks.fede.rankingBot;

import rocks.fede.rankingBot.exceptions.MessageParsingException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by fede on 07/05/17.
 */
public class ParsedMessage {

    private String command;
    private List<String> team0;
    private List<String> team1;
    private int team0_score;
    private int team1_score;
    private String cup_name;
    private String specialty_name;
    private String admin_name;

    public ParsedMessage(String message) throws MessageParsingException {
        parseString(message);
    }

    private boolean isPlayerName(String possiblePlayerName) {
        if (possiblePlayerName.startsWith("@") && possiblePlayerName.contains(".") && possiblePlayerName.length() > 3)
            return true;
        else return false;
    }

    private void parseString(String message) throws MessageParsingException {
        List<String> messageComponents = new ArrayList<String>(Arrays.asList(message.split(" ")));
        try {
            messageComponents.remove(0);
            switch (messageComponents.get(0)) {
                case "result":
                    this.command = messageComponents.remove(0);
                    if (isPlayerName(messageComponents.get(0))) {
                        List<String> team0 = new ArrayList<>();
                        this.team0 = team0;
                        team0.add(messageComponents.remove(0));
                        if (isPlayerName(messageComponents.get(0))) {
                            team0.add(messageComponents.remove(0));
                        }
                        this.team0_score = Integer.parseInt(messageComponents.remove(0));
                        if (isPlayerName(messageComponents.get(0))) {
                            List<String> team1 = new ArrayList<>();
                            this.team1 = team1;
                            team1.add(messageComponents.remove(0));
                            if (isPlayerName(messageComponents.get(0))) {
                                team1.add(messageComponents.remove(0));
                            }
                            this.team1_score = Integer.parseInt(messageComponents.remove(0));
                        }
                        else
                            throw new MessageParsingException("Unable to parse current message: " + message);
                    }
                    else
                        throw new MessageParsingException("Unable to parse current message: " + message);
                    break;
                case "ranking":
                    this.command = messageComponents.remove(0);
                    if (messageComponents.size() >= 2)
                        this.cup_name = messageComponents.remove(0);
                    else
                        this.cup_name = "default";
                    break;
                case "cups":
                    this.command = messageComponents.remove(0);
                    break;
                case "cup_new":
                    this.command = messageComponents.remove(0);
                    this.cup_name = messageComponents.remove(0);
                    break;
                case "cup_delete":
                    this.command = messageComponents.remove(0);
                    this.cup_name = messageComponents.remove(0);
                    break;
                case "cup_enable":
                    this.command = messageComponents.remove(0);
                    this.cup_name = messageComponents.remove(0);
                    break;
                case "cup_disable":
                    this.command = messageComponents.remove(0);
                    this.cup_name = messageComponents.remove(0);
                    break;
                case "cup_rebuild_ranking":
                    this.command = messageComponents.remove(0);
                    this.cup_name = messageComponents.remove(0);
                    break;
                case "specialty_new":
                    this.command = messageComponents.remove(0);
                    this.specialty_name = messageComponents.remove(0);
                    break;
                case "specialty_bind":
                    this.command = messageComponents.remove(0);
                    break;
                case "admin_new":
                    this.command = messageComponents.remove(0);
                    this.admin_name = messageComponents.remove(0);
                    break;
                case "admin_delete":
                    this.command = messageComponents.remove(0);
                    this.admin_name = messageComponents.remove(0);
                    break;
                default:
                    break;
            }
        }
        catch (IndexOutOfBoundsException e) {
            throw new MessageParsingException("Unable to parse current message: " + message);
        }
        catch (NumberFormatException e) {
            throw new MessageParsingException("Unable to parse current message: " + message);
        }
    }

    public String getCommand() {
        return command;
    }

    public List<String> getTeam0() {
        return team0;
    }

    public List<String> getTeam1() {
        return team1;
    }

    public int getTeam0Score() {
        return team0_score;
    }

    public int getTeam1Score() {
        return team1_score;
    }

    public String getCupName() {
        return cup_name;
    }

    public String getSpecialtyName() {
        return specialty_name;
    }

    public String getAdminName() {
        return admin_name;
    }


}
