import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.util.ArrayList;

public class GameTest {

    @Test
    public void testExchangeHandOutputSize() throws FileNotFoundException {
        ArrayList<Tile> playerHand = new ArrayList<>();
        ArrayList<Tile> expectedOutput = new ArrayList<>();

        Game game = new Game();
        game.nextPlayer();
        Player player = game.getCurrentPlayer();
        player.getHand().getHand().clear();
        playerHand.add(new Tile("A", 1));
        playerHand.add(new Tile("B", 1));
        playerHand.add(new Tile("C", 1));
        playerHand.add(new Tile("D", 1));
        playerHand.add(new Tile("E", 1));
        playerHand.add(new Tile("F", 1));
        playerHand.add(new Tile("N", 1));
        player.getHand().addTiles(playerHand, false);

        game.addToExchangeTilesFromHand('A');
        game.addToExchangeTilesFromHand('N');

        game.processCommand(new Command("exchange", "AN", null));

        assert(player.getHand().getHandSize() == 7);
    }

    @Test
    public void testExchangeHandOutputValue() throws FileNotFoundException {
        ArrayList<Tile> playerHand = new ArrayList<>();
        ArrayList<Tile> expectedOutput = new ArrayList<>();

        Game game = new Game();
        game.nextPlayer();
        Player player = game.getCurrentPlayer();
        player.getHand().getHand().clear();
        playerHand.add(new Tile("A", 1));
        playerHand.add(new Tile("B", 1));
        playerHand.add(new Tile("C", 1));
        playerHand.add(new Tile("D", 1));
        playerHand.add(new Tile("E", 1));
        playerHand.add(new Tile("F", 1));
        playerHand.add(new Tile("N", 1));
        player.getHand().addTiles(playerHand, false);

        game.addToExchangeTilesFromHand('F');
        game.addToExchangeTilesFromHand('N');

        game.processCommand(new Command("exchange", "FN", null));

        expectedOutput.add((new Tile("A", 1)));
        expectedOutput.add((new Tile("B", 1)));
        expectedOutput.add((new Tile("C", 1)));
        expectedOutput.add((new Tile("D", 1)));
        expectedOutput.add((new Tile("E", 1)));

        playerHand.remove(playerHand.size()-1);
        playerHand.remove(playerHand.size()-1);

        assert(playerHand.toString().equals(expectedOutput.toString()));
    }

    @Test
    public void testExchangeHandExchangedTiles() throws FileNotFoundException {
        ArrayList<Tile> playerHand = new ArrayList<>();
        ArrayList<String> expectedOutput = new ArrayList<>();

        Game game = new Game();
        game.nextPlayer();
        Player player = game.getCurrentPlayer();
        player.getHand().getHand().clear();
        playerHand.add(new Tile("A", 1));
        playerHand.add(new Tile("B", 1));
        playerHand.add(new Tile("C", 1));
        playerHand.add(new Tile("D", 1));
        playerHand.add(new Tile("E", 1));
        playerHand.add(new Tile("F", 1));
        playerHand.add(new Tile("N", 1));
        player.getHand().addTiles(playerHand, false);

        game.addToExchangeTilesFromHand('N');
        game.addToExchangeTilesFromHand('F');

        game.processCommand(new Command("exchange", "FN", null));

        expectedOutput.add(playerHand.get(playerHand.size()-1).getLetter());
        playerHand.remove(playerHand.size()-1);
        expectedOutput.add(playerHand.get(playerHand.size()-1).getLetter());
        playerHand.remove(playerHand.size()-1);

        assert(game.getExchangeTilesFromHand().toString().equals(expectedOutput.toString()));
    }

    @Test
    public void testPlayMoveAndCheckHand() throws FileNotFoundException {
        ArrayList<Tile> playerHand = new ArrayList<>();
        ArrayList<Tile> expectedOutput = new ArrayList<>();

        Game game = new Game();
        game.nextPlayer();
        Player player = game.getCurrentPlayer();
        player.getHand().getHand().clear();
        playerHand.add(new Tile("A", 1));
        playerHand.add(new Tile("B", 1));
        playerHand.add(new Tile("C", 1));
        playerHand.add(new Tile("D", 1));
        playerHand.add(new Tile("E", 1));
        playerHand.add(new Tile("O", 1));
        playerHand.add(new Tile("R", 1));
        player.getHand().addTiles(playerHand, false);

        game.addToRemoveTilesFromHand('O');
        game.addToRemoveTilesFromHand('R');

        game.processCommand(new Command("play", "OR", "8H"));

        expectedOutput.add((new Tile("A", 1)));
        expectedOutput.add((new Tile("B", 1)));
        expectedOutput.add((new Tile("C", 1)));
        expectedOutput.add((new Tile("D", 1)));
        expectedOutput.add((new Tile("E", 1)));

        playerHand.remove(playerHand.size()-1);
        playerHand.remove(playerHand.size()-1);

        assert(playerHand.toString().equals(expectedOutput.toString()));
    }

    @Test
    public void testPlayMoveValidPlacement() throws FileNotFoundException {
        ArrayList<Tile> playerHand = new ArrayList<>();
        boolean exp;

        Game game = new Game();
        game.nextPlayer();
        Player player = game.getCurrentPlayer();
        player.getHand().getHand().clear();
        playerHand.add(new Tile("A", 1));
        playerHand.add(new Tile("B", 1));
        playerHand.add(new Tile("C", 1));
        playerHand.add(new Tile("D", 1));
        playerHand.add(new Tile("E", 1));
        playerHand.add(new Tile("O", 1));
        playerHand.add(new Tile("R", 1));
        player.getHand().addTiles(playerHand, false);

        game.addToRemoveTilesFromHand('O');
        game.addToRemoveTilesFromHand('R');

        exp = game.processCommand(new Command("play", "OR", "8H"));

        assert(exp);
    }

    @Test
    public void testPlayMoveInvalidPlacement() throws FileNotFoundException {
        ArrayList<Tile> playerHand = new ArrayList<>();
        boolean exp;

        Game game = new Game();
        game.nextPlayer();
        Player player = game.getCurrentPlayer();
        player.getHand().getHand().clear();
        playerHand.add(new Tile("A", 1));
        playerHand.add(new Tile("B", 1));
        playerHand.add(new Tile("C", 1));
        playerHand.add(new Tile("D", 1));
        playerHand.add(new Tile("E", 1));
        playerHand.add(new Tile("O", 1));
        playerHand.add(new Tile("R", 1));
        player.getHand().addTiles(playerHand, false);

        game.addToRemoveTilesFromHand('O');
        game.addToRemoveTilesFromHand('R');

        exp = game.processCommand(new Command("play", "OR", "20H"));

        assert(!exp);
    }

    @Test
    public void testPlayMoveValidWord() throws FileNotFoundException {
        ArrayList<Tile> playerHand = new ArrayList<>();
        boolean exp;

        Game game = new Game();
        game.nextPlayer();
        Player player = game.getCurrentPlayer();
        player.getHand().getHand().clear();
        playerHand.add(new Tile("A", 1));
        playerHand.add(new Tile("B", 1));
        playerHand.add(new Tile("C", 1));
        playerHand.add(new Tile("D", 1));
        playerHand.add(new Tile("E", 1));
        playerHand.add(new Tile("O", 1));
        playerHand.add(new Tile("R", 1));
        player.getHand().addTiles(playerHand, false);

        game.addToRemoveTilesFromHand('O');
        game.addToRemoveTilesFromHand('R');

        exp = game.processCommand(new Command("play", "OR", "8H"));

        assert(exp);
    }

    @Test
    public void testPlayMoveInvalidWord() throws FileNotFoundException {
        ArrayList<Tile> playerHand = new ArrayList<>();
        boolean exp;

        Game game = new Game();
        game.nextPlayer();
        Player player = game.getCurrentPlayer();
        player.getHand().getHand().clear();
        playerHand.add(new Tile("A", 1));
        playerHand.add(new Tile("B", 1));
        playerHand.add(new Tile("C", 1));
        playerHand.add(new Tile("D", 1));
        playerHand.add(new Tile("E", 1));
        playerHand.add(new Tile("O", 1));
        playerHand.add(new Tile("R", 1));
        player.getHand().addTiles(playerHand, false);

        game.addToRemoveTilesFromHand('E');
        game.addToRemoveTilesFromHand('O');

        exp = game.processCommand(new Command("play", "OR", "8H"));

        assert(!exp);
    }

    @Test
    public void testPlayMoveLettersNotInHand() throws FileNotFoundException {
        ArrayList<Tile> playerHand = new ArrayList<>();
        boolean exp;

        Game game = new Game();
        game.nextPlayer();
        Player player = game.getCurrentPlayer();
        player.getHand().getHand().clear();
        playerHand.add(new Tile("A", 1));
        playerHand.add(new Tile("B", 1));
        playerHand.add(new Tile("C", 1));
        playerHand.add(new Tile("D", 1));
        playerHand.add(new Tile("E", 1));
        playerHand.add(new Tile("O", 1));
        playerHand.add(new Tile("R", 1));
        player.getHand().addTiles(playerHand, false);

        game.addToRemoveTilesFromHand('T');
        game.addToRemoveTilesFromHand('A');
        game.addToRemoveTilesFromHand('R');

        exp = game.processCommand(new Command("play", "OR", "8H"));

        assert(!exp);
    }

}