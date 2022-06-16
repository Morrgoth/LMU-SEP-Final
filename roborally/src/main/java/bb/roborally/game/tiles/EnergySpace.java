package bb.roborally.game.tiles;

import bb.roborally.data.messages.game_events.Energy;
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

public class EnergySpace extends Tile{
    final int activationOrder = 7;
    int remainedEnergyCube = 1;

    public int getRemainedEnergyCube() {
        return remainedEnergyCube;
    }

    @Override
    String getName() {
        return "EnergySpace";dfdfgn
    }

    public Energy gainEnergyCubeFromEnergySpace(Robot robot, EnergySpace energySpace){
        if(energySpace.getRemainedEnergyCube() == 1){
            robot.increaseEnergyCubeAmount();
            energySpace.decreaseRemainedEnergyCube();
        }
        return new Energy(robot.getClientID(), 1, "EnergySpace");
    }

    public void increaseRemainedEnergyCube() {
        this.remainedEnergyCube += 1;
    }

    public void decreaseRemainedEnergyCube() { this.remainedEnergyCube -= 1; }
}
