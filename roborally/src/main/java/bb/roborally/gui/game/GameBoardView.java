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
    ArrayList<ArrayList<StackPane>> cells = new ArrayList<>();

    public GameBoardView() {
        buildGameBoardView();
    }

    private void buildGameBoardView() {
        int cols = 13;
        int rows = 10;
        for (int i = 0; i < rows; i++) {
            ArrayList<StackPane> cellsRow = new ArrayList<>();
            HBox row = new HBox();
            for (int j = 0; j < cols; j++) {
                StackPane stackPane = new StackPane();
                stackPane.setPrefHeight(20);
                stackPane.setPrefWidth(20);
                row.getChildren().add(stackPane);
                cellsRow.add(stackPane);
            }
            gameBoard.getChildren().add(row);
            cells.add(cellsRow);
        }
    }

    public VBox getGameBoard() {
        return gameBoard;
    }

    public void populateBoard(Board board) {
        int x = 0;
        for (ArrayList<Cell> col: board.getGameMap()) {
            int y = 0;
            for (Cell cell: col) {
                populateField(y, x, cell);
                y += 1;
            }
            x += 1;
        }
    }

    private void populateField(int x, int y, Cell cell) {
        StackPane stackPane = cells.get(x).get(y);
        for (Tile tile : cell.getTiles()) {
            if (tile.getResource() != null || !tile.getResource().equals("")) {
                Image image = new Image(getClass().getResource(tile.getResource()).toExternalForm());
                ImageView imageView = new ImageView(image);
                imageView.setFitHeight(40);
                imageView.setFitWidth(40);
                stackPane.getChildren().add(imageView);
            }

        }
    }
}
