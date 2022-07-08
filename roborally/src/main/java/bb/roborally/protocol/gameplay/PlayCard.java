package bb.roborally.protocol.gameplay;

import bb.roborally.protocol.Envelope;
import bb.roborally.protocol.Message;

public class PlayCard implements Message {

    public String card = "MoveI";

    public PlayCard(){
    }

    public String getCard() {
        return card;
    }

    public void setCard(String card) {
        this.card = card;
    }

    @Override
    public String toJson() {
        return toEnvelope().toJson();
    }

    @Override
    public Envelope toEnvelope() {
        return new Envelope(Envelope.MessageType.PLAY_CARD, this);
    }
}
