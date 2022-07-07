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

    public static ArrayList<PlayingCard> toPlayingCards(String[] cards) {
        ArrayList<PlayingCard> hand = new ArrayList<>();
        for (String cardName: cards) {
            if (cardName.equals("Again")) {
                hand.add(new Again());
            } else if (cardName.equals("BackUp")) {
                hand.add(new BackUp());
            } else if (cardName.equals("MoveI")) {
                hand.add(new Move1());
            } else if (cardName.equals("MoveII")) {
                hand.add(new Move2());
            } else if (cardName.equals("MoveIII")) {
                hand.add(new Move3());
            } else if (cardName.equals("PowerUp")) {
                hand.add(new PowerUp());
            } else if (cardName.equals("Spam")) {
                hand.add(new Spam());
            } else if (cardName.equals("Trojan")) {
                hand.add(new Trojan());
            } else if (cardName.equals("TurnLeft")) {
                hand.add(new TurnLeft());
            } else if (cardName.equals("TurnRight")) {
                hand.add(new TurnRight());
            } else if (cardName.equals("UTurn")) {
                hand.add(new UTurn());
            } else if (cardName.equals("Virus")) {
                hand.add(new Virus());
            } else if (cardName.equals("Worm")) {
                hand.add(new Worm());
            }
        }
        return hand;
    }

    //public Image getResource();
}
