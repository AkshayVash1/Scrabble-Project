import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

/**
 * An instance of BoardPanel represents the state of the Scrabble board in the GUI.
 * The boardPanel gets updated following each valid play move.
 *
 * @author Mahtab Ameli
 * @date 2022-11-04
 * @version 0.0
 */

public class BoardPanel extends JPanel{

    /**
     * Cells of the Scrabble board.
     */
    private JPanel cells[][];

    /**
     * Temporary frame to hold and test the BoardPanel, before adding the panel to the game's main GUI's frame.
     */
    JFrame boardFrame;

    /**
     * Constructor for the class.
     */
    public BoardPanel() {
        cells = new JPanel[15][15];

        this.setSize(600,600);
        this.setLayout(new GridLayout(15, 15));
        this.setBorder(new LineBorder(Color.BLACK));
        this.addCells();

        // create boardFrame
        this.initializeBoardFrame();
        boardFrame.add(this);
    }


    /**
     * Creates and initializes the temporary boardFrame which will contain BoardPanel.
     */
    private void initializeBoardFrame() {
        boardFrame = new JFrame("Welcome to Scrabble!");
        boardFrame.setSize(600,600);
        boardFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        boardFrame.setVisible(true);
        boardFrame.revalidate();
    }

    /**
     * Creates and add the 15*15 JPanel cells, which hold labels indicating the square type
     */
    private void addCells() {
        for (int row = 1; row < 16; row++) {
            for  (int col = 1; col < 16; col++) {

                Square thisSquare = new Square(row, col);

                JPanel cell = new JPanel();
                cell.setLayout(new BorderLayout());

                // set the cell's background color according to square's multiplier type
                cell.setBackground(new Color(thisSquare.getMultiplier().getRGB_color()));
                cell.setBorder(new LineBorder(Color.BLACK));

                // create a label for the cell to indicate premium square's multiplier type
                JLabel cellLabel = createCellLabel(thisSquare);

                // add label to cell
                cell.add(cellLabel, BorderLayout.CENTER);

                // add cell to this panel
                this.add(cell);
            }
        }
    }


    /**
     * Creates a label for given square, marking the multiplier type for premium squares.
     * @param square
     * @return
     */
    private JLabel createCellLabel(Square square) {
        JLabel label = new JLabel();

        // center the label's alignment
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setVerticalAlignment(SwingConstants.CENTER);

        // if the square is not premium, set text as a blank space
        if (square.getMultiplier().equals(Square.Multiplier.NONE)) {
            label.setText(" ");
        }
        // if square is a premium, set the cell's label as the multiplier type
        else {
            label.setText(String.valueOf(square.getMultiplier()));
            label.setFont(new Font(Font.MONOSPACED, Font.BOLD,18));
        }
        return label;
    }

    public static void main(String[] args) {
        BoardPanel boardPanel = new BoardPanel();
    }
}
