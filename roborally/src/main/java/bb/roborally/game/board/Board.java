package bb.roborally.game.board;

import bb.roborally.data.messages.Envelope;
import bb.roborally.data.messages.Message;

import java.util.ArrayList;

/**
 * author Philipp Keyzman
 */
public class Board implements Message {
	private Cell cell;
	private ArrayList<Cell> gameMap;

	public ArrayList<Cell> getGameMap() {
		return gameMap;
	}

	public void setGameMap(ArrayList<Cell> gameMap) {
		this.gameMap = gameMap;
	}

	@Override
	public String toJson() {
		return toEnvelope().toJson();
	}

	@Override
	public Envelope toEnvelope() {
		return new Envelope(Envelope.MessageType.GAME_STARTED, this);
	}

	public Cell getCell() {
		return cell;
	}

	public void setCell(Cell cell) {
		this.cell = cell;
	}
}

