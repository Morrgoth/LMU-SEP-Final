package bb.roborally.client.board;

import bb.roborally.client.RoboRallyModel;
import bb.roborally.client.robot_selector.Robot;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

/**
 *
 * @author Bence Ament
 */
public class BoardViewModel {

    private final RoboRallyModel roboRallyModel;
    private BoardView boardView;

    public BoardViewModel(RoboRallyModel roboRallyModel) {
        this.roboRallyModel = roboRallyModel;
    }

    public void connect(BoardView boardView) {
        this.boardView = boardView;
        observeModelAndUpdate();
    }

    private void observeModelAndUpdate() {
        boardView.populate(roboRallyModel.getGameBoard());
        for (final Robot robot: roboRallyModel.getPlayerQueue().getRobots()) {
            robot.positionUpdateProperty().addListener(new ChangeListener<Boolean>() {
                @Override
                public void changed(ObservableValue<? extends Boolean> observableValue, Boolean oldVal, Boolean newVal) {
                    if (newVal) {
                        // TODO: move this pop() to a seperate method, this is pulling down the build-up phase
                        boardView.getCellView(robot.getNextPosition().getX(), robot.getNextPosition().getY()).pop();
                        boardView.displayRobot(robot);
                        robot.positionUpdateProperty().set(false);
                    }
                }
            });

            robot.orientationUpdateProperty().addListener(new ChangeListener<Boolean>() {
                @Override
                public void changed(ObservableValue<? extends Boolean> observableValue, Boolean oldVal, Boolean newVal) {
                    if (newVal) {
                        boardView.rotateRobot(robot);
                        robot.orientationUpdateProperty().set(false);
                    }
                }
            });
        }
    }
}
