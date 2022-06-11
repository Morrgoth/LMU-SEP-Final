package bb.roborally.data.messages.chat;

import bb.roborally.data.messages.Envelope;
import bb.roborally.data.messages.Message;

public class ReceivedChat implements Message {

    private String message;
    private int to;
    private int from;
    private boolean isPrivate;

    @Override
    public String toJson() {
        return toEnvelope().toJson();
    }

    @Override
    public Envelope toEnvelope() {
        return null;
    }
}
