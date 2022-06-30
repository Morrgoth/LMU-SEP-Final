package bb.roborally.gui.game;

import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class GameViewCell extends ListCell<String> {
    //has errors for now
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
