import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DragGestureEvent;
import java.awt.dnd.DragGestureListener;
import java.awt.dnd.DragSource;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.ArrayList;

public class HandPanel extends JPanel implements ScrabbleView {

    private Player player;
    private Game game;

    public HandPanel(Game game)
    {
        this.player = new Player(10);
        ArrayList <Tile> placeHolder = new ArrayList<>();
        placeHolder.add(new Tile("X", 1));
        placeHolder.add(new Tile("X", 1));
        placeHolder.add(new Tile("X", 1));
        placeHolder.add(new Tile("X", 1));
        placeHolder.add(new Tile("X", 1));
        placeHolder.add(new Tile("X", 1));
        placeHolder.add(new Tile("X", 1));
        player.getHand().addTiles(placeHolder, false);

        this.setPreferredSize(new Dimension(400,30));
        this.game = game;
        refreshHand();
        game.addScrabbleView(this);
    }

    private void refreshHand()
    {
        //MouseController controller = new MouseController(this.game);
        this.removeAll();

        for (Tile t : this.player.getHand().getHand())
        {
            JPanel p = new JPanel();
            p.setBackground(new Color(Square.Multiplier.NONE.getRGB_color()));
            JLabel l = new JLabel(t.toString());
            DragSource ds = new DragSource();
            ds.createDefaultDragGestureRecognizer(l, 1, this::dragGestureRecognized);
            l.setSize(10,10);
            l.setFont(new Font(Font.MONOSPACED, Font.BOLD,18));
            p.add(l);
            this.add(p);
        }
    }

    @Override
    public void update(Player currentPlayer, Board board) {
        this.player = currentPlayer;
        refreshHand();
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

        var cursor = Cursor.getDefaultCursor();

        if (event.getDragAction() == DnDConstants.ACTION_COPY) {

            cursor = DragSource.DefaultCopyDrop;
        }

        JLabel src = (JLabel) event.getComponent();

        event.startDrag(cursor, new TransferableTile((retrieveTile(src.getText()))));
    }
}
