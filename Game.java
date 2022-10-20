import java.util.ArrayList;
import java.util.Scanner;

public class Game {

    private Bag bag = new Bag();
    private Board board = new Board();
    private ArrayList<Player> playerList = new ArrayList<Player>();

    public Game() {}

    public void createPlayers(String input) {
        int value = Integer.parseInt(input);

        for (int i = 0; i < value; i++) {
            playerList.add(new Player());
        }
    }

    public void printHand(Player player) {
        String hand = "";

        for (int i = 0; i < player.getHand.getHandSize(); i++) {
            hand += player.getHand().get(i);
        }

        System.out.println("Hand: " + hand);
    }

    public ArrayList getPlayerList() {
        return playerList;
    }

    public static void main(String[] args) {

        Game game = new Game();
        Scanner scanner = new Scanner(System.in);
        String userInput;
        Player currentPlayer;
        boolean flag = false;

        boolean running = true;

        System.out.println("Welcome to Scrabble!");
        System.out.println("Type number of players (2, 3, or 4):");
        userInput = scanner.nextLine();

        while(!flag) {
            if (Integer.parseInt(userInput) == (2 | 3 | 4)) {
                game.createPlayers(userInput);
                flag = true;
            }
            else {
                System.out.println("Not a valid input, please try again.");
            }
        }

        flag = false;

        System.out.println("Type 'Start' to start the game, or 'Quit' to quit the game.");

        while(!flag) {
            if (userInput.equalsIgnoreCase("Start")) {
                flag = true;
            }
            else if (userInput.equalsIgnoreCase("Quit")) {
                System.exit(0);
            }
            else {
                System.out.println("Not a valid input, please try again.");
            }
        }

        currentPlayer = game.playerList.get(0);

        GAME:
        do {
            boolean nextPlayer = false;

            game.printHand(currentPlayer);

            while(!nextPlayer) {

            }
        } while(running);

    }

}
