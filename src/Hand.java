import java.util.ArrayList;
import java.util.Collections;

public class Hand {

    public final static int MAX_HAND_SIZE = 7;
    public final static int PARSE_CHAR_AT_ZERO = 0;

    private ArrayList<Tile> hand;
    private ArrayList<Tile> recentlyRemoved;
    private ArrayList<Tile> recentlyAdded;

    /**
     * Public constructor for class Hand. Use to initialize a hand of size MAX_HAND_SIZE (7)*
     * */
    public Hand()
    {
        this.hand = new ArrayList<>(MAX_HAND_SIZE);
        this.recentlyRemoved = new ArrayList<>();
        this.recentlyAdded = new ArrayList<>();
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
        for (Tile t : this.hand)
        {
            System.out.print(t + " ");
        }
        System.out.println();
    }

    /**
     * Removes a subset of Tile objects from the Hand Arraylist.
     * @param removeTiles ArrayList of Character element to remove the corresponding Tiles
     * @return boolean Return true if successfully removed Tile objects, else return false.
     * */
    public boolean removeTiles(ArrayList<Character> removeTiles)
    {
        boolean rc = true;
        int remCount = 0;

        this.recentlyRemoved.clear();

        ArrayList<Tile> mock = new ArrayList<>();
        mock.addAll(this.hand);

        if ((rc = (!removeTiles.isEmpty())) == false)
        {
            System.out.println("FAILED, PASSED IN ARRAY IS EMPTY");
        }
        else
        {
            for (Character c : removeTiles) {
                for (Tile l : mock) {
                    if (c.equals(l.getLetter().charAt(PARSE_CHAR_AT_ZERO)))
                    {
                        this.hand.remove(l);
                        this.recentlyRemoved.add(l);
                        remCount++;
                        break;
                    }
                }
            }
        }

        return rc;
    }

    /**
     * Returns a set of the recently removed Tiles in order to be added back to the bag if play not valid.
     * @return the set of recently removed Tiles as a Tile ArrayList
     * */
    public ArrayList<Tile> getRecentlyRemoved()
    {
        return this.recentlyRemoved;
    }

    /**
     * Returns a set of the recently added Tiles in order to be remove from hand if play not valid.
     * @return the set of recently removed Tiles as a Tile ArrayList
     * */
    public ArrayList<Tile> getRecentlyAdded()
    {
        return this.recentlyAdded;
    }

    /**
     * Adds a set of Tile objects to the Hand Arraylist.
     * @param addTiles ArrayList of Tile elements to added to hand
     * */
    public void addTiles(ArrayList<Tile> addTiles)
    {
        this.recentlyAdded.clear();

        this.recentlyAdded.addAll(addTiles);
        this.hand.addAll(addTiles);

    }

    /**
     * Adds a set of Tile objects to the Hand Arraylist.
     * @return Integer indicating the size of hand ArrayList.
     * */
    public int getHandSize()
    {
        return this.hand.size();
    }

    /**
     * Getter that returns the ArrayList of Tile objects for hand.
     * @return ArrayList<Tile> hand.
     * */
    public ArrayList<Tile> getHand() {
        return this.hand;
    }
}