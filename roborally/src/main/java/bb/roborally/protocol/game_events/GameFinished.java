package bb.roborally.protocol.game_events;

import bb.roborally.protocol.Envelope;
import bb.roborally.protocol.Message;

/**
 * @author Muqiu Wang
 */
public class GameFinished implements Message {
    private int clientID;

    public GameFinished() {
    }

    public GameFinished(int clientID) {
        this.clientID = clientID;
    }

    public int getClientID() {
        return clientID;
    }

    public void setClientID(int clientID) {
        this.clientID = clientID;
    }

    @Override
    public String toJson() {
        return toEnvelope().toJson();
    }

    @Override
    public Envelope toEnvelope() {
        return new Envelope(Envelope.MessageType.GAME_FINISHED, this);
    }
}
