package src;

import java.beans.beancontext.BeanContextChild;
import java.util.*;

/**
 * Board is a 16*16 table. row and column 0 contain labels. the other 15*15 Cells each include a Square and a Tile.
 * Each Square has a corresponding multiplier type. Each tile has a letter that will be printed on Board.
 * Each Cell of the board has a Square (with a specific multiplier and color) and a Tile (with a letter and a score value).
 */
public class Board {

    private String[][] cells;                   // Stores letters of tiles
    private HashMap<String, Tile> tiles;        // Maps Tiles to corresponding coordinates
    private HashMap<String, Square> squares;    // Maps Squares to their corresponding coordinates

    final String COLOR_RESET = "\u001B[0m"; // for resetting the color of print statements

    public enum Direction{HORIZONTAL, VERTICAL}


    public Board() {
        this.cells = new String[16][16];     // first row and col are for grid labels. the other 15*15 are for placing Squares and Tiles
        this.tiles = new HashMap<>();
        this.squares = new HashMap<>();
        initializeBoard();                   // assign a Square and a Tile to each cell
    }


    // Place a Square on each cell and then place a Tile on the Square.
    private void initializeBoard() {
        Tile emptyTile = new Tile(" ", 0);
        Square thisSquare;
        for (int row = 0; row < cells.length; row++) {
            for (int col = 0; col < cells[row].length; col++) {
                // Each placement has a corresponding Tile and a Square
                thisSquare = new Square(row,col);
                String coordinates = thisSquare.getStringCoordinates();
                tiles.put(coordinates, emptyTile);   // put an empty tile on thisSquare
                squares.put(coordinates, thisSquare);   // put thisSquare on the placement corresponding to coordinates (this placement)
                cells[row][col] = (tiles.get(coordinates).getLetter());     // store the letter of the tile in this placement
            }
        }
    }


    // prints the state of the board
    public void printBoard() {
        String dashedLine = "-";
        final String GREEN_BOLD_TEXT_COLOR = "\033[1;92m";     //bold green text color code for board labels
        final String YELLOW_BOLD_TEXT_COLOR = "\033[1;93m";               //bold yellow text color for printing the letters of tiles

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
                        col++;
                    }
                    String colAsLetter = Square.columns.get(col);
                    System.out.printf(GREEN_BOLD_TEXT_COLOR + "%5s", colAsLetter);
                    System.out.printf(COLOR_RESET + "|");
                }
                // print board's row labels as numbers
                else if ((col == 0) && (row > 0) && (row < cells.length)) {
                    System.out.printf("|");
                    System.out.printf(GREEN_BOLD_TEXT_COLOR + "%4s", row);
                    System.out.printf(COLOR_RESET + "|");
                }
                else {
                    // print Squares in corresponding color
                    String coordinates = "" + row + Square.columns.get(col);
                    Square thisSquare = squares.get(coordinates);

                    String squareColor = thisSquare.getMultiplier().getColor();
                    System.out.printf(squareColor + YELLOW_BOLD_TEXT_COLOR + "%5s", cells[row][col]);
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



    // returns true if a square has the empty tile (square has not been played yet)
    private boolean cellIsBlank(int row, int col){

        if(cells[row][col].equals(" ")) {
            return true;
        }
        return false;
    }

    private Square getSquareAt(String coordinates) {
        return squares.get(coordinates);
    }


    private String getCellContent(int row, int col){
        return cells[row][col];
    }

    public void placeTileAt(int row, int col, Tile tile) {
        final String RED_BOLD_TEXT_COLOR = "\033[1;31m";    // red bold text color for printing error messages
        if (row < 1  || row > 15) {
            System.out.println(RED_BOLD_TEXT_COLOR + "ERROR: invalid row " + row);
            System.out.println("Cannot place tile: " + tile.getLetter() + ", on row: " + row + ", column: " + col);
            System.out.println("Valid row range is 1 to 15 inclusive." + COLOR_RESET);
            return;
        }
        if (col < 1  || col > 15) {
            System.out.println(RED_BOLD_TEXT_COLOR+ "ERROR: invalid column " + col);
            System.out.println("Cannot place tile: " + tile.getLetter() + ", on row: " + row + ", column: " + col);
            System.out.println("Valid column range is 1 to 15 inclusive." + COLOR_RESET);
            return;
        }

        if (cellIsBlank(row, col)) {
            String coordinates = "" + row + Square.columns.get(col);
            tiles.put(coordinates,tile);
            cells[row][col] = tile.getLetter();
        }
        else {
            System.out.println(RED_BOLD_TEXT_COLOR + "ERROR: cannot place tile " + tile.getLetter()
                    + " on non-empty cell: " + "row: " + row + ", col: " + col + COLOR_RESET);
            return;
        }

    }

    public void placeWord(int row, int col, ArrayList<Tile> tiles, Direction direction) {
        ArrayList<Tile> wordTiles = tiles;
        int ROW = row;
        int COL = col;
        for (Tile tile: tiles) {
                this.placeTileAt(ROW, COL, tile);
                if (direction == Direction.VERTICAL) {
                    ROW++;
                } else {    // direction is horizontal
                    COL++;
                }
        }

    }


    public static void main(String[] args) {
        Board board = new Board();
        board.printBoard();

        // placing tiles for word "CAT" horizontally starting at 8H
        ArrayList<Tile> HELLO_tiles = new ArrayList<>();
        HELLO_tiles.add(new Tile("H", 3));
        HELLO_tiles.add(new Tile("E", 1));
        HELLO_tiles.add(new Tile("L", 1));
        HELLO_tiles.add(new Tile("L", 1));
        HELLO_tiles.add(new Tile("O", 1));

        board.placeWord(8, 8, HELLO_tiles, Direction.VERTICAL);

        board.printBoard();
    }


    //todo implement
    // returns a list of all horizontal words to be checked by WordReader
    //public List<String> getHorizontalWords() {}

    //todo implement
    // returns a list of all vertical words to be checked by WordReader
    //public List<String> getVerticalWords() {}



}
