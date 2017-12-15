
/**
 * This class represents the aliens on the map. It contains their health and their coordinates.
 *
 * @author Jan Schneider 4919081 Group 8b
 */
public class Alien extends Tokens {

    /**
     * This method sets the aliens coordinates.
     * @param x A parameter for the x coordinate.
     * @param y A parameter for the y coordinate.
     */
    public Alien(int x, int y) {
        setxCoordinate(x);
        setyCoordinate(y);
        sethealth(1);
    }

    /**
     * This method rolls the hit chance for an alien to hit the player and if it hits removes one health from him.
     * It shows if the alien hit the player or missed.
     * @param player player This is used to get the player coordinates.
     * @param gameBoard Char containing the gameboard.
     */
    public void attack(Player player, Map gameBoard) {
        if (distance(player) < (int) (Math.random() * (gameBoard.getgameBoard().length + gameBoard.getgameBoard()[0].length)) + 1) {
            player.sethealth(player.gethealth() - 1);
            System.out.println("The alien (" + getyCoordinate() + "," + getxCoordinate() + ") hit the player.");
        } else {
            System.out.println("The alien (" + getyCoordinate() + "," + getxCoordinate() + ") missed the player.");
        }

    }

    /**
     * This method calculates the distance between the player and the alien.
     * @param player This is used to get the player coordinates.
     * @return Returns the distance between the player and an alien.
     */
    public int distance(Player player) {
        int distance;
        distance = Math.abs(getxCoordinate() - player.getxCoordinate()) + Math.abs(getyCoordinate() - player.getyCoordinate());

        return distance;
    }

}
