package bb.roborally.game.cards;


/**
 * general Interface for all kinds of Cards - makes it possible to create a general CardDeck class
 * @author Veronika Heckel
 * @author Muqiu Wang
 * @author Tolga Engin
 * @author Zeynab Baiani
 * @author Bence Ament
 * @author  Philipp Keyzman
 */
public abstract class PlayingCard {
    private boolean discarded = false;
    private boolean active = false;

    public abstract String getName();

    public boolean isDiscarded() {
        return discarded;
    }

    public void setDiscarded(boolean discarded) {
        this.discarded = discarded;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public boolean isAvailable() {
        return !discarded && !active;
    }

    //public Image getResource();
}
