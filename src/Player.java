import java.util.ArrayList;

public class Player {

    public static final int MIN_POINTS = 0;

    private Hand hand;
    private int points;

    /**
     * Constructor to initialize player points to 0 when player is created. Constructor to initialize
     * hand.
     * */
    public Player ()
    {
        this.points = MIN_POINTS;
        hand = new Hand();
    }

    /*Note: Exchange can be called either for play or for exchange.
    For Exchange: In Game, add letters to bag instead of to board.
    For Play: In Game, add letters to board instead of to bag.
     */
    /**
     * Emulates the exchanging of Tiles by removing the Tile objects from hand and replacing with new TIle objects
     * passed into the method.
     * @param removeTiles The subset of Tile objects to be removed from Hand
     * @param addTiles The set of Tile objects to be added to the Hand
     * @return boolean Return true is successfully exchanged Tiles, else return false.
     * */
    public boolean exchange(ArrayList<Tile> addTiles, ArrayList<Tile> removeTiles)
    {
        boolean rc = true;

        if ((rc = this.hand.addTiles(addTiles)) == false)
        {
            System.out.println("Couldn't add Tiles to hand");
        }
        else if ((rc = this.hand.removeTiles(removeTiles)) == false)
        {
            System.out.println("Couldn't remove tiles from hand");
        }

        return rc;
    }

    /**
     * Rearranges object tiles in Hand.
     * @return void
     * */
    public void shuffle()
    {
        this.hand.shuffleHand();
    }

    /**
     * Getter to get current state of the points of the user.
     * @return int current points of Player
     * */
    public int getPoints()
    {
        return this.points;
    }

    /**
     * Added select amount of points to users total point pool.
     * @param addedPoints the amount of points to add to total Player points
     * */
    public void addPoints(int addedPoints)
    {
        this.points += addedPoints;
    }
}
