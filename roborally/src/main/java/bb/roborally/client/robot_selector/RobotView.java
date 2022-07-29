package bb.roborally.client.robot_selector;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

/**
 *
 * @author Bence Ament
 * @author Muqiu Wang
 */
public class RobotView {
    private final VBox view = new VBox();
    private ImageView imageView;
    public RobotView(int number){
        Label label;
        switch (number){
            case 1:
                label = new Label("1: Twonky");
                imageView = new ImageView(new Image(getClass().getResource("/robots/board_robots/robot_board_orange.png").toExternalForm()));
                view.getChildren().addAll(label, imageView);
                break;
            case 2:
                label = new Label("2: Hulk x90");
                imageView = new ImageView(new Image(getClass().getResource("/robots/board_robots/robot_board_rot.png").toExternalForm()));
                view.getChildren().addAll(label, imageView);
                break;
            case 3:
                label = new Label("3: HammerBot");
                imageView = new ImageView(new Image(getClass().getResource("/robots/board_robots/robot_board_lila.png").toExternalForm()));
                view.getChildren().addAll(label, imageView);
                break;
            case 4:
                label = new Label("4: SmashBot");
                imageView = new ImageView(new Image(getClass().getResource("/robots/board_robots/robot_board_gelb.png").toExternalForm()));
                view.getChildren().addAll(label, imageView);
                break;
            case 5:
                label = new Label("5: ZoomBot");
                imageView = new ImageView(new Image(getClass().getResource("/robots/board_robots/robot_board_grün.png").toExternalForm()));
                view.getChildren().addAll(label, imageView);
                break;
            case 6:
                label = new Label("6: SpinBot");
                imageView = new ImageView(new Image(getClass().getResource("/robots/board_robots/robot_board_blau.png").toExternalForm()));
                view.getChildren().addAll(label, imageView);
                break;
        }
        imageView.setFitHeight(100);
        imageView.setFitWidth(100);
        view.setAlignment(Pos.CENTER);
    }

    public VBox getView() {
        return view;
    }

    public ImageView getImageView() {
        return imageView;
    }

    public void setAvailability(boolean available) {
        if(available){
            view.setStyle("-fx-opacity: 1.0");
        }else{
            view.setStyle("-fx-opacity: 0.5");
        }
    }
}
