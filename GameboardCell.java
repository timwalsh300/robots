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
    
    // constructor
    GameboardCell(int column, int row) {
        xPosition = column;
        yPosition = row;
        
        //no barriers by default
        barrierNorth = false;
        barrierEast = false;
        barrierSouth = false;
        barrierWest = false;
    }
    
    // copy contstructor
    GameboardCell(GameboardCell g) {
        xPosition = g.getX();
        yPosition = g.getY();
        barrierNorth = g.hasBarrierNorth();
        barrierEast = g.hasBarrierEast();
        barrierSouth = g.hasBarrierSouth();
        barrierWest = g.hasBarrierWest();
        distanceNorth = g.checkDistanceNorth();
        distanceEast = g.checkDistanceEast();
        distanceSouth = g.checkDistanceSouth();
        distanceWest = g.checkDistanceWest();
        isOccupied = g.isOccupied();
        isGoal = g.isGoal();
        occupyingRobot = g.getOccupyingRobot();
        goalColor = g.getGoalColor();
        goalType = g.getGoalType();
    }
    
    public int getX() {
        return xPosition;
    }
    
    public int getY() {
        return yPosition;
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
        if (pc == null) {
            isOccupied = false;
            occupyingRobot = null;
        }
        else {
            isOccupied = true;
            occupyingRobot = pc;
        }
    }
    
    public void updateDistances(GameboardCell[][] board) {
        // traverse in all directions and update distanceNorth, distanceSouth,
        // distanceEast, and distanceWest...
        distanceNorth = 0;
        for (int i = yPosition; i > 0; i--) {
            if (board[xPosition][i].hasBarrierNorth() || board[xPosition][i-1].isOccupied())
                break;
            else
                distanceNorth++;
        }
        distanceEast = 0;
        for (int i = xPosition; i < 16; i++) {
            if (board[i][yPosition].hasBarrierEast() || board[i+1][yPosition].isOccupied())
                break;
            else
                distanceEast++;
        }
        distanceSouth = 0;
        for (int i = yPosition; i < 16; i++) {
            if (board[xPosition][i].hasBarrierSouth() || board[xPosition][i+1].isOccupied())
                break;
            else
                distanceSouth++;
        }
        distanceWest = 0;
        for (int i = xPosition; i > 0; i--) {
            if (board[i][yPosition].hasBarrierWest() || board[i-1][yPosition].isOccupied())
                break;
            else
                distanceWest++;
        }
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
