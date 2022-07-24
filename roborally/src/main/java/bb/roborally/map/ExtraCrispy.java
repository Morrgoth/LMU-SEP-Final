package bb.roborally.map;

import bb.roborally.protocol.map.tiles.*;
import bb.roborally.server.game.Orientation;
import bb.roborally.server.game.board.ServerCell;

import java.util.ArrayList;

/**
 * created Map Extra Crispy
 *
 * @author Muqiu Wang
 * @author Veronika Heckel
 */
public class ExtraCrispy {

    public static ArrayList<ArrayList<ServerCell>> buildExtraCrispy() {
        int xAxis = 13;
        int yAxis = 10;
        int maxCellContent = 4;

        ArrayList<ArrayList<ServerCell>> extraCrispy = new ArrayList<ArrayList<ServerCell>>();

        //for - Schleife x-Koordinaten (äußerste ArrayList)
        for (int i = 0; i < xAxis; i++) {
            extraCrispy.add(new ArrayList<ServerCell>());

            //for - Schleife y-Koordinaten (mittlere ArrayList)
            for (int j = 0; j < yAxis; j++) {
                extraCrispy.get(i).add(new ServerCell(i, j));

                //for - Schleife Cells (innerste ArrayList)
                for (int k = 0; k < maxCellContent; k++) {
                    
                    if(i == 0 && j == 0){
                        if(k == 0){
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.RIGHT);
                            RestartPoint restartPoint = new RestartPoint("A", orientations);
                            extraCrispy.get(i).get(j).addTile(restartPoint);
                        }
                    }

                    if(i == 1 && j == 0){
                        if(k == 0){
                            Empty empty = new Empty("A");
                            extraCrispy.get(i).get(j).addTile(empty);
                        }
                    }

                    if(i == 2 && j == 0){
                        if(k == 0){
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.RIGHT);
                            orientations.add(Orientation.LEFT);
                            ConveyorBelt conveyorBelt = new ConveyorBelt("A", 1, orientations);
                            extraCrispy.get(i).get(j).addTile(conveyorBelt);
                        }
                    }

                    if(i == 3 && j == 0){
                        if(k == 0){
                            Empty empty = new Empty("4A");
                            extraCrispy.get(i).get(j).addTile(empty);
                        }
                    }

                    if(i == 4 && j == 0){
                        if(k == 0) {
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.BOTTOM);
                            orientations.add(Orientation.TOP);
                            ConveyorBelt conveyorBelt = new ConveyorBelt("4A", 2, orientations);
                            extraCrispy.get(i).get(j).addTile(conveyorBelt);
                        }
                    }

                    if(i == 5 && j == 0){
                        if(k == 0){
                            Empty empty = new Empty("4A");
                            extraCrispy.get(i).get(j).addTile(empty);
                        }
                    }

                    if(i == 6 && j == 0){
                        if(k == 0){
                            Empty empty = new Empty("4A");
                            extraCrispy.get(i).get(j).addTile(empty);
                        }
                    }

                    if(i == 7 && j == 0){
                        if(k == 0){
                            Empty empty = new Empty("4A");
                            extraCrispy.get(i).get(j).addTile(empty);
                        }
                    }

                    if(i == 8 && j == 0){
                        if(k == 0){
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.TOP);
                            EnergySpace energySpace = new EnergySpace("4A", orientations, 1);
                            extraCrispy.get(i).get(j).addTile(energySpace);
                        }
                    }

                    if(i == 9 && j == 0){
                        if(k == 0){
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.LEFT);
                            Wall wall = new Wall("4A", orientations);
                            extraCrispy.get(i).get(j).addTile(wall);
                        }

                        if(k == 0){
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.LEFT);
                            Laser laser = new Laser("4A", orientations,1);
                            extraCrispy.get(i).get(j).addTile(laser);
                        }
                    }

                    if(i == 10 && j == 0){
                        if(k == 0){
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.LEFT);
                            orientations.add(Orientation.RIGHT);
                            Laser laser = new Laser("4A", orientations,1);
                            extraCrispy.get(i).get(j).addTile(laser);
                        }
                    }

                    if(i == 11 && j == 0){
                        if(k == 0){
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.RIGHT);
                            Wall wall = new Wall("4A", orientations);
                            extraCrispy.get(i).get(j).addTile(wall);
                        }

                        if(k == 0){
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.RIGHT);
                            Laser laser = new Laser("4A", orientations,1);
                            extraCrispy.get(i).get(j).addTile(laser);
                        }
                    }

                    if(i == 12 && j == 0){
                        if(k == 0){
                            Empty empty = new Empty("4A");
                            extraCrispy.get(i).get(j).addTile(empty);
                        }
                    }

                    if(i == 0 && j == 1){
                        if(k == 0){
                            Empty empty = new Empty("A");
                            extraCrispy.get(i).get(j).addTile(empty);
                        }
                    }

                    if(i == 1 && j == 1){
                        if(k == 0){
                            StartPoint startPoint = new StartPoint("A");
                            extraCrispy.get(i).get(j).addTile(startPoint);
                        }
                    }

                    if(i == 2 && j == 1){
                        if(k == 0){
                            Empty empty = new Empty("A");
                            extraCrispy.get(i).get(j).addTile(empty);
                        }
                    }

                    if(i == 3 && j == 1){
                        if(k == 0){
                            Empty empty = new Empty("4A");
                            extraCrispy.get(i).get(j).addTile(empty);
                        }
                    }

                    if(i == 4 && j == 1){
                        if(k == 0) {
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.BOTTOM);
                            orientations.add(Orientation.TOP);
                            ConveyorBelt conveyorBelt = new ConveyorBelt("4A", 2, orientations);
                            extraCrispy.get(i).get(j).addTile(conveyorBelt);
                        }
                    }

                    if(i == 5 && j == 1){
                        if(k == 0){
                            Empty empty = new Empty("4A");
                            extraCrispy.get(i).get(j).addTile(empty);
                        }
                    }

                    if(i == 6 && j == 1){
                        if(k == 0){
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.BOTTOM);
                            orientations.add(Orientation.RIGHT);
                            ConveyorBelt conveyorBelt = new ConveyorBelt("4A", 1, orientations);
                            extraCrispy.get(i).get(j).addTile(conveyorBelt);
                        }
                    }

                    if(i == 7 && j == 1){
                        if(k == 0){
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.LEFT);
                            orientations.add(Orientation.RIGHT);
                            ConveyorBelt conveyorBelt = new ConveyorBelt("4A", 1, orientations);
                            extraCrispy.get(i).get(j).addTile(conveyorBelt);
                        }
                    }

                    if(i == 8 && j == 1){
                        if(k == 0){
                            Empty empty = new Empty("4A");
                            extraCrispy.get(i).get(j).addTile(empty);
                        }
                    }

                    if(i == 9 && j == 1){
                        if(k == 0){
                            Empty empty = new Empty("4A");
                            extraCrispy.get(i).get(j).addTile(empty);
                        }
                    }

                    if(i == 10 && j == 1){
                        if(k == 0){
                            Empty empty = new Empty("4A");
                            extraCrispy.get(i).get(j).addTile(empty);
                        }
                    }

                    if(i == 11 && j == 1){
                        if(k == 0){
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.BOTTOM);
                            orientations.add(Orientation.TOP);
                            ConveyorBelt conveyorBelt = new ConveyorBelt("4A", 2, orientations);
                            extraCrispy.get(i).get(j).addTile(conveyorBelt);
                        }
                    }

                    if(i == 12 && j == 1){
                        if(k == 0){
                            Empty empty = new Empty("4A");
                            extraCrispy.get(i).get(j).addTile(empty);
                        }
                    }

                    if(i == 0 && j == 2){
                        if(k == 0){
                            Empty empty = new Empty("A");
                            extraCrispy.get(i).get(j).addTile(empty);
                        }
                    }

                    if(i == 1 && j == 2){
                        if(k == 0){
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.TOP);
                            Wall wall = new Wall("A", orientations);
                            extraCrispy.get(i).get(j).addTile(wall);
                        }
                    }

                    if(i == 2 && j == 2){
                        if(k == 0){
                            Empty empty = new Empty("A");
                            extraCrispy.get(i).get(j).addTile(empty);
                        }
                    }

                    if(i == 3 && j == 2){
                        if(k == 0){
                            Empty empty = new Empty("4A");
                            extraCrispy.get(i).get(j).addTile(empty);
                        }
                    }

                    if(i == 4 && j == 2){
                        if(k == 0){
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.BOTTOM);
                            orientations.add(Orientation.TOP);
                            ConveyorBelt conveyorBelt = new ConveyorBelt("4A", 2, orientations);
                            extraCrispy.get(i).get(j).addTile(conveyorBelt);
                        }
                    }

                    if(i == 5 && j == 2){
                        if(k == 0) {
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.TOP);
                            Wall wall = new Wall("4A", orientations);
                            extraCrispy.get(i).get(j).addTile(wall);
                        }
                        if(k == 1) {
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.TOP);
                            Laser laser = new Laser("4A", orientations,1);
                            extraCrispy.get(i).get(j).addTile(laser);
                        }
                        if(k == 2){
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.RIGHT);
                            CheckPoint checkPoint = new CheckPoint("4A",orientations,4);
                            extraCrispy.get(i).get(j).addTile(checkPoint);
                        }
                    }

                    if(i == 6 && j == 2){
                        if(k == 0){
                            Pit pit = new Pit("4A");
                            extraCrispy.get(i).get(j).addTile(pit);
                        }
                    }

                    if(i == 7 && j == 2){
                        if(k == 0){
                            Empty empty = new Empty("4A");
                            extraCrispy.get(i).get(j).addTile(empty);
                        }
                    }

                    if(i == 8 && j == 2){
                        if(k == 0){
                            Empty empty = new Empty("4A");
                            extraCrispy.get(i).get(j).addTile(empty);
                        }
                    }

                    if(i == 9 && j == 2){
                        if(k == 0){
                            Pit pit = new Pit("4A");
                            extraCrispy.get(i).get(j).addTile(pit);
                        }
                    }

                    if(i == 10 && j == 2){
                        if(k == 0) {
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.TOP);
                            Wall wall = new Wall("4A", orientations);
                            extraCrispy.get(i).get(j).addTile(wall);
                        }
                        if(k == 1) {
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.TOP);
                            Laser laser = new Laser("4A", orientations,1);
                            extraCrispy.get(i).get(j).addTile(laser);
                        }
                        if(k == 2){
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.RIGHT);
                            CheckPoint checkPoint = new CheckPoint("4A",orientations,1);
                            extraCrispy.get(i).get(j).addTile(checkPoint);
                        }
                    }

                    if(i == 11 && j == 2){
                        if(k == 0){
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.BOTTOM);
                            orientations.add(Orientation.TOP);
                            ConveyorBelt conveyorBelt = new ConveyorBelt("4A", 2, orientations);
                            extraCrispy.get(i).get(j).addTile(conveyorBelt);
                        }
                    }

                    if(i == 12 && j == 2){
                        if(k == 0){
                            Empty empty = new Empty("4A");
                            extraCrispy.get(i).get(j).addTile(empty);
                        }
                    }

                    if(i == 0 && j == 3){
                        if(k == 0){
                            StartPoint startPoint = new StartPoint("A");
                            extraCrispy.get(i).get(j).addTile(startPoint);
                        }
                    }

                    if(i == 1 && j == 3){
                        if(k == 0){
                            Empty empty = new Empty("A");
                            extraCrispy.get(i).get(j).addTile(empty);
                        }
                    }

                    if(i == 2 && j == 3){
                        if(k == 0){
                            Empty empty = new Empty("A");
                            extraCrispy.get(i).get(j).addTile(empty);
                        }
                    }

                    if(i == 3 && j == 3){
                        if(k == 0){
                            Empty empty = new Empty("4A");
                            extraCrispy.get(i).get(j).addTile(empty);
                        }
                    }

                    if(i == 4 && j == 3){
                        if(k == 0){
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.TOP);
                            orientations.add(Orientation.RIGHT);
                            ConveyorBelt conveyorBelt = new ConveyorBelt("4A", 2, orientations);
                            extraCrispy.get(i).get(j).addTile(conveyorBelt);
                        }
                    }

                    if(i == 5 && j == 3){
                        if(k == 0){
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.RIGHT);
                            orientations.add(Orientation.LEFT);
                            ConveyorBelt conveyorBelt = new ConveyorBelt("4A", 2, orientations);
                            extraCrispy.get(i).get(j).addTile(conveyorBelt);
                        }

                        if(k == 1){
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.TOP);
                            orientations.add(Orientation.BOTTOM);
                            Laser laser = new Laser("4A", orientations,1);
                            extraCrispy.get(i).get(j).addTile(laser);
                        }
                    }

                    if(i == 6 && j == 3){
                        if(k == 0){
                            Pit pit = new Pit("4A");
                            extraCrispy.get(i).get(j).addTile(pit);
                        }
                    }

                    if(i == 7 && j == 3){
                        if(k == 0){
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.LEFT);
                            Wall wall = new Wall("4A", orientations);
                            extraCrispy.get(i).get(j).addTile(wall);
                        }

                        if(k == 1){
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.LEFT);
                            Laser laser = new Laser("4A", orientations,1);
                            extraCrispy.get(i).get(j).addTile(laser);
                        }
                    }

                    if(i == 8 && j == 3){
                        if(k == 0){
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.RIGHT);
                            Wall wall = new Wall("4A", orientations);
                            extraCrispy.get(i).get(j).addTile(wall);
                        }

                        if(k == 1){
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.RIGHT);
                            Laser laser = new Laser("4A", orientations,1);
                            extraCrispy.get(i).get(j).addTile(laser);
                        }
                    }

                    if(i == 9 && j == 3){
                        if(k == 0){
                            Pit pit = new Pit("4A");
                            extraCrispy.get(i).get(j).addTile(pit);
                        }
                    }

                    if(i == 10 && j == 3){
                        if(k == 0){
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.LEFT);
                            orientations.add(Orientation.RIGHT);
                            ConveyorBelt conveyorBelt = new ConveyorBelt("4A", 2, orientations);
                            extraCrispy.get(i).get(j).addTile(conveyorBelt);
                        }

                        if(k == 1){
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.TOP);
                            orientations.add(Orientation.BOTTOM);
                            Laser laser = new Laser("4A", orientations,1);
                            extraCrispy.get(i).get(j).addTile(laser);
                        }
                    }

                    if(i == 11 && j == 3){
                        if(k == 0){
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.LEFT);
                            orientations.add(Orientation.TOP);
                            ConveyorBelt conveyorBelt = new ConveyorBelt("4A", 2, orientations);
                            extraCrispy.get(i).get(j).addTile(conveyorBelt);
                        }
                    }

                    if(i == 12 && j == 3){
                        if(k == 0){
                            Empty empty = new Empty("4A");
                            extraCrispy.get(i).get(j).addTile(empty);
                        }
                    }

                    if(i == 0 && j == 4){
                        if(k == 0){
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.RIGHT);
                            Antenna antenna = new Antenna("A", orientations);
                            extraCrispy.get(i).get(j).addTile(antenna);
                        }
                    }

                    if(i == 1 && j == 4){
                        if(k == 0){
                            StartPoint startPoint = new StartPoint("A");
                            extraCrispy.get(i).get(j).addTile(startPoint);
                        }
                    }

                    if(i == 2 && j == 4){
                        if(k == 0){
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.RIGHT);
                            Wall wall = new Wall("A", orientations);
                            extraCrispy.get(i).get(j).addTile(wall);
                        }
                    }

                    if(i == 3 && j == 4){
                        if(k == 0){
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.TOP);
                            Wall wall = new Wall("4A", orientations);
                            extraCrispy.get(i).get(j).addTile(wall);
                        }

                        if(k == 1){
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.LEFT);
                            EnergySpace energySpace = new EnergySpace("4A", orientations,1);
                            extraCrispy.get(i).get(j).addTile(energySpace);
                        }
                    }

                    if(i == 4 && j == 4){
                        if(k == 0){
                            Empty empty = new Empty("4A");
                            extraCrispy.get(i).get(j).addTile(empty);
                        }
                    }

                    if(i == 5 && j == 4){
                        if(k == 0){
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.TOP);
                            orientations.add(Orientation.BOTTOM);
                            Laser laser = new Laser("4A", orientations,1);
                            extraCrispy.get(i).get(j).addTile(laser);
                        }
                    }

                    if(i == 6 && j == 4){
                        if(k == 0){
                            ArrayList<Orientation> orientations= new ArrayList<>();
                            orientations.add(Orientation.CLOCKWISE);
                            Gear gear = new Gear("4A",orientations);
                            extraCrispy.get(i).get(j).addTile(gear);
                        }
                    }

                    if(i == 7 && j == 4){
                        if(k == 0){
                            Empty empty = new Empty("4A");
                            extraCrispy.get(i).get(j).addTile(empty);
                        }
                    }

                    if(i == 8 && j == 4){
                        if(k == 0){
                            Empty empty = new Empty("4A");
                            extraCrispy.get(i).get(j).addTile(empty);
                        }
                    }

                    if(i == 9 && j == 4){
                        if(k == 0){
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.RIGHT);
                            Wall wall = new Wall("4A", orientations);
                            extraCrispy.get(i).get(j).addTile(wall);
                        }
                    }

                    if(i == 10 && j == 4){
                        if(k == 0){
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.TOP);
                            orientations.add(Orientation.BOTTOM);
                            Laser laser = new Laser("4A", orientations,1);
                            extraCrispy.get(i).get(j).addTile(laser);
                        }
                    }

                    if(i == 11 && j == 4){
                        if(k == 0){
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.TOP);
                            EnergySpace energySpace = new EnergySpace("4A", orientations,1);
                            extraCrispy.get(i).get(j).addTile(energySpace);
                        }
                    }

                    if(i == 12 && j == 4){
                        if(k == 0){
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.TOP);
                            Wall wall = new Wall("4A", orientations);
                            extraCrispy.get(i).get(j).addTile(wall);
                        }
                    }

                    if(i == 0 && j == 5){
                        if(k == 0){
                            Empty empty = new Empty("A");
                            extraCrispy.get(i).get(j).addTile(empty);
                        }
                    }

                    if(i == 1 && j == 5){
                        if(k == 0){
                            StartPoint startPoint = new StartPoint("A");
                            extraCrispy.get(i).get(j).addTile(startPoint);
                        }
                    }

                    if(i == 2 && j == 5){
                        if(k == 0){
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.RIGHT);
                            Wall wall = new Wall("A", orientations);
                            extraCrispy.get(i).get(j).addTile(wall);
                        }
                    }

                    if(i == 3 && j == 5){
                        if(k == 0){
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.BOTTOM);
                            Wall wall = new Wall("4A", orientations);
                            extraCrispy.get(i).get(j).addTile(wall);
                        }
                    }

                    if(i == 4 && j == 5){
                        if(k == 0){
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            Empty empty = new Empty("4A");
                            extraCrispy.get(i).get(j).addTile(empty);
                        }
                    }

                    if(i == 5 && j == 5){
                        if(k == 0){
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.TOP);
                            orientations.add(Orientation.BOTTOM);
                            Laser laser = new Laser("4A", orientations,1);
                            extraCrispy.get(i).get(j).addTile(laser);
                        }
                    }

                    if(i == 6 && j == 5){
                        if(k == 0){
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.LEFT);
                            Wall wall = new Wall( "4A", orientations);
                            extraCrispy.get(i).get(j).addTile(wall);
                        }
                    }

                    if(i == 7 && j == 5){
                        if(k == 0){
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.TOP);
                            EnergySpace energySpace = new EnergySpace("4A", orientations,1);
                            extraCrispy.get(i).get(j).addTile(energySpace);
                        }
                    }

                    if(i == 8 && j == 5){
                        if(k == 0){
                            Empty empty = new Empty("4A");
                            extraCrispy.get(i).get(j).addTile(empty);
                        }
                    }

                    if(i == 9 && j == 5){
                        if(k == 0){
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.COUNTERCLOCKWISE);
                            Gear gear = new Gear("4A",orientations);
                            extraCrispy.get(i).get(j).addTile(gear);
                        }
                    }

                    if(i == 10 && j == 5){
                        if(k == 0){
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.TOP);
                            orientations.add(Orientation.BOTTOM);
                            Laser laser = new Laser("4A", orientations,1);
                            extraCrispy.get(i).get(j).addTile(laser);
                        }
                    }

                    if(i == 11 && j == 5){
                        if(k == 0){
                            Empty empty = new Empty("4A");
                            extraCrispy.get(i).get(j).addTile(empty);
                        }
                    }

                    if(i == 12 && j == 5){
                        if(k == 0){
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.BOTTOM);
                            Wall wall = new Wall("4A", orientations);
                            extraCrispy.get(i).get(j).addTile(wall);
                        }
                    }

                    if(i == 0 && j == 6){
                        if(k == 0){
                            StartPoint startPoint = new StartPoint("A");
                            extraCrispy.get(i).get(j).addTile(startPoint);
                        }
                    }

                    if(i == 1 && j == 6){
                        if(k == 0){
                            Empty empty = new Empty("A");
                            extraCrispy.get(i).get(j).addTile(empty);
                        }
                    }

                    if(i == 2 && j == 6){
                        if(k == 0){
                            Empty empty = new Empty("A");
                            extraCrispy.get(i).get(j).addTile(empty);
                        }
                    }

                    if(i == 3 && j == 6){
                        if(k == 0){
                            Empty empty = new Empty("4A");
                            extraCrispy.get(i).get(j).addTile(empty);
                        }
                    }

                    if(i == 4 && j == 6){
                        if(k == 0){
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.RIGHT);
                            orientations.add(Orientation.BOTTOM);
                            ConveyorBelt conveyorBelt = new ConveyorBelt("4A", 2, orientations);
                            extraCrispy.get(i).get(j).addTile(conveyorBelt);
                        }
                    }

                    if(i == 5 && j == 6){
                        if(k == 0){
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.RIGHT);
                            orientations.add(Orientation.LEFT);
                            ConveyorBelt conveyorBelt = new ConveyorBelt("4A", 2, orientations);
                            extraCrispy.get(i).get(j).addTile(conveyorBelt);
                        }

                        if(k == 1){
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.TOP);
                            orientations.add(Orientation.BOTTOM);
                            Laser laser = new Laser("4A", orientations,1);
                            extraCrispy.get(i).get(j).addTile(laser);
                        }
                    }

                    if(i == 6 && j == 6){
                        if(k == 0){
                            Pit pit = new Pit("4A");
                            extraCrispy.get(i).get(j).addTile(pit);
                        }
                    }

                    if(i == 7 && j == 6){
                        if(k == 0){
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.LEFT);
                            Wall wall = new Wall("4A", orientations);
                            extraCrispy.get(i).get(j).addTile(wall);
                        }

                        if(k == 1){
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.LEFT);
                            Laser laser = new Laser("4A", orientations,1);
                            extraCrispy.get(i).get(j).addTile(laser);
                        }
                    }

                    if(i == 8 && j == 6){
                        if(k == 0){
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.RIGHT);
                            Wall wall = new Wall("4A", orientations);
                            extraCrispy.get(i).get(j).addTile(wall);
                        }

                        if(k == 1){
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.RIGHT);
                            Laser laser = new Laser("4A", orientations,1);
                            extraCrispy.get(i).get(j).addTile(laser);
                        }
                    }

                    if(i == 9 && j == 6){
                        if(k == 0){
                            Pit pit = new Pit("4A");
                            extraCrispy.get(i).get(j).addTile(pit);
                        }
                    }

                    if(i == 10 && j == 6){
                        if(k == 0){
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.LEFT);
                            orientations.add(Orientation.RIGHT);
                            ConveyorBelt conveyorBelt = new ConveyorBelt("4A", 2, orientations);
                            extraCrispy.get(i).get(j).addTile(conveyorBelt);
                        }

                        if(k == 1){
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.TOP);
                            orientations.add(Orientation.BOTTOM);
                            Laser laser = new Laser("4A", orientations,1);
                            extraCrispy.get(i).get(j).addTile(laser);
                        }
                    }

                    if(i == 11 && j == 6){
                        if(k == 0){
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.LEFT);
                            orientations.add(Orientation.BOTTOM);
                            ConveyorBelt conveyorBelt = new ConveyorBelt("4A", 2, orientations);
                            extraCrispy.get(i).get(j).addTile(conveyorBelt);
                        }
                    }

                    if(i == 12 && j == 6){
                        if(k == 0){
                            Empty empty = new Empty("4A");
                            extraCrispy.get(i).get(j).addTile(empty);
                        }
                    }

                    if(i == 0 && j == 7){
                        if(k == 0){
                            Empty empty = new Empty("A");
                            extraCrispy.get(i).get(j).addTile(empty);
                        }
                    }

                    if(i == 1 && j == 7){
                        if(k == 0){
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.BOTTOM);
                            Wall wall = new Wall("A", orientations);
                            extraCrispy.get(i).get(j).addTile(wall);
                        }
                    }

                    if(i == 2 && j == 7){
                        if(k == 0){
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            Empty empty = new Empty("A");
                            extraCrispy.get(i).get(j).addTile(empty);
                        }
                    }

                    if(i == 3 && j == 7){
                        if(k == 0){
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            Empty empty = new Empty("4A");
                            extraCrispy.get(i).get(j).addTile(empty);
                        }
                    }

                    if(i == 4 && j == 7){
                        if(k == 0){
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.TOP);
                            orientations.add(Orientation.BOTTOM);
                            ConveyorBelt conveyorBelt = new ConveyorBelt("4A", 2, orientations);
                            extraCrispy.get(i).get(j).addTile(conveyorBelt);
                        }
                    }

                    if(i == 5 && j == 7){
                        if(k == 0) {
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.BOTTOM);
                            Wall wall = new Wall("4A", orientations);
                            extraCrispy.get(i).get(j).addTile(wall);
                        }
                        if(k == 1) {
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.BOTTOM);
                            Laser laser = new Laser("4A", orientations,1);
                            extraCrispy.get(i).get(j).addTile(laser);
                        }
                        if(k == 2){
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.RIGHT);
                            CheckPoint checkPoint = new CheckPoint("4A",orientations,2);
                            extraCrispy.get(i).get(j).addTile(checkPoint);
                        }
                    }

                    if(i == 6 && j == 7){
                        if(k == 0){
                            Pit pit = new Pit("4A");
                            extraCrispy.get(i).get(j).addTile(pit);
                        }
                    }

                    if(i == 7 && j == 7){
                        if(k == 0){
                            Empty empty = new Empty("4A");
                            extraCrispy.get(i).get(j).addTile(empty);
                        }
                    }

                    if(i == 8 && j == 7){
                        if(k == 0){
                            Empty empty = new Empty("4A");
                            extraCrispy.get(i).get(j).addTile(empty);
                        }
                    }

                    if(i == 9 && j == 7){
                        if(k == 0){
                            Pit pit = new Pit("4A");
                            extraCrispy.get(i).get(j).addTile(pit);
                        }
                    }

                    if(i == 10 && j == 7){
                        if(k == 0) {
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.BOTTOM);
                            Wall wall = new Wall("4A", orientations);
                            extraCrispy.get(i).get(j).addTile(wall);
                        }
                        if(k == 1) {
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.BOTTOM);
                            Laser laser = new Laser("4A", orientations,1);
                            extraCrispy.get(i).get(j).addTile(laser);
                        }
                        if(k == 2){
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.RIGHT);
                            CheckPoint checkPoint = new CheckPoint("4A",orientations,3);
                            extraCrispy.get(i).get(j).addTile(checkPoint);
                        }
                    }

                    if(i == 11 && j == 7){
                        if(k == 0){
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.TOP);
                            orientations.add(Orientation.BOTTOM);
                            ConveyorBelt conveyorBelt = new ConveyorBelt("4A", 2, orientations);
                            extraCrispy.get(i).get(j).addTile(conveyorBelt);
                        }
                    }

                    if(i == 12 && j == 7){
                        if(k == 0){
                            Empty empty = new Empty("4A");
                            extraCrispy.get(i).get(j).addTile(empty);
                        }
                    }

                    if(i == 0 && j == 8){
                        if(k == 0){
                            Empty empty = new Empty("A");
                            extraCrispy.get(i).get(j).addTile(empty);
                        }
                    }

                    if(i == 1 && j == 8){
                        if(k == 0) {
                            StartPoint startPoint = new StartPoint("A");
                            extraCrispy.get(i).get(j).addTile(startPoint);
                        }
                    }

                    if(i == 2 && j == 8){
                        if(k == 0){
                            Empty empty = new Empty("A");
                            extraCrispy.get(i).get(j).addTile(empty);
                        }
                    }

                    if(i == 3 && j == 8){
                        if(k == 0){
                            Empty empty = new Empty("4A");
                            extraCrispy.get(i).get(j).addTile(empty);
                        }
                    }

                    if(i == 4 && j == 8){
                        if(k == 0){
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.TOP);
                            orientations.add(Orientation.BOTTOM);
                            ConveyorBelt conveyorBelt = new ConveyorBelt("4A", 2, orientations);
                            extraCrispy.get(i).get(j).addTile(conveyorBelt);
                        }
                    }

                    if(i == 5 && j == 8){
                        if(k == 0){
                            Empty empty = new Empty("A");
                            extraCrispy.get(i).get(j).addTile(empty);
                        }
                    }

                    if(i == 6 && j == 8){
                        if(k == 0){
                            Empty empty = new Empty("4A");
                            extraCrispy.get(i).get(j).addTile(empty);
                        }
                    }

                    if(i == 7 && j == 8){
                        if(k == 0){
                            Empty empty = new Empty("4A");
                            extraCrispy.get(i).get(j).addTile(empty);
                        }
                    }

                    if(i == 8 && j == 8){
                        if(k == 0){
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.RIGHT);
                            orientations.add(Orientation.LEFT);
                            ConveyorBelt conveyorBelt = new ConveyorBelt("4A", 1, orientations);
                            extraCrispy.get(i).get(j).addTile(conveyorBelt);
                        }
                    }

                    if(i == 9 && j == 8){
                        if(k == 0){
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.TOP);
                            orientations.add(Orientation.LEFT);
                            ConveyorBelt conveyorBelt = new ConveyorBelt("4A", 1, orientations);
                            extraCrispy.get(i).get(j).addTile(conveyorBelt);
                        }
                    }

                    if(i == 10 && j == 8) {
                        if (k == 0) {
                            Empty empty = new Empty("4A");
                            extraCrispy.get(i).get(j).addTile(empty);
                        }
                    }

                    if(i == 11 && j == 8){
                        if(k == 0){
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.TOP);
                            orientations.add(Orientation.BOTTOM);
                            ConveyorBelt conveyorBelt = new ConveyorBelt("4A", 2, orientations);
                            extraCrispy.get(i).get(j).addTile(conveyorBelt);
                        }
                    }

                    if(i == 12 && j == 8){
                        if(k == 0){
                            Empty empty = new Empty("4A");
                            extraCrispy.get(i).get(j).addTile(empty);
                        }
                    }

                    if(i == 0 && j == 9){
                        if(k == 0){
                            Empty empty = new Empty("A");
                            extraCrispy.get(i).get(j).addTile(empty);
                        }
                    }

                    if(i == 1 && j == 9){
                        if(k == 0){
                            Empty empty = new Empty("A");
                            extraCrispy.get(i).get(j).addTile(empty);
                        }
                    }

                    if(i == 2 && j == 9){
                        if(k == 0){
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.RIGHT);
                            orientations.add(Orientation.LEFT);
                            ConveyorBelt conveyorBelt = new ConveyorBelt("A", 1, orientations);
                            extraCrispy.get(i).get(j).addTile(conveyorBelt);
                        }
                    }

                    if(i == 3 && j == 9){
                        if(k == 0){
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.TOP);
                            EnergySpace energySpace = new EnergySpace("4A", orientations, 1);
                            extraCrispy.get(i).get(j).addTile(energySpace);
                        }
                    }

                    if(i == 4 && j == 9){
                        if(k == 0) {
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.LEFT);
                            Wall wall = new Wall("4A", orientations);
                            extraCrispy.get(i).get(j).addTile(wall);
                        }
                        if(k == 1) {
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.LEFT);
                            Laser laser = new Laser("4A", orientations,1);
                            extraCrispy.get(i).get(j).addTile(laser);
                        }
                    }

                    if(i == 5 && j == 9){
                        if(k == 0){
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.LEFT);
                            orientations.add(Orientation.RIGHT);
                            Laser laser = new Laser("4A", orientations,1);
                            extraCrispy.get(i).get(j).addTile(laser);
                        }
                    }

                    if(i == 6 && j == 9){
                        if(k == 0) {
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.RIGHT);
                            Wall wall = new Wall("4A", orientations);
                            extraCrispy.get(i).get(j).addTile(wall);
                        }
                        if(k == 1) {
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.RIGHT);
                            Laser laser = new Laser("4A", orientations,1);
                            extraCrispy.get(i).get(j).addTile(laser);
                        }
                    }

                    if(i == 7 && j == 9){
                        if(k == 0){
                            Empty empty = new Empty("4A");
                            extraCrispy.get(i).get(j).addTile(empty);
                        }
                    }

                    if(i == 8 && j == 9){
                        if(k == 0){
                            Empty empty = new Empty("4A");
                            extraCrispy.get(i).get(j).addTile(empty);
                        }
                    }

                    if(i == 9 && j == 9){
                        if(k == 0){
                            Empty empty = new Empty("4A");
                            extraCrispy.get(i).get(j).addTile(empty);
                        }
                    }

                    if(i == 10 && j == 9){
                        if(k == 0){
                            Empty empty = new Empty("4A");
                            extraCrispy.get(i).get(j).addTile(empty);
                        }
                    }

                    if(i == 11 && j == 9){
                        if(k == 0){
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.TOP);
                            orientations.add(Orientation.BOTTOM);
                            ConveyorBelt conveyorBelt = new ConveyorBelt("4A", 2, orientations);
                            extraCrispy.get(i).get(j).addTile(conveyorBelt);
                        }
                    }

                    if(i == 12 && j == 9){
                        if(k == 0){
                            Empty empty = new Empty("4A");
                            extraCrispy.get(i).get(j).addTile(empty);
                        }
                    }
                }
            }
        }
        return extraCrispy;
    }
}
