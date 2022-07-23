package bb.roborally.server.game.activation;


import bb.roborally.protocol.game_events.Movement;
import bb.roborally.protocol.game_events.Reboot;
import bb.roborally.server.Server;
import bb.roborally.server.game.*;
import java.io.IOException;


/**
 * @author Veronika Heckel
 */
public class Move2Handler {

    Server server;
    Game game;
    User user;

    public Move2Handler(Server server, Game game, User user) {
        this.server = server;
        this.game = game;
        this.user = user;
    }

    /**
     * Class manages the movements of  aRobot for two steps. It considers the Pt-Case and the Off-Board Case.
     * In the case of having multiple Robots in one row - the moving Robot is capable of pushing other Robots. Walls inf front of a Robot in the single- and multi- Robot-Moving case
     * are built in. Walls between neighboring Robots are also handled.
     * @throws IOException
     * @throws IndexOutOfBoundsException
     */
    public void handle() throws IOException, IndexOutOfBoundsException {
        Robot robot = user.getRobot();
        Position position = user.getRobot().getPosition();
        Orientation orientation = user.getRobot().getRobotOrientation();
        int x = position.getX();
        int y = position.getY();
        MovementCheck movementCheck = new MovementCheck(game.getBoard(), game);
        if (movementCheck.checkIfBlockedAlt(position, orientation, 0)) {                //check if a robot can move at all
            server.broadcast(new Movement(user.getClientID(), x, y));
        } else {
            if (robot.getRobotOrientation() == Orientation.TOP) {                           //if moving is possible --> iterating over all Robot-Orientations
                Position currentField = new Position(position.getX(), position.getY() - 1);             //current field is the field one step ahead of a Robot to determine if one step is possible --> if not jumping directly to the handling of one step
                if (!movementCheck.checkIfBlockedAlt(currentField, orientation, 0)) {
                    Orientation orientationFirst = Orientation.TOP;
                    if (movementCheck.robotForwardCheck(game.getPlayerQueue().getUsers().get(0), game.getPlayerQueue().getUsers().get(1), orientationFirst, 1) && (!movementCheck.checkIfBlockedAlt(game.getPlayerQueue().getUsers().get(0).getRobot().getPosition(),orientationFirst,0))) {
                        for (int i = 1; i < game.getPlayerQueue().getUsers().size(); i++) {           //check if Players are neighbors - store them in extra list
                            if (movementCheck.checkIfBlockedAlt(game.getPlayerQueue().getUsers().get(i).getRobot().getPosition(), orientationFirst, 0)){
                                movementCheck.checkIfLastTwoAreNeighbors(game.getPlayerQueue().getUsers().get(i-1), game.getPlayerQueue().getUsers().get(i), Orientation.BOTTOM, -1);
                                break;
                            }else{
                                if(movementCheck.checkIfBlockedAlt(game.getPlayerQueue().getUsers().get(i).getRobot().getPosition(), orientationFirst, 1)){
                                    movementCheck.checkIfLastTwoAreNeighbors(game.getPlayerQueue().getUsers().get(i-1), game.getPlayerQueue().getUsers().get(i), Orientation.BOTTOM, -1);
                                    break;
                                }else{
                                    movementCheck.checkIfLastTwoAreNeighbors(game.getPlayerQueue().getUsers().get(i-1), game.getPlayerQueue().getUsers().get(i), Orientation.BOTTOM, -1);
                                }
                            }
                        }

                        if (movementCheck.checkIfBlockedAlt(movementCheck.getNeighbors().get(movementCheck.getNeighbors().size() - 1).getRobot().getPosition(), orientationFirst, 0)) {         //check if last member of neighbors is blocked --> no movement - setting position on old position
                            for (int i = 0; i < game.getPlayerQueue().getUsers().size(); i++) {
                                if (movementCheck.getNeighbors().contains(game.getPlayerQueue().getUsers().get(i))) {
                                    game.getPlayerQueue().getUsers().get(i).getRobot().setPosition(new Position(game.getPlayerQueue().getUsers().get(i).getRobot().getPosition().getX(), game.getPlayerQueue().getUsers().get(i).getRobot().getPosition().getY()));
                                    server.broadcast(new Movement(game.getPlayerQueue().getUsers().get(i).getClientID(), game.getPlayerQueue().getUsers().get(i).getRobot().getPosition().getX(), game.getPlayerQueue().getUsers().get(i).getRobot().getPosition().getY()));
                                }
                            }
                        } else if (movementCheck.checkIfBlockedAlt(movementCheck.getNeighbors().get(movementCheck.getNeighbors().size() - 1).getRobot().getPosition(), orientationFirst, 1)&& (!movementCheck.fallingInPit(movementCheck.getNeighbors().get(movementCheck.getNeighbors().size()-1),0,-1))) {          //if the field one step ahead the last member is blocked --> only one move is possible
                            for (int i = 0; i < game.getPlayerQueue().getUsers().size(); i++) {                                                 //iterating over players-list, checking if they are also in the neighbors-list and set them one step ahead
                                if (movementCheck.getNeighbors().contains(game.getPlayerQueue().getUsers().get(i))) {
                                    if(!(movementCheck.checkIfBlockedAlt(game.getPlayerQueue().getUsers().get(i).getRobot().getPosition(), orientationFirst, 0))) {
                                        try {
                                            //try-catch-clause for handling Off-Board-case
                                            game.getPlayerQueue().getUsers().get(i).getRobot().setPosition(new Position(game.getPlayerQueue().getUsers().get(i).getRobot().getPosition().getX(), game.getPlayerQueue().getUsers().get(i).getRobot().getPosition().getY() - 1));
                                            if (!(movementCheck.fallingInPit(game.getPlayerQueue().getUsers().get(i),0,0))) {               //checking Pit case
                                                server.broadcast(new Movement(game.getPlayerQueue().getUsers().get(i).getClientID(), game.getPlayerQueue().getUsers().get(i).getRobot().getPosition().getX(), game.getPlayerQueue().getUsers().get(i).getRobot().getPosition().getY() - 1));
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
                                    }else{
                                        for(int j = 0; j < i; j++){                         //if there is a wall between two neighbors --> set all neighbors in front of it one step back, i is the last member in front of a wall, j is the iteration-variable for all members that need to step back
                                            game.getPlayerQueue().getUsers().get(j).getRobot().setPosition(new Position(game.getPlayerQueue().getUsers().get(j).getRobot().getPosition().getX(), game.getPlayerQueue().getUsers().get(j).getRobot().getPosition().getY() + 1));
                                            server.broadcast(new Movement(game.getPlayerQueue().getUsers().get(j).getClientID(), game.getPlayerQueue().getUsers().get(j).getRobot().getPosition().getX(), game.getPlayerQueue().getUsers().get(j).getRobot().getPosition().getY()));
                                        }
                                        break;
                                    }
                                }
                            }
                        } else {
                            for (int i = 0; i < game.getPlayerQueue().getUsers().size(); i++) {                 //if there is noc block in the range of two steps -->  all neighbors including the moving Robot go to steps ahead
                                if (movementCheck.getNeighbors().contains(game.getPlayerQueue().getUsers().get(i))) {
                                    if(!(movementCheck.checkIfBlockedAlt(game.getPlayerQueue().getUsers().get(i).getRobot().getPosition(), orientationFirst, 0))) {
                                        try {
                                            if(movementCheck.fallingInPit(game.getPlayerQueue().getUsers().get(i), 0, - 1)){
                                                game.getPlayerQueue().getUsers().get(i).getRobot().setPosition(new Position(game.getPlayerQueue().getUsers().get(i).getRobot().getPosition().getX(), game.getPlayerQueue().getUsers().get(i).getRobot().getPosition().getY() - 1)); //handling Off-Board --> Reboot
                                            }else {
                                                game.getPlayerQueue().getUsers().get(i).getRobot().setPosition(new Position(game.getPlayerQueue().getUsers().get(i).getRobot().getPosition().getX(), game.getPlayerQueue().getUsers().get(i).getRobot().getPosition().getY() - 2));
                                            }

                                            if (!(movementCheck.fallingInPit(game.getPlayerQueue().getUsers().get(i),0,0))) {                   //handling Pit
                                                server.broadcast(new Movement(game.getPlayerQueue().getUsers().get(i).getClientID(), game.getPlayerQueue().getUsers().get(i).getRobot().getPosition().getX(), game.getPlayerQueue().getUsers().get(i).getRobot().getPosition().getY() - 2));
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
                                    }else{
                                        for(int j = 0; j < i; j++){                 //hanling a wall between neighbors
                                            game.getPlayerQueue().getUsers().get(j).getRobot().setPosition(new Position(game.getPlayerQueue().getUsers().get(j).getRobot().getPosition().getX(), game.getPlayerQueue().getUsers().get(j).getRobot().getPosition().getY() + 2));
                                            server.broadcast(new Movement(game.getPlayerQueue().getUsers().get(j).getClientID(), game.getPlayerQueue().getUsers().get(j).getRobot().getPosition().getX(), game.getPlayerQueue().getUsers().get(j).getRobot().getPosition().getY()));
                                        }
                                        break;
                                    }
                                }
                            }
                        }
                    } else {                //if there are no neighbors only the moving Robot goes two steps ahead
                        // Move 2x
                        try {
                            robot.setPosition(new Position(x, y - 2));
                            if (!(movementCheck.fallingInPit(user,0,0))) {
                                server.broadcast(new Movement(user.getClientID(), x, y - 2));
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
                } else {            //if the blockage check shows one wall one step ahead of the Robot --> handling only one step
                    //only 1 step
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
                                    server.broadcast(new Movement(game.getPlayerQueue().getUsers().get(i).getClientID(), game.getPlayerQueue().getUsers().get(i).getRobot().getPosition().getX(), game.getPlayerQueue().getUsers().get(i).getRobot().getPosition().getY()));
                                }
                            }
                        } else {
                            for (int i = 0; i < game.getPlayerQueue().getUsers().size(); i++) {
                                if (movementCheck.getNeighbors().contains(game.getPlayerQueue().getUsers().get(i))) {
                                    if(!(movementCheck.checkIfBlockedAlt(game.getPlayerQueue().getUsers().get(i).getRobot().getPosition(), orientationFirst, 0))) {
                                        try {
                                            game.getPlayerQueue().getUsers().get(i).getRobot().setPosition(new Position(game.getPlayerQueue().getUsers().get(i).getRobot().getPosition().getX(), game.getPlayerQueue().getUsers().get(i).getRobot().getPosition().getY() - 1));
                                            if (!(movementCheck.fallingInPit(game.getPlayerQueue().getUsers().get(i),0,0))) {
                                                server.broadcast(new Movement(game.getPlayerQueue().getUsers().get(i).getClientID(), game.getPlayerQueue().getUsers().get(i).getRobot().getPosition().getX(), game.getPlayerQueue().getUsers().get(i).getRobot().getPosition().getY() - 1));
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
                    } else {
                        try {
                            robot.setPosition(new Position(x, y - 1));
                            if (!(movementCheck.fallingInPit(user,0,0))) {
                                server.broadcast(new Movement(user.getClientID(), x, y - 1));
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
            } else if (user.getRobot().getRobotOrientation() == Orientation.LEFT) {
                if (!movementCheck.checkIfBlockedAlt(position, orientation, 1)) {
                    Orientation orientationFirst = Orientation.LEFT;
                    if (movementCheck.robotForwardCheck(game.getPlayerQueue().getUsers().get(0), game.getPlayerQueue().getUsers().get(1), orientationFirst, 1) && (!movementCheck.checkIfBlockedAlt(game.getPlayerQueue().getUsers().get(0).getRobot().getPosition(),orientationFirst,0))) {
                        for (int i = 1; i < game.getPlayerQueue().getUsers().size(); i++) {           //check if Players are neighbors - store them in extra list
                            if (movementCheck.checkIfBlockedAlt(game.getPlayerQueue().getUsers().get(i).getRobot().getPosition(), orientationFirst, 0)){
                                movementCheck.checkIfLastTwoAreNeighbors(game.getPlayerQueue().getUsers().get(i-1), game.getPlayerQueue().getUsers().get(i), Orientation.RIGHT, -1);
                                break;
                            }else{
                                if(movementCheck.checkIfBlockedAlt(game.getPlayerQueue().getUsers().get(i).getRobot().getPosition(), orientationFirst, 1)){
                                    movementCheck.checkIfLastTwoAreNeighbors(game.getPlayerQueue().getUsers().get(i-1), game.getPlayerQueue().getUsers().get(i), Orientation.RIGHT, -1);
                                    break;
                                }else{
                                    movementCheck.checkIfLastTwoAreNeighbors(game.getPlayerQueue().getUsers().get(i-1), game.getPlayerQueue().getUsers().get(i), Orientation.RIGHT, -1);
                                }
                            }
                        }
                        if (movementCheck.checkIfBlockedAlt(movementCheck.getNeighbors().get(movementCheck.getNeighbors().size() - 1).getRobot().getPosition(), orientationFirst, 0)) {
                            //robot.setPosition(new Position(currentField.getX(), currentField.getY()));
                            for (int i = 0; i < game.getPlayerQueue().getUsers().size(); i++) {
                                if (movementCheck.getNeighbors().contains(game.getPlayerQueue().getUsers().get(i))) {
                                    game.getPlayerQueue().getUsers().get(i).getRobot().setPosition(new Position(game.getPlayerQueue().getUsers().get(i).getRobot().getPosition().getX(), game.getPlayerQueue().getUsers().get(i).getRobot().getPosition().getY()));
                                    server.broadcast(new Movement(game.getPlayerQueue().getUsers().get(i).getClientID(), game.getPlayerQueue().getUsers().get(i).getRobot().getPosition().getX(), game.getPlayerQueue().getUsers().get(i).getRobot().getPosition().getY()));
                                }
                            }
                        } else if (movementCheck.checkIfBlockedAlt(movementCheck.getNeighbors().get(movementCheck.getNeighbors().size() - 1).getRobot().getPosition(), orientationFirst, 1) && (!movementCheck.fallingInPit(movementCheck.getNeighbors().get(movementCheck.getNeighbors().size()-1),-1,0))) {
                            for (int i = 0; i < game.getPlayerQueue().getUsers().size(); i++) {
                                if (movementCheck.getNeighbors().contains(game.getPlayerQueue().getUsers().get(i))) {
                                    if(!(movementCheck.checkIfBlockedAlt(game.getPlayerQueue().getUsers().get(i).getRobot().getPosition(), orientationFirst, 0))) {
                                        try {
                                            game.getPlayerQueue().getUsers().get(i).getRobot().setPosition(new Position(game.getPlayerQueue().getUsers().get(i).getRobot().getPosition().getX() - 1, game.getPlayerQueue().getUsers().get(i).getRobot().getPosition().getY()));
                                            if (!(movementCheck.fallingInPit(game.getPlayerQueue().getUsers().get(i),0,0))) {
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
                                    }else{
                                        for(int j = 0; j < i; j++){
                                            game.getPlayerQueue().getUsers().get(j).getRobot().setPosition(new Position(game.getPlayerQueue().getUsers().get(j).getRobot().getPosition().getX() + 1, game.getPlayerQueue().getUsers().get(j).getRobot().getPosition().getY()));
                                            server.broadcast(new Movement(game.getPlayerQueue().getUsers().get(j).getClientID(), game.getPlayerQueue().getUsers().get(j).getRobot().getPosition().getX(), game.getPlayerQueue().getUsers().get(j).getRobot().getPosition().getY()));
                                        }
                                        break;
                                    }
                                }
                            }
                        } else {
                            for (int i = 0; i < game.getPlayerQueue().getUsers().size(); i++) {
                                if (movementCheck.getNeighbors().contains(game.getPlayerQueue().getUsers().get(i))) {
                                    if(!(movementCheck.checkIfBlockedAlt(game.getPlayerQueue().getUsers().get(i).getRobot().getPosition(), orientationFirst, 0))) {
                                        try {
                                            if(movementCheck.fallingInPit(game.getPlayerQueue().getUsers().get(i), -1,0)){
                                                game.getPlayerQueue().getUsers().get(i).getRobot().setPosition(new Position(game.getPlayerQueue().getUsers().get(i).getRobot().getPosition().getX() - 1, game.getPlayerQueue().getUsers().get(i).getRobot().getPosition().getY()));
                                            }else {
                                                game.getPlayerQueue().getUsers().get(i).getRobot().setPosition(new Position(game.getPlayerQueue().getUsers().get(i).getRobot().getPosition().getX() - 2, game.getPlayerQueue().getUsers().get(i).getRobot().getPosition().getY()));
                                            }

                                            if (!(movementCheck.fallingInPit(game.getPlayerQueue().getUsers().get(i),0,0))) {
                                                server.broadcast(new Movement(game.getPlayerQueue().getUsers().get(i).getClientID(), game.getPlayerQueue().getUsers().get(i).getRobot().getPosition().getX() - 2, game.getPlayerQueue().getUsers().get(i).getRobot().getPosition().getY()));
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
                                    }else{
                                        for(int j = 0; j < i; j++){
                                            game.getPlayerQueue().getUsers().get(j).getRobot().setPosition(new Position(game.getPlayerQueue().getUsers().get(j).getRobot().getPosition().getX() + 2 , game.getPlayerQueue().getUsers().get(j).getRobot().getPosition().getY()));
                                            server.broadcast(new Movement(game.getPlayerQueue().getUsers().get(j).getClientID(), game.getPlayerQueue().getUsers().get(j).getRobot().getPosition().getX(), game.getPlayerQueue().getUsers().get(j).getRobot().getPosition().getY()));
                                        }
                                        break;
                                    }
                                }
                            }
                        }
                    } else {
                        // Move2x
                        try {
                            robot.setPosition(new Position(x - 2, y));
                            if (!(movementCheck.fallingInPit(user,0,0))) {
                                server.broadcast(new Movement(user.getClientID(), x - 2, y));
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
                } else {
                    // Move only 1
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
                                            if (!(movementCheck.fallingInPit(game.getPlayerQueue().getUsers().get(i),0,0))) {
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
                                    }else{
                                        for(int j = 0; j < i; j++){
                                            game.getPlayerQueue().getUsers().get(j).getRobot().setPosition(new Position(game.getPlayerQueue().getUsers().get(j).getRobot().getPosition().getX() + 1, game.getPlayerQueue().getUsers().get(j).getRobot().getPosition().getY()));
                                            server.broadcast(new Movement(game.getPlayerQueue().getUsers().get(j).getClientID(), game.getPlayerQueue().getUsers().get(j).getRobot().getPosition().getX(), game.getPlayerQueue().getUsers().get(j).getRobot().getPosition().getY()));
                                        }
                                        break;
                                    }
                                }
                            }
                        }
                    }else {
                        try {
                            robot.setPosition(new Position(x - 1, y));
                            if (!(movementCheck.fallingInPit(user,0,0))) {
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
                }
            } else if (user.getRobot().getRobotOrientation() == Orientation.BOTTOM) {
                Position currentField = new Position(position.getX(), position.getY() + 1);
                if (!movementCheck.checkIfBlockedAlt(currentField, orientation, 0)) {
                    Orientation orientationFirst = Orientation.BOTTOM;
                    if (movementCheck.robotForwardCheck(game.getPlayerQueue().getUsers().get(0), game.getPlayerQueue().getUsers().get(1), orientationFirst, 1) && (!movementCheck.checkIfBlockedAlt(game.getPlayerQueue().getUsers().get(0).getRobot().getPosition(),orientationFirst,0))) {
                        for (int i = 1; i < game.getPlayerQueue().getUsers().size(); i++) {           //check if Players are neighbors - store them in extra list
                            if (movementCheck.checkIfBlockedAlt(game.getPlayerQueue().getUsers().get(i).getRobot().getPosition(), orientationFirst, 0)){
                                movementCheck.checkIfLastTwoAreNeighbors(game.getPlayerQueue().getUsers().get(i-1), game.getPlayerQueue().getUsers().get(i), Orientation.TOP, -1);
                                break;
                            }else{
                                if(movementCheck.checkIfBlockedAlt(game.getPlayerQueue().getUsers().get(i).getRobot().getPosition(), orientationFirst, 1)){
                                    movementCheck.checkIfLastTwoAreNeighbors(game.getPlayerQueue().getUsers().get(i-1), game.getPlayerQueue().getUsers().get(i), Orientation.TOP, -1);
                                    break;
                                }else{
                                    movementCheck.checkIfLastTwoAreNeighbors(game.getPlayerQueue().getUsers().get(i-1), game.getPlayerQueue().getUsers().get(i), Orientation.TOP, -1);
                                }
                            }
                        }
                        if (movementCheck.checkIfBlockedAlt(movementCheck.getNeighbors().get(movementCheck.getNeighbors().size() - 1).getRobot().getPosition(), orientationFirst, 0)){
                            for (int i = 0; i < game.getPlayerQueue().getUsers().size(); i++) {
                                if (movementCheck.getNeighbors().contains(game.getPlayerQueue().getUsers().get(i))) {
                                    game.getPlayerQueue().getUsers().get(i).getRobot().setPosition(new Position(game.getPlayerQueue().getUsers().get(i).getRobot().getPosition().getX(), game.getPlayerQueue().getUsers().get(i).getRobot().getPosition().getY()));
                                    server.broadcast(new Movement(game.getPlayerQueue().getUsers().get(i).getClientID(), game.getPlayerQueue().getUsers().get(i).getRobot().getPosition().getX(), game.getPlayerQueue().getUsers().get(i).getRobot().getPosition().getY()));
                                }
                            }
                        } else if (movementCheck.checkIfBlockedAlt(movementCheck.getNeighbors().get(movementCheck.getNeighbors().size() - 1).getRobot().getPosition(), orientationFirst, 1)&& (!movementCheck.fallingInPit(movementCheck.getNeighbors().get(movementCheck.getNeighbors().size()-1),0,1))) {
                            for (int i = 0; i < game.getPlayerQueue().getUsers().size(); i++) {
                                if (movementCheck.getNeighbors().contains(game.getPlayerQueue().getUsers().get(i))) {
                                    if(!(movementCheck.checkIfBlockedAlt(game.getPlayerQueue().getUsers().get(i).getRobot().getPosition(), orientationFirst, 0))) {
                                        try {
                                            game.getPlayerQueue().getUsers().get(i).getRobot().setPosition(new Position(game.getPlayerQueue().getUsers().get(i).getRobot().getPosition().getX(), game.getPlayerQueue().getUsers().get(i).getRobot().getPosition().getY() + 1));
                                            if (!(movementCheck.fallingInPit(game.getPlayerQueue().getUsers().get(i),0,0))) {
                                                server.broadcast(new Movement(game.getPlayerQueue().getUsers().get(i).getClientID(), game.getPlayerQueue().getUsers().get(i).getRobot().getPosition().getX(), game.getPlayerQueue().getUsers().get(i).getRobot().getPosition().getY() + 1));
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
                                    }else{
                                        for(int j = 0; j < i; j++){
                                            game.getPlayerQueue().getUsers().get(j).getRobot().setPosition(new Position(game.getPlayerQueue().getUsers().get(j).getRobot().getPosition().getX(), game.getPlayerQueue().getUsers().get(j).getRobot().getPosition().getY() - 1));
                                            server.broadcast(new Movement(game.getPlayerQueue().getUsers().get(j).getClientID(), game.getPlayerQueue().getUsers().get(j).getRobot().getPosition().getX(), game.getPlayerQueue().getUsers().get(j).getRobot().getPosition().getY()));
                                        }
                                        break;
                                    }
                                }
                            }
                        } else {
                            for (int i = 0; i < game.getPlayerQueue().getUsers().size(); i++) {
                                if (movementCheck.getNeighbors().contains(game.getPlayerQueue().getUsers().get(i))) {
                                    if(!(movementCheck.checkIfBlockedAlt(game.getPlayerQueue().getUsers().get(i).getRobot().getPosition(), orientationFirst, 0))) {
                                        try {
                                            if(movementCheck.fallingInPit(game.getPlayerQueue().getUsers().get(i), 0, 1)){
                                                game.getPlayerQueue().getUsers().get(i).getRobot().setPosition(new Position(game.getPlayerQueue().getUsers().get(i).getRobot().getPosition().getX(), game.getPlayerQueue().getUsers().get(i).getRobot().getPosition().getY() + 1));
                                            }else {
                                                game.getPlayerQueue().getUsers().get(i).getRobot().setPosition(new Position(game.getPlayerQueue().getUsers().get(i).getRobot().getPosition().getX(), game.getPlayerQueue().getUsers().get(i).getRobot().getPosition().getY() + 2));
                                            }

                                            if (!(movementCheck.fallingInPit(game.getPlayerQueue().getUsers().get(i),0,0))) {
                                                server.broadcast(new Movement(game.getPlayerQueue().getUsers().get(i).getClientID(), game.getPlayerQueue().getUsers().get(i).getRobot().getPosition().getX(), game.getPlayerQueue().getUsers().get(i).getRobot().getPosition().getY() + 2));
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
                                    }else{
                                        for(int j = 0; j < i; j++){
                                            game.getPlayerQueue().getUsers().get(j).getRobot().setPosition(new Position(game.getPlayerQueue().getUsers().get(j).getRobot().getPosition().getX(), game.getPlayerQueue().getUsers().get(j).getRobot().getPosition().getY() - 2));
                                            server.broadcast(new Movement(game.getPlayerQueue().getUsers().get(j).getClientID(), game.getPlayerQueue().getUsers().get(j).getRobot().getPosition().getX(), game.getPlayerQueue().getUsers().get(j).getRobot().getPosition().getY()));
                                        }
                                        break;
                                    }
                                }
                            }
                        }
                    } else {
                        // Move 2x
                        try {
                            robot.setPosition(new Position(x, y + 2));
                            if (!(movementCheck.fallingInPit(user,0,0))) {
                                server.broadcast(new Movement(user.getClientID(), x, y + 2));
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
                } else {
                    // Move only 1
                    Orientation orientationFirst = Orientation.BOTTOM;
                    if (movementCheck.robotForwardCheck(game.getPlayerQueue().getUsers().get(0), game.getPlayerQueue().getUsers().get(1), orientationFirst, 1) && (!movementCheck.checkIfBlockedAlt(game.getPlayerQueue().getUsers().get(0).getRobot().getPosition(),orientationFirst,0))) {
                        for (int i = 1; i < game.getPlayerQueue().getUsers().size(); i++) {           //check if Players are neighbors - store them in extra list
                            if (movementCheck.checkIfBlockedAlt(game.getPlayerQueue().getUsers().get(i).getRobot().getPosition(), orientationFirst, 0)){
                                movementCheck.checkIfLastTwoAreNeighbors(game.getPlayerQueue().getUsers().get(i-1), game.getPlayerQueue().getUsers().get(i), Orientation.TOP, -1);
                                break;
                            }else{
                                movementCheck.checkIfLastTwoAreNeighbors(game.getPlayerQueue().getUsers().get(i-1), game.getPlayerQueue().getUsers().get(i), Orientation.TOP, -1);
                            }
                        }
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
                                    if(!(movementCheck.checkIfBlockedAlt(game.getPlayerQueue().getUsers().get(i).getRobot().getPosition(), orientationFirst, 0))) {
                                        try {
                                            game.getPlayerQueue().getUsers().get(i).getRobot().setPosition(new Position(game.getPlayerQueue().getUsers().get(i).getRobot().getPosition().getX(), game.getPlayerQueue().getUsers().get(i).getRobot().getPosition().getY() + 1));
                                            if (!(movementCheck.fallingInPit(game.getPlayerQueue().getUsers().get(i),0,0))) {
                                                server.broadcast(new Movement(game.getPlayerQueue().getUsers().get(i).getClientID(), game.getPlayerQueue().getUsers().get(i).getRobot().getPosition().getX(), game.getPlayerQueue().getUsers().get(i).getRobot().getPosition().getY() + 1));
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
                                    }else{
                                        for(int j = 0; j < i; j++){
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
                            if (!(movementCheck.fallingInPit(user,0,0))) {
                                server.broadcast(new Movement(user.getClientID(), x, y + 1));
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
            } else if (user.getRobot().getRobotOrientation() == Orientation.RIGHT) {
                Position currentField = new Position(position.getX() + 1, position.getY());
                Orientation orientationFirst = Orientation.RIGHT;
                if (!movementCheck.checkIfBlockedAlt(currentField, orientation, 0)) {
                    if (movementCheck.robotForwardCheck(game.getPlayerQueue().getUsers().get(0), game.getPlayerQueue().getUsers().get(1), orientationFirst, 1) && (!movementCheck.checkIfBlockedAlt(game.getPlayerQueue().getUsers().get(0).getRobot().getPosition(),orientationFirst,0))) {
                        for (int i = 1; i < game.getPlayerQueue().getUsers().size(); i++) {           //check if Players are neighbors - store them in extra list
                            if (movementCheck.checkIfBlockedAlt(game.getPlayerQueue().getUsers().get(i).getRobot().getPosition(), orientationFirst, 0)){
                                movementCheck.checkIfLastTwoAreNeighbors(game.getPlayerQueue().getUsers().get(i-1), game.getPlayerQueue().getUsers().get(i), Orientation.LEFT, -1);
                                break;
                            }else{
                                if(movementCheck.checkIfBlockedAlt(game.getPlayerQueue().getUsers().get(i).getRobot().getPosition(), orientationFirst, 1)){
                                    movementCheck.checkIfLastTwoAreNeighbors(game.getPlayerQueue().getUsers().get(i-1), game.getPlayerQueue().getUsers().get(i), Orientation.LEFT, -1);
                                    break;
                                }else{
                                    movementCheck.checkIfLastTwoAreNeighbors(game.getPlayerQueue().getUsers().get(i-1), game.getPlayerQueue().getUsers().get(i), Orientation.LEFT, -1);
                                }
                            }
                        }

                        if (movementCheck.checkIfBlockedAlt(movementCheck.getNeighbors().get(movementCheck.getNeighbors().size() - 1).getRobot().getPosition(), orientationFirst, 0)) {
                            for (int i = 0; i < game.getPlayerQueue().getUsers().size(); i++) {
                                if (movementCheck.getNeighbors().contains(game.getPlayerQueue().getUsers().get(i))) {
                                    game.getPlayerQueue().getUsers().get(i).getRobot().setPosition(new Position(game.getPlayerQueue().getUsers().get(i).getRobot().getPosition().getX(), game.getPlayerQueue().getUsers().get(i).getRobot().getPosition().getY()));
                                    server.broadcast(new Movement(game.getPlayerQueue().getUsers().get(i).getClientID(), game.getPlayerQueue().getUsers().get(i).getRobot().getPosition().getX(), game.getPlayerQueue().getUsers().get(i).getRobot().getPosition().getY()));
                                }
                            }
                        } else if (movementCheck.checkIfBlockedAlt(movementCheck.getNeighbors().get(movementCheck.getNeighbors().size() - 1).getRobot().getPosition(), orientationFirst, 1)  && (!movementCheck.fallingInPit(movementCheck.getNeighbors().get(movementCheck.getNeighbors().size()-1),1,0))) {        //gehe nur einen, wenn auf dem übernächsten Feld eine Wand ist und auf dem nächsten Feld kein Pit
                            for (int i = 0; i < game.getPlayerQueue().getUsers().size(); i++) {
                                if (movementCheck.getNeighbors().contains(game.getPlayerQueue().getUsers().get(i))) {
                                    if(!(movementCheck.checkIfBlockedAlt(game.getPlayerQueue().getUsers().get(i).getRobot().getPosition(), orientationFirst, 0))) {
                                        try {
                                            game.getPlayerQueue().getUsers().get(i).getRobot().setPosition(new Position(game.getPlayerQueue().getUsers().get(i).getRobot().getPosition().getX() + 1, game.getPlayerQueue().getUsers().get(i).getRobot().getPosition().getY()));
                                            if (!(movementCheck.fallingInPit(game.getPlayerQueue().getUsers().get(i),0,0))) {
                                                server.broadcast(new Movement(game.getPlayerQueue().getUsers().get(i).getClientID(), game.getPlayerQueue().getUsers().get(i).getRobot().getPosition().getX() + 1, game.getPlayerQueue().getUsers().get(i).getRobot().getPosition().getY()));
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
                                    }else{
                                        for(int j = 0; j < i; j++){
                                            game.getPlayerQueue().getUsers().get(j).getRobot().setPosition(new Position(game.getPlayerQueue().getUsers().get(j).getRobot().getPosition().getX() - 1, game.getPlayerQueue().getUsers().get(j).getRobot().getPosition().getY()));
                                            server.broadcast(new Movement(game.getPlayerQueue().getUsers().get(j).getClientID(), game.getPlayerQueue().getUsers().get(j).getRobot().getPosition().getX(), game.getPlayerQueue().getUsers().get(j).getRobot().getPosition().getY()));
                                        }
                                        break;
                                    }
                                }
                            }
                        } else {
                            for (int i = 0; i < game.getPlayerQueue().getUsers().size(); i++) {
                                if (movementCheck.getNeighbors().contains(game.getPlayerQueue().getUsers().get(i))) {
                                    if(!(movementCheck.checkIfBlockedAlt(game.getPlayerQueue().getUsers().get(i).getRobot().getPosition(), orientationFirst, 0))) {
                                        try {
                                            if (movementCheck.fallingInPit(game.getPlayerQueue().getUsers().get(i),1,0)) {
                                            game.getPlayerQueue().getUsers().get(i).getRobot().setPosition(new Position(game.getPlayerQueue().getUsers().get(i).getRobot().getPosition().getX() + 1, game.getPlayerQueue().getUsers().get(i).getRobot().getPosition().getY()));
                                            }else{
                                                game.getPlayerQueue().getUsers().get(i).getRobot().setPosition(new Position(game.getPlayerQueue().getUsers().get(i).getRobot().getPosition().getX() + 2, game.getPlayerQueue().getUsers().get(i).getRobot().getPosition().getY()));
                                            }

                                            if (!movementCheck.fallingInPit(game.getPlayerQueue().getUsers().get(i), 0, 0)) {
                                                    server.broadcast(new Movement(game.getPlayerQueue().getUsers().get(i).getClientID(), game.getPlayerQueue().getUsers().get(i).getRobot().getPosition().getX() + 2, game.getPlayerQueue().getUsers().get(i).getRobot().getPosition().getY()));
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

                                    }else{
                                        for(int j = 0; j < i; j++){
                                            game.getPlayerQueue().getUsers().get(j).getRobot().setPosition(new Position(game.getPlayerQueue().getUsers().get(j).getRobot().getPosition().getX() - 2, game.getPlayerQueue().getUsers().get(j).getRobot().getPosition().getY()));
                                            server.broadcast(new Movement(game.getPlayerQueue().getUsers().get(j).getClientID(), game.getPlayerQueue().getUsers().get(j).getRobot().getPosition().getX(), game.getPlayerQueue().getUsers().get(j).getRobot().getPosition().getY()));
                                        }
                                        break;
                                    }
                                }
                            }
                        }
                    } else {
                        // Move 2x
                        try {
                            robot.setPosition(new Position(x + 2, y));
                            if (!(movementCheck.fallingInPit(user,0,0))) {
                                server.broadcast(new Movement(user.getClientID(), x + 2, y));
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
                } else {
                    // Move only 1
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
                                            game.getPlayerQueue().getUsers().get(i).getRobot().setPosition(new Position(game.getPlayerQueue().getUsers().get(i).getRobot().getPosition().getX() + 1, game.getPlayerQueue().getUsers().get(i).getRobot().getPosition().getY() + 1));
                                            if (!(movementCheck.fallingInPit(game.getPlayerQueue().getUsers().get(i),0,0))) {
                                                server.broadcast(new Movement(game.getPlayerQueue().getUsers().get(i).getClientID(), game.getPlayerQueue().getUsers().get(i).getRobot().getPosition().getX() + 1, game.getPlayerQueue().getUsers().get(i).getRobot().getPosition().getY() + 1));
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
                    } else {
                        try {
                            robot.setPosition(new Position(x + 1, y));
                            if (!(movementCheck.fallingInPit(user,0,0))) {
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
}







