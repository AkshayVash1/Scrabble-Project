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
    private final int frameWidth = 1000;
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
        //this.setLayout(new GridLayout(2,1)); //todo revisit later
        this.setLayout(new BorderLayout()); //todo revisit later
        this.setVisible(true);
        this.setResizable(false);
        //this.revalidate();
    }


    /**
     * Creates and adds to this frame all component panels of the GUI.
     */
    private void initializePanels(Game game) {
        // adding the BoardPanel

        JPanel leftPanel = new JPanel();

        leftPanel.setLayout(new BorderLayout());

        JPanel rightPanel = new JPanel();

        GameConsolePanel gameConsolePanel = new GameConsolePanel(game);
        BoardPanel boardPanel = new BoardPanel(game);
        HandPanel handPanel = new HandPanel(game);
        GameCommandPanel gameCommandPanel = new GameCommandPanel(game);
        StatusPanel statusPanel = new StatusPanel(game);

        // southPanel contains handPanel and gameCommandPanel
        JPanel southPanel = new JPanel();

        //PlayerDisplayPanel playerDisplayPanel = new PlayerDisplayPanel(game);
        //this.add(playerDisplayPanel);

        // adding handPanel and gameCommandPanel to southPanel instead of adding to the frame directly
        southPanel.add(handPanel, BorderLayout.CENTER);
        southPanel.add(gameCommandPanel,BorderLayout.CENTER);
        //southPanel.add(playerDisplayPanel, BorderLayout.EAST);
        //this.add(handPanel);
        //this.add(gameCommandPanel);
        leftPanel.add(boardPanel, BorderLayout.PAGE_START);
        leftPanel.add(southPanel, BorderLayout.PAGE_END);
        //rightPanel.add(statusPanel, BorderLayout.NORTH);
        rightPanel.add(gameConsolePanel, BorderLayout.SOUTH);


        this.add(leftPanel);
        //this.add(rightPanel);
        //this.add(rightPanel, BorderLayout.EAST);

        this.revalidate();
    }


    public static void main(String[] args) throws FileNotFoundException {
        ScrabbleFrame gameFrame = new ScrabbleFrame();
    }
}
