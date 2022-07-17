package bb.roborally.client.map_selector;

import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

public class MapView {
    private final VBox view = new VBox();
    private ImageView imageView;

    public MapView(String map){
        Label label;
        switch (map){
            case "DizzyHighWay":
                label = new Label("DIZZY HIGHWAY");
                imageView = new ImageView(new Image("/MapImages/DizzyHighWay.png"));
                view.getChildren().addAll(label, imageView);
                break;
            case "ExtraCrispy":
                label = new Label("EXTRA CRISPY");
                imageView = new ImageView(new Image("/MapImages/ExtraCrispy.png"));
                view.getChildren().addAll(label, imageView);
                break;
            case "LostBearings":
                label = new Label("LOST BEARINGS");
                imageView = new ImageView(new Image("/MapImages/LostBearings.png"));
                view.getChildren().addAll(label, imageView);
                break;
            case "DeathTrap":
                label = new Label("DEATH TRAP");
                imageView = new ImageView(new Image("/MapImages/DeathTrap.png"));
                view.getChildren().addAll(label, imageView);
                break;
            case "Twister":
                label = new Label("TWISTER");
                imageView = new ImageView(new Image("/MapImages/Twister.png"));
                view.getChildren().addAll(label, imageView);
                break;
        }
    }

    public VBox getView() {
        return view;
    }

    public ImageView getImageView() {
        return imageView;
    }
}
