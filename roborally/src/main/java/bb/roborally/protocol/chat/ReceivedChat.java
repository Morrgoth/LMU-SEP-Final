package bb.roborally.protocol.chat;

import bb.roborally.protocol.Envelope;
import bb.roborally.protocol.Message;

public class ReceivedChat implements Message {

    private String message;
    private int from;
    private boolean isPrivate;

    public ReceivedChat(String message, int from, boolean isPrivate ) {
        this.message = message;
        this.from = from;
        this.isPrivate = isPrivate;
    }

    public ReceivedChat() {

    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setFrom(int from) {
        this.from = from;
    }

    public int getFrom() {
        return from;
    }

    public boolean isPrivate() {
        return isPrivate;
    }

    public void setPrivate(boolean aPrivate) {
        isPrivate = aPrivate;
    }

    @Override
    public String toJson() {
        return toEnvelope().toJson();
    }

    @Override
    public Envelope toEnvelope() {
        return new Envelope(Envelope.MessageType.RECEIVED_CHAT, this);
    }
}
