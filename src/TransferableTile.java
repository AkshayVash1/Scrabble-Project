import java.awt.*;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;

public class TransferableTile implements Transferable {

    public static final DataFlavor tileFlavor =
            new DataFlavor(Tile.class, "Tile");


    public static final DataFlavor[] supportedFlavors = {
            tileFlavor
    };

    private final Tile tile;

    public TransferableTile(Tile tile) {

        this.tile = tile;
    }

    @Override
    public DataFlavor[] getTransferDataFlavors() {
        return supportedFlavors;
    }

    @Override
    public boolean isDataFlavorSupported(DataFlavor dataFlavor) {
        return dataFlavor.equals(tileFlavor) ||
                dataFlavor.equals(DataFlavor.stringFlavor);
    }

    @Override
    public Object getTransferData(DataFlavor flavor) {
        return this.tile;
    }
}