package bb.roborally.server.game.tiles;

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
        if (this.getOrientations().get(0).equals(Orientation.TOP) &&
                this.getCount() == 1) {
            path = "/TileImages/energycube_not_activated.png";
        }
        if (this.getOrientations().get(0).equals(Orientation.TOP) &&
                this.getCount() == 0) {
            path = "/TileImages/energycube_activated.png";
        }
        if (this.getOrientations().get(0).equals(Orientation.LEFT) &&
                this.getCount() == 1) {
            path = "/TileImages/variants/energycube_not_activated_horizontal.png";
        }
        if (this.getOrientations().get(0).equals(Orientation.LEFT) &&
                this.getCount() == 0) {
            path = "/TileImages/variants/energycube_activated_horizontal.png";
        }
        /*}
        if (this.getOrientations().get(0).equals(Orientation.BOTTOM) &&
                this.getRemainedEnergyCube() == 1) {
            path = "/TileImages/energycube_not_activated.png";
        }
        if (this.getOrientations().get(0).equals(Orientation.BOTTOM) &&
                this.getRemainedEnergyCube() == 0) {
            path = "/TileImages/energycube_activated.png";
        }
        if (this.getOrientations().get(0).equals(Orientation.LEFT) &&
                this.getRemainedEnergyCube() == 1) {
            path = "/TileImages/variants/energycube_not_activated_horizontal.png";
        }
        if (this.getOrientations().get(0).equals(Orientation.LEFT) &&
                this.getRemainedEnergyCube() == 0) {
            path = "/TileImages/variants/energycube_activated_horizontal.png";
        }*/
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

