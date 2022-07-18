package bb.roborally.server.game.activation;


import bb.roborally.protocol.game_events.Movement;
import bb.roborally.protocol.game_events.Reboot;
import bb.roborally.server.Server;
import bb.roborally.server.game.*;
import java.io.IOException;


public class Move1Handler {

    Server server;
    Game game;
    User user;

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
        if (movementCheck.checkIfBlockedAlt(position, orientation, 0)) {
            server.broadcast(new Movement(user.getClientID(), x, y));
        } else {
            if (user.getRobot().getRobotOrientation() == Orientation.TOP) {
                Orientation orientationFirst = Orientation.TOP;
                if (game.getPlayerQueue().getUsers().size() != 1) {
                    movementCheck.checkIfFirstTwoAreNeighbors(game.getPlayerQueue().getUsers().get(0).getRobot().getPosition(), orientationFirst, 1);  //check if first two are neighbors and store the first one in same list - extra method because the first one will not be stored in first method
                    for (int i = 0; i < game.getPlayerQueue().getUsers().size(); i++) {           //check if Players are neighbors - store them in extra list
                        movementCheck.robotForwardCheck(game.getPlayerQueue().getUsers().get(i).getRobot().getPosition(), orientationFirst, 1);
                    }
                    if (movementCheck.checkIfBlockedAlt(movementCheck.getNeighbors().get(movementCheck.getNeighbors().size() - 1).getRobot().getPosition(), orientationFirst, 0)) {
                        //robot.setPosition(new Position(currentField.getX(), currentField.getY()));
                        for (int i = 0; i < game.getPlayerQueue().getUsers().size(); i++) {
                            if (movementCheck.getNeighbors().contains(game.getPlayerQueue().getUsers().get(i))) {
                                game.getPlayerQueue().getUsers().get(i).getRobot().setPosition(new Position(game.getPlayerQueue().getUsers().get(i).getRobot().getPosition().getX(), game.getPlayerQueue().getUsers().get(i).getRobot().getPosition().getY()));
                                if (movementCheck.fallingInPit(game.getPlayerQueue().getUsers().get(i)) || movementCheck.robotIsOffBoard(game.getPlayerQueue().getUsers().get(i))) {
                                    server.broadcast(new Reboot(game.getPlayerQueue().getUsers().get(i).getClientID()));
                                }
                                server.broadcast(new Movement(game.getPlayerQueue().getUsers().get(i).getClientID(), game.getPlayerQueue().getUsers().get(i).getRobot().getPosition().getX(), game.getPlayerQueue().getUsers().get(i).getRobot().getPosition().getY()));
                            }
                        }
                    } else {
                        for (int i = 0; i < game.getPlayerQueue().getUsers().size(); i++) {
                            if (movementCheck.getNeighbors().contains(game.getPlayerQueue().getUsers().get(i))) {
                                game.getPlayerQueue().getUsers().get(i).getRobot().setPosition(new Position(game.getPlayerQueue().getUsers().get(i).getRobot().getPosition().getX(), game.getPlayerQueue().getUsers().get(i).getRobot().getPosition().getY() - 1));
                                if (movementCheck.fallingInPit(game.getPlayerQueue().getUsers().get(i)) || movementCheck.robotIsOffBoard(game.getPlayerQueue().getUsers().get(i))) {
                                    server.broadcast(new Reboot(game.getPlayerQueue().getUsers().get(i).getClientID()));
                                }
                                server.broadcast(new Movement(game.getPlayerQueue().getUsers().get(i).getClientID(), game.getPlayerQueue().getUsers().get(i).getRobot().getPosition().getX(), game.getPlayerQueue().getUsers().get(i).getRobot().getPosition().getY() - 1));
                            }
                        }
                    }
                }
                    if (game.getPlayerQueue().getUsers().size() == 1) {
                        robot.setPosition(new Position(x, y - 1));
                        if (movementCheck.fallingInPit(user) || movementCheck.robotIsOffBoard(user)) {
                            //robot.setPosition(new Position(currentField.getX(), currentField.getY()));
                            server.broadcast(new Reboot(user.getClientID()));
                        } else {
                            if (movementCheck.robotForwardCheck(user.getRobot().getPosition(), user.getRobot().getRobotOrientation(), 0)) {
                                movementCheck.pushRobot(server, game, user, orientation, 1);
                            }
                        }

                        server.broadcast(new Movement(user.getClientID(), x, y - 1));

                    }

            } else if (user.getRobot().getRobotOrientation() == Orientation.LEFT) {
                Orientation orientationFirst = Orientation.LEFT;
                if (game.getPlayerQueue().getUsers().size() != 1) {
                    movementCheck.checkIfFirstTwoAreNeighbors(game.getPlayerQueue().getUsers().get(0).getRobot().getPosition(), orientationFirst, 1);  //check if first two are neighbors and store the first one in same list - extra method because the first one will not be stored in first method


                    for (int i = 0; i < game.getPlayerQueue().getUsers().size(); i++) {           //check if Players are neighbors - store them in extra list
                        movementCheck.robotForwardCheck(game.getPlayerQueue().getUsers().get(i).getRobot().getPosition(), orientationFirst, 1);
                    }

                    if (movementCheck.checkIfBlockedAlt(movementCheck.getNeighbors().get(movementCheck.getNeighbors().size() - 1).getRobot().getPosition(), orientationFirst, 0)) {
                        //robot.setPosition(new Position(currentField.getX(), currentField.getY()));
                        for (int i = 0; i < game.getPlayerQueue().getUsers().size(); i++) {
                            if (movementCheck.getNeighbors().contains(game.getPlayerQueue().getUsers().get(i))) {
                                game.getPlayerQueue().getUsers().get(i).getRobot().setPosition(new Position(game.getPlayerQueue().getUsers().get(i).getRobot().getPosition().getX(), game.getPlayerQueue().getUsers().get(i).getRobot().getPosition().getY()));
                                if (movementCheck.fallingInPit(game.getPlayerQueue().getUsers().get(i)) || movementCheck.robotIsOffBoard(game.getPlayerQueue().getUsers().get(i))) {
                                    server.broadcast(new Reboot(game.getPlayerQueue().getUsers().get(i).getClientID()));
                                }
                                server.broadcast(new Movement(game.getPlayerQueue().getUsers().get(i).getClientID(), game.getPlayerQueue().getUsers().get(i).getRobot().getPosition().getX(), game.getPlayerQueue().getUsers().get(i).getRobot().getPosition().getY()));
                            }
                        }
                    } else {
                        for (int i = 0; i < game.getPlayerQueue().getUsers().size(); i++) {
                            if (movementCheck.getNeighbors().contains(game.getPlayerQueue().getUsers().get(i))) {
                                game.getPlayerQueue().getUsers().get(i).getRobot().setPosition(new Position(game.getPlayerQueue().getUsers().get(i).getRobot().getPosition().getX() - 1, game.getPlayerQueue().getUsers().get(i).getRobot().getPosition().getY()));
                                if (movementCheck.fallingInPit(game.getPlayerQueue().getUsers().get(i)) || movementCheck.robotIsOffBoard(game.getPlayerQueue().getUsers().get(i))) {
                                    server.broadcast(new Reboot(game.getPlayerQueue().getUsers().get(i).getClientID()));
                                }
                                server.broadcast(new Movement(game.getPlayerQueue().getUsers().get(i).getClientID(), game.getPlayerQueue().getUsers().get(i).getRobot().getPosition().getX() - 1, game.getPlayerQueue().getUsers().get(i).getRobot().getPosition().getY()));
                            }
                        }
                    }
                }

                    if (game.getPlayerQueue().getUsers().size() == 1) {
                        robot.setPosition(new Position(x - 1, y));
                        if (movementCheck.fallingInPit(user) || movementCheck.robotIsOffBoard(user)) {
                            //robot.setPosition(new Position(currentField.getX(), currentField.getY()));
                            server.broadcast(new Reboot(user.getClientID()));
                        } else {
                            if (movementCheck.robotForwardCheck(user.getRobot().getPosition(), user.getRobot().getRobotOrientation(), 0)) {
                                movementCheck.pushRobot(server, game, user, orientation, 1);
                            }
                        }
                        server.broadcast(new Movement(user.getClientID(), x - 1, y));
                    }

            } else if (user.getRobot().getRobotOrientation() == Orientation.BOTTOM) {
                Orientation orientationFirst = Orientation.BOTTOM;
                if (game.getPlayerQueue().getUsers().size() != 1) {
                    movementCheck.checkIfFirstTwoAreNeighbors(game.getPlayerQueue().getUsers().get(0).getRobot().getPosition(), orientationFirst, 1);  //check if first two are neighbors and store the first one in same list - extra method because the first one will not be stored in first method


                    for (int i = 0; i < game.getPlayerQueue().getUsers().size(); i++) {           //check if Players are neighbors - store them in extra list
                        movementCheck.robotForwardCheck(game.getPlayerQueue().getUsers().get(i).getRobot().getPosition(), orientationFirst, 1);
                    }

                    if (movementCheck.checkIfBlockedAlt(movementCheck.getNeighbors().get(movementCheck.getNeighbors().size() - 1).getRobot().getPosition(), orientationFirst, 0)) {
                        //robot.setPosition(new Position(currentField.getX(), currentField.getY()));
                        for (int i = 0; i < game.getPlayerQueue().getUsers().size(); i++) {
                            if (movementCheck.getNeighbors().contains(game.getPlayerQueue().getUsers().get(i))) {
                                game.getPlayerQueue().getUsers().get(i).getRobot().setPosition(new Position(game.getPlayerQueue().getUsers().get(i).getRobot().getPosition().getX(), game.getPlayerQueue().getUsers().get(i).getRobot().getPosition().getY()));
                                if (movementCheck.fallingInPit(game.getPlayerQueue().getUsers().get(i)) || movementCheck.robotIsOffBoard(game.getPlayerQueue().getUsers().get(i))) {
                                    server.broadcast(new Reboot(game.getPlayerQueue().getUsers().get(i).getClientID()));
                                }
                                server.broadcast(new Movement(game.getPlayerQueue().getUsers().get(i).getClientID(), game.getPlayerQueue().getUsers().get(i).getRobot().getPosition().getX(), game.getPlayerQueue().getUsers().get(i).getRobot().getPosition().getY()));
                            }
                        }
                    } else {
                        for (int i = 0; i < game.getPlayerQueue().getUsers().size(); i++) {
                            if (movementCheck.getNeighbors().contains(game.getPlayerQueue().getUsers().get(i))) {
                                game.getPlayerQueue().getUsers().get(i).getRobot().setPosition(new Position(game.getPlayerQueue().getUsers().get(i).getRobot().getPosition().getX(), game.getPlayerQueue().getUsers().get(i).getRobot().getPosition().getY() + 1));
                                if (movementCheck.fallingInPit(game.getPlayerQueue().getUsers().get(i)) || movementCheck.robotIsOffBoard(game.getPlayerQueue().getUsers().get(i))) {
                                    server.broadcast(new Reboot(game.getPlayerQueue().getUsers().get(i).getClientID()));
                                }
                                server.broadcast(new Movement(game.getPlayerQueue().getUsers().get(i).getClientID(), game.getPlayerQueue().getUsers().get(i).getRobot().getPosition().getX(), game.getPlayerQueue().getUsers().get(i).getRobot().getPosition().getY() + 1));
                            }
                        }
                    }
                }

                    if (game.getPlayerQueue().getUsers().size() == 1) {
                        robot.setPosition(new Position(x, y + 1));
                        if (movementCheck.fallingInPit(user) || movementCheck.robotIsOffBoard(user)) {
                            //robot.setPosition(new Position(currentField.getX(), currentField.getY()));
                            server.broadcast(new Reboot(user.getClientID()));
                        } else {
                            if (movementCheck.robotForwardCheck(user.getRobot().getPosition(), user.getRobot().getRobotOrientation(), 0)) {
                                movementCheck.pushRobot(server, game, user, orientation, 1);
                            }
                        }
                        server.broadcast(new Movement(user.getClientID(), x, y + 1));
                    }

            } else if (user.getRobot().getRobotOrientation() == Orientation.RIGHT) {
                Orientation orientationFirst = Orientation.RIGHT;
                if (game.getPlayerQueue().getUsers().size() != 1) {
                    movementCheck.checkIfFirstTwoAreNeighbors(game.getPlayerQueue().getUsers().get(0).getRobot().getPosition(), orientationFirst, 1);  //check if first two are neighbors and store the first one in same list - extra method because the first one will not be stored in first method


                    for (int i = 0; i < game.getPlayerQueue().getUsers().size(); i++) {           //check if Players are neighbors - store them in extra list
                        movementCheck.robotForwardCheck(game.getPlayerQueue().getUsers().get(i).getRobot().getPosition(), orientationFirst, 1);
                    }

                    if (movementCheck.checkIfBlockedAlt(movementCheck.getNeighbors().get(movementCheck.getNeighbors().size() - 1).getRobot().getPosition(), orientationFirst, 0)) {
                        //robot.setPosition(new Position(currentField.getX(), currentField.getY()));
                        for (int i = 0; i < game.getPlayerQueue().getUsers().size(); i++) {
                            if (movementCheck.getNeighbors().contains(game.getPlayerQueue().getUsers().get(i))) {
                                game.getPlayerQueue().getUsers().get(i).getRobot().setPosition(new Position(game.getPlayerQueue().getUsers().get(i).getRobot().getPosition().getX(), game.getPlayerQueue().getUsers().get(i).getRobot().getPosition().getY()));
                                if (movementCheck.fallingInPit(game.getPlayerQueue().getUsers().get(i)) || movementCheck.robotIsOffBoard(game.getPlayerQueue().getUsers().get(i))) {
                                    server.broadcast(new Reboot(game.getPlayerQueue().getUsers().get(i).getClientID()));
                                }
                                server.broadcast(new Movement(game.getPlayerQueue().getUsers().get(i).getClientID(), game.getPlayerQueue().getUsers().get(i).getRobot().getPosition().getX(), game.getPlayerQueue().getUsers().get(i).getRobot().getPosition().getY()));
                            }
                        }
                    } else {
                        for (int i = 0; i < game.getPlayerQueue().getUsers().size(); i++) {
                            if (movementCheck.getNeighbors().contains(game.getPlayerQueue().getUsers().get(i))) {
                                game.getPlayerQueue().getUsers().get(i).getRobot().setPosition(new Position(game.getPlayerQueue().getUsers().get(i).getRobot().getPosition().getX() + 1, game.getPlayerQueue().getUsers().get(i).getRobot().getPosition().getY()));
                                if (movementCheck.fallingInPit(game.getPlayerQueue().getUsers().get(i)) || movementCheck.robotIsOffBoard(game.getPlayerQueue().getUsers().get(i))) {
                                    server.broadcast(new Reboot(game.getPlayerQueue().getUsers().get(i).getClientID()));
                                }
                                server.broadcast(new Movement(game.getPlayerQueue().getUsers().get(i).getClientID(), game.getPlayerQueue().getUsers().get(i).getRobot().getPosition().getX() + 1, game.getPlayerQueue().getUsers().get(i).getRobot().getPosition().getY()));
                            }
                        }
                    }
                }
                    if (game.getPlayerQueue().getUsers().size() == 1) {
                        robot.setPosition(new Position(x + 1, y));
                        if (movementCheck.fallingInPit(user) || movementCheck.robotIsOffBoard(user)) {
                            //robot.setPosition(new Position(currentField.getX(), currentField.getY()));
                            server.broadcast(new Reboot(user.getClientID()));
                        } else {
                            if (movementCheck.robotForwardCheck(user.getRobot().getPosition(), user.getRobot().getRobotOrientation(), 0)) {
                                movementCheck.pushRobot(server, game, user, orientation, 1);
                            }
                        }

                        server.broadcast(new Movement(user.getClientID(), x + 1, y));

                    }

            }
        }
    }
}



