package bb.roborally.data.messages.chat;

import bb.roborally.data.messages.Envelope;
import bb.roborally.data.messages.Message;

public class ReceivedChat implements Message {

    private String message;
    private int to;
    private int from;
    private boolean isPrivate;

    public ReceivedChat(String message, int to, int from, boolean isPrivate ) {
        this.message = message;
        this.to = to;
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

    public void setTo(int to) {
        this.to = to;
    }

    public int getTo() {
        return to;
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
