package bb.roborally.client.timer;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import java.util.Timer;

public class TimerView {

    private final HBox view = new HBox();


    public TimerView() {
        Label time = new Label("30:00");
        view.getChildren().addAll(time);
        applyStyle();
    }

    private void applyStyle() {
        view.setStyle("-fx-background-color: rgba(239, 246, 252, 0.87);");
        view.setAlignment(Pos.CENTER);
    }

    public HBox getView() {
        return view;
    }
}
