package bb.roborally.data.messages.gameplay;

import bb.roborally.data.messages.Envelope;
import bb.roborally.data.messages.Message;


/**
 * @author Veronika Heckel
 */
public class ActivePhase implements Message {

    private int phase;

    public ActivePhase(){

    }

    public ActivePhase(int phase){

        this.phase = phase;
    }

    public int getPhase() {
        return phase;
    }

    public void setPhase(int phase) {
        this.phase = phase;
    }

    @Override
    public String toJson(){
        return toEnvelope().toJson();
    }

    @Override
    public Envelope toEnvelope(){
        return new Envelope(Envelope.MessageType.ACTIVE_PHASE, this);
    }
}
