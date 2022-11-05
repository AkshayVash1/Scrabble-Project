import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

/**
 * A square is added to each cell of the Scrabble board. Each square has one of five Multiplier types,
 * 4 representing Scrabble's premium squares and 1 representing regular squares.
 *
 * @author Mahtab Ameli
 * @date 2022-10-25
 * @version 0.0
 */
public class Square {

    /**
     * Multiplier type for premium squares of the board.
     */
    public enum Multiplier {
        DL(2, "\033[46m", 0XB0E0E6),    // Double Letter, multiplies by 2, background color cyan
        TL(3, "\033[46m", 0X6495ED),    // Triple Letter, multiplies by 3, background color blue
        DW(2, "\033[46m", 0X9370DB),    // Double Word, multiplies by 2, background color purple
        TW(3, "\033[46m", 0XF08080),    // Triple Word, multiplies by 3, background color red
        NONE(1, "\033[46m", 0XFFFAF0);// not a premium square, multiplies by 1, text color bold white

        /**
         * Int value of each Multiplier type.
         */
        private final int value;

        /**
         * Print color value corresponding to Multiplier's name.
         */
        private final String color;

        /**
         * Print RGB color value corresponding to Multiplier's name.
         */
        private final int RGB_color;

        /**
         * Constructor for the enum.
         *  @param value Multiplier's int value.
         */
        Multiplier(int value, String color, int RGB_color) {
            this.value = value;
            this.color = color;
            this.RGB_color = RGB_color;
        }

        /**
         * Getter for the Multiplier value.
         *
         * @return Multiplier value.
         */
        public int getValue() {return value; }

        /**
         * Getter for color code of the Multiplier.
         *
         * @return Multiplier color code.
         */
        public String getColor() {return color; }

        public int getRGB_color() {return RGB_color; }
    }

    private int row;
    private int col;
    private Multiplier multiplier;
    public static HashMap<Integer,String> columns;

    /**
     * Constructor for the class Square.
     * Creates a Square when both row column are passed separately as int values.
     */
    public Square(Integer row, Integer col) {
        // todo errors for unacceptable inputs
        this.row = row;
        this.col = col;
        columns = new HashMap<>();
        initializeColumns();
        assignMultiplier();
    }

    /**
     * Assigns String letter values to integer values for all columns of the board.
     */
    private void initializeColumns() {
        columns.put(1,"A"); columns.put(2,"B"); columns.put(3,"C");
        columns.put(4,"D"); columns.put(5,"E"); columns.put(6,"F");
        columns.put(7,"G"); columns.put(8,"H"); columns.put(9,"I");
        columns.put(10,"J"); columns.put(11,"K"); columns.put(12,"L");
        columns.put(13,"M"); columns.put(14,"N"); columns.put(15,"O");
    }

    /**
     * Returns the coordinates of the square as a string of the row as a digit, followed by the column as a letter .
     *
     * @return this square's coordinates as a string.
     */
    public String getStringCoordinates() {
        String stringCoordinates = "" + row + columns.get(col);
        return stringCoordinates;
    }

    /**
     * Getter for the square's multiplier.
     *
     * @return this square's multiplier.
     */
    public Multiplier getMultiplier() {
        return this.multiplier;
    }


    /**
     * Assigns this square's multiplier based on the square's coordinates. Called from the constructor.
     */
    private void assignMultiplier() {
        String coordinates = "" + row + columns.get(col);

        ArrayList<String> DL_coordsList = new ArrayList<>(Arrays.asList(
                new String[]{"1D","1L","3G","3I","4A","4H","4O","7C","7G","7I","7M","8D","8L"
                        ,"9C","9G","9I","9M","12A","12H","12O","13G","13I","15D","15L"}));
        ArrayList<String> TL_coordsList = new ArrayList<>(Arrays.asList(
                new String[]{"2F","2J","6B","6F","6J","6N","10B","10F","10J","10N","14F","14J"}));
        ArrayList<String> DW_coordsList = new ArrayList<>(Arrays.asList(
                new String[]{"2B","2N","3C","3M","4D","4L","5E","5K","8H","11E","11K","12D","12L","13C","13M","14B","14N"}));
        ArrayList<String> TW_coordsList = new ArrayList<>(Arrays.asList(
                new String[]{"1A","1H","1O","8A","8O","15A","15H","15O"}));

        if (DL_coordsList.contains(coordinates)) {
            this.multiplier = Multiplier.DL;
        }
        else if (TL_coordsList.contains(coordinates)) {
            this.multiplier = Multiplier.TL;
        }
        else if (DW_coordsList.contains(coordinates)) {
            this.multiplier = Multiplier.DW;
        }
        else if (TW_coordsList.contains(coordinates)) {
            this.multiplier = Multiplier.TW;
        }
        else {
            this.multiplier = Multiplier.NONE;
        }
    }

}