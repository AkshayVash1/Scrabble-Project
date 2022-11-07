/**
 * The main frame of Scrabble's graphical user interface.
 * The frame contains all panels that reflect the state of the game.
 *
 * @author Mahtab Ameli
 * @date 2022-11-05
 * @version 0.0
 */
import javax.swing.*;
import java.awt.*;
import java.io.FileNotFoundException;

public class ScrabbleFrame extends JFrame {

    /**
     * Dimensions of the frame.
     */
    private final int frameWidth = 600;
    private final int frameHeight = 600;


    /**
     * Constructor for the class.
     */
    public ScrabbleFrame() throws FileNotFoundException{
        super("Welcome to Scrabble!");
        this.initializeFrame();
        Game game = new Game();
        this.initializePanels(game);
    }


    /**
     * Creates and adds to this frame all component panels of the GUI.
     */
    private void initializePanels(Game game) {
        // adding the BoardPanel
        BoardPanel boardPanel = new BoardPanel(game);
        this.add(boardPanel);
        HandPanel handPanel = new HandPanel(game);
        this.add(handPanel);
        GameCommandPanel gameCommandPanel = new GameCommandPanel(game);
        this.add(gameCommandPanel);
        //PlayerDisplayPanel playerDisplayPanel = new PlayerDisplayPanel(game);
        //this.add(playerDisplayPanel);

        // todo add other panels

        this.revalidate();
    }


    /**
     * Initializes this frame.
     */
    private void initializeFrame() {
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setSize(frameWidth, frameHeight);
        this.setLayout(new GridLayout(2, 2)); //todo revisit later
        this.setVisible(true);
        this.revalidate();
    }


    public static void main(String[] args) throws FileNotFoundException {
        ScrabbleFrame gameFrame = new ScrabbleFrame();
    }
}
