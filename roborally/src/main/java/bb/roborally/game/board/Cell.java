package bb.roborally.game.board;

import bb.roborally.data.messages.Envelope;
import bb.roborally.data.messages.Message;
import bb.roborally.game.tiles.Tile;

import java.util.ArrayList;

/**
 * author Philipp Keyzman
 */
public class Cell{

	private Tile tile;
	private ArrayList<Tile> cell;

	public Cell(ArrayList<Tile> cell){
		this.cell = cell;
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

}
