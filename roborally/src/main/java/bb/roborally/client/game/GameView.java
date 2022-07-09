package bb.roborally.client.game;

import bb.roborally.client.chat.ChatView;
import bb.roborally.client.programming_interface.ProgrammingInterfaceView;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Popup;
import javafx.stage.Stage;

public class GameView {
    private Stage stage;
    private Popup popup;
    private Label errorMessage;
    private GridPane view;
    private Button chat;
    private Button playerStatus;
    private Label time;
    private Label playerMat;
    private Label upgradeCards;
    private Label phases;
    private Label gameBoard;
    private Label upgradeShop;
    private final ChatView chatView = new ChatView();
    private final ProgrammingInterfaceView programmingInterfaceView = new ProgrammingInterfaceView();
    private HBox controlBox;

    private GameBoardView gameBoardView;

    public GameBoardView getGameBoardView() {
        return gameBoardView;
    }

    public GameView(Stage stage) {
        this.stage = stage;
        buildUI();
    }

    public void buildUI() {
        popup = new Popup();
        popup.centerOnScreen();
        errorMessage = new Label();
        HBox errorBox = new HBox();
        errorBox.setPrefHeight(40);
        errorBox.setPrefWidth(600);
        errorBox.setStyle("-fx-background-color: #ff6961; -fx-background-radius: 10 10 10 10");
        errorBox.setAlignment(Pos.CENTER);
        errorBox.getChildren().add(errorMessage);
        popup.getContent().addAll(errorBox);
        view = new GridPane();
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
        HBox gameBoard = new HBox();
        controlBox = new HBox();
        VBox chatContainer = new VBox();
        chatContainer.getChildren().add(chatView.getView());
        VBox cards = new VBox();
        cards.getChildren().addAll(program,upgrade);

        clickOption.getChildren().addAll(chat,playerStatus);
        controlBox.getChildren().addAll(upgradeShop);
        timer.getChildren().addAll(time);
        gameBoard.getChildren().addAll(this.gameBoard);
        phase.getChildren().addAll(phases);
        VBox rightSide = new VBox(timer,phase,chatContainer);
        gameBoardView = new GameBoardView();
        VBox leftSide = new VBox(gameBoardView.getGameBoard(),cards, controlBox);
        view.addColumn(1,rightSide);
        view.addColumn(0,leftSide);

        timer.setAlignment(Pos.CENTER);
        phase.setAlignment(Pos.CENTER);
        gameBoard.setAlignment(Pos.CENTER);
        program.setAlignment(Pos.CENTER);
        upgrade.setAlignment(Pos.CENTER);
        controlBox.setAlignment(Pos.CENTER);
        rightSide.setAlignment(Pos.BOTTOM_RIGHT);
        leftSide.setAlignment(Pos.TOP_LEFT);


        chatFormHolder.setSpacing(20);
        timer.setPrefHeight(50);
        phase.setPrefHeight(50);
        gameBoard.setPrefHeight(300);
        gameBoard.setPrefWidth(500);
        rightSide.setSpacing(20);
        rightSide.setPrefWidth(300);
        leftSide.setSpacing(20);
        leftSide.setPrefWidth(600);
        rightSide.setPadding(new Insets(20,20,20,20));
        leftSide.setPadding(new Insets(20, 20, 20, 20));

        //chatContainer.setStyle("-fx-background-color: #FFFFFF");

        timer.setStyle("-fx-background-color: rgba(239, 246, 252, 0.87);");
        phase.setStyle("-fx-background-color: #6666FF");
        gameBoard.setStyle("-fx-background-color: #FFFFFF");
        program.setStyle("-fx-background-color: rgba(214, 214, 231, 0.87);");
        upgrade.setStyle("-fx-background-color: #D6D6E7");
        controlBox.setStyle("-fx-background-color: rgba(214, 214, 231, 0.87)");
        view.setStyle("-fx-background-color:linear-gradient(to bottom, #386D8B, #494986, #638395)");
        //("-fx-background-color:linear-gradient(to left, #3b8d99, #6b6b83, #aa4b6b)");
    }

    public Parent getParent() {
        return view;
    }

    public Label getPhases() {
        return phases;
    }

    public void showErrorPopup() {
        popup.show(stage);
    }

    public void hideErrorPopup() {
        popup.hide();
    }
    public Popup getPopup() {
        return popup;
    }
    public Label getErrorMessage() {
        return errorMessage;
    }
    public HBox getControlBox() {
        return controlBox;
    }

    public ProgrammingInterfaceView getProgrammingInterfaceView() {
        return programmingInterfaceView;
    }

    public ChatView getChatView() {
        return chatView;
    }
}
