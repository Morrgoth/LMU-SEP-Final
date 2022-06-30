package bb.roborally.gui.game;

import bb.roborally.game.User;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.util.Callback;

public class GameView {
    private GridPane view;
    private ListView<String> chatListView;
    private TextField messageField;
    private ComboBox<User> usersCombobox;

    private Button clearTargetButton;
    private Button sendButton;
    private Label test;
    public GameView() {
        buildUI();
    }

    public void buildUI() {
        Callback<ListView<User>, ListCell<User>> factory = lv -> new ListCell<User>() {
            @Override
            protected void updateItem(User item, boolean empty) {
                super.updateItem(item, empty);
                setText(empty ? "" : item.getName());
            }

        };
        view = new GridPane();
        VBox chatContainer = new VBox();
        chatListView = new ListView<>();
        HBox chatFormHolder = new HBox();
        HBox messageTargetSelector = new HBox();
        usersCombobox = new ComboBox();
        usersCombobox.setCellFactory(factory);
        messageField = new TextField();
        sendButton = new Button("Send");
        clearTargetButton = new Button("Clear");
        chatFormHolder.getChildren().addAll(messageField, sendButton);
        messageTargetSelector.getChildren().addAll(usersCombobox, clearTargetButton);
        chatContainer.getChildren().addAll(chatListView, chatFormHolder, messageTargetSelector);
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

    public Button getClearTargetButton() {
        return clearTargetButton;
    }

    public Parent getParent() {
        return view;
    }
    public ComboBox getUserComboBox(){
        return usersCombobox;
    }

}
