package bb.roborally.server.game.activation;

import bb.roborally.protocol.game_events.Animation;
import bb.roborally.protocol.game_events.Movement;
import bb.roborally.protocol.game_events.PlayerTurning;
import bb.roborally.protocol.game_events.Reboot;
import bb.roborally.server.Server;
import bb.roborally.server.game.Game;
import bb.roborally.server.game.Orientation;
import bb.roborally.server.game.Position;
import bb.roborally.server.game.User;
import bb.roborally.server.game.board.ServerCell;

import java.io.IOException;
import java.util.ArrayList;

public class BlueConveyorBeltActivator {

    private Server server;
    private Game game;

    public BlueConveyorBeltActivator(Server server, Game game) {
        this.server = server;
        this.game = game;
    }

    public void activate() throws IOException{
        Animation animation = new Animation("BlueConveyorBelt");
            server.broadcast(animation);
        ArrayList<ServerCell> belts = game.getBoard().getBlueConveyorBelts();
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
                Position position = user.getRobot().getPosition();
                int x = position.getX();
                int y = position.getY();
                Orientation orientation0 = game.getBoard().get(x, y).getTile("ConveyorBelt").getOrientations().get(0);
                //the first step
                if(orientation0 == Orientation.LEFT){
                    position.setX(x-1);
                }else if(orientation0 == Orientation.BOTTOM){
                    position.setY(y+1);
                }else if(orientation0 == Orientation.RIGHT){
                    position.setX(x+1);
                }else if(orientation0 == Orientation.TOP){
                    position.setY(y-1);
                }

                //check if the robot needs to reboot after the first step
                MovementCheck movementCheck = new MovementCheck(game.getBoard());
                if(movementCheck.robotIsOffServerBoard(user) || movementCheck.fallingInPit(user, 0, 0)){
                    RebootHandler rebootHandler = new RebootHandler(server, game, user);
                    rebootHandler.reboot();
                    server.broadcast(new Reboot(user.getClientID()));
                }

                //check if the robot is still on the belt: yes->belt still works, no->movement ends
                if(!game.getBoard().get(position.getX(), position.getY()).hasTile("ConveyorBelt")){
                    server.broadcast(new Movement(user.getClientID(), position.getX(), position.getY()));
                }else{
                    //firstly, check if the robot needs to turn
                    Orientation orientation1 = game.getBoard().get(position.getX(), position.getY()).getTile("ConveyorBelt").getOrientations().get(0);
                    if(orientation1 != orientation0 && !user.getRobot().isOpposite(orientation1)){
                        switch (orientation1){
                            case TOP:
                                if(orientation0 == Orientation.RIGHT){
                                    user.getRobot().turnCounterclockwise90Deg();
                                    server.broadcast(new PlayerTurning(user.getClientID(), "counterclockwise"));
                                }else if(orientation0 == Orientation.LEFT){
                                    user.getRobot().turnClockwise90Deg();
                                    server.broadcast(new PlayerTurning(user.getClientID(), "clockwise"));
                                }
                                break;
                            case RIGHT:
                                if(orientation0 == Orientation.BOTTOM){
                                    user.getRobot().turnCounterclockwise90Deg();
                                    server.broadcast(new PlayerTurning(user.getClientID(), "counterclockwise"));
                                }else if(orientation0 == Orientation.TOP) {
                                    user.getRobot().turnClockwise90Deg();
                                    server.broadcast(new PlayerTurning(user.getClientID(), "clockwise"));
                                }
                                break;
                            case BOTTOM:
                                if(orientation0 == Orientation.LEFT){
                                    user.getRobot().turnCounterclockwise90Deg();
                                    server.broadcast(new PlayerTurning(user.getClientID(), "counterclockwise"));
                                }else if(orientation0 == Orientation.RIGHT){
                                    user.getRobot().turnClockwise90Deg();
                                    server.broadcast(new PlayerTurning(user.getClientID(), "clockwise"));
                                }
                                break;
                            case LEFT:
                                if(orientation0 == Orientation.TOP){
                                    user.getRobot().turnCounterclockwise90Deg();
                                    server.broadcast(new PlayerTurning(user.getClientID(), "counterclockwise"));
                                }else if(orientation0 == Orientation.BOTTOM){
                                    user.getRobot().turnClockwise90Deg();
                                    server.broadcast(new PlayerTurning(user.getClientID(), "clockwise"));
                                }
                                break;
                        }
                    }

                    //After turning or no need to turn, step 2 begins
                    Position position1 = user.getRobot().getPosition();
                    int x1 = position1.getX();
                    int y1 = position1.getY();
                    if(orientation1 == Orientation.LEFT){
                        position1.setX(x1-1);
                        server.broadcast(new Movement(user.getClientID(), position1.getX(), position1.getY()));
                    }else if(orientation1 == Orientation.BOTTOM){
                        position1.setY(y1+1);
                        server.broadcast(new Movement(user.getClientID(), position1.getX(), position1.getY()));
                    }else if(orientation1 == Orientation.RIGHT){
                        position1.setX(x1+1);
                        server.broadcast(new Movement(user.getClientID(), position1.getX(), position1.getY()));
                    }else if(orientation1 == Orientation.TOP){
                        position1.setY(y1-1);
                        server.broadcast(new Movement(user.getClientID(), position1.getX(), position1.getY()));
                    }

                    //check if the robot needs to reboot after the second step
                    MovementCheck movementCheck1 = new MovementCheck(game.getBoard());
                    if(movementCheck1.robotIsOffServerBoard(user) || movementCheck1.fallingInPit(user, 0, 0)){
                        RebootHandler rebootHandler = new RebootHandler(server, game, user);
                        rebootHandler.reboot();
                        server.broadcast(new Reboot(user.getClientID()));
                    }

                    //check if the robot still on the belt: yes->check if the robot needs to turn->movement ends, no->movement ends
                    if(!game.getBoard().get(position1.getX(), position1.getY()).hasTile("ConveyorBelt")){
                        server.broadcast(new Movement(user.getClientID(), position1.getX(), position1.getY()));
                    }else{
                        //check if the robot needs to turn
                        Orientation orientation2 = game.getBoard().get(position1.getX(), position1.getY()).getTile("ConveyorBelt").getOrientations().get(0);
                        if(orientation2 != orientation1 && !user.getRobot().isOpposite(orientation2)){
                            switch (orientation2){
                                case TOP:
                                    if(orientation1 == Orientation.RIGHT){
                                        user.getRobot().turnCounterclockwise90Deg();
                                        server.broadcast(new PlayerTurning(user.getClientID(), "counterclockwise"));
                                    }else if(orientation1 == Orientation.LEFT){
                                        user.getRobot().turnClockwise90Deg();
                                        server.broadcast(new PlayerTurning(user.getClientID(), "clockwise"));
                                    }
                                    break;
                                case RIGHT:
                                    if(orientation1 == Orientation.BOTTOM){
                                        user.getRobot().turnCounterclockwise90Deg();
                                        server.broadcast(new PlayerTurning(user.getClientID(), "counterclockwise"));
                                    }else if(orientation1 == Orientation.TOP) {
                                        user.getRobot().turnClockwise90Deg();
                                        server.broadcast(new PlayerTurning(user.getClientID(), "clockwise"));
                                    }
                                    break;
                                case BOTTOM:
                                    if(orientation1 == Orientation.LEFT){
                                        user.getRobot().turnCounterclockwise90Deg();
                                        server.broadcast(new PlayerTurning(user.getClientID(), "counterclockwise"));
                                    }else if(orientation1 == Orientation.RIGHT){
                                        user.getRobot().turnClockwise90Deg();
                                        server.broadcast(new PlayerTurning(user.getClientID(), "clockwise"));
                                    }
                                    break;
                                case LEFT:
                                    if(orientation1 == Orientation.TOP){
                                        user.getRobot().turnCounterclockwise90Deg();
                                        server.broadcast(new PlayerTurning(user.getClientID(), "counterclockwise"));
                                    }else if(orientation1 == Orientation.BOTTOM){
                                        user.getRobot().turnClockwise90Deg();
                                        server.broadcast(new PlayerTurning(user.getClientID(), "clockwise"));
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
