package bb.roborally.game.tiles;

import bb.roborally.game.Orientation;
import bb.roborally.game.Position;
import bb.roborally.game.Robot;

import java.util.ArrayList;

/**
 * @author  Philipp Keyzman
 */
public class StartPoint extends Tile {

    public StartPoint() {
    }

    public StartPoint(String isOnBoard) {
        this.setIsOnBoard(isOnBoard);
    }

    //public StartPoint(Position startingPoint, Robot robot){
    //    this.startingPoint = startingPoint;
    //    this.robotClientID = robot.getClientID();
    //}



    @Override
    public String getType() {
        return "StartPoint";
    }

    @Override
    public String getResource(){
        String path = "";
        path = "/TileImages/starting_point.png";
        return path;
    }
}
