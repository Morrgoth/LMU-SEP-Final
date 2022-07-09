package bb.roborally.client.timer;

import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

public class TimerView {

    private final HBox view = new HBox();

    public TimerView() {
        Label time = new Label("30:00");
        view.getChildren().addAll(time);
        applyStyle();
    }

    private void applyStyle() {
        view.setStyle("-fx-background-color: rgba(239, 246, 252, 0.87);");
    }

    public HBox getView() {
        return view;
    }
}
