package bb.roborally.game;

import bb.roborally.game.tiles.Tile;

import java.util.ArrayList;

/**
 * creating a certain position as reference for each item on the board. Each item on the board has a certain position. This class
 * makes it possible to compare them and to make updates during the game.
 *
 * @author Veronika Heckel
 * @author Muqiu Wang
 * @author Philipp Keyzman
 */
public class Position {
    // ORIENTATION : LEFT,RIGHT
    private int column;
    //ORIENTATION : FRONT,BACK
    private int row;
    private String typeName;
    private int maxColumns;
    private int maxRows;

    private ArrayList<Position> field;

    public Position(int column, int row) {
        this.column = column;
        this.row = row;
    }
    public Position(int column, int row,String typeName) {
        this.column = column;
        this.row = row;
        this.typeName = typeName;
    }

    public int getColumn() {
        return column;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }
    public int getMaxColumns() {
        return maxColumns;
    }

    public void setMaxColumns(int maxColumns) {
        this.maxColumns = maxColumns;
    }

    public int getMaxRows() {
        return maxRows;
    }

    public void setMaxRows(int maxRows) {
        this.maxRows = maxRows;
    }

    public ArrayList<Position> populatePositions(int maxColumns, int maxRows){
        this.maxRows = maxRows;

        int totalFields = maxColumns * maxColumns;

        for (int i = 0; i <= maxColumns; maxColumns++ ){
            for (int j = 0 ; j <= maxRows; maxRows++){
                Position position = new Position(i, j);
                field.add(totalFields,position);
            }
        }
        return field;
    }

}
