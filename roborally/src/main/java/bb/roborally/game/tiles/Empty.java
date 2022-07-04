package bb.roborally.game.tiles;

import bb.roborally.game.Position;

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



}
