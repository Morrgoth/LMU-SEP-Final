package bb.roborally.client.robot_selector;

import java.util.ArrayList;

public class RobotRegistry {

    ArrayList<Robot> robots = new ArrayList<>();
    private final Robot twonky = new Robot(0, "Twonky");
    private final Robot hulk90 = new Robot(1, "Hulk x90");
    private final Robot hammerBot = new Robot(2, "HammerBot");
    private final Robot smashBot = new Robot(3, "SmashBot");
    private final Robot zoomBot = new Robot(4, "ZoomBot");
    private final Robot spinBot = new Robot(5, "SpinBot");

    public RobotRegistry() {
    }


    public Robot getRobotByFigureId(int figureId) {
        if (figureId == 0) {
            return twonky;
        } else if (figureId == 1) {
            return hulk90;
        } else if (figureId == 2) {
            return hammerBot;
        } else if (figureId == 3) {
            return smashBot;
        } else if (figureId == 4) {
            return zoomBot;
        } else if (figureId == 5) {
            return spinBot;
        }
        return null;
    }

    public void makeRobotUnavailable(int figureId) {
        getRobotByFigureId(figureId).availableProperty().set(false);
    }
}
