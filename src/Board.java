import java.util.*;

/**
 * Board is a 16*16 table. row and column 0 contain labels. the other 15*15 Cells each include a Square and a Tile.
 * Each Square has a corresponding multiplier type. Each tile has a letter that will be printed on Board.
 * Tiles are placed on Squares, which are placed on the Cells of the board.
 */
public class Board {

    private String[][] cells;                   // Stores letters of tiles
    private HashMap<Square, Tile> tiles;        // Maps Tiles to corresponding Squares based on matching coordinates
    private HashMap<String, Square> squares;    // Maps Squares to their corresponding coordinates

    final String COLOR_RESET = "\u001B[0m"; // for resetting the color of print statements


    public Board() {
        this.cells = new String[16][16];     // first row and col are for grid labels. the other 15*15 are for placing Squares and Tiles
        this.tiles = new HashMap<>();
        this.squares = new HashMap<>();
        initializeBoard();                   // assign a Square and a Tile to each cell
    }


    // Place a Square on each cell and then place a Tile on the Square.
    private void initializeBoard() {
        Tile emptyTile = new Tile("*", 0);
        Square thisSquare;
        for (int row = 0; row < cells.length; row++) {
            for (int col = 0; col < cells[row].length; col++) {
                // Each placement has a corresponding Tile and a Square
                thisSquare = new Square(row,col);
                tiles.put(thisSquare, emptyTile);   // put an empty tile on thisSquare
                String coordinates = thisSquare.getStringCoordinates();
                squares.put(coordinates, thisSquare);   // put thisSquare on the placement corresponding to coordinates (this placement)
                cells[row][col] = (tiles.get(thisSquare).getLetter());     // store the letter of the tile in this placement
            }
        }
    }


    // prints the state of the board
    public void printBoard() {
        String dashedLine = "-";
        final String YELLOW_BOLD_BRIGHT = "\033[1;93m";     // yellow color code for board labels

        // make a string dashedLine
        for (int i = 0; i < 19; i++) {
            dashedLine+= "-----";
        }

        // iterating through nested loop of rows and columns
        for (int row = 0; row < cells.length; row++) {
            System.out.println(dashedLine);
            for (int col = 0; col < cells[row].length; col++) {

                // print board's column labels as letters
                if ((row == 0) && (col < cells.length)) {
                    // skip printing column label for column 0
                    if (col == 0) {
                        System.out.printf("|" +"%5s", "|");
                        col++;  //todo check if causes problems later
                    }
                    String colAsLetter = Square.columns.get(col);
                    System.out.printf(YELLOW_BOLD_BRIGHT + "%5s", colAsLetter);
                    System.out.printf(COLOR_RESET + "|");
                }
                // print board's row labels as numbers
                else if ((col == 0) && (row > 0) && (row < cells.length)) {
                    System.out.printf("|");
                    System.out.printf(YELLOW_BOLD_BRIGHT + "%4s", row);
                    System.out.printf(COLOR_RESET + "|");
                }
                else {
                    // print Squares in corresponding color
                    String coordinates = "" + row + Square.columns.get(col);
                    Square thisSquare = squares.get(coordinates);

                    String squareColor = thisSquare.getMultiplier().getColor();
                    System.out.printf(squareColor + "%5s", cells[row][col]);
                    System.out.print(COLOR_RESET + "|");
                }
            }
            System.out.println();
        }
        System.out.println(dashedLine);
        printColorLegend();
    }


    // prints a color legend after the board to indicate the cell background color associated with premium squares
    private void printColorLegend() {
        final String COLOR_RESET = "\u001B[0m";     // color code for resetting text color to original
        System.out.println("\n" + "Color Legend");
        System.out.println(Square.Multiplier.DL.getColor() + "DL: Double Letter Score" + COLOR_RESET);
        System.out.println(Square.Multiplier.TL.getColor() + "TL: Triple Letter Score" + COLOR_RESET);
        System.out.println(Square.Multiplier.DW.getColor() + "DW: Double Word Score" + COLOR_RESET);
        System.out.println(Square.Multiplier.TW.getColor() + "TW: Triple Word Score" + COLOR_RESET + "\n");
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

    /*    // todo finish
    // returns true if a square has the empty tile (square hasn't been played yet)
    private boolean isBlank(int row, int col){
        final String PURPLE_BACKGROUND = "\033[45m"; // PURPLE
        if(cells[row][col].equals(PURPLE_BACKGROUND + "*" + COLOR_RESET)) {
            return true;
        }
        return false;
    }*/

}
