import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Hashtable;

public class AIPlayer extends Player{
    /**
     * Constructor to initialize player points to 0 when player is created. Constructor to initialize
     * hand.
     *
     * @param playerNumber the number assigned to the player
     */

    private Board board;

    private HashMap<String, String> possiblePlays;
    private HashMap<String, Boolean> playableCoordinates;
    private Game game;

    public AIPlayer(int playerNumber, Board board, Game game) {
        super(playerNumber);
        performBoardCopy(board);

        this.setAI(true);
        this.possiblePlays = new HashMap<>();
        this.playableCoordinates = new HashMap<String, Boolean>();
        this.game = game;
    }

    public void analyzeBoard(Board board) throws FileNotFoundException {
        performBoardCopy(board);
        this.playableCoordinates = this.board.getAIPlayableCoordinates();
        System.out.println(this.playableCoordinates.toString());
        ArrayList<Tile> hand = this.getHand().getHand();

        for (int i = 0; i < hand.size(); i++)
        {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(hand.get(i).getLetter());

            for (String s : this.playableCoordinates.keySet())
            {
                boolean direction = this.playableCoordinates.get(s);
                attemptAiMove(stringBuilder.toString(), changeStartingCoordinatesToVertical(s, direction),
                        direction);
            }
        }

        for (int i = 0; i < hand.size(); i++)
        {
            for (int j = 0; j < hand.size(); j++)
            {
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append(hand.get(i).getLetter());
                stringBuilder.append(hand.get(j).getLetter());

                for (String s : this.playableCoordinates.keySet())
                {
                    boolean direction = this.playableCoordinates.get(s);
                    attemptAiMove(stringBuilder.toString(), changeStartingCoordinatesToVertical(s, direction),
                            direction);
                }
            }
        }

        for (int i = 0; i < hand.size(); i++)
        {
            for (int j = 0; j < hand.size(); j++)
            {
                for (int k = 0; k < hand.size(); k++) {
                    StringBuilder stringBuilder = new StringBuilder();
                    stringBuilder.append(hand.get(i).getLetter());
                    stringBuilder.append(hand.get(j).getLetter());
                    stringBuilder.append(hand.get(k).getLetter());

                    for (String s : this.playableCoordinates.keySet()) {
                        boolean direction = this.playableCoordinates.get(s);
                        attemptAiMove(stringBuilder.toString(), changeStartingCoordinatesToVertical(s, direction),
                                direction);
                    }
                }
            }
        }

        for (int i = 0; i < hand.size(); i++)
        {
            for (int j = 0; j < hand.size(); j++)
            {
                for (int k = 0; k < hand.size(); k++)
                {
                    for (int l = 0; l < hand.size(); l++) {
                        StringBuilder stringBuilder = new StringBuilder();
                        stringBuilder.append(hand.get(i).getLetter());
                        stringBuilder.append(hand.get(j).getLetter());
                        stringBuilder.append(hand.get(k).getLetter());
                        stringBuilder.append(hand.get(l).getLetter());

                        for (String s : this.playableCoordinates.keySet()) {
                            boolean direction = this.playableCoordinates.get(s);
                            attemptAiMove(stringBuilder.toString(), changeStartingCoordinatesToVertical(s, direction),
                                    direction);
                        }
                    }
                }
            }
        }
    }

    private void attemptAiMove(String currentAttempt, String placementAttempt, boolean placementDirection)
            throws FileNotFoundException {
        ArrayList<Character> currentAttemptArrayList = new ArrayList<>();
        for (Character c : currentAttempt.toCharArray()) { currentAttemptArrayList.add(c); }

        this.getHand().removeTiles(currentAttemptArrayList,false);

        PlayMove playMove = new PlayMove(placementAttempt, this.getHand().getRecentlyRemoved(),
                this.board, placementDirection);
        if (playMove.placeTile()) {
            if (playMove.checkWord()) {
                possiblePlays.put(currentAttempt, placementAttempt);
                this.getHand().addTiles(this.getHand().getRecentlyRemoved(), false);
            }
        }
        else
        {
            this.getHand().addTiles(this.getHand().getRecentlyRemoved(), false);
        }
    }

    public void playHighestMove() throws FileNotFoundException {
        String possiblePlaysArray[] = (String[]) this.possiblePlays.keySet().toArray();
        String bestWord = " ";
        int bestWordPoints = 0;
        int currentWordPoints = 0;
        for (int i = 0; i < possiblePlaysArray.length; i++)
        {
            currentWordPoints = Board.calculateWordScore(possiblePlaysArray[i]);
            if(currentWordPoints > bestWordPoints)
            {
                bestWordPoints = currentWordPoints;
                bestWord = possiblePlaysArray[i];
            }
        }

        this.game.processCommand(new Command("play", this.possiblePlays.get(bestWord), bestWord));
    }

    private void performBoardCopy(Board board)
    {
        this.board = board;
        /*
        this.board.setCells(board.getCells());
        this.board.setSquares(board.getSquares());
        this.board.setTiles(board.getTiles());
        this.board.setFirstPlay(board.isFirstPlay());
        */
    }

    public HashMap<String, String> getPossiblePlays() {
        return possiblePlays;
    }

    private String changeStartingCoordinatesToVertical(String coordinates, boolean direction) {

        if (!direction) {
            String num = " ";
            char letter = ' ';
            if (coordinates.length() == 3) {
                num = coordinates.substring(0, 2);
                letter = coordinates.charAt(2);
            } else {
                num = coordinates.substring(0, 1);
                letter = coordinates.charAt(1);
            }

            StringBuilder sb = new StringBuilder();

            sb.append(letter);
            sb.append(num);

            return sb.toString();
        }
        else
        {
            return coordinates;
        }
    }
}
