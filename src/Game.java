import java.util.ArrayList;
import java.util.Scanner;

public class Game {

    private Player player1;
    private Player player2;
    ArrayList<Object> positions = new ArrayList<>();
    Scanner scanner = new Scanner(System.in);

    public Game() {
        for (int i = 1; i <= 9; i++) {
            positions.add(i);
        }
    }


    public void playGame() {

        setUp();

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

    //-----------------------------------METHOD: SET UP WITH PLAYER NAMES---------------------------------------------------
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
        System.out.println("\n" + this.player1.getName() + ", your marker is an X. " + this.player2.getName() + ", your marker is an O.");
    }

    //------------------------METHOD: PRESENT THE CURRENT PLAYING BOARD-----------------------------------------------------
    public void presentBoard() {
        System.out.println("+----+----+----+\n| " +
                positions.get(0) + "  | " + positions.get(1) + "  | " + positions.get(2) + "  |\n+----+----+----+\n| " +
                positions.get(3) + "  | " + positions.get(4) + "  | " + positions.get(5) + "  |\n+----+----+----+\n| " +
                positions.get(6) + "  | " + positions.get(7) + "  | " + positions.get(8) + "  |\n+----+----+----+\n");
    }



//-----------------------------METHOD: PLACE MARKER ON BOARD------------------------------------------------------------
    public void placeMarker(Player player, String symbol) {
        System.out.println(player.getName() + ", choose a position, 1-9, where you wish to place your marker.");

        boolean validInput = false;
        while (!validInput) {
            int chosenMove = scanner.nextInt();
            scanner.nextLine();

            if (chosenMove >= 1 && chosenMove <= 9) {
                if (positions.get(chosenMove - 1) instanceof String) {
                    System.out.println("You cannot place your marker here. Enter a new position, please.");
                } else {
                    positions.set(chosenMove - 1, symbol);
                    presentBoard();
                    validInput = true;
                }
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
            return true;
        } else if (isThereADraw()) {
            System.out.println("We've got ourselves a draw!");
            return true;
        }
        return false;
    }



    private boolean isThereADraw() {
        for(Object object : positions) {
            if (object instanceof Integer) {
                return false;
            }
        }
        return true;


    }
}
