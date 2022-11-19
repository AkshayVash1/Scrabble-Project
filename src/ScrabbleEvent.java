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
