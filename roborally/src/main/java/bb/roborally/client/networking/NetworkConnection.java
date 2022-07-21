package bb.roborally.client.networking;

import bb.roborally.protocol.Message;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.logging.Logger;

public class NetworkConnection {
    private static final Logger LOGGER = Logger.getLogger(NetworkConnection.class.getName());
    private Socket socket;
    private BufferedReader inputStream;
    private PrintWriter outputStream;
    private static NetworkConnection networkConnection;

    private NetworkConnection() {}

    public static NetworkConnection getInstance() {
        if (networkConnection == null) {
            networkConnection = new NetworkConnection();
        }
        return networkConnection;
    }

    public void initialize(Socket socket, BufferedReader dataInputStream, PrintWriter dataOutputStream) {
        this.socket = socket;
        this.inputStream = dataInputStream;
        this.outputStream = dataOutputStream;
    }

    public Socket getSocket() {
        return socket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    public BufferedReader getInputStream() {
        return inputStream;
    }

    public void setInputStream(BufferedReader inputStream) {
        this.inputStream = inputStream;
    }

    public PrintWriter getOutputStream() {
        return outputStream;
    }

    public void send(Message message) {
        outputStream.println(message.toJson());
        LOGGER.info("Outgoing: " + message.toJson());
    }

    public void setOutputStream(PrintWriter outputStream) {
        this.outputStream = outputStream;
    }

    public boolean isOpen() {
        return !socket.isClosed();
    }
}
