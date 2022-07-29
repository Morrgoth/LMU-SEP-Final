package bb.roborally.protocol.gameplay;

import bb.roborally.protocol.Envelope;
import bb.roborally.protocol.Message;

/**
 * @author Zeynab Baiani
 */
public class CurrentPlayer implements Message {

    public int clientID;

    public CurrentPlayer(){
    }

    public CurrentPlayer(int clientID) {
        this.clientID = clientID;
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
        return new Envelope(Envelope.MessageType.CURRENT_PLAYER, this);
    }

}
