/*
 * This project simulates a version of the game "Ricochet Robots" in order
 * to automate and test various human player strategies (that take into
 * consideration limited working memory) against the optimal benchmark 
 * algorithm than can only be executed by a computer.
 *
 * Contributors are Nate Stickney and Tim Walsh.
 */

package robots;

public enum RobotPiece {
    RED_ROBOT ("red"), 
    BLUE_ROBOT ("blue"), 
    GREEN_ROBOT ("green"), 
    YELLOW_ROBOT ("yellow"),
    NO_ROBOT ("blank");
    
    private final String color;
    
    RobotPiece (String color) {
        this.color = color;
    }
    
    public String color() {
        return color; 
    }
}
