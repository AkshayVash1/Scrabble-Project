/**
 * An instance of BoardPanel represents the state of the Scrabble board in the GUI.
 * The boardPanel gets updated following each valid play move.
 *
 * @author Mahtab Ameli
 * @date 2022-11-13
 * @version 0.0
 */

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

public class BoardPanel extends JPanel implements ScrabbleView{

    private Game game;

    /**
     * Cells of the Scrabble board.
     */
    private JPanel cells[][];
    private Board board;

    /**
     * Constructor for the class.
     */
    public BoardPanel(Game game) {
        this.game = game;
        this.game.addScrabbleView(this);
        this.board = new Board();

        cells = new JPanel[16][16];
        final int BOARD_SIZE = 600;
        this.setPreferredSize(new Dimension(BOARD_SIZE, BOARD_SIZE));
        this.setLayout(new GridLayout(cells.length, cells.length));
        this.setBorder(new LineBorder(Color.BLACK));
        this.addCells();
    }


    /**
     * Creates and add the 15*15 JPanel cells, which hold labels indicating the square type
     */
    private void addCells() {
        for (int row = 1; row < 16; row++) {
            for (int col = 1; col < 16; col++) {

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

                this.cells[row][col] = cell;

                // add cell to this panel
                this.add(cell);
            }
        }
    }

    /**
     * Creates a label for given square, to indicate the multiplier type for premium squares as label text.
     *
     * @param square
     * @return
     */
    private JLabel createCellLabel(Square square) {
        JLabel label = new JLabel();
        BoardDropTargetController dtl = new BoardDropTargetController(label, square.getStringCoordinates(), this.game);

        // center the label's alignment
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setVerticalAlignment(SwingConstants.CENTER);
        label.setFont(new Font(Font.MONOSPACED, Font.BOLD, 18));
        label.setTransferHandler(new TransferHandler("text"));

        // if the square is not premium, set label's text as a blank space
        if (square.getMultiplier().equals(Square.Multiplier.NONE)) {
            label.setText(" ");
        }
        // if square is a premium, set label's text as the multiplier type
        else {
            // if square is the one at the centre of the board (game start square), set label text as a star
            if (square.isCentreSquare()) {
                //label.setIcon(new ImageIcon("src/star_icon.png"));
                label.setText("*");
            } else {
                label.setForeground(Color.white);
                label.setText(String.valueOf(square.getMultiplier()));
            }
        }
        return label;
    }

    /**
     * helper method that iterates through the rows and columns of the board and updates each square with
     * the respective value of the model board.
     * */
    private void updateBoard()
    {
        for (int row = 1; row < 16; row++) {
            for (int col = 1; col < 16; col++) {
                for (Component jc : this.cells[row][col].getComponents()) {
                    if ( jc instanceof JLabel ) {
                        ((JLabel) jc).setText(this.board.getLetterAtSquare(row, col));
                    }
                }
            }
        }
    }

    /**
     * Inherited from the ScrabbleView interface to update the state of the board in various different locations
     * within the model.
     * @return none
     * */
    @Override
    public void update(Player currentPlayer, Board board, boolean status) {
        this.board = board;
        updateBoard();
    }
}