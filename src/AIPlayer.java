import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class AIPlayer extends Player{
    /**
     * Constructor to initialize player points to 0 when player is created. Constructor to initialize
     * hand.
     *
     * @param playerNumber the number assigned to the player
     */

    private Board board;
    private boolean isAI;
    private HashMap<String, String> possiblePlays;
    private ArrayList<String> playableCoordinates;
    private Game game;

    public AIPlayer(int playerNumber, Board board, Game game) {
        super(playerNumber);
        this.board = board;
        this.board.setCells(board.getCells());
        this.board.setSquares(board.getSquares());
        this.board.setTiles(board.getTiles());
        this.board.setFirstPlay(board.isFirstPlay());

        this.isAI = true;
        this.possiblePlays = new HashMap<>();
        this.playableCoordinates = new ArrayList<>();
        this.game = game;
    }

    public void possiblePlays() {

    }


    public boolean isAI() {
        return this.isAI;
    }

    private void analyzeBoard() throws FileNotFoundException {
        this.playableCoordinates = this.board.getAIPlayableCoordinates();
        ArrayList<Tile> hand = this.getHand().getHand();

        for (int i = 0; i < hand.size(); i++)
        {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(hand.get(i).getLetter());

            for (String s : this.playableCoordinates)
            {
                attemptAiMove(stringBuilder.toString(), s, getDirection(s));
            }
        }

        for (int i = 0; i < hand.size(); i++)
        {
            for (int j = 0; i < hand.size(); j++)
            {
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append(hand.get(i).getLetter());
                stringBuilder.append(hand.get(j).getLetter());

                for (String s : this.playableCoordinates)
                {
                    attemptAiMove(stringBuilder.toString(), s, getDirection(s));
                }
            }
        }

        for (int i = 0; i < hand.size(); i++)
        {
            for (int j = 0; i < hand.size(); j++)
            {
                for (int k = 0; k < hand.size(); k++) {
                    StringBuilder stringBuilder = new StringBuilder();
                    stringBuilder.append(hand.get(i).getLetter());
                    stringBuilder.append(hand.get(j).getLetter());
                    stringBuilder.append(hand.get(k).getLetter());

                    for (String s : this.playableCoordinates) {
                        attemptAiMove(stringBuilder.toString(), s, getDirection(s));
                    }
                }
            }
        }

        for (int i = 0; i < hand.size(); i++)
        {
            for (int j = 0; i < hand.size(); j++)
            {
                for (int k = 0; k < hand.size(); k++)
                {
                    for (int l = 0; l < hand.size(); l++) {
                        StringBuilder stringBuilder = new StringBuilder();
                        stringBuilder.append(hand.get(i).getLetter());
                        stringBuilder.append(hand.get(j).getLetter());
                        stringBuilder.append(hand.get(k).getLetter());
                        stringBuilder.append(hand.get(l).getLetter());

                        for (String s : this.playableCoordinates) {
                            attemptAiMove(stringBuilder.toString(), s, getDirection(s));
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

    private boolean getDirection(String s)
    {
        return Character.isDigit(s.charAt(0));
    }
}
