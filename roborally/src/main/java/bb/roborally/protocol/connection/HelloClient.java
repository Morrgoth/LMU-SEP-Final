package bb.roborally.protocol.connection;

import bb.roborally.protocol.Envelope;
import bb.roborally.protocol.Message;

/**
 * @author Veronika Heckel
 */
public class HelloClient implements Message {
    private String protocol = "Version 1.0";

    public HelloClient(){
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
        return new Envelope(Envelope.MessageType.HELLO_CLIENT, this);
    }
}
