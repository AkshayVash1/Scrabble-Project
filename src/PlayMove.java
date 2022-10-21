public class PlayMove {

    private String wordAttempt;
    private String placementAttempt;
    private Board board;

    public PlayMove(String wordAttempt, String placementAttempt, Board board)
    {
        this.wordAttempt = wordAttempt;
        this.placementAttempt = placementAttempt;
        this.board = board;
    }

    public String getWordAttempt()
    {
        return this.wordAttempt;
    }

    public String getPlacementAttempt()
    {
        return this.placementAttempt;
    }

    public boolean getPlacementDirection()
    {
        boolean rc = false;

        if ((rc = Character.isDigit(this.placementAttempt.charAt(0)) == true))
        {
            rc = this.placeHorizontally();
        }
        else if ((rc = Character.isDigit(this.placementAttempt.charAt(1)) == true))
        {
            rc = this.placeVertically();
        }

        return rc;
    }

    public boolean hasPlacementAttempt()
    {
        return (this.placementAttempt != null);
    }

    public boolean hasWordAttempt()
    {
        return (this.wordAttempt != null);
    }

    /*Requires Board Implementation to continue */
    private boolean placeHorizontally()
    {
        return true;
    }

    /*Requires Board Implementation to continue */
    private boolean placeVertically()
    {
        return true;
    }
}
