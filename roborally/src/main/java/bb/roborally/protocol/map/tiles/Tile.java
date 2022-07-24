package bb.roborally.protocol.map.tiles;

import bb.roborally.server.game.Orientation;

import java.util.ArrayList;


/**
 * abstract parent class to all items that are on the board.
 * the position can be called by every item and every item can be assigned to a certain position
 *
 * @author Muqiu Wang
 * @author  Philipp Keyzman
 */
public abstract class Tile{

    int count;
    private String isOnBoard;
    private ArrayList<Orientation> orientations;

    public abstract String getType();

    public String getIsOnBoard() {
        return isOnBoard;
    }

    public void setIsOnBoard(String isOnBoard) {
        this.isOnBoard = isOnBoard;
    }

    public ArrayList<Orientation> getOrientations() {
        return orientations;
    }

    public void setOrientations(ArrayList<Orientation> orientations) {
        this.orientations = orientations;
    }

    public abstract String getResource();

    public int getCount() {
        return count;
    }
    public void setCount(int count) {
        this.count = count;
    }

}
