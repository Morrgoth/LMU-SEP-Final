package bb.roborally.protocol.gameplay;

import bb.roborally.protocol.Envelope;
import bb.roborally.protocol.Message;

/**
 * @author Bence Ament
 */
public class TimerStarted implements Message {
    public TimerStarted() {}

    @Override
    public String toJson() {
        return toEnvelope().toJson();
    }

    @Override
    public Envelope toEnvelope() {
        return new Envelope(Envelope.MessageType.TIMER_STARTED, this);
    }
}
