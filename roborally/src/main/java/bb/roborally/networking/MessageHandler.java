package bb.roborally.networking;

import bb.roborally.data.messages.Envelope;
import bb.roborally.gui.RoboRallyModel;
import javafx.application.Platform;

import java.io.DataInputStream;

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
                            roboRallyModel.process(envelope);
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
