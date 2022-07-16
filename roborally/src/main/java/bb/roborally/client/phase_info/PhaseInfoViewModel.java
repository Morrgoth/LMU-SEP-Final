package bb.roborally.client.phase_info;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

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
                    phaseInfoView.setBuildUpColor();
                } else if (newVal.equals("Programming Phase")) {
                    phaseInfoView.setProgrammingColor();
                } else if (newVal.equals("Activation Phase")) {
                    phaseInfoView.setActivationColor();
                }
            }
        });
    }

    private void setupListeners() {
        //
    }
}
