package bb.roborally.data.messages.game_events;

import bb.roborally.data.messages.Envelope;
import bb.roborally.data.messages.Message;

public class PlayerTurning implements Message {
    private int clientID;

    public String getRotation() {
        return rotation;
    }

    public void setRotation(String rotation) {
        this.rotation = rotation;
    }

    private String rotation;

    public int getClientID() {
        return clientID;
    }

    public void setClientID(int clientID) {
        this.clientID = clientID;
    }

    public PlayerTurning(int clientID, String rotation) {
        this.clientID = clientID;
        this.rotation = rotation;
    }

    public PlayerTurning(){}

    @Override
    public String toJson() {
        return toEnvelope().toJson();
    }

    @Override
    public Envelope toEnvelope() {
        return new Envelope(Envelope.MessageType.PLAYER_TURNING, this);
    }
}
