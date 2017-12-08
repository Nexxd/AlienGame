import java.util.Scanner;
/**
 * The AlienGame expanded with more functionality and classes.
 * This is the main class of the AlienGame. It contains the main method and
 * the needed game logic.
 *
 * @author Jan Schneider 4919081 Group 8b
 * @author Jan Ammermann 4846992 Group 8b
 */
public class AlienGame {

    /**
     * This method is the main method of the AlienGame and checks all the parameters for errors and starts the game.
     * @param args This parameter contains the horizontal, vertical and numberAliens values.
     */
    public static void main(String[] args) {

        /**
         * This checks if the choosen parameters are in the right range and format
         * and if not displays a corresponding massage and shuts the program down.
         */
        try {
            Integer.parseInt(args[0]);
            Integer.parseInt(args[1]);
            Integer.parseInt(args[2]);
        } catch (NumberFormatException e) {
            System.out.println("Please try again by only using numbers as the starting parameters.");
            System.exit(0);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Please try again by not entering more or less than three parameters.");
            System.exit(0);
        }

        int vertical = Integer.parseInt(args[0]);
        int horizontal = Integer.parseInt(args[1]);
        int numberAliens = Integer.parseInt(args[2]);

        /**
         * This checks if the game board is smaller then 1 space
         * and if so displays a corresponding massage and shuts the program down.
         */
        if (horizontal * vertical < 1) {
            System.out.println("The game board can't be smaller than 1 space.");
            System.exit(0);
        }

        /**
         * This checks if the number of aliens exceeds x * y
         * and if so displays a corresponding massage and shuts the program down.
         */
        if (numberAliens >= (horizontal) * (vertical)) {
            System.out.println("To many Aliens were choosen. Aliens should not exceed horizontal * vertical.");
            System.exit(0);
        }
        Map gameBoard = new Map(horizontal, vertical, numberAliens);
        gameBoard.mapOutput();
        game(gameBoard);
    }

    /**
     * This method informs the user of his health and if he won or lost the game.
     * This method prints the map each round.
     * @param gameBoard Char containing the gameboard.
     */
    public static void game(Map gameBoard) {
        while (end(gameBoard) && endwon(gameBoard)) {
            int[] newCoordinate = inputscan();
            pAttack(gameBoard, newCoordinate);
            aAttack(gameBoard);
            gameBoard.mapOutput();
            System.out.println(" The player has " + gameBoard.player.gethealth() + " hitpoints left");
        }
        if (!endwon(gameBoard) && end(gameBoard)) {
            System.out.print(" You've won the game, congratulations! ");
        } else if (endwon(gameBoard) && !end(gameBoard)) {
            System.out.print(" You've lost the game, Game over. ");
        }
    }

    /**
     * This method is the logic behind the player attack.
     * @param gameBoard Char containing the gameboard.
     * @param coordinate int array that contains the attack coordinates.
     */
    public static void pAttack(Map gameBoard, int[] coordinate) {
        //int missed = 0;
        //boolean allMissed = false;
        for (Alien alien: gameBoard.aliens) {
            if (alien.getxCoordinate() == coordinate[0] && alien.getyCoordinate() == coordinate[1]) {
                gameBoard.player.attack(alien, gameBoard);
            }
        }
    }

    /**
     * This method is the logic behind the alien attack.
     * @param gameBoard Char containing the gameboard.
     */
    public static void aAttack(Map gameBoard) {
        for (Alien alien: gameBoard.aliens) {
            if (alien.gethealth() == 1) {
                alien.attack(gameBoard.player, gameBoard);
            }
        }
    }

    /**
     * This method gives the user the ability to interact with the program through an input scanner.
     * The user can designate a target on the gameboard and attack it or enter the menu and continue or exit the game.
     * @return This returns the newCoordinate later used in the pAttack method.
     */
    public static int[] inputscan() {
        int[] coordinate = new int[2];
        Scanner inputscanner = new Scanner(System.in);
        boolean run = true;
        while (run == true) {
            System.out.println(" Do you wish to \"attack\" or enter the \"menu\"?");
            switch (inputscanner.next()) {
                case "attack":
                    coordinate = inputattack();
                    return coordinate;
                /**
                    while (true) {
                        coordinate = inputattack();
                        if (playerHit() == true) {
                            return coordinate;
                        } else {
                            continue;
                        }
                    }
                */
                case "menu":
                    System.out.println("If you want to quit the game type \"exit\" or");
                    System.out.println(" if you want to continue type \"continue.\"");
                    switch (inputscanner.next()) {
                        case "continue":
                            coordinate = inputattack();
                            return coordinate;
                        /**
                            coordinate = inputattack();
                            if (playerHit() == true) {
                                return coordinate;
                            } else {
                                coordinate = inputattack();
                                return coordinate;
                            }
                        */
                        case "exit":
                            System.out.println("Goodbye");
                            System.exit(0);
                        default:
                            System.out.println("This command is unknown, please enter a valid command.");
                            continue;
                    }
                default:
                    System.out.println("This command is unknown, please enter a valid command.");
                    continue;
            }
        }
        inputscanner.close();
        return coordinate;
    }


    public static int[] inputattack() {
        int[] newCoordinate = new int[2];
        Scanner scanner = new Scanner(System.in);
        int attack = 1;
        System.out.print("Which coordinates would you like to attack? Type in \"x y\": ");
        while (attack >= 0) {
            int s = scanner.nextInt();
            newCoordinate[attack] = s;
            System.out.println(s);
            attack--;
        }
        //scanner.close();
        return newCoordinate;
    }

    /**
    public static boolean playerHit() {
        int[] coordinate = new int[2];
        coordinate = ;
        int[] playerCoordinate = new int[2];
        playerCoordinate[0] = player.xCoordinate;
        playerCoordinate[1] = player.yCoordinate;
        if (coordinate == playerCoordinate) {
            System.out.println("You can't attack yourself. Please try again.");
            return false;
        } else if (alienHit() == true) {
            return true;
        } else {
            System.out.println("You need to enter the coordinates of an alien. Please try again.");
        }
        return true;
    }
    */

    /**
    public static boolean alienHit() {
        int[] coordinate = new int[2];
        coordinate = ;
        boolean hit = false;
        for (Alien alien: gameBoard.aliens) {
            if (alien.xCoordinate == coordinate[0] && alien.yCoordinate == coordinate[1]) {
                hit = true;
                return true;
            } else {
                continue;
            }
        }
    }
    */

    /**
     * This method checks if the player is still alive.
     * @param gameBoard Char containing the gameboard.
     */
    public static Boolean end(Map gameBoard) {
        if (gameBoard.player.gethealth() < 1) {
            return false;
        }
        return true;
    }

    /**
     * This method checks if the aliens are still alive.
     * @param gameBoard Char containing the gameboard.
     */
    public static Boolean endwon(Map gameBoard) {
        Boolean checkAlien = false;
        for (Alien alien: gameBoard.aliens) {
            if (alien.gethealth() == 1) {
                checkAlien = true;
            }
        }
        return checkAlien;
    }
}
