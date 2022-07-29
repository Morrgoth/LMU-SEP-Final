package bb.roborally.client.board;

import bb.roborally.protocol.map.Cell;
import bb.roborally.protocol.map.tiles.Tile;
import javafx.scene.Node;
import javafx.scene.layout.StackPane;

/**
 *
 * @author Bence Ament
 */
public class CellView {

    public static final int CELL_WIDTH = 40;
    public static final int CELL_HEIGHT = 40;
    private final Cell cell;
    private final StackPane view = new StackPane();
    private Node overlay;
    private final Position position = new Position();
    public CellView(Cell cell) {
        this.cell = cell;
        for (Tile tile: cell.getTiles()) {
            TileView tileView = new TileView(tile);
            view.getChildren().add(tileView.getView());
        }
    }

    public StackPane getView() {
        return view;
    }

    public void push(Node node) {
        view.getChildren().add(node);
        overlay = node;
    }

    public void pop() {
        view.getChildren().remove(overlay);
    }

    public void setPosition(int x, int y) {
        position.set(x, y);
    }

    public Position getPosition() {
        return position;
    }

    public Cell getCell() {
        return cell;
    }
}
