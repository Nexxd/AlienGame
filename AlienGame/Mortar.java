/**
 * This class represents the player. It contains the players health and coordinates.
 *
 * @author Jan Schneider 4919081 Group 8b
 */
public class Mortar extends Player {

    /**
     * This method sets the players coordinates.
     * @param x The x coordinate of the player.
     * @param y The y coordinate of the player.
     */
    public Player(int x, int y) {
        sethealth(3);
    }

    /**
     * This method calculates the distance between the alien and the player
     * and adjusts it for the mortar class.
     * @param alien This imports the x and y Coordinate of the aliens.
     * @return This returns the distance between the player and the alien.
     */
    public int distance(Alien alien) {
        int distance;
        distance = (Math.abs(getxCoordinate() - alien.getxCoordinate()) + Math.abs(getyCoordinate() - alien.getyCoordinate())) + 3;

        return distance;
    }
}
