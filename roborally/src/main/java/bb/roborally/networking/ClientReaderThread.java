package bb.roborally.networking;

import bb.roborally.data.messages.Envelope;
import bb.roborally.gui.game.GameModel;
import javafx.application.Platform;

import java.io.DataInputStream;

public class ClientReaderThread extends Thread{
    GameModel gameModel;
    DataInputStream dataInputStream;
    public ClientReaderThread(DataInputStream dataInputStream, GameModel gameModel){
        this.dataInputStream = dataInputStream;
        this.gameModel = gameModel;
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
                json = dataInputStream.readUTF();
                if(json != null) {
                    Envelope envelope = Envelope.fromJson(json);
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            gameModel.process(envelope);
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
