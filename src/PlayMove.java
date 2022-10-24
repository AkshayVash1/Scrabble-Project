import java.util.ArrayList;

public class PlayMove {

    public final static int PARSE_CHAR_AT_ONE = 1;

    private String placementAttempt;
    private Board board;
    private Board.Direction direction;
    private ArrayList<Tile> wordTiles;

    public PlayMove(String placementAttempt, ArrayList<Tile> wordTiles, Board board, boolean direction)
    {
        this.board = board;
        this.placementAttempt = placementAttempt;
        this.direction = direction ? Board.Direction.HORIZONTAL : Board.Direction.VERTICAL;
        this.wordTiles = wordTiles;
    }

    private int parseRow()
    {
        if (this.direction == Board.Direction.HORIZONTAL)
        {
            return this.placementAttempt.charAt(Hand.PARSE_CHAR_AT_ZERO);
        }
        else
        {
            return this.placementAttempt.charAt(PlayMove.PARSE_CHAR_AT_ONE);
        }
    }

    private int parseColumn()
    {
        if (this.direction == Board.Direction.VERTICAL)
        {
            return this.placementAttempt.charAt(Hand.PARSE_CHAR_AT_ZERO);
        }
        else
        {
            return this.placementAttempt.charAt(PlayMove.PARSE_CHAR_AT_ONE);
        }
    }

    public boolean placeTile()
    {
        //return this.board.placeWord(parseRow(), parseColumn(), this.wordTiles, this.direction); modify for this
        //to return void

        return true;

    }

    public Board getUpdatedBoard()
    {
        return this.board;
    }
}