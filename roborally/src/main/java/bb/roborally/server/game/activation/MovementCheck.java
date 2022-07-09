package bb.roborally.server.game.activation;


import bb.roborally.server.game.Orientation;
import bb.roborally.server.game.Position;
import bb.roborally.server.game.Robot;
import bb.roborally.server.game.User;
import bb.roborally.server.game.board.Board;
import bb.roborally.server.game.board.Cell;
import bb.roborally.server.game.tiles.Wall;


//all Checks of Robotermovement implemented here
public class MovementCheck {
    Board board;

    //is Wall forward Check
    public boolean wallForwardCheck(User user){
        boolean wallForward = false;
        Robot robot = user.getRobot();
        Position position = robot.getPosition();
        //first check if certain cell on board contains a wall - get(1) for cells with wall ans LaserRay / get(2) for Wall and normal Laser
        if(board.get(position.getX(), position.getY()).getTiles().get(1).equals(board.get(position.getX(), position.getY()).getTile("Wall")) ||
           board.get(position.getX(), position.getY()).getTiles().get(2).equals(board.get(position.getX(), position.getY()).getTile("Wall"))){
            //Check if position of Robot is equal to position of cell-position on board
            if(position.equals(board.get(position.getX(), position.getY()).getPosition())){
                //check the robot Orientation compared to the wall in same cell and check with orientation of the wall
                if(robot.getRobotOrientation().equals(Orientation.TOP) &&
                    board.get(position.getX(), position.getY()).getTile("Wall").getOrientations().get(0).equals(Orientation.TOP)) {
                    wallForward = true;
                }

                if(robot.getRobotOrientation().equals(Orientation.BOTTOM) &&
                    board.get(position.getX(), position.getY()).getTile("Wall").getOrientations().get(0).equals(Orientation.BOTTOM)){
                    wallForward = true;
                }

                if(robot.getRobotOrientation().equals(Orientation.LEFT) &&
                    board.get(position.getX(), position.getY()).getTile("Wall").getOrientations().get(0).equals(Orientation.LEFT)){
                    wallForward = true;
                }

                if(robot.getRobotOrientation().equals(Orientation.RIGHT) &&
                    board.get(position.getX(), position.getY()).getTile("Wall").getOrientations().get(0).equals(Orientation.RIGHT)) {
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

        //check if the position of the position of robot 1 and 2 have certain positions on board
        if(position1.equals(board.get(position1.getX(), position1.getY()).getPosition()) &&
           position2.equals(board.get(position2.getX(), position2.getY()).getPosition())) {
            //check the orientation of robot1
            if (robot1.getRobotOrientation().equals(Orientation.TOP)) {
                //if one move forward (based on the orientation of the robot) is the same position as  robot2 --> collusion
                if (position1.getY() - 1 == position2.getY() && position1.getX() == position2.getX()) {
                    robotForward = true;
                }
            }

            if (robot1.getRobotOrientation().equals(Orientation.BOTTOM)) {
                if (position1.getY() + 1 == position2.getY() && position1.getX() == position1.getX()) {
                    robotForward = true;
                }
            }


            if (robot1.getRobotOrientation().equals(Orientation.LEFT)) {
                if (position1.getY() == position2.getY() && position1.getX() - 1 == position2.getX()) {
                    robotForward = true;
                }
            }

            if (robot1.getRobotOrientation().equals(Orientation.RIGHT)) {
                if (position1.getY() == position2.getY() && position1.getX() + 1 == position2.getX()) {
                    robotForward = true;
                }
            }
        }
        return robotForward;
    }

    //RebootPointCheck
    public boolean rebootPassingCheck(User user){
        boolean rebootPassing = false;
        Robot robot = user.getRobot();
        Position position = robot.getPosition();


        //check if specific cell on board contains rebootPoint
        if(board.get(position.getX(), position.getY()).getTiles().get(1).equals(board.get(position.getX(), position.getY()).getTile("RestartPoint"))){
           //check if the position of robot is the same on the board - if yes --> reboot == true
            if(position.equals(board.get(position.getX(), position.getY()).getPosition())){
                rebootPassing = true;
            }
        }
        return rebootPassing;
    }


    //PitCheck
    public boolean pitPassingCheck(User user) {
        boolean pitPassing = false;
        Robot robot = user.getRobot();
        Position position = robot.getPosition();

        //check if cell on board contains Pit
        if (board.get(position.getX(), position.getY()).getTiles().get(1).equals(board.get(position.getX(), position.getY()).getTile("Pit"))) {

            //check if position of robot has a specific cell on the board with the same coordinates --> if all true --> Pit == true
            if (position.equals(board.get(position.getX(), position.getY()).getPosition())) {
                pitPassing = true;
            }
        }
        return pitPassing;
    }
}
