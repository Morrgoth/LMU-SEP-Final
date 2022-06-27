package bb.roborally.game.tiles;

import bb.roborally.data.messages.game_events.Movement;
import bb.roborally.game.Orientation;
import bb.roborally.game.Robot;

import java.util.ArrayList;


/**
 * @author Veronika Heckel
 * @author Muqiu Wang
 * @author Tolga Engin
 * @author Zeynab Baiani
 * @author Bence Ament
 * @author  Philipp Keyzman
 *
 */
public class PushPanel extends Tile{
    final int activationOrder = 3;
    private ArrayList<Integer> registers;
    private ArrayList<String> orientations;
    private String type;
    private String isOnBoard;

    public PushPanel() {
    }
    public PushPanel(String type, String isOnBoard, ArrayList<String> orientations, ArrayList<Integer> registers){
        this.type = type;
        this.isOnBoard = isOnBoard;
        this.orientations = orientations;
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

    public Movement pushPanelEffect(Robot robot, int activeRegister){
        if(registers.contains(activeRegister)){
            int column = robot.getPosition().getColumn();
            int row = robot.getPosition().getRow();
            if(this.orientations.contains("right")){
                robot.getPosition().setColumn(column+1);
                return new Movement(robot.getClientID(), column+1, robot.getPosition().getRow());
            }else if(this.orientations.contains("bottom")){
                robot.getPosition().setRow(row+1);
                return new Movement(robot.getClientID(), robot.getPosition().getColumn(), robot.getPosition().getRow()+1);
            }else if(this.orientations.contains("left")){
                robot.getPosition().setColumn(column-1);
                return new Movement(robot.getClientID(), robot.getPosition().getColumn()-1, robot.getPosition().getRow());
            }else if(this.orientations.contains("top")){
                robot.getPosition().setRow(row-1);
                return new Movement(robot.getClientID(), robot.getPosition().getColumn(), robot.getPosition().getRow()-1);
            }
        }else{
            return null;
        }
        return null;
    }
}
