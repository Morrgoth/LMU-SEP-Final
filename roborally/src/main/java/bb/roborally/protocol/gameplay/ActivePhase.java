package bb.roborally.protocol.gameplay;

import bb.roborally.protocol.Envelope;
import bb.roborally.protocol.Message;


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
