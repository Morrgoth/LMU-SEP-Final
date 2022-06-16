package bb.roborally.networking;

import bb.roborally.data.util.User;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

public class NetworkConnection {

    private Socket socket;
    private DataInputStream dataInputStream;
    private DataOutputStream dataOutputStream;
    //private User user;
    private static NetworkConnection networkConnection;

    private NetworkConnection() {}

    public static NetworkConnection getInstance() {
        if (networkConnection == null) {
            networkConnection = new NetworkConnection();
        }
        return networkConnection;
    }

    public void initialize(Socket socket, DataInputStream dataInputStream, DataOutputStream dataOutputStream) {
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

    public DataInputStream getDataInputStream() {
        return dataInputStream;
    }

    public void setDataInputStream(DataInputStream dataInputStream) {
        this.dataInputStream = dataInputStream;
    }

    public DataOutputStream getDataOutputStream() {
        return dataOutputStream;
    }

    public void setDataOutputStream(DataOutputStream dataOutputStream) {
        this.dataOutputStream = dataOutputStream;
    }
}
