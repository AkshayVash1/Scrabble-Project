import java.io.*;
import java.util.ArrayList;

public class GameState implements Serializable{

    private Game game;
    private ArrayList<GameState> stateHistory;

    public final static String FILENAME = "src/undoredo.txt";

    public GameState(Game game) {
        this.game = game;
        this.stateHistory = new ArrayList<>();

        updateCurrentGameHistory();
    }

    private void updateCurrentGameHistory(){
        File newFile = new File(FILENAME);
        FileInputStream inputStream = null;
        ObjectInputStream ois = null;
        if (newFile.length() != 0) {
            try {
                inputStream = new FileInputStream(FILENAME);
                ois = new ObjectInputStream(inputStream);
                try {
                    this.stateHistory = (ArrayList<GameState>) ois.readObject();
                } catch (ClassNotFoundException c)
                {
                    System.out.print("c");
                }
            } catch (IOException e)
            {
                System.out.println(e.getMessage());
            }
        }

        this.stateHistory.add(this);
        saveCurrentGameState();
    }

    private void saveCurrentGameState() {
        FileOutputStream outputStream = null;
        try {
            PrintWriter writer = new PrintWriter(FILENAME);
            writer.print("");
        } catch (FileNotFoundException f)
        {
            System.out.print("f");
        }
        ObjectOutputStream oos = null;
        try {
            outputStream = new FileOutputStream(FILENAME);
            oos = new ObjectOutputStream(outputStream);
            oos.writeObject(this.stateHistory);
        }
        catch (IOException e)
        {
            System.out.print("e2");
        }

        System.out.println(this.stateHistory.size());
    }

    public Game getGame()
    {
        return this.game;
    }

}
