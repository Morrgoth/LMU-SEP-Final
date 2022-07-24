package bb.roborally.client.start_menu;

import bb.roborally.client.chat.ChatView;
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
    private final ChatView chatView = new ChatView();
    private final TextField usernameField = new TextField();
    private final RobotSelectorView robotSelectorView = new RobotSelectorView();
    private final PlayerListView playerListView = new PlayerListView(PlayerListView.Kind.COMPACT);
    private final MapSelectorView mapSelectorView = new MapSelectorView();
    private final Button submitButton = new Button("Submit");
    private final Button readyButton = new Button("Ready");
    private final Button startButton = new Button("Start");
    public StartMenuView() {
        GridPane leftCol = new GridPane();
        GridPane rightCol = new GridPane();
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

        leftCol.addRow(0, title);
        leftCol.addRow(1, separator);
        leftCol.addRow(2, usernameField);
        leftCol.addRow(3, robotSelectorView.getView());
        leftCol.addRow(4, submitButton);
        leftCol.addRow(5, playerListView.getListView());
        leftCol.addRow(6, readyButton);
        leftCol.addRow(7, mapSelectorView.getView());
        leftCol.addRow(8, startButton);

        rightCol.addRow(0, chatView.getView());

        view.addColumn(0, leftCol);
        view.addColumn(1, rightCol);

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

    public ChatView getChatView() {
        return chatView;
    }
}
