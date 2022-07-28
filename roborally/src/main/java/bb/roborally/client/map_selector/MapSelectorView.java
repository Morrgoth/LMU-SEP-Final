package bb.roborally.client.map_selector;

import javafx.scene.layout.HBox;

public class MapSelectorView {
    private Map selectedMap = null;

    private final HBox view = new HBox();
    private final MapView mapView1 = new MapView("Dizzy Highway");
    private final MapView mapView2 = new MapView("Extra Crispy");
    private final MapView mapView3 = new MapView("Lost Bearings");
    private final MapView mapView4 = new MapView("Death Trap");
    private final MapView mapView5 = new MapView("Twister");
    public MapSelectorView() {
        view.getChildren().addAll(mapView1.getView(), mapView2.getView(), mapView3.getView(),
                mapView4.getView(), mapView5.getView());
        view.setSpacing(20);
    }

    public HBox getView() {
        return view;
    }


    public void setDisabled(boolean disable) {
        if(disable){
            mapView1.setSelected(true);
            mapView2.setSelected(true);
            mapView3.setSelected(true);
            mapView4.setSelected(true);
            mapView5.setSelected(true);
        }else{
            mapView1.setSelected(false);
            mapView2.setSelected(false);
            mapView3.setSelected(false);
            mapView4.setSelected(false);
            mapView5.setSelected(false);
        }
    }

    public void clearSelection() {
        //mapComboBox.getSelectionModel().clearSelection();
    }

    public Map getSelectedMap() {
        return selectedMap;
    }

    public void setSelectedMap(Map selectedMap) {
        this.selectedMap = selectedMap;
    }

    public MapView getMap(String mapName){
        if(mapName.equals("DizzyHighway")){
            return getMapView1();
        } else if(mapName.equals("ExtraCrispy")){
            return getMapView2();
        } else if(mapName.equals("LostBearings")){
            return getMapView3();
        } else if(mapName.equals("DeathTrap")){
            return getMapView4();
        } else if(mapName.equals("Twister")){
            return getMapView5();
        }
        return null;
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
