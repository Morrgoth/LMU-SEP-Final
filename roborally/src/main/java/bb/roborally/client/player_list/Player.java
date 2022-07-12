package bb.roborally.client.player_list;

import bb.roborally.client.player_inventory.PlayerInventoryModel;
import bb.roborally.client.robot_selector.Robot;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.BooleanBinding;
import javafx.beans.property.*;

public class Player {
    public static final int NO_ID = -99999999;
    private final IntegerProperty id = new SimpleIntegerProperty(NO_ID);
    private final StringProperty name = new SimpleStringProperty("");
    private Robot robot;
    private final BooleanBinding idSet = Bindings.notEqual(NO_ID, id);
    private final BooleanProperty added = new SimpleBooleanProperty(false);
    private final BooleanProperty ready = new SimpleBooleanProperty(false);
    private final BooleanProperty mapSelector = new SimpleBooleanProperty(false);
    private final PlayerInventoryModel playerInventory = new PlayerInventoryModel();

    public Player() {

    }

    public Player(int id) {
        this.id.set(id);
    }

    public void add(String name, Robot robot) {
        this.name.set(name);
        this.robot = robot;
        added.set(true);
    }

    public int getId() {
        return id.get();
    }

    public void setId(int id) {
        this.id.set(id);
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

    public boolean isIdSet() {
        return idSet.get();
    }

    public BooleanBinding idSetProperty() {
        return idSet;
    }

    public PlayerInventoryModel getPlayerInventory() {
        return playerInventory;
    }

    @Override
    public String toString() {
        if (name.get().isEmpty()) {
            return "Player(" + id.get() + ")";
        } else {
            return name.get() + "(" + id.get() + ")";
        }
    }
}
