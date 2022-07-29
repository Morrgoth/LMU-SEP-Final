package bb.roborally.server;

import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 *
 * @author Bence Ament
 */
public class ClientList {
    private static int clientIdCounter = 0;
    private final HashMap<Integer, Socket> clientList = new HashMap<Integer,Socket>();

    /**
     * @return The next available clientId to be given out
     */
    public static int getNextClientId() {
        return clientIdCounter++;
    }

    /**
     * @param clientId
     * @return Returns true if a User with the name of the provided user parameter is already in the list, false otherwise
     */
    public boolean containsClient(int clientId) {
        clearClientList();
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

    /**
     * This method checks if any clients lost connection to the server, if so it deletes the disconnected clients
     */
    public void clearClientList() {
        Iterator<Map.Entry<Integer, Socket>> iterator = clientList.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<Integer, Socket> client = iterator.next();
            if (client.getValue().isClosed()) {
                iterator.remove();
            }
        }
    }

    /**
     * @param clientId
     * @return The Socket connection of the User
     */
    public Socket getClient(int clientId) {
        clearClientList();
        return clientList.get(clientId);
    }

    /**
     * @return A list of all clients
     */
    public ArrayList<Socket> getAllClients() {
        clearClientList();
        return new ArrayList<>(clientList.values());
    }

    /**
     * @param clientId
     * @return a list of all clients except the one with clientId
     */
    public ArrayList<Socket> getAllClientsExcept(int clientId) {
        clearClientList();
        ArrayList<Socket> sockets = new ArrayList<>();
        for (int id: clientList.keySet()) {
            if (id != clientId) {
                sockets.add(clientList.get(id));
            }
        }
        return sockets;
    }

    public int size() {
        clearClientList();
        return clientList.size();
    }
}
