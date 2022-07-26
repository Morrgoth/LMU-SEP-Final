package bb.roborally.client.map_selector;

import bb.roborally.client.robot_selector.RobotView;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

public class MapViewModel {
    private final Map map;
    private MapView mapView;

    public MapViewModel(Map map){this.map = map;}

    public void connect(MapView mapView) {
        this.mapView = mapView;
        observeModelAndUpdate();
        setupListeners();
    }

    public void observeModelAndUpdate() {

        map.selectProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observableValue, Boolean oldVal, Boolean newVal) {
                mapView.setSelected(!newVal);
            }
        });

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
