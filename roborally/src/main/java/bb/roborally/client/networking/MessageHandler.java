package bb.roborally.client.networking;

import bb.roborally.protocol.Envelope;
import bb.roborally.protocol.Error;
import bb.roborally.protocol.chat.ReceivedChat;
import bb.roborally.protocol.connection.Alive;
import bb.roborally.protocol.gameplay.*;
import bb.roborally.protocol.lobby.PlayerAdded;
import bb.roborally.protocol.lobby.PlayerStatus;
import bb.roborally.protocol.map.SelectMap;
import bb.roborally.server.game.board.Board;
import bb.roborally.client.RoboRallyModel;
import javafx.application.Platform;

public class MessageHandler extends Thread{
    RoboRallyModel roboRallyModel;
    public MessageHandler(RoboRallyModel roboRallyModel){
        this.roboRallyModel = roboRallyModel;
    }
    /**
     * Handling of messages received from the Server
     */
    public void run()
    {
        System.out.println("ClientReaderThreadUI started running");
        try{
            String json=null;
            while(true){
                // RECEIVE MESSAGE FROM SERVER
                json = NetworkConnection.getInstance().getDataInputStream().readUTF();
                System.out.println(json);
                if(json != null) {
                    Envelope envelope = Envelope.fromJson(json);
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            if (envelope.getMessageType() == Envelope.MessageType.PLAYER_ADDED) {
                                roboRallyModel.process((PlayerAdded) envelope.getMessageBody());
                            } else if (envelope.getMessageType() == Envelope.MessageType.ALIVE) {
                                roboRallyModel.process((Alive) envelope.getMessageBody());
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
