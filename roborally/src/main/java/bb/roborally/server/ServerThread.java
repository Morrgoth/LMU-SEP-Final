package bb.roborally.server;

import bb.roborally.data.messages.Envelope;
import bb.roborally.data.messages.chat.SendChat;
import bb.roborally.data.messages.connection.Alive;
import bb.roborally.data.messages.connection.HelloClient;
import bb.roborally.data.messages.connection.HelloServer;
import bb.roborally.data.messages.connection.Welcome;
import bb.roborally.data.messages.gameplay.SelectedCard;
import bb.roborally.data.messages.gameplay.SetStartingPoint;
import bb.roborally.data.messages.lobby.PlayerValues;
import bb.roborally.data.messages.lobby.SetStatus;
import bb.roborally.data.messages.map.MapSelected;
import bb.roborally.game.User;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Timer;

public class ServerThread extends Thread{
    private final Socket socket;
    private final DataInputStream dataInputStream;
    private final DataOutputStream dataOutputStream;
    private final Server server;
    private User user;

    public ServerThread(Server server, Socket socket) throws IOException {
        this.server = server;
        this.socket = socket;
        this.dataInputStream = new DataInputStream(socket.getInputStream());
        this.dataOutputStream = new DataOutputStream(socket.getOutputStream());
    }

    /**
     * Receives, processes and forwards messages from Clients.
     */
    public void run(){
        connect();
        String json = "";
        try{
            server.updateUser(user.getClientID());
            DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());
            while(!socket.isClosed()) {
                // Receive messages from User and forward them to the Server for execution
                json = dataInputStream.readUTF();
                System.out.println(json);
                Envelope envelope = Envelope.fromJson(json);
                if (envelope.getMessageType() == Envelope.MessageType.ALIVE) {
                    Alive alive = (Alive) envelope.getMessageBody();
                    server.process(alive, this.user);
                } else if (envelope.getMessageType() == Envelope.MessageType.PLAYER_VALUES) {
                    PlayerValues playerValues = (PlayerValues) envelope.getMessageBody();
                    server.process(playerValues, user);
                } else if (envelope.getMessageType() == Envelope.MessageType.SET_STATUS) {
                    SetStatus setStatus = (SetStatus) envelope.getMessageBody();
                    server.process(setStatus, user);
                } else if (envelope.getMessageType() == Envelope.MessageType.SEND_CHAT) {
                    SendChat sendChat = (SendChat) envelope.getMessageBody();
                    server.process(sendChat, user);
                } else if (envelope.getMessageType() == Envelope.MessageType.MAP_SELECTED) {
                    MapSelected mapSelected = (MapSelected) envelope.getMessageBody();
                    server.process(mapSelected, user);
                } else if (envelope.getMessageType() == Envelope.MessageType.SET_STARTING_POINT) {
                    SetStartingPoint setStartingPoint = (SetStartingPoint) envelope.getMessageBody();
                    server.process(setStartingPoint, user);
                } else if (envelope.getMessageType() == Envelope.MessageType.SELECTED_CARD) {
                    SelectedCard selectedCard = (SelectedCard) envelope.getMessageBody();
                    server.process(selectedCard, user);
                } else {
                    //TODO: Illegal Message: Error handling
                }
            }
        } catch(Exception e) {
            System.out.println("ServerThreadError: " + e.getMessage());
        }
    }

    public void connect() {
        try {
            HelloClient helloClient = new HelloClient();
            this.dataOutputStream.writeUTF(helloClient.toJson());
            String helloServerJson = this.dataInputStream.readUTF();

            System.out.println(helloServerJson);

            Envelope helloServerEnvelope = Envelope.fromJson(helloServerJson);
            if (helloServerEnvelope.getMessageType() == Envelope.MessageType.HELLO_SERVER) {
                HelloServer helloServer = (HelloServer) helloServerEnvelope.getMessageBody();
                int clientId = ClientList.getNextClientId();
                server.getClientList().addClient(clientId, socket);
                this.user = new User(clientId, helloServer.isAI());
                Welcome welcome = new Welcome(clientId);
                dataOutputStream.writeUTF(welcome.toJson());
                AliveChecker aliveChecker = new AliveChecker(server, socket, user);
                Timer timer = new Timer();
                timer.schedule(aliveChecker, 0, 5000);
            } else {
                // Error: incorrect message type
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
