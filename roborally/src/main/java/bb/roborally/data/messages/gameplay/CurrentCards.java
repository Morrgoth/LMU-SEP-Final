package bb.roborally.data.messages.gameplay;

import bb.roborally.data.messages.Envelope;
import bb.roborally.data.messages.Message;

import java.util.HashMap;

public class CurrentCards implements Message {

    private HashMap<Integer, String> activeCards;

    public CurrentCards() {}

    public CurrentCards(HashMap<Integer, String> activeCards) {
        this.activeCards = activeCards;
    }

    public HashMap<Integer, String> getActiveCards() {
        return activeCards;
    }

    public void setActiveCards(HashMap<Integer, String> activeCards) {
        this.activeCards = activeCards;
    }

    @Override
    public String toJson() {
        return this.toEnvelope().toJson();
    }

    @Override
    public Envelope toEnvelope() {
        return new Envelope(Envelope.MessageType.CURRENT_CARDS, this);
    }
}
