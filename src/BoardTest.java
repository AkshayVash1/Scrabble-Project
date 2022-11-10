/**
 * Unit test for the class Board.
 *
 * @author Mahtab Ameli
 * @date 2022-11-09
 * @version 0.0
 */

import junit.framework.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;

import static org.junit.Assert.*;


public class BoardTest {

    private Board board;
    private String[][] testCells;
    private int cellsLength;
    private HashMap<String, Square> testSquares;
    private HashMap<String, Tile> testTiles;


    @Before
    public void setUp() throws Exception {
        board = new Board();
        testCells = board.getCells();
        testSquares = board.getSquares();
        testTiles = board.getTiles();
        cellsLength = board.getCells().length;
    }

/*    @After
    public void tearDown() throws Exception {
    }*/

    /**
     * Test Case: new instance of Board is created and all cells are "blank".
     * Tests whether String contents of the newly-created board's cells are correctly equal to a single space " ".
     * Methods covered: initializeBoard(), getCells()
     *
     */
    @Test
    public void testCellsInitiallyBlank() {
        int nonBlankCount = 0;
        for (int row = 0; row < cellsLength; row++) {
            for (int col = 0; col < cellsLength; col++) {
                if (!testCells[row][col].equals(" ")) {
                    nonBlankCount++;
                }
            }
        }
        assertEquals(0, nonBlankCount);
    }


    /**
     * TEST CASE: new instance of Board has been created and it should have 61 premium squares + 195 non-premium squares.
     * Tests whether the new board correctly contains all 61 premium squares of the correct type, and all 164 non-premium squares:
     * Expected count of each premium square type: 8 Triple Word, 17 Double word, 12 Triple Letter, 12 Double Letter
     * Methods covered: initializeBoard(), getCells(), getSquares()
     *
     */
    @Test
    public void testPremiumSquaresAdded() {
        //int DL_count = 0; int DW_count = 0; int TL_count = 0; int TW_count = 0;
        int DL_count = 0; int DW_count = 0; int TL_count = 0; int TW_count = 0; int nonPremium_count = 0;
        int sumSquares;
        String[] keys = testSquares.keySet().toArray(new String[0]);

        for (int i = 0; i < testSquares.keySet().size(); i++) {
            Square s = testSquares.get(keys[i]);
            Square.Multiplier m = s.getMultiplier();
            // count double letter squares
            if (m.equals(Square.Multiplier.DL)) {
                DL_count++;
            }
            // count double word squares
            if (m.equals(Square.Multiplier.DW)) {
                DW_count++;
            }
            // count triple letter squares
            if (m.equals(Square.Multiplier.TL)) {
                TL_count++;
            }
            // count triple word squares
            if (m.equals(Square.Multiplier.TW)) {
                TW_count++;
            }
            // count non-premium squares
            if (m.equals(Square.Multiplier.NONE)) {
                nonPremium_count++;
            }
        }
        sumSquares = DL_count + DW_count + TL_count + TW_count + nonPremium_count;
        assertEquals(256, sumSquares);
    }



    /**
     * Test Case: The board has been created but no player has started playing yet.
     * Tests whether the isFirstPlay Boolean is correctly set to true at the start of the game.
     * Methods covered: isFirstPlay()
     *
     */
    @Test
    public void testFirstPlayTrue() {
        assertEquals(true, board.isFirstPlay());
    }


    @Test
    public void getTiles() {
    }

    @Test
    public void setTiles() {
    }

    @Test
    public void getSquares() {
    }

    @Test
    public void isFirstPlay() {
    }

    @Test
    public void setFirstPlay() {
    }

    @Test
    public void setSquares() {
    }

    @Test
    public void printBoard() {
    }

    @Test
    public void placeTileAt() {
    }

    @Test
    public void allAdjacentsBlank() {
    }

    @Test
    public void getAllAdjacentCells() {
    }

    @Test
    public void getStringCoords() {
    }

    @Test
    public void getAdjacentCells() {
    }

    @Test
    public void getBordersAdjacentCells() {
    }

    @Test
    public void getCornersAdjacentCells() {
    }

    @Test
    public void adjacentConditionMet() {
    }

    @Test
    public void placeWord() {
    }

    @Test
    public void getWordFromTiles() {
    }

    @Test
    public void getWordsOnRow() {

    }

    @Test
    public void getWordsOnCol() {
    }

    @Test
    public void getWordScore() {
    }

    @Test
    public void getHorizontalWords() {
    }

    @Test
    public void getVerticalWords() {
    }
}