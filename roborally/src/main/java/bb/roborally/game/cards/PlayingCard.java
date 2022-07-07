package bb.roborally.game.cards;


import java.util.ArrayList;

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
    private boolean marked = false;

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

    public static PlayingCard fromString(String cardName) {
        if (cardName.equals("Again")) {
            return new Again();
        } else if (cardName.equals("BackUp")) {
            return new BackUp();
        } else if (cardName.equals("MoveI")) {
            return new Move1();
        } else if (cardName.equals("MoveII")) {
            return new Move2();
        } else if (cardName.equals("MoveIII")) {
            return new Move3();
        } else if (cardName.equals("PowerUp")) {
            return new PowerUp();
        } else if (cardName.equals("Spam")) {
            return new Spam();
        } else if (cardName.equals("Trojan")) {
            return new Trojan();
        } else if (cardName.equals("TurnLeft")) {
            return new TurnLeft();
        } else if (cardName.equals("TurnRight")) {
            return new TurnRight();
        } else if (cardName.equals("UTurn")) {
            return new UTurn();
        } else if (cardName.equals("Virus")) {
            return new Virus();
        } else if (cardName.equals("Worm")) {
            return new Worm();
        } else {
            return null;
        }
    }

    public static ArrayList<PlayingCard> toPlayingCards(String[] cards) {
        ArrayList<PlayingCard> hand = new ArrayList<>();
        for (String cardName: cards) {
            hand.add(fromString(cardName));
        }
        return hand;
    }

    @Override
    public String toString() {
        return getName();
    }

    public boolean isMarked() {
        return marked;
    }

    public void setMarked(boolean marked) {
        this.marked = marked;
    }

    //public Image getResource();
}
