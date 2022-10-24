package src;

import java.util.ArrayList;
import java.util.List;
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
            playerList.get(i).initializePlayerHand((ArrayList<Tile>) bag.removeTiles(7));
        }


    }

    private boolean processCommand(Command command, Player currentPlayer) {
        ArrayList<Character> removeTilesFromHand = new ArrayList<>();
        List<Tile> addTilesToHand = new ArrayList<>();
        InHand inHand = null;

        if (!command.hasAction()) {
            System.out.println("Unknown Command");
            return false;
        }
        else if (command.hasWordAttempt()) {
            inHand = new InHand(command.getWordAttempt(), currentPlayer.getHand());
        }

        String action = command.getAction();

        switch (action) {
            case "exchange":
                assert inHand != null;
                if (inHand.wordInHand()) {
                    removeTilesFromHand = inHand.wordToList();
                    addTilesToHand = this.bag.removeTiles(removeTilesFromHand.size());
                    bag.placeTiles(currentPlayer.exchange((ArrayList<Tile>) addTilesToHand,
                            removeTilesFromHand));
                }
                else {
                    return false;
                }
                break;

            case "play":
                assert inHand != null;
                if (inHand.wordInHand()) {
                    removeTilesFromHand = inHand.wordToList();
                    addTilesToHand = this.bag.removeTiles(removeTilesFromHand.size());
                    PlayMove playMove = new PlayMove(command.getPlacementAttempt(),
                            currentPlayer.exchange((ArrayList<Tile>) addTilesToHand, removeTilesFromHand),
                            this.board, command.getPlacementDirection());

                    if (playMove.placeTile()) {
                        this.board = playMove.getUpdatedBoard();
                    }
                    else {
                        this.bag.placeTiles(addTilesToHand);
                        currentPlayer.rollBack();
                    }
                }
                break;

            case "shuffle":
                currentPlayer.shuffle();
                break;

            case "pass":

                break;

            default:
                break;
        }

        return false;
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
        Parser parser = new Parser();

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
            Command command = parser.getCommand();



            while(!nextPlayer) {

            }
        } while(running);

    }

}
