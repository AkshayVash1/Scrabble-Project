import javax.swing.*;
import java.awt.*;

public class GameConsolePanel extends JPanel implements ScrabbleView {

    private JTextArea console;
    private Game game;
    private final static String NEWLINE = "\n";
    private final static String NEWLINE2 = "\n\n";

    public GameConsolePanel(Game game) {
        super();

        this.game = game;
        this.game.addScrabbleView(this);

        initializePanel();

        this.add(console);
    }

    private void initializePanel() {
        console = new JTextArea(31, 30);

        JScrollPane scrollPane = new JScrollPane(console);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);

        console.setEditable(false);
        console.setLineWrap(true);
        console.setWrapStyleWord(true);
        console.setSize(new Dimension(400, 500));
        console.setBackground(Color.lightGray);
        console.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        this.setPreferredSize(new Dimension(400, 500));
        this.setVisible(true);
        this.appendHelp();
    }

    public void appendHelp() {
        console.setText("Welcome to Scrabble! Scrabble is a word game for 2 to 4 Players." + NEWLINE +
                "The goal of the game is to accumulate more than or equal to 50 points"  + NEWLINE2);
    }

    @Override
    public void update(Player currentPlayer, Board board) {
        console.append("Player " + currentPlayer.getPlayerNumber() + "'s turn..." + NEWLINE2);

        String action = game.getCommand();

        switch(action) {
            case "play":

                break;
            default:
                break;

        }
    }
}
