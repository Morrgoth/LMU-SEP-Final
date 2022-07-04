package bb.roborally.game.tiles;

import bb.roborally.game.Orientation;
import bb.roborally.game.Position;
import bb.roborally.game.Robot;

import java.util.ArrayList;

/**
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
        this.setIsOnBoard(isOnBoard);
    }

    public StartPoint(Position startingPoint, Robot robot){
        this.startingPoint = startingPoint;
        this.robotClientID = robot.getClientID();
    }
    public StartPoint(Position startingPoint){
        this.startingPoint = startingPoint;
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

    @Override
    public String getResource(){
        String path = "";
        path = "![](../../../../../resources/TileImages/starting_point.png)";
        return path;
    }
}
