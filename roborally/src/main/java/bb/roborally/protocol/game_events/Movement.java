package bb.roborally.protocol.game_events;

import bb.roborally.protocol.Envelope;
import bb.roborally.protocol.Message;

public class Movement implements Message {
    private int clientID;
    private int x;
    private int y;

    public Movement(){
    }

    public Movement(int clientID, int x, int y){
        this.clientID = clientID;
        this.x = x;
        this.y = y;
    }

    public int getClientID() {
        return clientID;
    }

    public void setClientID(int clientID) {
        this.clientID = clientID;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    @Override
    public String toJson() {
        return toEnvelope().toJson();
    }

    @Override
    public Envelope toEnvelope() {
        return new Envelope(Envelope.MessageType.MOVEMENT, this);
    }
}
