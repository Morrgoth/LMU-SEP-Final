package bb.roborally.game;

/**
 * creating a certain position as reference for each item on the board. Each item on the board has a certain position. This class
 * makes it possible to compare them and to make updates during the game.
 * @author Veronika Heckel
 * @author Muqiu Wang
 * @author Tolga Engin
 * @author Zeynab Baiani
 * @author Bence Ament
 * @author  Philipp Keyzman
 */
public class Position {
    // ORIENTATION : LEFT,RIGHT
    private int column;
    //ORIENTATION : FRONT,BACK
    private int row;
    private String typeName;

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
}
