import javax.swing.*;
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
    private boolean isFirstPlay;

    // direction of word placement
    public enum Direction{HORIZONTAL, VERTICAL}

    // colors and corresponding color code used in this class's print statements
    public enum TextColor{
        GREEN_BOLD("\033[1;92m"),
        YELLOW_BOLD("\033[1;93m"),
        COLOR_RESET("\u001B[0m");
        final String code;
        TextColor(String colorCode) {
            this.code = colorCode;
        }
    }

    public Board() {
        this.cells = new String[16][16];     // first row and col are for grid labels. the other 15*15 are for placing Squares and Tiles
        this.tiles = new HashMap<>();
        this.squares = new HashMap<>();
        this.isFirstPlay = true;
        initializeBoard();                   // assign a Square and a Tile to each cell
    }

    public String[][] getCells() {
        return cells;
    }

    public void setCells(String[][] cells) {
        for (int i = 0; i < 15; i++)
        {
            for (int j = 0; j < 15; j++)
            {
                this.cells[i][j] = cells[i][j];
            }
        }
    }

    public HashMap<String, Tile> getTiles() {
        return tiles;
    }

    public void setTiles(HashMap<String, Tile> tiles) {
        this.tiles.clear();
        this.tiles.putAll(tiles);
    }

    public HashMap<String, Square> getSquares() {
        return squares;
    }

    public void setSquares(HashMap<String, Square> squares) {
        this.squares.clear();
        this.squares.putAll(squares);
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
                    System.out.printf(TextColor.GREEN_BOLD.code + "%5s", colAsLetter);
                    System.out.printf(TextColor.COLOR_RESET.code + "|");
                }
                // print board's row labels as numbers
                else if ((col == 0) && (row > 0) && (row < cells.length)) {
                    System.out.printf("|");
                    System.out.printf(TextColor.GREEN_BOLD.code + "%4s", row);
                    System.out.printf(TextColor.COLOR_RESET.code + "|");
                }
                else {
                    // print Squares in corresponding color
                    String coordinates = "" + row + Square.columns.get(col);
                    Square thisSquare = squares.get(coordinates);

                    String squareColor = thisSquare.getMultiplier().getColor();
                    System.out.printf(squareColor + TextColor.YELLOW_BOLD.code + "%5s", cells[row][col]);
                    System.out.print(TextColor.COLOR_RESET.code + "|");
                }
            }
            System.out.println();
        }
        System.out.println(dashedLine);
        printColorLegend();
    }



    // prints a color legend after the board to indicate the cell background color associated with premium squares
    private void printColorLegend() {
        System.out.println("\n" + "Color Legend");
        System.out.println(Square.Multiplier.DL.getColor() + "DL: Double Letter Score" + TextColor.COLOR_RESET.code);
        System.out.println(Square.Multiplier.TL.getColor() + "TL: Triple Letter Score" + TextColor.COLOR_RESET.code);
        System.out.println(Square.Multiplier.DW.getColor() + "DW: Double Word Score" + TextColor.COLOR_RESET.code);
        System.out.println(Square.Multiplier.TW.getColor() + "TW: Triple Word Score" + TextColor.COLOR_RESET.code + "\n");
    }



    // returns true if a square has the empty tile (square has not been played yet)
    private boolean cellIsBlank(int row, int col){
        // a cell is empty if it stores a single space (" ")
        if(cells[row][col].equals(" ")) {
            return true;
        }
        return false;
    }



    public Boolean placeTileAt(int row, int col, Tile tile) {
        // row out of bounds error
        if (row < 1  || row > 15) {
            System.out.println("Cannot place tile " + tile.getLetter() + ", on invalid row: " + row
                    + ", and valid column: " + col);
            System.out.println("Valid row range is 1 to 15 inclusive.");
            return false;
        }
        // column out of bounds error
        if (col < 1  || col > 15) {

            System.out.println("ERROR: Cannot place tile " + tile.getLetter() + " on row: " + row
                    + ", and invalid column: " + col);
            System.out.println("Valid column range is 1 to 15 inclusive.");
            return false;
        }
        // successful placement
        if (cellIsBlank(row, col)) {
            String coordinates = "" + row + Square.columns.get(col);
            tiles.put(coordinates,tile);
            cells[row][col] = tile.getLetter();
            return true;
        }
        // placement attempt on non-empty cell error
        else {
            System.out.println("ERROR: Cannot place tile " + tile.getLetter()
                    + " on non-empty cell: " + "row: " + row + ", col: " + col);
            return false;
        }

    }



    // returns true if at least one cell adjacent to given cell is not blank (hence the word can be placed according to Scrabble rules)
    public Boolean allAdjacentsBlank(int row, int col) {
        Boolean allBlank = false;
        int blankCount = 0;
        ArrayList<String> adjacentCells = getAllAdjacentCells(row,col);
        for (String cell: adjacentCells) {
            if(cell.equals(" ")) {
                blankCount++;
            }
        }
        if (blankCount < adjacentCells.size()) {
            return false;
        }
        return true;
    }


    // returns list of all cells adjacent to given cell, regardless of cell's type (corner, border, regular)
    public ArrayList<String> getAllAdjacentCells(int row, int col) {
        ArrayList<String> adjacentCells = new ArrayList<>();
        //handle corner cells
        ArrayList<String> cornerCellCoords = new ArrayList<String>(Arrays.asList(new String[]{"1A", "1O", "15A", "15O"}));
        String thisCell = getStringCoords(row,col);
        //if this cell is a corner cell
        if (cornerCellCoords.contains(thisCell)) {
            adjacentCells = getCornersAdjacentCells(row, col);
        }
        // else if this cell is not a  corner cell, but it's on the b order row/columns of the board
        else if ((row == 1) || (row == 15) || (col == 1) || (col == 15)) {
            adjacentCells = getBordersAdjacentCells(row, col); }
        // else if the cell is a regular cell
        else if ((row > 1) && (row < 15) && (col > 1) && (col < 15)) {
            adjacentCells = getAdjacentCells(row,col);}
        else {
            System.out.println("ERROR: Invalid cell");
        }
        return adjacentCells;
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


    // returns true if this condition is met: if the word were to be placed
    // , at least one tile would be adjacent to an existing tile
    public Boolean adjacentConditionMet(int row, int col, ArrayList<Tile> tiles, Direction direction) {
        ArrayList<Tile> wordTiles = tiles;
        int ROW = row;
        int COL = col;
        int count = 0;

        for (Tile tile: wordTiles) {
            // check condition for tile
            if (!allAdjacentsBlank(ROW,COL)) {count++;}
            if (direction == Direction.VERTICAL) {ROW++;}
            else {COL++;}
        }
        // if at least one tile would be next to a non-blank tile, the condition is met
        if (count > 0) {
            return true;
        }
        return false;
    }



    //Places as many letters on board as are valid. returns true if placement is valid. returns false if placement is invalid.
    public Boolean placeWord(int row, int col, ArrayList<Tile> tiles, Direction direction) {
        ArrayList<Tile> wordTiles = tiles;
        Boolean adjacentConditionMet = true;

        int ROW = row;
        int COL = col;
        Boolean tilePlaced = false;
        int tilePlacedCount = 0;
        adjacentConditionMet = adjacentConditionMet(row, col, tiles, direction);
        if (isFirstPlay)
        {
            adjacentConditionMet = true;
            this.isFirstPlay = false;
        }
        for (Tile tile: tiles) {
            tilePlaced = this.placeTileAt(ROW, COL, tile);
            if (tilePlaced) {tilePlacedCount++; }
            if (direction == Direction.VERTICAL) {  // placement direction is vertical
                ROW++;
            } else {    // placement direction is horizontal
                COL++;
            }
        }
        // return true if adjacent Not empty condition was met and all the tiles were placed successfully
        if (!adjacentConditionMet) {
            System.out.println("ERROR: Placement is NOT valid." +
            "\nAt least one tile must be adjacent to an existing tile.");
            return false;
        }
        else if ((adjacentConditionMet) && tilePlacedCount == tiles.size()){
            System.out.println("Placement is valid.");
            getWordScore(row, col, tiles, direction);
            return true;
        }
        return false;
    }



    public String getWordFromTiles(ArrayList<Tile> tiles) {
        String word = "";
        ArrayList<Tile> tilesList = tiles;
        for (Tile t: tilesList) {
            word += t.getLetter();
        }
        return word;
    }



    public ArrayList<String> getWordsOnRow(int row) {
        ArrayList<String> wordsOnRow = new ArrayList<>();
        String currentWord = "";
        String currentLetter;
        int ROW = row;
        //iterate through columns of ROW == row
            for (int COL = 1; COL < cells.length - 1; COL++) {
                if (!cellIsBlank(ROW, COL)) {   // if
                    currentLetter = cells[ROW][COL];
                    currentWord += currentLetter;
                    // if next cell in row is blank, store currentWord in horizontalWords list and move on to next word
                    if (cellIsBlank(ROW, COL + 1)) {
                        wordsOnRow.add(currentWord);
                        //System.out.println("current word on row is: " + currentWord);
                        currentWord = ""; // set currentWord to empty string so it can store the next word in row
                    }
                    // handle cells on the right border of the board (col == 15)
                    if (!cellIsBlank(ROW, COL + 1) && (COL == cells.length - 2)) {
                        currentWord += cells[ROW][COL + 1];
                        wordsOnRow.add(currentWord);
                        //System.out.println("current word on row is: " + currentWord);
                        currentWord = ""; // set currentWord to empty string so it can store the next word in row
                    }
                }
            }
        System.out.println("number of words on row: " + wordsOnRow.size());
        return wordsOnRow;
    }

    public ArrayList<String> getWordsOnCol(int col) {
        ArrayList<String> wordsOnCol = new ArrayList<>();
        String currentWord = "";
        String currentLetter;
        int COL = col;
        //iterate through rows of COL == col
        for (int ROW = 1; ROW < cells.length - 1; ROW++) {
            if (!cellIsBlank(ROW, COL)) {   // if
                currentLetter = cells[ROW][COL];
                currentWord += currentLetter;
                // if next cell in row is blank, store currentWord in horizontalWords list and move on to next word
                if (cellIsBlank(ROW + 1, COL)) {
                    wordsOnCol.add(currentWord);
                    //System.out.println("current word on row is: " + currentWord);
                    currentWord = ""; // set currentWord to empty string so it can store the next word in row
                }
                // handle cells on the bottom border of the board (row == 15)
                if (!cellIsBlank(ROW + 1, COL) && (ROW == cells.length - 2)) {
                    currentWord += cells[ROW + 1][COL];
                    wordsOnCol.add(currentWord);
                    //System.out.println("current word on row is: " + currentWord);
                    currentWord = ""; // set currentWord to empty string so it can store the next word in row
                }
            }
        }
        System.out.println("number of words on column: " + wordsOnCol.size());
        return wordsOnCol;
    }



    // given the dirsction of word placement, checks which word on that row/column the word attempt is a subset of
    //score the entire word (including suffix/prefix and new letters added)
    public int getWordScore(int row, int col, ArrayList<Tile> tilesList, Direction direction) {
        ArrayList<Tile> wordTiles = tilesList;
        String lettersPlayed = getWordFromTiles(wordTiles);
        String wordToScore = lettersPlayed;
        ArrayList<String> wordsOnRow ;
        ArrayList<String> wordsOnCol;

        int wordScore = 0;
        if (direction == Direction.HORIZONTAL) {
            // we want to check which word on row contains horizontal wordFromTiles
            wordsOnRow = getWordsOnRow(row);
            for (String w: wordsOnRow) {
                if (w.contains(lettersPlayed)) {
                    wordToScore = w;
                    System.out.println("horizontal word, " + w + " contains letters played: " + lettersPlayed);
                    break;
                }
            }
        }
        // else direction == Direction.VERTICAL
        else {
            // we want to check which word on column contains vertical wordFromTiles
            wordsOnCol = getWordsOnCol(col);
            for (String w: wordsOnCol) {
                if (w.contains(lettersPlayed)) {
                    wordToScore = w;
                    System.out.println("vertical word, " + w + " contains letters played: " + lettersPlayed);
                    break;
                }
            }
        }
        wordScore = calculateWordScore(wordToScore);
        System.out.println("score for word " + wordToScore + ": " + wordScore);
        return wordScore;
    }



    private int calculateWordScore(String wordToScore) {
        int letterScore = 0;
        int wordScore = 0;

        for (int i = 0; i < wordToScore.length(); i++) {
            letterScore = Bag.getLetterValue("" + wordToScore.charAt(i));
            wordScore += letterScore;
            System.out.println("current character of word " + wordToScore + ": " + wordToScore.charAt(i));
        }
        return wordScore;
    }


    // returns ArrayList of all horizontal words placed on board
    public ArrayList<String> getHorizontalWords () {
        ArrayList<String> horizontalWords = new ArrayList<>();
        String currentWord = "";
        String currentLetter;

        for (int row = 1; row < cells.length; row++) {
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
                    // handle cells on the right border of the board (col == 15)
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



        // returns ArrayList of all Vertical words placed on board
        public ArrayList<String> getVerticalWords () {
            ArrayList<String> VerticalWords = new ArrayList<>();
            String currentWord = "";
            String currentLetter;

            for (int col = 1; col < cells.length; col++) {
                for (int row = 1; row < cells.length - 1; row++) {
                    if (!cellIsBlank(row, col)) {
                        currentLetter = cells[row][col];
                        currentWord += currentLetter;
                        // if next cell in column is blank, store currentWord in horizontalWords list and move on to next word
                        if (cellIsBlank(row + 1, col)) {
                            VerticalWords.add(currentWord);
                            System.out.println("current word is: " + currentWord);
                            currentWord = ""; // set currentWord to empty string so it can store the next word in row
                        }
                        // handle cells on the bottom border of board (row == 15)
                        if (!cellIsBlank(row + 1, col) && (row == cells.length - 2)) {
                            currentWord += cells[row + 1][col];
                            VerticalWords.add(currentWord);
                            System.out.println("current word is: " + currentWord);
                            currentWord = ""; // set currentWord to empty string so it can store the next word in row
                        }
                    }
                }
            }
            System.out.println("number of words " + VerticalWords.size());
            return VerticalWords;
        }



        public static void main (String[]args){
            Board board = new Board();
            board.printBoard();
        }

}
