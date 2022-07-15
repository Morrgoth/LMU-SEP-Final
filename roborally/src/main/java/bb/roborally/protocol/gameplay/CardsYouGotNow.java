package bb.roborally.protocol.gameplay;

import bb.roborally.protocol.Envelope;
import bb.roborally.protocol.Message;

public class CardsYouGotNow implements Message {

    private String[] cards;

    public CardsYouGotNow() {}

    public CardsYouGotNow(String[] cards) {
        this.cards = cards;
    }

    public String[] getCards() {
        return cards;
    }

    public void setCards(String[] cards) {
        this.cards = cards;
    }

    @Override
    public String toJson() {
        return this.toEnvelope().toJson();
    }

    @Override
    public Envelope toEnvelope() {
        return new Envelope(Envelope.MessageType.CARDS_YOU_GOT_NOW, this);
    }
}
