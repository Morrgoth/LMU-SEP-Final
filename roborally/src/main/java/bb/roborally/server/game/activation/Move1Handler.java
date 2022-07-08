package bb.roborally.server.game.activation;

import bb.roborally.protocol.game_events.Movement;
import bb.roborally.server.Server;
import bb.roborally.server.game.*;

import java.io.IOException;

public class Move1Handler {

    Server server;
    Game game;
    User user;

    public Move1Handler(Server server, Game game, User user) {
        this.server = server;
        this.game = game;
        this.user = user;
    }

    public void handle() {
        Robot robot = user.getRobot();
        Position position = robot.getPosition();
        if (robot.getRobotOrientation() == Orientation.LEFT) {
            Position nextPosition = new Position(position.getX() - 1, position.getY());
            robot.setPosition(nextPosition);
            Movement movement = new Movement(user.getClientID(), nextPosition.getX(), nextPosition.getY());
            try {
                server.broadcast(movement);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else if (robot.getRobotOrientation() == Orientation.RIGHT) {

        } else if (robot.getRobotOrientation() == Orientation.TOP) {

        } else if (robot.getRobotOrientation() == Orientation.BOTTOM) {

        }
    }
}
