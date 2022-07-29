package bb.roborally.client.phase_info;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author Bence Ament
 */
public class PhaseModel {
    public enum Phase {
        BUILD_UP,
        PROGRAMMING,
        ACTIVATION
    }
    private final StringProperty phaseName = new SimpleStringProperty("Phase");
    private final StringProperty phaseDescription = new SimpleStringProperty("Description.");
    private final BooleanProperty buildUpActive = new SimpleBooleanProperty(false);

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

    public boolean isBuildUpActive() {
        return buildUpActive.get();
    }

    public BooleanProperty buildUpActiveProperty() {
        return buildUpActive;
    }

    public Phase getPhase() {
        if (phaseName.get().equals("Build-Up Phase")) {
            return Phase.BUILD_UP;
        } else if (phaseName.get().equals("Programming Phase")) {
            return Phase.PROGRAMMING;
        } else if (phaseName.get().equals("Activation Phase")) {
            return Phase.ACTIVATION;
        }
        return null;
    }
}
