package bb.roborally.client.player_inventory;

import bb.roborally.client.player_list.Player;
import javafx.util.converter.NumberStringConverter;

/**
 *
 * @author Bence Ament
 */
public class PlayerInventoryViewModel {

    private final Player localPlayer;
    private PlayerInventoryView view;

    public PlayerInventoryViewModel(Player localPlayer) {
        this.localPlayer = localPlayer;
    }

    public void connect(PlayerInventoryView view) {
        this.view = view;
        observeModelAndUpdate();
        setupListeners();
    }

    private void observeModelAndUpdate() {
        view.getEnergyCubeDisplay().textProperty().bindBidirectional(localPlayer.getPlayerInventory().energyCubeCountProperty(), new NumberStringConverter());
        view.getCheckpointCountDisplay().textProperty().bindBidirectional(localPlayer.getPlayerInventory().checkpointCountProperty(), new NumberStringConverter());
    }

    private void setupListeners() {

    }
}
