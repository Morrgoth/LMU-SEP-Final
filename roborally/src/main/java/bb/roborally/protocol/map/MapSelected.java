package bb.roborally.protocol.map;

import bb.roborally.protocol.Envelope;
import bb.roborally.protocol.Message;

public class MapSelected implements Message {
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
