package bb.roborally.server.game.activation;


import bb.roborally.protocol.game_events.Movement;
import bb.roborally.protocol.game_events.Reboot;
import bb.roborally.server.Server;
import bb.roborally.server.game.*;
import bb.roborally.server.game.board.Board;
import bb.roborally.server.game.cards.PowerUp;


import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;


//all Checks of RoboterMovement implemented here
public class MovementCheck {
    Board board;
    Game game;

    ArrayList<Orientation> orientations = new ArrayList<>();
    ArrayList<Orientation> orientationsReversed = new ArrayList<>();

    ArrayList<Position> neighbors = new ArrayList<>();
    ArrayList<Orientation> orientationsForBlock = new ArrayList<>();

    public MovementCheck(Board board) {
        this.board = board;
    }

    public MovementCheck(Game game) {
        this.game = game;
    }

    public MovementCheck(Board board, Game game) {
        this.board = board;
        this.game = game;
    }

    // Check if the robot is blocked in a given orientation
   /* public boolean checkIfBlocked(User user, Orientation orientation) {
        Robot robot = user.getRobot();
        Position position = robot.getPosition();
        int x = position.getX();
        int y = position.getY();


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
    }*/

    public boolean checkIfBlockedAlt(Position position, Orientation orientation, int step) {
        int x = position.getX();
        int y = position.getY();


        if (orientation == Orientation.TOP) {
            if (board.get(x, y - step).hasTile("Wall") && board.get(x, y).getTile("Wall").getOrientations().get(0) == Orientation.TOP) {
                return true;
            }
            if (board.get(x, y - step - 1).hasTile("Wall") && board.get(x, y - step - 1).getTile("Wall").getOrientations().get(0) == Orientation.BOTTOM) {
                return true;
            }
        } else if (orientation == Orientation.LEFT) {
            if (board.get(x - step, y).hasTile("Wall") && board.get(x - step, y).getTile("Wall").getOrientations().get(0) == Orientation.LEFT) {
                return true;
            }
            if (board.get(x - step - 1, y).hasTile("Wall") && board.get(x - step - 1, y).getTile("Wall").getOrientations().get(0) == Orientation.RIGHT) {
                return true;
            }
        } else if (orientation == Orientation.BOTTOM) {
            if (board.get(x, y + step).hasTile("Wall") && board.get(x, y + step).getTile("Wall").getOrientations().get(0) == Orientation.BOTTOM) {
                return true;
            }
            if (board.get(x, y + step +  1).hasTile("Wall") && board.get(x, y + step + 1).getTile("Wall").getOrientations().get(0) == Orientation.TOP) {
                return true;
            }
        } else if (orientation == Orientation.RIGHT) {
            if (board.get(x + step, y).hasTile("Wall") && board.get(x + step, y).getTile("Wall").getOrientations().get(0) == Orientation.RIGHT) {
                return true;
            }
            if (board.get(x + step +1, y).hasTile("Wall") && board.get(x + step + 1, y).getTile("Wall").getOrientations().get(0) == Orientation.LEFT) {
                return true;
            }
        }

        //The robot is in this orientation not blocked
        return false;
    }

    public void pushRobot(Server server, Game game, User user, Orientation orientation, int step) throws IOException {

        //Liste aller spieler im Spiel
        ArrayList<User> usersInGame = game.getPlayerQueue().getUsers();

        //erster Spieler wird entfernt, da von ihm aus geschoben wird (er bewegt sich ja selbst durch z.B. Move1Handler

        int x = usersInGame.get(0).getRobot().getPosition().getX();
        int y = usersInGame.get(0).getRobot().getPosition().getY();

        storeValuesOrientationPushForward(orientations, usersInGame.get(0).getRobot().getRobotOrientation());

        usersInGame.remove(usersInGame.get(0));
        //Durchlauf durch alle User im Spiel
        for (User user1 : usersInGame) {
            int x1 = user1.getRobot().getPosition().getX();
            int y1 = user1.getRobot().getPosition().getY();

            if (user1.getRobot().getPosition().equals(usersInGame.get(0).getRobot().getPosition())) {
                if (checkIfBlockedAlt(new Position(x, y), orientation, 0)) {
                    user1.getRobot().setPosition(new Position(x1, y1));
                } else {
                    if (orientations.get(0) == Orientation.TOP) {
                        user1.getRobot().setPosition(new Position(x, y - step));
                        server.broadcast(new Movement(user1.getClientID(), x1, y1 - step));
                        if (robotForwardCheck(user1.getRobot().getPosition(), orientations.get(0), 0)) {
                            pushRobot(server, game, user1, orientations.get(0), 1);
                        }
                        if (robotForwardCheck(user1.getRobot().getPosition(), orientations.get(0), -1)) {
                            pushRobot(server, game, user1, orientations.get(0), 1);
                        }
                        if (robotForwardCheck(user1.getRobot().getPosition(), orientations.get(0), -2)) {
                            pushRobot(server, game, user1, orientations.get(0), 1);
                        }
                    } else if (orientations.get(0) == Orientation.LEFT) {
                        user1.getRobot().setPosition(new Position(x - step, y));
                        server.broadcast(new Movement(user1.getClientID(), x1 - step, y1));
                        if (robotForwardCheck(user1.getRobot().getPosition(), orientations.get(0), 0)) {
                            pushRobot(server, game, user1, orientations.get(0), 1);
                        }
                        if (robotForwardCheck(user1.getRobot().getPosition(), orientations.get(0), -1)) {
                            pushRobot(server, game, user1, orientations.get(0), 1);
                        }
                        if (robotForwardCheck(user1.getRobot().getPosition(), orientations.get(0), -2)) {
                            pushRobot(server, game, user1, orientations.get(0), 1);
                        }

                    } else if (orientations.get(0) == Orientation.BOTTOM) {
                        user1.getRobot().setPosition(new Position(x, y + step));
                        server.broadcast(new Movement(user1.getClientID(), x1, y1 + step));
                        if (robotForwardCheck(user1.getRobot().getPosition(), orientations.get(0), 0)) {
                            pushRobot(server, game, user1, orientations.get(0), 1);
                        }
                        if (robotForwardCheck(user1.getRobot().getPosition(), orientations.get(0), -1)) {
                            pushRobot(server, game, user1, orientations.get(0), 1);
                        }
                        if (robotForwardCheck(user1.getRobot().getPosition(), orientations.get(0), -2)) {
                            pushRobot(server, game, user1, orientations.get(0), 1);
                        }

                    } else if (orientations.get(0) == Orientation.RIGHT) {
                        user1.getRobot().setPosition(new Position(x + step, y));
                        server.broadcast(new Movement(user1.getClientID(), x1 + step, y));
                        if (robotForwardCheck(user1.getRobot().getPosition(), orientations.get(0), 0)) {
                            pushRobot(server, game, user1, orientations.get(0), 1);
                        }
                        if (robotForwardCheck(user1.getRobot().getPosition(), orientations.get(0), -1)) {
                            pushRobot(server, game, user1, orientations.get(0), 1);
                        }
                        if (robotForwardCheck(user1.getRobot().getPosition(), orientations.get(0), -2)) {
                            pushRobot(server, game, user1, orientations.get(0), 1);
                        }

                    }
                    if (robotIsOffBoard(user1) || fallingInPit(user1)) {
                        server.broadcast(new Reboot(user1.getClientID()));
                    }
                }
            }
        }
    }


    public void pushRobotBackwards(Server server, Game game, User user, Orientation orientation, int step) throws IOException {

        //Liste aller spieler im Spiel
        ArrayList<User> usersInGame = game.getPlayerQueue().getUsers();

        //erster Spieler wird entfernt, da von ihm aus geschoben wird (er bewegt sich ja selbst durch z.B. Move1Handler

        int x = usersInGame.get(0).getRobot().getPosition().getX();
        int y = usersInGame.get(0).getRobot().getPosition().getY();

        storeValuesOrientationPushBackwards(orientationsReversed, usersInGame.get(0).getRobot().getRobotOrientation());

        usersInGame.remove(usersInGame.get(0));
        //Durchlauf durch alle User im Spiel
        for (User user1 : usersInGame) {
            int x1 = user1.getRobot().getPosition().getX();
            int y1 = user1.getRobot().getPosition().getY();

            if (user1.getRobot().getPosition().equals(usersInGame.get(0).getRobot().getPosition())) {
                if (checkIfBlockedAlt(new Position(x, y), orientation,0)) {
                    user1.getRobot().setPosition(new Position(x1, y1));
                } else {
                    if (orientationsReversed.get(0) == Orientation.TOP) {
                        user1.getRobot().setPosition(new Position(x, y + step));
                        server.broadcast(new Movement(user1.getClientID(), x1, y1 + step));
                        if (robotForwardCheck(user1.getRobot().getPosition(), orientationsReversed.get(0), 0)) {
                            pushRobot(server, game, user1, orientationsReversed.get(0), 1);
                        }

                    } else if (orientationsReversed.get(0) == Orientation.LEFT) {
                        user1.getRobot().setPosition(new Position(x + step, y));
                        server.broadcast(new Movement(user1.getClientID(), x1 + step, y1));
                        if (robotForwardCheck(user1.getRobot().getPosition(), orientationsReversed.get(0), 0)) {
                            pushRobot(server, game, user1, orientationsReversed.get(0), 1);
                        }

                    } else if (orientationsReversed.get(0) == Orientation.BOTTOM) {
                        user1.getRobot().setPosition(new Position(x, y - step));
                        server.broadcast(new Movement(user1.getClientID(), x1, y1 - step));
                        if (robotForwardCheck(user1.getRobot().getPosition(), orientationsReversed.get(0), 0)) {
                            pushRobot(server, game, user1, orientationsReversed.get(0), 1);
                        }
                    } else if (orientationsReversed.get(0) == Orientation.RIGHT) {
                        user1.getRobot().setPosition(new Position(x - step, y));
                        server.broadcast(new Movement(user1.getClientID(), x1 - step, y));
                        if (robotForwardCheck(user1.getRobot().getPosition(), orientationsReversed.get(0), 0)) {
                            pushRobot(server, game, user1, orientationsReversed.get(0), 1);
                        }
                    }
                    if (robotIsOffBoard(user1) || fallingInPit(user1)) {
                        server.broadcast(new Reboot(user1.getClientID()));
                    }
                }
            }
        }
    }


    public void storeValuesOrientationPushForward(ArrayList<Orientation> orientationsReversed, Orientation orientation) {
        orientationsReversed.add(orientation);

    }

    public void storeValuesOrientationPushBackwards(ArrayList<Orientation> orientationsReversed, Orientation orientation) {
        orientationsReversed.add(orientation);
    }


    //is Robot forward Check
    public boolean robotForwardCheck(Position position, Orientation orientation, int step) {

        int x = position.getX();
        int y = position.getY();

        boolean neighbor = false;

        //Durchlauf durch alle User im Spiel
        for (int i = 1; i < game.getPlayerQueue().getUsers().size(); i++) {
            int x1 = game.getPlayerQueue().getUsers().get(1).getRobot().getPosition().getX();
            int y1 = game.getPlayerQueue().getUsers().get(1).getRobot().getPosition().getY();

            if (orientation == Orientation.TOP) {
                if (x1 == x && y1 == y - step) {
                    neighbor = true;
                }
            } else if (orientation == Orientation.LEFT) {
                if (x1 == x - step && y1 == y) {
                    neighbor = true;
                }
            } else if (orientation == Orientation.BOTTOM) {
                if (x1 == x && y1 == y + step) {
                    neighbor = true;
                }
            } else if (orientation == Orientation.RIGHT) {
                if (x1 == x + step && y1 == y) {
                    neighbor = true;
                }
            }
            if(neighbor){
                storeNeighbors(neighbors,game.getPlayerQueue().getUsers().get(i).getRobot().getPosition());  //Speichern jeder Position der Nachbarn in Liste
            }

        }
        return neighbor;
    }

    public boolean checkIfLastTwoAreNeighbors(Position position, Orientation orientation, int step){
        int x = game.getPlayerQueue().getUsers().get(game.getPlayerQueue().getUsers().size()-2).getRobot().getPosition().getX();
        int y = game.getPlayerQueue().getUsers().get(game.getPlayerQueue().getUsers().size()-2).getRobot().getPosition().getY();

        int x1 = game.getPlayerQueue().getUsers().get(game.getPlayerQueue().getUsers().size()-1).getRobot().getPosition().getX();
        int y1 = game.getPlayerQueue().getUsers().get(game.getPlayerQueue().getUsers().size()-1).getRobot().getPosition().getY();

        boolean neighbor = false;
        if (orientation == Orientation.TOP) {
            if (x1 == x && y1 == y - step) {
                neighbor = true;
            }
        } else if (orientation == Orientation.LEFT) {
            if (x1 == x - step && y1 == y) {
                neighbor = true;
            }
        } else if (orientation == Orientation.BOTTOM) {
            if (x1 == x && y1 == y + step) {
                neighbor = true;
            }
        } else if (orientation == Orientation.RIGHT) {
            if (x1 == x + step && y1 == y) {
                neighbor = true;
            }
            storeNeighbors(neighbors,game.getPlayerQueue().getUsers().get().getRobot().getPosition()); //store last neighbor
        }
        if(neighbor){

        }
        return neighbor;
    }


    //Check über die Nachbarsliste, ob letzter Nachbar geblockt ist
    public boolean checkPushWithBlock(Position position, Orientation orientation, int step) {
        return checkIfBlockedAlt(neighbor.get(neighbor.size()-1), orientation,step);
    }

    //Hilfsmethode zum Speichern der Nachbarn - für Check, ob der letzte Nachbar eine Wand auf seiner Position mit Orientation der sich bewegenden Figur hat
    public void storeNeighbors(ArrayList<Position> neighbor, Position position){
        neighbor.add(position);
    }

    public void storeOrientationsForBlock(ArrayList <Orientation> orientationsForBlock, Orientation orientation){
        orientationsForBlock.add(orientation);
    }
    /*public boolean robotForwardCheckForTwoSteps(Position position, Orientation orientation){

        int x = position.getX();
        int y = position.getY();

        //Durchlauf durch alle User im Spiel
        for (int i = 1; i < game.getPlayerQueue().getUsers().size();i++) {
            int x1 = game.getPlayerQueue().getUsers().get(1).getRobot().getPosition().getX();
            int y1 = game.getPlayerQueue().getUsers().get(1).getRobot().getPosition().getY();

            if (orientation == Orientation.TOP) {
                if (x1 == x && y1 == y + 1) {
                    return true;
                }
            } else if (orientation == Orientation.LEFT) {
                if (x1 == x + 1 && y1 == y) {
                    return true;
                }
            } else if (orientation == Orientation.BOTTOM) {
                if (x1 == x && y1 == y - 1) {
                    return true;
                }
            } else if (orientation == Orientation.RIGHT) {
                if (x1 == x -1 && y1 == y) {
                    return true;
                }
            }
        }
        return false;
    }*/

    /*public boolean robotForwardCheckForThreeSteps(Position position, Orientation orientation){
        int x = position.getX();
        int y = position.getY();

        //Durchlauf durch alle User im Spiel
        for (int i = 1; i < game.getPlayerQueue().getUsers().size();i++) {
            int x1 = game.getPlayerQueue().getUsers().get(1).getRobot().getPosition().getX();
            int y1 = game.getPlayerQueue().getUsers().get(1).getRobot().getPosition().getY();

            if (orientation == Orientation.TOP) {
                if (x1 == x && y1 == y + 2) {
                    return true;
                }
            } else if (orientation == Orientation.LEFT) {
                if (x1 == x + 2 && y1 == y) {
                    return true;
                }
            } else if (orientation == Orientation.BOTTOM) {
                if (x1 == x && y1 == y - 2) {
                    return true;
                }
            } else if (orientation == Orientation.RIGHT) {
                if (x1 == x - 2 && y1 == y) {
                    return true;
                }
            }
        }
        return false;

    }*/




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
            //RebootHandler.getInstance().addUser(user);
            //check if position of robot has a specific cell on the board with the same coordinates --> if all true --> Pit == true
            return true;
        }

        return false;
    }

    public boolean robotIsOffBoard(User user){

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
