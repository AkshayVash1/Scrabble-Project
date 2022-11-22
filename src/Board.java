import java.lang.reflect.Array;
import java.util.*;

/**
 * A board is a 16*16 table of cells, representing the board of the game Scrabble. Each cell of the board contains
 * a square with its corresponding color and multiplier value, as well as the tile placed on it, which has a letter and score.
 * The cells themselves store the value of the tile letter placed on them. Printing the board displays the state of the game.
 *
 * @author Mahtab Ameli
 * @date 2022-11-13
 * @version 2.0
 */
public class Board {

    /**
     * 16*16 array of String stores letters of tiles placed.
     */
    private String[][] cells;

    /**
     * Maps Tiles to corresponding String coordinates (row as digit, and column as letter).
     */
    private HashMap<String, Tile> tiles;

    /**
     * Maps Squares to their corresponding String coordinates (row as digit, and column as letter).
     */
    private HashMap<String, Square> squares;

    /**
     * True if the current move is the first play of the game.
     */
    private boolean isFirstPlay;

    /**
     * Most recently played word
     */
    private String playedWord = "";

    public String getPlayedWord() {
        return playedWord;
    }

    /**
     * Direction of word placement on the board
     */
    public enum Direction{HORIZONTAL, VERTICAL}


    /**
     * IF the word is invalid or not
     */
    private boolean invalidPlacement;

    public boolean isInvalidPlacement() {
        return invalidPlacement;
    }

    /**
     * Colors and corresponding color codes used in this class's print statements.
     */
    public enum TextColor{
        GREEN_BOLD("\033[1;92m"),
        YELLOW_BOLD("\033[1;93m"),
        COLOR_RESET("\u001B[0m");
        final String code;
        TextColor(String colorCode) {
            this.code = colorCode;
        }
    }

    /**
     * Constructor for the class.
     * Creates a board and initializes its fields.
     */
    public Board() {
        this.cells = new String[16][16];     // first row and col are for grid labels. the other 15*15 are for placing Squares and Tiles
        this.tiles = new HashMap<>();
        this.squares = new HashMap<>();
        this.isFirstPlay = true;
        initializeBoard();                   // assign a Square and a Tile to each cell
    }

    /**
     * Returns array of cells of the board.
     *
     * @return the array of cells.
     */
    public String[][] getCells() {
        return cells;
    }

    /**
     * Setter for the cells array.
     *
     * @param cells the array of all cells on the board.
     */
    public void setCells(String[][] cells) {
        for (int i = 0; i < 15; i++)
        {
            for (int j = 0; j < 15; j++)
            {
                this.cells[i][j] = cells[i][j];
            }
        }
    }

    /**
     * Returns list of all tiles on the board.
     *
     * @return the tiles HashMap.
     */
    public HashMap<String, Tile> getTiles() {
        return tiles;
    }

    /**
     * Setter for the tiles' hashmap.
     *
     * @param tiles the mapping between coordinates and tiles of the board.
     */
    public void setTiles(HashMap<String, Tile> tiles) {
        this.tiles.clear();
        this.tiles.putAll(tiles);
    }

    /**
     * Getter for the squares' hashmap.
     *
     * @return the squares HashMap.
     */
    public HashMap<String, Square> getSquares() {
        return squares;
    }

    /**
     * Returns true if the current move is the first play move of the game.
     *
     * @returns true if the move is the first play.
     */
    public boolean isFirstPlay() {
        return isFirstPlay;
    }

    /**
     * Setter for the first game flag.
     *
     * @param firstPlay the boolean value for whether current move is the first play move of the game.
     */
    public void setFirstPlay(boolean firstPlay) {
        isFirstPlay = firstPlay;
    }

    /**
     * Setter for the squares' hashmap.
     *
     * @param squares the mapping between coordinates and squares of the board.
     */
    public void setSquares(HashMap<String, Square> squares) {
        this.squares.clear();
        this.squares.putAll(squares);
    }

    /**
     * Places a Square on each cell and then place a Tile on the Square. Update the value of the cell to tile's letter.
     */
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


    /**
     * Prints the state of the board, displaying each square's corresponding color, and each cell's letter value.
     */
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

    /**
     * Prints a color legend under the board to indicate the cell background color associated with premium squares.
     */
    private void printColorLegend() {
        System.out.println("\n" + "Color Legend");
        System.out.println(Square.Multiplier.DL.getColor() + "DL: Double Letter Score" + TextColor.COLOR_RESET.code);
        System.out.println(Square.Multiplier.TL.getColor() + "TL: Triple Letter Score" + TextColor.COLOR_RESET.code);
        System.out.println(Square.Multiplier.DW.getColor() + "DW: Double Word Score" + TextColor.COLOR_RESET.code);
        System.out.println(Square.Multiplier.TW.getColor() + "TW: Triple Word Score" + TextColor.COLOR_RESET.code + "\n");
    }

    /**
     * Returns true if a square has the empty tile (square has not been played yet).
     * @param row the integer value of the row of given cell.
     * @param col the integer value of the column of given cell.
     * @return true if the cell has not been played yet.
     */
    private boolean cellIsBlank(int row, int col){
        // a cell is empty if it stores a single space (" ")
        if(cells[row][col].equals(" ")) {
            return true;
        }
        return false;
    }

    public ArrayList<String> getAIPlayableCoordinates(){
        ArrayList<String> playableCoordinates = new ArrayList<>();

        for (int ROW = 1; ROW < cells.length - 1; ROW++) {
            for (int COL = 1; COL < cells.length - 1; COL++) {
                if(!cells[ROW][COL].equals(" ")) {
                    if(cells[ROW+1][COL].equals(" "))
                    {
                        playableCoordinates.add(getStringCoords(ROW + 1, COL));
                    }
                    if(cells[ROW][COL+1].equals(" "))
                    {
                        playableCoordinates.add(getStringCoords(ROW, COL+1));
                    }
                }
            }
        }

        return playableCoordinates;
    }

    /**
     * Places a single tile on the cell with given coordinates. Prints error messages if placement is invalid.
     * @param row the integer value of the row of given cell.
     * @param col the integer value of the column of given cell.
     * @param tile
     * @return true if given tile was placed successfully.
     */
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

    /**
     * Returns true if at least one cell adjacent to given cell is not blank (hence the word can be placed according to Scrabble rules).
     * @param row the integer value of the row of given cell.
     * @param col the integer value of the column of given cell.
     * @return true if none of the adjacent cells have been played before.
     */
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

    /**
     * Returns list of all cells adjacent to given cell, handling all cell types (corner, border, regular)
     * @param row the integer value of the row of given cell.
     * @param col the integer value of the column of given cell.
     * @return list of adjacent cells.
     */
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

    /**
     * Return's square's coordinates as a string of the row as a digit, and the column as a letter.
     *
     * @param row the integer value of the row of given cell.
     * @param col the integer value of the column of given cell.
     * @return coordinates as a string with digit row and letter column.
     */
    public String getStringCoords(int row, int col) {
        String stringCoordinates = "" + row + Square.columns.get(col);
        return stringCoordinates;
    }

    /**
     * Returns the String content of the cell located above given cell
     *
     * @param row the integer value of the row of given cell.
     * @param col the integer value of the column of given cell.
     * @return string content of cell above.
     */
    private String getTopCellContent(int row, int col) {return cells[row-1][col];}

    /**
     * Returns the String content of the cell located to the right of given cell.
     *
     * @param row the integer value of the row of given cell.
     * @param col the integer value of the column of given cell.
     * @return string content of cell on the right.
     */
    private String getRightCellContent(int row, int col) {return cells[row][col+1];}

    /**
     * Returns the String content of the cell located underneath the given cell.
     *
     * @param row the integer value of the row of given cell.
     * @param col the integer value of the column of given cell.
     * @return string content of cell underneath.
     */
    private String getBottomCellContent(int row, int col) {return cells[row+1][col];}

    /**
     * Returns the String content of the cell located to the left of given cell.
     *
     * @param row the integer value of the row of given cell.
     * @param col the integer value of the column of given cell.
     * @return string content of cell on the left.
     */
    private String getLeftCellContent(int row, int col) {return cells[row][col-1];}

    /**
     * Returns a list of contents of all cells adjacent to given cell.
     * Handles ell type: regular (not in coreners or on the borders)
     *
     * @param row the integer value of the row of given cell.
     * @param col the integer value of the column of given cell.
     * @return list of contents of adjacent cells.
     */
    public ArrayList<String> getAdjacentCells(int row, int col) {
        ArrayList<String> adjacentCells = new ArrayList<>();
        adjacentCells.add(getTopCellContent(row, col));
        adjacentCells.add(getRightCellContent(row, col));
        adjacentCells.add(getBottomCellContent(row, col));
        adjacentCells.add(getLeftCellContent(row, col));
        return adjacentCells;
    }

    /**
     * Returns a list of contents of all cells adjacent to given cell.
     * Handles ell type: border (cells on the outer borders of the board)
     *
     * @param row the integer value of the row of given cell.
     * @param col the integer value of the column of given cell.
     * @return list of contents of adjacent cells.
     */
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

    /**
     * Returns a list of contents of all cells adjacent to given cell.
     * Handles ell type: corner (cells on the 4 corners of the board).
     *
     * @param row the integer value of the row of given cell.
     * @param col the integer value of the column of given cell.
     * @return list of contents of adjacent cells.
     */
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

    /**
     * Returns true if this condition would be met after word placement:
     * At least one tile is be adjacent to an existing tile.
     *
     * @param row the integer value of the row of given cell.
     * @param col the integer value of the column of given cell.
     * @return true if at least one adjacent cell is not blank.
     */
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

    /**
     * Places as many single letters on board as are valid. Returns true if placement is valid.
     * Returns false if placement was unsuccessful for that cell.
     *
     * @param row the integer value of the row of given cell.
     * @param col the integer value of the column of given cell.
     * @param tiles list of tiles of the word to be placed.
     * @param direction the direction of the word to be placed.
     * @return true if placement of given word would be valid if it were to be placed.
     */
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
            invalidPlacement = true;
            return false;
        }
        else if ((adjacentConditionMet) && tilePlacedCount == tiles.size()){
            System.out.println("Placement is valid.");
            getWordScore(row, col, tiles, direction);
            return true;
        }
        return false;
    }

    /**
     * Concatenates and returns String combination of letters from given tiles.
     *
     * @param tiles
     * @return string concatenation of letters on given tiles.
     */
    public String getWordFromTiles(ArrayList<Tile> tiles) {
        String word = "";
        ArrayList<Tile> tilesList = tiles;
        for (Tile t: tilesList) {
            word += t.getLetter();
        }
        return word;
    }

    /**
     * Returns the String letter placed on given coordinates.
     */
    public String getLetterAt(int row, int col) {
        return cells[row][col];
    }

    /**
     * Returns list of all String words on given row of board.
     *
     * @param row the integer value of the row of given cell.
     * @return list of all words on given row of board.
     */
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

    /**
     * Returns list of all String words on given column of board.
     *
     * @param col the integer value of the column of given cell.
     * @return list of all words on given column of board.
     */
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

    /**
     * Returns the score for given word.
     *
     * @param row the integer value of the row of given cell.
     * @param col the integer value of the column of given cell.
     * @param tilesList list of tiles of the word to be scored.
     * @param direction placement direction of the word to be scored.
     * @return sum score of values of tiles.
     */
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
        playedWord = wordToScore;
        System.out.println("score for word " + wordToScore + ": " + wordScore);
        return wordScore;
    }

    /**
     * Calculates score of the given word.
     *
     * @param wordToScore the String word to be scored.
     * @return score of word.
     */
    public static int calculateWordScore(String wordToScore) {
        int letterScore = 0;
        int wordScore = 0;

        for (int i = 0; i < wordToScore.length(); i++) {
            letterScore = Bag.getLetterValue("" + wordToScore.charAt(i));
            wordScore += letterScore;
            //System.out.println("current character of word " + wordToScore + ": " + wordToScore.charAt(i));
        }
        return wordScore;
    }

    /**
     * Returns the score for given word multiplied by corresponding premium scores' multipliers.
     *
     * @param row the integer value of the row of given cell.
     * @param col the integer value of the column of given cell.
     * @param tilesList list of tiles of the word to be scored.
     * @param direction placement direction of the word to be scored.
     * @return the multiplied score of the word.
     */
    public int calculatePremiumScore(int row, int col, ArrayList<Tile> tilesList, Direction direction) {
        int premiumScore = 0;
        return premiumScore;
    }

    /**
     * Returns ArrayList of all horizontal words placed on board.
     *
     * @return list of horizontal words.
     */
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
        //System.out.println("number of words " + horizontalWords.size());
        return horizontalWords;
    }

    /**
     * Returns a list of all vertical words placed on board.
     *
     * @return list of vertical words.
     */
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
                        //System.out.println("current word is: " + currentWord);
                        currentWord = ""; // set currentWord to empty string so it can store the next word in row
                    }
                    // handle cells on the bottom border of board (row == 15)
                    if (!cellIsBlank(row + 1, col) && (row == cells.length - 2)) {
                        currentWord += cells[row + 1][col];
                        VerticalWords.add(currentWord);
                        //System.out.println("current word is: " + currentWord);
                        currentWord = ""; // set currentWord to empty string so it can store the next word in row
                    }
                }
            }
        }
        //System.out.println("number of words " + VerticalWords.size());
        return VerticalWords;
    }

    public String getLetterAtSquare(int row, int col)
    {
        return this.cells[row][col];
    }

}
