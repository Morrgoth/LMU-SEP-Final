package roborally.src.main.java.bb.roborally.data.messages.lobby_messages;
import bb.roborally.data.messages.Envelope;
import bb.roborally.data.messages.Message;

public class PlayerAdded implements Message{
	private int clientID;
	private String name;
	private int figure;

	public class PlayerAdded(){

	}

	public class PlayerAdded(int clientID, String name, int figure){
		this.clientID = clientID;
		this.name = name;
		this.figure = figure;
	}

	public int getClientID() {
		return clientID;
	}

	public void setClientID(int clientID) {
		clientID = clientID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getFigure() {
		return figure;
	}

	public void setFigure(int figure) {
		this.figure = figure;
	}

	@Override
	public String toJson() {
		return toEnvelope().toJson();
	}

	@Override
	public Envelope toEnvelope() {
		return new Envelope (Envelope.MessageType.PLAYER_ADDED,this);
	}
}
