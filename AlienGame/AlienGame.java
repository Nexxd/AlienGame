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
        int playerToken = PlayerToken();
        Map gameBoard = new Map(horizontal, vertical, numberAliens, playerToken);
        gameBoard.mapOutput();
        game(gameBoard, playerToken);
    }

    /**
     * This method informs the user of his health and if he won or lost the game.
     * This method prints the map each round.
     * @param gameBoard Char containing the gameboard.
     * @param playerToken Integer containing the choosen ingame class.
     */
    public static void game(Map gameBoard, int playerToken) {
        while (end(gameBoard) && endwon(gameBoard)) {
            int[] newCoordinate = inputscan(gameBoard, playerToken);
            pAttack(gameBoard, newCoordinate, playerToken);
            aAttack(gameBoard);
            gameBoard.mapOutput();
            System.out.println(" The player has " + gameBoard.getplayer().gethealth() + " hitpoints left");
        }
        if (!endwon(gameBoard) && end(gameBoard)) {
            System.out.print(" You've won the game, congratulations! ");
        } else if (endwon(gameBoard) && !end(gameBoard)) {
            System.out.print(" You've lost the game, Game over. ");
        }
    }

    /**
     * This method is the logic behind the player attack.
     * It checks if the player has choosen the Grenadier class and if so will execute multiple attacks.
     * @param gameBoard Char containing the gameboard.
     * @param coordinate int array that contains the attack coordinates.
     * @param playerToken Integer containing the choosen ingame class.
     */
    public static void pAttack(Map gameBoard, int[] coordinate, int playerToken) {
        for (Alien alien: gameBoard.getaliens()) {
            if (alien.getxCoordinate() == coordinate[0] && alien.getyCoordinate() == coordinate[1]) {
                gameBoard.getplayer().attack(alien, gameBoard);
            }
            if (playerToken == 2) {
                if (alien.getxCoordinate() == coordinate[0] && alien.getyCoordinate() == coordinate[1] + 1) {
                    gameBoard.getplayer().attack(alien, gameBoard);
                }
                if (alien.getxCoordinate() == coordinate[0] && alien.getyCoordinate() == coordinate[1] - 1) {
                    gameBoard.getplayer().attack(alien, gameBoard);
                }
                if (alien.getxCoordinate() == coordinate[0] + 1 && alien.getyCoordinate() == coordinate[1]) {
                    gameBoard.getplayer().attack(alien, gameBoard);
                }
                if (alien.getxCoordinate() == coordinate[0] - 1 && alien.getyCoordinate() == coordinate[1]) {
                    gameBoard.getplayer().attack(alien, gameBoard);
                }
            }
        }
    }

    /**
     * This method is the logic behind the alien attack.
     * @param gameBoard Char containing the gameboard.
     */
    public static void aAttack(Map gameBoard) {
        for (Alien alien: gameBoard.getaliens()) {
            if (alien.gethealth() == 1) {
                alien.attack(gameBoard.getplayer(), gameBoard);
            }
        }
    }

    /**
     * This method gives the user the ability to interact with the program through an input scanner.
     * The user can designate a target on the gameboard and attack it or enter the menu and continue or exit the game.
     * @return This returns the coordinate later used in the pAttack method.
     */
    public static int[] inputscan(Map gameBoard, int playerToken) {
        int[] coordinate = new int[2];
        boolean run = true;
        while (run == true) {
            if (playerToken == 0) {
                coordinate = gruntUI(gameBoard);
                return coordinate;
            } else if (playerToken == 1) {
                coordinate = scoutUI();
                return coordinate;
            } else {
                coordinate = grenadierUI();
                return coordinate;
            }
        }
        return coordinate;
    }

    public static int[] gruntUI(Map gameBoard) {
        Scanner inputscanner = new Scanner(System.in);
        int[] coordinate = new int[2];
        boolean run = true;
        while (run == true) {
            System.out.println(" Do you wish to \"attack\", \"heal\" or enter the \"menu\"?");
            switch (inputscanner.next()) {
                case "attack":
                    coordinate = inputattack();
                    return coordinate;
                case "heal":
                    gameBoard.getplayer().sethealth(gameBoard.getplayer().gethealth() + 3);
                    System.out.println("You've healed 3 health.");
                    try {
                        coordinate[0] = gameBoard.getplayer().getxCoordinate();
                        coordinate[1] = gameBoard.getplayer().getyCoordinate();
                    } catch (java.util.InputMismatchException e) {
                        System.out.println("The aliens attack you.");
                    }
                    return coordinate;
                case "menu":
                    System.out.println("If you want to quit the game type \"exit\" or");
                    System.out.println(" if you want to continue type \"continue.\"");
                    switch (inputscanner.next()) {
                        case "continue":
                            coordinate = inputattack();
                            return coordinate;
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
        return coordinate;
    }

    public static int[] scoutUI() {
        Scanner inputscanner = new Scanner(System.in);
        int[] coordinate = new int[2];
        boolean run = true;
        while (run == true) {
            System.out.println(" Do you wish to \"attack\", \"evade\" or enter the \"menu\"?");
            switch (inputscanner.next()) {
                case "attack":
                    coordinate = inputattack();
                    return coordinate;
                //case "evade":

                case "menu":
                    System.out.println("If you want to quit the game type \"exit\" or");
                    System.out.println(" if you want to continue type \"continue.\"");
                    switch (inputscanner.next()) {
                        case "continue":
                            coordinate = inputattack();
                            return coordinate;
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
        return coordinate;
    }

    public static int[] grenadierUI() {
        Scanner inputscanner = new Scanner(System.in);
        int[] coordinate = new int[2];
        boolean run = true;
        while (run == true) {
            System.out.println(" Do you wish to \"attack\" or enter the \"menu\"?");
            switch (inputscanner.next()) {
                case "attack":
                    coordinate = inputattack();
                    return coordinate;
                case "menu":
                    System.out.println("If you want to quit the game type \"exit\" or");
                    System.out.println(" if you want to continue type \"continue.\"");
                    switch (inputscanner.next()) {
                        case "continue":
                            coordinate = inputattack();
                            return coordinate;
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
        return coordinate;
    }

    /**
     * This method gives the user the ability to designate a target on the gameboard.
     *
     * @return This returns the newCoordinate later used inputscan method.
     */
    public static int[] inputattack() {
        int[] newCoordinate = new int[2];
        Scanner scanner = new Scanner(System.in);
        int attack = 1;
        int s;
        System.out.println("Which coordinates would you like to attack? Type in \"x y\": ");
        while (attack >= 0) {
            try {
                s = scanner.nextInt();
            } catch (java.util.InputMismatchException e) {
                System.out.println("This is not a valid coordinate. The aliens attack you.");
                break;
            }
            newCoordinate[attack] = s;
            System.out.println(s);
            attack--;
        }
        //scanner.close();
        return newCoordinate;
    }

    /**
     * This method checks if the player is still alive.
     * @param gameBoard Char containing the gameboard.
     */
    public static Boolean end(Map gameBoard) {
        if (gameBoard.getplayer().gethealth() < 1) {
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
        for (Alien alien: gameBoard.getaliens()) {
            if (alien.gethealth() == 1) {
                checkAlien = true;
            }
        }
        return checkAlien;
    }

    /**
     * This method gives the user the ability to choose between three ingame classes or enter the help section.
     * @return This returns the token variable used to determine which class the user chose.
     */
    public static int PlayerToken() {
        Scanner inputscanner = new Scanner(System.in);
        boolean run = true;
        int token = 0;
        while (run == true) {
            System.out.println(" Which class do you want to choose?");
            System.out.println(" Type in \"Grunt\", \"Scout\", \"Grenadier\" or \"Random\"");
            System.out.println(" Type in \"Help\" to get more information on the different classes.");
            switch (inputscanner.next()) {
                case "Help":
                    System.out.println(" The Grunt starts the game with 8 health and the normal chance to hit.");
                    System.out.println(" The Scout starts the game with 5 health and a better chance to hit.");
                    System.out.println(" The Grenadier starts the game with 3 health and a slightly better chance to hit.");
                    System.out.println(" The Grenadier will also hit adjacent coordinates on a succesfull attack.");
                    continue;
                case "Grunt":
                    System.out.println("You've choosen the \"Grunt\"");
                    token = 0;
                    return token;
                case "Scout":
                    System.out.println("You've choose the \"Scout\"");
                    token = 1;
                    return token;
                case "Grenadier":
                    System.out.println("You've choosen the \"Grenadier\"");
                    token = 2;
                    return token;
                case "Random":
                    System.out.println("You've choosen the \"Random\" class.");
                    token = (int)(Math.random() * 2);
                    if (token == 0) {
                        System.out.println("You're the \"Grunt\"");
                    } else if (token == 1) {
                        System.out.println("You're the \"Scout\"");
                    } else if (token == 2) {
                        System.out.println("You're the \"Grenadier\"");
                    }
                    return token;
                default:
                    System.out.println("This command is unknown, please enter a valid command.");
                    continue;
            }
        }
    inputscanner.close();
    return token;
    }
}
