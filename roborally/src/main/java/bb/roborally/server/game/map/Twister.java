package bb.roborally.server.game.map;

import bb.roborally.server.game.Orientation;
import bb.roborally.server.game.board.Cell;
import bb.roborally.server.game.tiles.*;

import java.util.ArrayList;

public class Twister {
    public static ArrayList<ArrayList<Cell>> buildTwister() {
        int xAxis = 13;
        int yAxis = 10;
        int maxCellContent = 4;

        ArrayList<ArrayList<Cell>> twister = new ArrayList<ArrayList<Cell>>();

        for (int i = 0; i < xAxis; i++) {
            twister.add(new ArrayList<Cell>());

            for (int j = 0; j < yAxis; j++) {
                twister.get(i).add(new Cell(i, j));

                for (int k = 0; k < maxCellContent; k++) {
                    if (i >= 0 && i <= 2) {
                        if (k == 0) {
                            Empty empty = new Empty("A");
                            twister.get(i).get(j).addTile(empty);
                        }
                    }

                    if (i >= 3 && i <= 12) {
                        if (k == 0) {
                            Empty empty = new Empty("6B");
                            twister.get(i).get(j).addTile(empty);
                        }
                    }

                    //Restart Point
                    if(i == 0 && j == 7){
                        if(k == 0){
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.RIGHT);
                            RestartPoint restartPoint = new RestartPoint("A", orientations);
                            twister.get(i).get(j).addTile(restartPoint);
                        }
                    }

                    //Conveyor Belts in A
                    if(i == 2 && j == 9){
                        if(k == 0){
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.RIGHT);
                            orientations.add(Orientation.LEFT);
                            ConveyorBelt conveyorBelt = new ConveyorBelt("A", 1, orientations);
                            twister.get(i).get(j).addTile(conveyorBelt);
                        }
                    }
                    if(i == 2 && j == 0){
                        if(k == 0){
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.RIGHT);
                            orientations.add(Orientation.LEFT);
                            ConveyorBelt conveyorBelt = new ConveyorBelt("A", 1, orientations);
                            twister.get(i).get(j).addTile(conveyorBelt);
                        }
                    }

                    //Start Points
                    if(i == 1 && j == 1){
                        if(k == 0){
                            StartPoint startPoint = new StartPoint("A");
                            twister.get(i).get(j).addTile(startPoint);
                        }
                    }
                    if(i == 0 && j == 3){
                        if(k == 0){
                            StartPoint startPoint = new StartPoint("A");
                            twister.get(i).get(j).addTile(startPoint);
                        }
                    }
                    if(i == 1 && j == 4){
                        if(k == 0){
                            StartPoint startPoint = new StartPoint("A");
                            twister.get(i).get(j).addTile(startPoint);
                        }
                    }
                    if(i == 1 && j == 5){
                        if(k == 0){
                            StartPoint startPoint = new StartPoint("A");
                            twister.get(i).get(j).addTile(startPoint);
                        }
                    }
                    if(i == 0 && j == 6){
                        if(k == 0){
                            StartPoint startPoint = new StartPoint("A");
                            twister.get(i).get(j).addTile(startPoint);
                        }
                    }
                    if(i == 1 && j == 8){
                        if(k == 0){
                            StartPoint startPoint = new StartPoint("A");
                            twister.get(i).get(j).addTile(startPoint);
                        }
                    }

                    //Antenna
                    if(i == 0 && j == 4){
                        if(k == 0){
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.RIGHT);
                            Antenna antenna = new Antenna("A", orientations);
                            twister.get(i).get(j).addTile(antenna);
                        }
                    }

                    //Walls in A
                    if(i == 1 && j == 2) {
                        if (k == 0) {
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.TOP);
                            Wall wall = new Wall("A", orientations);
                            twister.get(i).get(j).addTile(wall);
                        }
                    }

                    if(i == 2 && j == 4) {
                        if (k == 0) {
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.RIGHT);
                            Wall wall = new Wall("A", orientations);
                            twister.get(i).get(j).addTile(wall);
                        }
                    }

                    if(i == 2 && j == 5){
                        if(k == 0){
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.RIGHT);
                            Wall wall = new Wall("A", orientations);
                            twister.get(i).get(j).addTile(wall);
                        }
                    }

                    if(i == 1 && j == 7){
                        if(k == 0){
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.BOTTOM);
                            Wall wall = new Wall("A", orientations);
                            twister.get(i).get(j).addTile(wall);
                        }
                    }

                        //6B MAP
                        //EnergySpace with wall on the same tile
                        if(i == 7 && j == 4){
                            if(k == 0){
                                ArrayList<Orientation> orientations = new ArrayList<>();
                                orientations.add(Orientation.BOTTOM);
                                Wall wall = new Wall("6B", orientations);
                                twister.get(i).get(j).addTile(wall);
                            }
                            if(k == 1){
                                ArrayList<Orientation> orientations = new ArrayList<>();
                                orientations.add(Orientation.TOP);
                                EnergySpace energySpace = new EnergySpace("6B", orientations,1);
                                twister.get(i).get(j).addTile(energySpace);
                            }
                        }
                        if(i == 8 && j == 5){
                            if(k == 0){
                                ArrayList<Orientation> orientations = new ArrayList<>();
                                orientations.add(Orientation.TOP);
                                Wall wall = new Wall("6B", orientations);
                                twister.get(i).get(j).addTile(wall);
                            }
                            if(k == 0){
                                ArrayList<Orientation> orientations = new ArrayList<>();
                                orientations.add(Orientation.TOP);
                                EnergySpace energySpace = new EnergySpace("6B", orientations,1);
                                twister.get(i).get(j).addTile(energySpace);
                            }
                        }

                        //EnergySpace Alone
                        if(i == 5 && j == 2){
                            if(k == 0){
                                ArrayList<Orientation> orientations = new ArrayList<>();
                                orientations.add(Orientation.TOP);
                                EnergySpace energySpace = new EnergySpace("6B", orientations,1);
                                twister.get(i).get(j).addTile(energySpace);
                            }
                        }
                        if(i == 10 && j == 2){
                            if(k == 0){
                                ArrayList<Orientation> orientations = new ArrayList<>();
                                orientations.add(Orientation.TOP);
                                EnergySpace energySpace = new EnergySpace("6B", orientations,1);
                                twister.get(i).get(j).addTile(energySpace);
                            }
                        }
                        if(i == 10 && j == 7){
                            if(k == 0){
                                ArrayList<Orientation> orientations = new ArrayList<>();
                                orientations.add(Orientation.TOP);
                                EnergySpace energySpace = new EnergySpace("6B", orientations,1);
                                twister.get(i).get(j).addTile(energySpace);
                            }
                        }
                        if(i == 5 && j == 7){
                            if(k == 0){
                                ArrayList<Orientation> orientations = new ArrayList<>();
                                orientations.add(Orientation.TOP);
                                EnergySpace energySpace = new EnergySpace("6B", orientations,1);
                                twister.get(i).get(j).addTile(energySpace);
                            }
                        }

                        //walls alone
                        if(i == 8 && j == 4) {
                            if (k == 0) {
                                ArrayList<Orientation> orientations = new ArrayList<>();
                                orientations.add(Orientation.LEFT);
                                Wall wall = new Wall("6B", orientations);
                                twister.get(i).get(j).addTile(wall);
                            }
                        }

                    if(i == 7 && j == 5) {
                        if (k == 0) {
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.RIGHT);
                            Wall wall = new Wall("6B", orientations);
                            twister.get(i).get(j).addTile(wall);
                        }
                    }



                        //conveyor belts bottom top
                        if(i == 4 && j == 2){
                            if(k == 0){
                                ArrayList<Orientation> orientations = new ArrayList<>();
                                orientations.add(Orientation.TOP);
                                orientations.add(Orientation.BOTTOM);
                                ConveyorBelt conveyorBelt = new ConveyorBelt("6B", 2, orientations);
                                twister.get(i).get(j).addTile(conveyorBelt);
                            }
                        }
                        if(i == 6 && j == 2){
                            if(k == 0){
                                ArrayList<Orientation> orientations = new ArrayList<>();
                                orientations.add(Orientation.BOTTOM);
                                orientations.add(Orientation.TOP);
                                ConveyorBelt conveyorBelt = new ConveyorBelt("6B", 2, orientations);
                                twister.get(i).get(j).addTile(conveyorBelt);
                            }
                        }
                        if(i == 9 && j == 2){
                            if(k == 0){
                                ArrayList<Orientation> orientations = new ArrayList<>();
                                orientations.add(Orientation.TOP);
                                orientations.add(Orientation.BOTTOM);
                                ConveyorBelt conveyorBelt = new ConveyorBelt("6B", 2, orientations);
                                twister.get(i).get(j).addTile(conveyorBelt);
                            }
                        }
                        if(i == 11 && j == 2){
                            if(k == 0){
                                ArrayList<Orientation> orientations = new ArrayList<>();
                                orientations.add(Orientation.BOTTOM);
                                orientations.add(Orientation.TOP);
                                ConveyorBelt conveyorBelt = new ConveyorBelt("6B", 2, orientations);
                                twister.get(i).get(j).addTile(conveyorBelt);
                            }
                        }
                        if(i == 11 && j == 7){
                            if(k == 0){
                                ArrayList<Orientation> orientations = new ArrayList<>();
                                orientations.add(Orientation.BOTTOM);
                                orientations.add(Orientation.TOP);
                                ConveyorBelt conveyorBelt = new ConveyorBelt("6B", 2, orientations);
                                twister.get(i).get(j).addTile(conveyorBelt);
                            }
                        }
                        if(i == 4 && j == 7){
                            if(k == 0){
                                ArrayList<Orientation> orientations = new ArrayList<>();
                                orientations.add(Orientation.TOP);
                                orientations.add(Orientation.BOTTOM);
                                ConveyorBelt conveyorBelt = new ConveyorBelt("6B", 2, orientations);
                                twister.get(i).get(j).addTile(conveyorBelt);
                            }
                        }

                        //conveyor belts left right
                        if(i == 5 && j == 1){
                            if(k == 0){
                                ArrayList<Orientation> orientations = new ArrayList<>();
                                orientations.add(Orientation.RIGHT);
                                orientations.add(Orientation.LEFT);
                                ConveyorBelt conveyorBelt = new ConveyorBelt("6B", 2, orientations);
                                twister.get(i).get(j).addTile(conveyorBelt);
                            }
                        }
                        if(i == 10 && j == 3){
                            if(k == 0){
                                ArrayList<Orientation> orientations = new ArrayList<>();
                                orientations.add(Orientation.LEFT);
                                orientations.add(Orientation.RIGHT);
                                ConveyorBelt conveyorBelt = new ConveyorBelt("6B", 2, orientations);
                                twister.get(i).get(j).addTile(conveyorBelt);
                            }
                        }
                        if(i == 10 && j == 6){
                            if(k == 0){
                                ArrayList<Orientation> orientations = new ArrayList<>();
                                orientations.add(Orientation.RIGHT);
                                orientations.add(Orientation.LEFT);
                                ConveyorBelt conveyorBelt = new ConveyorBelt("6B", 2, orientations);
                                twister.get(i).get(j).addTile(conveyorBelt);
                            }
                        }
                        if(i == 5 && j == 8){
                            if(k == 0){
                                ArrayList<Orientation> orientations = new ArrayList<>();
                                orientations.add(Orientation.LEFT);
                                orientations.add(Orientation.RIGHT);
                                ConveyorBelt conveyorBelt = new ConveyorBelt("6B", 2, orientations);
                                twister.get(i).get(j).addTile(conveyorBelt);
                            }
                        }
                        if(i == 5 && j == 6){
                            if(k == 0){
                                ArrayList<Orientation> orientations = new ArrayList<>();
                                orientations.add(Orientation.RIGHT);
                                orientations.add(Orientation.LEFT);
                                ConveyorBelt conveyorBelt = new ConveyorBelt("6B", 2, orientations);
                                twister.get(i).get(j).addTile(conveyorBelt);
                            }
                        }
                    if(i == 10 && j == 8){
                        if(k == 0){
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.LEFT);
                            orientations.add(Orientation.RIGHT);
                            ConveyorBelt conveyorBelt = new ConveyorBelt("6B", 2, orientations);
                            twister.get(i).get(j).addTile(conveyorBelt);
                        }
                    }

                        //wall+lasers
                        if(i == 7 && j == 0){
                            if(k == 0){
                                ArrayList<Orientation> orientations = new ArrayList<>();
                                orientations.add(Orientation.LEFT);
                                Laser laser = new Laser("6B", orientations,1);
                                twister.get(i).get(j).addTile(laser);
                            }

                            if(k == 1){
                                ArrayList<Orientation> orientations = new ArrayList<>();
                                orientations.add(Orientation.LEFT);
                                Wall wall = new Wall("6B", orientations);
                                twister.get(i).get(j).addTile(wall);
                            }
                        }
                        if(i == 8 && j == 0){
                            if(k == 0){
                                ArrayList<Orientation> orientations = new ArrayList<>();
                                orientations.add(Orientation.RIGHT);
                                Wall wall = new Wall("6B", orientations);
                                twister.get(i).get(j).addTile(wall);
                            }

                            if(k == 1){
                                ArrayList<Orientation> orientations = new ArrayList<>();
                                orientations.add(Orientation.RIGHT);
                                Laser laser = new Laser("6B", orientations,1);
                                twister.get(i).get(j).addTile(laser);
                            }
                        }
                        if(i == 3 && j == 5){
                            if(k == 0){
                                ArrayList<Orientation> orientations = new ArrayList<>();
                                orientations.add(Orientation.BOTTOM);
                                Laser laser = new Laser("6B", orientations,1);
                                twister.get(i).get(j).addTile(laser);
                            }

                            if(k == 1){
                                ArrayList<Orientation> orientations = new ArrayList<>();
                                orientations.add(Orientation.BOTTOM);
                                Wall wall = new Wall("6B", orientations);
                                twister.get(i).get(j).addTile(wall);
                            }
                        }
                        if(i == 3 && j == 4){
                            if(k == 0){
                                ArrayList<Orientation> orientations = new ArrayList<>();
                                orientations.add(Orientation.TOP);
                                Wall wall = new Wall("6B", orientations);
                                twister.get(i).get(j).addTile(wall);
                            }

                            if(k == 1){
                                ArrayList<Orientation> orientations = new ArrayList<>();
                                orientations.add(Orientation.TOP);
                                Laser laser = new Laser("6B", orientations,1);
                                twister.get(i).get(j).addTile(laser);
                            }
                        }

                        if(i == 12 && j == 4){
                            if(k == 0){
                                ArrayList<Orientation> orientations = new ArrayList<>();
                                orientations.add(Orientation.TOP);
                                Laser laser = new Laser("6B", orientations,1);
                                twister.get(i).get(j).addTile(laser);
                            }

                            if(k == 1){
                                ArrayList<Orientation> orientations = new ArrayList<>();
                                orientations.add(Orientation.TOP);
                                Wall wall = new Wall("6B", orientations);
                                twister.get(i).get(j).addTile(wall);
                            }
                        }
                        if(i == 12 && j == 5){
                            if(k == 0){
                                ArrayList<Orientation> orientations = new ArrayList<>();
                                orientations.add(Orientation.BOTTOM);
                                Wall wall = new Wall("6B", orientations);
                                twister.get(i).get(j).addTile(wall);
                            }

                            if(k == 1){
                                ArrayList<Orientation> orientations = new ArrayList<>();
                                orientations.add(Orientation.BOTTOM);
                                Laser laser = new Laser("6B", orientations,1);
                                twister.get(i).get(j).addTile(laser);
                            }
                        }

                        if(i == 8 && j == 9){
                            if(k == 0){
                                ArrayList<Orientation> orientations = new ArrayList<>();
                                orientations.add(Orientation.RIGHT);
                                Laser laser = new Laser("6B", orientations,1);
                                twister.get(i).get(j).addTile(laser);
                            }

                            if(k == 1){
                                ArrayList<Orientation> orientations = new ArrayList<>();
                                orientations.add(Orientation.RIGHT);
                                Wall wall = new Wall("6B", orientations);
                                twister.get(i).get(j).addTile(wall);
                            }
                        }
                        if(i == 7 && j == 9){
                            if(k == 0){
                                ArrayList<Orientation> orientations = new ArrayList<>();
                                orientations.add(Orientation.LEFT);
                                Wall wall = new Wall("6B", orientations);
                                twister.get(i).get(j).addTile(wall);
                            }

                            if(k == 1){
                                ArrayList<Orientation> orientations = new ArrayList<>();
                                orientations.add(Orientation.LEFT);
                                Laser laser = new Laser("6B", orientations,1);
                                twister.get(i).get(j).addTile(laser);
                            }
                        }

                        //CHECKPOINTS
                        if(i == 5 && j == 3){
                            if(k == 0){
                                ArrayList<Orientation> orientations = new ArrayList<>();
                                orientations.add(Orientation.LEFT);
                                orientations.add(Orientation.RIGHT);
                                ConveyorBelt conveyorBelt = new ConveyorBelt("6B", 2, orientations);
                                twister.get(i).get(j).addTile(conveyorBelt);
                            }
                            if(k == 1){
                                ArrayList<Orientation> orientations = new ArrayList<>();
                                orientations.add(Orientation.TOP);
                                CheckPoint checkPoint = new CheckPoint("6B",orientations,3);
                                twister.get(i).get(j).addTile(checkPoint);
                            }
                        }

                        if(i == 10 && j == 1){
                            if(k == 0){
                                ArrayList<Orientation> orientations = new ArrayList<>();
                                orientations.add(Orientation.RIGHT);
                                orientations.add(Orientation.LEFT);
                                ConveyorBelt conveyorBelt = new ConveyorBelt("6B", 2, orientations);
                                twister.get(i).get(j).addTile(conveyorBelt);
                            }
                            if(k == 1){
                                ArrayList<Orientation> orientations = new ArrayList<>();
                                orientations.add(Orientation.TOP);
                                CheckPoint checkPoint = new CheckPoint("6B",orientations,1);
                                twister.get(i).get(j).addTile(checkPoint);
                            }
                        }

                        if(i == 6 && j == 7){
                            if(k == 0){
                                ArrayList<Orientation> orientations = new ArrayList<>();
                                orientations.add(Orientation.BOTTOM);
                                orientations.add(Orientation.TOP);
                                ConveyorBelt conveyorBelt = new ConveyorBelt("6B", 2, orientations);
                                twister.get(i).get(j).addTile(conveyorBelt);
                            }
                            if(k == 1){
                                ArrayList<Orientation> orientations = new ArrayList<>();
                                orientations.add(Orientation.TOP);
                                CheckPoint checkPoint = new CheckPoint("6B",orientations,2);
                                twister.get(i).get(j).addTile(checkPoint);
                            }
                        }

                        if(i == 9 && j == 7){
                            if(k == 0){
                                ArrayList<Orientation> orientations = new ArrayList<>();
                                orientations.add(Orientation.TOP);
                                orientations.add(Orientation.BOTTOM);
                                ConveyorBelt conveyorBelt = new ConveyorBelt("6B", 2, orientations);
                                twister.get(i).get(j).addTile(conveyorBelt);
                            }
                            if(k == 1){
                                ArrayList<Orientation> orientations = new ArrayList<>();
                                orientations.add(Orientation.TOP);
                                CheckPoint checkPoint = new CheckPoint("6B",orientations,4);
                                twister.get(i).get(j).addTile(checkPoint);
                            }
                        }

                        //conveyor belt corner parts
                        if(i == 4 && j == 1){
                            if(k == 0){
                                ArrayList<Orientation> orientations = new ArrayList<>();
                                orientations.add(Orientation.RIGHT);
                                orientations.add(Orientation.BOTTOM);
                                ConveyorBelt conveyorBelt = new ConveyorBelt("6B", 2, orientations);
                                twister.get(i).get(j).addTile(conveyorBelt);
                            }
                        }
                        if(i == 9 && j == 1){
                            if(k == 0){
                                ArrayList<Orientation> orientations = new ArrayList<>();
                                orientations.add(Orientation.RIGHT);
                                orientations.add(Orientation.BOTTOM);
                                ConveyorBelt conveyorBelt = new ConveyorBelt("6B", 2, orientations);
                                twister.get(i).get(j).addTile(conveyorBelt);
                            }
                        }
                        if(i == 9 && j == 6){
                            if(k == 0){
                                ArrayList<Orientation> orientations = new ArrayList<>();
                                orientations.add(Orientation.RIGHT);
                                orientations.add(Orientation.BOTTOM);
                                ConveyorBelt conveyorBelt = new ConveyorBelt("6B", 2, orientations);
                                twister.get(i).get(j).addTile(conveyorBelt);
                            }
                        }
                        if(i == 4 && j == 6){
                            if(k == 0){
                                ArrayList<Orientation> orientations = new ArrayList<>();
                                orientations.add(Orientation.RIGHT);
                                orientations.add(Orientation.BOTTOM);
                                ConveyorBelt conveyorBelt = new ConveyorBelt("6B", 2, orientations);
                                twister.get(i).get(j).addTile(conveyorBelt);
                            }
                        }

                        if(i == 4 && j == 3){
                            if(k == 0){
                                ArrayList<Orientation> orientations = new ArrayList<>();
                                orientations.add(Orientation.TOP);
                                orientations.add(Orientation.RIGHT);
                                ConveyorBelt conveyorBelt = new ConveyorBelt("6B", 2, orientations);
                                twister.get(i).get(j).addTile(conveyorBelt);
                            }
                        }
                        if(i == 9 && j == 3){
                            if(k == 0){
                                ArrayList<Orientation> orientations = new ArrayList<>();
                                orientations.add(Orientation.TOP);
                                orientations.add(Orientation.RIGHT);
                                ConveyorBelt conveyorBelt = new ConveyorBelt("6B", 2, orientations);
                                twister.get(i).get(j).addTile(conveyorBelt);
                            }
                        }
                        if(i == 4 && j == 8){
                            if(k == 0){
                                ArrayList<Orientation> orientations = new ArrayList<>();
                                orientations.add(Orientation.TOP);
                                orientations.add(Orientation.RIGHT);
                                ConveyorBelt conveyorBelt = new ConveyorBelt("6B", 2, orientations);
                                twister.get(i).get(j).addTile(conveyorBelt);
                            }
                        }
                        if(i == 9 && j == 8){
                            if(k == 0){
                                ArrayList<Orientation> orientations = new ArrayList<>();
                                orientations.add(Orientation.TOP);
                                orientations.add(Orientation.RIGHT);
                                ConveyorBelt conveyorBelt = new ConveyorBelt("6B", 2, orientations);
                                twister.get(i).get(j).addTile(conveyorBelt);
                            }
                        }

                        if(i == 6 && j == 3){
                            if(k == 0){
                                ArrayList<Orientation> orientations = new ArrayList<>();
                                orientations.add(Orientation.LEFT);
                                orientations.add(Orientation.TOP);
                                ConveyorBelt conveyorBelt = new ConveyorBelt("6B", 2, orientations);
                                twister.get(i).get(j).addTile(conveyorBelt);
                            }
                        }
                        if(i == 6 && j == 8){
                            if(k == 0){
                                ArrayList<Orientation> orientations = new ArrayList<>();
                                orientations.add(Orientation.LEFT);
                                orientations.add(Orientation.TOP);
                                ConveyorBelt conveyorBelt = new ConveyorBelt("6B", 2, orientations);
                                twister.get(i).get(j).addTile(conveyorBelt);
                            }
                        }
                        if(i == 11 && j == 3){
                            if(k == 0){
                                ArrayList<Orientation> orientations = new ArrayList<>();
                                orientations.add(Orientation.LEFT);
                                orientations.add(Orientation.TOP);
                                ConveyorBelt conveyorBelt = new ConveyorBelt("6B", 2, orientations);
                                twister.get(i).get(j).addTile(conveyorBelt);
                            }
                        }
                        if(i == 11 && j == 8){
                            if(k == 0){
                                ArrayList<Orientation> orientations = new ArrayList<>();
                                orientations.add(Orientation.LEFT);
                                orientations.add(Orientation.TOP);
                                ConveyorBelt conveyorBelt = new ConveyorBelt("6B", 2, orientations);
                                twister.get(i).get(j).addTile(conveyorBelt);
                            }
                        }

                        if(i ==6 && j == 1 ){
                            if( k == 0){
                                ArrayList<Orientation> orientations = new ArrayList<>();
                                orientations.add(Orientation.BOTTOM);
                                orientations.add(Orientation.LEFT);
                                ConveyorBelt conveyorBelt = new ConveyorBelt("6B", 2, orientations);
                                twister.get(i).get(j).addTile(conveyorBelt);
                            }
                        }
                        if(i ==6 && j == 6 ){
                            if( k == 0){
                                ArrayList<Orientation> orientations = new ArrayList<>();
                                orientations.add(Orientation.BOTTOM);
                                orientations.add(Orientation.LEFT);
                                ConveyorBelt conveyorBelt = new ConveyorBelt("6B", 2, orientations);
                                twister.get(i).get(j).addTile(conveyorBelt);
                            }
                        }
                        if(i ==11 && j == 1 ){
                            if( k == 0){
                                ArrayList<Orientation> orientations = new ArrayList<>();
                                orientations.add(Orientation.BOTTOM);
                                orientations.add(Orientation.LEFT);
                                ConveyorBelt conveyorBelt = new ConveyorBelt("6B", 2, orientations);
                                twister.get(i).get(j).addTile(conveyorBelt);
                            }
                        }
                        if(i ==11 && j == 6 ){
                            if( k == 0){
                                ArrayList<Orientation> orientations = new ArrayList<>();
                                orientations.add(Orientation.BOTTOM);
                                orientations.add(Orientation.LEFT);
                                ConveyorBelt conveyorBelt = new ConveyorBelt("6B", 2, orientations);
                                twister.get(i).get(j).addTile(conveyorBelt);
                            }
                    }

                    //Empty Floors
                        if (i ==0  && j == 0){
                        if(k == 0){
                            Empty empty = new Empty("A");
                            twister.get(i).get(j).addTile(empty);
                        }
                    }

                    if (i ==1  && j == 0){
                        if(k == 0){
                            Empty empty = new Empty("A");
                            twister.get(i).get(j).addTile(empty);
                        }
                    }

                    if (i ==3  && j == 0){
                        if(k == 0){
                            Empty empty = new Empty("6B");
                            twister.get(i).get(j).addTile(empty);
                        }
                    }

                    if (i ==4  && j == 0){
                        if(k == 0){
                            Empty empty = new Empty("6B");
                            twister.get(i).get(j).addTile(empty);
                        }
                    }
                    if (i ==5  && j == 0){
                        if(k == 0){
                            Empty empty = new Empty("6B");
                            twister.get(i).get(j).addTile(empty);
                        }
                    }
                    if (i ==6  && j == 0){
                        if(k == 0){
                            Empty empty = new Empty("6B");
                            twister.get(i).get(j).addTile(empty);
                        }
                    }

                    if (i ==9  && j == 0){
                        if(k == 0){
                            Empty empty = new Empty("6B");
                            twister.get(i).get(j).addTile(empty);
                        }
                    }

                    if (i ==10  && j == 0){
                        if(k == 0){
                            Empty empty = new Empty("6B");
                            twister.get(i).get(j).addTile(empty);
                        }
                    }

                    if (i ==11  && j == 0){
                        if(k == 0){
                            Empty empty = new Empty("6B");
                            twister.get(i).get(j).addTile(empty);
                        }
                    }

                    if (i ==12  && j == 0){
                        if(k == 0){
                            Empty empty = new Empty("6B");
                            twister.get(i).get(j).addTile(empty);
                        }
                    }

                    if (i ==0  && j == 1){
                        if(k == 0){
                            Empty empty = new Empty("A");
                            twister.get(i).get(j).addTile(empty);
                        }
                    }

                    if (i ==2  && j == 1){
                        if(k == 0){
                            Empty empty = new Empty("A");
                            twister.get(i).get(j).addTile(empty);
                        }
                    }

                    if (i == 3  && j == 1){
                        if(k == 0){
                            Empty empty = new Empty("6B");
                            twister.get(i).get(j).addTile(empty);
                        }
                    }

                    if (i ==7  && j == 1){
                        if(k == 0){
                            Empty empty = new Empty("6B");
                            twister.get(i).get(j).addTile(empty);
                        }
                    }

                    if (i ==8  && j == 1){
                        if(k == 0){
                            Empty empty = new Empty("6B");
                            twister.get(i).get(j).addTile(empty);
                        }
                    }

                    if (i ==12  && j == 1){
                        if(k == 0){
                            Empty empty = new Empty("6B");
                            twister.get(i).get(j).addTile(empty);
                        }
                    }

                    if (i ==0  && j == 2){
                        if(k == 0){
                            Empty empty = new Empty("A");
                            twister.get(i).get(j).addTile(empty);
                        }
                    }

                    if (i ==2  && j == 2){
                        if(k == 0){
                            Empty empty = new Empty("A");
                            twister.get(i).get(j).addTile(empty);
                        }
                    }

                    if (i ==3  && j == 2){
                        if(k == 0){
                            Empty empty = new Empty("6B");
                            twister.get(i).get(j).addTile(empty);
                        }
                    }

                    if (i ==7  && j == 2){
                        if(k == 0){
                            Empty empty = new Empty("6B");
                            twister.get(i).get(j).addTile(empty);
                        }
                    }

                    if (i ==8  && j == 2){
                        if(k == 0){
                            Empty empty = new Empty("6B");
                            twister.get(i).get(j).addTile(empty);
                        }
                    }

                    if (i ==12  && j == 2){
                        if(k == 0){
                            Empty empty = new Empty("6B");
                            twister.get(i).get(j).addTile(empty);
                        }
                    }

                    if (i ==1  && j == 3){
                        if(k == 0){
                            Empty empty = new Empty("A");
                            twister.get(i).get(j).addTile(empty);
                        }
                    }

                    if (i ==2  && j == 3){
                        if(k == 0){
                            Empty empty = new Empty("A");
                            twister.get(i).get(j).addTile(empty);
                        }
                    }

                    if (i ==3  && j == 3){
                        if(k == 0){
                            Empty empty = new Empty("6B");
                            twister.get(i).get(j).addTile(empty);
                        }
                    }

                    if (i ==7  && j == 3){
                        if(k == 0){
                            Empty empty = new Empty("6B");
                            twister.get(i).get(j).addTile(empty);
                        }
                    }

                    if (i ==8  && j == 3){
                        if(k == 0){
                            Empty empty = new Empty("6B");
                            twister.get(i).get(j).addTile(empty);
                        }
                    }

                    if (i ==12  && j == 3){
                        if(k == 0){
                            Empty empty = new Empty("6B");
                            twister.get(i).get(j).addTile(empty);
                        }
                    }

                    if (i ==4  && j == 4){
                        if(k == 0){
                            Empty empty = new Empty("6B");
                            twister.get(i).get(j).addTile(empty);
                        }
                    }

                    if (i ==5  && j == 4){
                        if(k == 0){
                            Empty empty = new Empty("6B");
                            twister.get(i).get(j).addTile(empty);
                        }
                    }

                    if (i ==6  && j == 4){
                        if(k == 0){
                            Empty empty = new Empty("6B");
                            twister.get(i).get(j).addTile(empty);
                        }
                    }

                    if (i ==9  && j == 4){
                        if(k == 0){
                            Empty empty = new Empty("6B");
                            twister.get(i).get(j).addTile(empty);
                        }
                    }

                    if (i ==10  && j == 4){
                        if(k == 0){
                            Empty empty = new Empty("6B");
                            twister.get(i).get(j).addTile(empty);
                        }
                    }

                    if (i ==11  && j == 4){
                        if(k == 0){
                            Empty empty = new Empty("6B");
                            twister.get(i).get(j).addTile(empty);
                        }
                    }
                    if (i ==0  && j == 5){
                        if(k == 0){
                            Empty empty = new Empty("A");
                            twister.get(i).get(j).addTile(empty);
                        }
                    }

                    if (i ==5  && j == 5){
                        if(k == 0){
                            Empty empty = new Empty("6B");
                            twister.get(i).get(j).addTile(empty);
                        }
                    }

                    if (i ==6  && j == 5){
                        if(k == 0){
                            Empty empty = new Empty("6B");
                            twister.get(i).get(j).addTile(empty);
                        }
                    }

                    if (i ==9  && j == 5){
                        if(k == 0){
                            Empty empty = new Empty("6B");
                            twister.get(i).get(j).addTile(empty);
                        }
                    }

                    if (i ==10  && j == 5){
                        if(k == 0){
                            Empty empty = new Empty("6B");
                            twister.get(i).get(j).addTile(empty);
                        }
                    }

                    if (i ==11  && j == 5){
                        if(k == 0){
                            Empty empty = new Empty("6B");
                            twister.get(i).get(j).addTile(empty);
                        }
                    }

                    if (i ==1  && j == 6){
                        if(k == 0){
                            Empty empty = new Empty("A");
                            twister.get(i).get(j).addTile(empty);
                        }
                    }

                    if (i ==2  && j == 6){
                        if(k == 0){
                            Empty empty = new Empty("A");
                            twister.get(i).get(j).addTile(empty);
                        }
                    }

                    if (i ==3  && j == 6){
                        if(k == 0){
                            Empty empty = new Empty("6B");
                            twister.get(i).get(j).addTile(empty);
                        }
                    }

                    if (i ==7  && j == 6){
                        if(k == 0){
                            Empty empty = new Empty("6B");
                            twister.get(i).get(j).addTile(empty);
                        }
                    }

                    if (i ==8  && j == 6){
                        if(k == 0){
                            Empty empty = new Empty("6B");
                            twister.get(i).get(j).addTile(empty);
                        }
                    }

                    if (i ==12 && j == 6){
                        if(k == 0){
                            Empty empty = new Empty("6B");
                            twister.get(i).get(j).addTile(empty);
                        }
                    }

                    if (i ==2  && j == 7){
                        if(k == 0){
                            Empty empty = new Empty("A");
                            twister.get(i).get(j).addTile(empty);
                        }
                    }

                    if (i ==3  && j == 7){
                        if(k == 0){
                            Empty empty = new Empty("6B");
                            twister.get(i).get(j).addTile(empty);
                        }
                    }

                    if (i ==7  && j == 7){
                        if(k == 0){
                            Empty empty = new Empty("6B");
                            twister.get(i).get(j).addTile(empty);
                        }
                    }

                    if (i ==8  && j == 7){
                        if(k == 0){
                            Empty empty = new Empty("6B");
                            twister.get(i).get(j).addTile(empty);
                        }
                    }

                    if (i ==12 && j == 7){
                        if(k == 0){
                            Empty empty = new Empty("6B");
                            twister.get(i).get(j).addTile(empty);
                        }
                    }

                    if (i ==0  && j == 8){
                        if(k == 0){
                            Empty empty = new Empty("A");
                            twister.get(i).get(j).addTile(empty);
                        }
                    }

                    if (i ==2  && j == 8){
                        if(k == 0){
                            Empty empty = new Empty("A");
                            twister.get(i).get(j).addTile(empty);
                        }
                    }

                    if (i ==3  && j == 8){
                        if(k == 0){
                            Empty empty = new Empty("6B");
                            twister.get(i).get(j).addTile(empty);
                        }
                    }

                    if (i ==7  && j == 8){
                        if(k == 0){
                            Empty empty = new Empty("6B");
                            twister.get(i).get(j).addTile(empty);
                        }
                    }

                    if (i ==8  && j == 8){
                        if(k == 0){
                            Empty empty = new Empty("6B");
                            twister.get(i).get(j).addTile(empty);
                        }
                    }

                    if (i ==12 && j == 8){
                        if(k == 0){
                            Empty empty = new Empty("6B");
                            twister.get(i).get(j).addTile(empty);
                        }
                    }

                    if (i ==0  && j == 9){
                        if(k == 0){
                            Empty empty = new Empty("A");
                            twister.get(i).get(j).addTile(empty);
                        }
                    }

                    if (i ==1  && j == 9){
                        if(k == 0){
                            Empty empty = new Empty("A");
                            twister.get(i).get(j).addTile(empty);
                        }
                    }

                    if (i ==3  && j == 9){
                        if(k == 0){
                            Empty empty = new Empty("6B");
                            twister.get(i).get(j).addTile(empty);
                        }
                    }

                    if (i ==4  && j == 9){
                        if(k == 0){
                            Empty empty = new Empty("6B");
                            twister.get(i).get(j).addTile(empty);
                        }
                    }

                    if (i ==5  && j == 9){
                        if(k == 0){
                            Empty empty = new Empty("6B");
                            twister.get(i).get(j).addTile(empty);
                        }
                    }

                    if (i ==6 && j == 9){
                        if(k == 0){
                            Empty empty = new Empty("6B");
                            twister.get(i).get(j).addTile(empty);
                        }
                    }

                    if (i ==9  && j == 9){
                        if(k == 0){
                            Empty empty = new Empty("6B");
                            twister.get(i).get(j).addTile(empty);
                        }
                    }

                    if (i ==10  && j == 9){
                        if(k == 0){
                            Empty empty = new Empty("6B");
                            twister.get(i).get(j).addTile(empty);
                        }
                    }

                    if (i ==11  && j == 9){
                        if(k == 0){
                            Empty empty = new Empty("6B");
                            twister.get(i).get(j).addTile(empty);
                        }
                    }

                    if (i ==12 && j == 9){
                        if(k == 0){
                            Empty empty = new Empty("6B");
                            twister.get(i).get(j).addTile(empty);
                        }
                    }

                }
            }
        }
        for(int i=0;i<=2;i++){
            for(int j=0;j<=9;j++){
                twister.get(i).get(j).setAMap(true);
            }
        }
        return twister;
    }
}
