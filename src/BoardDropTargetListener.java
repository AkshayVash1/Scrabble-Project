import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DropTarget;
import java.awt.dnd.DropTargetAdapter;
import java.awt.dnd.DropTargetDropEvent;
import java.io.IOException;

public class BoardDropTargetListener extends DropTargetAdapter {

    private final DropTarget dropTarget;
    private final JLabel label;
    private final String boardCoordinates;
    private Game game;

    public BoardDropTargetListener(JLabel label, String boardCoordinates, Game game ) {
        this.label = label;


        this.boardCoordinates = boardCoordinates;
        this.game = game;

        dropTarget = new DropTarget(label, DnDConstants.ACTION_COPY,
                this, true, null);
    }

    @Override
    public void drop(DropTargetDropEvent dropTargetDropEvent) {
        try {
            var tr = dropTargetDropEvent.getTransferable();
            var tile = (Tile) tr.getTransferData(TransferableTile.tileFlavor);

            if (dropTargetDropEvent.isDataFlavorSupported(TransferableTile.tileFlavor)) {

                dropTargetDropEvent.acceptDrop(DnDConstants.ACTION_COPY);
                this.label.setText(tile.getLetter());
                this.label.setForeground(Color.black);

                if (this.game.getFirstPlayInTurn() == true)
                {
                    this.game.setStartingCoordinates(this.boardCoordinates);
                }

                System.out.println("INITIAL " +this.game.getStartingCoordinates().charAt(1) + " " +  boardCoordinates.charAt(1));

                if ((this.game.getFirstPlayInTurn() == false) &&
                        (this.game.getStartingCoordinates().charAt((this.game.getStartingCoordinates().length()
                                == 3) ? 2 : 1) == boardCoordinates.charAt((boardCoordinates.length() == 3) ? 2: 1)))
                {
                    this.game.changeStartingCoordinatesToVertical();
                    System.out.println(game.getStartingCoordinates());
                }

                System.out.println(boardCoordinates);

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