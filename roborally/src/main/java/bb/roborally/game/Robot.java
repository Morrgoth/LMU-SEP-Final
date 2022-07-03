package bb.roborally.game;

/**
 * contains attributes and possible actions of a robot
 * @author Veronika Heckel
 * @author Muqiu Wang
 * @author Tolga Engin
 * @author Zeynab Baiani
 * @author Bence Ament
 * @autor  Philipp Keyzman
 */
public class Robot {
    private int clientID;
    private boolean available = true;
    private int figureId;
    private String name;
    private String color;
    private Position position;
    private Position previousPosition;
    private Orientation robotOrientation;

    private int energyCubeAmount;

    private int checkPointTokens;

    public Robot(int figureId, String name) {
        this.figureId = figureId;
        this.name = name;
    }

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

    public int getFigureId() {
        return figureId;
    }

    public void setFigureId(int figureId) {
        this.figureId = figureId;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
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

    public Position getPreviousPosition() {
        return previousPosition;
    }

    public void setPreviousPosition(Position position) {
        this.previousPosition = position;
    }

    @Override
    public String toString() {
        return getFigureId() + ": " + getName();
    }
}


