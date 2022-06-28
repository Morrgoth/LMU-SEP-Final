package bb.roborally.game.map;

import bb.roborally.game.Position;
import bb.roborally.game.board.Cell;
import bb.roborally.game.tiles.ConveyorBelt;
import bb.roborally.game.tiles.EnergySpace;
import bb.roborally.game.tiles.Floor;
import bb.roborally.game.tiles.Tile;
import javafx.geometry.Pos;

import java.util.ArrayList;

public class DizzyHighway {
    ArrayList<ArrayList<ArrayList<Tile>>> dizzyHighway;


    public DizzyHighway(ArrayList<ArrayList<ArrayList<Tile>>> dizzyHighway) {
        this.dizzyHighway = dizzyHighway;

    }


    public void buildDizzyHighway() {
        int xAxis = 13;
        int yAxis = 10;
        int maxCellContent = 3;

        dizzyHighway = new ArrayList<ArrayList<ArrayList<Tile>>>();

        //for - Schleife x-Koordinaten (äußerste ArrayList)
        for (int i = 0; i < xAxis; i++) {
            dizzyHighway.add(new ArrayList<ArrayList<Tile>>());

            //for - Schleife y-Koordinaten (mittlere ArrayList)
            for (int j = 0; j < yAxis; j++) {
                dizzyHighway.get(i).add(new ArrayList<Tile>());

                //for - Schleife Cells (innerste ArrayList)
                for(int k = 0; k <= maxCellContent; k++){
                    Floor floor = new Floor();
                    dizzyHighway.get(i).get(j).add(floor);

                    if(i == 2 || i == 4 || i == 5 && j == 0){
                        ConveyorBelt conveyorBelt = new ConveyorBelt();
                        dizzyHighway.get(i).get(j).add(conveyorBelt);
                    }
                    if(i == 12 && j == 0){
                        EnergySpace energySpace = new EnergySpace();
                        dizzyHighway.get(i).get(j).add(energySpace);
                    }

                    }

                }

        }
    }
}



     /**   dizzyHighway.add(5, 2, new ArrayList<Tile> ());
        Wall wall = new Wall(wallposition, orientation);
        BoardLaser laser = new BoardLaser(laserPosition, orientation, 1);
        for(Tile tile: cell){
            cell.add(floor);
            cell.add(wall);
            cell.add(laser);
        }

        for(int k = 0; k <= 3; k++){


        }
        if(position);
    }

}
         public ArrayList<Position> populatePositions(int maxColumns, int maxRows){
        this.maxRows = maxRows;
        int minColumn = 0;
        int minRow = 0;
        int totalFields = maxColumns * maxColumns;
        for (int i = 0; i <= maxColumns; maxColumns++){
            for (int j = 0 ; j <= maxRows; maxRows++){
                Position position = new Position(i, j);
                field.add(totalFields,position);
                if(position.getColumn() != minColumn && position.getColumn() != maxColumns|| position.getRow() != minRow && position.getRow() != maxRows)
                    Floor floor = new Floor();
                    floor.setPosition(position);

            }
        }
        return field;
    }
      **/
