package bb.roborally.protocol.map.tiles;

import bb.roborally.server.game.Orientation;

import java.util.ArrayList;

/**
 * @author Muqiu Wang
 */
public class CheckPoint extends Tile{
    private int count;

    public CheckPoint() {
    }

    public CheckPoint(String isOnBoard, ArrayList<Orientation> orientations, int count) {
        this.setIsOnBoard(isOnBoard);
        this.setOrientations(orientations);
        this.count = count;
    }

    @Override
    public String getType() {
        return "CheckPoint";
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
