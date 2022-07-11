package bb.roborally.client.player_list;

import bb.roborally.server.game.User;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.util.Callback;

public class PlayerListView {

    private final ListView<Player> view = new ListView<>();

    Callback<ListView<Player>, ListCell<Player>> playerListCellFactory = new Callback<ListView<Player>, ListCell<Player>>() {
        @Override
        public ListCell<Player> call(ListView<Player> l) {
            return new ListCell<Player>() {
                @Override
                protected void updateItem(Player item, boolean empty) {
                    super.updateItem(item, empty);
                    if (item == null || empty) {
                        setGraphic(null);
                    } else {
                        if (item.isAdded()) {
                            if (item.isReady()) {
                                setText("[ready] " + item.getName() + "(" + item.getRobot().getName() + ")");
                            } else {
                                setText(item.getName() + "(" + item.getRobot().getName() + ")");
                            }
                        }
                    }
                }
            } ;
        }
    };

    public PlayerListView() {
        view.setCellFactory(playerListCellFactory);
    }

    public ListView<Player> getView() {
        return view;
    }

    public void setPrefHeight(int height) {
        view.setPrefHeight(height);
    }
}
