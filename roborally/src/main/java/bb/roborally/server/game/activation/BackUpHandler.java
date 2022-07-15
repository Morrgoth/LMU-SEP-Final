package bb.roborally.server.game.activation;

import bb.roborally.protocol.game_events.Movement;
import bb.roborally.protocol.game_events.Reboot;
import bb.roborally.server.Server;
import bb.roborally.server.game.*;

import java.io.IOException;

public class BackUpHandler {

    Server server;
    Game game;
    User user;

    public BackUpHandler(Server server, Game game, User user){
        this.server = server;
        this.game = game;
        this.user = user;
    }

    public void handle() throws IOException {
        Robot robot = user.getRobot();
        Position position = user.getRobot().getPosition();
        Orientation orientation = user.getRobot().getRobotOrientation();
        int x = position.getX();
        int y = position.getY();

        Orientation newOrientation = null;
        if(orientation == Orientation.TOP){
            newOrientation = Orientation.BOTTOM;
        } else if (orientation == Orientation.RIGHT) {
            newOrientation = Orientation.LEFT;
        } else if (orientation == Orientation.LEFT){
            newOrientation = Orientation.RIGHT;
        } else if (orientation == Orientation.BOTTOM) {
            newOrientation = Orientation.TOP;
        }
        MovementCheck movementCheck = new MovementCheck(game.getBoard(), game);
        if (movementCheck.checkIfBlockedAlt(position, newOrientation)) {
            server.broadcast(new Movement(user.getClientID(), x, y));
        } else {
            if (user.getRobot().getRobotOrientation() == Orientation.TOP) {
                robot.setPosition(new Position(x, y + 1));
                server.broadcast(new Movement(user.getClientID(), x, y + 1));
            } else if (user.getRobot().getRobotOrientation() == Orientation.LEFT) {
                robot.setPosition(new Position(x + 1, y));
                server.broadcast(new Movement(user.getClientID(), x + 1, y));
            } else if (user.getRobot().getRobotOrientation() == Orientation.BOTTOM) {
                robot.setPosition(new Position(x, y - 1));
                server.broadcast(new Movement(user.getClientID(), x, y - 1));
            } else if (user.getRobot().getRobotOrientation() == Orientation.RIGHT) {
                robot.setPosition(new Position(x - 1, y));
                server.broadcast(new Movement(user.getClientID(), x - 1, y));
            }
            if (movementCheck.fallingInPit(user) || movementCheck.robotIsOffBoard(user)) {
                server.broadcast(new Reboot(user.getClientID()));
            } else {
                if(movementCheck.robotForwardCheck(user.getRobot().getPosition(), user.getRobot().getRobotOrientation(), 1)){
                    movementCheck.pushRobotBackwards(server, game, user, orientation, 1);
                }
            }
        }

    }
}
