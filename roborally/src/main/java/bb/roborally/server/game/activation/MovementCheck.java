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
    int numberOfPositions;


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




    public boolean robotForwardCheck(Game game, Robot robot, Orientation orientation){
        Position position = robot.getPosition();
        int x = position.getX();
        int y = position.getY();
        //for-Schleife über Positionen die vor gegangen werden soll
        for(int i = 0; i < getNumberOfPositions(); i++){
            //Schleife über die Positionen aller spieler im Spiel
            for(Position position1: game.getUsersPositions()){
                int x1 = position1.getX();
                int y1 = position1.getY();
                //Abfrage der Orientierung in welcher Richtung nach vorhandenen Robotern gesucht wird
                switch (orientation){
                    case TOP:
                        //wenn Position x des Roboters mit einem Anderen Roboter übereinstimmt und sich in y - Richtung je nach  Orientierung im Bereich y und der Anzahl der schritte in y Richtung ein Roboter befindet, dann True
                        if(x1 == x && y1 == y - getNumberOfPositions()){
                            return true;
                        }
                    case LEFT:
                        if(x1 == x - getNumberOfPositions() && y1 == y){
                            return true;
                        }
                    case BOTTOM:
                        if(x1 == x && y1 == y + getNumberOfPositions()){
                            return  true;
                        }
                    case RIGHT:
                        if(x1 == x + getNumberOfPositions() && y1 == y){
                            return true;
                        }
                }
            }
        }

        return false;
    }

    //is Robot forward Check
    public Position pushRobotForward(Game game, User user, Orientation orientation) {


        //Liste aller spieler im Spiel
        ArrayList<User> usersInGame = new ArrayList<>(game.getPlayerQueue().getUsers());
        //erster Spieler wird entfernt, da von ihm aus geschoben wird (er bewegt sich ja selbst durch z.B. Move1Handler
        usersInGame.remove(user);

        int x = user.getRobot().getPosition().getX();
        int y = user.getRobot().getPosition().getY();
        //Durchlauf durch alle User im Spiel
        Position newPosition = null;
        for (User user1 : usersInGame) {
            newPosition = user1.getRobot().getPosition();
            int x1 = user1.getRobot().getPosition().getX();
            int y1 = user1.getRobot().getPosition().getY();

            //wenn sich die Positionen von Roboter 1 und 2 x und y je nach Richtung um 1 unterscheiden würden
            //wir müssen hier denk ich immer ein Feld im Voraus fragen, da wir davor auch den RobotForwardCheck machen und da auch einen Schritt immer im Voraus fragen
            //deshalb erst die Orientierung fragen und dann die Koordinaten dazu
            if (user.getRobot().getRobotOrientation().equals(Orientation.TOP)) {
                if (y - 1 == y1 && x1 == x) {
                    //Check, ob sich ein Roboter vor einem anderen befindet, evtl bei mehreren Robotern hintereinander?? ANsonsten kann das vielleicht wegfallen, wenn wir das in Move1 schon checken??
                    if (!robotForwardCheck(game, user.getRobot(), orientation)) {
                        user1.getRobot().setPosition(new Position(x, y - 1));
                    }
                }
            }

            if (user.getRobot().getRobotOrientation().equals(Orientation.BOTTOM)) {
                if (y + 1 == y1 && x1 == x) {
                    if (!robotForwardCheck(game, user.getRobot(), orientation)) {
                        user1.getRobot().setPosition(new Position(x, y + 1));
                    }
                }
            }
            if (user.getRobot().getRobotOrientation().equals(Orientation.LEFT)) {
                if (y == y1 && x1 - 1 == x) {
                    if (!robotForwardCheck(game, user.getRobot(), orientation)) {
                        user1.getRobot().setPosition(new Position(x - 1, y));
                    }
                }
            }
            if (user.getRobot().getRobotOrientation().equals(Orientation.RIGHT)) {
                if (y == y1 && x1 - 1 == x) {
                    if (!robotForwardCheck(game, user.getRobot(), orientation)) {
                        user1.getRobot().setPosition(new Position(x + 1, y));
                    }
                }
            }
        }
        return newPosition;
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

    public int getNumberOfPositions(){
        return numberOfPositions;
    }

    public void setNumberOfPositions(int numberOpPositions){
        this.numberOfPositions = numberOpPositions;
    }
}
