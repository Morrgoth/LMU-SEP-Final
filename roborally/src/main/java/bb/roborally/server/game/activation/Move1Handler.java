package bb.roborally.server.game.activation;


import bb.roborally.protocol.game_events.Movement;
import bb.roborally.protocol.game_events.Reboot;
import bb.roborally.server.Server;
import bb.roborally.server.game.*;
import javafx.geometry.Pos;

import java.io.IOException;
import java.util.ArrayList;


public class Move1Handler {

    Server server;
    Game game;
    User user;


    ArrayList<Orientation> storeOrientations = new ArrayList<>();

    public Move1Handler(Server server, Game game, User user) {
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
        MovementCheck movementCheck = new MovementCheck(game.getBoard(), game);

        if (orientation == Orientation.TOP) {
            if (movementCheck.checkIfBlockedAlt(robot.getPosition(), orientation)) {
                server.broadcast(new Movement(user.getClientID(), x, y));
                } else {
                    robot.setPosition(new Position(x, y - 1));
                    server.broadcast(new Movement(user.getClientID(), x, y - 1));
                }
                if (movementCheck.fallingInPit(user) || movementCheck.robotIsOffBoard(user)) {
                    server.broadcast(new Reboot(user.getClientID()));
                }
                if (movementCheck.robotForwardCheck(robot.getPosition(), orientation, 1)){
                    handle();
                }

            } else if (orientation == Orientation.LEFT) {
                if (movementCheck.checkIfBlockedAlt(robot.getPosition(), orientation)) {
                    server.broadcast(new Movement(user.getClientID(), x, y));
                } else {
                    robot.setPosition(new Position(x - 1, y));
                    server.broadcast(new Movement(user.getClientID(), x - 1, y));
                }
                if (movementCheck.fallingInPit(user) || movementCheck.robotIsOffBoard(user)) {
                    server.broadcast(new Reboot(user.getClientID()));
                }
                if (movementCheck.robotForwardCheck(robot.getPosition(), orientation, 1)){
                    handle();
                }

            } else if (orientation == Orientation.BOTTOM) {
                if (movementCheck.checkIfBlockedAlt(robot.getPosition(), orientation)) {
                    server.broadcast(new Movement(user.getClientID(), x, y));
                } else {
                    Position newPosition = new Position(position.getX(), position.getY()-1);
                    if(movementCheck.robotForwardCheck(newPosition, orientation, 1)){
                        if(movementCheck.checkIfBlockedAlt(newPosition, orientation)){
                            server.broadcast(new Movement(user.getClientID(), x,y));
                        }
                    }
                    //robot.setPosition(new Position(x, y + 1));
                }
                if (movementCheck.fallingInPit(user) || movementCheck.robotIsOffBoard(user)) {
                    server.broadcast(new Reboot(user.getClientID()));
                }
                if (movementCheck.robotForwardCheck(robot.getPosition(), orientation, 1)){

                }

            } else if (storeOrientations.get(0) == Orientation.RIGHT){
                if (movementCheck.checkIfBlockedAlt(robot.getPosition(), orientation)) {
                    server.broadcast(new Movement(user.getClientID(), x, y));
                } else {
                    robot.setPosition(new Position(x + 1, y));
                }
                if (movementCheck.fallingInPit(user) || movementCheck.robotIsOffBoard(user)) {
                    server.broadcast(new Reboot(user.getClientID()));
                }
                if (movementCheck.robotForwardCheck(robot.getPosition(), orientation,1)){
                    handle();
                }
            }
        }
    }










