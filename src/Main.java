import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws NotEnoughTiles {

        /*
        Tile t1 = new Tile("A", 1);
        Tile t2 = new Tile("B", 1);
        Tile t3 = new Tile("C", 1);
        Tile t4 = new Tile("D", 1);
        Tile t5 = new Tile("E", 1);
        Tile t6 = new Tile("F", 1);
        Tile t7 = new Tile("G", 1); */
        Tile t8 = new Tile("H", 1);
        Tile t9 = new Tile("I", 1);
        Tile t10 = new Tile("J", 1);

        Bag bag = new Bag();

        /*
        ArrayList<Tile> tileList = new ArrayList<>();
        tileList.add(t1);
        tileList.add(t2);
        tileList.add(t3);
        tileList.add(t4);
        tileList.add(t5);
        tileList.add(t6);
        tileList.add(t7);

         */

        ArrayList<Tile> t = new ArrayList<>();
        t.add(t8);
        t.add(t9);
        t.add(t10);

        System.out.println(bag.getBagSize());

        Player player = new Player();
        player.initializePlayerHand((ArrayList<Tile>) bag.removeTiles(7));

        player.displayHand();
        System.out.println(bag.getBagSize());
        /*

        InHand inHand = new InHand("CAD", player.getHand());
        System.out.println(inHand.wordInHand());
        player.displayHand();
        bag.placeTiles(player.exchange(t, inHand.wordToList()));
        player.displayHand();
        player.shuffle();
*/

       // System.out.println(player.exchange(t, inHand.wordToList()));
        //player.displayHand();
    }
}