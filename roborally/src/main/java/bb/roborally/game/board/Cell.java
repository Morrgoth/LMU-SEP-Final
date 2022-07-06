package bb.roborally.game.board;

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
	private final int HEIGHT = 40;
	private final int WIDTH = 40;
	private ArrayList<Tile> tiles;
	private final StackPane stackPane = new StackPane();
	private Node overlay = null;
	public Cell() {
		this.tiles = new ArrayList<>();
		stackPane.setPrefHeight(HEIGHT);
		stackPane.setPrefWidth(WIDTH);
	}
	public Cell(ArrayList<Tile> tiles) {
		this.tiles = tiles;
		stackPane.setPrefHeight(HEIGHT);
		stackPane.setPrefWidth(WIDTH);
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
	public void populate() {
		clear();
		for (Tile tile: tiles) {
			Image image = new Image(getClass().getResource(tile.getResource()).toExternalForm());
			ImageView imageView = new ImageView(image);
			imageView.setFitHeight(HEIGHT);
			imageView.setFitWidth(WIDTH);
			stackPane.getChildren().add(imageView);
		}
	}
	public int getTileCount() {
		return tiles.size();
	}
	public void push(Node node) {
		stackPane.getChildren().add(node);
		overlay = node;
	}
	public void pop() {
		stackPane.getChildren().remove(overlay);
	}
}
