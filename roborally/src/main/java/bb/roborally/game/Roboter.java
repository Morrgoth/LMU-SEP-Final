package bb.roborally.game;

/**
 * contains attributes and possible actions of a robot
 * @author Veronika Heckel
 * @author Muqiu Wang
 * @author Tolga Engin
 * @author Zeynab Baiani
 * @author Bence Ament
 */
public class Roboter {
    private String name;
    private String color;
    private Position position;
    private Orientation orientation;

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

    public Orientation getOrientation() {
        return orientation;
    }


    public enum Orientation{
        FRONT,
        RIGHT,
        LEFT,
        BACK
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
}


