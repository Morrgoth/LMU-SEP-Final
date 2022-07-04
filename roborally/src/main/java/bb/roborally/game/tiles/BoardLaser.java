package bb.roborally.game.tiles;

import bb.roborally.data.messages.Message;
import bb.roborally.data.messages.game_events.DrawDamage;
import bb.roborally.game.Orientation;
import bb.roborally.game.PlayerInventory;
import bb.roborally.game.Position;
import bb.roborally.game.Robot;

import java.util.ArrayList;

import static bb.roborally.game.cards.DamageCard.CardType.SPAM_CARD;

/**
 * @author Philipp Keyzman
 */
public class BoardLaser extends Tile {

    private Position laserPosition;
    private String type;
    private String isOnBoard;
    private ArrayList<Orientation> laserOrientation;
    final int activationOrder = 5;
    private int count;

    public BoardLaser() {

    }
    public BoardLaser(String type, String isOnBoard, ArrayList<Orientation> orientations, int count){
        this.type = type;
        this.setIsOnBoard(isOnBoard);
        this.setOrientations(orientations);
        this.count = count;
    }

    @Override
    public String getType() {
        return "Laser";
    }

    @Override
    public String getResource() {
        String path = "";
        if (this.getBoardLaserOrientation().equals(Orientation.TOP) &&
                this.getCount() == 1) {
            path = "/TileImages/variants/wall_laser1_top.png)";
        }
        if (this.getBoardLaserOrientation().equals(Orientation.TOP) &&
                this.getCount() == 2) {
            path = "/TileImages/variants/wall_laser2_top.png)";
        }
        if (this.getBoardLaserOrientation().equals(Orientation.TOP) &&
                this.getCount() == 3) {
            path = "/TileImages/variants/wall_laser3_top.png)";
        }
        if (this.getBoardLaserOrientation().equals(Orientation.RIGHT) &&
                this.getCount() == 1) {
            path = "/TileImages/variants/wall_laser1_right.png)";
        }
        if (this.getBoardLaserOrientation().equals(Orientation.RIGHT) &&
                this.getCount() == 2) {
            path = "/TileImages/variants/wall_laser2_right.png)";
        }
        if (this.getBoardLaserOrientation().equals(Orientation.RIGHT) &&
                this.getCount()== 3) {
            path = "/TileImages/variants/wall_laser3_right.png)";
        }
        if (this.getBoardLaserOrientation().equals(Orientation.BOTTOM) &&
                this.getCount() == 1) {
            path = "/TileImages/wall_laser1_bottom.png)";
        }
        if (this.getBoardLaserOrientation().equals(Orientation.BOTTOM) &&
                this.getCount() == 2) {
            path = "/TileImages/variants/wall_laser2_bottom.png)";
        }
        if (this.getBoardLaserOrientation().equals(Orientation.BOTTOM) &&
                this.getCount() == 3) {
            path = "/TileImages/variants/wall_laser3_bottom.png)";
        }
        if (this.getBoardLaserOrientation().equals(Orientation.LEFT) &&
                this.getCount() == 1) {
            path = "/TileImages/variants/wall_laser1_left.png)";
        }
        if (this.getBoardLaserOrientation().equals(Orientation.LEFT) &&
                this.getCount() == 2) {
            path = "/TileImages/wall_laser2_left.png)";
        }
        if (this.getBoardLaserOrientation().equals(Orientation.LEFT) &&
                this.getCount() == 3) {
            path = "/TileImages/wall_laser3_left.png)";
        }
        return path;
    }

    public Position getBoardLaserPosition() {
        return laserPosition;
    }
    public void setBoardLaserPosition(Position laserPosition) {
        this.laserPosition = laserPosition;
    }
    @Override
    public int getActivationOrder() {
        return activationOrder;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public ArrayList<Orientation> getBoardLaserOrientation() {
        return laserOrientation;
    }

    public void setBoardLaserOrientation(ArrayList<Orientation> laserOrientation) {
        this.laserOrientation = laserOrientation;
    }
}

    /*

ShootingLogic to work on further

    public ArrayList<Message> shootLaser(BoardLaser boardLaser, Robot robot) {
        ArrayList <Message> message = new ArrayList<Message>();
        switch (count) {
            case 1:
                if (boardLaserOrientation.equals(Orientation.LEFT) && getBoardLaserPosition().getColumn() > Antenna.getAntennaPosition().getColumn()
                        || getBoardLaserPosition().getColumn() > Wall.getWallPosition().getColumn()) {

                    if (PlayerInventory.getClientID() == robot.getClientID()) {
                        PlayerInventory.addCard(SPAM_CARD);

                        robot.setLasered();
                        message.add(new DrawDamage(robot));
                        message.add(new AddCard(robot));
                    }
                }
                if (boardLaserOrientation.equals(Orientation.RIGHT) && getBoardLaserPosition().getColumn() < Antenna.getAntennaPosition().getColumn()
                        || getBoardLaserPosition().getColumn() < Wall.getWallPosition().getColumn()) {

                    if (robot.getClientID() == PlayerInventory.getClientID()) {
                        PlayerInventory.addCard(SPAM_CARD);

                        robot.setLasered();
                        message.add(new DrawDamage(robot));
                        message.add(new AddCard(robot));
                    }
                }
                if (boardLaserOrientation.equals(Orientation.TOP) && getBoardLaserPosition().getRow() > Antenna.getAntennaPosition().getRow()
                        || getBoardLaserPosition().getRow() > Wall.getWallPosition().getRow()) {

                    if (robot.getClientID() == PlayerInventory.getClientID()) {
                        PlayerInventory.addCard(SPAM_CARD);

                        robot.setLasered();
                        message.add(new DrawDamage(robot));
                        message.add(new AddCard(robot));
                    }
                }
                if (boardLaserOrientation.equals(Orientation.BOTTOM) && getBoardLaserPosition().getRow() < Antenna.getAntennaPosition().getRow()
                        || getBoardLaserPosition().getRow() < Wall.getWallPosition().getRow()) {

                    if (robot.getClientID() == PlayerInventory.getClientID()) {
                        PlayerInventory.addCard(SPAM_CARD);

                        robot.setLasered();
                        message.add(new DrawDamage(robot));
                        message.add(new AddCard(robot));
                    }
                }
            case 2:
                if (boardLaserOrientation.equals(Orientation.LEFT) && getBoardLaserPosition().getColumn() > Antenna.getAntennaPosition().getColumn()
                        || getBoardLaserPosition().getColumn() > Wall.getWallPosition().getColumn()) {

                    if (robot.getClientID() == PlayerInventory.getClientID()) {
                        PlayerInventory.addCard(SPAM_CARD);

                        robot.setLasered();
                        message.add(new DrawDamage(robot));
                        message.add(new AddCard(robot));
                    }
                }
                if (boardLaserOrientation.equals(Orientation.RIGHT) && getBoardLaserPosition().getColumn() < Antenna.getAntennaPosition().getColumn()
                        || getBoardLaserPosition().getColumn() < Wall.getWallPosition().getColumn()) {

                    if (robot.getClientID() == PlayerInventory.getClientID()) {
                        PlayerInventory.addCard(SPAM_CARD);

                        robot.setLasered();
                        message.add(new DrawDamage(robot));
                        message.add(new AddCard(robot));
                    }
                }
                if (boardLaserOrientation.equals(Orientation.TOP) && getBoardLaserPosition().getRow() > Antenna.getAntennaPosition().getRow()
                        || getBoardLaserPosition().getRow() > Wall.getWallPosition().getRow()) {

                    if (robot.getClientID() == PlayerInventory.getClientID()) {
                        PlayerInventory.addCard(SPAM_CARD);

                        robot.setLasered();
                        message.add(new DrawDamage(robot));
                        message.add(new AddCard(robot));
                    }
                }
                if (boardLaserOrientation.equals(Orientation.BOTTOM) && getBoardLaserPosition().getRow() < Antenna.getAntennaPosition().getRow()
                        || getBoardLaserPosition().getRow() < Wall.getWallPosition().getRow()) {

                    if (robot.getClientID() == PlayerInventory.getClientID()) {
                        PlayerInventory.addCard(SPAM_CARD);

                        robot.setLasered();
                        message.add(new DrawDamage(robot));
                        message.add(new AddCard(robot));
                    }
                }

            case 3:
                if (boardLaserOrientation.equals(Orientation.LEFT) && getBoardLaserPosition().getColumn() > Antenna.getAntennaPosition().getColumn()
                        || getBoardLaserPosition().getColumn() > Wall.getWallPosition().getColumn()) {

                    if (robot.getClientID() == PlayerInventory.getClientID()) {
                        PlayerInventory.addCard(SPAM_CARD);

                        robot.setLasered();
                        message.add(new DrawDamage(robot));
                        message.add(new AddCard(robot));
                    }
                }
                if (boardLaserOrientation.equals(Orientation.RIGHT) && getBoardLaserPosition().getColumn() < Antenna.getAntennaPosition().getColumn()
                        || getBoardLaserPosition().getColumn() < Wall.getWallPosition().getColumn()) {

                    if (robot.getClientID() == PlayerInventory.getClientID()) {
                        PlayerInventory.addCard(SPAM_CARD);

                        robot.setLasered();
                        message.add(new DrawDamage(robot));
                        message.add(new AddCard(robot));
                    }
                }
                if (boardLaserOrientation.equals(Orientation.TOP) && getBoardLaserPosition().getRow() > Antenna.getAntennaPosition().getRow()
                        || getBoardLaserPosition().getRow() > Wall.getWallPosition().getRow()) {

                    if (robot.getClientID() == PlayerInventory.getClientID()) {
                        PlayerInventory.addCard(SPAM_CARD);

                        robot.setLasered();
                        message.add(new DrawDamage(robot));
                        message.add(new AddCard(robot));
                    }
                }
                if (boardLaserOrientation.equals(Orientation.BOTTOM) && getBoardLaserPosition().getRow() < Antenna.getAntennaPosition().getRow()
                        || getBoardLaserPosition().getRow() < Wall.getWallPosition().getRow()) {

                    if (robot.getClientID() == PlayerInventory.getClientID()) {
                        PlayerInventory.addCard(SPAM_CARD);

                        robot.setLasered();
                        message.add(new DrawDamage(robot));
                        message.add(new AddCard(robot));
                    }
                }
        }
        return message;
    }
*/


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