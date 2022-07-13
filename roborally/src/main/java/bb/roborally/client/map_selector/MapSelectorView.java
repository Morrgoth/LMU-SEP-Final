package bb.roborally.client.map_selector;

import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class MapSelectorView {

    private final VBox view = new VBox();
    private final ComboBox<String> mapComboBox = new ComboBox<>();
    public MapSelectorView() {
        Label label = new Label("Select a map: ");
        view.getChildren().addAll(label, mapComboBox);
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
}
