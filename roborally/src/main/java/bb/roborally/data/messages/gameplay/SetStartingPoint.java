package bb.roborally.data.messages.gameplay;

import bb.roborally.data.messages.Envelope;
import bb.roborally.data.messages.Message;

/**
 * @author Veronika Heckel
 */
public class SetStartingPoint implements Message {

    private int x;
    private int y;

    public SetStartingPoint(){

    }

    public SetStartingPoint(int x, int y){
        this.x = x;
        this.y = y;
    }


    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    @Override
    public String toJson() {
        return toEnvelope().toJson();
    }

    @Override
    public Envelope toEnvelope() {
        return new Envelope(Envelope.MessageType.SET_STARTINGPOINT, this);
    }
}
