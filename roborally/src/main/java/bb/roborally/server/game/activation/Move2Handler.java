package bb.roborally.server.game.activation;


import bb.roborally.protocol.game_events.Movement;
import bb.roborally.protocol.game_events.Reboot;
import bb.roborally.server.Server;
import bb.roborally.server.game.*;
import java.io.IOException;


public class Move2Handler {

    Server server;
    Game game;
    User user;

    public Move2Handler(Server server, Game game, User user) {
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
                    // Move 2
                    robot.setPosition(new Position(x, y - 2));
                    if(movementCheck.fallingInPit(user) || movementCheck.robotIsOffBoard(user)){
                        server.broadcast(new Reboot(user.getClientID()));
                    }else{
                        movementCheck.pushRobot(server, game, user, orientation,2);
                    }
                    server.broadcast(new Movement(user.getClientID(), x, y - 2));
                } else {
                    // Move only 1
                    robot.setPosition(new Position(x, y - 1));
                    if(movementCheck.fallingInPit(user) || movementCheck.robotIsOffBoard(user)){
                        server.broadcast(new Reboot(user.getClientID()));
                    }
                    server.broadcast(new Movement(user.getClientID(), currentField.getX(), currentField.getY()));
                }
            } else if (user.getRobot().getRobotOrientation() == Orientation.LEFT) {
                if (!movementCheck.checkIfBlockedAlt(new Position(position.getX() - 1, position.getY()), orientation)) {
                    // Move 2
                    robot.setPosition(new Position(x - 2, y));
                    if(movementCheck.fallingInPit(user) || movementCheck.robotIsOffBoard(user)){
                        server.broadcast(new Reboot(user.getClientID()));
                    }
                    server.broadcast(new Movement(user.getClientID(), x - 2, y));
                } else {
                    // Move only 1
                    robot.setPosition(new Position(x-1, y ));
                    if(movementCheck.fallingInPit(user) || movementCheck.robotIsOffBoard(user)){
                        server.broadcast(new Reboot(user.getClientID()));
                    }
                    server.broadcast(new Movement(user.getClientID(), x-1, y ));
                }
            } else if (user.getRobot().getRobotOrientation() == Orientation.BOTTOM) {
                if (!movementCheck.checkIfBlockedAlt(new Position(position.getX(), position.getY() + 1), orientation)) {
                    // Move 2
                    robot.setPosition(new Position(x, y + 2));
                    if(movementCheck.fallingInPit(user) || movementCheck.robotIsOffBoard(user)){
                        server.broadcast(new Reboot(user.getClientID()));
                    }
                    server.broadcast(new Movement(user.getClientID(), x, y + 2));
                } else {
                    // Move only 1
                    robot.setPosition(new Position(x, y + 1));
                    if(movementCheck.fallingInPit(user) || movementCheck.robotIsOffBoard(user)){
                        server.broadcast(new Reboot(user.getClientID()));
                    }
                    server.broadcast(new Movement(user.getClientID(), x, y + 1));
                }
            } else if (user.getRobot().getRobotOrientation() == Orientation.RIGHT) {
                if (!movementCheck.checkIfBlockedAlt(new Position(position.getX() + 1, position.getY()), orientation)) {  //CHeck funktionoiert speichert nicht die neue position, sondern setzt es zu ausgangsposition
                    // Move 2
                    robot.setPosition(new Position(x + 2, y));
                    if(movementCheck.fallingInPit(user) || movementCheck.robotIsOffBoard(user)){
                        server.broadcast(new Reboot(user.getClientID()));
                    }
                    server.broadcast(new Movement(user.getClientID(), x + 2, y));
                } else {
                    // Move only 1
                    robot.setPosition(new Position(x + 1, y));
                    if(movementCheck.fallingInPit(user) || movementCheck.robotIsOffBoard(user)){
                        server.broadcast(new Reboot(user.getClientID()));
                    }
                    server.broadcast(new Movement(user.getClientID(), x + 1, y));
                }
            }
        }
    }
}

