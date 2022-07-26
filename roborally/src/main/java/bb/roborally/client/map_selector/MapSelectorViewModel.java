package bb.roborally.client.map_selector;

import bb.roborally.client.RoboRallyModel;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

public class MapSelectorViewModel {

    private final RoboRallyModel roboRallyModel;
    private MapSelectorView view;

    public MapSelectorViewModel(RoboRallyModel roboRallyModel) {
        this.roboRallyModel = roboRallyModel;
    }

    public void connect(MapSelectorView view) {
        this.view = view;
        observeModelAndUpdate();
        setupListeners();
    }

    private void observeModelAndUpdate() {
        view.getMapComboBox().setItems(roboRallyModel.getObservableListAvailableMaps());
        roboRallyModel.getPlayerQueue().getLocalPlayer().mapSelectorProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observableValue, Boolean oldVal, Boolean newVal) {
                if (newVal) {
                    view.getMapComboBox().setDisable(false);
                } else {
                    view.getMapComboBox().setDisable(true);
                }
            }
        });

        MapViewModel mapViewModel1 = new MapViewModel(roboRallyModel.getMapRegistry().getMapByName("DizzyHighWay"));
        mapViewModel1.connect(view.getMapView1());

        MapViewModel mapViewModel2 = new MapViewModel(roboRallyModel.getMapRegistry().getMapByName("ExtraCrispy"));
        mapViewModel2.connect(view.getMapView2());

        MapViewModel mapViewModel3 = new MapViewModel(roboRallyModel.getMapRegistry().getMapByName("LostBearings"));
        mapViewModel3.connect(view.getMapView3());

        MapViewModel mapViewModel4 = new MapViewModel(roboRallyModel.getMapRegistry().getMapByName("DeathTrap"));
        mapViewModel4.connect(view.getMapView4());

        MapViewModel mapViewModel5 = new MapViewModel(roboRallyModel.getMapRegistry().getMapByName("Twister"));
        mapViewModel5.connect(view.getMapView5());
    }

    private void setupListeners() {
        view.getMapView1().getImageView().setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                view.setSelectedMap(roboRallyModel.getMapRegistry().getMapByName("DizzyHighWay"));

            }
        });

        view.getMapView2().getImageView().setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                view.setSelectedMap(new Map("ExtraCrispy"));

            }
        });

        view.getMapView3().getImageView().setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                view.setSelectedMap(new Map("LostBearings"));

            }
        });

        view.getMapView4().getImageView().setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                view.setSelectedMap(new Map("DeathTrap"));

            }
        });

        view.getMapView5().getImageView().setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                view.setSelectedMap(new Map("Twister"));

            }
        });

    }

    public void selectMap(String mapName){
        for(Map map: roboRallyModel.getMapRegistry().maps){
            if(map.getName().equals(mapName)){
                roboRallyModel.getMapRegistry().getMapByName(mapName).selectProperty().set(true);
            }else{
                roboRallyModel.getMapRegistry().getMapByName(mapName).selectProperty().set(false);
            }
        }
    }
    public MapSelectorView getView() {
        return view;
    }
}
