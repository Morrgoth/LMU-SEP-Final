package bb.roborally.game.tiles;

import bb.roborally.data.messages.game_events.PlayerTurning;
import bb.roborally.game.Orientation;
import bb.roborally.game.Player;
import bb.roborally.game.Position;
import bb.roborally.game.Robot;

import java.util.ArrayList;

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
    private String type;
    private String isOnBoard;
    private String direction;

    public Gear(String type, String isOnBoard, String direction) {
        this.type = type;
        this.isOnBoard = isOnBoard;
        this.direction = direction;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    @Override
    public String getType() {
        return "Gear";
    }

    public Gear() {
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