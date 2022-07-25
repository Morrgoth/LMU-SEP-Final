package bb.roborally.client.player_list;

import javafx.geometry.Pos;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;
import javafx.util.Callback;

public class PlayerListView {

    private final VBox view = new VBox();
    private final ListView<Player> listView = new ListView<>();

    public enum Kind {
        COMPACT,
        DETAILED
    }

    Callback<ListView<Player>, ListCell<Player>> cellFactoryCompact = new Callback<ListView<Player>, ListCell<Player>>() {
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
                            if (item.getRobot() != null) {
                                setText(item.getName() + "(" + item.getRobot().getName() + ")");
                            } else {
                                setText(item.getName());
                            }
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

    Callback<ListView<Player>, ListCell<Player>> cellFactoryDetailed = new Callback<ListView<Player>, ListCell<Player>>() {
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
                        if (item.isReady()) {
                            final PlayerViewModel playerViewModel = new PlayerViewModel(item);
                            final PlayerView playerView = new PlayerView();
                            playerViewModel.connect(playerView);
                            setGraphic(playerView.getView());
                            //setText("New: " + item.getName() + "(" + item.getRobot().getName() + ")");
                        } else {
                            setGraphic(null);
                            setText(null);
                        }
                    }
                }
            } ;
        }
    };

    public PlayerListView(Kind kind) {
        if (kind == Kind.COMPACT) {
            listView.setCellFactory(cellFactoryCompact);
            view.getChildren().add(listView);
        } else if (kind == Kind.DETAILED) {
            listView.setCellFactory(cellFactoryDetailed);
            view.getChildren().add(listView);
        }
        view.setAlignment(Pos.CENTER);
        listView.setMaxWidth(300);
    }

    public VBox getView() {
        return view;
    }

    public ListView<Player> getListView() {
        return listView;
    }

    public void setPrefHeight(int height) {
        listView.setPrefHeight(height);
    }
}
