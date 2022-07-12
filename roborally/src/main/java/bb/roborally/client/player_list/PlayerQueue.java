package bb.roborally.client.player_list;

import bb.roborally.client.robot_selector.Robot;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.BooleanBinding;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.Optional;

public class PlayerQueue {

    private final Player localPlayer = new Player();
    private final ObservableList<Player> players = FXCollections.observableArrayList(localPlayer);
    private final BooleanProperty mustUpdate = new SimpleBooleanProperty(false);
    public ObservableList<Player> getObservableListPlayers() {
        return players;
    }

    public void setLocalPlayerId(int id) {
        localPlayer.setId(id);
        setMustUpdate(true);
    }

    public void addPlayer(int id, String name, Robot robot) {
        if (getPlayerById(id) == null) {
            Player other = new Player(id);
            players.add(other);
            other.add(name, robot);
        } else {
            Player present = getPlayerById(id);
            present.add(name, robot);
        }
        setMustUpdate(true);
    }

    public void setPlayerReady(int id, boolean ready) {
        getPlayerById(id).setReady(ready);
        if (!ready && id == getLocalPlayerId()) {
            localPlayer.mapSelectorProperty().set(false);
        }
        setMustUpdate(true);
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

    public int size() {
        return players.size();
    }

    public void setMustUpdate(boolean mustUpdate) {
        this.mustUpdate.set(mustUpdate);
    }

    public boolean isMustUpdate() {
        return mustUpdate.get();
    }

    public BooleanProperty mustUpdateProperty() {
        return mustUpdate;
    }
}
