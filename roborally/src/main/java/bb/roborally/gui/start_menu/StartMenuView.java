package bb.roborally.gui.start_menu;

import bb.roborally.game.Robot;
import bb.roborally.game.User;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.shape.Rectangle;
import javafx.stage.Popup;
import javafx.stage.PopupWindow;
import javafx.stage.Stage;
import javafx.util.Callback;

public class StartMenuView {
    private Stage stage;
    private GridPane view;
    private Popup popup;
    private Label errorMessage;
    private TextField usernameField;
    private ComboBox robotComboBox;
    private ComboBox<String> mapComboBox;
    private Button startButton;
    private ListView<User> usersListView;
    private Button submitButton;
    private Button readyButton;
    public StartMenuView(Stage stage) {
        this.stage = stage;
        buildUI();
    }
    public  TextField getUsernameField() {
        return usernameField;
    }
    public ComboBox<Robot> getRobotComboBox(){
        return robotComboBox;
    }
    public  Button getSubmitButton(){
        return submitButton;
    }
    public Popup getPopup() {
        return popup;
    }
    public Label getErrorMessage() {
        return errorMessage;
    }

    public Button getReadyButton() {return readyButton;}

    public ListView<User> getUsersListView() {
        return usersListView;
    }

    Callback<ListView<Robot>, ListCell<Robot>> robotComboBoxCellFactory = new Callback<ListView<Robot>, ListCell<Robot>>() {
        @Override
        public ListCell<Robot> call(ListView<Robot> l) {
            return new ListCell<Robot>() {
                @Override
                protected void updateItem(Robot item, boolean empty) {
                    super.updateItem(item, empty);
                    if (item == null || empty) {
                        setGraphic(null);
                    } else {
                        if (item.isAvailable()) {
                            setText(item.getFigureId() + ": " + item.getName());
                        } else {
                            setGraphic(null);
                        }
                    }
                }
            } ;
        }
    };

    Callback<ListView<User>, ListCell<User>> usersListViewCellFactory = new Callback<ListView<User>, ListCell<User>>() {
        @Override
        public ListCell<User> call(ListView<User> l) {
            return new ListCell<User>() {
                @Override
                protected void updateItem(User item, boolean empty) {
                    super.updateItem(item, empty);
                    if (item == null || empty) {
                        setGraphic(null);
                    } else {
                        if (item.playerAddedProperty().get()) {
                            if (item.isReady()) {
                                setText("[ready] " + item.getName() + "(" + item.getRobot().getName() + ")");
                            } else {
                                setText(item.getName() + "(" + item.getRobot().getName() + ")");
                            }
                        } else {
                            setGraphic(null);
                        }
                    }
                }
            } ;
        }
    };

    private void buildUI() {
        view = new GridPane();
        view.setId("loginView");
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
        Label title = new Label("Login");
        Separator separator = new Separator();
        usernameField = new TextField();
        usernameField.setPromptText("Username");
        robotComboBox = new ComboBox();
        robotComboBox.setCellFactory(robotComboBoxCellFactory);
        usersListView = new ListView<>();
        usersListView.setCellFactory(usersListViewCellFactory);
        usersListView.setPrefHeight(80);
        submitButton = new Button("Submit");
        submitButton.setId("loginButton");
        readyButton = new Button("Ready");
        readyButton.setDisable(true);
        Label mapLabel = new Label("Select a map");
        mapComboBox = new ComboBox();
        startButton = new Button("Start");
        mapComboBox.setDisable(true);
        startButton.setDisable(true);
        view.setVgap(16);
        view.setAlignment(Pos.CENTER);
        GridPane.setHalignment(submitButton, HPos.CENTER);
        GridPane.setHalignment(title, HPos.CENTER);

        view.addRow(0, title);
        view.addRow(1, separator);
        view.addRow(2, usernameField);
        view.addRow(3, robotComboBox);
        view.addRow(4, submitButton);
        view.addRow(5, usersListView);
        view.addRow(6, readyButton);
        view.addRow(7, mapLabel);
        view.addRow(8, mapComboBox);
        view.addRow(9, startButton);

    }

    public Parent getParent() {
        return view ;
    }

    public ComboBox getMapComboBox() {
        return mapComboBox;
    }

    public Button getStartButton() {
        return startButton;
    }

    public void showErrorPopup() {
        popup.show(stage);
    }

    public void hideErrorPopup() {
        popup.hide();
    }
}
