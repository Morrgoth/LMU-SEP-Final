package bb.roborally.client.phase_info;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

/**
 *
 * @author Zeynab Baiani
 * @Bence Ament
 */
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

    public void setBuildUpActiveColor() {
        view.setStyle("-fx-background-color: #FFE9C5");
    }
    public void setBuildUpInactiveColor() {
        view.setStyle("-fx-background-color: #555444");
    }


    public void setProgrammingColor() {
        view.setStyle("-fx-background-color: #6666FF");
    }

    public void setActivationColor() {
        view.setStyle("-fx-background-color: #B4F2E1");
    }
}
