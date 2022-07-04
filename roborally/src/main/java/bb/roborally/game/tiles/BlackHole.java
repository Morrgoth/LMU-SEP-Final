package bb.roborally.game.tiles;

import bb.roborally.data.messages.game_events.Reboot;
import bb.roborally.game.Orientation;
import bb.roborally.game.Position;
import bb.roborally.game.Robot;

import java.util.ArrayList;

/**
 * @author  Philipp Keyzman
 */
public class BlackHole extends  Tile{
    private Position blackHolePosition;
    private String type;
    private String isOnBoard;

    public BlackHole(){

    }

    public BlackHole(String type, String isOnBoard) {
        this.type = type;
        this.setIsOnBoard(isOnBoard);
    }

    public BlackHole(Position blackHolePosition){
        this.blackHolePosition = blackHolePosition;
    }
    public Reboot blackHoleFall (Robot robot){
        return new Reboot(robot.getClientID());
    }
    @Override
    public String getType() {
        return "BlackHole";
    }

    @Override
    public String getResource() {
        String path ="";
        path = "/TileImages/blackhole.png";
        return path;
    }

    public Position getBlackHolePosition() {
        return blackHolePosition;
    }

    public void setBlackHolePosition(Position blackHolePosition) {
        this.blackHolePosition = blackHolePosition;
    }
}
/*
    If you land on a pit, you immediately fall in and must reboot your robot. (See Damage and
        Reboots on page 15.)

 */