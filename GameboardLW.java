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
 * This is the class that will be instantiated to represent the game board.
 * It is a lighter-weight implementation of the original Gameboard class,
 * intended to take up less space on the heap...
 */
public class GameboardLW {
    
    private boolean[][] barriersNorth;
    private boolean[][] barriersEast;
    private boolean[][] barriersSouth;
    private boolean[][] barriersWest;
    private RobotPiece[][] robotLocations;
    private RobotBookmark redRobot, blueRobot, greenRobot, yellowRobot;
    private Goal[][] goalLocations;
    private Goal targetGoal;
    private boolean boardIsSolved;
    
    // constructor
    public GameboardLW() {
        barriersNorth = new boolean[16][16];
        barriersEast = new boolean[16][16];
        barriersSouth = new boolean[16][16];
        barriersWest = new boolean[16][16];
        robotLocations = new RobotPiece[16][16];
        goalLocations = new Goal[16][16];
        for (int i = 0; i < 16; i++) {
            for (int j = 0; j < 16; j++) {
                robotLocations[i][j] = RobotPiece.NO_ROBOT;
                goalLocations[i][j] = Goal.NO_GOAL;
            }
        }
        boardIsSolved = false;
        
        // fixed barriers around edges and center
        for (int i = 0; i < 16; i++) {
            barriersNorth[i][0] = true;
        }
        barriersNorth[7][9] = true;
        barriersNorth[8][9] = true;
        
        for (int i = 0; i < 16; i++) {
            barriersEast[15][i] = true;
        }
        barriersEast[6][7] = true;
        barriersEast[6][8] = true;
        
        for (int i = 0; i < 16; i++) {
            barriersSouth[i][15] = true;
        }
        barriersSouth[7][6] = true;
        barriersSouth[8][6] = true;
        
        for (int i = 0; i < 16; i++) {
            barriersWest[0][i] = true;
        }
        barriersWest[9][7] = true;
        barriersWest[9][8] = true;
        
        // let's hardcode barrier, goal, and robot positions for now, and
        // later we'll add parameters for the constructor to set them
        barriersNorth[9][2] = true;
        barriersNorth[14][2] = true;
        barriersNorth[5][3] = true;
        barriersNorth[2][4] = true;
        barriersNorth[0][5] = true;
        barriersNorth[10][5] = true;
        barriersNorth[15][5] = true;
        barriersNorth[1][6] = true;
        barriersNorth[7][6] = true;
        barriersNorth[12][6] = true;
        barriersNorth[3][9] = true;
        barriersNorth[15][9] = true;
        barriersNorth[8][10] = true;
        barriersNorth[13][10] = true;
        barriersNorth[1][12] = true;
        barriersNorth[10][12] = true;
        barriersNorth[6][13] = true;
        barriersNorth[14][13] = true;
        barriersNorth[0][14] = true;
        barriersNorth[2][14] = true;
        barriersNorth[9][14] = true;
        
        barriersSouth[9][1] = true;
        barriersSouth[14][1] = true;
        barriersSouth[5][2] = true;
        barriersSouth[2][3] = true;
        barriersSouth[0][4] = true;
        barriersSouth[10][4] = true;
        barriersSouth[15][4] = true;
        barriersSouth[1][5] = true;
        barriersSouth[7][5] = true;
        barriersSouth[12][5] = true;
        barriersSouth[3][8] = true;
        barriersSouth[15][8] = true;
        barriersSouth[8][9] = true;
        barriersSouth[13][9] = true;
        barriersSouth[1][11] = true;
        barriersSouth[10][11] = true;
        barriersSouth[6][12] = true;
        barriersSouth[14][12] = true;
        barriersSouth[0][13] = true;
        barriersSouth[2][13] = true;
        barriersSouth[9][13] = true;
        
        barriersEast[3][0] = true;
        barriersEast[10][0] = true;
        barriersEast[8][1] = true;
        barriersEast[5][2] = true;
        barriersEast[14][2] = true;
        barriersEast[2][4] = true;
        barriersEast[10][4] = true;
        barriersEast[6][5] = true;
        barriersEast[0][6] = true;
        barriersEast[11][6] = true;
        barriersEast[3][9] = true;
        barriersEast[7][10] = true;
        barriersEast[12][10] = true;
        barriersEast[0][11] = true;
        barriersEast[10][11] = true;
        barriersEast[6][12] = true;
        barriersEast[13][12] = true;
        barriersEast[1][14] = true;
        barriersEast[9][14] = true;
        barriersEast[5][15] = true;
        barriersEast[11][15] = true;
        
        barriersWest[4][0] = true;
        barriersWest[11][0] = true;
        barriersWest[9][1] = true;
        barriersWest[6][2] = true;
        barriersWest[15][2] = true;
        barriersWest[3][4] = true;
        barriersWest[11][4] = true;
        barriersWest[7][5] = true;
        barriersWest[1][6] = true;
        barriersWest[12][6] = true;
        barriersWest[4][9] = true;
        barriersWest[8][10] = true;
        barriersWest[13][10] = true;
        barriersWest[1][11] = true;
        barriersWest[11][11] = true;
        barriersWest[7][12] = true;
        barriersWest[14][12] = true;
        barriersWest[2][14] = true;
        barriersWest[10][14] = true;
        barriersWest[6][15] = true;
        barriersWest[12][15] = true;
        
        goalLocations[9][1] = Goal.GREEN_STAR;
        goalLocations[5][2] = Goal.BLUE_STAR;
        goalLocations[14][2] = Goal.YELLOW_MOON;
        goalLocations[2][4] = Goal.GREEN_MOON;
        goalLocations[10][4] = Goal.RED_PLANET;
        goalLocations[7][5] = Goal.RED_GEAR;
        goalLocations[1][6] = Goal.YELLOW_PLANET;
        goalLocations[12][6] = Goal.BLUE_GEAR;
        goalLocations[3][9] = Goal.YELLOW_STAR;
        goalLocations[8][10] = Goal.SILVER_GOAL;
        goalLocations[13][10] = Goal.RED_STAR;
        goalLocations[1][11] = Goal.RED_MOON;
        goalLocations[10][11] = Goal.GREEN_PLANET;
        goalLocations[6][12] = Goal.BLUE_PLANET;
        goalLocations[14][12] = Goal.YELLOW_GEAR;
        goalLocations[2][14] = Goal.GREEN_GEAR;
        goalLocations[9][14] = Goal.BLUE_MOON;
        
        blueRobot = new RobotBookmark(RobotPiece.BLUE_ROBOT, 0, 13);
        robotLocations[blueRobot.column()][blueRobot.row()] = RobotPiece.BLUE_ROBOT;
        redRobot = new RobotBookmark(RobotPiece.RED_ROBOT, 0, 15);
        robotLocations[redRobot.column()][redRobot.row()] = RobotPiece.RED_ROBOT;
        greenRobot = new RobotBookmark(RobotPiece.GREEN_ROBOT, 2, 14);
        robotLocations[greenRobot.column()][greenRobot.row()] = RobotPiece.GREEN_ROBOT;
        yellowRobot = new RobotBookmark(RobotPiece.YELLOW_ROBOT, 1, 15);
        robotLocations[yellowRobot.column()][yellowRobot.row()] = RobotPiece.YELLOW_ROBOT;
        
        //flip a card
        targetGoal = Goal.RED_STAR;
        
    }
    
    // copy constructor
    public GameboardLW(GameboardLW g) {
        barriersNorth = new boolean[16][16];
        for (int i = 0; i < 16; i++) {
            System.arraycopy(g.barriersNorth[i], 0, barriersNorth[i], 0, 16);
        }
        barriersEast = new boolean[16][16];
        for (int i = 0; i < 16; i++) {
            System.arraycopy(g.barriersEast[i], 0, barriersEast[i], 0, 16);
        }
        barriersSouth = new boolean[16][16];
        for (int i = 0; i < 16; i++) {
            System.arraycopy(g.barriersSouth[i], 0, barriersSouth[i], 0, 16);
        }
        barriersWest = new boolean[16][16];
        for (int i = 0; i < 16; i++) {
            System.arraycopy(g.barriersWest[i], 0, barriersWest[i], 0, 16);
        }
        robotLocations = new RobotPiece[16][16];
        for (int i = 0; i < 16; i++) {
            System.arraycopy(g.robotLocations[i], 0, robotLocations[i], 0, 16);
        }
        goalLocations = new Goal[16][16];
        for (int i = 0; i < 16; i++) {
            System.arraycopy(g.goalLocations[i], 0, goalLocations[i], 0, 16);
        }
        targetGoal = g.targetGoal;
        redRobot = new RobotBookmark(RobotPiece.RED_ROBOT, g.redRobot.column(), g.redRobot.row());
        blueRobot = new RobotBookmark(RobotPiece.BLUE_ROBOT, g.blueRobot.column(), g.blueRobot.row());
        greenRobot = new RobotBookmark(RobotPiece.GREEN_ROBOT, g.greenRobot.column(), g.greenRobot.row());
        yellowRobot = new RobotBookmark(RobotPiece.YELLOW_ROBOT, g.yellowRobot.column(), g.yellowRobot.row());
        boardIsSolved = g.boardIsSolved;
    }
    
    public boolean boardIsSolved() {
        return boardIsSolved;
    }
    
    public boolean robotCanMove(RobotPiece r, Direction d) {
        int xCheck, yCheck;
        switch (r) {
            case RED_ROBOT:
                xCheck = redRobot.column();
                yCheck = redRobot.row();
                break;
            case BLUE_ROBOT:
                xCheck = blueRobot.column();
                yCheck = blueRobot.row();
                break;
            case YELLOW_ROBOT:
                xCheck = yellowRobot.column();
                yCheck = yellowRobot.row();
                break;
            case GREEN_ROBOT:
                xCheck = greenRobot.column();
                yCheck = greenRobot.row();
                break;
            default:
                xCheck = -1;
                yCheck = -1;
                break;
        }
        switch (d) {
            case NORTH:
                if (barriersNorth[xCheck][yCheck])
                    return false;
                else if (yCheck > 1)
                    return robotLocations[xCheck][yCheck - 1].equals(RobotPiece.NO_ROBOT);
            case EAST:
                if (barriersEast[xCheck][yCheck])
                    return false;
                else if (xCheck < 15)
                    return robotLocations[xCheck + 1][yCheck].equals(RobotPiece.NO_ROBOT);
            case SOUTH:
                if (barriersSouth[xCheck][yCheck])
                    return false;
                else if (yCheck < 15)
                    return robotLocations[xCheck][yCheck + 1].equals(RobotPiece.NO_ROBOT);
            case WEST:
                if (barriersWest[xCheck][yCheck])
                    return false;
                else if (xCheck > 1)
                    return robotLocations[xCheck - 1][yCheck].equals(RobotPiece.NO_ROBOT);
            default:
                return false;
        }
    }
    
    public void moveRobot(RobotPiece r, Direction d) {
        int oldX, oldY;
        int newX = -1, newY = -1;
        while (robotCanMove(r,d)) {
            switch (r) {
                case RED_ROBOT:
                    oldX = redRobot.column();
                    oldY = redRobot.row();
                    break;
                case BLUE_ROBOT:
                    oldX = blueRobot.column();
                    oldY = blueRobot.row();
                    break;
                case YELLOW_ROBOT:
                    oldX = yellowRobot.column();
                    oldY = yellowRobot.row();
                    break;
                case GREEN_ROBOT:
                    oldX = greenRobot.column();
                    oldY = greenRobot.row();
                    break;
                default:
                    oldX = -1;
                    oldY = -1;
                    break;
            }
            newX = oldX;
            newY = oldY;
            switch (d) {
                case NORTH:
                    newY--;
                    break;
                case EAST:
                    newX++;
                    break;
                case SOUTH:
                    newY++;
                    break;
                case WEST:
                    newX--;
                    break;
            }
            switch (r) {
                case RED_ROBOT:
                    redRobot.setLoc(newX, newY);
                    break;
                case BLUE_ROBOT:
                    blueRobot.setLoc(newX, newY);
                    break;
                case YELLOW_ROBOT:
                    yellowRobot.setLoc(newX, newY);
                    break;
                case GREEN_ROBOT:
                    greenRobot.setLoc(newX, newY);
                    break;
            }
            robotLocations[oldX][oldY] = RobotPiece.NO_ROBOT;
            robotLocations[newX][newY] = r;
        }
        // check for solution
        if (robotLocations[newX][newY].color().equals(goalLocations[newX][newY].color())
                && robotLocations[newX][newY].color().equals(targetGoal.color())
                && goalLocations[newX][newY] == targetGoal)
            boardIsSolved = true;
    }
    
    private class RobotBookmark {
        
        private int column;
        private int row;
        private final RobotPiece robot;
        
        public RobotBookmark(RobotPiece r, int x, int y) {
            robot = r;
            column = x;
            row = y;
        }
        public void setLoc(int x, int y) {
            column = x;
            row = y;
        }
        public int column() {
            return column;
        }
        public int row() {
            return row;
        }
        public String color() {
            return robot.color();
        }
    }
}
