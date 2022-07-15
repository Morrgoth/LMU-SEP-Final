package bb.roborally.protocol.connection;

import bb.roborally.protocol.Envelope;
import bb.roborally.protocol.Message;

/**
 * @author Veronika Heckel
 *  * @author Muqiu Wang
 *  * @author Tolga Engin
 *  * @author Zeynab Baiani
 *  * @author Bence Ament
 *  * @autor  Philipp Keyzman
 */
public class HelloServer implements Message {
    private String group = "BlindeBonbons";
    private boolean isAI;
    private String protocol = "Version 1.0";
    private int clientID;

    public HelloServer(){
    }

    public HelloServer(boolean isAI){
        this.isAI = isAI;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public boolean isAI() {
        return isAI;
    }

    public void setAI(boolean AI) {
        isAI = AI;
    }

    public String getProtocol() {
        return protocol;
    }

    public void setProtocol(String protocol) {
        this.protocol = protocol;
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
        return new Envelope(Envelope.MessageType.HELLO_SERVER, this);
    }


}
