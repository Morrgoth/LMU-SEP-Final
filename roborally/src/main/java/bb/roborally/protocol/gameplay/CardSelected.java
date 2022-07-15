package bb.roborally.protocol.gameplay;

import bb.roborally.protocol.Envelope;
import bb.roborally.protocol.Message;

public class CardSelected implements Message {

    private int clientID;
    private int register;
    private boolean filled;

    public CardSelected() {}

    public CardSelected(int clientID, int register, boolean filled) {
        this.clientID = clientID;
        this.register = register;
        this.filled = filled;
    }

    public int getClientID() {
        return clientID;
    }

    public void setClientID(int clientID) {
        this.clientID = clientID;
    }

    public int getRegister() {
        return register;
    }

    public void setRegister(int register) {
        this.register = register;
    }

    public boolean isFilled() {
        return filled;
    }

    public void setFilled(boolean filled) {
        this.filled = filled;
    }

    @Override
    public String toJson() {
        return toEnvelope().toJson();
    }

    @Override
    public Envelope toEnvelope() {
        return new Envelope(Envelope.MessageType.CARD_SELECTED, this);
    }
}
