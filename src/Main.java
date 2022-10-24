import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        ArrayList<String> hypotheticalWords = new ArrayList<>();
        hypotheticalWords.add("home");
        hypotheticalWords.add("apple");
        hypotheticalWords.add("SDJDSKD");
        hypotheticalWords.add("cat");
        WordValidator wordValidation = new WordValidator(hypotheticalWords);


//        List<Tile> hypotheticalHand = new ArrayList<>();
//        hypotheticalHand.add(new Tile("A",1));
//        hypotheticalHand.add(new Tile("B",1));
//        hypotheticalHand.add(new Tile("C",1));
//        Bag bag1 = new Bag();
//        bag1.print();
//        System.out.println("Num of tiles in bag: "+ bag1.getBagSize());
//        System.out.println();
//        try {
//            System.out.println("Tiles removed from bag: "+ bag1.removeTiles(7));
//        } catch (NotEnoughTiles e) {
//            System.out.println(e.getMessage());
//        }
//        System.out.println("Num of tiles in bag: "+ bag1.getBagSize());
//        bag1.placeTiles(hypotheticalHand);
//        System.out.println();
//        System.out.println("Tiles Added to bag: " + hypotheticalHand);
//        System.out.println("Num of tiles in bag: "+ bag1.getBagSize());

    }
}