package bb.roborally.data.messages.type_adapters.game_events;

import bb.roborally.data.messages.Envelope;
import bb.roborally.data.messages.Message;

public class BlockAction implements Message {
	public static int clientID;

	public BlockAction(int clientID){
		BlockAction.clientID = clientID;
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
		return new Envelope(Envelope.MessageType.BLOCK_ACTION, this);
	}
}
