package bb.roborally.server;

import bb.roborally.data.messages.Envelope;
import bb.roborally.data.messages.LoginConfirmation;
import bb.roborally.data.messages.LoginRequest;
import bb.roborally.data.util.User;

import java.io.DataInputStream;
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
                    DataInputStream dataInputStream = new DataInputStream(clientSocket.getInputStream());
                    String json = dataInputStream.readUTF();
                    Envelope envelope = Envelope.fromJson(json);
                    if (envelope.getMessageType().equals("LoginRequest")) {
                        LoginRequest loginRequest = (LoginRequest) envelope.getMessageBody();
                        User user = loginRequest.getUser();
                        handleLoginRequest(user, clientSocket);
                    } else {
                        System.out.println("Received Invalid LoginRequest!");
                        clientSocket.close();
                    }
                }
            }
        }
        catch(Exception e) {
            System.out.println("ServerError: " +  e.getMessage());
        }
    }

    /**
     * Processes valid LoginRequests, upon successful login starts a new ServerThread for the User and sends
     * out notifications. Upon unsuccessful login attempt it sends out an error message.
     * @param user
     * @param socket
     * @throws IOException
     */
    private void handleLoginRequest(User user, Socket socket) throws IOException {
        if (!clientList.containsClient(user)) {
            clientList.addClient(user, socket);
            LoginConfirmation loginConfirmation = new LoginConfirmation(user);
            broadcast(loginConfirmation.toEnvelope(), new User[] {user}, null);
            ServerThread messageRouterThread = new ServerThread(this, socket);
            messageRouterThread.start();
        } else {
            //DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());
            //ServerEvent loginError = new ServerEvent("Username is already in use. Please " +
            //        "choose another username.",
            //        ServerEvent.ServerEventType.NAME_ALREADY_TAKEN);
            //dataOutputStream.writeUTF(loginError.toEnvelope().toJson()); // LOGIN_ERROR
            socket.close();
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

    public synchronized void process(Envelope envelope) {

    }

}
