package bb.roborally.client.robot_selector;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

/**
 *
 * @author Bence Ament
 * @author Muqiu Wang
 */
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
                robotView.setAvailability(newVal);
            }
        });

        robot.selectProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observableValue, Boolean oldVal, Boolean newVal) {
                robotView.setAvailability(!newVal);
            }
        });
    }

    private void setupListeners() {
        robotView.getImageView().setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                //haven't submitted yet
            }
        });
    }

    public RobotView getRobotView() {
        return robotView;
    }
}
