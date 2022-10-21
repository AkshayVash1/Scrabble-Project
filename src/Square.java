public class Square {

    public enum Column {
        A(1), B(2), C(3),
        D(4), E(5), F(6),
        G(7), H(8), I(9),
        J(10), K(11), L(12),
        M(13), N(14), O(15);

        final int ColumnDigit;

        Column(int ColumnDigit) {
            this.ColumnDigit = ColumnDigit;
        }

        public int getColumnDigit() {
            return ColumnDigit;
        }
    }

    public int getColAsInt(char c) {

        switch (c) {
            case 'A' | 'a': return Column.A.getColumnDigit();
            case 'B' | 'b': return Column.B.getColumnDigit();
            case 'C' | 'c': return Column.C.getColumnDigit();
            case 'D' | 'd': return Column.D.getColumnDigit();
            case 'E' | 'e': return Column.E.getColumnDigit();
            case 'F' | 'f': return Column.F.getColumnDigit();
            case 'G' | 'g': return Column.G.getColumnDigit();
            case 'H' | 'h': return Column.H.getColumnDigit();
            case 'I' | 'i': return Column.I.getColumnDigit();
            case 'J' | 'j': return Column.J.getColumnDigit();
            case 'K' | 'k': return Column.K.getColumnDigit();
            case 'L' | 'l': return Column.L.getColumnDigit();
            case 'M' | 'm': return Column.M.getColumnDigit();
            case 'N' | 'n': return Column.N.getColumnDigit();
            case 'O' | 'o': return Column.O.getColumnDigit();
            default: throw new IllegalArgumentException("There is no such column!");
        }
    }

    public enum Multiplier {
        DL(2, "\u001B[36m"),    // Double Letter, multiplies by 2, color cyan
        TL(3, "\u001B[34m"),    // Triple Letter, multiplies by 3, color blue
        DW(2, "\u001B[35m"),    // Double Word, multiplies by 2, color purple
        TW(3, "\u001B[31m");    // Triple Word, multiplies by 3, color red

        private final int value;
        private final String color;

        Multiplier(int value, String color) {
            this.value = value;
            this.color = color;
        }

        public int getValue() {return value; }
        public String getColor() {return color;}

    }


    private int row;
    private int col;
    private String multiplier;
    private String colorCode; // todo think if want to keep

    /**
     * constructor 1
     * creates a Square when both row column are passed separately as int values
     */
    public Square(Integer row, Integer col) {
        this.row = row;
        this.col = col;
    }

    /**
     * constructor 2
     * creates a Square when row and column are passed as a single 2-character string,
     * EXAMPLE: "9A" is the square with row 9, col A
     * Convention: first character is the row, second character is the column
     * handles column both as a letter or a digit
     *
     */
    public Square(String coordinates) {
        coordinates.trim(); // trim in case spaces are passed by mistake

        char rowChar = coordinates.charAt(0);
        char colChar = coordinates.charAt(1);

        // if column is passed as the standard letter instead of int, get its int ordinal value
        if (Character.isDigit(colChar)) {
            this.col = getColAsInt(colChar);
        }

        // gives error if row is passed as a letter
        if (!Character.isDigit(rowChar)) {
            System.out.println("Invalid input. The row must a number from 1 to 15");
        } else {
            this.row = (int) rowChar;
        }


    }

    //todo implement
   private Boolean isPremium(Square this) {
        return false;
   }

    // todo getDirection method

    // todo implement
    private Boolean isPremium(Square square) { return false; }




}
