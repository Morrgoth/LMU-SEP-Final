package bb.roborally.data.messages.connection;

import bb.roborally.data.messages.Envelope;
import bb.roborally.data.messages.Message;

public class HelloClient implements Message {
    private String protocol = "Version 0.1";

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
