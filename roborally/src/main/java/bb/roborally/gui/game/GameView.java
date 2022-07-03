package bb.roborally.gui.game;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.TextAlignment;

public class GameView {
    private GridPane view;
    private ListView<String> chatListView;
    private TextField messageField;
    private Button sendButton;
    private Button chat;
    private Button playerStatus;
    private Label time;
    private Label playerMat;
    private Label upgradeCards;
    private Label phases;
    private Label gameBoard;
    private Label upgradeShop;
    public GameView() {
        buildUI();
    }

    public void buildUI() {
        view = new GridPane();
        chatListView = new ListView<>();

        HBox chatFormHolder = new HBox();
        HBox clickOption = new HBox();
        HBox timer = new HBox();
        HBox phase = new HBox();
        HBox program = new HBox();
        HBox upgrade = new HBox();
        HBox gameBoards = new HBox();
        HBox shop = new HBox();
        VBox chatContainer = new VBox();
        VBox cards = new VBox();
        VBox rightSide = new VBox(timer,phase,chatContainer);
        VBox leftSide = new VBox(gameBoards,cards,shop);

        messageField = new TextField();
        sendButton = new Button("Send");
        chat = new Button("Chat");
        playerStatus = new Button("Player Status");
        time = new Label("TIMER");
        phases = new Label("PHASE");
        gameBoard = new Label("Game Board");
        playerMat = new Label("Player Mat");
        upgradeCards = new Label("Upgrade Cards");
        upgradeShop = new Label("Upgrade shop/ Program Cards");

        cards.getChildren().addAll(program,upgrade);
        chatFormHolder.getChildren().addAll(messageField, sendButton);
        clickOption.getChildren().addAll(chat,playerStatus);
        program.getChildren().addAll(playerMat);
        upgrade.getChildren().addAll(upgradeCards);
        shop.getChildren().addAll(upgradeShop);
        timer.getChildren().addAll(time);
        gameBoards.getChildren().addAll(gameBoard);
        phase.getChildren().addAll(phases);
        chatContainer.getChildren().addAll(chatListView, chatFormHolder,clickOption);
        view.addColumn(1,rightSide);
        view.addColumn(0,leftSide);

        timer.setAlignment(Pos.CENTER);
        phase.setAlignment(Pos.CENTER);
        gameBoards.setAlignment(Pos.CENTER);
        program.setAlignment(Pos.CENTER);
        upgrade.setAlignment(Pos.CENTER);
        shop.setAlignment(Pos.CENTER);
        rightSide.setAlignment(Pos.BOTTOM_RIGHT);
        leftSide.setAlignment(Pos.TOP_LEFT);


        chatFormHolder.setSpacing(20);
        timer.setPrefHeight(50);
        phase.setPrefHeight(50);
        gameBoards.setPrefHeight(300);
        gameBoards.setPrefWidth(500);
        rightSide.setSpacing(20);
        rightSide.setPrefWidth(300);
        leftSide.setSpacing(20);
        leftSide.setPrefWidth(600);
        rightSide.setPadding(new Insets(20,20,20,20));
        leftSide.setPadding(new Insets(20, 20, 20, 20));

        //chatContainer.setStyle("-fx-background-color: #FFFFFF");
        sendButton.setStyle("-fx-background-color: #D6D6E7");
        messageField.setStyle("-fx-background-color: rgba(221, 221, 238, 0.3);");
        timer.setStyle("-fx-background-color: rgba(239, 246, 252, 0.87);");
        phase.setStyle("-fx-background-color: #6666FF");
        gameBoards.setStyle("-fx-background-color: #FFFFFF");
        program.setStyle("-fx-background-color: rgba(214, 214, 231, 0.87);");
        upgrade.setStyle("-fx-background-color: #D6D6E7");
        shop.setStyle("-fx-background-color: rgba(214, 214, 231, 0.87)");
        view.setStyle("-fx-background-color:linear-gradient(180deg, #386D8B 0%, #494986 47.15%, rgba(99, 131, 149, 0.6) 99.79%);");
        //view.addRow(2, chatContainer);
        //view.addRow(0,timer);
        //view.addRow(1,phase);
        //rightSide.setHgap(10);
        //view.setVgap(10);
        //view.setAlignment(Pos.BOTTOM_RIGHT);
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

    //private void messagePlayers(){
    //    //check if the name is already taken or emtpty
    //    if (view.getUsernameField().getText() == null || view.getUsernameField().getText().trim().isEmpty()) {
    //        view.getInfoLabel().setText("Error: Missing username!");
    //    }
    //    //sends messages using UTF8 coding
    //    else{
    //        String username = view.getUsernameField().getText();
    //        int robotIndex = (int) view.getRobotComboBox().getValue();
    //        PlayerValues playerValues = new PlayerValues(username, robotIndex);
    //        try {
    //            NetworkConnection.getInstance().getDataOutputStream().writeUTF(playerValues.toJson());
    //        } catch (IOException e) {
    //            throw new RuntimeException(e);
    //        }
//
    //    }
//
    //}

}
