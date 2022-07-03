package bb.roborally.gui.game;

import bb.roborally.game.board.Board;
import bb.roborally.game.tiles.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import java.io.File;
import java.net.URISyntaxException;
import java.util.ArrayList;

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
        for (ArrayList<ArrayList<Tile>> col: board.getGameMap()) {
            int y = 0;
            for (ArrayList<Tile> cell: col) {
                populateField(y, x, cell);
                y += 1;
            }
            x += 1;
        }
    }

    private void populateField(int x, int y, ArrayList<Tile> tiles) {
        StackPane stackPane = cells.get(x).get(y);
        for (Tile tile: tiles) {
            String path = "";
             if (tile instanceof Antenna) {
                 path = "/TileImages/antenna.png";
             } else if (tile instanceof BlackHole) {
                 path = "/TileImages/wall.png";
             } else if (tile instanceof BoardLaser) {
                 path = "/TileImages/wall_laser1.png";
             } else if (tile instanceof CheckPoint) {
                 path = "/TileImages/checkpoint1.png";
             } else if (tile instanceof ConveyorBelt) {
                 path = "/TileImages/green_belt_straight.png";
             } else if (tile instanceof EnergySpace) {
                 path = "/TileImages/energycube_activated.png";
             } else if (tile instanceof Floor) {
                 path = "/TileImages/floor.png";
             } else if (tile instanceof Gear) {
                 path = "/TileImages/wall.png";
             } else if (tile instanceof PushPanel) {
                 path = "/TileImages/wall.png";
             } else if (tile instanceof RebootPoint) {
                 path = "/TileImages/reboot.png";
             } else if (tile instanceof StartPoint) {
                 path = "/TileImages/starting_point.png";
             } else if (tile instanceof Wall) {
                 path = "/TileImages/wall.png";
             }

            if (!path.equals("")) {
                Image image = new Image(getClass().getResource(path).toExternalForm());
                ImageView imageView = new ImageView(image);
                imageView.setFitHeight(40);
                imageView.setFitWidth(40);
                stackPane.getChildren().add(imageView);
            }

        }
    }
}
