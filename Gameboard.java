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
public class Gameboard {
    
    private GameboardCell[][] board;
    
    // let's hardcode barrier, goal, and robot positions for now, and
    // later we'll add parameters for the constructor to set them
    public Gameboard() {
        board = new GameboardCell[16][16];
        for (int row = 0; row < 16; row++) {
            for (int column = 0; column < 16; column++) {
                
                board[row][column] = new GameboardCell(row, column);
                
                if (row == 0)
                    board[row][column].setBarrierNorth(true);
                if (row == 15)
                    board[row][column].setBarrierSouth(true);
                if (column == 0)
                    board[row][column].setBarrierWest(true);
                if (column == 15)
                    board[row][column].setBarrierEast(true);
            }
        }
        
        // hardcoded positions using this example,
        // read top-bottom and left-right...
        // http://en.wikipedia.org/wiki/Ricochet_Robot#mediaviewer/File:Roboter.jpg
        board[1][9].setGoal(Robots.PieceColor.GREEN, Robots.GoalType.STAR);
        board[2][5].setGoal(Robots.PieceColor.BLUE, Robots.GoalType.STAR);
        board[2][14].setGoal(Robots.PieceColor.YELLOW, Robots.GoalType.MOON);
        board[4][2].setGoal(Robots.PieceColor.GREEN, Robots.GoalType.MOON);
        board[4][10].setGoal(Robots.PieceColor.RED, Robots.GoalType.PLANET);
        board[5][7].setGoal(Robots.PieceColor.RED, Robots.GoalType.GEAR);
        board[6][1].setGoal(Robots.PieceColor.YELLOW, Robots.GoalType.PLANET);
        board[6][12].setGoal(Robots.PieceColor.BLUE, Robots.GoalType.GEAR);
        board[9][3].setGoal(Robots.PieceColor.YELLOW, Robots.GoalType.STAR);
        board[10][8].setGoal(Robots.PieceColor.SILVER, Robots.GoalType.SILVER);
        board[10][13].setGoal(Robots.PieceColor.RED, Robots.GoalType.STAR);
        board[11][1].setGoal(Robots.PieceColor.RED, Robots.GoalType.MOON);
        board[11][10].setGoal(Robots.PieceColor.GREEN, Robots.GoalType.PLANET);
        board[12][6].setGoal(Robots.PieceColor.BLUE, Robots.GoalType.PLANET);
        board[12][14].setGoal(Robots.PieceColor.YELLOW, Robots.GoalType.GEAR);
        board[14][2].setGoal(Robots.PieceColor.GREEN, Robots.GoalType.GEAR);
        board[14][9].setGoal(Robots.PieceColor.BLUE, Robots.GoalType.MOON);
        
        board[13][0].setOccupied(Robots.PieceColor.BLUE);
        board[14][3].setOccupied(Robots.PieceColor.GREEN); //moved one place to the right from the picture
        board[15][0].setOccupied(Robots.PieceColor.RED);
        board[15][1].setOccupied(Robots.PieceColor.YELLOW);
        
        board[0][3].setBarrierEast(true);
        board[0][4].setBarrierWest(true);
        board[0][10].setBarrierEast(true);
        board[0][11].setBarrierWest(true);
        board[1][8].setBarrierEast(true);
        board[1][9].setBarrierWest(true);
        board[1][9].setBarrierSouth(true);
        board[1][14].setBarrierSouth(true);
        board[2][5].setBarrierEast(true);
        board[2][5].setBarrierSouth(true);
        board[2][6].setBarrierWest(true);
        board[2][9].setBarrierNorth(true);
        board[2][14].setBarrierEast(true);
        board[2][14].setBarrierNorth(true);
        board[3][2].setBarrierSouth(true);
        board[3][5].setBarrierNorth(true);
        board[4][0].setBarrierSouth(true);
        board[4][2].setBarrierNorth(true);
        board[4][2].setBarrierEast(true);
        board[4][3].setBarrierWest(true);
        board[4][10].setBarrierSouth(true);
        board[4][10].setBarrierEast(true);
        board[4][15].setBarrierSouth(true);
        board[5][0].setBarrierNorth(true);
        board[5][1].setBarrierSouth(true);
        board[5][6].setBarrierEast(true);
        board[5][7].setBarrierSouth(true);
        board[5][7].setBarrierWest(true);
        board[5][7].setBarrierSouth(true);
        board[5][10].setBarrierNorth(true);
        board[5][12].setBarrierSouth(true);
        board[5][15].setBarrierNorth(true);
        board[6][0].setBarrierEast(true);
        board[6][1].setBarrierNorth(true);
        board[6][1].setBarrierWest(true);
        board[6][7].setBarrierNorth(true);
        board[6][7].setBarrierSouth(true);
        board[6][8].setBarrierSouth(true);
        board[6][11].setBarrierEast(true);
        board[6][12].setBarrierNorth(true);
        board[6][12].setBarrierWest(true);
        board[7][6].setBarrierEast(true);
        board[7][9].setBarrierWest(true);
        board[8][3].setBarrierSouth(true);
        board[8][6].setBarrierEast(true);
        board[8][9].setBarrierWest(true);
        board[8][15].setBarrierSouth(true);
        board[9][3].setBarrierNorth(true);
        board[9][3].setBarrierEast(true);
        board[9][4].setBarrierWest(true);
        board[9][7].setBarrierNorth(true);
        board[9][8].setBarrierNorth(true);
        board[9][8].setBarrierSouth(true);
        board[9][13].setBarrierSouth(true);
        board[9][15].setBarrierNorth(true);
        board[10][7].setBarrierEast(true);
        board[10][8].setBarrierNorth(true);
        board[10][8].setBarrierWest(true);
        board[10][12].setBarrierEast(true);
        board[10][13].setBarrierNorth(true);
        board[10][13].setBarrierWest(true);
        board[11][0].setBarrierEast(true);
        board[11][1].setBarrierSouth(true);
        board[11][1].setBarrierWest(true);
        board[11][10].setBarrierSouth(true);
        board[11][10].setBarrierEast(true);
        board[11][11].setBarrierWest(true);
        board[12][1].setBarrierNorth(true);
        board[12][6].setBarrierSouth(true);
        board[12][6].setBarrierEast(true);
        board[12][10].setBarrierNorth(true);
        board[12][13].setBarrierEast(true);
        board[12][14].setBarrierSouth(true);
        board[12][14].setBarrierWest(true);
        board[13][0].setBarrierSouth(true);
        board[13][2].setBarrierSouth(true);
        board[13][0].setBarrierSouth(true);
        board[13][6].setBarrierNorth(true);
        board[13][9].setBarrierSouth(true);
        board[13][14].setBarrierNorth(true);
        board[14][0].setBarrierNorth(true);
        board[14][1].setBarrierEast(true);
        board[14][2].setBarrierNorth(true);
        board[14][2].setBarrierWest(true);
        board[14][9].setBarrierEast(true);
        board[14][9].setBarrierNorth(true);
        board[14][10].setBarrierWest(true);
        board[15][5].setBarrierEast(true);
        board[15][6].setBarrierWest(true);
        board[15][11].setBarrierEast(true);
        board[15][12].setBarrierWest(true);
        
    }
    
    public void moveRobot(Robots.PieceColor c, Robots.Direction d) {
        
    }
    
}
