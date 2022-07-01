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
    private static int userIdCounter = 0;
    private final HashMap<Integer, Socket> clientList = new HashMap<Integer,Socket>();
    public static int getNextUserId() {
        return userIdCounter++;
    }
    /**
     * @param clientId
     * @return Returns true if a User with the name of the provided user parameter is already in the list, false otherwise
     */
    public boolean containsClient(int clientId) {
        return clientList.containsKey(clientId);
    }

    /**
     * @param clientId
     * @param socket The connection of the User to the Server
     * @return Returns true if the user was added successfully, false if some error occured (e.g. the username
     * is already in use)
     */
    public void addClient(int clientId, Socket socket) {
        if (!containsClient(clientId)) {
            clientList.put(clientId, socket);
        }
    }

    public void updateClientList() {
        for (int clientId: clientList.keySet()) {
            if (clientList.get(clientId).isClosed()) {
                clientList.remove(clientId);
            }
        }
    }

    public ArrayList<Socket> getAllClients() {
        updateClientList();
        return new ArrayList<>(clientList.values());
    }

    public ArrayList<Socket> getAllClientsExcept(int clientId) {
        ArrayList<Socket> sockets = new ArrayList<>();
        updateClientList();
        for (int id: clientList.keySet()) {
            if (id != clientId) {
                sockets.add(clientList.get(id));
            }
        }
        return sockets;
    }

    /**
     * @param clientId
     * @return The Socket connection of the User
     */
    public Socket getClientSocket(int clientId) {
        return clientList.get(clientId);
    }

    public int size() {
        return clientList.size();
    }
}
