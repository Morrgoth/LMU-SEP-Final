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
                 if(((Antenna) tile).getAntennaPosition().equals(Orientation.TOP)){
                     path = "/TileImages/antenna.png";
                 }
                 if(((Antenna) tile).getAntennaPosition().equals(Orientation.RIGHT)){
                     path = "/TileImages/antenna.png";
                 }
                 if(((Antenna) tile).getAntennaPosition().equals(Orientation.BOTTOM)){
                     path = "/TileImages/antenna.png";
                 }
                 if(((Antenna) tile).getAntennaPosition().equals(Orientation.LEFT)){
                     path = "/TileImages/antenna.png";
                 }
             } else if (tile instanceof BlackHole) {
                 path = "/TileImages/wall.png";
             } else if (tile instanceof BoardLaser ) {
                 if(((BoardLaser) tile).getBoardLaserOrientation().equals(Orientation.TOP) &&
                 ((BoardLaser) tile).getCount() == 1){
                     path = "/TileImages/wall_laser1.png";
                 }
                 if(((BoardLaser) tile).getBoardLaserOrientation().equals(Orientation.TOP) &&
                         ((BoardLaser) tile).getCount() == 2){
                     path = "/TileImages/wall_laser1.png";
                 }
                 if(((BoardLaser) tile).getBoardLaserOrientation().equals(Orientation.TOP) &&
                         ((BoardLaser) tile).getCount() == 3){
                     path = "/TileImages/wall_laser1.png";
                 }
                 if(((BoardLaser) tile).getBoardLaserOrientation().equals(Orientation.RIGHT) &&
                         ((BoardLaser) tile).getCount() == 1){
                     path = "/TileImages/wall_laser1.png";
                 }
                 if(((BoardLaser) tile).getBoardLaserOrientation().equals(Orientation.RIGHT) &&
                         ((BoardLaser) tile).getCount() == 2){
                     path = "/TileImages/wall_laser1.png";
                 }
                 if(((BoardLaser) tile).getBoardLaserOrientation().equals(Orientation.RIGHT) &&
                         ((BoardLaser) tile).getCount() == 3){
                     path = "/TileImages/wall_laser1.png";
                 }
                 if(((BoardLaser) tile).getBoardLaserOrientation().equals(Orientation.BOTTOM) &&
                         ((BoardLaser) tile).getCount() == 1){
                     path = "/TileImages/wall_laser1.png";
                 }
                 if(((BoardLaser) tile).getBoardLaserOrientation().equals(Orientation.BOTTOM) &&
                         ((BoardLaser) tile).getCount() == 2){
                     path = "/TileImages/wall_laser1.png";
                 }
                 if(((BoardLaser) tile).getBoardLaserOrientation().equals(Orientation.BOTTOM) &&
                         ((BoardLaser) tile).getCount() == 3){
                     path = "/TileImages/wall_laser1.png";
                 }
                 if(((BoardLaser) tile).getBoardLaserOrientation().equals(Orientation.LEFT) &&
                         ((BoardLaser) tile).getCount() == 1){
                     path = "/TileImages/wall_laser1.png";
                 }
                 if(((BoardLaser) tile).getBoardLaserOrientation().equals(Orientation.LEFT) &&
                         ((BoardLaser) tile).getCount() == 2){
                     path = "/TileImages/wall_laser1.png";
                 }
                 if(((BoardLaser) tile).getBoardLaserOrientation().equals(Orientation.LEFT) &&
                         ((BoardLaser) tile).getCount() == 3){
                     path = "/TileImages/wall_laser1.png";
                 }

             } else if (tile instanceof CheckPoint) {
                 if(tile.getOrientations().equals(Orientation.TOP) &&
                 ((CheckPoint) tile).getNumber() == 1){
                     path = "/TileImages/checkpoint1.png";
                 }
                 if(tile.getOrientations().equals(Orientation.TOP) &&
                         ((CheckPoint) tile).getNumber() == 2){
                     path = "/TileImages/checkpoint1.png";
                 }
                 if(tile.getOrientations().equals(Orientation.TOP) &&
                         ((CheckPoint) tile).getNumber() == 3){
                     path = "/TileImages/checkpoint1.png";
                 }
                 if(tile.getOrientations().equals(Orientation.TOP) &&
                         ((CheckPoint) tile).getNumber() == 4){
                     path = "/TileImages/checkpoint1.png";
                 }
                 if(tile.getOrientations().equals(Orientation.TOP) &&
                         ((CheckPoint) tile).getNumber() == 5){
                     path = "/TileImages/checkpoint1.png";
                 }
                 if(tile.getOrientations().equals(Orientation.RIGHT) &&
                         ((CheckPoint) tile).getNumber() == 1){
                     path = "/TileImages/checkpoint1.png";
                 }
                 if(tile.getOrientations().equals(Orientation.RIGHT) &&
                         ((CheckPoint) tile).getNumber() == 2){
                     path = "/TileImages/checkpoint1.png";
                 }
                 if(tile.getOrientations().equals(Orientation.RIGHT) &&
                         ((CheckPoint) tile).getNumber() == 3){
                     path = "/TileImages/checkpoint1.png";
                 }
                 if(tile.getOrientations().equals(Orientation.RIGHT) &&
                         ((CheckPoint) tile).getNumber() == 4){
                     path = "/TileImages/checkpoint1.png";
                 }
                 if(tile.getOrientations().equals(Orientation.RIGHT) &&
                         ((CheckPoint) tile).getNumber() == 5){
                     path = "/TileImages/checkpoint1.png";
                 }
                 if(tile.getOrientations().equals(Orientation.BOTTOM) &&
                         ((CheckPoint) tile).getNumber() == 1){
                     path = "/TileImages/checkpoint1.png";
                 }
                 if(tile.getOrientations().equals(Orientation.BOTTOM) &&
                         ((CheckPoint) tile).getNumber() == 2){
                     path = "/TileImages/checkpoint1.png";
                 }
                 if(tile.getOrientations().equals(Orientation.BOTTOM) &&
                         ((CheckPoint) tile).getNumber() == 3){
                     path = "/TileImages/checkpoint1.png";
                 }
                 if(tile.getOrientations().equals(Orientation.BOTTOM) &&
                         ((CheckPoint) tile).getNumber() == 4){
                     path = "/TileImages/checkpoint1.png";
                 }
                 if(tile.getOrientations().equals(Orientation.BOTTOM) &&
                         ((CheckPoint) tile).getNumber() == 5){
                     path = "/TileImages/checkpoint1.png";
                 }
                 if(tile.getOrientations().equals(Orientation.LEFT) &&
                         ((CheckPoint) tile).getNumber() == 1){
                     path = "/TileImages/checkpoint1.png";
                 }
                 if(tile.getOrientations().equals(Orientation.LEFT) &&
                         ((CheckPoint) tile).getNumber() == 2){
                     path = "/TileImages/checkpoint1.png";
                 }
                 if(tile.getOrientations().equals(Orientation.LEFT) &&
                         ((CheckPoint) tile).getNumber() == 3){
                     path = "/TileImages/checkpoint1.png";
                 }
                 if(tile.getOrientations().equals(Orientation.LEFT) &&
                         ((CheckPoint) tile).getNumber() == 4){
                     path = "/TileImages/checkpoint1.png";
                 }
                 if(tile.getOrientations().equals(Orientation.LEFT) &&
                         ((CheckPoint) tile).getNumber() == 5){
                     path = "/TileImages/checkpoint1.png";
                 }
             } else if (tile instanceof ConveyorBelt) {
                 if(((ConveyorBelt) tile).getBeltOrientation().contains(Orientation.TOP) &&
                 ((ConveyorBelt) tile).getSpeed() == 1){
                     path = "/TileImages/green_belt_straight.png";
                 }

             } else if (tile instanceof EnergySpace) {
                 if(tile.getOrientations().equals(Orientation.TOP) &&
                 ((EnergySpace) tile).getRemainedEnergyCube() == 1){
                     path = "/TileImages/energycube_activated.png";
                 }
                 if(tile.getOrientations().equals(Orientation.TOP) &&
                         ((EnergySpace) tile).getRemainedEnergyCube() == 0){
                     path = "/TileImages/energycube_activated.png";
                 }
                 if(tile.getOrientations().equals(Orientation.RIGHT) &&
                         ((EnergySpace) tile).getRemainedEnergyCube() == 1){
                     path = "/TileImages/energycube_activated.png";
                 }
                 if(tile.getOrientations().equals(Orientation.RIGHT) &&
                         ((EnergySpace) tile).getRemainedEnergyCube() == 0){
                     path = "/TileImages/energycube_activated.png";
                 }
                 if(tile.getOrientations().equals(Orientation.BOTTOM) &&
                         ((EnergySpace) tile).getRemainedEnergyCube() == 1){
                     path = "/TileImages/energycube_activated.png";
                 }
                 if(tile.getOrientations().equals(Orientation.BOTTOM) &&
                         ((EnergySpace) tile).getRemainedEnergyCube() == 0){
                     path = "/TileImages/energycube_activated.png";
                 }
                 if(tile.getOrientations().equals(Orientation.LEFT) &&
                         ((EnergySpace) tile).getRemainedEnergyCube() == 1){
                     path = "/TileImages/energycube_activated.png";
                 }
                 if(tile.getOrientations().equals(Orientation.LEFT) &&
                         ((EnergySpace) tile).getRemainedEnergyCube() == 0){
                     path = "/TileImages/energycube_activated.png";
                 }

             } else if (tile instanceof Floor) {
                 path = "/TileImages/floor.png";
             } else if (tile instanceof Gear) {
                 if(((Gear) tile).getDirection().equals("clockwise")){
                     path = "/TileImages/gear.png";
                 }
                 if(((Gear) tile).getDirection().equals("counterclockwise")){
                     path = "/TileImages/gear.png";
                 }
             } else if (tile instanceof PushPanel) {
                 if(tile.getOrientations().equals(Orientation.TOP) &&
                 ((PushPanel) tile).getRegisters().contains(1) &&
                 ((PushPanel) tile).getRegisters().contains(3) &&
                 ((PushPanel) tile).getRegisters().contains(5) ){
                     path = "/TileImages/Pushpanel.png";
                 }
                 if(tile.getOrientations().equals(Orientation.RIGHT) &&
                         ((PushPanel) tile).getRegisters().contains(1) &&
                         ((PushPanel) tile).getRegisters().contains(3) &&
                         ((PushPanel) tile).getRegisters().contains(5) ){
                     path = "/TileImages/Pushpanel.png";
                 }
                 if(tile.getOrientations().equals(Orientation.BOTTOM) &&
                         ((PushPanel) tile).getRegisters().contains(1) &&
                         ((PushPanel) tile).getRegisters().contains(3) &&
                         ((PushPanel) tile).getRegisters().contains(5) ){
                     path = "/TileImages/Pushpanel.png";
                 }
                 if(tile.getOrientations().equals(Orientation.LEFT) &&
                         ((PushPanel) tile).getRegisters().contains(1) &&
                         ((PushPanel) tile).getRegisters().contains(3) &&
                         ((PushPanel) tile).getRegisters().contains(5) ){
                     path = "/TileImages/Pushpanel.png";
                 }
                 if(tile.getOrientations().equals(Orientation.TOP) &&
                         ((PushPanel) tile).getRegisters().contains(2) &&
                         ((PushPanel) tile).getRegisters().contains(4) ){
                     path = "/TileImages/Pushpanel.png";
                 }
                 if(tile.getOrientations().equals(Orientation.RIGHT) &&
                         ((PushPanel) tile).getRegisters().contains(2) &&
                         ((PushPanel) tile).getRegisters().contains(4) ){
                     path = "/TileImages/Pushpanel.png";
                 }
                 if(tile.getOrientations().equals(Orientation.BOTTOM) &&
                         ((PushPanel) tile).getRegisters().contains(2) &&
                         ((PushPanel) tile).getRegisters().contains(4) ){
                     path = "/TileImages/Pushpanel.png";
                 }
                 if(tile.getOrientations().equals(Orientation.LEFT) &&
                         ((PushPanel) tile).getRegisters().contains(2) &&
                         ((PushPanel) tile).getRegisters().contains(4) ){
                     path = "/TileImages/Pushpanel.png";
                 }
             } else if (tile instanceof RebootPoint) {
                 if(tile.getOrientations().equals(Orientation.TOP)){
                     path = "/TileImages/reboot.png";
                 }
                 if(tile.getOrientations().equals(Orientation.RIGHT)){
                     path = "/TileImages/reboot.png";
                 }
                 if(tile.getOrientations().equals(Orientation.BOTTOM)){
                     path = "/TileImages/reboot.png";
                 }
                 if(tile.getOrientations().equals(Orientation.LEFT)){
                     path = "/TileImages/reboot.png";
                 }

             } else if (tile instanceof StartPoint) {
                 path = "/TileImages/starting_point.png";
             } else if (tile instanceof Wall) {
                 if(((Wall) tile).getWallOrientation().equals(Orientation.TOP)){
                     path = "/TileImages/wall.png";
                 }
                 if(((Wall) tile).getWallOrientation().equals(Orientation.RIGHT)){
                     path = "/TileImages/wall.png";
                 }
                 if(((Wall) tile).getWallOrientation().equals(Orientation.BOTTOM)){
                     path = "/TileImages/wall.png";
                 }
                 if(((Wall) tile).getWallOrientation().equals(Orientation.LEFT)){
                     path = "/TileImages/wall.png";
                 }
                 if(((Wall) tile).getWallOrientation().equals(Orientation.TOP) &&
                 ((Wall) tile).getWallOrientation().equals(Orientation.LEFT)){
                     path = "/TileImages/wall.png";
                 }
                 if(((Wall) tile).getWallOrientation().equals(Orientation.TOP) &&
                         ((Wall) tile).getWallOrientation().equals(Orientation.RIGHT)){
                     path = "/TileImages/wall.png";
                 }
                 if(((Wall) tile).getWallOrientation().equals(Orientation.BOTTOM) &&
                         ((Wall) tile).getWallOrientation().equals(Orientation.LEFT)){
                     path = "/TileImages/wall.png";
                 }
                 if(((Wall) tile).getWallOrientation().equals(Orientation.BOTTOM) &&
                         ((Wall) tile).getWallOrientation().equals(Orientation.RIGHT)){
                     path = "/TileImages/wall.png";
                 }
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
