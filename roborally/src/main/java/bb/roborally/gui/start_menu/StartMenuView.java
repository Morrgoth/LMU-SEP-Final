package bb.roborally.gui.start_menu;

import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;

public class StartMenuView {
    private GridPane view;
    private TextField usernameField;
    private ComboBox robotComboBox;
    private Label infoLabel;
    private ListView<String> usersListView;
    private Button submitButton;
    private Button readyButton;
    public StartMenuView() {
        buildUI();
    }
    public  TextField getUsernameField() {
        return usernameField;
    }
    public  ComboBox getRobotComboBox(){
        return robotComboBox;
    }
    public Label getInfoLabel(){
        return infoLabel;
    }
    public  Button getSubmitButton(){
        return submitButton;
    }

    public Button getReadyButton() {return readyButton;}

    public ListView<String> getUsersListView() {
        return usersListView;
    }

    private void buildUI() {
        view = new GridPane();
        view.setId("loginView");
        Label title = new Label("Login");
        Separator separator = new Separator();
        usernameField = new TextField();
        usernameField.setPromptText("Username");
        robotComboBox = new ComboBox();
        robotComboBox.getItems().add(1);
        robotComboBox.getItems().add(2);
        robotComboBox.getItems().add(3);
        robotComboBox.getItems().add(4);
        robotComboBox.getItems().add(5);
        usersListView = new ListView<>();
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
