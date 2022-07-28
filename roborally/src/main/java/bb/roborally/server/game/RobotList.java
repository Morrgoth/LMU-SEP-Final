package bb.roborally.server.game;

import java.util.ArrayList;

public class RobotList {

    ArrayList<Robot> robots = new ArrayList<>();

    public RobotList() {
        initializeRobots();
    }

    private void initializeRobots() {
        Robot twonky = new Robot(0, "Twonky");
        Robot hulk90 = new Robot(1, "Hulk x90");
        Robot hammerBot = new Robot(2, "HammerBot");
        Robot smashBot = new Robot(3, "SmashBot");
        Robot zoomBot = new Robot(4, "ZoomBot");
        Robot spinBot = new Robot(5, "SpinBot");
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

    public Robot getRobotByPosition(int x,int y) {
        for(Robot robot: robots){
            if(robot.getPosition().getX() == x
                && robot.getPosition().getY() == y){
                robot.getFigureId();
                return robot;
            }
        }
        return null;
    }

    public boolean isRobotOnPosition(int x,int y) {
        boolean isOnPosition = false;

        for (Robot robot : robots) {
            if(robot.getPosition().getX() == x && robot.getPosition().getY() == y){
                return true;
            }
        }
        return isOnPosition;
    }

    public boolean isRobotAvailable(int figureId) {
        for (Robot robot: robots) {
            if (robot.getFigureId() == figureId) {
                return robot.isAvailable();
            }
        }
        return false;
    }

    public void makeUnavailable(int figureId) {
        for (Robot robot: robots) {
            if (robot.getFigureId() == figureId) {
                robot.setAvailable(false);
            }
        }
    }
}
