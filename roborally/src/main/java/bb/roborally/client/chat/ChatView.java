package bb.roborally.client.chat;

import bb.roborally.client.player_list.Player;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.util.Callback;

public class ChatView {
    private final VBox container = new VBox();
    private final ListView<String> chatListView = new ListView<>();
    private final TextField messageField = new TextField();
    private final ComboBox<Player> playerComboBox = new ComboBox<>();
    private final Button clearTargetButton = new Button("Clear");
    private final Button sendButton = new Button("Send");
    Callback<ListView<Player>, ListCell<Player>> playerComboBoxCellFactory = new Callback<ListView<Player>, ListCell<Player>>() {
        @Override
        public ListCell<Player> call(ListView<Player> l) {
            return new ListCell<Player>() {
                @Override
                protected void updateItem(Player item, boolean empty) {
                    super.updateItem(item, empty);
                    if (item == null || empty) {
                        setGraphic(null);
                    } else {
                        setText(item.getName() + "(" + item.getId() + ")");
                    }
                }
            } ;
        }
    };

    public ChatView() {
        playerComboBox.setCellFactory(playerComboBoxCellFactory);
        HBox chatFormHolder = new HBox();
        HBox messageTargetSelector = new HBox();
        chatFormHolder.getChildren().addAll(messageField, sendButton);
        messageTargetSelector.getChildren().addAll(playerComboBox, clearTargetButton);
        container.getChildren().addAll(chatListView, chatFormHolder, messageTargetSelector);
        applyStyle();
    }

    private void applyStyle() {
        sendButton.setStyle("-fx-background-color: #D6D6E7");
        messageField.setStyle("-fx-background-color: rgba(221, 221, 238, 0.3);");
        container.setStyle("-fx-background-color: #eee");
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

    public ComboBox<Player> getPlayerComboBox() {
        return playerComboBox;
    }

    public Button getClearTargetButton() {
        return clearTargetButton;
    }

    public Button getSendButton() {
        return sendButton;
    }
}
