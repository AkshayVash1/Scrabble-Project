import java.util.ArrayList;

public class Player {

    private Hand hand;
    private int points;

    public Player ()
    {
        this.points = 0;
        hand = new Hand();
    }

    /*Note: Exchange can be called either for play or for exchange.
    For Exchange: In Game, add letters to bag instead of to board.
    For Play: In Game, add letters to board instead of to bag.
     */
    public boolean exchange(ArrayList<Tile> bagTiles, ArrayList<Tile> removeTiles)
    {
        boolean rc = true;

        if ((rc = this.hand.addTiles(bagTiles)) == false)
        {
            System.out.println("Couldn't add Tiles to hand");
        }
        else if ((rc = this.hand.removeTiles(removeTiles)) == false)
        {
            System.out.println("Couldn't remove tiles from hand");
        }

        return rc;
    }

    public void shuffle()
    {
        this.hand.shuffleHand();
    }

    public int getPoints()
    {
        return this.points;
    }

    public void addPoints(int addedPoints)
    {
        this.points += addedPoints;
    }
}
