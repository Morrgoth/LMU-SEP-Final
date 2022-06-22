package bb.roborally.game.board;

import bb.roborally.data.messages.Envelope;
import bb.roborally.data.messages.Message;
import bb.roborally.game.tiles.Tile;

import java.util.ArrayList;

/**
 * author Philipp Keyzman
 */
public class Cell implements Message {

	private Tile tile;
	private ArrayList<Tile> cell;

	public ArrayList <Tile> cell(Tile tile){

	}

	public Tile getTile() {
		return tile;
	}

	public void setTile(Tile tile) {
		this.tile = tile;
	}
	public ArrayList<Tile> getCell() {
		return cell;
	}

	public void setCell(ArrayList<Tile> cell) {
		this.cell = cell;
	}

	@Override
	public String toJson() {
		return toEnvelope().toJson();
	}

	@Override
	public Envelope toEnvelope() {
		return new Envelope(Envelope.MessageType.CELL_Created, this);
	}

}
