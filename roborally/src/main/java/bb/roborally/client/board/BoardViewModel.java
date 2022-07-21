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
        for (Robot robot: roboRallyModel.getPlayerQueue().getRobots()) {
            boardView.getCell(robot.getPosition().getY(), robot.getPosition().getX()).push(robot.getRobotElement());
            robot.getPosition().xProperty().addListener(new ChangeListener<Number>() {
                @Override
                public void changed(ObservableValue<? extends Number> observableValue, Number oldVal, Number newVal) {
                    boardView.getCell(robot.getPosition().getY(), oldVal.intValue()).pop();
                    boardView.getCell(robot.getPosition().getY(), newVal.intValue()).push(robot.getRobotElement());
                }
            });
            robot.getPosition().yProperty().addListener(new ChangeListener<Number>() {
                @Override
                public void changed(ObservableValue<? extends Number> observableValue, Number oldVal, Number newVal) {
                    boardView.getCell(oldVal.intValue(), robot.getPosition().getX()).pop();
                    boardView.getCell(newVal.intValue(), robot.getPosition().getX()).push(robot.getRobotElement());
                }
            });
        }
    }
}
