package bb.roborally.game.map;

import bb.roborally.game.Orientation;
import bb.roborally.game.Position;
import bb.roborally.game.board.Board;
import bb.roborally.game.tiles.*;


import java.util.ArrayList;

/**
 * created Map Extra Crispy
 *
 * @author Muqiu Wang
 * @author Veronika Heckel
 */
public class ExtraCrispy extends Board {
    private ArrayList<ArrayList<ArrayList<Tile>>> extraCrispy;


    public ExtraCrispy (ArrayList<ArrayList<ArrayList<Tile>>> extraCrispy) {
        super(extraCrispy);
        this.extraCrispy = extraCrispy;
    }


    public void buildExtraCrispy() {
        int xAxis = 13;
        int yAxis = 10;
        int maxCellContent = 4;

        extraCrispy = new ArrayList<ArrayList<ArrayList<Tile>>>();

        //for - Schleife x-Koordinaten (äußerste ArrayList)
        for (int i = 0; i < xAxis; i++) {
            extraCrispy.add(new ArrayList<ArrayList<Tile>>());

            //for - Schleife y-Koordinaten (mittlere ArrayList)
            for (int j = 0; j < yAxis; j++) {
                extraCrispy.get(i).add(new ArrayList<Tile>());

                //for - Schleife Cells (innerste ArrayList)
                for (int k = 0; k < maxCellContent; k++) {
                    if (i >= 0 && i <= 2) {
                        if (k == 0) {
                            Empty empty = new Empty("A");
                            extraCrispy.get(i).get(j).add(empty);
                        }
                    }

                    if (i >= 3 && i <= 12) {
                        if (k == 0) {
                            Empty empty = new Empty("4A");
                            extraCrispy.get(i).get(j).add(empty);
                        }
                    }

                    if(i == 0 && j == 0){
                        if(k == 1){
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.RIGHT);
                            RestartPoint restartPoint = new RestartPoint("A", orientations);
                            extraCrispy.get(i).get(j).add(restartPoint);
                        }
                    }

                    if(i == 2 && j == 0){
                        if(k == 1){
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.RIGHT);
                            orientations.add(Orientation.LEFT);
                            ConveyorBelt conveyorBelt = new ConveyorBelt("A", 1, orientations);
                            extraCrispy.get(i).get(j).add(conveyorBelt);
                        }
                    }

                    if(i == 4 && j == 0){
                        if(k == 1) {
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.BOTTOM);
                            orientations.add(Orientation.TOP);
                            ConveyorBelt conveyorBelt = new ConveyorBelt("4A", 2, orientations);
                            extraCrispy.get(i).get(j).add(conveyorBelt);
                        }
                    }

                    if(i == 8 && j == 0){
                        if(k == 1){
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.VERTICAL);
                            EnergySpace energySpace = new EnergySpace("4A", orientations, 1);
                            extraCrispy.get(i).get(j).add(energySpace);
                        }
                    }

                    if(i == 9 && j == 0){
                        if(k == 1){
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.LEFT);
                            Wall wall = new Wall("4A", orientations);
                            extraCrispy.get(i).get(j).add(wall);
                        }

                        if(k == 2){
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.LEFT);
                            Laser laser = new Laser("4A", orientations,1);
                            extraCrispy.get(i).get(j).add(laser);
                        }
                    }
                    if(i == 10 && j == 0){
                        if(k == 1){
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.LEFT);
                            orientations.add(Orientation.RIGHT);
                            Laser laser = new Laser("4A", orientations,1);
                            extraCrispy.get(i).get(j).add(laser);
                        }
                    }

                    if(i == 11 && j == 0){
                        if(k == 1){
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.RIGHT);
                            Wall wall = new Wall("4A", orientations);
                            extraCrispy.get(i).get(j).add(wall);
                        }

                        if(k == 2){
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.RIGHT);
                            Laser laser = new Laser("4A", orientations,1);
                            extraCrispy.get(i).get(j).add(laser);
                        }
                    }

                    if(i == 1 && j == 1){
                        if(k == 1){
                            StartPoint startPoint = new StartPoint("A");
                            extraCrispy.get(i).get(j).add(startPoint);
                        }
                    }

                    if(i == 4 && j == 1){
                        if(k == 1) {
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.BOTTOM);
                            orientations.add(Orientation.TOP);
                            ConveyorBelt conveyorBelt = new ConveyorBelt("4A", 2, orientations);
                            extraCrispy.get(i).get(j).add(conveyorBelt);
                        }
                    }

                    if(i == 6 && j == 1){
                        if(k == 1){
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.BOTTOM);
                            orientations.add(Orientation.RIGHT);
                            ConveyorBelt conveyorBelt = new ConveyorBelt("4A", 1, orientations);
                            extraCrispy.get(i).get(j).add(conveyorBelt);
                        }
                    }

                    if(i == 7 && j == 1){
                        if(k == 1){
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.LEFT);
                            orientations.add(Orientation.RIGHT);
                            ConveyorBelt conveyorBelt = new ConveyorBelt("4A", 1, orientations);
                            extraCrispy.get(i).get(j).add(conveyorBelt);
                        }
                    }

                    if(i == 11 && j == 1){
                        if(k == 1){
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.BOTTOM);
                            orientations.add(Orientation.TOP);
                            ConveyorBelt conveyorBelt = new ConveyorBelt("4A", 2, orientations);
                            extraCrispy.get(i).get(j).add(conveyorBelt);
                        }
                    }

                    if(i == 1 && j == 2){
                        if(k == 1){
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.TOP);
                            Wall wall = new Wall("A", orientations);
                            extraCrispy.get(i).get(j).add(wall);
                        }
                    }

                    if(i == 4 && j == 2){
                        if(k == 1){
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.BOTTOM);
                            orientations.add(Orientation.TOP);
                            ConveyorBelt conveyorBelt = new ConveyorBelt("4A", 2, orientations);
                            extraCrispy.get(i).get(j).add(conveyorBelt);
                        }
                    }

                    if(i == 5 && j == 2){
                        if(k == 1) {
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.TOP);
                            Wall wall = new Wall("4A", orientations);
                            extraCrispy.get(i).get(j).add(wall);
                        }
                        if(k == 2) {
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.TOP);
                            Laser laser = new Laser("4A", orientations,1);
                            extraCrispy.get(i).get(j).add(laser);
                        }
                        if(k == 3){
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.RIGHT);
                            CheckPoint checkPoint = new CheckPoint("4A",orientations,4);
                            extraCrispy.get(i).get(j).add(checkPoint);
                        }
                    }

                    if(i == 6 && j == 2){
                        if(k == 1){
                            Pit pit = new Pit("4A");
                            extraCrispy.get(i).get(j).add(pit);
                        }
                    }

                    if(i == 9 && j == 2){
                        if(k == 1){
                            Pit pit = new Pit("4A");
                            extraCrispy.get(i).get(j).add(pit);
                        }
                    }

                    if(i == 10 && j == 2){
                        if(k == 1) {
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.TOP);
                            Wall wall = new Wall("4A", orientations);
                            extraCrispy.get(i).get(j).add(wall);
                        }
                        if(k == 2) {
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.TOP);
                            Laser laser = new Laser("Laser", "4A", orientations,1);
                            laser.setBoardLaserPosition(new Position(i,j));
                            extraCrispy.get(i).get(j).add(laser);
                        }
                        if(k == 3){
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.RIGHT);
                            CheckPoint checkPoint = new CheckPoint("CheckPoint","4A",orientations,1);
                            checkPoint.setPosition(new Position(i, j));
                            extraCrispy.get(i).get(j).add(checkPoint);
                        }
                    }

                    if(i == 11 && j == 2){
                        if(k == 1){
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.BOTTOM);
                            orientations.add(Orientation.TOP);
                            ConveyorBelt conveyorBelt = new ConveyorBelt("ConveyorBelt","4A", 2, orientations);
                            conveyorBelt.setPosition(new Position(i,j));
                            conveyorBelt.setActivationOrder(1);
                            extraCrispy.get(i).get(j).add(conveyorBelt);
                        }
                    }

                    if(i == 0 && j == 3){
                        if(k == 1){
                            StartPoint startPoint = new StartPoint("StartPoint", "A");
                            startPoint.setPosition(new Position(i, j));
                            extraCrispy.get(i).get(j).add(startPoint);
                        }
                    }

                    if(i == 4 && j == 3){
                        if(k == 1){
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.TOP);
                            orientations.add(Orientation.RIGHT);
                            ConveyorBelt conveyorBelt = new ConveyorBelt("ConveyorBelt","4A", 2, orientations);
                            conveyorBelt.setPosition(new Position(i,j));
                            conveyorBelt.setActivationOrder(1);
                            extraCrispy.get(i).get(j).add(conveyorBelt);
                        }
                    }

                    if(i == 5 && j == 3){
                        if(k == 1){
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.RIGHT);
                            orientations.add(Orientation.LEFT);
                            ConveyorBelt conveyorBelt = new ConveyorBelt("ConveyorBelt","4A", 2, orientations);
                            conveyorBelt.setPosition(new Position(i,j));
                            conveyorBelt.setActivationOrder(1);
                            extraCrispy.get(i).get(j).add(conveyorBelt);
                        }

                        if(k == 2){
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.TOP);
                            orientations.add(Orientation.BOTTOM);
                            Laser laser = new Laser("Laser", "4A", orientations,1);
                            laser.setBoardLaserPosition(new Position(i,j));
                            extraCrispy.get(i).get(j).add(laser);
                        }
                    }

                    if(i == 6 && j == 3){
                        if(k == 1){
                            Pit pit = new Pit("BlackHole", "4A");
                            pit.setBlackHolePosition(new Position(i,j));
                            extraCrispy.get(i).get(j).add(pit);
                        }
                    }

                    if(i == 7 && j == 3){
                        if(k == 1){
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.LEFT);
                            Wall wall = new Wall("Wall", "4A", orientations);
                            wall.setWallPosition(new Position(i,j));
                            extraCrispy.get(i).get(j).add(wall);
                        }

                        if(k == 2){
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.LEFT);
                            Laser laser = new Laser("Laser", "4A", orientations,1);
                            laser.setBoardLaserPosition(new Position(i,j));
                            extraCrispy.get(i).get(j).add(laser);
                        }
                    }

                    if(i == 8 && j == 3){
                        if(k == 1){
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.RIGHT);
                            Wall wall = new Wall("Wall", "4A", orientations);
                            wall.setWallPosition(new Position(i,j));
                            extraCrispy.get(i).get(j).add(wall);
                        }

                        if(k == 2){
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.RIGHT);
                            Laser laser = new Laser("Laser", "4A", orientations,1);
                            laser.setBoardLaserPosition(new Position(i,j));
                            extraCrispy.get(i).get(j).add(laser);
                        }
                    }

                    if(i == 9 && j == 3){
                        if(k == 1){
                            Pit pit = new Pit("BlackHole", "4A");
                            pit.setBlackHolePosition(new Position(i,j));
                            extraCrispy.get(i).get(j).add(pit);
                        }
                    }

                    if(i == 10 && j == 3){
                        if(k == 1){
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.LEFT);
                            orientations.add(Orientation.RIGHT);
                            ConveyorBelt conveyorBelt = new ConveyorBelt("ConveyorBelt","4A", 2, orientations);
                            conveyorBelt.setPosition(new Position(i,j));
                            conveyorBelt.setActivationOrder(1);
                            extraCrispy.get(i).get(j).add(conveyorBelt);
                        }

                        if(k == 2){
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.TOP);
                            orientations.add(Orientation.BOTTOM);
                            Laser laser = new Laser("Laser", "4A", orientations,1);
                            laser.setBoardLaserPosition(new Position(i,j));
                            extraCrispy.get(i).get(j).add(laser);
                        }
                    }

                    if(i == 11 && j == 3){
                        if(k == 1){
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.LEFT);
                            orientations.add(Orientation.TOP);
                            ConveyorBelt conveyorBelt = new ConveyorBelt("ConveyorBelt","4A", 2, orientations);
                            conveyorBelt.setPosition(new Position(i,j));
                            conveyorBelt.setActivationOrder(1);
                            extraCrispy.get(i).get(j).add(conveyorBelt);
                        }
                    }

                    if(i == 0 && j == 4){
                        if(k == 1){
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.RIGHT);
                            Antenna antenna = new Antenna("Antenna", "A", orientations);
                            antenna.setAntennaPosition(new Position(i, j));
                            extraCrispy.get(i).get(j).add(antenna);
                        }
                    }

                    if(i == 1 && j == 4){
                        if(k == 1){
                            StartPoint startPoint = new StartPoint("StartPoint", "A");
                            startPoint.setPosition(new Position(i, j));
                            extraCrispy.get(i).get(j).add(startPoint);
                        }
                    }

                    if(i == 2 && j == 4){
                        if(k == 1){
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.RIGHT);
                            Wall wall = new Wall("Wall", "A", orientations);
                            wall.setWallPosition(new Position(i,j));
                            extraCrispy.get(i).get(j).add(wall);
                        }
                    }

                    if(i == 3 && j == 4){
                        if(k == 1){
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.TOP);
                            Wall wall = new Wall("Wall", "4A", orientations);
                            wall.setWallPosition(new Position(i,j));
                            extraCrispy.get(i).get(j).add(wall);
                        }

                        if(k == 2){
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.HORIZONTAL);
                            EnergySpace energySpace = new EnergySpace("EnergySpace","4A", orientations,1);
                            energySpace.setPosition(new Position(i,j));
                            extraCrispy.get(i).get(j).add(energySpace);
                        }
                    }

                    if(i == 5 && j == 4){
                        if(k == 1){
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.TOP);
                            orientations.add(Orientation.BOTTOM);
                            Laser laser = new Laser("Laser", "4A", orientations,1);
                            laser.setBoardLaserPosition(new Position(i,j));
                            extraCrispy.get(i).get(j).add(laser);
                        }
                    }

                    if(i == 6 && j == 4){
                        if(k == 1){
                            Gear gear = new Gear("Gear","4A","clockwise");
                            gear.setPosition(new Position(i, j));
                            extraCrispy.get(i).get(j).add(gear);
                        }
                    }

                    if(i == 9 && j == 4){
                        if(k == 1){
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.RIGHT);
                            Wall wall = new Wall("Wall", "4A", orientations);
                            wall.setWallPosition(new Position(i,j));
                            extraCrispy.get(i).get(j).add(wall);
                        }
                    }

                    if(i == 10 && j == 4){
                        if(k == 1){
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.TOP);
                            orientations.add(Orientation.BOTTOM);
                            Laser laser = new Laser("Laser", "4A", orientations,1);
                            laser.setBoardLaserPosition(new Position(i,j));
                            extraCrispy.get(i).get(j).add(laser);
                        }
                    }

                    if(i == 11 && j == 4){
                        if(k == 1){
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.VERTICAL);
                            EnergySpace energySpace = new EnergySpace("EnergySpace","4A", orientations,1);
                            energySpace.setPosition(new Position(i,j));
                            extraCrispy.get(i).get(j).add(energySpace);
                        }
                    }

                    if(i == 12 && j == 4){
                        if(k == 1){
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.TOP);
                            Wall wall = new Wall("Wall", "4A", orientations);
                            wall.setWallPosition(new Position(i,j));
                            extraCrispy.get(i).get(j).add(wall);
                        }
                    }

                    if(i == 1 && j == 5){
                        if(k == 1){
                            StartPoint startPoint = new StartPoint("StartPoint", "A");
                            startPoint.setPosition(new Position(i, j));
                            extraCrispy.get(i).get(j).add(startPoint);
                        }
                    }

                    if(i == 2 && j == 5){
                        if(k == 1){
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.RIGHT);
                            Wall wall = new Wall("Wall", "A", orientations);
                            wall.setWallPosition(new Position(i,j));
                            extraCrispy.get(i).get(j).add(wall);
                        }
                    }

                    if(i == 3 && j == 5){
                        if(k == 1){
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.BOTTOM);
                            Wall wall = new Wall("Wall", "4A", orientations);
                            wall.setWallPosition(new Position(i,j));
                            extraCrispy.get(i).get(j).add(wall);
                        }
                    }

                    if(i == 5 && j == 5){
                        if(k == 1){
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.TOP);
                            orientations.add(Orientation.BOTTOM);
                            Laser laser = new Laser("Laser", "4A", orientations,1);
                            laser.setBoardLaserPosition(new Position(i,j));
                            extraCrispy.get(i).get(j).add(laser);
                        }
                    }

                    if(i == 6 && j == 5){
                        if(k == 1){
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.LEFT);
                            Wall wall = new Wall("Wall", "4A", orientations);
                            wall.setWallPosition(new Position(i,j));
                            extraCrispy.get(i).get(j).add(wall);
                        }
                    }

                    if(i == 7 && j == 5){
                        if(k == 1){
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.VERTICAL);
                            EnergySpace energySpace = new EnergySpace("EnergySpace","4A", orientations,1);
                            energySpace.setPosition(new Position(i,j));
                            extraCrispy.get(i).get(j).add(energySpace);
                        }
                    }

                    if(i == 9 && j == 5){
                        if(k == 1){
                            Gear gear = new Gear("Gear","4A","counterclockwise");
                            gear.setPosition(new Position(i, j));
                            extraCrispy.get(i).get(j).add(gear);
                        }
                    }

                    if(i == 10 && j == 5){
                        if(k == 1){
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.TOP);
                            orientations.add(Orientation.BOTTOM);
                            Laser laser = new Laser("Laser", "4A", orientations,1);
                            laser.setBoardLaserPosition(new Position(i,j));
                            extraCrispy.get(i).get(j).add(laser);
                        }
                    }

                    if(i == 12 && j == 5){
                        if(k == 1){
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.BOTTOM);
                            Wall wall = new Wall("Wall", "4A", orientations);
                            wall.setWallPosition(new Position(i,j));
                            extraCrispy.get(i).get(j).add(wall);
                        }
                    }

                    if(i == 0 && j == 6){
                        if(k == 1){
                            StartPoint startPoint = new StartPoint("StartPoint", "A");
                            startPoint.setPosition(new Position(i, j));
                            extraCrispy.get(i).get(j).add(startPoint);
                        }
                    }

                    if(i == 4 && j == 6){
                        if(k == 1){
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.RIGHT);
                            orientations.add(Orientation.BOTTOM);
                            ConveyorBelt conveyorBelt = new ConveyorBelt("ConveyorBelt","4A", 2, orientations);
                            conveyorBelt.setPosition(new Position(i,j));
                            conveyorBelt.setActivationOrder(1);
                            extraCrispy.get(i).get(j).add(conveyorBelt);
                        }
                    }

                    if(i == 5 && j == 6){
                        if(k == 1){
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.RIGHT);
                            orientations.add(Orientation.LEFT);
                            ConveyorBelt conveyorBelt = new ConveyorBelt("ConveyorBelt","4A", 2, orientations);
                            conveyorBelt.setPosition(new Position(i,j));
                            conveyorBelt.setActivationOrder(1);
                            extraCrispy.get(i).get(j).add(conveyorBelt);
                        }

                        if(k == 2){
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.TOP);
                            orientations.add(Orientation.BOTTOM);
                            Laser laser = new Laser("Laser", "4A", orientations,1);
                            laser.setBoardLaserPosition(new Position(i,j));
                            extraCrispy.get(i).get(j).add(laser);
                        }
                    }

                    if(i == 6 && j == 6){
                        if(k == 1){
                            Pit pit = new Pit("BlackHole", "4A");
                            pit.setBlackHolePosition(new Position(i,j));
                            extraCrispy.get(i).get(j).add(pit);
                        }
                    }

                    if(i == 7 && j == 6){
                        if(k == 1){
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.LEFT);
                            Wall wall = new Wall("Wall", "4A", orientations);
                            wall.setWallPosition(new Position(i,j));
                            extraCrispy.get(i).get(j).add(wall);
                        }

                        if(k == 2){
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.LEFT);
                            Laser laser = new Laser("Laser", "4A", orientations,1);
                            laser.setBoardLaserPosition(new Position(i,j));
                            extraCrispy.get(i).get(j).add(laser);
                        }
                    }

                    if(i == 8 && j == 6){
                        if(k == 1){
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.RIGHT);
                            Wall wall = new Wall("Wall", "4A", orientations);
                            wall.setWallPosition(new Position(i,j));
                            extraCrispy.get(i).get(j).add(wall);
                        }

                        if(k == 2){
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.RIGHT);
                            Laser laser = new Laser("Laser", "4A", orientations,1);
                            laser.setBoardLaserPosition(new Position(i,j));
                            extraCrispy.get(i).get(j).add(laser);
                        }
                    }

                    if(i == 9 && j == 6){
                        if(k == 1){
                            Pit pit = new Pit("BlackHole", "4A");
                            pit.setBlackHolePosition(new Position(i,j));
                            extraCrispy.get(i).get(j).add(pit);
                        }
                    }

                    if(i == 10 && j == 6){
                        if(k == 1){
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.LEFT);
                            orientations.add(Orientation.RIGHT);
                            ConveyorBelt conveyorBelt = new ConveyorBelt("ConveyorBelt","4A", 2, orientations);
                            conveyorBelt.setPosition(new Position(i,j));
                            conveyorBelt.setActivationOrder(1);
                            extraCrispy.get(i).get(j).add(conveyorBelt);
                        }

                        if(k == 2){
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.TOP);
                            orientations.add(Orientation.BOTTOM);
                            Laser laser = new Laser("Laser", "4A", orientations,1);
                            laser.setBoardLaserPosition(new Position(i,j));
                            extraCrispy.get(i).get(j).add(laser);
                        }
                    }

                    if(i == 11 && j == 6){
                        if(k == 1){
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.LEFT);
                            orientations.add(Orientation.BOTTOM);
                            ConveyorBelt conveyorBelt = new ConveyorBelt("ConveyorBelt","4A", 2, orientations);
                            conveyorBelt.setPosition(new Position(i,j));
                            conveyorBelt.setActivationOrder(1);
                            extraCrispy.get(i).get(j).add(conveyorBelt);
                        }
                    }

                    if(i == 1 && j == 7){
                        if(k == 1){
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.BOTTOM);
                            Wall wall = new Wall("Wall", "4A", orientations);
                            wall.setWallPosition(new Position(i,j));
                            extraCrispy.get(i).get(j).add(wall);
                        }
                    }

                    if(i == 4 && j == 7){
                        if(k == 1){
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.TOP);
                            orientations.add(Orientation.BOTTOM);
                            ConveyorBelt conveyorBelt = new ConveyorBelt("ConveyorBelt","4A", 2, orientations);
                            conveyorBelt.setPosition(new Position(i,j));
                            conveyorBelt.setActivationOrder(1);
                            extraCrispy.get(i).get(j).add(conveyorBelt);
                        }
                    }

                    if(i == 5 && j == 7){
                        if(k == 1) {
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.BOTTOM);
                            Wall wall = new Wall("Wall", "4A", orientations);
                            wall.setWallPosition(new Position(i,j));
                            extraCrispy.get(i).get(j).add(wall);
                        }
                        if(k == 2) {
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.BOTTOM);
                            Laser laser = new Laser("Laser", "4A", orientations,1);
                            laser.setBoardLaserPosition(new Position(i,j));
                            extraCrispy.get(i).get(j).add(laser);
                        }
                        if(k == 3){
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.RIGHT);
                            CheckPoint checkPoint = new CheckPoint("CheckPoint","4A",orientations,2);
                            checkPoint.setPosition(new Position(i, j));
                            extraCrispy.get(i).get(j).add(checkPoint);
                        }
                    }

                    if(i == 6 && j == 7){
                        if(k == 1){
                            Pit pit = new Pit("BlackHole", "4A");
                            pit.setBlackHolePosition(new Position(i,j));
                            extraCrispy.get(i).get(j).add(pit);
                        }
                    }

                    if(i == 9 && j == 7){
                        if(k == 1){
                            Pit pit = new Pit("BlackHole", "4A");
                            pit.setBlackHolePosition(new Position(i,j));
                            extraCrispy.get(i).get(j).add(pit);
                        }
                    }

                    if(i == 10 && j == 7){
                        if(k == 1) {
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.BOTTOM);
                            Wall wall = new Wall("Wall", "4A", orientations);
                            wall.setWallPosition(new Position(i,j));
                            extraCrispy.get(i).get(j).add(wall);
                        }
                        if(k == 2) {
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.BOTTOM);
                            Laser laser = new Laser("Laser", "4A", orientations,1);
                            laser.setBoardLaserPosition(new Position(i,j));
                            extraCrispy.get(i).get(j).add(laser);
                        }
                        if(k == 3){
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.RIGHT);
                            CheckPoint checkPoint = new CheckPoint("CheckPoint","4A",orientations,3);
                            checkPoint.setPosition(new Position(i, j));
                            extraCrispy.get(i).get(j).add(checkPoint);
                        }
                    }

                    if(i == 11 && j == 7){
                        if(k == 1){
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.TOP);
                            orientations.add(Orientation.BOTTOM);
                            ConveyorBelt conveyorBelt = new ConveyorBelt("ConveyorBelt","4A", 2, orientations);
                            conveyorBelt.setPosition(new Position(i,j));
                            conveyorBelt.setActivationOrder(1);
                            extraCrispy.get(i).get(j).add(conveyorBelt);
                        }
                    }

                    if(i == 1 && j == 8){
                        if(k == 1) {
                            StartPoint startPoint = new StartPoint("StartPoint", "A");
                            startPoint.setPosition(new Position(i, j));
                            extraCrispy.get(i).get(j).add(startPoint);
                        }
                    }

                    if(i == 4 && j == 8){
                        if(k == 1){
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.TOP);
                            orientations.add(Orientation.BOTTOM);
                            ConveyorBelt conveyorBelt = new ConveyorBelt("ConveyorBelt","4A", 2, orientations);
                            conveyorBelt.setPosition(new Position(i,j));
                            conveyorBelt.setActivationOrder(1);
                            extraCrispy.get(i).get(j).add(conveyorBelt);
                        }
                    }

                    if(i == 8 && j == 8){
                        if(k == 1){
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.RIGHT);
                            orientations.add(Orientation.LEFT);
                            ConveyorBelt conveyorBelt = new ConveyorBelt("ConveyorBelt","4A", 1, orientations);
                            conveyorBelt.setPosition(new Position(i,j));
                            conveyorBelt.setActivationOrder(2);
                            extraCrispy.get(i).get(j).add(conveyorBelt);
                        }
                    }

                    if(i == 9 && j == 8){
                        if(k == 1){
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.TOP);
                            orientations.add(Orientation.LEFT);
                            ConveyorBelt conveyorBelt = new ConveyorBelt("ConveyorBelt","4A", 1, orientations);
                            conveyorBelt.setPosition(new Position(i,j));
                            conveyorBelt.setActivationOrder(2);
                            extraCrispy.get(i).get(j).add(conveyorBelt);
                        }
                    }

                    if(i == 11 && j == 8){
                        if(k == 1){
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.TOP);
                            orientations.add(Orientation.BOTTOM);
                            ConveyorBelt conveyorBelt = new ConveyorBelt("ConveyorBelt","4A", 2, orientations);
                            conveyorBelt.setPosition(new Position(i,j));
                            conveyorBelt.setActivationOrder(1);
                            extraCrispy.get(i).get(j).add(conveyorBelt);
                        }
                    }

                    if(i == 2 && j == 9){
                        if(k == 1){
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.RIGHT);
                            orientations.add(Orientation.LEFT);
                            ConveyorBelt conveyorBelt = new ConveyorBelt("ConveyorBelt","A", 1, orientations);
                            conveyorBelt.setPosition(new Position(i,j));
                            conveyorBelt.setActivationOrder(2);
                            extraCrispy.get(i).get(j).add(conveyorBelt);
                        }
                    }

                    if(i == 3 && j == 9){
                        if(k == 1){
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.VERTICAL);
                            EnergySpace energySpace = new EnergySpace("EnergySpace","4A", orientations, 1);
                            energySpace.setPosition(new Position(i,j));
                            extraCrispy.get(i).get(j).add(energySpace);
                        }
                    }

                    if(i == 4 && j == 9){
                        if(k == 1) {
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.LEFT);
                            Wall wall = new Wall("Wall", "4A", orientations);
                            wall.setWallPosition(new Position(i,j));
                            extraCrispy.get(i).get(j).add(wall);
                        }
                        if(k == 2) {
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.LEFT);
                            Laser laser = new Laser("Laser", "4A", orientations,1);
                            laser.setBoardLaserPosition(new Position(i,j));
                            extraCrispy.get(i).get(j).add(laser);
                        }
                    }

                    if(i == 5 && j == 9){
                        if(k == 1){
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.LEFT);
                            orientations.add(Orientation.RIGHT);
                            Laser laser = new Laser("Laser", "4A", orientations,1);
                            laser.setBoardLaserPosition(new Position(i,j));
                            extraCrispy.get(i).get(j).add(laser);
                        }
                    }

                    if(i == 6 && j == 9){
                        if(k == 1) {
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.RIGHT);
                            Wall wall = new Wall("Wall", "4A", orientations);
                            wall.setWallPosition(new Position(i,j));
                            extraCrispy.get(i).get(j).add(wall);
                        }
                        if(k == 2) {
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.RIGHT);
                            Laser laser = new Laser("Laser", "4A", orientations,1);
                            laser.setBoardLaserPosition(new Position(i,j));
                            extraCrispy.get(i).get(j).add(laser);
                        }
                    }

                    if(i == 11 && j == 9){
                        if(k == 1){
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.TOP);
                            orientations.add(Orientation.BOTTOM);
                            ConveyorBelt conveyorBelt = new ConveyorBelt("ConveyorBelt","4A", 2, orientations);
                            conveyorBelt.setPosition(new Position(i,j));
                            conveyorBelt.setActivationOrder(1);
                            extraCrispy.get(i).get(j).add(conveyorBelt);
                        }
                    }
                }
                }
            }
        }
    }
}
