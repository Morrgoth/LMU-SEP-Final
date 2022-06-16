package bb.roborally.game.tiles;

import bb.roborally.data.messages.game_events.PlayerTurning;
import bb.roborally.game.Player;
import bb.roborally.game.Position;
import bb.roborally.game.Robot;

/**
 * @author Veronika Heckel
 * @author Muqiu Wang
 * @author Tolga Engin
 * @author Zeynab Baiani
 * @author Bence Ament
 * @autor  Philipp Keyzman
 */
public class Gear extends Tile{
    final int activationOrder = 4;
    @Override
    String getName() {
        return "Gear";
    }

    public enum GearType{
        GEAR_COUNTERCLOCKWISE,
        GEAR_CLOCKWISE
    }

    public PlayerTurning turnCounterclockwise(Robot robot){
        if(robot.getOrientation() == Robot.Orientation.FRONT){
            robot.setOrientation(Robot.Orientation.LEFT);
        }else if(robot.getOrientation() == Robot.Orientation.LEFT){
            robot.setOrientation(Robot.Orientation.BACK);
        }else if(robot.getOrientation() == Robot.Orientation.BACK){
            robot.setOrientation(Robot.Orientation.RIGHT);
        }else if(robot.getOrientation() == Robot.Orientation.RIGHT){
            robot.setOrientation(Robot.Orientation.FRONT);
        }
        return new PlayerTurning(robot.getClientID(), "counterclockwise");
    }

    public PlayerTurning turnClockwise(Robot robot){
        if(robot.getOrientation() == Robot.Orientation.FRONT){
            robot.setOrientation(Robot.Orientation.RIGHT);
        }else if(robot.getOrientation() == Robot.Orientation.RIGHT){
            robot.setOrientation(Robot.Orientation.BACK);
        }else if(robot.getOrientation() == Robot.Orientation.BACK){
            robot.setOrientation(Robot.Orientation.LEFT);
        }else if(robot.getOrientation() == Robot.Orientation.LEFT){
            robot.setOrientation(Robot.Orientation.FRONT);
        }
        return new PlayerTurning(robot.getClientID(), "clockwise");
    }
}
