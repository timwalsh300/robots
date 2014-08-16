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
 * This interface will be implemented by all game players. It extends Runnable
 * to provide for multi-threading.
 */
public interface RobotsPlayer extends Runnable{
    
    // method to accept the initial gameboard from Main class
    public void initialize(Gameboard b);
    
    // Runnable requires this to be implemented; will contain player logic
    //public void run();
    
    // method returns true if the player has found a solution
    public boolean hasFoundSolution();
    
    // method returns string encoding the solution; sequence of moves...
    // example: blue-north blue-east red-north...
    public String getProposedSolution();
    
}
