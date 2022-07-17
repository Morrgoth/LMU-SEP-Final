package bb.roborally.client.robot_selector;

import bb.roborally.server.game.board.Cell;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Robot {
    private final int id;
    private final String name;
    private final Position startPosition = new Position();
    private final Position position = new Position();
    private Orientation orientation = Orientation.LEFT;
    private final BooleanProperty available = new SimpleBooleanProperty(true);
    private String login_path;
    private String board_path;

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
        if (name.equals("Twonky")) {
            return new Image(getClass().getResource("/robots/board_robots/robot_board_orange.png").toExternalForm());
        } else if (name.equals("Hulk x90")) {
            return new Image(getClass().getResource("/robots/board_robots/robot_board_rot.png").toExternalForm());
        } else if (name.equals("HammerBot")) {
            return new Image(getClass().getResource("/robots/board_robots/robot_board_lila.png").toExternalForm());
        } else if (name.equals("SmashBot")) {
            return new Image(getClass().getResource("/robots/board_robots/robot_board_gelb.png").toExternalForm());
        } else if (name.equals("ZoomBot")) {
            return new Image(getClass().getResource("/robots/board_robots/robot_board_grün.png").toExternalForm());
        } else if (name.equals("SpinBot")) {
            return new Image(getClass().getResource("/robots/board_robots/robot_board_blau.png").toExternalForm());
        }
        return null;
    }

    public Image getLoginRobotImage() {
        return new Image(getClass().getResource(login_path).toExternalForm());
    }

    public ImageView getRobotElement() {
        ImageView imageView = new ImageView(getBoardRobotImage());
        imageView.setFitHeight(Cell.CELL_HEIGHT);
        imageView.setFitWidth(Cell.CELL_WIDTH);
        return imageView;
    }

    public void rotate(Orientation orientation) {
        if (orientation == Orientation.CLOCKWISE) {
            // TODO
        } else if (orientation == Orientation.COUNTERCLOCKWISE) {
            // TODO
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
}
