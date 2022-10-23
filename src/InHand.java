import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class InHand {

    private String wordAttempt;
    private Hand hand;

    public InHand(String wordAttempt, Hand hand)
    {
        this.wordAttempt = wordAttempt;
        this.hand = hand;
    }

    public boolean wordInHand()
    {
        ArrayList<Tile> mockHand = (ArrayList<Tile>) (this.hand.getHand().clone());

        int initialSize = mockHand.size();

        for (char c : this.wordAttempt.toCharArray())
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

    public List<Character> wordToList()
    {
        List<Character> wordAttemptList = new ArrayList<>();
        for (Character c : this.wordAttempt.toCharArray()) { wordAttemptList.add(c); }

        return wordAttemptList;
    }
}
