package bb.roborally.server.game.tiles;

/**
 * @author  Philipp Keyzman
 */
public class StartPoint extends Tile {

    private transient boolean taken = false;

    public StartPoint() {
    }

    public StartPoint(String isOnBoard) {
        this.setIsOnBoard(isOnBoard);
    }

    //public StartPoint(Position startingPoint, Robot robot){
    //    this.startingPoint = startingPoint;
    //    this.robotClientID = robot.getClientID();
    //}



    @Override
    public String getType() {
        return "StartPoint";
    }

    @Override
    public String getResource(){
        String path = "";
        path = "/TileImages/starting_point.png";
        return path;
    }

    public boolean isTaken() {
        return taken;
    }

    public void setTaken(boolean taken) {
        this.taken = taken;
    }
}
