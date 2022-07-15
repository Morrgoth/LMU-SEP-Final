package bb.roborally.protocol.game_events;

import bb.roborally.protocol.Envelope;
import bb.roborally.protocol.Message;

public class PlayerTurning implements Message {
    private int clientID;
    private String rotation;

    public PlayerTurning(){}
    public PlayerTurning(int clientID, String rotation) {
        this.clientID = clientID;
        this.rotation = rotation;
    }
    public int getClientID() {
        return clientID;
    }
    public void setClientID(int clientID) {
        this.clientID = clientID;
    }
    public String getRotation() {
        return rotation;
    }
    public void setRotation(String rotation) {
        this.rotation = rotation;
    }

    @Override
    public String toJson() {
        return toEnvelope().toJson();
    }

    @Override
    public Envelope toEnvelope() {
        return new Envelope(Envelope.MessageType.PLAYER_TURNING, this);
    }
}
