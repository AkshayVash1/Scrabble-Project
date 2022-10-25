import java.util.ArrayList;

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

        for (Character c : this.wordAttempt.toCharArray())
        {
            for (Tile t : mockHand)
            {
                if (c.equals(t.getLetter().charAt(Hand.PARSE_CHAR_AT_ZERO)))
                {
                    mockHand.remove(t);
                    break;
                }
            }
        }

        return (mockHand.size() == (initialSize - this.wordAttempt.length()));
    }

    public ArrayList<Character> wordToList()
    {
        ArrayList<Character> wordAttemptList = new ArrayList<>();
        for (Character c : this.wordAttempt.toCharArray()) { wordAttemptList.add(c); }

        return wordAttemptList;
    }
}