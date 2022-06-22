package bb.roborally.game.tiles;

import bb.roborally.data.messages.game_events.Reboot;
import bb.roborally.game.Position;
import bb.roborally.game.Robot;

/**
 * @author Veronika Heckel
 * @author Muqiu Wang
 * @author Tolga Engin
 * @author Zeynab Baiani
 * @author Bence Ament
 * @author  Philipp Keyzman
 */
public class BlackHole extends  Tile{
    private Position blackHolePosition;

    public BlackHole(){

    }
    public BlackHole(Position blackHolePosition){
        this.blackHolePosition = blackHolePosition;
    }
    public Reboot blackHoleFall (Robot robot){
        return new Reboot(robot.getClientID());
    }
    @Override
    String getName() {
        return "BlackHole";
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