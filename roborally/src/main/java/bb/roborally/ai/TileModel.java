package bb.roborally.ai;

import bb.roborally.protocol.Orientation;
import bb.roborally.protocol.map.tiles.CheckPoint;
import bb.roborally.protocol.map.tiles.ConveyorBelt;
import bb.roborally.protocol.map.tiles.PushPanel;
import bb.roborally.protocol.map.tiles.Tile;

import java.util.ArrayList;

public class TileModel {

    private TileType type;
    private String isOnBoard;
    private ArrayList<Orientation> orientations = new ArrayList<>();
    private ArrayList<Integer> registers = new ArrayList<>();
    private int speed = 0;
    private int count = 0;

    public TileModel(Tile tile) {
        this.type = toTileType(tile.getType());
        this.isOnBoard = tile.getIsOnBoard();
        if (type == TileType.CHECK_POINT) {
            CheckPoint checkPoint = (CheckPoint) tile;
            setCount(checkPoint.getCount());
        } else if (type == TileType.CONVEYOR_BELT) {
            ConveyorBelt conveyorBelt = (ConveyorBelt) tile;
            setOrientations(conveyorBelt.getOrientations());
            setSpeed(conveyorBelt.getSpeed());
        } else if (type == TileType.PUSH_PANEL) {
            PushPanel pushPanel = (PushPanel) tile;
            setOrientations(pushPanel.getOrientations());
            setRegisters(pushPanel.getRegisters());
        }
    }

    public TileType getType() {
        return type;
    }

    public String getIsOnBoard() {
        return isOnBoard;
    }

    public ArrayList<Orientation> getOrientations() {
        return orientations;
    }

    public void setOrientations(ArrayList<Orientation> orientations) {
        this.orientations = orientations;
    }

    public ArrayList<Integer> getRegisters() {
        return registers;
    }

    public void setRegisters(ArrayList<Integer> registers) {
        this.registers = registers;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public enum TileType {
        ANTENNA,
        CHECK_POINT,
        CONVEYOR_BELT,
        EMPTY,
        ENERGY_SPACE,
        GEAR,
        PIT,
        PUSH_PANEL,
        RESTART_POINT,
        START_POINT,
        WALL
    }

    public static TileType toTileType(String str) {
        if ("Antenna".equals(str)) {
            return TileType.ANTENNA;
        } else if ("CheckPoint".equals(str)) {
            return TileType.CHECK_POINT;
        } else if ("ConveyorBelt".equals(str)) {
            return TileType.CONVEYOR_BELT;
        } else if ("Empty".equals(str)) {
            return TileType.EMPTY;
        } else if ("EnergySpace".equals(str)) {
            return TileType.ENERGY_SPACE;
        } else if ("Gear".equals(str)) {
            return TileType.GEAR;
        } else if ("Pit".equals(str)) {
            return TileType.PIT;
        } else if ("PushPanel".equals(str)) {
            return TileType.PUSH_PANEL;
        } else if ("RestartPoint".equals(str)) {
            return TileType.RESTART_POINT;
        } else if ("StartPoint".equals(str)) {
            return TileType.START_POINT;
        } else if ("Wall".equals(str)) {
            return TileType.WALL;
        } else {
            return null;
        }
    }
}
