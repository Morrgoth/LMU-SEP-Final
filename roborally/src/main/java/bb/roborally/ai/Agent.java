package bb.roborally.ai;

import bb.roborally.protocol.Envelope;
import bb.roborally.protocol.connection.HelloServer;
import bb.roborally.protocol.connection.Welcome;
import bb.roborally.protocol.lobby.PlayerStatus;
import bb.roborally.protocol.lobby.PlayerValues;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public abstract class Agent {
    private final String ip;
    private final int port;
    private Socket socket;
    private DataOutputStream dataOutputStream;
    private DataInputStream dataInputStream;
    private final int FIGURE = 3;
    private int id;

    public Agent(String ip, int port) {
        this.ip = ip;
        this.port = port;
    }

    public void start() {
        try {
            if (connect()) {
                if (register()) {
                    program();
                }
            } else {
                // TODO: Implement retrying
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private boolean connect() throws IOException {
        Socket socket = new Socket(ip, port);
        DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());
        DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());
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
                return true;
            }
            socket.close();
            return false;
        }
        socket.close();
        return false;
    }

    private boolean register() throws IOException {
        PlayerValues playerValues = new PlayerValues(getName(), FIGURE);
        dataOutputStream.writeUTF(playerValues.toJson());
        PlayerStatus playerStatus = new PlayerStatus(id, true);
        dataOutputStream.writeUTF(playerStatus.toJson());
        return true;
    }

    private void program() {
        //createProgram();
    }

    protected abstract String[] createProgram(String[] availableCards);
    protected abstract String getName();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}