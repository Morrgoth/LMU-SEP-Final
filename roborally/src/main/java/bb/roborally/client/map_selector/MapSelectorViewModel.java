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

        MapViewModel mapViewModel1 = new MapViewModel();
        mapViewModel1.connect(view.getMapView1());

        MapViewModel mapViewModel2 = new MapViewModel();
        mapViewModel2.connect(view.getMapView2());

        MapViewModel mapViewModel3 = new MapViewModel();
        mapViewModel3.connect(view.getMapView3());

        MapViewModel mapViewModel4 = new MapViewModel();
        mapViewModel4.connect(view.getMapView4());

        MapViewModel mapViewModel5 = new MapViewModel();
        mapViewModel5.connect(view.getMapView5());
    }

    private void setupListeners() {
        view.getMapView1().getImageView().setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                view.setSelectedMap("DizzyHighWay");
                view.getMapView1().setSelected(true);
            }
        });

        view.getMapView2().getImageView().setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                view.setSelectedMap("ExtraCrispy");
                view.getMapView2().setSelected(true);
            }
        });

        view.getMapView3().getImageView().setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                view.setSelectedMap("LostBearings");
                view.getMapView3().setSelected(true);
            }
        });

        view.getMapView4().getImageView().setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                view.setSelectedMap("DeathTrap");
                view.getMapView4().setSelected(true);
            }
        });

        view.getMapView5().getImageView().setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                view.setSelectedMap("Twister");
                view.getMapView5().setSelected(true);
            }
        });

    }

    public MapSelectorView getView() {
        return view;
    }
}
