import javax.swing.*;
import java.awt.*;

public class GameConsolePanel extends JPanel implements ScrabbleView {

    private JTextArea console;
    private Game game;

    public GameConsolePanel(Game game) {
        super();

        this.game = game;
        this.game.addScrabbleView(this);

        initializePanel();
    }

    private void initializePanel() {
        this.setPreferredSize(new Dimension(400, 500));
        this.setBackground(Color.gray);
        this.setVisible(true);

        console = new JTextArea();
        console.setLineWrap(true);
        console.setWrapStyleWord(true);
        console.setAutoscrolls(true);
    }

    public void appendHelp() {
        console.append("Help");
    }

    @Override
    public void update(Player currentPlayer, Board board) {

    }
}
