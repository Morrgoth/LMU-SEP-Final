package bb.roborally.data.messages.connection;

import bb.roborally.data.messages.Envelope;
import bb.roborally.data.messages.Message;

public class ConnectionUpdate implements Message {

    private int clientID;
    private boolean isConnected;
    private String action;

    public ConnectionUpdate(int clientID, boolean isConnected, String action){
        this.clientID = clientID;
        this.isConnected = isConnected;
        this.action = action;
    }

    public ConnectionUpdate(){
    }


    public int getClientID() {
        return clientID;
    }

    public void setClientID(int clientID) {
        this.clientID = clientID;
    }

    public boolean isConnected() {
        return isConnected;
    }

    public void setConnected(boolean connected) {
        isConnected = connected;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    @Override
    public String toJson() {
        return toEnvelope().toJson();
    }

    @Override
    public Envelope toEnvelope() {
        return null;
    }
}
