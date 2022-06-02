package bb.roborally.gui.start_menu;

import bb.roborally.gui.RoboRally;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

public class StartMenuViewModel {
    private StartMenuModel model;
    private StartMenuView view;
    private RoboRally roboRally;

    public StartMenuViewModel(RoboRally roboRally, StartMenuModel startMenuModel, StartMenuView startMenuView) {
        this.roboRally = roboRally;
        model = startMenuModel;
        view = startMenuView;
        setupListeners();
        observeModelandUpdate();
    }
    /**
     * Listens for user input through the GUI.
     */
    private void setupListeners() {
        view.getButton().setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                submitLoginForm();
            }
        });

        view.getIpField().setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                if (keyEvent.getCode() == KeyCode.ENTER) {
                    submitLoginForm();
                }
            }
        });

        view.getPortField().setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                if (keyEvent.getCode() == KeyCode.ENTER) {
                    submitLoginForm();
                }
            }
        });

        view.getUsernameField().setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                if (keyEvent.getCode() == KeyCode.ENTER) {
                    submitLoginForm();
                }
            }
        });
    }

    /**
     * Listens for changes in the LoginModel and updates the GUI accordingly
     */
    private void observeModelandUpdate() {
        model.errorMessageProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String oldVal, String newVal) {
                if (!newVal.equals(oldVal) && !newVal.equals("")) {
                    view.getErrorLabel().setText(newVal);
                }
            }
        });
    }

    private void submitLoginForm() {
        if (view.getIpField().getText() == null || view.getIpField().getText().trim().isEmpty()) {
            model.setErrorMessage("Error: Missing IP address!");
        } else if (view.getIpField().getText() == null || view.getPortField().getText().trim().isEmpty()) {
            model.setErrorMessage("Error: Missing port number!");
        } else if (view.getUsernameField().getText() == null || view.getUsernameField().getText().trim().isEmpty()) {
            model.setErrorMessage("Error: Missing username!");
        } else {
            model.setIp(view.getIpField().getText());
            model.setPort(Integer.parseInt(view.getPortField().getText()));
            model.setUsername(view.getUsernameField().getText());
            roboRally.login();
        }
    }

}
