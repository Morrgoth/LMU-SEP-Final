package bb.roborally.client.player_inventory;

import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

/**
 *
 * @author Bence Ament
 */
public class PlayerInventoryView {

    private final HBox view = new HBox();
    private final Label energyCubeDisplay = new Label();
    private final Label checkpointCountDisplay = new Label();

    public PlayerInventoryView() {
        Label energyCube = new Label("Energy cubes: ");
        Label checkpoints = new Label("Checkpoints reached: ");
        HBox energy = new HBox();
        energy.getChildren().addAll(energyCube, energyCubeDisplay);
        HBox checkpoint = new HBox();
        checkpoint.getChildren().addAll(checkpoints, checkpointCountDisplay);
        view.setSpacing(20);
        view.getChildren().addAll(energy, checkpoint);
        applyStyle();
    }

    private void applyStyle() {
        view.setStyle("-fx-background-color:#fff");
    }

    public HBox getView() {
        return view;
    }

    public Label getEnergyCubeDisplay() {
        return energyCubeDisplay;
    }

    public Label getCheckpointCountDisplay() {
        return checkpointCountDisplay;
    }
}
