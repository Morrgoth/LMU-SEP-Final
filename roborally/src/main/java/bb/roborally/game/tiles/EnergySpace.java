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

    int remainedEnergyCube = 1;

    private Position energySpace;
    private ArrayList<Orientation> energyOrientation;

    public EnergySpace() {
    }

    public EnergySpace(int remainedEnergyCube,ArrayList<Orientation> energyOrientation){
        this.remainedEnergyCube = remainedEnergyCube;
        this.energyOrientation = energyOrientation;
    }

    public void setRemainedEnergyCube(int remainedEnergyCube) {
        this.remainedEnergyCube = remainedEnergyCube;
    }
    public ArrayList<Orientation> getEnergyOrientation() {
        return energyOrientation;
    }

    public void setEnergyOrientation(ArrayList<Orientation> energyOrientation) {
        this.energyOrientation = energyOrientation;
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

    public Energy gainEnergyCubeFromEnergySpace(Robot robot, EnergySpace energySpace){
        if(energySpace.getRemainedEnergyCube() == 1){
            robot.increaseEnergyCubeAmount();
            energySpace.decreaseRemainedEnergyCube();
        }
        return new Energy(robot.getClientID(), 1, "EnergySpace");
    }


    public Position getEnergySpace() {
        return energySpace;
    }

    public void setEnergySpace(Position energySpace) {
        this.energySpace = energySpace;
    }
}
