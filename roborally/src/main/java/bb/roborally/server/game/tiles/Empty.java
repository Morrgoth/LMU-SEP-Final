package bb.roborally.server.game.tiles;

/**
 * @author  Philipp Keyzman
 */
public class Empty extends Tile{

    public Empty(){

    }

    public Empty(String isOnBoard) {
        this.setIsOnBoard(isOnBoard);
    }

    @Override
    public String getType() {
        return "Empty";
    }
    @Override
    public String getResource() {
        String path = "";
        path = "/TileImages/floor.png";
        return path;
    }

}
