import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class PlayerSelectorPanel extends JPanel {
    public static final int WIDTH = 180;
    public static final int HEIGHT = 25;
    JComboBox comboBox = new JComboBox();
    private JComboBox oldJComboBox = new JComboBox();
    private String playerAmount;
    private Image backgroundImage;

    private StartMenuFrame startMenuFrame;
    JLabel jlabel = new JLabel("Please Select Number of players");

    public PlayerSelectorPanel(StartMenuFrame startMenuFrame) {
        this.startMenuFrame = startMenuFrame;
        this.add(initializePlayerSelector());
    }

    private JPanel initializePlayerSelector() {
        try {
            backgroundImage = ImageIO.read(new File("src\\start_menu_background.jpg"));
        } catch (IOException e) {
            jlabel.setForeground(Color.black);
        }

        comboBox.setName("JComboBox");
        JPanel jComboBoxPanel = new JPanel(new GridLayout(2, 0));
        jComboBoxPanel.setBackground(new Color(0, 0, 0, 0));
        jlabel.setForeground(Color.white);
        comboBox.addItem("");
        for (int i = 2; i < 5; i++) {
            comboBox.addItem(i + " players");
        }
        comboBox.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        jComboBoxPanel.add(jlabel);
        jComboBoxPanel.add(comboBox);
        jComboBoxListener();
        return jComboBoxPanel;
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        // Draw the background image.
        g.drawImage(backgroundImage, 0, 0, this);
    }

    private void jComboBoxListener() {
        comboBox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    comboBox.setName(String.valueOf(comboBox.getSelectedItem()));
                    if (comboBox.getSelectedItem().equals(2 + " players")) {
                        playerAmount = "2";
                        oldJComboBox.setName(String.valueOf(comboBox.getSelectedItem()));
                    } else if (comboBox.getSelectedItem().equals(3 + " players")) {
                        playerAmount = "3";

                        oldJComboBox.setName(String.valueOf(comboBox.getSelectedItem()));
                    } else if (comboBox.getSelectedItem().equals(4 + " players")) {
                        playerAmount = "4";

                        oldJComboBox.setName(String.valueOf(comboBox.getSelectedItem()));
                    }

                    startMenuFrame.createPlayers(playerAmount);
                }
            }
        });
    }
}
