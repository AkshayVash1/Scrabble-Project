/**
 * Primary model of the game. Contains logic relating to the board and player hand management. This class also
 * initializes the Bag that will be used to communicate with the player's hand. The players are also initialized
 * within this class. Since it's a model this class also manages updating the views.
 * @Author Akshay Vashisht
 * @Author Mohamed Kaddour
 * @Author Jaydon Haghighi
 * @Author Mahtab Ameli
 * @Date 2022-11-13
 * @Version 2.0
 */

import javax.swing.*;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class Game {

    private Bag bag = new Bag();
    private Board board = new Board();
    private ArrayList<Player> playerList = new ArrayList<Player>();
    private Player currentPlayer;
    private boolean placementCheck;
    private List<ScrabbleView> views;
    private int activeCount;
    private ArrayList<Character> removeTilesFromHand;
    private ArrayList<Character> exchangeTilesFromHand;
    private boolean firstPlayInTurn;
    private String startingCoordinates;
    private boolean gameFinished;

    /**
     * Public constructor for class game.
     */
    public Game() {
        this.views = new ArrayList<>();
        this.removeTilesFromHand= new ArrayList<>();
        this.exchangeTilesFromHand = new ArrayList<>();
        this.firstPlayInTurn = true;
        this.gameFinished = false;
    }

    /**
     * Creates number of players based on user input
     * @param input User input for number of players
     */
    public void createPlayers(String input) {
        int value = Integer.parseInt(input);

        playerList.clear();
        for (int i = 0; i < value; i++) {
            playerList.add(new Player(i));
            playerList.get(i).initializePlayerHand((ArrayList<Tile>) bag.removeTiles(7));
        }
        this.activeCount = this.playerList.size();
        this.currentPlayer = this.playerList.get(0);
        for(ScrabbleView v : this.views){v.update(new ScrabbleEvent(this.currentPlayer, this.board, this.gameFinished));}
    }

    /**
     * Adds ScrabbleView object to list of views
     *
     * @param sv
     */
    public void addScrabbleView(ScrabbleView sv)
    {
        this.views.add(sv);
    }

    /**
     * Logic for changing the turn order from current player to next player
     */
    public void nextPlayer()
    {
        this.removeTilesFromHand.clear();
        if (currentPlayer != null) {
            if (currentPlayer.getPoints() >= 50) {
                this.gameFinished = true;
            } else {
                if (this.currentPlayer.getPlayerNumber() == (this.playerList.size() - 1)) {
                    this.currentPlayer = this.playerList.get(0);
                } else {
                    this.currentPlayer = this.playerList.get((this.currentPlayer.getPlayerNumber() + 1));
                }
            }
        }

        this.firstPlayInTurn = true;
        for(ScrabbleView v : this.views){v.update(new ScrabbleEvent(this.currentPlayer, this.board, this.gameFinished));}
    }

    /**
     * Returns current player
     *
     * @return currentPlayer
     */
    public Player getCurrentPlayer()
    {
        return this.currentPlayer;
    }

    /**
     * Clears the array list RemoveTilesFromHand
     */
    public void clearRemoveTilesFromHand()
    {
        removeTilesFromHand.clear();
    }

    /**
     * Adds a character to RemoveTilesFromHand array list
     *
     * @param c
     */
    public void addToRemoveTilesFromHand(Character c)
    {
        if (c == '_')
        {
            c = JOptionPane.showInputDialog(null, "Enter letter").charAt(0);
        }
        this.removeTilesFromHand.add(c);
        this.firstPlayInTurn = false;
    }

    /**
     * Adds a character to ExchangeTilesFromHand array list
     *
     * @param c
     */
    public void addToExchangeTilesFromHand(Character c)
    {
        this.exchangeTilesFromHand.add(c);
    }

    /**
     * Removes a character from ExchangeTilesFromHand array list
     *
     * @param c
     */
    public void removeFromExchangeTilesFromHand(Character c)
    {
        this.exchangeTilesFromHand.remove(c);
    }

    /**
     * Clears the RemoveFromExchangeTilesFromHand array list
     */
    public void clearRemoveFromExchangeTilesFromHand()
    {
        this.exchangeTilesFromHand.clear();
    }

    /**
     * Returns ExchangeTilesFromHand array list
     *
     * @return exchangeTilesFromHand
     */
    public ArrayList<Character> getExchangeTilesFromHand()
    {
        return this.exchangeTilesFromHand;
    }

    /**
     * Returns FirstPlayInTurn
     *
     * @return firstPlayInTurn
     */
    public boolean getFirstPlayInTurn()
    {
        return this.firstPlayInTurn;
    }

    /**
     * Initializes the starting coordinates for the game
     *
     * @param startingCoordinates
     */
    public void setStartingCoordinates(String startingCoordinates)
    {
        this.startingCoordinates = startingCoordinates;
    }

    /**
     * Converts starting coordinate code from Horizontal direction to vertical direction
     */
    public void changeStartingCoordinatesToVertical()
    {
        String num = " ";
        char letter = ' ';
        if (this.startingCoordinates.length() == 3) {
            num = startingCoordinates.substring(0, 2);
            letter = this.startingCoordinates.charAt(2);
        }
        else
        {
            num =startingCoordinates.substring(0, 1);
            letter = this.startingCoordinates.charAt(1);

        }
        StringBuilder sb = new StringBuilder();

        sb.append(letter);
        sb.append(num);

        this.startingCoordinates = sb.toString();
    }

    /**
     * Returns the starting coordinate for the game
     *
     * @return startingCoordinate
     */
    public String getStartingCoordinates()
    {
        return this.startingCoordinates;
    }

    /**
     * Converts the characters in an array to a single string
     *
     * @param ar
     * @return arraylist String
     */
    public static String convertCharArrayListToString(ArrayList<Character> ar)
    {
        StringBuilder sb = new StringBuilder();

        for (Character c : ar)
        {
            sb.append(c);
        }

        return sb.toString();
    }

    /**
     * Takes in a command from a given player and performs an action based on the command
     * @param command Contains the different parts of the command the user entered
     * @return returns true of false (Should be void)
     */
    public boolean processCommand(Command command) throws FileNotFoundException {
        List<Tile> addTilesToHand = new ArrayList<>();
        InHand inHand = null;
        placementCheck = true;
        boolean rc = true;

        String action = command.getAction();

        switch (action) {
            case "exchange":
                inHand = new InHand(convertCharArrayListToString(this.exchangeTilesFromHand), currentPlayer.getHand());

                if (inHand.wordInHand()) {
                    this.exchangeTilesFromHand = inHand.wordToList();
                    addTilesToHand = this.bag.removeTiles(this.exchangeTilesFromHand.size());
                    bag.placeTiles(currentPlayer.exchange((ArrayList<Tile>) addTilesToHand,
                            this.exchangeTilesFromHand)); //only enter capital letters
                }
                else {
                    System.out.println("All tiles not in hand");
                    rc = false;
                }

                for(ScrabbleView v : this.views){v.update(new ScrabbleEvent(this.currentPlayer, this.board, this.gameFinished));}
                break;

            case "play":
                inHand = new InHand(convertCharArrayListToString(this.removeTilesFromHand), currentPlayer.getHand());
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
                            rc = false;
                        }

                    }
                    else {
                        this.bag.placeTiles(addTilesToHand);
                        currentPlayer.rollBack();
                        placementCheck = false;
                        rc = false;
                    }
                }
                else
                {
                    System.out.println("All tiles not in hand");
                    rc = false;
                }

                for(ScrabbleView v : this.views){v.update(new ScrabbleEvent(this.currentPlayer, this.board, this.gameFinished));}
                break;

            case "shuffle":
                currentPlayer.shuffle();
                for(ScrabbleView v : this.views){v.update(new ScrabbleEvent(this.currentPlayer, this.board, this.gameFinished));}
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

        return rc;
    }

    /**
     * Board getter method
     * @return returns instance of board in game
     */
    public Board getBoard() {
        return board;
    }

}
