package bb.roborally.game.tiles;

import bb.roborally.data.messages.game_events.CheckPointReached;
import bb.roborally.game.Position;
import bb.roborally.game.Robot;

/**
 * @author Muqiu Wang
 */
public class CheckPoint extends Tile{
    final int activationOrder = 8;
    private int number;

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
