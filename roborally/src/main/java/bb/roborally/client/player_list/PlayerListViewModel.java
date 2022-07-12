package bb.roborally.client.player_list;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ListChangeListener;
import javafx.collections.transformation.FilteredList;

public class PlayerListViewModel {

    private final PlayerQueue playerQueue;
    private PlayerListView view;

    public PlayerListViewModel(PlayerQueue playerQueue) {
        this.playerQueue = playerQueue;
    }

    public void connect(PlayerListView playerListView) {
        view = playerListView;
        observeModelAndUpdate();
        setUpListeners();
    }

    private void observeModelAndUpdate() {
        FilteredList<Player> filteredPlayers = new FilteredList<>(playerQueue.getObservableListPlayers());
        filteredPlayers.setPredicate(player -> !player.isAdded());
        view.getView().setItems(filteredPlayers);
        playerQueue.mustUpdateProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observableValue, Boolean oldVal, Boolean newVal) {
                if (newVal) {
                    FilteredList<Player> filteredPlayers = new FilteredList<>(playerQueue.getObservableListPlayers());
                    filteredPlayers.setPredicate(player -> !player.isAdded());
                    view.getView().setItems(filteredPlayers);
                    view.getView().refresh();
                    playerQueue.setMustUpdate(false);
                }
            }
        });
    }

    private void setUpListeners() {

    }
}
