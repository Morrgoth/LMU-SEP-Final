package bb.roborally.data.messages.gameplay;

import bb.roborally.data.messages.Envelope;
import bb.roborally.data.messages.Message;

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
