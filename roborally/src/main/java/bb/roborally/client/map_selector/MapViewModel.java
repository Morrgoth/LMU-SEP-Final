package bb.roborally.client.map_selector;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

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
                mapView.setSelected(newVal);
            }
        });

    }

    public void setupListeners(){

    }

    public MapView getMapView() {
        return mapView;
    }
}
