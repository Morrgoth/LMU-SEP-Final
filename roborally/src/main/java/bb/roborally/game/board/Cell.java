package bb.roborally.game.board;

import bb.roborally.game.tiles.Tile;

import java.util.ArrayList;

/**
 * author Philipp Keyzman
 */
public class Cell {
	private ArrayList<Tile> tiles;
	public Cell() {
		this.tiles = new ArrayList<>();
	}
	public Cell(ArrayList<Tile> tiles){
		this.tiles = tiles;
	}
	public ArrayList<Tile> getTiles() {
		return tiles;
	}
	public void setTiles(ArrayList<Tile> tiles) {
		this.tiles = tiles;
	}
	public void addTile(Tile tile) {
		tiles.add(tile);
	}

	public Tile getTile(int index) {
		return tiles.get(index);
	}
}
