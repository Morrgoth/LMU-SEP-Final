package bb.roborally.game.tiles;

import bb.roborally.data.messages.game_events.BlockAction;
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
    private static Position wallPosition;
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

    public static Position getWallPosition() {
        return wallPosition;
    }

    public void setWallPosition(Position wallPosition) {
        Wall.wallPosition = wallPosition;
    }

    public ArrayList<Orientation> getWallOrientation() {
        return wallOrientation;
    }

    public void setWallOrientation(Orientation wallOrientation) {
        this.wallOrientation.add(wallOrientation);
    }
    public void setWallOrientationTwo(Orientation wallOrientation,Orientation wallOrientationTwo) {
        this.wallOrientation.add(wallOrientation);
        this.wallOrientation.add(wallOrientationTwo);
    }

    public BlockAction blockGameAction(Robot robot){
        if( robot.getPosition() == wallPosition){
            if(wallOrientation.contains(robot.getRobotOrientation())){

            }
        }
        return new BlockAction(BlockAction.clientID);
    }
    public BlockAction blockGameAction(BoardLaser boardLaser){

        if( boardLaser.getBoardLaserPosition() == wallPosition){
            if(wallOrientation.contains(boardLaser.getBoardLaserOrientation())){

            }
        }
        return new BlockAction(BlockAction.clientID);
    }

}
