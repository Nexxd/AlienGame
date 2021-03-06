/**
 * This abstract class represents the all the player tokens. It contains needed methods for the player.
 *
 * @author Jan Schneider 4919081 Group 8b
 */
public abstract class Player extends Tokens{

    /**
     * This method kills an alien if hit and replaces it on the map with a "x", while telling the player if he hit or not.
     * @param alien This imports the x and y Coordinate of the aliens.
     * @param gameBoard Char containing the gameboard.
     */
    public void attack(Alien alien, Map gameBoard) {
        if (distance(alien) < (int) (Math.random() * (gameBoard.getgameBoard().length + gameBoard.getgameBoard()[0].length)) + 1) {
            alien.sethealth(0);
            gameBoard.getgameBoard()[alien.getxCoordinate()][alien.getyCoordinate()] = 'X';
            System.out.println("The player has hit the alien at position (" + alien.getyCoordinate() + "," + alien.getxCoordinate() + ")");
        } else {
            System.out.println("The player missed the alien at position (" + alien.getyCoordinate() + "," + alien.getxCoordinate() + ")");
        }

    }

    /**
     * This method calculates the distance between the alien and the player.
     * @param alien This imports the x and y Coordinate of the aliens.
     * @return This returns the distance between the player and the alien.
     */
    public int distance(Alien alien) {
        int distance;
        distance = Math.abs(getxCoordinate() - alien.getxCoordinate()) + Math.abs(getyCoordinate() - alien.getyCoordinate());

        return distance;
    }
}
