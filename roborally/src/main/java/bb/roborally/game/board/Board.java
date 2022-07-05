package bb.roborally.game.board;

import bb.roborally.data.messages.Envelope;
import bb.roborally.data.messages.Message;
import bb.roborally.game.tiles.Tile;

import java.util.ArrayList;

/**
 * author Philipp Keyzman
 */
public class Board implements Message {
	private ArrayList<ArrayList<Cell>> gameMap;

	public Board(ArrayList<ArrayList<Cell>> gameMap) {
		this.gameMap = gameMap;
	}

	public ArrayList<ArrayList<Cell>> getGameMap() {
		return gameMap;
	}

	public void setGameMap(ArrayList<ArrayList<Cell>> gameMap) {
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

}



