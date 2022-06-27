package bb.roborally.data.messages;

import bb.roborally.data.messages.connection.ConnectionUpdate;
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
        PLAYER_VALUES ("PlayerValues"),
        MOVEMENT ("Movement"),
        PLAYER_TURNING ("PlayerTurning"),
        ANIMATION ("Animation"),
        REBOOT ("Reboot"),
        REBOOT_DIRECTION ("RebootDirection"),
        ENERGY ("Energy"),
        CHECK_POINT_REACHED ("CheckPointReached"),
        GAME_FINISHED ("GameFinished"),
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
        PLAYER_ADDED("PlayerAdded"),
        SET_STATUS("SetStatus"),
        PLAYER_STATUS("PlayerStatus"),
        SELECT_MAP("SelectMap"),
        MAP_SELECTED("MapSelected"),
        ACTIVE_PHASE("ActivePhase"),
        NOT_YOUR_CARDS("NotYourCards"),
        SET_STARTING_POINT("SetStartingPoint"),
        SHUFFLE_CODING("ShuffleCoding"),
        STARTING_POINT_TAKEN("StartingPointTaken"),
        YOUR_CARDS("YourCards"),
        CONNECTION_UPDATE("ConnectionUpdate"),
        DRAW_DAMAGE("DrawDamage"),
        PICK_DAMAGE("PickDamage"),
        SELECTED_DAMAGE("SelectedDamage");


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
