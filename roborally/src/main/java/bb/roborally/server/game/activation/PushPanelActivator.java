package bb.roborally.server.game.activation;

import bb.roborally.protocol.Position;
import bb.roborally.protocol.game_events.Animation;
import bb.roborally.protocol.game_events.Movement;
import bb.roborally.protocol.game_events.Reboot;
import bb.roborally.server.Server;
import bb.roborally.server.game.*;
import bb.roborally.server.game.board.ServerCell;

import java.io.IOException;
import java.util.ArrayList;

import static bb.roborally.server.game.Orientation.*;

public class PushPanelActivator {
    private Server server;
    private Game game;

    private int register;

    public PushPanelActivator(Server server, Game game , int register) {
        this.server = server;
        this.game = game;
        this.register = register;
    }

    public void activate() throws IOException{
        Animation animation = new Animation("PushPanel");
        server.broadcast(animation);
        register = ActivationPhaseHandler.getRegister();

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

                    switch (pushPanels.get(counter).getTile("PushPanel").getOrientations().get(0)){
                        case TOP:
                            robot.setPosition(new Position(x, y-1));
                            break;
                        case LEFT:
                            robot.setPosition(new Position(x-1, y));
                            break;
                        case RIGHT:
                            robot.setPosition(new Position(x+1, y));
                            break;
                        case BOTTOM:
                            robot.setPosition(new Position(x, y+1));
                            break;
                    }
                    server.broadcast(new Movement(user.getClientID(), robot.getPosition().getX(), robot.getPosition().getY()));

                    //check whether the robot needs to reboot
                    MovementCheck movementCheck = new MovementCheck(game.getBoard());
                    if(movementCheck.robotIsOffServerBoard(user) || movementCheck.fallingInPit(user,0,0)){
                        RebootHandler rebootHandler = new RebootHandler(server, game, user);
                        rebootHandler.reboot();
                        server.broadcast(new Reboot(user.getClientID()));
                    }
                }
                counter += 1;
            }
        }
    }
}
