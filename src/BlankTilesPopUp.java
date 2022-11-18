import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BlankTilesPopUp extends JFrame implements ActionListener {

    private final int frameWidth = 50;
    private final int frameHeight = 50;

    public BlankTilesPopUp()
    {
        super("Blank Tile Selection!");
        this.initializeFrame();
        this.setVisible(true);
    }

    private void initializeFrame() {
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setSize(frameWidth, frameHeight);
        this.setLayout(new BorderLayout()); //todo revisit later
        this.setResizable(false);
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {

    }
}
