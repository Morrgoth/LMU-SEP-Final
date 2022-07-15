package bb.roborally.protocol.game_events;

import bb.roborally.protocol.Envelope;
import bb.roborally.protocol.Message;

public class RebootDirection implements Message {
    private String direction;

    public RebootDirection() {
    }

    public RebootDirection(String direction) {
        this.direction = direction;
    }
    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }


    @Override
    public String toJson() {
        return toEnvelope().toJson();
    }

    @Override
    public Envelope toEnvelope() {
        return new Envelope(Envelope.MessageType.REBOOT_DIRECTION, this);
    }
}
