import javax.swing.*;
import java.io.FileNotFoundException;

public class StartMenuFrame extends JFrame {
    int frameWidth = 400;
    int frameHeight = 400;
    private ScrabbleFrame scrabbleFrame;

    public StartMenuFrame() { //TODO: Catch Exception
        super("Welcome to Scrabble!");
        this.initializeFrame();
        this.initializePanel();
        this.setVisible(true);
        this.setResizable(false);
        scrabbleFrame = new ScrabbleFrame();

    }

    private void initializeFrame() {
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setSize(frameWidth, frameHeight);
    }

    private void initializePanel() {
        this.add(new PlayerSelectorPanel(this));
    }

    public void createPlayers(String playerAmount) {
        scrabbleFrame.createPlayers(playerAmount);
        //this.setVisible(false);
        scrabbleFrame.setVisible(true);
    }


    public static void main(String[] args) throws FileNotFoundException {
        StartMenuFrame startMenuFrame = new StartMenuFrame();
    }
}
