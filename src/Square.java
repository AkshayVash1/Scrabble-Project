import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;


public class Square {

    public enum Multiplier {
        DL(2, "\033[46m"),    // Double Letter, multiplies by 2, color cyan
        TL(3, "\033[44m"),    // Triple Letter, multiplies by 3, color blue
        DW(2, "\033[45m"),    // Double Word, multiplies by 2, color purple
        TW(3, "\033[41m"),    // Triple Word, multiplies by 3, color red
        NONE(1, "\u001B[37m");

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
    private Multiplier multiplier;
    public static HashMap<Integer,String> columns;

    /**
     * constructor 1
     * creates a Square when both row column are passed separately as int values
     */
    public Square(Integer row, Integer col) {
        // todo errors for unacceptable inputs
        this.row = row;
        this.col = col;
        columns = new HashMap<>();
        initializeColumns();
        assignMultiplier();
    }

    private void initializeColumns() {
        //todo error checking
        columns.put(1,"A"); columns.put(2,"B"); columns.put(3,"C");
        columns.put(4,"D"); columns.put(5,"E"); columns.put(6,"F");
        columns.put(7,"G"); columns.put(8,"H"); columns.put(9,"I");
        columns.put(10,"J"); columns.put(11,"K"); columns.put(12,"L");
        columns.put(13,"M"); columns.put(14,"N"); columns.put(15,"O");
    }


    // return's square's coordinates as a string of a row as a number and column as a letter
    public String getStringCoordinates() {
        String stringCoordinates = "" + row + columns.get(col);
        return stringCoordinates;
    }

    public Multiplier getMultiplier() {
        return this.multiplier;
    }


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


    /**
     * constructor 2
     * creates a Square when row and column are passed as a single 2-character string,
     * EXAMPLE: "9A" is the square with row 9, col A
     * Convention: first character is the row, second character is the column
     * handles column both as a letter or a digit
     *
     */
/*    public Square(String coordinates) {
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

    }*/


}
