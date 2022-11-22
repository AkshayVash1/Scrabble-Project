import java.util.ArrayList;
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

    public AIPlayer(int playerNumber, Board board) {
        super(playerNumber);
        this.board = board;
        this.board.setCells(board.getCells());
        this.board.setSquares(board.getSquares());
        this.board.setTiles(board.getTiles());
        this.board.setFirstPlay(board.isFirstPlay());

        this.isAI = true;
        this.possiblePlays = new HashMap<>();
        this.playableCoordinates = new ArrayList<>();
    }

    public boolean isAI() {
        return this.isAI;
    }

    private void analyzeBoard()
    {
        this.playableCoordinates = this.board.getAIPlayableCoordinates();
    }
}
