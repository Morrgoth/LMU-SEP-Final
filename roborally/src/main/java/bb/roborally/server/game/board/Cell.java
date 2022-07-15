package bb.roborally.server.game.board;


import bb.roborally.server.game.Position;
import bb.roborally.server.game.tiles.Tile;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import java.util.ArrayList;



/**
 * author Philipp Keyzman
 */
public class Cell {
	public static boolean AMap=false;

	private Position position;
	private ArrayList<Tile> tiles;
	private final StackPane stackPane = new StackPane();
	private Node overlay = null;
	public static final int CELL_WIDTH = 40;
	public static final int CELL_HEIGHT = 40;
	public Cell() {
		this.tiles = new ArrayList<>();
		stackPane.setPrefHeight(CELL_HEIGHT);
		stackPane.setPrefWidth(CELL_WIDTH);
	}

	public Cell(ArrayList<Tile> tiles) {
		this.tiles = tiles;
		stackPane.setPrefHeight(CELL_HEIGHT);
		stackPane.setPrefWidth(CELL_WIDTH);
	}
	public Cell(int x, int y) {
		this.tiles = new ArrayList<>();
		setPosition(x, y);
	}

	public void setAMap(boolean value) {
		this.AMap = value;
	}

	public StackPane getStackPane() {
		return stackPane;
	}
	public void clear() {
		stackPane.getChildren().clear();
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
	public Tile getTile(String type) {
		for (Tile tile: tiles) {
			if (tile.getType().equals(type)) {
				return tile;
			}
		}
		return null;
	}
	public void populate() {
		clear();
		for (Tile tile : tiles) {
			if(getTileCount() > 1 ) {

				//Combination 	Floor (Empty) + StartBoardLetter
				//Combination 	Floor (Empty) + GameBoardletter

				//Combination 	Floor (Empty) + Startpoint
				if (getTile(0).equals(getTile("Empty"))) {
					getTile(tile.getType()).getResource();
				}
				if (getTile(1).equals(getTile("StartPoint"))) {
					getTile(tile.getType()).getResource();
				}

				//Combination	Floor (Empty) + LaserRay
				if (getTile(0).equals(getTile("Empty"))) {
					getTile(tile.getType()).getResource();
				}
				if (getTile(1).equals(getTile("Laser"))){
					getTile(tile.getType()).getResource();
				}

				//Combination 	Floor (Empty) + Antenna
				if (getTile(0).equals(getTile("Empty"))) {
					getTile(tile.getType()).getResource();
				}
				if (getTile(1).equals(getTile("Antenna"))) {
					getTile(tile.getType()).getResource();
				}

				//Combination	Floor (Empty) + Conveyorbelt
				if (getTile(0).equals(getTile("Empty"))) {
					getTile(tile.getType()).getResource();
				}
				if (getTile(1).equals(getTile("ConveyorBelt"))) {
					getTile(tile.getType()).getResource();
				}

				//Combination 	Floor (Empty) + Energyspace
				if (getTile(0).equals(getTile("Empty"))) {
					getTile(tile.getType()).getResource();
				}
				if (getTile(1).equals(getTile("EnergySpace"))) {
					getTile(tile.getType()).getResource();
				}

				//Combination 	Floor (Empty) + Checkpoint
				if (getTile(0).equals(getTile("Empty"))) {
					getTile(tile.getType()).getResource();
				}
				if (getTile(1).equals(getTile("CheckPoint"))) {
					getTile(tile.getType()).getResource();
				}

				//Combination 	Floor (Empty) + Restartpoint
				if (getTile(0).equals(getTile("Empty"))) {
					getTile(tile.getType()).getResource();
				}
				if (getTile(1).equals(getTile("RestartPoint"))) {
					getTile(tile.getType()).getResource();
				}

				//Combination 	Floor (Empty) + Pit
				if (getTile(0).equals(getTile("Empty"))) {
					getTile(tile.getType()).getResource();
				}
				if (getTile(1).equals(getTile("Pit"))) {
					getTile(tile.getType()).getResource();
				}

				//Combination   Floor (Empty) + Gear
				if (getTile(0).equals(getTile("Empty"))) {
					getTile(tile.getType()).getResource();
				}
				if (getTile(1).equals(getTile("Gear"))) {
					getTile(tile.getType()).getResource();
				}
			}

			if (getTileCount() > 2) {

				//Combination Wall + Laser
				if (getTile(0).equals(getTile("Empty"))) {
					getTile(tile.getType()).getResource();
				}
				if (getTile(1).equals(getTile("Wall"))) {
					getTile(tile.getType()).getResource();
				}
				if (getTile(2).equals(getTile("Laser"))) {
					getTile(tile.getType()).getResource();
				}

				//Combination Wall + Laser (without Laser-Head)
				//if necessary - change places of Wall and Laser in tiles-List in Maps
				if (getTile(0).equals(getTile("Empty"))) {
					getTile(tile.getType()).getResource();
				}
				if (getTile(1).equals(getTile("Wall"))) {
					getTile(tile.getType()).getResource();
				}
				if (getTile(2).equals(getTile("Laser"))) {
					getTile(tile.getType()).getResource();
				}
				//Combination Wall + Pushpanel
				if (getTile(0).equals(getTile("Empty"))) {
					getTile(tile.getType()).getResource();
				}
				if (getTile(1).equals(getTile("Wall"))) {
					getTile(tile.getType()).getResource();
				}
				if (getTile(2).equals(getTile("PushPanel"))) {
					getTile(tile.getType()).getResource();
				}
			}
			Image image = new Image(getClass().getResource(tile.getResource()).toExternalForm());
			ImageView imageView = new ImageView(image);
			imageView.setFitHeight(CELL_HEIGHT);
			imageView.setFitWidth(CELL_WIDTH);
			stackPane.getChildren().add(imageView);
		}
	}


	public int getTileCount() {
		return tiles.size();
	}
	public boolean hasTile(String type) {
		for (Tile tile: tiles) {
			if (tile.getType().equals(type)) {
				return true;
			}
		}
		return false;
	}
	public void push(Node node) {
		stackPane.getChildren().add(node);
		overlay = node;
	}
	public void pop() {
		stackPane.getChildren().remove(overlay);
	}

	public Position getPosition() {
		return position;
	}

	public void setPosition(int x, int y) {
		this.position = new Position(x, y);
	}
}
