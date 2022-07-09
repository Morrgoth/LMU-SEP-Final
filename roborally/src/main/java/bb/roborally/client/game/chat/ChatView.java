package bb.roborally.client.game.chat;

import bb.roborally.server.game.User;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.util.Callback;

public class ChatView {

    private final VBox container = new VBox();
    private final ListView<String> chatListView = new ListView<>();
    private final TextField messageField = new TextField();
    private final ComboBox<User> userComboBox = new ComboBox<User>();
    private final Button clearTargetButton = new Button("Clear");
    private final Button sendButton = new Button("Send");
    private final Callback<ListView<User>, ListCell<User>> usersComboBoxCellFactory = new Callback<ListView<User>, ListCell<User>>() {
        @Override
        public ListCell<User> call(ListView<User> l) {
            return new ListCell<User>() {
                @Override
                protected void updateItem(User item, boolean empty) {
                    super.updateItem(item, empty);
                    if (item == null || empty) {
                        setGraphic(null);
                    } else {
                        setText(item.getName() + "(" + item.getClientID() + ")");
                    }
                }
            } ;
        }
    };

    public ChatView() {
        userComboBox.setCellFactory(usersComboBoxCellFactory);
        HBox chatFormHolder = new HBox();
        HBox messageTargetSelector = new HBox();
        chatFormHolder.getChildren().addAll(messageField, sendButton);
        messageTargetSelector.getChildren().addAll(userComboBox, clearTargetButton);
        container.getChildren().addAll(chatListView, chatFormHolder, messageTargetSelector);
        applyStyle();
    }

    private void applyStyle() {
        sendButton.setStyle("-fx-background-color: #D6D6E7");
        messageField.setStyle("-fx-background-color: rgba(221, 221, 238, 0.3);");
    }


    public VBox getView() {
        return container;
    }

    public ListView<String> getChatListView() {
        return chatListView;
    }

    public TextField getMessageField() {
        return messageField;
    }

    public ComboBox<User> getUserComboBox() {
        return userComboBox;
    }

    public Button getClearTargetButton() {
        return clearTargetButton;
    }

    public Button getSendButton() {
        return sendButton;
    }
}
