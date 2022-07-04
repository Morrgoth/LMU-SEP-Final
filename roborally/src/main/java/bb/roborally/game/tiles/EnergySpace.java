package bb.roborally.game.tiles;

import bb.roborally.data.messages.game_events.Energy;
import bb.roborally.game.Orientation;
import bb.roborally.game.Position;
import bb.roborally.game.Robot;

import java.util.ArrayList;

/**
 * @author Veronika Heckel
 * @author Muqiu Wang
 * @author Tolga Engin
 * @author Zeynab Baiani
 * @author Bence Ament
 * @author  Philipp Keyzman
 */

public class EnergySpace extends Tile{
    final int activationOrder = 7;
    private Position position;

    private String type;
    private String isOnBoard;
    private ArrayList<Orientation> orientations;
    private int remainedEnergyCube = 1;

    public EnergySpace() {
    }

    public EnergySpace(String type, String isOnBoard, ArrayList<Orientation> orientations, int remainedEnergyCube) {
        this.type = type;
        this.setIsOnBoard(isOnBoard);
        this.setOrientations(orientations);
        this.remainedEnergyCube = remainedEnergyCube;
    }

    @Override
    public Position getPosition() {
        return position;
    }

    @Override
    public void setPosition(Position position) {
        this.position = position;
    }
    public void setRemainedEnergyCube(int remainedEnergyCube) {
        this.remainedEnergyCube = remainedEnergyCube;
    }

    public int getRemainedEnergyCube() {
        return remainedEnergyCube;
    }


    @Override
    public String getType() {
        return "EnergySpace";
    }

    @Override
    public String getResource() {
        String path = "";
        if (this.getOrientations().equals(Orientation.TOP) &&
                this.getRemainedEnergyCube() == 1) {
            path = "/TileImages/energycube_not_activated.png";
        }
        if (this.getOrientations().equals(Orientation.TOP) &&
                this.getRemainedEnergyCube() == 0) {
            path = "/TileImages/energycube_activated.png";
        }
        if (this.getOrientations().equals(Orientation.RIGHT) &&
                this.getRemainedEnergyCube() == 1) {
            path = "/TileImages/variants/energycube_not_activated_horizontal.png";
        }
        if (this.getOrientations().equals(Orientation.RIGHT) &&
                this.getRemainedEnergyCube() == 0) {
            path = "/TileImages/variants/energycube_activated_horizontal.png";
        }
        if (this.getOrientations().equals(Orientation.BOTTOM) &&
                this.getRemainedEnergyCube() == 1) {
            path = "/TileImages/energycube_not_activated.png";
        }
        if (this.getOrientations().equals(Orientation.BOTTOM) &&
                this.getRemainedEnergyCube() == 0) {
            path = "/TileImages/energycube_activated.png";
        }
        if (this.getOrientations().equals(Orientation.LEFT) &&
                this.getRemainedEnergyCube() == 1) {
            path = "/TileImages/variants/energycube_not_activated_horizontal.png";
        }
        if (this.getOrientations().equals(Orientation.LEFT) &&
                this.getRemainedEnergyCube() == 0) {
            path = "/TileImages/variants/energycube_activated_horizontal.png";
        }
        return path;
    }

    public void increaseRemainedEnergyCube() {
        this.remainedEnergyCube += 1;
    }

    public void decreaseRemainedEnergyCube() { this.remainedEnergyCube -= 1; }

    public void resetEnergyCube(){
        this.remainedEnergyCube = 1;
    }

    @Override
    public ArrayList<Orientation> getOrientations() {
        return orientations;
    }

    @Override
    public void setOrientations(ArrayList<Orientation> orientations) {
        this.orientations = orientations;
    }

    public Energy gainEnergyCubeFromEnergySpace(Robot robot, EnergySpace energySpace){
        if(energySpace.getRemainedEnergyCube() == 1){
            robot.increaseEnergyCubeAmount();
            energySpace.decreaseRemainedEnergyCube();
        }
        return new Energy(robot.getClientID(), 1, "EnergySpace");
    }
}
