package bb.roborally.server.game.activation;
import bb.roborally.protocol.Orientation;
import bb.roborally.protocol.Position;
import bb.roborally.server.game.*;
import bb.roborally.server.game.board.ServerBoard;
import java.util.ArrayList;
import java.util.Random;


/**
 * Class contains all exported methods of the needed checks for managing the Movement of the Robots
 * @author Zeynab Baiani
 * @author Muqiu Wang
 * @author Veronika Heckel
 */

public class MovementCheck {
    ServerBoard serverBoard;
    Game game;

    ArrayList<Orientation> orientations = new ArrayList<>();
    ArrayList<Orientation> orientationsReversed = new ArrayList<>();

    ArrayList<User> neighbors = new ArrayList<>();
    ArrayList<Orientation> orientationsForBlock = new ArrayList<>();

    public MovementCheck(ServerBoard serverBoard) {
        this.serverBoard = serverBoard;
    }

    public MovementCheck(Game game) {
        this.game = game;
    }

    public MovementCheck(ServerBoard serverBoard, Game game) {
        this.serverBoard = serverBoard;
        this.game = game;
    }


    /**
     * checks if a robot is blocked by a wall
     * @param position --> position of the Robot which is the reference Point for the check
     * @param orientation --> Orintation of the referenced Robot
     * @param step --> indicating the steps of checking, makes the method variable for each situation
     * @return boolean
     * @throws IndexOutOfBoundsException
     */
    public boolean checkIfBlockedAlt(Position position, Orientation orientation, int step) throws IndexOutOfBoundsException {
        int x = position.getX();
        int y = position.getY();

    try{
        if (orientation == Orientation.TOP) {
            if (serverBoard.get(x, y - step).hasTile("Wall") && serverBoard.get(x, y - step).getTile("Wall").getOrientations().get(0) == Orientation.TOP  || serverBoard.get(x, y - step).hasTile("Antenna")) {
                return true;
            }
            if (serverBoard.get(x, y - step - 1).hasTile("Wall") && serverBoard.get(x, y - step - 1).getTile("Wall").getOrientations().get(0) == Orientation.BOTTOM || serverBoard.get(x, y - step - 1).hasTile("Antenna")) {
                return true;
            }
        } else if (orientation == Orientation.LEFT) {
            if (serverBoard.get(x - step, y).hasTile("Wall") && serverBoard.get(x - step, y).getTile("Wall").getOrientations().get(0) == Orientation.LEFT || serverBoard.get(x - step, y).hasTile("Antenna")) {
                return true;
            }
            if (serverBoard.get(x - step - 1, y).hasTile("Wall") && serverBoard.get(x - step - 1, y).getTile("Wall").getOrientations().get(0) == Orientation.RIGHT || serverBoard.get(x - step - 1, y).hasTile("Antenna")) {
                return true;
            }
        } else if (orientation == Orientation.BOTTOM) {
            if (serverBoard.get(x, y + step).hasTile("Wall") && serverBoard.get(x, y + step).getTile("Wall").getOrientations().get(0) == Orientation.BOTTOM || serverBoard.get(x, y + step).hasTile("Antenna")) {
                return true;
            }
            if (serverBoard.get(x, y + step +  1).hasTile("Wall") && serverBoard.get(x, y + step + 1).getTile("Wall").getOrientations().get(0) == Orientation.TOP || serverBoard.get(x, y + step +  1).hasTile("Antenna")) {
                return true;
            }
        } else if (orientation == Orientation.RIGHT) {
            if (serverBoard.get(x + step, y).hasTile("Wall") && serverBoard.get(x + step, y).getTile("Wall").getOrientations().get(0) == Orientation.RIGHT || serverBoard.get(x + step, y).hasTile("Antenna")) {
                return true;
            }
            if (serverBoard.get(x + step + 1, y).hasTile("Wall") && serverBoard.get(x + step + 1, y).getTile("Wall").getOrientations().get(0) == Orientation.LEFT) {
                return true;
                }
            }
        } catch (IndexOutOfBoundsException e) {
        return false;
    }
        //The robot is in this orientation not blocked
        return false;
    }


    /**
     * @param user1
     * @param user2
     * @param orientation
     * @param step
     * @return
     * @throws IndexOutOfBoundsException
     */
    //is Robot forward Check
    public boolean robotForwardCheck(User user1, User user2,  Orientation orientation, int step) throws IndexOutOfBoundsException {

        int x = user1.getRobot().getPosition().getX();
        int y = user1.getRobot().getPosition().getY();

        int x1 = user2.getRobot().getPosition().getX();
        int y1 = user2.getRobot().getPosition().getY();


        boolean neighbor = false;

            try{
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
                if(neighbor && (!neighbors.contains(user1))){
                    storeNeighbors(neighbors,user1);  //Speichern jeder Position der Nachbarn in Liste
                }

            } catch (IndexOutOfBoundsException e) {
                neighbor = false;
            }
        return neighbor;
    }



    /**
     * @param user1 --> first Robot
     * @param user2 --> second Robot
     * @param orientation --> orientation of Robot that is the reference Point (Robot that is actively moving
     * @param step --> variable for making method reusable
     * @return boolean
     * @throws IndexOutOfBoundsException
     */
    public boolean checkIfLastTwoAreNeighbors(User user1, User user2, Orientation orientation, int step)  throws IndexOutOfBoundsException{
        int x = user1.getRobot().getPosition().getX();
        int y = user1.getRobot().getPosition().getY();

        int x1 = user2.getRobot().getPosition().getX();
        int y1 = user2.getRobot().getPosition().getY();

        boolean neighbor = false;

        try{
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
                storeNeighbors(neighbors,user2); //store last neighbor
            }

        } catch (IndexOutOfBoundsException e) {
            neighbor = false;
        }
        return neighbor;
    }



    //RebootPointCheck - randomized Orientation
    public Orientation rebootPointStartOrientation(User user){
        int pick = new Random().nextInt(Orientation.values().length);
        Robot robot = user.getRobot();
        Position position = robot.getPosition();
        if(serverBoard.get(position.getX(), position.getY()).getTiles().get(1).equals(serverBoard.get(position.getX(), position.getY()).getTile("RestartPoint"))){
            position.setX(serverBoard.get(position.getX(),position.getY()).getPosition().getX());
            position.setY(serverBoard.get(position.getY(),position.getY()).getPosition().getY());
            robot.setRobotOrientation(Orientation.values()[pick]);
        }
        return null;
    }


    /**
     * @param user --> Robot for checking
     * @return boolean
     */
    //PitCheck
    public boolean fallingInPit(User user, int stepsX, int stepsY) {
        Robot robot = user.getRobot();
        Position position = robot.getPosition();

        //check if cell on serverBoard contains Pit
            if (serverBoard.get(position.getX() + stepsX, position.getY() + stepsY).getTile("Pit") != null) {
                //RebootHandler.getInstance().addUser(user);
                //check if position of robot has a specific cell on the serverBoard with the same coordinates --> if all true --> Pit == true
                return true;
            }
        return false;
    }

    /**
     * @param user --> Robot for checking
     * @return boolean
     */
   public boolean robotIsOffServerBoard(User user){

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


    /**
     * @param neighbors --> list that contains all neighbors in one movement
     * @param user
     */
    //Hilfsmethode zum Speichern der Nachbarn - für Check, ob der letzte Nachbar eine Wand auf seiner Position mit Orientation der sich bewegenden Figur hat
    public void storeNeighbors(ArrayList<User> neighbors, User user){
        neighbors.add(user);
    }


    public ArrayList<User> getNeighbors(){
        return neighbors;
    }

}



