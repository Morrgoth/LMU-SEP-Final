package bb.roborally.game.tiles;

import bb.roborally.game.Orientation;
import bb.roborally.game.Position;

import java.util.ArrayList;

/**
 * @author Veronika Heckel
 * @author Muqiu Wang
 * @author Tolga Engin
 * @author Zeynab Baiani
 * @author Bence Ament
 * @author  Philipp Keyzman
 */
public class Floor extends Tile{
    private Position floorPosition;
    private boolean isEmpty = false;
    private String type;
    private String isOnBoard;


    public Floor(){

    }

    public Floor(String type, String isOnBoard) {
        this.type = type;
        this.isOnBoard = isOnBoard;
    }

    public Floor(Position floorPosition){
        this.floorPosition = floorPosition;
    }
    @Override
    public String getType() {
        return "Floor";
    }

    public Position getFloorPosition() {
        return floorPosition;
    }

    public void setFloorPosition(Position floorPosition) {
        this.floorPosition = floorPosition;
    }

    @Override
    public boolean isEmpty() {
        return isEmpty;
    }

    @Override
    public void setEmpty(boolean empty) {
        isEmpty = empty;
    }
}
