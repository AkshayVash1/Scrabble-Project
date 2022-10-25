import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Game {

    private Bag bag = new Bag();
    private Board board = new Board();
    private ArrayList<Player> playerList = new ArrayList<Player>();

    public Game() {}

    private void createPlayers(String input) {
        int value = Integer.parseInt(input);

        for (int i = 0; i < value; i++) {
            playerList.add(new Player(i));
            playerList.get(i).initializePlayerHand((ArrayList<Tile>) bag.removeTiles(7));
        }
    }

    private boolean processCommand(Command command, Player currentPlayer) {
        ArrayList<Character> removeTilesFromHand = new ArrayList<>();
        List<Tile> addTilesToHand = new ArrayList<>();
        InHand inHand = null;

        inHand = new InHand(command.getWordAttempt(), currentPlayer.getHand());

        String action = command.getAction();

        switch (action) {
            case "exchange":
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
                currentPlayer.displayHand();
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

    private ArrayList getPlayerList() {
        return playerList;
    }

    private Board getBoard() {
        return board;
    }

    public static void main(String[] args) {

        Game game = new Game();
        Scanner scanner = new Scanner(System.in);
        String userInput;
        Player currentPlayer;
        boolean flag = false;
        Parser parser = new Parser();
        int activeCount = 0;

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
        Command command = null;
        flag = false;

        GAME:
        do {
            activeCount = game.playerList.size();;

            for (int i = 0; i < game.playerList.size(); i++) {
                if (!game.playerList.get(i).isActive()) {
                    activeCount--;
                }
            }
            if (activeCount == 1)
            {
                System.out.println("Not enough players");
                running = false;
            }
            else if (!currentPlayer.isActive()) {
                currentPlayer = game.playerList.get(currentPlayer.getPlayerNumber() + 1);
            }
            else {
                game.getBoard().printBoard();

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
                    else if (!command.hasPlacementAttempt() && ((command.getAction().equalsIgnoreCase("play") |
                            (command.getAction().equalsIgnoreCase("exchange")))))
                    {
                        System.out.println("Placement attempt is blank");
                    }
                    else {
                        flag = true;
                    }
                }
                flag = false;

                game.processCommand(command, currentPlayer);

                if (currentPlayer.getPlayerNumber() == (game.playerList.size() - 1)) {
                    currentPlayer = game.playerList.get(0);
                }
                else {
                    currentPlayer = game.playerList.get(currentPlayer.getPlayerNumber()+1);
                }
            }

        } while(running);

    }

}
