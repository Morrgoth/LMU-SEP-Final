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
public class Laser extends Tile {

    @Override
    String getName() {
        return "Laser";
    }

    public enum LaserType{
        SINGLE_LASER,
        DOUBLE_LASER,
        TRIPPLE_LASER
    }

}
