package bb.roborally.server.game.activation;

import bb.roborally.protocol.game_events.Movement;
import bb.roborally.protocol.game_events.Reboot;
import bb.roborally.server.Server;
import bb.roborally.server.game.*;

import java.io.IOException;

/**
 * @author Veronika Heckel
 */
public class BackUpHandler {

    Server server;
    Game game;
    User user;

    public BackUpHandler(Server server, Game game, User user){
        this.server = server;
        this.game = game;
        this.user = user;
    }

    /**
     * Class manages the movements of  aRobot for one step back. It considers the Pt-Case and the Off-Board Case.
     * In the case of having multiple Robots in one row - the moving Robot is capable of pushing other Robots. Walls inf front of a Robot in the single- and multi- Robot-Moving case
     * are built in. Walls between neighboring Robots are also handled.
     * Procedure is same as in Move1Handler only with reversed Directions.
     * @throws IOException
     * @throws IndexOutOfBoundsException
     */
    public void handle() throws IOException {
        Robot robot = user.getRobot();
        Position position = user.getRobot().getPosition();
        Orientation orientation = user.getRobot().getRobotOrientation();
        int x = position.getX();
        int y = position.getY();

        Orientation newOrientation = null;                  //Flipping the Directions of the Robot
        if (orientation == Orientation.TOP) {
            newOrientation = Orientation.BOTTOM;
        } else if (orientation == Orientation.RIGHT) {
            newOrientation = Orientation.LEFT;
        } else if (orientation == Orientation.LEFT) {
            newOrientation = Orientation.RIGHT;
        } else if (orientation == Orientation.BOTTOM) {
            newOrientation = Orientation.TOP;
        }
        MovementCheck movementCheck = new MovementCheck(game.getBoard(), game);
        if (movementCheck.checkIfBlockedAlt(position, newOrientation, 0)) {
            server.broadcast(new Movement(user.getClientID(), x, y));
        } else {
            if (user.getRobot().getRobotOrientation() == Orientation.TOP) {
                Orientation orientationFirst = Orientation.BOTTOM;
                if (movementCheck.checkIfFirstTwoAreNeighbors(game.getPlayerQueue().getUsers().get(0), game.getPlayerQueue().getUsers().get(1), orientationFirst, 1)) {
                    for (int i = 0; i < game.getPlayerQueue().getUsers().size() - 1; i++) {           //check if Players are neighbors - store them in extra list
                        movementCheck.robotForwardCheck(game.getPlayerQueue().getUsers().get(i), game.getPlayerQueue().getUsers().get(i+1), orientationFirst, 1);
                    }
                    movementCheck.checkIfLastTwoAreNeighbors(game.getPlayerQueue().getUsers().get(game.getPlayerQueue().getUsers().size() - 2), game.getPlayerQueue().getUsers().get(game.getPlayerQueue().getUsers().size() - 1), orientationFirst, 1);
                    if (movementCheck.checkIfBlockedAlt(movementCheck.getNeighbors().get(movementCheck.getNeighbors().size() - 1).getRobot().getPosition(), orientationFirst, 0)) {

                        for (int i = 0; i < game.getPlayerQueue().getUsers().size(); i++) {
                            if (movementCheck.getNeighbors().contains(game.getPlayerQueue().getUsers().get(i))) {
                                game.getPlayerQueue().getUsers().get(i).getRobot().setPosition(new Position(game.getPlayerQueue().getUsers().get(i).getRobot().getPosition().getX(), game.getPlayerQueue().getUsers().get(i).getRobot().getPosition().getY()));
                                server.broadcast(new Movement(game.getPlayerQueue().getUsers().get(i).getClientID(), game.getPlayerQueue().getUsers().get(i).getRobot().getPosition().getX(), game.getPlayerQueue().getUsers().get(i).getRobot().getPosition().getY()));
                            }
                        }
                    } else {
                        for (int i = 0; i < game.getPlayerQueue().getUsers().size(); i++) {
                            if (movementCheck.getNeighbors().contains(game.getPlayerQueue().getUsers().get(i))) {
                                if (!(movementCheck.checkIfBlockedAlt(game.getPlayerQueue().getUsers().get(i).getRobot().getPosition(), orientationFirst, 0))) {
                                    try {
                                        game.getPlayerQueue().getUsers().get(i).getRobot().setPosition(new Position(game.getPlayerQueue().getUsers().get(i).getRobot().getPosition().getX(), game.getPlayerQueue().getUsers().get(i).getRobot().getPosition().getY() + 1));         //Flipped Directions
                                        if (!(movementCheck.fallingInPit(game.getPlayerQueue().getUsers().get(i)))) {
                                            server.broadcast(new Movement(game.getPlayerQueue().getUsers().get(i).getClientID(), game.getPlayerQueue().getUsers().get(i).getRobot().getPosition().getX(), game.getPlayerQueue().getUsers().get(i).getRobot().getPosition().getY() + 1));
                                        } else {
                                            RebootHandler rebootHandler = new RebootHandler(server, game, user);
                                            rebootHandler.reboot();
                                            server.broadcast(new Reboot(game.getPlayerQueue().getUsers().get(i).getClientID()));
                                        }
                                    } catch (IndexOutOfBoundsException e) {
                                        RebootHandler rebootHandler = new RebootHandler(server, game, user);
                                        rebootHandler.reboot();
                                        server.broadcast(new Reboot(game.getPlayerQueue().getUsers().get(i).getClientID()));
                                    }
                                } else {
                                    for (int j = 0; j < i; j++) {
                                        game.getPlayerQueue().getUsers().get(j).getRobot().setPosition(new Position(game.getPlayerQueue().getUsers().get(j).getRobot().getPosition().getX(), game.getPlayerQueue().getUsers().get(j).getRobot().getPosition().getY() - 1));
                                        server.broadcast(new Movement(game.getPlayerQueue().getUsers().get(j).getClientID(), game.getPlayerQueue().getUsers().get(j).getRobot().getPosition().getX(), game.getPlayerQueue().getUsers().get(j).getRobot().getPosition().getY()));
                                    }
                                    break;
                                }
                            }
                        }
                    }
                } else {
                    robot.setPosition(new Position(x, y + 1));
                    try {
                        if (!(movementCheck.fallingInPit(user))) {
                            server.broadcast(new Movement(user.getClientID(), x, y + 1));
                        } else {
                            RebootHandler rebootHandler = new RebootHandler(server, game, user);
                            rebootHandler.reboot();
                        }
                        server.broadcast(new Reboot(user.getClientID()));
                    } catch (IndexOutOfBoundsException e) {
                        RebootHandler rebootHandler = new RebootHandler(server, game, user);
                        rebootHandler.reboot();
                        server.broadcast(new Reboot(user.getClientID()));
                    }
                }
            } else if (user.getRobot().getRobotOrientation() == Orientation.LEFT) {
                Orientation orientationFirst = Orientation.RIGHT;
                if (movementCheck.checkIfFirstTwoAreNeighbors(game.getPlayerQueue().getUsers().get(0), game.getPlayerQueue().getUsers().get(1), orientationFirst, 1)) {
                    for (int i = 0; i < game.getPlayerQueue().getUsers().size() - 1; i++) {           //check if Players are neighbors - store them in extra list
                        movementCheck.robotForwardCheck(game.getPlayerQueue().getUsers().get(i), game.getPlayerQueue().getUsers().get(i+1), orientationFirst, 1);
                    }
                    movementCheck.checkIfLastTwoAreNeighbors(game.getPlayerQueue().getUsers().get(game.getPlayerQueue().getUsers().size() - 2), game.getPlayerQueue().getUsers().get(game.getPlayerQueue().getUsers().size() - 1), orientationFirst, 1);
                    if (movementCheck.checkIfBlockedAlt(movementCheck.getNeighbors().get(movementCheck.getNeighbors().size() - 1).getRobot().getPosition(), orientationFirst, 0)) {
                        //robot.setPosition(new Position(currentField.getX(), currentField.getY()));
                        for (int i = 0; i < game.getPlayerQueue().getUsers().size(); i++) {
                            if (movementCheck.getNeighbors().contains(game.getPlayerQueue().getUsers().get(i))) {
                                game.getPlayerQueue().getUsers().get(i).getRobot().setPosition(new Position(game.getPlayerQueue().getUsers().get(i).getRobot().getPosition().getX(), game.getPlayerQueue().getUsers().get(i).getRobot().getPosition().getY()));
                                server.broadcast(new Movement(game.getPlayerQueue().getUsers().get(i).getClientID(), game.getPlayerQueue().getUsers().get(i).getRobot().getPosition().getX(), game.getPlayerQueue().getUsers().get(i).getRobot().getPosition().getY()));
                            }
                        }
                    } else {
                        for (int i = 0; i < game.getPlayerQueue().getUsers().size(); i++) {
                            if (movementCheck.getNeighbors().contains(game.getPlayerQueue().getUsers().get(i))) {
                                if (!(movementCheck.checkIfBlockedAlt(game.getPlayerQueue().getUsers().get(i).getRobot().getPosition(), orientationFirst, 0))) {
                                    try {
                                        game.getPlayerQueue().getUsers().get(i).getRobot().setPosition(new Position(game.getPlayerQueue().getUsers().get(i).getRobot().getPosition().getX() + 1, game.getPlayerQueue().getUsers().get(i).getRobot().getPosition().getY()));         //Flipped Directions
                                        if (!(movementCheck.fallingInPit(game.getPlayerQueue().getUsers().get(i)))) {
                                            server.broadcast(new Movement(game.getPlayerQueue().getUsers().get(i).getClientID(), game.getPlayerQueue().getUsers().get(i).getRobot().getPosition().getX() + 1, game.getPlayerQueue().getUsers().get(i).getRobot().getPosition().getY()));
                                        } else {
                                            RebootHandler rebootHandler = new RebootHandler(server, game, user);
                                            rebootHandler.reboot();
                                            server.broadcast(new Reboot(game.getPlayerQueue().getUsers().get(i).getClientID()));
                                        }
                                    } catch (IndexOutOfBoundsException e) {
                                        RebootHandler rebootHandler = new RebootHandler(server, game, user);
                                        rebootHandler.reboot();
                                        server.broadcast(new Reboot(game.getPlayerQueue().getUsers().get(i).getClientID()));
                                    }
                                } else {
                                    for (int j = 0; j < i; j++) {
                                        game.getPlayerQueue().getUsers().get(j).getRobot().setPosition(new Position(game.getPlayerQueue().getUsers().get(j).getRobot().getPosition().getX() - 1, game.getPlayerQueue().getUsers().get(j).getRobot().getPosition().getY()));
                                        server.broadcast(new Movement(game.getPlayerQueue().getUsers().get(j).getClientID(), game.getPlayerQueue().getUsers().get(j).getRobot().getPosition().getX(), game.getPlayerQueue().getUsers().get(j).getRobot().getPosition().getY()));
                                    }
                                    break;
                                }
                            }
                        }
                    }
                } else {
                    robot.setPosition(new Position(x + 1, y));
                    try {
                        if (!(movementCheck.fallingInPit(user))) {
                            server.broadcast(new Movement(user.getClientID(), x + 1, y));
                        } else {
                            RebootHandler rebootHandler = new RebootHandler(server, game, user);
                            rebootHandler.reboot();
                            server.broadcast(new Reboot(user.getClientID()));
                        }
                    } catch (IndexOutOfBoundsException e) {
                        RebootHandler rebootHandler = new RebootHandler(server, game, user);
                        rebootHandler.reboot();
                        server.broadcast(new Reboot(user.getClientID()));
                    }
                }
            }else if (user.getRobot().getRobotOrientation() == Orientation.BOTTOM) {
                Orientation orientationFirst = Orientation.TOP;
                if (movementCheck.checkIfFirstTwoAreNeighbors(game.getPlayerQueue().getUsers().get(0), game.getPlayerQueue().getUsers().get(1), orientationFirst, 1)) {
                    for (int i = 0; i < game.getPlayerQueue().getUsers().size() - 1; i++) {           //check if Players are neighbors - store them in extra list
                        movementCheck.robotForwardCheck(game.getPlayerQueue().getUsers().get(i), game.getPlayerQueue().getUsers().get(i+1), orientationFirst, 1);
                    }
                    movementCheck.checkIfLastTwoAreNeighbors(game.getPlayerQueue().getUsers().get(game.getPlayerQueue().getUsers().size() - 2), game.getPlayerQueue().getUsers().get(game.getPlayerQueue().getUsers().size() - 1), orientationFirst, 1);
                    if (movementCheck.checkIfBlockedAlt(movementCheck.getNeighbors().get(movementCheck.getNeighbors().size() - 1).getRobot().getPosition(), orientationFirst, 0)) {
                        //robot.setPosition(new Position(currentField.getX(), currentField.getY()));
                        for (int i = 0; i < game.getPlayerQueue().getUsers().size(); i++) {
                            if (movementCheck.getNeighbors().contains(game.getPlayerQueue().getUsers().get(i))) {
                                game.getPlayerQueue().getUsers().get(i).getRobot().setPosition(new Position(game.getPlayerQueue().getUsers().get(i).getRobot().getPosition().getX(), game.getPlayerQueue().getUsers().get(i).getRobot().getPosition().getY()));
                                server.broadcast(new Movement(game.getPlayerQueue().getUsers().get(i).getClientID(), game.getPlayerQueue().getUsers().get(i).getRobot().getPosition().getX(), game.getPlayerQueue().getUsers().get(i).getRobot().getPosition().getY()));
                            }
                        }
                    } else {
                        for (int i = 0; i < game.getPlayerQueue().getUsers().size(); i++) {
                            if (movementCheck.getNeighbors().contains(game.getPlayerQueue().getUsers().get(i))) {
                                if(!(movementCheck.checkIfBlockedAlt(game.getPlayerQueue().getUsers().get(i).getRobot().getPosition(), orientationFirst, 0))) {
                                    try {
                                        game.getPlayerQueue().getUsers().get(i).getRobot().setPosition(new Position(game.getPlayerQueue().getUsers().get(i).getRobot().getPosition().getX(), game.getPlayerQueue().getUsers().get(i).getRobot().getPosition().getY() - 1));     //FLipped Directions
                                        if (!(movementCheck.fallingInPit(game.getPlayerQueue().getUsers().get(i)))) {
                                            server.broadcast(new Movement(game.getPlayerQueue().getUsers().get(i).getClientID(), game.getPlayerQueue().getUsers().get(i).getRobot().getPosition().getX(), game.getPlayerQueue().getUsers().get(i).getRobot().getPosition().getY() - 1));
                                        } else {
                                            RebootHandler rebootHandler = new RebootHandler(server, game, user);
                                            rebootHandler.reboot();
                                            server.broadcast(new Reboot(game.getPlayerQueue().getUsers().get(i).getClientID()));
                                        }
                                    } catch (IndexOutOfBoundsException e) {
                                        RebootHandler rebootHandler = new RebootHandler(server, game, user);
                                        rebootHandler.reboot();
                                        server.broadcast(new Reboot(game.getPlayerQueue().getUsers().get(i).getClientID()));
                                    }
                                }else{
                                    for(int j = 0; j < i; j++){
                                        game.getPlayerQueue().getUsers().get(j).getRobot().setPosition(new Position(game.getPlayerQueue().getUsers().get(j).getRobot().getPosition().getX(), game.getPlayerQueue().getUsers().get(j).getRobot().getPosition().getY() + 1));
                                        server.broadcast(new Movement(game.getPlayerQueue().getUsers().get(j).getClientID(), game.getPlayerQueue().getUsers().get(j).getRobot().getPosition().getX(), game.getPlayerQueue().getUsers().get(j).getRobot().getPosition().getY()));
                                    }
                                    break;
                                }
                            }
                        }
                    }
                }else {
                    robot.setPosition(new Position(x, y - 1));
                    try {
                        if (!(movementCheck.fallingInPit(user))) {
                            server.broadcast(new Movement(user.getClientID(), x, y - 1));
                        }else{
                            server.broadcast(new Reboot(user.getClientID()));
                        }
                    } catch (IndexOutOfBoundsException e) {
                        RebootHandler rebootHandler = new RebootHandler(server, game, user);
                        rebootHandler.reboot();
                        server.broadcast(new Reboot(user.getClientID()));
                    }
                }
            } else if (user.getRobot().getRobotOrientation() == Orientation.RIGHT) {
                Orientation orientationFirst = Orientation.LEFT;
                if (movementCheck.checkIfFirstTwoAreNeighbors(game.getPlayerQueue().getUsers().get(0), game.getPlayerQueue().getUsers().get(1), orientationFirst, 1)) {
                    for (int i = 0; i < game.getPlayerQueue().getUsers().size() - 1; i++) {           //check if Players are neighbors - store them in extra list
                        movementCheck.robotForwardCheck(game.getPlayerQueue().getUsers().get(i), game.getPlayerQueue().getUsers().get(i+1), orientationFirst, 1);
                    }
                    movementCheck.checkIfLastTwoAreNeighbors(game.getPlayerQueue().getUsers().get(game.getPlayerQueue().getUsers().size() - 2), game.getPlayerQueue().getUsers().get(game.getPlayerQueue().getUsers().size() - 1), orientationFirst, 1);
                    if (movementCheck.checkIfBlockedAlt(movementCheck.getNeighbors().get(movementCheck.getNeighbors().size() - 1).getRobot().getPosition(), orientationFirst, 0)) {
                        //robot.setPosition(new Position(currentField.getX(), currentField.getY()));
                        for (int i = 0; i < game.getPlayerQueue().getUsers().size(); i++) {
                            if (movementCheck.getNeighbors().contains(game.getPlayerQueue().getUsers().get(i))) {
                                game.getPlayerQueue().getUsers().get(i).getRobot().setPosition(new Position(game.getPlayerQueue().getUsers().get(i).getRobot().getPosition().getX(), game.getPlayerQueue().getUsers().get(i).getRobot().getPosition().getY()));
                                server.broadcast(new Movement(game.getPlayerQueue().getUsers().get(i).getClientID(), game.getPlayerQueue().getUsers().get(i).getRobot().getPosition().getX(), game.getPlayerQueue().getUsers().get(i).getRobot().getPosition().getY()));
                            }
                        }
                    } else {
                        for (int i = 0; i < game.getPlayerQueue().getUsers().size(); i++) {
                            if (movementCheck.getNeighbors().contains(game.getPlayerQueue().getUsers().get(i))) {
                                if(!(movementCheck.checkIfBlockedAlt(game.getPlayerQueue().getUsers().get(i).getRobot().getPosition(), orientationFirst, 0))) {
                                    try {
                                        game.getPlayerQueue().getUsers().get(i).getRobot().setPosition(new Position(game.getPlayerQueue().getUsers().get(i).getRobot().getPosition().getX() - 1, game.getPlayerQueue().getUsers().get(i).getRobot().getPosition().getY()));         //Flipped Directions
                                        if (!(movementCheck.fallingInPit(game.getPlayerQueue().getUsers().get(i)))) {
                                            server.broadcast(new Movement(game.getPlayerQueue().getUsers().get(i).getClientID(), game.getPlayerQueue().getUsers().get(i).getRobot().getPosition().getX() - 1, game.getPlayerQueue().getUsers().get(i).getRobot().getPosition().getY()));
                                        } else {
                                            RebootHandler rebootHandler = new RebootHandler(server, game, user);
                                            rebootHandler.reboot();
                                            server.broadcast(new Reboot(game.getPlayerQueue().getUsers().get(i).getClientID()));
                                        }
                                    } catch (IndexOutOfBoundsException e) {
                                        RebootHandler rebootHandler = new RebootHandler(server, game, user);
                                        rebootHandler.reboot();
                                        server.broadcast(new Reboot(game.getPlayerQueue().getUsers().get(i).getClientID()));
                                    }
                                }else{
                                    for(int j = 0; j < i; j++){
                                        game.getPlayerQueue().getUsers().get(j).getRobot().setPosition(new Position(game.getPlayerQueue().getUsers().get(j).getRobot().getPosition().getX() - 1, game.getPlayerQueue().getUsers().get(j).getRobot().getPosition().getY()));
                                        server.broadcast(new Movement(game.getPlayerQueue().getUsers().get(j).getClientID(), game.getPlayerQueue().getUsers().get(j).getRobot().getPosition().getX(), game.getPlayerQueue().getUsers().get(j).getRobot().getPosition().getY()));
                                    }
                                    break;
                                }
                            }
                        }
                    }
                }else{
                    robot.setPosition(new Position(x, y - 1));
                    try{
                        if (!(movementCheck.fallingInPit(user))) {
                            server.broadcast(new Movement(user.getClientID(), x, y - 1));
                        }else{
                            RebootHandler rebootHandler = new RebootHandler(server, game, user);
                            rebootHandler.reboot();
                            server.broadcast(new Reboot(user.getClientID()));
                        }
                    }catch (IndexOutOfBoundsException e){
                        RebootHandler rebootHandler = new RebootHandler(server, game, user);
                        rebootHandler.reboot();
                        server.broadcast(new Reboot(user.getClientID()));
                    }
                }
            }
        }
    }
}
