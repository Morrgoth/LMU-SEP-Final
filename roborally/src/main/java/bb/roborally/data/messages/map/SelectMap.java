package bb.roborally.data.messages.map;

import bb.roborally.data.messages.Message;
import bb.roborally.data.messages.Envelope;

public class SelectMap implements Message{
	private String[] availableMaps;

	public SelectMap(){

	}

	public SelectMap(String[] availableMaps){
		this.availableMaps = availableMaps;
	}

	public String[] getAvailableMaps() {
		return availableMaps;
	}

	public void setAvailableMaps(String[] availableMaps) {
		this.availableMaps = availableMaps;
	}

	@Override
	public String toJson() {
		return toEnvelope().toJson();
	}

	@Override
	public Envelope toEnvelope() {
		return new Envelope(Envelope.MessageType.SELECT_MAP,this);
	}
}



/*
	"messageType": "SelectMap",
	"messageBody": {
	"availableMaps": ["DizzyHighway"]

*/