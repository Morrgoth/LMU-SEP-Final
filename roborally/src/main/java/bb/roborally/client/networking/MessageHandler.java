package bb.roborally.client.networking;

import bb.roborally.protocol.Envelope;
import bb.roborally.protocol.Error;
import bb.roborally.protocol.chat.ReceivedChat;
import bb.roborally.protocol.connection.Alive;
import bb.roborally.protocol.connection.HelloClient;
import bb.roborally.protocol.connection.Welcome;
import bb.roborally.protocol.gameplay.*;
import bb.roborally.protocol.lobby.PlayerAdded;
import bb.roborally.protocol.lobby.PlayerStatus;
import bb.roborally.protocol.map.SelectMap;
import bb.roborally.server.game.board.Board;
import bb.roborally.client.RoboRallyModel;
import javafx.application.Platform;

import java.util.logging.Logger;

public class MessageHandler extends Thread{
    private static final Logger LOGGER = Logger.getLogger(MessageHandler.class.getName());
    RoboRallyModel roboRallyModel;
    public MessageHandler(RoboRallyModel roboRallyModel){
        this.roboRallyModel = roboRallyModel;
    }
    /**
     * Handling of messages received from the Server
     */
    public void run()
    {
        LOGGER.info("MessageHandler started running");
        try{
            String json = null;
            while(NetworkConnection.getInstance().isOpen()) {
                // RECEIVE MESSAGE FROM SERVER
                json = NetworkConnection.getInstance().getInputStream().readLine();
                if(json != null) {
                    LOGGER.info("Incoming: " + json);
                    Envelope envelope = Envelope.fromJson(json);
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            if (envelope.getMessageType() == Envelope.MessageType.HELLO_CLIENT) {
                                roboRallyModel.process((HelloClient) envelope.getMessageBody());
                            } else if (envelope.getMessageType() == Envelope.MessageType.WELCOME) {
                                roboRallyModel.process((Welcome) envelope.getMessageBody());
                            } else if (envelope.getMessageType() == Envelope.MessageType.PLAYER_ADDED) {
                                roboRallyModel.process((PlayerAdded) envelope.getMessageBody());
                            } else if (envelope.getMessageType() == Envelope.MessageType.ALIVE) {
                                NetworkConnection.getInstance().send(envelope.getMessageBody());
                            } else if (envelope.getMessageType() == Envelope.MessageType.PLAYER_STATUS) {
                                roboRallyModel.process((PlayerStatus) envelope.getMessageBody());
                            } else if (envelope.getMessageType() == Envelope.MessageType.SELECT_MAP) {
                                roboRallyModel.process((SelectMap) envelope.getMessageBody());
                            } else if (envelope.getMessageType() == Envelope.MessageType.RECEIVED_CHAT) {
                                roboRallyModel.process((ReceivedChat) envelope.getMessageBody());
                            } else if (envelope.getMessageType() == Envelope.MessageType.GAME_STARTED) {
                                roboRallyModel.process((Board) envelope.getMessageBody());
                            } else if (envelope.getMessageType() == Envelope.MessageType.ACTIVE_PHASE) {
                                roboRallyModel.process((ActivePhase) envelope.getMessageBody());
                            } else if (envelope.getMessageType() == Envelope.MessageType.ERROR) {
                                roboRallyModel.process((Error) envelope.getMessageBody());
                            } else if (envelope.getMessageType() == Envelope.MessageType.STARTING_POINT_TAKEN) {
                                roboRallyModel.process((StartingPointTaken) envelope.getMessageBody());
                            } else if (envelope.getMessageType() == Envelope.MessageType.YOUR_CARDS) {
                                roboRallyModel.process((YourCards) envelope.getMessageBody());
                            } else if (envelope.getMessageType() == Envelope.MessageType.NOT_YOUR_CARDS) {
                                roboRallyModel.process((NotYourCards) envelope.getMessageBody());
                            } else if (envelope.getMessageType() == Envelope.MessageType.SHUFFLE_CODING) {
                                roboRallyModel.process((ShuffleCoding) envelope.getMessageBody());
                            } else if (envelope.getMessageType() == Envelope.MessageType.CARDS_YOU_GOT_NOW) {
                                roboRallyModel.process((CardsYouGotNow) envelope.getMessageBody());
                            } else if (envelope.getMessageType() == Envelope.MessageType.CURRENT_CARDS) {
                                roboRallyModel.process((CurrentCards) envelope.getMessageBody());
                            }
                        }
                    });
                }
                json = null;
            }
        }
        catch(Exception e){
            // Error Message should be added here
        }
    }
}
