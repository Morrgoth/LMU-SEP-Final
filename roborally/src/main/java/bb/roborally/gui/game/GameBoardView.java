package bb.roborally.gui.game;

import bb.roborally.game.Orientation;
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

import static bb.roborally.game.Orientation.*;
import static bb.roborally.game.Orientation.RIGHT;

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
        for (ArrayList<ArrayList<Tile>> col : board.getGameMap()) {
            int y = 0;
            for (ArrayList<Tile> cell : col) {
                populateField(y, x, cell);
                y += 1;
            }
            x += 1;
        }
    }

    private void populateField(int x, int y, ArrayList<Tile> tiles) {
        StackPane stackPane = cells.get(x).get(y);
        for (Tile tile : tiles) {
            String path = "";
            if (tile instanceof Antenna) {
               tile.getResource();
            } else if (tile instanceof BlackHole) {
                path = "![](../../../../../resources/TileImages/blackhole.png)";
            } else if (tile instanceof BoardLaser) {
                if (((BoardLaser) tile).getBoardLaserOrientation().equals(Orientation.TOP) &&
                        ((BoardLaser) tile).getCount() == 1) {
                    path = "![](../../../../../resources/TileImages/variants/wall_laser1_top.png)";
                }
                if (((BoardLaser) tile).getBoardLaserOrientation().equals(Orientation.TOP) &&
                        ((BoardLaser) tile).getCount() == 2) {
                    path = "![](../../../../../resources/TileImages/variants/wall_laser2_top.png)";
                }
                if (((BoardLaser) tile).getBoardLaserOrientation().equals(Orientation.TOP) &&
                        ((BoardLaser) tile).getCount() == 3) {
                    path = "![](../../../../../resources/TileImages/variants/wall_laser3_top.png)";
                }
                if (((BoardLaser) tile).getBoardLaserOrientation().equals(Orientation.RIGHT) &&
                        ((BoardLaser) tile).getCount() == 1) {
                    path = "![](../../../../../resources/TileImages/variants/wall_laser1_right.png)";
                }
                if (((BoardLaser) tile).getBoardLaserOrientation().equals(Orientation.RIGHT) &&
                        ((BoardLaser) tile).getCount() == 2) {
                    path = "![](../../../../../resources/TileImages/variants/wall_laser2_right.png)";
                }
                if (((BoardLaser) tile).getBoardLaserOrientation().equals(Orientation.RIGHT) &&
                        ((BoardLaser) tile).getCount() == 3) {
                    path = "![](../../../../../resources/TileImages/variants/wall_laser3_right.png)";
                }
                if (((BoardLaser) tile).getBoardLaserOrientation().equals(Orientation.BOTTOM) &&
                        ((BoardLaser) tile).getCount() == 1) {
                    path = "![](../../../../../resources/TileImages/wall_laser1_bottom.png)";
                }
                if (((BoardLaser) tile).getBoardLaserOrientation().equals(Orientation.BOTTOM) &&
                        ((BoardLaser) tile).getCount() == 2) {
                    path = "![](../../../../../resources/TileImages/variants/wall_laser2_bottom.png)";
                }
                if (((BoardLaser) tile).getBoardLaserOrientation().equals(Orientation.BOTTOM) &&
                        ((BoardLaser) tile).getCount() == 3) {
                    path = "![](../../../../../resources/TileImages/variants/wall_laser3_bottom.png)";
                }
                if (((BoardLaser) tile).getBoardLaserOrientation().equals(Orientation.LEFT) &&
                        ((BoardLaser) tile).getCount() == 1) {
                    path = "![](../../../../../resources/TileImages/variants/wall_laser1_left.png)";
                }
                if (((BoardLaser) tile).getBoardLaserOrientation().equals(Orientation.LEFT) &&
                        ((BoardLaser) tile).getCount() == 2) {
                    path = "![](../../../../../resources/TileImages/wall_laser2_left.png)";
                }
                if (((BoardLaser) tile).getBoardLaserOrientation().equals(Orientation.LEFT) &&
                        ((BoardLaser) tile).getCount() == 3) {
                    path = "![](../../../../../resources/TileImages/wall_laser3_left.png)";
                }

            } else if (tile instanceof CheckPoint) {
                if (tile.getOrientations().equals(Orientation.TOP) &&
                        ((CheckPoint) tile).getNumber() == 1) {
                    path = "![](../../../../../resources/TileImages/checkpoint1_top.png)";
                }
                if (tile.getOrientations().equals(Orientation.TOP) &&
                        ((CheckPoint) tile).getNumber() == 2) {
                    path = "![](../../../../../resources/TileImages/checkpoint2_top.png)";
                }
                if (tile.getOrientations().equals(Orientation.TOP) &&
                        ((CheckPoint) tile).getNumber() == 3) {
                    path = "![](../../../../../resources/TileImages/checkpoint3_top.png)";
                }
                if (tile.getOrientations().equals(Orientation.TOP) &&
                        ((CheckPoint) tile).getNumber() == 4) {
                    path = "![](../../../../../resources/TileImages/checkpoint4_top.png)";
                }
                if (tile.getOrientations().equals(Orientation.TOP) &&
                        ((CheckPoint) tile).getNumber() == 5) {
                    path = "![](../../../../../resources/TileImages/checkpoint5_top.png)";
                }
                if (tile.getOrientations().equals(Orientation.RIGHT) &&
                        ((CheckPoint) tile).getNumber() == 1) {
                    path = "![](../../../../../resources/TileImages/checkpoint6_top.png)";
                }
                if (tile.getOrientations().equals(Orientation.RIGHT) &&
                        ((CheckPoint) tile).getNumber() == 2) {
                    path = "![](../../../../../resources/TileImages/variants/checkpoint1_right.png)";
                }
                if (tile.getOrientations().equals(Orientation.RIGHT) &&
                        ((CheckPoint) tile).getNumber() == 3) {
                    path = "![](../../../../../resources/TileImages/variants/checkpoint2_right.png)";
                }
                if (tile.getOrientations().equals(Orientation.RIGHT) &&
                        ((CheckPoint) tile).getNumber() == 4) {
                    path = "![](../../../../../resources/TileImages/variants/checkpoint3_right.png)";
                }
                if (tile.getOrientations().equals(Orientation.RIGHT) &&
                        ((CheckPoint) tile).getNumber() == 5) {
                    path = "![](../../../../../resources/TileImages/variants/checkpoint4_right.png)";
                }
                if (tile.getOrientations().equals(Orientation.BOTTOM) &&
                        ((CheckPoint) tile).getNumber() == 1) {
                    path = "![](../../../../../resources/TileImages/variants/checkpoint1_bottom.png)";
                }
                if (tile.getOrientations().equals(Orientation.BOTTOM) &&
                        ((CheckPoint) tile).getNumber() == 2) {
                    path = "![](../../../../../resources/TileImages/variants/checkpoint2_bottom.png)";
                }
                if (tile.getOrientations().equals(Orientation.BOTTOM) &&
                        ((CheckPoint) tile).getNumber() == 3) {
                    path = "![](../../../../../resources/TileImages/variants/checkpoint3_bottom.png)";
                }
                if (tile.getOrientations().equals(Orientation.BOTTOM) &&
                        ((CheckPoint) tile).getNumber() == 4) {
                    path = "![](../../../../../resources/TileImages/variants/checkpoint4_bottom.png)";
                }
                if (tile.getOrientations().equals(Orientation.BOTTOM) &&
                        ((CheckPoint) tile).getNumber() == 5) {
                    path = "![](../../../../../resources/TileImages/variants/checkpoint5_bottom.png)";
                }
                if (tile.getOrientations().equals(Orientation.LEFT) &&
                        ((CheckPoint) tile).getNumber() == 1) {
                    path = "![](../../../../../resources/TileImages/variants/checkpoint1_left.png)";
                }
                if (tile.getOrientations().equals(Orientation.LEFT) &&
                        ((CheckPoint) tile).getNumber() == 2) {
                    path = "![](../../../../../resources/TileImages/variants/checkpoint2_left.png)";
                }
                if (tile.getOrientations().equals(Orientation.LEFT) &&
                        ((CheckPoint) tile).getNumber() == 3) {
                    path = "![](../../../../../resources/TileImages/variants/checkpoint3_left.png)";
                }
                if (tile.getOrientations().equals(Orientation.LEFT) &&
                        ((CheckPoint) tile).getNumber() == 4) {
                    path = "![](../../../../../resources/TileImages/variants/checkpoint4_left.png)";
                }
                if (tile.getOrientations().equals(Orientation.LEFT) &&
                        ((CheckPoint) tile).getNumber() == 5) {
                    path = "![](../../../../../resources/TileImages/variants/checkpoint5_left.png)";
                }
            } else if (tile instanceof ConveyorBelt) {
                if (((ConveyorBelt) tile).getBeltOrientation(0).equals(Orientation.TOP) &&
                        ((ConveyorBelt) tile).getBeltOrientation(1).equals(BOTTOM) &&
                        ((ConveyorBelt) tile).getSpeed() == 1) {
                    path = "![](../../../../../resources/TileImages/variants/GreenBeltTop.png)";
                }
                if (((ConveyorBelt) tile).getBeltOrientation(0).equals(Orientation.RIGHT) &&
                        ((ConveyorBelt) tile).getBeltOrientation(1).equals(LEFT) &&
                        ((ConveyorBelt) tile).getSpeed() == 1) {
                    path = "![](../../../../../resources/TileImages/variants/GreenBeltRight.png)";
                }
                if (((ConveyorBelt) tile).getBeltOrientation(0).equals(Orientation.BOTTOM) &&
                        ((ConveyorBelt) tile).getBeltOrientation(1).equals(TOP) &&
                        ((ConveyorBelt) tile).getSpeed() == 1) {
                    path = "![](../../../../../resources/TileImages/green_belt_straight.png)";
                }
                if (((ConveyorBelt) tile).getBeltOrientation(0).equals(Orientation.LEFT) &&
                        ((ConveyorBelt) tile).getBeltOrientation(1).equals(RIGHT) &&
                        ((ConveyorBelt) tile).getSpeed() == 1) {
                    path = "![](../../../../../resources/TileImages/variants/GreenBeltLeft.png)";
                }
                ///
                if (((ConveyorBelt) tile).getBeltOrientation(0).equals(LEFT) &&
                        ((ConveyorBelt) tile).getBeltOrientation(1).equals(BOTTOM) &&
                        ((ConveyorBelt) tile).getSpeed() == 1) {
                    path = "![](../../../../../resources/TileImages/variants/green_belt_counter_clockwise_left_bottom.png)";
                }

                if (((ConveyorBelt) tile).getBeltOrientation(0).equals(TOP) &&
                        ((ConveyorBelt) tile).getBeltOrientation(1).equals(RIGHT) &&
                        ((ConveyorBelt) tile).getSpeed() == 1) {
                    path = "![](../../../../../resources/TileImages/variants/green_belt_clockwise_right_top.png)";
                }
                if (((ConveyorBelt) tile).getBeltOrientation(0).equals(BOTTOM) &&
                        ((ConveyorBelt) tile).getBeltOrientation(1).equals(RIGHT) &&
                        ((ConveyorBelt) tile).getSpeed() == 1) {
                    path = "![](../../../../../resources/TileImages/variants/green_belt_counter_clockwise_bottom_right.png)";
                }
                if (((ConveyorBelt) tile).getBeltOrientation(0).equals(LEFT) &&
                        ((ConveyorBelt) tile).getBeltOrientation(1).equals(TOP) &&
                        ((ConveyorBelt) tile).getSpeed() == 1) {
                    path = "![](../../../../../resources/TileImages/variants/green_belt_clockwise_left_bottom.png)";
                }
                if (((ConveyorBelt) tile).getBeltOrientation(0).equals(RIGHT) &&
                        ((ConveyorBelt) tile).getBeltOrientation(1).equals(BOTTOM) &&
                        ((ConveyorBelt) tile).getSpeed() == 1) {
                    path = "![](../../../../../resources/TileImages/green_belt_clockwise_bottom_left.png)";
                }
                if (((ConveyorBelt) tile).getBeltOrientation(0).equals(TOP) &&
                        ((ConveyorBelt) tile).getBeltOrientation(1).equals(LEFT) &&
                        ((ConveyorBelt) tile).getSpeed() == 1) {
                    path = "![](../../../../../resources/TileImages/variants/green_belt_clockwise_right_top.png)";
                }
                if (((ConveyorBelt) tile).getBeltOrientation(0).equals(BOTTOM) &&
                        ((ConveyorBelt) tile).getBeltOrientation(1).equals(LEFT) &&
                        ((ConveyorBelt) tile).getSpeed() == 1) {
                    path = "![](../../../../../resources/TileImages/variants/green_belt_clockwise_bottom_left.png)";
                }
                if (((ConveyorBelt) tile).getBeltOrientation(0).equals(RIGHT) &&
                        ((ConveyorBelt) tile).getBeltOrientation(1).equals(TOP) &&
                        ((ConveyorBelt) tile).getSpeed() == 1) {
                    path = "![](../../../../../resources/TileImages/green_belt_counter_clockwise_bottom_right.png)";
                }

                if (((ConveyorBelt) tile).getBeltOrientation(0).equals(Orientation.TOP) &&
                        ((ConveyorBelt) tile).getBeltOrientation(1).equals(BOTTOM) &&
                        ((ConveyorBelt) tile).getSpeed() == 2) {
                    path = "![](../../../../../resources/TileImages/variants/blue_belt_straight_top.png)";
                }
                if (((ConveyorBelt) tile).getBeltOrientation(0).equals(Orientation.RIGHT) &&
                        ((ConveyorBelt) tile).getBeltOrientation(1).equals(LEFT) &&
                        ((ConveyorBelt) tile).getSpeed() == 2) {
                    path = "![](../../../../../resources/TileImages/variants/BlueBeltRight.png)";
                }
                if (((ConveyorBelt) tile).getBeltOrientation(0).equals(Orientation.BOTTOM) &&
                        ((ConveyorBelt) tile).getBeltOrientation(1).equals(TOP) &&
                        ((ConveyorBelt) tile).getSpeed() == 2) {
                    path = "![](../../../../../resources/TileImages/blue_belt_straight.png)";
                }
                if (((ConveyorBelt) tile).getBeltOrientation(0).equals(Orientation.LEFT) &&
                        ((ConveyorBelt) tile).getBeltOrientation(1).equals(RIGHT) &&
                        ((ConveyorBelt) tile).getSpeed() == 2) {
                    path = "![](../../../../../resources/TileImages/variants/BlueBeltLeft.png)";
                }

                if (((ConveyorBelt) tile).getBeltOrientation(0).equals(LEFT) &&
                        ((ConveyorBelt) tile).getBeltOrientation(1).equals(BOTTOM) &&
                        ((ConveyorBelt) tile).getSpeed() == 2) {
                    path = "![](../../../../../resources/TileImages/blue_belt_clockwise_left_top.png)";
                }
                if (((ConveyorBelt) tile).getBeltOrientation(0).equals(TOP) &&
                        ((ConveyorBelt) tile).getBeltOrientation(1).equals(RIGHT) &&
                        ((ConveyorBelt) tile).getSpeed() == 2) {
                    path = "![](../../../../../resources/TileImages/variants/blue_belt_clockwise_right_bottom.png)";
                }
                if (((ConveyorBelt) tile).getBeltOrientation(0).equals(BOTTOM) &&
                        ((ConveyorBelt) tile).getBeltOrientation(1).equals(RIGHT) &&
                        ((ConveyorBelt) tile).getSpeed() == 2) {
                    path = "![](../../../../../resources/TileImages/variants/blue_belt_counter_clockwise_right_bottom.png)";
                }
                if (((ConveyorBelt) tile).getBeltOrientation(0).equals(LEFT) &&
                        ((ConveyorBelt) tile).getBeltOrientation(1).equals(TOP) &&
                        ((ConveyorBelt) tile).getSpeed() == 2) {
                    path = "![](../../../../../resources/TileImages/blue_belt_clockwise_left_top.png)";
                }
                if (((ConveyorBelt) tile).getBeltOrientation(0).equals(RIGHT) &&
                        ((ConveyorBelt) tile).getBeltOrientation(1).equals(BOTTOM) &&
                        ((ConveyorBelt) tile).getSpeed() == 2) {
                    path = "![](../../../../../resources/TileImages/variants/blue_belt_clockwise_right_bottom.png)";
                }
                if (((ConveyorBelt) tile).getBeltOrientation(0).equals(TOP) &&
                        ((ConveyorBelt) tile).getBeltOrientation(1).equals(LEFT) &&
                        ((ConveyorBelt) tile).getSpeed() == 2) {
                    path = "![](../../../../../resources/TileImages/variants/blue_belt_counter_clockwise_top_left.png)";
                }
                if (((ConveyorBelt) tile).getBeltOrientation(0).equals(BOTTOM) &&
                        ((ConveyorBelt) tile).getBeltOrientation(1).equals(LEFT) &&
                        ((ConveyorBelt) tile).getSpeed() == 2) {
                    path = "![](../../../../../resources/TileImages/variants/blue_belt_clockwise_bottom_left.png)";
                }
                if (((ConveyorBelt) tile).getBeltOrientation(0).equals(RIGHT) &&
                        ((ConveyorBelt) tile).getBeltOrientation(1).equals(TOP) &&
                        ((ConveyorBelt) tile).getSpeed() == 2) {
                    path = "![](../../../../../resources/TileImages/variants/blue_belt_counter_clockwise_right_top.png)";
                }

                ////
                if(((ConveyorBelt) tile).getBeltOrientation(0).equals(LEFT) &&
                        ((ConveyorBelt) tile).getBeltOrientation(1).equals(BOTTOM) &&
                        ((ConveyorBelt) tile).getBeltOrientation(2).equals(RIGHT) &&
                        ((ConveyorBelt) tile).getSpeed() == 2){
                    path = "![](../../../../../resources/TileImages/variants/RB left.png)";
                }
                if(((ConveyorBelt) tile).getBeltOrientation(0).equals(TOP) &&
                        ((ConveyorBelt) tile).getBeltOrientation(1).equals(RIGHT) &&
                        ((ConveyorBelt) tile).getBeltOrientation(2).equals(BOTTOM) &&
                        ((ConveyorBelt) tile).getSpeed() == 2){
                    path = "![](../../../../../resources/TileImages/variants/RB top2.png)";
                }
                if(((ConveyorBelt) tile).getBeltOrientation(0).equals(BOTTOM) &&
                        ((ConveyorBelt) tile).getBeltOrientation(1).equals(RIGHT) &&
                        ((ConveyorBelt) tile).getBeltOrientation(2).equals(TOP) &&
                        ((ConveyorBelt) tile).getSpeed() == 2){
                    path = "![](../../../../../resources/TileImages/variants/RB Bottom.png)";
                }
                if(((ConveyorBelt) tile).getBeltOrientation(0).equals(LEFT) &&
                        ((ConveyorBelt) tile).getBeltOrientation(1).equals(TOP) &&
                        ((ConveyorBelt) tile).getBeltOrientation(2).equals(RIGHT) &&
                        ((ConveyorBelt) tile).getSpeed() == 2){
                    path = "![](../../../../../resources/TileImages/variants/RB left2.png)";
                }
                if(((ConveyorBelt) tile).getBeltOrientation(0).equals(RIGHT) &&
                        ((ConveyorBelt) tile).getBeltOrientation(1).equals(BOTTOM) &&
                        ((ConveyorBelt) tile).getBeltOrientation(2).equals(LEFT) &&
                        ((ConveyorBelt) tile).getSpeed() == 2){
                    path = "![](../../../../../resources/TileImages/variants/RB right2.png)";
                }
                if(((ConveyorBelt) tile).getBeltOrientation(0).equals(TOP) &&
                        ((ConveyorBelt) tile).getBeltOrientation(1).equals(LEFT) &&
                        ((ConveyorBelt) tile).getBeltOrientation(2).equals(BOTTOM) &&
                        ((ConveyorBelt) tile).getSpeed() == 2){
                    path = "![](../../../../../resources/TileImages/variants/RB top.png)";
                }
                if(((ConveyorBelt) tile).getBeltOrientation(0).equals(BOTTOM) &&
                        ((ConveyorBelt) tile).getBeltOrientation(1).equals(LEFT) &&
                        ((ConveyorBelt) tile).getBeltOrientation(2).equals(TOP) &&
                        ((ConveyorBelt) tile).getSpeed() == 2){
                    path = "![](../../../../../resources/TileImages/variants/RB Bottom 2.png)";
                }
                if(((ConveyorBelt) tile).getBeltOrientation(0).equals(RIGHT) &&
                        ((ConveyorBelt) tile).getBeltOrientation(1).equals(TOP) &&
                        ((ConveyorBelt) tile).getBeltOrientation(2).equals(LEFT) &&
                        ((ConveyorBelt) tile).getSpeed() == 2){
                    path = "![](../../../../../resources/TileImages/variants/RB right.png)";
                }

            } else if (tile instanceof EnergySpace) {
                    if (tile.getOrientations().equals(Orientation.TOP) &&
                            ((EnergySpace) tile).getRemainedEnergyCube() == 1) {
                        path = "![](../../../../../resources/TileImages/energycube_not_activated.png)";
                    }
                    if (tile.getOrientations().equals(Orientation.TOP) &&
                            ((EnergySpace) tile).getRemainedEnergyCube() == 0) {
                        path = "![](../../../../../resources/TileImages/energycube_activated.png)";
                    }
                    if (tile.getOrientations().equals(Orientation.RIGHT) &&
                            ((EnergySpace) tile).getRemainedEnergyCube() == 1) {
                        path = "![](../../../../../resources/TileImages/variants/energycube_not_activated_horizontal.png)";
                    }
                    if (tile.getOrientations().equals(Orientation.RIGHT) &&
                            ((EnergySpace) tile).getRemainedEnergyCube() == 0) {
                        path = "![](../../../../../resources/TileImages/variants/energycube_activated_horizontal.png)";
                    }
                    if (tile.getOrientations().equals(Orientation.BOTTOM) &&
                            ((EnergySpace) tile).getRemainedEnergyCube() == 1) {
                        path = "![](../../../../../resources/TileImages/energycube_not_activated.png)";
                    }
                    if (tile.getOrientations().equals(Orientation.BOTTOM) &&
                            ((EnergySpace) tile).getRemainedEnergyCube() == 0) {
                        path = "![](../../../../../resources/TileImages/energycube_activated.png)";
                    }
                    if (tile.getOrientations().equals(Orientation.LEFT) &&
                            ((EnergySpace) tile).getRemainedEnergyCube() == 1) {
                        path = "![](../../../../../resources/TileImages/variants/energycube_not_activated_horizontal.png)";
                    }
                    if (tile.getOrientations().equals(Orientation.LEFT) &&
                            ((EnergySpace) tile).getRemainedEnergyCube() == 0) {
                        path = "![](../../../../../resources/TileImages/variants/energycube_activated_horizontal.png)";
                    }

                } else if (tile instanceof Floor) {
                    path = "![](../../../../../resources/TileImages/floor.png)";
                } else if (tile instanceof Gear) {
                    tile.getResource();
                } else if (tile instanceof PushPanel) {
                    tile.getResource();
                } else if (tile instanceof RebootPoint) {
                    tile.getResource();
                } else if (tile instanceof StartPoint) {
                    tile.getResource();
                } else if (tile instanceof Wall) {
                    tile.getResource();
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

