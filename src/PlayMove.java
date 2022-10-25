import java.util.ArrayList;
import java.util.Scanner;

public class PlayMove {

    public final static int PARSE_CHAR_AT_ONE = 1;
    public final static int PARSE_CHAR_AT_TWO = 2;
    public final static int PARSE_CHAR_AT_THREE = 3;
    public final static int ASCII_BASE = 64;
    public final static int DOUBLE_DIGIT_DETECTION = 3;

    private String placementAttempt;
    private Board board;
    private Board.Direction direction;
    private ArrayList<Tile> wordTiles;

    public PlayMove(String placementAttempt, ArrayList<Tile> wordTiles, Board board, boolean direction)
    {
        this.board = board;
        this.placementAttempt = placementAttempt;
        System.out.println(direction);
        this.direction = direction ? Board.Direction.HORIZONTAL : Board.Direction.VERTICAL;
        this.wordTiles = wordTiles;
    }

    private int parseRow()
    {
        if (this.direction == Board.Direction.HORIZONTAL)
        {
            if (this.placementAttempt.length() == DOUBLE_DIGIT_DETECTION) {
                return Integer.parseInt(placementAttempt.substring(Hand.PARSE_CHAR_AT_ZERO, PlayMove.PARSE_CHAR_AT_TWO));
            }
            else
            {
                return Integer.parseInt(String.valueOf(this.placementAttempt.charAt(Hand.PARSE_CHAR_AT_ZERO)));
            }
        }
        else
        {
            if (this.placementAttempt.length() == DOUBLE_DIGIT_DETECTION) {
                return Integer.parseInt(placementAttempt.substring(PlayMove.PARSE_CHAR_AT_ONE,
                        PARSE_CHAR_AT_THREE));
            }
            else
            {
                return Integer.parseInt(String.valueOf(this.placementAttempt.charAt(PlayMove.PARSE_CHAR_AT_ONE)));
            }
        }
    }

    private int parseColumn()
    {
        if (this.direction == Board.Direction.VERTICAL)
        {
            return (this.placementAttempt.charAt(Hand.PARSE_CHAR_AT_ZERO)) - ASCII_BASE;
        }
        else
        {
            return (this.placementAttempt.charAt(this.placementAttempt.length() == DOUBLE_DIGIT_DETECTION
                    ? PlayMove.PARSE_CHAR_AT_TWO : PlayMove.PARSE_CHAR_AT_ONE)) - ASCII_BASE;
        }
    }

    private boolean isLetter(char c) {
        return (c >= 'A' && c <= 'Z');
    }

    public void handleBlanks()
    {
        Scanner scan = new Scanner(System.in);
        String userValue = "";
        boolean flag = false;

        System.out.println(wordTiles.toString());

        for (Tile t : this.wordTiles)
        {
            if (t.getLetter().equals("_"))
            {
                flag = false;
                System.out.println("Enter blank replacement (1 LETTER)");
                userValue = scan.nextLine();

                while (!flag)
                {
                    if (!(userValue.length() == 1))
                    {
                        System.out.println("Enter one character");
                        userValue = scan.nextLine();
                    }
                    else if (!(isLetter(userValue.charAt(0))))
                    {
                        System.out.println("Enter a letter");
                        userValue = scan.nextLine();
                    }
                    else {
                        flag = true;
                        t.setLetter(userValue);
                    }
                }
            }
        }
    }

    public boolean placeTile()
    {
        handleBlanks();
        return this.board.placeWord(parseRow(), parseColumn(), this.wordTiles, this.direction);
    }

    public Board getUpdatedBoard()
    {
        return this.board;
    }
}