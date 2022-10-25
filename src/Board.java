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
        // a cell is empty if it stores a single space (" ")
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

    // todo fix boolean return type
    public Boolean placeTileAt(int row, int col, Tile tile) {
        final String RED_BOLD_TEXT_COLOR = "\033[1;31m";    // red bold text color for printing error messages

        // invalid row error
        if (row < 1  || row > 15) {
            System.out.println(RED_BOLD_TEXT_COLOR + "ERROR: invalid row " + row);
            System.out.println("Cannot place tile: " + tile.getLetter() + ", on row: " + row + ", column: " + col);
            System.out.println("Valid row range is 1 to 15 inclusive." + COLOR_RESET);
            return false;
        }
        // invalid column error
        if (col < 1  || col > 15) {
            System.out.println(RED_BOLD_TEXT_COLOR+ "ERROR: invalid column " + col);
            System.out.println("Cannot place tile: " + tile.getLetter() + ", on row: " + row + ", column: " + col);
            System.out.println("Valid column range is 1 to 15 inclusive." + COLOR_RESET);
            return false;
        }

        // successful placement
        if (cellIsBlank(row, col)) {
            String coordinates = "" + row + Square.columns.get(col);
            tiles.put(coordinates,tile);
            cells[row][col] = tile.getLetter();
            return true;

            // todo error condition for when the tile is not touching another tile
/*            if (allAdjacentCellsEmpty() == false) {

            }*/
        }
        else {
            System.out.println(RED_BOLD_TEXT_COLOR + "ERROR: cannot place tile " + tile.getLetter()
                    + " on non-empty cell: " + "row: " + row + ", col: " + col + COLOR_RESET);
            return false;
        }

    }

    // returns false if the placement of the entire word would cause any error cases:
    // case 1: at least one tile of the word would exceed the edges of the board or
    // case 2: at least one tile of the word would intersect with another occupied cell
    // case 3: all tiles of the word are next to empty cells (can only place a word if at least one tile touches another tile)
    private Boolean placementIsValid() {
        return false;
    }

    // returns true if at least one cell adjacent to given cell is not blank
    private boolean adjacentNotBlank(int row, int col) {
        //if cell is a corner cell

        return false;
    }



    // return's square's coordinates as a string of a row as a number and column as a letter
    public String getStringCoords(int row, int col) {
        String stringCoordinates = "" + row + Square.columns.get(col);
        return stringCoordinates;
    }

    private String getTopCellContent(int row, int col) {return cells[row-1][col];}
    private String getRightCellContent(int row, int col) {return cells[row][col+1];}
    private String getBottomCellContent(int row, int col) {return cells[row+1][col];}
    private String getLeftCellContent(int row, int col) {return cells[row][col-1];}


    // returns list of cell contents adjacent to given cell
    // only for cells that are not in corners or on borders (cells that have 4 adjacent cells each)
    public ArrayList<String> getAdjacentCells(int row, int col) {
        ArrayList<String> adjacentCells = new ArrayList<>();

        adjacentCells.add(getTopCellContent(row, col));
        adjacentCells.add(getRightCellContent(row, col));
        adjacentCells.add(getBottomCellContent(row, col));
        adjacentCells.add(getLeftCellContent(row, col));

        return adjacentCells;
    }

    // returns list of cell contents adjacent to a border cell
    public ArrayList<String> getBordersAdjacentCells(int row, int col) {
        ArrayList<String> adjacentCells = new ArrayList<>();

        // cell is on top row (row == 1) but not a corner cell
        if ((row == 1) && (col > 1) && (col < 15)) {
            adjacentCells.add(getRightCellContent(row, col));
            adjacentCells.add(getBottomCellContent(row, col));
            adjacentCells.add(getLeftCellContent(row, col));
        }

        // cell is on rightmost column (col == 15) but not a corner cell
        else if ((col == 15) && (row > 1) && (row < 15)) {
            adjacentCells.add(getTopCellContent(row, col));
            adjacentCells.add(getBottomCellContent(row, col));
            adjacentCells.add(getLeftCellContent(row, col));
        }

        // cell is on bottom row (row == 15) but not a corner cell
        else if ((row == 15) && (col > 1) && (col < 15)) {
            adjacentCells.add(getTopCellContent(row, col));
            adjacentCells.add(getRightCellContent(row, col));
            adjacentCells.add(getLeftCellContent(row, col));
        }

        // cell is on leftmost column (col == 1) but not a corner cell
        else if ((col == 1) && (row > 1) && (row < 15)) {
            adjacentCells.add(getTopCellContent(row, col));
            adjacentCells.add(getBottomCellContent(row, col));
            adjacentCells.add(getLeftCellContent(row, col));
        }
        else {
            System.out.println("Error: given cell is either not on a border or is a corner cell.");
        }
        return adjacentCells;
    }


    // returns list of all cells adjacent to corner cells of the board
    public ArrayList<String> getCornersAdjacentCells(int row, int col) {

        ArrayList<String> adjacentCells = new ArrayList<>();
        ArrayList<String> cornerCellCoords = new ArrayList<String>(Arrays.asList(new String[]{"1A", "1O", "15A", "15O"}));
        String thisCell = getStringCoords(row,col);
        //if this cell is a corner cell
        if (cornerCellCoords.contains(thisCell)) {
            switch (thisCell) {
                case "1A":
                    adjacentCells.add(getRightCellContent(row,col));
                    adjacentCells.add(getBottomCellContent(row, col));
                    break;
                case "1O":
                    adjacentCells.add(getLeftCellContent(row,col));
                    adjacentCells.add(getBottomCellContent(row, col));
                    break;
                case "15A":
                    adjacentCells.add(getRightCellContent(row,col));
                    adjacentCells.add(getTopCellContent(row, col));
                    break;
                case "15O":
                    adjacentCells.add(getLeftCellContent(row,col));
                    adjacentCells.add(getTopCellContent(row, col));
                    break;
                default:
                    System.out.println("Error: Not a corner cell!");
            }
        }
        return adjacentCells;
    }



    // todo fix implementation of Boolean return type
    public Boolean placeWord(int row, int col, ArrayList<Tile> tiles, Direction direction) {
        ArrayList<Tile> wordTiles = tiles;
        int ROW = row;
        int COL = col;
        Boolean tilePlaced = false;
        int successCount = 0;

        for (Tile tile: tiles) {
                tilePlaced = this.placeTileAt(ROW, COL, tile);
                if (direction == Direction.VERTICAL) {
                    ROW++;
                } else {    // direction is horizontal
                    COL++;
                }
        }
        // if all the tiles were placed successfully return true
        return false;
    }


    // todo bug fix: single letter word on right edge
    public ArrayList<String> getHorizontalWords() {
        ArrayList<String> horizontalWords = new ArrayList<>();
        String currentWord = "";
        String currentLetter;
        //Boolean wordComplete = false;

        for (int row = 1; row < cells.length ; row++) {
            for (int col = 1; col < cells.length - 1; col++) {
                if (!cellIsBlank(row, col)) {   // if
                    currentLetter = cells[row][col];
                    currentWord += currentLetter;
                    // if next cell in row is blank, store currentWord in horizontalWords list and move on to next word
                    if (cellIsBlank(row, col + 1)) {
                        horizontalWords.add(currentWord);
                        System.out.println("current word is: " + currentWord);
                        currentWord = ""; // set currentWord to empty string so it can store the next word in row
                    }
                    // handle cells on the right border of the board
                    if (!cellIsBlank(row, col + 1) && (col == cells.length - 2)) {
                        currentWord += cells[row][col + 1];
                        horizontalWords.add(currentWord);
                        System.out.println("current word is: " + currentWord);
                        currentWord = ""; // set currentWord to empty string so it can store the next word in row
                    }
                }
            }
        }
        System.out.println("number of words " + horizontalWords.size());
        return horizontalWords;
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
        board.placeWord(8, 11, HELLO_tiles, Direction.HORIZONTAL);




        // testing all corners
        board.placeTileAt(1,1,new Tile("A",1));
        board.placeTileAt(1,2,new Tile("B",1));
        board.placeTileAt(2,1,new Tile("C",1));
        System.out.println(board.getCornersAdjacentCells(1,1));


        board.placeTileAt(1,15,new Tile("D",1));
        board.placeTileAt(1,14,new Tile("E",1));
        board.placeTileAt(2,15,new Tile("F",1));
        System.out.println(board.getCornersAdjacentCells(1,15));

        board.placeTileAt(15,1,new Tile("G",1));
        board.placeTileAt(15,2,new Tile("H",1));
        board.placeTileAt(14,1,new Tile("I",1));
        System.out.println(board.getCornersAdjacentCells(15,1));

        board.placeTileAt(15,15,new Tile("J",1));
        board.placeTileAt(15,14,new Tile("K",1));
        board.placeTileAt(14,15,new Tile("L",1));
        System.out.println(board.getCornersAdjacentCells(15,15));

        ArrayList <String> horizWords = board.getHorizontalWords();


        board.printBoard();

        // testing border cells
        System.out.println("---------------------------------------------------------------");
        System.out.println("testing border cells adjacents");
        System.out.println(board.getBordersAdjacentCells(5, 5));

        // testing getAdjacentCells()
        System.out.println("---------------------------------------------------------------");
        System.out.println("testing get adjacent cells");
        board.placeTileAt(8,7,new Tile("P",1));
        board.placeTileAt(8,9,new Tile("Q",1));
        board.placeTileAt(7,8,new Tile("R",1));
        System.out.println(board.getAdjacentCells(8, 8));
        System.out.println(board.getAdjacentCells(8, 14));

        board.printBoard();
    }



    //todo implement
    // returns a list of all vertical words to be checked by WordReader
    //public List<String> getVerticalWords() {}

}
