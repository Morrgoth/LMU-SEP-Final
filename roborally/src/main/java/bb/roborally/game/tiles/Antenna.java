package bb.roborally.game.tiles;

import bb.roborally.data.messages.game_events.AntennaState;
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

    private Position antennaPosition;
    private ArrayList<Integer> robotDistance;
    public Antenna () {

    }
    public Antenna(Position antennaPosition){

    }

    @Override
    String getName() {
        return "Antenna";
    }

    public Position getAntennaPosition() {
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

}
