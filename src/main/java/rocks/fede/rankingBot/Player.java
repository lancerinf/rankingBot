package rocks.fede.rankingBot;

/**
 * Created by fede on 09/04/17.
 */
public class Player {

    private static final double DEFAULT_INITIAL_SCORE = 50;
    private double rank;
    private String name;

    public Player(String name){
        this.name = name;
        this.rank = Player.DEFAULT_INITIAL_SCORE;
    }
    public Player(String name, double initialScore){
        this.name = name;
        this.rank = initialScore;
    }

    public String getName(){
        return this.name;
    }
    public double getScore(){
        return this.rank;
    }
    public void updateScore(double newScore){
        this.rank = newScore;
    }
}
