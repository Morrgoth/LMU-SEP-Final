package bb.roborally.protocol.game_events;

import bb.roborally.protocol.Envelope;
import bb.roborally.protocol.Message;

/**
 * @author Muqiu Wang
 */
public class Animation implements Message {
    private String type;

    public Animation(String type) {
        this.type = type;
    }
    public Animation(){}
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toJson() {
        return toEnvelope().toJson();
    }

    @Override
    public Envelope toEnvelope() {
        return new Envelope(Envelope.MessageType.ANIMATION, this);
    }
}
