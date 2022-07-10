package bb.roborally.client.start_menu;

import bb.roborally.client.robot_selector.RobotSelectorView;
import bb.roborally.server.game.Robot;
import bb.roborally.server.game.User;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Popup;
import javafx.stage.Stage;
import javafx.util.Callback;

public class StartMenuView {
    private GridPane view;
    private TextField usernameField;
    private final RobotSelectorView robotSelectorView = new RobotSelectorView();
    private ComboBox<String> mapComboBox;
    private Button startButton;
    private ListView<User> usersListView;
    private Button submitButton;
    private Button readyButton;
    public StartMenuView() {
        buildUI();
    }
    public  TextField getUsernameField() {
        return usernameField;
    }
    public  Button getSubmitButton(){
        return submitButton;
    }

    public Button getReadyButton() {return readyButton;}

    public ListView<User> getUsersListView() {
        return usersListView;
    }

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
        Label title = new Label("Login");
        Separator separator = new Separator();
        usernameField = new TextField();
        usernameField.setPromptText("Username");

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
        view.addRow(3, robotSelectorView.getView());
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

    public RobotSelectorView getRobotSelectorView() {
        return robotSelectorView;
    }
}
