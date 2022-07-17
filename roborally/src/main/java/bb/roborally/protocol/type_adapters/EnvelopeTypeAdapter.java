package bb.roborally.protocol.type_adapters;

import bb.roborally.protocol.Envelope;
import bb.roborally.protocol.Error;
import bb.roborally.protocol.chat.ReceivedChat;
import bb.roborally.protocol.chat.SendChat;
import bb.roborally.protocol.type_adapters.chat.ReceivedChatTypeAdapter;
import bb.roborally.protocol.type_adapters.chat.SendChatTypeAdapter;
import bb.roborally.protocol.gameplay.CardPlayed;
import bb.roborally.protocol.gameplay.CurrentPlayer;
import bb.roborally.protocol.gameplay.PlayCard;
import bb.roborally.protocol.lobby.PlayerAdded;
import bb.roborally.protocol.lobby.PlayerStatus;
import bb.roborally.protocol.lobby.PlayerValues;
import bb.roborally.protocol.lobby.SetStatus;
import bb.roborally.protocol.map.MapSelected;
import bb.roborally.protocol.map.SelectMap;
import bb.roborally.protocol.type_adapters.connection.*;
import bb.roborally.protocol.type_adapters.game_events.*;
import bb.roborally.protocol.type_adapters.gameplay.*;
import bb.roborally.protocol.type_adapters.lobby.PlayerAddedTypeAdapter;
import bb.roborally.protocol.type_adapters.lobby.PlayerStatusTypeAdapter;
import bb.roborally.protocol.type_adapters.lobby.PlayerValuesTypeAdapter;
import bb.roborally.protocol.type_adapters.lobby.SetStatusTypeAdapter;
import bb.roborally.protocol.type_adapters.map.BoardTypeAdapter;
import bb.roborally.protocol.type_adapters.map.MapSelectedTypeAdapter;
import bb.roborally.protocol.type_adapters.map.SelectMapTypeAdapter;
import bb.roborally.server.game.board.Board;
import bb.roborally.protocol.connection.*;
import bb.roborally.protocol.game_events.*;
import bb.roborally.protocol.gameplay.*;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

//import roborally.src.main.java.bb.roborally.data.messages.lobby_messages.*;
//import roborally.src.main.java.bb.roborally.data.messages.type_adapters.lobby.PlayerAddedTypeAdapter;
//import roborally.src.main.java.bb.roborally.data.messages.type_adapters.map.SelectMapTypeAdapter;

import java.util.logging.Logger;

import java.io.IOException;

/**
 * @author Muqiu Wang
 * @author Bence Ament
 * @author Zeynab Baiani
 * @author Philipp Keyzman
 * @author Veronika Heckel
 */
public class EnvelopeTypeAdapter extends TypeAdapter<Envelope> {

    private static final Logger LOGGER = Logger.getLogger(EnvelopeTypeAdapter.class.getName());
    @Override
    public void write(JsonWriter jsonWriter, Envelope envelope) throws IOException {
        jsonWriter.beginObject();
        jsonWriter.name("messageType").value(envelope.getMessageType().getTypeName());
        jsonWriter.name("messageBody");
        if (envelope.getMessageType() == Envelope.MessageType.HELLO_CLIENT){
            new HelloClientTypeAdapter().write(jsonWriter, (HelloClient) envelope.getMessageBody());
        } else if (envelope.getMessageType() == Envelope.MessageType.ALIVE){
            new AliveTypeAdapter().write(jsonWriter, (Alive) envelope.getMessageBody());
        } else if (envelope.getMessageType() == Envelope.MessageType.HELLO_SERVER){
            new HelloServerTypeAdapter().write(jsonWriter, (HelloServer) envelope.getMessageBody());
        } else if (envelope.getMessageType() == Envelope.MessageType.WELCOME){
            new WelcomeTypeAdapter().write(jsonWriter, (Welcome) envelope.getMessageBody());
        } else if (envelope.getMessageType() == Envelope.MessageType.PLAYER_VALUES) {
            new PlayerValuesTypeAdapter().write(jsonWriter, (PlayerValues) envelope.getMessageBody());
        } else if (envelope.getMessageType() == Envelope.MessageType.SELECTED_CARD) {
            new SelectedCardTypeAdapter().write(jsonWriter, (SelectedCard) envelope.getMessageBody());
        } else if (envelope.getMessageType() == Envelope.MessageType.CARD_SELECTED) {
            new CardSelectedTypeAdapter().write(jsonWriter, (CardSelected) envelope.getMessageBody());
        } else if (envelope.getMessageType() == Envelope.MessageType.SELECTION_FINISHED) {
            new SelectionFinishedTypeAdapter().write(jsonWriter, (SelectionFinished) envelope.getMessageBody());
        } else if (envelope.getMessageType() == Envelope.MessageType.TIMER_STARTED) {
            new TimerStartedTypeAdapter().write(jsonWriter, (TimerStarted) envelope.getMessageBody());
        } else if (envelope.getMessageType() == Envelope.MessageType.TIMER_ENDED) {
            new TimerEndedTypeAdapter().write(jsonWriter, (TimerEnded) envelope.getMessageBody());
        } else if (envelope.getMessageType() == Envelope.MessageType.CARDS_YOU_GOT_NOW) {
            new CardsYouGotNowTypeAdapter().write(jsonWriter, (CardsYouGotNow) envelope.getMessageBody());
        } else if (envelope.getMessageType() == Envelope.MessageType.CURRENT_CARDS) {
            new CurrentCardsTypeAdapter().write(jsonWriter, (CurrentCards) envelope.getMessageBody());
        } else if (envelope.getMessageType() == Envelope.MessageType.REPLACE_CARD) {
            new ReplaceCardTypeAdapter().write(jsonWriter, (ReplaceCard) envelope.getMessageBody());
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
        } else if (envelope.getMessageType() == Envelope.MessageType.ACTIVE_PHASE) {
            new ActivePhaseTypeAdapter().write(jsonWriter, (ActivePhase) envelope.getMessageBody());
        } else if (envelope.getMessageType() == Envelope.MessageType.NOT_YOUR_CARDS) {
            new NotYourCardTypeAdapter().write(jsonWriter, (NotYourCards) envelope.getMessageBody());
        } else if (envelope.getMessageType() == Envelope.MessageType.SHUFFLE_CODING){
            new ShuffleCodingTypeAdapter().write(jsonWriter, (ShuffleCoding) envelope.getMessageBody());
        } else if (envelope.getMessageType() == Envelope.MessageType.SET_STARTING_POINT){
            new SetStartingPointTypeAdapter().write(jsonWriter, (SetStartingPoint) envelope.getMessageBody());
        } else if (envelope.getMessageType() == Envelope.MessageType.STARTING_POINT_TAKEN){
            new StartingPointTakenTypeAdapter().write(jsonWriter, (StartingPointTaken) envelope.getMessageBody());
        } else if (envelope.getMessageType() == Envelope.MessageType.YOUR_CARDS){
            new YourCardsTypeAdapter().write(jsonWriter, (YourCards) envelope.getMessageBody());
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
        } else if (envelope.getMessageType() == Envelope.MessageType.ENERGY){
            new EnergyTypeAdapter().write(jsonWriter, (Energy) envelope.getMessageBody());
        } else if (envelope.getMessageType() == Envelope.MessageType.CHECK_POINT_REACHED){
            new CheckPointReachedTypeAdapter().write(jsonWriter, (CheckPointReached) envelope.getMessageBody());
        } else if (envelope.getMessageType() == Envelope.MessageType.GAME_FINISHED){
            new GameFinishedTypeAdapter().write(jsonWriter, (GameFinished) envelope.getMessageBody());
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
        }else if (envelope.getMessageType() == Envelope.MessageType.CONNECTION_UPDATE) {
            new ConnectionUpdateTypeAdapter().write(jsonWriter, (ConnectionUpdate) envelope.getMessageBody());
        }else if (envelope.getMessageType() == Envelope.MessageType.DRAW_DAMAGE) {
            new DrawDamageTypeAdapter().write(jsonWriter, (DrawDamage) envelope.getMessageBody());
        }else if (envelope.getMessageType() == Envelope.MessageType.PICK_DAMAGE) {
            new PickDamageTypeAdapter().write(jsonWriter, (PickDamage) envelope.getMessageBody());
        }else if (envelope.getMessageType() == Envelope.MessageType.SELECTED_DAMAGE) {
            new SelectedDamageTypeAdapter().write(jsonWriter, (SelectedDamage) envelope.getMessageBody());
        }else if (envelope.getMessageType() == Envelope.MessageType.GAME_STARTED) {
            new BoardTypeAdapter().write(jsonWriter, (Board) envelope.getMessageBody());
        }
        else {
            LOGGER.severe("The MessageType '" + envelope.getMessageType().getTypeName() + "' is not " +
                    "recognized by EnvelopeTypeAdapter.");
        }
        jsonWriter.endObject();
    }

    @Override
    public Envelope read(JsonReader jsonReader) throws IOException {
        jsonReader.beginObject();
        String name;
        String type = null;
        JsonObject messageBody = null;
        while (jsonReader.hasNext()) {
            name = jsonReader.nextName();
            if ("messageBody".equals(name)) {
                messageBody = JsonParser.parseReader(jsonReader).getAsJsonObject();
            } else if ("messageType".equals(name)) {
                type = jsonReader.nextString();
            }
        }
        jsonReader.endObject();
        Envelope envelope = new Envelope();
        envelope.setMessageType(Envelope.MessageType.toMessageType(type));
        if (messageBody != null) {
            if (envelope.getMessageType() == Envelope.MessageType.HELLO_CLIENT){
                envelope.setMessageBody(new HelloClientTypeAdapter().fromJson(messageBody.toString()));
            } else if (envelope.getMessageType() == Envelope.MessageType.ALIVE) {
                envelope.setMessageBody(new AliveTypeAdapter().fromJson(messageBody.toString()));
            } else if (envelope.getMessageType() == Envelope.MessageType.HELLO_SERVER) {
                envelope.setMessageBody(new HelloServerTypeAdapter().fromJson(messageBody.toString()));
            } else if (envelope.getMessageType() == Envelope.MessageType.WELCOME) {
                envelope.setMessageBody(new WelcomeTypeAdapter().fromJson(messageBody.toString()));
            } else if (envelope.getMessageType() == Envelope.MessageType.SELECTED_CARD) {
                envelope.setMessageBody(new SelectedCardTypeAdapter().fromJson(messageBody.toString()));
            } else if (envelope.getMessageType() == Envelope.MessageType.CARD_SELECTED) {
                envelope.setMessageBody(new CardSelectedTypeAdapter().fromJson(messageBody.toString()));
            } else if (envelope.getMessageType() == Envelope.MessageType.SELECTION_FINISHED) {
                envelope.setMessageBody(new SelectionFinishedTypeAdapter().fromJson(messageBody.toString()));
            } else if (envelope.getMessageType() == Envelope.MessageType.TIMER_STARTED) {
                envelope.setMessageBody(new TimerStartedTypeAdapter().fromJson(messageBody.toString()));
            } else if (envelope.getMessageType() == Envelope.MessageType.TIMER_ENDED) {
                envelope.setMessageBody(new TimerEndedTypeAdapter().fromJson(messageBody.toString()));
            } else if (envelope.getMessageType() == Envelope.MessageType.CARDS_YOU_GOT_NOW) {
                envelope.setMessageBody(new CardsYouGotNowTypeAdapter().fromJson(messageBody.toString()));
            } else if (envelope.getMessageType() == Envelope.MessageType.CURRENT_CARDS) {
                envelope.setMessageBody(new CurrentCardsTypeAdapter().fromJson(messageBody.toString()));
            } else if (envelope.getMessageType() == Envelope.MessageType.REPLACE_CARD) {
                envelope.setMessageBody(new ReplaceCardTypeAdapter().fromJson(messageBody.toString()));
            } else if (envelope.getMessageType() == Envelope.MessageType.SEND_CHAT) {
                envelope.setMessageBody(new SendChatTypeAdapter().fromJson(messageBody.toString()));
            } else if (envelope.getMessageType() == Envelope.MessageType.RECEIVED_CHAT) {
                envelope.setMessageBody(new ReceivedChatTypeAdapter().fromJson(messageBody.toString()));
            }else if (envelope.getMessageType() == Envelope.MessageType.ERROR) {
                envelope.setMessageBody(new ErrorTypeAdapter().fromJson(messageBody.toString()));
            }else if (envelope.getMessageType() == Envelope.MessageType.PLAY_CARD) {
                envelope.setMessageBody(new PlayCardTypeAdapter().fromJson(messageBody.toString()));
            }else if (envelope.getMessageType() == Envelope.MessageType.CARD_PLAYED) {
                envelope.setMessageBody(new CardPlayedTypeAdapter().fromJson(messageBody.toString()));
            }else if (envelope.getMessageType() == Envelope.MessageType.CURRENT_PLAYER) {
                envelope.setMessageBody(new CurrentPlayerTypeAdapter().fromJson(messageBody.toString()));
            } else if (envelope.getMessageType() == Envelope.MessageType.ACTIVE_PHASE){
                envelope.setMessageBody(new ActivePhaseTypeAdapter().fromJson(messageBody.toString()));
            } else if (envelope.getMessageType() == Envelope.MessageType.NOT_YOUR_CARDS){
                envelope.setMessageBody(new NotYourCardTypeAdapter().fromJson(messageBody.toString()));
            } else if (envelope.getMessageType() == Envelope.MessageType.SET_STARTING_POINT){
                envelope.setMessageBody(new SetStartingPointTypeAdapter().fromJson(messageBody.toString()));
            } else if (envelope.getMessageType() == Envelope.MessageType.SHUFFLE_CODING){
                envelope.setMessageBody(new ShuffleCodingTypeAdapter().fromJson(messageBody.toString()));
            } else if (envelope.getMessageType() == Envelope.MessageType.STARTING_POINT_TAKEN){
                envelope.setMessageBody(new StartingPointTakenTypeAdapter().fromJson(messageBody.toString()));
            } else if (envelope.getMessageType() == Envelope.MessageType.YOUR_CARDS){
                envelope.setMessageBody((new YourCardsTypeAdapter().fromJson(messageBody.toString())));
            } else if (envelope.getMessageType() == Envelope.MessageType.MOVEMENT){
                envelope.setMessageBody(new MovementTypeAdapter().fromJson(messageBody.toString()));
            } else if (envelope.getMessageType() == Envelope.MessageType.PLAYER_TURNING){
                envelope.setMessageBody(new PlayerTurningTypeAdapter().fromJson(messageBody.toString()));
            } else if(envelope.getMessageType() == Envelope.MessageType.ANIMATION){
                envelope.setMessageBody(new AnimationTypeAdapter().fromJson(messageBody.toString()));
            } else if (envelope.getMessageType() == Envelope.MessageType.REBOOT){
                envelope.setMessageBody(new RebootTypeAdapter().fromJson(messageBody.toString()));
            } else if (envelope.getMessageType() == Envelope.MessageType.REBOOT_DIRECTION){
                envelope.setMessageBody(new RebootDirectionTypeAdapter().fromJson(messageBody.toString()));
            } else if(envelope.getMessageType() == Envelope.MessageType.ENERGY){
                envelope.setMessageBody(new EnergyTypeAdapter().fromJson(messageBody.toString()));
            } else if (envelope.getMessageType() == Envelope.MessageType.CHECK_POINT_REACHED){
                envelope.setMessageBody(new CheckPointReachedTypeAdapter().fromJson(messageBody.toString()));
            } else if (envelope.getMessageType() == Envelope.MessageType.GAME_FINISHED){
                envelope.setMessageBody(new GameFinishedTypeAdapter().fromJson(messageBody.toString()));
            } else if (envelope.getMessageType() == Envelope.MessageType.PLAYER_VALUES) {
                envelope.setMessageBody( new PlayerValuesTypeAdapter().fromJson(messageBody.toString()));
            } else if (envelope.getMessageType() == Envelope.MessageType.PLAYER_ADDED) {
                envelope.setMessageBody(new PlayerAddedTypeAdapter().fromJson(messageBody.toString()));
            } else if (envelope.getMessageType() == Envelope.MessageType.SET_STATUS) {
                envelope.setMessageBody(new SetStatusTypeAdapter().fromJson(messageBody.toString()));
            } else if (envelope.getMessageType() == Envelope.MessageType.PLAYER_STATUS) {
                envelope.setMessageBody(new PlayerStatusTypeAdapter().fromJson(messageBody.toString()));
            } else if (envelope.getMessageType() == Envelope.MessageType.SELECT_MAP) {
                envelope.setMessageBody(new SelectMapTypeAdapter().fromJson(messageBody.toString()));
            } else if (envelope.getMessageType() == Envelope.MessageType.MAP_SELECTED) {
                envelope.setMessageBody(new MapSelectedTypeAdapter().fromJson(messageBody.toString()));
            } else if (envelope.getMessageType() == Envelope.MessageType.CONNECTION_UPDATE) {
                envelope.setMessageBody(new ConnectionUpdateTypeAdapter().fromJson(messageBody.toString()));
            }else if (envelope.getMessageType() == Envelope.MessageType.DRAW_DAMAGE) {
                envelope.setMessageBody(new DrawDamageTypeAdapter().fromJson(messageBody.toString()));
            }else if (envelope.getMessageType() == Envelope.MessageType.PICK_DAMAGE) {
                envelope.setMessageBody(new PickDamageTypeAdapter().fromJson(messageBody.toString()));
            }else if (envelope.getMessageType() == Envelope.MessageType.GAME_STARTED) {
                System.out.println(messageBody.toString());
                envelope.setMessageBody(new BoardTypeAdapter().fromJson(messageBody.toString()));
            }else if (envelope.getMessageType() == Envelope.MessageType.SELECTED_DAMAGE) {
                envelope.setMessageBody(new SelectedDamageTypeAdapter().fromJson(messageBody.toString()));
            }
            else {
                LOGGER.severe("The MessageType '" + envelope.getMessageType().getTypeName() + "' is not " +
                        "recognized by EnvelopeTypeAdapter.");
                envelope.setMessageBody(null);
            }
        }
        return envelope;
    }
}
