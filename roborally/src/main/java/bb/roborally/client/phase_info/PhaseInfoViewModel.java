package bb.roborally.client.phase_info;

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
    }

    private void setupListeners() {
        //
    }
}
