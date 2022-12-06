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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

public class ScrabbleFrame extends JFrame implements ActionListener {

    /**
     * Dimensions of the frame.
     */
    private final int frameWidth = 1025;
    private final int frameHeight = 1000;
    private Game game = new Game();
    private GameConsolePanel gameConsolePanel;
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

        UndoRedoController urc = new UndoRedoController(this.game);

        JPanel mainPanel = new JPanel(new BorderLayout());

        JPanel leftPanel = new JPanel();
        leftPanel.setLayout(new BorderLayout());

        JPanel rightPanel = new JPanel();
        rightPanel.setLayout(new BorderLayout());
        JPanel southPanel = new JPanel();

        gameConsolePanel = new GameConsolePanel(game);
        BoardPanel boardPanel = new BoardPanel(game);
        HandPanel handPanel = new HandPanel(game);
        GameCommandPanel gameCommandPanel = new GameCommandPanel(game);
        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("Menu");
        JMenu undoredo = new JMenu("Undo/Redo");
        JMenuItem help = new JMenuItem("Help");
        JMenuItem exit = new JMenuItem("Exit");
        JMenuItem undo = new JMenuItem("Undo");
        undo.addActionListener(urc);
        JMenuItem redo = new JMenuItem("Redo");
        help.addActionListener(this);
        exit.addActionListener(this);
        menu.add(help);
        menu.add(exit);
        undoredo.add(undo);
        undoredo.add(redo);
        menuBar.add(menu);
        menuBar.add(undoredo);

        southPanel.add(gameCommandPanel,BorderLayout.CENTER);
        leftPanel.add(boardPanel, BorderLayout.NORTH);
        leftPanel.add(handPanel, BorderLayout.CENTER);
        leftPanel.add(gameCommandPanel, BorderLayout.SOUTH);
        rightPanel.add(gameConsolePanel, BorderLayout.CENTER);

        mainPanel.add(leftPanel, BorderLayout.WEST);
        mainPanel.add(rightPanel, BorderLayout.EAST);

        this.add(mainPanel);
        this.setJMenuBar(menuBar);
        this.revalidate();
    }

    /**
     * Creates the players for the game
     *
     * @param playerAmount
     */
    public void createPlayers(String playerAmount, String AIAmount) {
        game.createPlayers(playerAmount, AIAmount);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch(e.getActionCommand()) {
            case "Help":
                gameConsolePanel.appendHelp();
                break;
            case "Exit":
                this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
        }
    }
}