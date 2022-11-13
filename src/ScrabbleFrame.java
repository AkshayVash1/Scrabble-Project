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
    private final int frameWidth = 1025;
    private final int frameHeight = 1000;
    private Game game = new Game();

    /**
     * Constructor for the class.
     */
    public ScrabbleFrame() {
        super("Welcome to Scrabble!");
        this.initializeFrame();
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
        //this.setVisible(true);
        this.setResizable(false);
        //this.revalidate();
    }


    /**
     * Creates and adds to this frame all component panels of the GUI.
     */
    private void initializePanels(Game game) {
        JPanel mainPanel = new JPanel(new BorderLayout());

        JPanel leftPanel = new JPanel();
        leftPanel.setLayout(new BorderLayout());

        JPanel rightPanel = new JPanel();
        rightPanel.setLayout(new BorderLayout());

        GameConsolePanel gameConsolePanel = new GameConsolePanel(game);
        BoardPanel boardPanel = new BoardPanel(game);
        HandPanel handPanel = new HandPanel(game);
        GameCommandPanel gameCommandPanel = new GameCommandPanel(game);
        StatusPanel statusPanel = new StatusPanel(game);
        JPanel southPanel = new JPanel();

        southPanel.add(gameCommandPanel,BorderLayout.CENTER);
        leftPanel.add(boardPanel, BorderLayout.NORTH);
        leftPanel.add(handPanel, BorderLayout.CENTER);
        leftPanel.add(gameCommandPanel, BorderLayout.SOUTH);
        rightPanel.add(gameConsolePanel, BorderLayout.CENTER);
        rightPanel.add(statusPanel, BorderLayout.NORTH);


        mainPanel.add(leftPanel, BorderLayout.WEST);
        mainPanel.add(rightPanel, BorderLayout.EAST);

        this.add(mainPanel);

        this.revalidate();
    }

    public void createPlayers(String playerAmount) {
        game.createPlayers(playerAmount);
    }
}
