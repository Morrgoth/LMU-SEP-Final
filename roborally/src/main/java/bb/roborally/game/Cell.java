package bb.roborally.game;

import bb.roborally.game.tiles.Tile;

import java.util.ArrayList;

public class Cell {
    private ArrayList<Tile> cell;

    public ArrayList<Tile> getCell() {
        return cell;
    }

    public void setCell(ArrayList<Tile> cell) {
        this.cell = cell;
    }
}
