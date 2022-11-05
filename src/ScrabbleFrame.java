import javax.swing.*;
import java.awt.*;

public class ScrabbleFrame extends JFrame {

    public ScrabbleFrame(){

        super("Test Frame");
        this.setLayout(new GridLayout(2, 1));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.setSize(1300,1200);
        BoardPanel bp = new BoardPanel();
        this.add(bp);
        HandPanel hp = new HandPanel();
        this.add(hp);

    }

    public static void main (String[] args)
    {
        ScrabbleFrame tf = new ScrabbleFrame();
    }
}
