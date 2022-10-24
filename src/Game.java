package src;

import java.util.ArrayList;
import java.util.Scanner;
import src.*;

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

        while(!flag) {
            System.out.println("Type number of players (2, 3, or 4):");
            userInput = scanner.nextLine();
            if (userInput.equals("2") | userInput.equals("3") | userInput.equals("4") ) {
                game.createPlayers(userInput);
                flag = true;
            }
            else {
                System.out.println("Not a valid input, please try again.");
            }
        }

        flag = false;

        while(!flag) {
            System.out.println("Type 'Start' to start the game, or 'Quit' to quit the game:");
            userInput = scanner.nextLine();
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



            while(!nextPlayer) {

            }
        } while(running);

    }

}
