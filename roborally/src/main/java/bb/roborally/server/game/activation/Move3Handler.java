package bb.roborally.server.game.activation;


import bb.roborally.protocol.game_events.Movement;
import bb.roborally.protocol.game_events.Reboot;
import bb.roborally.server.Server;
import bb.roborally.server.game.*;
import java.io.IOException;


public class Move3Handler {

    Server server;
    Game game;
    User user;

    public Move3Handler(Server server, Game game, User user) {
        this.server = server;
        this.game = game;
        this.user = user;
    }

    public void handleAlt() throws IOException {
        Robot robot = user.getRobot();
        Position position = user.getRobot().getPosition();
        Orientation orientation = user.getRobot().getRobotOrientation();
        int x = position.getX();
        int y = position.getY();
        MovementCheck movementCheck = new MovementCheck(game.getBoard(), game);
        if (!movementCheck.checkIfBlockedAlt(position, orientation)) {
            if (robot.getRobotOrientation() == Orientation.TOP) {
                Position currentField = new Position(position.getX(), position.getY() - 1);
                if (!movementCheck.checkIfBlockedAlt(currentField, orientation)) {
                    // Move 3
                    robot.setPosition(new Position(x, y - 3));
                    server.broadcast(new Movement(user.getClientID(), x, y - 3));
                } else if (!movementCheck.checkIfBlockedAlt(currentField, orientation)) {
                    //Move only 2
                    robot.setPosition(new Position(x, y - 2));
                    server.broadcast(new Movement(user.getClientID(), x, y - 2));
                } else {
                    // Move only 1
                    robot.setPosition(new Position(x, y - 1));
                    server.broadcast(new Movement(user.getClientID(), currentField.getX(), currentField.getY()));
                }
            } else if (user.getRobot().getRobotOrientation() == Orientation.LEFT) {
                Position currentField = new Position(position.getX() - 1, position.getY());
                if (!movementCheck.checkIfBlockedAlt(currentField, orientation)) {
                    // Move 3
                    robot.setPosition(new Position(x - 3, y));
                    server.broadcast(new Movement(user.getClientID(), x - 3, y));
                } else if (!movementCheck.checkIfBlockedAlt(currentField, orientation)) {
                    //Move only 2
                    robot.setPosition(new Position(x - 2, y));
                    server.broadcast(new Movement(user.getClientID(), x - 2, y));
                } else {
                    // Move only 1
                    robot.setPosition(new Position(x - 1, y));
                    server.broadcast(new Movement(user.getClientID(), currentField.getX(), currentField.getY()));
                }
            } else if (user.getRobot().getRobotOrientation() == Orientation.BOTTOM) {
                Position currentField = new Position(position.getX(), position.getY() + 1);
                if (!movementCheck.checkIfBlockedAlt(currentField, orientation)) {
                    // Move 3
                    robot.setPosition(new Position(x, y + 3));
                    server.broadcast(new Movement(user.getClientID(), x, y + 3));
                } else if (!movementCheck.checkIfBlockedAlt(currentField, orientation)) {
                    //Move only 2
                    robot.setPosition(new Position(x, y + 2));
                    server.broadcast(new Movement(user.getClientID(), x, y + 2));
                } else {
                    // Move only 1
                    robot.setPosition(new Position(x, y + 1));
                    server.broadcast(new Movement(user.getClientID(), currentField.getX(), currentField.getY()));
                }
            } else if (user.getRobot().getRobotOrientation() == Orientation.RIGHT) {
                Position currentField = new Position(position.getX() + 1, position.getY());
                if (!movementCheck.checkIfBlockedAlt(currentField, orientation)) {
                    // Move 3
                    robot.setPosition(new Position(x + 3, y));
                    server.broadcast(new Movement(user.getClientID(), x + 3, y));
                } else if (!movementCheck.checkIfBlockedAlt(currentField, orientation)) {
                    //Move only 2
                    robot.setPosition(new Position(x + 2, y));
                    server.broadcast(new Movement(user.getClientID(), x + 2, y));
                } else {
                    // Move only 1
                    robot.setPosition(new Position(x + 1, y));
                    server.broadcast(new Movement(user.getClientID(), currentField.getX(), currentField.getY()));
                }
            }
        }
    }
}

   /* public void handle() throws IOException {
        Robot robot = user.getRobot();
        Position position = user.getRobot().getPosition();
        Orientation orientation = user.getRobot().getRobotOrientation();
        int x = position.getX();
        int y = position.getY();

        MovementCheck movementCheck = new MovementCheck(game.getBoard(), game);
        if (movementCheck.checkIfBlockedAlt(position, orientation)) {
            server.broadcast(new Movement(user.getClientID(), x, y));
        } else {
            if (user.getRobot().getRobotOrientation() == Orientation.TOP) {
                robot.setPosition(new Position(x, y - 3));
                server.broadcast(new Movement(user.getClientID(), x, y - 3));
            } else if (user.getRobot().getRobotOrientation() == Orientation.LEFT) {
                robot.setPosition(new Position(x - 3, y));
                server.broadcast(new Movement(user.getClientID(), x - 3, y));
            } else if (user.getRobot().getRobotOrientation() == Orientation.BOTTOM) {
                robot.setPosition(new Position(x, y + 3));
                server.broadcast(new Movement(user.getClientID(), x, y + 3));
            } else if (user.getRobot().getRobotOrientation() == Orientation.RIGHT) {
                robot.setPosition(new Position(x + 3, y));
                server.broadcast(new Movement(user.getClientID(), x + 3, y)); //Fehler hier geht er rein nachdem er Bottom abgearbeitet hat
            }
        }
        if (movementCheck.fallingInPit(user) || movementCheck.robotIsOffBoard(user)) {
            server.broadcast(new Reboot(user.getClientID()));
        } else {
            movementCheck.pushRobot(server, game, user, orientation, 3);
        }
    }
}*/

    /*public void handle(User user) {
        Robot robot = user.getRobot();
        Position position = robot.getPosition();
        MovementCheck movementCheck = new MovementCheck(game.getBoard());
        if (movementCheck.wallForwardCheck(user)){
            System.out.println("road is blocked by wall");
        } else{
            ArrayList<User> users = game.getPlayerQueue().getUsers();
            for (User otherUser : users) {
                if (movementCheck.robotForwardCheck(game, otherUser)) {
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
    }*/

