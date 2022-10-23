import java.util.ArrayList;
import java.util.List;

public class PlayMove {

    private String wordAttempt;
    private String placementAttempt;
    private InHand inHand;
    private Command command;
    private Board board;
    private Hand hand;
    private boolean direction;

    public PlayMove(String wordAttempt, String placementAttempt, Hand hand, Board board)
    {
        this.hand = hand;
        this.board = board;
        this.wordAttempt = wordAttempt;
        this.placementAttempt = placementAttempt;
        //this.command = new Command(this.wordAttempt, this.placementAttempt);
        this.inHand = new InHand(wordAttempt, hand);
    }

    public boolean placeHorizontally()
    {
        //When direction is implemented, pass in this.command.getPlacementDirection
        //this.board.placeTiles(this.placementAttempt);
        return true;
    }

    /*Requires Board Implementation to continue */
    private boolean placeVertically()
    {
        return true;
    }
}
