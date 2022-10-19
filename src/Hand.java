import java.util.ArrayList;
import java.util.Collections;

public class Hand {

    public final static int MAX_HAND_SIZE = 7;

    private ArrayList<Tile> hand;

    /**
     * Public constructor for class Hand. Use to initialize a hand of size MAX_HAND_SIZE (7)*
     * */
    public Hand()
    {
        this.hand = new ArrayList<>(MAX_HAND_SIZE);
    }

    /**
     * Rearranges elements in hand ArrayList using static Collections.shuffle method.
     * @return void
     * */
    public void shuffleHand()
    {
        Collections.shuffle(this.hand);
    }

    /**
     * Iterates through ArrayList hand and displays the tile value and the letter value of each Tile.
     * @return void
     * */
    public void displayHand()
    {
        int num = 1;
        for (Tile t : this.hand)
        {
            System.out.print(t.getLetter() + "" + t.getValue() + " ");
        }
        System.out.println();
    }

    /**
     * Removes a subset of Tile objects from the Hand Arraylist.
     * @param removeTiles ArrayList of Tile elements to be removed from hand
     * @return boolean Return true if successfully removed Tile objects, else return false.
     * */
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
        else if (( rc = this.hand.containsAll(removeTiles)) == false)
        {
            System.out.println("NOT ALL REQUESTED REMOVE TILES IN HAND");
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
        }

        return rc;
    }

    /**
     * Adds a set of Tile objects to the Hand Arraylist.
     * @param addTiles ArrayList of Tile elements to added to hand
     * @return boolean Return true if successfully added Tile objects, else return false.
     * */
    public boolean addTiles(ArrayList<Tile> addTiles)
    {
        boolean rc = true;

        if ((rc = (this.hand.size() + addTiles.size() == MAX_HAND_SIZE)) == false)
        {
            System.out.println("MISMATCH WITH ADDING TILES");
        }
        else
        {
            this.hand.addAll(addTiles);
        }

        return rc;
    }

    /**
     * Adds a set of Tile objects to the Hand Arraylist.
     * @return Integer indicating the size of hand ArrayList.
     * */
    public int getHandSize()
    {
        return this.hand.size();
    }
}
