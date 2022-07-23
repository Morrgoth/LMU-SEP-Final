package bb.roborally.server.game.activation;


import bb.roborally.protocol.game_events.Movement;
import bb.roborally.protocol.game_events.Reboot;
import bb.roborally.server.Server;
import bb.roborally.server.game.*;
import java.io.IOException;


/**
 * @author Veronika Heckel
 */
public class Move3Handler {

    Server server;
    Game game;
    User user;

    public Move3Handler(Server server, Game game, User user) {
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

    public void handleAlt() throws IOException {
        Robot robot = user.getRobot();
        Position position = user.getRobot().getPosition();
        Orientation orientation = user.getRobot().getRobotOrientation();
        int x = position.getX();
        int y = position.getY();
        MovementCheck movementCheck = new MovementCheck(game.getBoard(), game);
        if (movementCheck.checkIfBlockedAlt(position, orientation, 0)) {              // first initial check if one Robot can move
            server.broadcast(new Movement(user.getClientID(), x, y));
        } else {
            if (robot.getRobotOrientation() == Orientation.TOP) {

                Position currentField = new Position(position.getX(), position.getY() - 2);             //current field - is two steps ahead of Robot
                if (!movementCheck.checkIfBlockedAlt(currentField, orientation, 0)) {
                    // Move 3
                    Orientation orientationFirst = Orientation.TOP;             //if Movement is possible -- iterating over Robot Orientations
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
                                    if(movementCheck.checkIfBlockedAlt(game.getPlayerQueue().getUsers().get(i).getRobot().getPosition(), orientationFirst, 2) && (!movementCheck.fallingInPit(game.getPlayerQueue().getUsers().get(i), 0, - 2))){
                                        movementCheck.checkIfLastTwoAreNeighbors(game.getPlayerQueue().getUsers().get(i-1), game.getPlayerQueue().getUsers().get(i), Orientation.BOTTOM, -1);
                                        break;
                                    }else{
                                        movementCheck.checkIfLastTwoAreNeighbors(game.getPlayerQueue().getUsers().get(i-1), game.getPlayerQueue().getUsers().get(i), orientationFirst, 1);
                                    }
                                }
                            }
                        }

                        if (movementCheck.checkIfBlockedAlt(movementCheck.getNeighbors().get(movementCheck.getNeighbors().size() - 1).getRobot().getPosition(), orientationFirst, 0)) {             //check if all Neighbors are blocked (last neighbor is blocked -> no movement
                            for (int i = 0; i < game.getPlayerQueue().getUsers().size(); i++) {
                                if (movementCheck.getNeighbors().contains(game.getPlayerQueue().getUsers().get(i))) {
                                    game.getPlayerQueue().getUsers().get(i).getRobot().setPosition(new Position(game.getPlayerQueue().getUsers().get(i).getRobot().getPosition().getX(), game.getPlayerQueue().getUsers().get(i).getRobot().getPosition().getY()));
                                    server.broadcast(new Movement(game.getPlayerQueue().getUsers().get(i).getClientID(), game.getPlayerQueue().getUsers().get(i).getRobot().getPosition().getX(), game.getPlayerQueue().getUsers().get(i).getRobot().getPosition().getY()));
                                }
                            }
                        } else if (movementCheck.checkIfBlockedAlt(movementCheck.getNeighbors().get(movementCheck.getNeighbors().size() - 1).getRobot().getPosition(), orientationFirst, 1) && (!movementCheck.fallingInPit(movementCheck.getNeighbors().get(movementCheck.getNeighbors().size()-1),0,-1))) {          //if there is one field free ahead -->> all members go one step ahead
                            for (int i = 0; i < game.getPlayerQueue().getUsers().size(); i++) {
                                if (movementCheck.getNeighbors().contains(game.getPlayerQueue().getUsers().get(i))) {
                                    try {                                           //handling Off-board --> Reboot
                                        game.getPlayerQueue().getUsers().get(i).getRobot().setPosition(new Position(game.getPlayerQueue().getUsers().get(i).getRobot().getPosition().getX(), game.getPlayerQueue().getUsers().get(i).getRobot().getPosition().getY() - 1));
                                        if (!(movementCheck.fallingInPit(game.getPlayerQueue().getUsers().get(i), 0, 0))) {               //handling pit --> Reboot
                                            server.broadcast(new Movement(game.getPlayerQueue().getUsers().get(i).getClientID(), game.getPlayerQueue().getUsers().get(i).getRobot().getPosition().getX(), game.getPlayerQueue().getUsers().get(i).getRobot().getPosition().getY() - 1));
                                        }else{
                                            RebootHandler rebootHandler = new RebootHandler(server, game, game.getPlayerQueue().getUsers().get(i));
                                            rebootHandler.reboot();
                                            server.broadcast(new Reboot(game.getPlayerQueue().getUsers().get(i).getClientID()));
                                        }
                                    } catch (IndexOutOfBoundsException e) {
                                        RebootHandler rebootHandler = new RebootHandler(server, game, game.getPlayerQueue().getUsers().get(i));
                                        rebootHandler.reboot();
                                        server.broadcast(new Reboot(game.getPlayerQueue().getUsers().get(i).getClientID()));
                                    }
                                }
                            }
                        } else if (movementCheck.checkIfBlockedAlt(movementCheck.getNeighbors().get(movementCheck.getNeighbors().size() - 1).getRobot().getPosition(), orientationFirst, 2) && ((!movementCheck.fallingInPit(movementCheck.getNeighbors().get(movementCheck.getNeighbors().size()-1),0,-1)) ||(!movementCheck.fallingInPit(movementCheck.getNeighbors().get(movementCheck.getNeighbors().size()-1),0,-2)))) {          //if tehre is a wall two steps ahead of the last member --> all Robots move two steps ahead
                            for (int i = 0; i < game.getPlayerQueue().getUsers().size(); i++) {
                                if (movementCheck.getNeighbors().contains(game.getPlayerQueue().getUsers().get(i))) {
                                    try {
                                        game.getPlayerQueue().getUsers().get(i).getRobot().setPosition(new Position(game.getPlayerQueue().getUsers().get(i).getRobot().getPosition().getX(), game.getPlayerQueue().getUsers().get(i).getRobot().getPosition().getY() - 2));
                                        if (!(movementCheck.fallingInPit(game.getPlayerQueue().getUsers().get(i), 0, 0))) {
                                            server.broadcast(new Movement(game.getPlayerQueue().getUsers().get(i).getClientID(), game.getPlayerQueue().getUsers().get(i).getRobot().getPosition().getX(), game.getPlayerQueue().getUsers().get(i).getRobot().getPosition().getY() - 2));
                                        }else{
                                            RebootHandler rebootHandler = new RebootHandler(server, game, game.getPlayerQueue().getUsers().get(i));
                                            rebootHandler.reboot();
                                            server.broadcast(new Reboot(game.getPlayerQueue().getUsers().get(i).getClientID()));
                                        }
                                    } catch (IndexOutOfBoundsException e) {
                                        RebootHandler rebootHandler = new RebootHandler(server, game, game.getPlayerQueue().getUsers().get(i));
                                        rebootHandler.reboot();
                                        server.broadcast(new Reboot(game.getPlayerQueue().getUsers().get(i).getClientID()));
                                    }
                                }
                            }
                        } else {
                            for (int i = 0; i < game.getPlayerQueue().getUsers().size(); i++) {             //if three steps are possible --> go three stepss for all Robots
                                if (movementCheck.getNeighbors().contains(game.getPlayerQueue().getUsers().get(i))) {
                                    try {
                                        if(movementCheck.fallingInPit(game.getPlayerQueue().getUsers().get(i), 0,-1)){
                                            game.getPlayerQueue().getUsers().get(i).getRobot().setPosition(new Position(game.getPlayerQueue().getUsers().get(i).getRobot().getPosition().getX(), game.getPlayerQueue().getUsers().get(i).getRobot().getPosition().getY() - 1));
                                        }else if(movementCheck.fallingInPit(game.getPlayerQueue().getUsers().get(i), 0, -2)){
                                            game.getPlayerQueue().getUsers().get(i).getRobot().setPosition(new Position(game.getPlayerQueue().getUsers().get(i).getRobot().getPosition().getX(), game.getPlayerQueue().getUsers().get(i).getRobot().getPosition().getY() - 2));
                                        }else {
                                            game.getPlayerQueue().getUsers().get(i).getRobot().setPosition(new Position(game.getPlayerQueue().getUsers().get(i).getRobot().getPosition().getX(), game.getPlayerQueue().getUsers().get(i).getRobot().getPosition().getY() - 3));
                                        }

                                        if (!(movementCheck.fallingInPit(game.getPlayerQueue().getUsers().get(i), 0, 0))) {
                                            server.broadcast(new Movement(game.getPlayerQueue().getUsers().get(i).getClientID(), game.getPlayerQueue().getUsers().get(i).getRobot().getPosition().getX(), game.getPlayerQueue().getUsers().get(i).getRobot().getPosition().getY() - 3));
                                        }else{
                                            RebootHandler rebootHandler = new RebootHandler(server, game, game.getPlayerQueue().getUsers().get(i));
                                            rebootHandler.reboot();
                                            server.broadcast(new Reboot(game.getPlayerQueue().getUsers().get(i).getClientID()));
                                        }
                                    } catch (IndexOutOfBoundsException e) {
                                        RebootHandler rebootHandler = new RebootHandler(server, game, game.getPlayerQueue().getUsers().get(i));
                                        rebootHandler.reboot();
                                        server.broadcast(new Reboot(game.getPlayerQueue().getUsers().get(i).getClientID()));
                                    }
                                }
                            }
                        }
                    } else {
                        robot.setPosition(new Position(x, y - 3));
                        try {
                            if (!(movementCheck.fallingInPit(user, 0, 0))){
                                server.broadcast(new Movement(user.getClientID(), x, y - 3));
                            }else{
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
                } else if (!movementCheck.checkIfBlockedAlt(new Position(position.getX(), position.getY() - 1), orientation, 0)) {
                    //Move only 2
                    currentField = new Position(position.getX(), position.getY() - 1);
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
                                        movementCheck.checkIfLastTwoAreNeighbors(game.getPlayerQueue().getUsers().get(i-1), game.getPlayerQueue().getUsers().get(i), orientationFirst, 1);
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
                            } else if (movementCheck.checkIfBlockedAlt(movementCheck.getNeighbors().get(movementCheck.getNeighbors().size() - 1).getRobot().getPosition(), orientationFirst, 1) && (!movementCheck.fallingInPit(movementCheck.getNeighbors().get(movementCheck.getNeighbors().size()-1),0,-1))) {
                                for (int i = 0; i < game.getPlayerQueue().getUsers().size(); i++) {
                                    if (movementCheck.getNeighbors().contains(game.getPlayerQueue().getUsers().get(i))) {
                                        try {
                                            game.getPlayerQueue().getUsers().get(i).getRobot().setPosition(new Position(game.getPlayerQueue().getUsers().get(i).getRobot().getPosition().getX(), game.getPlayerQueue().getUsers().get(i).getRobot().getPosition().getY() - 1));
                                            if (!(movementCheck.fallingInPit(game.getPlayerQueue().getUsers().get(i), 0, 0))) {
                                                server.broadcast(new Movement(game.getPlayerQueue().getUsers().get(i).getClientID(), game.getPlayerQueue().getUsers().get(i).getRobot().getPosition().getX(), game.getPlayerQueue().getUsers().get(i).getRobot().getPosition().getY() - 1));
                                            }else{
                                                RebootHandler rebootHandler = new RebootHandler(server, game, game.getPlayerQueue().getUsers().get(i));
                                                rebootHandler.reboot();
                                                server.broadcast(new Reboot(game.getPlayerQueue().getUsers().get(i).getClientID()));
                                            }
                                        } catch (IndexOutOfBoundsException e) {
                                            RebootHandler rebootHandler = new RebootHandler(server, game, game.getPlayerQueue().getUsers().get(i));
                                            rebootHandler.reboot();
                                            server.broadcast(new Reboot(game.getPlayerQueue().getUsers().get(i).getClientID()));
                                        }
                                    }
                                }
                            } else {
                                for (int i = 0; i < game.getPlayerQueue().getUsers().size(); i++) {
                                    if (movementCheck.getNeighbors().contains(game.getPlayerQueue().getUsers().get(i))) {
                                        try {
                                            if(movementCheck.fallingInPit(game.getPlayerQueue().getUsers().get(i), 0,-1)){
                                                game.getPlayerQueue().getUsers().get(i).getRobot().setPosition(new Position(game.getPlayerQueue().getUsers().get(i).getRobot().getPosition().getX(), game.getPlayerQueue().getUsers().get(i).getRobot().getPosition().getY() - 1));
                                            }else {
                                                game.getPlayerQueue().getUsers().get(i).getRobot().setPosition(new Position(game.getPlayerQueue().getUsers().get(i).getRobot().getPosition().getX(), game.getPlayerQueue().getUsers().get(i).getRobot().getPosition().getY() - 2));
                                            }
                                            if (!(movementCheck.fallingInPit(game.getPlayerQueue().getUsers().get(i), 0, 0))) {
                                                server.broadcast(new Movement(game.getPlayerQueue().getUsers().get(i).getClientID(), game.getPlayerQueue().getUsers().get(i).getRobot().getPosition().getX(), game.getPlayerQueue().getUsers().get(i).getRobot().getPosition().getY() - 2));
                                            }else{
                                                RebootHandler rebootHandler = new RebootHandler(server, game, user);
                                                rebootHandler.reboot();
                                                server.broadcast(new Reboot(game.getPlayerQueue().getUsers().get(i).getClientID()));
                                            }
                                        } catch (IndexOutOfBoundsException e) {
                                            RebootHandler rebootHandler = new RebootHandler(server, game, user);
                                            rebootHandler.reboot();
                                            server.broadcast(new Reboot(game.getPlayerQueue().getUsers().get(i).getClientID()));
                                        }
                                    }
                                }
                            }
                        } else {
                            // Move 2x
                            robot.setPosition(new Position(x, y - 2));
                            try {
                                if (!(movementCheck.fallingInPit(user, 0, 0))){
                                    server.broadcast(new Movement(user.getClientID(), x, y - 2));
                                }else{
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
                                        try {
                                            game.getPlayerQueue().getUsers().get(i).getRobot().setPosition(new Position(game.getPlayerQueue().getUsers().get(i).getRobot().getPosition().getX(), game.getPlayerQueue().getUsers().get(i).getRobot().getPosition().getY() - 1));
                                            if (!(movementCheck.fallingInPit(game.getPlayerQueue().getUsers().get(i), 0, 0))) {
                                                server.broadcast(new Movement(game.getPlayerQueue().getUsers().get(i).getClientID(), game.getPlayerQueue().getUsers().get(i).getRobot().getPosition().getX(), game.getPlayerQueue().getUsers().get(i).getRobot().getPosition().getY() - 1));
                                            }else{
                                                RebootHandler rebootHandler = new RebootHandler(server, game, game.getPlayerQueue().getUsers().get(i));
                                                rebootHandler.reboot();
                                                server.broadcast(new Reboot(game.getPlayerQueue().getUsers().get(i).getClientID()));
                                            }
                                        } catch (IndexOutOfBoundsException e) {
                                            RebootHandler rebootHandler = new RebootHandler(server, game, game.getPlayerQueue().getUsers().get(i));
                                            rebootHandler.reboot();
                                            server.broadcast(new Reboot(game.getPlayerQueue().getUsers().get(i).getClientID()));
                                        }
                                    }
                                }
                            }
                        } else {
                            robot.setPosition(new Position(x, y - 1));
                            try {
                                if (!(movementCheck.fallingInPit(user, 0, 0))){
                                    server.broadcast(new Movement(user.getClientID(), x, y - 1));
                                }else{
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
            }else if (user.getRobot().getRobotOrientation() == Orientation.LEFT) {
                Position currentField;
                //Move 3
                if (!movementCheck.checkIfBlockedAlt(new Position(position.getX() - 2, position.getY()), orientation, 0)) {
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
                                    if(movementCheck.checkIfBlockedAlt(game.getPlayerQueue().getUsers().get(i).getRobot().getPosition(), orientationFirst, 2) && (!movementCheck.fallingInPit(game.getPlayerQueue().getUsers().get(i), -2,0))){
                                        movementCheck.checkIfLastTwoAreNeighbors(game.getPlayerQueue().getUsers().get(i-1), game.getPlayerQueue().getUsers().get(i), Orientation.RIGHT, -1);
                                        break;
                                    }else{
                                        movementCheck.checkIfLastTwoAreNeighbors(game.getPlayerQueue().getUsers().get(i-1), game.getPlayerQueue().getUsers().get(i), orientationFirst, 1);
                                    }
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
                                    try{
                                        game.getPlayerQueue().getUsers().get(i).getRobot().setPosition(new Position(game.getPlayerQueue().getUsers().get(i).getRobot().getPosition().getX() - 1, game.getPlayerQueue().getUsers().get(i).getRobot().getPosition().getY()));
                                        if (!(movementCheck.fallingInPit(game.getPlayerQueue().getUsers().get(i), 0, 0))) {
                                            server.broadcast(new Movement(game.getPlayerQueue().getUsers().get(i).getClientID(), game.getPlayerQueue().getUsers().get(i).getRobot().getPosition().getX() - 1, game.getPlayerQueue().getUsers().get(i).getRobot().getPosition().getY()));
                                        }else{
                                            RebootHandler rebootHandler = new RebootHandler(server, game, game.getPlayerQueue().getUsers().get(i));
                                            rebootHandler.reboot();
                                            server.broadcast(new Reboot(game.getPlayerQueue().getUsers().get(i).getClientID()));
                                        }
                                    } catch (IndexOutOfBoundsException e) {
                                        RebootHandler rebootHandler = new RebootHandler(server, game, game.getPlayerQueue().getUsers().get(i));
                                        rebootHandler.reboot();
                                        server.broadcast(new Reboot(game.getPlayerQueue().getUsers().get(i).getClientID()));
                                    }
                                }
                            }
                        } else if (movementCheck.checkIfBlockedAlt(movementCheck.getNeighbors().get(movementCheck.getNeighbors().size() - 1).getRobot().getPosition(), orientationFirst, 2) && ((!movementCheck.fallingInPit(movementCheck.getNeighbors().get(movementCheck.getNeighbors().size()-1),-1,0)) || (!movementCheck.fallingInPit(movementCheck.getNeighbors().get(movementCheck.getNeighbors().size()-1),-2,0)))) {
                            for (int i = 0; i < game.getPlayerQueue().getUsers().size(); i++) {
                                if (movementCheck.getNeighbors().contains(game.getPlayerQueue().getUsers().get(i))) {
                                    try{
                                        game.getPlayerQueue().getUsers().get(i).getRobot().setPosition(new Position(game.getPlayerQueue().getUsers().get(i).getRobot().getPosition().getX() - 2, game.getPlayerQueue().getUsers().get(i).getRobot().getPosition().getY()));
                                        if (!(movementCheck.fallingInPit(game.getPlayerQueue().getUsers().get(i), 0, 0))) {
                                            server.broadcast(new Movement(game.getPlayerQueue().getUsers().get(i).getClientID(), game.getPlayerQueue().getUsers().get(i).getRobot().getPosition().getX() - 2, game.getPlayerQueue().getUsers().get(i).getRobot().getPosition().getY()));
                                        }else{
                                            RebootHandler rebootHandler = new RebootHandler(server, game, game.getPlayerQueue().getUsers().get(i));
                                            rebootHandler.reboot();
                                            server.broadcast(new Reboot(game.getPlayerQueue().getUsers().get(i).getClientID()));
                                        }
                                    } catch (IndexOutOfBoundsException e) {
                                        RebootHandler rebootHandler = new RebootHandler(server, game, game.getPlayerQueue().getUsers().get(i));
                                        rebootHandler.reboot();
                                        server.broadcast(new Reboot(game.getPlayerQueue().getUsers().get(i).getClientID()));
                                    }
                                }
                            }
                        } else {
                            for (int i = 0; i < game.getPlayerQueue().getUsers().size(); i++) {
                                if (movementCheck.getNeighbors().contains(game.getPlayerQueue().getUsers().get(i))) {
                                    try{
                                        if(movementCheck.fallingInPit(game.getPlayerQueue().getUsers().get(i), -1, 0)){
                                            game.getPlayerQueue().getUsers().get(i).getRobot().setPosition(new Position(game.getPlayerQueue().getUsers().get(i).getRobot().getPosition().getX() - 1, game.getPlayerQueue().getUsers().get(i).getRobot().getPosition().getY()));
                                        }else if(movementCheck.fallingInPit(game.getPlayerQueue().getUsers().get(i), -2,0)){
                                            game.getPlayerQueue().getUsers().get(i).getRobot().setPosition(new Position(game.getPlayerQueue().getUsers().get(i).getRobot().getPosition().getX() - 2, game.getPlayerQueue().getUsers().get(i).getRobot().getPosition().getY()));
                                        }else {
                                            game.getPlayerQueue().getUsers().get(i).getRobot().setPosition(new Position(game.getPlayerQueue().getUsers().get(i).getRobot().getPosition().getX() - 3, game.getPlayerQueue().getUsers().get(i).getRobot().getPosition().getY()));
                                        }

                                        if (!(movementCheck.fallingInPit(game.getPlayerQueue().getUsers().get(i), 0, 0))) {
                                            server.broadcast(new Movement(game.getPlayerQueue().getUsers().get(i).getClientID(), game.getPlayerQueue().getUsers().get(i).getRobot().getPosition().getX() - 3, game.getPlayerQueue().getUsers().get(i).getRobot().getPosition().getY()));
                                        }else{
                                            RebootHandler rebootHandler = new RebootHandler(server, game, game.getPlayerQueue().getUsers().get(i));
                                            rebootHandler.reboot();
                                            server.broadcast(new Reboot(game.getPlayerQueue().getUsers().get(i).getClientID()));
                                        }
                                    } catch (IndexOutOfBoundsException e) {
                                        RebootHandler rebootHandler = new RebootHandler(server, game, game.getPlayerQueue().getUsers().get(i));
                                        rebootHandler.reboot();
                                        server.broadcast(new Reboot(game.getPlayerQueue().getUsers().get(i).getClientID()));
                                    }
                                }
                            }
                        }
                    } else {
                        try{
                            robot.setPosition(new Position(x - 3, y));
                            if (!(movementCheck.fallingInPit(user, 0, 0))) {
                                server.broadcast(new Movement(user.getClientID(), x - 3, y));
                            }else{
                                RebootHandler rebootHandler = new RebootHandler(server, game, user);
                                rebootHandler.reboot();
                                server.broadcast(new Reboot(user.getClientID()));
                            }
                        }catch(IndexOutOfBoundsException e){
                            RebootHandler rebootHandler = new RebootHandler(server, game, user);
                            rebootHandler.reboot();
                            server.broadcast(new Reboot(user.getClientID()));
                        }
                    }
                } else if (!movementCheck.checkIfBlockedAlt(new Position(position.getX() - 1, position.getY()), orientation, 0)) {
                    //Move only 2
                    currentField = new Position(position.getX(), position.getY() - 1);
                    if (!movementCheck.checkIfBlockedAlt(currentField, orientation, 0)) {
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
                                        movementCheck.checkIfLastTwoAreNeighbors(game.getPlayerQueue().getUsers().get(i-1), game.getPlayerQueue().getUsers().get(i), orientationFirst, 1);
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
                                        try{
                                            game.getPlayerQueue().getUsers().get(i).getRobot().setPosition(new Position(game.getPlayerQueue().getUsers().get(i).getRobot().getPosition().getX() - 1, game.getPlayerQueue().getUsers().get(i).getRobot().getPosition().getY()));
                                            if (!(movementCheck.fallingInPit(game.getPlayerQueue().getUsers().get(i), 0, 0))) {
                                                server.broadcast(new Movement(game.getPlayerQueue().getUsers().get(i).getClientID(), game.getPlayerQueue().getUsers().get(i).getRobot().getPosition().getX() - 1, game.getPlayerQueue().getUsers().get(i).getRobot().getPosition().getY()));
                                            }else{
                                                RebootHandler rebootHandler = new RebootHandler(server, game, game.getPlayerQueue().getUsers().get(i));
                                                rebootHandler.reboot();
                                                server.broadcast(new Reboot(game.getPlayerQueue().getUsers().get(i).getClientID()));
                                            }
                                        } catch (IndexOutOfBoundsException e) {
                                            RebootHandler rebootHandler = new RebootHandler(server, game, game.getPlayerQueue().getUsers().get(i));
                                            rebootHandler.reboot();
                                            server.broadcast(new Reboot(game.getPlayerQueue().getUsers().get(i).getClientID()));
                                        }
                                    }
                                }
                            } else {
                                for (int i = 0; i < game.getPlayerQueue().getUsers().size(); i++) {
                                    if (movementCheck.getNeighbors().contains(game.getPlayerQueue().getUsers().get(i))) {
                                        try{
                                            if(movementCheck.fallingInPit(game.getPlayerQueue().getUsers().get(i), -1,0)){
                                                game.getPlayerQueue().getUsers().get(i).getRobot().setPosition(new Position(game.getPlayerQueue().getUsers().get(i).getRobot().getPosition().getX() - 1, game.getPlayerQueue().getUsers().get(i).getRobot().getPosition().getY()));
                                            }else {
                                                game.getPlayerQueue().getUsers().get(i).getRobot().setPosition(new Position(game.getPlayerQueue().getUsers().get(i).getRobot().getPosition().getX() - 2, game.getPlayerQueue().getUsers().get(i).getRobot().getPosition().getY()));
                                            }
                                            if (!(movementCheck.fallingInPit(game.getPlayerQueue().getUsers().get(i), 0, 0))) {
                                                server.broadcast(new Movement(game.getPlayerQueue().getUsers().get(i).getClientID(), game.getPlayerQueue().getUsers().get(i).getRobot().getPosition().getX() - 2, game.getPlayerQueue().getUsers().get(i).getRobot().getPosition().getY()));
                                            }else{
                                                RebootHandler rebootHandler = new RebootHandler(server, game, game.getPlayerQueue().getUsers().get(i));
                                                rebootHandler.reboot();
                                                server.broadcast(new Reboot(game.getPlayerQueue().getUsers().get(i).getClientID()));
                                            }
                                        } catch (IndexOutOfBoundsException e) {
                                            RebootHandler rebootHandler = new RebootHandler(server, game, game.getPlayerQueue().getUsers().get(i));
                                            rebootHandler.reboot();
                                            server.broadcast(new Reboot(game.getPlayerQueue().getUsers().get(i).getClientID()));
                                        }
                                    }
                                }
                            }
                        } else {
                            // Move 2x
                            try{
                                robot.setPosition(new Position(x - 2, y));
                                if (!(movementCheck.fallingInPit(user, 0, 0))) {
                                    server.broadcast(new Movement(user.getClientID(), x - 2, y));
                                }else{
                                    RebootHandler rebootHandler = new RebootHandler(server, game, user);
                                    rebootHandler.reboot();
                                    server.broadcast(new Reboot(user.getClientID()));
                                }
                            }catch(IndexOutOfBoundsException e){
                                RebootHandler rebootHandler = new RebootHandler(server, game, user);
                                rebootHandler.reboot();
                                server.broadcast(new Reboot(user.getClientID()));
                            }
                        }
                    } else {
                        // Move only 1
                        Orientation orientationFirst = Orientation.LEFT;
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
                                        try{
                                            game.getPlayerQueue().getUsers().get(i).getRobot().setPosition(new Position(game.getPlayerQueue().getUsers().get(i).getRobot().getPosition().getX() - 1, game.getPlayerQueue().getUsers().get(i).getRobot().getPosition().getY()));
                                            if (!(movementCheck.fallingInPit(game.getPlayerQueue().getUsers().get(i), 0, 0))) {
                                                server.broadcast(new Movement(game.getPlayerQueue().getUsers().get(i).getClientID(), game.getPlayerQueue().getUsers().get(i).getRobot().getPosition().getX() - 1, game.getPlayerQueue().getUsers().get(i).getRobot().getPosition().getY()));
                                            }else{
                                                RebootHandler rebootHandler = new RebootHandler(server, game, game.getPlayerQueue().getUsers().get(i));
                                                rebootHandler.reboot();
                                                server.broadcast(new Reboot(game.getPlayerQueue().getUsers().get(i).getClientID()));
                                            }
                                        } catch (IndexOutOfBoundsException e) {
                                            RebootHandler rebootHandler = new RebootHandler(server, game, game.getPlayerQueue().getUsers().get(i));
                                            rebootHandler.reboot();
                                            server.broadcast(new Reboot(game.getPlayerQueue().getUsers().get(i).getClientID()));
                                        }
                                    }
                                }
                            }
                        } else {
                            try{
                                robot.setPosition(new Position(x - 1, y));
                                if (!(movementCheck.fallingInPit(user, 0, 0))) {
                                    server.broadcast(new Movement(user.getClientID(), x - 1, y));
                                }else{
                                    RebootHandler rebootHandler = new RebootHandler(server, game, user);
                                    rebootHandler.reboot();
                                    server.broadcast(new Reboot(user.getClientID()));
                                }
                            }catch(IndexOutOfBoundsException e){
                                RebootHandler rebootHandler = new RebootHandler(server, game, user);
                                rebootHandler.reboot();
                                server.broadcast(new Reboot(user.getClientID()));
                            }
                        }
                    }
                }
            } else if (user.getRobot().getRobotOrientation() == Orientation.BOTTOM) {
                Position currentField;
                if (!movementCheck.checkIfBlockedAlt(new Position(position.getX(), position.getY() + 2), orientation, 0)) {
                    // Move 3
                    Orientation orientationFirst = Orientation.LEFT;
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
                                    if(movementCheck.checkIfBlockedAlt(game.getPlayerQueue().getUsers().get(i).getRobot().getPosition(), orientationFirst, 2) && (!movementCheck.fallingInPit(game.getPlayerQueue().getUsers().get(i), 0,  2))){
                                        movementCheck.checkIfLastTwoAreNeighbors(game.getPlayerQueue().getUsers().get(i-1), game.getPlayerQueue().getUsers().get(i), Orientation.TOP, -1);
                                        break;
                                    }else{
                                        movementCheck.checkIfLastTwoAreNeighbors(game.getPlayerQueue().getUsers().get(i-1), game.getPlayerQueue().getUsers().get(i), orientationFirst, 1);
                                    }
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
                        } else if (movementCheck.checkIfBlockedAlt(movementCheck.getNeighbors().get(movementCheck.getNeighbors().size() - 1).getRobot().getPosition(), orientationFirst, 1) && (!movementCheck.fallingInPit(movementCheck.getNeighbors().get(movementCheck.getNeighbors().size()-1),0,1))) {
                            for (int i = 0; i < game.getPlayerQueue().getUsers().size(); i++) {
                                if (movementCheck.getNeighbors().contains(game.getPlayerQueue().getUsers().get(i))) {
                                    try {
                                        game.getPlayerQueue().getUsers().get(i).getRobot().setPosition(new Position(game.getPlayerQueue().getUsers().get(i).getRobot().getPosition().getX(), game.getPlayerQueue().getUsers().get(i).getRobot().getPosition().getY() + 1));
                                        if (!(movementCheck.fallingInPit(game.getPlayerQueue().getUsers().get(i), 0, 0))) {
                                            server.broadcast(new Movement(game.getPlayerQueue().getUsers().get(i).getClientID(), game.getPlayerQueue().getUsers().get(i).getRobot().getPosition().getX(), game.getPlayerQueue().getUsers().get(i).getRobot().getPosition().getY() + 1));
                                        }else{
                                            RebootHandler rebootHandler = new RebootHandler(server, game, game.getPlayerQueue().getUsers().get(i));
                                            rebootHandler.reboot();
                                            server.broadcast(new Reboot(game.getPlayerQueue().getUsers().get(i).getClientID()));
                                        }
                                    } catch (IndexOutOfBoundsException e) {
                                        RebootHandler rebootHandler = new RebootHandler(server, game, game.getPlayerQueue().getUsers().get(i));
                                        rebootHandler.reboot();
                                        server.broadcast(new Reboot(game.getPlayerQueue().getUsers().get(i).getClientID()));
                                    }
                                }
                            }
                        } else if (movementCheck.checkIfBlockedAlt(movementCheck.getNeighbors().get(movementCheck.getNeighbors().size() - 1).getRobot().getPosition(), orientationFirst, 2) && ((!movementCheck.fallingInPit(movementCheck.getNeighbors().get(movementCheck.getNeighbors().size()-1),0,1)) || (!movementCheck.fallingInPit(movementCheck.getNeighbors().get(movementCheck.getNeighbors().size()-1),0,2)))) {
                            for (int i = 0; i < game.getPlayerQueue().getUsers().size(); i++) {
                                if (movementCheck.getNeighbors().contains(game.getPlayerQueue().getUsers().get(i))) {
                                    try {
                                        game.getPlayerQueue().getUsers().get(i).getRobot().setPosition(new Position(game.getPlayerQueue().getUsers().get(i).getRobot().getPosition().getX(), game.getPlayerQueue().getUsers().get(i).getRobot().getPosition().getY() + 2));
                                        if (!(movementCheck.fallingInPit(game.getPlayerQueue().getUsers().get(i), 0, 0))) {
                                            server.broadcast(new Movement(game.getPlayerQueue().getUsers().get(i).getClientID(), game.getPlayerQueue().getUsers().get(i).getRobot().getPosition().getX(), game.getPlayerQueue().getUsers().get(i).getRobot().getPosition().getY() + 2));
                                        }else{
                                            RebootHandler rebootHandler = new RebootHandler(server, game, game.getPlayerQueue().getUsers().get(i));
                                            rebootHandler.reboot();
                                            server.broadcast(new Reboot(game.getPlayerQueue().getUsers().get(i).getClientID()));
                                        }
                                    } catch (IndexOutOfBoundsException e) {
                                        RebootHandler rebootHandler = new RebootHandler(server, game, game.getPlayerQueue().getUsers().get(i));
                                        rebootHandler.reboot();
                                        server.broadcast(new Reboot(game.getPlayerQueue().getUsers().get(i).getClientID()));
                                    }
                                }
                            }
                        } else {
                            for (int i = 0; i < game.getPlayerQueue().getUsers().size(); i++) {
                                if (movementCheck.getNeighbors().contains(game.getPlayerQueue().getUsers().get(i))) {
                                    try {
                                        if(movementCheck.fallingInPit(game.getPlayerQueue().getUsers().get(i), 0, 1)){
                                            game.getPlayerQueue().getUsers().get(i).getRobot().setPosition(new Position(game.getPlayerQueue().getUsers().get(i).getRobot().getPosition().getX(), game.getPlayerQueue().getUsers().get(i).getRobot().getPosition().getY() + 1));
                                        }else if(movementCheck.fallingInPit(game.getPlayerQueue().getUsers().get(i), 0,1)){
                                            game.getPlayerQueue().getUsers().get(i).getRobot().setPosition(new Position(game.getPlayerQueue().getUsers().get(i).getRobot().getPosition().getX(), game.getPlayerQueue().getUsers().get(i).getRobot().getPosition().getY() + 2));
                                        }else {
                                            game.getPlayerQueue().getUsers().get(i).getRobot().setPosition(new Position(game.getPlayerQueue().getUsers().get(i).getRobot().getPosition().getX(), game.getPlayerQueue().getUsers().get(i).getRobot().getPosition().getY() + 3));
                                        }

                                        if (!(movementCheck.fallingInPit(game.getPlayerQueue().getUsers().get(i), 0, 0))) {
                                            server.broadcast(new Movement(game.getPlayerQueue().getUsers().get(i).getClientID(), game.getPlayerQueue().getUsers().get(i).getRobot().getPosition().getX(), game.getPlayerQueue().getUsers().get(i).getRobot().getPosition().getY() + 3));
                                        }else{
                                            RebootHandler rebootHandler = new RebootHandler(server, game, game.getPlayerQueue().getUsers().get(i));
                                            rebootHandler.reboot();
                                            server.broadcast(new Reboot(game.getPlayerQueue().getUsers().get(i).getClientID()));
                                        }
                                    } catch (IndexOutOfBoundsException e) {
                                        RebootHandler rebootHandler = new RebootHandler(server, game, game.getPlayerQueue().getUsers().get(i));
                                        rebootHandler.reboot();
                                        server.broadcast(new Reboot(game.getPlayerQueue().getUsers().get(i).getClientID()));
                                    }
                                }
                            }
                        }
                    } else {
                        try {
                            robot.setPosition(new Position(x, y + 3));
                            if (!(movementCheck.fallingInPit(user, 0, 0))) {
                                server.broadcast(new Movement(user.getClientID(), x, y + 3));
                            }else{
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
                } else if (!movementCheck.checkIfBlockedAlt(new Position(position.getX(), position.getY() + 1), orientation, 0)) {
                    //Move only 2
                    currentField = new Position(position.getX(), position.getY() + 1);
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
                                        movementCheck.checkIfLastTwoAreNeighbors(game.getPlayerQueue().getUsers().get(i-1), game.getPlayerQueue().getUsers().get(i), orientationFirst, 1);
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
                            } else if (movementCheck.checkIfBlockedAlt(movementCheck.getNeighbors().get(movementCheck.getNeighbors().size() - 1).getRobot().getPosition(), orientationFirst, 1) && (!movementCheck.fallingInPit(movementCheck.getNeighbors().get(movementCheck.getNeighbors().size()-1),0,1))) {
                                for (int i = 0; i < game.getPlayerQueue().getUsers().size(); i++) {
                                    if (movementCheck.getNeighbors().contains(game.getPlayerQueue().getUsers().get(i))) {
                                        try {
                                            game.getPlayerQueue().getUsers().get(i).getRobot().setPosition(new Position(game.getPlayerQueue().getUsers().get(i).getRobot().getPosition().getX(), game.getPlayerQueue().getUsers().get(i).getRobot().getPosition().getY() + 1));
                                            if (!(movementCheck.fallingInPit(game.getPlayerQueue().getUsers().get(i), 0, 0))) {
                                                server.broadcast(new Movement(game.getPlayerQueue().getUsers().get(i).getClientID(), game.getPlayerQueue().getUsers().get(i).getRobot().getPosition().getX(), game.getPlayerQueue().getUsers().get(i).getRobot().getPosition().getY() + 1));
                                            }else{
                                                RebootHandler rebootHandler = new RebootHandler(server, game, game.getPlayerQueue().getUsers().get(i));
                                                rebootHandler.reboot();
                                                server.broadcast(new Reboot(game.getPlayerQueue().getUsers().get(i).getClientID()));
                                            }
                                        } catch (IndexOutOfBoundsException e) {
                                            RebootHandler rebootHandler = new RebootHandler(server, game, game.getPlayerQueue().getUsers().get(i));
                                            rebootHandler.reboot();
                                            server.broadcast(new Reboot(game.getPlayerQueue().getUsers().get(i).getClientID()));
                                        }
                                    }
                                }
                            } else {
                                for (int i = 0; i < game.getPlayerQueue().getUsers().size(); i++) {
                                    if (movementCheck.getNeighbors().contains(game.getPlayerQueue().getUsers().get(i))) {
                                        try {
                                            if(movementCheck.fallingInPit(game.getPlayerQueue().getUsers().get(i), 0,1)){
                                                game.getPlayerQueue().getUsers().get(i).getRobot().setPosition(new Position(game.getPlayerQueue().getUsers().get(i).getRobot().getPosition().getX(), game.getPlayerQueue().getUsers().get(i).getRobot().getPosition().getY() + 1));
                                            }else {
                                                game.getPlayerQueue().getUsers().get(i).getRobot().setPosition(new Position(game.getPlayerQueue().getUsers().get(i).getRobot().getPosition().getX(), game.getPlayerQueue().getUsers().get(i).getRobot().getPosition().getY() + 2));
                                            }
                                            if (!(movementCheck.fallingInPit(game.getPlayerQueue().getUsers().get(i), 0, 0))) {
                                                server.broadcast(new Movement(game.getPlayerQueue().getUsers().get(i).getClientID(), game.getPlayerQueue().getUsers().get(i).getRobot().getPosition().getX(), game.getPlayerQueue().getUsers().get(i).getRobot().getPosition().getY() + 2));
                                            }else{
                                                RebootHandler rebootHandler = new RebootHandler(server, game, game.getPlayerQueue().getUsers().get(i));
                                                rebootHandler.reboot();
                                                server.broadcast(new Reboot(game.getPlayerQueue().getUsers().get(i).getClientID()));
                                            }
                                        } catch (IndexOutOfBoundsException e) {
                                            RebootHandler rebootHandler = new RebootHandler(server, game, game.getPlayerQueue().getUsers().get(i));
                                            rebootHandler.reboot();
                                            server.broadcast(new Reboot(game.getPlayerQueue().getUsers().get(i).getClientID()));
                                        }
                                    }
                                }
                            }
                        } else {
                            // Move 2x
                            try {
                                robot.setPosition(new Position(x, y + 2));
                                if (!(movementCheck.fallingInPit(user, 0, 0))) {
                                    server.broadcast(new Movement(user.getClientID(), x, y + 2));
                                }else{
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
                                        try {
                                            game.getPlayerQueue().getUsers().get(i).getRobot().setPosition(new Position(game.getPlayerQueue().getUsers().get(i).getRobot().getPosition().getX(), game.getPlayerQueue().getUsers().get(i).getRobot().getPosition().getY() + 1));
                                            if (!(movementCheck.fallingInPit(game.getPlayerQueue().getUsers().get(i), 0, 0))) {
                                                server.broadcast(new Movement(game.getPlayerQueue().getUsers().get(i).getClientID(), game.getPlayerQueue().getUsers().get(i).getRobot().getPosition().getX(), game.getPlayerQueue().getUsers().get(i).getRobot().getPosition().getY() + 1));
                                            }else{
                                                RebootHandler rebootHandler = new RebootHandler(server, game, game.getPlayerQueue().getUsers().get(i));
                                                rebootHandler.reboot();
                                                server.broadcast(new Reboot(game.getPlayerQueue().getUsers().get(i).getClientID()));
                                            }
                                        } catch (IndexOutOfBoundsException e) {
                                            RebootHandler rebootHandler = new RebootHandler(server, game, game.getPlayerQueue().getUsers().get(i));
                                            rebootHandler.reboot();
                                            server.broadcast(new Reboot(game.getPlayerQueue().getUsers().get(i).getClientID()));
                                        }
                                    }
                                }
                            }
                        } else {
                            try {
                                robot.setPosition(new Position(x, y + 1));
                                if (!(movementCheck.fallingInPit(user, 0, 0))) {
                                    server.broadcast(new Movement(user.getClientID(), x, y + 1));
                                }else{
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
            } else if (user.getRobot().getRobotOrientation() == Orientation.RIGHT) {
                Position currentField;
                if (!movementCheck.checkIfBlockedAlt(new Position(position.getX() + 2, position.getY()), orientation, 0)) {
                    // Move 3
                    if (!movementCheck.checkIfBlockedAlt(new Position(position.getX() + 2, position.getY()), orientation, 0)) {
                        Orientation orientationFirst = Orientation.RIGHT;
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
                                        if(movementCheck.checkIfBlockedAlt(game.getPlayerQueue().getUsers().get(i).getRobot().getPosition(), orientationFirst, 2) && (!movementCheck.fallingInPit(game.getPlayerQueue().getUsers().get(i), 2, 0))){
                                            movementCheck.checkIfLastTwoAreNeighbors(game.getPlayerQueue().getUsers().get(i-1), game.getPlayerQueue().getUsers().get(i), Orientation.LEFT, -1);
                                            break;
                                        }else{
                                            movementCheck.checkIfLastTwoAreNeighbors(game.getPlayerQueue().getUsers().get(i-1), game.getPlayerQueue().getUsers().get(i), orientationFirst, 1);
                                        }
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
                            } else if (movementCheck.checkIfBlockedAlt(movementCheck.getNeighbors().get(movementCheck.getNeighbors().size() - 1).getRobot().getPosition(), orientationFirst, 1) && (!movementCheck.fallingInPit(movementCheck.getNeighbors().get(movementCheck.getNeighbors().size()-1),1,0))) {
                                for (int i = 0; i < game.getPlayerQueue().getUsers().size(); i++) {
                                    if (movementCheck.getNeighbors().contains(game.getPlayerQueue().getUsers().get(i))) {
                                        try {
                                            game.getPlayerQueue().getUsers().get(i).getRobot().setPosition(new Position(game.getPlayerQueue().getUsers().get(i).getRobot().getPosition().getX() + 1, game.getPlayerQueue().getUsers().get(i).getRobot().getPosition().getY()));
                                            if (!(movementCheck.fallingInPit(game.getPlayerQueue().getUsers().get(i), 0, 0))) {
                                                server.broadcast(new Movement(game.getPlayerQueue().getUsers().get(i).getClientID(), game.getPlayerQueue().getUsers().get(i).getRobot().getPosition().getX() + 1, game.getPlayerQueue().getUsers().get(i).getRobot().getPosition().getY()));
                                            }else{
                                                RebootHandler rebootHandler = new RebootHandler(server, game, game.getPlayerQueue().getUsers().get(i));
                                                rebootHandler.reboot();
                                                server.broadcast(new Reboot(game.getPlayerQueue().getUsers().get(i).getClientID()));
                                            }
                                        } catch (IndexOutOfBoundsException e) {
                                            RebootHandler rebootHandler = new RebootHandler(server, game, game.getPlayerQueue().getUsers().get(i));
                                            rebootHandler.reboot();
                                            server.broadcast(new Reboot(game.getPlayerQueue().getUsers().get(i).getClientID()));
                                        }
                                    }
                                }
                            } else if (movementCheck.checkIfBlockedAlt(movementCheck.getNeighbors().get(movementCheck.getNeighbors().size() - 1).getRobot().getPosition(), orientationFirst, 2) && ((!movementCheck.fallingInPit(movementCheck.getNeighbors().get(movementCheck.getNeighbors().size()-1),1,0)) || (!movementCheck.fallingInPit(movementCheck.getNeighbors().get(movementCheck.getNeighbors().size()-1),2,0))))  {
                                for (int i = 0; i < game.getPlayerQueue().getUsers().size(); i++) {
                                    if (movementCheck.getNeighbors().contains(game.getPlayerQueue().getUsers().get(i))) {
                                        try {
                                            game.getPlayerQueue().getUsers().get(i).getRobot().setPosition(new Position(game.getPlayerQueue().getUsers().get(i).getRobot().getPosition().getX() + 2, game.getPlayerQueue().getUsers().get(i).getRobot().getPosition().getY()));
                                            if (!(movementCheck.fallingInPit(game.getPlayerQueue().getUsers().get(i), 0, 0))) {
                                                server.broadcast(new Movement(game.getPlayerQueue().getUsers().get(i).getClientID(), game.getPlayerQueue().getUsers().get(i).getRobot().getPosition().getX() + 2, game.getPlayerQueue().getUsers().get(i).getRobot().getPosition().getY()));
                                            }else{
                                                RebootHandler rebootHandler = new RebootHandler(server, game, game.getPlayerQueue().getUsers().get(i));
                                                rebootHandler.reboot();
                                                server.broadcast(new Reboot(game.getPlayerQueue().getUsers().get(i).getClientID()));
                                            }
                                        } catch (IndexOutOfBoundsException e) {
                                            RebootHandler rebootHandler = new RebootHandler(server, game, game.getPlayerQueue().getUsers().get(i));
                                            rebootHandler.reboot();
                                            server.broadcast(new Reboot(game.getPlayerQueue().getUsers().get(i).getClientID()));
                                        }
                                    }
                                }
                            } else {
                                for (int i = 0; i < game.getPlayerQueue().getUsers().size(); i++) {
                                    if (movementCheck.getNeighbors().contains(game.getPlayerQueue().getUsers().get(i))) {
                                        try {
                                            if(movementCheck.fallingInPit(game.getPlayerQueue().getUsers().get(i), 1,0)){
                                                game.getPlayerQueue().getUsers().get(i).getRobot().setPosition(new Position(game.getPlayerQueue().getUsers().get(i).getRobot().getPosition().getX() + 1, game.getPlayerQueue().getUsers().get(i).getRobot().getPosition().getY()));
                                            }else if(movementCheck.fallingInPit(game.getPlayerQueue().getUsers().get(i),2,0)){
                                                game.getPlayerQueue().getUsers().get(i).getRobot().setPosition(new Position(game.getPlayerQueue().getUsers().get(i).getRobot().getPosition().getX() + 2, game.getPlayerQueue().getUsers().get(i).getRobot().getPosition().getY()));
                                            }else {
                                                game.getPlayerQueue().getUsers().get(i).getRobot().setPosition(new Position(game.getPlayerQueue().getUsers().get(i).getRobot().getPosition().getX() + 3, game.getPlayerQueue().getUsers().get(i).getRobot().getPosition().getY()));
                                            }
                                            if (!(movementCheck.fallingInPit(game.getPlayerQueue().getUsers().get(i), 0, 0))) {
                                                server.broadcast(new Movement(game.getPlayerQueue().getUsers().get(i).getClientID(), game.getPlayerQueue().getUsers().get(i).getRobot().getPosition().getX() + 3, game.getPlayerQueue().getUsers().get(i).getRobot().getPosition().getY()));
                                            }else{
                                                RebootHandler rebootHandler = new RebootHandler(server, game, game.getPlayerQueue().getUsers().get(i));
                                                rebootHandler.reboot();
                                                server.broadcast(new Reboot(game.getPlayerQueue().getUsers().get(i).getClientID()));
                                            }
                                        } catch (IndexOutOfBoundsException e) {
                                            RebootHandler rebootHandler = new RebootHandler(server, game, game.getPlayerQueue().getUsers().get(i));
                                            rebootHandler.reboot();
                                            server.broadcast(new Reboot(game.getPlayerQueue().getUsers().get(i).getClientID()));
                                        }
                                    }
                                }
                            }
                        } else {
                            try {
                                robot.setPosition(new Position(x + 1, y));
                                if (!(movementCheck.fallingInPit(user, 0, 0))) {
                                    server.broadcast(new Movement(user.getClientID(), x + 1, y));
                                }else{
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
                } else if (!movementCheck.checkIfBlockedAlt(new Position(position.getX() + 1, position.getY()), orientation, 0)) {
                        //Move only 2
                        currentField = new Position(position.getX() + 1, position.getY());
                        if (!movementCheck.checkIfBlockedAlt(currentField, orientation, 0)) {
                            Orientation orientationFirst = Orientation.RIGHT;
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
                                            movementCheck.checkIfLastTwoAreNeighbors(game.getPlayerQueue().getUsers().get(i-1), game.getPlayerQueue().getUsers().get(i), orientationFirst, 1);
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
                                } else if (movementCheck.checkIfBlockedAlt(movementCheck.getNeighbors().get(movementCheck.getNeighbors().size() - 1).getRobot().getPosition(), orientationFirst, 1) && (!movementCheck.fallingInPit(movementCheck.getNeighbors().get(movementCheck.getNeighbors().size()-1),1,0))) {
                                    for (int i = 0; i < game.getPlayerQueue().getUsers().size(); i++) {
                                        if (movementCheck.getNeighbors().contains(game.getPlayerQueue().getUsers().get(i))) {
                                            try {
                                                game.getPlayerQueue().getUsers().get(i).getRobot().setPosition(new Position(game.getPlayerQueue().getUsers().get(i).getRobot().getPosition().getX() + 1, game.getPlayerQueue().getUsers().get(i).getRobot().getPosition().getY()));
                                                if (!(movementCheck.fallingInPit(game.getPlayerQueue().getUsers().get(i), 0, 0))) {
                                                    server.broadcast(new Movement(game.getPlayerQueue().getUsers().get(i).getClientID(), game.getPlayerQueue().getUsers().get(i).getRobot().getPosition().getX() + 1, game.getPlayerQueue().getUsers().get(i).getRobot().getPosition().getY()));
                                                }else {
                                                    RebootHandler rebootHandler = new RebootHandler(server, game, game.getPlayerQueue().getUsers().get(i));
                                                    rebootHandler.reboot();
                                                    server.broadcast(new Reboot(game.getPlayerQueue().getUsers().get(i).getClientID()));
                                                }
                                            } catch (IndexOutOfBoundsException e) {
                                                RebootHandler rebootHandler = new RebootHandler(server, game, game.getPlayerQueue().getUsers().get(i));
                                                rebootHandler.reboot();
                                                server.broadcast(new Reboot(game.getPlayerQueue().getUsers().get(i).getClientID()));
                                            }
                                        }
                                    }
                                } else {
                                    for (int i = 0; i < game.getPlayerQueue().getUsers().size(); i++) {
                                        if (movementCheck.getNeighbors().contains(game.getPlayerQueue().getUsers().get(i))) {
                                            try {
                                                if(movementCheck.fallingInPit(game.getPlayerQueue().getUsers().get(i), 1,0)){
                                                    game.getPlayerQueue().getUsers().get(i).getRobot().setPosition(new Position(game.getPlayerQueue().getUsers().get(i).getRobot().getPosition().getX() + 1, game.getPlayerQueue().getUsers().get(i).getRobot().getPosition().getY()));
                                                }else {
                                                    game.getPlayerQueue().getUsers().get(i).getRobot().setPosition(new Position(game.getPlayerQueue().getUsers().get(i).getRobot().getPosition().getX() + 2, game.getPlayerQueue().getUsers().get(i).getRobot().getPosition().getY()));
                                                }
                                                if (!(movementCheck.fallingInPit(game.getPlayerQueue().getUsers().get(i), 0, 0))) {
                                                    server.broadcast(new Movement(game.getPlayerQueue().getUsers().get(i).getClientID(), game.getPlayerQueue().getUsers().get(i).getRobot().getPosition().getX() + 2, game.getPlayerQueue().getUsers().get(i).getRobot().getPosition().getY()));
                                                }else{
                                                    RebootHandler rebootHandler = new RebootHandler(server, game, game.getPlayerQueue().getUsers().get(i));
                                                    rebootHandler.reboot();
                                                    server.broadcast(new Reboot(game.getPlayerQueue().getUsers().get(i).getClientID()));
                                                }
                                            } catch (IndexOutOfBoundsException e) {
                                                RebootHandler rebootHandler = new RebootHandler(server, game, game.getPlayerQueue().getUsers().get(i));
                                                rebootHandler.reboot();
                                                server.broadcast(new Reboot(game.getPlayerQueue().getUsers().get(i).getClientID()));
                                            }
                                        }
                                    }
                                }
                            } else {
                                // Move 2x
                                try {
                                    robot.setPosition(new Position(x + 2, y));
                                    if (!(movementCheck.fallingInPit(user, 0, 0))) {
                                        server.broadcast(new Movement(user.getClientID(), x + 2, y));
                                    }else{
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
                            Orientation orientationFirst = Orientation.RIGHT;
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
                                            try {
                                                game.getPlayerQueue().getUsers().get(i).getRobot().setPosition(new Position(game.getPlayerQueue().getUsers().get(i).getRobot().getPosition().getX() + 1, game.getPlayerQueue().getUsers().get(i).getRobot().getPosition().getY()));
                                                if (!(movementCheck.fallingInPit(game.getPlayerQueue().getUsers().get(i), 0, 0))) {
                                                    server.broadcast(new Movement(game.getPlayerQueue().getUsers().get(i).getClientID(), game.getPlayerQueue().getUsers().get(i).getRobot().getPosition().getX() + 1, game.getPlayerQueue().getUsers().get(i).getRobot().getPosition().getY()));
                                                }else{
                                                    RebootHandler rebootHandler = new RebootHandler(server, game, game.getPlayerQueue().getUsers().get(i));
                                                    rebootHandler.reboot();
                                                    server.broadcast(new Reboot(game.getPlayerQueue().getUsers().get(i).getClientID()));
                                                }
                                            } catch (IndexOutOfBoundsException e) {
                                                RebootHandler rebootHandler = new RebootHandler(server, game, game.getPlayerQueue().getUsers().get(i));
                                                rebootHandler.reboot();
                                                server.broadcast(new Reboot(game.getPlayerQueue().getUsers().get(i).getClientID()));
                                            }
                                        }
                                    }
                                }
                            } else {
                                try {
                                    robot.setPosition(new Position(x + 1, y));
                                    if (!(movementCheck.fallingInPit(user, 0, 0))) {
                                        server.broadcast(new Movement(user.getClientID(), x + 1, y));
                                    }else{
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
    }







