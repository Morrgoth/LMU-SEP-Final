package bb.roborally.server.game;

import bb.roborally.protocol.Orientation;
import bb.roborally.protocol.Position;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

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
    private boolean available = true;
    private int figureId;
    private String name;
    private Position position;
    private Orientation robotOrientation = Orientation.RIGHT;


    public Robot(int figureId, String name) {
        this.figureId = figureId;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public void turnClockwise90Deg(){
        if(robotOrientation == Orientation.LEFT){
            setRobotOrientation(Orientation.TOP);
        }else if(robotOrientation == Orientation.TOP){
            setRobotOrientation(Orientation.RIGHT);
        }else if(robotOrientation == Orientation.RIGHT){
            setRobotOrientation(Orientation.BOTTOM);
        }else if(robotOrientation == Orientation.BOTTOM){
            setRobotOrientation(Orientation.LEFT);
        }
    }

    public void turnCounterclockwise90Deg(){
        if(robotOrientation == Orientation.LEFT){
            setRobotOrientation(Orientation.BOTTOM);
        }else if(robotOrientation == Orientation.BOTTOM){
            setRobotOrientation(Orientation.RIGHT);
        }else if(robotOrientation == Orientation.RIGHT){
            setRobotOrientation(Orientation.TOP);
        }else if(robotOrientation == Orientation.TOP){
            setRobotOrientation(Orientation.LEFT);
        }
    }


    public ImageView getRobotElement() {
        Image image = new Image(getClass().getResource("/robots/demo_robot.png").toExternalForm());
        ImageView imageView = new ImageView(image);
        imageView.setFitHeight(40);
        imageView.setFitWidth(40);
        return imageView;
    }

    @Override
    public String toString() {
        return getFigureId() + ": " + getName();
    }

}
