package bb.roborally.protocol.game_events;

import bb.roborally.protocol.Envelope;
import bb.roborally.protocol.Message;

public class DrawDamage implements Message {
    private int clientID;
    private String cards;

    public DrawDamage(int clientID, String cards){
        this.clientID = clientID;
        this.cards = cards;
    }
    public DrawDamage(){
    }

    public int getClientID() {
        return clientID;
    }

    public void setClientID(int clientID) {
        this.clientID = clientID;
    }

    public String getCards() {
        return cards;
    }

    public void setCards(String cards) {
        this.cards = cards;
    }

    @Override
    public String toJson() {
        return toEnvelope().toJson();
    }

    @Override
    public Envelope toEnvelope() {
        return new Envelope(Envelope.MessageType.DRAW_DAMAGE, this);
    }
}
