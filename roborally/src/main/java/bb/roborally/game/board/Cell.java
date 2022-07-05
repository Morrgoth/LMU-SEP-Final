package bb.roborally.game.board;

import bb.roborally.data.messages.Envelope;
import bb.roborally.data.messages.Message;
import bb.roborally.game.Orientation;
import bb.roborally.game.tiles.*;

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
	
	public String getResource(){
		String path = "";
		for(Tile tile: cell){
			if(cell.indexOf(tile) == 0 && tile instanceof Empty){
				path = "/TileImages/floor.png";
			}
			if(cell.indexOf(tile) == 1 && tile instanceof Wall){
				if (tile.getOrientations().get(0).equals(Orientation.TOP)) {
					path = "/TileImages/wall_top.png";
				}
				if (tile.getOrientations().get(0).equals(Orientation.RIGHT)) {
					path = "/TileImages/variants/wall_right.png";
				}
				if (tile.getOrientations().get(0).equals(Orientation.BOTTOM)) {
					path = "/TileImages/variants/wall_bottom.png";
				}
				if (tile.getOrientations().get(0).equals(Orientation.LEFT)) {
					path = "/TileImages/variants/wall_left.png";
				}
			}
			if(cell.indexOf(tile) == 2 && tile instanceof Laser) {
				if (tile.getOrientations().get(0).equals(Orientation.TOP) &&
						((Laser) tile).getCount() == 1) {
					path = "/TileImages/variants/wall_laser1_top.png";
				}
				if (tile.getOrientations().get(0).equals(Orientation.RIGHT) &&
						((Laser) tile).getCount() == 1) {
					path = "/TileImages/variants/wall_laser1_right.png";
				}
				if (tile.getOrientations().get(0).equals(Orientation.BOTTOM) &&
						((Laser) tile).getCount() == 1) {
					path = "/TileImages/wall_laser1_bottom.png";
				}
				if (tile.getOrientations().get(0).equals(Orientation.LEFT) &&
						((Laser) tile).getCount() == 1) {
					path = "/TileImages/variants/wall_laser1_left.png";
				}
			}
			if(cell.indexOf(tile) == 2 && tile instanceof PushPanel){
				if (tile.getOrientations().get(0).equals(Orientation.TOP) &&
					    ((PushPanel) tile).getRegisters().contains(1) &&
						((PushPanel) tile).getRegisters().contains(3) &&
						((PushPanel) tile).getRegisters().contains(5)){
					path = "/TileImages/variants/pushpanel135_top.png";
				}
				if (tile.getOrientations().get(0).equals(Orientation.RIGHT) &&
						((PushPanel) tile).getRegisters().contains(1) &&
						((PushPanel) tile).getRegisters().contains(3) &&
						((PushPanel) tile).getRegisters().contains(5)) {
						path = "/TileImages/variants/pushpanel135_right.png";
				}
				if (tile.getOrientations().get(0).equals(Orientation.BOTTOM) &&
						((PushPanel) tile).getRegisters().contains(1) &&
						((PushPanel) tile).getRegisters().contains(3) &&
						((PushPanel) tile).getRegisters().contains(5)) {
						path = "/TileImages/variants/pushpanel135_bottom.png";
				}
				if (tile.getOrientations().get(0).equals(Orientation.LEFT) &&
						((PushPanel) tile).getRegisters().contains(1) &&
						((PushPanel) tile).getRegisters().contains(3) &&
						((PushPanel) tile).getRegisters().contains(5)) {
						path = "/TileImages/pushpanel135_left.png";
				}
				if (tile.getOrientations().get(0).equals(Orientation.TOP) &&
						((PushPanel) tile).getRegisters().contains(2) &&
						((PushPanel) tile).getRegisters().contains(4)) {
							path = "/TileImages/variants/pushpanel24_top.png";
				}
				if (tile.getOrientations().get(0).equals(Orientation.RIGHT) &&
						((PushPanel) tile).getRegisters().contains(2) &&
						((PushPanel) tile).getRegisters().contains(4)) {
						path = "/TileImages/variants/pushpanel24_right.png";
					}
				if (tile.getOrientations().get(0).equals(Orientation.BOTTOM) &&
						((PushPanel) tile).getRegisters().contains(2) &&
						((PushPanel) tile).getRegisters().contains(4)) {
						path = "/TileImages/variants/pushpanel24_bottom.png";
				}
				if (tile.getOrientations().get(0).equals(Orientation.LEFT) &&
						((PushPanel) tile).getRegisters().contains(2) &&
						((PushPanel) tile).getRegisters().contains(4)) {
						path = "/TileImages/pushpanel24_left.png";
						}
					}
				}
		return path;
	}

}


