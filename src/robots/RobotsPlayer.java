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
 * to provide for multi-threading. All public methods should be synchronized
 * because they shouldn't be executed if reinitialize() is in progress
 */
public interface RobotsPlayer extends Runnable{
    
    // method to accept another gameboard from Main class after
    // getting an initial gameboard passed through the constructor...
    // this should be synchronized
    void reinitialize(GameboardLW b);
    
    // return the name of the player
    String getName();
    
    // Runnable requires this to be implemented; will contain player logic,
    // execute in its own thread, and automatically print the solution to the 
    // console when complete...
    // this should be synchronized
    // void run();
    
    // method returns true if the player has found a solution...
    // this should be synchronized
    boolean hasFoundSolution();
    
    // method returns string encoding the solution; sequence of moves...
    // example: blue-north blue-east red-north...
    // this should be synchronized
    String getSolution();
    
}
