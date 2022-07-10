package bb.roborally.server.game.activation;

import bb.roborally.server.Server;
import bb.roborally.server.game.*;
import bb.roborally.server.game.cards.TurnRight;

public class TurnRightHandler {
    Server server;
    Game game;
    User user;

    public TurnRightHandler(Server server, Game game, User user) {
        this.server = server;
        this.game = game;
        this.user = user;
    }

    public void handle() {
        Robot robot = user.getRobot();
        Position position = robot.getPosition();
        if (robot.getRobotOrientation() == Orientation.LEFT) {
            robot.setRobotOrientation(Orientation.TOP);
        } else if (robot.getRobotOrientation() == Orientation.RIGHT) {
            robot.setRobotOrientation(Orientation.BOTTOM);
        } else if (robot.getRobotOrientation() == Orientation.TOP) {
            robot.setRobotOrientation(Orientation.RIGHT);
        } else if (robot.getRobotOrientation() == Orientation.BOTTOM) {
            robot.setRobotOrientation(Orientation.LEFT);
        }
    }
}
