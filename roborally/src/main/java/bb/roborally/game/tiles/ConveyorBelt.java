package bb.roborally.game.tiles;

import bb.roborally.data.messages.Message;
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
    private ArrayList<String> beltOrientation;
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

    public ArrayList<String> getBeltOrientation() {
        return beltOrientation;
    }

    public void setBeltOrientation(ArrayList<String> orientation) {
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

        if (getBeltOrientation().equals("TOP")) {
            int newRow = robot.getPosition().getRow() - 1;
            robot.getPosition().setColumn(newRow);


        }if (getBeltOrientation().equals("BOTTOM")) {
            int newRow = robot.getPosition().getRow() + 1;
            robot.getPosition().setColumn(newRow);

        }if (getBeltOrientation().equals("LEFT")) {
            int newColumn = robot.getPosition().getColumn() - 1;
            robot.getPosition().setColumn(newColumn);

        }if (getBeltOrientation().equals("RIGHT")) {
            int newColumn = robot.getPosition().getColumn() + 1;
            robot.getPosition().setColumn(newColumn);

        }if (getBeltOrientation().equals("TOP_LEFT")) {
            if(robot.getRobotOrientation().equals("LEFT")){
                robot.setRobotOrientation(Orientation.BOTTOM);
            }if(robot.getRobotOrientation().equals("RIGHT")){
                robot.setRobotOrientation(Orientation.RIGHT);
            }if(robot.getRobotOrientation().equals("FRONT")){
                robot.setRobotOrientation(Orientation.LEFT);
            }if(robot.getRobotOrientation().equals("BOTTOM")){
                robot.setRobotOrientation(Orientation.RIGHT);
            }
            int newRow = robot.getPosition().getRow() - 1;
            robot.getPosition().setColumn(newRow);

        }if (getBeltOrientation().equals("TOP_RIGHT")) {
            if(robot.getRobotOrientation().equals("LEFT")){
                robot.setRobotOrientation(Orientation.TOP);
            }if(robot.getRobotOrientation().equals("RIGHT")){
                robot.setRobotOrientation(Orientation.BOTTOM);
            }if(robot.getRobotOrientation().equals("TOP")){
                robot.setRobotOrientation(Orientation.RIGHT);
            }if(robot.getRobotOrientation().equals("BOTTOM")){
                robot.setRobotOrientation(Orientation.LEFT);
            }int newRow = robot.getPosition().getRow() - 1;
            robot.getPosition().setColumn(newRow);
        }
        if (getBeltOrientation().equals("BOTTOM_LEFT")) {
            if(robot.getRobotOrientation().equals("LEFT")){
                robot.setRobotOrientation(Orientation.BOTTOM);
            }if(robot.getRobotOrientation().equals("RIGHT")){
                robot.setRobotOrientation(Orientation.TOP);
            }if(robot.getRobotOrientation().equals("TOP")){
                robot.setRobotOrientation(Orientation.LEFT);
            }if(robot.getRobotOrientation().equals("BOTTOM")){
                robot.setRobotOrientation(Orientation.RIGHT);
            }
            int newRow = robot.getPosition().getRow() + 1;
            robot.getPosition().setColumn(newRow);
        }
        if (getBeltOrientation().equals("BOTTOM_RIGHT")) {
            if(robot.getRobotOrientation().equals("LEFT")){
                robot.setRobotOrientation(Orientation.TOP);
            }if(robot.getRobotOrientation().equals("RIGHT")){
                robot.setRobotOrientation(Orientation.BOTTOM);
            }if(robot.getRobotOrientation().equals("FRONT")){
                robot.setRobotOrientation(Orientation.RIGHT);
            }if(robot.getRobotOrientation().equals("BACK")){
                robot.setRobotOrientation(Orientation.LEFT);
            }
            int newRow = robot.getPosition().getRow() + 1;
            robot.getPosition().setColumn(newRow);
        }




    }


    public void moveOTwo(String robotName) {
        Robot robot = new Robot();

        if (robotName.equals(robot.getName())) {

            getBeltOrientation();

            if (getBeltOrientation().equals("FRONT")) {
                int newRow = robot.getPosition().getRow() - 2;
                robot.getPosition().setColumn(newRow);

            }if (getBeltOrientation().equals("BACK")) {
                int newRow = robot.getPosition().getRow() + 2;
                robot.getPosition().setColumn(newRow);

            }if (getBeltOrientation().equals("LEFT")) {
                int newColumn = robot.getPosition().getColumn() - 2;
                robot.getPosition().setColumn(newColumn);

            }if (getBeltOrientation().equals("RIGHT")) {
                int newColumn = robot.getPosition().getColumn() + 2;
                robot.getPosition().setColumn(newColumn);
            }
        }if (getBeltOrientation().equals("FRONT_LEFT")) {
            if(robot.getRobotOrientation().equals("LEFT")){
                robot.setRobotOrientation(Orientation.BOTTOM);
            }if(robot.getRobotOrientation().equals("RIGHT")){
                robot.setRobotOrientation(Orientation.RIGHT);
            }if(robot.getRobotOrientation().equals("FRONT")){
                robot.setRobotOrientation(Orientation.LEFT);
            }if(robot.getRobotOrientation().equals("BACK")){
                robot.setRobotOrientation(Orientation.RIGHT);
            }
            int newRow = robot.getPosition().getRow() - 2;
            robot.getPosition().setColumn(newRow);

        }if (getBeltOrientation().equals("FRONT_RIGHT")) {
            if(robot.getRobotOrientation().equals("LEFT")){
                robot.setRobotOrientation(Orientation.FRONT);
            }if(robot.getRobotOrientation().equals("RIGHT")){
                robot.setRobotOrientation(Orientation.BOTTOM);
            }if(robot.getRobotOrientation().equals("FRONT")){
                robot.setRobotOrientation(Orientation.RIGHT);
            }if(robot.getRobotOrientation().equals("BACK")){
                robot.setRobotOrientation(Orientation.LEFT);
            }
            int newRow = robot.getPosition().getRow() - 2;
            robot.getPosition().setColumn(newRow);
        }
        if (getBeltOrientation().equals("BACK_LEFT")) {
            if(robot.getRobotOrientation().equals("LEFT")){
                robot.setRobotOrientation(Orientation.BOTTOM);
            }if(robot.getRobotOrientation().equals("RIGHT")){
                robot.setRobotOrientation(Orientation.FRONT);
            }if(robot.getRobotOrientation().equals("FRONT")){
                robot.setRobotOrientation(Orientation.LEFT);
            }if(robot.getRobotOrientation().equals("BACK")){
                robot.setRobotOrientation(Orientation.RIGHT);
            }
            int newRow = robot.getPosition().getRow() + 2;
            robot.getPosition().setColumn(newRow);
        }
        if (getBeltOrientation().equals("BACK_RIGHT")) {
            if(robot.getRobotOrientation().equals("LEFT")){
                robot.setRobotOrientation(Orientation.FRONT);
            }if(robot.getRobotOrientation().equals("RIGHT")){
                robot.setRobotOrientation(Orientation.BOTTOM);
            }if(robot.getRobotOrientation().equals("FRONT")){
                robot.setRobotOrientation(Orientation.RIGHT);
            }if(robot.getRobotOrientation().equals("BACK")){
                robot.setRobotOrientation(Orientation.LEFT);
            }
            int newRow = robot.getPosition().getRow() + 2;
            robot.getPosition().setColumn(newRow);
        }
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