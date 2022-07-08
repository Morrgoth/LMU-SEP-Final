package bb.roborally.protocol.gameplay;

import bb.roborally.protocol.Envelope;
import bb.roborally.protocol.Message;

/**
 * @author Veronika Heckel
 */
public class ShuffleCoding implements Message {

    private int clientID;


    public ShuffleCoding(){

    }

    public ShuffleCoding (int clientID){
        this.clientID = clientID;
    }


    public int getClientID() {
        return clientID;
    }

    public void setClientID(int clientID) {
        this.clientID = clientID;
    }


    @Override
    public String toJson() {
        return toEnvelope().toJson();
    }

    @Override
    public Envelope toEnvelope() {
        return new Envelope(Envelope.MessageType.SHUFFLE_CODING, this);
    }
}
