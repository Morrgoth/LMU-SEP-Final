package bb.roborally.game.board;

import bb.roborally.game.Position;
import bb.roborally.game.tiles.Tile;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;

import java.util.ArrayList;


/**
 * author Philipp Keyzman
 */
public class Cell {
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
			if(tiles.size() > 1 ) {

				//Combination 	Floor (Empty) + Startpoint
				if (getTile(0).equals(getTile("Empty"))) {
					getTile(tile.getType()).getResource();
				}
				if (getTile(1).equals(getTile("Startpoint"))) {
					getTile(tile.getType()).getResource();
				}

				//Combination 	Floor (Empty) + Antenna
				if (getTile(0).equals(getTile("Empty"))) {
					getTile(tile.getType()).getResource();
				}
				if (getTile(1).equals(getTile("Antenna"))) {
					getTile(tile.getType()).getResource();
				}

				//Comnbination	Floor (Empty) + Conveyorbelt
				if (getTile(0).equals(getTile("Empty"))) {
					getTile(tile.getType()).getResource();
				}
				if (getTile(1).equals(getTile("Conveyorbelt"))) {
					getTile(tile.getType()).getResource();
				}

				//Combination 	Floor (Empty) + Energyspace
				if (getTile(0).equals(getTile("Empty"))) {
					getTile(tile.getType()).getResource();
				}
				if (getTile(1).equals(getTile("Energyspace"))) {
					getTile(tile.getType()).getResource();
				}

				//Combination 	Floor (Empty) + Checkpoint
				if (getTile(0).equals(getTile("Empty"))) {
					getTile(tile.getType()).getResource();
				}
				if (getTile(1).equals(getTile("Checkpoint"))) {
					getTile(tile.getType()).getResource();
				}

				//Combination 	Floor (Empty) + Restartpoint
				if (getTile(0).equals(getTile("Empty"))) {
					getTile(tile.getType()).getResource();
				}
				if (getTile(1).equals(getTile("Restartpoint"))) {
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

			if (tiles.size() > 2) {

				//Combination Wall + Laser
				if (getTile(0).equals(getTile("Empty"))) {
					getTile("Empty").getResource();
				}
				if (getTile(1).equals(getTile("Wall"))) {
					getTile("Wall").getResource();
				}
				if (getTile(2).equals(getTile("Laser"))) {
					getTile("Laser").getResource();
				}

				//Combination Floor (Empty) + Laser_Ray
				if (getTile(0).equals(getTile("Empty"))) {
					getTile("Empty").getResource();
				}
				if (getTile(1).equals(getTile("Wall"))) {
					getTile("Wall").getResource();
				}
				if (getTile(2).equals(getTile("Laser"))) {
					getTile("Laser").getResource();
				}

				//Combination Wall + Pushpanel
				if (getTile(0).equals(getTile("Empty"))) {
					getTile("Empty").getResource();
				}
				if (getTile(1).equals(getTile("Wall"))) {
					getTile("Wall").getResource();
				}
				if (getTile(2).equals(getTile("Pushpanel"))) {
					getTile("Pushpanel").getResource();
				}
			}

				Image image = new Image((getClass().getResource(tile.getResource())).toExternalForm());
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
