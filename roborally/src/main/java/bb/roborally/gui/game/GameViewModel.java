package bb.roborally.gui.game;

import bb.roborally.data.messages.chat.SendChat;
import bb.roborally.game.User;
import bb.roborally.gui.data.RoboRallyModel;
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
        observeModelAndUpdate();
        view.getChatListView().setItems(roboRallyModel.getObservableListChatMessages());
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
                sendMessage(message);
            }
        });

        view.getMessageField().setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                if (keyEvent.getCode() == KeyCode.ENTER) {
                    String message = view.getMessageField().getText().trim();
                    sendMessage(message);
                }
            }
        });

        view.getClearTargetButton().setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                view.getUserComboBox().getSelectionModel().clearSelection();
            }
        });
    }

    private void sendMessage(String message) {
        if (!message.equals("")) {
            view.getMessageField().setText("");
            SendChat sendChat;
            if (view.getUserComboBox().getValue() == null) {
                sendChat = new SendChat(message, -1);
            } else {
                User target = (User) view.getUserComboBox().getValue();
                sendChat = new SendChat(message, target.getClientID());
            }
            try {
                NetworkConnection.getInstance().getDataOutputStream().writeUTF(sendChat.toJson());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private void observeModelAndUpdate() {
        view.getUserComboBox().setItems(roboRallyModel.getPlayerRegistry().getObservableListUsers());
        view.getGameBoardView().populateBoard(roboRallyModel.getGameBoard());
    }

}
