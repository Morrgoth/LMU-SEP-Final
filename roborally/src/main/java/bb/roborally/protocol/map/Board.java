package bb.roborally.protocol.map;

import java.util.ArrayList;

public class Board {
    private String name;
    private ArrayList<ArrayList<Cell>> cells = new ArrayList<>();

    public Board(String name) {
        this.name = name;
    }

    public Board(ArrayList<ArrayList<Cell>> cells) {
        this.cells = cells;
    }

    public Board(String name, ArrayList<ArrayList<Cell>> cells) {
        this.name = name;
        this.cells = cells;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<ArrayList<Cell>> getCells() {
        return cells;
    }

    public void setCells(ArrayList<ArrayList<Cell>> cells) {
        this.cells = cells;
    }

    public Cell get(int x, int y) {
        return cells.get(x).get(y);
    }
}
