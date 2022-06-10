package bb.roborally.data.messages.lobby;

import bb.roborally.data.messages.Envelope;
import bb.roborally.data.messages.Message;

public class PlayerValues implements Message {

	private String playerName;
	private int figure;

	public PlayerValues() {
	}

	public PlayerValues(int figure, String playerName) {
		this.playerName = playerName;
		this.figure = figure;
	}

	public String getPlayerName() {
		return playerName;
	}

	public void setPlayerName(String playerName) {
		this.playerName = playerName;
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
		return new Envelope (Envelope.MessageType.PLAYER_VALUES, this);
	}
}



/*
	Setzen des Spielernamens und der Figur
	Nach dem erfolgreichen Verbindungsaufbau kann ein Spieler seinen Namen und seine Spielfigur
	auswählen. Die Spielfigur soll dabei einzigartig sein, der Spielernamen darf mehrfach vergeben
	werden.

		"messageType": "PlayerValues",
		"messageBody": {
		"name": "Nr. 5",
		"figure": 5
*/