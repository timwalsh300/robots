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

    public enum PieceColor {
        RED, BLUE, YELLOW, GREEN, SILVER;
    }
    public enum GoalType {
        STAR, MOON, PLANET, GEAR, SILVER;
    }
    
    public static void main(String[] args) {
        Gameboard game = new Gameboard();
        System.out.println("add breakpoint here to debug");
        
        OptimalRobotsPlayer player1 = new OptimalRobotsPlayer();
        player1.initialize(game);
        Thread thread1 = new Thread(player1);
        
        // AnotherPlayer player2 = new AnotherPlayer();
        // player2.initialize(game);
        // Thread thread2 = new Thread(player2);
        
        thread1.start();
        // thread2.start();
        // etc.
        
    }
    
}
