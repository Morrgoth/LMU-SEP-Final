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
public class Belt extends Tile{

    @Override
    String getName() {
        return "Belt";
    }

    public enum BeltType{
        BELT_GREEN,
        BELT_BLUE
    }
}
