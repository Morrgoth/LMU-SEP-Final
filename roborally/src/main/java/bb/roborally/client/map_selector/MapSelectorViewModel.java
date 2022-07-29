package bb.roborally.client.map_selector;

import bb.roborally.client.RoboRallyModel;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
/**
 * @author Muqiu Wang
 * @author Bence Ament
 */
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
        roboRallyModel.getPlayerQueue().getLocalPlayer().mapSelectorProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observableValue, Boolean oldVal, Boolean newVal) {
                if (newVal) {
                    view.setDisabled(false);
                } else {
                    view.setDisabled(true);
                }
            }
        });

        MapViewModel mapViewModel1 = new MapViewModel(roboRallyModel.getMapRegistry().getMapByName("Dizzy Highway"));
        mapViewModel1.connect(view.getMapView1());

        MapViewModel mapViewModel2 = new MapViewModel(roboRallyModel.getMapRegistry().getMapByName("Extra Crispy"));
        mapViewModel2.connect(view.getMapView2());

        MapViewModel mapViewModel3 = new MapViewModel(roboRallyModel.getMapRegistry().getMapByName("Lost Bearings"));
        mapViewModel3.connect(view.getMapView3());

        MapViewModel mapViewModel4 = new MapViewModel(roboRallyModel.getMapRegistry().getMapByName("Death Trap"));
        mapViewModel4.connect(view.getMapView4());

        MapViewModel mapViewModel5 = new MapViewModel(roboRallyModel.getMapRegistry().getMapByName("Twister"));
        mapViewModel5.connect(view.getMapView5());
    }

    private void setupListeners() {
        view.getMapView1().getImageView().setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                selectMap("Dizzy Highway");
            }
        });

        view.getMapView2().getImageView().setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                selectMap("Extra Crispy");
            }
        });

        view.getMapView3().getImageView().setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                selectMap("Lost Bearings");
            }
        });

        view.getMapView4().getImageView().setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                selectMap("Death Trap");
            }
        });

        view.getMapView5().getImageView().setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                selectMap("Twister");
            }
        });

    }

    public void selectMap(String mapName){
        view.setSelectedMap(roboRallyModel.getMapRegistry().getMapByName(mapName));
        roboRallyModel.getMapRegistry().getMapByName(mapName).selectProperty().set(true);
        for(final Map map: roboRallyModel.getMapRegistry().maps){
            if(!map.getName().equals(mapName)) {
                map.selectProperty().set(false);
            }
        }
    }

    public MapSelectorView getView() {
        return view;
    }
}
