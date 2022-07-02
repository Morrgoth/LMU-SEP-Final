package bb.roborally.server;

import bb.roborally.data.messages.*;
import bb.roborally.data.messages.chat.ReceivedChat;
import bb.roborally.data.messages.chat.SendChat;
import bb.roborally.data.messages.connection.Alive;
import bb.roborally.data.messages.lobby.PlayerAdded;
import bb.roborally.data.messages.lobby.PlayerStatus;
import bb.roborally.data.messages.lobby.PlayerValues;
import bb.roborally.data.messages.lobby.SetStatus;
import bb.roborally.game.PlayerQueue;
import bb.roborally.game.User;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private final ClientList clientList = new ClientList();
    private final PlayerQueue playerQueue = new PlayerQueue(2);
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
        playerQueue.remove(user);
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
        for (Message message: playerQueue.generatePlayersUpdate()) {
            broadcastOnly(message, clientId);
        }
    }

    public void process(Envelope envelope) throws IOException {
        // Catchall process
    }

    public void process(Alive alive, User user) {
        user.setUserStatus(User.UserStatus.VERIFIED);
    }

    public void process(PlayerValues playerValues, User user) throws IOException {
        // TODO: Check that the Robot is unique, username must not be unique -> in ClientList
        user.setName(playerValues.getName());
        user.setFigure(playerValues.getFigure());
        PlayerAdded playerAdded = new PlayerAdded(user.getClientID(), user.getName(), user.getFigure());
        playerQueue.add(user);
        broadcast(playerAdded);
    }

    public void process(SetStatus setStatus, User user) throws IOException {
        user.setReady(setStatus.isReady());
        PlayerStatus playerStatus = new PlayerStatus(user.getClientID(), user.isReady());
        playerQueue.update(playerStatus);
        broadcast(playerStatus);
    }

    public void process(SendChat sendChat, User user) throws IOException {
        ReceivedChat receivedChat = new ReceivedChat(sendChat.getMessage(), user.getClientID(), false);
        chatHistory.addMessage(receivedChat);
        broadcast(receivedChat);
    }

    public ClientList getClientList() {
        return clientList;
    }
}
