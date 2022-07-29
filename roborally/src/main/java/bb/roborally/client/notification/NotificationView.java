package bb.roborally.client.notification;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

/**
 *
 * @author Bence Ament
 */
public class NotificationView {

    private final HBox view = new HBox();
    private final Label errorMessage = new Label();

    public NotificationView() {
        view.setPrefHeight(40);
        view.setPrefWidth(600);
        view.setAlignment(Pos.CENTER);
        view.getChildren().add(errorMessage);
    }

    public void setKind(Notification.Kind kind) {
        if (kind == Notification.Kind.INFO) {
            view.setStyle("-fx-background-color: #B4F2E1; -fx-background-radius: 10 10 10 10");
        } else if (kind == Notification.Kind.WARNING) {
            view.setStyle("-fx-background-color: #FFE9C5; -fx-background-radius: 10 10 10 10");
        } else if (kind == Notification.Kind.ERROR) {
            view.setStyle("-fx-background-color: #FA9191; -fx-background-radius: 10 10 10 10");
        }
    }

    public HBox getView() {
        return view;
    }

    public Label getErrorMessage() {
        return errorMessage;
    }
}
