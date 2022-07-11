package bb.roborally.client.player_list;

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
        FilteredList<Player> filteredList = new FilteredList<>(playerQueue.getObservableListPlayers());
        filteredList.setPredicate(player -> !player.getName().isEmpty());
        view.getView().setItems(playerQueue.getObservableListPlayers());
    }

    private void setUpListeners() {

    }
}
