import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class Game {

    private Player player1;
    private Player player2;
    ArrayList<Object> positions = new ArrayList<>();
    Scanner scanner = new Scanner(System.in);
    int numberOfGamesPlayed = 0;

    public Game() {
        for (int i = 1; i <= 9; i++) {
            positions.add(i);
        }
    }


    public void playGame() {
        System.out.println("----------------+ TIC TAC TOE +----------------");
        System.out.println("Do you wish to play against another human or against the computer? Enter 1 for another human, and enter 2 for the computer.");

        boolean chooseGameAlternative = false;
        while (!chooseGameAlternative) {
            String chosenAlternative = scanner.nextLine();

            if (chosenAlternative.trim().equals("1")) {
                setUp();
                chooseGameAlternative = true;
            } else if (chosenAlternative.trim().equals("2")) {
                setUpComputer();
                chooseGameAlternative = true;
            } else {
                System.out.println("You have not chosen a valid option. Please enter 1 for playing against a human or 2 for playing against the computer.");
            }
        }


        boolean isRunning = true;
        while(isRunning) {

            placeMakerLoop();

            numberOfGamesPlayed ++;
            System.out.println("Current standing:");
            System.out.println("Number of games played: " + numberOfGamesPlayed);
            System.out.println(player1.getName() + " wins: " + player1.wins);
            System.out.println(player2.getName() + " wins: " + player2.wins);
            System.out.println("Number of draws: " + (numberOfGamesPlayed-player1.wins-player2.wins));

            System.out.println("Do you wish to play another round? (y/n)");

            boolean validInput = false;
            while(!validInput) {
                String newGame = scanner.nextLine();
                    if (newGame.trim().equalsIgnoreCase("y")) {
                        for (int i = 1; i >= 1 && i <= 9; i++) {
                            positions.set(i - 1, i);
                        }
                        validInput = true;
                    } else if (newGame.trim().equalsIgnoreCase("n")) {
                        System.out.println("Thank you for playing, have a great day!");
                        isRunning = false;
                        validInput = true;
                    } else {
                        System.out.println();
                        System.out.println("You have chosen neither y nor n. Please try again.");
                    }
            }
        }
    }





//-----------------------------------METHOD: SET UP HUMAN VS HUMAN------------------------------------------------------
    public void setUp() {
        System.out.println("You are to play the game Tic Tac Toe.");
        System.out.println("This is what the playing board looks like:\n");
        presentBoard();

        System.out.println("Player 1, please type your name:");
        String namePlayer1 = scanner.nextLine();
        System.out.println("Player 2, please type your name:");
        String namePlayer2 = scanner.nextLine();

        this.player1 = new Player(namePlayer1);
        this.player2 = new Player(namePlayer2);

        System.out.println("\n" + this.player1.getName() + ", your marker is an X. " + this.player2.getName() + ", your marker is an O.\n");
    }




//----------------------------------METHOD: SET UP HUMAN VS COMPUTER----------------------------------------------------
    public void setUpComputer() {
        System.out.println("You have chosen to play the game Tic Tac Toe against the computer.");
        System.out.println("This is what the playing board looks like:\n");
        presentBoard();

        System.out.println("Player 1, please type your name:");
        String namePlayer1 = scanner.nextLine();

        this.player1 = new Player(namePlayer1);
        this.player2 = new Player("Computer");

        System.out.println("\n" + this.player1.getName() + ", your marker is an X. " + this.player2.getName() + "'s marker is an O.\n");
    }




//------------------------METHOD: PRESENT THE CURRENT PLAYING BOARD-----------------------------------------------------
    public void presentBoard() {
        System.out.println("+----+----+----+\n| " +
                positions.get(0) + "  | " + positions.get(1) + "  | " + positions.get(2) + "  |\n+----+----+----+\n| " +
                positions.get(3) + "  | " + positions.get(4) + "  | " + positions.get(5) + "  |\n+----+----+----+\n| " +
                positions.get(6) + "  | " + positions.get(7) + "  | " + positions.get(8) + "  |\n+----+----+----+\n");
    }



//-----------------------------METHOD: HUMAN PLACES MARKER ON BOARD-----------------------------------------------------
    public void placeMarker(Player player, String symbol) {
        System.out.println(player.getName() + ", choose a position, 1-9, where you wish to place your marker.");

        boolean validInput = false;
        int chosenMove = -1;

        while (!validInput) {

            try {
                chosenMove = scanner.nextInt();
                scanner.nextLine();
                if (chosenMove >= 1 && chosenMove <= 9) {
                    if (positions.get(chosenMove - 1) instanceof String) {
                        System.out.println("You cannot place your marker here. Enter a new position, please.");
                    } else {
                        validInput = true;
                    }
                } else {
                    System.out.println("You have not chosen a number between 1-9. Please try again.");
                }
            } catch(InputMismatchException exception) {
                System.out.println("This is not a valid input. Please enter a number between 1-9.");
                scanner.nextLine();
            }
        }

        positions.set(chosenMove - 1, symbol);
        presentBoard();
    }



//-----------------------------METHOD: COMPUTER PLACES MARKER ON BOARD--------------------------------------------------
    public void placeMarkerComputer(Player player, String symbol) {
        int computerMove = -1;
        boolean validRandomNumber = false;

        while(!validRandomNumber) {
            Random random = new Random();
            computerMove = random.nextInt(1, 10);
            if (positions.get(computerMove - 1) instanceof String) {

            } else {
                validRandomNumber = true;
            }
        }

        positions.set(computerMove - 1, symbol);
        presentBoard();

    }



//----------------------------------METHOD: LOOPS THROUGH THE ROUNDS TO PLACE MARKERS-----------------------------------
    public void placeMakerLoop() {
        boolean thereIsAWinner = false;

        while (!thereIsAWinner) {
            placeMarker(player1, "X");

            thereIsAWinner = checkWinner();
            if(thereIsAWinner) {
                break;
            }

            placeMarker(player2, "O");

            thereIsAWinner = checkWinner();
            if(thereIsAWinner) {
                break;
            }
        }
    }



//--------------------------------METHOD: CHECK IF SOMEONE HAS 3 IN A ROW-----------------------------------------------
    private boolean checkWinner() {
        if ((positions.get(0).equals("X") && positions.get(1).equals("X") && positions.get(2).equals("X")) ||
                (positions.get(3).equals("X") && positions.get(4).equals("X") && positions.get(5).equals("X")) ||
                (positions.get(6).equals("X") && positions.get(7).equals("X") && positions.get(8).equals("X")) ||
                (positions.get(0).equals("X") && positions.get(3).equals("X") && positions.get(6).equals("X")) ||
                (positions.get(1).equals("X") && positions.get(4).equals("X") && positions.get(7).equals("X")) ||
                (positions.get(2).equals("X") && positions.get(5).equals("X") && positions.get(8).equals("X")) ||
                (positions.get(0).equals("X") && positions.get(4).equals("X") && positions.get(8).equals("X")) ||
                (positions.get(2).equals("X") && positions.get(4).equals("X") && positions.get(6).equals("X"))) {
            System.out.println(player1.getName() + " is the winner! Congratulations!");
            player1.wins ++;
            return true;
        } else if ((positions.get(0).equals("O") && positions.get(1).equals("O") && positions.get(2).equals("O")) ||
                (positions.get(3).equals("O") && positions.get(4).equals("O") && positions.get(5).equals("O")) ||
                (positions.get(6).equals("O") && positions.get(7).equals("O") && positions.get(8).equals("O")) ||
                (positions.get(0).equals("O") && positions.get(3).equals("O") && positions.get(6).equals("O")) ||
                (positions.get(1).equals("O") && positions.get(4).equals("O") && positions.get(7).equals("O")) ||
                (positions.get(2).equals("O") && positions.get(5).equals("O") && positions.get(8).equals("O")) ||
                (positions.get(0).equals("O") && positions.get(4).equals("O") && positions.get(8).equals("O")) ||
                (positions.get(2).equals("O") && positions.get(4).equals("O") && positions.get(6).equals("O"))) {
            System.out.println(player2.getName() + " is the winner! Congratulations!");
            player2.wins ++;
            return true;
        } else if (isThereADraw()) {
            System.out.println("We've got ourselves a draw!");
            return true;
        }
        return false;
    }


//-------------------------------METHOD: CHECK IF THERE IS A DRAW--------------------------------------------------------
    private boolean isThereADraw() {
        for(Object object : positions) {
            if (object instanceof Integer) {
                return false;
            }
        }
        return true;
    }
}
