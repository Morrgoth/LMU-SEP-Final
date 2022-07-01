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
}
