package bb.roborally.networking;

import bb.roborally.data.messages.Envelope;
import bb.roborally.data.messages.Error;
import bb.roborally.data.messages.chat.ReceivedChat;
import bb.roborally.data.messages.connection.Alive;
import bb.roborally.data.messages.gameplay.ActivePhase;
import bb.roborally.data.messages.gameplay.StartingPointTaken;
import bb.roborally.data.messages.lobby.PlayerAdded;
import bb.roborally.data.messages.lobby.PlayerStatus;
import bb.roborally.data.messages.map.MapSelected;
import bb.roborally.data.messages.map.SelectMap;
import bb.roborally.game.board.Board;
import bb.roborally.gui.data.RoboRallyModel;
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
