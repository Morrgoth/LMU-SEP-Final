package bb.roborally.game.tiles;

import bb.roborally.game.Orientation;
import bb.roborally.game.Position;
import bb.roborally.game.Robot;

import java.util.ArrayList;

/**
 * @author Veronika Heckel
 * @author Muqiu Wang
 * @author Tolga Engin
 * @author Zeynab Baiani
 * @author Bence Ament
 * @author  Philipp Keyzman
 */
public class StartPoint extends Tile{
    private Position startingPoint;
    private String type;
    private String isOnBoard;
    private int robotClientID;

    public StartPoint(){

    }

    public StartPoint(String type, String isOnBoard) {
        this.type = type;
        this.isOnBoard = isOnBoard;
    }

    public StartPoint(Position startingPoint, Robot robot){
        this.startingPoint = startingPoint;
        this.robotClientID = robot.getClientID();
    }



    @Override
    public String getType() {
        return "StartPoint";
    }

    public int getRobotClientID() {
        return robotClientID;
    }

    public void setRobotClientID(int robotClientID) {
        this.robotClientID = robotClientID;
    }

    public Position getStartingPoint() {
        return startingPoint;
    }

    public void setStartingPoint(Position startingPoint) {
        this.startingPoint = startingPoint;
    }
}
