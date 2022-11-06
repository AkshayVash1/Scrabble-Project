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
     * FrameSize enum contains constant values for the width and height of the frame.
     */
    public enum FrameSize {
        WIDTH(600),
        HEIGHT(600);

        /**
         * Value representing the width and length of the frame.
         */
        private final int value;

        /**
         * Constructor for the enum.
         * @param value
         */
        FrameSize(int value) {
            this.value = value;
        }

        /**
         * Returns the length value of FrameSize.
         * @return the length value of width/height.
         */
        public int getValue() {
            return value;
        }
    }

    /**
     * Constructor for the class.
     */
    public ScrabbleFrame() throws FileNotFoundException {
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

        // todo add other panels

        this.revalidate();
    }


    /**
     * Initializes this frame.
     */
    private void initializeFrame() {
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setSize(FrameSize.WIDTH.getValue(), FrameSize.HEIGHT.getValue());
        this.setLayout(new GridLayout(2, 2)); //todo revisit later
        this.setVisible(true);
        this.revalidate();
    }


    public static void main(String[] args) throws FileNotFoundException {
        ScrabbleFrame gameFrame = new ScrabbleFrame();
    }
}
