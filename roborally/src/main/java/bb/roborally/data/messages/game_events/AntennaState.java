package bb.roborally.data.messages.game_events;

import bb.roborally.data.messages.Envelope;
import bb.roborally.data.messages.Message;
import bb.roborally.game.Position;
import bb.roborally.game.Robot;

import java.util.ArrayList;

public class AntennaState implements Message {
	private Position antennaPosition;
	private int antennaColumn;
	private int antennaRow;

	public AntennaState(Position antennaPosition){
		this.antennaPosition = antennaPosition;
	}

	public AntennaState(int antennaColumn, int antennaRow) {
	}


	public Position getAntennaPosition() {
		return antennaPosition;
	}

	public void setAntennaPosition(Position antennaPosition) {
		this.antennaPosition = antennaPosition;
	}


	@Override
	public String toJson() {
		return toEnvelope().toJson();
	}

	@Override
	public Envelope toEnvelope() {
		return new Envelope(Envelope.MessageType.ANTENNA_STATE, this);
	}


}
