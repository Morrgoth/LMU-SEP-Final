package bb.roborally.server.game.tiles;

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


    @Override
    public String getResource() {
        String path = "";
        if (this.getCount() == 1) {
            path = "/TileImages/checkpoint1_top.png";
        }
        if (this.getCount() == 2) {
            path = "/TileImages/checkpoint2_top.png";
        }
        if (this.getCount() == 3) {
            path = "/TileImages/checkpoint3_top.png";
        }
        if (this.getCount() == 4) {
            path = "/TileImages/checkpoint4_top.png";
        }
        if (this.getCount() == 5) {
            path = "/TileImages/checkpoint5_top.png";
        }
        return path;
    }

}
