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
                    System.out.println("FIRST");
                    this.game.setStartingCoordinates(this.boardCoordinates);
                }

                this.game.addToRemoveTilesFromHand(tile.getLetter().charAt(0));

                System.out.println(boardCoordinates);
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