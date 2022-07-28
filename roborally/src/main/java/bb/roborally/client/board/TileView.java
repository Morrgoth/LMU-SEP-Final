package bb.roborally.client.board;

import bb.roborally.protocol.map.tiles.*;
import bb.roborally.protocol.Orientation;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.Objects;

import static bb.roborally.protocol.Orientation.*;
import static bb.roborally.protocol.Orientation.TOP;

public class TileView {

    private ImageView view = new ImageView();

    public TileView(Tile tile) {
        if (tile instanceof Antenna antenna) {
            view = new ImageView(new Image(Objects.requireNonNull(getClass().getResource(getResource(antenna))).toExternalForm()));
        } else if (tile instanceof CheckPoint checkPoint) {
            view = new ImageView(new Image(Objects.requireNonNull(getClass().getResource(getResource(checkPoint))).toExternalForm()));
        } else if (tile instanceof ConveyorBelt conveyorBelt) {
            view = new ImageView(new Image(Objects.requireNonNull(getClass().getResource(getResource(conveyorBelt))).toExternalForm()));
        } else if (tile instanceof Empty empty) {
            view = new ImageView(new Image(Objects.requireNonNull(getClass().getResource(getResource(empty))).toExternalForm()));
        } else if (tile instanceof EnergySpace energySpace) {
            view = new ImageView(new Image(Objects.requireNonNull(getClass().getResource(getResource(energySpace))).toExternalForm()));
        } else if (tile instanceof Gear gear) {
            view = new ImageView(new Image(Objects.requireNonNull(getClass().getResource(getResource(gear))).toExternalForm()));
        } else if (tile instanceof Laser laser) {
            view = new ImageView(new Image(Objects.requireNonNull(getClass().getResource(getResource(laser))).toExternalForm()));
        } else if (tile instanceof Pit pit) {
            view = new ImageView(new Image(Objects.requireNonNull(getClass().getResource(getResource(pit))).toExternalForm()));
        } else if (tile instanceof PushPanel pushPanel) {
            view = new ImageView(new Image(Objects.requireNonNull(getClass().getResource(getResource(pushPanel))).toExternalForm()));
        } else if (tile instanceof RestartPoint restartPoint) {
            view = new ImageView(new Image(Objects.requireNonNull(getClass().getResource(getResource(restartPoint))).toExternalForm()));
        } else if (tile instanceof StartPoint startPoint) {
            view = new ImageView(new Image(Objects.requireNonNull(getClass().getResource(getResource(startPoint))).toExternalForm()));
        } else if (tile instanceof Wall wall) {
            view = new ImageView(new Image(Objects.requireNonNull(getClass().getResource(getResource(wall))).toExternalForm()));
        }
        view.setFitWidth(CellView.CELL_WIDTH);
        view.setFitHeight(CellView.CELL_HEIGHT);
    }

    public ImageView getView() {
        return view;
    }

    public String getResource(Antenna antenna){
        String path = "";
        if (antenna.getOrientations().get(0).equals(Orientation.TOP)) {
            path = "/TileImages/antenna.png";
        }
        if (antenna.getOrientations().get(0).equals(Orientation.RIGHT)) {
            path = "/TileImages/variants/antenna_right.png";
        }
        if (antenna.getOrientations().get(0).equals(Orientation.BOTTOM)) {
            path = "/TileImages/variants/antenna_bottom.png";
        }
        if (antenna.getOrientations().get(0).equals(Orientation.LEFT)) {
            path = "/TileImages/variants/antenna_left.png";
        }
        return path;
    }

    public String getResource(CheckPoint checkPoint) {
        String path = "";
        if (checkPoint.getCount() == 1) {
            path = "/TileImages/checkpoint1_top.png";
        }
        if (checkPoint.getCount() == 2) {
            path = "/TileImages/checkpoint2_top.png";
        }
        if (checkPoint.getCount() == 3) {
            path = "/TileImages/checkpoint3_top.png";
        }
        if (checkPoint.getCount() == 4) {
            path = "/TileImages/checkpoint4_top.png";
        }
        if (checkPoint.getCount() == 5) {
            path = "/TileImages/checkpoint5_top.png";
        }
        return path;
    }

    public String getResource(ConveyorBelt conveyorBelt) {
        String path = "";
        if (conveyorBelt.getOrientations().get(0).equals(Orientation.TOP) &&
                conveyorBelt.getOrientations().get(1).equals(BOTTOM) &&
                conveyorBelt.getSpeed() == 1) {
            path = "/TileImages/variants/GreenBeltTop.png";
        }
        if (conveyorBelt.getOrientations().get(0).equals(Orientation.RIGHT) &&
                conveyorBelt.getOrientations().get(1).equals(LEFT) &&
                conveyorBelt.getSpeed() == 1) {
            path = "/TileImages/variants/GreenBeltRight.png";
        }
        if (conveyorBelt.getOrientations().get(0).equals(Orientation.BOTTOM) &&
                conveyorBelt.getOrientations().get(1).equals(TOP) &&
                conveyorBelt.getSpeed() == 1) {
            path = "/TileImages/green_belt_straight.png";
        }
        if (conveyorBelt.getOrientations().get(0).equals(Orientation.LEFT) &&
                conveyorBelt.getOrientations().get(1).equals(RIGHT) &&
                conveyorBelt.getSpeed() == 1) {
            path = "/TileImages/variants/GreenBeltLeft.png";
        }
        ///
        if (conveyorBelt.getOrientations().get(0).equals(LEFT) &&
                conveyorBelt.getOrientations().get(1).equals(BOTTOM) &&
                conveyorBelt.getSpeed() == 1) {
            path = "/TileImages/variants/green_belt_counter_clockwise_left_bottom.png";
        }

        if (conveyorBelt.getOrientations().get(0).equals(TOP) &&
                conveyorBelt.getOrientations().get(1).equals(RIGHT) &&
                conveyorBelt.getSpeed() == 1) {
            path = "/TileImages/variants/green_belt_clockwise_top_right.png";
        }
        if (conveyorBelt.getOrientations().get(0).equals(BOTTOM) &&
                conveyorBelt.getOrientations().get(1).equals(RIGHT) &&
                conveyorBelt.getSpeed() == 1) {
            path = "/TileImages/variants/green_belt_counter_clockwise_bottom_right.png";
        }
        if (conveyorBelt.getOrientations().get(0).equals(LEFT) &&
                conveyorBelt.getOrientations().get(1).equals(TOP) &&
                conveyorBelt.getSpeed() == 1) {
            path = "/TileImages/green_belt_clockwise_left_top.png";
        }
        if (conveyorBelt.getOrientations().get(0).equals(RIGHT) &&
                conveyorBelt.getOrientations().get(1).equals(BOTTOM) &&
                conveyorBelt.getSpeed() == 1) {
            path = "/TileImages/green_belt_clockwise_right_bottom.png";
        }
        if (conveyorBelt.getOrientations().get(0).equals(TOP) &&
                conveyorBelt.getOrientations().get(1).equals(LEFT) &&
                conveyorBelt.getSpeed() == 1) {
            path = "/TileImages/variants/green_belt_counter_clockwise_top_left.png";
        }
        if (conveyorBelt.getOrientations().get(0).equals(BOTTOM) &&
                conveyorBelt.getOrientations().get(1).equals(LEFT) &&
                conveyorBelt.getSpeed() == 1) {
            path = "/TileImages/green_belt_clockwise_bottom_left.png";
        }
        if (conveyorBelt.getOrientations().get(0).equals(RIGHT) &&
                conveyorBelt.getOrientations().get(1).equals(TOP) &&
                conveyorBelt.getSpeed() == 1) {
            path = "/TileImages/variants/green_belt_counter_clockwise_right_top.png";
        }

        if (conveyorBelt.getOrientations().get(0).equals(Orientation.TOP) &&
                conveyorBelt.getOrientations().get(1).equals(BOTTOM) &&
                conveyorBelt.getSpeed() == 2) {
            path = "/TileImages/variants/blue_belt_straight_top.png";
        }
        if (conveyorBelt.getOrientations().get(0).equals(Orientation.RIGHT) &&
                conveyorBelt.getOrientations().get(1).equals(LEFT) &&
                conveyorBelt.getSpeed() == 2) {
            path = "/TileImages/variants/BlueBeltRight.png";
        }
        if (conveyorBelt.getOrientations().get(0).equals(Orientation.BOTTOM) &&
                conveyorBelt.getOrientations().get(1).equals(TOP) &&
                conveyorBelt.getSpeed() == 2) {
            path = "/TileImages/blue_belt_straight.png";
        }
        if (conveyorBelt.getOrientations().get(0).equals(Orientation.LEFT) &&
                conveyorBelt.getOrientations().get(1).equals(RIGHT) &&
                conveyorBelt.getSpeed() == 2) {
            path = "/TileImages/variants/BlueBeltLeft.png";
        }

        if (conveyorBelt.getOrientations().get(0).equals(LEFT) &&
                conveyorBelt.getOrientations().get(1).equals(BOTTOM) &&
                conveyorBelt.getSpeed() == 2) {
            path = "/TileImages/blue_belt_counter_clockwise_left_bottom.png";
        }
        if (conveyorBelt.getOrientations().get(0).equals(TOP) &&
                conveyorBelt.getOrientations().get(1).equals(RIGHT) &&
                conveyorBelt.getSpeed() == 2) {
            path = "/TileImages/variants/blue_belt_clockwise_top_right.png";
        }
        if (conveyorBelt.getOrientations().get(0).equals(BOTTOM) &&
                conveyorBelt.getOrientations().get(1).equals(RIGHT) &&
                conveyorBelt.getSpeed() == 2) {
            path = "/TileImages/variants/blue_belt_counter_clockwise_bottom_right.png";
        }
        if (conveyorBelt.getOrientations().get(0).equals(LEFT) &&
                conveyorBelt.getOrientations().get(1).equals(TOP) &&
                conveyorBelt.getSpeed() == 2) {
            path = "/TileImages/blue_belt_clockwise_left_top.png";
        }
        if (conveyorBelt.getOrientations().get(0).equals(RIGHT) &&
                conveyorBelt.getOrientations().get(1).equals(BOTTOM) &&
                conveyorBelt.getSpeed() == 2) {
            path = "/TileImages/variants/blue_belt_clockwise_right_bottom.png";
        }
        if (conveyorBelt.getOrientations().get(0).equals(TOP) &&
                conveyorBelt.getOrientations().get(1).equals(LEFT) &&
                conveyorBelt.getSpeed() == 2) {
            path = "/TileImages/variants/blue_belt_counter_clockwise_top_left.png";
        }
        if (conveyorBelt.getOrientations().get(0).equals(BOTTOM) &&
                conveyorBelt.getOrientations().get(1).equals(LEFT) &&
                conveyorBelt.getSpeed() == 2) {
            path = "/TileImages/variants/blue_belt_clockwise_bottom_left.png";
        }
        if (conveyorBelt.getOrientations().get(0).equals(RIGHT) &&
                conveyorBelt.getOrientations().get(1).equals(TOP) &&
                conveyorBelt.getSpeed() == 2) {
            path = "/TileImages/variants/blue_belt_counter_clockwise_right_top.png";
        }

        if (conveyorBelt.getOrientations().size() >= 3) {
            if (conveyorBelt.getOrientations().get(0).equals(LEFT) &&
                    conveyorBelt.getOrientations().get(1).equals(RIGHT) &&
                    conveyorBelt.getOrientations().get(2).equals(BOTTOM) &&
                    conveyorBelt.getSpeed() == 2) {
                path = "/TileImages/variants/RB_left.png";
            }
            if (conveyorBelt.getOrientations().get(0).equals(TOP) &&
                    conveyorBelt.getOrientations().get(1).equals(BOTTOM) &&
                    conveyorBelt.getOrientations().get(2).equals(RIGHT) &&
                    conveyorBelt.getSpeed() == 2) {
                path = "/TileImages/variants/RB_top2.png";
            }
            if (conveyorBelt.getOrientations().get(0).equals(BOTTOM) &&
                    conveyorBelt.getOrientations().get(1).equals(TOP) &&
                    conveyorBelt.getOrientations().get(2).equals(RIGHT) &&
                    conveyorBelt.getSpeed() == 2) {
                path = "/TileImages/variants/RB_Bottom.png";
            }
            if (conveyorBelt.getOrientations().get(0).equals(LEFT) &&
                    conveyorBelt.getOrientations().get(1).equals(RIGHT) &&
                    conveyorBelt.getOrientations().get(2).equals(TOP) &&
                    conveyorBelt.getSpeed() == 2) {
                path = "/TileImages/variants/RB_left2.png";
            }
            if (conveyorBelt.getOrientations().get(0).equals(RIGHT) &&
                    conveyorBelt.getOrientations().get(1).equals(LEFT) &&
                    conveyorBelt.getOrientations().get(2).equals(BOTTOM) &&
                    conveyorBelt.getSpeed() == 2) {
                path = "/TileImages/variants/RB_right2.png";
            }
            if (conveyorBelt.getOrientations().get(0).equals(TOP) &&
                    conveyorBelt.getOrientations().get(1).equals(BOTTOM) &&
                    conveyorBelt.getOrientations().get(2).equals(LEFT) &&
                    conveyorBelt.getSpeed() == 2) {
                path = "/TileImages/variants/RB_top.png";
            }
            if (conveyorBelt.getOrientations().get(0).equals(BOTTOM) &&
                    conveyorBelt.getOrientations().get(1).equals(TOP) &&
                    conveyorBelt.getOrientations().get(2).equals(LEFT) &&
                    conveyorBelt.getSpeed() == 2) {
                path = "/TileImages/variants/RB_Bottom_2.png";
            }
            if (conveyorBelt.getOrientations().get(0).equals(RIGHT) &&
                    conveyorBelt.getOrientations().get(1).equals(LEFT) &&
                    conveyorBelt.getOrientations().get(2).equals(TOP) &&
                    conveyorBelt.getSpeed() == 2) {
                path = "/TileImages/variants/RB_right.png";
            }
        }

        return path;
    }

    public String getResource(Empty empty) {
        String path = "";
        path = "/TileImages/floor.png";
        return path;
    }

    public String getResource(EnergySpace energySpace) {
        String path = "";
        if (energySpace.getCount() == 1) {
            path = "/TileImages/energycube_not_activated.png";
        }
        if (energySpace.getCount() == 0) {
            path = "/TileImages/energycube_activated.png";
        }
        return path;
    }

    public String getResource(Gear gear){
        String path = "";
        if (gear.getOrientations().get(0).toString().equals("clockwise")) {
            path = "/TileImages/gear_clockwise.png";
        }
        else {
            path = "/TileImages/gear_counterclockwise.png";
        }
        return path;
    }

    public String getResource(Laser laser) {
        String path = "";
        if (laser.getOrientations().get(0).equals(Orientation.TOP) &&
                laser.getCount() == 1) {
            path = "/TileImages/variants/laser1_top.png";
        }
        if (laser.getOrientations().get(0).equals(Orientation.TOP) &&
                laser.getCount() == 2) {
            path = "/TileImages/variants/wall_laser2_top.png";
        }
        if (laser.getOrientations().get(0).equals(Orientation.TOP) &&
                laser.getCount() == 3) {
            path = "/TileImages/variants/wall_laser3_top.png";
        }
        if (laser.getOrientations().get(0).equals(Orientation.RIGHT) &&
                laser.getCount() == 1) {
            path = "/TileImages/variants/laser1_right.png";
        }
        if (laser.getOrientations().get(0).equals(Orientation.RIGHT) &&
                laser.getCount() == 2) {
            path = "/TileImages/variants/wall_laser2_right.png";
        }
        if (laser.getOrientations().get(0).equals(Orientation.RIGHT) &&
                laser.getCount() == 3) {
            path = "/TileImages/variants/wall_laser3_right.png";
        }
        if (laser.getOrientations().get(0).equals(Orientation.BOTTOM) &&
                laser.getCount() == 1) {
            path = "/TileImages/variants/laser1_bottom.png";
        }
        if (laser.getOrientations().get(0).equals(Orientation.BOTTOM) &&
                laser.getCount() == 2) {
            path = "/TileImages/variants/wall_laser2_bottom.png";
        }
        if (laser.getOrientations().get(0).equals(Orientation.BOTTOM) &&
                laser.getCount() == 3) {
            path = "/TileImages/variants/wall_laser3_bottom.png";
        }
        if (laser.getOrientations().get(0).equals(Orientation.LEFT) &&
                laser.getCount() == 1) {
            path = "/TileImages/laser1_left.png";
        }
        if (laser.getOrientations().get(0).equals(Orientation.LEFT) &&
                laser.getCount() == 2) {
            path = "/TileImages/wall_laser2_left.png";
        }
        if (laser.getOrientations().get(0).equals(Orientation.LEFT) &&
                laser.getCount() == 3) {
            path = "/TileImages/wall_laser3_left.png";
        }
        return path;
    }

    public String getResource(Pit pit) {
        String path = "";
        path = "/TileImages/blackhole.png";
        return path;
    }

    public String getResource(PushPanel pushPanel) {
        String path = "";
        if (pushPanel.getOrientations().get(0).equals(Orientation.TOP) &&
                (pushPanel.getRegisters().contains(1) &&
                        (pushPanel.getRegisters().contains(3) &&
                                (pushPanel.getRegisters().contains(5))))) {
            path = "/TileImages/variants/pushpanel135_top.png";
        }
        if (pushPanel.getOrientations().get(0).equals(Orientation.RIGHT) &&
                (pushPanel.getRegisters().contains(1) &&
                        (pushPanel.getRegisters().contains(3) &&
                                (pushPanel.getRegisters().contains(5))))) {
            path = "/TileImages/variants/pushpanel135_right.png";
        }
        if (pushPanel.getOrientations().get(0).equals(Orientation.BOTTOM) &&
                (pushPanel.getRegisters().contains(1) &&
                        (pushPanel.getRegisters().contains(3) &&
                                (pushPanel.getRegisters().contains(5))))) {
            path = "/TileImages/variants/pushpanel135_bottom.png";
        }
        if (pushPanel.getOrientations().get(0).equals(Orientation.LEFT) &&
                (pushPanel.getRegisters().contains(1) &&
                        (pushPanel.getRegisters().contains(3) &&
                                (pushPanel.getRegisters().contains(5))))) {
            path = "/TileImages/pushpanel135_left.png";
        }
        if (pushPanel.getOrientations().get(0).equals(Orientation.TOP) &&
                (pushPanel.getRegisters().contains(2) &&
                        (pushPanel.getRegisters().contains(4)))) {
            path = "/TileImages/variants/pushpanel24_top.png";
        }
        if (pushPanel.getOrientations().get(0).equals(Orientation.RIGHT) &&
                (pushPanel.getRegisters().contains(2) &&
                        (pushPanel.getRegisters().contains(4)))) {
            path = "/TileImages/variants/pushpanel24_right.png";
        }
        if (pushPanel.getOrientations().get(0).equals(Orientation.BOTTOM) &&
                (pushPanel.getRegisters().contains(2) &&
                        (pushPanel.getRegisters().contains(4)))) {
            path = "/TileImages/variants/pushpanel24_bottom.png";
        }
        if (pushPanel.getOrientations().get(0).equals(Orientation.LEFT) &&
                (pushPanel.getRegisters().contains(2) &&
                        (pushPanel.getRegisters().contains(4)))) {
            path = "/TileImages/pushpanel24_left.png";
        }
        return path;
    }

    public String getResource(RestartPoint restartPoint) {
        String path = "";
        if(restartPoint.getOrientations().get(0).equals(Orientation.TOP)){
            path = "/TileImages/reboot_top.png";
        }
        if(restartPoint.getOrientations().get(0).equals(Orientation.BOTTOM)){
            path = "/TileImages/variants/reboot_bottom.png";
        }
        if(restartPoint.getOrientations().get(0).equals(Orientation.LEFT)){
            path = "/TileImages/variants/reboot_left.png";
        }
        if(restartPoint.getOrientations().get(0).equals(Orientation.RIGHT)){
            path = "/TileImages/variants/reboot_right.png";
        }
        return path;
    }

    public String getResource(StartPoint startPoint) {
        String path = "";
        path = "/TileImages/starting_point.png";
        return path;
    }

    public String getResource(Wall wall) {
        String path = "";
        if (wall.getOrientations().get(0).equals(Orientation.TOP)) {
            path = "/TileImages/variants/wall_top2.png";
        }
        if (wall.getOrientations().get(0).equals(Orientation.RIGHT)) {
            path = "/TileImages/variants/wall_right2.png";
        }
        if (wall.getOrientations().get(0).equals(Orientation.BOTTOM)) {
            path = "/TileImages/variants/wall_bottom2.png";
        }
        if (wall.getOrientations().get(0).equals(Orientation.LEFT)) {
            path = "/TileImages/variants/wall_left2.png";
        }
        if(wall.getOrientations().size() >= 2){
            if (wall.getOrientations().get(0).equals(Orientation.TOP) &&
                    (wall.getOrientations().get(1).equals(Orientation.LEFT))) {
                path = "/TileImages/variants/wall2_top_left.png";
            }
            if (wall.getOrientations().get(0).equals(Orientation.TOP) &&
                    (wall.getOrientations().get(1).equals(Orientation.RIGHT))) {
                path = "/TileImages/variants/wall2_right_top.png";
            }
            if (wall.getOrientations().get(0).equals(Orientation.BOTTOM) &&
                    (wall.getOrientations().get(1).equals(Orientation.LEFT))) {
                path = "/TileImages/wall2_left_bottom.png";
            }
            if (wall.getOrientations().get(0).equals(Orientation.BOTTOM) &&
                    (wall.getOrientations().get(1).equals(Orientation.RIGHT))) {
                path = "/TileImages/variants/wall2_bottom_right.png";
            }
        }
        return path;
    }
}
