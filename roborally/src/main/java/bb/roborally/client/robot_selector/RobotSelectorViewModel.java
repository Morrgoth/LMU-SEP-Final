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
        RobotViewModel robotViewModel1 = new RobotViewModel(roboRallyModel.getRobotRegistry().getRobotByFigureId(1));
        robotViewModel1.connect(view.getRobotView1());
    }

    private void setupListeners() {

    }

}
