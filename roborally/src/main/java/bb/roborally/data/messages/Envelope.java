package bb.roborally.data.messages;

import bb.roborally.data.messages.type_adapters.EnvelopeTypeAdapter;

import java.io.IOException;

public class Envelope implements Message {
    private MessageType messageType;
    private Message messageBody;

    public Envelope(MessageType messageType, Message messageBody) {
        this.messageType = messageType;
        this.messageBody = messageBody;
    }

    public Envelope() {}

    public enum MessageType {
        LOGIN_REQUEST("LoginRequest"),
        LOGIN_CONFIRMATION("LoginConfirmation"),
        LOGIN_ERROR("LoginError"),
        CHAT_MESSAGE("ChatMessage"),
        LOGOUT_REQUEST("LogoutRequest"),
        LOGOUT_CONFIRMATION("LogoutConfirmation"),
        HELLO_CLIENT("HelloClient"),
        ALIVE ("Alive"),
        HELLO_SERVER ("HelloServer"),
        WELCOME ("Welcome"),
        SEND_CHAT ("SendChat"),
        RECEIVED_CHAT ("ReceivedChat"),
        ERROR ("Error"),
        CARD_PLAYED ("CardPlayed"),
        CURRENT_PLAYER ("CurrentPlayer"),
        PLAY_CARD ("PlayCard"),
        PLAYER_VALUES("PlayerValues"),
        PLAYER_ADDED("PlayerAdded"),
        SET_STATUS("SetStatus"),
        PLAYER_STATUS("PlayerStatus"),
        SELECT_MAP("SelectMap"),
        MAP_SELECTED("MapSelected");


        private final String typeName;

        private MessageType(final String typeName) {
            this.typeName = typeName;
        }

        public String getTypeName() {
            return this.typeName;
        }

        public static MessageType toMessageType(String typeName) {
            for(MessageType v : values())
                if(v.getTypeName().equals(typeName)) return v;
            throw new IllegalArgumentException();
        }
    }

    public MessageType getMessageType() {
        return messageType;
    }

    public void setMessageType(MessageType messageType) {
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
