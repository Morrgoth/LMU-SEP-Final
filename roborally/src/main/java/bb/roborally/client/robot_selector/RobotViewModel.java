package bb.roborally.client.robot_selector;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

public class RobotViewModel {

    private final Robot robot;
    private RobotView robotView;

    public RobotViewModel(Robot robot) {
        this.robot = robot;
    }

    public void connect(RobotView robotView) {
        this.robotView = robotView;
        observeModelAndUpdate();
        setupListeners();
    }

    private void observeModelAndUpdate() {
        robot.availableProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observableValue, Boolean oldVal, Boolean newVal) {
                // React to change, grey out robot
            }
        });
    }

    private void setupListeners() {

    }

    public RobotView getRobotView() {
        return robotView;
    }
}
