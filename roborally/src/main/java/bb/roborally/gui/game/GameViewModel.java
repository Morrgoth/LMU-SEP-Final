package bb.roborally.gui.game;

import bb.roborally.data.messages.chat.SendChat;
import bb.roborally.gui.RoboRally;
import bb.roborally.gui.RoboRallyModel;
import bb.roborally.networking.NetworkConnection;
import javafx.collections.ListChangeListener;
import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

import java.io.IOException;

public class GameViewModel {
    private final RoboRallyModel roboRallyModel;
    private final GameView view;

    public GameViewModel(RoboRallyModel roboRallyModel, GameView gameView) {
        this.roboRallyModel = roboRallyModel;
        view = gameView;
        setUpListeners();
        view.getChatListView().setItems(roboRallyModel.chatMessagesProperty());
    }

    private void setUpListeners() {

        view.getChatListView().getItems().addListener(new ListChangeListener<String>() {
            @Override
            public void onChanged(Change<? extends String> change) {
                view.getChatListView().scrollTo(view.getChatListView().getItems().size() - 1);
            }
        });
        view.getSendButton().setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                String message = view.getMessageField().getText();
                view.getMessageField().setText("");
                SendChat sendChat = new SendChat(message, -1);
                try {
                    NetworkConnection.getInstance().getDataOutputStream().writeUTF(sendChat.toJson());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        view.getMessageField().setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                if (keyEvent.getCode() == KeyCode.ENTER) {
                    String message = view.getMessageField().getText();
                    view.getMessageField().setText("");
                    SendChat sendChat = new SendChat(message, -1);
                    try {
                        NetworkConnection.getInstance().getDataOutputStream().writeUTF(sendChat.toJson());
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        });
    }

}
