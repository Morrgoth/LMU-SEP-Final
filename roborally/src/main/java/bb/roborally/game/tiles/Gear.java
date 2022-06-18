package bb.roborally.game.tiles;

import bb.roborally.data.messages.game_events.PlayerTurning;
import bb.roborally.game.Player;
import bb.roborally.game.Position;
import bb.roborally.game.Robot;

import static bb.roborally.game.Orientation.*;

/**
 * @author Veronika Heckel
 * @author Muqiu Wang
 * @author Tolga Engin
 * @author Zeynab Baiani
 * @author Bence Ament
 * @author  Philipp Keyzman
 */
public class Gear extends Tile{
    final int activationOrder = 4;
    @Override
    public String getName() {
        return "Gear";
    }

    public enum GearType{
        GEAR_COUNTERCLOCKWISE,
        GEAR_CLOCKWISE
    }

    public PlayerTurning turnCounterclockwise(Robot robot){
        if(robot.getRobotOrientation() == RIGHT){
            robot.setRobotOrientation(TOP);
        }else if(robot.getRobotOrientation() == TOP){
            robot.setRobotOrientation(LEFT);
        }else if(robot.getRobotOrientation() == LEFT){
            robot.setRobotOrientation(BOTTOM);
        }else if(robot.getRobotOrientation() == BOTTOM){
            robot.setRobotOrientation(RIGHT);
        }
        return new PlayerTurning(robot.getClientID(), "counterclockwise");
    }

    public PlayerTurning turnClockwise(Robot robot){
        if(robot.getRobotOrientation() == RIGHT){
            robot.setRobotOrientation(BOTTOM);
        }else if(robot.getRobotOrientation() == BOTTOM){
            robot.setRobotOrientation(LEFT);
        }else if(robot.getRobotOrientation() == LEFT){
            robot.setRobotOrientation(TOP);
        }else if(robot.getRobotOrientation() == TOP){
            robot.setRobotOrientation(RIGHT);
        }
        return new PlayerTurning(robot.getClientID(), "clockwise");
    }
}
