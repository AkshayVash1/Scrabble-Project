import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        Tile t1 = new Tile("A", 1);
        Tile t2 = new Tile("B", 1);
        Tile t3 = new Tile("C", 1);
        Tile t4 = new Tile("D", 1);
        Tile t5 = new Tile("E", 1);
        Tile t6 = new Tile("F", 1);
        Tile t7 = new Tile("G", 1);
        Tile t8 = new Tile("H", 1);
        Tile t9 = new Tile("I", 1);
        Tile t10 = new Tile("J", 1);

        Hand h1 = new Hand();
        ArrayList<Tile> tileList = new ArrayList<>();
        tileList.add(t1);
        tileList.add(t2);
        tileList.add(t3);
        tileList.add(t4);
        tileList.add(t5);
        tileList.add(t6);
        tileList.add(t7);
        h1.addTiles(tileList);

        InHand inHand = new InHand("CAD", h1);

        h1.displayHand();
        //System.out.println(inHand.wordInHand());
       // h1.removeTiles(inHand.wordToList());
       // h1.displayHand();

        ArrayList<Tile> t = new ArrayList<>();
        t.add(t8);
        t.add(t9);
        t.add(t10);

        Player player = new Player();
        //System.out.println(player.exchange(t, inHand.wordToList()));
        //h1.displayHand();
    }
}