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


}