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
 */
@Deprecated
public class Gameboard {
    
    private GameboardCell[][] board;
    private GameboardCell redRobot, blueRobot, greenRobot, yellowRobot, silverRobot;
    private GameboardCell redStar, redMoon, redPlanet, redGear;
    private GameboardCell blueStar, blueMoon, bluePlanet, blueGear;
    private GameboardCell greenStar, greenMoon, greenPlanet, greenGear;
    private GameboardCell yellowStar, yellowMoon, yellowPlanet, yellowGear;
    private GameboardCell silverGoal;
    private GoalType desiredGoalType;
    private PieceColor desiredGoalColor;
    private boolean boardIsSolved = false;
    
    // let's hardcode barrier, goal, and robot positions for now, and
    // later we'll add parameters for the constructor to set them
    public Gameboard() {
        board = new GameboardCell[16][16];
        for (int column = 0; column < 16; column++) {
            for (int row = 0; row < 16; row++) {
                
                board[column][row] = new GameboardCell(column, row);
                
                // set up barriers for the outer wall
                if (row == 0)
                    board[column][row].setBarrierNorth(true);
                if (row == 15)
                    board[column][row].setBarrierSouth(true);
                if (column == 0)
                    board[column][row].setBarrierWest(true);
                if (column == 15)
                    board[column][row].setBarrierEast(true);
            }
        }
        
        // set up barriers for the inner square
        board[6][7].setBarrierEast(true);
        board[6][8].setBarrierEast(true);
        board[7][6].setBarrierSouth(true);
        board[8][6].setBarrierSouth(true);
        board[9][7].setBarrierWest(true);
        board[9][8].setBarrierWest(true);
        board[7][9].setBarrierNorth(true);
        board[8][9].setBarrierNorth(true);
        
        // hardcode other positions using this example,
        // read left to right, top to bottom [column x][row y]...
        // http://en.wikipedia.org/wiki/Ricochet_Robot#mediaviewer/File:Roboter.jpg
        
        board[3][0].setBarrierEast(true);
        board[4][0].setBarrierWest(true);
        board[10][0].setBarrierEast(true);
        board[11][0].setBarrierWest(true);
        board[8][1].setBarrierEast(true);
        board[9][1].setBarrierWest(true);
        board[9][1].setBarrierSouth(true);
        board[14][1].setBarrierSouth(true);
        board[5][2].setBarrierEast(true);
        board[5][2].setBarrierSouth(true);
        board[6][2].setBarrierWest(true);
        board[9][2].setBarrierNorth(true);
        board[14][2].setBarrierEast(true);
        board[14][2].setBarrierNorth(true);
        board[2][3].setBarrierSouth(true);
        board[5][3].setBarrierNorth(true);
        board[0][4].setBarrierSouth(true);
        board[2][4].setBarrierNorth(true);
        board[2][4].setBarrierEast(true);
        board[3][4].setBarrierWest(true);
        board[10][4].setBarrierSouth(true);
        board[10][4].setBarrierEast(true);
        board[15][4].setBarrierSouth(true);
        board[0][5].setBarrierNorth(true);
        board[1][5].setBarrierSouth(true);
        board[6][5].setBarrierEast(true);
        board[7][5].setBarrierSouth(true);
        board[7][5].setBarrierWest(true);
        board[7][5].setBarrierSouth(true);
        board[10][5].setBarrierNorth(true);
        board[12][5].setBarrierSouth(true);
        board[15][5].setBarrierNorth(true);
        board[0][6].setBarrierEast(true);
        board[1][6].setBarrierNorth(true);
        board[1][6].setBarrierWest(true);
        board[7][6].setBarrierNorth(true);
        board[11][6].setBarrierEast(true);
        board[12][6].setBarrierNorth(true);
        board[12][6].setBarrierWest(true);
        board[3][8].setBarrierSouth(true);
        board[15][8].setBarrierSouth(true);
        board[3][9].setBarrierNorth(true);
        board[3][9].setBarrierEast(true);
        board[4][9].setBarrierWest(true);
        board[8][9].setBarrierSouth(true);
        board[13][9].setBarrierSouth(true);
        board[15][9].setBarrierNorth(true);
        board[7][10].setBarrierEast(true);
        board[8][10].setBarrierNorth(true);
        board[8][10].setBarrierWest(true);
        board[12][10].setBarrierEast(true);
        board[13][10].setBarrierNorth(true);
        board[13][10].setBarrierWest(true);
        board[0][11].setBarrierEast(true);
        board[1][11].setBarrierSouth(true);
        board[1][11].setBarrierWest(true);
        board[10][11].setBarrierSouth(true);
        board[10][11].setBarrierEast(true);
        board[11][11].setBarrierWest(true);
        board[1][12].setBarrierNorth(true);
        board[6][12].setBarrierSouth(true);
        board[6][12].setBarrierEast(true);
        board[10][12].setBarrierNorth(true);
        board[13][12].setBarrierEast(true);
        board[14][12].setBarrierSouth(true);
        board[14][12].setBarrierWest(true);
        board[0][13].setBarrierSouth(true);
        board[2][13].setBarrierSouth(true);
        board[0][13].setBarrierSouth(true);
        board[6][13].setBarrierNorth(true);
        board[9][13].setBarrierSouth(true);
        board[14][13].setBarrierNorth(true);
        board[0][14].setBarrierNorth(true);
        board[1][14].setBarrierEast(true);
        board[2][14].setBarrierNorth(true);
        board[2][14].setBarrierWest(true);
        board[9][14].setBarrierEast(true);
        board[9][14].setBarrierNorth(true);
        board[10][14].setBarrierWest(true);
        board[5][15].setBarrierEast(true);
        board[6][15].setBarrierWest(true);
        board[11][15].setBarrierEast(true);
        board[12][15].setBarrierWest(true);
        
        // set and record initial locations of goals
        board[9][1].setGoal(PieceColor.GREEN, GoalType.STAR);
        greenStar = new GameboardCell(9,1);
        board[5][2].setGoal(PieceColor.BLUE, GoalType.STAR);
        blueStar = new GameboardCell(5,2);
        board[14][2].setGoal(PieceColor.YELLOW, GoalType.MOON);
        yellowMoon = new GameboardCell(14,2);
        board[2][4].setGoal(PieceColor.GREEN, GoalType.MOON);
        greenMoon = new GameboardCell(2,4);
        board[10][4].setGoal(PieceColor.RED, GoalType.PLANET);
        redPlanet = new GameboardCell(10,4);
        board[7][5].setGoal(PieceColor.RED, GoalType.GEAR);
        redGear = new GameboardCell(7,5);
        board[1][6].setGoal(PieceColor.YELLOW, GoalType.PLANET);
        yellowPlanet = new GameboardCell(1,6);
        board[12][6].setGoal(PieceColor.BLUE, GoalType.GEAR);
        blueGear = new GameboardCell(12,6);
        board[3][9].setGoal(PieceColor.YELLOW, GoalType.STAR);
        yellowStar = new GameboardCell(3,9);
        board[8][10].setGoal(PieceColor.SILVER, GoalType.SILVER);
        silverGoal = new GameboardCell(8,10);
        board[13][10].setGoal(PieceColor.RED, GoalType.STAR);
        redStar = new GameboardCell(13,10);
        board[1][11].setGoal(PieceColor.RED, GoalType.MOON);
        redMoon = new GameboardCell(1,11);
        board[10][11].setGoal(PieceColor.GREEN, GoalType.PLANET);
        greenPlanet = new GameboardCell(10,11);
        board[6][12].setGoal(PieceColor.BLUE, GoalType.PLANET);
        bluePlanet = new GameboardCell(6,12);
        board[9][1].setGoal(PieceColor.YELLOW, GoalType.GEAR);
        yellowGear = new GameboardCell(9,1);
        board[2][14].setGoal(PieceColor.GREEN, GoalType.GEAR);
        greenGear = new GameboardCell(2,14);
        board[9][14].setGoal(PieceColor.BLUE, GoalType.MOON);
        blueMoon = new GameboardCell(9,14);        
        
        // set and record initial locations of robots
        board[0][13].setOccupied(PieceColor.BLUE);
        blueRobot = new GameboardCell(0,13);
        board[2][14].setOccupied(PieceColor.GREEN);
        greenRobot = new GameboardCell(2,14);
        board[0][15].setOccupied(PieceColor.RED);
        redRobot = new GameboardCell(0,15);
        board[1][15].setOccupied(PieceColor.YELLOW);
        yellowRobot = new GameboardCell(1,15);
        //board[12][15].setOccupied(PieceColor.SILVER);
        //silverRobot = new GameboardCell(12,15);
        
        // flip a card
        desiredGoalType = GoalType.STAR;
        desiredGoalColor = PieceColor.RED;
        
        // determine initial distances to barriers/robots in all directions from each cell
        for (int i = 0; i < 16; i++) {
            for (int j = 0; j < 16; j++) {
                board[i][j].updateDistances(this.board);
            }
        }
    }
    
    // copy constructor
    public Gameboard(Gameboard g) {
        board = new GameboardCell[16][16];
        for (int i = 0; i < 16; i++) {
            for (int j = 0; j < 16; j++) {
                board[i][j] = new GameboardCell(g.board[i][j]);
            }
        }
        redRobot = new GameboardCell(g.redRobot);
        blueRobot = new GameboardCell(g.blueRobot);
        greenRobot = new GameboardCell(g.greenRobot);
        yellowRobot = new GameboardCell(g.yellowRobot);
        //silverRobot = new GameboardCell(g.silverRobot);
        redStar = new GameboardCell(g.redStar);
        redPlanet = new GameboardCell(g.redPlanet);
        redMoon = new GameboardCell(g.redMoon);
        redGear = new GameboardCell(g.redGear);
        blueStar = new GameboardCell(g.blueStar);
        bluePlanet = new GameboardCell(g.bluePlanet);
        blueMoon = new GameboardCell(g.blueMoon);
        blueGear = new GameboardCell(g.blueGear);
        yellowStar = new GameboardCell(g.yellowStar);
        yellowPlanet = new GameboardCell(g.yellowPlanet);
        yellowMoon = new GameboardCell(g.yellowMoon);
        yellowGear = new GameboardCell(g.yellowGear);
        greenStar = new GameboardCell(g.greenStar);
        greenPlanet = new GameboardCell(g.greenPlanet);
        greenMoon = new GameboardCell(g.greenMoon);
        greenGear = new GameboardCell(g.greenGear);
        silverGoal = new GameboardCell(g.silverGoal);
        desiredGoalType = g.desiredGoalType;
        desiredGoalColor = g.desiredGoalColor;
        boardIsSolved = g.boardIsSolved();
    }
    
    public boolean boardIsSolved() {
        return boardIsSolved;
    }
    
    public boolean robotCanMove(PieceColor c, Direction d) {
        int xCheck, yCheck;
        switch (c) {
            case RED:
                xCheck = redRobot.getX();
                yCheck = redRobot.getY();
                break;
            case BLUE:
                xCheck = blueRobot.getX();
                yCheck = blueRobot.getY();
                break;
            case YELLOW:
                xCheck = yellowRobot.getX();
                yCheck = yellowRobot.getY();
                break;
            case GREEN:
                xCheck = greenRobot.getX();
                yCheck = greenRobot.getY();
                break;
//            case SILVER:
//                xCheck = silverRobot.getX();
//                yCheck = silverRobot.getY();
//                break; 
            default:
                xCheck = -1;
                yCheck = -1;
                break;
        }
        switch (d) {
            case NORTH:
                return !board[xCheck][yCheck].hasBarrierNorth();
            case EAST:
                return !board[xCheck][yCheck].hasBarrierEast();
            case SOUTH:
                return !board[xCheck][yCheck].hasBarrierSouth();
            case WEST:
                return !board[xCheck][yCheck].hasBarrierWest();
            default:
                return false;
        }
    }
    
    public void moveRobot(PieceColor c, Direction d) {
        int oldX, oldY, newX, newY;
        
        // find the robot to move
        switch (c) {
            case RED:
                oldX = redRobot.getX();
                oldY = redRobot.getY();
                break;
            case BLUE:
                oldX = blueRobot.getX();
                oldY = blueRobot.getY();
                break;
            case YELLOW:
                oldX = yellowRobot.getX();
                oldY = yellowRobot.getY();
                break;
            case GREEN:
                oldX = greenRobot.getX();
                oldY = greenRobot.getY();
                break;
//            case SILVER:
//                oldX = silverRobot.getX();
//                oldY = silverRobot.getY();
//                break; 
            default:
                oldX = -1;
                oldY = -1;
                break;
        }
        
        // determine new location of that robot given the chosen direction
        switch (d) {
            case NORTH:
                newX = oldX;
                newY = oldY - board[oldX][oldY].checkDistanceNorth();
                break;
            case EAST:
                newX = oldX + board[oldX][oldY].checkDistanceEast();
                newY = oldY;
                break;
            case SOUTH:
                newX = oldX;
                newY = oldY + board[oldX][oldY].checkDistanceSouth();
                break;
            case WEST:
                newX = oldX - board[oldX][oldY].checkDistanceWest();
                newY = oldY;
                break;
            default:
                newX = -1;
                newY = -1;
                break;
        }
        
        // update the board
        board[oldX][oldY].setOccupied(null);
        board[newX][newY].setOccupied(c);
        // update distances for these rows and columns
        for (int i = 0; i < 16; i++) {
            board[i][oldY].updateDistances(board);
        }
        for (int i = 0; i < 16; i++) {
            board[i][newY].updateDistances(board);
        }
        for (int i = 0; i < 16; i++) {
            board[oldX][i].updateDistances(board);
        }
        for (int i = 0; i < 16; i++) {
            board[newX][i].updateDistances(board);
        }
        
        // record the new location
        switch (c) {
            case RED:
                redRobot = new GameboardCell(newX, newY);
                break;
            case BLUE:
                blueRobot = new GameboardCell(newX, newY);
                break;
            case YELLOW:
                yellowRobot = new GameboardCell(newX, newY);
                break;
            case GREEN:
                greenRobot = new GameboardCell(newX, newY);
                break;
//            case SILVER:
//                silverRobot = new GameboardCell(newX, newY);
//                break;
        }
        
        // check for solution
        if (board[newX][newY].getOccupyingRobot() == 
                board[newX][newY].getGoalColor() &&
                board[newX][newY].getGoalColor() ==
                desiredGoalColor &&
                board[newX][newY].getGoalType() ==
                desiredGoalType)
            boardIsSolved = true;
    }
    
}
