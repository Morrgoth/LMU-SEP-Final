package bb.roborally.networking;

import bb.roborally.gui.game.GameModel;

import java.io.DataOutputStream;

public class ClientWriterThread extends Thread{
    private GameModel gameModel;
    private DataOutputStream dataOutputStream;
    public ClientWriterThread(DataOutputStream dataOutputStream, GameModel gameModel){
        this.dataOutputStream = dataOutputStream;
        this.gameModel = gameModel;
    }
    /**
     * This method listens to the changes of the ChatModel, and upon detecting a new Message written by the User
     * it forwards it to the Server.
     */
    public void run()
    {
        System.out.println("ClientWriterThreadUI started running");
        // Keep listening for messages to send

    }
}
