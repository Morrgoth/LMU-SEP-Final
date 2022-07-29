package bb.roborally.client.player_list;

/**
 *
 * @author Bence Ament
 */
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
        view.getListView().setItems(playerQueue.getObservableListPlayers());
    }

    private void setUpListeners() {

    }
}
