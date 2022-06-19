package bb.roborally.game;

/**
 * contains attributes and possible actions of a robot
 * @author Veronika Heckel
 * @author Muqiu Wang
 * @author Tolga Engin
 * @author Zeynab Baiani
 * @author Bence Ament
 * @author  Philipp Keyzman
 */
public class Robot {
    private int clientID = Player.getClientID();
    private String name;
    private String color;
    private Position position;
    private Orientation robotOrientation;
    private boolean isBlocked;

    private int energyCubeAmount;

    private int checkPointTokens;
    private int lasered;

    public int getClientID() {
        return clientID;
    }

    public void setClientID(int clientID) {
        this.clientID = clientID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public Orientation getRobotOrientation() {
        return robotOrientation;
    }

    public void setRobotOrientation(Orientation robotOrientation) {
        this.robotOrientation = robotOrientation;
    }

    public int distanceToAntenna(){
        return 0;
    }

    public void turnLeft(){

    }

    public void turnRight(){

    }

    public void turnRound(){

    }

    public void useLaser(){

    }

    public int getEnergyCubeAmount() {
        return energyCubeAmount;
    }

    public void increaseEnergyCubeAmount() {
        this.energyCubeAmount += 1;
    }

    public void decreaseEnergyCubeAmount() { this.energyCubeAmount -= 1; }

    public int getCheckPointTokens() {
        return checkPointTokens;
    }

    public void gainCheckPointTokens() {
        this.checkPointTokens += 1;
    }


    public int getLasered() {
        return lasered;
    }

    public void setLasered() {
        this.lasered += 1;
    }

    public boolean isBlocked() {
        return isBlocked;
    }

    public void setBlocked(boolean blocked) {
        isBlocked = blocked;
    }
}


