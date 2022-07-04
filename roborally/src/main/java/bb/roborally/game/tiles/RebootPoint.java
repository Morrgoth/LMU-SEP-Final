package bb.roborally.game.tiles;

import bb.roborally.data.messages.game_events.Reboot;
import bb.roborally.game.Orientation;
import bb.roborally.game.Robot;

import java.util.ArrayList;


public class RebootPoint extends Tile{

    private String type;
    private String isOnBoard;
    private ArrayList<Orientation> orientations;
    private ArrayList<Robot> rebootQueue = new ArrayList<>();

    public RebootPoint() {
    }

    public RebootPoint(String type, String isOnBoard, ArrayList<Orientation> orientations) {
        this.type = type;
        this.setIsOnBoard(isOnBoard);
        this.setOrientations(orientations);
    }

    @Override
    public String getType() {
        return "RebootPoint";
    }

    public Reboot needToReboot(Robot robot){
        rebootQueue.add(robot);
        return new Reboot(robot.getClientID());
    }

    @Override
    public ArrayList<Orientation> getOrientations() {
        return orientations;
    }

    @Override
    public void setOrientations(ArrayList<Orientation> orientations) {
        this.orientations = orientations;
    }
    @Override
    public String getResource(){
        String path = "";
        if (this.getOrientations().equals(Orientation.TOP)) {
            path = "![](../../../../../resources/TileImages/reboot_top.png)";
        }
        if (this.getOrientations().equals(Orientation.RIGHT)) {
            path = "![](../../../../../resources/TileImages/variants/reboot_right.png)";
        }
        if (this.getOrientations().equals(Orientation.BOTTOM)) {
            path = "![](../../../../../resources/TileImages/variants/reboot_bottom.png)";
        }
        if (this.getOrientations().equals(Orientation.LEFT)) {
            path = "![](../../../../../resources/TileImages/variants/reboot_left.png)";
        }
        return path;
    }


}
