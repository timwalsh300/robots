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
        
        Thread p1 = new Thread(new OptimalRobotsPlayer("OptimalPlayer", new GameboardLW()));
        //Thread p2 = new Thread(new NextRobotsPlayer("NextPlayer", new GameboardLW()));
        
        p1.start();
        //p2.start();
        
        try {
            p1.join();
            //p2.join();
        }
        catch (InterruptedException e) {
            System.out.println("InterruptedException: " + e.getMessage());
        }
    }
    
}
