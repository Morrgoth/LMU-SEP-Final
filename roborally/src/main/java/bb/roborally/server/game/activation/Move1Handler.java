package bb.roborally.server.game.activation;


import bb.roborally.protocol.game_events.Movement;
import bb.roborally.protocol.game_events.Reboot;
import bb.roborally.server.Server;
import bb.roborally.server.game.*;
import java.io.IOException;


/**
 * @author Veronika Heckel
 */
public class Move1Handler {

    Server server;
    Game game;
    User user;

    public Move1Handler(Server server, Game game, User user) {
        this.server = server;
        this.game = game;
        this.user = user;
    }

    /**
     * Class manages the movements of  aRobot for one step. It considers the Pt-Case and the Off-Board Case.
     * In the case of having multiple Robots in one row - the moving Robot is capable of pushing other Robots. Walls inf front of a Robot in the single- and multi- Robot-Moving case
     * are built in. Walls between neighboring Robots are also handled.
     * @throws IOException
     */
    public void handle() throws IOException {
        Robot robot = user.getRobot();
        Position position = user.getRobot().getPosition();
        Orientation orientation = user.getRobot().getRobotOrientation();
        int x = position.getX();
        int y = position.getY();


        MovementCheck movementCheck = new MovementCheck(game.getBoard(), game);
        if (movementCheck.checkIfBlockedAlt(position, orientation, 0)) {            //First Check if Robot can go one step - if not --> no Movements, else iterating over Robot-Orientations
            server.broadcast(new Movement(user.getClientID(), x, y));
        } else {
            if (user.getRobot().getRobotOrientation() == Orientation.TOP) {
                Orientation orientationFirst = Orientation.TOP;

                if (movementCheck.checkIfFirstTwoAreNeighbors(game.getPlayerQueue().getUsers().get(0), game.getPlayerQueue().getUsers().get(1), orientationFirst, 1)) {             //Check if only on Robot is moving or if there are any Neighbors
                    for (int i = 0; i < game.getPlayerQueue().getUsers().size() - 1; i++) {                                                                                                 //check if Players are neighbors - store them in extra list "neighbors"
                        movementCheck.robotForwardCheck(game.getPlayerQueue().getUsers().get(i), game.getPlayerQueue().getUsers().get(i + 1), orientationFirst, 1);
                    }
                    movementCheck.checkIfLastTwoAreNeighbors(game.getPlayerQueue().getUsers().get(game.getPlayerQueue().getUsers().size() - 2), game.getPlayerQueue().getUsers().get(game.getPlayerQueue().getUsers().size() - 1), orientationFirst, 1);                //Checks if last two are neighbors to store the last member of the neighbors

                    if (movementCheck.checkIfBlockedAlt(movementCheck.getNeighbors().get(movementCheck.getNeighbors().size() - 1).getRobot().getPosition(), orientationFirst, 0)) {             //Check if last Member of Neighbors is blocked --> no movement at all / iterating over whole Players list who are also in neighborslist and set the same position as at the beginning ov moving phase
                        for (int i = 0; i < game.getPlayerQueue().getUsers().size(); i++) {
                            if (movementCheck.getNeighbors().contains(game.getPlayerQueue().getUsers().get(i))) {                                                                                   //if not blocked, iterating over whole Playerslist (also members of neighbors-list) and set one position ahead
                                game.getPlayerQueue().getUsers().get(i).getRobot().setPosition(new Position(game.getPlayerQueue().getUsers().get(i).getRobot().getPosition().getX(), game.getPlayerQueue().getUsers().get(i).getRobot().getPosition().getY()));
                                server.broadcast(new Movement(game.getPlayerQueue().getUsers().get(i).getClientID(), game.getPlayerQueue().getUsers().get(i).getRobot().getPosition().getX(), game.getPlayerQueue().getUsers().get(i).getRobot().getPosition().getY()));            //sending each member of list, which is also moving a message that he is moving
                            }
                        }
                    } else {
                        for (int i = 0; i < game.getPlayerQueue().getUsers().size(); i++) {                                                            //if not blocked, iterating over whole Playerslist (also members of neighbors-list) and set one position ahead
                            if (movementCheck.getNeighbors().contains(game.getPlayerQueue().getUsers().get(i))) {                                          //sending each member of list, which is also moving a message that he is moving
                                if (!(movementCheck.checkIfBlockedAlt(game.getPlayerQueue().getUsers().get(i).getRobot().getPosition(), orientationFirst, 0))) {            //check if last member of neighbors is not blocked --> if yes and other neighbors are behind a wall --> else clause handling this case, otherwise one step ahead for every neighbor and other neighbors after the wall stay on same position
                                    try {
                                        game.getPlayerQueue().getUsers().get(i).getRobot().setPosition(new Position(game.getPlayerQueue().getUsers().get(i).getRobot().getPosition().getX(), game.getPlayerQueue().getUsers().get(i).getRobot().getPosition().getY() - 1));             //actual moving process in try - catch clause because of potentially leaving the board --> Rebooting
                                        if (!(movementCheck.fallingInPit(game.getPlayerQueue().getUsers().get(i), 0, 0))) {                                                                                                                                                                      //check if player is on Pit --> Reboot
                                            server.broadcast(new Movement(game.getPlayerQueue().getUsers().get(i).getClientID(), game.getPlayerQueue().getUsers().get(i).getRobot().getPosition().getX(), game.getPlayerQueue().getUsers().get(i).getRobot().getPosition().getY() - 1));
                                        } else {
                                            RebootHandler rebootHandler = new RebootHandler(server, game, game.getPlayerQueue().getUsers().get(i));
                                            rebootHandler.reboot();
                                            server.broadcast(new Reboot(game.getPlayerQueue().getUsers().get(i).getClientID()));
                                        }
                                    } catch (
                                            IndexOutOfBoundsException e) {                                                         //catching exception --> player off board (Reboot)
                                        RebootHandler rebootHandler = new RebootHandler(server, game, game.getPlayerQueue().getUsers().get(i));
                                        rebootHandler.reboot();
                                        server.broadcast(new Reboot(game.getPlayerQueue().getUsers().get(i).getClientID()));
                                    }
                                } else {
                                    for (int j = 0; j < i; j++) {           //if there are neighbors behind a wall - all members that are in front of it need to go one step back (the
                                        game.getPlayerQueue().getUsers().get(j).getRobot().setPosition(new Position(game.getPlayerQueue().getUsers().get(j).getRobot().getPosition().getX(), game.getPlayerQueue().getUsers().get(j).getRobot().getPosition().getY() + 1));
                                        server.broadcast(new Movement(game.getPlayerQueue().getUsers().get(j).getClientID(), game.getPlayerQueue().getUsers().get(j).getRobot().getPosition().getX(), game.getPlayerQueue().getUsers().get(j).getRobot().getPosition().getY()));
                                    }
                                    break;          //leaving the loop over player list, because neighbors  behind a wall don't move
                                }
                            }
                        }
                    }
                } else {                                            //handling steps of only one player
                    robot.setPosition(new Position(x, y - 1));
                    try {
                        if (!(movementCheck.fallingInPit(user, 0, 0))) {              //Pit check, if yes --> Reboot
                            server.broadcast(new Movement(user.getClientID(), x, y - 1));           // Message to client --> Movements
                        } else {
                            RebootHandler rebootHandler = new RebootHandler(server, game, user);
                            rebootHandler.reboot();
                            server.broadcast(new Reboot(user.getClientID()));
                        }
                    } catch (
                            IndexOutOfBoundsException e) {                                                 //Exception - handling for off-board-case
                        RebootHandler rebootHandler = new RebootHandler(server, game, user);
                        rebootHandler.reboot();
                        server.broadcast(new Reboot(user.getClientID()));
                    }
                }
            } else if (user.getRobot().getRobotOrientation() == Orientation.LEFT) {
                Orientation orientationFirst = Orientation.LEFT;
                if (movementCheck.checkIfFirstTwoAreNeighbors(game.getPlayerQueue().getUsers().get(0), game.getPlayerQueue().getUsers().get(1), orientationFirst, 1)) {
                    for (int i = 0; i < game.getPlayerQueue().getUsers().size() - 1; i++) {           //check if Players are neighbors - store them in extra list
                        movementCheck.robotForwardCheck(game.getPlayerQueue().getUsers().get(i), game.getPlayerQueue().getUsers().get(i + 1), orientationFirst, 1);
                    }
                    movementCheck.checkIfLastTwoAreNeighbors(game.getPlayerQueue().getUsers().get(game.getPlayerQueue().getUsers().size() - 2), game.getPlayerQueue().getUsers().get(game.getPlayerQueue().getUsers().size() - 1), orientationFirst, 1);
                    //check if first two are neighbors and store the first one in same list - extra method because the first one will not be stored in first method

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
                                        game.getPlayerQueue().getUsers().get(i).getRobot().setPosition(new Position(game.getPlayerQueue().getUsers().get(i).getRobot().getPosition().getX() - 1, game.getPlayerQueue().getUsers().get(i).getRobot().getPosition().getY()));
                                        if (!(movementCheck.fallingInPit(game.getPlayerQueue().getUsers().get(i), 0, 0))) {
                                            server.broadcast(new Movement(game.getPlayerQueue().getUsers().get(i).getClientID(), game.getPlayerQueue().getUsers().get(i).getRobot().getPosition().getX() - 1, game.getPlayerQueue().getUsers().get(i).getRobot().getPosition().getY()));
                                        } else {
                                            RebootHandler rebootHandler = new RebootHandler(server, game, game.getPlayerQueue().getUsers().get(i));
                                            rebootHandler.reboot();
                                            server.broadcast(new Reboot(game.getPlayerQueue().getUsers().get(i).getClientID()));
                                        }
                                    } catch (IndexOutOfBoundsException e) {
                                        RebootHandler rebootHandler = new RebootHandler(server, game, game.getPlayerQueue().getUsers().get(i));
                                        rebootHandler.reboot();
                                        server.broadcast(new Reboot(game.getPlayerQueue().getUsers().get(i).getClientID()));
                                    }
                                } else {
                                    for (int j = 0; j < i; j++) {
                                        game.getPlayerQueue().getUsers().get(j).getRobot().setPosition(new Position(game.getPlayerQueue().getUsers().get(j).getRobot().getPosition().getX() + 1, game.getPlayerQueue().getUsers().get(j).getRobot().getPosition().getY()));
                                        server.broadcast(new Movement(game.getPlayerQueue().getUsers().get(j).getClientID(), game.getPlayerQueue().getUsers().get(j).getRobot().getPosition().getX(), game.getPlayerQueue().getUsers().get(j).getRobot().getPosition().getY()));
                                    }
                                    break;
                                }
                            }
                        }
                    }
                } else {
                    try {
                        robot.setPosition(new Position(x - 1, y));
                        if (!(movementCheck.fallingInPit(user, 0, 0))) {
                            server.broadcast(new Movement(user.getClientID(), x - 1, y));
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
            } else if (user.getRobot().getRobotOrientation() == Orientation.BOTTOM) {
                Orientation orientationFirst = Orientation.BOTTOM;
                if (movementCheck.checkIfFirstTwoAreNeighbors(game.getPlayerQueue().getUsers().get(0), game.getPlayerQueue().getUsers().get(1), orientationFirst, 1)) {
                    for (int i = 0; i < game.getPlayerQueue().getUsers().size() - 1; i++) {           //check if Players are neighbors - store them in extra list
                        movementCheck.robotForwardCheck(game.getPlayerQueue().getUsers().get(i), game.getPlayerQueue().getUsers().get(i + 1), orientationFirst, 1);
                    }
                    movementCheck.checkIfLastTwoAreNeighbors(game.getPlayerQueue().getUsers().get(game.getPlayerQueue().getUsers().size() - 2), game.getPlayerQueue().getUsers().get(game.getPlayerQueue().getUsers().size() - 1), orientationFirst, 1);
                    //check if last two are neighbors and store the first one in same list - extra method because the first one will not be stored in first method
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
                                        game.getPlayerQueue().getUsers().get(i).getRobot().setPosition(new Position(game.getPlayerQueue().getUsers().get(i).getRobot().getPosition().getX(), game.getPlayerQueue().getUsers().get(i).getRobot().getPosition().getY() + 1));
                                        if (!(movementCheck.fallingInPit(game.getPlayerQueue().getUsers().get(i), 0, 0))) {
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
                    try {
                        robot.setPosition(new Position(x, y + 1));
                        if (!(movementCheck.fallingInPit(user, 0, 0))) {
                            server.broadcast(new Movement(user.getClientID(), x,y + 1));
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
            } else if (user.getRobot().getRobotOrientation() == Orientation.RIGHT) {
                Orientation orientationFirst = Orientation.RIGHT;
                if (movementCheck.checkIfFirstTwoAreNeighbors(game.getPlayerQueue().getUsers().get(0), game.getPlayerQueue().getUsers().get(1), orientationFirst, 1)) {
                    for (int i = 0; i < game.getPlayerQueue().getUsers().size() - 1; i++) {           //check if Players are neighbors - store them in extra list
                        movementCheck.robotForwardCheck(game.getPlayerQueue().getUsers().get(i), game.getPlayerQueue().getUsers().get(i + 1), orientationFirst, 1);
                    }
                    movementCheck.checkIfLastTwoAreNeighbors(game.getPlayerQueue().getUsers().get(game.getPlayerQueue().getUsers().size() - 2), game.getPlayerQueue().getUsers().get(game.getPlayerQueue().getUsers().size() - 1), orientationFirst, 1);
                    //check if last two are neighbors and store the first one in same list - extra method because the first one will not be stored in first method
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
                                        game.getPlayerQueue().getUsers().get(i).getRobot().setPosition(new Position(game.getPlayerQueue().getUsers().get(i).getRobot().getPosition().getX() + 1, game.getPlayerQueue().getUsers().get(i).getRobot().getPosition().getY()));
                                        if (!(movementCheck.fallingInPit(game.getPlayerQueue().getUsers().get(i), 0, 0))) {
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
                    try {
                        robot.setPosition(new Position(x + 1, y));
                        if (!(movementCheck.fallingInPit(user, 0, 0))) {
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
            }
        }
    }
}







