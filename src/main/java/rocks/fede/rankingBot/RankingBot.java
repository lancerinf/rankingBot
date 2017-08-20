package rocks.fede.rankingBot;

import static java.util.Objects.requireNonNull;

/**
 * Created by fede on 5/25/17.
 */
public class RankingBot implements Runnable {

    private final String SLACK_API_TOKEN;

    @Override
    public void run() {
        //TODO implement websocket connection
        //TODO search for existing data
    }

    public RankingBot(RankingBotBuilder builder) {
        this.SLACK_API_TOKEN = builder.SLACK_API_TOKEN;
    }

    public static RankingBotBuilder newBuilder() {
        return new RankingBotBuilder();
    }

    public static class RankingBotBuilder {
        private String SLACK_API_TOKEN;

        public RankingBotBuilder() { }

        public RankingBotBuilder setApiToken(String slackApiToken){
            this.SLACK_API_TOKEN = requireNonNull(slackApiToken);
            return this;
        }

        public RankingBot build() {
            return new RankingBot(this);
        }
    }
}
