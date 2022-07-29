package bb.roborally.client.notification;

import javafx.beans.property.StringProperty;

/**
 * Singleton Notification Manager.
 *
 * @author Bence Ament
 */
public class Notification {
    private static Notification notification = null;
    private StringProperty errorMessageProperty;
    private final NotificationView notificationView = new NotificationView();
    private NotificationViewModel notificationViewModel;

    public enum Kind {
        INFO,
        WARNING,
        ERROR
    }

    public enum Duration {
        SHORT,
        MEDIUM,
        LONG
    }


    private Notification() {}

    public static Notification getInstance() {
        return notification;
    }

    public static void init(StringProperty stringProperty) {
        notification = new Notification();
        notification.errorMessageProperty = stringProperty;
        notification.notificationViewModel = new NotificationViewModel(notification.errorMessageProperty);
        notification.notificationViewModel.connect(notification.notificationView);
    }

    /**
     * @param kind
     * @param message
     * @param duration
     */
    public void show(Kind kind, String message, Duration duration) {
        notificationViewModel.setDuration(duration);
        notificationView.setKind(kind);
        errorMessageProperty.set(message);
    }

    /**
     * @param kind
     * @param message
     * shows a short Notification
     */
    public void show_short(Kind kind, String message) {
        notificationViewModel.setDuration(Duration.SHORT);
        notificationView.setKind(kind);
        errorMessageProperty.set(message);
    }

    /**
     * @param kind
     * @param message
     * shows a medium Notification
     */
    public void show_medium(Kind kind, String message) {
        notificationViewModel.setDuration(Duration.MEDIUM);
        notificationView.setKind(kind);
        errorMessageProperty.set(message);
    }

    /**
     * @param kind
     * @param message
     * shows a long Notification
     */
    public void show_long(Kind kind, String message) {
        notificationViewModel.setDuration(Duration.LONG);
        notificationView.setKind(kind);
        errorMessageProperty.set(message);
    }
}
