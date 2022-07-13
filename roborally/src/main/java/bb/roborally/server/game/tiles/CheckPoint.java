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
        if (this.getOrientations().get(0).equals(Orientation.TOP) &&
                this.getCount() == 1) {
            path = "/TileImages/checkpoint1_top.png";
        }
        if (this.getOrientations().get(0).equals(Orientation.TOP) &&
                this.getCount() == 2) {
            path = "/TileImages/checkpoint2_top.png";
        }
        if (this.getOrientations().get(0).equals(Orientation.TOP) &&
                this.getCount() == 3) {
            path = "/TileImages/checkpoint3_top.png";
        }
        if (this.getOrientations().get(0).equals(Orientation.TOP) &&
                this.getCount() == 4) {
            path = "/TileImages/checkpoint4_top.png";
        }
        if (this.getOrientations().get(0).equals(Orientation.TOP) &&
                this.getCount() == 5) {
            path = "/TileImages/checkpoint5_top.png";
        }
        if (this.getOrientations().get(0).equals(Orientation.RIGHT) &&
                this.getCount() == 1) {
            path = "/TileImages/variants/checkpoint1_right.png";
        }
        if (this.getOrientations().get(0).equals(Orientation.RIGHT) &&
                this.getCount() == 2) {
            path = "/TileImages/variants/checkpoint2_right.png";
        }
        if (this.getOrientations().get(0).equals(Orientation.RIGHT) &&
                this.getCount() == 3) {
            path = "/TileImages/variants/checkpoint3_right.png";
        }
        if (this.getOrientations().get(0).equals(Orientation.RIGHT) &&
                this.getCount()== 4) {
            path = "/TileImages/variants/checkpoint4_right.png";
        }
        if (this.getOrientations().get(0).equals(Orientation.RIGHT) &&
                this.getCount() == 5) {
            path = "/TileImages/variants/checkpoint5_right.png";
        }
        if (this.getOrientations().get(0).equals(Orientation.BOTTOM) &&
                this.getCount() == 1) {
            path = "/TileImages/variants/checkpoint1_bottom.png";
        }
        if (this.getOrientations().get(0).equals(Orientation.BOTTOM) &&
                this.getCount() == 2) {
            path = "/TileImages/variants/checkpoint2_bottom.png";
        }
        if (this.getOrientations().get(0).equals(Orientation.BOTTOM) &&
                this.getCount() == 3) {
            path = "/TileImages/variants/checkpoint3_bottom.png";
        }
        if (this.getOrientations().get(0).equals(Orientation.BOTTOM) &&
                this.getCount()== 4) {
            path = "/TileImages/variants/checkpoint4_bottom.png";
        }
        if (this.getOrientations().get(0).equals(Orientation.BOTTOM) &&
                this.getCount() == 5) {
            path = "/TileImages/variants/checkpoint5_bottom.png";
        }
        if (this.getOrientations().get(0).equals(Orientation.LEFT) &&
                this.getCount() == 1) {
            path = "/TileImages/variants/checkpoint1_left.png";
        }
        if (this.getOrientations().get(0).equals(Orientation.LEFT) &&
                this.getCount() == 2) {
            path = "/TileImages/variants/checkpoint2_left.png";
        }
        if (this.getOrientations().get(0).equals(Orientation.LEFT) &&
                this.getCount() == 3) {
            path = "/TileImages/variants/checkpoint3_left.png";
        }
        if (this.getOrientations().get(0).equals(Orientation.LEFT) &&
                this.getCount() == 4) {
            path = "/TileImages/variants/checkpoint4_left.png";
        }
        if (this.getOrientations().get(0).equals(Orientation.LEFT) &&
                this.getCount() == 5) {
            path = "/TileImages/variants/checkpoint5_left.png";
        }
        return path;
    }

}
