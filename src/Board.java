import java.util.HashMap;
import java.util.List;

public class Board {

    private String[][] squares;                 // Stores letters of tiles
    private HashMap<Square, Tile> tiles;        // Maps Tiles to specific Squares
    private HashMap<Square, String> multipliers;// Maps Multiplier enum types to premium Squares

    public Board() {
        this.squares = new String[16][16];     // first row and col are for grid labels
        this.tiles = new HashMap<>();
        this.multipliers = new HashMap<>();
        initializeBoard();      // fill the squares with empty tiles
        initializeMultipliers();// map multipliers to corresponding premium squares
    }


    // Fill the squares with empty tiles
    private void initializeBoard() {
        Tile emptyTile = new Tile("*", 0);

        for (int row = 0; row < squares.length; row++) {
            for (int col = 0; col < squares[row].length; col++) {
                Square tempSquare = new Square(row,col);
                tiles.put(tempSquare, emptyTile);
                squares[row][col] = (tiles.get(tempSquare).getLetter());
            }
        }
    }


    private void printColorLegend() {
        final String COLOR_RESET = "\u001B[0m";     // color code for resetting text color to original
        System.out.println("\n" + "Color Legend");
        System.out.println(Square.Multiplier.DL.getColor() + "DL: Double Letter Score" + COLOR_RESET);
        System.out.println(Square.Multiplier.TL.getColor() + "TL: Triple Letter Score" + COLOR_RESET);
        System.out.println(Square.Multiplier.DW.getColor() + "DW: Double Word Score" + COLOR_RESET);
        System.out.println(Square.Multiplier.TW.getColor() + "TW: Triple Word Score" + COLOR_RESET + "\n");


    }

    // todo print premium squares with color
    //      fix formatting
    //      make column labels letters
    public void printBoard() {
        String dashedLine = "";
        final String TEXT_GREEN = "\u001B[32m";     // green color code for green board labels
        final String COLOR_RESET = "\u001B[0m";     // color code for resetting text color

        for (int i = 0; i < 20; i++) {
            dashedLine+= "-----";
        }

        // iterating through nested loop of rows and columns
        for (int row = 0; row < squares.length; row++) {
            System.out.println(dashedLine);
            for (int col = 0; col < squares[row].length; col++) {

                // print board's column labels
                if ((row == 0) && (col > 0) && (col < squares.length)) {
                    // skips printing column label for column 0
                    if (col == 0) {
                        System.out.printf("%5s", "|");
                    }
                    System.out.printf(TEXT_GREEN + "%5s", col);
                    System.out.printf(COLOR_RESET + "|");
                }
                // print board's row labels
                else if ((col == 0)&& (row < squares.length)) {
                    System.out.printf(TEXT_GREEN + "%4s", row);
                    System.out.printf(COLOR_RESET + "|");
                }
                else {
                    System.out.printf("%5s", squares[row][col]);
                    System.out.print("|");
                }
            }
            System.out.println();
        }
        System.out.println(dashedLine);
        printColorLegend();
    }

    //todo assign premium squares
    private void initializeMultipliers() {}

    // todo finish
    // returns true if a square has the empty tile (square hasn't been played yet)
    private boolean isBlank(int row, int col){
        if(squares[row][col].equals("*")) {
            return true;
        }
        return false;
    }

    public static void main(String[] args) {

        Board board = new Board();
        board.printBoard();
    }

    //todo implement
    // places tiles on the board when Player plays
    // creates new Square using startingSquare String
    public void placeTiles(String startingSquare, List<Tile> tiles) {}  // todo add direction parameter

    //todo implement
    // returns a list of all horizontal words to be checked by WordReader
    //public List<String> getHorizontalWords() {}

    //todo implement
    // returns a list of all vertical words to be checked by WordReader
    //public List<String> getVerticalWords() {}


}
