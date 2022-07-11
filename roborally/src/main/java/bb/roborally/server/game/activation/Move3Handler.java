package bb.roborally.server.game.activation;

import bb.roborally.protocol.game_events.Movement;
import bb.roborally.server.Server;
import bb.roborally.server.game.*;

import java.io.IOException;
import java.util.ArrayList;

public class Move3Handler {

    Server server;
    Game game;
    User user;

    public Move3Handler(Server server, Game game, User user) {
        this.server = server;
        this.game = game;
        this.user = user;
    }

    public void handle(User user) {
        Robot robot = user.getRobot();
        Position position = robot.getPosition();
        MovementCheck movementCheck = new MovementCheck();
        if (movementCheck.wallOnSameFieldForwardCheck(user) == true){
            System.out.println("road is blocked by wall");
        } else{
            ArrayList<User> users = PlayerQueue.getUsers();
            for (User otherUser : users) {
                if (movementCheck.robotForwardCheck(user, otherUser)) {
                    Move2Handler move2Handler = new Move2Handler(server,game,otherUser);
                    move2Handler.handle(otherUser);
                }
            }
            if (robot.getRobotOrientation() == Orientation.LEFT) {
            Position nextPosition = new Position(position.getX() - 3, position.getY());
            robot.setPosition(nextPosition);
            Movement movement = new Movement(user.getClientID(), nextPosition.getX(), nextPosition.getY());
            try {
                server.broadcast(movement);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else if (robot.getRobotOrientation() == Orientation.RIGHT) {
            Position nextPosition = new Position(position.getX() + 3, position.getY());
            robot.setPosition(nextPosition);
            Movement movement = new Movement(user.getClientID(), nextPosition.getX(), nextPosition.getY());
            try {
                server.broadcast(movement);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        } else if (robot.getRobotOrientation() == Orientation.TOP) {
            Position nextPosition = new Position(position.getX(), position.getY() + 3);
            robot.setPosition(nextPosition);
            Movement movement = new Movement(user.getClientID(), nextPosition.getX(), nextPosition.getY());
            try {
                server.broadcast(movement);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        } else if (robot.getRobotOrientation() == Orientation.BOTTOM) {
            Position nextPosition = new Position(position.getX(), position.getY() - 3);
            robot.setPosition(nextPosition);
            Movement movement = new Movement(user.getClientID(), nextPosition.getX(), nextPosition.getY());
            try {
                server.broadcast(movement);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }
    }
    }
}
