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

    public void activate() {
        Animation animation = new Animation("GreenConveyorBelt");
        try {
            server.broadcast(animation);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

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
                                            try {
                                                server.broadcast(new PlayerTurning(user.getClientID(), "counterclockwise"));
                                            } catch (IOException e) {
                                                throw new RuntimeException(e);
                                            }
                                        case LEFT:
                                            user.getRobot().setRobotOrientation(Orientation.TOP);
                                            try {
                                                server.broadcast(new PlayerTurning(user.getClientID(), "clockwise"));
                                            } catch (IOException e) {
                                                throw new RuntimeException(e);
                                            }
                                    }
                                case LEFT:
                                    switch (user.getRobot().getRobotOrientation()){
                                        case TOP:
                                            user.getRobot().setRobotOrientation(Orientation.LEFT);
                                            try {
                                                server.broadcast(new PlayerTurning(user.getClientID(), "counterclockwise"));
                                            } catch (IOException e) {
                                                throw new RuntimeException(e);
                                            }
                                        case BOTTOM:
                                            user.getRobot().setRobotOrientation(Orientation.LEFT);
                                            try {
                                                server.broadcast(new PlayerTurning(user.getClientID(), "clockwise"));
                                            } catch (IOException e) {
                                                throw new RuntimeException(e);
                                            }
                                    }
                                case BOTTOM:
                                    switch (user.getRobot().getRobotOrientation()){
                                        case LEFT:
                                            user.getRobot().setRobotOrientation(Orientation.BOTTOM);
                                            try {
                                                server.broadcast(new PlayerTurning(user.getClientID(), "counterclockwise"));
                                            } catch (IOException e) {
                                                throw new RuntimeException(e);
                                            }
                                        case RIGHT:
                                            user.getRobot().setRobotOrientation(Orientation.BOTTOM);
                                            try {
                                                server.broadcast(new PlayerTurning(user.getClientID(), "clockwise"));
                                            } catch (IOException e) {
                                                throw new RuntimeException(e);
                                            }
                                    }
                                case RIGHT:
                                    switch (user.getRobot().getRobotOrientation()){
                                        case BOTTOM:
                                            user.getRobot().setRobotOrientation(Orientation.RIGHT);
                                            try {
                                                server.broadcast(new PlayerTurning(user.getClientID(), "counterclockwise"));
                                            } catch (IOException e) {
                                                throw new RuntimeException(e);
                                            }
                                        case TOP:
                                            user.getRobot().setRobotOrientation(Orientation.RIGHT);
                                            try {
                                                server.broadcast(new PlayerTurning(user.getClientID(), "clockwise"));
                                            } catch (IOException e) {
                                                throw new RuntimeException(e);
                                            }
                                    }
                            }
                        }
                    }
                }
                //After PlayerTurning or there is no need to turn
                Position position = user.getRobot().getPosition();
                int x = position.getX();
                int y = position.getY();
                Movement movement;
                switch (greenConveyorBelt.get(counter).getTiles().get(1).getOrientations().get(0)){
                    case TOP -> position.setY(y-1);
                    case LEFT -> position.setX(x-1);
                    case RIGHT -> position.setX(x+1);
                    case BOTTOM -> position.setY(y+1);
                }
                movement = new Movement(user.getClientID(), position.getX(), position.getY());
                try {
                    server.broadcast(movement);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                //check whether the robot needs to reboot
                MovementCheck movementCheck = new MovementCheck(game.getBoard());
                if(movementCheck.robotIsOffBoard(user) || movementCheck.fallingInPit(user)){
                    try {
                        server.broadcast(new Reboot(user.getClientID()));
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }
    }
}
