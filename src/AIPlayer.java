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

    public AIPlayer(int playerNumber, Board board) {
        super(playerNumber);
        this.board = board;
        this.isAI = true;
        this.possiblePlays = new HashMap<>();
    }

    public boolean isAI() {
        return this.isAI;
    }

    private void analyzeBoard()
    {
        ArrayList<String> playableCoordinates = new ArrayList<>();
        this.board.getAIPlayableCoordinates();
    }
}
