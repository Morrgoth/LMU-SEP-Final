package bb.roborally.protocol.gameplay;

import bb.roborally.protocol.Envelope;
import bb.roborally.protocol.Message;

public class SelectedCard implements Message {
    private String card;
    private int register;

    public SelectedCard() {}

    public SelectedCard(String card, int register) {
        this.card = card;
        this.register = register;
    }

    public String getCard() {
        return card;
    }

    public void setCard(String card) {
        this.card = card;
    }

    public int getRegister() {
        return register;
    }

    public void setRegister(int register) {
        this.register = register;
    }

    @Override
    public String toJson() {
        return toEnvelope().toJson();
    }

    @Override
    public Envelope toEnvelope() {
        return new Envelope(Envelope.MessageType.SELECTED_CARD, this);
    }
}
