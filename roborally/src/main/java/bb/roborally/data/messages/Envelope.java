package bb.roborally.data.messages;

import bb.roborally.data.messages.type_adapters.EnvelopeTypeAdapter;

import java.io.IOException;

public class Envelope implements Message {
    private MessageType messageType;
    private Message messageBody;

    public Envelope(MessageType messageType, Message messageBody)  {
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
        PLAYER_VALUES ("PlayerValues"),
        SELECTED_CARD("SelectedCard"),
        CARD_SELECTED("CardSelected"),
        SELECTION_FINISHED("SelectionFinished"),
        TIMER_STARTED("TimerStarted"),
        TIMER_ENDED("TimerEnded"),
        CARDS_YOU_GOT_NOW("CardsYouGotNow"),
        CURRENT_CARDS("CurrentCards"),
        REPLACE_CARD("ReplaceCard"),
        SEND_CHAT ("SendChat"),
        RECEIVED_CHAT ("ReceivedChat"),
        ERROR ("Error"),
        CARD_PLAYED ("CardPlayed"),
        CURRENT_PLAYER ("CurrentPlayer"),
        PLAY_CARD ("PlayCard"),
        ACTIVE_PHASE ("ActivePhase"),
        NOT_YOUR_CARDS ("NotYourCards"),
        SET_STARTINGPOINT ("SetStartingPoint"),
        SHUFFLE_CODING ("ShuffleCoding"),
        STARTINGPOINT_TAKEN ("StartingPointTaken"),
        YOUR_CARDS ("YourCards");
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
