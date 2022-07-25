package bb.roborally.server.game.activation;

import bb.roborally.protocol.game_events.Animation;
import bb.roborally.protocol.game_events.Movement;
import bb.roborally.protocol.game_events.Reboot;
import bb.roborally.server.Server;
import bb.roborally.server.game.*;
import bb.roborally.server.game.board.ServerCell;

import java.io.IOException;
import java.util.ArrayList;

public class PushPanelActivator {
    private Server server;
    private Game game;
    private int register;

    public PushPanelActivator(Server server, Game game, int register) {
        this.server = server;
        this.game = game;
        this.register = register;
    }

    public void activate() throws IOException{
        Animation animation = new Animation("PushPanel");
        server.broadcast(animation);

        //get pushPanels with the numbers that contain the actual register-number, the other pushPanels won't be activated
        ArrayList<ServerCell> pushPanels = game.getBoard().getPushPanels(register);
        for(User user: game.getPlayerQueue().getUsers()){
            int counter = 0;
            Position position = user.getRobot().getPosition();
            while(counter < pushPanels.size()){
                if(pushPanels.get(counter).getPosition().equals(position)){
                    Robot robot = user.getRobot();
                    int x = robot.getPosition().getX();
                    int y = robot.getPosition().getY();
                    Movement movement;
                    switch (pushPanels.get(counter).getTiles().get(1).getOrientations().get(0)){
                        case TOP -> robot.setPosition(new Position(x, y+1));
                        case LEFT -> robot.setPosition(new Position(x+1, y));
                        case RIGHT -> robot.setPosition(new Position(x-1, y));
                        case BOTTOM -> robot.setPosition(new Position(x, y-1));
                    }
                    movement = new Movement(user.getClientID(), robot.getPosition().getX(), robot.getPosition().getY());
                    server.broadcast(movement);

                    //check whether the robot needs to reboot
                    MovementCheck movementCheck = new MovementCheck(game.getBoard());
                    if(movementCheck.robotIsOffServerBoard(user) || movementCheck.fallingInPit(user,0,0)){
                        server.broadcast(new Reboot(user.getClientID()));
                    }
                }
                counter += 1;
            }
        }
    }
}
