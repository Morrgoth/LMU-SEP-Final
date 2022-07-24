package bb.roborally.protocol.map;

import bb.roborally.protocol.Envelope;
import bb.roborally.protocol.Message;

public record GameStarted(Board board) implements Message {

    @Override
    public String toJson() {
        return toEnvelope().toJson();
    }

    @Override
    public Envelope toEnvelope() {
        return new Envelope(Envelope.MessageType.GAME_STARTED, this);
    }
}
