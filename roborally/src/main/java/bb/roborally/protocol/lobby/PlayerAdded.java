package bb.roborally.protocol.lobby;
import bb.roborally.protocol.Envelope;
import bb.roborally.protocol.Message;

public class PlayerAdded implements Message {
	private int clientID;
	private String name;
	private int figure;

	public  PlayerAdded(){

	}

	public  PlayerAdded(int clientID, String name, int figure){
		this.clientID = clientID;
		this.name = name;
		this.figure = figure;
	}

	public int getClientID() {
		return clientID;
	}

	public void setClientID(int clientID) {
		this.clientID = clientID;
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
