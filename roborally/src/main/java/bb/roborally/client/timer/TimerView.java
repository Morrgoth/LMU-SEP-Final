package bb.roborally.client.timer;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.util.Duration;

public class TimerView {

    private final HBox view = new HBox();
    Label timerLabel = new Label();

    Timeline timeline;
    private int timeSeconds = 30;

    public TimerView() {
        timeline = new Timeline(
                new KeyFrame(Duration.seconds(1),
                        new EventHandler<ActionEvent>() {
                            @Override
                            public void handle(ActionEvent event) {
                                if (timeSeconds > 0) {
                                    timerLabel.setText(String.valueOf(timeSeconds--));
                                } else {
                                    timeline.stop();
                                }
                            }
                        }));
        timeline.setCycleCount(1);
        timerLabel.setText(String.valueOf(timeSeconds));
        timerLabel.setTextFill(Color.RED);
        timerLabel.setStyle("-fx-font-size: 2em;");
        view.getChildren().addAll(timerLabel);
        applyStyle();
    }

    private void applyStyle() {
        view.setStyle("-fx-background-color: rgba(239, 246, 252, 0.87);");
        view.setAlignment(Pos.CENTER);
    }

    public HBox getView() {
        return view;
    }

    public void start() {
        timeline.play();
    }

    public void reset() {
        timeline.stop();
        timeSeconds = 30;
        timerLabel.setText(String.valueOf(timeSeconds));
    }
}
