package bb.roborally.game.tiles;

import bb.roborally.data.messages.Envelope;
import bb.roborally.data.messages.Message;
import bb.roborally.game.Orientation;
import bb.roborally.game.Position;
import bb.roborally.game.Robot;

import java.util.ArrayList;


/**
 * abstract parent class to all items that are on the board.
 * the position can be called by every item and every item can be assigned to a certain position
 * @author Veronika Heckel
 * @author Muqiu Wang
 * @author Tolga Engin
 * @author Zeynab Baiani
 * @author Bence Ament
 * @author  Philipp Keyzman
 */
public class Tile{
    private Position position;
    // map declaration

    private String isOnBoard;
    private ArrayList<Orientation> orientations;
    private int layer;
    private int activationOrder;
    private boolean isEmpty;
    private String type;



    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }


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


    public int getLayer() {
        return layer;
    }

    public void setLayer(int layer) {
        this.layer = layer;
    }

    public int getActivationOrder() {
        return activationOrder;
    }

    public int setActivationOrder(int activationOrder) {
        this.activationOrder = activationOrder;
        return  activationOrder;
    }

    public boolean isEmpty() {
        return isEmpty;
    }

    public void setEmpty(boolean empty) {
        isEmpty = empty;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

}
