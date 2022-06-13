package bb.roborally.data.messages.connection;

import bb.roborally.data.messages.Envelope;
import bb.roborally.data.messages.Message;

/**
 * @author Veronika Heckel
 *  * @author Muqiu Wang
 *  * @author Zeynab Baiani
 *  * @author Bence Ament
 *  * @autor  Philipp Keyzman
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
