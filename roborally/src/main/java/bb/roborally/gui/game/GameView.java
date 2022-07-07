package bb.roborally.gui.game;

import bb.roborally.game.User;
import bb.roborally.game.cards.PlayingCard;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Popup;
import javafx.stage.Stage;
import javafx.util.Callback;

public class GameView {
    private Stage stage;
    private Popup popup;
    private Label errorMessage;
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
    private HBox programmingInterface;
    private VBox programmingInterfaceLeftCol;
    private VBox programmingInterfaceRightCol;
    private ComboBox<PlayingCard> register1ComboBox;
    private Button clearRegister1Button;
    private ComboBox<PlayingCard> register2ComboBox;
    private Button clearRegister2Button;
    private ComboBox<PlayingCard> register3ComboBox;
    private Button clearRegister3Button;
    private ComboBox<PlayingCard> register4ComboBox;
    private Button clearRegister4Button;
    private ComboBox<PlayingCard> register5ComboBox;
    private Button clearRegister5Button;
    private Button resetProgramButton;
    private Button submitProgramButton;

    private GameBoardView gameBoardView;

    public GameBoardView getGameBoardView() {
        return gameBoardView;
    }

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

    Callback<ListView<PlayingCard>, ListCell<PlayingCard>> registerComboBoxCellFactory = new Callback<ListView<PlayingCard>, ListCell<PlayingCard>>() {
        @Override
        public ListCell<PlayingCard> call(ListView<PlayingCard> stringListView) {
            return new ListCell<PlayingCard>() {
                @Override
                protected void updateItem(PlayingCard item, boolean empty) {
                    super.updateItem(item, empty);
                    if (item == null || empty) {
                        setGraphic(null);
                    } else {
                        if (!item.isMarked()) {
                            setText(item.getName());
                        } else {
                            setGraphic(null);
                        }
                    }
                }
            };
        }
    };

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
        HBox gameBoard = new HBox();
        programmingInterface = new HBox();
        VBox chatContainer = new VBox();
        HBox messageTargetSelector = new HBox();
        clearTargetButton = new Button("Clear");
        messageTargetSelector.getChildren().addAll(usersCombobox, clearTargetButton);
        chatContainer.getChildren().addAll(chatListView, chatFormHolder, messageTargetSelector);
        VBox cards = new VBox();
        cards.getChildren().addAll(program,upgrade);
        chatFormHolder.getChildren().addAll(messageField, sendButton);
        clickOption.getChildren().addAll(chat,playerStatus);
        // TODO: Uncomment, commented out so I can test the Programming Phase
        //program.getChildren().addAll(playerMat);
        //upgrade.getChildren().addAll(upgradeCards);
        programmingInterfaceLeftCol = new VBox();
        programmingInterfaceRightCol = new VBox();
        HBox register1 = new HBox();
        Label registerLabel1 = new Label("Register 1");
        register1ComboBox = new ComboBox<>();
        register1ComboBox.setPrefWidth(150);
        register1ComboBox.setCellFactory(registerComboBoxCellFactory);
        clearRegister1Button = new Button("Clear");
        register1.getChildren().addAll(registerLabel1, register1ComboBox, clearRegister1Button);
        HBox register2 = new HBox();
        Label registerLabel2 = new Label("Register 2");
        register2ComboBox = new ComboBox<>();
        register2ComboBox.setPrefWidth(150);
        register2ComboBox.setCellFactory(registerComboBoxCellFactory);
        clearRegister2Button = new Button("Clear");
        register2.getChildren().addAll(registerLabel2, register2ComboBox, clearRegister2Button);
        HBox register3 = new HBox();
        Label registerLabel3 = new Label("Register 3");
        register3ComboBox = new ComboBox<>();
        register3ComboBox.setPrefWidth(150);
        register3ComboBox.setCellFactory(registerComboBoxCellFactory);
        clearRegister3Button = new Button("Clear");
        register3.getChildren().addAll(registerLabel3, register3ComboBox, clearRegister3Button);
        programmingInterfaceLeftCol.getChildren().addAll(register1, register2, register3);
        HBox register4 = new HBox();
        Label registerLabel4 = new Label("Register 4");
        register4ComboBox = new ComboBox<>();
        register4ComboBox.setPrefWidth(150);
        register4ComboBox.setCellFactory(registerComboBoxCellFactory);
        clearRegister4Button = new Button("Clear");
        register4.getChildren().addAll(registerLabel4, register4ComboBox, clearRegister4Button);
        HBox register5 = new HBox();
        Label registerLabel5 = new Label("Register 5");
        register5ComboBox = new ComboBox<>();
        register5ComboBox.setPrefWidth(150);
        register5ComboBox.setCellFactory(registerComboBoxCellFactory);
        clearRegister5Button = new Button("Clear");
        register5.getChildren().addAll(registerLabel5, register5ComboBox, getClearRegister5Button());
        HBox controlPanel = new HBox();
        resetProgramButton = new Button("Clear All");
        submitProgramButton = new Button("Submit");
        controlPanel.getChildren().addAll(resetProgramButton, submitProgramButton);
        programmingInterfaceRightCol.getChildren().addAll(register4, register5, controlPanel);
        programmingInterface.getChildren().addAll(upgradeShop);
        timer.getChildren().addAll(time);
        gameBoard.getChildren().addAll(this.gameBoard);
        phase.getChildren().addAll(phases);
        VBox rightSide = new VBox(timer,phase,chatContainer);
        gameBoardView = new GameBoardView();
        VBox leftSide = new VBox(gameBoardView.getGameBoard(),cards,programmingInterface);
        view.addColumn(1,rightSide);
        view.addColumn(0,leftSide);

        timer.setAlignment(Pos.CENTER);
        phase.setAlignment(Pos.CENTER);
        gameBoard.setAlignment(Pos.CENTER);
        program.setAlignment(Pos.CENTER);
        upgrade.setAlignment(Pos.CENTER);
        programmingInterface.setAlignment(Pos.CENTER);
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
        sendButton.setStyle("-fx-background-color: #D6D6E7");
        messageField.setStyle("-fx-background-color: rgba(221, 221, 238, 0.3);");
        timer.setStyle("-fx-background-color: rgba(239, 246, 252, 0.87);");
        phase.setStyle("-fx-background-color: #6666FF");
        gameBoard.setStyle("-fx-background-color: #FFFFFF");
        program.setStyle("-fx-background-color: rgba(214, 214, 231, 0.87);");
        upgrade.setStyle("-fx-background-color: #D6D6E7");
        programmingInterface.setStyle("-fx-background-color: rgba(214, 214, 231, 0.87)");
        view.setStyle("-fx-background-color:linear-gradient(to bottom, #386D8B, #494986, #638395)");
        //("-fx-background-color:linear-gradient(to left, #3b8d99, #6b6b83, #aa4b6b)");
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
    public HBox getProgrammingInterface() {
        return programmingInterface;
    }

    public Button getSubmitProgramButton() {
        return submitProgramButton;
    }

    public Button getResetProgramButton() {
        return resetProgramButton;
    }

    public ComboBox<PlayingCard> getRegister1ComboBox() {
        return register1ComboBox;
    }

    public ComboBox<PlayingCard> getRegister2ComboBox() {
        return register2ComboBox;
    }

    public ComboBox<PlayingCard> getRegister3ComboBox() {
        return register3ComboBox;
    }

    public ComboBox<PlayingCard> getRegister4ComboBox() {
        return register4ComboBox;
    }

    public ComboBox<PlayingCard> getRegister5ComboBox() {
        return register5ComboBox;
    }

    public Button getClearRegister1Button() {
        return clearRegister1Button;
    }

    public Button getClearRegister2Button() {
        return clearRegister2Button;
    }

    public Button getClearRegister3Button() {
        return clearRegister3Button;
    }

    public Button getClearRegister4Button() {
        return clearRegister4Button;
    }

    public Button getClearRegister5Button() {
        return clearRegister5Button;
    }

    public VBox getProgrammingInterfaceLeftCol() {
        return programmingInterfaceLeftCol;
    }

    public VBox getProgrammingInterfaceRightCol() {
        return programmingInterfaceRightCol;
    }
}
