package bb.roborally.server.game.activation;


import bb.roborally.server.game.Orientation;
import bb.roborally.server.game.Position;
import bb.roborally.server.game.Robot;
import bb.roborally.server.game.User;
import bb.roborally.server.game.board.Board;


import java.util.Random;


//all Checks of RoboterMovement implemented here
public class MovementCheck {
    Board board;

    //1st Method WallCheck - checks the same Field of Robot and Wall
    public  boolean wallOnSameFieldForwardCheck(User user){
        boolean wallForward = false;
        Robot robot = user.getRobot();
        Position position = robot.getPosition();

        //check the robot Orientation compared to the wall in same cell and check with orientation of the wall
        if(robot.getRobotOrientation().equals(Orientation.TOP)){
            //first check if certain cell on board contains a wall - get(1) for cells with wall ans LaserRay / get(2) for Wall and normal Laser
            if(board.get(position.getX(), position.getY()).getTiles().get(1).equals(board.get(position.getX(), position.getY()).getTile("Wall")) ||
                    board.get(position.getX(), position.getY()).getTiles().get(2).equals(board.get(position.getX(), position.getY()).getTile("Wall"))) {
                if (board.get(position.getX(), position.getY()).getTile("Wall").getOrientations().get(0).equals(Orientation.TOP)) {
                    wallForward = true;
                }
            }
        }

        if(robot.getRobotOrientation().equals(Orientation.BOTTOM)){
            if(board.get(position.getX(), position.getY()).getTiles().get(1).equals(board.get(position.getX(), position.getY()).getTile("Wall")) ||
                    board.get(position.getX(), position.getY()).getTiles().get(2).equals(board.get(position.getX(), position.getY()).getTile("Wall"))) {
                if (board.get(position.getX(), position.getY()).getTile("Wall").getOrientations().get(0).equals(Orientation.BOTTOM)) {
                    wallForward = true;
                }
            }
        }

        if(robot.getRobotOrientation().equals(Orientation.LEFT)){
            if(board.get(position.getX(), position.getY()).getTiles().get(1).equals(board.get(position.getX(), position.getY()).getTile("Wall")) ||
                    board.get(position.getX(), position.getY()).getTiles().get(2).equals(board.get(position.getX(), position.getY()).getTile("Wall"))) {
                if (board.get(position.getX(), position.getY()).getTile("Wall").getOrientations().get(0).equals(Orientation.LEFT)) {
                    wallForward = true;
                }
            }
        }
        if(robot.getRobotOrientation().equals(Orientation.RIGHT)){
            if(board.get(position.getX(), position.getY()).getTiles().get(1).equals(board.get(position.getX(), position.getY()).getTile("Wall")) ||
                    board.get(position.getX(), position.getY()).getTiles().get(2).equals(board.get(position.getX(), position.getY()).getTile("Wall"))) {
                if (board.get(position.getX(), position.getY()).getTile("Wall").getOrientations().get(0).equals(Orientation.RIGHT)) {
                    wallForward = true;
                }
            }
        }


        //Check if field next to the robot has a wall, that is blocking the move
        //first check if certain cell minus position of Robot (depending on robot orientation) board contains a wall - get(1) for cells with wall ans LaserRay / get(2) for Wall and normal Laser
        if(robot.getRobotOrientation().equals(Orientation.TOP)) {
            if (board.get(position.getX(), position.getY() - 1).getTiles().get(1).equals(board.get(position.getX(), position.getY()- 1).getTile("Wall")) ||
                    board.get(position.getX(), position.getY() - 1).getTiles().get(2).equals(board.get(position.getX(), position.getY() - 1).getTile("Wall"))) {
                //check the robot Orientation compared to the wall in same cell and check with orientation of the wall
                if (board.get(position.getX(), position.getY() - 1).getTile("Wall").getOrientations().get(0).equals(Orientation.BOTTOM)) {
                    wallForward = true;
                }
            }
        }
        if(robot.getRobotOrientation().equals(Orientation.BOTTOM)) {
            if (board.get(position.getX(), position.getY() + 1).getTiles().get(1).equals(board.get(position.getX(), position.getY() + 1).getTile("Wall")) ||
                    board.get(position.getX(), position.getY() + 1).getTiles().get(2).equals(board.get(position.getX(), position.getY() + 1).getTile("Wall"))) {
                //check the robot Orientation compared to the wall in same cell and check with orientation of the wall
                if (board.get(position.getX(), position.getY() + 1).getTile("Wall").getOrientations().get(0).equals(Orientation.TOP)) {
                    wallForward = true;
                }
            }
        }

        if(robot.getRobotOrientation().equals(Orientation.LEFT)) {
            if (board.get(position.getX() - 1, position.getY()).getTiles().get(1).equals(board.get(position.getX() - 1, position.getY()).getTile("Wall")) ||
                    board.get(position.getX() - 1, position.getY()).getTiles().get(2).equals(board.get(position.getX() - 1, position.getY()).getTile("Wall"))) {
                //check the robot Orientation compared to the wall in same cell and check with orientation of the wall
                if (board.get(position.getX() -1, position.getY()).getTile("Wall").getOrientations().get(0).equals(Orientation.RIGHT)) {
                    wallForward= true;
                }
            }
        }
        if(robot.getRobotOrientation().equals(Orientation.RIGHT)) {
            if (board.get(position.getX() + 1, position.getY()).getTiles().get(1).equals(board.get(position.getX() + 1, position.getY()).getTile("Wall")) ||
                    board.get(position.getX() + 1, position.getY()).getTiles().get(2).equals(board.get(position.getX() + 1, position.getY()).getTile("Wall"))) {
                //check the robot Orientation compared to the wall in same cell and check with orientation of the wall
                if (board.get(position.getX() + 1, position.getY()).getTile("Wall").getOrientations().get(0).equals(Orientation.LEFT)) {
                    wallForward = true;
                }
            }
        }
        return wallForward;
    }


    //is Robot forward Check
    public boolean robotForwardCheck(User user1, User user2){
        boolean robotForward = false;
        Robot robot1 = user1.getRobot();
        Robot robot2 = user2.getRobot();
        Position position1 = robot1.getPosition();
        Position position2 = robot2.getPosition();

        //check the orientation of robot1
        if (robot1.getRobotOrientation().equals(Orientation.TOP)) {
            //if one move forward (based on the orientation of the robot) is the same position as  robot2 --> collusion
            if (board.get(position1.getX() , position1.getY() - 1).getPosition().equals(board.get(position2.getX(), position2.getX() - 1).getPosition())) {
                robotForward = true;
            }
        }

        if (robot1.getRobotOrientation().equals(Orientation.BOTTOM)) {
            if (board.get(position1.getX() , position1.getY() + 1).getPosition().equals(board.get(position2.getX(), position2.getX()).getPosition())) {
                robotForward = true;
            }
        }

        if (robot1.getRobotOrientation().equals(Orientation.LEFT)) {
            if (board.get(position1.getX() - 1, position1.getY()).getPosition().equals(board.get(position2.getX() - 1, position2.getX()).getPosition())) {
                robotForward = true;
            }
        }

        if (robot1.getRobotOrientation().equals(Orientation.RIGHT)) {
            if (board.get(position1.getX() + 1, position1.getY()).getPosition().equals(board.get(position2.getX(), position2.getX()).getPosition())) {
                robotForward = true;
            }
        }

        return robotForward;
    }

    //RebootPointCheck - randomized Orientation
    public Orientation rebootPointStartOrientation(User user){
        int pick = new Random().nextInt(Orientation.values().length);
        Robot robot = user.getRobot();
        Position position = robot.getPosition();
        if(board.get(position.getX(), position.getY()).getTiles().get(1).equals(board.get(position.getX(), position.getY()).getTile("RestartPoint"))){
            position.setX(board.get(position.getX(),position.getY()).getPosition().getX());
            position.setY(board.get(position.getY(),position.getY()).getPosition().getY());
            robot.setRobotOrientation(Orientation.values()[pick]);
        }
        return null;
    }


    //PitCheck
    public boolean fallingInPit(User user) {
        boolean pitPassing = false;
        Robot robot = user.getRobot();
        Position position = robot.getPosition();

        //check if cell on board contains Pit
        if (board.get(position.getX(), position.getY()).getTiles().get(1).equals(board.get(position.getX(), position.getY()).getTile("Pit"))) {
            //check if position of robot has a specific cell on the board with the same coordinates --> if all true --> Pit == true
            pitPassing = true;
        }

        return pitPassing;
    }

    public boolean robotIsOffBoard(User user){
        boolean robotOffBoard = false;
        Robot robot = user.getRobot();
        Position position = robot.getPosition();
        if(robot.getRobotOrientation().equals(Orientation.TOP)){
            if(position.getY() -1 < board.getGameMap().get(position.getY()).size()){
                robotOffBoard = true;
            }
            if(robot.getRobotOrientation().equals(Orientation.BOTTOM)){
                if(position.getY() +1 > board.getGameMap().get(position.getY()).size()){
                    robotOffBoard = true;
                }
            }
            if(robot.getRobotOrientation().equals(Orientation.LEFT)){
                if(position.getX() - 1 < board.getGameMap().size()){
                    robotOffBoard = true;
                }
            }
            if(robot.getRobotOrientation().equals(Orientation.RIGHT)){
                if(position.getX() + 1 > board.getGameMap().size()){
                    robotOffBoard = true;
                }
            }
        }
        return robotOffBoard;
    }
}
