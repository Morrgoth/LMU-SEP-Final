package bb.roborally.data.messages.game_events;

import bb.roborally.data.messages.Envelope;
import bb.roborally.data.messages.Message;
import bb.roborally.game.Robot;

public class DrawDamage implements Message {
	private int clientID;
	public DrawDamage() {
	}

	public DrawDamage(Robot robot) {
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
		return new Envelope(Envelope.MessageType.PLAYER_LASERED, this);
	}
}