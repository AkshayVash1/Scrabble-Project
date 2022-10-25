public class Command {

    public final static int PLACEMENT_ATTEMPT_SIZE = 2;

    private String wordAttempt;
    private String placementAttempt;
    private String action;

    public Command(String action, String wordAttempt, String placementAttempt) {
        this.wordAttempt = wordAttempt;
        this.placementAttempt = placementAttempt;
        this.action = action;
    }

    public String getWordAttempt() {
        return this.wordAttempt;
    }

    public String getPlacementAttempt() {
        return this.placementAttempt;
    }

    public String getAction() {
        return this.action;
    }

    public boolean getPlacementDirection() {
        /* Portion may be removed if exception handling already handled by other class */
        if (this.placementAttempt != null && this.wordAttempt != null) {
            return Character.isDigit(this.placementAttempt.charAt(0));
        } else {
            /* todo throw exception possibly */
            System.out.println("NULL PASSED IN");
        }

        return false;
    }

    public boolean hasAction()
    {
        return (this.action != null);
    }

    public boolean hasPlacementAttempt() {
        return (this.placementAttempt != null);
    }

    public boolean hasWordAttempt() {
        return (this.wordAttempt != null);
    }
}