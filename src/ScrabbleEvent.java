/**
 * Describes an Event which is the result of a users action. This event is used in the functional interface to update
 * all views of the model.
 *
 * @Author Mohamed Kaddour
 * @Date 2022-11-13
 * @Version 1.0
 */

import java.util.EventObject;

public class ScrabbleEvent {

    private boolean gameFinished;
    private Player currentPlayer;
    private Board board;

    public ScrabbleEvent(Player player, Board board, boolean gameFinished) {
        this.currentPlayer = player;
        this.board = board;
        this.gameFinished = gameFinished;
    }

    public boolean isGameFinished() {
        return gameFinished;
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public Board getBoard() {
        return board;
    }
}
