package bb.roborally.data.messages.gameplay;

import bb.roborally.data.messages.Envelope;
import bb.roborally.data.messages.Message;

/**
 * @author Veronika Heckel
 */
public class YourCards implements Message {


    private String[] cardsInHand;

    public YourCards() {}

    public YourCards(String[] cardsInHand) {
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

