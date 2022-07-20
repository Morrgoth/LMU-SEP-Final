package bb.roborally.server.game.activation;

import bb.roborally.protocol.game_events.Animation;
import bb.roborally.protocol.game_events.Movement;
import bb.roborally.protocol.game_events.PlayerTurning;
import bb.roborally.protocol.game_events.Reboot;
import bb.roborally.server.Server;
import bb.roborally.server.game.*;
import bb.roborally.server.game.board.Cell;

import java.io.IOException;
import java.util.ArrayList;

public class GreenConveyorBeltActivator {
    private Server server;
    private Game game;
    private ArrayList<User> alreadyOnBelts;

    public GreenConveyorBeltActivator(Server server, Game game, ArrayList<User> alreadyOnBelts) {
        this.server = server;
        this.game = game;
        this.alreadyOnBelts = alreadyOnBelts;
    }

    public void activate() throws IOException{
        Animation animation = new Animation("GreenConveyorBelt");
        server.broadcast(animation);

        ArrayList<Cell> greenConveyorBelt = game.getBoard().getGreenConveyorBelts();
        for(User user: game.getPlayerQueue().getUsers()){
            boolean isOnTile = false;
            int counter = 0;
            while(counter < greenConveyorBelt.size()){
                if(greenConveyorBelt.get(counter).getPosition().equals(user.getRobot().getPosition())){
                    isOnTile = true;
                }
                counter += 1;
            }
            if(isOnTile){
                //PlayerTurning before the activation of belts
                if(alreadyOnBelts.contains(user)){
                    Position position = user.getRobot().getPosition();
                    ArrayList<Orientation> orientations = game.getBoard().get(position.getX(), position.getY()).getTile("ConveyorBelt").getOrientations();
                    if(orientations.size() == 2 &&
                            (orientations.contains(Orientation.TOP) && orientations.contains(Orientation.RIGHT)) ||
                            (orientations.contains(Orientation.TOP) && orientations.contains(Orientation.LEFT)) ||
                            (orientations.contains(Orientation.BOTTOM) && orientations.contains(Orientation.RIGHT)) ||
                            (orientations.contains(Orientation.BOTTOM) && orientations.contains(Orientation.LEFT))){
                        if(user.getRobot().getRobotOrientation() != orientations.get(0)){
                            switch (orientations.get(0)){
                                case TOP:
                                    switch (user.getRobot().getRobotOrientation()){
                                        case RIGHT:
                                            user.getRobot().setRobotOrientation(Orientation.TOP);
                                            server.broadcast(new PlayerTurning(user.getClientID(), "counterclockwise"));
                                        case LEFT:
                                            user.getRobot().setRobotOrientation(Orientation.TOP);
                                            server.broadcast(new PlayerTurning(user.getClientID(), "clockwise"));
                                    }
                                case LEFT:
                                    switch (user.getRobot().getRobotOrientation()){
                                        case TOP:
                                            user.getRobot().setRobotOrientation(Orientation.LEFT);
                                            server.broadcast(new PlayerTurning(user.getClientID(), "counterclockwise"));
                                        case BOTTOM:
                                            user.getRobot().setRobotOrientation(Orientation.LEFT);
                                            server.broadcast(new PlayerTurning(user.getClientID(), "clockwise"));
                                    }
                                case BOTTOM:
                                    switch (user.getRobot().getRobotOrientation()){
                                        case LEFT:
                                            user.getRobot().setRobotOrientation(Orientation.BOTTOM);
                                            server.broadcast(new PlayerTurning(user.getClientID(), "counterclockwise"));
                                        case RIGHT:
                                            user.getRobot().setRobotOrientation(Orientation.BOTTOM);
                                            server.broadcast(new PlayerTurning(user.getClientID(), "clockwise"));
                                    }
                                case RIGHT:
                                    switch (user.getRobot().getRobotOrientation()){
                                        case BOTTOM:
                                            user.getRobot().setRobotOrientation(Orientation.RIGHT);
                                            server.broadcast(new PlayerTurning(user.getClientID(), "counterclockwise"));
                                        case TOP:
                                            user.getRobot().setRobotOrientation(Orientation.RIGHT);
                                            server.broadcast(new PlayerTurning(user.getClientID(), "clockwise"));
                                    }
                            }
                        }
                    }
                }
                //After PlayerTurning or there is no need to turn
                Position position = user.getRobot().getPosition();
                int x = position.getX();
                int y = position.getY();
                ArrayList<Orientation> orientations = game.getBoard().get(position.getX(), position.getY()).getTile("ConveyorBelt").getOrientations();
                switch (orientations.get(0)){
                    case TOP -> position.setY(y-1);
                    case LEFT -> position.setX(x-1);
                    case RIGHT -> position.setX(x+1);
                    case BOTTOM -> position.setY(y+1);
                }
                //check whether the robot needs to reboot
                MovementCheck movementCheck = new MovementCheck(game.getBoard());
                if(movementCheck.robotIsOffBoard(user) || movementCheck.fallingInPit(user,0,0)){
                    server.broadcast(new Reboot(user.getClientID()));
                }
                server.broadcast(new Movement(user.getClientID(), position.getX(), position.getY()));
            }
        }
    }
}
