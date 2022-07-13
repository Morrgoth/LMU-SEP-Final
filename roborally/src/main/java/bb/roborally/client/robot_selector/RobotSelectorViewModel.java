package bb.roborally.client.robot_selector;

import bb.roborally.client.RoboRallyModel;

public class RobotSelectorViewModel {

    private final RoboRallyModel roboRallyModel;
    private RobotSelectorView view;

    public RobotSelectorViewModel(RoboRallyModel roboRallyModel) {
        this.roboRallyModel = roboRallyModel;
    }

    public void connect(RobotSelectorView view) {
        this.view = view;
        observeModelAndUpdate();
        setupListeners();
    }

    private void observeModelAndUpdate() {
        view.getRobotComboBox().setItems(roboRallyModel.getRobotRegistry().getObservableListSelectableRobots());
    }

    private void setupListeners() {

    }

}
