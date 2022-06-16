package bb.roborally.game.tiles;

import bb.roborally.data.messages.game_events.Movement;
import bb.roborally.game.Orientation;
import bb.roborally.game.Position;
import bb.roborally.game.Robot;


/**
 * @author Veronika Heckel
 * @author Muqiu Wang
 * @author Tolga Engin
 * @author Zeynab Baiani
 * @author Bence Ament
 * @autor  Philipp Keyzman
 *
 */
public class PushPanel extends Tile{
    final int activationOrder = 3;
    private int labeledNumber1;
    private int labeledNumber2;
    private Orientation orientation;

    @Override
    String getName() {
        return "PushPanel";
    }

    public Movement pushPanelEffect(Robot robot, int endRegister){
        if(endRegister == labeledNumber1 || endRegister == labeledNumber2){
            if(this.orientation == Orientation.FRONT){
                return new Movement(robot.getClientID(), 1, 0);
            }else if(this.orientation == Orientation.RIGHT){
                return new Movement(robot.getClientID(), 0, 1);
            }else if(this.orientation == Orientation.BACK){
                return new Movement(robot.getClientID(), -1, 0);
            }else if(this.orientation == Orientation.LEFT){
                return new Movement(robot.getClientID(), 0, -1);
            }
        }else{
            return new Movement(robot.getClientID(), 0, 0);
        }
        return null;
    }
}
