package bb.roborally.client.phase_info;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
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
        view.setAlignment(Pos.CENTER);
        phaseName.setStyle("-fx-font-weight: bold;");
        phaseDescription.setWrapText(true);
        view.setSpacing(5);
        view.setPadding(new Insets(5, 5, 5, 5));
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
