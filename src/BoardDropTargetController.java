/**
 * This class is part of the "Scrabble" application.
 *
 * One of two controllers for the model. This controller is meant to control the state of both the board and
 * the player's hand in regards to the model. It deteects when a user drops a tile onto the board and then performs
 * actions accordingly.
 *
 * Uses the board coordinates as well to determine whether the play is considered to be a vertical play based off
 * of the drop or a horizontal play based off of the drop.
 *
 * @author Mohamed Kaddour
 * @date 2022.11.13
 * @Version 1.0
 */

import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DropTarget;
import java.awt.dnd.DropTargetAdapter;
import java.awt.dnd.DropTargetDropEvent;
import java.io.IOException;

public class BoardDropTargetController extends DropTargetAdapter {

    private final DropTarget dropTarget;
    private final JLabel label;
    private final String boardCoordinates;
    private Game game;

    /**
     * Constructor that takes in the different aspects of the BoardPanel view in order to properly update both
     * the board visually and also the model Board.
     * @param label of type JLabel the label to update the board with.
     * @param boardCoordinates of type String indicating the board coordinates where the tile was dropped
     * @param game of type Game, the model.
     * */
    public BoardDropTargetController(JLabel label, String boardCoordinates, Game game ) {
        this.label = label;
        this.boardCoordinates = boardCoordinates;
        this.game = game;

        dropTarget = new DropTarget(label, DnDConstants.ACTION_COPY,
                this, true, null);
    }

    /**
     * Helper method to determine the size of the coordinates in order to perform an effective exchange.
     * @return char
     * @param boardCoordinates the boardCoordinates to process.
     * */
    private char determineCharacterSwap(String boardCoordinates)
    {
        return boardCoordinates.charAt((boardCoordinates.length() == 3) ? 2 : 1);
    }

    /**
     * The drop listener which process the drop command from the mouse and then performs various actions on the
     * model with it. This includes checking the direction that the player intends to play with.
     *
     * @param dropTargetDropEvent
     * */
    @Override
    public void drop(DropTargetDropEvent dropTargetDropEvent) {
        try {
            Transferable tr = dropTargetDropEvent.getTransferable();
            Tile tile = (Tile) tr.getTransferData(TransferableTile.tileFlavor);

            if (dropTargetDropEvent.isDataFlavorSupported(TransferableTile.tileFlavor)) {

                dropTargetDropEvent.acceptDrop(DnDConstants.ACTION_COPY);
                this.label.setText(tile.getLetter());
                this.label.setForeground(Color.black);

                if (this.game.getFirstPlayInTurn() == true)
                {
                    this.game.setStartingCoordinates(this.boardCoordinates);
                    
                }

                if ((this.game.getFirstPlayInTurn() == false) &&
                        (determineCharacterSwap(this.game.getStartingCoordinates())
                                == determineCharacterSwap(this.boardCoordinates)))
                {
                    this.game.changeStartingCoordinatesToVertical();
                }

                if (tile.getLetter() == "_")
                {
                    tile.setLetter(JOptionPane.showInputDialog(null, "Enter letter"));
                    this.label.setText(tile.getLetter());
                }

                this.game.addToRemoveTilesFromHand(tile.getLetter().charAt(0));

                dropTargetDropEvent.dropComplete(true);
                return;
            }

            dropTargetDropEvent.rejectDrop();
        } catch (Exception e) {
            e.printStackTrace();
            dropTargetDropEvent.rejectDrop();
        }
    }
}