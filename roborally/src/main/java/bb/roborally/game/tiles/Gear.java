package bb.roborally.game.tiles;

import bb.roborally.game.Position;
import bb.roborally.game.Robot;

/**
 * @author Veronika Heckel
 * @author Muqiu Wang
 * @author Tolga Engin
 * @author Zeynab Baiani
 * @author Bence Ament
 * @autor  Philipp Keyzman
 */
public class Gear extends Tile{
    @Override
    String getName() {
        return "Gear";
    }

    public enum GearType{
        GEAR_LEFT,
        GEAR_RIGHT
    }
}
