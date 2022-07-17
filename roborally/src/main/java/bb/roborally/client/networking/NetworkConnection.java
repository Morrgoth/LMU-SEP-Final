package bb.roborally.client.networking;

import bb.roborally.protocol.Message;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.PrintWriter;
import java.net.Socket;

public class NetworkConnection {

    private Socket socket;
    private BufferedReader dataInputStream;
    private PrintWriter dataOutputStream;
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
        this.dataInputStream = dataInputStream;
        this.dataOutputStream = dataOutputStream;
    }

    public Socket getSocket() {
        return socket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    public BufferedReader getDataInputStream() {
        return dataInputStream;
    }

    public void setDataInputStream(BufferedReader dataInputStream) {
        this.dataInputStream = dataInputStream;
    }

    public PrintWriter getDataOutputStream() {
        return dataOutputStream;
    }

    public void send(Message message) {
        dataOutputStream.println(message.toJson());
    }

    public void setDataOutputStream(PrintWriter dataOutputStream) {
        this.dataOutputStream = dataOutputStream;
    }

    public boolean isOpen() {
        return !socket.isClosed();
    }
}
