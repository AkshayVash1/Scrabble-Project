/**
 * Class to detect when a drag gesture is performed. Once detected, the Tileflavour is transferred alongside the
 * drag path and to the drop target.
 *
 * @Author Mohamed Kaddour
 * @Date 2022.11.13
 * */

import javax.swing.*;
import java.awt.*;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DragGestureEvent;
import java.awt.dnd.DragGestureListener;
import java.awt.dnd.DragSource;

public class DragGestureController {

    private Player player;

    /**
     * Constructor to initialize player
     *
     * @param player Player
     * */
    public DragGestureController(Player player)
    {
        this.player = player;
    }

    /**
     * Private helper method to retrieve the tile within the player's hand based on the letter
     * @param s String
     * @return Tile
     * */
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

    /**
     * Event listener for when the drag is detected with the cursor. Once this is detected, the text is transfered
     * over to the board
     * @param event DragGestureEvent
     * */
    public void dragGestureRecognized(DragGestureEvent event) {

        Cursor cursor = Cursor.getDefaultCursor();

        if (event.getDragAction() == DnDConstants.ACTION_COPY) {
            cursor = DragSource.DefaultCopyDrop;
        }

        JLabel src = (JLabel) event.getComponent();
        event.startDrag(cursor, new TransferableTile((retrieveTile(src.getText()))));
    }
}
