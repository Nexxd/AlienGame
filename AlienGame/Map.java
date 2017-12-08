/**
 * This class represents the gameboard and and refreshes it after each round.
 *
 * @author Jan Schneider 4919081 Group 8b
 */
public class Map {
    char[][] gameBoard;
    Player player;
    Alien[] aliens;

    /**
     * This methods initializes the gameboard used in this program.
     * @param horizontal the width as integer.
     * @param vertical the height as integer.
     * @param numberAliens the number of aliens as integer.
     */
    public Map(int horizontal, int vertical, int numberAliens) {
        this.aliens = new Alien[numberAliens];
        this.gameBoard = new char[horizontal][vertical];
        this.gameBoard = fillspace(gameBoard);
        this.gameBoard = fillunits(numberAliens, horizontal, vertical);
    }

   /**
    * This method fills the gameboard with the aliens and the player.
    * @param horizontal the width as integer.
    * @param vertical the height as integer.
    * @param numberAliens the number of aliens as integer.
    * @return Char[][] containing the aliens and the player.
    */
    char[][] fillunits(int numberAliens, int horizontal, int vertical) {
        for (int n = 0; n < numberAliens + 1; n++) {
            int x = (int) (Math.random() * horizontal);
            int y = (int) (Math.random() * vertical);
            while (gameBoard[x][y] == 'P' || gameBoard[x][y] == 'A') {
                x = (int) (Math.random() * horizontal);
                y = (int) (Math.random() * vertical);
            }
            if (n == 0) {
                gameBoard[x][y] = 'P';
                player = new Player(x, y);
            } else {
                gameBoard[x][y] = 'A';
                Alien alien = new Alien(x, y);
                aliens[n - 1] = alien;
            }
        }
        return gameBoard;
    }

    /**
     * This method prints the gameboard.
     */
    public void mapOutput() {
        System.out.println("Map:");
        for (int n = 0; n < gameBoard.length; n++) {
            if (n == 0) {
                System.out.print("  ");
                for (int m = 0; m < gameBoard[0].length; m++) {
                    System.out.print(m);
                }
                System.out.println();
                System.out.println(" O" + repeat(gameBoard[0].length, "=") + "O");
            }
            System.out.print(n + "|");
            for (int l = 0; l < gameBoard[n].length; l++) {
                System.out.print(gameBoard[n][l]);
            }
            System.out.println("|");
        }
        System.out.println(" O" + repeat(gameBoard[0].length, "=") + "O");
    }

    /**
     * This method can be used to replace \0 on the gameboard.
     * @param count Parameter used to be replaced by a length.
     * @param with Paramter used to later describe something that should replace something.
     * @return Returns a string used to replace null with something
     */
    public static String repeat(int count, String with) {
        return new String(new char[count]).replace("\0", with);
    }

    /**
     * This method is used to initialize the open spaces on the gameboard.
     * @param gameBoard Char containing the gameboard.
     * @return Char[][] containing empty spaces.
     */
    public static char[][] fillspace(char[][] gameBoard) {
        for (int n = 0; n < gameBoard.length; n++) {
            for (int m = 0; m < gameBoard[n].length; m++) {
                gameBoard[n][m] = ' ';
            }
        }
        return gameBoard;
    }
}
