package bb.roborally.client.chat;

import bb.roborally.client.RoboRallyModel;
import bb.roborally.client.networking.NetworkConnection;
import bb.roborally.client.notification.Notification;
import bb.roborally.client.player_list.Player;
import bb.roborally.protocol.chat.SendChat;
import javafx.collections.ListChangeListener;
import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

import java.io.IOException;

public class ChatViewModel {

    private final RoboRallyModel roboRallyModel;
    private ChatView view;

    public ChatViewModel(RoboRallyModel roboRallyModel) {
        this.roboRallyModel = roboRallyModel;
    }

    public void connect(ChatView chatView) {
        this.view = chatView;
        observeModelAndUpdate();
        setupListeners();
    }

    private void observeModelAndUpdate() {
        view.getChatListView().setItems(roboRallyModel.getObservableListChatMessages());
        view.getPlayerComboBox().setItems(roboRallyModel.getPlayerQueue().getObservableListPlayers());
    }

    private void setupListeners() {
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
                view.getPlayerComboBox().getSelectionModel().clearSelection();
            }
        });
    }

    private void sendMessage(String message) {
        if (!message.equals("")) {
            view.getMessageField().setText("");
            SendChat sendChat;
            if (view.getPlayerComboBox().getValue() == null) {
                sendChat = new SendChat(message, -1);
            } else {
                Player target = (Player) view.getPlayerComboBox().getValue();
                sendChat = new SendChat(message, target.getId());
            }
            try {
                NetworkConnection.getInstance().getDataOutputStream().writeUTF(sendChat.toJson());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else {
            Notification.getInstance().show_short(Notification.Kind.WARNING, "You cannot send empty messages!");
        }
    }
}
