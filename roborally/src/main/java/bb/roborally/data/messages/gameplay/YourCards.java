package bb.roborally.data.messages.gameplay;

import bb.roborally.data.messages.Envelope;
import bb.roborally.data.messages.Message;
import bb.roborally.game.cards.PlayingCard;

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

