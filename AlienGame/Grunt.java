/**
 * This class represents the grunt class. It contains the players health and coordinates.
 *
 * @author Jan Schneider 4919081 Group 8b
 */
public class Grunt extends Player {

    /**
     * This method sets the players coordinates.
     * @param x The x coordinate of the player.
     * @param y The y coordinate of the player.
     */
    public Grunt(int x, int y) {
        setxCoordinate(x);
        setyCoordinate(y);
        sethealth(8);
    }
}
