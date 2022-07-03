package bb.roborally.gui.start_menu;

import bb.roborally.data.messages.lobby.PlayerValues;
import bb.roborally.data.messages.lobby.SetStatus;
import bb.roborally.data.messages.map.MapSelected;
import bb.roborally.gui.RoboRally;
import bb.roborally.gui.data.RoboRallyModel;
import bb.roborally.networking.NetworkConnection;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

import java.io.IOException;

public class StartMenuViewModel {
    private final RoboRally roboRally;
    private final RoboRallyModel roboRallyModel;
    private final StartMenuView view;

    public StartMenuViewModel(RoboRally roboRally, RoboRallyModel roboRallyModel, StartMenuView startMenuView) {
        this.roboRally = roboRally;
        this.roboRallyModel = roboRallyModel;
        view = startMenuView;
        setupListeners();
        observeModelandUpdate();
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
                SetStatus setStatus = new SetStatus(!roboRallyModel.getPlayerRegistry().loggedInUserReadyProperty().get());
                try {
                    NetworkConnection.getInstance().getDataOutputStream().writeUTF(setStatus.toJson());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        view.getMapComboBox().getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observableValue, Object oldVal, Object newVal) {
                view.getStartButton().setDisable(newVal == null);
            }
        });

        view.getStartButton().setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                String map = (String) view.getMapComboBox().getSelectionModel().getSelectedItem();
                MapSelected mapSelected = new MapSelected(map);
                try {
                    NetworkConnection.getInstance().getDataOutputStream().writeUTF(mapSelected.toJson());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }

    /**
     * Listens for changes in the LoginModel and updates the GUI accordingly
     */
    private void observeModelandUpdate() {
        view.getUsersListView().setItems(roboRallyModel.getPlayerRegistry().getObservableListUsers());
        view.getRobotComboBox().setItems(roboRallyModel.getRobotRegistry().getObservableListSelectableRobots());
        view.getMapComboBox().setItems(roboRallyModel.getObservableListAvailableMaps());
        roboRallyModel.getPlayerRegistry().loggedInUserAddedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observableValue, Boolean oldVal, Boolean newVal) {
                if (newVal) {
                    view.getReadyButton().setDisable(false);
                    view.getUsernameField().setDisable(true);
                    view.getRobotComboBox().setDisable(true);
                    view.getSubmitButton().setDisable(true);
                }
            }
        });

        roboRallyModel.getPlayerRegistry().loggedInUserReadyProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observableValue, Boolean oldVal, Boolean newVal) {
                if (newVal) {
                    view.getReadyButton().setText("Not Ready");
                } else {
                    view.getReadyButton().setText("Ready");
                }
            }
        });

        roboRallyModel.getPlayerRegistry().loggedInUserMapSelectorProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observableValue, Boolean oldVal, Boolean newVal) {
                view.getMapComboBox().setDisable(!newVal);
                view.getMapComboBox().getSelectionModel().clearSelection();
            }
        });

        roboRallyModel.gameStartedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observableValue, Boolean oldVal, Boolean newVal) {
                if (newVal) {
                    roboRally.openGameView();
                }
            }
        });
    }

    private void submitPlayerValuesForm() {
        if (view.getUsernameField().getText() == null || view.getUsernameField().getText().trim().isEmpty()) {
            view.getInfoLabel().setText("Error: Missing username!");
        } else {
            String username = view.getUsernameField().getText();
            int robotIndex = (int) view.getRobotComboBox().getValue().getFigureId();
            PlayerValues playerValues = new PlayerValues(username, robotIndex);
            try {
                NetworkConnection.getInstance().getDataOutputStream().writeUTF(playerValues.toJson());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private void messagePlayers(){
        //check if the name is already taken or emtpty
        if (view.getUsernameField().getText() == null || view.getUsernameField().getText().trim().isEmpty()) {
            view.getInfoLabel().setText("Error: Missing username!");
        }
        //sends messages using UTF8 coding
        else{
            String username = view.getUsernameField().getText();
            int robotIndex = (int) view.getRobotComboBox().getValue().getFigureId();
            PlayerValues playerValues = new PlayerValues(username, robotIndex);
            try {
                NetworkConnection.getInstance().getDataOutputStream().writeUTF(playerValues.toJson());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }

    }

}
