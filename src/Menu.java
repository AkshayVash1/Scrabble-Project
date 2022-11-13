import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Menu extends JMenu implements ScrabbleView, ActionListener {

    private Game game;
    private GameConsolePanel gameConsole;
    private JMenuItem help, exit;
    private JFrame exitPopUp;

    public Menu(Game game) {
        super();
        this.game = game;
        initMenu();
    }

    private void initMenu() {
        help = new JMenuItem("Help");
        exit = new JMenuItem("Exit");
        this.add(help);
        this.add(exit);
    }

    private void initExitPopUp() {

    }

    @Override
    public void update(Player currentPlayer, Board board) {

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch(e.getActionCommand()) {
            case "Help":
                break;
            case "Exit":
                exitPopUp = new JFrame();
        }
    }
}
