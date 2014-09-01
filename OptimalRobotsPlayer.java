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
    
    private Gameboard initialBoard;
    private String name, solution = " ";
    private int bestNumberOfMoves = 4;
    private int statesExamined = 0;
    private boolean hasFoundSolution = false;
    
    public void initialize(String n, Gameboard b) {
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
        private Gameboard gameState;
        private TreeNode redNorth, redEast, redSouth, redWest;
        private TreeNode blueNorth, blueEast, blueSouth, blueWest;
        private TreeNode yellowNorth, yellowEast, yellowSouth, yellowWest;
        private TreeNode greenNorth, greenEast, greenSouth, greenWest;
        //private TreeNode silverNorth, silverEast, silverSouth, silverWest;
        private String solutionThusFar;
        
        TreeNode(int n, String s, Gameboard g, PieceColor pc, Direction d) {
            depth = n;
            solutionThusFar = s;
            gameState = new Gameboard(g);
            if (pc != null && d != null)
                gameState.moveRobot(pc, d);
        }
        
        public void buildAndSearchTree() {
            System.out.println(statesExamined++);
            if (depth == bestNumberOfMoves) {
                return;
            }
            else if (gameState.boardIsSolved()) {
                hasFoundSolution = true;
                bestNumberOfMoves = depth;
                solution = solutionThusFar;
                return;
            }
            else {
                if (gameState.robotCanMove(PieceColor.RED, Direction.NORTH)) {
                    redNorth = new TreeNode(depth + 1, solutionThusFar + "Red-North ", gameState, PieceColor.RED, Direction.NORTH);
                    redNorth.buildAndSearchTree();
                }
                if (gameState.robotCanMove(PieceColor.RED, Direction.EAST)) {
                    redEast = new TreeNode(depth + 1, solutionThusFar + "Red-East ", gameState, PieceColor.RED, Direction.EAST);
                    redEast.buildAndSearchTree();
                }
                if (gameState.robotCanMove(PieceColor.RED, Direction.SOUTH)) {
                    redSouth = new TreeNode(depth + 1, solutionThusFar + "Red-South ", gameState, PieceColor.RED, Direction.SOUTH);
                    redSouth.buildAndSearchTree();
                }
                if (gameState.robotCanMove(PieceColor.RED, Direction.WEST)) {
                    redWest = new TreeNode(depth + 1, solutionThusFar + "Red-West ", gameState, PieceColor.RED, Direction.WEST);
                    redWest.buildAndSearchTree();
                }
                if (gameState.robotCanMove(PieceColor.BLUE, Direction.NORTH)) {
                    blueNorth = new TreeNode(depth + 1, solutionThusFar + "Blue-North ", gameState, PieceColor.BLUE, Direction.NORTH);
                    blueNorth.buildAndSearchTree();
                }
                if (gameState.robotCanMove(PieceColor.BLUE, Direction.EAST)) {
                    blueEast = new TreeNode(depth + 1, solutionThusFar + "Blue-East ", gameState, PieceColor.BLUE, Direction.EAST);
                    blueEast.buildAndSearchTree();
                }
                if (gameState.robotCanMove(PieceColor.BLUE, Direction.SOUTH)) {
                    blueSouth = new TreeNode(depth + 1, solutionThusFar + "Blue-South ", gameState, PieceColor.BLUE, Direction.SOUTH);
                    blueSouth.buildAndSearchTree();
                }
                if (gameState.robotCanMove(PieceColor.BLUE, Direction.WEST)) {
                    blueWest = new TreeNode(depth + 1, solutionThusFar + "Blue-West ", gameState, PieceColor.BLUE, Direction.WEST);
                    blueWest.buildAndSearchTree();
                }
                if (gameState.robotCanMove(PieceColor.YELLOW, Direction.NORTH)) {
                    yellowNorth = new TreeNode(depth + 1, solutionThusFar + "Yellow-North ", gameState, PieceColor.YELLOW, Direction.NORTH);
                    yellowNorth.buildAndSearchTree();
                }
                if (gameState.robotCanMove(PieceColor.YELLOW, Direction.EAST)) {
                    yellowEast = new TreeNode(depth + 1, solutionThusFar + "Yellow-East ", gameState, PieceColor.YELLOW, Direction.EAST);
                    yellowEast.buildAndSearchTree();
                }
                if (gameState.robotCanMove(PieceColor.YELLOW, Direction.SOUTH)) {
                    yellowSouth = new TreeNode(depth + 1, solutionThusFar + "Yellow-South ", gameState, PieceColor.YELLOW, Direction.SOUTH);
                    yellowSouth.buildAndSearchTree();
                }
                if (gameState.robotCanMove(PieceColor.YELLOW, Direction.WEST)) {
                    yellowWest = new TreeNode(depth + 1, solutionThusFar + "Yellow-West ", gameState, PieceColor.YELLOW, Direction.WEST);
                    yellowWest.buildAndSearchTree();
                }
                if (gameState.robotCanMove(PieceColor.GREEN, Direction.NORTH)) {
                    greenNorth = new TreeNode(depth + 1, solutionThusFar + "Green-North ", gameState, PieceColor.GREEN, Direction.NORTH);
                    greenNorth.buildAndSearchTree();
                }
                if (gameState.robotCanMove(PieceColor.GREEN, Direction.EAST)) {
                    greenEast = new TreeNode(depth + 1, solutionThusFar + "Green-East ", gameState, PieceColor.GREEN, Direction.EAST);
                    greenEast.buildAndSearchTree();
                }
                if (gameState.robotCanMove(PieceColor.GREEN, Direction.SOUTH)) {
                    greenSouth = new TreeNode(depth + 1, solutionThusFar + "Green-South ", gameState, PieceColor.GREEN, Direction.SOUTH);
                    greenSouth.buildAndSearchTree();
                }
                if (gameState.robotCanMove(PieceColor.GREEN, Direction.WEST)) {
                    greenWest = new TreeNode(depth + 1, solutionThusFar + "Green-West ", gameState, PieceColor.GREEN, Direction.WEST);
                    greenWest.buildAndSearchTree();
                }
//                if (gameState.robotCanMove(PieceColor.SILVER, Direction.NORTH)) {
//                    silverNorth = new TreeNode(depth + 1, solutionThusFar + "Silver-North ", gameState, PieceColor.SILVER, Direction.NORTH);
//                    silverNorth.buildAndSearchTree();
//                }
//                if (gameState.robotCanMove(PieceColor.SILVER, Direction.EAST)) {
//                    silverEast = new TreeNode(depth + 1, solutionThusFar + "Silver-East ", gameState, PieceColor.SILVER, Direction.EAST);
//                    silverEast.buildAndSearchTree();
//                }
//                if (gameState.robotCanMove(PieceColor.SILVER, Direction.SOUTH)) {
//                    silverSouth = new TreeNode(depth + 1, solutionThusFar + "Silver-South ", gameState, PieceColor.SILVER, Direction.SOUTH);
//                    silverSouth.buildAndSearchTree();
//                }
//                if (gameState.robotCanMove(PieceColor.SILVER, Direction.WEST)) {
//                    silverWest = new TreeNode(depth + 1, solutionThusFar + "Silver-West ", gameState, PieceColor.SILVER, Direction.WEST);
//                    silverWest.buildAndSearchTree();
//                }
                
            }
        }
        
    }
    
}
