package bb.roborally.client.robot_selector;

import bb.roborally.client.board.Position;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Robot {
    private final int id;
    private final String name;
    private final Position startPosition = new Position();
    private final Position position = new Position();
    private final Position nextPosition = new Position();
    private final BooleanProperty positionUpdate = new SimpleBooleanProperty(false);
    private Orientation startOrientation = Orientation.RIGHT; // TODO: determine using map-name
    private Orientation orientation = null;
    private Orientation nextOrientation = null;
    private int rotationDeg = 0;
    private final BooleanProperty orientationUpdate = new SimpleBooleanProperty(false);
    private final BooleanProperty available = new SimpleBooleanProperty(true);
    private final BooleanProperty select = new SimpleBooleanProperty(false);

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
        setNextPosition(x, y);
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(int x, int y) {
        this.position.set(x, y);
    }

    public Position getNextPosition() {
        return nextPosition;
    }

    public void setNextPosition(int x, int y) {
        this.nextPosition.set(x, y);
        positionUpdate.set(true);
    }

    public boolean isPositionUpdate() {
        return positionUpdate.get();
    }

    public BooleanProperty positionUpdateProperty() {
        return positionUpdate;
    }

    public Orientation getOrientation() {
        return orientation;
    }

    public void setOrientation(Orientation orientation) {
        this.orientation = orientation;
        orientationUpdate.set(true);
    }

    public Orientation getStartOrientation() {
        return startOrientation;
    }

    public void setStartOrientation(Orientation startOrientation) {
        this.startOrientation = startOrientation;
    }

    public Orientation getNextOrientation() {
        return nextOrientation;
    }

    public int getRotationDeg() {
        return rotationDeg;
    }

    public void setNextOrientation(Orientation rotation) {
        if (rotation == Orientation.CLOCKWISE) {
            if (orientation == Orientation.TOP) {
                nextOrientation = Orientation.RIGHT;
            } else if (orientation == Orientation.LEFT) {
                nextOrientation = Orientation.TOP;
            } else if (orientation == Orientation.RIGHT) {
                nextOrientation = Orientation.BOTTOM;
            } else if (orientation == Orientation.BOTTOM) {
                nextOrientation = Orientation.LEFT;
            }
            rotationDeg = 90;
        } else if (rotation == Orientation.COUNTERCLOCKWISE) {
            if (orientation == Orientation.TOP) {
                nextOrientation = Orientation.LEFT;
            } else if (orientation == Orientation.LEFT) {
                nextOrientation = Orientation.BOTTOM;
            } else if (orientation == Orientation.RIGHT) {
                nextOrientation = Orientation.TOP;
            } else if (orientation == Orientation.BOTTOM) {
                nextOrientation = Orientation.RIGHT;
            }
            rotationDeg = -90;
        }
        orientationUpdateProperty().set(true);
    }

    public boolean isOrientationUpdate() {
        return orientationUpdate.get();
    }

    public BooleanProperty orientationUpdateProperty() {
        return orientationUpdate;
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

    public boolean isSelect() {
        return select.get();
    }

    public BooleanProperty selectProperty() {
        return select;
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

    public ImageView getRobotElement() {
        if (startOrientation == Orientation.TOP) {
            ImageView imageView = new ImageView(getBoardRobotImage());
            imageView.setFitHeight(40);
            imageView.setFitWidth(40);
            return imageView;
        } else if (startOrientation == Orientation.RIGHT) {
            ImageView imageView = new ImageView(getBoardRobotImage());
            imageView.setRotate(imageView.getRotate() + 90);
            imageView.setFitHeight(40);
            imageView.setFitWidth(40);
            return imageView;
        } else if (startOrientation == Orientation.LEFT) {
            ImageView imageView = new ImageView(getBoardRobotImage());
            imageView.setRotate(imageView.getRotate() - 90);
            imageView.setFitHeight(40);
            imageView.setFitWidth(40);
            return imageView;
        } else if (startOrientation == Orientation.BOTTOM) {
            ImageView imageView = new ImageView(getBoardRobotImage());
            imageView.setRotate(imageView.getRotate() + 180);
            imageView.setFitHeight(40);
            imageView.setFitWidth(40);
            return imageView;
        }
        return null;
    }

    @Override
    public String toString() {
        return id + ": " + name;
    }
}
