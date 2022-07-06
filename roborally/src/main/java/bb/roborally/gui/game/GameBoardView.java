package bb.roborally.gui.game;

import bb.roborally.game.board.Board;
import bb.roborally.game.board.Cell;
import bb.roborally.game.tiles.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

import java.io.File;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Objects;

public class GameBoardView {
    VBox gameBoard = new VBox();
    Board board;
    ArrayList<ArrayList<HBox>> fields = new ArrayList<>();
    ArrayList<ArrayList<Cell>> cells = new ArrayList<>();
    public GameBoardView() {

    }

    public void populateBoard(Board board) {
        this.board = board;
        buildGameBoardView();
        int y = 0;
        for (ArrayList<Cell> col: board.getGameMap()) {
            int x = 0;
            for (Cell cell: col) {
                cell.populate();
                fields.get(x).get(y).getChildren().add(cell.getStackPane());
                cells.get(x).set(y, cell);
                cell.setPosition(x, y);
                x += 1;
            }
            y += 1;
        }
    }

    private void buildGameBoardView() {
        int maxX = 13;
        int maxY = 10;
        for (int y = 0; y < maxY; y++) {
            ArrayList<HBox> hboxRow = new ArrayList<>();
            ArrayList<Cell> cellRow = new ArrayList<>();
            HBox row = new HBox();
            for (int x = 0; x < maxX; x++) {
                HBox field = new HBox();
                row.getChildren().add(field);
                hboxRow.add(field);
                cellRow.add(null);
            }
            gameBoard.getChildren().add(row);
            fields.add(hboxRow);
            cells.add(cellRow);
        }
    }

    public VBox getGameBoard() {
        return gameBoard;
    }

    public Cell getCell(int x, int y) {
        return cells.get(x).get(y);
    }
}
