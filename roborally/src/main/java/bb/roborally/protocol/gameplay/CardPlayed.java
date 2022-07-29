package bb.roborally.protocol.gameplay;

import bb.roborally.protocol.Envelope;
import bb.roborally.protocol.Message;

/**
 * @author Zeynab Baiani
 */
public class CardPlayed implements Message {

    public int clientID;
    public String card;

    public CardPlayed(int clientID, String card){
        this.clientID = clientID;
        this. card = card;
    }

    public CardPlayed() {

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
