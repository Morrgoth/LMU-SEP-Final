package bb.roborally.client.map_selector;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;

public class Map {
    private String name;
    private final BooleanProperty select = new SimpleBooleanProperty(false);

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