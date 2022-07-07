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
        for (int x = 0; x < xAxis; x++) {
            dizzyHighway.add(new ArrayList<Cell>());

            //for - Schleife y-Koordinaten (mittlere ArrayList)
            for (int y = 0; y < yAxis; y++) {
                dizzyHighway.get(x).add(new Cell(x, y));

                //for - Schleife Cells (innerste ArrayList)
                for(int k = 0; k < maxCellContent; k++){
                    if (x>=3 && x<=12){
                        if (k == 0) {
                            Empty empty = new Empty("5B");
                            dizzyHighway.get(x).get(y).addTile(empty);
                        }
                    }

                    if (x>=0 && x<=2){
                        if (k==0){
                            Empty empty = new Empty("A");
                            dizzyHighway.get(x).get(y).addTile(empty);
                        }
                    }


                    if(x == 2 && y == 0){
                        if(k == 1){
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.RIGHT);
                            orientations.add(Orientation.LEFT);
                            ConveyorBelt conveyorBelt = new ConveyorBelt("A",1, orientations);
                            dizzyHighway.get(x).get(y).addTile(conveyorBelt);

                        }
                    }

                    if(x == 4 && y == 0){
                        if(k == 1) {
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.BOTTOM);
                            orientations.add(Orientation.TOP);
                            ConveyorBelt conveyorBelt = new ConveyorBelt("5B",2, orientations);
                            dizzyHighway.get(x).get(y).addTile(conveyorBelt);
                        }
                    }

                    if(x == 5 && y == 0){
                        if(k == 1){
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.BOTTOM);
                            orientations.add(Orientation.TOP);
                            ConveyorBelt conveyorBelt = new ConveyorBelt("5B",2, orientations);
                            dizzyHighway.get(x).get(y).addTile(conveyorBelt);
                        }
                    }

                    if(x == 12 && y == 0){
                        if(k == 1){
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.VERTICAL);
                            EnergySpace energySpace = new EnergySpace("5B", orientations, 1);
                            dizzyHighway.get(x).get(y).addTile(energySpace);
                        }
                    }

                    if(x == 1 && y == 1){
                        if(k == 1) {
                            StartPoint startPoint = new StartPoint( "A");
                            dizzyHighway.get(x).get(y).addTile(startPoint);
                        }
                    }


                    if(x == 4 && y == 1){
                        if(k == 1){
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.BOTTOM);
                            orientations.add(Orientation.TOP);
                            orientations.add(Orientation.RIGHT);
                            ConveyorBelt conveyorBelt = new ConveyorBelt("5B",2, orientations);
                            dizzyHighway.get(x).get(y).addTile(conveyorBelt);
                        }
                    }

                    if(x == 5 && y == 1){
                        if(k == 1){
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.LEFT);
                            orientations.add(Orientation.RIGHT);
                            orientations.add(Orientation.TOP);
                            ConveyorBelt conveyorBelt = new ConveyorBelt("5B",2, orientations);
                            dizzyHighway.get(x).get(y).addTile(conveyorBelt);
                        }
                    }

                    if(x == 6 && y == 1){
                        if(k == 1){
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.LEFT);
                            orientations.add(Orientation.RIGHT);
                            ConveyorBelt conveyorBelt = new ConveyorBelt("5B",2, orientations);
                            dizzyHighway.get(x).get(y).addTile(conveyorBelt);
                        }
                    }

                    if(x == 7 && y == 1){
                        if(k == 1){
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.LEFT);
                            orientations.add(Orientation.RIGHT);
                            ConveyorBelt conveyorBelt = new ConveyorBelt("5B",2, orientations);
                            dizzyHighway.get(x).get(y).addTile(conveyorBelt);
                        }
                    }

                    if(x == 8 && y == 1){
                        if(k == 2){
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.LEFT);
                            orientations.add(Orientation.RIGHT);
                            ConveyorBelt conveyorBelt = new ConveyorBelt("5B",2, orientations);
                            dizzyHighway.get(x).get(y).addTile(conveyorBelt);
                        }
                    }

                    if(x == 9 && y == 1){
                        if(k == 1){
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.LEFT);
                            orientations.add(Orientation.RIGHT);
                            ConveyorBelt conveyorBelt = new ConveyorBelt("5B",2, orientations);
                            dizzyHighway.get(x).get(y).addTile(conveyorBelt);
                        }
                    }

                    if(x == 10 && y == 1){
                        if(k == 1){
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.LEFT);
                            orientations.add(Orientation.RIGHT);
                            ConveyorBelt conveyorBelt = new ConveyorBelt("5B",2, orientations);
                            dizzyHighway.get(x).get(y).addTile(conveyorBelt);
                        }
                    }

                    if(x == 11 && y == 1) {
                        if(k == 1){
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.LEFT);
                            orientations.add(Orientation.RIGHT);
                            orientations.add(Orientation.BOTTOM);
                            ConveyorBelt conveyorBelt = new ConveyorBelt("5B",2, orientations);
                            dizzyHighway.get(x).get(y).addTile(conveyorBelt);
                        }
                    }

                    if(x == 12 && y == 1){
                        if(k == 1){
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.LEFT);
                            orientations.add(Orientation.RIGHT);
                            ConveyorBelt conveyorBelt = new ConveyorBelt("5B",2, orientations);
                            dizzyHighway.get(x).get(y).addTile(conveyorBelt);
                        }
                    }

                    if(x == 1 && y == 2){
                        if(k == 2){
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.TOP);
                            Wall wall = new Wall( "A", orientations);
                            dizzyHighway.get(x).get(y).addTile(wall);
                        }
                    }

                    if(x == 4 && y == 2){
                        if(k == 1){
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.BOTTOM);
                            orientations.add(Orientation.TOP);
                            ConveyorBelt conveyorBelt = new ConveyorBelt("5B",2, orientations);
                            dizzyHighway.get(x).get(y).addTile(conveyorBelt);
                        }
                    }

                    if(x == 5 && y == 2){
                        if(k == 1){
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.VERTICAL);
                            EnergySpace energySpace = new EnergySpace("5B", orientations, 1);
                            dizzyHighway.get(x).get(y).addTile(energySpace);
                        }
                    }

                    if(x == 11 && y == 2){
                        if(k == 1){
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.TOP);
                            orientations.add(Orientation.BOTTOM);
                            orientations.add(Orientation.RIGHT);
                            ConveyorBelt conveyorBelt = new ConveyorBelt("5B",2, orientations);
                            dizzyHighway.get(x).get(y).addTile(conveyorBelt);
                        }
                    }

                    if(x == 12 && y == 2){
                        if(k == 1){
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.LEFT);
                            orientations.add(Orientation.RIGHT);
                            ConveyorBelt conveyorBelt = new ConveyorBelt("5B",2, orientations);
                            dizzyHighway.get(x).get(y).addTile(conveyorBelt);
                        }
                    }

                    if(x == 0 && y == 3){
                        if(k == 1){
                            StartPoint startPoint = new StartPoint("A");
                            dizzyHighway.get(x).get(y).addTile(startPoint);
                        }
                    }

                    if(x == 4 && y == 3){
                        if(k == 1){
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.BOTTOM);
                            orientations.add(Orientation.TOP);
                            ConveyorBelt conveyorBelt = new ConveyorBelt("5B",2, orientations);
                            dizzyHighway.get(x).get(y).addTile(conveyorBelt);
                        }
                    }

                    if(x == 6 && y == 3){
                        if(k == 1){
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.TOP);
                            Wall wall = new Wall( "5B", orientations);
                            dizzyHighway.get(x).get(y).addTile(wall);
                        }
                        if(k == 2){
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.TOP);
                            orientations.add(Orientation.BOTTOM);
                            Laser laser = new Laser( "5B", orientations,1);
                            dizzyHighway.get(x).get(y).addTile(laser);
                        }
                    }

                    if(x == 7 && y == 3){
                        if(k == 1){
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.BOTTOM);
                            RestartPoint restartPoint = new RestartPoint("5B",orientations);
                            dizzyHighway.get(x).get(y).addTile(restartPoint);
                        }
                    }

                    if(x == 8 && y == 3){
                        if(k == 1){
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.LEFT);
                            Wall wall = new Wall( "5B", orientations);
                            dizzyHighway.get(x).get(y).addTile(wall);
                        }

                        if(k == 2){
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.LEFT);
                            orientations.add(Orientation.RIGHT);
                            Laser laser = new Laser( "5B", orientations,1);
                            dizzyHighway.get(x).get(y).addTile(laser);
                        }
                    }

                    if(x == 9 && y == 3){
                        if(k == 1){
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.RIGHT);
                            Wall wall = new Wall( "5B", orientations);
                            dizzyHighway.get(x).get(y).addTile(wall);
                        }
                        if(k == 2){
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.RIGHT);
                            orientations.add(Orientation.LEFT);
                            Laser laser = new Laser( "5B", orientations,1);
                            dizzyHighway.get(x).get(y).addTile(laser);
                        }
                    }

                    if(x == 11 && y == 3){
                        if(k == 1){
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.TOP);
                            orientations.add(Orientation.BOTTOM);
                            ConveyorBelt conveyorBelt = new ConveyorBelt("5B",2, orientations);
                            dizzyHighway.get(x).get(y).addTile(conveyorBelt);
                        }
                    }

                    if(x == 12 && y == 3){
                        if(k == 1){
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.RIGHT);
                            CheckPoint checkPoint = new CheckPoint("5B",orientations,1);
                            dizzyHighway.get(x).get(y).addTile(checkPoint);
                        }
                    }

                    if(x == 0 && y == 4){
                        if(k == 1){
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.RIGHT);
                            Antenna antenna = new Antenna( "A", orientations);
                            dizzyHighway.get(x).get(y).addTile(antenna);
                        }
                    }

                    if(x == 1 && y == 4){
                        if(k == 1) {
                            StartPoint startPoint = new StartPoint( "A");
                            dizzyHighway.get(x).get(y).addTile(startPoint);
                        }
                    }

                    if(x == 2 && y == 4){
                        if(k == 1){
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.RIGHT);
                            Wall wall = new Wall( "A", orientations);
                            dizzyHighway.get(x).get(y).addTile(wall);
                        }
                    }

                    if(x == 4 && y == 4) {
                        if(k == 1){
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.BOTTOM);
                            orientations.add(Orientation.TOP);
                            ConveyorBelt conveyorBelt = new ConveyorBelt("5B",2, orientations);
                            dizzyHighway.get(x).get(y).addTile(conveyorBelt);
                        }
                    }

                    if(x == 6 && y == 4){
                        if(k == 1){
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.BOTTOM);
                            Wall wall = new Wall( "5B", orientations);
                            dizzyHighway.get(x).get(y).addTile(wall);
                        }

                        if(k == 2){
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.BOTTOM);
                            Laser laser = new Laser( "5B", orientations,1);
                            dizzyHighway.get(x).get(y).addTile(laser);
                        }
                    }

                    if(x == 8 && y == 4){
                        if(k == 1){
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.VERTICAL);
                            EnergySpace energySpace = new EnergySpace("5B", orientations, 1);
                            dizzyHighway.get(x).get(y).addTile(energySpace);
                        }
                    }

                    if(x == 11 && y == 4){
                        if(k == 1){
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.TOP);
                            orientations.add(Orientation.BOTTOM);
                            ConveyorBelt conveyorBelt = new ConveyorBelt("5B",2, orientations);
                            dizzyHighway.get(x).get(y).addTile(conveyorBelt);
                        }
                    }

                    if(x == 1 && y == 5){
                        if(k == 1){
                            StartPoint startPoint = new StartPoint( "A");
                            dizzyHighway.get(x).get(y).addTile(startPoint);
                        }
                    }

                    if(x == 2 && y == 5){
                        if(k == 1){
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.RIGHT);
                            Wall wall = new Wall( "5B", orientations);
                            dizzyHighway.get(x).get(y).addTile(wall);
                        }
                    }

                    if(x == 4 && y == 5){
                        if(k == 1){
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.BOTTOM);
                            orientations.add(Orientation.TOP);
                            ConveyorBelt conveyorBelt = new ConveyorBelt("5B",2, orientations);
                            dizzyHighway.get(x).get(y).addTile(conveyorBelt);
                        }
                    }

                    if(x == 7 && y == 5){
                        if(k == 1){
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.VERTICAL);
                            EnergySpace energySpace = new EnergySpace("5B", orientations, 1);
                            dizzyHighway.get(x).get(y).addTile(energySpace);
                        }
                    }

                    if(x == 9 && y == 5){
                        if(k == 1){
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.TOP);
                            Wall wall = new Wall( "5B", orientations);
                            dizzyHighway.get(x).get(y).addTile(wall);
                        }
                        if(k == 2){
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.TOP);
                            Laser laser = new Laser( "5B", orientations,1);
                            dizzyHighway.get(x).get(y).addTile(laser);
                        }
                    }

                    if(x == 11 && y == 5){
                        if(k == 1){
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.TOP);
                            orientations.add(Orientation.BOTTOM);
                            ConveyorBelt conveyorBelt = new ConveyorBelt("5B",2, orientations);
                            dizzyHighway.get(x).get(y).addTile(conveyorBelt);
                        }
                    }

                    if(x == 0 && y == 6){
                        if(k == 1){
                            StartPoint startPoint = new StartPoint( "A");
                            dizzyHighway.get(x).get(y).addTile(startPoint);
                        }
                    }

                    if(x == 4 && y == 6){
                        if(k == 1){
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.BOTTOM);
                            orientations.add(Orientation.TOP);
                            ConveyorBelt conveyorBelt = new ConveyorBelt("5B",2, orientations);
                            dizzyHighway.get(x).get(y).addTile(conveyorBelt);
                        }
                    }

                    if(x == 6 && y == 6){
                        if(k == 1){
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.LEFT);
                            Wall wall = new Wall( "5B", orientations);
                            dizzyHighway.get(x).get(y).addTile(wall);
                        }
                        if(k == 2){
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.LEFT);
                            orientations.add(Orientation.RIGHT);
                            Laser laser = new Laser( "5B", orientations,1);
                            dizzyHighway.get(x).get(y).addTile(laser);
                        }
                    }

                    if(x == 7 && y == 6){
                        if(k == 1){
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.RIGHT);
                            Wall wall = new Wall("5B", orientations);
                            dizzyHighway.get(x).get(y).addTile(wall);
                        }

                        if(k == 2){
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.RIGHT);
                            Laser laser = new Laser( "5B", orientations,1);
                            dizzyHighway.get(x).get(y).addTile(laser);
                        }
                    }

                    if(x == 9 && y == 6){
                        if(k == 1){
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.BOTTOM);
                            Wall wall = new Wall( "5B", orientations);
                            dizzyHighway.get(x).get(y).addTile(wall);
                        }
                        if(k == 2){
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.BOTTOM);
                            orientations.add(Orientation.TOP);
                            Laser laser = new Laser( "5B", orientations,1);
                            dizzyHighway.get(x).get(y).addTile(laser);
                        }
                    }

                    if(x == 11 && y == 6){
                        if(k == 1){
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.TOP);
                            orientations.add(Orientation.BOTTOM);
                            ConveyorBelt conveyorBelt = new ConveyorBelt("5B",2, orientations);
                            dizzyHighway.get(x).get(y).addTile(conveyorBelt);
                        }
                    }

                    if(x == 1 && y == 7){
                        if(k == 1){
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.BOTTOM);
                            Wall wall = new Wall("A", orientations);
                            dizzyHighway.get(x).get(y).addTile(wall);
                        }
                    }

                    if(x == 3 && y == 7){
                        if(k == 1){
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.RIGHT);
                            orientations.add(Orientation.LEFT);
                            ConveyorBelt conveyorBelt = new ConveyorBelt("5B",2, orientations);
                            dizzyHighway.get(x).get(y).addTile(conveyorBelt);
                        }
                    }

                    if(x == 4 && y == 7){
                        if(k == 1){
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.BOTTOM);
                            orientations.add(Orientation.TOP);
                            orientations.add(Orientation.LEFT);
                            ConveyorBelt conveyorBelt = new ConveyorBelt("5B",2, orientations);
                            dizzyHighway.get(x).get(y).addTile(conveyorBelt);
                        }
                    }

                    if(x == 10 && y == 7){
                        if(k == 1){
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.VERTICAL);
                            EnergySpace energySpace = new EnergySpace("5B", orientations, 1);
                            dizzyHighway.get(x).get(y).addTile(energySpace);
                        }
                    }

                    if(x == 11 && y == 7){
                        if(k == 2){
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.TOP);
                            orientations.add(Orientation.BOTTOM);
                            ConveyorBelt conveyorBelt = new ConveyorBelt("5B",2, orientations);
                            dizzyHighway.get(x).get(y).addTile(conveyorBelt);
                        }
                    }

                    if(x == 1 && y == 8){
                        if(k == 1){
                            StartPoint startPoint = new StartPoint( "A");
                            dizzyHighway.get(x).get(y).addTile(startPoint);

                        }
                    }

                    if(x == 3 && y == 8) {
                        if(k == 1){
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.RIGHT);
                            orientations.add(Orientation.LEFT);
                            ConveyorBelt conveyorBelt = new ConveyorBelt("5B",2, orientations);
                            dizzyHighway.get(x).get(y).addTile(conveyorBelt);
                        }
                    }

                    if(x == 4 && y == 8){
                        if(k == 1){
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.RIGHT);
                            orientations.add(Orientation.LEFT);
                            orientations.add(Orientation.TOP);
                            ConveyorBelt conveyorBelt = new ConveyorBelt("5B",2, orientations);
                            dizzyHighway.get(x).get(y).addTile(conveyorBelt);
                        }
                    }

                    if(x == 5 && y == 8){
                        if(k == 1){
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.RIGHT);
                            orientations.add(Orientation.LEFT);
                            ConveyorBelt conveyorBelt = new ConveyorBelt("5B",2, orientations);
                            dizzyHighway.get(x).get(y).addTile(conveyorBelt);
                        }
                    }

                    if(x == 6 && y == 8){
                        if(k == 1){
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.RIGHT);
                            orientations.add(Orientation.LEFT);
                            ConveyorBelt conveyorBelt = new ConveyorBelt("5B",2, orientations);
                            dizzyHighway.get(x).get(y).addTile(conveyorBelt);
                        }
                    }

                    if(x == 7 && y == 8){
                        if(k == 1){
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.RIGHT);
                            orientations.add(Orientation.LEFT);
                            ConveyorBelt conveyorBelt = new ConveyorBelt("5B",2, orientations);
                            dizzyHighway.get(x).get(y).addTile(conveyorBelt);
                        }
                    }

                    if(x == 8 && y == 8){
                        if(k == 1){
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.RIGHT);
                            orientations.add(Orientation.LEFT);
                            ConveyorBelt conveyorBelt = new ConveyorBelt("5B",2, orientations);
                            dizzyHighway.get(x).get(y).addTile(conveyorBelt);
                        }
                    }

                    if(x == 9 && y == 8){
                        if(k == 1){
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.RIGHT);
                            orientations.add(Orientation.LEFT);
                            ConveyorBelt conveyorBelt = new ConveyorBelt("5B",2, orientations);
                            dizzyHighway.get(x).get(y).addTile(conveyorBelt);
                        }
                    }

                    if(x == 10 && y == 8){
                        if(k == 1){
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.RIGHT);
                            orientations.add(Orientation.LEFT);
                            orientations.add(Orientation.BOTTOM);
                            ConveyorBelt conveyorBelt = new ConveyorBelt("5B",2, orientations);
                            dizzyHighway.get(x).get(y).addTile(conveyorBelt);
                        }
                    }

                    if(x == 11 && y == 8 ){
                        if(k == 1){
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.TOP);
                            orientations.add(Orientation.BOTTOM);
                            orientations.add(Orientation.LEFT);
                            ConveyorBelt conveyorBelt = new ConveyorBelt("5B",2, orientations);
                            dizzyHighway.get(x).get(y).addTile(conveyorBelt);
                        }
                    }

                    if(x == 2 && y == 9){
                        if(k == 1){
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.RIGHT);
                            orientations.add(Orientation.LEFT);
                            ConveyorBelt conveyorBelt = new ConveyorBelt("A",1, orientations);
                            dizzyHighway.get(x).get(y).addTile(conveyorBelt);
                        }
                    }

                    if(x == 3 && y == 9){
                        if(k == 1){
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.VERTICAL);
                            EnergySpace energySpace = new EnergySpace("5B", orientations, 1);
                            dizzyHighway.get(x).get(y).addTile(energySpace);
                        }
                    }

                    if(x == 10 && y == 9) {
                        if (k == 1) {
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.TOP);
                            orientations.add(Orientation.BOTTOM);
                            ConveyorBelt conveyorBelt = new ConveyorBelt("5B",2, orientations);
                            dizzyHighway.get(x).get(y).addTile(conveyorBelt);
                        }
                    }

                    if(x == 11 && y == 9){
                        if(k == 1){
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.TOP);
                            orientations.add(Orientation.BOTTOM);
                            ConveyorBelt conveyorBelt = new ConveyorBelt("5B",2, orientations);
                            dizzyHighway.get(x).get(y).addTile(conveyorBelt);
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
