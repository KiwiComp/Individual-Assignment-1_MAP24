import java.util.ArrayList;
import java.util.Scanner;

public class Game {

    private Player player1;
    private Player player2;
    ArrayList<Object> positions = new ArrayList<>();
    Scanner scanner = new Scanner(System.in);

    public Game() {
        for(int i = 1; i<=9; i++) {
            positions.add(i);
        }
    }

    public void setUp() {
        System.out.println("You are to play the game Tic Tac Toe.");
        System.out.println("This is what the playing board looks like:\n");
        System.out.println("Player 1, please type your name:");
        String namePlayer1 = scanner.nextLine();
        System.out.println("Player 2, please type your name:");
        String namePlayer2 = scanner.nextLine();
        this.player1 = new Player(namePlayer1);
        this.player2 = new Player(namePlayer2);
        System.out.println(this.player1.getName() + ", your marker is an X. " + this.player2.getName() + ", your marker is an O.");
    }
}
