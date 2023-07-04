package PacMan.model;

public class Level {
    private int NumberOfGhosts;
    private int DelayBetweenGhosts;
    private double MovementSpeedup;

    public Level(int numGhosts, int delay, double GhostSpeed){
        NumberOfGhosts = numGhosts;
        DelayBetweenGhosts = delay;
        MovementSpeedup = GhostSpeed;
    }

    public int getNumGhosts() {
        return NumberOfGhosts;
    }

    public int getDelay() {
        return DelayBetweenGhosts;
    }

    public double getSpeed() {
        return MovementSpeedup;
    }

}
