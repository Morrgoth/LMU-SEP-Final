package bb.roborally.protocol.connection;

import bb.roborally.protocol.Envelope;
import bb.roborally.protocol.Message;

/**
 * @author Veronika Heckel
 * @author Muqiu Wang
 * @author Zeynab Baiani
 * @author Bence Ament
 * @author  Philipp Keyzman
 */
public class Welcome implements Message {
    private int clientID;

    public Welcome(){

    }

    public Welcome(int clientID){
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
        return new Envelope(Envelope.MessageType.WELCOME, this);
    }
}
