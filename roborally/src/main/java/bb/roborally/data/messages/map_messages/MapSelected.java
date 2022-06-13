package roborally.src.main.java.bb.roborally.data.messages.lobby_messages;

import bb.roborally.data.messages.Envelope;
import bb.roborally.data.messages.Message;

public class MapSelected {
	private String map;

	public MapSelected(){

	}

	public MapSelected(String map){
		this.map = map;
	}


	public String getMap() {
		return map;
	}

	public void setMap(String map) {
		this.map = map;
	}

	@Override
	public String toJson() {
		return toEnvelope().toJson();
	}

	@Override
	public Envelope toEnvelope() {
		return new Envelope (Envelope.MessageType.MAP_SELECTED,this);
	}
}
