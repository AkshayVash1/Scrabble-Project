import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;


public class GameCommandPanel extends JPanel implements ActionListener, ScrabbleView {
    public static final String PLAY = "Button:" + 0 + ":" + 0;
    public static final String EXCHANGE_BUTTON = "Button:" + 0 + ":" + 1;
    public static final String SHUFFLE = "Button:" + 1 + ":" + 0;
    public static final String PASS = "Button:" + 1 + ":" + 1;
    public static final String EXCHANGE_COMMAND = "Exchange";
    public static final String FORFEIT = "Forfeit";
    private final JButton[][] buttons = new JButton[2][2];
    private Player player;
    ArrayList<Tile> playerHand = new ArrayList<>();
    JFrame exchangeFrame = new JFrame();
    JPanel tilePanel = new JPanel(new GridLayout(0, 7));
    JPanel resetButtonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
    ArrayList<JToggleButton> tileButtons = new ArrayList<>();
    ArrayList<Tile> tilesToExchange = new ArrayList<>();

    private Game game;

    public GameCommandPanel(Game game) {
        this.game = game;
        this.game.addScrabbleView(this);
        this.player = new Player(10);
        this.playerHand = this.player.getHand().getHand();
        this.setPreferredSize(new Dimension(500,300));
        this.add(initializeGameCommands());
        initializeExchangeFrame();
    }

    private JPanel initializeGameCommands() {
        this.playerHand = this.player.getHand().getHand();
        JPanel gameButtonsPanel = new JPanel(new GridLayout(2, 2));
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                JButton button = new JButton();
                button.setActionCommand("Button:" + i + ":" + j); //setting actionCommand of each button based on i and j
                button.addActionListener(this);
                buttons[i][j] = button; //adding button to buttons
                gameButtonsPanel.add(button); //adding button to buttonPanel
            }
        }
        buttons[0][0].setText("Play");
        buttons[0][1].setText("Exchange");
        buttons[1][0].setText("Shuffle");
        buttons[1][1].setText("Pass");
        return gameButtonsPanel;
    }

    private void initializeExchangeFrame() {
        int counter = 0;
        this.playerHand = this.player.getHand().getHand();

        tilePanel.removeAll();
        this.resetButtonPanel.removeAll();
        for (Tile tile : this.playerHand) {
            JToggleButton tileButton = new JToggleButton(tile.getLetter());
            tileButton.setActionCommand("Letter:" + counter);
            tileButton.setName(String.valueOf(playerHand.get(counter)));
            tileButton.addActionListener(this);
            tilePanel.add(tileButton);
            tileButtons.add(counter, tileButton);
            counter++;
        }
        JButton exchangeButton = new JButton(EXCHANGE_COMMAND);
        resetButtonPanel.add(exchangeButton);
        exchangeButton.addActionListener(this);
        exchangeFrame.add(tilePanel);
        exchangeFrame.add(resetButtonPanel, BorderLayout.SOUTH);
        exchangeFrame.setSize(900, 180);
        exchangeFrame.setResizable(false);
        exchangeFrame.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
    }

    private void processToggleButtonAction(ActionEvent e) {
        this.playerHand = this.player.getHand().getHand();

        JToggleButton button = (JToggleButton) e.getSource();
        final String[] split = button.getActionCommand().split(":");
        int col = Integer.parseInt(split[1]);
        if (tileButtons.get(col).getText().equals(button.getText())) {
            if (button.isSelected()) {
                tilesToExchange.add(playerHand.get(col));
                System.out.println(tilesToExchange);
            } else {
                tilesToExchange.remove(playerHand.get(col));
            }

        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals(PLAY)) {
            try {
                this.game.processCommand(new Command("play", " ", this.game.getStartingCoordinates()));
            } catch (FileNotFoundException fileNotFoundException) {
                fileNotFoundException.printStackTrace();
            }
            this.game.nextPlayer();
        } else if (e.getActionCommand().equals(EXCHANGE_BUTTON)) {
            exchangeFrame.setVisible(true);
        } else if (e.getActionCommand().equals(SHUFFLE)) {
            /**
             * Shuffle Hand change order of tiles within hand
             */
            Collections.shuffle(playerHand);
        } else if (e.getActionCommand().equals(PASS)) {
            this.game.nextPlayer();
            /**
             * Pass Turn go to next player
             */
            System.out.println("button11");
        } else if (e.getActionCommand().contains(EXCHANGE_COMMAND)) {
            /**
             * Will grab tilesToExchange and send it to Game to exchange the tiles
             */
        } else if (e.getActionCommand().contains(FORFEIT)) {
            /**
             * Will grab tilesToExchange and send it to Game to exchange the tiles
             */
        } else if (e.getActionCommand().contains("Letter")) {
            processToggleButtonAction(e);
        }

    }

    @Override
    public void update(Player currentPlayer) {
        this.player = currentPlayer;
        initializeExchangeFrame();
        this.revalidate();
    }
}

