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

    public Integer getLetterValue(String letter) {
        if (letter.equals("A") || letter.equals("E") || letter.equals("I") || letter.equals("O") || letter.equals("U")
                || letter.equals("L") || letter.equals("N") || letter.equals("S") || letter.equals("T") || letter.equals("R")) {
            return 1;
        } else if (letter.equals("D") || letter.equals("G")) {
            return 2;
        } else if (letter.equals("B") || letter.equals("C") || letter.equals("M") || letter.equals("P")) {
            return 3;
        } else if (letter.equals("F") || letter.equals("H") || letter.equals("V") || letter.equals("W") || letter.equals("Y")) {
            return 4;
        } else if (letter.equals("K")) {
            return 5;
        } else if (letter.equals("J") || letter.equals("X")) {
            return 8;
        } else if (letter.equals("Q") || letter.equals("Z")) {
            return 10;
        }
        return 0;
    }

    @Override
    public String toString() {
        return "["+letter+", "+value+"]";
    }
}
