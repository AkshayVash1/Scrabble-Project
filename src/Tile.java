public class Tile {
    private String letter;
    private Integer value;

    public Tile(String letter, Integer value) {
        this.letter = letter;
        this.value = value;
    }

    public String getLetter() {
        return letter;
    }

    public Integer getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "{Letter: "+letter+", Points: "+value+"}";
    }
}
