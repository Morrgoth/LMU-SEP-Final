package bb.roborally.client.map_selector;


import bb.roborally.client.robot_selector.Orientation;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;

/**
 * @author Muqiu Wang
 */
public class Map {
    private String name;
    private final BooleanProperty select = new SimpleBooleanProperty(false);
    private final Orientation startOrientation;

    public Map(String name, Orientation startOrientation) {
        this.name = name;
        this.startOrientation = startOrientation;
    }

    public String getName() {
        return name;
    }

    public BooleanProperty selectProperty() {
        return select;
    }


}