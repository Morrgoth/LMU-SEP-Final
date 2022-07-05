package bb.roborally.game.tiles;

import bb.roborally.data.messages.game_events.CheckPointReached;
import bb.roborally.game.Orientation;
import bb.roborally.game.Position;
import bb.roborally.game.Robot;

import java.util.ArrayList;

/**
 * @author Muqiu Wang
 */
public class CheckPoint extends Tile{
    private int number;

    public CheckPoint() {
    }

    public CheckPoint(String isOnBoard, ArrayList<Orientation> orientations, int number) {
        this.setIsOnBoard(isOnBoard);
        this.setOrientations(orientations);
        this.number = number;
    }

    @Override
    public String getType() {
        return "CheckPoint";
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    //public CheckPointReached checkPointReached(Robot robot){
    //    if(robot.getCheckPointTokens() < this.number){
    //        robot.addCheckPointTokens();
    //    }else{
    //        return null;
    //    }
    //    return new CheckPointReached(robot.getClientID(), robot.getCheckPointTokens());
    //}


    @Override
    public String getResource() {
        String path = "";
        if (this.getOrientations().get(0).equals(Orientation.TOP) &&
                this.getNumber() == 1) {
            path = "/TileImages/checkpoint1_top.png";
        }
        if (this.getOrientations().get(0).equals(Orientation.TOP) &&
                this.getNumber() == 2) {
            path = "/TileImages/checkpoint2_top.png";
        }
        if (this.getOrientations().get(0).equals(Orientation.TOP) &&
                this.getNumber() == 3) {
            path = "/TileImages/checkpoint3_top.png";
        }
        if (this.getOrientations().get(0).equals(Orientation.TOP) &&
                this.getNumber() == 4) {
            path = "/TileImages/checkpoint4_top.png";
        }
        if (this.getOrientations().get(0).equals(Orientation.TOP) &&
                this.getNumber() == 5) {
            path = "/TileImages/checkpoint5_top.png";
        }
        if (this.getOrientations().get(0).equals(Orientation.RIGHT) &&
                this.getNumber() == 1) {
            path = "/TileImages/variants/checkpoint1_right.png";
        }
        if (this.getOrientations().get(0).equals(Orientation.RIGHT) &&
                this.getNumber() == 2) {
            path = "/TileImages/variants/checkpoint2_right.png";
        }
        if (this.getOrientations().get(0).equals(Orientation.RIGHT) &&
                this.getNumber() == 3) {
            path = "/TileImages/variants/checkpoint3_right.png";
        }
        if (this.getOrientations().get(0).equals(Orientation.RIGHT) &&
                this.getNumber()== 4) {
            path = "/TileImages/variants/checkpoint4_right.png";
        }
        if (this.getOrientations().get(0).equals(Orientation.RIGHT) &&
                this.getNumber() == 5) {
            path = "/TileImages/variants/checkpoint5_right.png";
        }
        if (this.getOrientations().get(0).equals(Orientation.BOTTOM) &&
                this.getNumber() == 1) {
            path = "/TileImages/variants/checkpoint1_bottom.png";
        }
        if (this.getOrientations().get(0).equals(Orientation.BOTTOM) &&
                this.getNumber() == 2) {
            path = "/TileImages/variants/checkpoint2_bottom.png";
        }
        if (this.getOrientations().get(0).equals(Orientation.BOTTOM) &&
                this.getNumber() == 3) {
            path = "/TileImages/variants/checkpoint3_bottom.png";
        }
        if (this.getOrientations().get(0).equals(Orientation.BOTTOM) &&
                this.getNumber()== 4) {
            path = "/TileImages/variants/checkpoint4_bottom.png";
        }
        if (this.getOrientations().get(0).equals(Orientation.BOTTOM) &&
                this.getNumber() == 5) {
            path = "/TileImages/variants/checkpoint5_bottom.png";
        }
        if (this.getOrientations().get(0).equals(Orientation.LEFT) &&
                this.getNumber() == 1) {
            path = "/TileImages/variants/checkpoint1_left.png";
        }
        if (this.getOrientations().get(0).equals(Orientation.LEFT) &&
                this.getNumber() == 2) {
            path = "/TileImages/variants/checkpoint2_left.png";
        }
        if (this.getOrientations().get(0).equals(Orientation.LEFT) &&
                this.getNumber() == 3) {
            path = "/TileImages/variants/checkpoint3_left.png";
        }
        if (this.getOrientations().get(0).equals(Orientation.LEFT) &&
                this.getNumber() == 4) {
            path = "/TileImages/variants/checkpoint4_left.png";
        }
        if (this.getOrientations().get(0).equals(Orientation.LEFT) &&
                this.getNumber() == 5) {
            path = "/TileImages/variants/checkpoint5_left.png";
        }
        return path;
    }

}
