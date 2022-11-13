import jdk.jfr.Event;

import java.util.EventObject;

public class ScrabbleEvent extends EventObject {
    /**
     *
     * @param
     * @throws
     */
    public ScrabbleEvent(Game game) {
        super(game);
    }
}
