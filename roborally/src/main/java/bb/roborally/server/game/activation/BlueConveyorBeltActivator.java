package bb.roborally.server.game.activation;

import bb.roborally.protocol.Message;
import bb.roborally.protocol.game_events.Animation;
import bb.roborally.protocol.game_events.Movement;
import bb.roborally.protocol.game_events.PlayerTurning;
import bb.roborally.protocol.game_events.Reboot;
import bb.roborally.server.Server;
import bb.roborally.server.game.Game;
import bb.roborally.server.game.Orientation;
import bb.roborally.server.game.Position;
import bb.roborally.server.game.User;
import bb.roborally.server.game.board.Cell;

import java.io.IOException;
import java.security.cert.PolicyNode;
import java.util.ArrayList;

public class BlueConveyorBeltActivator {

    private Server server;
    private Game game;
    ArrayList<User> alreadyOnBelts;

    public BlueConveyorBeltActivator(Server server, Game game, ArrayList<User> alreadyOnBelts) {
        this.server = server;
        this.game = game;
        this.alreadyOnBelts = alreadyOnBelts;
    }

    public void activate() throws IOException{
        Animation animation = new Animation("BlueConveyorBelt");
            server.broadcast(animation);
        ArrayList<Cell> belts = game.getBoard().getBlueConveyorBelts();
        for (User user: game.getPlayerQueue().getUsers()) {
            boolean isOnTile = false;
            int counter = 0;
            while (counter < belts.size() && !isOnTile) {
                if (belts.get(counter).getPosition().equals(user.getRobot().getPosition())) {
                    isOnTile = true;
                }
                counter += 1;
            }
            if (isOnTile) {
                //handle playerTurning before the activation of belts
                if(alreadyOnBelts.contains(user)){
                    Position position = user.getRobot().getPosition();
                    ArrayList<Orientation> orientations = game.getBoard().get(position.getX(), position.getY()).getTile("ConveyorBelt").getOrientations();
                    if(orientations.size() == 3 || (orientations.size() == 2 &&
                            (orientations.contains(Orientation.TOP) && orientations.contains(Orientation.RIGHT)) ||
                            (orientations.contains(Orientation.TOP) && orientations.contains(Orientation.LEFT)) ||
                            (orientations.contains(Orientation.BOTTOM) && orientations.contains(Orientation.RIGHT)) ||
                            (orientations.contains(Orientation.BOTTOM) && orientations.contains(Orientation.LEFT)))){
                        if(user.getRobot().getRobotOrientation() != orientations.get(0)){
                            switch (orientations.get(0)){
                                case TOP:
                                    switch (user.getRobot().getRobotOrientation()){
                                        case RIGHT:
                                            user.getRobot().setRobotOrientation(Orientation.TOP);
                                            server.broadcast(new PlayerTurning(user.getClientID(), "counterclockwise"));
                                            break;
                                        case LEFT:
                                            user.getRobot().setRobotOrientation(Orientation.TOP);
                                            server.broadcast(new PlayerTurning(user.getClientID(), "clockwise"));
                                            break;
                                    }
                                    break;
                                case LEFT:
                                    switch (user.getRobot().getRobotOrientation()){
                                        case TOP:
                                            user.getRobot().setRobotOrientation(Orientation.LEFT);
                                            server.broadcast(new PlayerTurning(user.getClientID(), "counterclockwise"));
                                            break;
                                        case BOTTOM:
                                            user.getRobot().setRobotOrientation(Orientation.LEFT);
                                            server.broadcast(new PlayerTurning(user.getClientID(), "clockwise"));
                                            break;
                                    }
                                    break;
                                case BOTTOM:
                                    switch (user.getRobot().getRobotOrientation()){
                                        case LEFT:
                                            user.getRobot().setRobotOrientation(Orientation.BOTTOM);
                                            server.broadcast(new PlayerTurning(user.getClientID(), "counterclockwise"));
                                            break;
                                        case RIGHT:
                                            user.getRobot().setRobotOrientation(Orientation.BOTTOM);
                                            server.broadcast(new PlayerTurning(user.getClientID(), "clockwise"));
                                            break;
                                    }
                                    break;
                                case RIGHT:
                                    switch (user.getRobot().getRobotOrientation()){
                                        case BOTTOM:
                                            user.getRobot().setRobotOrientation(Orientation.RIGHT);
                                            server.broadcast(new PlayerTurning(user.getClientID(), "counterclockwise"));
                                            break;
                                        case TOP:
                                            user.getRobot().setRobotOrientation(Orientation.RIGHT);
                                            server.broadcast(new PlayerTurning(user.getClientID(), "clockwise"));
                                            break;
                                    }
                                    break;
                            }
                        }
                    }
                }

                //After PlayerTurning or there is no need to turn
                Position position = user.getRobot().getPosition();
                int x = position.getX();
                int y = position.getY();
                ArrayList<Orientation> orientations = game.getBoard().get(position.getX(), position.getY()).getTile("ConveyorBelt").getOrientations();
                //first step forward
                switch (orientations.get(0)){
                    case LEFT -> position.setX(x-1);
                    case RIGHT -> position.setX(x+1);
                    case TOP -> position.setY(y-1);
                    case BOTTOM -> position.setY(y+1);
                }
                //check whether the robot needs to reboot after the first step
                MovementCheck movementCheck = new MovementCheck(game.getBoard());
                if(movementCheck.robotIsOffBoard(user) || movementCheck.fallingInPit(user, 0, 0)){
                    server.broadcast(new Reboot(user.getClientID()));
                }
                //if actual space still contains belt, belt-effect still works, or the effect doesn't work anymore
                if(game.getBoard().get(position.getX(), position.getY()).getTile("ConveyorBelt") != null){
                    //Check whether the robot needs to turn before step 2 (for all robots, although they came from spaces without belts)
                    ArrayList<Orientation> orientations2 = game.getBoard().get(position.getX(), position.getY()).getTile("ConveyorBelt").getOrientations();
                    if(orientations2.size() == 3 || (orientations2.size() == 2 &&
                            (orientations2.contains(Orientation.TOP) && orientations2.contains(Orientation.RIGHT)) ||
                            (orientations2.contains(Orientation.TOP) && orientations2.contains(Orientation.LEFT)) ||
                            (orientations2.contains(Orientation.BOTTOM) && orientations2.contains(Orientation.RIGHT)) ||
                            (orientations2.contains(Orientation.BOTTOM) && orientations2.contains(Orientation.LEFT)))){
                        if(user.getRobot().getRobotOrientation() != orientations2.get(0)){
                            switch (orientations2.get(0)){
                                case TOP:
                                    switch (user.getRobot().getRobotOrientation()){
                                        case RIGHT:
                                            user.getRobot().setRobotOrientation(Orientation.TOP);
                                            server.broadcast(new PlayerTurning(user.getClientID(), "counterclockwise"));
                                            break;
                                        case LEFT:
                                            user.getRobot().setRobotOrientation(Orientation.TOP);
                                            server.broadcast(new PlayerTurning(user.getClientID(), "clockwise"));
                                            break;
                                    }
                                    break;
                                case LEFT:
                                    switch (user.getRobot().getRobotOrientation()){
                                        case TOP:
                                            user.getRobot().setRobotOrientation(Orientation.LEFT);
                                            server.broadcast(new PlayerTurning(user.getClientID(), "counterclockwise"));
                                            break;
                                        case BOTTOM:
                                            user.getRobot().setRobotOrientation(Orientation.LEFT);
                                            server.broadcast(new PlayerTurning(user.getClientID(), "clockwise"));
                                            break;
                                    }
                                    break;
                                case BOTTOM:
                                    switch (user.getRobot().getRobotOrientation()){
                                        case LEFT:
                                            user.getRobot().setRobotOrientation(Orientation.BOTTOM);
                                            server.broadcast(new PlayerTurning(user.getClientID(), "counterclockwise"));
                                            break;
                                        case RIGHT:
                                            user.getRobot().setRobotOrientation(Orientation.BOTTOM);
                                            server.broadcast(new PlayerTurning(user.getClientID(), "clockwise"));
                                            break;
                                    }
                                    break;
                                case RIGHT:
                                    switch (user.getRobot().getRobotOrientation()){
                                        case BOTTOM:
                                            user.getRobot().setRobotOrientation(Orientation.RIGHT);
                                            server.broadcast(new PlayerTurning(user.getClientID(), "counterclockwise"));
                                            break;
                                        case TOP:
                                            user.getRobot().setRobotOrientation(Orientation.RIGHT);
                                            server.broadcast(new PlayerTurning(user.getClientID(), "clockwise"));
                                            break;
                                    }
                                    break;
                            }
                        }
                    }

                    ArrayList<Orientation> orientations1 = game.getBoard().get(position.getX(), position.getY()).getTile("ConveyorBelt").getOrientations();
                    switch (orientations1.get(0)){
                        case LEFT -> position.setX(position.getX()-1);
                        case RIGHT -> position.setX(position.getX()+1);
                        case TOP -> position.setY(position.getY()-1);
                        case BOTTOM -> position.setY(position.getY()+1);
                    }
                    //check whether the robot needs to reboot after the second step
                    MovementCheck movementCheck1 = new MovementCheck(game.getBoard());
                    if(movementCheck1.robotIsOffBoard(user) || movementCheck1.fallingInPit(user, 0, 0)){
                        server.broadcast(new Reboot(user.getClientID()));
                    }
                }
                //update the position of the robot
                server.broadcast(new Movement(user.getClientID(), position.getX(), position.getY()));

                //Check whether the robots that were already on belts need to turn
                if(alreadyOnBelts.contains(user)){
                    Position position1 = user.getRobot().getPosition();
                    ArrayList<Orientation> orientations1 = game.getBoard().get(position1.getX(), position1.getY()).getTile("ConveyorBelt").getOrientations();
                    if(orientations1.size() == 3 || (orientations1.size() == 2 &&
                            (orientations1.contains(Orientation.TOP) && orientations1.contains(Orientation.RIGHT)) ||
                            (orientations1.contains(Orientation.TOP) && orientations1.contains(Orientation.LEFT)) ||
                            (orientations1.contains(Orientation.BOTTOM) && orientations1.contains(Orientation.RIGHT)) ||
                            (orientations1.contains(Orientation.BOTTOM) && orientations1.contains(Orientation.LEFT)))){
                        if(user.getRobot().getRobotOrientation() != orientations1.get(0)){
                            switch (orientations1.get(0)){
                                case TOP:
                                    switch (user.getRobot().getRobotOrientation()){
                                        case RIGHT:
                                            user.getRobot().setRobotOrientation(Orientation.TOP);
                                            server.broadcast(new PlayerTurning(user.getClientID(), "counterclockwise"));
                                            break;
                                        case LEFT:
                                            user.getRobot().setRobotOrientation(Orientation.TOP);
                                            server.broadcast(new PlayerTurning(user.getClientID(), "clockwise"));
                                            break;
                                    }
                                    break;
                                case LEFT:
                                    switch (user.getRobot().getRobotOrientation()){
                                        case TOP:
                                            user.getRobot().setRobotOrientation(Orientation.LEFT);
                                            server.broadcast(new PlayerTurning(user.getClientID(), "counterclockwise"));
                                            break;
                                        case BOTTOM:
                                            user.getRobot().setRobotOrientation(Orientation.LEFT);
                                            server.broadcast(new PlayerTurning(user.getClientID(), "clockwise"));
                                            break;
                                    }
                                    break;
                                case BOTTOM:
                                    switch (user.getRobot().getRobotOrientation()){
                                        case LEFT:
                                            user.getRobot().setRobotOrientation(Orientation.BOTTOM);
                                            server.broadcast(new PlayerTurning(user.getClientID(), "counterclockwise"));
                                            break;
                                        case RIGHT:
                                            user.getRobot().setRobotOrientation(Orientation.BOTTOM);
                                            server.broadcast(new PlayerTurning(user.getClientID(), "clockwise"));
                                            break;
                                    }
                                    break;
                                case RIGHT:
                                    switch (user.getRobot().getRobotOrientation()){
                                        case BOTTOM:
                                            user.getRobot().setRobotOrientation(Orientation.RIGHT);
                                            server.broadcast(new PlayerTurning(user.getClientID(), "counterclockwise"));
                                            break;
                                        case TOP:
                                            user.getRobot().setRobotOrientation(Orientation.RIGHT);
                                            server.broadcast(new PlayerTurning(user.getClientID(), "clockwise"));
                                            break;
                                    }
                                    break;
                            }
                        }
                    }
                }
            }
        }
    }
}
