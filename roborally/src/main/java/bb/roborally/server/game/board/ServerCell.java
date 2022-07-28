package bb.roborally.server.game.board;


import bb.roborally.protocol.map.Cell;
import bb.roborally.protocol.map.tiles.Tile;
import bb.roborally.protocol.Position;

import java.util.ArrayList;



/**
 * author Philipp Keyzman
 */
public class ServerCell {
	private Cell cell;
	private Position position;

	public ServerCell(Cell cell) {
		this.cell = cell;
	}

	public ServerCell(Cell cell, int x, int y) {
		this.cell = cell;
		this.position = new Position(x, y);
	}
	public ServerCell(int x, int y) {
		setPosition(x, y);
	}

	public ArrayList<Tile> getTiles() {
		return cell.getTiles();
	}

	// TODO: remove, not necessary
	public void setTiles(ArrayList<Tile> tiles) {
		this.cell.setTiles(tiles);
	}
	// TODO: remove, not necessary
	public void addTile(Tile tile) {
		cell.addTile(tile);
	}
	public Tile getTile(int index) {
		return cell.getTile(index);
	}
	public Tile getTile(String type) {
		for (Tile tile: cell.getTiles()) {
			if (tile.getType().equals(type)) {
				return tile;
			}
		}
		return null;
	}

	public boolean hasTile(String type) {
		for (Tile tile: cell.getTiles()) {
			if (tile.getType().equals(type)) {
				return true;
			}
		}
		return false;
	}

	public Position getPosition() {
		return position;
	}

	public void setPosition(int x, int y) {
		this.position = new Position(x, y);
	}
}
