package bb.roborally.client.map_selector;

import javafx.collections.ObservableList;

public class MapSelectorViewModel {

    private final ObservableList<String> availableMaps;
    private MapSelectorView view;

    public MapSelectorViewModel(ObservableList<String> availableMaps) {
        this.availableMaps = availableMaps;
    }

    public void connect(MapSelectorView view) {
        this.view = view;
        observeModelAndUpdate();
        setupListeners();
    }

    private void observeModelAndUpdate() {
        view.getMapComboBox().setItems(availableMaps);
    }

    private void setupListeners() {

    }

    public MapSelectorView getView() {
        return view;
    }
}
