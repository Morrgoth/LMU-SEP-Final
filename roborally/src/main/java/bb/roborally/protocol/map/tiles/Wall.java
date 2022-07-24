package bb.roborally.protocol.map.tiles;

import bb.roborally.server.game.Orientation;

import java.util.ArrayList;

/**
 * @author  Philipp Keyzman
 */
public class Wall extends Tile {

    public Wall() {

    }

    public Wall(String isOnBoard, ArrayList<Orientation> orientations) {
        this.setIsOnBoard(isOnBoard);
        super.setOrientations(orientations);
    }

    @Override
    public ArrayList<Orientation> getOrientations() {
        return super.getOrientations();
    }
    @Override
    public String getType() {
        return "Wall";
    }
}