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

        RobotViewModel robotViewModel2 = new RobotViewModel(roboRallyModel.getRobotRegistry().getRobotByFigureId(2));
        robotViewModel2.connect(view.getRobotView2());

        RobotViewModel robotViewModel3 = new RobotViewModel(roboRallyModel.getRobotRegistry().getRobotByFigureId(3));
        robotViewModel3.connect(view.getRobotView3());

        RobotViewModel robotViewModel4 = new RobotViewModel(roboRallyModel.getRobotRegistry().getRobotByFigureId(4));
        robotViewModel4.connect(view.getRobotView4());

        RobotViewModel robotViewModel5 = new RobotViewModel(roboRallyModel.getRobotRegistry().getRobotByFigureId(5));
        robotViewModel5.connect(view.getRobotView5());

        RobotViewModel robotViewModel6 = new RobotViewModel(roboRallyModel.getRobotRegistry().getRobotByFigureId(6));
        robotViewModel6.connect(view.getRobotView6());
    }

    private void setupListeners() {

    }

}
