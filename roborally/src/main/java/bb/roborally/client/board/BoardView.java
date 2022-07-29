package bb.roborally.client.board;

import bb.roborally.client.robot_selector.Robot;
import bb.roborally.protocol.map.Board;
import bb.roborally.protocol.map.Cell;
import bb.roborally.protocol.map.tiles.Empty;
import bb.roborally.protocol.map.tiles.StartPoint;
import bb.roborally.protocol.map.tiles.Tile;
import javafx.scene.Parent;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;

import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author Bence Ament
 */
public class BoardView {
    private final GridPane boardGrid = new GridPane();
    private final Pane robotLayer = new Pane();
    private final StackPane view = new StackPane();
    private final HashMap<Robot, ImageView> robotsOnBoard = new HashMap<>();
    ArrayList<ArrayList<CellView>> cells = new ArrayList<>();
    public BoardView() {
        robotLayer.setPickOnBounds(false);
        robotLayer.prefWidthProperty().bind(view.widthProperty());
        robotLayer.prefHeightProperty().bind(view.heightProperty());
    }

    public Parent getView() {
        return view;
    }

    public void clear() {
        cells.clear();
        view.getChildren().clear();
    }

    public void setCells(ArrayList<ArrayList<Cell>> cells) {
        clear();
        for (ArrayList<Cell> row: cells) {
            ArrayList<CellView> cellViews = new ArrayList<>();
            for (Cell cell: row) {
                if (!cell.hasTile("Empty")) {
                    cell.getTiles().add(0, new Empty());
                }
                cellViews.add(new CellView(cell));
            }
            this.cells.add(cellViews);
        }
    }

    /**
     * @param board
     * populates the Board visually
     */
    public void populate(Board board) {
        setCells(board.getCells());
        int y = 0;
        for (ArrayList<CellView> col: cells) {
            int x = 0;
            for (CellView cellView: col) {
                boardGrid.add(cellView.getView(), y, x);
                cellView.setPosition(x, y);
                x += 1;
            }
            y += 1;
        }
        view.getChildren().addAll(boardGrid, robotLayer);
    }

    public CellView getCellView(int x, int y) {
        return cells.get(x).get(y);
    }

    /**
     * @param robot robot to add or move
     */
    public void displayRobot(Robot robot) {
        if (robot.getPosition().isSet()) {
            moveRobot(robot);
        } else {
            addRobot(robot);
        }
    }

    /**
     * @param robot robot to be added to the board
     */
    public void addRobot(Robot robot) {
        final ImageView robotImage = robot.getRobotElement();
        robotsOnBoard.put(robot, robotImage);
        robotLayer.getChildren().add(robotImage);
        robotImage.setLayoutX(/*robotLayer.getLayoutX() +*/ robot.getNextPosition().getX() * 40);
        robotImage.setLayoutY(/*robotLayer.getLayoutY() +*/ robot.getNextPosition().getY() * 40);
        robot.setPosition(robot.getNextPosition().getX(), robot.getNextPosition().getY());
        robot.setOrientation(robot.getStartOrientation());
    }

    /**
     * @param robot to be moved to a new position
     */
    public void moveRobot(Robot robot) {
        final ImageView robotImage = robotsOnBoard.get(robot);
        double nextX = robot.getNextPosition().getX() * CellView.CELL_WIDTH;
        double nextY = robot.getNextPosition().getY() * CellView.CELL_HEIGHT;
        robotImage.setLayoutX(nextX);
        robotImage.setLayoutY(nextY);
        robot.setPosition(robot.getNextPosition().getX(), robot.getNextPosition().getY());
    }

    /**
     * @param robot robot to be rotated
     */
    public void rotateRobot(Robot robot) {
        final ImageView robotImage = robotsOnBoard.get(robot);
        robotImage.setRotate(robotImage.getRotate() + robot.getRotationDeg());
        robot.setOrientation(robot.getNextOrientation());
    }

    /**
     * @return all the Cells that contain a Tile
     */
    public ArrayList<CellView> getStartPoints() {
        ArrayList<CellView> startPoints = new ArrayList<>();
        for (ArrayList<CellView> cellsRow: cells) {
            for (CellView cellView: cellsRow) {
                for (Tile tile: cellView.getCell().getTiles()) {
                    if (tile instanceof StartPoint) {
                        startPoints.add(cellView);
                    }
                }
            }
        }
        return startPoints;
    }
}
