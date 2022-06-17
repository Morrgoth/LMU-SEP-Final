package bb.roborally.server;

import bb.roborally.data.messages.*;
import bb.roborally.data.messages.chat.ReceivedChat;
import bb.roborally.data.messages.chat.SendChat;
import bb.roborally.data.messages.connection.Alive;
import bb.roborally.data.messages.lobby.PlayerAdded;
import bb.roborally.data.messages.lobby.PlayerStatus;
import bb.roborally.data.messages.lobby.PlayerValues;
import bb.roborally.data.messages.lobby.SetStatus;
import bb.roborally.game.User;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Arrays;

public class Server {
    private final int PORT = 6868;
    public ClientList clientList = new ClientList();
    public static void main(String[] args){
        Server server = new Server();
        server.registerUsers();
    }

    /**
     * Waits for and handles the Login Requests of Users.
     */
    public void registerUsers() {
        try {
            ServerSocket server = new ServerSocket(PORT);
            InetAddress inetAddress = InetAddress.getLocalHost();
            System.out.println("Server started running on " + inetAddress.getHostAddress() + ":" + PORT);
            while(true) {
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

    /**
     * This method can be used to broadcast messages to subsets of all users.
     * @param envelope The message to be broadcast
     * @param whitelist The list of users who must receive the message
     * @param blacklist the list of users who mustn't recieve the message
     * @throws IOException
     */
    private void broadcast(Envelope envelope, User[] whitelist, User[] blacklist) throws IOException {
        if (whitelist != null) {
            for (User recipient: whitelist) {
                if (clientList.getClientSocket(recipient) != null ){
                    DataOutputStream dataOutputStream = new DataOutputStream(clientList.getClientSocket(recipient).getOutputStream());
                    dataOutputStream.writeUTF(envelope.toJson());
                }
            }
        } else if (blacklist != null) {
            for (User recipient: clientList.getUsers()) {
                if (!Arrays.asList(blacklist).contains(recipient)) {
                    DataOutputStream dataOutputStream = new DataOutputStream(clientList.getClientSocket(recipient).getOutputStream());
                    dataOutputStream.writeUTF(envelope.toJson());
                }
            }
        }
        else {
            for (User recipient: clientList.getUsers()) {
                DataOutputStream dataOutputStream = new DataOutputStream(clientList.getClientSocket(recipient).getOutputStream());
                dataOutputStream.writeUTF(envelope.toJson());
            }
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
        broadcast(playerAdded.toEnvelope(), null, null);
    }

    public void process(SetStatus setStatus, User user) throws IOException {
        user.setReady(setStatus.isReady());
        PlayerStatus playerStatus = new PlayerStatus(user.getClientID(), user.isReady());
        broadcast(playerStatus.toEnvelope(), null, null);
    }

    public void process(SendChat sendChat, User user) throws IOException {
        ReceivedChat receivedChat = new ReceivedChat(sendChat.getMessage(), user.getClientID(), false);
        broadcast(receivedChat.toEnvelope(), null, null);
    }

}
