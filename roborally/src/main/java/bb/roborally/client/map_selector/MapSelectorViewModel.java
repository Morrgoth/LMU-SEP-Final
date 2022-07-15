package bb.roborally.client.map_selector;

import bb.roborally.client.RoboRallyModel;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

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
    }

    private void setupListeners() {

    }

    public MapSelectorView getView() {
        return view;
    }
}
