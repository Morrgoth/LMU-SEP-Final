package bb.roborally.client.map_selector;


import bb.roborally.client.robot_selector.Orientation;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;

public class Map {
    private String name;
    private final BooleanProperty select = new SimpleBooleanProperty(false);
    private Orientation startOrientation;

    public Map(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public BooleanProperty selectProperty() {
        return select;
    }


}