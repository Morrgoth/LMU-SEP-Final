package bb.roborally.client.start_menu;

import bb.roborally.client.map_selector.MapSelectorView;
import bb.roborally.client.robot_selector.RobotSelectorView;
import bb.roborally.server.game.User;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.util.Callback;

public class StartMenuView {
    private final GridPane view = new GridPane();
    private final TextField usernameField = new TextField();
    private final RobotSelectorView robotSelectorView = new RobotSelectorView();
    private ListView<User> usersListView;
    private final MapSelectorView mapSelectorView = new MapSelectorView();
    private final Button submitButton = new Button("Submit");
    private final Button readyButton = new Button("Ready");
    private final Button startButton = new Button("Start");
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
        view.setId("loginView");
        Label title = new Label("Login");
        Separator separator = new Separator();
        usernameField.setPromptText("Username");

        usersListView = new ListView<>();
        usersListView.setCellFactory(usersListViewCellFactory);
        usersListView.setPrefHeight(80);
        readyButton.setDisable(true);
        mapSelectorView.disable(true);
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
        view.addRow(7, mapSelectorView.getView());
        view.addRow(8, startButton);
    }

    public Parent getView() {
        return view ;
    }

    public Button getStartButton() {
        return startButton;
    }

    public RobotSelectorView getRobotSelectorView() {
        return robotSelectorView;
    }

    public MapSelectorView getMapSelectorView() {
        return mapSelectorView;
    }
}
