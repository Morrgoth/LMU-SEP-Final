package bb.roborally.server;

import bb.roborally.data.messages.Message;
import bb.roborally.data.messages.lobby.PlayerAdded;
import bb.roborally.data.messages.lobby.PlayerStatus;
import bb.roborally.game.User;

import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

public class ClientList {
    public HashMap<User, Socket> clientList = new HashMap<User,Socket>();

    /**
     * @param user The User to be checked against the list
     * @return Returns true if a User with the name of the provided user parameter is already in the list, false otherwise
     */
    public boolean containsClient(User user) {
        return clientList.containsKey(user);
    }

    /**
     * @param user The User to be logged in, the username of this user must be unique in the list
     * @param socket The connection of the User to the Server
     * @return Returns true if the user was added successfully, false if some error occured (e.g. the username
     * is already in use)
     */
    public void addClient(User user, Socket socket) {
        if (!containsClient(user)) {
            clientList.put(user, socket);
        }
    }

    /**
     * @param user The User to be removed from the list, and whose connection should be closed
     * @return Returns true if the User was successfully removed, false otherwise
     * @throws IOException
     */
    public void removeClient(User user) throws IOException {
        if (containsClient(user)) {
            getClientSocket(user).close();
            clientList.remove(user);
        }
    }

    /**
     * @return Returns a Set of the currently logged-in Users without their Socket connections
     */
    public Set<User> getUsers() {
        return clientList.keySet();
    }

    /**
     * @param user The User whose Socket connection is needed
     * @return The Socket connection of the User
     */
    public Socket getClientSocket(User user) {
        return clientList.get(user);
    }

    public int size() {
        return clientList.size();
    }

    public ArrayList<Message> createLoggedInUsersUpdate() {
        ArrayList<Message> messages = new ArrayList<>();
        for (User user: getUsers()) {
            PlayerAdded playerAdded = new PlayerAdded(user.getClientID(), user.getName(), user.getFigure());
            PlayerStatus playerStatus = new PlayerStatus(user.getClientID(), user.readyPropertyProperty().get());
            messages.add(playerAdded);
            messages.add(playerStatus);
        }
        return messages;
    }

}
