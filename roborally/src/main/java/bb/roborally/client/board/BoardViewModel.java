package bb.roborally.client.board;

import bb.roborally.client.RoboRallyModel;
import bb.roborally.client.robot_selector.Robot;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

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
                        boardView.getCellView(robot.getPosition().getY(), robot.getPosition().getX()).pop();
                        boardView.displayRobot(robot, robot.getPosition().getY(), robot.getPosition().getX());
                        robot.positionUpdateProperty().set(false);
                    }
                }
            });
        }
    }
}
