package bb.roborally.protocol.map.tiles;

import bb.roborally.protocol.Orientation;

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

}
