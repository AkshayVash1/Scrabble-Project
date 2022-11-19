import javax.swing.*;
import java.awt.*;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DragGestureEvent;
import java.awt.dnd.DragGestureListener;
import java.awt.dnd.DragSource;

public class DragGestureController {

    private Player player;

    public DragGestureController(Player player)
    {
        this.player = player;
    }

    private Tile retrieveTile(String s)
    {
        for (Tile t : this.player.getHand().getHand()) {
            if ( t.getLetter().equals(Character.toString(s.charAt(0))))
            {
                return t;
            }
        }

        return null;
    }

    public void dragGestureRecognized(DragGestureEvent event) {

        Cursor cursor = Cursor.getDefaultCursor();

        if (event.getDragAction() == DnDConstants.ACTION_COPY) {
            cursor = DragSource.DefaultCopyDrop;
        }

        JLabel src = (JLabel) event.getComponent();
        event.startDrag(cursor, new TransferableTile((retrieveTile(src.getText()))));
    }
}
