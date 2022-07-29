package bb.roborally.server.game.activation;

import bb.roborally.protocol.Orientation;
import bb.roborally.protocol.Position;
import bb.roborally.protocol.game_events.PlayerTurning;
import bb.roborally.server.Server;
import bb.roborally.server.game.*;

import java.io.IOException;

/**
 * @author Tolga Engin
 */
public class TurnLeftHandler {
    Server server;
    Game game;
    User user;

    public TurnLeftHandler(Server server, Game game, User user) {
        this.server = server;
        this.game = game;
        this.user = user;
    }

    public void handle()throws IOException {
        Robot robot = user.getRobot();
        Position position = robot.getPosition();
        if (robot.getRobotOrientation() == Orientation.LEFT) {
            robot.setRobotOrientation(Orientation.BOTTOM);
            server.broadcast(new PlayerTurning(user.getClientID(), "counterclockwise"));
        } else if (robot.getRobotOrientation() == Orientation.RIGHT) {
            robot.setRobotOrientation(Orientation.TOP);
            server.broadcast(new PlayerTurning(user.getClientID(), "counterclockwise"));
        } else if (robot.getRobotOrientation() == Orientation.TOP) {
            robot.setRobotOrientation(Orientation.LEFT);
            server.broadcast(new PlayerTurning(user.getClientID(), "counterclockwise"));
        } else if (robot.getRobotOrientation() == Orientation.BOTTOM) {
            robot.setRobotOrientation(Orientation.RIGHT);
            server.broadcast(new PlayerTurning(user.getClientID(), "counterclockwise"));
        }



    }
}

