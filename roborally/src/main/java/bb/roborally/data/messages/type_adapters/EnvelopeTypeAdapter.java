package bb.roborally.data.messages.type_adapters;

import bb.roborally.data.messages.*;
import bb.roborally.data.messages.connection.Alive;
import bb.roborally.data.messages.connection.HelloClient;
import bb.roborally.data.messages.connection.HelloServer;
import bb.roborally.data.messages.connection.Welcome;
import bb.roborally.data.messages.game_events.*;
import bb.roborally.data.messages.lobby.PlayerValues;
import bb.roborally.data.messages.type_adapters.game_events.*;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.util.EventListener;
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
        } else if (envelope.getMessageType() == Envelope.MessageType.PLAYER_VALUES) {
            //new PlayerValuesTypeAdapter().write(jsonWriter, (PlayerValues) envelope.getMessageBody());
        } else if (envelope.getMessageType() == Envelope.MessageType.MOVEMENT){
            new MovementTypeAdapter().write(jsonWriter, (Movement) envelope.getMessageBody());
        } else if (envelope.getMessageType() == Envelope.MessageType.PLAYER_TURNING){
            new PlayerTurningTypeAdapter().write(jsonWriter, (PlayerTurning) envelope.getMessageBody());
        } else if (envelope.getMessageType() == Envelope.MessageType.ANIMATION){
            new AnimationTypeAdapter().write(jsonWriter, (Animation) envelope.getMessageBody());
        } else if (envelope.getMessageType() == Envelope.MessageType.REBOOT){
            new RebootTypeAdapter().write(jsonWriter, (Reboot) envelope.getMessageBody());
        } else if (envelope.getMessageType() == Envelope.MessageType.REBOOT_DIRECTION){
            new RebootDirectionTypeAdapter().write(jsonWriter, (RebootDirection) envelope.getMessageBody());
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
                } else if (envelope.getMessageType() == Envelope.MessageType.PLAYER_VALUES) {
                    //envelope.setMessageBody(new PlayerValuesTypeAdapter.read(jsonReader));
                } else if (envelope.getMessageType() == Envelope.MessageType.MOVEMENT){
                    envelope.setMessageBody(new MovementTypeAdapter().read(jsonReader));
                } else if (envelope.getMessageType() == Envelope.MessageType.PLAYER_TURNING){
                    envelope.setMessageBody(new PlayerTurningTypeAdapter().read(jsonReader));
                } else if(envelope.getMessageType() == Envelope.MessageType.ANIMATION){
                    envelope.setMessageBody(new AnimationTypeAdapter().read(jsonReader));
                } else if (envelope.getMessageType() == Envelope.MessageType.REBOOT){
                    envelope.setMessageBody(new RebootTypeAdapter().read(jsonReader));
                } else if (envelope.getMessageType() == Envelope.MessageType.REBOOT_DIRECTION){
                    envelope.setMessageBody(new RebootDirectionTypeAdapter().read(jsonReader));
                }else {
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
