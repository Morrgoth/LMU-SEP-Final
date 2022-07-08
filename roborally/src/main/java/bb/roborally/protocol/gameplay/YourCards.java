package bb.roborally.protocol.gameplay;

import bb.roborally.protocol.Envelope;
import bb.roborally.protocol.Message;
import bb.roborally.server.game.cards.PlayingCard;

import java.util.ArrayList;

/**
 * @author Veronika Heckel
 */
public class YourCards implements Message {

    private String[] cardsInHand;

    public YourCards() {}

    public YourCards(String[] cardsInHand) {
        this.cardsInHand = cardsInHand;
    }

    public YourCards(ArrayList<PlayingCard> hand) {
        String[] cardsInHand = new String[hand.size()];
        int index = 0;
        for (PlayingCard card: hand) {
            cardsInHand[index] = card.getName();
            index += 1;
        }
        this.cardsInHand = cardsInHand;
    }

    public String[] getCardsInHand() {
        return cardsInHand;
    }

    public void setCardsInHand(String[] cardsInHand) {
        this.cardsInHand = cardsInHand;
    }

    @Override
    public String toJson() {
        return this.toEnvelope().toJson();
    }

    @Override
    public Envelope toEnvelope() {
        return new Envelope(Envelope.MessageType.YOUR_CARDS, this);
    }
}

