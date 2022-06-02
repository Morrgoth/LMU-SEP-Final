package bb.roborally.data.messages;

import bb.roborally.data.messages.type_adapters.EnvelopeTypeAdapter;

import java.io.IOException;

public class Envelope implements Message {
    private String messageType;
    private Message messageBody;

    public Envelope(String messageType, Message messageBody) {
        this.messageType = messageType;
        this.messageBody = messageBody;
    }

    public Envelope() {}

    public String getMessageType() {
        return messageType;
    }

    public void setMessageType(String messageType) {
        this.messageType = messageType;
    }

    public Message getMessageBody() {
        return messageBody;
    }

    public void setMessageBody(Message messageBody) {
        this.messageBody = messageBody;
    }

    @Override
    public String toJson() {
        return new EnvelopeTypeAdapter().toJson(this);
    }

    @Override
    public Envelope toEnvelope() {
        return this;
    }

    public static Envelope fromJson(String json) throws IOException {
        return new EnvelopeTypeAdapter().fromJson(json);
    }
}
