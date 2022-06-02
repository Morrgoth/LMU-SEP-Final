package bb.roborally.gui.game;

import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class GameView {
    GridPane view;
    ListView<String> chatListView;

    public GameView() {
        buildUI();
    }

    public void buildUI() {
        view = new GridPane();
        VBox chatContainer = new VBox();
        chatListView = new ListView<>();
        HBox chatFormHolder = new HBox();
        TextField messageField = new TextField();
        Button sendButton = new Button("Send");
        chatFormHolder.getChildren().addAll(messageField, sendButton);
        chatContainer.getChildren().addAll(chatListView, chatFormHolder);
        view.addRow(0, chatContainer);
    }

    public Parent getParent() {
        return view;
    }
}
