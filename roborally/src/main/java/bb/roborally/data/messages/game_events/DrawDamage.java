package bb.roborally.data.messages.game_events;

import bb.roborally.data.messages.Envelope;
import bb.roborally.data.messages.Message;

public class DrawDamage implements Message {
    @Override
    public String toJson() {
        return toEnvelope().toJson();
    }

    @Override
    public Envelope toEnvelope() {
        return null;
    }
}
