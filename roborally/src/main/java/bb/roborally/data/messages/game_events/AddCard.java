package bb.roborally.data.messages.game_events;

import bb.roborally.data.messages.Envelope;
import bb.roborally.data.messages.Message;
import bb.roborally.game.Robot;

public class AddCard implements Message{
	private int clientID;

	public AddCard(){

	}
	public AddCard(Robot robot){
		this.clientID = robot.getClientID();
	}

	public int getClientID() {
		return clientID;
	}

	public void setClientID(int clientID) {
		this.clientID = clientID;
	}

	@Override
	public String toJson() {
		return toEnvelope().toJson();
	}

	@Override
	public Envelope toEnvelope() {
		return new Envelope(Envelope.MessageType.ADD_CARD, this);
	}
}
