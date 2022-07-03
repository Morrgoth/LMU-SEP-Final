package bb.roborally.gui.data;

import bb.roborally.game.Robot;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;

public class RobotRegistry {

    ArrayList<Robot> robots = new ArrayList<>();
    private final ObservableList<Robot> selectableRobots;

    public RobotRegistry() {
        initializeRobots();
        selectableRobots = FXCollections.observableArrayList(robots);
    }

    public ObservableList<Robot> getObservableListSelectableRobots() { return selectableRobots; }

    private void initializeRobots() {
        Robot twonky = new Robot(1, "Twonky");
        Robot hulk90 = new Robot(2, "Hulk x90");
        Robot hammerBot = new Robot(3, "HammerBot");
        Robot smashBot = new Robot(4, "SmashBot");
        Robot zoomBot = new Robot(5, "ZoomBot");
        Robot spinBot = new Robot(6, "SpinBot");
        robots.add(twonky);
        robots.add(hulk90);
        robots.add(hammerBot);
        robots.add(smashBot);
        robots.add(zoomBot);
        robots.add(spinBot);
    }

    public Robot getRobotByFigureId(int figureId) {
        for (Robot robot: robots) {
            if (robot.getFigureId() == figureId) {
                return robot;
            }
        }
        return null;
    }

    public void makeUnavailable(int figureId) {
        selectableRobots.removeIf(robot -> robot.getFigureId() == figureId);
    }
}
