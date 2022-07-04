package bb.roborally.game;

import java.util.ArrayList;

public class RobotList {

    ArrayList<Robot> robots = new ArrayList<>();

    public RobotList() {
        initializeRobots();
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
}
