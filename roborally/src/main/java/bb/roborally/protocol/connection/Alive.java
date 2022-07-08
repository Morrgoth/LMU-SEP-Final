package bb.roborally.protocol.connection;

import bb.roborally.protocol.Envelope;
import bb.roborally.protocol.Message;

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
