package bb.roborally.protocol.map.tiles;

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

public class EnergySpace extends Tile {
    private int count = 1;

    public EnergySpace() {
    }

    public EnergySpace(String isOnBoard, ArrayList<Orientation> orientations, int count) {
        this.setIsOnBoard(isOnBoard);
        this.setOrientations(orientations);
        this.count = count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getCount() {
        return count;
    }


    @Override
    public String getType() {
        return "EnergySpace";
    }

    public void increaseRemainedEnergyCube() {
        this.count += 1;
    }

    public void decreaseRemainedEnergyCube() {
        this.count -= 1;
    }

    public void resetEnergyCube() {
        this.count = 1;
    }

}

