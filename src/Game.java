/**
 * @Author Akshay Vashisht
 * @Date 2022-10-25
 * @Version 1.0
 */

import javax.swing.text.View;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Game {

    private Bag bag = new Bag();
    private Board board = new Board();
    private ArrayList<Player> playerList = new ArrayList<Player>();
    private Player currentPlayer;
    private boolean placementCheck;
    private List<ScrabbleView> views;
    private int activeCount;

    /**
     * Public constructor for class game.
     */
    public Game() throws FileNotFoundException {
        this.views = new ArrayList<>();

        this.printRules();
        this.createPlayers("2");
        this.activeCount = this.playerList.size();
        this.currentPlayer = this.playerList.get(0);
        for(ScrabbleView v : this.views){v.update(this.currentPlayer);}
    }

    /**
     * Creates number of players based on user input
     * @param input User input for number of players
     */
    private void createPlayers(String input) {
        int value = Integer.parseInt(input);

        for (int i = 0; i < value; i++) {
            playerList.add(new Player(i));
            playerList.get(i).initializePlayerHand((ArrayList<Tile>) bag.removeTiles(7));
        }
    }

    public void addScrabbleView(ScrabbleView sv)
    {
        this.views.add(sv);
    }

    public void nextPlayer()
    {
        if (this.currentPlayer.getPlayerNumber() == (this.playerList.size() - 1)) {
            this.currentPlayer = this.playerList.get(0);
        }
        else {
            this.currentPlayer = this.playerList.get((this.currentPlayer.getPlayerNumber() + 1));
        }

        for(ScrabbleView v : this.views){v.update(this.currentPlayer);}
    }

    /**
     * Prints rules for the game
     */
    private void printRules() {
        System.out.println("\n");
        System.out.println("Welcome to Scrabble!\n");
        System.out.println("------------------------------------------------");
        System.out.println("Scrabble is a word game with 2-4 players. The goal of the game is to accumulate more " +
                "than or equal to 50 points");
        System.out.println("Each player starts with 7 tiles in their hand. All players have the 5 different actions " +
                "they can perform: Play, Exchange, Pass, Shuffle and Forfeit.");
        System.out.println("Play is used to place words from your hand onto the board (placing is done by typing " +
                "'Play CAT (your word) A1 (the tile)'");
        System.out.println("The order of the tile placement is important. Letter first means vertical placement and " +
                "Number first means horizontal (A1) is vertical");
        System.out.println("If the placement is illegal you will be asked to type a legal placement");
        System.out.println("Exchange is used to exchange any letters in your hand for new ones from the bag. " +
                "This is done by typing 'Exchange AO (words to be exchanged)'");
        System.out.println("Pass will pass the current players turn. This is done by typing 'Pass'");
        System.out.println("Shuffle will shuffle the current players hand so they can see their hand in a different " +
                "order. This is done by typing 'Shuffle'");
        System.out.println("Forfeit will have the player removed from the game " +
                "and all of the player's tiles will be put back into the bag");
        System.out.println("Good luck and have fun!");
        System.out.println("------------------------------------------------");
    }

    /**
     * Takes in a command from a given player and performs an action based on the command
     * @param command Contains the different parts of the command the user entered
     * @param currentPlayer The current player playing their turn
     * @return returns true of false (Should be void)
     */
    public boolean processCommand(Command command, Player currentPlayer) throws FileNotFoundException {
        ArrayList<Character> removeTilesFromHand = new ArrayList<>();
        List<Tile> addTilesToHand = new ArrayList<>();
        InHand inHand = null;
        placementCheck = true;

        inHand = new InHand(command.getWordAttempt(), currentPlayer.getHand());

        String action = command.getAction();

        switch (action) {
            case "exchange":
                if (inHand.wordInHand()) {
                    removeTilesFromHand = inHand.wordToList();
                    addTilesToHand = this.bag.removeTiles(removeTilesFromHand.size());
                    bag.placeTiles(currentPlayer.exchange((ArrayList<Tile>) addTilesToHand,
                            removeTilesFromHand)); //only enter capital letters
                }
                else {
                    System.out.println("All tiles not in hand");
                    return false;
                }
                break;

            case "play":
                if (inHand.wordInHand()) {
                    removeTilesFromHand = inHand.wordToList();
                    addTilesToHand = this.bag.removeTiles(removeTilesFromHand.size());
                    PlayMove playMove = new PlayMove(command.getPlacementAttempt(),
                            currentPlayer.exchange((ArrayList<Tile>) addTilesToHand, removeTilesFromHand),
                            this.board, command.getPlacementDirection());
                    if (playMove.placeTile()) {
                        if (playMove.checkWord()) {
                            this.board = playMove.getUpdatedBoard();
                            currentPlayer.addPoints(playMove.getPlayedWordScore());
                        }
                        else {
                            System.out.println("Word is not valid.");
                            this.bag.placeTiles(addTilesToHand);
                            currentPlayer.rollBack();
                        }

                    }
                    else {
                        this.bag.placeTiles(addTilesToHand);
                        currentPlayer.rollBack();
                        placementCheck = false;
                    }
                }
                else
                {
                    System.out.println("All tiles not in hand");
                    return false;
                }
                break;

            case "shuffle":
                currentPlayer.shuffle();
                break;

            case "pass":
                break;

            case "forfeit":
                System.out.println("Player " + (currentPlayer.getPlayerNumber()+1) + " has forfeited");
                bag.placeTiles(currentPlayer.getHand().getHand());
                currentPlayer.setActive(false);
                break;

            default:
                break;
        }

        return false;
    }

    /**
     * Board getter method
     * @return returns instance of board in game
     */
    private Board getBoard() {
        return board;
    }

    /**
     * gameLoop()
     * @throws FileNotFoundException
     */
    public void gameLoop() throws FileNotFoundException {

        //Game game = new Game();
        Scanner scanner = new Scanner(System.in);
        String userInput;
        Player currentPlayer;
        boolean flag = false;
        Parser parser = new Parser();
        int activeCount = 0;

        boolean running = true;

        this.printRules();

        while(!flag) {
            System.out.println("Type number of players (2, 3, or 4):");
            userInput = scanner.nextLine();
            if (userInput.equals("2") | userInput.equals("3") | userInput.equals("4") ) {
                this.createPlayers(userInput);
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

        currentPlayer = this.playerList.get(0);
        Command command = null;
        flag = false;

        GAME:
        do {
            activeCount = this.playerList.size();

            if (currentPlayer.getPoints() >= 50) {
                System.out.println("Player " + currentPlayer.getPlayerNumber() + " wins with "
                        + currentPlayer.getPoints()+ "!");
            }
            for (int i = 0; i < this.playerList.size(); i++) {
                if (!this.playerList.get(i).isActive()) {
                    activeCount--;
                }
            }
            if (activeCount == 1)
            {
                System.out.println("Not enough players");
                running = false;
            }
            else if (!currentPlayer.isActive()) {
                currentPlayer = this.playerList.get(currentPlayer.getPlayerNumber() + 1);
            }
            else {
                this.getBoard().printBoard();

                System.out.println("It is Player " + (currentPlayer.getPlayerNumber() + 1) + "'s turn");
                System.out.println("Current Player Points: " + currentPlayer.getPoints());
                currentPlayer.displayHand();
                System.out.println("What would you like to do? (play, exchange, shuffle, pass, forfeit)");
                while (!flag)
                {
                    command = parser.getCommand();
                    if (!command.hasAction()) {
                        System.out.println("Invalid Command");
                    }
                    else if (!command.hasWordAttempt() && ((command.getAction().equalsIgnoreCase("play") |
                            (command.getAction().equalsIgnoreCase("exchange")))))
                    {
                        System.out.println("Word attempt is blank");
                    }
                    else if (!command.hasPlacementAttempt() &&
                            ((command.getAction().equalsIgnoreCase("play"))))
                    {
                        System.out.println("Placement attempt is blank");
                    }
                    else {
                        flag = true;
                    }
                }
                flag = false;

                this.processCommand(command, currentPlayer);

                if (!command.getAction().equals("shuffle")) {;
                    if (!this.placementCheck) {
                        System.out.println("Try again");
                    }
                    if (currentPlayer.getPlayerNumber() == (this.playerList.size()-1)) {
                        currentPlayer = this.playerList.get(0);
                    }
                    else {
                        currentPlayer = this.playerList.get(currentPlayer.getPlayerNumber()+1);
                    }
                }
            }

        } while(running);

    }

}
