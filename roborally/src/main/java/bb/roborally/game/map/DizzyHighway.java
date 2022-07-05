package bb.roborally.game.map;


import bb.roborally.game.Orientation;
import bb.roborally.game.Position;
import bb.roborally.game.board.Board;
import bb.roborally.game.board.Cell;
import bb.roborally.game.tiles.*;


import java.util.ArrayList;


/**
 * creates Map Dizzy HighWay
 *
 * @author Veronika Heckel
 */
public class DizzyHighway {

    public static ArrayList<ArrayList<Cell>> buildDizzyHighway() {
        int xAxis = 13;
        int yAxis = 10;
        int maxCellContent = 4;

        ArrayList<ArrayList<Cell>> dizzyHighway = new ArrayList<ArrayList<Cell>>();

        //for - Schleife x-Koordinaten (äußerste ArrayList)
        for (int i = 0; i < xAxis; i++) {
            dizzyHighway.add(new ArrayList<Cell>());

            //for - Schleife y-Koordinaten (mittlere ArrayList)
            for (int j = 0; j < yAxis; j++) {
                dizzyHighway.get(i).add(new Cell());

                //for - Schleife Cells (innerste ArrayList)
                for(int k = 0; k < maxCellContent; k++){
                    if (i>=3 && i<=12){
                        if (k == 0) {
                            Empty empty = new Empty("5B");
                            dizzyHighway.get(i).get(j).addTile(empty);
                        }
                    }

                    if (i>=0 && i<=2){
                        if (k==0){
                            Empty empty = new Empty("A");
                            dizzyHighway.get(i).get(j).addTile(empty);
                        }
                    }


                    if(i == 2 && j == 0){
                        if(k == 1){
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.RIGHT);
                            orientations.add(Orientation.LEFT);
                            ConveyorBelt conveyorBelt = new ConveyorBelt("A",1, orientations);
                            dizzyHighway.get(i).get(j).addTile(conveyorBelt);

                        }
                    }

                    if(i == 4 && j == 0){
                        if(k == 1) {
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.BOTTOM);
                            orientations.add(Orientation.TOP);
                            ConveyorBelt conveyorBelt = new ConveyorBelt("5B",2, orientations);
                            dizzyHighway.get(i).get(j).addTile(conveyorBelt);
                        }
                    }

                    if(i == 5 && j == 0){
                        if(k == 1){
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.BOTTOM);
                            orientations.add(Orientation.TOP);
                            ConveyorBelt conveyorBelt = new ConveyorBelt("5B",2, orientations);
                            dizzyHighway.get(i).get(j).addTile(conveyorBelt);
                        }
                    }

                    if(i == 12 && j == 0){
                        if(k == 1){
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.VERTICAL);
                            EnergySpace energySpace = new EnergySpace("5B", orientations, 1);
                            dizzyHighway.get(i).get(j).addTile(energySpace);
                        }
                    }

                    if(i == 1 && j == 1){
                        if(k == 1) {
                            StartPoint startPoint = new StartPoint( "A");
                            dizzyHighway.get(i).get(j).addTile(startPoint);
                        }
                    }


                    if(i == 4 && j == 1){
                        if(k == 1){
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.BOTTOM);
                            orientations.add(Orientation.TOP);
                            orientations.add(Orientation.RIGHT);
                            ConveyorBelt conveyorBelt = new ConveyorBelt("5B",2, orientations);
                            dizzyHighway.get(i).get(j).addTile(conveyorBelt);
                        }
                    }

                    if(i == 5 && j == 1){
                        if(k == 1){
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.LEFT);
                            orientations.add(Orientation.RIGHT);
                            orientations.add(Orientation.TOP);
                            ConveyorBelt conveyorBelt = new ConveyorBelt("5B",2, orientations);
                            dizzyHighway.get(i).get(j).addTile(conveyorBelt);
                        }
                    }

                    if(i == 6 && j == 1){
                        if(k == 1){
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.LEFT);
                            orientations.add(Orientation.RIGHT);
                            ConveyorBelt conveyorBelt = new ConveyorBelt("5B",2, orientations);
                            dizzyHighway.get(i).get(j).addTile(conveyorBelt);
                        }
                    }

                    if(i == 7 && j == 1){
                        if(k == 1){
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.LEFT);
                            orientations.add(Orientation.RIGHT);
                            ConveyorBelt conveyorBelt = new ConveyorBelt("5B",2, orientations);
                            dizzyHighway.get(i).get(j).addTile(conveyorBelt);
                        }
                    }

                    if(i == 8 && j == 1){
                        if(k == 2){
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.LEFT);
                            orientations.add(Orientation.RIGHT);
                            ConveyorBelt conveyorBelt = new ConveyorBelt("5B",2, orientations);
                            dizzyHighway.get(i).get(j).addTile(conveyorBelt);
                        }
                    }

                    if(i == 9 && j == 1){
                        if(k == 1){
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.LEFT);
                            orientations.add(Orientation.RIGHT);
                            ConveyorBelt conveyorBelt = new ConveyorBelt("5B",2, orientations);
                            dizzyHighway.get(i).get(j).addTile(conveyorBelt);
                        }
                    }

                    if(i == 10 && j == 1){
                        if(k == 1){
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.LEFT);
                            orientations.add(Orientation.RIGHT);
                            ConveyorBelt conveyorBelt = new ConveyorBelt("5B",2, orientations);
                            dizzyHighway.get(i).get(j).addTile(conveyorBelt);
                        }
                    }

                    if(i == 11 && j == 1) {
                        if(k == 1){
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.LEFT);
                            orientations.add(Orientation.RIGHT);
                            orientations.add(Orientation.BOTTOM);
                            ConveyorBelt conveyorBelt = new ConveyorBelt("5B",2, orientations);
                            dizzyHighway.get(i).get(j).addTile(conveyorBelt);
                        }
                    }

                    if(i == 12 && j == 1){
                        if(k == 1){
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.LEFT);
                            orientations.add(Orientation.RIGHT);
                            ConveyorBelt conveyorBelt = new ConveyorBelt("5B",2, orientations);
                            dizzyHighway.get(i).get(j).addTile(conveyorBelt);
                        }
                    }

                    if(i == 1 && j == 2){
                        if(k == 2){
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.TOP);
                            Wall wall = new Wall( "A", orientations);
                            dizzyHighway.get(i).get(j).addTile(wall);
                        }
                    }

                    if(i == 4 && j == 2){
                        if(k == 1){
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.BOTTOM);
                            orientations.add(Orientation.TOP);
                            ConveyorBelt conveyorBelt = new ConveyorBelt("5B",2, orientations);
                            dizzyHighway.get(i).get(j).addTile(conveyorBelt);
                        }
                    }

                    if(i == 5 && j == 2){
                        if(k == 1){
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.VERTICAL);
                            EnergySpace energySpace = new EnergySpace("5B", orientations, 1);
                            dizzyHighway.get(i).get(j).addTile(energySpace);
                        }
                    }

                    if(i == 11 && j == 2){
                        if(k == 1){
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.TOP);
                            orientations.add(Orientation.BOTTOM);
                            orientations.add(Orientation.RIGHT);
                            ConveyorBelt conveyorBelt = new ConveyorBelt("5B",2, orientations);
                            dizzyHighway.get(i).get(j).addTile(conveyorBelt);
                        }
                    }

                    if(i == 12 && j == 2){
                        if(k == 1){
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.LEFT);
                            orientations.add(Orientation.RIGHT);
                            ConveyorBelt conveyorBelt = new ConveyorBelt("5B",2, orientations);
                            dizzyHighway.get(i).get(j).addTile(conveyorBelt);
                        }
                    }

                    if(i == 0 && j == 3){
                        if(k == 1){
                            StartPoint startPoint = new StartPoint("A");
                            dizzyHighway.get(i).get(j).addTile(startPoint);
                        }
                    }

                    if(i == 4 && j == 3){
                        if(k == 1){
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.BOTTOM);
                            orientations.add(Orientation.TOP);
                            ConveyorBelt conveyorBelt = new ConveyorBelt("5B",2, orientations);
                            dizzyHighway.get(i).get(j).addTile(conveyorBelt);
                        }
                    }

                    if(i == 6 && j == 3){
                        if(k == 1){
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.TOP);
                            Wall wall = new Wall( "5B", orientations);
                            dizzyHighway.get(i).get(j).addTile(wall);
                        }
                        if(k == 2){
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.TOP);
                            orientations.add(Orientation.BOTTOM);
                            Laser laser = new Laser( "5B", orientations,1);
                            dizzyHighway.get(i).get(j).addTile(laser);
                        }
                    }

                    if(i == 7 && j == 3){
                        if(k == 1){
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.BOTTOM);
                            RestartPoint restartPoint = new RestartPoint("5B",orientations);
                            dizzyHighway.get(i).get(j).addTile(restartPoint);
                        }
                    }

                    if(i == 8 && j == 3){
                        if(k == 1){
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.LEFT);
                            Wall wall = new Wall( "5B", orientations);
                            dizzyHighway.get(i).get(j).addTile(wall);
                        }

                        if(k == 2){
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.LEFT);
                            Laser laser = new Laser( "5B", orientations,1);
                            dizzyHighway.get(i).get(j).addTile(laser);
                        }
                    }

                    if(i == 9 && j == 3){
                        if(k == 1){
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.RIGHT);
                            Wall wall = new Wall( "5B", orientations);
                            dizzyHighway.get(i).get(j).addTile(wall);
                        }
                        if(k == 2){
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.LEFT);
                            orientations.add(Orientation.RIGHT);
                            Laser laser = new Laser( "5B", orientations,1);
                            dizzyHighway.get(i).get(j).addTile(laser);
                        }
                    }

                    if(i == 11 && j == 3){
                        if(k == 1){
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.TOP);
                            orientations.add(Orientation.BOTTOM);
                            ConveyorBelt conveyorBelt = new ConveyorBelt("5B",2, orientations);
                            dizzyHighway.get(i).get(j).addTile(conveyorBelt);
                        }
                    }

                    if(i == 12 && j == 3){
                        if(k == 1){
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.RIGHT);
                            CheckPoint checkPoint = new CheckPoint("5B",orientations,1);
                            dizzyHighway.get(i).get(j).addTile(checkPoint);
                        }
                    }

                    if(i == 0 && j == 4){
                        if(k == 1){
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.RIGHT);
                            Antenna antenna = new Antenna( "A", orientations);
                            dizzyHighway.get(i).get(j).addTile(antenna);
                        }
                    }

                    if(i == 1 && j == 4){
                        if(k == 1) {
                            StartPoint startPoint = new StartPoint( "A");
                            dizzyHighway.get(i).get(j).addTile(startPoint);
                        }
                    }

                    if(i == 2 && j == 4){
                        if(k == 1){
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.RIGHT);
                            Wall wall = new Wall( "A", orientations);
                            dizzyHighway.get(i).get(j).addTile(wall);
                        }
                    }

                    if(i == 4 && j == 4) {
                        if(k == 1){
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.BOTTOM);
                            orientations.add(Orientation.TOP);
                            ConveyorBelt conveyorBelt = new ConveyorBelt("5B",2, orientations);
                            dizzyHighway.get(i).get(j).addTile(conveyorBelt);
                        }
                    }

                    if(i == 6 && j == 4){
                        if(k == 1){
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.BOTTOM);
                            Wall wall = new Wall( "5B", orientations);
                            dizzyHighway.get(i).get(j).addTile(wall);
                        }

                        if(k == 2){
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.BOTTOM);
                            Laser laser = new Laser( "5B", orientations,1);
                            dizzyHighway.get(i).get(j).addTile(laser);
                        }
                    }

                    if(i == 8 && j == 4){
                        if(k == 1){
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.VERTICAL);
                            EnergySpace energySpace = new EnergySpace("5B", orientations, 1);
                            dizzyHighway.get(i).get(j).addTile(energySpace);
                        }
                    }

                    if(i == 11 && j == 4){
                        if(k == 1){
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.TOP);
                            orientations.add(Orientation.BOTTOM);
                            ConveyorBelt conveyorBelt = new ConveyorBelt("5B",2, orientations);
                            dizzyHighway.get(i).get(j).addTile(conveyorBelt);
                        }
                    }

                    if(i == 1 && j == 5){
                        if(k == 1){
                            StartPoint startPoint = new StartPoint( "A");
                            dizzyHighway.get(i).get(j).addTile(startPoint);
                        }
                    }

                    if(i == 2 && j == 5){
                        if(k == 1){
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.RIGHT);
                            Wall wall = new Wall( "5B", orientations);
                            dizzyHighway.get(i).get(j).addTile(wall);
                        }
                    }

                    if(i == 4 && j == 5){
                        if(k == 1){
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.BOTTOM);
                            orientations.add(Orientation.TOP);
                            ConveyorBelt conveyorBelt = new ConveyorBelt("5B",2, orientations);
                            dizzyHighway.get(i).get(j).addTile(conveyorBelt);
                        }
                    }

                    if(i == 7 && j == 5){
                        if(k == 1){
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.VERTICAL);
                            EnergySpace energySpace = new EnergySpace("5B", orientations, 1);
                            dizzyHighway.get(i).get(j).addTile(energySpace);
                        }
                    }

                    if(i == 9 && j == 5){
                        if(k == 1){
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.TOP);
                            Wall wall = new Wall( "5B", orientations);
                            dizzyHighway.get(i).get(j).addTile(wall);
                        }
                        if(k == 2){
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.TOP);
                            Laser laser = new Laser( "5B", orientations,1);
                            dizzyHighway.get(i).get(j).addTile(laser);
                        }
                    }

                    if(i == 11 && j == 5){
                        if(k == 1){
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.TOP);
                            orientations.add(Orientation.BOTTOM);
                            ConveyorBelt conveyorBelt = new ConveyorBelt("5B",2, orientations);
                            dizzyHighway.get(i).get(j).addTile(conveyorBelt);
                        }
                    }

                    if(i == 0 && j == 6){
                        if(k == 1){
                            StartPoint startPoint = new StartPoint( "A");
                            dizzyHighway.get(i).get(j).addTile(startPoint);
                        }
                    }

                    if(i == 4 && j == 6){
                        if(k == 1){
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.BOTTOM);
                            orientations.add(Orientation.TOP);
                            ConveyorBelt conveyorBelt = new ConveyorBelt("5B",2, orientations);
                            dizzyHighway.get(i).get(j).addTile(conveyorBelt);
                        }
                    }

                    if(i == 6 && j == 6){
                        if(k == 1){
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.LEFT);
                            Wall wall = new Wall( "5B", orientations);
                            dizzyHighway.get(i).get(j).addTile(wall);
                        }
                        if(k == 2){
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.LEFT);
                            orientations.add(Orientation.RIGHT);
                            Laser laser = new Laser( "5B", orientations,1);
                            dizzyHighway.get(i).get(j).addTile(laser);
                        }
                    }

                    if(i == 7 && j == 6){
                        if(k == 1){
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.RIGHT);
                            Wall wall = new Wall("5B", orientations);
                            dizzyHighway.get(i).get(j).addTile(wall);
                        }

                        if(k == 2){
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.RIGHT);
                            Laser laser = new Laser( "5B", orientations,1);
                            dizzyHighway.get(i).get(j).addTile(laser);
                        }
                    }

                    if(i == 9 && j == 6){
                        if(k == 1){
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.BOTTOM);
                            Wall wall = new Wall( "5B", orientations);
                            dizzyHighway.get(i).get(j).addTile(wall);
                        }
                        if(k == 2){
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.TOP);
                            orientations.add(Orientation.BOTTOM);
                            Laser laser = new Laser( "5B", orientations,1);
                            dizzyHighway.get(i).get(j).addTile(laser);
                        }
                    }

                    if(i == 11 && j == 6){
                        if(k == 1){
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.TOP);
                            orientations.add(Orientation.BOTTOM);
                            ConveyorBelt conveyorBelt = new ConveyorBelt("5B",2, orientations);
                            dizzyHighway.get(i).get(j).addTile(conveyorBelt);
                        }
                    }

                    if(i == 1 && j == 7){
                        if(k == 1){
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.BOTTOM);
                            Wall wall = new Wall("A", orientations);
                            dizzyHighway.get(i).get(j).addTile(wall);
                        }
                    }

                    if(i == 3 && j == 7){
                        if(k == 1){
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.RIGHT);
                            orientations.add(Orientation.LEFT);
                            ConveyorBelt conveyorBelt = new ConveyorBelt("5B",2, orientations);
                            dizzyHighway.get(i).get(j).addTile(conveyorBelt);
                        }
                    }

                    if(i == 4 && j == 7){
                        if(k == 1){
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.BOTTOM);
                            orientations.add(Orientation.TOP);
                            orientations.add(Orientation.LEFT);
                            ConveyorBelt conveyorBelt = new ConveyorBelt("5B",2, orientations);
                            dizzyHighway.get(i).get(j).addTile(conveyorBelt);
                        }
                    }

                    if(i == 10 && j == 7){
                        if(k == 1){
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.VERTICAL);
                            EnergySpace energySpace = new EnergySpace("5B", orientations, 1);
                            dizzyHighway.get(i).get(j).addTile(energySpace);
                        }
                    }

                    if(i == 11 && j == 7){
                        if(k == 2){
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.TOP);
                            orientations.add(Orientation.BOTTOM);
                            ConveyorBelt conveyorBelt = new ConveyorBelt("5B",2, orientations);
                            dizzyHighway.get(i).get(j).addTile(conveyorBelt);
                        }
                    }

                    if(i == 1 && j == 8){
                        if(k == 1){
                            StartPoint startPoint = new StartPoint( "A");
                            dizzyHighway.get(i).get(j).addTile(startPoint);

                        }
                    }

                    if(i == 3 && j == 8) {
                        if(k == 1){
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.RIGHT);
                            orientations.add(Orientation.LEFT);
                            ConveyorBelt conveyorBelt = new ConveyorBelt("5B",2, orientations);
                            dizzyHighway.get(i).get(j).addTile(conveyorBelt);
                        }
                    }

                    if(i == 4 && j == 8){
                        if(k == 1){
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.RIGHT);
                            orientations.add(Orientation.LEFT);
                            orientations.add(Orientation.TOP);
                            ConveyorBelt conveyorBelt = new ConveyorBelt("5B",2, orientations);
                            dizzyHighway.get(i).get(j).addTile(conveyorBelt);
                        }
                    }

                    if(i == 5 && j == 8){
                        if(k == 1){
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.RIGHT);
                            orientations.add(Orientation.LEFT);
                            ConveyorBelt conveyorBelt = new ConveyorBelt("5B",2, orientations);
                            dizzyHighway.get(i).get(j).addTile(conveyorBelt);
                        }
                    }

                    if(i == 6 && j == 8){
                        if(k == 1){
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.RIGHT);
                            orientations.add(Orientation.LEFT);
                            ConveyorBelt conveyorBelt = new ConveyorBelt("5B",2, orientations);
                            dizzyHighway.get(i).get(j).addTile(conveyorBelt);
                        }
                    }

                    if(i == 7 && j == 8){
                        if(k == 1){
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.RIGHT);
                            orientations.add(Orientation.LEFT);
                            ConveyorBelt conveyorBelt = new ConveyorBelt("5B",2, orientations);
                            dizzyHighway.get(i).get(j).addTile(conveyorBelt);
                        }
                    }

                    if(i == 8 && j == 8){
                        if(k == 1){
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.RIGHT);
                            orientations.add(Orientation.LEFT);
                            ConveyorBelt conveyorBelt = new ConveyorBelt("5B",2, orientations);
                            dizzyHighway.get(i).get(j).addTile(conveyorBelt);
                        }
                    }

                    if(i == 9 && j == 8){
                        if(k == 1){
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.RIGHT);
                            orientations.add(Orientation.LEFT);
                            ConveyorBelt conveyorBelt = new ConveyorBelt("5B",2, orientations);
                            dizzyHighway.get(i).get(j).addTile(conveyorBelt);
                        }
                    }

                    if(i == 10 && j == 8){
                        if(k == 1){
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.RIGHT);
                            orientations.add(Orientation.LEFT);
                            orientations.add(Orientation.BOTTOM);
                            ConveyorBelt conveyorBelt = new ConveyorBelt("5B",2, orientations);
                            dizzyHighway.get(i).get(j).addTile(conveyorBelt);
                        }
                    }

                    if(i == 11 && j == 8 ){
                        if(k == 1){
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.TOP);
                            orientations.add(Orientation.BOTTOM);
                            orientations.add(Orientation.LEFT);
                            ConveyorBelt conveyorBelt = new ConveyorBelt("5B",2, orientations);
                            dizzyHighway.get(i).get(j).addTile(conveyorBelt);
                        }
                    }

                    if(i == 2 && j == 9){
                        if(k == 1){
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.RIGHT);
                            orientations.add(Orientation.LEFT);
                            ConveyorBelt conveyorBelt = new ConveyorBelt("A",1, orientations);
                            dizzyHighway.get(i).get(j).addTile(conveyorBelt);
                        }
                    }

                    if(i == 3 && j == 9){
                        if(k == 1){
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.VERTICAL);
                            EnergySpace energySpace = new EnergySpace("5B", orientations, 1);
                            dizzyHighway.get(i).get(j).addTile(energySpace);
                        }
                    }

                    if(i == 10 && j == 9) {
                        if (k == 1) {
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.TOP);
                            orientations.add(Orientation.BOTTOM);
                            ConveyorBelt conveyorBelt = new ConveyorBelt("5B",2, orientations);
                            dizzyHighway.get(i).get(j).addTile(conveyorBelt);
                        }
                    }

                    if(i == 11 && j == 9){
                        if(k == 1){
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.TOP);
                            orientations.add(Orientation.BOTTOM);
                            ConveyorBelt conveyorBelt = new ConveyorBelt("5B",2, orientations);
                            dizzyHighway.get(i).get(j).addTile(conveyorBelt);
                        }
                    }
                }
            }
        }
        return dizzyHighway;
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
