package bb.roborally.server.game.activation;


import bb.roborally.server.game.*;
import bb.roborally.server.game.board.Board;
import bb.roborally.server.game.board.Cell;
import bb.roborally.server.game.tiles.Tile;
import javafx.geometry.Pos;


import java.util.ArrayList;
import java.util.Random;


//all Checks of RoboterMovement implemented here
public class MovementCheck {
    Board board;
    Game game;

    public MovementCheck(Board board) {
        this.board = board;
    }
    public MovementCheck(Game game){
        this.game = game;
    }

    public MovementCheck(Board board, Game game) {
        this.board = board;
        this.game = game;
    }

    //1st Method WallCheck - checks the same Field of Robot and Wall
    public boolean wallForwardCheck(User user) {
        Robot robot = user.getRobot();
        Position position = robot.getPosition();
        int x = position.getX();
        int y = position.getY();

        //Check if wall with the same orientation on the same tile, when yes, return true
        if(board.get(x, y).hasTile("Wall")){
            Tile wall = board.get(x, y).getTile("Wall");
            if(wall.getOrientations().get(0) == robot.getRobotOrientation()){
                return true;
            }
        }
        //Check if the next space has a wall, when yes, return true
        switch (robot.getRobotOrientation()){
            case TOP:
                Tile wall = board.get(x, y-1).getTile("Wall");
                if(board.get(x, y-1).hasTile("Wall") && wall.getOrientations().get(0) == Orientation.BOTTOM){
                    return true;
                }
            case LEFT:
                Tile wall1 = board.get(x-1, y).getTile("Wall");
                if(board.get(x-1, y).hasTile("Wall") && wall1.getOrientations().get(0) == Orientation.RIGHT) {
                    return true;
                }
            case BOTTOM:
                Tile wall2 = board.get(x, y+1).getTile("Wall");
                if(board.get(x, y+1).hasTile("Wall") && wall2.getOrientations().get(0) == Orientation.TOP) {
                    return true;
                }
            case RIGHT:
                Tile wall3 = board.get(x+1, y).getTile("Wall");
                if(board.get(x+1, y).hasTile("Wall") && wall3.getOrientations().get(0) == Orientation.LEFT) {
                    return true;
                }
        }

        //The robot is not blocked
        return false;
    }
    
    public boolean wallBehindTest(User user){
        Robot robot = user.getRobot();
        Position position = robot.getPosition();
        int x = position.getX();
        int y = position.getY();

        //Check if wall with the opposite orientation on the same tile, when yes, return true
        if(board.get(x, y).hasTile("Wall")){
            Tile wall = board.get(x, y).getTile("Wall");
            switch (wall.getOrientations().get(0)){
                case TOP:
                    if(robot.getRobotOrientation() == Orientation.BOTTOM){
                        return true;
                    }
                case LEFT:
                    if(robot.getRobotOrientation() == Orientation.RIGHT){
                        return true;
                    }
                case BOTTOM:
                    if(robot.getRobotOrientation() == Orientation.TOP){
                        return true;
                    }
                case RIGHT:
                    if(robot.getRobotOrientation() == Orientation.LEFT){
                        return true;
                    }
            }
        }
        //Check if the next space has a wall, when yes, return true
        switch (robot.getRobotOrientation()){
            case TOP:
                Tile wall = board.get(x, y+1).getTile("Wall");
                if(board.get(x, y-1).hasTile("Wall") && wall.getOrientations().get(0) == Orientation.TOP){
                    return true;
                }
            case LEFT:
                Tile wall1 = board.get(x+1, y).getTile("Wall");
                if(board.get(x-1, y).hasTile("Wall") && wall1.getOrientations().get(0) == Orientation.LEFT) {
                    return true;
                }
            case BOTTOM:
                Tile wall2 = board.get(x, y-1).getTile("Wall");
                if(board.get(x, y+1).hasTile("Wall") && wall2.getOrientations().get(0) == Orientation.BOTTOM) {
                    return true;
                }
            case RIGHT:
                Tile wall3 = board.get(x-1, y).getTile("Wall");
                if(board.get(x+1, y).hasTile("Wall") && wall3.getOrientations().get(0) == Orientation.RIGHT) {
                    return true;
                }
        }

        //The robot is not blocked
        return false;
    }






    //is Robot forward Check
    public boolean pushRobotForwardCheck(Game game, User user, Orientation orientation) {
        ArrayList<User> usersInGame = new ArrayList<>(game.getPlayerQueue().getUsers());
        usersInGame.remove(user);
        for(User user1: usersInGame){
            if(user1.getRobot().getPosition() == user.getRobot().getPosition()){
                if(user.getRobot().getRobotOrientation().equals(Orientation.TOP))
            }
        }
        return false;
    }


    //Robot behind check
    public boolean robotBehindCheck(Game game, User user){
        Position position = user.getRobot().getPosition();
        int x = position.getX();
        int y = position.getY();
        for(Position position1: game.getUsersPositions()){
            int x1 = position1.getX();
            int y1 = position1.getY();
            switch (user.getRobot().getRobotOrientation()){
                case TOP:
                    if(x1 == x && y1 == y+1){
                        return true;
                    }
                case LEFT:
                    if(x1 == x+1 && y1 == y){
                        return true;
                    }
                case BOTTOM:
                    if(x1 == x && y1 == y-1){
                        return  true;
                    }
                case RIGHT:
                    if(x1 == x-1 && y1 == y){
                        return true;
                    }
            }
        }
        return false;
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
        Robot robot = user.getRobot();
        Position position = robot.getPosition();

        //check if cell on board contains Pit
        if (board.get(position.getX(), position.getY()).getTile("Pit") != null) {
            //check if position of robot has a specific cell on the board with the same coordinates --> if all true --> Pit == true
            return true;
        }

        return false;
    }

    public boolean robotIsOffBoard(User user) {
        Robot robot = user.getRobot();
        Position position = robot.getPosition();
        int x = position.getX();
        int y = position.getY();
        if (x < 0 || y < 0) {
            return true;
        }
        if (x > 12 || y > 9) {
            return true;
        }
        return false;
    }
}
