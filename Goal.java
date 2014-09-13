/*
 * This project simulates a version of the game "Ricochet Robots" in order
 * to automate and test various human player strategies (that take into
 * consideration limited working memory) against the optimal benchmark 
 * algorithm than can only be executed by a computer.
 *
 * Contributors are Nate Stickney and Tim Walsh.
 */

package robots;

public enum Goal {
    RED_STAR ("red", "star"),
    RED_MOON ("red", "moon"),
    RED_GEAR ("red", "gear"),
    RED_PLANET ("red", "planet"),
    BLUE_STAR ("blue", "star"),
    BLUE_MOON ("blue", "moon"),
    BLUE_GEAR ("blue", "gear"),
    BLUE_PLANET ("blue", "planet"),
    GREEN_STAR ("green", "star"),
    GREEN_MOON ("green", "moon"),
    GREEN_GEAR ("green", "gear"),
    GREEN_PLANET ("green", "planet"),
    YELLOW_STAR ("yellow", "star"),
    YELLOW_MOON ("yellow", "moon"),
    YELLOW_GEAR ("yellow", "gear"),
    YELLOW_PLANET ("yellow", "planet"),
    SILVER_GOAL ("silver", "swirl"),
    NO_GOAL ("blank", "blank");
    
    private final String color, type;
    
    Goal (String c, String t) {
        this.color = c;
        this.type = t;
    }
    
    public String color() {
        return color; 
    }
    
    public String type() {
        return type;
    }
}
