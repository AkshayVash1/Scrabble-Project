import javax.swing.*;
import java.awt.*;

public class GameConsolePanel extends JPanel implements ScrabbleView {

    private JTextArea console;
    private Game game;
    private final static String NEWLINE = "\n";
    private final static String NEWLINE2 = "\n\n";
    private int textCount;
    private boolean firstUpdate;

    public GameConsolePanel(Game game) {
        super();

        this.game = game;
        this.game.addScrabbleView(this);

        initializePanel();
        firstUpdate = true;
        this.add(console);
    }

    private void initializePanel() {
        console = new JTextArea(31, 20);

        JScrollPane scrollPane = new JScrollPane(console);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);

        console.setEditable(false);
        console.setLineWrap(true);
        console.setWrapStyleWord(true);
        console.setSize(new Dimension(400, 500));
        console.setBackground(Color.lightGray);
        console.setText("Welcome to Scrabble! Scrabble is a word game for 2 to 4 Players." + NEWLINE +
                "The goal of the game is to accumulate more than or equal to 50 points."  + NEWLINE2 +
                "Press help for details." + NEWLINE2);

        this.setPreferredSize(new Dimension(400, 500));
        this.setVisible(true);
    }

    public void appendHelp()
    {
        console.append("Scrabble is a word game with 2-4 players. The goal of the game is to accumulate more than or equal to 50 points \n" +
                "Each player starts with 7 tiles in their hand. All players have the 5 different actions \n" +
                "they can perform: Play, Exchange, Pass, Shuffle \n" +
                "To start, drag and drop the tiles you want to play to the board IN THE CORRECT WORD ORDER. Once all the tiles\n" +
                "are placed, you can press play and if the play and word is valid, the next player's turn will happen\n" +
                "You can also shuffle, pass or press exchange and select which tiles you would like to exchange\n\n");

        textCount += 8;
    }

    @Override
    public void update(Player currentPlayer, Board board, boolean status) {
        if (textCount > 12) {
            console.setText("");
            textCount = 0;
        }
        else {
            textCount++;
        }

        console.append("Player " + (currentPlayer.getPlayerNumber()+1) + "'s turn..." +  "Points:" +
                currentPlayer.getPoints() + NEWLINE2);

        if (status == true)
        {
            console.append("GAME IS FINISHED! PLAYER " + (currentPlayer.getPlayerNumber()+1) + " WON!" + NEWLINE2 +
            "Please close game and play again");
        }
    }
}
