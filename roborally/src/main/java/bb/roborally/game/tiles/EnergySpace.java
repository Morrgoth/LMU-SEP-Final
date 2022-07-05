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
    private int remainedEnergyCube = 1;

    public EnergySpace() {
    }

    public EnergySpace(String isOnBoard, ArrayList<Orientation> orientations, int remainedEnergyCube) {
        this.setIsOnBoard(isOnBoard);
        this.setOrientations(orientations);
        this.remainedEnergyCube = remainedEnergyCube;
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

    public void increaseRemainedEnergyCube() {
        this.remainedEnergyCube += 1;
    }

    public void decreaseRemainedEnergyCube() { this.remainedEnergyCube -= 1; }

    public void resetEnergyCube(){
        this.remainedEnergyCube = 1;
    }

    //public Energy gainEnergyCubeFromEnergySpace(Robot robot, EnergySpace energySpace){
    //    if(energySpace.getRemainedEnergyCube() == 1){
    //        robot.increaseEnergyCubeAmount();
    //        energySpace.decreaseRemainedEnergyCube();
    //    }
    //    return new Energy(robot.getClientID(), 1, "EnergySpace");
    //}


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

    //public Energy gainEnergyCubeFromEnergySpace(Robot robot, EnergySpace energySpace){
    //    if(energySpace.getRemainedEnergyCube() == 1){
    //        robot.increaseEnergyCubeAmountBy(1);
    //        energySpace.decreaseRemainedEnergyCube();
    //    }
    //    return new Energy(robot.getClientID(), 1, "EnergySpace");
    //}
}
