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
 * This player will perform exhaustive breadth-first search to find the optimal
 * solution for a given game board. This strategy is not feasible for us
 * mortals, but its result will serve as a benchmark. It will likely take 
 * longer to execute than human-capable strategies.
 */
public class OptimalRobotsPlayer implements RobotsPlayer{
    
    private GameboardLW initialBoard;
    private String name, solution = " ";
    private int bestNumberOfMoves = 5; // this initial number is the maximum depth to search
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
        TreeNode root = new TreeNode(0, solution, initialBoard, null, null);
        root.buildAndSearchTree();
        
        // print solution to the console when done
        System.out.println(getSolution());
    } 
    
    public boolean hasFoundSolution() {
        return hasFoundSolution;
    }
    
    public String getSolution() {
        if (this.hasFoundSolution())
            return this.getName() + ":" + solution;
        else
            return this.getName() + " has no solution.";
    }
    
    private class TreeNode {
        
        private int depth;
        private GameboardLW gameState;
        private TreeNode redNorth, redEast, redSouth, redWest;
        private TreeNode blueNorth, blueEast, blueSouth, blueWest;
        private TreeNode yellowNorth, yellowEast, yellowSouth, yellowWest;
        private TreeNode greenNorth, greenEast, greenSouth, greenWest;
        private String solutionThusFar;
        private RobotPiece lastRobot;
        private Direction lastDir;
        
        TreeNode(int n, String s, GameboardLW g, RobotPiece pc, Direction d) {
            depth = n;
            solutionThusFar = s;
            gameState = new GameboardLW(g);
            if (pc != null && d != null)
                gameState.moveRobot(pc, d);
            lastRobot = pc;
            lastDir = d;
        }
        
        public void buildAndSearchTree() {
            System.out.println(statesExamined++);
            if (depth > bestNumberOfMoves) {
                return;
            }
            else if (gameState.boardIsSolved()) {
                hasFoundSolution = true;
                bestNumberOfMoves = depth;
                solution = solutionThusFar;
                return;
            }
            else {
                if (gameState.robotCanMove(RobotPiece.RED_ROBOT, Direction.NORTH) &&
                        (lastRobot != RobotPiece.RED_ROBOT && lastDir != Direction.SOUTH)) {
                    redNorth = new TreeNode(depth + 1, solutionThusFar + "Red-North ", gameState, RobotPiece.RED_ROBOT, Direction.NORTH);
                    redNorth.buildAndSearchTree();
                }
                if (gameState.robotCanMove(RobotPiece.RED_ROBOT, Direction.EAST) &&
                        (lastRobot != RobotPiece.RED_ROBOT && lastDir != Direction.WEST)) {
                    redEast = new TreeNode(depth + 1, solutionThusFar + "Red-East ", gameState, RobotPiece.RED_ROBOT, Direction.EAST);
                    redEast.buildAndSearchTree();
                }
                if (gameState.robotCanMove(RobotPiece.RED_ROBOT, Direction.SOUTH) &&
                        (lastRobot != RobotPiece.RED_ROBOT && lastDir != Direction.NORTH)) {
                    redSouth = new TreeNode(depth + 1, solutionThusFar + "Red-South ", gameState, RobotPiece.RED_ROBOT, Direction.SOUTH);
                    redSouth.buildAndSearchTree();
                }
                if (gameState.robotCanMove(RobotPiece.RED_ROBOT, Direction.WEST) &&
                        (lastRobot != RobotPiece.RED_ROBOT && lastDir != Direction.EAST)) {
                    redWest = new TreeNode(depth + 1, solutionThusFar + "Red-West ", gameState, RobotPiece.RED_ROBOT, Direction.WEST);
                    redWest.buildAndSearchTree();
                }
                if (gameState.robotCanMove(RobotPiece.BLUE_ROBOT, Direction.NORTH) &&
                        (lastRobot != RobotPiece.BLUE_ROBOT && lastDir != Direction.SOUTH)) {
                    blueNorth = new TreeNode(depth + 1, solutionThusFar + "Blue-North ", gameState, RobotPiece.BLUE_ROBOT, Direction.NORTH);
                    blueNorth.buildAndSearchTree();
                }
                if (gameState.robotCanMove(RobotPiece.BLUE_ROBOT, Direction.EAST) &&
                        (lastRobot != RobotPiece.BLUE_ROBOT && lastDir != Direction.WEST)) {
                    blueEast = new TreeNode(depth + 1, solutionThusFar + "Blue-East ", gameState, RobotPiece.BLUE_ROBOT, Direction.EAST);
                    blueEast.buildAndSearchTree();
                }
                if (gameState.robotCanMove(RobotPiece.BLUE_ROBOT, Direction.SOUTH) &&
                        (lastRobot != RobotPiece.BLUE_ROBOT && lastDir != Direction.NORTH)) {
                    blueSouth = new TreeNode(depth + 1, solutionThusFar + "Blue-South ", gameState, RobotPiece.BLUE_ROBOT, Direction.SOUTH);
                    blueSouth.buildAndSearchTree();
                }
                if (gameState.robotCanMove(RobotPiece.BLUE_ROBOT, Direction.WEST) &&
                        (lastRobot != RobotPiece.BLUE_ROBOT && lastDir != Direction.EAST)) {
                    blueWest = new TreeNode(depth + 1, solutionThusFar + "Blue-West ", gameState, RobotPiece.BLUE_ROBOT, Direction.WEST);
                    blueWest.buildAndSearchTree();
                }
                if (gameState.robotCanMove(RobotPiece.YELLOW_ROBOT, Direction.NORTH) &&
                        (lastRobot != RobotPiece.YELLOW_ROBOT && lastDir != Direction.SOUTH)) {
                    yellowNorth = new TreeNode(depth + 1, solutionThusFar + "Yellow-North ", gameState, RobotPiece.YELLOW_ROBOT, Direction.NORTH);
                    yellowNorth.buildAndSearchTree();
                }
                if (gameState.robotCanMove(RobotPiece.YELLOW_ROBOT, Direction.EAST) &&
                        (lastRobot != RobotPiece.YELLOW_ROBOT && lastDir != Direction.WEST)) {
                    yellowEast = new TreeNode(depth + 1, solutionThusFar + "Yellow-East ", gameState, RobotPiece.YELLOW_ROBOT, Direction.EAST);
                    yellowEast.buildAndSearchTree();
                }
                if (gameState.robotCanMove(RobotPiece.YELLOW_ROBOT, Direction.SOUTH) &&
                        (lastRobot != RobotPiece.YELLOW_ROBOT && lastDir != Direction.NORTH)) {
                    yellowSouth = new TreeNode(depth + 1, solutionThusFar + "Yellow-South ", gameState, RobotPiece.YELLOW_ROBOT, Direction.SOUTH);
                    yellowSouth.buildAndSearchTree();
                }
                if (gameState.robotCanMove(RobotPiece.YELLOW_ROBOT, Direction.WEST) &&
                        (lastRobot != RobotPiece.YELLOW_ROBOT && lastDir != Direction.EAST)) {
                    yellowWest = new TreeNode(depth + 1, solutionThusFar + "Yellow-West ", gameState, RobotPiece.YELLOW_ROBOT, Direction.WEST);
                    yellowWest.buildAndSearchTree();
                }
                if (gameState.robotCanMove(RobotPiece.GREEN_ROBOT, Direction.NORTH) &&
                        (lastRobot != RobotPiece.GREEN_ROBOT && lastDir != Direction.SOUTH)) {
                    greenNorth = new TreeNode(depth + 1, solutionThusFar + "Green-North ", gameState, RobotPiece.GREEN_ROBOT, Direction.NORTH);
                    greenNorth.buildAndSearchTree();
                }
                if (gameState.robotCanMove(RobotPiece.GREEN_ROBOT, Direction.EAST) &&
                        (lastRobot != RobotPiece.GREEN_ROBOT && lastDir != Direction.WEST)) {
                    greenEast = new TreeNode(depth + 1, solutionThusFar + "Green-East ", gameState, RobotPiece.GREEN_ROBOT, Direction.EAST);
                    greenEast.buildAndSearchTree();
                }
                if (gameState.robotCanMove(RobotPiece.GREEN_ROBOT, Direction.SOUTH) &&
                        (lastRobot != RobotPiece.GREEN_ROBOT && lastDir != Direction.NORTH)) {
                    greenSouth = new TreeNode(depth + 1, solutionThusFar + "Green-South ", gameState, RobotPiece.GREEN_ROBOT, Direction.SOUTH);
                    greenSouth.buildAndSearchTree();
                }
                if (gameState.robotCanMove(RobotPiece.GREEN_ROBOT, Direction.WEST) &&
                        (lastRobot != RobotPiece.GREEN_ROBOT && lastDir != Direction.EAST)) {
                    greenWest = new TreeNode(depth + 1, solutionThusFar + "Green-West ", gameState, RobotPiece.GREEN_ROBOT, Direction.WEST);
                    greenWest.buildAndSearchTree();
                }
                
            }
        }
        
    }
    
}
