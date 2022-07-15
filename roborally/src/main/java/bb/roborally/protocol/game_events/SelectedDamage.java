package bb.roborally.protocol.game_events;

import bb.roborally.protocol.Envelope;
import bb.roborally.protocol.Message;

public class SelectedDamage implements Message {
    private String cards;

    public SelectedDamage(String cards){
        this.cards=cards;
    }
    public SelectedDamage(){
    }

    public String getCards() {
        return cards;
    }

    public void setCards(String cards) {
        this.cards = cards;
    }

    @Override
    public String toJson() {
        return toEnvelope().toJson();
    }

    @Override
    public Envelope toEnvelope() {
        return new Envelope(Envelope.MessageType.SELECTED_DAMAGE, this);
    }
}
