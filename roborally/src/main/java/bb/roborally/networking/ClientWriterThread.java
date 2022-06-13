package bb.roborally.networking;

import bb.roborally.data.messages.ChatMessage;
import bb.roborally.gui.game.GameModel;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

import java.io.DataOutputStream;
import java.io.IOException;

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
        gameModel.currentMessageProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String oldVal, String newVal) {
                if (!newVal.equals("")) {
                    ChatMessage chatMessage = new ChatMessage(gameModel.getUser(), newVal);
                    try {
                        dataOutputStream.writeUTF(chatMessage.toJson());
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    gameModel.resetCurrentMessage();
                }
            }
        });

    }
}
