package bb.roborally.protocol.map.tiles;

import bb.roborally.protocol.Orientation;
import bb.roborally.server.game.Robot;

import java.util.ArrayList;

/**
 * @author Muqui Wang
 */
public class RestartPoint extends Tile{

    private ArrayList<Robot> rebootQueue = new ArrayList<>();

    public RestartPoint() {
    }

    public RestartPoint( String isOnBoard, ArrayList<Orientation> orientations) {
        this.setIsOnBoard(isOnBoard);
        this.setOrientations(orientations);
    }

    @Override
    public String getType() {
        return "RestartPoint";
    }
}

