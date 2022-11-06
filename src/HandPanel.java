import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class HandPanel extends JPanel implements ScrabbleView {

    private Player player;
    private Game game;

    public HandPanel(Game game)
    {
        this.player = new Player(10);
        refreshHand();
        game.addScrabbleView(this);
    }

    private void refreshHand()
    {
        DragMouseAdapter listener = new DragMouseAdapter();
        this.removeAll();

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

    @Override
    public void update(Player currentPlayer) {
        this.player = currentPlayer;
        refreshHand();
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
