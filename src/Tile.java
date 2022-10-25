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

    public void setLetter(String letter)
    {
        this.letter = letter;
    }

    @Override
    public String toString() {
        return "{Letter: "+letter+", Points: "+value+"}";
    }
}
