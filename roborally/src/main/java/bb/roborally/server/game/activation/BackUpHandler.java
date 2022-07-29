package bb.roborally.server.game.activation;

import bb.roborally.protocol.Orientation;
import bb.roborally.protocol.Position;
import bb.roborally.protocol.game_events.Movement;
import bb.roborally.protocol.game_events.Reboot;
import bb.roborally.server.Server;
import bb.roborally.server.game.*;

import java.io.IOException;

/**
 * @author Zeynab Baiani
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
            Movement movement = new Movement(user.getClientID(), x,y);
            server.broadcast(movement);
        } else {
            if (user.getRobot().getRobotOrientation() == Orientation.TOP) {                         //iterating over all Directions of the Robot
                Orientation orientationFirst = Orientation.BOTTOM;
                if (movementCheck.robotForwardCheck(game.getPlayerQueue().getUsers().get(0), game.getPlayerQueue().getUsers().get(1), orientationFirst, 1) && (!movementCheck.checkIfBlockedAlt(game.getPlayerQueue().getUsers().get(0).getRobot().getPosition(),orientationFirst,0))) {
                    for (int i = 1; i < game.getPlayerQueue().getUsers().size(); i++) {                                                                             //check if Players are neighbors - store them in extra list
                        if (movementCheck.checkIfBlockedAlt(game.getPlayerQueue().getUsers().get(i).getRobot().getPosition(), orientationFirst, 0)){
                            movementCheck.checkIfLastTwoAreNeighbors(game.getPlayerQueue().getUsers().get(i-1), game.getPlayerQueue().getUsers().get(i), Orientation.TOP, -1);
                            break;
                        }else{
                            movementCheck.checkIfLastTwoAreNeighbors(game.getPlayerQueue().getUsers().get(i-1), game.getPlayerQueue().getUsers().get(i), Orientation.TOP, -1);
                        }
                    }
                    if (movementCheck.checkIfBlockedAlt(movementCheck.getNeighbors().get(movementCheck.getNeighbors().size() - 1).getRobot().getPosition(), orientationFirst, 0)) {                         //moving every member of neighbors' list one or no step ahead depending of the block

                        for (int i = 0; i < game.getPlayerQueue().getUsers().size(); i++) {
                            if (movementCheck.getNeighbors().contains(game.getPlayerQueue().getUsers().get(i))) {
                                game.getPlayerQueue().getUsers().get(i).getRobot().setPosition(new Position(game.getPlayerQueue().getUsers().get(i).getRobot().getPosition().getX(), game.getPlayerQueue().getUsers().get(i).getRobot().getPosition().getY()));
                                Movement movement = new Movement(game.getPlayerQueue().getUsers().get(i).getClientID(), game.getPlayerQueue().getUsers().get(i).getRobot().getPosition().getX(), game.getPlayerQueue().getUsers().get(i).getRobot().getPosition().getY());
                                server.broadcast(movement);
                            }
                        }
                    } else {
                        for (int i = 0; i < game.getPlayerQueue().getUsers().size(); i++) {
                            if (movementCheck.getNeighbors().contains(game.getPlayerQueue().getUsers().get(i))) {
                                if (!(movementCheck.checkIfBlockedAlt(game.getPlayerQueue().getUsers().get(i).getRobot().getPosition(), orientationFirst, 0))) {
                                    try {
                                        game.getPlayerQueue().getUsers().get(i).getRobot().setPosition(new Position(game.getPlayerQueue().getUsers().get(i).getRobot().getPosition().getX(), game.getPlayerQueue().getUsers().get(i).getRobot().getPosition().getY() + 1));         //Flipped Directions
                                        if (movementCheck.fallingInPit(game.getPlayerQueue().getUsers().get(i),0,0)) {
                                            RebootHandler rebootHandler = new RebootHandler(server, game, game.getPlayerQueue().getUsers().get(i));
                                            rebootHandler.reboot();
                                            Reboot reboot = new Reboot(game.getPlayerQueue().getUsers().get(i).getClientID());
                                            server.broadcast(reboot);
                                        }else{
                                            Movement movement = new Movement(game.getPlayerQueue().getUsers().get(i).getClientID(), game.getPlayerQueue().getUsers().get(i).getRobot().getPosition().getX(), game.getPlayerQueue().getUsers().get(i).getRobot().getPosition().getY() + 1);
                                            server.broadcast(movement);
                                        }
                                    } catch (IndexOutOfBoundsException e) {
                                        RebootHandler rebootHandler = new RebootHandler(server, game, game.getPlayerQueue().getUsers().get(i));
                                        rebootHandler.reboot();
                                        Reboot reboot = new Reboot(game.getPlayerQueue().getUsers().get(i).getClientID());
                                        server.broadcast(reboot);
                                    }
                                } else {
                                    for (int j = 0; j < i; j++) {
                                        game.getPlayerQueue().getUsers().get(j).getRobot().setPosition(new Position(game.getPlayerQueue().getUsers().get(j).getRobot().getPosition().getX(), game.getPlayerQueue().getUsers().get(j).getRobot().getPosition().getY() - 1));
                                        Movement movement = new Movement(game.getPlayerQueue().getUsers().get(i).getClientID(), game.getPlayerQueue().getUsers().get(i).getRobot().getPosition().getX(), game.getPlayerQueue().getUsers().get(i).getRobot().getPosition().getY() - 1) ;
                                        server.broadcast(movement);
                                    }
                                    break;
                                }
                            }
                        }
                    }
                } else {
                    try {                                                                                       //moving one single user
                        robot.setPosition(new Position(x, y + 1));                                          //Opposite Counting of x and y coordinates because of reversed directions
                        if(movementCheck.fallingInPit(user,0,0)){
                            RebootHandler rebootHandler = new RebootHandler(server, game, user);
                            rebootHandler.reboot();
                            Reboot reboot = new Reboot(user.getClientID());
                            server.broadcast(reboot);
                        }else{
                            Movement movement = new Movement(user.getClientID(), x, y + 1);
                            server.broadcast(movement);
                        }
                    } catch (IndexOutOfBoundsException e) {                                                      //handling off-Board Situation
                        RebootHandler rebootHandler = new RebootHandler(server, game, user);
                        rebootHandler.reboot();
                        Reboot reboot = new Reboot(user.getClientID());
                        server.broadcast(reboot);
                    }
                }
            } else if (user.getRobot().getRobotOrientation() == Orientation.LEFT) {
                Orientation orientationFirst = Orientation.RIGHT;
                if (movementCheck.robotForwardCheck(game.getPlayerQueue().getUsers().get(0), game.getPlayerQueue().getUsers().get(1), orientationFirst, 1) && (!movementCheck.checkIfBlockedAlt(game.getPlayerQueue().getUsers().get(0).getRobot().getPosition(),orientationFirst,0))) {
                    for (int i = 1; i < game.getPlayerQueue().getUsers().size(); i++) {           //check if Players are neighbors - store them in extra list
                        if (movementCheck.checkIfBlockedAlt(game.getPlayerQueue().getUsers().get(i).getRobot().getPosition(), orientationFirst, 0)){
                            movementCheck.checkIfLastTwoAreNeighbors(game.getPlayerQueue().getUsers().get(i-1), game.getPlayerQueue().getUsers().get(i), Orientation.LEFT, -1);
                            break;
                        }else{
                            movementCheck.checkIfLastTwoAreNeighbors(game.getPlayerQueue().getUsers().get(i-1), game.getPlayerQueue().getUsers().get(i), Orientation.LEFT, -1);
                        }
                    }
                    if (movementCheck.checkIfBlockedAlt(movementCheck.getNeighbors().get(movementCheck.getNeighbors().size() - 1).getRobot().getPosition(), orientationFirst, 0)) {
                        for (int i = 0; i < game.getPlayerQueue().getUsers().size(); i++) {
                            if (movementCheck.getNeighbors().contains(game.getPlayerQueue().getUsers().get(i))) {
                                game.getPlayerQueue().getUsers().get(i).getRobot().setPosition(new Position(game.getPlayerQueue().getUsers().get(i).getRobot().getPosition().getX(), game.getPlayerQueue().getUsers().get(i).getRobot().getPosition().getY()));
                                Movement movement = new Movement(game.getPlayerQueue().getUsers().get(i).getClientID(), game.getPlayerQueue().getUsers().get(i).getRobot().getPosition().getX(), game.getPlayerQueue().getUsers().get(i).getRobot().getPosition().getY());
                                server.broadcast(movement);
                            }
                        }
                    } else {
                        for (int i = 0; i < game.getPlayerQueue().getUsers().size(); i++) {
                            if (movementCheck.getNeighbors().contains(game.getPlayerQueue().getUsers().get(i))) {
                                if (!(movementCheck.checkIfBlockedAlt(game.getPlayerQueue().getUsers().get(i).getRobot().getPosition(), orientationFirst, 0))) {
                                    try {
                                        game.getPlayerQueue().getUsers().get(i).getRobot().setPosition(new Position(game.getPlayerQueue().getUsers().get(i).getRobot().getPosition().getX() + 1, game.getPlayerQueue().getUsers().get(i).getRobot().getPosition().getY()));         //Flipped Directions
                                        if (movementCheck.fallingInPit(game.getPlayerQueue().getUsers().get(i),0,0)) {
                                            RebootHandler rebootHandler = new RebootHandler(server, game, game.getPlayerQueue().getUsers().get(i));
                                            rebootHandler.reboot();
                                            Reboot reboot = new Reboot(game.getPlayerQueue().getUsers().get(i).getClientID());
                                            server.broadcast(reboot);
                                        }else{
                                            Movement movement = new Movement(game.getPlayerQueue().getUsers().get(i).getClientID(), game.getPlayerQueue().getUsers().get(i).getRobot().getPosition().getX() + 1, game.getPlayerQueue().getUsers().get(i).getRobot().getPosition().getY());
                                            server.broadcast(movement);
                                        }
                                    } catch (IndexOutOfBoundsException e) {
                                        RebootHandler rebootHandler = new RebootHandler(server, game, game.getPlayerQueue().getUsers().get(i));
                                        rebootHandler.reboot();
                                        Reboot reboot = new Reboot(game.getPlayerQueue().getUsers().get(i).getClientID());
                                        server.broadcast(reboot);
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
                        if (movementCheck.fallingInPit(user,0,0)) {
                            RebootHandler rebootHandler = new RebootHandler(server, game, user);
                            rebootHandler.reboot();
                            Reboot reboot = new Reboot(user.getClientID());
                            server.broadcast(reboot);
                        }else{
                            Movement movement = new Movement(user.getClientID(), x + 1, y);
                            server.broadcast(movement);
                        }
                    } catch (IndexOutOfBoundsException e) {
                        RebootHandler rebootHandler = new RebootHandler(server, game, user);
                        rebootHandler.reboot();
                        Reboot reboot = new Reboot(user.getClientID());
                        server.broadcast(reboot);
                    }
                }
            }else if (user.getRobot().getRobotOrientation() == Orientation.BOTTOM) {
                Orientation orientationFirst = Orientation.TOP;
                if (movementCheck.robotForwardCheck(game.getPlayerQueue().getUsers().get(0), game.getPlayerQueue().getUsers().get(1), orientationFirst, 1) && (!movementCheck.checkIfBlockedAlt(game.getPlayerQueue().getUsers().get(0).getRobot().getPosition(),orientationFirst,0))) {
                    for (int i = 1; i < game.getPlayerQueue().getUsers().size(); i++) {           //check if Players are neighbors - store them in extra list
                        if (movementCheck.checkIfBlockedAlt(game.getPlayerQueue().getUsers().get(i).getRobot().getPosition(), orientationFirst, 0)){
                            movementCheck.checkIfLastTwoAreNeighbors(game.getPlayerQueue().getUsers().get(i-1), game.getPlayerQueue().getUsers().get(i), Orientation.BOTTOM, -1);
                            break;
                        }else{
                            movementCheck.checkIfLastTwoAreNeighbors(game.getPlayerQueue().getUsers().get(i-1), game.getPlayerQueue().getUsers().get(i), Orientation.BOTTOM, -1);
                        }
                    }
                    if (movementCheck.checkIfBlockedAlt(movementCheck.getNeighbors().get(movementCheck.getNeighbors().size() - 1).getRobot().getPosition(), orientationFirst, 0)) {
                        for (int i = 0; i < game.getPlayerQueue().getUsers().size(); i++) {
                            if (movementCheck.getNeighbors().contains(game.getPlayerQueue().getUsers().get(i))) {
                                game.getPlayerQueue().getUsers().get(i).getRobot().setPosition(new Position(game.getPlayerQueue().getUsers().get(i).getRobot().getPosition().getX(), game.getPlayerQueue().getUsers().get(i).getRobot().getPosition().getY()));
                                Movement movement = new Movement(game.getPlayerQueue().getUsers().get(i).getClientID(), game.getPlayerQueue().getUsers().get(i).getRobot().getPosition().getX(), game.getPlayerQueue().getUsers().get(i).getRobot().getPosition().getY());
                                server.broadcast(movement);
                            }
                        }
                    } else {
                        for (int i = 0; i < game.getPlayerQueue().getUsers().size(); i++) {
                            if (movementCheck.getNeighbors().contains(game.getPlayerQueue().getUsers().get(i))) {
                                if(!(movementCheck.checkIfBlockedAlt(game.getPlayerQueue().getUsers().get(i).getRobot().getPosition(), orientationFirst, 0))) {
                                    try {
                                        game.getPlayerQueue().getUsers().get(i).getRobot().setPosition(new Position(game.getPlayerQueue().getUsers().get(i).getRobot().getPosition().getX(), game.getPlayerQueue().getUsers().get(i).getRobot().getPosition().getY() - 1));     //FLipped Directions
                                        if (movementCheck.fallingInPit(game.getPlayerQueue().getUsers().get(i),0,0)){
                                            RebootHandler rebootHandler = new RebootHandler(server, game, game.getPlayerQueue().getUsers().get(i));
                                            rebootHandler.reboot();
                                            Reboot reboot = new Reboot(game.getPlayerQueue().getUsers().get(i).getClientID());
                                            server.broadcast(reboot);
                                        }else{
                                            Movement movement = new Movement(game.getPlayerQueue().getUsers().get(i).getClientID(), game.getPlayerQueue().getUsers().get(i).getRobot().getPosition().getX(), game.getPlayerQueue().getUsers().get(i).getRobot().getPosition().getY() - 1);
                                            server.broadcast(movement);
                                        }
                                    } catch (IndexOutOfBoundsException e) {
                                        RebootHandler rebootHandler = new RebootHandler(server, game, game.getPlayerQueue().getUsers().get(i));
                                        rebootHandler.reboot();
                                        Reboot reboot = new Reboot(game.getPlayerQueue().getUsers().get(i).getClientID());
                                        server.broadcast(reboot);
                                    }
                                }else{
                                    for(int j = 0; j < i; j++){
                                        game.getPlayerQueue().getUsers().get(j).getRobot().setPosition(new Position(game.getPlayerQueue().getUsers().get(j).getRobot().getPosition().getX(), game.getPlayerQueue().getUsers().get(j).getRobot().getPosition().getY() + 1));
                                        Movement movement = new Movement(game.getPlayerQueue().getUsers().get(i).getClientID(), game.getPlayerQueue().getUsers().get(i).getRobot().getPosition().getX(), game.getPlayerQueue().getUsers().get(i).getRobot().getPosition().getY() + 1);
                                        server.broadcast(movement);
                                    }
                                    break;
                                }
                            }
                        }
                    }
                }else {
                    try {
                        robot.setPosition(new Position(x, y - 1));
                        if (movementCheck.fallingInPit(user,0,0)) {
                            RebootHandler rebootHandler = new RebootHandler(server, game, user);
                            rebootHandler.handle();
                            Reboot reboot = new Reboot(user.getClientID());
                            server.broadcast(reboot);
                        }else{
                            Movement movement = new Movement(user.getClientID(), x, y - 1);
                            server.broadcast(movement);
                        }
                    } catch (IndexOutOfBoundsException e) {
                        RebootHandler rebootHandler = new RebootHandler(server, game, user);
                        rebootHandler.reboot();
                        Reboot reboot = new Reboot(user.getClientID());
                        server.broadcast(reboot);
                    }
                }
            } else if (user.getRobot().getRobotOrientation() == Orientation.RIGHT) {
                Orientation orientationFirst = Orientation.LEFT;
                if (movementCheck.robotForwardCheck(game.getPlayerQueue().getUsers().get(0), game.getPlayerQueue().getUsers().get(1), orientationFirst, 1) && (!movementCheck.checkIfBlockedAlt(game.getPlayerQueue().getUsers().get(0).getRobot().getPosition(),orientationFirst,0))) {
                    for (int i = 1; i < game.getPlayerQueue().getUsers().size(); i++) {           //check if Players are neighbors - store them in extra list
                        if (movementCheck.checkIfBlockedAlt(game.getPlayerQueue().getUsers().get(i).getRobot().getPosition(), orientationFirst, 0)){
                            movementCheck.checkIfLastTwoAreNeighbors(game.getPlayerQueue().getUsers().get(i-1), game.getPlayerQueue().getUsers().get(i), Orientation.RIGHT, -1);
                            break;
                        }else{
                            movementCheck.checkIfLastTwoAreNeighbors(game.getPlayerQueue().getUsers().get(i-1), game.getPlayerQueue().getUsers().get(i), Orientation.RIGHT, -1);
                        }
                    }
                    if (movementCheck.checkIfBlockedAlt(movementCheck.getNeighbors().get(movementCheck.getNeighbors().size() - 1).getRobot().getPosition(), orientationFirst, 0)) {
                        //robot.setPosition(new Position(currentField.getX(), currentField.getY()));
                        for (int i = 0; i < game.getPlayerQueue().getUsers().size(); i++) {
                            if (movementCheck.getNeighbors().contains(game.getPlayerQueue().getUsers().get(i))) {
                                game.getPlayerQueue().getUsers().get(i).getRobot().setPosition(new Position(game.getPlayerQueue().getUsers().get(i).getRobot().getPosition().getX(), game.getPlayerQueue().getUsers().get(i).getRobot().getPosition().getY()));
                                Movement movement = new Movement(game.getPlayerQueue().getUsers().get(i).getClientID(), game.getPlayerQueue().getUsers().get(i).getRobot().getPosition().getX(), game.getPlayerQueue().getUsers().get(i).getRobot().getPosition().getY());
                                server.broadcast(movement);
                            }
                        }
                    } else {
                        for (int i = 0; i < game.getPlayerQueue().getUsers().size(); i++) {
                            if (movementCheck.getNeighbors().contains(game.getPlayerQueue().getUsers().get(i))) {
                                if(!(movementCheck.checkIfBlockedAlt(game.getPlayerQueue().getUsers().get(i).getRobot().getPosition(), orientationFirst, 0))) {
                                    try {
                                        game.getPlayerQueue().getUsers().get(i).getRobot().setPosition(new Position(game.getPlayerQueue().getUsers().get(i).getRobot().getPosition().getX() - 1, game.getPlayerQueue().getUsers().get(i).getRobot().getPosition().getY()));         //Flipped Directions
                                        if (movementCheck.fallingInPit(game.getPlayerQueue().getUsers().get(i),0,0)){
                                            RebootHandler rebootHandler = new RebootHandler(server, game, game.getPlayerQueue().getUsers().get(i));
                                            rebootHandler.reboot();
                                            Reboot reboot = new Reboot(game.getPlayerQueue().getUsers().get(i).getClientID());
                                            server.broadcast(reboot);
                                        }else{
                                            Movement movement = new Movement(game.getPlayerQueue().getUsers().get(i).getClientID(), game.getPlayerQueue().getUsers().get(i).getRobot().getPosition().getX() - 1, game.getPlayerQueue().getUsers().get(i).getRobot().getPosition().getY());
                                            server.broadcast(movement);
                                        }
                                    } catch (IndexOutOfBoundsException e) {
                                        RebootHandler rebootHandler = new RebootHandler(server, game, game.getPlayerQueue().getUsers().get(i));
                                        rebootHandler.reboot();
                                        Reboot reboot = new Reboot(game.getPlayerQueue().getUsers().get(i).getClientID());
                                        server.broadcast(reboot);
                                    }
                                }else{
                                    for(int j = 0; j < i; j++) {
                                        game.getPlayerQueue().getUsers().get(j).getRobot().setPosition(new Position(game.getPlayerQueue().getUsers().get(j).getRobot().getPosition().getX() + 1, game.getPlayerQueue().getUsers().get(j).getRobot().getPosition().getY()));
                                        Movement movement = new Movement(game.getPlayerQueue().getUsers().get(i).getClientID(), game.getPlayerQueue().getUsers().get(i).getRobot().getPosition().getX() + 1, game.getPlayerQueue().getUsers().get(i).getRobot().getPosition().getY());
                                        server.broadcast(movement);
                                    }
                                    break;
                                }
                            }
                        }
                    }
                }else{
                    try{
                        robot.setPosition(new Position(x - 1, y));
                        if (movementCheck.fallingInPit(user,0,0)) {
                            RebootHandler rebootHandler = new RebootHandler(server, game, user);
                            rebootHandler.reboot();
                            Reboot reboot = new Reboot(user.getClientID());
                            server.broadcast(reboot);
                        }else{
                            Movement movement = new Movement(user.getClientID(), x - 1, y);
                            server.broadcast(movement);
                        }
                    }catch (IndexOutOfBoundsException e){
                        RebootHandler rebootHandler = new RebootHandler(server, game, user);
                        rebootHandler.reboot();
                        Reboot reboot = new Reboot(user.getClientID());
                        server.broadcast(reboot);
                    }
                }
            }
        }
    }
}
