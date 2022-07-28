package bb.roborally.server;

import bb.roborally.protocol.Envelope;
import bb.roborally.protocol.Error;
import bb.roborally.protocol.chat.SendChat;
import bb.roborally.protocol.connection.Alive;
import bb.roborally.protocol.connection.HelloClient;
import bb.roborally.protocol.connection.HelloServer;
import bb.roborally.protocol.connection.Welcome;
import bb.roborally.protocol.game_events.RebootDirection;
import bb.roborally.protocol.gameplay.PlayCard;
import bb.roborally.protocol.gameplay.SelectedCard;
import bb.roborally.protocol.gameplay.SetStartingPoint;
import bb.roborally.protocol.lobby.PlayerValues;
import bb.roborally.protocol.lobby.SetStatus;
import bb.roborally.protocol.map.MapSelected;
import bb.roborally.server.game.User;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Timer;
import java.util.logging.Logger;

public class ServerThread extends Thread{
    private static final Logger LOGGER = Logger.getLogger(ServerThread.class.getName());
    private final Socket socket;
    private final BufferedReader inputStream;
    private final PrintWriter outputStream;
    private final Server server;
    private User user;

    public ServerThread(Server server, Socket socket) throws IOException {
        this.server = server;
        this.socket = socket;
        this.inputStream = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        this.outputStream = new PrintWriter(socket.getOutputStream(), true);
    }

    /**
     * Receives, processes and forwards messages from Clients.
     */
    public void run(){
        connect();
        String json = "";
        try{
            server.updateUser(user.getClientID());
            while(!socket.isClosed()) {
                // Receive messages from User and forward them to the Server for execution
                json = inputStream.readLine();
                LOGGER.info("Incoming(" + user.getClientID() + "): " + json);
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
                } else if (envelope.getMessageType() == Envelope.MessageType.REBOOT_DIRECTION) {
                    RebootDirection rebootDirection = (RebootDirection) envelope.getMessageBody();
                    server.process(rebootDirection, user);
                } else if (envelope.getMessageType() == Envelope.MessageType.PLAY_CARD) {
                    PlayCard playCard = (PlayCard) envelope.getMessageBody();
                    server.process(playCard, user);
                } else {
                    LOGGER.severe("Unrecognisable MessageType!");
                }
            }
        } catch(Exception e) {
            LOGGER.severe(e.getMessage());
        }
    }

    public void connect() {
        try {
            HelloClient helloClient = new HelloClient();
            this.outputStream.println(helloClient.toJson());
            String helloServerJson = this.inputStream.readLine();
            LOGGER.info("New Connection: " + helloServerJson);
            Envelope helloServerEnvelope = Envelope.fromJson(helloServerJson);
            if (helloServerEnvelope.getMessageType() == Envelope.MessageType.HELLO_SERVER) {
                HelloServer helloServer = (HelloServer) helloServerEnvelope.getMessageBody();
                if (helloServer.getProtocol().equals(Server.PROTOCOL_VERSION)) {
                    int clientId = ClientList.getNextClientId();
                    server.getClientList().addClient(clientId, socket);
                    this.user = new User(clientId, helloServer.isAI());
                    Welcome welcome = new Welcome(clientId);
                    outputStream.println(welcome.toJson());
                    AliveChecker aliveChecker = new AliveChecker(server, socket, user);
                    Timer timer = new Timer();
                    timer.schedule(aliveChecker, 0, 5000);
                } else {
                    Error error = new Error("Server does not support this protocol version!");
                    outputStream.println(error.toJson());
                }
            } else {
                // Error: incorrect message type
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
