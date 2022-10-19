import java.util.ArrayList;
import java.util.Collections;

public class Hand {

    public final static int MAX_HAND_SIZE = 7;

    private ArrayList<Tile> hand;

    public Hand()
    {
        this.hand = new ArrayList<>(MAX_HAND_SIZE);
    }

    public void shuffleHand()
    {
        Collections.shuffle(this.hand);
    }

    public void displayHand()
    {
        int num = 1;
        for (Tile t : this.hand)
        {
            System.out.println("TILE"+ num++ +": POINT:"+t.getValue()+" LETTER:"+ t.getLetter());
        }
    }

    public boolean removeTiles(ArrayList<Tile> removeTiles)
    {
        boolean rc = true;
        int remCount = 0;
        ArrayList<Tile> mock = new ArrayList<>();
        mock.addAll(this.hand);

        if ((rc = (!removeTiles.isEmpty())) == false)
        {
            System.out.println("FAILED, PASSED IN ARRAY IS EMPTY");
        }
        else
        {
            for (Tile t : removeTiles) {
                for (Tile l : mock) {
                    if (l.equals(t))
                    {
                        this.hand.remove(l);
                        remCount++;
                    }
                }
            }

            if ((rc = (removeTiles.size() == remCount)) == false)
            {
                System.out.println("NOT ALL REQUESTED REMOVE TILES IN HAND");
                this.hand.clear();
                this.hand.addAll(mock);
            }
        }

        return rc;
    }

    public boolean addTiles(ArrayList<Tile> removeTiles)
    {
        boolean rc = true;

        if ((rc = (this.hand.size() + removeTiles.size() == MAX_HAND_SIZE)) == false)
        {
            System.out.println("MISMATCH WITH ADDING TILES");
        }
        else
        {
            this.hand.addAll(removeTiles);
        }

        return rc;
    }
}
