package bb.roborally.server.game.activation;

import bb.roborally.server.Server;
import bb.roborally.server.game.*;

public class UTurnHandler {
    Server server;
    Game game;
    User user;

    public UTurnHandler(Server server, Game game, User user) {
        this.server = server;
        this.game = game;
        this.user = user;
    }

    public void handle() {
        Robot robot = user.getRobot();
        Position position = robot.getPosition();
        if (robot.getRobotOrientation() == Orientation.LEFT) {
            robot.setRobotOrientation(Orientation.RIGHT);
        } else if (robot.getRobotOrientation() == Orientation.RIGHT) {
            robot.setRobotOrientation(Orientation.LEFT);
        } else if (robot.getRobotOrientation() == Orientation.TOP) {
            robot.setRobotOrientation(Orientation.BOTTOM);
        } else if (robot.getRobotOrientation() == Orientation.BOTTOM) {
            robot.setRobotOrientation(Orientation.TOP);
        }
    }
}
