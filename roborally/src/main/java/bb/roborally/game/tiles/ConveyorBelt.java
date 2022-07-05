package bb.roborally.game.tiles;

import bb.roborally.data.messages.Message;
import bb.roborally.data.messages.game_events.Movement;
import bb.roborally.data.messages.game_events.PlayerTurning;
import bb.roborally.game.Orientation;
import bb.roborally.game.Position;
import bb.roborally.game.Robot;

import java.util.ArrayList;

import static bb.roborally.game.Orientation.*;
import static bb.roborally.game.Orientation.BOTTOM_LEFT;
import static bb.roborally.game.Orientation.BOTTOM_RIGHT;
import static bb.roborally.game.Orientation.TOP_LEFT;
import static bb.roborally.game.Orientation.TOP_RIGHT;
import static org.controlsfx.control.PopOver.ArrowLocation.*;


/**
 * @author Veronika Heckel
 * @author Muqiu Wang
 * @author Tolga Engin
 * @author Zeynab Baiani
 * @author Bence Ament
 * @author  Philipp Keyzman
 */
public class ConveyorBelt extends Tile {
    private int speed;

    public ConveyorBelt(String isOnBoard, int speed, ArrayList<Orientation> orientations) {
        this.setIsOnBoard(isOnBoard);
        this.speed = speed;
        this.setOrientations(orientations);
    }

    public ConveyorBelt() {

    }

    @Override
    public String getType() {
        return "ConveyorBelt";
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {

        this.speed = speed;
    }


    @Override
    public String getResource() {
        String path = "";
        if (this.getOrientations().get(0).equals(Orientation.TOP) &&
                this.getOrientations().get(1).equals(BOTTOM) &&
                this.getSpeed() == 1) {
            path = "/TileImages/variants/GreenBeltTop.png";
        }
        if (this.getOrientations().get(0).equals(Orientation.RIGHT) &&
                this.getOrientations().get(1).equals(LEFT) &&
                this.getSpeed() == 1) {
            path = "/TileImages/variants/GreenBeltRight.png";
        }
        if (this.getOrientations().get(0).equals(Orientation.BOTTOM) &&
                this.getOrientations().get(1).equals(TOP) &&
                this.getSpeed() == 1) {
            path = "/TileImages/green_belt_straight.png";
        }
        if (this.getOrientations().get(0).equals(Orientation.LEFT) &&
                this.getOrientations().get(1).equals(RIGHT) &&
                this.getSpeed() == 1) {
            path = "/TileImages/variants/GreenBeltLeft.png";
        }
        ///
        if (this.getOrientations().get(0).equals(LEFT) &&
                this.getOrientations().get(1).equals(BOTTOM) &&
                this.getSpeed() == 1) {
            path = "/TileImages/variants/green_belt_counter_clockwise_left_bottom.png";
        }

        if (this.getOrientations().get(0).equals(TOP) &&
                this.getOrientations().get(1).equals(RIGHT) &&
                this.getSpeed() == 1) {
            path = "/TileImages/variants/green_belt_clockwise_right_top.png";
        }
        if (this.getOrientations().get(0).equals(BOTTOM) &&
                this.getOrientations().get(1).equals(RIGHT) &&
                this.getSpeed() == 1) {
            path = "/TileImages/variants/green_belt_counter_clockwise_bottom_right.png";
        }
        if (this.getOrientations().get(0).equals(LEFT) &&
                this.getOrientations().get(1).equals(TOP) &&
                this.getSpeed() == 1) {
            path = "/TileImages/variants/green_belt_clockwise_left_bottom.png";
        }
        if (this.getOrientations().get(0).equals(RIGHT) &&
                this.getOrientations().get(1).equals(BOTTOM) &&
                this.getSpeed() == 1) {
            path = "/TileImages/green_belt_clockwise_bottom_left.png";
        }
        if (this.getOrientations().get(0).equals(TOP) &&
                this.getOrientations().get(1).equals(LEFT) &&
                this.getSpeed() == 1) {
            path = "/TileImages/variants/green_belt_clockwise_right_top.png";
        }
        if (this.getOrientations().get(0).equals(BOTTOM) &&
                this.getOrientations().get(1).equals(LEFT) &&
                this.getSpeed() == 1) {
            path = "/TileImages/variants/green_belt_clockwise_bottom_left.png";
        }
        if (this.getOrientations().get(0).equals(RIGHT) &&
                this.getOrientations().get(1).equals(TOP) &&
                this.getSpeed() == 1) {
            path = "/TileImages/green_belt_counter_clockwise_bottom_right.png";
        }

        if (this.getOrientations().get(0).equals(Orientation.TOP) &&
                this.getOrientations().get(1).equals(BOTTOM) &&
                this.getSpeed() == 2) {
            path = "/TileImages/variants/blue_belt_straight_top.png";
        }
        if (this.getOrientations().get(0).equals(Orientation.RIGHT) &&
                this.getOrientations().get(1).equals(LEFT) &&
                this.getSpeed() == 2) {
            path = "/TileImages/variants/BlueBeltRight.png";
        }
        if (this.getOrientations().get(0).equals(Orientation.BOTTOM) &&
                this.getOrientations().get(1).equals(TOP) &&
                this.getSpeed() == 2) {
            path = "/TileImages/blue_belt_straight.png";
        }
        if (this.getOrientations().get(0).equals(Orientation.LEFT) &&
                this.getOrientations().get(1).equals(RIGHT) &&
                this.getSpeed() == 2) {
            path = "/TileImages/variants/BlueBeltLeft.png";
        }

        if (this.getOrientations().get(0).equals(LEFT) &&
                this.getOrientations().get(1).equals(BOTTOM) &&
                this.getSpeed() == 2) {
            path = "/TileImages/blue_belt_clockwise_left_top.png";
        }
        if (this.getOrientations().get(0).equals(TOP) &&
                this.getOrientations().get(1).equals(RIGHT) &&
                this.getSpeed() == 2) {
            path = "/TileImages/variants/blue_belt_clockwise_right_bottom.png";
        }
        if (this.getOrientations().get(0).equals(BOTTOM) &&
                this.getOrientations().get(1).equals(RIGHT) &&
                this.getSpeed() == 2) {
            path = "/TileImages/variants/blue_belt_counter_clockwise_right_bottom.png";
        }
        if (this.getOrientations().get(0).equals(LEFT) &&
                this.getOrientations().get(1).equals(TOP) &&
                this.getSpeed() == 2) {
            path = "/TileImages/blue_belt_clockwise_left_top.png";
        }
        if (this.getOrientations().get(0).equals(RIGHT) &&
                this.getOrientations().get(1).equals(BOTTOM) &&
                this.getSpeed() == 2) {
            path = "/TileImages/variants/blue_belt_clockwise_right_bottom.png";
        }
        if (this.getOrientations().get(0).equals(TOP) &&
                this.getOrientations().get(1).equals(LEFT) &&
                this.getSpeed() == 2) {
            path = "/TileImages/variants/blue_belt_counter_clockwise_top_left.png";
        }
        if (this.getOrientations().get(0).equals(BOTTOM) &&
                this.getOrientations().get(1).equals(LEFT) &&
                this.getSpeed() == 2) {
            path = "/TileImages/variants/blue_belt_clockwise_bottom_left.png";
        }
        if (this.getOrientations().get(0).equals(RIGHT) &&
                this.getOrientations().get(1).equals(TOP) &&
                this.getSpeed() == 2) {
            path = "/TileImages/variants/blue_belt_counter_clockwise_right_top.png";
        }

        if (getOrientations().size() >= 3) {
            if (this.getOrientations().get(0).equals(LEFT) &&
                    this.getOrientations().get(1).equals(RIGHT) &&
                    this.getOrientations().get(2).equals(BOTTOM) &&
                    this.getSpeed() == 2) {
                path = "/TileImages/variants/RB left.png";
            }
            if (this.getOrientations().get(0).equals(TOP) &&
                    this.getOrientations().get(1).equals(BOTTOM) &&
                    this.getOrientations().get(2).equals(RIGHT) &&
                    this.getSpeed() == 2) {
                path = "/TileImages/variants/RB top2.png";
            }
            if (this.getOrientations().get(0).equals(BOTTOM) &&
                    this.getOrientations().get(1).equals(TOP) &&
                    this.getOrientations().get(2).equals(RIGHT) &&
                    this.getSpeed() == 2) {
                path = "/TileImages/variants/RB Bottom.png";
            }
            if (this.getOrientations().get(0).equals(LEFT) &&
                    this.getOrientations().get(1).equals(RIGHT) &&
                    this.getOrientations().get(2).equals(TOP) &&
                    this.getSpeed() == 2) {
                path = "/TileImages/variants/RB left2.png";
            }
            if (this.getOrientations().get(0).equals(RIGHT) &&
                    this.getOrientations().get(1).equals(LEFT) &&
                    this.getOrientations().get(2).equals(BOTTOM) &&
                    this.getSpeed() == 2) {
                path = "/TileImages/variants/RB right2.png";
            }
            if (this.getOrientations().get(0).equals(TOP) &&
                    this.getOrientations().get(1).equals(BOTTOM) &&
                    this.getOrientations().get(2).equals(LEFT) &&
                    this.getSpeed() == 2) {
                path = "/TileImages/variants/RB top.png";
            }
            if (this.getOrientations().get(0).equals(BOTTOM) &&
                    this.getOrientations().get(1).equals(TOP) &&
                    this.getOrientations().get(2).equals(LEFT) &&
                    this.getSpeed() == 2) {
                path = "/TileImages/variants/RB Bottom 2.png";
            }
            if (this.getOrientations().get(0).equals(RIGHT) &&
                    this.getOrientations().get(1).equals(LEFT) &&
                    this.getOrientations().get(2).equals(TOP) &&
                    this.getSpeed() == 2) {
                path = "/TileImages/variants/RB right.png";
            }
        }

        return path;
    }
}


    /*public Position getBeltPosition() {
        return position;
    }

    public void setBeltPosition(Position position) {
        this.position = position;
    }

    //public PlayerTurning beltTurnCounterclockwise(Robot robot){
    //    if(robot.getRobotOrientation() == RIGHT){
    //        robot.setRobotOrientation(TOP);
    //    }else if(robot.getRobotOrientation() == TOP){
    //        robot.setRobotOrientation(LEFT);
    //    }else if(robot.getRobotOrientation() == LEFT){
    //        robot.setRobotOrientation(BOTTOM);
    //    }else if(robot.getRobotOrientation() == BOTTOM){
    //        robot.setRobotOrientation(RIGHT);
    //    }
    //    return new PlayerTurning(robot.getClientID(), "counterclockwise");
    //}
//
    //public PlayerTurning beltTurnClockwise(Robot robot){
    //    if(robot.getRobotOrientation() == RIGHT){
    //        robot.setRobotOrientation(BOTTOM);
    //    }else if(robot.getRobotOrientation() == BOTTOM){
    //        robot.setRobotOrientation(LEFT);
    //    }else if(robot.getRobotOrientation() == LEFT){
    //        robot.setRobotOrientation(TOP);
    //    }else if(robot.getRobotOrientation() == TOP){
    //        robot.setRobotOrientation(RIGHT);
    //    }
    //    return new PlayerTurning(robot.getClientID(), "clockwise");
    //}
//
    //public ArrayList<Message> moveOne(Robot robot) {
//
    //    ArrayList<Message> message = new ArrayList<>();
    //    int newRow;
    //    int newColumn;
//
    //    if (getBeltOrientation().contains(TOP)) {
    //        newRow = robot.getPosition().getRow() - 1;
    //        robot.getPosition().setColumn(newRow);
    //        message.add(new Movement(robot.getClientID(), robot.getPosition().getColumn(), newRow));
//
    //    }
    //    if (getBeltOrientation().contains(BOTTOM)) {
    //        newRow = robot.getPosition().getRow() + 1;
    //        robot.getPosition().setColumn(newRow);
    //        message.add(new Movement(robot.getClientID(), robot.getPosition().getColumn(), newRow));
//
    //    }
    //    if (getBeltOrientation().contains(LEFT)) {
    //        newColumn = robot.getPosition().getColumn() - 1;
    //        robot.getPosition().setColumn(newColumn);
    //        message.add(new Movement(robot.getClientID(), newColumn, robot.getPosition().getRow()));
//
    //    }
    //    if (getBeltOrientation().contains(RIGHT)) {
    //        newColumn = robot.getPosition().getColumn() + 1;
    //        robot.getPosition().setColumn(newColumn);
    //        message.add(new Movement(robot.getClientID(), newColumn, robot.getPosition().getRow()));
//
    //    }
    //    return message;
    //}
/*

    needs to .contains(ConveyorBelt) from board to finish logic!!

    public ArrayList<Message> moveTurnOne(Robot robot) {

        ArrayList<Message> message = new ArrayList<>();
        int newRow;
        int newColumn;

        if (getBeltOrientation().contains(TOP_LEFT)) {

            newColumn = robot.getPosition().getColumn() - 1;

            robot.getPosition().setColumn(newColumn);

            message.add(new Movement(robot.getClientID(), newColumn, robot.getPosition().getRow()));

            if(robot.getPreviousPosition().equals(getBeltPosition())){
                beltTurnCounterclockwise(robot);
            }

        }
        if (getBeltOrientation().contains(TOP_RIGHT)) {
            beltTurnClockwise(robot);

            newRow = robot.getPosition().getRow() - 1;
            newColumn = robot.getPosition().getColumn() + 1;

            robot.getPosition().setRow(newRow);
            robot.getPosition().setColumn(newColumn);

            message.add(new Movement(robot.getClientID(), robot.getPosition().getColumn(), newRow));
            message.add(new Movement(robot.getClientID(), newColumn, robot.getPosition().getRow()));

            if(robot.getPreviousPosition().equals(getBeltPosition())){
                beltTurnClockwise(robot);
            }
        }
        if (getBeltOrientation().contains(BOTTOM_LEFT)) {
            beltTurnClockwise(robot);

            newRow = robot.getPosition().getRow() + 1;
            newColumn = robot.getPosition().getColumn() - 1;

            robot.getPosition().setRow(newRow);
            robot.getPosition().setColumn(newColumn);

            message.add(new Movement(robot.getClientID(), robot.getPosition().getColumn(), newRow));
            message.add(new Movement(robot.getClientID(), newColumn, robot.getPosition().getRow()));
        }

        if (getBeltOrientation().contains(BOTTOM_RIGHT)) {
            beltTurnCounterclockwise(robot);

            newRow = robot.getPosition().getRow() + 1;
            newColumn = robot.getPosition().getColumn() + 1;

            robot.getPosition().setRow(newRow);
            robot.getPosition().setColumn(newColumn);

            message.add(new Movement(robot.getClientID(), robot.getPosition().getColumn(), newRow));
            message.add(new Movement(robot.getClientID(), newColumn, robot.getPosition().getRow()));
        }
        return message;
    }
*/
    //public ArrayList<Message> moveTwo(Robot robot) {
//
    //    ArrayList<Message> message = new ArrayList<>();
    //    int newRow;
    //    int newColumn;
//
    //    if (getBeltOrientation().contains(TOP)) {
    //        newRow = robot.getPosition().getRow() - 2;
    //        robot.getPosition().setColumn(newRow);
    //        message.add(new Movement(robot.getClientID(), robot.getPosition().getColumn(), newRow));
//
    //    }
    //    if (getBeltOrientation().contains(BOTTOM)) {
    //        newRow = robot.getPosition().getRow() + 2;
    //        robot.getPosition().setColumn(newRow);
    //        message.add(new Movement(robot.getClientID(), robot.getPosition().getColumn(), newRow));
//
    //    }
    //    if (getBeltOrientation().contains(LEFT)) {
    //        newColumn = robot.getPosition().getColumn() - 2;
    //        robot.getPosition().setColumn(newColumn);
    //        message.add(new Movement(robot.getClientID(), newColumn, robot.getPosition().getRow()));
//
    //    }
    //    if (getBeltOrientation().contains(RIGHT)) {
    //        newColumn = robot.getPosition().getColumn() + 2;
    //        robot.getPosition().setColumn(newColumn);
    //        message.add(new Movement(robot.getClientID(), newColumn, robot.getPosition().getRow()));
    //    }
    //    return message;
    //}
//
    //public ArrayList<Message> moveTurnTwo(Robot robot) {
//
    //    ArrayList<Message> message = new ArrayList<>();
    //    int newRow;
    //    int newColumn;
//
    //    if (getBeltOrientation().contains(TOP_LEFT)) {
    //        beltTurnCounterclockwise(robot);
//
    //        newRow = robot.getPosition().getRow() - 1;
    //        newColumn = robot.getPosition().getColumn() - 1;
//
    //        robot.getPosition().setRow(newRow);
    //        robot.getPosition().setColumn(newColumn);
//
    //        message.add(new Movement(robot.getClientID(), robot.getPosition().getColumn(), newRow));
    //        message.add(new Movement(robot.getClientID(), newColumn, robot.getPosition().getRow()));
//
    //    }
//
    //    if(getBeltOrientation().contains(LEFT_TOP)){
    //        beltTurnCounterclockwise(robot);
//
    //        newRow = robot.getPosition().getRow() - 1;
    //        newColumn = robot.getPosition().getColumn() - 1;
//
    //        robot.getPosition().setRow(newRow);
    //        robot.getPosition().setColumn(newColumn);
//
    //        message.add(new Movement(robot.getClientID(), newColumn, robot.getPosition().getRow()));
    //        message.add(new Movement(robot.getClientID(), robot.getPosition().getColumn(), newRow));
//
    //    }
//
    //    if (getBeltOrientation().contains(TOP_RIGHT)) {
    //        beltTurnClockwise(robot);
//
    //        newRow = robot.getPosition().getRow() - 1;
    //        newColumn = robot.getPosition().getColumn() + 1;
//
    //        robot.getPosition().setRow(newRow);
    //        robot.getPosition().setColumn(newColumn);
//
    //        message.add(new Movement(robot.getClientID(), robot.getPosition().getColumn(), newRow));
    //        message.add(new Movement(robot.getClientID(), newColumn, robot.getPosition().getRow()));
//
    //    }
    //    if(getBeltOrientation().contains(RIGHT_TOP)){
    //        beltTurnClockwise(robot);
//
    //        newRow = robot.getPosition().getRow() - 1;
    //        newColumn = robot.getPosition().getColumn() + 1;
//
    //        robot.getPosition().setRow(newRow);
    //        robot.getPosition().setColumn(newColumn);
//
    //        message.add(new Movement(robot.getClientID(), newColumn, robot.getPosition().getRow()));
    //        message.add(new Movement(robot.getClientID(), robot.getPosition().getColumn(), newRow));
//
    //    }
//
    //    if (getBeltOrientation().contains(BOTTOM_LEFT)) {
    //        beltTurnClockwise(robot);
//
    //        newRow = robot.getPosition().getRow() + 1;
    //        newColumn = robot.getPosition().getColumn() - 1;
//
    //        robot.getPosition().setRow(newRow);
    //        robot.getPosition().setColumn(newColumn);
//
    //        message.add(new Movement(robot.getClientID(), robot.getPosition().getColumn(), newRow));
    //        message.add(new Movement(robot.getClientID(), newColumn, robot.getPosition().getRow()));
    //    }
//
    //    if(getBeltOrientation().contains(LEFT_BOTTOM)){
    //        beltTurnClockwise(robot);
//
    //        newRow = robot.getPosition().getRow() + 1;
    //        newColumn = robot.getPosition().getColumn() - 1;
//
    //        robot.getPosition().setRow(newRow);
    //        robot.getPosition().setColumn(newColumn);
//
    //        message.add(new Movement(robot.getClientID(), newColumn, robot.getPosition().getRow()));
    //        message.add(new Movement(robot.getClientID(), robot.getPosition().getColumn(), newRow));
//
    //    }
//
    //    if (getBeltOrientation().contains(BOTTOM_RIGHT)) {
    //        beltTurnCounterclockwise(robot);
//
    //        newRow = robot.getPosition().getRow() + 1;
    //        newColumn = robot.getPosition().getColumn() + 1;
//
    //        robot.getPosition().setRow(newRow);
    //        robot.getPosition().setColumn(newColumn);
//
    //        message.add(new Movement(robot.getClientID(), robot.getPosition().getColumn(), newRow));
    //        message.add(new Movement(robot.getClientID(), newColumn, robot.getPosition().getRow()));
    //    }
//
    //    if (getBeltOrientation().contains(RIGHT_BOTTOM)) {
    //        beltTurnCounterclockwise(robot);
//
    //        newRow = robot.getPosition().getRow() + 1;
    //        newColumn = robot.getPosition().getColumn() + 1;
//
    //        robot.getPosition().setRow(newRow);
    //        robot.getPosition().setColumn(newColumn);
//
    //        message.add(new Movement(robot.getClientID(), newColumn, robot.getPosition().getRow()));
    //        message.add(new Movement(robot.getClientID(), robot.getPosition().getColumn(), newRow));
//
    //    }
//
    //    if (getBeltOrientation().contains(TOP_RIGHT)) {
    //        beltTurnCounterclockwise(robot);
//
    //        newRow = robot.getPosition().getRow() - 1;
    //        newColumn = robot.getPosition().getColumn() + 1;
//
    //        robot.getPosition().setRow(newRow);
    //        robot.getPosition().setColumn(newColumn);
//
    //        message.add(new Movement(robot.getClientID(), robot.getPosition().getColumn(), newRow));
    //        message.add(new Movement(robot.getClientID(), newColumn, robot.getPosition().getRow()));
    //    }
//
    //    if (getBeltOrientation().contains(RIGHT_TOP)) {
    //        beltTurnCounterclockwise(robot);
//
    //        newRow = robot.getPosition().getRow() - 1;
    //        newColumn = robot.getPosition().getColumn() + 1;
//
    //        robot.getPosition().setRow(newRow);
    //        robot.getPosition().setColumn(newColumn);
//
    //        message.add(new Movement(robot.getClientID(), newColumn, robot.getPosition().getRow()));
    //        message.add(new Movement(robot.getClientID(), robot.getPosition().getColumn(), newRow));
//
    //    }
//
    //    return message;
    //}



// beim turn row +-1 UND column +-1
// f[r jeede der Orientations 4 Optionen beim turn einschreiben im Bezug auf 90grad turn
// add right top/bottom && left-top/bottom

/*
Some conveyor belts have a curved arrow indicating a
rotating section of the belt.
Robots rotate 90 degrees in the direction of the curved
arrow as they move onto the curved section of the belt.

If a robot moves onto the curved section of a conveyor
belt by means other than the conveyor belt itself, the
robot does not turn 90 degrees.

If a robot moves onto the curved section of a conveyor
belt by means of the conveyor belt, but it moves from a
straight arrowed space instead of a curved arrow space,
it will not turn 90 degrees.
 */