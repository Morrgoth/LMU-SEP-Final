package bb.roborally.game.tiles;

import bb.roborally.data.messages.game_events.Movement;
import bb.roborally.game.Orientation;
import bb.roborally.game.Position;
import bb.roborally.game.Robot;

import java.util.ArrayList;

/**
 * @author  Philipp Keyzman
 */
public class Wall extends Tile{
    private static Position wallPosition;
    private ArrayList<Orientation> wallOrientation;

    public Wall(){

    }
    public Wall (Position wallPosition,ArrayList<Orientation> Orientation){
        Wall.wallPosition = wallPosition;
        this.wallOrientation = wallOrientation;
    }
    @Override
    public String getType() {
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

    public void setWallOrientation(ArrayList<Orientation> wallOrientation) {
        this.wallOrientation = wallOrientation;
    }
    public void setWallOrientationTwo(Orientation wallOrientation,Orientation wallOrientationTwo) {
        this.wallOrientation.add(wallOrientation);
        this.wallOrientation.add(wallOrientationTwo);
    }

    public Movement blockGameAction(Robot robot){
        if( robot.getPosition() == wallPosition){
            if(wallOrientation.contains(robot.getRobotOrientation())){

            }
        }
        return new Movement(robot.getClientID(),robot.getPosition().getColumn(),robot.getPosition().getRow());
    }
    /*
    do we still need this, if BoardLasers check for the distance themselves?


    public BlockAction blockLaserAction(BoardLaser boardLaser){

        if( boardLaser.getBoardLaserPosition() == wallPosition){
            if(wallOrientation.contains(boardLaser.getBoardLaserOrientation())){

            }
        }
        return new BlockAction(BlockAction.clientID);
    }

     */

}



