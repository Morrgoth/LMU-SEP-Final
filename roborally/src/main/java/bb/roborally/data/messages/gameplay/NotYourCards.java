package bb.roborally.data.messages.gameplay;

import bb.roborally.data.messages.Envelope;
import bb.roborally.data.messages.Message;

/**
 * @author Veronika Heckel
 */
public class NotYourCards implements Message {

    private int clientID;
    private int cardsInHand;

    public NotYourCards(){

    }

    public NotYourCards (int clientID, int cardsInHand){
        this.clientID = clientID;
        this.cardsInHand = cardsInHand;
    }

    public int getClientID() {
        return clientID;
    }

    public void setClientID(int clientID) {
        this.clientID = clientID;
    }

    public int getCardsInHand() {
        return cardsInHand;
    }

    public void setCardsInHand(int cardsInHand) {
        this.cardsInHand = cardsInHand;
    }

    @Override
    public String toJson() {
        return toEnvelope().toJson();
    }

    @Override
    public Envelope toEnvelope() {
        return new Envelope(Envelope.MessageType.NOT_YOUR_CARDS, this);
    }
}
