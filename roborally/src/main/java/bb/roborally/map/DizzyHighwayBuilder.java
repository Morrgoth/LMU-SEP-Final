package bb.roborally.map;


import bb.roborally.protocol.map.Board;
import bb.roborally.protocol.map.Cell;
import bb.roborally.protocol.map.GameStarted;
import bb.roborally.protocol.map.tiles.*;
import bb.roborally.protocol.Orientation;

import java.util.ArrayList;


/**
 * creates Map Dizzy HighWay
 *
 * @author Muqiu Wang
 */
public class DizzyHighwayBuilder implements BoardBuilder {

    @Override
    public GameStarted build() {

        final int X_MAX = 13;
        final int Y_MAX = 10;
        final int maxCellContent = 4;

        ArrayList<ArrayList<Cell>> dizzyHighway = new ArrayList<>();

        for (int x = 0; x < X_MAX; x++) {
            dizzyHighway.add(new ArrayList<>());

            for (int y = 0; y < Y_MAX; y++) {
                dizzyHighway.get(x).add(new Cell());

                for(int k = 0; k < maxCellContent; k++){

                    if (x == 0 && y == 0){
                        if (k == 0) {
                            Empty empty = new Empty("A");
                            dizzyHighway.get(x).get(y).addTile(empty);
                        }
                    }

                    if (x == 1 && y == 0){
                        if (k==0){
                            Empty empty = new Empty("A");
                            dizzyHighway.get(x).get(y).addTile(empty);
                        }
                    }


                    if(x == 2 && y == 0){
                        if(k == 0){
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.RIGHT);
                            orientations.add(Orientation.LEFT);
                            ConveyorBelt conveyorBelt = new ConveyorBelt("A",1, orientations);
                            dizzyHighway.get(x).get(y).addTile(conveyorBelt);

                        }
                    }

                    if(x == 3 && y == 0){
                        Empty empty = new Empty("5B");
                        dizzyHighway.get(x).get(y).addTile(empty);
                    }

                    if(x == 4 && y == 0){
                        if(k == 0) {
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.BOTTOM);
                            orientations.add(Orientation.TOP);
                            ConveyorBelt conveyorBelt = new ConveyorBelt("5B",2, orientations);
                            dizzyHighway.get(x).get(y).addTile(conveyorBelt);
                        }
                    }

                    if(x == 5 && y == 0){
                        if(k == 0){
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.BOTTOM);
                            orientations.add(Orientation.TOP);
                            ConveyorBelt conveyorBelt = new ConveyorBelt("5B",2, orientations);
                            dizzyHighway.get(x).get(y).addTile(conveyorBelt);
                        }
                    }

                    if((x >= 6) && (x <= 11) && y == 0){
                        Empty empty = new Empty("5B");
                        dizzyHighway.get(x).get(y).addTile(empty);
                    }

                    if(x == 12 && y == 0){
                        if(k == 0){
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.TOP);
                            EnergySpace energySpace = new EnergySpace("5B", orientations, 1);
                            dizzyHighway.get(x).get(y).addTile(energySpace);
                        }
                    }

                    if(x == 0 && y == 1){
                        Empty empty = new Empty("A");
                        dizzyHighway.get(x).get(y).addTile(empty);
                    }

                    if(x == 1 && y == 1){
                        if(k == 0) {
                            StartPoint startPoint = new StartPoint( "A");
                            dizzyHighway.get(x).get(y).addTile(startPoint);
                        }
                    }

                    if((x == 2 || x == 3) && y == 1){
                        Empty empty = new Empty("A");
                        dizzyHighway.get(x).get(y).addTile(empty);
                    }


                    if(x == 4 && y == 1){
                        if(k == 0){
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.BOTTOM);
                            orientations.add(Orientation.TOP);
                            orientations.add(Orientation.RIGHT);
                            ConveyorBelt conveyorBelt = new ConveyorBelt("5B",2, orientations);
                            dizzyHighway.get(x).get(y).addTile(conveyorBelt);
                        }
                    }

                    if(x == 5 && y == 1){
                        if(k == 0){
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.LEFT);
                            orientations.add(Orientation.RIGHT);
                            orientations.add(Orientation.TOP);
                            ConveyorBelt conveyorBelt = new ConveyorBelt("5B",2, orientations);
                            dizzyHighway.get(x).get(y).addTile(conveyorBelt);
                        }
                    }

                    if(x == 6 && y == 1){
                        if(k == 0){
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.LEFT);
                            orientations.add(Orientation.RIGHT);
                            ConveyorBelt conveyorBelt = new ConveyorBelt("5B",2, orientations);
                            dizzyHighway.get(x).get(y).addTile(conveyorBelt);
                        }
                    }

                    if(x == 7 && y == 1){
                        if(k == 0){
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.LEFT);
                            orientations.add(Orientation.RIGHT);
                            ConveyorBelt conveyorBelt = new ConveyorBelt("5B",2, orientations);
                            dizzyHighway.get(x).get(y).addTile(conveyorBelt);
                        }
                    }

                    if(x == 8 && y == 1){
                        if(k == 1){
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.LEFT);
                            orientations.add(Orientation.RIGHT);
                            ConveyorBelt conveyorBelt = new ConveyorBelt("5B",2, orientations);
                            dizzyHighway.get(x).get(y).addTile(conveyorBelt);
                        }
                    }

                    if(x == 9 && y == 1){
                        if(k == 0){
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.LEFT);
                            orientations.add(Orientation.RIGHT);
                            ConveyorBelt conveyorBelt = new ConveyorBelt("5B",2, orientations);
                            dizzyHighway.get(x).get(y).addTile(conveyorBelt);
                        }
                    }

                    if(x == 10 && y == 1){
                        if(k == 0){
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.LEFT);
                            orientations.add(Orientation.RIGHT);
                            ConveyorBelt conveyorBelt = new ConveyorBelt("5B",2, orientations);
                            dizzyHighway.get(x).get(y).addTile(conveyorBelt);
                        }
                    }

                    if(x == 11 && y == 1) {
                        if(k == 0){
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.LEFT);
                            orientations.add(Orientation.RIGHT);
                            orientations.add(Orientation.BOTTOM);
                            ConveyorBelt conveyorBelt = new ConveyorBelt("5B",2, orientations);
                            dizzyHighway.get(x).get(y).addTile(conveyorBelt);
                        }
                    }

                    if(x == 12 && y == 1){
                        if(k == 0){
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.LEFT);
                            orientations.add(Orientation.RIGHT);
                            ConveyorBelt conveyorBelt = new ConveyorBelt("5B",2, orientations);
                            dizzyHighway.get(x).get(y).addTile(conveyorBelt);
                        }
                    }

                    if(x == 0 && y == 2){
                        Empty empty = new Empty("A");
                        dizzyHighway.get(x).get(y).addTile(empty);
                    }

                    if(x == 1 && y == 2){
                        if(k == 0){
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.TOP);
                            Wall wall = new Wall( "A", orientations);
                            dizzyHighway.get(x).get(y).addTile(wall);
                        }
                    }

                    if(x == 2 && y == 2){
                        Empty empty = new Empty("A");
                        dizzyHighway.get(x).get(y).addTile(empty);
                    }

                    if(x == 3 && y == 2){
                        Empty empty = new Empty("5B");
                        dizzyHighway.get(x).get(y).addTile(empty);
                    }

                    if(x == 4 && y == 2){
                        if(k == 0){
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.BOTTOM);
                            orientations.add(Orientation.TOP);
                            ConveyorBelt conveyorBelt = new ConveyorBelt("5B",2, orientations);
                            dizzyHighway.get(x).get(y).addTile(conveyorBelt);
                        }
                    }

                    if(x == 5 && y == 2){
                        if(k == 0){
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.TOP);
                            EnergySpace energySpace = new EnergySpace("5B", orientations, 1);
                            dizzyHighway.get(x).get(y).addTile(energySpace);
                        }
                    }

                    if(x >= 6 && x <= 10 && y == 2){
                        Empty empty = new Empty("5B");
                        dizzyHighway.get(x).get(y).addTile(empty);
                    }

                    if(x == 11 && y == 2){
                        if(k == 0){
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.TOP);
                            orientations.add(Orientation.BOTTOM);
                            orientations.add(Orientation.RIGHT);
                            ConveyorBelt conveyorBelt = new ConveyorBelt("5B",2, orientations);
                            dizzyHighway.get(x).get(y).addTile(conveyorBelt);
                        }
                    }

                    if(x == 12 && y == 2){
                        if(k == 0){
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.LEFT);
                            orientations.add(Orientation.RIGHT);
                            ConveyorBelt conveyorBelt = new ConveyorBelt("5B",2, orientations);
                            dizzyHighway.get(x).get(y).addTile(conveyorBelt);
                        }
                    }

                    if(x == 0 && y == 3){
                        if(k == 0){
                            StartPoint startPoint = new StartPoint("A");
                            dizzyHighway.get(x).get(y).addTile(startPoint);
                        }
                    }

                    if((x == 1 || x == 2) && y == 3){
                        Empty empty = new Empty("A");
                        dizzyHighway.get(x).get(y).addTile(empty);
                    }

                    if(x == 3 && y == 3){
                        Empty empty = new Empty("5B");
                        dizzyHighway.get(x).get(y).addTile(empty);
                    }

                    if(x == 4 && y == 3){
                        if(k == 0){
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.BOTTOM);
                            orientations.add(Orientation.TOP);
                            ConveyorBelt conveyorBelt = new ConveyorBelt("5B",2, orientations);
                            dizzyHighway.get(x).get(y).addTile(conveyorBelt);
                        }
                    }

                    if(x == 5 && y == 3){
                        Empty empty = new Empty("5B");
                        dizzyHighway.get(x).get(y).addTile(empty);
                    }

                    if(x == 6 && y == 3){
                        if(k == 0){
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.TOP);
                            Wall wall = new Wall( "5B", orientations);
                            dizzyHighway.get(x).get(y).addTile(wall);
                        }
                    }

                    if(x == 7 && y == 3){
                        if(k == 0){
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.BOTTOM);
                            RestartPoint restartPoint = new RestartPoint("5B",orientations);
                            dizzyHighway.get(x).get(y).addTile(restartPoint);
                        }
                    }

                    if(x == 8 && y == 3){
                        if(k == 0){
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.RIGHT);
                            Laser laser = new Laser( "5B", orientations,1);
                            dizzyHighway.get(x).get(y).addTile(laser);
                        }

                        if(k == 1){
                         ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.LEFT);
                            Wall wall = new Wall( "5B", orientations);
                            dizzyHighway.get(x).get(y).addTile(wall);
                        }
                    }


                    if(x == 9 && y == 3){
                        if(k == 0){
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.RIGHT);
                            Wall wall = new Wall( "5B", orientations);
                            dizzyHighway.get(x).get(y).addTile(wall);
                        }
                    }

                    if(x == 10 && y == 3){
                        Empty empty = new Empty("5B");
                        dizzyHighway.get(x).get(y).addTile(empty);
                    }

                    if(x == 11 && y == 3){
                        if(k == 0){
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.TOP);
                            orientations.add(Orientation.BOTTOM);
                            ConveyorBelt conveyorBelt = new ConveyorBelt("5B",2, orientations);
                            dizzyHighway.get(x).get(y).addTile(conveyorBelt);
                        }
                    }

                    if(x == 12 && y == 3){
                        if(k == 0){
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.RIGHT);
                            CheckPoint checkPoint = new CheckPoint("5B",orientations,1);
                            dizzyHighway.get(x).get(y).addTile(checkPoint);
                        }
                    }

                    if(x == 0 && y == 4){
                        if(k == 0){
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.RIGHT);
                            Antenna antenna = new Antenna( "A", orientations);
                            dizzyHighway.get(x).get(y).addTile(antenna);
                        }
                    }

                    if(x == 1 && y == 4){
                        if(k == 0) {
                            StartPoint startPoint = new StartPoint( "A");
                            dizzyHighway.get(x).get(y).addTile(startPoint);
                        }
                    }

                    if(x == 2 && y == 4){
                        if(k == 0){
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.RIGHT);
                            Wall wall = new Wall( "A", orientations);
                            dizzyHighway.get(x).get(y).addTile(wall);
                        }
                    }

                    if(x == 3 && y == 4){
                        Empty empty = new Empty("5B");
                        dizzyHighway.get(x).get(y).addTile(empty);
                    }

                    if(x == 4 && y == 4) {
                        if(k == 0){
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.BOTTOM);
                            orientations.add(Orientation.TOP);
                            ConveyorBelt conveyorBelt = new ConveyorBelt("5B",2, orientations);
                            dizzyHighway.get(x).get(y).addTile(conveyorBelt);
                        }
                    }

                    if(x == 5 && y == 4){
                        Empty empty = new Empty("5B");
                        dizzyHighway.get(x).get(y).addTile(empty);
                    }

                    if(x == 6 && y == 4){
                        if(k == 0){
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.TOP);
                            Laser laser = new Laser( "5B", orientations,1);
                            dizzyHighway.get(x).get(y).addTile(laser);
                        }

                        if(k == 1){
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.BOTTOM);
                            Wall wall = new Wall( "5B", orientations);
                            dizzyHighway.get(x).get(y).addTile(wall);
                        }
                    }

                    if(x == 7 && y == 4){
                        Empty empty = new Empty("A");
                        dizzyHighway.get(x).get(y).addTile(empty);
                    }

                    if(x == 8 && y == 4){
                        if(k == 0){
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.TOP);
                            EnergySpace energySpace = new EnergySpace("5B", orientations, 1);
                            dizzyHighway.get(x).get(y).addTile(energySpace);
                        }
                    }

                    if((x == 9 || x == 10) && y == 4){
                        Empty empty = new Empty("5B");
                        dizzyHighway.get(x).get(y).addTile(empty);
                    }

                    if(x == 11 && y == 4){
                        if(k == 0){
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.TOP);
                            orientations.add(Orientation.BOTTOM);
                            ConveyorBelt conveyorBelt = new ConveyorBelt("5B",2, orientations);
                            dizzyHighway.get(x).get(y).addTile(conveyorBelt);
                        }
                    }

                    if(x == 12 && y == 4){
                        Empty empty = new Empty("5B");
                        dizzyHighway.get(x).get(y).addTile(empty);
                    }

                    if(x == 0 && y == 5){
                        Empty empty = new Empty("A");
                        dizzyHighway.get(x).get(y).addTile(empty);
                    }

                    if(x == 1 && y == 5){
                        if(k == 0){
                            StartPoint startPoint = new StartPoint( "A");
                            dizzyHighway.get(x).get(y).addTile(startPoint);
                        }
                    }

                    if(x == 2 && y == 5){
                        if(k == 0){
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.RIGHT);
                            Wall wall = new Wall( "5B", orientations);
                            dizzyHighway.get(x).get(y).addTile(wall);
                        }
                    }

                    if(x == 3 && y == 5){
                        Empty empty = new Empty("5B");
                        dizzyHighway.get(x).get(y).addTile(empty);
                    }

                    if(x == 4 && y == 5){
                        if(k == 0){
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.BOTTOM);
                            orientations.add(Orientation.TOP);
                            ConveyorBelt conveyorBelt = new ConveyorBelt("5B",2, orientations);
                            dizzyHighway.get(x).get(y).addTile(conveyorBelt);
                        }
                    }

                    if((x == 5 || x == 6) && y == 5){
                        Empty empty = new Empty("5B");
                        dizzyHighway.get(x).get(y).addTile(empty);
                    }

                    if(x == 7 && y == 5){
                        if(k == 0){
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.TOP);
                            EnergySpace energySpace = new EnergySpace("5B", orientations, 1);
                            dizzyHighway.get(x).get(y).addTile(energySpace);
                        }
                    }

                    if(x == 8 && y == 5){
                        Empty empty = new Empty("5B");
                        dizzyHighway.get(x).get(y).addTile(empty);
                    }

                    if(x == 9 && y == 5){
                        if(k == 0){
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.BOTTOM);
                            Laser laser = new Laser( "5B", orientations,1);
                            dizzyHighway.get(x).get(y).addTile(laser);
                        }
                        if(k == 1){
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.TOP);
                            Wall wall = new Wall( "5B", orientations);
                            dizzyHighway.get(x).get(y).addTile(wall);
                        }
                    }

                    if(x == 10 && y == 5){
                        Empty empty = new Empty("5B");
                        dizzyHighway.get(x).get(y).addTile(empty);
                    }

                    if(x == 11 && y == 5){
                        if(k == 0){
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.TOP);
                            orientations.add(Orientation.BOTTOM);
                            ConveyorBelt conveyorBelt = new ConveyorBelt("5B",2, orientations);
                            dizzyHighway.get(x).get(y).addTile(conveyorBelt);
                        }
                    }

                    if(x == 12 && y == 5){
                        Empty empty = new Empty("5B");
                        dizzyHighway.get(x).get(y).addTile(empty);
                    }

                    if(x == 0 && y == 6){
                        if(k == 1){
                            StartPoint startPoint = new StartPoint( "A");
                            dizzyHighway.get(x).get(y).addTile(startPoint);
                        }
                    }

                    if((x == 1 || x == 2) && y == 6){
                        Empty empty = new Empty("A");
                        dizzyHighway.get(x).get(y).addTile(empty);
                    }

                    if(x == 3 && y == 6){
                        Empty empty = new Empty("5B");
                        dizzyHighway.get(x).get(y).addTile(empty);
                    }

                    if(x == 4 && y == 6){
                        if(k == 0){
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.BOTTOM);
                            orientations.add(Orientation.TOP);
                            ConveyorBelt conveyorBelt = new ConveyorBelt("5B",2, orientations);
                            dizzyHighway.get(x).get(y).addTile(conveyorBelt);
                        }
                    }

                    if(x == 5 && y == 6){
                        Empty empty = new Empty("5B");
                        dizzyHighway.get(x).get(y).addTile(empty);
                    }


                    if(x == 6 && y == 6){
                        if(k == 0){
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.LEFT);
                            Wall wall = new Wall( "5B", orientations);
                            dizzyHighway.get(x).get(y).addTile(wall);
                        }
                    }

                    if(x == 7 && y == 6){
                        if(k == 0){
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.LEFT);
                            Laser laser = new Laser( "5B", orientations,1);
                            dizzyHighway.get(x).get(y).addTile(laser);
                        }

                        if(k == 1){
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.RIGHT);
                            Wall wall = new Wall("5B", orientations);
                            dizzyHighway.get(x).get(y).addTile(wall);
                        }
                    }

                    if(x == 8 && y == 6){
                        Empty empty = new Empty("5B");
                        dizzyHighway.get(x).get(y).addTile(empty);
                    }


                    if(x == 9 && y == 6){
                        if(k == 1){
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.BOTTOM);
                            Wall wall = new Wall( "5B", orientations);
                            dizzyHighway.get(x).get(y).addTile(wall);
                        }
                    }

                    if(x == 10 && y == 6){
                        Empty empty = new Empty("5B");
                        dizzyHighway.get(x).get(y).addTile(empty);
                    }

                    if(x == 11 && y == 6){
                        if(k == 0){
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.TOP);
                            orientations.add(Orientation.BOTTOM);
                            ConveyorBelt conveyorBelt = new ConveyorBelt("5B",2, orientations);
                            dizzyHighway.get(x).get(y).addTile(conveyorBelt);
                        }
                    }

                    if(x == 12 && y == 6){
                        Empty empty = new Empty("5B");
                        dizzyHighway.get(x).get(y).addTile(empty);
                    }

                    if(x == 0 && y == 7){
                        Empty empty = new Empty("A");
                        dizzyHighway.get(x).get(y).addTile(empty);
                    }

                    if(x == 1 && y == 7){
                        if(k == 0){
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.BOTTOM);
                            Wall wall = new Wall("A", orientations);
                            dizzyHighway.get(x).get(y).addTile(wall);
                        }
                    }

                    if(x == 2 && y == 7){
                        Empty empty = new Empty("5B");
                        dizzyHighway.get(x).get(y).addTile(empty);
                    }

                    if(x == 3 && y == 7){
                        if(k == 0){
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.RIGHT);
                            orientations.add(Orientation.LEFT);
                            ConveyorBelt conveyorBelt = new ConveyorBelt("5B",2, orientations);
                            dizzyHighway.get(x).get(y).addTile(conveyorBelt);
                        }
                    }

                    if(x == 4 && y == 7){
                        if(k == 0){
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.BOTTOM);
                            orientations.add(Orientation.TOP);
                            orientations.add(Orientation.LEFT);
                            ConveyorBelt conveyorBelt = new ConveyorBelt("5B",2, orientations);
                            dizzyHighway.get(x).get(y).addTile(conveyorBelt);
                        }
                    }

                    if(x >= 5 && x <= 9 && y == 7){
                        Empty empty = new Empty("5B");
                        dizzyHighway.get(x).get(y).addTile(empty);
                    }

                    if(x == 10 && y == 7){
                        if(k == 0){
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.TOP);
                            EnergySpace energySpace = new EnergySpace("5B", orientations, 1);
                            dizzyHighway.get(x).get(y).addTile(energySpace);
                        }
                    }

                    if(x == 11 && y == 7){
                        if(k == 0){
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.TOP);
                            orientations.add(Orientation.BOTTOM);
                            ConveyorBelt conveyorBelt = new ConveyorBelt("5B",2, orientations);
                            dizzyHighway.get(x).get(y).addTile(conveyorBelt);
                        }
                    }

                    if(x == 12 && y == 7){
                        Empty empty = new Empty("5B");
                        dizzyHighway.get(x).get(y).addTile(empty);
                    }

                    if(x == 0 && y == 8){
                        Empty empty = new Empty("A");
                        dizzyHighway.get(x).get(y).addTile(empty);
                    }

                    if(x == 1 && y == 8){
                        if(k == 0){
                            StartPoint startPoint = new StartPoint( "A");
                            dizzyHighway.get(x).get(y).addTile(startPoint);
                        }
                    }

                    if(x == 2 && y == 8){
                        Empty empty = new Empty("A");
                        dizzyHighway.get(x).get(y).addTile(empty);
                    }

                    if(x == 3 && y == 8) {
                        if(k == 0){
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.RIGHT);
                            orientations.add(Orientation.LEFT);
                            ConveyorBelt conveyorBelt = new ConveyorBelt("5B",2, orientations);
                            dizzyHighway.get(x).get(y).addTile(conveyorBelt);
                        }
                    }

                    if(x == 4 && y == 8){
                        if(k == 0){
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.RIGHT);
                            orientations.add(Orientation.LEFT);
                            orientations.add(Orientation.TOP);
                            ConveyorBelt conveyorBelt = new ConveyorBelt("5B",2, orientations);
                            dizzyHighway.get(x).get(y).addTile(conveyorBelt);
                        }
                    }

                    if(x == 5 && y == 8){
                        if(k == 0){
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.RIGHT);
                            orientations.add(Orientation.LEFT);
                            ConveyorBelt conveyorBelt = new ConveyorBelt("5B",2, orientations);
                            dizzyHighway.get(x).get(y).addTile(conveyorBelt);
                        }
                    }

                    if(x == 6 && y == 8){
                        if(k == 0){
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.RIGHT);
                            orientations.add(Orientation.LEFT);
                            ConveyorBelt conveyorBelt = new ConveyorBelt("5B",2, orientations);
                            dizzyHighway.get(x).get(y).addTile(conveyorBelt);
                        }
                    }

                    if(x == 7 && y == 8){
                        if(k == 0){
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.RIGHT);
                            orientations.add(Orientation.LEFT);
                            ConveyorBelt conveyorBelt = new ConveyorBelt("5B",2, orientations);
                            dizzyHighway.get(x).get(y).addTile(conveyorBelt);
                        }
                    }

                    if(x == 8 && y == 8){
                        if(k == 0){
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.RIGHT);
                            orientations.add(Orientation.LEFT);
                            ConveyorBelt conveyorBelt = new ConveyorBelt("5B",2, orientations);
                            dizzyHighway.get(x).get(y).addTile(conveyorBelt);
                        }
                    }

                    if(x == 9 && y == 8){
                        if(k == 0){
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.RIGHT);
                            orientations.add(Orientation.LEFT);
                            ConveyorBelt conveyorBelt = new ConveyorBelt("5B",2, orientations);
                            dizzyHighway.get(x).get(y).addTile(conveyorBelt);
                        }
                    }

                    if(x == 10 && y == 8){
                        if(k == 0){
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.RIGHT);
                            orientations.add(Orientation.LEFT);
                            orientations.add(Orientation.BOTTOM);
                            ConveyorBelt conveyorBelt = new ConveyorBelt("5B",2, orientations);
                            dizzyHighway.get(x).get(y).addTile(conveyorBelt);
                        }
                    }

                    if(x == 11 && y == 8 ){
                        if(k == 0){
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.TOP);
                            orientations.add(Orientation.BOTTOM);
                            orientations.add(Orientation.LEFT);
                            ConveyorBelt conveyorBelt = new ConveyorBelt("5B",2, orientations);
                            dizzyHighway.get(x).get(y).addTile(conveyorBelt);
                        }
                    }

                    if(x == 12 && y == 8){
                        Empty empty = new Empty("5B");
                        dizzyHighway.get(x).get(y).addTile(empty);
                    }

                    if((x == 0 || x == 1) && y == 9){
                        Empty empty = new Empty("A");
                        dizzyHighway.get(x).get(y).addTile(empty);
                    }

                    if(x == 2 && y == 9){
                        if(k == 0){
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.RIGHT);
                            orientations.add(Orientation.LEFT);
                            ConveyorBelt conveyorBelt = new ConveyorBelt("A",1, orientations);
                            dizzyHighway.get(x).get(y).addTile(conveyorBelt);
                        }
                    }

                    if(x == 3 && y == 9){
                        if(k == 0){
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.TOP);
                            EnergySpace energySpace = new EnergySpace("5B", orientations, 1);
                            dizzyHighway.get(x).get(y).addTile(energySpace);
                        }
                    }

                    if(x >= 4 && x <= 9 && y == 9){
                        Empty empty = new Empty("5B");
                        dizzyHighway.get(x).get(y).addTile(empty);
                    }

                    if(x == 10 && y == 9) {
                        if (k == 0) {
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.TOP);
                            orientations.add(Orientation.BOTTOM);
                            ConveyorBelt conveyorBelt = new ConveyorBelt("5B",2, orientations);
                            dizzyHighway.get(x).get(y).addTile(conveyorBelt);
                        }
                    }

                    if(x == 11 && y == 9){
                        if(k == 0){
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.TOP);
                            orientations.add(Orientation.BOTTOM);
                            ConveyorBelt conveyorBelt = new ConveyorBelt("5B",2, orientations);
                            dizzyHighway.get(x).get(y).addTile(conveyorBelt);
                        }
                    }

                    if(x == 12 && y == 9){
                        Empty empty = new Empty("5B");
                        dizzyHighway.get(x).get(y).addTile(empty);
                    }
                }
            }
        }
        return new GameStarted(new Board(dizzyHighway));
    }
}
