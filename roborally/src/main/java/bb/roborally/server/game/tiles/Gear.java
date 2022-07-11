package bb.roborally.server.game.tiles;

import bb.roborally.server.game.Orientation;

import java.util.ArrayList;

/**
 * @author Veronika Heckel
 * @author Muqiu Wang
 * @author Tolga Engin
 * @author Zeynab Baiani
 * @author Bence Ament
 * @author  Philipp Keyzman
 */
public class Gear extends Tile{

    public Gear( String isOnBoard, ArrayList<Orientation> orientations) {
        this.setIsOnBoard(isOnBoard);
        this.setOrientations(orientations);
    }

    @Override
    public String getType() {
        return "Gear";
    }

    public Gear() {
    }

    @Override
    public String getResource(){
        String path = "";
        if (this.getOrientations().get(0).toString().equals("clockwise")) {
            path = "/TileImages/gear_clockwise.png";
        }
        else {
            path = "/TileImages/gear_counter_clockwise.png";
        }
        return path;
    }

}
