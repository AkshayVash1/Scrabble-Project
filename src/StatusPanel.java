import javax.swing.*;
import java.awt.*;

public class StatusPanel extends JPanel implements ScrabbleView {

    private Game game;

    public StatusPanel(Game game) {
        super();

        this.game = game;
        this.game.addScrabbleView(this);

        initializePanel();

    }

    private void initializePanel() {
        this.setPreferredSize(new Dimension(400, 100));
        this.setBackground(Color.black);
        this.setVisible(true);

    }


    @Override
    public void update(Player currentPlayer, Board board) {

    }
}
