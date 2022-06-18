package bb.roborally.game.tiles;

import bb.roborally.data.messages.Message;
import bb.roborally.data.messages.game_events.Animation;
import bb.roborally.game.Orientation;
import bb.roborally.game.Player;
import bb.roborally.game.Position;

import java.util.ArrayList;

/**
 * @author Veronika Heckel
 * @author Muqiu Wang
 * @author Tolga Engin
 * @author Zeynab Baiani
 * @author Bence Ament
 * @author  Philipp Keyzman
 */
public class BoardLaser extends Tile {

    private Position laserPosition;
    // map declaration
    private Orientation laserOrientation;

    //containing each tile in ArrayList<Tiles> of same index, we won't be needing layer variables - discuss;
    //private int layer;
    final int activationOrder = 5;
    private int laserRegister;

    public BoardLaser() {

    }
    public BoardLaser (Position laserPosition, Orientation laserOrientation,int laserRegister) {
        this.laserPosition = laserPosition;
        this.laserOrientation = laserOrientation;
        this.laserRegister = laserRegister;
    }

    String getName() {
        return "Laser";
    }


    public Position getLaserPosition() {
        return laserPosition;
    }

    public void setLaserPosition(Position laserPosition) {
        this.laserPosition = laserPosition;
    }
    /*
    @Override
    public int getLayer() {
        return layer;
    }

    @Override
    public void setLayer(int layer) {
        this.layer = layer;
    }
    */
    @Override
    public int getActivationOrder() {
        return activationOrder;
    }

    public int getLaserRegister() {
        return laserRegister;
    }

    public void setLaserRegister(int laserRegister) {
        this.laserRegister = laserRegister;
    }

    public Orientation getLaserOrientation() {
        return laserOrientation;
    }

    public void setLaserOrientation(Orientation laserOrientation) {
        this.laserOrientation = laserOrientation;
    }

    /*
    continue here
    lacking Antenna / Wall / Floor / board or cell for contains(robot) check / lacks robot -> player || robot -> playerInventory connection for addCard(SPAM)
     */
    public ArrayList <Message> shootLaserOne(BoardLaser boardLaser) {

        if(laserRegister == 1 ) {
            if(laserOrientation.equals(Orientation.LEFT) && getLaserPosition().getColumn() > Antenna.getPosition().getColumn()
                    ||boardLaser.getPosition().getColumn() > Wall.getPosition().getColumn()) {

                robot.equals(Player.getRobot());)
        }
        }
        return message;
    }



}
/*
move to server side for adaptation

        if(laserOrientation.equals(Orientation.LEFT) && laserRegister == 1){
            for(int i = getLaserPosition().getColumn(); i <= 0 ; i--){
                switch (getName()) {
                    case "Twonkey":
                        break;
                    case "SquashBot":
                        break;
                    case"Twich":
                        break;
                    case"ZoomBot":
                        break;
                    case"HammerBot":
                        break;
                    case"SpinBot":
                        break;
                    case"HulkX90":
                        break;
                    case"TrundleBot":
                        break;
                    case "Antenna":
                        break;
                    case "Wall":
                        break;
                    case "Laser":
                        break;
                }
            }
        }

 */


/*
TWONKEY
        Type: Series 12 Twonky style Vid Bot

        SQUASH BOT
        Type: Heavy Gauge Roller Bot

        TWITCH
        Type: Tread-mounted Inspector Bot

        ZOOM BOT
        Type: Mark VII Zoom Bot

        HAMMER BOT
        Type: Maxwell Class Hammer Bot

        SPIN BOT
        Type: Spin-o-lux Dervish-style Spin Bot

        HULK X90
        Type: H.G. Well and Good Tripod

        TRUNDLE BOT
        Type: Class VI Tank-style Trundle Bot


Board Lasers fire, hitting any robots
in their line of sight. Board lasers
cannot fire through walls, the priority
antenna, or hit more than one robot,
and they shoot from the red and white
pointer. (Take a SPAM damage card for
each laser that hits you. See
 */