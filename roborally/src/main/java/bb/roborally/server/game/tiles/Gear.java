package bb.roborally.server.game.tiles;

import bb.roborally.server.game.Orientation;

import java.util.ArrayList;

/**
 * @author Veronika Heckel
 * @author Muqiu Wang
 * @author Tolga Engin
 * @author Zeynab Baiani
 * @author Bence Ament
 * @author  Philipp Keyzman
 */
public class Gear extends Tile{

    public Gear( String isOnBoard, ArrayList<Orientation> orientations) {
        this.setIsOnBoard(isOnBoard);
        this.setOrientations(orientations);
    }

    @Override
    public String getType() {
        return "Gear";
    }

    public Gear() {
    }

    //public PlayerTurning turnCounterclockwise(Robot robot){
    //    if(robot.getRobotOrientation() == RIGHT){
    //        robot.setRobotOrientation(TOP);
    //    }else if(robot.getRobotOrientation() == TOP){
    //        robot.setRobotOrientation(LEFT);
    //    }else if(robot.getRobotOrientation() == LEFT){
    //        robot.setRobotOrientation(BOTTOM);
    //    }else if(robot.getRobotOrientation() == BOTTOM){
    //        robot.setRobotOrientation(RIGHT);
    //    }
    //    return new PlayerTurning(robot.getClientID(), "counterclockwise");
    //}
//
    //public PlayerTurning turnClockwise(Robot robot){
    //    if(robot.getRobotOrientation() == RIGHT){
    //        robot.setRobotOrientation(BOTTOM);
    //    }else if(robot.getRobotOrientation() == BOTTOM){
    //        robot.setRobotOrientation(LEFT);
    //    }else if(robot.getRobotOrientation() == LEFT){
    //        robot.setRobotOrientation(TOP);
    //    }else if(robot.getRobotOrientation() == TOP){
    //        robot.setRobotOrientation(RIGHT);
    //    }
    //    return new PlayerTurning(robot.getClientID(), "clockwise");
    //}

    @Override
    public String getResource(){
        String path = "";
        if (this.getOrientations().get(0).toString().equals("clockwise")) {
            path = "/TileImages/gear_clockwise.png";
        }
        else {
            path = "/TileImages/gear_counter_clockwise.png";
        }
        return path;
    }

}
