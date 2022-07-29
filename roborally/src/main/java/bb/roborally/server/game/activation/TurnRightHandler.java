package bb.roborally.server.game.activation;

import bb.roborally.protocol.Orientation;
import bb.roborally.protocol.game_events.PlayerTurning;
import bb.roborally.server.Server;
import bb.roborally.server.game.*;

import java.io.IOException;

/**
 * @author Tolga Engin
 */
public class TurnRightHandler {
    Server server;
    Game game;
    User user;

    public TurnRightHandler(Server server, Game game, User user) {
        this.server = server;
        this.game = game;
        this.user = user;
    }

    public void handle() throws IOException {
        Robot robot = user.getRobot();
        if (robot.getRobotOrientation() == Orientation.LEFT) {
            robot.setRobotOrientation(Orientation.TOP);
            server.broadcast(new PlayerTurning(user.getClientID(), "clockwise"));
        } else if (robot.getRobotOrientation() == Orientation.RIGHT) {
            robot.setRobotOrientation(Orientation.BOTTOM);
            server.broadcast(new PlayerTurning(user.getClientID(), "clockwise"));
        } else if (robot.getRobotOrientation() == Orientation.TOP) {
            robot.setRobotOrientation(Orientation.RIGHT);
            server.broadcast(new PlayerTurning(user.getClientID(), "clockwise"));
        } else if (robot.getRobotOrientation() == Orientation.BOTTOM) {
            robot.setRobotOrientation(Orientation.LEFT);
            server.broadcast(new PlayerTurning(user.getClientID(), "clockwise"));
        }
    }
}
