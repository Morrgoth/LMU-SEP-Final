package bb.roborally.data.messages.gameplay;

import bb.roborally.data.messages.Envelope;
import bb.roborally.data.messages.Message;

public class CardPlayed implements Message {

    public int clientID;
    public String card = "MoveI";

    public CardPlayed(){
    }

    public void setCard(String card) {
        this.card = card;
    }

    public String getCard() {
        return card;
    }

    public void setClientID(int clientID) {
        this.clientID = clientID;
    }

    public int getClientID() {
        return clientID;
    }


    @Override
    public String toJson() {
        return toEnvelope().toJson();
    }

    @Override
    public Envelope toEnvelope() {
        return new Envelope(Envelope.MessageType.CARD_PLAYED, this);
    }
}
