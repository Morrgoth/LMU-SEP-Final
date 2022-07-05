package bb.roborally.game.tiles;

import bb.roborally.data.messages.game_events.Reboot;
import bb.roborally.game.Orientation;
import bb.roborally.game.Robot;

import java.util.ArrayList;

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

    @Override
    public String getResource() {
        String path = "";
        if(this.getOrientations().get(0).equals(Orientation.TOP)){
            path = "/TileImages/reboot_top.png";
        }
        if(this.getOrientations().get(0).equals(Orientation.BOTTOM)){
            path = "/TileImages/variants/reboot_bottom.png";
        }
        if(this.getOrientations().get(0).equals(Orientation.LEFT)){
            path = "/TileImages/variants/reboot_left.png";
        }
        if(this.getOrientations().get(0).equals(Orientation.RIGHT)){
            path = "/TileImages/variants/reboot_right.png";
        }
        return path;
    }

    /*public Reboot needToReboot(Robot robot){
        rebootQueue.add(robot);
        return new Reboot(robot.getClientID());*/
    }

