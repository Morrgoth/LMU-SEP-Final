package bb.roborally.client.map_selector;

import javafx.geometry.Pos;
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
                imageView = new ImageView(new Image(getClass().getResource("/MapImages/DizzyHighWay.png").toExternalForm()));
                view.getChildren().addAll(label, imageView);
                break;
            case "ExtraCrispy":
                label = new Label("EXTRA CRISPY");
                imageView = new ImageView(new Image(getClass().getResource("/MapImages/ExtraCrispy.png").toExternalForm()));
                view.getChildren().addAll(label, imageView);
                break;
            case "LostBearings":
                label = new Label("LOST BEARINGS");
                imageView = new ImageView(new Image(getClass().getResource("/MapImages/LostBearings.png").toExternalForm()));
                view.getChildren().addAll(label, imageView);
                break;
            case "DeathTrap":
                label = new Label("DEATH TRAP");
                imageView = new ImageView(new Image(getClass().getResource("/MapImages/DeathTrap.png").toExternalForm()));
                view.getChildren().addAll(label, imageView);
                break;
            case "Twister":
                label = new Label("TWISTER");
                imageView = new ImageView(new Image(getClass().getResource("/MapImages/Twister.png").toExternalForm()));
                view.getChildren().addAll(label, imageView);
                break;
        }
        imageView.setFitHeight(130);
        imageView.setFitWidth(130);
        view.setAlignment(Pos.CENTER);
    }

    public VBox getView() {
        return view;
    }

    public ImageView getImageView() {
        return imageView;
    }
    public void setSelected(boolean available){
        if(available){
            view.setStyle("-fx-opacity: 0.5");
        }else{
            view.setStyle("-fx-opacity: 1.0");
        }
    }
}
