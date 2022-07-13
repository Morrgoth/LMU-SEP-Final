package bb.roborally.client.player_list;

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
                        setText(null);
                    } else {
                        if (item.isAdded() && !item.isReady()) {
                            setText(item.getName() + "(" + item.getRobot().getName() + ")");
                        } else if (item.isAdded() && item.isReady()) {
                            setText("[ready] " + item.getName() + "(" + item.getRobot().getName() + ")");
                        } else {
                            setGraphic(null);
                            setText(null);
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
