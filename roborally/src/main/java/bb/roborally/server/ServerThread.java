package bb.roborally.server;

import bb.roborally.data.messages.Envelope;

import java.io.DataInputStream;
import java.net.Socket;

public class ServerThread extends Thread{
    private final Socket clientSocket;
    private final Server server;

    public ServerThread(Server server, Socket clientSocket) {
        this.server = server;
        this.clientSocket = clientSocket;
    }

    /**
     * Receives, processes and forwards messages from Clients.
     */
    public void run(){
        String json = "";
        try{
            DataInputStream dataInputStream = new DataInputStream(clientSocket.getInputStream());
            while(!clientSocket.isClosed()) {
                // Receive messages from User and forward them to the Server for execution
                json = dataInputStream.readUTF();
                Envelope envelope = Envelope.fromJson(json);
                server.process(envelope);
            }
        }
        catch(Exception e) {
            System.out.println("ServerThreadError: " + e.getMessage());
        }
    }
}
