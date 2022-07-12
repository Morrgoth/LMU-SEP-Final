package bb.roborally.server.game.activation;


import bb.roborally.protocol.game_events.Movement;
import bb.roborally.protocol.game_events.Reboot;
import bb.roborally.server.Server;
import bb.roborally.server.game.*;
import bb.roborally.server.game.board.Board;
import bb.roborally.server.game.board.Cell;
import bb.roborally.server.game.tiles.Tile;
import javafx.geometry.Pos;


import java.io.IOException;
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

    // Check if the robot is blocked in a given orientation
    public boolean checkIfBlocked(User user, Orientation orientation) {
        Robot robot = user.getRobot();
        Position position = robot.getPosition();
        int x = position.getX();
        int y = position.getY();

        //Check if wall with the same orientation on the same tile, when yes, return true
        if (board.get(x, y).hasTile("Wall")) {
            Tile wall = board.get(x, y).getTile("Wall");
            if (wall.getOrientations().get(0) == robot.getRobotOrientation()) {
                return true;
            }
        }

        switch (orientation) {

            case TOP:
                if(board.get(x,y).hasTile("Wall") && board.get(x, y).getTile("Wall").getOrientations().get(0) == Orientation.TOP ||
                        board.get(x,y-1).hasTile("Wall") && board.get(x,y-1).getTile("Wall").getOrientations().get(0) == Orientation.BOTTOM) {
                    return true;
                }
            case LEFT:
                if (board.get(x, y).hasTile("Wall") && board.get(x, y).getTile("Wall").getOrientations().get(0) == Orientation.LEFT ||
                        board.get(x-1, y).hasTile("Wall") && board.get(x-1,y).getTile("Wall").getOrientations().get(0) == Orientation.RIGHT) {
                    return true;
                }
            case BOTTOM:
                if (board.get(x, y).hasTile("Wall") && board.get(x, y).getTile("Wall").getOrientations().get(0) == Orientation.BOTTOM ||
                        board.get(x, y+1).hasTile("Wall") && board.get(x,y+1).getTile("Wall").getOrientations().get(0) == Orientation.TOP) {
                    return true;
                }
            case RIGHT:
                if (board.get(x, y).hasTile("Wall") && board.get(x, y).getTile("Wall").getOrientations().get(0) == Orientation.RIGHT ||
                        board.get(x+1, y).hasTile("Wall") && board.get(x+1,y+1).getTile("Wall").getOrientations().get(0) == Orientation.LEFT) {
                    return true;
                }
        }

        //The robot is in this orientation not blocked
        return false;
    }

    public void pushRobot(Server server, Game game, User user, Orientation orientation, int step) throws IOException{


        //Liste aller spieler im Spiel
        ArrayList<User> usersInGame = game.getPlayerQueue().getUsers();
        //erster Spieler wird entfernt, da von ihm aus geschoben wird (er bewegt sich ja selbst durch z.B. Move1Handler
        usersInGame.remove(user);

        int x = user.getRobot().getPosition().getX();
        int y = user.getRobot().getPosition().getY();
        //Durchlauf durch alle User im Spiel
        for (User user1 : usersInGame) {
            int x1 = user1.getRobot().getPosition().getX();
            int y1 = user1.getRobot().getPosition().getY();

            if(user1.getRobot().getPosition().equals(user.getRobot().getPosition())){
                for(int i = 0; i < step; i++){
                    if(checkIfBlocked(user1, orientation)){
                        user1.getRobot().setPosition(new Position(x1, y1));
                    }else{
                        switch (orientation){
                            case TOP:
                                user1.getRobot().setPosition(new Position(x, y - i));
                                server.broadcast(new Movement(user1.getClientID(), x, y-i));
                            case LEFT:
                                user1.getRobot().setPosition(new Position(x - i, y));
                                server.broadcast(new Movement(user1.getClientID(), x - i, y));
                            case RIGHT:
                                user1.getRobot().setPosition(new Position(x + i, y));
                                server.broadcast(new Movement(user1.getClientID(), x + i, y));
                            case BOTTOM:
                                user1.getRobot().setPosition(new Position(x, y + i));
                                server.broadcast(new Movement(user1.getClientID(), x, y + i));
                        }
                        if(robotIsOffBoard(user1) || fallingInPit(user1)){
                            server.broadcast(new Reboot(user1.getClientID()));
                        }
                    }
                }
            }
        }
    }


    //is Robot forward Check
    public boolean robotForwardCheck(Game game, User user){
        Position position = user.getRobot().getPosition();
        int x = position.getX();
        int y = position.getY();
        for(Position position1: game.getUsersPositions()){
            int x1 = position1.getX();
            int y1 = position1.getY();
            switch (user.getRobot().getRobotOrientation()){
                case TOP:
                    if(x1 == x && y1 == y-1){
                        return true;
                    }
                case LEFT:
                    if(x1 == x-1 && y1 == y){
                        return true;
                    }
                case BOTTOM:
                    if(x1 == x && y1 == y+1){
                        return  true;
                    }
                case RIGHT:
                    if(x1 == x+1 && y1 == y){
                        return true;
                    }
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
