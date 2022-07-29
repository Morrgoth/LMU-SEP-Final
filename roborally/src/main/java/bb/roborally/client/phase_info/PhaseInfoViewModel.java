package bb.roborally.client.phase_info;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

/**
 *
 * @author Zeynab Baiani
 * @Bence Ament
 */
public class PhaseInfoViewModel {
    private final PhaseModel phaseModel;
    private PhaseInfoView phaseInfoView;

    public PhaseInfoViewModel(PhaseModel phaseModel) {
        this.phaseModel = phaseModel;
    }

    public void connect(PhaseInfoView phaseInfoView) {
        this.phaseInfoView = phaseInfoView;
        observeModelAndUpdate();
        setupListeners();
    }

    private void observeModelAndUpdate() {
        phaseInfoView.getPhaseName().textProperty().bind(phaseModel.phaseNameProperty());
        phaseInfoView.getPhaseDescription().textProperty().bind(phaseModel.phaseDescriptionProperty());
        phaseModel.phaseNameProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String oldVal , String newVal) {
                if (newVal.equals("Build-Up Phase")) {
                    if (phaseModel.isBuildUpActive()) {
                        phaseInfoView.setBuildUpActiveColor();
                    } else {
                        phaseInfoView.setBuildUpInactiveColor();
                    }
                } else if (newVal.equals("Programming Phase")) {
                    phaseInfoView.setProgrammingColor();
                } else if (newVal.equals("Activation Phase")) {
                    phaseInfoView.setActivationColor();
                }
            }
        });
        phaseModel.buildUpActiveProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observableValue, Boolean oldVal, Boolean newVal) {
                if (phaseModel.phaseNameProperty().get().equals("Build-Up Phase")) {
                    if (newVal) {
                        phaseInfoView.setBuildUpActiveColor();
                    } else {
                        phaseInfoView.setBuildUpInactiveColor();
                    }
                }
            }
        });
    }

    private void setupListeners() {
        //
    }
}
