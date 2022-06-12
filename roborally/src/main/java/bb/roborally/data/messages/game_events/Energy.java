package bb.roborally.data.messages.game_events;

import bb.roborally.data.messages.Envelope;
import bb.roborally.data.messages.Message;

public class Energy implements Message {
    private int clientID;
    private int count;
    private String source;

    public Energy() {
    }

    public Energy(int clientID, int count, String source) {
        this.clientID = clientID;
        this.count = count;
        this.source = source;
    }

    public int getClientID() {
        return clientID;
    }

    public void setClientID(int clientID) {
        this.clientID = clientID;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    @Override
    public String toJson() {
        return toEnvelope().toJson();
    }

    @Override
    public Envelope toEnvelope() {
        return new Envelope(Envelope.MessageType.ENERGY, this);
    }
}
