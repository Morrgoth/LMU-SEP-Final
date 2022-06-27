package bb.roborally.gui.game;

import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class GameView {
    private GridPane view;
    private ListView<String> chatListView;
    private TextField messageField;
    private Button sendButton;
    private Label test;
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
}
