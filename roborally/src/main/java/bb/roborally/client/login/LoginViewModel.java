package bb.roborally.client.login;

import bb.roborally.client.RoboRally;
import bb.roborally.client.RoboRallyModel;
import bb.roborally.client.ViewManager;
import bb.roborally.client.notification.Notification;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

/**
 *
 * @author Bence Ament
 * @author Zeynab Baiani
 * @author Muqiu Wang
 */
public class LoginViewModel {
    private final RoboRally roboRally;
    private final RoboRallyModel roboRallyModel;
    private LoginView view;

    public LoginViewModel(RoboRally roboRally, RoboRallyModel roboRallyModel) {
        this.roboRally = roboRally;
        this.roboRallyModel = roboRallyModel;
    }

    public void connect(LoginView view) {
        this.view = view;
        observeModelAndUpdate();
        setUpListeners();
    }

    private void observeModelAndUpdate() {

        roboRallyModel.ipSetProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observableValue, Boolean oldVal, Boolean newVal) {
                if (newVal) {
                    ViewManager.openLoaderView();
                    roboRally.connect();
                }
            }
        });
    }

    private void setUpListeners() {
        view.getSubmitButton().setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                submitForm();
            }
        });

        view.getIpField().setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                if (keyEvent.getCode() == KeyCode.ENTER) {
                    submitForm();
                }
            }
        });

        view.getPortField().setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                if (keyEvent.getCode() == KeyCode.ENTER) {
                    submitForm();
                }
            }
        });
    }

    /**
     * Helper method to submit the form.
     */
    private void submitForm() {
        if (!view.getIpField().getText().isEmpty()) {
            if (!view.getPortField().getText().isEmpty()) {
                String ip = view.getIpField().getText();
                String portStr = view.getPortField().getText();
                int port = Integer.parseInt(portStr);
                roboRallyModel.setIp(ip);
                roboRallyModel.setPort(port);
            } else {
                Notification.getInstance().show_medium(Notification.Kind.ERROR, "Missing port");
            }
        } else {
            Notification.getInstance().show_medium(Notification.Kind.ERROR, "Missing IP address");
        }
    }

}
