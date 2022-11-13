import java.io.FileNotFoundException;
import java.util.ArrayList;

import static org.junit.Assert.*;

public class PlayMoveTest {

    @org.junit.Test
    public void getPlayedWordScore() {
        Board board = new Board();
        ArrayList<Tile> tilesToPlace = new ArrayList<>();
        tilesToPlace.add(new Tile("A", 1));
        tilesToPlace.add(new Tile("N", 1));
        tilesToPlace.add(new Tile("D", 2));

        PlayMove move = new PlayMove("8H", tilesToPlace, board, true);
        assert(move.getPlayedWordScore() == 4);
    }

    @org.junit.Test
    public void placeTile() {

    }

    @org.junit.Test
    public void getUpdatedBoard() {
    }

    @org.junit.Test
    public void checkWord() throws FileNotFoundException {
        Board board = new Board();
        ArrayList<Tile> tilesToPlace = new ArrayList<>();
        tilesToPlace.add(new Tile("A", 1));
        tilesToPlace.add(new Tile("N", 1));
        tilesToPlace.add(new Tile("D", 2));

        PlayMove move = new PlayMove("8H", tilesToPlace, board, true);
        move.placeTile();
        assert(move.checkWord() == true);
    }
}