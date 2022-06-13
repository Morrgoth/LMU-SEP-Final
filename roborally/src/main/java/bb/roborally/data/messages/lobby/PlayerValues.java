package bb.roborally.data.messages.lobby;

import bb.roborally.data.messages.Envelope;
import bb.roborally.data.messages.Message;

public class PlayerValues implements Message {
	private String name;
	private int figure;

	public PlayerValues(){
	}

	public PlayerValues(String name, int figure){
		this.name = name;
		this.figure = figure;
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
		return new Envelope (Envelope.MessageType.PLAYER_VALUES,this);
	}
}
