package bb.roborally.gui.start_menu;

import bb.roborally.data.messages.lobby.PlayerValues;
import bb.roborally.data.util.User;
import bb.roborally.gui.RoboRallyModel;
import bb.roborally.networking.NetworkConnection;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

import java.io.IOException;

public class StartMenuViewModel {
    private RoboRallyModel roboRallyModel;
    private StartMenuView view;

    public StartMenuViewModel(RoboRallyModel roboRallyModel, StartMenuView startMenuView) {
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

        view.getUsersListView().setItems(roboRallyModel.userStringsProperty());
    }

    /**
     * Listens for changes in the LoginModel and updates the GUI accordingly
     */
    private void observeModelandUpdate() {
        roboRallyModel.loggedInUserProperty().addListener(new ChangeListener<User>() {
            @Override
            public void changed(ObservableValue<? extends User> observableValue, User oldVal, User newVal) {
                if (newVal.getName() != null) {
                    view.getInfoLabel().setText("Success: your username is: " + newVal.getName() + " and you have " +
                            "robot Nr. " + newVal.getFigure());
                }
            }
        });

    }

    private void submitPlayerValuesForm() {
        if (view.getUsernameField().getText() == null || view.getUsernameField().getText().trim().isEmpty()) {
            view.getInfoLabel().setText("Error: Missing username!");
        } else {
            String username = view.getUsernameField().getText();
            int robotIndex = (int) view.getRobotComboBox().getValue();
            PlayerValues playerValues = new PlayerValues(username, robotIndex);
            try {
                NetworkConnection.getInstance().getDataOutputStream().writeUTF(playerValues.toJson());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

}
