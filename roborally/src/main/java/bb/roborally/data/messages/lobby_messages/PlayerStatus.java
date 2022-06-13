package bb.roborally.data.messages.lobby_messages;

import bb.roborally.data.messages.Envelope;
import bb.roborally.data.messages.Message;

public class PlayerStatus implements Message{
	private int clientID;
	private boolean ready;

	public PlayerStatus() {

	}

	public PlayerStatus(int clientID,boolean ready){
		this.clientID = clientID;
		this.ready = ready;
	}

	public int getClientID() {
		return clientID;
	}

	public void setClientID(int clientID) {
		this.clientID = clientID;
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
		return new Envelope (Envelope.MessageType.PLAYER_STATUS,this);
	}
}

