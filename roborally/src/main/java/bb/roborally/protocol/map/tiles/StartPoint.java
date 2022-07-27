package bb.roborally.protocol.map.tiles;

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

    @Override
    public String getType() {
        return "StartPoint";
    }

    public boolean isTaken() {
        return taken;
    }

    public void setTaken(boolean taken) {
        this.taken = taken;
    }
}
