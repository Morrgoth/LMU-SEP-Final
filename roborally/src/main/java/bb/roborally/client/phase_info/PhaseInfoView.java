package bb.roborally.client.phase_info;

import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class PhaseInfoView {
    private final VBox view = new VBox();
    private final Label phaseName = new Label("PHASE");
    private final Label phaseDescription = new Label("Description.");

    public PhaseInfoView() {
        view.getChildren().addAll(phaseName, phaseDescription);
        applyStyle();
    }

    private void applyStyle() {
        view.setStyle("-fx-background-color: #6666FF");
    }

    public VBox getView() {
        return view;
    }

    public Label getPhaseName() {
        return phaseName;
    }

    public Label getPhaseDescription() {
        return phaseDescription;
    }
}
