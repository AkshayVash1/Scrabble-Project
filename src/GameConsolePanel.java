import javax.swing.*;
import java.awt.*;

public class GameConsolePanel extends JFrame implements ScrabbleView {

    private JTextArea console;
    private Game game;

    public GameConsolePanel(Game game) {
        super("Game Console");

        this.game = game;
        this.game.addScrabbleView(this);

        initializePanel();
    }

    private void initializePanel() {
        this.setSize(400, 600);
        this.setBackground(Color.gray);
        this.setVisible(true);

        console = new JTextArea();
        console.setLineWrap(true);
        console.setWrapStyleWord(true);
        console.setAutoscrolls(true);
    }

    @Override
    public void update(Player currentPlayer) {

    }
}
