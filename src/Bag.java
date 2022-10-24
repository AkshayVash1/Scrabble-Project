import java.util.*;

public class Bag {

    final private LinkedList<Tile> tiles = new LinkedList<>();
    private List<String> numOfLetters = Arrays.asList("A-9", "B-2", "C-2", "D-4", "E-12", "F-2", "G-3", "H-2", "I-9", "J-1",
            "K-1", "L-4", "M-2", "N-6", "O-8", "P-2", "Q-1", "R-6", "S-4", "T-6", "U-4", "V-2", "W-2", "X-1", "Y-2", "Z-1");
    private Random random = new Random();


    /**
     * Constructs Bag containing 100 tiles.
     */
    public Bag() {
        initialize();
    }


    /**
     * Initializes all letters with corresponding values and places it into tiles.
     */
    private void initialize() {
        final Map<Integer, List<String>> pointLetterMap = new HashMap<>();
        pointLetterMap.put(1, Arrays.asList("A", "E", "I", "O", "U", "L", "N", "S", "T", "R"));
        pointLetterMap.put(2, Arrays.asList("D", "G"));
        pointLetterMap.put(3, Arrays.asList("B", "C", "M", "P"));
        pointLetterMap.put(4, Arrays.asList("F", "H", "V", "W", "Y"));
        pointLetterMap.put(5, Arrays.asList("K"));
        pointLetterMap.put(8, Arrays.asList("J", "X"));
        pointLetterMap.put(10, Arrays.asList("Q", "Z"));

        final Map<String, Integer> eachLetterPointMap = new HashMap<>();

        for (Map.Entry<Integer, List<String>> entry : pointLetterMap.entrySet()) {
            for (String letter : entry.getValue()) {
                eachLetterPointMap.put(letter, entry.getKey());
            }
        }

        for (String numOfLetter : numOfLetters) {
            String[] token = numOfLetter.split("-");
            for (int i = 0; i < Integer.parseInt(token[1]); i++) {
                tiles.add(new Tile(token[0], eachLetterPointMap.get(token[0])));
            }
        }
        for (int i = 0; i < 2; i++) {
            tiles.add(new Tile("BLANK", null));
        }
    }

    /**
     * Gets bag size.
     * @return amount of tiles within bag
     */
    public int getBagSize() {
        return tiles.size();
    }

    public List<Tile> removeTiles(int amount) throws NotEnoughTiles {
        List<Tile> removedTiles = new ArrayList<>();
        if (amount > getBagSize()) {
            throw new NotEnoughTiles("Not Enough Tiles, Only "+getBagSize()+" Tiles Left.");
        }
        for (int i = 0; i < amount; i++) {
            removedTiles.add(tiles.remove(random.nextInt(getBagSize())));
        }

        return removedTiles;
    }

    /**
     * Adds tiles given back into bag.
     * @param tiles The subset of Tile objects to be placed into bag.
     */
    public void placeTiles(List<Tile> tiles) {
        for (Tile tile : tiles) {
            this.tiles.add(tile);
        }
    }

    /**
     * Prints all tiles within bag.
     */
    public void print() {
        for (Tile tile : tiles) {
            System.out.println(tile);
        }

    }
}
