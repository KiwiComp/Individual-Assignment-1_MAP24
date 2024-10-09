import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class Game {

    private Player player1;
    private Player player2;
    ArrayList<Object> positions = new ArrayList<>();
    ArrayList<int[]> winningCombinations = new ArrayList<>();
    Scanner scanner = new Scanner(System.in);
    int numberOfGamesPlayed = 0;
    int numberOfHumanPlayers;



    public Game() {
        for (int i = 1; i <= 9; i++) {
            positions.add(i);
        }

        winningCombinations.add(new int[]{0, 1, 2});
        winningCombinations.add(new int[]{3, 4, 5});
        winningCombinations.add(new int[]{6, 7, 8});
        winningCombinations.add(new int[]{0, 3, 6});
        winningCombinations.add(new int[]{1, 4, 7});
        winningCombinations.add(new int[]{2, 5, 8});
        winningCombinations.add(new int[]{0, 4, 8});
        winningCombinations.add(new int[]{6, 4, 2});
    }




//-----------------------------------------METHOD: RUN THE GAME---------------------------------------------------------
    public void playGame() {
        System.out.println("----------------+ TIC TAC TOE +----------------");
        System.out.println("Do you wish to play against another player or against the computer? Enter 1 for another player, and enter 2 for the computer.");

        boolean chooseGameAlternative = false;
        while (!chooseGameAlternative) {
            String chosenAlternative = scanner.nextLine();
            if (chosenAlternative.trim().equals("1")) {
                setUpHumans();
                numberOfHumanPlayers = 2;
                chooseGameAlternative = true;
            } else if (chosenAlternative.trim().equals("2")) {
                setUpComputer();
                numberOfHumanPlayers =1;
                chooseGameAlternative = true;
            } else {
                System.out.println("You have not chosen a valid option. Please enter 1 for playing against another player or 2 for playing against the computer.");
            }
        }


        boolean isRunning = true;
        while(isRunning) {

            placeMarkerLoop();

            numberOfGamesPlayed ++;

            System.out.println("\nCurrent standing:");
            System.out.println("--> Number of games played: " + numberOfGamesPlayed);
            System.out.println("--> " + player1.getName() + " wins: " + player1.wins);
            System.out.println("--> " + player2.getName() + " wins: " + player2.wins);
            System.out.println("--> Number of draws: " + (numberOfGamesPlayed-player1.wins-player2.wins));

            System.out.println("\nDo you wish to play another round? (y/n)");
            boolean validInput = false;
            while(!validInput) {
                String newGame = scanner.nextLine();
                    if (newGame.trim().equalsIgnoreCase("y")) {
                        System.out.println();
                        for (int i = 1; i >= 1 && i <= 9; i++) {
                            positions.set(i - 1, i);
                        }
                        validInput = true;
                    } else if (newGame.trim().equalsIgnoreCase("n")) {
                        System.out.println("\nThank you for playing, have a great day!");
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
    public void setUpHumans() {
        System.out.println("You have chosen to play against another player.");
        System.out.println("This is what the playing board looks like:\n");
        presentBoard();

        System.out.println("Player 1, please type your name:");
        String namePlayer1 = scanner.nextLine();
        System.out.println();
        System.out.println("Player 2, please type your name:");
        String namePlayer2 = scanner.nextLine();

        this.player1 = new Player(namePlayer1);
        this.player2 = new Player(namePlayer2);

        System.out.println("\n" + this.player1.getName() + ", your marker is an X. " + this.player2.getName() + ", your marker is an O.\n");
    }




//----------------------------------METHOD: SET UP HUMAN VS COMPUTER----------------------------------------------------
    public void setUpComputer() {
        System.out.println("You have chosen to play against the computer.");
        System.out.println("This is what the playing board looks like:\n");
        presentBoard();

        System.out.println("Please type your name:");
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





//----------------------------------METHOD: LOOPS THROUGH THE ROUNDS TO PLACE MARKERS-----------------------------------
    public void placeMarkerLoop() {
        boolean thereIsAWinner = false;

        while (!thereIsAWinner) {
            placeMarkerPlayer(player1, "X");

//            thereIsAWinner = checkWinner();
          thereIsAWinner = checkWinningCombinations(player1, "X");
            if(thereIsAWinner) {
                break;
            }

            if(numberOfHumanPlayers == 2) {
                placeMarkerPlayer(player2, "O");
            } else if(numberOfHumanPlayers == 1) {
                placeMarkerComputer(player2, "O");
            }

//            thereIsAWinner = checkWinner();
          thereIsAWinner = checkWinningCombinations(player2, "O");
            if(thereIsAWinner) {
                break;
            }
        }
    }




//-----------------------------METHOD: HUMAN PLACES MARKER ON BOARD-----------------------------------------------------
    public void placeMarkerPlayer(Player player, String symbol) {
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
        boolean validRandomNumber = false;
        int computerMove = -1;

        while(!validRandomNumber) {
            Random random = new Random();
            computerMove = random.nextInt(1, 10);
            if (!(positions.get(computerMove - 1) instanceof String)) {
                System.out.println(player.getName() + " places marker on position " + computerMove + ".");
                validRandomNumber = true;
            }
        }

        positions.set(computerMove - 1, symbol);
        presentBoard();
    }



//----------------------------METHOD: CHECK WINNER/DRAW-----------------------------------------------------------------
    public boolean checkWinningCombinations(Player player, String symbol) {
        for (int[] combinationToCheck : winningCombinations) {
            if(isThereAWinner(combinationToCheck, symbol)) {
                System.out.println(player.getName() + " is the winner!");
                player.wins++;
                return true;
            }

        } if (isThereADraw()) {
            System.out.println("We've got ourselves a draw!");
            return true;
        }
        return false;
    }



//-------------------------METHOD: CHECK WINNING COMBINATION------------------------------------------------------------
    private boolean isThereAWinner(int[] winningCombo, String symbol) {
        for(int index : winningCombo) {
            if(!(positions.get(index) instanceof String && positions.get(index).equals(symbol))) {
                return false;
            }
        }
        return true;
    }


//-------------------------------METHOD: CHECK IF THERE IS A DRAW-------------------------------------------------------
    private boolean isThereADraw() {
        for(Object object : positions) {
            if (object instanceof Integer) {
                return false;
            }
        }
        return true;
    }
}
