package bb.roborally.game.map;


import bb.roborally.game.Position;
import bb.roborally.game.board.Board;
import bb.roborally.game.tiles.*;
import javafx.geometry.Pos;

import java.util.ArrayList;


public class DizzyHighway extends Board {
    private ArrayList<ArrayList<ArrayList<Tile>>> dizzyHighway;


    public DizzyHighway(ArrayList<ArrayList<ArrayList<Tile>>> dizzyHighway) {
        super(dizzyHighway);
        this.dizzyHighway = dizzyHighway;
    }


    public void buildDizzyHighway() {
        int xAxis = 13;
        int yAxis = 10;
        int maxCellContent = 4;

        dizzyHighway = new ArrayList<ArrayList<ArrayList<Tile>>>();

        //for - Schleife x-Koordinaten (äußerste ArrayList)
        for (int i = 0; i < xAxis; i++) {
            dizzyHighway.add(new ArrayList<ArrayList<Tile>>());

            //for - Schleife y-Koordinaten (mittlere ArrayList)
            for (int j = 0; j < yAxis; j++) {
                dizzyHighway.get(i).add(new ArrayList<Tile>());

                //for - Schleife Cells (innerste ArrayList)
               for(int k = 0; k < maxCellContent; k++){
                        switch(k){
                            case 0:
                                Position position = new Position(i,j);
                                dizzyHighway.get(i).get(j).add(position);
                                break;

                            case 1:
                                Floor floor = new Floor();
                                floor.setFloorPosition(position);
                                dizzyHighway.get(i).get(j).add(floor);
                                break;
                   }


                   if(i == 2 && j == 0){
                       switch (k){
                           case 0:
                               Position position = new Position(2,0);
                               dizzyHighway.get(i).get(j).add(position);
                               break;

                           case 1:
                               ConveyorBelt conveyorBelt = new ConveyorBelt();
                               conveyorBelt.setBeltOrientation(RIGHT);
                               conveyorBelt.setActivationOrder(2);
                               conveyorBelt.setSpeed(1);
                               dizzyHighway.get(i).get(j).add(conveyorBelt);
                       }

                    }

                    if(i == 4 && j == 0 ){
                        switch(k){
                            case 0:
                                Position position = new Position(4,0);
                                dizzyHighway.get(i).get(j).add(position);
                                break;

                            case 1:
                                ConveyorBelt conveyorBelt = new ConveyorBelt(dizzyHighway.get(4).get(0),"onBoard", rechts ,1,false, 2);
                                conveyorBelt.setBeltOrientation(RIGHT);
                                conveyorBelt.setActivationOrder(1);
                                conveyorBelt.setSpeed(2);
                                dizzyHighway.get(i).get(j).add(conveyorBelt);
                        }

                    }

                    if(i == 5 && j == 0){
                        switch(k){
                            case 0:
                                Position position = new Position(5,0);
                                dizzyHighway.get(i).get(j).add(position);
                                break;

                            case 1:
                                ConveyorBelt conveyorBelt = new ConveyorBelt(dizzyHighway.get(4).get(0),"onBoard", rechts ,1,false, 2);
                                conveyorBelt.setBeltOrientation(RIGHT);
                                conveyorBelt.setActivationOrder(1);
                                conveyorBelt.setSpeed(2);
                                dizzyHighway.get(i).get(j).add(conveyorBelt);
                        }
                    }

                    if(i == 12 && j == 0){
                        switch (k){
                            case 0:
                                Position position = new Position(12, 0);
                                dizzyHighway.get(i).get(j).add(position);
                                break;

                            case 1:
                                EnergySpace energySpace = new EnergySpace();
                                dizzyHighway.get(i).get(j).add(energySpace);
                        }
                    }

                    if(i == 1 && j == 1){
                        StartPoint startPoint = new StartPoint();
                        dizzyHighway.get(i).get(j).add(startPoint);
                    }

                    if(i == 4 && j == 1 || i == 5 && j == 1 || i == 6 && j == 1 || i == 7 && j == 1 || i == 8 && j == 1 ||
                       i == 9 && j == 1 || i == 10 && j == 1 || i == 11 && j == 1 || i == 12 && j == 1){
                        ConveyorBelt conveyorBelt = new ConveyorBelt();
                        dizzyHighway.get(i).get(j).add(conveyorBelt);
                    }

                    if(i == 1 && j == 2){
                        Wall wall = new Wall();
                        dizzyHighway.get(i).get(j).add(wall);
                    }

                    if(i == 4 && j == 2 || i == 11 && j == 2 || i == 12 && j == 2){
                        ConveyorBelt conveyorBelt = new ConveyorBelt();
                        dizzyHighway.get(i).get(j).add(conveyorBelt);
                    }

                    if(i == 5 && j == 2){
                        EnergySpace energySpace = new EnergySpace();
                        dizzyHighway.get(i).get(j).add(energySpace);
                    }

                    if(i == 0 && j == 3){
                        StartPoint startPoint = new StartPoint();
                        dizzyHighway.get(i).get(j).add(startPoint);
                    }

                    if(i == 4 && j == 3){
                        ConveyorBelt conveyorBelt = new ConveyorBelt();
                        dizzyHighway.get(i).get(j).add(conveyorBelt);
                    }

                    if(i == 6 && j == 3){
                        Wall wall = new Wall();
                        BoardLaser boardLaser = new BoardLaser();
                        dizzyHighway.get(i).get(j).add(wall);
                        dizzyHighway.get(i).get(j).add(boardLaser);
                    }

                    if(i == 7 && j == 3){
                        RebootPoint rebootPoint = new RebootPoint();
                        dizzyHighway.get(i).get(j).add(rebootPoint);
                    }

                    if(i == 8 && j == 3){
                        Wall wall = new Wall();
                        BoardLaser boardLaser = new BoardLaser();
                        dizzyHighway.get(i).get(j).add(wall);
                        dizzyHighway.get(i).get(j).add(boardLaser);
                    }

                    if(i == 9 && j == 3){
                        Wall wall = new Wall();
                        dizzyHighway.get(i).get(j).add(wall);
                    }

                    if(i == 11 && j == 3){
                        ConveyorBelt conveyorBelt = new ConveyorBelt();
                        dizzyHighway.get(i).get(j).add(conveyorBelt);
                    }

                    if(i == 12 && j == 3){
                        CheckPoint checkPoint = new CheckPoint();
                        dizzyHighway.get(i).get(j).add(checkPoint);
                    }

                    if(i == 0 && j == 4){
                        Antenna antenna = new Antenna();
                        dizzyHighway.get(i).get(j).add(antenna);
                    }

                    if(i == 1 && j == 4){
                        StartPoint startPoint = new StartPoint();
                        dizzyHighway.get(i).get(j).add(startPoint);
                    }

                    if(i == 2 && j == 4){
                        Wall wall = new Wall();
                        dizzyHighway.get(i).get(j).add(wall);
                    }

                    if(i == 4 && j == 4) {
                        ConveyorBelt conveyorBelt = new ConveyorBelt();
                        dizzyHighway.get(i).get(j).add(conveyorBelt);
                    }

                    if(i == 6 && j == 4){
                        Wall wall = new Wall();
                        BoardLaser boardLaser = new BoardLaser();
                        dizzyHighway.get(i).get(j).add(wall);
                        dizzyHighway.get(i).get(j).add(boardLaser);
                    }

                    if(i == 8 && j == 4){
                        EnergySpace energySpace = new EnergySpace();
                        dizzyHighway.get(i).get(j).add(energySpace);
                    }

                    if(i == 11 && j == 4){
                        ConveyorBelt conveyorBelt = new ConveyorBelt();
                        dizzyHighway.get(i).get(j).add(conveyorBelt);
                    }

                    if(i == 1 && j == 5){
                        StartPoint startPoint = new StartPoint();
                        dizzyHighway.get(i).get(j).add(startPoint);
                    }

                    if(i == 2 && j == 5){
                        Wall wall = new Wall();
                        dizzyHighway.get(i).get(j).add(wall);
                    }

                    if(i == 4 && j == 5){
                        ConveyorBelt conveyorBelt = new ConveyorBelt();
                        dizzyHighway.get(i).get(j).add(conveyorBelt);
                    }

                    if(i == 7 && j == 5){
                        EnergySpace energySpace = new EnergySpace();
                        dizzyHighway.get(i).get(j).add(energySpace);
                    }

                    if(i == 9 && j == 5){
                        Wall wall = new Wall();
                        BoardLaser boardLaser = new BoardLaser();
                        dizzyHighway.get(i).get(j).add(wall);
                        dizzyHighway.get(i).get(j).add(boardLaser);
                    }

                    if(i == 11 && j == 5){
                        ConveyorBelt conveyorBelt = new ConveyorBelt();
                        dizzyHighway.get(i).get(j).add(conveyorBelt);
                    }

                    if(i == 0 && j == 6){
                        StartPoint startPoint = new StartPoint();
                        dizzyHighway.get(i).get(j).add(startPoint);
                    }

                    if(i == 4 && j == 6){
                        ConveyorBelt conveyorBelt = new ConveyorBelt();
                        dizzyHighway.get(i).get(j).add(conveyorBelt);
                    }

                    if(i == 6 && j == 6){
                        Wall wall = new Wall();
                        dizzyHighway.get(i).get(j).add(wall);
                    }

                    if(i == 7 && j == 6){
                        Wall wall = new Wall();
                        BoardLaser boardLaser = new BoardLaser();
                        dizzyHighway.get(i).get(j).add(wall);
                        dizzyHighway.get(i).get(j).add(boardLaser);
                    }

                    if(i == 9 && j == 6){
                        Wall wall = new Wall();
                        dizzyHighway.get(i).get(j).add(wall);
                    }

                    if(i == 11 && j == 6){
                        ConveyorBelt conveyorBelt = new ConveyorBelt();
                        dizzyHighway.get(i).get(j).add(conveyorBelt);
                    }

                    if(i == 1 && j == 7){
                        Wall wall = new Wall();
                        dizzyHighway.get(i).get(j).add(wall);
                    }

                    if(i == 3 && j == 7 || i == 4 && j == 7 || i == 11 && j == 7){
                        ConveyorBelt conveyorBelt = new ConveyorBelt();
                        dizzyHighway.get(i).get(j).add(conveyorBelt);
                    }

                    if(i == 10 && j == 6){
                        EnergySpace energySpace = new EnergySpace();
                        dizzyHighway.get(i).get(j).add(energySpace);
                    }

                    if(i == 2 && j == 8){
                        StartPoint startPoint = new StartPoint();
                        dizzyHighway.get(i).get(j).add(startPoint);
                    }

                    if(i == 3 && j == 8 ||i == 4 && j == 8 || i == 5 && j == 8 || i == 6 && j == 8 ||i == 7 && j == 8 ||
                       i == 8 && j == 8 || i == 9 && j == 8 || i == 10 && j == 8 || i == 11 && j == 8 ){
                        ConveyorBelt conveyorBelt = new ConveyorBelt();
                        dizzyHighway.get(i).get(j).add(conveyorBelt);
                    }

                    if(i == 2 && j == 9){
                        ConveyorBelt conveyorBelt = new ConveyorBelt();
                        dizzyHighway.get(i).get(j).add(conveyorBelt);
                    }

                    if(i == 3 && j == 9){
                        EnergySpace energySpace = new EnergySpace();
                        dizzyHighway.get(i).get(j).add(energySpace);
                    }

                    if(i == 10 && j == 9 || i == 11 && j == 9){
                        ConveyorBelt conveyorBelt = new ConveyorBelt();
                        dizzyHighway.get(i).get(j).add(conveyorBelt);
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
