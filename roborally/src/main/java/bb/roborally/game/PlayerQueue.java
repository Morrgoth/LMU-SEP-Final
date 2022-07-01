package bb.roborally.game;

import bb.roborally.data.messages.Message;
import bb.roborally.data.messages.lobby.PlayerAdded;
import bb.roborally.data.messages.lobby.PlayerStatus;
import bb.roborally.data.messages.lobby.SetStatus;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * this class implements the logic of the player order in the game
 * @author Veronika Heckel
 * @author Muqiu Wang
 * @author Tolga Engin
 * @author Zeynab Baiani
 * @author Bence Ament
 * @autor  Philipp Keyzman
 */
public class PlayerQueue {
    HashMap<Integer, User> users = new HashMap<Integer, User>();

    public void add(User user) {
        if (!users.containsKey(user.getClientID())) {
            users.put(user.getClientID(), user);
        }
    }

    public void update(PlayerStatus playerStatus) {
        users.get(playerStatus.getClientID()).readyPropertyProperty().set(playerStatus.isReady());
    }

    public ArrayList<Message> getCurrentPlayersUpdate() {
        ArrayList<Message> messages = new ArrayList<>();
        for (User user: users.values()) {
            PlayerAdded playerAdded = new PlayerAdded(user.getClientID(), user.getName(), user.getFigure());
            PlayerStatus playerStatus = new PlayerStatus(user.getClientID(), user.isReady());
            messages.add(playerAdded);
            messages.add(playerStatus);
        }
        return messages;
    }
}
