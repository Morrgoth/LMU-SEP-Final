package bb.roborally.data.messages.connection;

import bb.roborally.data.messages.Envelope;
import bb.roborally.data.messages.Message;

public class Alive implements Message {
    public Alive(){

    }

    @Override
    public String toJson() {
        return this.toEnvelope().toJson();
    }

    @Override
    public Envelope toEnvelope() {
        return new Envelope(Envelope.MessageType.ALIVE, this);
    }
}
