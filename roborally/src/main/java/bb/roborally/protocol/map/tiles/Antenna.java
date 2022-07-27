package bb.roborally.protocol.map.tiles;

import bb.roborally.server.game.Orientation;

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
}


