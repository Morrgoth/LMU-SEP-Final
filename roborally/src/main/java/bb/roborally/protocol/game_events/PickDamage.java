package bb.roborally.protocol.game_events;

import bb.roborally.protocol.Envelope;
import bb.roborally.protocol.Message;

public class PickDamage implements Message {

    private int count;
    private String availablePiles;

    public PickDamage(int count, String availablePiles){
        this.count = count;
        this.availablePiles = availablePiles;
    }

    public PickDamage(){}


    public String getAvailablePiles() {
        return availablePiles;
    }

    public void setAvailablePiles(String availablePiles) {
        this.availablePiles = availablePiles;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @Override
    public String toJson() {
        return toEnvelope().toJson();
    }

    @Override
    public Envelope toEnvelope() {
        return new Envelope(Envelope.MessageType.PICK_DAMAGE, this);
    }

}
