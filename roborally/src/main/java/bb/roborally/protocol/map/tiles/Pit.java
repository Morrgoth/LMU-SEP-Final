package bb.roborally.protocol.map.tiles;

/**
 * @author  Philipp Keyzman
 */
public class Pit extends  Tile {

    public Pit() {

    }

    @Override
    public String getType() {
        return "Pit";
    }

    public Pit(String isOnBoard) {
        this.setIsOnBoard(isOnBoard);
    }

    //public Reboot pitFall(Robot robot) {
    //    return new Reboot(robot.getClientID());
    //}

    @Override
    public String getResource() {
        String path = "";
        path = "/TileImages/blackhole.png";
        return path;
    }
}

 /*
    If you land on a pit, you immediately fall in and must reboot your robot. (See Damage and
        Reboots on page 15.)

 */