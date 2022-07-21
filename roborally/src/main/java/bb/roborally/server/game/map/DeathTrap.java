package bb.roborally.server.game.map;

import bb.roborally.server.game.Orientation;
import bb.roborally.server.game.board.Cell;
import bb.roborally.server.game.tiles.*;

import java.util.ArrayList;

public class DeathTrap {

    public boolean AMap;
    public static ArrayList<ArrayList<Cell>> buildDeathTrap() {
        int xAxis = 13;
        int yAxis = 10;
        int maxCellContent = 4;

        ArrayList<ArrayList<Cell>> deathTrap = new ArrayList<ArrayList<Cell>>();


        //for - Schleife x-Koordinaten (äußerste ArrayList)
        for (int i = 0; i < xAxis; i++) {
            deathTrap.add(new ArrayList<Cell>());

            //for - Schleife y-Koordinaten (mittlere ArrayList)
            for (int j= 0; j< yAxis; j++){
                deathTrap.get(i).add(new Cell(i, j));

                //for - Schleife Cells (innerste ArrayList)
                for (int k =0; k< maxCellContent; k++){

                    if (i == 0 && j == 0){
                        if(k == 0){
                            Empty empty = new Empty("2A");
                            deathTrap.get(i).get(j).addTile(empty);
                        }
                    }

                    if (i == 1 && j == 0){
                        if(k == 0){
                            Empty empty = new Empty("2A");
                            deathTrap.get(i).get(j).addTile(empty);
                        }
                    }

                    if(i == 2 && j == 0){
                        if(k == 0){
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.LEFT);
                            orientations.add(Orientation.RIGHT);
                            ConveyorBelt conveyorBelt = new ConveyorBelt("2A",1, orientations);
                            deathTrap.get(i).get(j).addTile(conveyorBelt);
                        }
                    }

                    if(i == 3 && j == 0){
                        if(k == 0){
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.LEFT);
                            orientations.add(Orientation.RIGHT);
                            ConveyorBelt conveyorBelt = new ConveyorBelt("2A",1, orientations);
                            deathTrap.get(i).get(j).addTile(conveyorBelt);
                        }
                    }

                    if(i == 4 && j == 0){
                        if(k == 0){
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.LEFT);
                            orientations.add(Orientation.BOTTOM);
                            ConveyorBelt conveyorBelt = new ConveyorBelt("2A",1, orientations);
                            deathTrap.get(i).get(j).addTile(conveyorBelt);
                        }
                    }

                    if (i == 5 && j == 0){
                        if(k == 0){
                            Empty empty = new Empty("2A");
                            deathTrap.get(i).get(j).addTile(empty);
                        }
                    }

                    if (i == 6 && j == 0){
                        if(k == 0){
                            Empty empty = new Empty("2A");
                            deathTrap.get(i).get(j).addTile(empty);
                        }
                    }

                    if (i == 7 && j == 0){
                        if(k == 0){
                            Empty empty = new Empty("2A");
                            deathTrap.get(i).get(j).addTile(empty);
                        }
                    }

                    if (i == 8 && j == 0){
                        if(k == 0){
                            Empty empty = new Empty("2A");
                            deathTrap.get(i).get(j).addTile(empty);
                        }
                    }

                    if (i == 9 && j == 0){
                        if(k == 0){
                            Empty empty = new Empty("2A");
                            deathTrap.get(i).get(j).addTile(empty);
                        }
                    }

                    if(i == 10 && j == 0){
                        if(k == 0){
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.LEFT);
                            orientations.add(Orientation.RIGHT);
                            ConveyorBelt conveyorBelt = new ConveyorBelt("A",1, orientations);
                            deathTrap.get(i).get(j).addTile(conveyorBelt);
                        }
                    }

                    if (i == 11 && j == 0){
                        if(k == 0){
                            Empty empty = new Empty("A");
                            deathTrap.get(i).get(j).addTile(empty);
                        }
                    }

                    if (i == 12 && j == 0){
                        if(k == 0){
                            Empty empty = new Empty("A");
                            deathTrap.get(i).get(j).addTile(empty);
                        }
                    }

                    if(i == 0 && j == 1){
                        if(k == 0){
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.LEFT);
                            CheckPoint checkPoint = new CheckPoint("2A",orientations,5);
                            deathTrap.get(i).get(j).addTile(checkPoint);
                        }
                    }

                    if (i == 1 && j == 1){
                        if (k == 0){
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.TOP);
                            Wall wall = new Wall( "2A", orientations);
                            deathTrap.get(i).get(j).addTile(wall);
                        }
                        if (k == 1){
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.TOP);
                            ArrayList<Integer> registers = new ArrayList<>();
                            registers.add(1);
                            registers.add(3);
                            registers.add(5);
                            PushPanel pushPanel  = new PushPanel( "2A", orientations, registers);
                            deathTrap.get(i).get(j).addTile(pushPanel);
                        }
                    }

                    if (i == 2 && j == 1){
                        if(k == 0){
                            Empty empty = new Empty("2A");
                            deathTrap.get(i).get(j).addTile(empty);
                        }
                    }


                    if (i == 3 && j == 1){
                        if (k == 0){
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.BOTTOM);
                            Wall wall = new Wall("2A", orientations);
                            deathTrap.get(i).get(j).addTile(wall);
                        }
                    }

                    if (i == 4 && j == 1){
                        if (k == 0){
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.TOP);
                            orientations.add(Orientation.RIGHT);
                            ConveyorBelt conveyorBelt = new ConveyorBelt("2A",1, orientations);
                            deathTrap.get(i).get(j).addTile(conveyorBelt);
                        }
                    }

                    if (i == 5 && j == 1){
                        if (k == 0){
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.LEFT);
                            orientations.add(Orientation.RIGHT);
                            ConveyorBelt conveyorBelt = new ConveyorBelt("2A",1, orientations);
                            deathTrap.get(i).get(j).addTile(conveyorBelt);
                        }
                    }

                    if (i == 6 && j == 1){
                        if(k == 0){
                            Empty empty = new Empty("2A");
                            deathTrap.get(i).get(j).addTile(empty);
                        }
                    }

                    if (i == 7 && j == 1){
                        if (k == 0){
                            Pit pit = new Pit( "2A");
                            deathTrap.get(i).get(j).addTile(pit);
                        }
                    }

                    if (i == 8 && j == 1){
                        if (k == 0){
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.RIGHT);
                            Wall wall = new Wall("2A", orientations);
                            deathTrap.get(i).get(j).addTile(wall);
                        }
                        if (k == 1){
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.RIGHT);
                            ArrayList<Integer> registers = new ArrayList<>();
                            registers.add(1);
                            registers.add(3);
                            registers.add(5);
                            PushPanel pushPanel  = new PushPanel( "2A", orientations, registers);
                            deathTrap.get(i).get(j).addTile(pushPanel);
                        }
                    }

                    if (i == 9 && j == 1){
                        if (k == 0){
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.TOP);
                            orientations.add(Orientation.BOTTOM);
                            ConveyorBelt conveyorBelt = new ConveyorBelt("2A",1, orientations);
                            deathTrap.get(i).get(j).addTile(conveyorBelt);
                        }
                    }

                    if (i == 10 && j == 1){
                        if(k == 0){
                            Empty empty = new Empty("A");
                            deathTrap.get(i).get(j).addTile(empty);
                        }
                    }

                    if(i == 11 && j == 1){
                        if(k == 0){
                            StartPoint startPoint = new StartPoint( "A");
                            deathTrap.get(i).get(j).addTile(startPoint);
                        }
                    }

                    if (i == 12 && j == 1){
                        if(k == 0){
                            Empty empty = new Empty("A");
                            deathTrap.get(i).get(j).addTile(empty);
                        }
                    }

                    if (i == 0 && j == 2){
                        if(k == 0){
                            Empty empty = new Empty("2A");
                            deathTrap.get(i).get(j).addTile(empty);
                        }
                    }

                    if (i == 1 && j == 2){
                        if (k == 0){
                            Pit pit = new Pit( "2A");
                            deathTrap.get(i).get(j).addTile(pit);
                        }
                    }

                    if (i == 2 && j == 2){
                        if (k == 0){
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.LEFT);
                            Wall wall = new Wall( "2A", orientations);
                            deathTrap.get(i).get(j).addTile(wall);
                        }
                        if (k == 1) {
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.LEFT);
                            ArrayList<Integer> registers = new ArrayList<>();
                            registers.add(2);
                            registers.add(4);
                            PushPanel pushPanel = new PushPanel( "2A", orientations, registers);
                            deathTrap.get(i).get(j).addTile(pushPanel);
                        }
                    }

                    if (i == 3 && j == 2){
                        if (k == 0){
                            Pit pit = new Pit( "2A");
                            deathTrap.get(i).get(j).addTile(pit);
                        }
                    }

                    if(i == 4 && j == 2){
                        if(k == 0){
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.TOP);
                            EnergySpace energySpace = new EnergySpace("2A", orientations,1);
                            deathTrap.get(i).get(j).addTile(energySpace);
                        }
                    }

                    if (i == 5 && j == 2){
                        if(k == 0){
                            Empty empty = new Empty("2A");
                            deathTrap.get(i).get(j).addTile(empty);
                        }
                    }

                    //Energyspace ist hier horizontal nicht vertikal
                    if(i == 6 && j == 2){
                        if(k == 0){
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.LEFT);
                            EnergySpace energySpace = new EnergySpace("2A", orientations,1);
                            deathTrap.get(i).get(j).addTile(energySpace);
                        }
                    }

                    if (i == 7 && j == 2){
                        if (k == 0){
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.TOP);
                            Wall wall = new Wall( "2A", orientations);
                            deathTrap.get(i).get(j).addTile(wall);
                        }
                        if (k == 1){
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.TOP);
                            ArrayList<Integer> registers = new ArrayList<>();
                            registers.add(2);
                            registers.add(4);
                            PushPanel pushPanel  = new PushPanel( "2A", orientations, registers);
                            deathTrap.get(i).get(j).addTile(pushPanel);
                        }
                    }

                    if(i == 8 && j == 2){
                        if(k == 0){
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.LEFT);
                            CheckPoint checkPoint = new CheckPoint("2A",orientations,4);
                            deathTrap.get(i).get(j).addTile(checkPoint);
                        }
                    }

                    if (i == 9 && j == 2){
                        if (k == 0){
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.TOP);
                            orientations.add(Orientation.BOTTOM);
                            ConveyorBelt conveyorBelt = new ConveyorBelt("2A",1, orientations);
                            deathTrap.get(i).get(j).addTile(conveyorBelt);
                        }
                    }

                    if (i == 10 && j == 2){
                        if(k == 0){
                            Empty empty = new Empty("A");
                            deathTrap.get(i).get(j).addTile(empty);
                        }
                    }

                    if (i == 11 && j == 2){
                        if (k == 0){
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.TOP);
                            Wall wall = new Wall( "A", orientations);
                            deathTrap.get(i).get(j).addTile(wall);
                        }
                    }

                    if (i == 12 && j == 2){
                        if(k == 0){
                            Empty empty = new Empty("A");
                            deathTrap.get(i).get(j).addTile(empty);
                        }
                    }

                    if (i == 0 && j == 3){
                        if(k == 0){
                            Empty empty = new Empty("2A");
                            deathTrap.get(i).get(j).addTile(empty);
                        }
                    }

                    if (i == 1 && j == 3){
                        if(k == 0){
                            Empty empty = new Empty("2A");
                            deathTrap.get(i).get(j).addTile(empty);
                        }
                    }

                    if(i == 2 && j == 3){
                        if(k == 0){
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.TOP);
                            EnergySpace energySpace = new EnergySpace("2A", orientations,1);
                            deathTrap.get(i).get(j).addTile(energySpace);
                        }
                    }

                    if (i == 3 && j == 3){
                        if(k == 0){
                            Empty empty = new Empty("2A");
                            deathTrap.get(i).get(j).addTile(empty);
                        }
                    }

                    if (i == 4 && j == 3){
                        if (k == 0){
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.BOTTOM);
                            Wall wall = new Wall("2A", orientations);
                            deathTrap.get(i).get(j).addTile(wall);
                        }
                        if (k == 1) {
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.BOTTOM);
                            ArrayList<Integer> registers = new ArrayList<>();
                            registers.add(2);
                            registers.add(4);
                            PushPanel pushPanel = new PushPanel( "2A", orientations, registers);
                            deathTrap.get(i).get(j).addTile(pushPanel);
                        }
                    }

                    if (i == 5 && j == 3){
                        if(k == 0){
                            Empty empty = new Empty("2A");
                            deathTrap.get(i).get(j).addTile(empty);
                        }
                    }

                    if (i == 7 && j == 3){
                        if (k == 0){
                            Pit pit = new Pit( "2A");
                            deathTrap.get(i).get(j).addTile(pit);
                        }
                    }

                    if (i == 8 && j == 3){
                        if (k == 0){
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.LEFT);
                            Wall wall = new Wall("2A", orientations);
                            deathTrap.get(i).get(j).addTile(wall);
                        }
                    }

                    if (i == 9 && j == 3){
                        if (k == 0){
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.TOP);
                            orientations.add(Orientation.BOTTOM);
                            ConveyorBelt conveyorBelt = new ConveyorBelt("2A",1, orientations);
                            deathTrap.get(i).get(j).addTile(conveyorBelt);
                        }
                    }

                    if (i == 10 && j == 3){
                        if(k == 0){
                            Empty empty = new Empty("A");
                            deathTrap.get(i).get(j).addTile(empty);
                        }
                    }

                    if (i == 11 && j == 3){
                        if(k == 0){
                            Empty empty = new Empty("A");
                            deathTrap.get(i).get(j).addTile(empty);
                        }
                    }

                    if(i == 12 && j == 3){
                        if(k == 0){
                            StartPoint startPoint = new StartPoint( "A");
                            deathTrap.get(i).get(j).addTile(startPoint);
                        }
                    }

                    if (i == 0 && j == 4){
                        if(k == 0){
                            Empty empty = new Empty("2A");
                            deathTrap.get(i).get(j).addTile(empty);
                        }
                    }

                    if (i == 1 && j == 4){
                        if (k == 0){
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.BOTTOM);
                            orientations.add(Orientation.TOP);
                            ConveyorBelt conveyorBelt = new ConveyorBelt("2A",1, orientations);
                            deathTrap.get(i).get(j).addTile(conveyorBelt);
                        }
                    }

                    if (i == 2 && j == 4){
                        if(k == 0){
                            Empty empty = new Empty("2A");
                            deathTrap.get(i).get(j).addTile(empty);
                        }
                    }

                    if (i == 3 && j == 4){
                        if (k == 0){
                            Pit pit = new Pit("2A");
                            deathTrap.get(i).get(j).addTile(pit);
                        }
                    }

                    if(i == 4 && j == 4){
                        if(k == 0){
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.LEFT);
                            CheckPoint checkPoint = new CheckPoint("2A",orientations,2);
                            deathTrap.get(i).get(j).addTile(checkPoint);
                        }
                    }

                    if (i == 5 && j == 4){
                        if(k == 0){
                            Empty empty = new Empty("2A");
                            deathTrap.get(i).get(j).addTile(empty);
                        }
                    }

                    if (i == 6 && j == 4){
                        if (k == 0){
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.TOP);
                            Wall wall = new Wall( "2A", orientations);
                            deathTrap.get(i).get(j).addTile(wall);
                        }
                        if (k == 1) {
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.TOP);
                            ArrayList<Integer> registers = new ArrayList<>();
                            registers.add(1);
                            registers.add(3);
                            registers.add(5);
                            PushPanel pushPanel = new PushPanel("2A", orientations, registers);
                            deathTrap.get(i).get(j).addTile(pushPanel);
                        }
                    }

                    if (i == 7 && j == 4){
                        if(k == 0){
                            Empty empty = new Empty("2A");
                            deathTrap.get(i).get(j).addTile(empty);
                        }
                    }

                    if (i == 8 && j == 4){
                        if (k == 0){
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.RIGHT);
                            orientations.add(Orientation.BOTTOM);
                            ConveyorBelt conveyorBelt = new ConveyorBelt("2A",1, orientations);
                            deathTrap.get(i).get(j).addTile(conveyorBelt);
                        }
                    }

                    if (i == 9 && j == 4){
                        if (k == 0){
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.TOP);
                            orientations.add(Orientation.LEFT);
                            ConveyorBelt conveyorBelt = new ConveyorBelt("2A",1, orientations);
                            deathTrap.get(i).get(j).addTile(conveyorBelt);
                        }
                    }

                    if (i == 10 && j == 4){
                        if (k == 0){
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.LEFT);
                            Wall wall = new Wall( "A", orientations);
                            deathTrap.get(i).get(j).addTile(wall);
                        }
                    }

                    if(i == 11 && j == 4){
                        if(k == 0){
                            StartPoint startPoint = new StartPoint( "A");
                            deathTrap.get(i).get(j).addTile(startPoint);
                        }
                    }

                    if (i == 12 && j == 4){
                        if(k == 0){
                            Empty empty = new Empty("A");
                            deathTrap.get(i).get(j).addTile(empty);
                        }
                    }

                    if (i == 0 && j == 5){
                        if (k == 0){
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.BOTTOM);
                            orientations.add(Orientation.RIGHT);
                            ConveyorBelt conveyorBelt = new ConveyorBelt("2A",1, orientations);
                            deathTrap.get(i).get(j).addTile(conveyorBelt);
                        }
                    }

                    if (i == 1 && j == 5){
                        if (k == 0){
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.LEFT);
                            orientations.add(Orientation.TOP);
                            ConveyorBelt conveyorBelt = new ConveyorBelt("2A",1, orientations);
                            deathTrap.get(i).get(j).addTile(conveyorBelt);
                        }
                    }

                    if (i == 2 && j == 5){
                        if(k == 0){
                            Empty empty = new Empty("2A");
                            deathTrap.get(i).get(j).addTile(empty);
                        }
                    }

                    if (i == 3 && j == 5){
                        if (k == 0){
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.BOTTOM);
                            Wall wall = new Wall( "2A", orientations);
                            deathTrap.get(i).get(j).addTile(wall);
                        }
                        if (k == 1) {
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.BOTTOM);
                            ArrayList<Integer> registers = new ArrayList<>();
                            registers.add(1);
                            registers.add(3);
                            registers.add(5);
                            PushPanel pushPanel = new PushPanel("2A", orientations, registers);
                            deathTrap.get(i).get(j).addTile(pushPanel);
                        }
                    }

                    if (i == 4 && j == 5){
                        if (k == 0){
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.TOP);
                            Wall wall = new Wall( "2A", orientations);
                            deathTrap.get(i).get(j).addTile(wall);
                        }
                        if (k == 1) {
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.TOP);
                            ArrayList<Integer> registers = new ArrayList<>();
                            registers.add(2);
                            registers.add(4);
                            PushPanel pushPanel = new PushPanel( "2A", orientations, registers);
                            deathTrap.get(i).get(j).addTile(pushPanel);
                        }
                    }

                    if (i == 5 && j == 5){
                        if (k == 0){
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.BOTTOM);
                            Wall wall = new Wall("2A", orientations);
                            deathTrap.get(i).get(j).addTile(wall);
                        }
                    }

                    if (i == 6 && j == 5){
                        if (k == 0){
                            Pit pit = new Pit("2A");
                            deathTrap.get(i).get(j).addTile(pit);
                        }
                    }

                    if (i == 7 && j == 5){
                        if(k == 0){
                            Empty empty = new Empty("2A");
                            deathTrap.get(i).get(j).addTile(empty);
                        }
                    }

                    if (i == 8 && j == 5){
                        if (k == 0){
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.TOP);
                            orientations.add(Orientation.BOTTOM);
                            ConveyorBelt conveyorBelt = new ConveyorBelt("2A",1, orientations);
                            deathTrap.get(i).get(j).addTile(conveyorBelt);
                        }
                    }

                    if (i == 9 && j == 5){
                        if(k == 0){
                            Empty empty = new Empty("2A");
                            deathTrap.get(i).get(j).addTile(empty);
                        }
                    }

                    if (i == 10 && j == 5){
                        if (k == 0){
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.LEFT);
                            Wall wall = new Wall("A", orientations);
                            deathTrap.get(i).get(j).addTile(wall);
                        }
                    }

                    if(i == 11 && j == 5){
                        if(k == 0){
                            StartPoint startPoint = new StartPoint( "A");
                            deathTrap.get(i).get(j).addTile(startPoint);
                        }
                    }

                    if(i == 12 && j == 5){
                        if(k == 0){
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.LEFT);
                            Antenna antenna = new Antenna("A", orientations);
                            deathTrap.get(i).get(j).addTile(antenna);
                        }
                    }

                    if (i == 0 && j == 6){
                        if (k == 0){
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.BOTTOM);
                            orientations.add(Orientation.TOP);
                            ConveyorBelt conveyorBelt = new ConveyorBelt("2A",1, orientations);
                            deathTrap.get(i).get(j).addTile(conveyorBelt);
                        }
                    }

                    if (i == 1 && j == 6){
                        if (k == 0){
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.RIGHT);
                            Wall wall = new Wall( "2A", orientations);
                            deathTrap.get(i).get(j).addTile(wall);
                        }
                    }

                    if (i == 2 && j == 6){
                        if (k == 0){
                            Pit pit = new Pit( "2A");
                            deathTrap.get(i).get(j).addTile(pit);
                        }
                    }

                    if (i == 3 && j == 6){
                        if(k == 0){
                            Empty empty = new Empty("2A");
                            deathTrap.get(i).get(j).addTile(empty);
                        }
                    }

                    if(i == 4 && j == 6){
                        if(k == 0){
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.TOP);
                            EnergySpace energySpace = new EnergySpace("2A", orientations, 1);
                            deathTrap.get(i).get(j).addTile(energySpace);
                        }
                    }

                    if (i == 5 && j == 6){
                        if(k == 0){
                            Empty empty = new Empty("2A");
                            deathTrap.get(i).get(j).addTile(empty);
                        }
                    }

                    if (i == 6 && j == 6){
                        if(k == 0){
                            Empty empty = new Empty("2A");
                            deathTrap.get(i).get(j).addTile(empty);
                        }
                    }

                    if(i == 7 && j == 6){
                        if(k == 0){
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.TOP);
                            EnergySpace energySpace = new EnergySpace("2A", orientations, 1);
                            deathTrap.get(i).get(j).addTile(energySpace);
                        }
                    }

                    if (i == 8 && j == 6){
                        if(k == 0){
                            Empty empty = new Empty("2A");
                            deathTrap.get(i).get(j).addTile(empty);
                        }
                    }

                    if (i == 9 && j == 6){
                        if(k == 0){
                            Empty empty = new Empty("2A");
                            deathTrap.get(i).get(j).addTile(empty);
                        }
                    }

                    if (i == 10 && j == 6){
                        if(k == 0){
                            Empty empty = new Empty("A");
                            deathTrap.get(i).get(j).addTile(empty);
                        }
                    }

                    if (i == 11 && j == 6){
                        if(k == 0){
                            Empty empty = new Empty("A");
                            deathTrap.get(i).get(j).addTile(empty);
                        }
                    }

                    if(i == 12 && j == 6){
                        if(k == 1){
                            StartPoint startPoint = new StartPoint( "A");
                            deathTrap.get(i).get(j).addTile(startPoint);
                        }
                    }

                    if (i == 0 && j == 7){
                        if (k == 0){
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.BOTTOM);
                            orientations.add(Orientation.TOP);
                            ConveyorBelt conveyorBelt = new ConveyorBelt("2A",1, orientations);
                            deathTrap.get(i).get(j).addTile(conveyorBelt);
                        }
                    }

                    if(i == 1 && j == 7){
                        if(k == 0){
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.LEFT);
                            CheckPoint checkPoint = new CheckPoint("2A",orientations,1);
                            deathTrap.get(i).get(j).addTile(checkPoint);
                        }
                    }

                    if (i == 2 && j == 7){
                        if (k == 0){
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.BOTTOM);
                            Wall wall = new Wall("2A", orientations);
                            deathTrap.get(i).get(j).addTile(wall);
                        }
                        if (k == 1) {
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.BOTTOM);
                            ArrayList<Integer> registers = new ArrayList<>();
                            registers.add(2);
                            registers.add(4);
                            PushPanel pushPanel = new PushPanel( "2A", orientations, registers);
                            deathTrap.get(i).get(j).addTile(pushPanel);
                        }
                    }

                    if(i == 3 && j == 7){
                        if(k == 0){
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.LEFT);
                            EnergySpace energySpace = new EnergySpace("2A", orientations, 1);
                            deathTrap.get(i).get(j).addTile(energySpace);
                        }
                    }

                    if (i == 4 && j == 7){
                        if(k == 0){
                            Empty empty = new Empty("2A");
                            deathTrap.get(i).get(j).addTile(empty);
                        }
                    }

                    if (i == 5 && j == 7){
                        if(k == 0){
                            Empty empty = new Empty("2A");
                            deathTrap.get(i).get(j).addTile(empty);
                        }
                    }

                    if (i == 6 && j == 7){
                        if (k == 0){
                            Pit pit = new Pit("2A");
                            deathTrap.get(i).get(j).addTile(pit);
                        }
                    }

                    if (i == 7 && j == 7){
                        if (k == 0){
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.RIGHT);
                            Wall wall = new Wall( "2A", orientations);
                            deathTrap.get(i).get(j).addTile(wall);
                        }
                        if (k == 1) {
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.RIGHT);
                            ArrayList<Integer> registers = new ArrayList<>();
                            registers.add(2);
                            registers.add(4);
                            PushPanel pushPanel = new PushPanel("2A", orientations, registers);
                            deathTrap.get(i).get(j).addTile(pushPanel);
                        }
                    }

                    if (i == 8 && j == 7){
                        if (k == 0){
                            Pit pit = new Pit( "2A");
                            deathTrap.get(i).get(j).addTile(pit);
                        }
                    }

                    if (i == 9 && j == 7){
                        if(k == 0){
                            Empty empty = new Empty("2A");
                            deathTrap.get(i).get(j).addTile(empty);
                        }
                    }

                    if (i == 10 && j == 7){
                        if(k == 0){
                            Empty empty = new Empty("A");
                            deathTrap.get(i).get(j).addTile(empty);
                        }
                    }

                    if (i == 11 && j == 7){
                        if (k == 0){
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.BOTTOM);
                            Wall wall = new Wall("A", orientations);
                            deathTrap.get(i).get(j).addTile(wall);
                        }
                    }

                    if (i == 12 && j == 7){
                        if(k == 0){
                            Empty empty = new Empty("A");
                            deathTrap.get(i).get(j).addTile(empty);
                        }
                    }

                    if (i == 0 && j == 8){
                        if (k == 0){
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.BOTTOM);
                            orientations.add(Orientation.TOP);
                            ConveyorBelt conveyorBelt = new ConveyorBelt("2A",1, orientations);
                            deathTrap.get(i).get(j).addTile(conveyorBelt);
                        }
                    }

                    if (i == 1 && j == 8){
                        if (k == 0){
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.LEFT);
                            Wall wall = new Wall("2A", orientations);
                            deathTrap.get(i).get(j).addTile(wall);
                        }
                        if (k == 1) {
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.LEFT);
                            ArrayList<Integer> registers = new ArrayList<>();
                            registers.add(1);
                            registers.add(3);
                            registers.add(5);
                            PushPanel pushPanel = new PushPanel( "2A", orientations, registers);
                            deathTrap.get(i).get(j).addTile(pushPanel);
                        }
                    }

                    if (i == 2 && j == 8){
                        if (k == 0){
                            Pit pit = new Pit( "2A");
                            deathTrap.get(i).get(j).addTile(pit);
                        }
                    }

                    if (i == 3 && j == 8){
                        if(k == 0){
                            Empty empty = new Empty("2A");
                            deathTrap.get(i).get(j).addTile(empty);
                        }
                    }

                    if (i == 4 && j == 8){
                        if (k == 0){
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.RIGHT);
                            orientations.add(Orientation.LEFT);
                            ConveyorBelt conveyorBelt = new ConveyorBelt("2A",1, orientations);
                            deathTrap.get(i).get(j).addTile(conveyorBelt);
                        }
                    }

                    if (i == 5 && j == 8){
                        if (k == 0){
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.BOTTOM);
                            orientations.add(Orientation.LEFT);
                            ConveyorBelt conveyorBelt = new ConveyorBelt("2A",1, orientations);
                            deathTrap.get(i).get(j).addTile(conveyorBelt);
                        }
                    }

                    if(i == 6 && j == 8){
                        if (k == 0) {
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.TOP);
                            Wall wall = new Wall( "2A", orientations);
                            deathTrap.get(i).get(j).addTile(wall);
                        }

                    }

                    if(i == 7 && j == 8){
                        if(k == 0){
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.LEFT);
                            CheckPoint checkPoint = new CheckPoint("2A",orientations,3);
                            deathTrap.get(i).get(j).addTile(checkPoint);
                        }
                    }

                    if (i == 8 && j == 8){
                        if (k == 0){
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.BOTTOM);
                            Wall wall = new Wall("2A", orientations);
                            deathTrap.get(i).get(j).addTile(wall);
                        }
                        if (k == 1) {
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.BOTTOM);
                            ArrayList<Integer> registers = new ArrayList<>();
                            registers.add(1);
                            registers.add(3);
                            registers.add(5);
                            PushPanel pushPanel = new PushPanel("2A", orientations, registers);
                            deathTrap.get(i).get(j).addTile(pushPanel);
                        }
                    }

                    if (i == 9 && j == 8){
                        if(k == 0){
                            Empty empty = new Empty("2A");
                            deathTrap.get(i).get(j).addTile(empty);
                        }
                    }

                    if (i == 10 && j == 8){
                        if(k == 0){
                            Empty empty = new Empty("A");
                            deathTrap.get(i).get(j).addTile(empty);
                        }
                    }

                    if(i == 11 && j == 8){
                        if(k == 0) {
                            StartPoint startPoint = new StartPoint( "A");
                            deathTrap.get(i).get(j).addTile(startPoint);
                        }
                    }

                    if (i == 12 && j == 8){
                        if(k == 0){
                            Empty empty = new Empty("A");
                            deathTrap.get(i).get(j).addTile(empty);
                        }
                    }

                    if (i == 0 && j == 9){
                        if(k == 0){
                            Empty empty = new Empty("2A");
                            deathTrap.get(i).get(j).addTile(empty);
                        }
                    }
                    if (i == 1 && j == 9){
                        if(k == 0){
                            Empty empty = new Empty("2A");
                            deathTrap.get(i).get(j).addTile(empty);
                        }
                    }

                    if (i == 2 && j == 9){
                        if(k == 0){
                            Empty empty = new Empty("2A");
                            deathTrap.get(i).get(j).addTile(empty);
                        }
                    }

                    if (i == 3 && j == 9){
                        if(k == 0){
                            Empty empty = new Empty("2A");
                            deathTrap.get(i).get(j).addTile(empty);
                        }
                    }

                    if (i == 4 && j == 9){
                        if(k == 0){
                            Empty empty = new Empty("2A");
                            deathTrap.get(i).get(j).addTile(empty);
                        }
                    }

                    if (i == 5 && j == 9){
                        if (k == 0){
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.RIGHT);
                            orientations.add(Orientation.TOP);
                            ConveyorBelt conveyorBelt = new ConveyorBelt("2A",1, orientations);
                            deathTrap.get(i).get(j).addTile(conveyorBelt);
                        }
                    }

                    if (i == 6 && j == 9){
                        if (k == 0){
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.RIGHT);
                            orientations.add(Orientation.LEFT);
                            ConveyorBelt conveyorBelt = new ConveyorBelt("2A",1, orientations);
                            deathTrap.get(i).get(j).addTile(conveyorBelt);
                        }
                    }

                    if (i == 7 && j == 9){
                        if (k == 0){
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.RIGHT);
                            orientations.add(Orientation.LEFT);
                            ConveyorBelt conveyorBelt = new ConveyorBelt("2A",1, orientations);
                            deathTrap.get(i).get(j).addTile(conveyorBelt);
                        }
                    }

                    if (i == 8 && j == 9){
                        if (k == 0){
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.RIGHT);
                            orientations.add(Orientation.LEFT);
                            ConveyorBelt conveyorBelt = new ConveyorBelt("2A",1, orientations);
                            deathTrap.get(i).get(j).addTile(conveyorBelt);
                        }
                    }

                    if (i == 9 && j == 9){
                        if(k == 0){
                            Empty empty = new Empty("2A");
                            deathTrap.get(i).get(j).addTile(empty);
                        }
                    }

                    if (i == 10 && j == 9){
                        if (k == 0){
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.LEFT);
                            orientations.add(Orientation.RIGHT);
                            ConveyorBelt conveyorBelt = new ConveyorBelt("A",1, orientations);
                            deathTrap.get(i).get(j).addTile(conveyorBelt);
                        }
                    }

                    if (i == 11 && j == 9){
                        if(k == 0){
                            Empty empty = new Empty("A");
                            deathTrap.get(i).get(j).addTile(empty);
                        }
                    }

                    if(i == 12 && j == 9){
                        if(k == 0){
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.LEFT);
                            RestartPoint restartPoint = new RestartPoint("A",orientations);
                            deathTrap.get(i).get(j).addTile(restartPoint);
                        }
                    }
                }
            }
        }
        return deathTrap;
    }

}
