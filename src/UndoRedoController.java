import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serializable;

public class UndoRedoController implements Serializable, ActionListener {

    Game game;

    public UndoRedoController(Game game){
        this.game = game;
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        if (this.game.undoGame() == false)
        {
            JOptionPane.showMessageDialog(null, "Nothing to Undo");
        }
    }
}
