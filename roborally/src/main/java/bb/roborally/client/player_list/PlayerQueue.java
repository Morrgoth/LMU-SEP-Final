package bb.roborally.client.player_list;

import bb.roborally.client.robot_selector.Robot;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.BooleanBinding;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.Optional;

public class PlayerQueue {

    private final Player localPlayer = new Player();
    private final ObservableList<Player> players = FXCollections.observableArrayList(localPlayer);
    public ObservableList<Player> getObservableListPlayers() {
        return players;
    }

    public void setLocalPlayerId(int id) {
        localPlayer.setId(id);
    }

    public void addPlayer(int id, String name, Robot robot) {
        if (getPlayerById(id) == null) {
            Player other = new Player(id);
            other.add(name, robot);
            other.setAdded(true);
            players.add(other);
        } else {
            Player present = getPlayerById(id);
            players.remove(present);
            present.add(name, robot);
            players.add(0, present);
        }
    }

    public Player getLocalPlayer() {
        return localPlayer;
    }

    public int getLocalPlayerId() {
        return localPlayer.getId();
    }

    public boolean getLocalPlayerIdSet() {
        return localPlayer.isIdSet();
    }

    public BooleanBinding localPlayerIdSetProperty() {
        return localPlayer.idSetProperty();
    }

    public Player getPlayerById(int id) {
        for (Player player: players) {
            if (player.getId() == id) {
                return player;
            }
        }
        return null;
    }
}
