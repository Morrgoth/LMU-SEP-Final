package bb.roborally.game.tiles;

import bb.roborally.data.messages.type_adapters.game_events.BlockAction;
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
public class Wall extends Tile{
    private Position wallPosition;
    private ArrayList<Orientation> wallOrientation;

    public Wall(){

    }
    public Wall (Position wallPosition,ArrayList<Orientation> wallOrientation){
        this.wallPosition = wallPosition;
        this.wallOrientation = wallOrientation;
    }
    @Override
    String getName() {
        return "Wall";
    }

    public Position getWallPosition() {
        return wallPosition;
    }

    public void setWallPosition(Position wallPosition) {
        this.wallPosition = wallPosition;
    }

    public ArrayList<Orientation> getWallOrientation() {
        return wallOrientation;
    }

    public void setWallOrientation(ArrayList<Orientation> wallOrientation) {
        this.wallOrientation = wallOrientation;
    }

    public BlockAction blockGameAction(Robot robot){
        if( robot.getPosition() == wallPosition){
            if(wallOrientation.contains(robot.getRobotOrientation())){

            }
        }
        return new BlockAction(BlockAction.clientID);
    }
    public BlockAction blockGameAction(Laser boardLaser){
        if( boardBoardLaser.getBoardLaserPosition() == wallPosition){
            if(wallOrientation.contains(boardLaser.getBoardLaserOrientation())){

            }
        }
        return new BlockAction(BlockAction.clientID);
    }

}
