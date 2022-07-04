package bb.roborally.game.map;


import bb.roborally.game.Orientation;
import bb.roborally.game.Position;
import bb.roborally.game.tiles.*;


import java.util.ArrayList;


/**
 * creates Map Dizzy HighWay
 *
 * @author Veronika Heckel
 */
public class DizzyHighway {

    //private ArrayList<ArrayList<ArrayList<Tile>>> dizzyHighway = new ArrayList<>();


    public static ArrayList<ArrayList<ArrayList<Tile>>> buildDizzyHighway() {
        int xAxis = 13;
        int yAxis = 10;
        int maxCellContent = 4;

        ArrayList<ArrayList<ArrayList<Tile>>> dizzyHighway = new ArrayList<ArrayList<ArrayList<Tile>>>();

        //for - Schleife x-Koordinaten (äußerste ArrayList)
        for (int i = 0; i < xAxis; i++) {
            dizzyHighway.add(new ArrayList<ArrayList<Tile>>());

            //for - Schleife y-Koordinaten (mittlere ArrayList)
            for (int j = 0; j < yAxis; j++) {
                dizzyHighway.get(i).add(new ArrayList<Tile>());

                //for - Schleife Cells (innerste ArrayList)
               for(int k = 0; k < maxCellContent; k++){
                   if(k == 0){
                       Floor floor = new Floor("Floor", "A");
                       Position positionFloor = new Position(i,j);
                       floor.setFloorPosition(positionFloor);
                       dizzyHighway.get(i).get(j).add(floor);
                   }


                   if(i == 2 && j == 0){
                       if(k == 1){
                           ArrayList<Orientation> orientations = new ArrayList<>();
                           orientations.add(Orientation.RIGHT);
                           orientations.add(Orientation.LEFT);
                           ConveyorBelt conveyorBelt = new ConveyorBelt("ConveyorBelt","5B",1, orientations);
                           conveyorBelt.setPosition(new Position(i,j));
                           conveyorBelt.setActivationOrder(2);
                           dizzyHighway.get(i).get(j).add(conveyorBelt);

                       }
                    }

                    if(i == 4 && j == 0){
                        if(k == 1) {
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.BOTTOM);
                            orientations.add(Orientation.TOP);
                            ConveyorBelt conveyorBelt = new ConveyorBelt("ConveyorBelt","5B",2, orientations);
                            conveyorBelt.setPosition(new Position(i,j));
                            conveyorBelt.setActivationOrder(1);
                            dizzyHighway.get(i).get(j).add(conveyorBelt);
                        }
                    }

                    if(i == 5 && j == 0){
                        if(k == 1){
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.BOTTOM);
                            orientations.add(Orientation.TOP);
                            ConveyorBelt conveyorBelt = new ConveyorBelt("ConveyorBelt","5B",2, orientations);
                            conveyorBelt.setPosition(new Position(i,j));
                            conveyorBelt.setActivationOrder(1);
                            dizzyHighway.get(i).get(j).add(conveyorBelt);
                        }
                    }

                    if(i == 12 && j == 0){
                        if(k == 1){
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.VERTICAL);
                            EnergySpace energySpace = new EnergySpace("EnergySpace","5B", orientations, 1);
                            energySpace.setPosition(new Position(i,j));
                            dizzyHighway.get(i).get(j).add(energySpace);
                        }
                    }

                    if(i == 1 && j == 1){
                        if(k == 1) {
                            StartPoint startPoint = new StartPoint("StartPoint", "A");
                            startPoint.setPosition(new Position(i, j));
                            dizzyHighway.get(i).get(j).add(startPoint);
                        }
                    }


                    if(i == 4 && j == 1){
                        if(k == 1){
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.BOTTOM);
                            orientations.add(Orientation.TOP);
                            orientations.add(Orientation.RIGHT);
                            ConveyorBelt conveyorBelt = new ConveyorBelt("ConveyorBelt","5B",2, orientations);
                            conveyorBelt.setPosition(new Position(i,j));
                            conveyorBelt.setActivationOrder(1);
                            dizzyHighway.get(i).get(j).add(conveyorBelt);
                        }
                    }

                    if(i == 5 && j == 1){
                        if(k == 1){
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.LEFT);
                            orientations.add(Orientation.RIGHT);
                            orientations.add(Orientation.TOP);
                            ConveyorBelt conveyorBelt = new ConveyorBelt("ConveyorBelt","5B",2, orientations);
                            conveyorBelt.setPosition(new Position(i,j));
                            conveyorBelt.setActivationOrder(1);
                            dizzyHighway.get(i).get(j).add(conveyorBelt);
                        }
                    }

                    if(i == 6 && j == 1){
                        if(k == 1){
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.LEFT);
                            orientations.add(Orientation.RIGHT);
                            ConveyorBelt conveyorBelt = new ConveyorBelt("ConveyorBelt","5B",2, orientations);
                            conveyorBelt.setPosition(new Position(i,j));
                            conveyorBelt.setActivationOrder(2);
                            dizzyHighway.get(i).get(j).add(conveyorBelt);
                        }
                    }

                    if(i == 7 && j == 1){
                        if(k == 1){
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.LEFT);
                            orientations.add(Orientation.RIGHT);
                            ConveyorBelt conveyorBelt = new ConveyorBelt("ConveyorBelt","5B",2, orientations);
                            conveyorBelt.setPosition(new Position(i,j));
                            conveyorBelt.setActivationOrder(1);
                            dizzyHighway.get(i).get(j).add(conveyorBelt);
                        }
                    }

                    if(i == 8 && j == 1){
                        if(k == 2){
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.LEFT);
                            orientations.add(Orientation.RIGHT);
                            ConveyorBelt conveyorBelt = new ConveyorBelt("ConveyorBelt","5B",2, orientations);
                            conveyorBelt.setPosition(new Position(i,j));
                            conveyorBelt.setActivationOrder(1);
                            dizzyHighway.get(i).get(j).add(conveyorBelt);
                        }
                    }

                    if(i == 9 && j == 1){
                        if(k == 1){
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.LEFT);
                            orientations.add(Orientation.RIGHT);
                            ConveyorBelt conveyorBelt = new ConveyorBelt("ConveyorBelt","5B",2, orientations);
                            conveyorBelt.setPosition(new Position(i,j));
                            conveyorBelt.setActivationOrder(1);
                            dizzyHighway.get(i).get(j).add(conveyorBelt);
                        }
                    }

                    if(i == 10 && j == 1){
                        if(k == 1){
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.LEFT);
                            orientations.add(Orientation.RIGHT);
                            ConveyorBelt conveyorBelt = new ConveyorBelt("ConveyorBelt","5B",2, orientations);
                            conveyorBelt.setPosition(new Position(i,j));
                            conveyorBelt.setActivationOrder(1);
                            dizzyHighway.get(i).get(j).add(conveyorBelt);
                        }
                    }

                    if(i == 11 && j == 1) {
                        if(k == 1){
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.LEFT);
                            orientations.add(Orientation.RIGHT);
                            orientations.add(Orientation.BOTTOM);
                            ConveyorBelt conveyorBelt = new ConveyorBelt("ConveyorBelt","5B",2, orientations);
                            conveyorBelt.setPosition(new Position(i,j));
                            conveyorBelt.setActivationOrder(1);
                            dizzyHighway.get(i).get(j).add(conveyorBelt);
                        }
                    }

                    if(i == 12 && j == 1){
                        if(k == 1){
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.LEFT);
                            orientations.add(Orientation.RIGHT);
                            ConveyorBelt conveyorBelt = new ConveyorBelt("ConveyorBelt","5B",2, orientations);
                            conveyorBelt.setPosition(new Position(i,j));
                            conveyorBelt.setActivationOrder(1);
                            dizzyHighway.get(i).get(j).add(conveyorBelt);
                        }
                    }

                    if(i == 1 && j == 2){
                        if(k == 2){
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.TOP);
                            Wall wall = new Wall("Wall", "A", orientations);
                            wall.setWallPosition(new Position(i,j));
                            dizzyHighway.get(i).get(j).add(wall);
                        }
                    }

                    if(i == 4 && j == 2){
                        if(k == 1){
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.BOTTOM);
                            orientations.add(Orientation.TOP);
                            ConveyorBelt conveyorBelt = new ConveyorBelt("ConveyorBelt","5B",2, orientations);
                            conveyorBelt.setPosition(new Position(i,j));
                            conveyorBelt.setActivationOrder(1);
                            dizzyHighway.get(i).get(j).add(conveyorBelt);
                        }
                    }

                    if(i == 11 && j == 2){
                        if(k == 1){
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.TOP);
                            orientations.add(Orientation.BOTTOM);
                            orientations.add(Orientation.RIGHT);
                            ConveyorBelt conveyorBelt = new ConveyorBelt("ConveyorBelt","5B",2, orientations);
                            conveyorBelt.setPosition(new Position(i,j));
                            conveyorBelt.setActivationOrder(1);
                            dizzyHighway.get(i).get(j).add(conveyorBelt);
                        }
                    }

                    if(i == 12 && j == 2){
                        if(k == 1){
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.LEFT);
                            orientations.add(Orientation.RIGHT);
                            ConveyorBelt conveyorBelt = new ConveyorBelt("ConveyorBelt","5B",2, orientations);
                            conveyorBelt.setPosition(new Position(i,j));
                            conveyorBelt.setActivationOrder(1);
                            dizzyHighway.get(i).get(j).add(conveyorBelt);
                        }
                    }

                    if(i == 5 && j == 2){
                        if(k == 1){
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.VERTICAL);
                            EnergySpace energySpace = new EnergySpace("EnergySpace","5B", orientations, 1);
                            energySpace.setPosition(new Position(i,j));
                            dizzyHighway.get(i).get(j).add(energySpace);
                        }
                    }

                    if(i == 0 && j == 3){
                        if(k == 1){
                            StartPoint startPoint = new StartPoint("StartPoint", "A");
                            startPoint.setPosition(new Position(i, j));
                            dizzyHighway.get(i).get(j).add(startPoint);
                        }
                    }

                    if(i == 4 && j == 3){
                        if(k == 1){
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.BOTTOM);
                            orientations.add(Orientation.TOP);
                            ConveyorBelt conveyorBelt = new ConveyorBelt("ConveyorBelt","5B",2, orientations);
                            conveyorBelt.setPosition(new Position(i,j));
                            conveyorBelt.setActivationOrder(1);
                            dizzyHighway.get(i).get(j).add(conveyorBelt);
                        }
                    }

                    if(i == 6 && j == 3){
                        if(k == 1){
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.TOP);
                            Wall wall = new Wall("Wall", "5B", orientations);
                            wall.setWallPosition(new Position(i,j));
                            dizzyHighway.get(i).get(j).add(wall);
                        }
                        if(k == 2){
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.BOTTOM);
                            Laser laser = new Laser("Laser", "5B", orientations,1);
                            laser.setBoardLaserPosition(new Position(i,j));
                            dizzyHighway.get(i).get(j).add(laser);
                        }
                    }

                    if(i == 7 && j == 3){
                        if(k == 1){
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.RIGHT);
                            RebootPoint rebootPoint = new RebootPoint("RebootPoint","5B",orientations);
                            dizzyHighway.get(i).get(j).add(rebootPoint);
                        }
                    }

                    if(i == 8 && j == 3){
                        if(k == 1){
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.LEFT);
                            Wall wall = new Wall("Wall", "5B", orientations);
                            wall.setWallPosition(new Position(i,j));
                            dizzyHighway.get(i).get(j).add(wall);
                        }

                        if(k == 2){
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.LEFT);
                            Laser laser = new Laser("Laser", "5B", orientations,1);
                            laser.setBoardLaserPosition(new Position(i,j));
                            dizzyHighway.get(i).get(j).add(laser);
                        }
                    }

                    if(i == 9 && j == 3){
                        if(k == 1){
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.RIGHT);
                            Wall wall = new Wall("Wall", "5B", orientations);
                            wall.setWallPosition(new Position(i,j));
                            dizzyHighway.get(i).get(j).add(wall);
                        }
                        if(k == 2){
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.LEFT);
                            Laser laser = new Laser("Laser", "5B", orientations,1);
                            laser.setBoardLaserPosition(new Position(i,j));
                            dizzyHighway.get(i).get(j).add(laser);
                        }
                    }

                    if(i == 11 && j == 3){
                        if(k == 1){
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.TOP);
                            orientations.add(Orientation.BOTTOM);
                            ConveyorBelt conveyorBelt = new ConveyorBelt("ConveyorBelt","5B",2, orientations);
                            conveyorBelt.setPosition(new Position(i,j));
                            conveyorBelt.setActivationOrder(1);
                            dizzyHighway.get(i).get(j).add(conveyorBelt);
                        }
                    }

                    if(i == 12 && j == 3){
                        if(k == 1){
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.LEFT);
                            CheckPoint checkPoint = new CheckPoint("CheckPoint","5B",orientations,1);
                            checkPoint.setPosition(new Position(i, j));
                            dizzyHighway.get(i).get(j).add(checkPoint);
                        }
                    }

                    if(i == 0 && j == 4){
                        if(k == 1){
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.RIGHT);
                            Antenna antenna = new Antenna("Antenna", "A", orientations);
                            antenna.setAntennaPosition(new Position(i, j));
                            dizzyHighway.get(i).get(j).add(antenna);
                        }
                    }

                    if(i == 1 && j == 4){
                        if(k == 1) {
                            StartPoint startPoint = new StartPoint("StartPoint", "A");
                            startPoint.setPosition(new Position(i, j));
                            dizzyHighway.get(i).get(j).add(startPoint);
                        }
                    }

                    if(i == 2 && j == 4){
                        if(k == 1){
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.RIGHT);
                            Wall wall = new Wall("Wall", "A", orientations);
                            wall.setWallPosition(new Position(i,j));
                            dizzyHighway.get(i).get(j).add(wall);
                        }
                    }

                    if(i == 4 && j == 4) {
                        if(k == 1){
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.BOTTOM);
                            orientations.add(Orientation.TOP);
                            ConveyorBelt conveyorBelt = new ConveyorBelt("ConveyorBelt","5B",2, orientations);
                            conveyorBelt.setPosition(new Position(i,j));
                            conveyorBelt.setActivationOrder(1);
                            dizzyHighway.get(i).get(j).add(conveyorBelt);
                        }
                    }

                    if(i == 6 && j == 4){
                        if(k == 1){
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.BOTTOM);
                            Wall wall = new Wall("Wall", "5B", orientations);
                            wall.setWallPosition(new Position(i,j));
                            dizzyHighway.get(i).get(j).add(wall);
                        }

                        if(k == 2){
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.BOTTOM);
                            Laser laser = new Laser("Laser", "5B", orientations,1);
                            laser.setBoardLaserPosition(new Position(i,j));
                            dizzyHighway.get(i).get(j).add(laser);
                        }
                    }

                    if(i == 8 && j == 4){
                        if(k == 1){
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.VERTICAL);
                            EnergySpace energySpace = new EnergySpace("EnergySpace","5B", orientations, 1);
                            energySpace.setPosition(new Position(i,j));
                            dizzyHighway.get(i).get(j).add(energySpace);
                        }
                    }

                    if(i == 11 && j == 4){
                        if(k == 1){
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.TOP);
                            orientations.add(Orientation.BOTTOM);
                            ConveyorBelt conveyorBelt = new ConveyorBelt("ConveyorBelt","5B",2, orientations);
                            conveyorBelt.setPosition(new Position(i,j));
                            conveyorBelt.setActivationOrder(1);
                            dizzyHighway.get(i).get(j).add(conveyorBelt);
                        }
                    }

                    if(i == 1 && j == 5){
                        if(k == 1){
                            StartPoint startPoint = new StartPoint("StartPoint", "A");
                            startPoint.setPosition(new Position(i, j));
                            dizzyHighway.get(i).get(j).add(startPoint);
                        }
                    }

                    if(i == 2 && j == 5){
                        if(k == 1){
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.RIGHT);
                            Wall wall = new Wall("Wall", "5B", orientations);
                            wall.setWallPosition(new Position(i,j));
                            dizzyHighway.get(i).get(j).add(wall);
                        }
                    }

                    if(i == 4 && j == 5){
                        if(k == 1){
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.BOTTOM);
                            orientations.add(Orientation.TOP);
                            ConveyorBelt conveyorBelt = new ConveyorBelt("ConveyorBelt","5B",2, orientations);
                            conveyorBelt.setPosition(new Position(i,j));
                            conveyorBelt.setActivationOrder(1);
                            dizzyHighway.get(i).get(j).add(conveyorBelt);
                        }
                    }

                    if(i == 7 && j == 5){
                        if(k == 1){
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.VERTICAL);
                            EnergySpace energySpace = new EnergySpace("EnergySpace","5B", orientations, 1);
                            energySpace.setPosition(new Position(i,j));
                            dizzyHighway.get(i).get(j).add(energySpace);
                        }
                    }

                    if(i == 9 && j == 5){
                        if(k == 1){
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.TOP);
                            Wall wall = new Wall("Wall", "5B", orientations);
                            wall.setWallPosition(new Position(i,j));
                            dizzyHighway.get(i).get(j).add(wall);
                        }
                        if(k == 2){
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.TOP);
                            Laser laser = new Laser("Laser", "5B", orientations,1);
                            laser.setBoardLaserPosition(new Position(i,j));
                            dizzyHighway.get(i).get(j).add(laser);
                        }
                    }

                    if(i == 11 && j == 5){
                        if(k == 1){
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.TOP);
                            orientations.add(Orientation.BOTTOM);
                            ConveyorBelt conveyorBelt = new ConveyorBelt("ConveyorBelt","5B",2, orientations);
                            conveyorBelt.setPosition(new Position(i,j));
                            conveyorBelt.setActivationOrder(1);
                            dizzyHighway.get(i).get(j).add(conveyorBelt);
                        }
                    }

                    if(i == 0 && j == 6){
                        if(k == 1){
                            StartPoint startPoint = new StartPoint("StartPoint", "A");
                            startPoint.setPosition(new Position(i, j));
                            dizzyHighway.get(i).get(j).add(startPoint);
                        }
                    }

                    if(i == 4 && j == 6){
                        if(k == 1){
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.BOTTOM);
                            orientations.add(Orientation.TOP);
                            ConveyorBelt conveyorBelt = new ConveyorBelt("ConveyorBelt","5B",2, orientations);
                            conveyorBelt.setPosition(new Position(i,j));
                            conveyorBelt.setActivationOrder(1);
                            dizzyHighway.get(i).get(j).add(conveyorBelt);
                        }
                    }

                    if(i == 6 && j == 6){
                        if(k == 1){
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.LEFT);
                            Wall wall = new Wall("Wall", "5B", orientations);
                            wall.setWallPosition(new Position(i,j));
                            dizzyHighway.get(i).get(j).add(wall);
                        }
                    }

                    if(i == 7 && j == 6){
                        if(k == 1){
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.RIGHT);
                            Wall wall = new Wall("Wall", "5B", orientations);
                            wall.setWallPosition(new Position(i,j));
                            dizzyHighway.get(i).get(j).add(wall);
                        }

                        if(k == 2){
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.RIGHT);
                            Laser laser = new Laser("Laser", "5B", orientations,1);
                            laser.setBoardLaserPosition(new Position(i,j));
                            dizzyHighway.get(i).get(j).add(laser);
                        }
                    }

                    if(i == 9 && j == 6){
                        if(k == 1){
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.BOTTOM);
                            Wall wall = new Wall("Wall", "5B", orientations);
                            wall.setWallPosition(new Position(i,j));
                            dizzyHighway.get(i).get(j).add(wall);
                        }
                        if(k == 2){
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.TOP);
                            Laser laser = new Laser("Laser", "5B", orientations,1);
                            laser.setBoardLaserPosition(new Position(i,j));
                            dizzyHighway.get(i).get(j).add(laser);
                        }
                    }

                    if(i == 11 && j == 6){
                        if(k == 1){
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.TOP);
                            orientations.add(Orientation.BOTTOM);
                            ConveyorBelt conveyorBelt = new ConveyorBelt("ConveyorBelt","5B",2, orientations);
                            conveyorBelt.setPosition(new Position(i,j));
                            conveyorBelt.setActivationOrder(1);
                            dizzyHighway.get(i).get(j).add(conveyorBelt);
                        }
                    }

                    if(i == 1 && j == 7){
                        if(k == 1){
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.BOTTOM);
                            Wall wall = new Wall("Wall", "A", orientations);
                            wall.setWallPosition(new Position(i,j));
                            dizzyHighway.get(i).get(j).add(wall);
                        }
                    }

                    if(i == 3 && j == 7){
                        if(k == 1){
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.RIGHT);
                            orientations.add(Orientation.LEFT);
                            ConveyorBelt conveyorBelt = new ConveyorBelt("ConveyorBelt","5B",2, orientations);
                            conveyorBelt.setPosition(new Position(i,j));
                            conveyorBelt.setActivationOrder(1);
                            dizzyHighway.get(i).get(j).add(conveyorBelt);
                        }
                    }

                    if(i == 4 && j == 7){
                        if(k == 1){
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.BOTTOM);
                            orientations.add(Orientation.TOP);
                            orientations.add(Orientation.LEFT);
                            ConveyorBelt conveyorBelt = new ConveyorBelt("ConveyorBelt","5B",2, orientations);
                            conveyorBelt.setPosition(new Position(i,j));
                            conveyorBelt.setActivationOrder(1);
                            dizzyHighway.get(i).get(j).add(conveyorBelt);
                        }
                    }

                    if(i == 10 && j == 7){
                        if(k == 1){
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.VERTICAL);
                            EnergySpace energySpace = new EnergySpace("EnergySpace","5B", orientations, 1);
                            energySpace.setPosition(new Position(i,j));
                            dizzyHighway.get(i).get(j).add(energySpace);
                        }
                    }

                    if(i == 11 && j == 7){
                        if(k == 2){
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.TOP);
                            orientations.add(Orientation.BOTTOM);
                            ConveyorBelt conveyorBelt = new ConveyorBelt("ConveyorBelt","5B",2, orientations);
                            conveyorBelt.setPosition(new Position(i,j));
                            conveyorBelt.setActivationOrder(1);
                            dizzyHighway.get(i).get(j).add(conveyorBelt);
                        }
                    }

                    if(i == 1 && j == 8){
                        if(k == 1){
                            StartPoint startPoint = new StartPoint("StartPoint", "A");
                            startPoint.setPosition(new Position(i, j));
                            dizzyHighway.get(i).get(j).add(startPoint);

                        }
                    }

                    if(i == 3 && j == 8) {
                        if(k == 1){
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.RIGHT);
                            orientations.add(Orientation.LEFT);
                            ConveyorBelt conveyorBelt = new ConveyorBelt("ConveyorBelt","5B",2, orientations);
                            conveyorBelt.setPosition(new Position(i,j));
                            conveyorBelt.setActivationOrder(1);
                            dizzyHighway.get(i).get(j).add(conveyorBelt);
                        }
                    }

                    if(i == 4 && j == 8){
                        if(k == 1){
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.RIGHT);
                            orientations.add(Orientation.LEFT);
                            orientations.add(Orientation.TOP);
                            ConveyorBelt conveyorBelt = new ConveyorBelt("ConveyorBelt","5B",2, orientations);
                            conveyorBelt.setPosition(new Position(i,j));
                            conveyorBelt.setActivationOrder(1);
                            dizzyHighway.get(i).get(j).add(conveyorBelt);
                        }
                    }

                    if(i == 5 && j == 8){
                        if(k == 1){
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.RIGHT);
                            orientations.add(Orientation.LEFT);
                            ConveyorBelt conveyorBelt = new ConveyorBelt("ConveyorBelt","5B",2, orientations);
                            conveyorBelt.setPosition(new Position(i,j));
                            conveyorBelt.setActivationOrder(1);
                            dizzyHighway.get(i).get(j).add(conveyorBelt);
                        }
                    }

                    if(i == 6 && j == 8){
                        if(k == 1){
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.RIGHT);
                            orientations.add(Orientation.LEFT);
                            ConveyorBelt conveyorBelt = new ConveyorBelt("ConveyorBelt","5B",2, orientations);
                            conveyorBelt.setPosition(new Position(i,j));
                            conveyorBelt.setActivationOrder(1);
                            dizzyHighway.get(i).get(j).add(conveyorBelt);
                        }
                    }

                    if(i == 7 && j == 8){
                        if(k == 1){
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.RIGHT);
                            orientations.add(Orientation.LEFT);
                            ConveyorBelt conveyorBelt = new ConveyorBelt("ConveyorBelt","5B",2, orientations);
                            conveyorBelt.setPosition(new Position(i,j));
                            conveyorBelt.setActivationOrder(1);
                            dizzyHighway.get(i).get(j).add(conveyorBelt);
                        }
                    }

                    if(i == 8 && j == 8){
                        if(k == 1){
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.RIGHT);
                            orientations.add(Orientation.LEFT);
                            ConveyorBelt conveyorBelt = new ConveyorBelt("ConveyorBelt","5B",2, orientations);
                            conveyorBelt.setPosition(new Position(i,j));
                            conveyorBelt.setActivationOrder(1);
                            dizzyHighway.get(i).get(j).add(conveyorBelt);
                        }
                    }

                    if(i == 9 && j == 8){
                        if(k == 1){
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.RIGHT);
                            orientations.add(Orientation.LEFT);
                            ConveyorBelt conveyorBelt = new ConveyorBelt("ConveyorBelt","5B",2, orientations);
                            conveyorBelt.setPosition(new Position(i,j));
                            conveyorBelt.setActivationOrder(1);
                            dizzyHighway.get(i).get(j).add(conveyorBelt);
                        }
                    }

                    if(i == 10 && j == 8){
                        if(k == 1){
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.RIGHT);
                            orientations.add(Orientation.LEFT);
                            orientations.add(Orientation.BOTTOM);
                            ConveyorBelt conveyorBelt = new ConveyorBelt("ConveyorBelt","5B",2, orientations);
                            conveyorBelt.setPosition(new Position(i,j));
                            conveyorBelt.setActivationOrder(1);
                            dizzyHighway.get(i).get(j).add(conveyorBelt);
                        }
                    }

                    if(i == 11 && j == 8 ){
                        if(k == 1){
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.TOP);
                            orientations.add(Orientation.RIGHT);
                            orientations.add(Orientation.BOTTOM);
                            ConveyorBelt conveyorBelt = new ConveyorBelt("ConveyorBelt","5B",2, orientations);
                            conveyorBelt.setPosition(new Position(i,j));
                            conveyorBelt.setActivationOrder(1);
                            dizzyHighway.get(i).get(j).add(conveyorBelt);
                        }
                    }

                    if(i == 2 && j == 9){
                        if(k == 1){
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.RIGHT);
                            orientations.add(Orientation.LEFT);
                            ConveyorBelt conveyorBelt = new ConveyorBelt("ConveyorBelt","A",1, orientations);
                            conveyorBelt.setPosition(new Position(i,j));
                            conveyorBelt.setActivationOrder(2);
                            dizzyHighway.get(i).get(j).add(conveyorBelt);
                        }
                    }

                    if(i == 3 && j == 9){
                        if(k == 1){
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.VERTICAL);
                            EnergySpace energySpace = new EnergySpace("EnergySpace","5B", orientations, 1);
                            energySpace.setPosition(new Position(i,j));
                            dizzyHighway.get(i).get(j).add(energySpace);
                        }
                    }

                    if(i == 10 && j == 9) {
                        if (k == 1) {
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.TOP);
                            orientations.add(Orientation.BOTTOM);
                            ConveyorBelt conveyorBelt = new ConveyorBelt("ConveyorBelt","5B",2, orientations);
                            conveyorBelt.setPosition(new Position(i,j));
                            conveyorBelt.setActivationOrder(1);
                            dizzyHighway.get(i).get(j).add(conveyorBelt);
                        }
                    }

                    if(i == 11 && j == 9){
                        if(k == 1){
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.TOP);
                            orientations.add(Orientation.BOTTOM);
                            ConveyorBelt conveyorBelt = new ConveyorBelt("ConveyorBelt","5B",2, orientations);
                            conveyorBelt.setPosition(new Position(i,j));
                            conveyorBelt.setActivationOrder(1);
                            dizzyHighway.get(i).get(j).add(conveyorBelt);
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
