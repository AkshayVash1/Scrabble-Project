import java.io.FileNotFoundException;
import java.lang.reflect.Array;
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

    public AIPlayer(int playerNumber, Game game) {
        super(playerNumber);

        this.board = new Board();
        this.setAI(true);
        this.possiblePlays = new HashMap<>();
        this.playableCoordinates = new HashMap<String, Boolean>();
        this.game = game;
    }

    public void analyzeBoard(Board board) throws FileNotFoundException {
        this.board.setCells(board.getCells());
        this.board.setSquares(board.getSquares());
        this.board.setTiles(board.getTiles());
        this.board.setFirstPlay(board.isFirstPlay());

        System.out.println(this.getHand().getHand().toString());
        this.playableCoordinates = this.board.getAIPlayableCoordinates();
        System.out.println(this.playableCoordinates.toString());
        ArrayList<Tile> hand = this.getHand().getHand();

        for (int i = 0; i < hand.size(); i++)
        {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(hand.get(i).getLetter());

            //System.out.println(stringBuilder.toString());

            for (String s : this.playableCoordinates.keySet())
            {
                boolean direction = this.playableCoordinates.get(s);
                //System.out.println(stringBuilder.toString() + " " + changeStartingCoordinatesToVertical(s, direction));
                attemptAiMove(stringBuilder.toString(), changeStartingCoordinatesToVertical(s, direction),
                        direction);
            }
        }

        for (int i = 0; i < hand.size(); i++)
        {
            for (int j = 0; j < hand.size(); j++)
            {
                if (i != j) {
                    StringBuilder stringBuilder = new StringBuilder();
                    stringBuilder.append(hand.get(i).getLetter());
                    stringBuilder.append(hand.get(j).getLetter());

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
                for (int k = 0; k < hand.size(); k++) {
                    if ((i != j) && (i != k ) && (j != k)) {
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
        }
    }

    private void attemptAiMove(String currentAttempt, String placementAttempt, boolean placementDirection)
            throws FileNotFoundException {
        ArrayList<Character> currentAttemptArrayList = new ArrayList<>();
        for (Character c : currentAttempt.toCharArray()) { currentAttemptArrayList.add(c); }

        PlayMove playMove = new PlayMove(placementAttempt, mapCharToTile(currentAttemptArrayList),
                this.board, placementDirection);
        if (playMove.placeTile()) {
            if (playMove.checkWord()) {
                possiblePlays.put(currentAttempt, placementAttempt);
                System.out.println(this.getHand().getHand().toString());
            }
        }
        else
        {
            System.out.println(this.getHand().getHand().toString());
        }
    }

    public void playHighestMove() throws FileNotFoundException {
        ArrayList<String> possiblePlays = new ArrayList<>();
        for (Object s : this.possiblePlays.keySet().toArray()) {possiblePlays.add((String)s);}
        String bestWord = " ";
        int bestWordPoints = 0;
        int currentWordPoints = 0;
        for (int i = 0; i < possiblePlays.size(); i++)
        {
            currentWordPoints = Board.calculateWordScore(possiblePlays.get(i));
            if(currentWordPoints > bestWordPoints)
            {
                bestWordPoints = currentWordPoints;
                bestWord = possiblePlays.get(i);
            }
        }

        System.out.println(bestWord + " " + this.possiblePlays.get(bestWord));

        for (Character c : bestWord.toCharArray()){this.game.addToRemoveTilesFromHand(c);}

        this.game.processCommand(new Command("play", bestWord, this.possiblePlays.get(bestWord)));
        this.possiblePlays.clear();
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

    private ArrayList<Tile> mapCharToTile(ArrayList<Character> currentAttemptArrayList)
    {
        ArrayList<Tile> returnArray = new ArrayList<>();

        for (Character c : currentAttemptArrayList)
        {
            for (Tile t : this.getHand().getHand())
            {
                if (t.getLetter().equals(Character.toString(c)))
                {
                    returnArray.add(t);
                    break;
                }
            }
        }

        return returnArray;
    }
}
