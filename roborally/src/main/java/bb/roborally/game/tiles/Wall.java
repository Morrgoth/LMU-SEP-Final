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

    public Wall(){

    }
    public Wall(String isOnBoard, ArrayList<Orientation> orientations){
        this.setIsOnBoard(isOnBoard);
        super.setOrientations(orientations);
    }
    /*public Wall (Position wallPosition,ArrayList<Orientation> orientations){
        Wall.wallPosition = wallPosition;
        this.wallOrientation = orientations;
    }*/
    @Override
    public String getType() {
        return "Wall";
    }



    /*public Movement blockGameAction(Robot robot){
        if( robot.getPosition() == wallPosition){
            if(wallOrientation.contains(robot.getRobotOrientation())){

            }
        }
        return new Movement(robot.getClientID(),robot.getPosition().getColumn(),robot.getPosition().getRow());
    }*/
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



