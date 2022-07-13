package bb.roborally.client.loader;

import javafx.animation.Animation;
import javafx.animation.Interpolator;
import javafx.animation.RotateTransition;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.util.Duration;

public class LoaderView {

    private GridPane view = new GridPane();

    public LoaderView() {
        Label label = new Label("Loading...");
        ImageView imageView = new ImageView(getClass().getResource("/extra/loader-robot-1.png").toExternalForm());
        imageView.setFitWidth(200);
        imageView.setFitHeight(241);
        view.setAlignment(Pos.CENTER);
        RotateTransition rt = new RotateTransition(Duration.millis(3500), imageView);
        rt.setByAngle(360);
        rt.setCycleCount(Animation.INDEFINITE);
        rt.setInterpolator(Interpolator.LINEAR);
        rt.play();
        view.setVgap(30);
        view.addRow(0, imageView);
        view.addRow(1, label);
    }

    public GridPane getView() {
        return view;
    }
}
