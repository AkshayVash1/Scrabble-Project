/**
 * This class is part of the "Scrabble" application.
 *
 * GameTest is primarily used to test particular logical streams of the model using JUnit5. 12 different play
 * cases with calling processCommand are called in order to ensure all possible error paths and playable paths are
 * covered. Point counting is also taken into account.
 *
 * @author Mohamed Kaddour
 * @date 2022.11.13
 */

import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.util.ArrayList;

public class GameTest {

    @Test
    public void testExchangeHandOutputSize() throws FileNotFoundException {
        ArrayList<Tile> playerHand = new ArrayList<>();
        ArrayList<Tile> expectedOutput = new ArrayList<>();

        Game game = new Game();
        game.createPlayers("1");
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
        game.createPlayers("1");
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
        game.createPlayers("1");
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
        game.createPlayers("1");
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
        game.createPlayers("1");
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
        game.createPlayers("1");
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
        game.createPlayers("1");
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
        game.createPlayers("1");
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

        //Not a valid word
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
        game.createPlayers("1");
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

    @Test
    public void testPlayMoveGetPlayedPoints() throws FileNotFoundException {
        ArrayList<Tile> playerHand = new ArrayList<>();

        Game game = new Game();
        game.createPlayers("1");
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

        game.addToRemoveTilesFromHand('D');
        game.addToRemoveTilesFromHand('O');

        game.processCommand(new Command("play", "DO", "8H"));

        //D has two points and O has one point
        assert(player.getPoints() == 3);
    }

    @Test
    public void testPlayMoveGetPlayedPointsAfterTwoPlays() throws FileNotFoundException {
        ArrayList<Tile> playerHand = new ArrayList<>();

        Game game = new Game();
        game.createPlayers("1");
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

        game.addToRemoveTilesFromHand('D');
        game.addToRemoveTilesFromHand('O');

        game.processCommand(new Command("play", "DO", "8H"));
        game.clearRemoveTilesFromHand();

        game.addToRemoveTilesFromHand('A');
        game.addToRemoveTilesFromHand('R');
        game.addToRemoveTilesFromHand('E');

        game.processCommand(new Command("play", "ARE", "H9"));

        //D has two points and O has one point to make 3 points
        //D has two points, A has one point, E has one point, R has one point to make 5 points
        //Total 8 points
        assert(player.getPoints() == 8);
    }

    @Test
    public void testPlayMoveOnAlreadyPlacedTilePosition() throws FileNotFoundException {
        ArrayList<Tile> playerHand = new ArrayList<>();
        boolean exp;

        Game game = new Game();
        game.createPlayers("1");
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

        game.addToRemoveTilesFromHand('D');
        game.addToRemoveTilesFromHand('O');

        game.processCommand(new Command("play", "OR", "8H"));

        game.clearRemoveTilesFromHand();

        game.addToRemoveTilesFromHand('A');
        game.addToRemoveTilesFromHand('R');
        game.addToRemoveTilesFromHand('E');

        exp = game.processCommand(new Command("play", "ARE", "H8"));

        assert(!exp);
    }

}