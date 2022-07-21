package bb.roborally.client.map_selector;

import bb.roborally.client.robot_selector.RobotView;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

public class MapViewModel {
    private MapView mapView;

    public MapViewModel(){}

    public void connect(MapView mapView) {
        this.mapView = mapView;
        observeModelAndUpdate();
        setupListeners();
    }

    public void observeModelAndUpdate() {

    }

    public void setupListeners(){
        mapView.getImageView().setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                //haven't started yet
            }
        });
    }

    public MapView getMapView() {
        return mapView;
    }
}
