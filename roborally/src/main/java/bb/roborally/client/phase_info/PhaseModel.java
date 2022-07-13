package bb.roborally.client.phase_info;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class PhaseModel {
    private final StringProperty phaseName = new SimpleStringProperty("Phase");
    private final StringProperty phaseDescription = new SimpleStringProperty("Description.");

    public PhaseModel() {}

    public void setPhase(int phase) {
        if (phase == 0) {
            phaseName.set("Build-Up Phase");
            phaseDescription.set("The robots must be placed on the Start Points of the Board. Available Start Points are green.");
        } else if (phase == 1) {
            phaseName.set("Upgrade Phase");
            phaseDescription.set("");
        } else if (phase == 2) {
            phaseName.set("Programming Phase");
            phaseDescription.set("Use the Programming Interface to create a Program by choosing 5 of your 9 Programming Cards.");
        } else if (phase == 3) {
            phaseName.set("Activation Phase");
            phaseDescription.set("The Programs of the robots will now be executed and certain game elements will be triggered. ");
        }
    }

    public StringProperty phaseNameProperty() {
        return phaseName;
    }

    public StringProperty phaseDescriptionProperty() {
        return phaseDescription;
    }
}
