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
 * This is main class that will construct the game board, instantiate the
 * players, and run them.
 * 
 */
public class Robots {

    public static void main(String[] args) {
        
        OptimalRobotsPlayer player1 = new OptimalRobotsPlayer();
        player1.initialize("player1", new Gameboard());
        Thread thread1 = new Thread(player1);
        
        // AnotherPlayer player2 = new AnotherPlayer();
        // player2.initialize("player2", game);
        // Thread thread2 = new Thread(player2);
        
        thread1.start();
        // thread2.start();
        // etc.
    }
    
}
