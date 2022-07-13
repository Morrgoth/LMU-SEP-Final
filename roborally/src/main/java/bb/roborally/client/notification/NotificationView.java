package bb.roborally.client.notification;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.stage.Popup;

public class NotificationView {

    private final Popup popup = new Popup();
    private final Label errorMessage = new Label();
    private final HBox notificationBox = new HBox();

    public NotificationView() {
        popup.centerOnScreen();
        notificationBox.setPrefHeight(40);
        notificationBox.setPrefWidth(600);
        notificationBox.setAlignment(Pos.CENTER);
        notificationBox.getChildren().add(errorMessage);
        popup.getContent().addAll(notificationBox);
    }

    public void setKind(Notification.Kind kind) {
        if (kind == Notification.Kind.INFO) {
            notificationBox.setStyle("-fx-background-color: #B4F2E1; -fx-background-radius: 10 10 10 10");
        } else if (kind == Notification.Kind.WARNING) {
            notificationBox.setStyle("-fx-background-color: #FFE9C5; -fx-background-radius: 10 10 10 10");
        } else if (kind == Notification.Kind.ERROR) {
            notificationBox.setStyle("-fx-background-color: #FA9191; -fx-background-radius: 10 10 10 10");
        }
    }

    public Popup getPopup() {
        return popup;
    }

    public Label getErrorMessage() {
        return errorMessage;
    }
}
