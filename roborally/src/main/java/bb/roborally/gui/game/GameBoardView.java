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
    public GameBoardView() {

    }

    public void populateBoard(Board board) {
        buildGameBoardView();
        int x = 0;
        for (ArrayList<Cell> col: board.getGameMap()) {
            int y = 0;
            for (Cell cell: col) {
                cell.populate();
                fields.get(y).get(x).getChildren().add(cell.getStackPane());
                y += 1;
            }
            x += 1;
        }
    }

    private void buildGameBoardView() {
        int cols = 13;
        int rows = 10;
        for (int i = 0; i < rows; i++) {
            ArrayList<HBox> cellsRow = new ArrayList<>();
            HBox row = new HBox();
            for (int j = 0; j < cols; j++) {
                HBox field = new HBox();
                row.getChildren().add(field);
                cellsRow.add(field);
            }
            gameBoard.getChildren().add(row);
            fields.add(cellsRow);
        }
    }

    public VBox getGameBoard() {
        return gameBoard;
    }
}
