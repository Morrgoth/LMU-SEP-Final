package bb.roborally.client.player_list;

import bb.roborally.client.robot_selector.Robot;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Player {
    private final int id;
    private final StringProperty name = new SimpleStringProperty("");
    private Robot robot;
    private final BooleanProperty added = new SimpleBooleanProperty(false);
    private final BooleanProperty ready = new SimpleBooleanProperty(false);
    private final BooleanProperty mapSelector = new SimpleBooleanProperty(false);

    public Player(int id) {
        this.id = id;
    }

    public void add(String name, Robot robot) {
        this.name.set(name);
        this.robot = robot;
        added.set(true);
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name.get();
    }

    public StringProperty nameProperty() {
        return name;
    }

    public Robot getRobot() {
        return robot;
    }


    public boolean isAdded() {
        return added.get();
    }

    public BooleanProperty addedProperty() {
        return added;
    }

    public void setAdded(boolean added) {
        this.added.set(added);
    }

    public boolean isReady() {
        return ready.get();
    }

    public BooleanProperty readyProperty() {
        return ready;
    }

    public void setReady(boolean ready) {
        this.ready.set(ready);
    }

    public boolean isMapSelector() {
        return mapSelector.get();
    }

    public BooleanProperty mapSelectorProperty() {
        return mapSelector;
    }
}
