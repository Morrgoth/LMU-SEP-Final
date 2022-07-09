package bb.roborally.server.game.activation;


import bb.roborally.server.game.Orientation;
import bb.roborally.server.game.Position;
import bb.roborally.server.game.Robot;
import bb.roborally.server.game.User;
import bb.roborally.server.game.board.Board;


//all Checks of Robotermovement implemented here
public class MovementCheck {
    Board board;



    //is Wall forward Check
    public boolean wallForwardCheck(User user){
        boolean wallForward = false;
        Robot robot = user.getRobot();
        Position position = robot.getPosition();
        if(robot.getRobotOrientation().equals(Orientation.TOP) &&
           board.get(position.getX(), position.getY()).getTile("Wall").getOrientations().get(0).equals(Orientation.TOP)){
           wallForward = true;

        }

        if(robot.getRobotOrientation().equals(Orientation.BOTTOM) &
           board.get(position.getX(), position.getY()).getTile("Wall").getOrientations().get(0).equals(Orientation.BOTTOM)){
           wallForward = true;
        }

        if(robot.getRobotOrientation().equals(Orientation.RIGHT) &&
           board.get(position.getX(), position.getY()).getTile("Wall").getOrientations().get(0).equals(Orientation.RIGHT)){
           wallForward = true;
        }

        if(robot.getRobotOrientation().equals(Orientation.LEFT) &&
           board.get(position.getX(), position.getY()).getTile("Wall").getOrientations().get(0).equals(Orientation.LEFT)){
           wallForward = true;
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
        if(robot1.getRobotOrientation().equals(Orientation.TOP) &&
           position1.getY() - 1 == position2.getY() &&
           position1.getX() == position1.getX()){
           robotForward = true;

        }
        if(robot1.getRobotOrientation().equals(Orientation.BOTTOM) &&
           position1.getY() + 1 == position2.getY() &&
           position1.getX() == position1.getX()){
           robotForward = true;

        }
        if(robot1.getRobotOrientation().equals(Orientation.LEFT) &&
           position1.getY() == position2.getY() &&
           position1.getX() - 1 == position2.getX()){
           robotForward = true;

        }
        if(robot1.getRobotOrientation().equals(Orientation.TOP) &&
           position1.getY() == position2.getY() &&
           position1.getX() + 1 == position2.getX()){
           robotForward = true;
        }

        return robotForward;

    }

    //RebootPointCheck
    public boolean rebootPassingCheck(User user){
        boolean rebootPassing = false;
        Robot robot = user.getRobot();
        Position position = robot.getPosition();

        if(board.get(position.getX(), position.getY()).getTiles().get(1).getType().equals("RestartPoint")){
            rebootPassing = true;

        }
        return rebootPassing;
    }


    //PitCheck
    public boolean pitPassingCheck(User user){
        boolean pitPassing = false;
        Robot robot = user.getRobot();
        Position position = robot.getPosition();
        if(board.get(position.getX(), position.getY()).getTiles().get(1).getType().equals("Pit")){
            pitPassing = true;

        }
    return pitPassing;
    }
}
