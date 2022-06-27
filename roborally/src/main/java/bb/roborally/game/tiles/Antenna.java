package bb.roborally.game.tiles;

import bb.roborally.game.Position;
import bb.roborally.game.Robot;

import java.util.ArrayList;

/**
 * @author Veronika Heckel
 * @author Muqiu Wang
 * @author Tolga Engin
 * @author Zeynab Baiani
 * @author Bence Ament
 * @author  Philipp Keyzman
 */
public class Antenna extends Tile{

    private static Position antennaPosition;
    private ArrayList<Integer> robotDistance;
    public Antenna () {

    }
    public Antenna(Position antennaPosition){

    }

    @Override
    public String getType() {
        return "Antenna";
    }

    public static Position getAntennaPosition() {
        return antennaPosition;
    }

    public void setAntennaPosition(Position antennaPosition) {
        this.antennaPosition = antennaPosition;
    }
    public ArrayList<Integer> getRobotDistance(Antenna antennaPosition,Robot robot) {
        int distanceColumn = (antennaPosition.getPosition().getColumn() - robot.getPosition().getColumn());
        int distanceRow =  (antennaPosition.getPosition().getRow() - robot.getPosition().getRow());
        int toPowerTwo = 2;

        int distanceColumnToPower = (int) Math.pow(distanceColumn,toPowerTwo);
        int distanceRowToPower = (int) Math.pow(distanceRow,toPowerTwo);

        int distance = distanceColumnToPower + distanceRowToPower;
        robotDistance.add(distance);
        return robotDistance;
    }

}
