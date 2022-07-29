package bb.roborally.protocol.map.tiles;

import bb.roborally.protocol.Orientation;

import java.util.ArrayList;


/**
 * @author Veronika Heckel
 * @author Muqiu Wang
 * @author Tolga Engin
 * @author Zeynab Baiani
 * @author Bence Ament
 * @author Philipp Keyzman
 */
public class ConveyorBelt extends Tile {
    private int speed;

    public ConveyorBelt(String isOnBoard, int speed, ArrayList<Orientation> orientations) {
        this.setIsOnBoard(isOnBoard);
        this.speed = speed;
        this.setOrientations(orientations);
    }

    public ConveyorBelt() {

    }

    @Override
    public String getType() {
        return "ConveyorBelt";
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {

        this.speed = speed;
    }
}