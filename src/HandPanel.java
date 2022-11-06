import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class HandPanel extends JPanel {

    private Player player;
    private Game game;

    public HandPanel(Game game)
    {
        this.game = game;
        player = new Player(1);
        Tile t1 = new Tile("A", 1);
        Tile t2 = new Tile("B", 1);
        Tile t3 = new Tile("C", 1);
        Tile t4 = new Tile("D", 1);
        Tile t5 = new Tile("E", 1);
        Tile t6 = new Tile("F", 1);
        Tile t7 = new Tile("G", 1);
        ArrayList<Tile> t = new ArrayList<>();
        t.add(t1);
        t.add(t2);
        t.add(t3);
        t.add(t4);
        t.add(t5);
        t.add(t6);
        t.add(t7);

        this.player.initializePlayerHand(t);
        initializeHand();
    }

    private void initializeHand ()
    {
        DragMouseAdapter listener = new DragMouseAdapter();

        for (Tile t : this.player.getHand().getHand())
        {
            JPanel p = new JPanel();
            p.setBackground(new Color(Square.Multiplier.NONE.getRGB_color()));
            JLabel l = new JLabel(t.toString());
            l.setSize(10,10);
            l.setFont(new Font(Font.MONOSPACED, Font.BOLD,18));
            l.addMouseListener(listener);
            l.setTransferHandler(new TransferHandler("text"));
            p.add(l);
            this.add(p);
        }
    }

    //Possible to be moved to controller
    private class DragMouseAdapter extends MouseAdapter {

        public void mousePressed(MouseEvent e) {

            var c = (JComponent) e.getSource();
            var handler = c.getTransferHandler();
            handler.exportAsDrag(c, e, TransferHandler.COPY);
        }
    }
}
