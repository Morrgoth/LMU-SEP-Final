package bb.roborally.gui.game;

import bb.roborally.game.User;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
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
    private Button chat;
    private Button playerStatus;
    private Label time;
    private Label playerMat;
    private Label upgradeCards;
    private Label phases;
    private Label gameBoard;
    private Label upgradeShop;

    Callback<ListView<User>, ListCell<User>> usersComboBoxCellFactory = new Callback<ListView<User>, ListCell<User>>() {
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
    public GameView() {
        buildUI();
    }

    public void buildUI() {
        view = new GridPane();
        chatListView = new ListView<>();
        usersCombobox = new ComboBox();
        usersCombobox.setCellFactory(usersComboBoxCellFactory);
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

        HBox chatFormHolder = new HBox();
        HBox clickOption = new HBox();
        HBox timer = new HBox();
        HBox phase = new HBox();
        HBox program = new HBox();
        HBox upgrade = new HBox();
        HBox gameBoards = new HBox();
        HBox shop = new HBox();
        VBox chatContainer = new VBox();
        HBox messageTargetSelector = new HBox();
        clearTargetButton = new Button("Clear");
        messageTargetSelector.getChildren().addAll(usersCombobox, clearTargetButton);
        chatContainer.getChildren().addAll(chatListView, chatFormHolder, messageTargetSelector);
        VBox cards = new VBox();
        cards.getChildren().addAll(program,upgrade);
        chatFormHolder.getChildren().addAll(messageField, sendButton);
        clickOption.getChildren().addAll(chat,playerStatus);
        program.getChildren().addAll(playerMat);
        upgrade.getChildren().addAll(upgradeCards);
        shop.getChildren().addAll(upgradeShop);
        timer.getChildren().addAll(time);
        gameBoards.getChildren().addAll(gameBoard);
        phase.getChildren().addAll(phases);
        VBox rightSide = new VBox(timer,phase,chatContainer);
        VBox leftSide = new VBox(gameBoards,cards,shop);
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
