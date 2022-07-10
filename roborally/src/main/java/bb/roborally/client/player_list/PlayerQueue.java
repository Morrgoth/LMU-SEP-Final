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
    private final int NO_LOCAL_USER = -99999999;
    private final IntegerProperty localPlayerId = new SimpleIntegerProperty(NO_LOCAL_USER);
    private final BooleanBinding localPlayerIdSet = Bindings.notEqual(NO_LOCAL_USER, localPlayerId);
    private final ObservableList<Player> players = FXCollections.observableArrayList();
    public ObservableList<Player> getObservableListPlayers() {
        return players;
    }

    public void addLocalPlayer(int id) {
        Player local = new Player(id);
        localPlayerId.set(id);
        players.add(local);
    }

    public void addPlayer(int id, String name, Robot robot) {
        Player other = new Player(id);
        other.add(name, robot);
        players.add(other);
    }

    public Player getLocalPlayer() {
        // TODO: cannot return null
        Optional<Player> optionalPlayer = players.stream().filter(player -> player.getId() == localPlayerId.get()).findFirst();
        return optionalPlayer.orElse(null);
    }

    public int getLocalPlayerId() {
        return localPlayerId.get();
    }

    public boolean getLocalPlayerIdSet() {
        return localPlayerIdSet.get();
    }

    public BooleanBinding localPlayerIdSetProperty() {
        return localPlayerIdSet;
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
