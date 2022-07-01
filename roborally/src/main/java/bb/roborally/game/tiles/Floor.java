package bb.roborally.game.tiles;

import bb.roborally.game.Position;

/**
 * @author  Philipp Keyzman
 */
public class Floor extends Tile{
    private Position floorPosition;
    private boolean isEmpty = false;

    public Floor(){

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

}
