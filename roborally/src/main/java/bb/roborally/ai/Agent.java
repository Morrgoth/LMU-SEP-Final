package bb.roborally.ai;

import bb.roborally.protocol.Envelope;
import bb.roborally.protocol.chat.SendChat;
import bb.roborally.protocol.connection.HelloServer;
import bb.roborally.protocol.connection.Welcome;
import bb.roborally.protocol.gameplay.SetStartingPoint;
import bb.roborally.protocol.lobby.PlayerValues;
import bb.roborally.protocol.lobby.SetStatus;
import bb.roborally.server.game.board.Board;
import bb.roborally.server.game.board.Cell;
import bb.roborally.server.game.tiles.StartPoint;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Optional;

public abstract class Agent {
    private final String ip;
    private final int port;
    private Socket socket;
    private DataOutputStream dataOutputStream;
    private DataInputStream dataInputStream;
    private int id;
    private Board board;

    public Agent(String ip, int port) {
        this.ip = ip;
        this.port = port;
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
                Envelope helloClientEnvelope = Envelope.fromJson(helloClientJson);
                if (helloClientEnvelope.getMessageType() == Envelope.MessageType.HELLO_CLIENT) {
                    HelloServer helloServer = new HelloServer(true);
                    dataOutputStream.writeUTF(helloServer.toJson());
                    String welcomeJson = dataInputStream.readUTF();
                    Envelope welcomeEnvelope = Envelope.fromJson(welcomeJson);
                    if (welcomeEnvelope.getMessageType() == Envelope.MessageType.WELCOME) {
                        Welcome welcome = (Welcome) welcomeEnvelope.getMessageBody();
                        setId(welcome.getClientID());
                        connected = true;
                    }
                    Thread.sleep(1000);
                }
                Thread.sleep(1000);
            } catch (IOException e) {
                try {
                    Thread.sleep(1000);
                    System.out.println(e.getMessage());
                } catch (InterruptedException ex) {
                    throw new RuntimeException(ex);
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private void register() {
        int FIGURE = 3;
        PlayerValues playerValues = new PlayerValues(getName(), FIGURE);
        try {
            dataOutputStream.writeUTF(playerValues.toJson());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        SendChat sendChat = new SendChat("Bot in the house!", -1);
        try {
            dataOutputStream.writeUTF(sendChat.toJson());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        SetStatus setStatus = new SetStatus(true);
        try {
            dataOutputStream.writeUTF(setStatus.toJson());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void listen() {
        while(!socket.isClosed()) {
            try {
                String json = dataInputStream.readUTF();
                System.out.println(json);
                Envelope envelope = Envelope.fromJson(json);
                if (envelope.getMessageType() == Envelope.MessageType.ALIVE) {
                    dataOutputStream.writeUTF(envelope.toJson());
                } else if (envelope.getMessageType() == Envelope.MessageType.GAME_STARTED) {
                    this.board = (Board) envelope.getMessageBody();
                    pickStartingPoint();
                } else if (envelope.getMessageType() == Envelope.MessageType.GAME_FINISHED) {
                    System.exit(0);
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        //createProgram();
    }

    private void pickStartingPoint() {
        ArrayList<Cell> startCells = board.getStartPoints();
        Optional<Cell> start = startCells.stream().filter(cell -> !((StartPoint) cell.getTile("StartPoint")).isTaken()).findAny();
        if (start.isPresent()) {
            Cell cell = start.get();
            SetStartingPoint setStartingPoint = new SetStartingPoint(cell.getPosition().getX(),
                    cell.getPosition().getY());
            try {
                dataOutputStream.writeUTF(setStartingPoint.toJson());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    protected abstract String[] createProgram(String[] availableCards);

    protected abstract String getName();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Board getBoard() {
        return board;
    }
}