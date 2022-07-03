package bb.roborally.gui.start_menu;

import bb.roborally.game.Robot;
import bb.roborally.game.User;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.util.Callback;

public class StartMenuView {
    private GridPane view;
    private TextField usernameField;
    private ComboBox robotComboBox;
    private Label infoLabel;
    private ListView<User> usersListView;
    private Button submitButton;
    private Button readyButton;
    public StartMenuView() {
        buildUI();
    }
    public  TextField getUsernameField() {
        return usernameField;
    }
    public ComboBox<Robot> getRobotComboBox(){
        return robotComboBox;
    }
    public Label getInfoLabel(){
        return infoLabel;
    }
    public  Button getSubmitButton(){
        return submitButton;
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
                        if (item.getPlayerAddedProperty().get()) {
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
        robotComboBox = new ComboBox();
        robotComboBox.setCellFactory(robotComboBoxCellFactory);
        usersListView = new ListView<>();
        usersListView.setCellFactory(usersListViewCellFactory);
        usersListView.setPrefHeight(80);
        infoLabel = new Label();
        submitButton = new Button("Submit");
        submitButton.setId("loginButton");
        readyButton = new Button("Ready");
        readyButton.setDisable(true);
        view.setVgap(16);
        view.setAlignment(Pos.CENTER);
        GridPane.setHalignment(submitButton, HPos.CENTER);
        GridPane.setHalignment(title, HPos.CENTER);

        view.addRow(0, title);
        view.addRow(1, separator);
        view.addRow(2, usernameField);
        view.addRow(3, robotComboBox);
        view.addRow(4, submitButton);
        view.addRow(5, infoLabel);
        view.addRow(6, usersListView);
        view.addRow(7, readyButton);
    }

    public Parent getParent() {
        return view ;
    }
}
