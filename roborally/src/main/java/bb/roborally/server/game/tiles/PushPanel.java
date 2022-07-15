package bb.roborally.server.game.tiles;

import bb.roborally.server.game.Orientation;

import java.util.ArrayList;


/**
 * @author Muqiu Wang
 */

public class PushPanel extends Tile{
    private ArrayList<Integer> registers;

    public PushPanel() {
    }
    public PushPanel( String isOnBoard, ArrayList<Orientation> orientations, ArrayList<Integer> registers){
        this.setIsOnBoard(isOnBoard);
        this.setOrientations(orientations);
        this.registers = registers;
    }

    @Override
    public String getType() {
        return "PushPanel";
    }

    public ArrayList<Integer> getRegisters() {
        return registers;
    }

    public void setRegisters(ArrayList<Integer> registers) {
        this.registers = registers;
    }

    @Override
    public String getResource(){
        String path = "";
        if (this.getOrientations().get(0).equals(Orientation.TOP) &&
                (this.getRegisters().contains(1) &&
                (this.getRegisters().contains(3) &&
                (this.getRegisters().contains(5))))) {
            path = "/TileImages/variants/pushpanel135_top.png";
        }
        if (this.getOrientations().get(0).equals(Orientation.RIGHT) &&
                (this.getRegisters().contains(1) &&
                (this.getRegisters().contains(3) &&
                (this.getRegisters().contains(5))))) {
            path = "/TileImages/variants/pushpanel135_right.png";
        }
        if (this.getOrientations().get(0).equals(Orientation.BOTTOM) &&
                (this.getRegisters().contains(1) &&
                (this.getRegisters().contains(3) &&
                (this.getRegisters().contains(5))))) {
            path = "/TileImages/variants/pushpanel135_bottom.png";
        }
        if (this.getOrientations().get(0).equals(Orientation.LEFT) &&
                (this.getRegisters().contains(1) &&
                (this.getRegisters().contains(3) &&
                (this.getRegisters().contains(5))))) {
            path = "/TileImages/pushpanel135_left.png";
        }
        if (this.getOrientations().get(0).equals(Orientation.TOP) &&
                (this.getRegisters().contains(2) &&
                (this.getRegisters().contains(4)))) {
            path = "/TileImages/variants/pushpanel24_top.png";
        }
        if (this.getOrientations().get(0).equals(Orientation.RIGHT) &&
                (this.getRegisters().contains(2) &&
                (this.getRegisters().contains(4)))) {
            path = "/TileImages/variants/pushpanel24_right.png";
        }
        if (this.getOrientations().get(0).equals(Orientation.BOTTOM) &&
                (this.getRegisters().contains(2) &&
                (this.getRegisters().contains(4)))) {
            path = "/TileImages/variants/pushpanel24_bottom.png";
        }
        if (this.getOrientations().get(0).equals(Orientation.LEFT) &&
                (this.getRegisters().contains(2) &&
                (this.getRegisters().contains(4)))) {
            path = "/TileImages/pushpanel24_left.png";
        }return path;
    }

}
