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
import static bb.roborally.game.Orientation.LEFT_BOTTOM;
import static bb.roborally.game.Orientation.LEFT_TOP;
import static bb.roborally.game.Orientation.RIGHT_TOP;
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

    private Position position;
    // map declaration
    private String isOnBoard;
    private ArrayList<Orientation> beltOrientation;
    private int activationOrder;
    private boolean isEmpty = false;
    private int speed;

    public ConveyorBelt() {

    }

    public ConveyorBelt(Position position, String isOnBoard, ArrayList<Orientation> beltOrientation, int activationOrder, boolean isEmpty, int speed) {
        this.position = position;
        this.isOnBoard = isOnBoard;
        this.beltOrientation = beltOrientation;
        this.activationOrder = activationOrder;
        this.isEmpty = isEmpty;
        this.speed = speed;
    }

    public ConveyorBelt(Position position, String isOnBoard, ArrayList<Orientation> beltOrientation, boolean isEmpty, int speed) {
        this.position = position;
        this.isOnBoard = isOnBoard;
        this.beltOrientation = beltOrientation;
        this.isEmpty = isEmpty;
        this.speed = speed;
    }


    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {

        this.speed = speed;
    }

    @Override
    public boolean isEmpty() {
        return isEmpty;
    }

    @Override
    public void setEmpty(boolean empty) {
        isEmpty = empty;
    }

    @Override
    public int getActivationOrder() {
        return activationOrder;
    }

    @Override
    public int setActivationOrder(int activationOrder) {

        this.activationOrder = activationOrder;
        return activationOrder;
    }

    public ArrayList<Orientation> getBeltOrientation() {
        return beltOrientation;
    }

    public void setBeltOrientation(ArrayList<Orientation> orientation) {
        this.beltOrientation = orientation;
    }

    @Override
    public String getIsOnBoard() {
        return isOnBoard;
    }

    @Override
    public void setIsOnBoard(String isOnBoard) {
        this.isOnBoard = isOnBoard;
    }

    @Override
    public String getType() {
        return "ConveyorBelt";
    }

    public Position getBeltPosition() {
        return position;
    }

    public void setBeltPosition(Position position) {
        this.position = position;
    }

    public int declareActivationOrder(int speed){
        this.speed = speed;
        if(speed == 1){
            setActivationOrder(2);
        }
        if(speed == 2){
            setActivationOrder(1);
        }
        return activationOrder;
    }

    public PlayerTurning beltTurnCounterclockwise(Robot robot){
        if(robot.getRobotOrientation() == RIGHT){
            robot.setRobotOrientation(TOP);
        }else if(robot.getRobotOrientation() == TOP){
            robot.setRobotOrientation(LEFT);
        }else if(robot.getRobotOrientation() == LEFT){
            robot.setRobotOrientation(BOTTOM);
        }else if(robot.getRobotOrientation() == BOTTOM){
            robot.setRobotOrientation(RIGHT);
        }
        return new PlayerTurning(robot.getClientID(), "counterclockwise");
    }

    public PlayerTurning beltTurnClockwise(Robot robot){
        if(robot.getRobotOrientation() == RIGHT){
            robot.setRobotOrientation(BOTTOM);
        }else if(robot.getRobotOrientation() == BOTTOM){
            robot.setRobotOrientation(LEFT);
        }else if(robot.getRobotOrientation() == LEFT){
            robot.setRobotOrientation(TOP);
        }else if(robot.getRobotOrientation() == TOP){
            robot.setRobotOrientation(RIGHT);
        }
        return new PlayerTurning(robot.getClientID(), "clockwise");
    }

    public ArrayList<Message> moveOne(Robot robot) {

        ArrayList<Message> message = new ArrayList<>();
        int newRow;
        int newColumn;

        if (getBeltOrientation().contains(TOP)) {
            newRow = robot.getPosition().getRow() - 1;
            robot.getPosition().setColumn(newRow);
            message.add(new Movement(robot.getClientID(), robot.getPosition().getColumn(), newRow));

        }
        if (getBeltOrientation().contains(BOTTOM)) {
            newRow = robot.getPosition().getRow() + 1;
            robot.getPosition().setColumn(newRow);
            message.add(new Movement(robot.getClientID(), robot.getPosition().getColumn(), newRow));

        }
        if (getBeltOrientation().contains(LEFT)) {
            newColumn = robot.getPosition().getColumn() - 1;
            robot.getPosition().setColumn(newColumn);
            message.add(new Movement(robot.getClientID(), newColumn, robot.getPosition().getRow()));

        }
        if (getBeltOrientation().contains(RIGHT)) {
            newColumn = robot.getPosition().getColumn() + 1;
            robot.getPosition().setColumn(newColumn);
            message.add(new Movement(robot.getClientID(), newColumn, robot.getPosition().getRow()));

        }
        return message;
    }
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
    public ArrayList<Message> moveTwo(Robot robot) {

        ArrayList<Message> message = new ArrayList<>();
        int newRow;
        int newColumn;

        if (getBeltOrientation().contains(TOP)) {
            newRow = robot.getPosition().getRow() - 2;
            robot.getPosition().setColumn(newRow);
            message.add(new Movement(robot.getClientID(), robot.getPosition().getColumn(), newRow));

        }
        if (getBeltOrientation().contains(BOTTOM)) {
            newRow = robot.getPosition().getRow() + 2;
            robot.getPosition().setColumn(newRow);
            message.add(new Movement(robot.getClientID(), robot.getPosition().getColumn(), newRow));

        }
        if (getBeltOrientation().contains(LEFT)) {
            newColumn = robot.getPosition().getColumn() - 2;
            robot.getPosition().setColumn(newColumn);
            message.add(new Movement(robot.getClientID(), newColumn, robot.getPosition().getRow()));

        }
        if (getBeltOrientation().contains(RIGHT)) {
            newColumn = robot.getPosition().getColumn() + 2;
            robot.getPosition().setColumn(newColumn);
            message.add(new Movement(robot.getClientID(), newColumn, robot.getPosition().getRow()));
        }
        return message;
    }

    public ArrayList<Message> moveTurnTwo(Robot robot) {

        ArrayList<Message> message = new ArrayList<>();
        int newRow;
        int newColumn;

        if (getBeltOrientation().contains(TOP_LEFT)) {
            beltTurnCounterclockwise(robot);

            newRow = robot.getPosition().getRow() - 1;
            newColumn = robot.getPosition().getColumn() - 1;

            robot.getPosition().setRow(newRow);
            robot.getPosition().setColumn(newColumn);

            message.add(new Movement(robot.getClientID(), robot.getPosition().getColumn(), newRow));
            message.add(new Movement(robot.getClientID(), newColumn, robot.getPosition().getRow()));

        }

        if(getBeltOrientation().contains(LEFT_TOP)){
            beltTurnCounterclockwise(robot);

            newRow = robot.getPosition().getRow() - 1;
            newColumn = robot.getPosition().getColumn() - 1;

            robot.getPosition().setRow(newRow);
            robot.getPosition().setColumn(newColumn);

            message.add(new Movement(robot.getClientID(), newColumn, robot.getPosition().getRow()));
            message.add(new Movement(robot.getClientID(), robot.getPosition().getColumn(), newRow));

        }

        if (getBeltOrientation().contains(TOP_RIGHT)) {
            beltTurnClockwise(robot);

            newRow = robot.getPosition().getRow() - 1;
            newColumn = robot.getPosition().getColumn() + 1;

            robot.getPosition().setRow(newRow);
            robot.getPosition().setColumn(newColumn);

            message.add(new Movement(robot.getClientID(), robot.getPosition().getColumn(), newRow));
            message.add(new Movement(robot.getClientID(), newColumn, robot.getPosition().getRow()));

        }
        if(getBeltOrientation().contains(RIGHT_TOP)){
            beltTurnClockwise(robot);

            newRow = robot.getPosition().getRow() - 1;
            newColumn = robot.getPosition().getColumn() + 1;

            robot.getPosition().setRow(newRow);
            robot.getPosition().setColumn(newColumn);

            message.add(new Movement(robot.getClientID(), newColumn, robot.getPosition().getRow()));
            message.add(new Movement(robot.getClientID(), robot.getPosition().getColumn(), newRow));

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

        if(getBeltOrientation().contains(LEFT_BOTTOM)){
            beltTurnClockwise(robot);

            newRow = robot.getPosition().getRow() + 1;
            newColumn = robot.getPosition().getColumn() - 1;

            robot.getPosition().setRow(newRow);
            robot.getPosition().setColumn(newColumn);

            message.add(new Movement(robot.getClientID(), newColumn, robot.getPosition().getRow()));
            message.add(new Movement(robot.getClientID(), robot.getPosition().getColumn(), newRow));

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

        if (getBeltOrientation().contains(Orientation.RIGHT_BOTTOM)) {
            beltTurnCounterclockwise(robot);

            newRow = robot.getPosition().getRow() + 1;
            newColumn = robot.getPosition().getColumn() + 1;

            robot.getPosition().setRow(newRow);
            robot.getPosition().setColumn(newColumn);

            message.add(new Movement(robot.getClientID(), newColumn, robot.getPosition().getRow()));
            message.add(new Movement(robot.getClientID(), robot.getPosition().getColumn(), newRow));

        }

        if (getBeltOrientation().contains(TOP_RIGHT)) {
            beltTurnCounterclockwise(robot);

            newRow = robot.getPosition().getRow() - 1;
            newColumn = robot.getPosition().getColumn() + 1;

            robot.getPosition().setRow(newRow);
            robot.getPosition().setColumn(newColumn);

            message.add(new Movement(robot.getClientID(), robot.getPosition().getColumn(), newRow));
            message.add(new Movement(robot.getClientID(), newColumn, robot.getPosition().getRow()));
        }

        if (getBeltOrientation().contains(RIGHT_TOP)) {
            beltTurnCounterclockwise(robot);

            newRow = robot.getPosition().getRow() - 1;
            newColumn = robot.getPosition().getColumn() + 1;

            robot.getPosition().setRow(newRow);
            robot.getPosition().setColumn(newColumn);

            message.add(new Movement(robot.getClientID(), newColumn, robot.getPosition().getRow()));
            message.add(new Movement(robot.getClientID(), robot.getPosition().getColumn(), newRow));

        }

        return message;
    }


}

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