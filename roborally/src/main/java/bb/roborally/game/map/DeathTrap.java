package bb.roborally.game.map;

import bb.roborally.game.Orientation;
import bb.roborally.game.Position;
import bb.roborally.game.board.Board;
import bb.roborally.game.tiles.*;

import java.util.ArrayList;

public class DeathTrap extends Board {

    private ArrayList<ArrayList<ArrayList<Tile>>> deathTrap;

    public DeathTrap(ArrayList<ArrayList<ArrayList<Tile>>> deathTrap) {
        super(deathTrap);
        this.deathTrap = deathTrap;
    }

    public void buildDeathTrap() {
        int xAxis = 13;
        int yAxis = 10;
        int maxCellContent = 4;

        deathTrap = new ArrayList<ArrayList<ArrayList<Tile>>>();

        //for - Schleife x-Koordinaten (äußerste ArrayList)
        for (int i = 0; i < xAxis; i++) {
            deathTrap.add(new ArrayList<ArrayList<Tile>>());

            //for - Schleife y-Koordinaten (mittlere ArrayList)
            for (int j= 0; j< yAxis; j++){
                deathTrap.get(i).add(new ArrayList<Tile>());

                //for - Schleife Cells (innerste ArrayList)
                for (int k =0; k< maxCellContent; k++){
                    if (k == 0){
                        Empty empty = new Empty("Floor", "2A");
                        empty.setFloorPosition(new Position(i, j));
                        deathTrap.get(i).get(j).add(empty);
                    }

                    if(i == 2 && j == 0){
                        if(k == 1){
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.LEFT);
                            orientations.add(Orientation.RIGHT);
                            ConveyorBelt conveyorBelt = new ConveyorBelt("ConveyorBelt","2A",1, orientations);
                            conveyorBelt.setPosition(new Position(i,j));
                            conveyorBelt.setActivationOrder(2);
                            deathTrap.get(i).get(j).add(conveyorBelt);
                        }
                    }

                    if(i == 3 && j == 0){
                        if(k == 1){
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.LEFT);
                            orientations.add(Orientation.RIGHT);
                            ConveyorBelt conveyorBelt = new ConveyorBelt("ConveyorBelt","2A",1, orientations);
                            conveyorBelt.setPosition(new Position(i,j));
                            conveyorBelt.setActivationOrder(2);
                            deathTrap.get(i).get(j).add(conveyorBelt);
                        }
                    }

                    if(i == 4 && j == 0){
                        if(k == 1){
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.LEFT);
                            orientations.add(Orientation.BOTTOM);
                            ConveyorBelt conveyorBelt = new ConveyorBelt("ConveyorBelt","2A",1, orientations);
                            conveyorBelt.setPosition(new Position(i,j));
                            conveyorBelt.setActivationOrder(2);
                            deathTrap.get(i).get(j).add(conveyorBelt);
                        }
                    }

                    if(i == 10 && j == 0){
                        if(k == 1){
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.LEFT);
                            orientations.add(Orientation.RIGHT);
                            ConveyorBelt conveyorBelt = new ConveyorBelt("ConveyorBelt","A",1, orientations);
                            conveyorBelt.setPosition(new Position(i,j));
                            conveyorBelt.setActivationOrder(2);
                            deathTrap.get(i).get(j).add(conveyorBelt);
                        }
                    }

                    if(i == 0 && j == 1){
                        if(k == 1){
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.LEFT);
                            CheckPoint checkPoint = new CheckPoint("CheckPoint","2A",orientations,5);
                            checkPoint.setPosition(new Position(i, j));
                            checkPoint.setActivationOrder(8);
                            deathTrap.get(i).get(j).add(checkPoint);
                        }
                    }

                    if (i == 1 && j == 1){
                        if (k == 1){
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.TOP);
                            Wall wall = new Wall("Wall", "2A", orientations);
                            wall.setWallPosition(new Position(i,j));
                            deathTrap.get(i).get(j).add(wall);
                        }
                        if (k == 2){
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.TOP);
                            ArrayList<Integer> registers = new ArrayList<>();
                            registers.add(1);
                            registers.add(3);
                            registers.add(5);
                            PushPanel pushPanel  = new PushPanel("PushPanel", "2A", orientations, registers);
                            pushPanel.setPosition(new Position(i,j));
                            deathTrap.get(i).get(j).add(pushPanel);
                        }
                    }

                    if (i == 3 && j == 1){
                        if (k == 1){
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.BOTTOM);
                            Wall wall = new Wall("Wall", "2A", orientations);
                            wall.setWallPosition(new Position(i,j));
                            deathTrap.get(i).get(j).add(wall);
                        }
                    }

                    if (i == 4 && j == 1){
                        if (k == 1){
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.TOP);
                            orientations.add(Orientation.RIGHT);
                            ConveyorBelt conveyorBelt = new ConveyorBelt("ConveyorBelt","2A",1, orientations);
                            conveyorBelt.setPosition(new Position(i,j));
                            conveyorBelt.setActivationOrder(2);
                            deathTrap.get(i).get(j).add(conveyorBelt);
                        }
                    }

                    if (i == 5 && j == 1){
                        if (k == 1){
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.LEFT);
                            orientations.add(Orientation.RIGHT);
                            ConveyorBelt conveyorBelt = new ConveyorBelt("ConveyorBelt","2A",1, orientations);
                            conveyorBelt.setPosition(new Position(i,j));
                            conveyorBelt.setActivationOrder(2);
                            deathTrap.get(i).get(j).add(conveyorBelt);
                        }
                    }

                    if (i == 7 && j == 1){
                        if (k == 1){
                            BlackHole blackHole = new BlackHole("BlackHole", "2A");
                            blackHole.setBlackHolePosition(new Position(i,j));
                            deathTrap.get(i).get(j).add(blackHole);
                        }
                    }

                    if (i == 8 && j == 1){
                        if (k == 1){
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.RIGHT);
                            Wall wall = new Wall("Wall", "2A", orientations);
                            wall.setWallPosition(new Position(i,j));
                            deathTrap.get(i).get(j).add(wall);
                        }
                        if (k == 2){
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.RIGHT);
                            ArrayList<Integer> registers = new ArrayList<>();
                            registers.add(1);
                            registers.add(3);
                            registers.add(5);
                            PushPanel pushPanel  = new PushPanel("PushPanel", "2A", orientations, registers);
                            pushPanel.setPosition(new Position(i,j));
                            deathTrap.get(i).get(j).add(pushPanel);
                        }
                    }

                    if (i == 9 && j == 1){
                        if (k == 1){
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.TOP);
                            orientations.add(Orientation.BOTTOM);
                            ConveyorBelt conveyorBelt = new ConveyorBelt("ConveyorBelt","2A",1, orientations);
                            conveyorBelt.setPosition(new Position(i,j));
                            conveyorBelt.setActivationOrder(2);
                            deathTrap.get(i).get(j).add(conveyorBelt);
                        }
                    }

                    if(i == 11 && j == 1){
                        if(k == 1){
                            StartPoint startPoint = new StartPoint("StartPoint", "A");
                            startPoint.setPosition(new Position(i, j));
                            deathTrap.get(i).get(j).add(startPoint);
                        }
                    }

                    if (i == 1 && j == 2){
                        if (k == 1){
                            BlackHole blackHole = new BlackHole("BlackHole", "2A");
                            blackHole.setBlackHolePosition(new Position(i,j));
                            deathTrap.get(i).get(j).add(blackHole);
                        }
                    }

                    if (i == 2 && j == 2){
                        if (k == 1){
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.LEFT);
                            Wall wall = new Wall("Wall", "2A", orientations);
                            wall.setWallPosition(new Position(i,j));
                            deathTrap.get(i).get(j).add(wall);
                        }
                        if (k == 2) {
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.LEFT);
                            ArrayList<Integer> registers = new ArrayList<>();
                            registers.add(2);
                            registers.add(4);
                            PushPanel pushPanel = new PushPanel("PushPanel", "2A", orientations, registers);
                            pushPanel.setPosition(new Position(i, j));
                            deathTrap.get(i).get(j).add(pushPanel);
                        }
                    }

                    if (i == 3 && j == 2){
                        if (k == 1){
                            BlackHole blackHole = new BlackHole("BlackHole", "2A");
                            blackHole.setBlackHolePosition(new Position(i,j));
                            deathTrap.get(i).get(j).add(blackHole);
                        }
                    }

                    if(i == 4 && j == 2){
                        if(k == 1){
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.VERTICAL);
                            EnergySpace energySpace = new EnergySpace("EnergySpace","2A", orientations,1);
                            energySpace.setPosition(new Position(i,j));
                            deathTrap.get(i).get(j).add(energySpace);
                        }
                    }

                    //Energyspace ist hier horizontal nicht vertikal
                    if(i == 6 && j == 2){
                        if(k == 1){
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.HORIZONTAL);
                            EnergySpace energySpace = new EnergySpace("EnergySpace","2A", orientations,1);
                            energySpace.setPosition(new Position(i,j));
                            deathTrap.get(i).get(j).add(energySpace);
                        }
                    }

                    if (i == 7 && j == 2){
                        if (k == 1){
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.TOP);
                            Wall wall = new Wall("Wall", "2A", orientations);
                            wall.setWallPosition(new Position(i,j));
                            deathTrap.get(i).get(j).add(wall);
                        }
                        if (k == 2){
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.TOP);
                            ArrayList<Integer> registers = new ArrayList<>();
                            registers.add(2);
                            registers.add(4);
                            PushPanel pushPanel  = new PushPanel("PushPanel", "2A", orientations, registers);
                            pushPanel.setPosition(new Position(i,j));
                            deathTrap.get(i).get(j).add(pushPanel);
                        }
                    }

                    if(i == 8 && j == 2){
                        if(k == 1){
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.LEFT);
                            CheckPoint checkPoint = new CheckPoint("CheckPoint","2A",orientations,4);
                            checkPoint.setPosition(new Position(i, j));
                            checkPoint.setActivationOrder(8);
                            deathTrap.get(i).get(j).add(checkPoint);
                        }
                    }

                    if (i == 9 && j == 2){
                        if (k == 1){
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.TOP);
                            orientations.add(Orientation.BOTTOM);
                            ConveyorBelt conveyorBelt = new ConveyorBelt("ConveyorBelt","2A",1, orientations);
                            conveyorBelt.setPosition(new Position(i,j));
                            conveyorBelt.setActivationOrder(2);
                            deathTrap.get(i).get(j).add(conveyorBelt);
                        }
                    }

                    if (i == 11 && j == 2){
                        if (k == 1){
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.TOP);
                            Wall wall = new Wall("Wall", "2A", orientations);
                            wall.setWallPosition(new Position(i,j));
                            deathTrap.get(i).get(j).add(wall);
                        }
                    }

                    if(i == 2 && j == 3){
                        if(k == 1){
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.VERTICAL);
                            EnergySpace energySpace = new EnergySpace("EnergySpace","2A", orientations,1);
                            energySpace.setPosition(new Position(i,j));
                            deathTrap.get(i).get(j).add(energySpace);
                        }
                    }

                    if (i == 4 && j == 3){
                        if (k == 1){
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.BOTTOM);
                            Wall wall = new Wall("Wall", "2A", orientations);
                            wall.setWallPosition(new Position(i,j));
                            deathTrap.get(i).get(j).add(wall);
                        }
                        if (k == 2) {
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.BOTTOM);
                            ArrayList<Integer> registers = new ArrayList<>();
                            registers.add(2);
                            registers.add(4);
                            PushPanel pushPanel = new PushPanel("PushPanel", "2A", orientations, registers);
                            pushPanel.setPosition(new Position(i, j));
                            deathTrap.get(i).get(j).add(pushPanel);
                        }
                    }

                    if (i == 7 && j == 3){
                        if (k == 1){
                            BlackHole blackHole = new BlackHole("BlackHole", "2A");
                            blackHole.setBlackHolePosition(new Position(i,j));
                            deathTrap.get(i).get(j).add(blackHole);
                        }
                    }

                    if (i == 8 && j == 3){
                        if (k == 1){
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.LEFT);
                            Wall wall = new Wall("Wall", "2A", orientations);
                            wall.setWallPosition(new Position(i,j));
                            deathTrap.get(i).get(j).add(wall);
                        }
                    }

                    if (i == 9 && j == 3){
                        if (k == 1){
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.TOP);
                            orientations.add(Orientation.BOTTOM);
                            ConveyorBelt conveyorBelt = new ConveyorBelt("ConveyorBelt","2A",1, orientations);
                            conveyorBelt.setPosition(new Position(i,j));
                            conveyorBelt.setActivationOrder(2);
                            deathTrap.get(i).get(j).add(conveyorBelt);
                        }
                    }

                    if(i == 12 && j == 3){
                        if(k == 1){
                            StartPoint startPoint = new StartPoint("StartPoint", "A");
                            startPoint.setPosition(new Position(i, j));
                            deathTrap.get(i).get(j).add(startPoint);
                        }
                    }

                    if (i == 1 && j == 4){
                        if (k == 1){
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.BOTTOM);
                            orientations.add(Orientation.TOP);
                            ConveyorBelt conveyorBelt = new ConveyorBelt("ConveyorBelt","2A",1, orientations);
                            conveyorBelt.setPosition(new Position(i,j));
                            conveyorBelt.setActivationOrder(2);
                            deathTrap.get(i).get(j).add(conveyorBelt);
                        }
                    }

                    if (i == 3 && j == 4){
                        if (k == 1){
                            BlackHole blackHole = new BlackHole("BlackHole", "2A");
                            blackHole.setBlackHolePosition(new Position(i,j));
                            deathTrap.get(i).get(j).add(blackHole);
                        }
                    }

                    if(i == 4 && j == 4){
                        if(k == 1){
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.LEFT);
                            CheckPoint checkPoint = new CheckPoint("CheckPoint","2A",orientations,2);
                            checkPoint.setPosition(new Position(i, j));
                            checkPoint.setActivationOrder(8);
                            deathTrap.get(i).get(j).add(checkPoint);
                        }
                    }

                    if (i == 6 && j == 4){
                        if (k == 1){
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.TOP);
                            Wall wall = new Wall("Wall", "2A", orientations);
                            wall.setWallPosition(new Position(i,j));
                            deathTrap.get(i).get(j).add(wall);
                        }
                        if (k == 2) {
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.TOP);
                            ArrayList<Integer> registers = new ArrayList<>();
                            registers.add(1);
                            registers.add(3);
                            registers.add(5);
                            PushPanel pushPanel = new PushPanel("PushPanel", "2A", orientations, registers);
                            pushPanel.setPosition(new Position(i, j));
                            deathTrap.get(i).get(j).add(pushPanel);
                        }
                    }

                    if (i == 8 && j == 4){
                        if (k == 1){
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.RIGHT);
                            orientations.add(Orientation.BOTTOM);
                            ConveyorBelt conveyorBelt = new ConveyorBelt("ConveyorBelt","2A",1, orientations);
                            conveyorBelt.setPosition(new Position(i,j));
                            conveyorBelt.setActivationOrder(2);
                            deathTrap.get(i).get(j).add(conveyorBelt);
                        }
                    }

                    if (i == 9 && j == 4){
                        if (k == 1){
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.TOP);
                            orientations.add(Orientation.LEFT);
                            ConveyorBelt conveyorBelt = new ConveyorBelt("ConveyorBelt","2A",1, orientations);
                            conveyorBelt.setPosition(new Position(i,j));
                            conveyorBelt.setActivationOrder(2);
                            deathTrap.get(i).get(j).add(conveyorBelt);
                        }
                    }

                    if (i == 10 && j == 4){
                        if (k == 1){
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.LEFT);
                            Wall wall = new Wall("Wall", "2A", orientations);
                            wall.setWallPosition(new Position(i,j));
                            deathTrap.get(i).get(j).add(wall);
                        }
                    }

                    if(i == 11 && j == 4){
                        if(k == 1){
                            StartPoint startPoint = new StartPoint("StartPoint", "A");
                            startPoint.setPosition(new Position(i, j));
                            deathTrap.get(i).get(j).add(startPoint);
                        }
                    }

                    if (i == 0 && j == 5){
                        if (k == 1){
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.BOTTOM);
                            orientations.add(Orientation.RIGHT);
                            ConveyorBelt conveyorBelt = new ConveyorBelt("ConveyorBelt","2A",1, orientations);
                            conveyorBelt.setPosition(new Position(i,j));
                            conveyorBelt.setActivationOrder(2);
                            deathTrap.get(i).get(j).add(conveyorBelt);
                        }
                    }

                    if (i == 1 && j == 5){
                        if (k == 1){
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.LEFT);
                            orientations.add(Orientation.TOP);
                            ConveyorBelt conveyorBelt = new ConveyorBelt("ConveyorBelt","2A",1, orientations);
                            conveyorBelt.setPosition(new Position(i,j));
                            conveyorBelt.setActivationOrder(2);
                            deathTrap.get(i).get(j).add(conveyorBelt);
                        }
                    }

                    if (i == 3 && j == 5){
                        if (k == 1){
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.BOTTOM);
                            Wall wall = new Wall("Wall", "2A", orientations);
                            wall.setWallPosition(new Position(i,j));
                            deathTrap.get(i).get(j).add(wall);
                        }
                        if (k == 2) {
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.BOTTOM);
                            ArrayList<Integer> registers = new ArrayList<>();
                            registers.add(1);
                            registers.add(3);
                            registers.add(5);
                            PushPanel pushPanel = new PushPanel("PushPanel", "2A", orientations, registers);
                            pushPanel.setPosition(new Position(i, j));
                            deathTrap.get(i).get(j).add(pushPanel);
                        }
                    }

                    if (i == 4 && j == 5){
                        if (k == 1){
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.TOP);
                            Wall wall = new Wall("Wall", "2A", orientations);
                            wall.setWallPosition(new Position(i,j));
                            deathTrap.get(i).get(j).add(wall);
                        }
                        if (k == 2) {
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.TOP);
                            ArrayList<Integer> registers = new ArrayList<>();
                            registers.add(2);
                            registers.add(4);
                            PushPanel pushPanel = new PushPanel("PushPanel", "2A", orientations, registers);
                            pushPanel.setPosition(new Position(i, j));
                            deathTrap.get(i).get(j).add(pushPanel);
                        }
                    }

                    if (i == 5 && j == 5){
                        if (k == 1){
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.BOTTOM);
                            Wall wall = new Wall("Wall", "2A", orientations);
                            wall.setWallPosition(new Position(i,j));
                            deathTrap.get(i).get(j).add(wall);
                        }
                    }

                    if (i == 6 && j == 5){
                        if (k == 1){
                            BlackHole blackHole = new BlackHole("BlackHole", "2A");
                            blackHole.setBlackHolePosition(new Position(i,j));
                            deathTrap.get(i).get(j).add(blackHole);
                        }
                    }

                    if (i == 8 && j == 5){
                        if (k == 1){
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.TOP);
                            orientations.add(Orientation.BOTTOM);
                            ConveyorBelt conveyorBelt = new ConveyorBelt("ConveyorBelt","2A",1, orientations);
                            conveyorBelt.setPosition(new Position(i,j));
                            conveyorBelt.setActivationOrder(2);
                            deathTrap.get(i).get(j).add(conveyorBelt);
                        }
                    }

                    if (i == 10 && j == 5){
                        if (k == 1){
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.LEFT);
                            Wall wall = new Wall("Wall", "A", orientations);
                            wall.setWallPosition(new Position(i,j));
                            deathTrap.get(i).get(j).add(wall);
                        }
                    }

                    if(i == 11 && j == 5){
                        if(k == 1){
                            StartPoint startPoint = new StartPoint("StartPoint", "A");
                            startPoint.setPosition(new Position(i, j));
                            deathTrap.get(i).get(j).add(startPoint);
                        }
                    }

                    if(i == 12 && j == 5){
                        if(k == 1){
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.LEFT);
                            Antenna antenna = new Antenna("Antenna", "A", orientations);
                            antenna.setAntennaPosition(new Position(i, j));
                            deathTrap.get(i).get(j).add(antenna);
                        }
                    }

                    if (i == 0 && j == 6){
                        if (k == 1){
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.BOTTOM);
                            orientations.add(Orientation.TOP);
                            ConveyorBelt conveyorBelt = new ConveyorBelt("ConveyorBelt","2A",1, orientations);
                            conveyorBelt.setPosition(new Position(i,j));
                            conveyorBelt.setActivationOrder(2);
                            deathTrap.get(i).get(j).add(conveyorBelt);
                        }
                    }

                    if (i == 1 && j == 6){
                        if (k == 1){
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.RIGHT);
                            Wall wall = new Wall("Wall", "2A", orientations);
                            wall.setWallPosition(new Position(i,j));
                            deathTrap.get(i).get(j).add(wall);
                        }
                    }

                    if (i == 2 && j == 6){
                        if (k == 1){
                            BlackHole blackHole = new BlackHole("BlackHole", "2A");
                            blackHole.setBlackHolePosition(new Position(i,j));
                            deathTrap.get(i).get(j).add(blackHole);
                        }
                    }

                    if(i == 4 && j == 6){
                        if(k == 1){
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.VERTICAL);
                            EnergySpace energySpace = new EnergySpace("EnergySpace","2A", orientations, 1);
                            energySpace.setPosition(new Position(i,j));
                            deathTrap.get(i).get(j).add(energySpace);
                        }
                    }

                    if(i == 7 && j == 6){
                        if(k == 1){
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.VERTICAL);
                            EnergySpace energySpace = new EnergySpace("EnergySpace","2A", orientations, 1);
                            energySpace.setPosition(new Position(i,j));
                            deathTrap.get(i).get(j).add(energySpace);
                        }
                    }

                    if(i == 12 && j == 6){
                        if(k == 1){
                            StartPoint startPoint = new StartPoint("StartPoint", "A");
                            startPoint.setPosition(new Position(i, j));
                            deathTrap.get(i).get(j).add(startPoint);
                        }
                    }

                    if (i == 0 && j == 7){
                        if (k == 1){
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.BOTTOM);
                            orientations.add(Orientation.TOP);
                            ConveyorBelt conveyorBelt = new ConveyorBelt("ConveyorBelt","2A",1, orientations);
                            conveyorBelt.setPosition(new Position(i,j));
                            conveyorBelt.setActivationOrder(2);
                            deathTrap.get(i).get(j).add(conveyorBelt);
                        }
                    }

                    if(i == 1 && j == 7){
                        if(k == 1){
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.LEFT);
                            CheckPoint checkPoint = new CheckPoint("CheckPoint","2A",orientations,1);
                            checkPoint.setPosition(new Position(i, j));
                            deathTrap.get(i).get(j).add(checkPoint);
                        }
                    }

                    if (i == 2 && j == 7){
                        if (k == 1){
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.BOTTOM);
                            Wall wall = new Wall("Wall", "2A", orientations);
                            wall.setWallPosition(new Position(i,j));
                            deathTrap.get(i).get(j).add(wall);
                        }
                        if (k == 2) {
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.BOTTOM);
                            ArrayList<Integer> registers = new ArrayList<>();
                            registers.add(2);
                            registers.add(4);
                            PushPanel pushPanel = new PushPanel("PushPanel", "2A", orientations, registers);
                            pushPanel.setPosition(new Position(i, j));
                            deathTrap.get(i).get(j).add(pushPanel);
                        }
                    }

                    if(i == 3 && j == 7){
                        if(k == 1){
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.HORIZONTAL);
                            EnergySpace energySpace = new EnergySpace("EnergySpace","2A", orientations, 1);
                            energySpace.setPosition(new Position(i,j));
                            deathTrap.get(i).get(j).add(energySpace);
                        }
                    }

                    if (i == 6 && j == 7){
                        if (k == 1){
                            BlackHole blackHole = new BlackHole("BlackHole", "2A");
                            blackHole.setBlackHolePosition(new Position(i,j));
                            deathTrap.get(i).get(j).add(blackHole);
                        }
                    }

                    if (i == 7 && j == 7){
                        if (k == 1){
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.RIGHT);
                            Wall wall = new Wall("Wall", "2A", orientations);
                            wall.setWallPosition(new Position(i,j));
                            deathTrap.get(i).get(j).add(wall);
                        }
                        if (k == 2) {
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.RIGHT);
                            ArrayList<Integer> registers = new ArrayList<>();
                            registers.add(2);
                            registers.add(4);
                            PushPanel pushPanel = new PushPanel("PushPanel", "2A", orientations, registers);
                            pushPanel.setPosition(new Position(i, j));
                            deathTrap.get(i).get(j).add(pushPanel);
                        }
                    }

                    if (i == 8 && j == 7){
                        if (k == 1){
                            BlackHole blackHole = new BlackHole("BlackHole", "2A");
                            blackHole.setBlackHolePosition(new Position(i,j));
                            deathTrap.get(i).get(j).add(blackHole);
                        }
                    }

                    if (i == 11 && j == 7){
                        if (k == 1){
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.BOTTOM);
                            Wall wall = new Wall("Wall", "A", orientations);
                            wall.setWallPosition(new Position(i,j));
                            deathTrap.get(i).get(j).add(wall);
                        }
                    }

                    if (i == 0 && j == 8){
                        if (k == 1){
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.BOTTOM);
                            orientations.add(Orientation.TOP);
                            ConveyorBelt conveyorBelt = new ConveyorBelt("ConveyorBelt","2A",1, orientations);
                            conveyorBelt.setPosition(new Position(i,j));
                            conveyorBelt.setActivationOrder(2);
                            deathTrap.get(i).get(j).add(conveyorBelt);
                        }
                    }

                    if (i == 1 && j == 8){
                        if (k == 1){
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.LEFT);
                            Wall wall = new Wall("Wall", "2A", orientations);
                            wall.setWallPosition(new Position(i,j));
                            deathTrap.get(i).get(j).add(wall);
                        }
                        if (k == 2) {
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.LEFT);
                            ArrayList<Integer> registers = new ArrayList<>();
                            registers.add(1);
                            registers.add(3);
                            registers.add(5);
                            PushPanel pushPanel = new PushPanel("PushPanel", "2A", orientations, registers);
                            pushPanel.setPosition(new Position(i, j));
                            deathTrap.get(i).get(j).add(pushPanel);
                        }
                    }

                    if (i == 2 && j == 8){
                        if (k == 1){
                            BlackHole blackHole = new BlackHole("BlackHole", "2A");
                            blackHole.setBlackHolePosition(new Position(i,j));
                            deathTrap.get(i).get(j).add(blackHole);
                        }
                    }

                    if (i == 4 && j == 8){
                        if (k == 1){
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.RIGHT);
                            orientations.add(Orientation.LEFT);
                            ConveyorBelt conveyorBelt = new ConveyorBelt("ConveyorBelt","2A",1, orientations);
                            conveyorBelt.setPosition(new Position(i,j));
                            conveyorBelt.setActivationOrder(2);
                            deathTrap.get(i).get(j).add(conveyorBelt);
                        }
                    }

                    if (i == 5 && j == 8){
                        if (k == 1){
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.BOTTOM);
                            orientations.add(Orientation.LEFT);
                            ConveyorBelt conveyorBelt = new ConveyorBelt("ConveyorBelt","2A",1, orientations);
                            conveyorBelt.setPosition(new Position(i,j));
                            conveyorBelt.setActivationOrder(2);
                            deathTrap.get(i).get(j).add(conveyorBelt);
                        }
                    }

                    if(i == 6 && j == 8){
                        if (k == 1) {
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.TOP);
                            Wall wall = new Wall("Wall", "2A", orientations);
                            wall.setWallPosition(new Position(i,j));
                            deathTrap.get(i).get(j).add(wall);
                        }

                    }

                    if(i == 7 && j == 8){
                        if(k == 1){
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.LEFT);
                            CheckPoint checkPoint = new CheckPoint("CheckPoint","2A",orientations,3);
                            checkPoint.setPosition(new Position(i, j));
                            deathTrap.get(i).get(j).add(checkPoint);
                        }
                    }

                    if (i == 8 && j == 8){
                        if (k == 1){
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.BOTTOM);
                            Wall wall = new Wall("Wall", "2A", orientations);
                            wall.setWallPosition(new Position(i,j));
                            deathTrap.get(i).get(j).add(wall);
                        }
                        if (k == 2) {
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.BOTTOM);
                            ArrayList<Integer> registers = new ArrayList<>();
                            registers.add(1);
                            registers.add(3);
                            registers.add(5);
                            PushPanel pushPanel = new PushPanel("PushPanel", "2A", orientations, registers);
                            pushPanel.setPosition(new Position(i, j));
                            deathTrap.get(i).get(j).add(pushPanel);
                        }
                    }

                    if(i == 11 && j == 8){
                        if(k == 1) {
                            StartPoint startPoint = new StartPoint("StartPoint", "A");
                            startPoint.setPosition(new Position(i, j));
                            deathTrap.get(i).get(j).add(startPoint);
                        }
                    }

                    if (i == 5 && j == 9){
                        if (k == 1){
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.RIGHT);
                            orientations.add(Orientation.TOP);
                            ConveyorBelt conveyorBelt = new ConveyorBelt("ConveyorBelt","2A",1, orientations);
                            conveyorBelt.setPosition(new Position(i,j));
                            conveyorBelt.setActivationOrder(2);
                            deathTrap.get(i).get(j).add(conveyorBelt);
                        }
                    }

                    if (i == 6 && j == 9){
                        if (k == 1){
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.RIGHT);
                            orientations.add(Orientation.LEFT);
                            ConveyorBelt conveyorBelt = new ConveyorBelt("ConveyorBelt","2A",1, orientations);
                            conveyorBelt.setPosition(new Position(i,j));
                            conveyorBelt.setActivationOrder(2);
                            deathTrap.get(i).get(j).add(conveyorBelt);
                        }
                    }

                    if (i == 7 && j == 9){
                        if (k == 1){
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.RIGHT);
                            orientations.add(Orientation.LEFT);
                            ConveyorBelt conveyorBelt = new ConveyorBelt("ConveyorBelt","2A",1, orientations);
                            conveyorBelt.setPosition(new Position(i,j));
                            conveyorBelt.setActivationOrder(2);
                            deathTrap.get(i).get(j).add(conveyorBelt);
                        }
                    }

                    if (i == 8 && j == 9){
                        if (k == 1){
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.RIGHT);
                            orientations.add(Orientation.LEFT);
                            ConveyorBelt conveyorBelt = new ConveyorBelt("ConveyorBelt","2A",1, orientations);
                            conveyorBelt.setPosition(new Position(i,j));
                            conveyorBelt.setActivationOrder(2);
                            deathTrap.get(i).get(j).add(conveyorBelt);
                        }
                    }

                    if (i == 10 && j == 9){
                        if (k == 1){
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.LEFT);
                            orientations.add(Orientation.RIGHT);
                            ConveyorBelt conveyorBelt = new ConveyorBelt("ConveyorBelt","A",1, orientations);
                            conveyorBelt.setPosition(new Position(i,j));
                            conveyorBelt.setActivationOrder(2);
                            deathTrap.get(i).get(j).add(conveyorBelt);
                        }
                    }

                    if(i == 12 && j == 9){
                        if(k == 1){
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.LEFT);
                            RestartPoint restartPoint = new RestartPoint("RebootPoint","A",orientations);
                            deathTrap.get(i).get(j).add(restartPoint);
                        }
                    }
                }
            }
        }
    }
}
