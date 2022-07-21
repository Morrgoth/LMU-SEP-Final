package bb.roborally.client.start_menu;

import bb.roborally.client.map_selector.MapSelectorView;
import bb.roborally.client.player_list.PlayerListView;
import bb.roborally.client.robot_selector.RobotSelectorView;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;

public class StartMenuView {
    private final GridPane view = new GridPane();
    private final TextField usernameField = new TextField();
    private final RobotSelectorView robotSelectorView = new RobotSelectorView();
    private final PlayerListView playerListView = new PlayerListView(PlayerListView.Kind.COMPACT);
    private final MapSelectorView mapSelectorView = new MapSelectorView();
    private final Button submitButton = new Button("Submit");
    private final Button readyButton = new Button("Ready");
    private final Button startButton = new Button("Start");
    public StartMenuView() {
        view.setId("loginView");
        Label title = new Label("Login");
        Separator separator = new Separator();
        usernameField.setPromptText("Username");

        readyButton.setDisable(true);
        mapSelectorView.disable(true);
        startButton.setDisable(true);
        view.setVgap(16);
        view.setAlignment(Pos.CENTER);
        GridPane.setHalignment(submitButton, HPos.CENTER);
        GridPane.setHalignment(title, HPos.CENTER);

        playerListView.setPrefHeight(80);

        view.addRow(0, title);
        view.addRow(1, separator);
        view.addRow(2, usernameField);
        view.addRow(3, robotSelectorView.getView());
        view.addRow(4, submitButton);
        view.addRow(5, playerListView.getListView());
        view.addRow(6, readyButton);
        view.addRow(7, mapSelectorView.getView());
        view.addRow(8, startButton);
    }
    public Parent getView() {
        return view ;
    }

    public  TextField getUsernameField() {
        return usernameField;
    }

    public  Button getSubmitButton(){
        return submitButton;
    }

    public Button getReadyButton() {return readyButton;}

    public Button getStartButton() {
        return startButton;
    }

    public RobotSelectorView getRobotSelectorView() {
        return robotSelectorView;
    }

    public PlayerListView getPlayerListView() {
        return playerListView;
    }

    public MapSelectorView getMapSelectorView() {
        return mapSelectorView;
    }

}
