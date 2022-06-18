package bb.roborally.game.tiles;

import bb.roborally.game.Position;
import bb.roborally.game.Robot;

/**
 * @author Veronika Heckel
 * @author Muqiu Wang
 * @author Tolga Engin
 * @author Zeynab Baiani
 * @author Bence Ament
 * @author  Philipp Keyzman
 */
public class Wall extends Tile{
    @Override
    public String getName() {
        return "Wall";
    }

    public enum WallType{
        WALL_STRAIGHT,
        WALL_ANGULAR

    }
}
