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
 * This player performs depth-limited depth-first-search to find the optimal
 * solution for a given game board. This strategy is not feasible for us
 * mortals, and it takes a long time, but its result will serve as a benchmark.
 */
public class OptimalRobotsPlayer implements RobotsPlayer{
    
    private GameboardLW initialBoard;
    private String name, solution = " ";
    private int bestNumberOfMoves = 6; // this initial number is the maximum depth to search
    private int statesExamined = 0;
    private boolean hasFoundSolution = false;
    
    public void initialize(String n, GameboardLW b) {
        name = n;
        initialBoard = b;
    }
    
    public String getName() throws NullPointerException {
        try {
            return name;
        }
        catch (NullPointerException e) {
            return "The player has not yet been intitialized.";
        }
    }
    
    //must override the run() method for actions to perform in its own thread
    @Override
    public void run() {
        // insert player logic here:
        buildAndSearchTree(0, solution, new GameboardLW(initialBoard), null, null);
        // print name and solution to the console somewhere in here
        System.out.println(getName() + " " + statesExamined);
    } 
    
    public boolean hasFoundSolution() {
        return hasFoundSolution;
    }
    
    public String getSolution() {
        if (this.hasFoundSolution())
            return (this.getName() + ", " + bestNumberOfMoves + " moves:" + solution);
        else
            return (this.getName() + " has no solution.");
    }
    
    
    private void buildAndSearchTree(int depth, String solutionThusFar, GameboardLW gameState, RobotPiece lastRobot, Direction lastDir) {
        if (lastRobot != null && lastDir != null)
                gameState.moveRobot(lastRobot, lastDir);
        if (depth > bestNumberOfMoves) {
            return;
        }
        else if (gameState.boardIsSolved()) {
            hasFoundSolution = true;
            bestNumberOfMoves = depth;
            solution = solutionThusFar;
            System.out.println(getSolution());
            return;
        }
        else {
            statesExamined++;
            if (gameState.robotCanMove(RobotPiece.RED_ROBOT, Direction.NORTH) &&
                    !(lastRobot == RobotPiece.RED_ROBOT && lastDir == Direction.SOUTH)) {
                buildAndSearchTree(depth + 1, solutionThusFar + "Red-North ", new GameboardLW(gameState), RobotPiece.RED_ROBOT, Direction.NORTH);
            }
            if (gameState.robotCanMove(RobotPiece.RED_ROBOT, Direction.EAST) &&
                    !(lastRobot == RobotPiece.RED_ROBOT && lastDir == Direction.WEST)) {
                buildAndSearchTree(depth + 1, solutionThusFar + "Red-East ", new GameboardLW(gameState), RobotPiece.RED_ROBOT, Direction.EAST);
            }
            if (gameState.robotCanMove(RobotPiece.RED_ROBOT, Direction.SOUTH) &&
                    !(lastRobot == RobotPiece.RED_ROBOT && lastDir == Direction.NORTH)) {
                buildAndSearchTree(depth + 1, solutionThusFar + "Red-South ", new GameboardLW(gameState), RobotPiece.RED_ROBOT, Direction.SOUTH);
            }
            if (gameState.robotCanMove(RobotPiece.RED_ROBOT, Direction.WEST) &&
                    !(lastRobot == RobotPiece.RED_ROBOT && lastDir == Direction.EAST)) {
                buildAndSearchTree(depth + 1, solutionThusFar + "Red-West ", new GameboardLW(gameState), RobotPiece.RED_ROBOT, Direction.WEST);
            }
            if (gameState.robotCanMove(RobotPiece.BLUE_ROBOT, Direction.NORTH) &&
                    !(lastRobot == RobotPiece.BLUE_ROBOT && lastDir == Direction.SOUTH)) {
                buildAndSearchTree(depth + 1, solutionThusFar + "Blue-North ", new GameboardLW(gameState), RobotPiece.BLUE_ROBOT, Direction.NORTH);
            }
            if (gameState.robotCanMove(RobotPiece.BLUE_ROBOT, Direction.EAST) &&
                    !(lastRobot == RobotPiece.BLUE_ROBOT && lastDir == Direction.WEST)) {
                buildAndSearchTree(depth + 1, solutionThusFar + "Blue-East ", new GameboardLW(gameState), RobotPiece.BLUE_ROBOT, Direction.EAST);
            }
            if (gameState.robotCanMove(RobotPiece.BLUE_ROBOT, Direction.SOUTH) &&
                    !(lastRobot == RobotPiece.BLUE_ROBOT && lastDir == Direction.NORTH)) {
                buildAndSearchTree(depth + 1, solutionThusFar + "Blue-South ", new GameboardLW(gameState), RobotPiece.BLUE_ROBOT, Direction.SOUTH);
            }
            if (gameState.robotCanMove(RobotPiece.BLUE_ROBOT, Direction.WEST) &&
                    !(lastRobot == RobotPiece.BLUE_ROBOT && lastDir == Direction.EAST)) {
                buildAndSearchTree(depth + 1, solutionThusFar + "Blue-West ", new GameboardLW(gameState), RobotPiece.BLUE_ROBOT, Direction.WEST);
            }
            if (gameState.robotCanMove(RobotPiece.YELLOW_ROBOT, Direction.NORTH) &&
                    !(lastRobot == RobotPiece.YELLOW_ROBOT && lastDir == Direction.SOUTH)) {
                buildAndSearchTree(depth + 1, solutionThusFar + "Yellow-North ", new GameboardLW(gameState), RobotPiece.YELLOW_ROBOT, Direction.NORTH);
            }
            if (gameState.robotCanMove(RobotPiece.YELLOW_ROBOT, Direction.EAST) &&
                    !(lastRobot == RobotPiece.YELLOW_ROBOT && lastDir == Direction.WEST)) {
                buildAndSearchTree(depth + 1, solutionThusFar + "Yellow-East ", new GameboardLW(gameState), RobotPiece.YELLOW_ROBOT, Direction.EAST);
            }
            if (gameState.robotCanMove(RobotPiece.YELLOW_ROBOT, Direction.SOUTH) &&
                    !(lastRobot == RobotPiece.YELLOW_ROBOT && lastDir == Direction.NORTH)) {
                buildAndSearchTree(depth + 1, solutionThusFar + "Yellow-South ", new GameboardLW(gameState), RobotPiece.YELLOW_ROBOT, Direction.SOUTH);
            }
            if (gameState.robotCanMove(RobotPiece.YELLOW_ROBOT, Direction.WEST) &&
                    !(lastRobot == RobotPiece.YELLOW_ROBOT && lastDir == Direction.EAST)) {
                buildAndSearchTree(depth + 1, solutionThusFar + "Yellow-West ", new GameboardLW(gameState), RobotPiece.YELLOW_ROBOT, Direction.WEST);
            }
            if (gameState.robotCanMove(RobotPiece.GREEN_ROBOT, Direction.NORTH) &&
                    !(lastRobot == RobotPiece.GREEN_ROBOT && lastDir == Direction.SOUTH)) {
                buildAndSearchTree(depth + 1, solutionThusFar + "Green-North ", new GameboardLW(gameState), RobotPiece.GREEN_ROBOT, Direction.NORTH);
            }
            if (gameState.robotCanMove(RobotPiece.GREEN_ROBOT, Direction.EAST) &&
                    !(lastRobot == RobotPiece.GREEN_ROBOT && lastDir == Direction.WEST)) {
                buildAndSearchTree(depth + 1, solutionThusFar + "Green-East ", new GameboardLW(gameState), RobotPiece.GREEN_ROBOT, Direction.EAST);
            }
            if (gameState.robotCanMove(RobotPiece.GREEN_ROBOT, Direction.SOUTH) &&
                    !(lastRobot == RobotPiece.GREEN_ROBOT && lastDir == Direction.NORTH)) {
                buildAndSearchTree(depth + 1, solutionThusFar + "Green-South ", new GameboardLW(gameState), RobotPiece.GREEN_ROBOT, Direction.SOUTH);
            }
            if (gameState.robotCanMove(RobotPiece.GREEN_ROBOT, Direction.WEST) &&
                    !(lastRobot == RobotPiece.GREEN_ROBOT && lastDir == Direction.EAST)) {
                buildAndSearchTree(depth + 1, solutionThusFar + "Green-West ", new GameboardLW(gameState), RobotPiece.GREEN_ROBOT, Direction.WEST);
            }
        }
    }
}
