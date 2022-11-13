/**
 * The main frame of Scrabble's graphical user interface.
 * The frame contains all panels that reflect the state of the game.
 *
 * @author Mahtab Ameli
 * @date 2022-11-05
 * @version 0.0
 */
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.io.FileNotFoundException;
import java.net.NoRouteToHostException;

public class ScrabbleFrame extends JFrame {

    /**
     * Dimensions of the frame.
     */
    private final int frameWidth = 600;
    private final int frameHeight = 1000;


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
     * Initializes this frame.
     */
    private void initializeFrame() {
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setSize(frameWidth, frameHeight);
        //this.setLayout(new GridLayout(2,2)); //todo revisit later
        this.setLayout(new BorderLayout()); //todo revisit later
        this.setVisible(true);
        this.setResizable(false);
        //this.revalidate();
    }


    /**
     * Creates and adds to this frame all component panels of the GUI.
     * @param game of type Game, passes the model to all the respective view Panels
     */
    private void initializePanels(Game game) {

        BoardPanel boardPanel = new BoardPanel(game);
        this.add(boardPanel, BorderLayout.NORTH);

        HandPanel handPanel = new HandPanel(game);
        GameCommandPanel gameCommandPanel = new GameCommandPanel(game);

        JPanel southPanel = new JPanel();
        southPanel.setLayout(new BorderLayout());
        southPanel.add(handPanel, BorderLayout.NORTH);
        southPanel.add(gameCommandPanel, BorderLayout.SOUTH);
        this.add(southPanel, BorderLayout.SOUTH);

        this.revalidate();
    }

    /**Main */
    public static void main(String[] args) throws FileNotFoundException {
        ScrabbleFrame gameFrame = new ScrabbleFrame();
    }
}
