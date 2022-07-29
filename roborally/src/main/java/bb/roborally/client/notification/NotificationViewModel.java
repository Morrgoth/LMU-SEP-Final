package bb.roborally.client.notification;

import bb.roborally.client.popup.Popup;
import javafx.application.Platform;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

/**
 *
 * @author Bence Ament
 */
public class NotificationViewModel {

    private final StringProperty errorMessageProperty;
    private NotificationView view;
    private final int SHORT_DURATION = 1500;
    private final int MEDIUM_DURATION = 2500;
    private final int LONG_DURATION = 3500;
    private int duration = MEDIUM_DURATION;

    public NotificationViewModel(StringProperty errorMessageProperty) {
        this.errorMessageProperty = errorMessageProperty;
    }

    public void connect(NotificationView notificationView) {
        this.view = notificationView;
        observeModelAndUpdate();
    }

    private void observeModelAndUpdate() {
        view.getErrorMessage().textProperty().bind(errorMessageProperty);
        errorMessageProperty.addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String oldVal, String newVal) {
                if (!newVal.equals("")) {
                    Popup.open(view.getView());
                    ( new Thread() { public void run() {
                        // do something
                        try {
                            Thread.sleep(duration);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                        Platform.runLater(new Runnable() {
                            @Override
                            public void run() {
                                errorMessageProperty.set("");
                            }
                        });

                    } } ).start();
                } else {
                    Popup.close();
                }
            }
        });
    }

    public void setDuration(Notification.Duration duration) {
        if (duration == Notification.Duration.SHORT) {
            this.duration = SHORT_DURATION;
        } else if (duration == Notification.Duration.MEDIUM) {
            this.duration = MEDIUM_DURATION;
        } else if (duration == Notification.Duration.LONG) {
            this.duration = LONG_DURATION;
        }

    }
}
