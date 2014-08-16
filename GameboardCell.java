/*
 * This project simulates a version of the game "Ricochet Robots" in order
 * to automate and test various human player strategies (that take into
 * consideration limited working memory) against the optimal benchmark 
 * algorithm than can only be executed by a computer.
 *
 * Contributors are Nate Stickney and Tim Walsh.
 */

package robots;

/**
 * Instances of this class will fill up the game board.
 */
public class GameboardCell {
    
    private final int xPosition, yPosition;
    private boolean barrierNorth, barrierEast, barrierSouth, barrierWest;
    private int distanceNorth, distanceEast, distanceSouth, distanceWest;
    private boolean isOccupied, isGoal;
    private Robots.PieceColor occupyingRobot, goalColor;
    private Robots.GoalType goalType;
    
    public GameboardCell(int a, int b) {
        xPosition = b;
        yPosition = a;
        
        //no barriers by default
        barrierNorth = false;
        barrierEast = false;
        barrierSouth = false;
        barrierWest = false;
    }
    
    public void setGoal(Robots.PieceColor pc, Robots.GoalType gt) {
        if (pc == null || gt == null)
            isGoal = false;
        else {
            isGoal = true;
            goalColor = pc;
            goalType = gt;
        }
    }
    
    public void setBarrierNorth(boolean n) {
        barrierNorth = n;
    }
    
    public void setBarrierEast(boolean e) {
        barrierEast = e;
    }
    
    public void setBarrierSouth(boolean s) {
        barrierSouth = s;
    }
    
    public void setBarrierWest(boolean w) {
        barrierWest = w;
    }
    
    public void setOccupied(Robots.PieceColor pc) {
        if (pc == null)
            isOccupied = false;
        else {
            isOccupied = true;
            occupyingRobot = pc;
        }
    }
    
    public void updateDistances(int n, int e, int s, int w) {
        distanceNorth = n;
        distanceEast = e;
        distanceSouth = s;
        distanceWest = w;
    }
    
    public boolean hasBarrierNorth() {
        return barrierNorth;
    }
    
    public boolean hasBarrierEast() {
        return barrierEast;
    }
    
    public boolean hasBarrierSouth() {
        return barrierSouth;
    }
    
    public boolean hasBarrierWest() {
        return barrierWest;
    }
    
    public int checkDistanceNorth() {
        return distanceNorth;
    }
    
    public int checkDistanceEast() {
        return distanceEast;
    }
    
    public int checkDistanceSouth() {
        return distanceSouth;
    }
    
    public int checkDistanceWest() {
        return distanceWest;
    }
    
    public boolean isOccupied() {
        return isOccupied;
    }
    
    public boolean isGoal() {
        return isGoal;
    }
    
    public Robots.PieceColor getOccupyingRobot() {
        return occupyingRobot;
    }
    
    public Robots.GoalType getGoalType() {
        return goalType;
    }
    
    public Robots.PieceColor getGoalColor() {
        return goalColor;
    }
    
}
