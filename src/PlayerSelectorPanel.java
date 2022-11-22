/**
 * This class is part of the "Scrabble" application.
 *
 * This panel initializes the game by creating a Selector Panel with the choice for the number of players needed to
 * start the game.
 *
 * @author Jaydon Haghighi
 * @date 2022.11.13
 */

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.File;
import java.io.IOException;

public class PlayerSelectorPanel extends JPanel implements ItemListener {
    public static final int WIDTH = 180;
    public static final int HEIGHT = 25;
    JComboBox playerComboBox = new JComboBox();
    JComboBox AIComboBox = new JComboBox();
    private JComboBox oldPlayerComboBox = new JComboBox();
    private JComboBox oldAIComboBox = new JComboBox();
    private String playerAmount;
    private String AIAmount;
    private Image backgroundImage;

    private StartMenuFrame startMenuFrame;
    JLabel playerSelectorLabel = new JLabel("Please Select Number of players");
    JLabel AISelectorLabel = new JLabel("Please Select Number of AIs");

    public PlayerSelectorPanel(StartMenuFrame startMenuFrame) {

        this.startMenuFrame = startMenuFrame;
        initializePlayerSelector();
    }

    private void initializePlayerSelector() {
        try {
            backgroundImage = ImageIO.read(new File("src\\start_menu_background.jpg"));
        } catch (IOException e) {
            playerSelectorLabel.setForeground(Color.black);
            AISelectorLabel.setForeground(Color.black);
        }

        playerComboBox.setName("playerComboBox");
        JPanel playerComboBoxPanel = new JPanel(new GridLayout(2, 0));
        playerComboBoxPanel.setBackground(new Color(0, 0, 0, 0));
        playerSelectorLabel.setForeground(Color.white);
        playerComboBox.addItem("");
        for (int i = 2; i < 5; i++) {
            playerComboBox.addItem(i + " players");
        }
        playerComboBox.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        playerComboBoxPanel.add(playerSelectorLabel);
        playerComboBoxPanel.add(playerComboBox);
        playerComboBox.addItemListener(this);
        this.add(playerComboBoxPanel);
    }
    private void initializeAISelector(int AILimit) {
        AIComboBox.setName("AIComboBox");
        JPanel AIComboBoxPanel = new JPanel(new GridLayout(2, 0));
        AIComboBoxPanel.setBackground(new Color(0, 0, 0, 0));
        AISelectorLabel.setForeground(Color.white);
        AIComboBox.addItem("");
        for (int i = 0; i < AILimit; i++) {
            AIComboBox.addItem(i + " AI players");
        }
        AIComboBox.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        AIComboBoxPanel.add(AISelectorLabel);
        AIComboBoxPanel.add(AIComboBox);
        AIComboBox.addItemListener(this);
        this.add(AIComboBoxPanel);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        // Draw the background image.
        g.drawImage(backgroundImage, 0, 0, this);
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        if (e.getStateChange() == ItemEvent.SELECTED) {
            playerComboBox.setName(String.valueOf(playerComboBox.getSelectedItem()));
            if (e.getSource().equals(playerComboBox)) {
                if (playerComboBox.getSelectedItem().equals(2 + " players")) {
                    playerAmount = "2";
                    initializeAISelector(1);
                    oldPlayerComboBox.setName(String.valueOf(playerComboBox.getSelectedItem()));
                } else if (playerComboBox.getSelectedItem().equals(3 + " players")) {
                    playerAmount = "3";
                    initializeAISelector(2);
                    oldPlayerComboBox.setName(String.valueOf(playerComboBox.getSelectedItem()));
                } else if (playerComboBox.getSelectedItem().equals(4 + " players")) {
                    playerAmount = "4";
                    initializeAISelector(3);
                    oldPlayerComboBox.setName(String.valueOf(playerComboBox.getSelectedItem()));
                }
            } else if (e.getSource().equals(AIComboBox)) {
                System.out.println("hello");

                startMenuFrame.createPlayers(playerAmount);
                startMenuFrame.setVisible(false);
            }
            this.revalidate();
            this.repaint();
        }

    }
}
