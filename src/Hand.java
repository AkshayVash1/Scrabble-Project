import java.util.ArrayList;
import java.util.Collections;

/**
 * This class is part of the "Scrabble" application.
 *
 * Hand class is used to simulate the Player's hand as it contains an ArrayList containing Tiles. It is also responsible
 * for interacting with this ArrayList by adding and removing Hands respectively. Also performs passive actions on
 * the Hand like shuffling. Hand also keeps track of recently removed and added Tiles
 *
 * @author Mohamed Kaddour
 * @date 2022.10.25
 */
public class Hand {

    public final static int MAX_HAND_SIZE = 7;
    public final static int PARSE_CHAR_AT_ZERO = 0;
    public final static String YELLOW_BOLD_TEXT_COLOR = "\033[1;93m";
    public final static String COLOR_RESET = "\u001B[0m";

    private ArrayList<Tile> hand;
    private ArrayList<Tile> recentlyRemoved;
    private ArrayList<Tile> recentlyAdded;

    /**
     * Public constructor for class Hand. Use to initialize a hand of size MAX_HAND_SIZE (7) and also the
     * recently removed and added ArrayLists*
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
            System.out.print(YELLOW_BOLD_TEXT_COLOR + t + " ");
        }
        System.out.println(COLOR_RESET);
        System.out.println();
    }

    /**
     * Removes a subset of Tile objects from the Hand Arraylist.
     * @param removeTiles ArrayList of Character element to remove the corresponding Tiles
     * @param clear Indicates it's safe to clear recently removed Tiles
     * @return boolean Return true if successfully removed Tile objects, else return false.
     * */
    public boolean removeTiles(ArrayList<Character> removeTiles, boolean clear)
    {
        boolean rc = true;

        if (clear == true) {
            this.recentlyRemoved.clear();
        }

        ArrayList<Tile> mock = new ArrayList<>();
        mock.addAll(this.hand);

        if ((rc = (!removeTiles.isEmpty())) == false)
        {
            System.out.println("FAILED, PASSED IN ARRAY IS EMPTY");
            rc = false;
        }
        else
        {
            for (Character c : removeTiles) {
                for (Tile l : mock) {
                    if (c.equals(l.getLetter().charAt(PARSE_CHAR_AT_ZERO)))
                    {
                        this.hand.remove(l);
                        if (clear == true) {
                            this.recentlyRemoved.add(l);
                        }
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
     * Returns a set of the recently added Tiles in order to be removed from hand if play not valid.
     * @return the set of recently removed Tiles as a Tile ArrayList
     * */
    public ArrayList<Tile> getRecentlyAdded()
    {
        return this.recentlyAdded;
    }

    /**
     * Adds a set of Tile objects to the Hand Arraylist.
     * @param addTiles ArrayList of Tile elements to added to hand
     * @param clear Indicates it's safe to clear recently added Tiles
     * */
    public void addTiles(ArrayList<Tile> addTiles, boolean clear)
    {
        if (clear == true) {
            this.recentlyAdded.clear();
            this.recentlyAdded.addAll(addTiles);
        }

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