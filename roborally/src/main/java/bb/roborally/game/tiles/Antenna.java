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
    private ArrayList<Robot> robotDistance;
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
    public ArrayList<Robot> getRobotDistance(Position antennaPosition,ArrayList<Robot> robotDistance) {
        for (int i= 0 ; i <= 0; i++){
            int robotColumnPosition = robotDistance.get(i).getPosition().getColumn();
            int robotRowPosition = robotDistance.get(i).getPosition().getRow();
            int distance = robotColumnPosition + robotRowPosition;
        }
        return robotDistance;
    }

}
