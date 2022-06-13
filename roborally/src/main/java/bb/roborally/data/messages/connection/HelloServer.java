package bb.roborally.data.messages.connection;

import bb.roborally.data.messages.Envelope;
import bb.roborally.data.messages.Message;

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
    private String protocol = "Version 0.1";

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

    @Override
    public String toJson() {
        return toEnvelope().toJson();
    }

    @Override
    public Envelope toEnvelope() {
        return new Envelope(Envelope.MessageType.HELLO_SERVER, this);
    }
}
