import java.util.ArrayList;
import java.util.List;

public class InHand {

    private String wordAttempt;
    private List<Character> wordAttemptChar;
    private List<Tile> tileList;
    private Hand hand;

    public InHand(String wordAttempt, Hand hand)
    {
        this.wordAttempt = wordAttempt;
        this.hand = hand;
        this.wordAttemptChar = new ArrayList<>();
        this.tileList = new ArrayList<>();
    }

    public boolean wordInHand()
    {
        ArrayList<Tile> mockHand = (ArrayList<Tile>) (this.hand.getHand().clone());

        int initialSize = mockHand.size();

        for (char c : this.wordAttemptChar)
        {
            for (Tile t : mockHand)
            {
                if (t.getLetter() == c)
                {
                    mockHand.remove(t);
                    break;
                }
            }
        }

        return (mockHand.size() == (initialSize - this.wordAttempt.length()));
    }

    public List<Tile> wordToTileList()
    {
        for (char c : this.wordAttempt.toCharArray())
        {
            Tile t = new Tile(c, 1);
            tileList.add(t);
        }

        return this.tileList;
    }
}
