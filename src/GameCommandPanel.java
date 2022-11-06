import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;


public class GameCommandPanel extends JPanel implements ActionListener {
    public static final String PLAY = "Button:" + 0 + ":" + 0;
    public static final String EXCHANGE_BUTTON = "Button:" + 0 + ":" + 1;
    public static final String SHUFFLE = "Button:" + 1 + ":" + 0;
    public static final String PASS = "Button:" + 1 + ":" + 1;
    public static final String RESET_SELECTION = "Reset Selection";
    public static final String EXCHANGE_COMMAND = "Exchange";
    private final JButton[][] buttons = new JButton[2][2];
    private Player player;
    ArrayList<Tile> hypotheticalHand = new ArrayList<>();
    JFrame exchangeFrame = new JFrame();
    JPanel tilePanel = new JPanel(new GridLayout(0, 7));
    JPanel resetButtonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
    ArrayList<JToggleButton> tileButtons = new ArrayList<>();
    ArrayList<Tile> tilesToExchange = new ArrayList<>();

    public GameCommandPanel() {
        createHand();
        JFrame hypotheticalFrame = new JFrame(); //Temp Frame
        hypotheticalFrame.setSize(500, 300);
        hypotheticalFrame.add(initializeGameCommands());
        hypotheticalFrame.setVisible(true);
        initializeExchangeFrame();
    }

    private void createHand() {
        player = new Player(1);
        Tile t1 = new Tile("A", 1);
        Tile t2 = new Tile("B", 1);
        Tile t3 = new Tile("C", 1);
        Tile t4 = new Tile("D", 1);
        Tile t5 = new Tile("E", 1);
        Tile t6 = new Tile("F", 1);
        Tile t7 = new Tile("G", 1);
        hypotheticalHand.add(t1);
        hypotheticalHand.add(t2);
        hypotheticalHand.add(t3);
        hypotheticalHand.add(t4);
        hypotheticalHand.add(t5);
        hypotheticalHand.add(t6);
        hypotheticalHand.add(t7);
    }

    private JPanel initializeGameCommands() {
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
        for (Tile tile : hypotheticalHand) {
            JToggleButton tileButton = new JToggleButton(tile.getLetter());
            tileButton.setActionCommand("Letter:" + counter);
            tileButton.setName(String.valueOf(hypotheticalHand.get(counter)));
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
        JToggleButton button = (JToggleButton) e.getSource();
        final String[] split = button.getActionCommand().split(":");
        int col = Integer.parseInt(split[1]);
        if (tileButtons.get(col).getText().equals(button.getText())) {
            if (button.isSelected()) {
                tilesToExchange.add(hypotheticalHand.get(col));
                System.out.println(tilesToExchange);
            } else {
                tilesToExchange.remove(hypotheticalHand.get(col));
            }

        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals(PLAY)) {
            /**
             * Play the move set onto board
             */
            System.out.println("button00");
        } else if (e.getActionCommand().equals(EXCHANGE_BUTTON)) {
            exchangeFrame.setVisible(true);
        } else if (e.getActionCommand().equals(SHUFFLE)) {
            /**
             * Shuffle Hand change order of tiles within hand
             */
            System.out.println("button10");
        } else if (e.getActionCommand().equals(PASS)) {
            /**
             * Pass Turn go to next player
             */
            System.out.println("button11");
        } else if (e.getActionCommand().contains("Letter")) {
            processToggleButtonAction(e);
        } else if (e.getActionCommand().contains(EXCHANGE_COMMAND)) {
            /**
             * Will grab tilesToExchange and send it to Game to exchange the tiles
             */
        }

    }

    public static void main(String[] args) {
        GameCommandPanel test1 = new GameCommandPanel();
    }
}

