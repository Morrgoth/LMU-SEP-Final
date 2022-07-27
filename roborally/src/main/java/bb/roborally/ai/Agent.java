package bb.roborally.ai;

import bb.roborally.protocol.Envelope;
import bb.roborally.protocol.Message;
import bb.roborally.protocol.chat.SendChat;
import bb.roborally.protocol.connection.HelloServer;
import bb.roborally.protocol.connection.Welcome;
import bb.roborally.protocol.gameplay.SelectedCard;
import bb.roborally.protocol.gameplay.SetStartingPoint;
import bb.roborally.protocol.gameplay.YourCards;
import bb.roborally.protocol.lobby.PlayerValues;
import bb.roborally.protocol.lobby.SetStatus;
import bb.roborally.server.game.board.ServerBoard;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public abstract class Agent {
    private static final Logger LOGGER = Logger.getLogger(Agent.class.getName());
    private final String ip;
    private final int port;
    private Socket socket;
    private DataOutputStream dataOutputStream;
    private DataInputStream dataInputStream;
    private int id;
    private ServerBoard serverBoard;
    private String[] yourCards = null;

    public Agent(String ip, int port) {
        this.ip = ip;
        this.port = port;
        setupLogger();
    }

    public void start() {
        connect();
        register();
        listen();
    }

    private void connect() {
        boolean connected = false;
        while (!connected) {
            try {
                socket = new Socket(ip, port);
                dataOutputStream = new DataOutputStream(socket.getOutputStream());
                dataInputStream = new DataInputStream(socket.getInputStream());
                String helloClientJson = dataInputStream.readUTF();
                LOGGER.info("Incoming: " + helloClientJson);
                Envelope helloClientEnvelope = Envelope.fromJson(helloClientJson);
                if (helloClientEnvelope.getMessageType() == Envelope.MessageType.HELLO_CLIENT) {
                    HelloServer helloServer = new HelloServer(true);
                    broadcast(helloServer);
                    String welcomeJson = dataInputStream.readUTF();
                    Envelope welcomeEnvelope = Envelope.fromJson(welcomeJson);
                    if (welcomeEnvelope.getMessageType() == Envelope.MessageType.WELCOME) {
                        Welcome welcome = (Welcome) welcomeEnvelope.getMessageBody();
                        setId(welcome.getClientID());
                        connected = true;
                        LOGGER.info("Bot connected to the server");
                    }
                    Thread.sleep(1000);
                }
                Thread.sleep(1000);
            } catch (IOException e) {
                try {
                    Thread.sleep(1000);
                    LOGGER.severe(e.getMessage());
                } catch (InterruptedException ex) {
                    LOGGER.severe(e.getMessage());
                }
            } catch (InterruptedException e) {
                LOGGER.severe(e.getMessage());
            }
        }
    }

    private void register() {
        int FIGURE = 3;
        PlayerValues playerValues = new PlayerValues(getName(), FIGURE);
        broadcast(playerValues);
        SendChat sendChat = new SendChat("Bot in the house!", -1);
        broadcast(sendChat);
        SetStatus setStatus = new SetStatus(true);
        broadcast(setStatus);
    }

    private void listen() {
        while(!socket.isClosed()) {
            try {
                String json = dataInputStream.readUTF();
                LOGGER.info("Incoming: " + json);
                Envelope envelope = Envelope.fromJson(json);
                if (envelope.getMessageType() == Envelope.MessageType.ALIVE) {
                    broadcast(envelope.getMessageBody());
                } else if (envelope.getMessageType() == Envelope.MessageType.GAME_STARTED) {
                    this.serverBoard = (ServerBoard) envelope.getMessageBody();
                    pickStartingPoint();
                } else if (envelope.getMessageType() == Envelope.MessageType.YOUR_CARDS) {
                    yourCards = ((YourCards) envelope.getMessageBody()).getCardsInHand();
                } else if (envelope.getMessageType() == Envelope.MessageType.TIMER_STARTED) {
                    String[] program = createProgram(yourCards);
                    int register = 1;
                    for (String card: program) {
                        SelectedCard selectedCard = new SelectedCard(card, register);
                        broadcast(selectedCard);
                        register += 1;
                    }
                } else if (envelope.getMessageType() == Envelope.MessageType.GAME_FINISHED) {
                    LOGGER.info("Game Finished: Bot stopping");
                    System.exit(0);
                }
            } catch (IOException e) {
                LOGGER.severe(e.getMessage());
            }
        }
    }

    private void pickStartingPoint() {
        int x = 0;
        int y = 0;
        boolean found = false;
        while (!found && x < serverBoard.getMap().size()) {
            while (!found && y < serverBoard.getMap().get(0).size()) {
                if (serverBoard.get(x, y).hasTile("StartPoint")) {
                    found = true;
                } else {
                    y += 1;
                }
            }
            if (!found) {
                x += 1;
            }
        }
        SetStartingPoint setStartingPoint = new SetStartingPoint(x, y);
        broadcast(setStartingPoint);
    }

    protected abstract String[] createProgram(String[] availableCards);

    protected abstract String getName();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ServerBoard getBoard() {
        return serverBoard;
    }

    private static void setupLogger(){
        LOGGER.setLevel(Level.ALL);
        try {
            FileHandler fileHandler = new FileHandler("log/bot.log");
            SimpleFormatter simpleFormatter = new SimpleFormatter();
            fileHandler.setFormatter(simpleFormatter);
            LOGGER.addHandler(fileHandler);
        } catch (IOException | SecurityException ex) {
            LOGGER.log(Level.SEVERE, ex.getMessage(), ex);
        }
    }

    private void broadcast(Message message) {
        try {
            dataOutputStream.writeUTF(message.toJson());
            LOGGER.info("Outgoing: " + message.toJson());
        } catch (IOException e) {
            LOGGER.severe(e.getMessage());
        }
    }
}