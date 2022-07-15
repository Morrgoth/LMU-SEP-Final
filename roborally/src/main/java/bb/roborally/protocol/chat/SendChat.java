package bb.roborally.protocol.chat;

import bb.roborally.protocol.Envelope;
import bb.roborally.protocol.Message;

public class SendChat implements Message {

    private String message;
    private int to;

    public SendChat() {
    }

    public SendChat(String message, int to) {
        this.message = message;
        this.to = to;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getTo() {
        return to;
    }

    public void setTo(int to){
        this.to = to;
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
