package bb.roborally.protocol.gameplay;

import bb.roborally.protocol.Envelope;
import bb.roborally.protocol.Message;

public class TimerEnded implements Message {
    private int[] clientIDs;

    public TimerEnded() {}

    public TimerEnded(int[] clientIDs) {
        this.clientIDs = clientIDs;
    }

    public int[] getClientIDs() {
        return clientIDs;
    }

    public void setClientIDs(int[] clientIDs) {
        this.clientIDs = clientIDs;
    }

    @Override
    public String toJson() {
        return toEnvelope().toJson();
    }

    @Override
    public Envelope toEnvelope() {
        return new Envelope(Envelope.MessageType.TIMER_ENDED, this);
    }
}
