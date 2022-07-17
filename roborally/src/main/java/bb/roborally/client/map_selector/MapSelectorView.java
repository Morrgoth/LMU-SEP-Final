package bb.roborally.client.map_selector;

import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class MapSelectorView {

    private final VBox view = new VBox();
    private final MapView mapView1 = new MapView("DizzyHighWay");
    private final MapView mapView2 = new MapView("ExtraCrispy");
    private final MapView mapView3 = new MapView("LostBearings");
    private final MapView mapView4 = new MapView("DeathTrap");
    private final MapView mapView5 = new MapView("Twister");
    private final ComboBox<String> mapComboBox = new ComboBox<>();
    public MapSelectorView() {
        Label label = new Label("Select a map: ");
        view.getChildren().addAll(label, mapComboBox, mapView1.getView(), mapView2.getView(), mapView3.getView(),
                mapView4.getView(), mapView5.getView());
    }

    public VBox getView() {
        return view;
    }

    public ComboBox<String> getMapComboBox() {
        return mapComboBox;
    }

    public void disable(boolean disable) {
        mapComboBox.setDisable(disable);
    }

    public void clearSelection() {
        mapComboBox.getSelectionModel().clearSelection();
    }

    public String getSelectedMap() {
        return mapComboBox.getValue();
    }

    public MapView getMapView1() {
        return mapView1;
    }

    public MapView getMapView2() {
        return mapView2;
    }

    public MapView getMapView3() {
        return mapView3;
    }

    public MapView getMapView4() {
        return mapView4;
    }

    public MapView getMapView5() {
        return mapView5;
    }
}
