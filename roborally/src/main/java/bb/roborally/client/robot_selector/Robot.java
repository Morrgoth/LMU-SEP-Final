package bb.roborally.client.robot_selector;

import bb.roborally.server.game.board.Cell;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Robot {
    private final int id;
    private final String name;
    private final Position startPosition = new Position();
    private final Position position = new Position();
    private Orientation orientation = Orientation.LEFT;
    private final StringProperty orientationStr = new SimpleStringProperty("left");
    private final BooleanProperty available = new SimpleBooleanProperty(true);

    public Robot(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Position getStartPosition() {
        return startPosition;
    }

    public void setStartPosition(int x, int y) {
        this.startPosition.set(x, y);
        this.position.set(x, y);
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(int x, int y) {
        this.position.set(x, y);
    }

    public Image getBoardRobotImage() {
        return new Image(getClass().getResource("/robots/board_robots/robot_board_blau.png").toExternalForm());
    }

    public Image getLoginRobotImage() {
        return new Image(getClass().getResource("/robots/login_robots/robot_login_blau.png").toExternalForm());
    }

    public ImageView getRobotElement() {
        ImageView imageView = new ImageView(getClass().getResource("/robots/board_robots/robot_board_blau.png").toExternalForm());
        imageView.setFitHeight(Cell.CELL_HEIGHT);
        imageView.setFitWidth(Cell.CELL_WIDTH);
        return imageView;
    }

    public void rotate(Orientation orientation) {
        // TODO: update orientation and orientationStr
        if (orientation == Orientation.CLOCKWISE) {
            // TODO: clockwise rotation
        } else if (orientation == Orientation.COUNTERCLOCKWISE) {
            // TODO: counterclockwise rotation
        }
    }

    public BooleanProperty availableProperty() {
        return available;
    }

    public boolean isAvailable() {
        return available.get();
    }

    public void setAvailable(boolean available) {
        this.available.set(available);
    }

    @Override
    public String toString() {
        return id + ": " + name;
    }

    public Orientation getOrientation() {
        return orientation;
    }

    public void setOrientation(Orientation orientation) {
        this.orientation = orientation;
    }

    public String getOrientationStr() {
        return orientationStr.get();
    }

    public StringProperty orientationStrProperty() {
        return orientationStr;
    }
}
