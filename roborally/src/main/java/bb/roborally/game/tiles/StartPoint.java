package bb.roborally.game.tiles;

import bb.roborally.game.Position;
import bb.roborally.game.Robot;

/**
 * @author  Philipp Keyzman
 */
public class StartPoint extends Tile{
    private Position startingPoint;
    private int robotClientID;

    public StartPoint(){

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
}
