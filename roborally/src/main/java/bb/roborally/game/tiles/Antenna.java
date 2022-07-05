package bb.roborally.game.tiles;

import bb.roborally.game.Orientation;
import bb.roborally.game.Position;
import bb.roborally.game.Robot;

import java.util.ArrayList;

/**
 * @author  Philipp Keyzman
 */
public class Antenna extends Tile{
    private ArrayList<Integer> robotDistance;
    public Antenna () {

    }

    public Antenna(String isOnBoard, ArrayList<Orientation> orientations) {
        this.setIsOnBoard(isOnBoard);
        this.setOrientations(orientations);
    }

    @Override
    public String getType() {
        return "Antenna";
    }

    /*public ArrayList<Integer> getRobotDistance(Antenna antennaPosition,Robot robot) {
        int distanceColumn = (antennaPosition.getPosition().getColumn() - robot.getPosition().getColumn());
        int distanceRow =  (antennaPosition.getPosition().getRow() - robot.getPosition().getRow());
        int toPowerTwo = 2;

        int distanceColumnToPower = (int) Math.pow(distanceColumn,toPowerTwo);
        int distanceRowToPower = (int) Math.pow(distanceRow,toPowerTwo);

        int distance = distanceColumnToPower + distanceRowToPower;
        robotDistance.add(distance);
        return robotDistance;
    }*/




    @Override
    public String getResource(){
        String path = "";
            if (this.getOrientations().equals(Orientation.TOP)) {
                path = "TileImages/antenna.png";
            }
            if (this.getOrientations().equals(Orientation.RIGHT)) {
                path = "TTileImages/variants/antenna_right.png";
            }
            if (this.getOrientations().equals(Orientation.BOTTOM)) {
                path = "TileImages/variants/antenna_bottom.png";
            }
            if (this.getOrientations().equals(Orientation.LEFT)) {
                path = "/TileImages/variants/antenna_left.png";
            }
        return path;
    }

}


