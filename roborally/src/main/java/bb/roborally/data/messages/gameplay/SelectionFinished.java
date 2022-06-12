package bb.roborally.data.messages.gameplay;

import bb.roborally.data.messages.Envelope;
import bb.roborally.data.messages.Message;

public class SelectionFinished implements Message {

    private int clientID;

    public SelectionFinished() {}

    public SelectionFinished(int clientID) {
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
        return new Envelope(Envelope.MessageType.SELECTION_FINISHED, this);
    }
}
