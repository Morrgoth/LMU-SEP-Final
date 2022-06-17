package bb.roborally.game.tiles;

import bb.roborally.data.messages.game_events.CheckPointReached;
import bb.roborally.game.Position;
import bb.roborally.game.Robot;

/**
 * @author Veronika Heckel
 * @author Muqiu Wang
 * @author Tolga Engin
 * @author Zeynab Baiani
 * @author Bence Ament
 * @autor  Philipp Keyzman
 */
public class CheckPoint extends Tile{
    final int activationOrder = 8;
    private int number;
    @Override
    String getName() {
        return "CheckPoint";
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
