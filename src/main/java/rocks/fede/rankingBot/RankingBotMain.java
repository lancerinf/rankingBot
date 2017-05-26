package rocks.fede.rankingBot;

import rocks.fede.rankingBot.exceptions.RequiredParameterNotFoundException;

import static java.util.Objects.isNull;

/**
 * Created by fede on 5/25/17.
 */
public class RankingBotMain {
    //TODO load parameters from environment

    private static String loadParameterFromEnv(String parameterName) {
        String parameter = System.getenv(parameterName);
        if (isNull(parameter)) {
            throw new RequiredParameterNotFoundException("Can't find required env parameter: " + parameterName);
        }
        return parameter;
    }


    //TODO start an instance of RankingBot

    public static void main(String[] args) {
        String SLACK_API_TOKEN = loadParameterFromEnv("SLACK_API_TOKEN");


    }
}
