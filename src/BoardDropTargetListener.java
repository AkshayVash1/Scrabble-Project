import javax.swing.*;
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

    public BoardDropTargetListener(JLabel label, String boardCoordinates ) {
        this.label = label;
        this.boardCoordinates = boardCoordinates;

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