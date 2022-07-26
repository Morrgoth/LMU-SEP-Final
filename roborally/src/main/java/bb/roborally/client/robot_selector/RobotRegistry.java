package bb.roborally.client.robot_selector;

import java.util.ArrayList;

public class RobotRegistry {

    ArrayList<Robot> robots = new ArrayList<>();
    private final Robot twonky = new Robot(1, "Twonky");
    private final Robot hulk90 = new Robot(2, "Hulk x90");
    private final Robot hammerBot = new Robot(3, "HammerBot");
    private final Robot smashBot = new Robot(4, "SmashBot");
    private final Robot zoomBot = new Robot(5, "ZoomBot");
    private final Robot spinBot = new Robot(6, "SpinBot");

    public RobotRegistry() {
    }

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
        if (figureId == 1) {
            return twonky;
        } else if (figureId == 2) {
            return hulk90;
        } else if (figureId == 3) {
            return hammerBot;
        } else if (figureId == 4) {
            return smashBot;
        } else if (figureId == 5) {
            return zoomBot;
        } else if (figureId == 6) {
            return spinBot;
        }
        return null;
    }

    public void makeRobotUnavailable(int figureId) {
        getRobotByFigureId(figureId).availableProperty().set(false);
    }
}
