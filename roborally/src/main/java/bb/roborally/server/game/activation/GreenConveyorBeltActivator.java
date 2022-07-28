package bb.roborally.server.game.activation;

import bb.roborally.protocol.game_events.Animation;
import bb.roborally.protocol.game_events.Movement;
import bb.roborally.protocol.game_events.PlayerTurning;
import bb.roborally.protocol.game_events.Reboot;
import bb.roborally.server.Server;
import bb.roborally.server.game.*;
import bb.roborally.server.game.board.ServerCell;

import java.io.IOException;
import java.util.ArrayList;

public class GreenConveyorBeltActivator {
    private Server server;
    private Game game;

    public GreenConveyorBeltActivator(Server server, Game game) {
        this.server = server;
        this.game = game;
    }

    public void activate() throws IOException{
        Animation animation = new Animation("GreenConveyorBelt");
        server.broadcast(animation);

        ArrayList<ServerCell> greenConveyorBelt = game.getBoard().getGreenConveyorBelts();
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
                Position position = user.getRobot().getPosition();
                int x = position.getX();
                int y = position.getY();
                Orientation orientation0 = game.getBoard().get(x, y).getTile("ConveyorBelt").getOrientations().get(0);
                //the first step
                if(orientation0 == Orientation.LEFT){
                    position.setX(x-1);
                    //server.broadcast(new Movement(user.getClientID(), position.getX(), position.getY()));
                }else if(orientation0 == Orientation.BOTTOM){
                    position.setY(y+1);
                    //server.broadcast(new Movement(user.getClientID(), position.getX(), position.getY()));
                }else if(orientation0 == Orientation.RIGHT){
                    position.setX(x+1);
                    //server.broadcast(new Movement(user.getClientID(), position.getX(), position.getY()));
                }else if(orientation0 == Orientation.TOP){
                    position.setY(y-1);
                    //server.broadcast(new Movement(user.getClientID(), position.getX(), position.getY()));
                }

                //check if the robot needs to reboot after the step
                try{
                    server.broadcast(new Movement(user.getClientID(), position.getX(), position.getY()));
                    MovementCheck movementCheck = new MovementCheck(game.getBoard());
                    if(movementCheck.fallingInPit(user, 0, 0)){
                        RebootHandler rebootHandler = new RebootHandler(server, game, user);
                        rebootHandler.reboot();
                        server.broadcast(new Reboot(user.getClientID()));
                    }
                }catch (IndexOutOfBoundsException e){
                    RebootHandler rebootHandler = new RebootHandler(server, game, user);
                    rebootHandler.reboot();
                    Reboot reboot = new Reboot(user.getClientID());
                    server.broadcast(reboot);
                }

                //check if the robot is still on the belt: yes->belt still works, no->movement ends
                if(game.getBoard().get(user.getRobot().getPosition().getX(), user.getRobot().getPosition().getY()).hasTile("ConveyorBelt")){
                    //check if the robot needs to turn
                    Orientation orientation1 = game.getBoard().get(position.getX(), position.getY()).getTile("ConveyorBelt").getOrientations().get(0);
                    if(orientation1 != orientation0){
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
                }
            }
        }
    }
}
