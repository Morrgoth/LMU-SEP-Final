package bb.roborally.protocol.lobby;

import bb.roborally.protocol.Envelope;
import bb.roborally.protocol.Message;

public class SetStatus implements Message {
	private boolean ready;

	public SetStatus(){

	}

	public SetStatus(boolean ready){
		this.ready = ready;
	}

	public boolean isReady() {
		return ready;
	}

	public void setReady(boolean ready) {
		this.ready = ready;
	}

	@Override
	public String toJson() {
		return toEnvelope().toJson();
	}

	@Override
	public Envelope toEnvelope() {
		return new Envelope(Envelope.MessageType.SET_STATUS, this);
	}
}
