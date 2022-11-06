/*&
import javax.swing.*;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DropTarget;
import java.awt.dnd.DropTargetAdapter;
import java.awt.dnd.DropTargetDropEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.FileNotFoundException;

//Possible to be moved to controller
public class MouseController extends MouseAdapter {

    private Game game;

    public MouseController(Game game) {
        this.game = game;
    }

    public void mousePressed(MouseEvent e) {
        JComponent c = (JComponent) e.getSource(); //c stores the component source.
        TransferHandler handler = c.getTransferHandler(); //Get the transfer handler of the component
        handler.exportAsDrag(c, e, TransferHandler.COPY);
    }
} */