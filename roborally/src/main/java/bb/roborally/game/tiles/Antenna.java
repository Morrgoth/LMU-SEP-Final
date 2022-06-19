package bb.roborally.game.tiles;

import bb.roborally.data.messages.game_events.BlockAction;
import bb.roborally.game.Position;
import bb.roborally.game.Robot;

import java.util.ArrayList;

/**
 * @author Veronika Heckel
 * @author Muqiu Wang
 * @author Tolga Engin
 * @author Zeynab Baiani
 * @author Bence Ament
 * @autor  Philipp Keyzman
 */
public class Antenna extends Tile{

    private static Position antennaPosition;
    private ArrayList<Integer> robotDistance;
    public Antenna () {

    }
    public Antenna(Position antennaPosition){

    }

    @Override
    String getName() {
        return "Antenna";
    }

    public static Position getAntennaPosition() {
        return antennaPosition;
    }

    public void setAntennaPosition(Position antennaPosition) {
        this.antennaPosition = antennaPosition;
    }
    public ArrayList<Integer> getRobotDistance(Robot robot) {
        int distance;
        int robotColumnPosition = robot.getPosition().getColumn();
        int robotRowPosition = robot.getPosition().getRow();

        distance = robotColumnPosition + robotRowPosition;
        robotDistance.add(distance);
        return robotDistance;
    }
    public BlockAction blockLaser(BoardLaser boardLaser){

        if( boardLaser.getBoardLaserPosition() == antennaPosition){
            return null;
        }
        return new BlockAction(BlockAction.clientID);
    }

}
