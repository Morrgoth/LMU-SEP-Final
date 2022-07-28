package bb.roborally.map;

import bb.roborally.protocol.map.Board;
import bb.roborally.protocol.map.Cell;
import bb.roborally.protocol.map.GameStarted;
import bb.roborally.protocol.map.tiles.*;
import bb.roborally.protocol.Orientation;
import bb.roborally.server.game.board.ServerCell;

import java.util.ArrayList;

public class TwisterBuilder implements BoardBuilder {

    @Override
    public GameStarted build() {

        final int xAxis = 13;
        final int yAxis = 10;
        final int maxCellContent = 4;

        ArrayList<ArrayList<Cell>> twister = new ArrayList<>();

        ///x-Axis
        for (int x = 0; x < xAxis; x++) {
            twister.add(new ArrayList<>());
            ///y-Axis
            for (int y = 0;y < yAxis; y++) {
                twister.get(x).add(new Cell());
                ///Cell - layers
                for (int k = 0; k < maxCellContent; k++) {

                    //Antenna
                    if (x == 0 && y == 4) {
                        if (k == 0) {
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.RIGHT);
                            Antenna antenna = new Antenna("A", orientations);
                            twister.get(x).get(y).addTile(antenna);
                        }
                    }
                    //Laser + Wall
                    if (x == 3 && y == 4) {
                        if (k == 0) {
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.BOTTOM);
                            Laser laser = new Laser("6B", orientations, 1);
                            twister.get(x).get(y).addTile(laser);
                        }
                        if (k == 1) {
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.TOP);
                            Wall wall = new Wall("6B", orientations);
                            twister.get(x).get(y).addTile(wall);
                        }
                    }
                    if (x == 8 && y == 0) {
                        if (k == 0) {
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.LEFT);
                            Laser laser = new Laser("6B", orientations, 1);
                            twister.get(x).get(y).addTile(laser);
                        }
                        if (k == 1) {
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.RIGHT);
                            Wall wall = new Wall("6B", orientations);
                            twister.get(x).get(y).addTile(wall);
                        }
                    }
                    if (x == 12 && y == 5) {
                        if (k == 0) {
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.TOP);
                            Laser laser = new Laser("6B", orientations, 1);
                            twister.get(x).get(y).addTile(laser);
                        }
                        if (k == 1) {
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.BOTTOM);
                            Wall wall = new Wall("6B", orientations);
                            twister.get(x).get(y).addTile(wall);
                        }
                    }
                    if (x == 7 && y == 9) {
                        if (k == 0) {
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.RIGHT);
                            Laser laser = new Laser("6B", orientations, 1);
                            twister.get(x).get(y).addTile(laser);
                        }
                        if (k == 1) {
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.LEFT);
                            Wall wall = new Wall("6B", orientations);
                            twister.get(x).get(y).addTile(wall);
                        }
                    }
                    //EnergySpace Alone
                    if (x == 5 && y == 2) {
                        if (k == 0) {
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.TOP);
                            EnergySpace energySpace = new EnergySpace("6B", orientations, 1);
                            twister.get(x).get(y).addTile(energySpace);
                        }
                    }
                    if (x == 10 && y == 2) {
                        if (k == 0) {
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.TOP);
                            EnergySpace energySpace = new EnergySpace("6B", orientations, 1);
                            twister.get(x).get(y).addTile(energySpace);
                        }
                    }
                    if (x == 10 && y == 7) {
                        if (k == 0) {
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.TOP);
                            EnergySpace energySpace = new EnergySpace("6B", orientations, 1);
                            twister.get(x).get(y).addTile(energySpace);
                        }
                    }
                    if (x == 5 && y == 7) {
                        if (k == 0) {
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.TOP);
                            EnergySpace energySpace = new EnergySpace("6B", orientations, 1);
                            twister.get(x).get(y).addTile(energySpace);
                        }
                    }
                    //EnergySpace + Wall
                    if (x == 7 && y == 4) {
                        if (k == 0) {
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.BOTTOM);
                            Wall wall = new Wall("6B", orientations);
                            twister.get(x).get(y).addTile(wall);
                        }
                        if (k == 1) {
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.TOP);
                            EnergySpace energySpace = new EnergySpace("6B", orientations, 1);
                            twister.get(x).get(y).addTile(energySpace);
                        }
                    }
                    if (x == 8 && y == 5) {
                        if (k == 0) {
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.TOP);
                            Wall wall = new Wall("6B", orientations);
                            twister.get(x).get(y).addTile(wall);
                        }
                        if (k == 0) {
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.TOP);
                            EnergySpace energySpace = new EnergySpace("6B", orientations, 1);
                            twister.get(x).get(y).addTile(energySpace);
                        }
                    }
                    //Wall only

                    if (x == 1 && y == 2) {
                        if (k == 0) {
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.TOP);
                            Wall wall = new Wall("A", orientations);
                            twister.get(x).get(y).addTile(wall);
                        }
                    }

                    if (x == 2 && y == 4) {
                        if (k == 0) {
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.RIGHT);
                            Wall wall = new Wall("A", orientations);
                            twister.get(x).get(y).addTile(wall);
                        }
                    }

                    if (x == 2 && y == 5) {
                        if (k == 0) {
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.RIGHT);
                            Wall wall = new Wall("A", orientations);
                            twister.get(x).get(y).addTile(wall);
                        }
                    }

                    if (x == 1 && y == 7) {
                        if (k == 0) {
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.BOTTOM);
                            Wall wall = new Wall("A", orientations);
                            twister.get(x).get(y).addTile(wall);
                        }
                    }
                    if (x == 7 && y == 0) {

                        if (k == 1) {
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.LEFT);
                            Wall wall = new Wall("6B", orientations);
                            twister.get(x).get(y).addTile(wall);
                        }
                    }

                    if (x == 3 && y == 5) {

                        if (k == 0) {
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.BOTTOM);
                            Wall wall = new Wall("6B", orientations);
                            twister.get(x).get(y).addTile(wall);
                        }
                    }

                    if (x == 12 && y == 4) {
                        if (k == 1) {
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.TOP);
                            Wall wall = new Wall("6B", orientations);
                            twister.get(x).get(y).addTile(wall);
                        }
                    }

                    if (x == 8 && y == 9) {
                        if (k == 1) {
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.RIGHT);
                            Wall wall = new Wall("6B", orientations);
                            twister.get(x).get(y).addTile(wall);
                        }
                    }
                    if (x == 8 && y == 4) {
                        if (k == 0) {
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.LEFT);
                            Wall wall = new Wall("6B", orientations);
                            twister.get(x).get(y).addTile(wall);
                        }
                    }

                    if (x == 7 && y == 5) {
                        if (k == 0) {
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.RIGHT);
                            Wall wall = new Wall("6B", orientations);
                            twister.get(x).get(y).addTile(wall);
                        }
                    }

                    //RebootPoint
                    if (x == 0 && y == 7) {
                        if (k == 0) {
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.RIGHT);
                            RestartPoint restartPoint = new RestartPoint("A", orientations);
                            twister.get(x).get(y).addTile(restartPoint);
                        }
                    }

                    //StartPoint
                    if (x == 0 && y == 3) {
                        if (k == 0) {
                            StartPoint startPoint = new StartPoint("A");
                            twister.get(x).get(y).addTile(startPoint);
                        }
                    }
                    if (x == 0 && y == 6) {
                        if (k == 0) {
                            StartPoint startPoint = new StartPoint("A");
                            twister.get(x).get(y).addTile(startPoint);
                        }
                    }
                    if (x == 1 && y == 1) {
                        if (k == 0) {
                            StartPoint startPoint = new StartPoint("A");
                            twister.get(x).get(y).addTile(startPoint);
                        }
                    }
                    if (x == 1 && y == 4) {
                        if (k == 0) {
                            StartPoint startPoint = new StartPoint("A");
                            twister.get(x).get(y).addTile(startPoint);
                        }
                    }
                    if (x == 1 && y == 5) {
                        if (k == 0) {
                            StartPoint startPoint = new StartPoint("A");
                            twister.get(x).get(y).addTile(startPoint);
                        }
                    }
                    if (x == 1 && y == 8) {
                        if (k == 0) {
                            StartPoint startPoint = new StartPoint("A");
                            twister.get(x).get(y).addTile(startPoint);
                        }
                    }
                    //CheckPoint + ConveyorBelt
                    if (x == 5 && y == 3) {
                        if (k == 1) {
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.TOP);
                            CheckPoint checkPoint = new CheckPoint("6B", orientations, 3);
                            twister.get(x).get(y).addTile(checkPoint);
                        }
                        if (k == 0) {
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.LEFT);
                            orientations.add(Orientation.RIGHT);
                            ConveyorBelt conveyorBelt = new ConveyorBelt("6B", 2, orientations);
                            twister.get(x).get(y).addTile(conveyorBelt);
                        }

                    }
                    if (x == 6 && y == 7) {
                        if (k == 1) {
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.TOP);
                            CheckPoint checkPoint = new CheckPoint("6B", orientations, 2);
                            twister.get(x).get(y).addTile(checkPoint);
                        }
                        if (k == 0) {
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.BOTTOM);
                            orientations.add(Orientation.TOP);
                            ConveyorBelt conveyorBelt = new ConveyorBelt("6B", 2, orientations);
                            twister.get(x).get(y).addTile(conveyorBelt);
                        }

                    }
                    if (x == 9 && y == 7) {
                        if (k == 1) {
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.TOP);
                            CheckPoint checkPoint = new CheckPoint("6B", orientations, 4);
                            twister.get(x).get(y).addTile(checkPoint);
                        }
                        if (k == 0) {
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.TOP);
                            orientations.add(Orientation.BOTTOM);
                            ConveyorBelt conveyorBelt = new ConveyorBelt("6B", 2, orientations);
                            twister.get(x).get(y).addTile(conveyorBelt);
                        }
                    }
                    if (x == 10 && y == 1) {
                        if (k == 1) {
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.TOP);
                            CheckPoint checkPoint = new CheckPoint("6B", orientations, 1);
                            twister.get(x).get(y).addTile(checkPoint);
                        }
                        if (k == 0) {
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.RIGHT);
                            orientations.add(Orientation.LEFT);
                            ConveyorBelt conveyorBelt = new ConveyorBelt("6B", 2, orientations);
                            twister.get(x).get(y).addTile(conveyorBelt);
                        }
                    }
                    //Conveyor Belts in A
                    if (x == 2 && y == 9) {
                        if (k == 0) {
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.RIGHT);
                            orientations.add(Orientation.LEFT);
                            ConveyorBelt conveyorBelt = new ConveyorBelt("A", 1, orientations);
                            twister.get(x).get(y).addTile(conveyorBelt);
                        }
                    }
                    if (x == 2 && y == 0) {
                        if (k == 0) {
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.RIGHT);
                            orientations.add(Orientation.LEFT);
                            ConveyorBelt conveyorBelt = new ConveyorBelt("A", 1, orientations);
                            twister.get(x).get(y).addTile(conveyorBelt);
                        }
                    }
                    //conveyor belts bottom top
                    if (x == 4 && y == 2) {
                        if (k == 0) {
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.TOP);
                            orientations.add(Orientation.BOTTOM);
                            ConveyorBelt conveyorBelt = new ConveyorBelt("6B", 2, orientations);
                            twister.get(x).get(y).addTile(conveyorBelt);
                        }
                    }
                    if (x == 6 && y == 2) {
                        if (k == 0) {
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.BOTTOM);
                            orientations.add(Orientation.TOP);
                            ConveyorBelt conveyorBelt = new ConveyorBelt("6B", 2, orientations);
                            twister.get(x).get(y).addTile(conveyorBelt);
                        }
                    }
                    if (x == 9 && y == 2) {
                        if (k == 0) {
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.TOP);
                            orientations.add(Orientation.BOTTOM);
                            ConveyorBelt conveyorBelt = new ConveyorBelt("6B", 2, orientations);
                            twister.get(x).get(y).addTile(conveyorBelt);
                        }
                    }
                    if (x == 11 && y == 2) {
                        if (k == 0) {
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.BOTTOM);
                            orientations.add(Orientation.TOP);
                            ConveyorBelt conveyorBelt = new ConveyorBelt("6B", 2, orientations);
                            twister.get(x).get(y).addTile(conveyorBelt);
                        }
                    }
                    if (x == 11 && y == 7) {
                        if (k == 0) {
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.BOTTOM);
                            orientations.add(Orientation.TOP);
                            ConveyorBelt conveyorBelt = new ConveyorBelt("6B", 2, orientations);
                            twister.get(x).get(y).addTile(conveyorBelt);
                        }
                    }
                    if (x == 4 && y == 7) {
                        if (k == 0) {
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.TOP);
                            orientations.add(Orientation.BOTTOM);
                            ConveyorBelt conveyorBelt = new ConveyorBelt("6B", 2, orientations);
                            twister.get(x).get(y).addTile(conveyorBelt);
                        }
                    }

                    //conveyor belts left right
                    if (x == 5 && y == 1) {
                        if (k == 0) {
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.RIGHT);
                            orientations.add(Orientation.LEFT);
                            ConveyorBelt conveyorBelt = new ConveyorBelt("6B", 2, orientations);
                            twister.get(x).get(y).addTile(conveyorBelt);
                        }
                    }
                    if (x == 10 && y == 3) {
                        if (k == 0) {
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.LEFT);
                            orientations.add(Orientation.RIGHT);
                            ConveyorBelt conveyorBelt = new ConveyorBelt("6B", 2, orientations);
                            twister.get(x).get(y).addTile(conveyorBelt);
                        }
                    }
                    if (x == 10 && y == 6) {
                        if (k == 0) {
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.RIGHT);
                            orientations.add(Orientation.LEFT);
                            ConveyorBelt conveyorBelt = new ConveyorBelt("6B", 2, orientations);
                            twister.get(x).get(y).addTile(conveyorBelt);
                        }
                    }
                    if (x == 5 && y == 8) {
                        if (k == 0) {
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.LEFT);
                            orientations.add(Orientation.RIGHT);
                            ConveyorBelt conveyorBelt = new ConveyorBelt("6B", 2, orientations);
                            twister.get(x).get(y).addTile(conveyorBelt);
                        }
                    }
                    if (x == 5 && y == 6) {
                        if (k == 0) {
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.RIGHT);
                            orientations.add(Orientation.LEFT);
                            ConveyorBelt conveyorBelt = new ConveyorBelt("6B", 2, orientations);
                            twister.get(x).get(y).addTile(conveyorBelt);
                        }
                    }
                    if (x == 10 && y == 8) {
                        if (k == 0) {
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.LEFT);
                            orientations.add(Orientation.RIGHT);
                            ConveyorBelt conveyorBelt = new ConveyorBelt("6B", 2, orientations);
                            twister.get(x).get(y).addTile(conveyorBelt);
                        }
                    }

                    //conveyor belt corner parts
                    if (x == 4 && y == 1) {
                        if (k == 0) {
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.RIGHT);
                            orientations.add(Orientation.BOTTOM);
                            ConveyorBelt conveyorBelt = new ConveyorBelt("6B", 2, orientations);
                            twister.get(x).get(y).addTile(conveyorBelt);
                        }
                    }
                    if (x == 9 && y == 1) {
                        if (k == 0) {
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.RIGHT);
                            orientations.add(Orientation.BOTTOM);
                            ConveyorBelt conveyorBelt = new ConveyorBelt("6B", 2, orientations);
                            twister.get(x).get(y).addTile(conveyorBelt);
                        }
                    }
                    if (x == 9 && y == 6) {
                        if (k == 0) {
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.RIGHT);
                            orientations.add(Orientation.BOTTOM);
                            ConveyorBelt conveyorBelt = new ConveyorBelt("6B", 2, orientations);
                            twister.get(x).get(y).addTile(conveyorBelt);
                        }
                    }
                    if (x == 4 && y == 6) {
                        if (k == 0) {
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.RIGHT);
                            orientations.add(Orientation.BOTTOM);
                            ConveyorBelt conveyorBelt = new ConveyorBelt("6B", 2, orientations);
                            twister.get(x).get(y).addTile(conveyorBelt);
                        }
                    }

                    if (x == 4 && y == 3) {
                        if (k == 0) {
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.TOP);
                            orientations.add(Orientation.RIGHT);
                            ConveyorBelt conveyorBelt = new ConveyorBelt("6B", 2, orientations);
                            twister.get(x).get(y).addTile(conveyorBelt);
                        }
                    }
                    if (x == 9 && y == 3) {
                        if (k == 0) {
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.TOP);
                            orientations.add(Orientation.RIGHT);
                            ConveyorBelt conveyorBelt = new ConveyorBelt("6B", 2, orientations);
                            twister.get(x).get(y).addTile(conveyorBelt);
                        }
                    }
                    if (x == 4 && y == 8) {
                        if (k == 0) {
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.TOP);
                            orientations.add(Orientation.RIGHT);
                            ConveyorBelt conveyorBelt = new ConveyorBelt("6B", 2, orientations);
                            twister.get(x).get(y).addTile(conveyorBelt);
                        }
                    }
                    if (x == 9 && y == 8) {
                        if (k == 0) {
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.TOP);
                            orientations.add(Orientation.RIGHT);
                            ConveyorBelt conveyorBelt = new ConveyorBelt("6B", 2, orientations);
                            twister.get(x).get(y).addTile(conveyorBelt);
                        }
                    }

                    if (x == 6 && y == 3) {
                        if (k == 0) {
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.LEFT);
                            orientations.add(Orientation.TOP);
                            ConveyorBelt conveyorBelt = new ConveyorBelt("6B", 2, orientations);
                            twister.get(x).get(y).addTile(conveyorBelt);
                        }
                    }
                    if (x == 6 && y == 8) {
                        if (k == 0) {
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.LEFT);
                            orientations.add(Orientation.TOP);
                            ConveyorBelt conveyorBelt = new ConveyorBelt("6B", 2, orientations);
                            twister.get(x).get(y).addTile(conveyorBelt);
                        }
                    }
                    if (x == 11 && y == 3) {
                        if (k == 0) {
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.LEFT);
                            orientations.add(Orientation.TOP);
                            ConveyorBelt conveyorBelt = new ConveyorBelt("6B", 2, orientations);
                            twister.get(x).get(y).addTile(conveyorBelt);
                        }
                    }
                    if (x == 11 && y == 8) {
                        if (k == 0) {
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.LEFT);
                            orientations.add(Orientation.TOP);
                            ConveyorBelt conveyorBelt = new ConveyorBelt("6B", 2, orientations);
                            twister.get(x).get(y).addTile(conveyorBelt);
                        }
                    }

                    if (x == 6 && y == 1) {
                        if (k == 0) {
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.BOTTOM);
                            orientations.add(Orientation.LEFT);
                            ConveyorBelt conveyorBelt = new ConveyorBelt("6B", 2, orientations);
                            twister.get(x).get(y).addTile(conveyorBelt);
                        }
                    }
                    if (x == 6 && y == 6) {
                        if (k == 0) {
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.BOTTOM);
                            orientations.add(Orientation.LEFT);
                            ConveyorBelt conveyorBelt = new ConveyorBelt("6B", 2, orientations);
                            twister.get(x).get(y).addTile(conveyorBelt);
                        }
                    }
                    if (x == 11 && y == 1) {
                        if (k == 0) {
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.BOTTOM);
                            orientations.add(Orientation.LEFT);
                            ConveyorBelt conveyorBelt = new ConveyorBelt("6B", 2, orientations);
                            twister.get(x).get(y).addTile(conveyorBelt);
                        }
                    }
                    if (x == 11 && y == 6) {
                        if (k == 0) {
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.BOTTOM);
                            orientations.add(Orientation.LEFT);
                            ConveyorBelt conveyorBelt = new ConveyorBelt("6B", 2, orientations);
                            twister.get(x).get(y).addTile(conveyorBelt);
                        }
                    }

                    //Empty Floors
                    if (x == 0 && y == 0) {
                        if (k == 0) {
                            Empty empty = new Empty("A");
                            twister.get(x).get(y).addTile(empty);
                        }
                    }

                    if (x == 1 && y == 0) {
                        if (k == 0) {
                            Empty empty = new Empty("A");
                            twister.get(x).get(y).addTile(empty);
                        }
                    }

                    if (x == 3 && y == 0) {
                        if (k == 0) {
                            Empty empty = new Empty("6B");
                            twister.get(x).get(y).addTile(empty);
                        }
                    }

                    if (x == 4 && y == 0) {
                        if (k == 0) {
                            Empty empty = new Empty("6B");
                            twister.get(x).get(y).addTile(empty);
                        }
                    }
                    if (x == 5 && y == 0) {
                        if (k == 0) {
                            Empty empty = new Empty("6B");
                            twister.get(x).get(y).addTile(empty);
                        }
                    }
                    if (x == 6 && y == 0) {
                        if (k == 0) {
                            Empty empty = new Empty("6B");
                            twister.get(x).get(y).addTile(empty);
                        }
                    }

                    if (x == 9 && y == 0) {
                        if (k == 0) {
                            Empty empty = new Empty("6B");
                            twister.get(x).get(y).addTile(empty);
                        }
                    }

                    if (x == 10 && y == 0) {
                        if (k == 0) {
                            Empty empty = new Empty("6B");
                            twister.get(x).get(y).addTile(empty);
                        }
                    }

                    if (x == 11 && y == 0) {
                        if (k == 0) {
                            Empty empty = new Empty("6B");
                            twister.get(x).get(y).addTile(empty);
                        }
                    }

                    if (x == 12 && y == 0) {
                        if (k == 0) {
                            Empty empty = new Empty("6B");
                            twister.get(x).get(y).addTile(empty);
                        }
                    }

                    if (x == 0 && y == 1) {
                        if (k == 0) {
                            Empty empty = new Empty("A");
                            twister.get(x).get(y).addTile(empty);
                        }
                    }

                    if (x == 2 && y == 1) {
                        if (k == 0) {
                            Empty empty = new Empty("A");
                            twister.get(x).get(y).addTile(empty);
                        }
                    }

                    if (x == 3 && y == 1) {
                        if (k == 0) {
                            Empty empty = new Empty("6B");
                            twister.get(x).get(y).addTile(empty);
                        }
                    }

                    if (x == 7 && y == 1) {
                        if (k == 0) {
                            Empty empty = new Empty("6B");
                            twister.get(x).get(y).addTile(empty);
                        }
                    }

                    if (x == 8 && y == 1) {
                        if (k == 0) {
                            Empty empty = new Empty("6B");
                            twister.get(x).get(y).addTile(empty);
                        }
                    }

                    if (x == 12 && y == 1) {
                        if (k == 0) {
                            Empty empty = new Empty("6B");
                            twister.get(x).get(y).addTile(empty);
                        }
                    }

                    if (x == 0 && y == 2) {
                        if (k == 0) {
                            Empty empty = new Empty("A");
                            twister.get(x).get(y).addTile(empty);
                        }
                    }

                    if (x == 2 && y == 2) {
                        if (k == 0) {
                            Empty empty = new Empty("A");
                            twister.get(x).get(y).addTile(empty);
                        }
                    }

                    if (x == 3 && y == 2) {
                        if (k == 0) {
                            Empty empty = new Empty("6B");
                            twister.get(x).get(y).addTile(empty);
                        }
                    }

                    if (x == 7 && y == 2) {
                        if (k == 0) {
                            Empty empty = new Empty("6B");
                            twister.get(x).get(y).addTile(empty);
                        }
                    }

                    if (x == 8 && y == 2) {
                        if (k == 0) {
                            Empty empty = new Empty("6B");
                            twister.get(x).get(y).addTile(empty);
                        }
                    }

                    if (x == 12 && y == 2) {
                        if (k == 0) {
                            Empty empty = new Empty("6B");
                            twister.get(x).get(y).addTile(empty);
                        }
                    }

                    if (x == 1 && y == 3) {
                        if (k == 0) {
                            Empty empty = new Empty("A");
                            twister.get(x).get(y).addTile(empty);
                        }
                    }

                    if (x == 2 && y == 3) {
                        if (k == 0) {
                            Empty empty = new Empty("A");
                            twister.get(x).get(y).addTile(empty);
                        }
                    }

                    if (x == 3 && y == 3) {
                        if (k == 0) {
                            Empty empty = new Empty("6B");
                            twister.get(x).get(y).addTile(empty);
                        }
                    }

                    if (x == 7 && y == 3) {
                        if (k == 0) {
                            Empty empty = new Empty("6B");
                            twister.get(x).get(y).addTile(empty);
                        }
                    }

                    if (x == 8 && y == 3) {
                        if (k == 0) {
                            Empty empty = new Empty("6B");
                            twister.get(x).get(y).addTile(empty);
                        }
                    }

                    if (x == 12 && y == 3) {
                        if (k == 0) {
                            Empty empty = new Empty("6B");
                            twister.get(x).get(y).addTile(empty);
                        }
                    }

                    if (x == 4 && y == 4) {
                        if (k == 0) {
                            Empty empty = new Empty("6B");
                            twister.get(x).get(y).addTile(empty);
                        }
                    }

                    if (x == 5 && y == 4) {
                        if (k == 0) {
                            Empty empty = new Empty("6B");
                            twister.get(x).get(y).addTile(empty);
                        }
                    }

                    if (x == 6 && y == 4) {
                        if (k == 0) {
                            Empty empty = new Empty("6B");
                            twister.get(x).get(y).addTile(empty);
                        }
                    }

                    if (x == 9 && y == 4) {
                        if (k == 0) {
                            Empty empty = new Empty("6B");
                            twister.get(x).get(y).addTile(empty);
                        }
                    }

                    if (x == 10 && y == 4) {
                        if (k == 0) {
                            Empty empty = new Empty("6B");
                            twister.get(x).get(y).addTile(empty);
                        }
                    }

                    if (x == 11 && y == 4) {
                        if (k == 0) {
                            Empty empty = new Empty("6B");
                            twister.get(x).get(y).addTile(empty);
                        }
                    }
                    if (x == 0 && y == 5) {
                        if (k == 0) {
                            Empty empty = new Empty("A");
                            twister.get(x).get(y).addTile(empty);
                        }
                    }

                    if (x == 5 && y == 5) {
                        if (k == 0) {
                            Empty empty = new Empty("6B");
                            twister.get(x).get(y).addTile(empty);
                        }
                    }

                    if (x == 6 && y == 5) {
                        if (k == 0) {
                            Empty empty = new Empty("6B");
                            twister.get(x).get(y).addTile(empty);
                        }
                    }

                    if (x == 9 && y == 5) {
                        if (k == 0) {
                            Empty empty = new Empty("6B");
                            twister.get(x).get(y).addTile(empty);
                        }
                    }

                    if (x == 10 && y == 5) {
                        if (k == 0) {
                            Empty empty = new Empty("6B");
                            twister.get(x).get(y).addTile(empty);
                        }
                    }

                    if (x == 11 && y == 5) {
                        if (k == 0) {
                            Empty empty = new Empty("6B");
                            twister.get(x).get(y).addTile(empty);
                        }
                    }

                    if (x == 1 && y == 6) {
                        if (k == 0) {
                            Empty empty = new Empty("A");
                            twister.get(x).get(y).addTile(empty);
                        }
                    }

                    if (x == 2 && y == 6) {
                        if (k == 0) {
                            Empty empty = new Empty("A");
                            twister.get(x).get(y).addTile(empty);
                        }
                    }

                    if (x == 3 && y == 6) {
                        if (k == 0) {
                            Empty empty = new Empty("6B");
                            twister.get(x).get(y).addTile(empty);
                        }
                    }

                    if (x == 7 && y == 6) {
                        if (k == 0) {
                            Empty empty = new Empty("6B");
                            twister.get(x).get(y).addTile(empty);
                        }
                    }

                    if (x == 8 && y == 6) {
                        if (k == 0) {
                            Empty empty = new Empty("6B");
                            twister.get(x).get(y).addTile(empty);
                        }
                    }

                    if (x == 12 && y == 6) {
                        if (k == 0) {
                            Empty empty = new Empty("6B");
                            twister.get(x).get(y).addTile(empty);
                        }
                    }

                    if (x == 2 && y == 7) {
                        if (k == 0) {
                            Empty empty = new Empty("A");
                            twister.get(x).get(y).addTile(empty);
                        }
                    }

                    if (x == 3 && y == 7) {
                        if (k == 0) {
                            Empty empty = new Empty("6B");
                            twister.get(x).get(y).addTile(empty);
                        }
                    }

                    if (x == 7 && y == 7) {
                        if (k == 0) {
                            Empty empty = new Empty("6B");
                            twister.get(x).get(y).addTile(empty);
                        }
                    }

                    if (x == 8 && y == 7) {
                        if (k == 0) {
                            Empty empty = new Empty("6B");
                            twister.get(x).get(y).addTile(empty);
                        }
                    }

                    if (x == 12 && y == 7) {
                        if (k == 0) {
                            Empty empty = new Empty("6B");
                            twister.get(x).get(y).addTile(empty);
                        }
                    }

                    if (x == 0 && y == 8) {
                        if (k == 0) {
                            Empty empty = new Empty("A");
                            twister.get(x).get(y).addTile(empty);
                        }
                    }

                    if (x == 2 && y == 8) {
                        if (k == 0) {
                            Empty empty = new Empty("A");
                            twister.get(x).get(y).addTile(empty);
                        }
                    }

                    if (x == 3 && y == 8) {
                        if (k == 0) {
                            Empty empty = new Empty("6B");
                            twister.get(x).get(y).addTile(empty);
                        }
                    }

                    if (x == 7 && y == 8) {
                        if (k == 0) {
                            Empty empty = new Empty("6B");
                            twister.get(x).get(y).addTile(empty);
                        }
                    }

                    if (x == 8 && y == 8) {
                        if (k == 0) {
                            Empty empty = new Empty("6B");
                            twister.get(x).get(y).addTile(empty);
                        }
                    }

                    if (x == 12 && y == 8) {
                        if (k == 0) {
                            Empty empty = new Empty("6B");
                            twister.get(x).get(y).addTile(empty);
                        }
                    }

                    if (x == 0 && y == 9) {
                        if (k == 0) {
                            Empty empty = new Empty("A");
                            twister.get(x).get(y).addTile(empty);
                        }
                    }

                    if (x == 1 && y == 9) {
                        if (k == 0) {
                            Empty empty = new Empty("A");
                            twister.get(x).get(y).addTile(empty);
                        }
                    }

                    if (x == 3 && y == 9) {
                        if (k == 0) {
                            Empty empty = new Empty("6B");
                            twister.get(x).get(y).addTile(empty);
                        }
                    }

                    if (x == 4 && y == 9) {
                        if (k == 0) {
                            Empty empty = new Empty("6B");
                            twister.get(x).get(y).addTile(empty);
                        }
                    }

                    if (x == 5 && y == 9) {
                        if (k == 0) {
                            Empty empty = new Empty("6B");
                            twister.get(x).get(y).addTile(empty);
                        }
                    }

                    if (x == 6 && y == 9) {
                        if (k == 0) {
                            Empty empty = new Empty("6B");
                            twister.get(x).get(y).addTile(empty);
                        }
                    }

                    if (x == 9 && y == 9) {
                        if (k == 0) {
                            Empty empty = new Empty("6B");
                            twister.get(x).get(y).addTile(empty);
                        }
                    }

                    if (x == 10 && y == 9) {
                        if (k == 0) {
                            Empty empty = new Empty("6B");
                            twister.get(x).get(y).addTile(empty);
                        }
                    }

                    if (x == 11 && y == 9) {
                        if (k == 0) {
                            Empty empty = new Empty("6B");
                            twister.get(x).get(y).addTile(empty);
                        }
                    }

                    if (x == 12 && y == 9) {
                        if (k == 0) {
                            Empty empty = new Empty("6B");
                            twister.get(x).get(y).addTile(empty);
                        }
                    }
                }
            }
        }
        return new GameStarted(new Board(twister));
    }
}


/*
Antenna         done
Pit             notOnMap
BoardLaser      done
CheckPoint      done
ConveyorBelt    done
EnergySpace     done
Floor           done
Gear            notOnMap
PushPanel       notOnMap
RebootPoint     done
StartPoint      done
Wall            done
 */