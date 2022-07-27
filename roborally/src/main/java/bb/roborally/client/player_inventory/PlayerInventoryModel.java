package bb.roborally.client.player_inventory;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class PlayerInventoryModel {
    private final IntegerProperty energyCubeCount = new SimpleIntegerProperty(8);
    private final IntegerProperty checkpointCount = new SimpleIntegerProperty(0);

    public void setEnergyCubeCount(int count) {
        this.energyCubeCount.set(count);
    }

    public int getEnergyCubeCount() {
        return energyCubeCount.get();
    }

    public IntegerProperty energyCubeCountProperty() {
        return energyCubeCount;
    }

    public void increaseEnergyCubeCount(int increase) {
        energyCubeCount.set(energyCubeCount.get() + increase);
    }
    public void decreaseEnergyCubeCount(int decrease) {
        energyCubeCount.set(energyCubeCount.get() - decrease);
    }

    public void incrementCheckpointCount() {
        this.checkpointCount.set(this.checkpointCount.get() + 1);
    }

    public int getCheckpointCount() {
        return checkpointCount.get();
    }

    public IntegerProperty checkpointCountProperty() {
        return checkpointCount;
    }
}
