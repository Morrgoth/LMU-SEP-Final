
package bb.roborally.protocol.map.tiles;

import bb.roborally.server.game.Orientation;

import java.util.ArrayList;

/**
 * @author Philipp Keyzman
 */
public class Laser extends Tile {
    private int count;

    public Laser() {

    }

    public Laser(String isOnBoard, ArrayList<Orientation> orientations, int count) {
        this.setIsOnBoard(isOnBoard);
        this.setOrientations(orientations);
        this.count = count;
    }

    @Override
    public String getType() {
        return "Laser";
    }



    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}




/*
    if boardLaser orientation e.g top
        and robot.pos == wall.position
        and laser.orientation == wall orientation

        isLasered

    if robot1 near laser column/row ignore robot2 with > column/row

 */

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