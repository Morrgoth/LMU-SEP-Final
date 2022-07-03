package bb.roborally.server;

import bb.roborally.data.messages.*;
import bb.roborally.data.messages.Error;
import bb.roborally.data.messages.chat.ReceivedChat;
import bb.roborally.data.messages.chat.SendChat;
import bb.roborally.data.messages.connection.Alive;
import bb.roborally.data.messages.lobby.PlayerAdded;
import bb.roborally.data.messages.lobby.PlayerStatus;
import bb.roborally.data.messages.lobby.PlayerValues;
import bb.roborally.data.messages.lobby.SetStatus;
import bb.roborally.data.messages.map.MapSelected;
import bb.roborally.data.messages.map.SelectMap;
import bb.roborally.game.Game;
import bb.roborally.game.PlayerQueue;
import bb.roborally.game.User;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Arrays;

public class Server {
    private final ClientList clientList = new ClientList();
    private final Game game = new Game(2);
    private final ChatHistory chatHistory = new ChatHistory();
    public static void main(String[] args) {
        Server server = new Server();
        server.registerUsers();
    }

    /**
     * Waits for and handles the Login Requests of Users.
     */
    public void registerUsers() {
        try {
            int PORT = 6868;
            ServerSocket server = new ServerSocket(PORT);
            InetAddress inetAddress = InetAddress.getLocalHost();
            System.out.println("Server started running on " + inetAddress.getHostAddress() + ":" + PORT);
            while (true) {
                Socket clientSocket = server.accept();
                if(clientSocket != null) {
                    ServerThread serverThread = new ServerThread(this, clientSocket);
                    serverThread.start();
                }
            }
        }
        catch(Exception e) {
            System.out.println("ServerError: " +  e.getMessage());
        }
    }

    public void logout(User user) {
        clientList.clearClientList();
        game.getPlayerQueue().remove(user);
    }

    private void broadcast(Message message) throws IOException {
        for (Socket socket: clientList.getAllClients()) {
            DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());
            dataOutputStream.writeUTF(message.toJson());
        }
    }

    /**
     * This method can be used to broadcast messages to subsets of all users.
     * @throws IOException
     */
    private void broadcastOnly(Message message, int targetClientId) throws IOException {
        DataOutputStream dataOutputStream = new DataOutputStream(clientList.getClient(targetClientId).getOutputStream());
        dataOutputStream.writeUTF(message.toJson());
    }

    private void broadcastExcept(Message message, int exceptClientId) throws IOException {
        for (Socket socket: clientList.getAllClientsExcept(exceptClientId)) {
            DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());
            dataOutputStream.writeUTF(message.toJson());
        }
    }

    public void updateUser(int clientId) throws IOException {
        for (ReceivedChat receivedChat: chatHistory.getPublicMessages()) {
            broadcastOnly(receivedChat, clientId);
        }
        for (Message message: game.getPlayerQueue().generatePlayersUpdate()) {
            broadcastOnly(message, clientId);
        }
    }

    public void process(Alive alive, User user) {
        user.setUserStatus(User.UserStatus.VERIFIED);
    }

    public void process(PlayerValues playerValues, User user) throws IOException {
        if (game.isRobotAvailable(playerValues.getFigure())) {
            user.setName(playerValues.getName());
            user.setFigure(playerValues.getFigure());
            game.setRobotUnavailable(playerValues.getFigure());
            PlayerAdded playerAdded = new PlayerAdded(user.getClientID(), user.getName(), user.getFigure());
            game.getPlayerQueue().add(user);
            broadcast(playerAdded);
        } else {
            Error error = new Error("Robot is already taken! Choose another one.");
            broadcastOnly(error, user.getClientID());
        }
    }

    public void process(SetStatus setStatus, User user) throws IOException {
        user.setReady(setStatus.isReady());
        PlayerStatus playerStatus = new PlayerStatus(user.getClientID(), user.isReady());
        game.getPlayerQueue().update(playerStatus);
        broadcast(playerStatus);
        // Send the SelectMap message if necessary
        if (game.getPlayerQueue().isMapSelectorAvailable() && !game.getPlayerQueue().isMapSelectorNotified()) {
            SelectMap selectMap = new SelectMap(game.getAvailableMaps());
            broadcastOnly(selectMap, game.getPlayerQueue().getMapSelectorClientId());
            game.getPlayerQueue().setMapSelectorNotified(true);
        }
    }

    public void process(SendChat sendChat, User user) throws IOException {
        ReceivedChat receivedChat = new ReceivedChat(sendChat.getMessage(), user.getClientID(), false);
        chatHistory.addMessage(receivedChat);
        broadcast(receivedChat);
    }

    public void process(MapSelected mapSelected, User user) throws IOException {
        if (user.getClientID() == game.getPlayerQueue().getMapSelectorClientId()) {
            if (Arrays.stream(game.getAvailableMaps()).anyMatch(map -> map.equals(mapSelected.getMap()))) {
                game.setMapSelected(true);
                game.setSelectedMap(mapSelected.getMap());
                // TODO: Replace this MapSelected with the GameStarted message once it is available
                broadcast(mapSelected);
            }
        }
    }

    public ClientList getClientList() {
        return clientList;
    }
}
