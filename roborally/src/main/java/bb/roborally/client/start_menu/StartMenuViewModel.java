package bb.roborally.client.start_menu;

import bb.roborally.client.ViewManager;
import bb.roborally.client.map_selector.MapSelectorViewModel;
import bb.roborally.client.notification.Notification;
import bb.roborally.client.player_list.PlayerListViewModel;
import bb.roborally.client.robot_selector.RobotSelectorViewModel;
import bb.roborally.protocol.lobby.PlayerValues;
import bb.roborally.protocol.lobby.SetStatus;
import bb.roborally.protocol.map.MapSelected;
import bb.roborally.client.RoboRallyModel;
import bb.roborally.client.networking.NetworkConnection;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

import java.io.IOException;

public class StartMenuViewModel {
    private final RoboRallyModel roboRallyModel;
    private StartMenuView view;

    public StartMenuViewModel(RoboRallyModel roboRallyModel) {
        this.roboRallyModel = roboRallyModel;
    }

    public void connect(StartMenuView view) {
        this.view = view;
        observeModelandUpdate();
        setupListeners();
    }
    /**
     * Listens for changes in the LoginModel and updates the GUI accordingly
     */
    private void observeModelandUpdate() {
        RobotSelectorViewModel robotSelectorViewModel = new RobotSelectorViewModel(roboRallyModel);
        robotSelectorViewModel.connect(view.getRobotSelectorView());
        MapSelectorViewModel mapSelectorViewModel = new MapSelectorViewModel(roboRallyModel);
        mapSelectorViewModel.connect(view.getMapSelectorView());
        PlayerListViewModel playerListViewModel = new PlayerListViewModel(roboRallyModel.getPlayerQueue());
        playerListViewModel.connect(view.getPlayerListView());

        roboRallyModel.getPlayerQueue().getLocalPlayer().addedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observableValue, Boolean oldVal, Boolean newVal) {
                if (newVal) {
                    view.getReadyButton().setDisable(false);
                    view.getUsernameField().setDisable(true);
                    view.getRobotSelectorView().disable(true);
                    view.getSubmitButton().setDisable(true);
                }
            }
        });

        roboRallyModel.getPlayerQueue().getLocalPlayer().readyProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observableValue, Boolean oldVal, Boolean newVal) {
                if (newVal) {
                    view.getReadyButton().setText("Not Ready");
                } else {
                    view.getReadyButton().setText("Ready");
                }
            }
        });

        roboRallyModel.getPlayerQueue().getLocalPlayer().mapSelectorProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observableValue, Boolean oldVal, Boolean newVal) {
                view.getMapSelectorView().disable(!newVal);
                view.getMapSelectorView().clearSelection();
            }
        });

        roboRallyModel.gameStartedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observableValue, Boolean oldVal, Boolean newVal) {
                if (newVal) {
                    ViewManager.openGameView();
                }
            }
        });
    }

    private void submitPlayerValuesForm() {
        if (view.getUsernameField().getText() == null || view.getUsernameField().getText().trim().isEmpty()) {
            Notification.getInstance().show_medium(Notification.Kind.ERROR, "Missing username!");
        } else if (view.getRobotSelectorView().getSelectedRobot() == null) {
            Notification.getInstance().show_medium(Notification.Kind.ERROR, "Missing robot!");
        } else {
            String username = view.getUsernameField().getText();
            int robotIndex = (int) view.getRobotSelectorView().getSelectedRobot().getId();
            PlayerValues playerValues = new PlayerValues(username, robotIndex);
            try {
                NetworkConnection.getInstance().getDataOutputStream().writeUTF(playerValues.toJson());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    /**
     * Listens for user input through the GUI.
     */
    private void setupListeners() {
        view.getSubmitButton().setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                submitPlayerValuesForm();
            }
        });

        view.getUsernameField().setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                if (keyEvent.getCode() == KeyCode.ENTER) {
                    submitPlayerValuesForm();
                }
            }
        });

        view.getReadyButton().setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                SetStatus setStatus = new SetStatus(!roboRallyModel.getPlayerQueue().getLocalPlayer().isReady());
                try {
                    NetworkConnection.getInstance().getDataOutputStream().writeUTF(setStatus.toJson());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        view.getMapSelectorView().getMapComboBox().getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observableValue, Object oldVal, Object newVal) {
                view.getStartButton().setDisable(newVal == null);
            }
        });

        view.getStartButton().setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                String map = (String) view.getMapSelectorView().getSelectedMap();
                MapSelected mapSelected = new MapSelected(map);
                try {
                    NetworkConnection.getInstance().getDataOutputStream().writeUTF(mapSelected.toJson());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }
}
