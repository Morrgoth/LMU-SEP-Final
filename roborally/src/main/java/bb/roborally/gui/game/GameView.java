package bb.roborally.gui.game;

import bb.roborally.data.messages.lobby.PlayerValues;
import bb.roborally.networking.NetworkConnection;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class GameView {
    private GridPane view;
    private ListView<String> chatListView;
    private TextField messageField;
    private Button sendButton;
    public GameView() {
        buildUI();
    }

    public void buildUI() {
        view = new GridPane();
        VBox chatContainer = new VBox();
        chatListView = new ListView<>();
        HBox chatFormHolder = new HBox();
        messageField = new TextField();
        sendButton = new Button("Send");
        chatFormHolder.getChildren().addAll(messageField, sendButton);
        chatContainer.getChildren().addAll(chatListView, chatFormHolder);
        view.addRow(0, chatContainer);
    }

    public ListView<String> getChatListView() {
        return this.chatListView;
    }

    public TextField getMessageField() {
        return this.messageField;
    }

    public Button getSendButton() {
        return this.sendButton;
    }

    public Parent getParent() {
        return view;
    }

    private void messagePlayers(){
        //check if the name is already taken or emtpty
        if (view.getUsernameField().getText() == null || view.getUsernameField().getText().trim().isEmpty()) {
            view.getInfoLabel().setText("Error: Missing username!");
        }
        //sends messages using UTF8 coding
        else{
            String username = view.getUsernameField().getText();
            int robotIndex = (int) view.getRobotComboBox().getValue();
            PlayerValues playerValues = new PlayerValues(username, robotIndex);
            try {
                NetworkConnection.getInstance().getDataOutputStream().writeUTF(playerValues.toJson());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }

    }

}
