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
    private String solution;
    private boolean hasFoundSolution = false;
    
    public void initialize(Gameboard b) {
        initialBoard = b;
    }
    
    //must override the run() method for actions to perform in its own thread
    @Override
    public void run() {
        
        // insert player logic here
        
        // print solution to the console when done
        System.out.println(getProposedSolution());
    }
    
    public boolean hasFoundSolution() {
        return hasFoundSolution;
    }
    
    public String getProposedSolution() {
        if (hasFoundSolution) {
            return "OptimalPlayer: " + solution;
        }
        else
            return "OptimalPlayer: " + "No solution found";
    }
    
}
