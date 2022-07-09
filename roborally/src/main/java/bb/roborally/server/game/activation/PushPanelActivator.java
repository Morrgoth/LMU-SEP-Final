package bb.roborally.server.game.activation;

import bb.roborally.protocol.game_events.Animation;
import bb.roborally.protocol.game_events.Movement;
import bb.roborally.server.Server;
import bb.roborally.server.game.*;
import bb.roborally.server.game.board.Cell;

import java.io.IOException;
import java.util.ArrayList;
import java.util.spi.ToolProvider;

public class PushPanelActivator {
    private Server server;
    private Game game;
    private int register;

    public PushPanelActivator(Server server, Game game, int register) {
        this.server = server;
        this.game = game;
        this.register = register;
    }

    public void activate(){
        Animation animation = new Animation("PushPanel");
        try {
            server.broadcast(animation);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        //get pushPanels with the numbers that contain the actual register-number, the other pushPanels won't be activated
        ArrayList<Cell> pushPanels = game.getBoard().getPushPanels(register);
        for(User user: game.getPlayerQueue().getUsers()){
            int counter = 0;
            Position position = user.getRobot().getPosition();
            while(counter < pushPanels.size()){
                if(pushPanels.get(counter).getPosition().equals(position)){
                    Robot robot = user.getRobot();
                    int x = robot.getPosition().getX();
                    int y = robot.getPosition().getY();
                    Movement movement;
                    switch (pushPanels.get(counter).getTiles().get(2).getOrientations().get(0)){
                        case TOP -> robot.setPosition(new Position(x, y+1));
                        case LEFT -> robot.setPosition(new Position(x+1, y));
                        case RIGHT -> robot.setPosition(new Position(x-1, y));
                        case BOTTOM -> robot.setPosition(new Position(x, y-1));
                    }
                    movement = new Movement(user.getClientID(), robot.getPosition().getX(), robot.getPosition().getY());
                    try {
                        server.broadcast(movement);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }

                    //TODO:Add Checkers(Pit-->Restart, EnergySpace-->EnergyCube+1)
                }
                counter += 1;
            }
        }
    }
}
