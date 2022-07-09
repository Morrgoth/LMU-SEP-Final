package bb.roborally.client.player_list;

import javafx.scene.control.ListView;

public class PlayerListView {

    private final ListView<Player> view = new ListView<>();

    public PlayerListView() {

    }

    public ListView<Player> getView() {
        return view;
    }
}
