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
    String getName() {
        return "Gear";
    }

    public enum GearType{
        GEAR_COUNTERCLOCKWISE,
        GEAR_CLOCKWISE
    }

    public PlayerTurning turnCounterclockwise(Robot robot){
        if(robot.getOrientation() == RIGHT){
            robot.setOrientation(TOP);
        }else if(robot.getOrientation() == TOP){
            robot.setOrientation(LEFT);
        }else if(robot.getOrientation() == LEFT){
            robot.setOrientation(BOTTOM);
        }else if(robot.getOrientation() == BOTTOM){
            robot.setOrientation(RIGHT);
        }
        return new PlayerTurning(robot.getClientID(), "counterclockwise");
    }

    public PlayerTurning turnClockwise(Robot robot){
        if(robot.getOrientation() == RIGHT){
            robot.setOrientation(BOTTOM);
        }else if(robot.getOrientation() == BOTTOM){
            robot.setOrientation(LEFT);
        }else if(robot.getOrientation() == LEFT){
            robot.setOrientation(TOP);
        }else if(robot.getOrientation() == TOP){
            robot.setOrientation(RIGHT);
        }
        return new PlayerTurning(robot.getClientID(), "clockwise");
    }
}
