package roborally.src.main.java.bb.roborally.data.messages.lobby_messages;

import bb.roborally.data.messages.Envelope;
import bb.roborally.data.messages.Message;

public class SetStatus implements Message{
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
		ready = ready;
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
