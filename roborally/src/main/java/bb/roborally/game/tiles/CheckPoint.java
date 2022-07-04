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
    private int number;

    public CheckPoint() {
    }

    public CheckPoint(String isOnBoard, ArrayList<Orientation> orientations, int number) {
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

    public CheckPointReached checkPointReached(Robot robot){
        if(robot.getCheckPointTokens() < this.number){
            robot.gainCheckPointTokens();
        }else{
            return null;
        }
        return new CheckPointReached(robot.getClientID(), robot.getCheckPointTokens());
    }
}
