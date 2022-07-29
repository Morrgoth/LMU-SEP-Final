package bb.roborally.server.game.activation;

import bb.roborally.protocol.game_events.Animation;
import bb.roborally.protocol.game_events.PlayerTurning;
import bb.roborally.server.Server;
import bb.roborally.server.game.Game;
import bb.roborally.protocol.Orientation;
import bb.roborally.server.game.Robot;
import bb.roborally.server.game.User;
import bb.roborally.server.game.board.ServerCell;

import java.io.IOException;
import java.util.ArrayList;

/**
 * @author Muqiu Wang
 */
public class GearActivator {
    private Server server;
    private Game game;

    public GearActivator(Server server, Game game) {
        this.server = server;
        this.game = game;
    }

    public void activate() throws IOException{
        Animation animation = new Animation("Gear");
        server.broadcast(animation);

        ArrayList<ServerCell> clockwiseGears = game.getBoard().getClockwiseGears();
        for(User user: game.getPlayerQueue().getUsers()){
            boolean isOnTile = false;
            int counter = 0;
            while(counter < clockwiseGears.size() && !isOnTile){
                if(clockwiseGears.get(counter).getPosition().equals(user.getRobot().getPosition())){
                    isOnTile = true;
                }
                counter += 1;
            }
            if(isOnTile){
                Robot robot = user.getRobot();
                switch (robot.getRobotOrientation()){
                    case TOP -> robot.setRobotOrientation(Orientation.RIGHT);
                    case RIGHT -> robot.setRobotOrientation(Orientation.BOTTOM);
                    case BOTTOM -> robot.setRobotOrientation(Orientation.LEFT);
                    case LEFT -> robot.setRobotOrientation(Orientation.TOP);
                }
                PlayerTurning playerTurning = new PlayerTurning(user.getClientID(), "clockwise");
                server.broadcast(playerTurning);
            }
        }

        ArrayList<ServerCell> counterclockwiseGears = game.getBoard().getCounterclockwiseGears();
        for(User user: game.getPlayerQueue().getUsers()){
            boolean isOnTile = false;
            int counter = 0;
            while(counter < clockwiseGears.size() && !isOnTile){
                if(counterclockwiseGears.get(counter).getPosition().equals(user.getRobot().getPosition())){
                    isOnTile = true;
                }
                counter += 1;
            }
            if(isOnTile){
                Robot robot = user.getRobot();
                switch (robot.getRobotOrientation()){
                    case TOP -> robot.setRobotOrientation(Orientation.LEFT);
                    case LEFT -> robot.setRobotOrientation(Orientation.BOTTOM);
                    case BOTTOM -> robot.setRobotOrientation(Orientation.RIGHT);
                    case RIGHT -> robot.setRobotOrientation(Orientation.TOP);
                }
                PlayerTurning playerTurning = new PlayerTurning(user.getClientID(), "counterclockwise");
                server.broadcast(playerTurning);
            }
        }
    }
}
