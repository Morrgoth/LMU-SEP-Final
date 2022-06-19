package bb.roborally.game.tiles;

import bb.roborally.game.Position;
import bb.roborally.game.Robot;

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
    private int robotClientID;

    public StartPoint(){

    }

    public StartPoint(Position startingPoint, Robot robot){
        this.startingPoint = startingPoint;
        this.robotClientID = robot.getClientID();
    }



    @Override
    String getName() {
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
