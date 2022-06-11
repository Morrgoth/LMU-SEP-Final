package bb.roborally.data.messages.chat;

import bb.roborally.data.messages.Envelope;
import bb.roborally.data.messages.Message;

public class SendChat implements Message {

    private String message;
    private int to;
    private int from;
    private boolean isPrivate;

    public SendChat() {
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getFrom() {
        return from;
    }

    public void setFrom(int from) {
        this.from = from;
    }

    public int getTo() {
        return to;
    }

    public void setTo(int to){
        this.to = to;
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
        return new Envelope(Envelope.MessageType.SEND_CHAT, this);
    }
}
