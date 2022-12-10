/**
 * Describes an Event which is the result of a users action. This event is used in the functional interface to update
 * all views of the model.
 *
 * @Author Mohamed Kaddour
 * @Date 2022-11-13
 * @Version 1.0
 */

import java.io.Serializable;


public class ScrabbleEvent implements Serializable {

    private boolean gameFinished;
    private Player currentPlayer;
    private Board board;

    /**
     * Constructor to initialize ScrabbleEvent with information that is passed during the event trigger
     * @param player
     * @param board
     * @param gameFinished
     * */
    public ScrabbleEvent(Player player, Board board, boolean gameFinished) {
        this.currentPlayer = player;
        this.board = board;
        this.gameFinished = gameFinished;
    }

    /**
     * Getter for if game is finished
     * @return boolean
     * */
    public boolean isGameFinished() {
        return gameFinished;
    }

    /**
     * Getter for current player
     * @return Player
     * */
    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    /**
     * Getter for current Board state
     * @return Board
     * */
    public Board getBoard() {
        return board;
    }
}
