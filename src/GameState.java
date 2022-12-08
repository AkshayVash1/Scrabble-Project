import java.io.*;
import java.util.ArrayList;

public class GameState implements Serializable{

    private Game game;
    private ArrayList<GameState> undoStateHistory;
    private ArrayList<GameState> redoStateHistory;

    public final static String FILENAME_UNDO = "src/undo.txt";
    public final static String FILENAME_REDO = "src/redo.txt";
    public final static String FILENAME_SAVE = "src/save.txt";

    public GameState(Game game, boolean undo) {
        this.game = game;
        this.undoStateHistory = new ArrayList<>();
        this.redoStateHistory = new ArrayList<>();

        if (undo) {
            updateCurrentGameHistoryUndo();
        }
        else
        {
            updateCurrentGameHistoryRedo();
        }
    }

    private void updateCurrentGameHistoryUndo(){
        File newFile = new File(FILENAME_UNDO);
        FileInputStream inputStream;
        ObjectInputStream ois;
        if (newFile.length() != 0) {
            try {
                inputStream = new FileInputStream(FILENAME_UNDO);
                ois = new ObjectInputStream(inputStream);
                try {
                    this.undoStateHistory = (ArrayList<GameState>) ois.readObject();
                } catch (ClassNotFoundException c) {
                    System.out.print("c");
                }
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }

        this.undoStateHistory.add(this);
        saveCurrentGameStateUndo();
    }

    private void saveCurrentGameStateUndo() {
        FileOutputStream outputStream = null;
        try {
            PrintWriter writer = new PrintWriter(FILENAME_UNDO);
            writer.print("");
        } catch (FileNotFoundException f)
        {
            System.out.print("f");
        }
        ObjectOutputStream oos = null;
        try {
            outputStream = new FileOutputStream(FILENAME_UNDO);
            oos = new ObjectOutputStream(outputStream);
            oos.writeObject(this.undoStateHistory);
        }
        catch (IOException e)
        {
            System.out.print("e2");
        }
    }

    private void updateCurrentGameHistoryRedo()
    {
        File newFile = new File(FILENAME_REDO);
        FileInputStream inputStream;
        ObjectInputStream ois;
        if (newFile.length() != 0) {
            try {
                inputStream = new FileInputStream(FILENAME_REDO);
                ois = new ObjectInputStream(inputStream);
                try {
                    this.redoStateHistory = (ArrayList<GameState>) ois.readObject();
                } catch (ClassNotFoundException c) {
                    System.out.print("c");
                }
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }

        this.redoStateHistory.add(this);
        saveCurrentGameStateRedo();
    }

    private void saveCurrentGameStateRedo() {
        FileOutputStream outputStream;
        try {
            PrintWriter writer = new PrintWriter(FILENAME_REDO);
            writer.print("");
        } catch (FileNotFoundException f)
        {
            System.out.print("f");
        }
        ObjectOutputStream oos;
        try {
            outputStream = new FileOutputStream(FILENAME_REDO);
            oos = new ObjectOutputStream(outputStream);
            oos.writeObject(this.redoStateHistory);
        }
        catch (IOException e)
        {
            System.out.print("e2");
        }
    }

    public Game getGame()
    {
        return this.game;
    }

}
