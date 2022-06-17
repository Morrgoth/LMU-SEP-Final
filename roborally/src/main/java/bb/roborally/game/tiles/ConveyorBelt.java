package bb.roborally.game.tiles;

import bb.roborally.data.messages.Message;
import bb.roborally.data.messages.game_events.Movement;
import bb.roborally.data.messages.game_events.PlayerTurning;
import static bb.roborally.game.Orientation.*;

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
public class ConveyorBelt extends Tile {

    private Position position;
    // map declaration
    private String isOnBoard;
    private ArrayList <Orientation> beltOrientation;
    private int layer;
    private int activationOrder;
    private boolean isEmpty = false;
    private int speed;

    public ConveyorBelt(){

    }

    public ConveyorBelt(Position position,String isOnBoard,ArrayList beltOrientation,int activationOrder,boolean isEmpty,int speed){
        this.position = position;
        this.isOnBoard = isOnBoard;
        this.beltOrientation = beltOrientation;
        this.activationOrder = activationOrder;
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

    @Override
    public int getLayer() {
        return layer;
    }

    @Override
    public void setLayer(int layer) {
        this.layer = layer;
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
    String getName() {
        return "ConveyorBelt"; }

    @Override
    public Position getPosition() {
        return position;
    }

    @Override
    public void setPosition(Position position) {
        this.position = position;
    }
//FRONT -1 & BACK +1 because our matrix starts in the left top corner

    public ArrayList <Message> moveOne(Robot robot) {

        ArrayList<Message> message = new ArrayList<>();
        int newRow;
        int newColumn;

        if (getBeltOrientation().contains(TOP)) {
            newRow = robot.getPosition().getRow() - 1;
            robot.getPosition().setColumn(newRow);

            message.add(new Movement(robot.getClientID(), robot.getPosition().getColumn(), newRow));

        }if (getBeltOrientation().contains(BOTTOM)) {
            newRow = robot.getPosition().getRow() + 1;
            robot.getPosition().setColumn(newRow);
            message.add(new Movement(robot.getClientID(),robot.getPosition().getColumn(),newRow));

        }if (getBeltOrientation().contains(LEFT)) {
            newColumn = robot.getPosition().getColumn() - 1;
            robot.getPosition().setColumn(newColumn);
            message.add(new Movement(robot.getClientID(),newColumn,robot.getPosition().getRow()));

        }if (getBeltOrientation().contains(RIGHT)) {
            newColumn = robot.getPosition().getColumn() + 1;
            robot.getPosition().setColumn(newColumn);
            message.add(new Movement(robot.getClientID(),newColumn,robot.getPosition().getRow()));

        }
            newColumn = robot.getPosition().getColumn() + 1;
            robot.getPosition().setColumn(newColumn);
            message.add(new Movement(robot.getClientID(),newColumn,robot.getPosition().getRow()));
        return message;
    }
    public ArrayList <Message> moveTurnOne(Robot robot) {

        ArrayList<Message> message = new ArrayList<>();
        int newRow;
        int newColumn;

        if (getBeltOrientation().contains(TOP_LEFT)) {
            if(robot.getRobotOrientation().equals(LEFT)) {
                robot.setRobotOrientation(LEFT);
                message.add(new PlayerTurning(robot.getClientID(), "counterclockwise"));
                newRow = robot.getPosition().getRow() - 1;
                robot.getPosition().setColumn(newRow);
                message.add(new Movement(robot.getClientID(), robot.getPosition().getColumn(), newRow));
            }
        }if (getBeltOrientation().contains(TOP_RIGHT)) {
            if (robot.getRobotOrientation().equals(RIGHT)) {
                robot.setRobotOrientation(RIGHT);
                message.add(new PlayerTurning(robot.getClientID(), "clockwise"));
                newRow = robot.getPosition().getRow() - 1;
                robot.getPosition().setColumn(newRow);
                message.add(new Movement(robot.getClientID(), robot.getPosition().getColumn(), newRow));
            }
        }
        if (getBeltOrientation().contains(BOTTOM_LEFT)) {
            if (robot.getRobotOrientation().equals(LEFT)) {
                robot.setRobotOrientation(LEFT);
                message.add(new PlayerTurning(robot.getClientID(), "counterclockwise"));
                newColumn = robot.getPosition().getColumn() + 1;
                robot.getPosition().setColumn(newColumn);
                message.add(new Movement(robot.getClientID(), newColumn, robot.getPosition().getRow()));
            }
        }
        if (getBeltOrientation().contains(BOTTOM_RIGHT)) {
            if(robot.getRobotOrientation().equals(RIGHT)) {
                robot.setRobotOrientation(RIGHT);
                message.add(new PlayerTurning(robot.getClientID(), "clockwise"));
                newColumn = robot.getPosition().getColumn() + 1;
                robot.getPosition().setColumn(newColumn);
                message.add(new Movement(robot.getClientID(), newColumn, robot.getPosition().getRow()));
            }
        }
        return message;
    }
    public ArrayList <Message> moveTwo(Robot robot) {

        ArrayList<Message> message = new ArrayList<>();
        int newRow;
        int newColumn;

        if (getBeltOrientation().contains(TOP)) {
            newRow = robot.getPosition().getRow() - 2;
            robot.getPosition().setColumn(newRow);

            message.add(new Movement(robot.getClientID(), robot.getPosition().getColumn(), newRow));

        }if (getBeltOrientation().contains(BOTTOM)) {
            newRow = robot.getPosition().getRow() + 2;
            robot.getPosition().setColumn(newRow);
            message.add(new Movement(robot.getClientID(),robot.getPosition().getColumn(),newRow));

        }if (getBeltOrientation().contains(LEFT)) {
            newColumn = robot.getPosition().getColumn() - 2;
            robot.getPosition().setColumn(newColumn);
            message.add(new Movement(robot.getClientID(),newColumn,robot.getPosition().getRow()));

        }if (getBeltOrientation().contains(RIGHT)) {
            newColumn = robot.getPosition().getColumn() + 2;
            robot.getPosition().setColumn(newColumn);
            message.add(new Movement(robot.getClientID(),newColumn,robot.getPosition().getRow()));

        }
        newColumn = robot.getPosition().getColumn() + 2;
        robot.getPosition().setColumn(newColumn);
        message.add(new Movement(robot.getClientID(),newColumn,robot.getPosition().getRow()));
        return message;
    }

    public ArrayList <Message> moveTurnTwo(Robot robot) {

        ArrayList<Message> message = new ArrayList<>();
        int newRow;
        int newColumn;

        if (getBeltOrientation().contains(TOP_LEFT)) {
            if(robot.getRobotOrientation().equals(LEFT)) {
                robot.setRobotOrientation(LEFT);
                message.add(new PlayerTurning(robot.getClientID(), "counterclockwise"));
                newRow = robot.getPosition().getRow() - 2;
                robot.getPosition().setColumn(newRow);
                message.add(new Movement(robot.getClientID(), robot.getPosition().getColumn(), newRow));
            }
        }if (getBeltOrientation().contains(TOP_RIGHT)) {
            if (robot.getRobotOrientation().equals(RIGHT)) {
                robot.setRobotOrientation(RIGHT);
                message.add(new PlayerTurning(robot.getClientID(), "clockwise"));
                newRow = robot.getPosition().getRow() - 2;
                robot.getPosition().setColumn(newRow);
                message.add(new Movement(robot.getClientID(), robot.getPosition().getColumn(), newRow));
            }
        }
        if (getBeltOrientation().contains(BOTTOM_LEFT)) {
            if (robot.getRobotOrientation().equals(LEFT)) {
                robot.setRobotOrientation(LEFT);
                message.add(new PlayerTurning(robot.getClientID(), "counterclockwise"));
                newColumn = robot.getPosition().getColumn() + 2;
                robot.getPosition().setColumn(newColumn);
                message.add(new Movement(robot.getClientID(), newColumn, robot.getPosition().getRow()));
            }
        }
        if (getBeltOrientation().contains(BOTTOM_RIGHT)) {
            if(robot.getRobotOrientation().equals(RIGHT)) {
                robot.setRobotOrientation(RIGHT);
                message.add(new PlayerTurning(robot.getClientID(), "clockwise"));
                newColumn = robot.getPosition().getColumn() + 2;
                robot.getPosition().setColumn(newColumn);
                message.add(new Movement(robot.getClientID(), newColumn, robot.getPosition().getRow()));
            }
        }
        return message;
    }

}


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