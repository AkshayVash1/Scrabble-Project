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
        DragGestureController dg = new DragGestureController(this.player);

        for (Tile t : this.player.getHand().getHand())
        {
            JPanel p = new JPanel();
            p.setBackground(new Color(Square.Multiplier.NONE.getRGB_color()));
            JLabel l = new JLabel(t.toString());
            DragSource ds = new DragSource();
            ds.createDefaultDragGestureRecognizer(l, 1, dg::dragGestureRecognized);
            l.setSize(10,10);
            l.setFont(new Font(Font.MONOSPACED, Font.BOLD,18));
            p.add(l);
            this.add(p);
        }
    }

    @Override
    public void update(ScrabbleEvent e) {
        this.player = e.getCurrentPlayer();
        refreshHand();
    }

}
