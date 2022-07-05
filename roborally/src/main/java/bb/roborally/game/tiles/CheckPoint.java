package bb.roborally.game.tiles;

import bb.roborally.data.messages.game_events.CheckPointReached;
import bb.roborally.game.Orientation;
import bb.roborally.game.Position;
import bb.roborally.game.Robot;

import java.util.ArrayList;

/**
 * @author Muqiu Wang
 */
public class CheckPoint extends Tile{
    final int activationOrder = 8;
    private Position position;
    private String type;
    private String isOnBoard;
    private ArrayList<Orientation> orientations;
    private int number;

    public CheckPoint() {
    }

    public CheckPoint(String type, String isOnBoard, ArrayList<Orientation> orientations, int number) {
        this.type = type;
        this.setIsOnBoard(isOnBoard);
        this.setOrientations(orientations);
        this.number = number;
    }

    @Override
    public String getType() {
        return "CheckPoint";
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    //public CheckPointReached checkPointReached(Robot robot){
    //    if(robot.getCheckPointTokens() < this.number){
    //        robot.addCheckPointTokens();
    //    }else{
    //        return null;
    //    }
    //    return new CheckPointReached(robot.getClientID(), robot.getCheckPointTokens());
    //}
}
