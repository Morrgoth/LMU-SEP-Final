package bb.roborally.data.messages.type_adapters;

import bb.roborally.data.messages.*;
import bb.roborally.data.messages.Error;
import bb.roborally.data.messages.chat.ReceivedChat;
import bb.roborally.data.messages.chat.SendChat;
import bb.roborally.data.messages.connection.Alive;
import bb.roborally.data.messages.connection.HelloClient;
import bb.roborally.data.messages.connection.HelloServer;
import bb.roborally.data.messages.connection.Welcome;
import bb.roborally.data.messages.gameplay.CardPlayed;
import bb.roborally.data.messages.gameplay.CurrentPlayer;
import bb.roborally.data.messages.gameplay.PlayCard;
import bb.roborally.data.messages.lobby_messages.PlayerAdded;
import bb.roborally.data.messages.lobby_messages.PlayerStatus;
import bb.roborally.data.messages.lobby_messages.PlayerValues;
import bb.roborally.data.messages.lobby_messages.SetStatus;
import bb.roborally.data.messages.map_messages.MapSelected;
import bb.roborally.data.messages.map_messages.SelectMap;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
//import roborally.src.main.java.bb.roborally.data.messages.lobby_messages.*;
//import roborally.src.main.java.bb.roborally.data.messages.type_adapters.PlayerAddedTypeAdapter;
//import roborally.src.main.java.bb.roborally.data.messages.type_adapters.SelectMapTypeAdapter;

import java.util.logging.Logger;

import java.io.IOException;

public class EnvelopeTypeAdapter extends TypeAdapter<Envelope> {

    private static Logger LOGGER = Logger.getLogger("EnvelopeLogger");
    @Override
    public void write(JsonWriter jsonWriter, Envelope envelope) throws IOException {
        jsonWriter.beginObject();
        jsonWriter.name("messageType").value(envelope.getMessageType().getTypeName());
        jsonWriter.name("messageBody");
        if (envelope.getMessageType() == Envelope.MessageType.LOGIN_REQUEST) {
            new LoginRequestTypeAdapter().write(jsonWriter, (LoginRequest) envelope.getMessageBody());
        } else if (envelope.getMessageType() == Envelope.MessageType.LOGIN_CONFIRMATION) {
            new LoginConfimationTypeAdapter().write(jsonWriter, (LoginConfirmation) envelope.getMessageBody());
        } else if (envelope.getMessageType() == Envelope.MessageType.CHAT_MESSAGE) {
            new ChatMessageTypeAdapter().write(jsonWriter, (ChatMessage) envelope.getMessageBody());
        } else if (envelope.getMessageType() == Envelope.MessageType.LOGOUT_REQUEST) {
            new LogoutRequestTypeAdapter().write(jsonWriter, (LogoutRequest) envelope.getMessageBody());
        } else if (envelope.getMessageType() == Envelope.MessageType.LOGOUT_CONFIRMATION) {
            new LogoutConfirmationTypeAdapter().write(jsonWriter, (LogoutConfirmation) envelope.getMessageBody());
        } else if (envelope.getMessageType() == Envelope.MessageType.LOGIN_ERROR) {
            new LoginErrorTypeAdapter().write(jsonWriter, (LoginError) envelope.getMessageBody());
        } else if (envelope.getMessageType() == Envelope.MessageType.HELLO_CLIENT){
            new HelloClientTypeAdapter().write(jsonWriter, (HelloClient) envelope.getMessageBody());
        } else if (envelope.getMessageType() == Envelope.MessageType.ALIVE){
            new AliveTypeAdapter().write(jsonWriter, (Alive) envelope.getMessageBody());
        } else if (envelope.getMessageType() == Envelope.MessageType.HELLO_SERVER){
            new HelloServerTypeAdapter().write(jsonWriter, (HelloServer) envelope.getMessageBody());
        } else if (envelope.getMessageType() == Envelope.MessageType.WELCOME){
            new WelcomeTypeAdapter().write(jsonWriter, (Welcome) envelope.getMessageBody());
        } else if (envelope.getMessageType() == Envelope.MessageType.SEND_CHAT) {
            new SendChatTypeAdapter().write(jsonWriter, (SendChat) envelope.getMessageBody());
        } else if (envelope.getMessageType() == Envelope.MessageType.RECEIVED_CHAT) {
            new ReceivedChatTypeAdapter().write(jsonWriter, (ReceivedChat) envelope.getMessageBody());
        } else if (envelope.getMessageType() == Envelope.MessageType.ERROR) {
            new ErrorTypeAdapter().write(jsonWriter, (Error) envelope.getMessageBody());
        }else if (envelope.getMessageType() == Envelope.MessageType.PLAY_CARD) {
            new PlayCardTypeAdapter().write(jsonWriter, (PlayCard) envelope.getMessageBody());
        }else if (envelope.getMessageType() == Envelope.MessageType.CARD_PLAYED) {
            new CardPlayedTypeAdapter().write(jsonWriter, (CardPlayed) envelope.getMessageBody());
        }else if (envelope.getMessageType() == Envelope.MessageType.CURRENT_PLAYER) {
            new CurrentPlayerTypeAdapter().write(jsonWriter, (CurrentPlayer) envelope.getMessageBody());
        } else if (envelope.getMessageType() == Envelope.MessageType.PLAYER_VALUES) {
            new PlayerValuesTypeAdapter().write(jsonWriter, (PlayerValues) envelope.getMessageBody());
        } else if (envelope.getMessageType() == Envelope.MessageType.PLAYER_ADDED) {
            new PlayerAddedTypeAdapter().write(jsonWriter, (PlayerAdded) envelope.getMessageBody());
        } else if (envelope.getMessageType() == Envelope.MessageType.SET_STATUS) {
            new SetStatusTypeAdapter().write(jsonWriter, (SetStatus) envelope.getMessageBody());
        } else if (envelope.getMessageType() == Envelope.MessageType.PLAYER_STATUS) {
            new PlayerStatusTypeAdapter().write(jsonWriter, (PlayerStatus) envelope.getMessageBody());
        } else if (envelope.getMessageType() == Envelope.MessageType.SELECT_MAP) {
            new SelectMapTypeAdapter().write(jsonWriter, (SelectMap) envelope.getMessageBody());
        } else if (envelope.getMessageType() == Envelope.MessageType.MAP_SELECTED) {
            new MapSelectedTypeAdapter().write(jsonWriter, (MapSelected) envelope.getMessageBody());
        }else {
            LOGGER.severe("The MessageType '" + envelope.getMessageType().getTypeName() + "' is not " +
                    "recognized by EnvelopeTypeAdapter.");
        }
        jsonWriter.endObject();
    }

    @Override
    public Envelope read(JsonReader jsonReader) throws IOException {
        jsonReader.beginObject();
        Envelope envelope = null;
        if ("messageType".equals(jsonReader.nextName())) {
            envelope = new Envelope();
            envelope.setMessageType(Envelope.MessageType.toMessageType(jsonReader.nextString()));
            if ("messageBody".equals(jsonReader.nextName())) {
                if (envelope.getMessageType() == Envelope.MessageType.LOGIN_REQUEST) {
                    envelope.setMessageBody(new LoginRequestTypeAdapter().read(jsonReader));
                } else if (envelope.getMessageType() == Envelope.MessageType.LOGIN_CONFIRMATION) {
                    envelope.setMessageBody(new LoginConfimationTypeAdapter().read(jsonReader));
                } else if(envelope.getMessageType() == Envelope.MessageType.CHAT_MESSAGE) {
                    envelope.setMessageBody(new ChatMessageTypeAdapter().read(jsonReader));
                } else if (envelope.getMessageType() == Envelope.MessageType.LOGOUT_REQUEST) {
                    envelope.setMessageBody(new LogoutRequestTypeAdapter().read(jsonReader));
                } else if (envelope.getMessageType() == Envelope.MessageType.LOGOUT_CONFIRMATION) {
                    envelope.setMessageBody(new LogoutConfirmationTypeAdapter().read(jsonReader));
                } else if (envelope.getMessageType() == Envelope.MessageType.LOGIN_ERROR) {
                    envelope.setMessageBody(new LoginErrorTypeAdapter().read(jsonReader));
                } else if (envelope.getMessageType() == Envelope.MessageType.HELLO_CLIENT){
                    envelope.setMessageBody(new HelloClientTypeAdapter().read(jsonReader));
                } else if (envelope.getMessageType() == Envelope.MessageType.ALIVE) {
                    envelope.setMessageBody(new AliveTypeAdapter().read(jsonReader));
                } else if (envelope.getMessageType() == Envelope.MessageType.HELLO_SERVER) {
                    envelope.setMessageBody(new HelloServerTypeAdapter().read(jsonReader));
                } else if (envelope.getMessageType() == Envelope.MessageType.WELCOME) {
                    envelope.setMessageBody(new WelcomeTypeAdapter().read(jsonReader));
                } else if (envelope.getMessageType() == Envelope.MessageType.SEND_CHAT) {
                    envelope.setMessageBody(new SendChatTypeAdapter().read(jsonReader));
                } else if (envelope.getMessageType() == Envelope.MessageType.RECEIVED_CHAT) {
                    envelope.setMessageBody(new ReceivedChatTypeAdapter().read(jsonReader));
                }else if (envelope.getMessageType() == Envelope.MessageType.ERROR) {
                    envelope.setMessageBody(new ErrorTypeAdapter().read(jsonReader));
                }else if (envelope.getMessageType() == Envelope.MessageType.PLAY_CARD) {
                    envelope.setMessageBody(new PlayCardTypeAdapter().read(jsonReader));
                }else if (envelope.getMessageType() == Envelope.MessageType.CARD_PLAYED) {
                    envelope.setMessageBody(new CardPlayedTypeAdapter().read(jsonReader));
                }else if (envelope.getMessageType() == Envelope.MessageType.CURRENT_PLAYER) {
                    envelope.setMessageBody(new CurrentPlayerTypeAdapter().read(jsonReader));
                }else if (envelope.getMessageType() == Envelope.MessageType.PLAYER_VALUES) {
                    envelope.setMessageBody( new PlayerValuesTypeAdapter().read(jsonReader));
                } else if (envelope.getMessageType() == Envelope.MessageType.PLAYER_ADDED) {
                    envelope.setMessageBody(new PlayerAddedTypeAdapter().read(jsonReader));
                } else if (envelope.getMessageType() == Envelope.MessageType.SET_STATUS) {
                    envelope.setMessageBody(new SetStatusTypeAdapter().read(jsonReader));
                } else if (envelope.getMessageType() == Envelope.MessageType.PLAYER_STATUS) {
                    envelope.setMessageBody(new PlayerStatusTypeAdapter().read(jsonReader));
                } else if (envelope.getMessageType() == Envelope.MessageType.SELECT_MAP) {
                    envelope.setMessageBody(new SelectMapTypeAdapter().read(jsonReader));
                } else if (envelope.getMessageType() == Envelope.MessageType.MAP_SELECTED) {
                    envelope.setMessageBody(new MapSelectedTypeAdapter().read(jsonReader));
                } else {
                    LOGGER.severe("The MessageType '" + envelope.getMessageType().getTypeName() + "' is not " +
                            "recognized by EnvelopeTypeAdapter.");
                    envelope.setMessageBody(null);
                }
            }
        }
        jsonReader.endObject();
        return envelope;
    }
}




