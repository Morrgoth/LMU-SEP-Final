package bb.roborally.server.game.activation;

import bb.roborally.protocol.game_events.Movement;
import bb.roborally.protocol.game_events.Reboot;
import bb.roborally.server.Server;
import bb.roborally.server.game.*;

import java.io.IOException;
import java.util.ArrayList;

public class Move1Handler {

    Server server;
    Game game;
    User user;

    public Move1Handler(Server server, Game game, User user) {
        this.server = server;
        this.game = game;
        this.user = user;
    }

    public void handle() throws IOException{
        Robot robot = user.getRobot();
        Position position = user.getRobot().getPosition();
        int x = position.getX();
        int y = position.getY();
        MovementCheck movementCheck = new MovementCheck(game.getBoard(), game);
        movementCheck.setNumberOpPositions(1);
        if(movementCheck.wallForwardCheck(user)){
            robot.setPosition(new Position(x,y));
            server.broadcast(new Movement(user.getClientID(), x, y));
        } else {
            for(int i = 0; i < movementCheck.getNumberOfPositions(); i++){
                if(!movementCheck.robotForwardCheck(game, robot, user.getRobot().getRobotOrientation())) {
                    switch (user.getRobot().getRobotOrientation()) {
                        case TOP:
                            robot.setPosition(new Position(x, y - movementCheck.getNumberOfPositions()));
                            server.broadcast(new Movement(user.getClientID(), x, y - 1));
                            if (movementCheck.fallingInPit(user)) {
                                server.broadcast(new Reboot(user.getClientID()));
                            }
                            if (movementCheck.robotIsOffBoard(user)) {
                                server.broadcast(new Reboot(user.getClientID()));
                            }
                        case BOTTOM:
                            robot.setPosition(new Position(x, y + movementCheck.getNumberOfPositions()));
                            server.broadcast(new Movement(user.getClientID(), x, y + 1));
                            if (movementCheck.fallingInPit(user)) {
                                server.broadcast(new Reboot(user.getClientID()));
                            }
                            if (movementCheck.robotIsOffBoard(user)) {
                                server.broadcast(new Reboot(user.getClientID()));
                            }
                        case LEFT:
                            robot.setPosition(new Position(x - movementCheck.getNumberOfPositions(), y));
                            server.broadcast(new Movement(user.getClientID(), x - 1, y));
                            if (movementCheck.fallingInPit(user)) {
                                server.broadcast(new Reboot(user.getClientID()));
                            }
                            if (movementCheck.robotIsOffBoard(user)) {
                                server.broadcast(new Reboot(user.getClientID()));
                            }
                        case RIGHT:
                            robot.setPosition(new Position(x + movementCheck.getNumberOfPositions(), y));
                            server.broadcast(new Movement(user.getClientID(), x + 1, y));
                            if (movementCheck.fallingInPit(user)) {
                                server.broadcast(new Reboot(user.getClientID()));
                            }
                            if (movementCheck.robotIsOffBoard(user)) {
                                server.broadcast(new Reboot(user.getClientID()));
                            }
                        }
                    }else{
                    switch (robot.getRobotOrientation()){
                        case TOP:
                            movementCheck.pushRobotForward(game, user, Orientation.TOP);
                            robot.setPosition(new Position(x, y + movementCheck.getNumberOfPositions()));
                            server.broadcast(new Movement(user.getClientID(), x, y + 1));
                        case BOTTOM:
                            movementCheck.pushRobotForward(game, user, Orientation.BOTTOM);
                            robot.setPosition(new Position(x, y + movementCheck.getNumberOfPositions()));
                            server.broadcast(new Movement(user.getClientID(), x, y + 1));
                        case LEFT:
                            movementCheck.pushRobotForward(game, user, Orientation.LEFT);
                            robot.setPosition(new Position(x, y + movementCheck.getNumberOfPositions()));
                            server.broadcast(new Movement(user.getClientID(), x - 1, y));
                        case RIGHT:
                            movementCheck.pushRobotForward(game, user, Orientation.RIGHT);
                            robot.setPosition(new Position(x, y + movementCheck.getNumberOfPositions()));
                            server.broadcast(new Movement(user.getClientID(), x + 1 , y));

                        }
                    }
                }
            }
        }
            /*switch (user.getRobot().getRobotOrientation()){
                case TOP:
                    user.getRobot().getPosition().setX(x);
                    user.getRobot().getPosition().setY(y-1);
                    server.broadcast(new Movement(user.getClientID(), x, y-1));
                    if(movementCheck.fallingInPit(user) || movementCheck.robotIsOffBoard(user)){
                        server.broadcast(new Reboot(user.getClientID()));
                    }else{
                        if(movementCheck.robotForwardCheck(game, user.getRobot(),Orientation.TOP)){

                            
                        }
                    }
                case BOTTOM:
                    user.getRobot().setPosition(new Position(x, y + 1));
                    server.broadcast(new Movement(user.getClientID(), x, y + 1));
                    if(movementCheck.fallingInPit(user) || movementCheck.robotIsOffBoard(user)){
                        server.broadcast(new Reboot(user.getClientID()));
                    }else{
                        if(movementCheck.robotForwardCheck(game, user,1)){

                        }
                    }
                case LEFT:
                    user.getRobot().setPosition(new Position(x - 1, y));
                    server.broadcast(new Movement(user.getClientID(), x - 1, y));
                    if(movementCheck.fallingInPit(user) || movementCheck.robotIsOffBoard(user)){
                        server.broadcast(new Reboot(user.getClientID()));
                    }else{
                        if(movementCheck.robotForwardCheck(game, user,1)){

                        }
                    }
                case RIGHT:
                    user.getRobot().setPosition(new Position(x + 1, y));
                    server.broadcast(new Movement(user.getClientID(), x + 1, y));
                    if(movementCheck.fallingInPit(user) || movementCheck.robotIsOffBoard(user)){
                        server.broadcast(new Reboot(user.getClientID()));
                    }else{
                        if(movementCheck.robotForwardCheck(game, user,1)){

                        }
                    }

            }
        }*/

    }

    /*public void handle(User user) {
        Robot robot = user.getRobot();
        Position position = robot.getPosition();
        MovementCheck movementCheck = new MovementCheck(game.getBoard());
        if (movementCheck.wallForwardCheck(user)){
            System.out.println("road is blocked by wall");
        } else{
        ArrayList<User> users = game.getPlayerQueue().getUsers();
        for (User otherUser : users) {
            if (movementCheck.robotForwardCheck(game, user)) {
                Move1Handler move1Handler = new Move1Handler(server,game,otherUser);
                move1Handler.handle(otherUser);
            }
        }
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
            Position nextPosition = new Position(position.getX() + 1, position.getY());
            robot.setPosition(nextPosition);
            Movement movement = new Movement(user.getClientID(), nextPosition.getX(), nextPosition.getY());
            try {
                server.broadcast(movement);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        } else if (robot.getRobotOrientation() == Orientation.TOP) {
            Position nextPosition = new Position(position.getX(), position.getY() + 1);
            robot.setPosition(nextPosition);
            Movement movement = new Movement(user.getClientID(), nextPosition.getX(), nextPosition.getY());
            try {
                server.broadcast(movement);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        } else if (robot.getRobotOrientation() == Orientation.BOTTOM) {
            Position nextPosition = new Position(position.getX(), position.getY() + 1);
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

