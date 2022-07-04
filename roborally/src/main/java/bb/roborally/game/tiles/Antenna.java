package bb.roborally.game.tiles;


import bb.roborally.game.Orientation;
import bb.roborally.game.Position;
import bb.roborally.game.Robot;
import javafx.scene.layout.StackPane;

import java.util.ArrayList;


/**
 * @author  Philipp Keyzman
 */
public class Antenna extends Tile{

    private static Position antennaPosition;
    private String type;
    private String isOnBoard;
    private ArrayList<Orientation> orientations;
    private ArrayList<Integer> robotDistance;

    public Antenna () {

    }

    public Antenna(String type, String isOnBoard, ArrayList<Orientation> orientations) {
        this.type = type;
        this.setIsOnBoard(isOnBoard);
        this.setOrientations(orientations);
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

    @Override
    public String getResource(){
        String path = "";
            if (this.getPosition().equals(Orientation.TOP)) {
                path = "TileImages/antenna.png)";
            }
            if (this.getPosition().equals(Orientation.RIGHT)) {
                path = "TileImages/variants/antenna_right.png)";
            }
            if (this.getPosition().equals(Orientation.BOTTOM)) {
                path = "TileImages/variants/antenna_bottom.png)";
            }
            if (this.getPosition().equals(Orientation.LEFT)) {
                path = "/TileImages/variants/antenna_left.png)";
            }
        return path;
    }

}


