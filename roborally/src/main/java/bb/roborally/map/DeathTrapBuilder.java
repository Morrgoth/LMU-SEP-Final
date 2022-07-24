package bb.roborally.map;

import bb.roborally.protocol.map.Board;
import bb.roborally.protocol.map.Cell;
import bb.roborally.protocol.map.GameStarted;
import bb.roborally.protocol.map.tiles.*;
import bb.roborally.server.game.Orientation;
import bb.roborally.server.game.board.ServerCell;

import java.util.ArrayList;

public class DeathTrapBuilder implements BoardBuilder {

    public GameStarted build() {
        final int X_MAX = 13;
        final int Y_MAX = 10;
        final int maxCellContent = 4;
        ArrayList<ArrayList<Cell>> deathTrap= new ArrayList<>();
        for (int x = 0; x < X_MAX; x++) {
            deathTrap.add(new ArrayList<>());
            for (int y = 0; y < Y_MAX; y++) {
                deathTrap.get(x).add(new Cell());
                for (int k = 0; k < maxCellContent; k++) {
                    if (x == 0 && y == 0) {
                        if (k == 0) {
                            Empty empty = new Empty("A");
                            deathTrap.get(x).get(y).addTile(empty);
                            }
                        }


                            if (x == 0 && y == 0) {
                                if (k == 0) {
                                    Empty empty = new Empty("2A");
                                    deathTrap.get(x).get(y).addTile(empty);
                                }
                            }

                            if (x == 1 && y == 0) {
                                if (k == 0) {
                                    Empty empty = new Empty("2A");
                                    deathTrap.get(x).get(y).addTile(empty);
                                }
                            }

                            if (x == 2 && y == 0) {
                                if (k == 0) {
                                    ArrayList<Orientation> orientations = new ArrayList<>();
                                    orientations.add(Orientation.LEFT);
                                    orientations.add(Orientation.RIGHT);
                                    ConveyorBelt conveyorBelt = new ConveyorBelt("2A", 1, orientations);
                                    deathTrap.get(x).get(y).addTile(conveyorBelt);
                                }
                            }

                            if (x == 3 && y == 0) {
                                if (k == 0) {
                                    ArrayList<Orientation> orientations = new ArrayList<>();
                                    orientations.add(Orientation.LEFT);
                                    orientations.add(Orientation.RIGHT);
                                    ConveyorBelt conveyorBelt = new ConveyorBelt("2A", 1, orientations);
                                    deathTrap.get(x).get(y).addTile(conveyorBelt);
                                }
                            }

                            if (x == 4 && y == 0) {
                                if (k == 0) {
                                    ArrayList<Orientation> orientations = new ArrayList<>();
                                    orientations.add(Orientation.LEFT);
                                    orientations.add(Orientation.BOTTOM);
                                    ConveyorBelt conveyorBelt = new ConveyorBelt("2A", 1, orientations);
                                    deathTrap.get(x).get(y).addTile(conveyorBelt);
                                }
                            }

                            if (x == 5 && y == 0) {
                                if (k == 0) {
                                    Empty empty = new Empty("2A");
                                    deathTrap.get(x).get(y).addTile(empty);
                                }
                            }

                            if (x == 6 && y == 0) {
                                if (k == 0) {
                                    Empty empty = new Empty("2A");
                                    deathTrap.get(x).get(y).addTile(empty);
                                }
                            }

                            if (x == 7 && y == 0) {
                                if (k == 0) {
                                    Empty empty = new Empty("2A");
                                    deathTrap.get(x).get(y).addTile(empty);
                                }
                            }

                            if (x == 8 && y == 0) {
                                if (k == 0) {
                                    Empty empty = new Empty("2A");
                                    deathTrap.get(x).get(y).addTile(empty);
                                }
                            }

                            if (x == 9 && y == 0) {
                                if (k == 0) {
                                    Empty empty = new Empty("2A");
                                    deathTrap.get(x).get(y).addTile(empty);
                                }
                            }

                            if (x == 10 && y == 0) {
                                if (k == 0) {
                                    ArrayList<Orientation> orientations = new ArrayList<>();
                                    orientations.add(Orientation.LEFT);
                                    orientations.add(Orientation.RIGHT);
                                    ConveyorBelt conveyorBelt = new ConveyorBelt("A", 1, orientations);
                                    deathTrap.get(x).get(y).addTile(conveyorBelt);
                                }
                            }

                            if (x == 11 && y == 0) {
                                if (k == 0) {
                                    Empty empty = new Empty("A");
                                    deathTrap.get(x).get(y).addTile(empty);
                                }
                            }

                            if (x == 12 && y == 0) {
                                if (k == 0) {
                                    Empty empty = new Empty("A");
                                    deathTrap.get(x).get(y).addTile(empty);
                                }
                            }

                            if (x == 0 && y == 1) {
                                if (k == 0) {
                                    ArrayList<Orientation> orientations = new ArrayList<>();
                                    orientations.add(Orientation.LEFT);
                                    CheckPoint checkPoint = new CheckPoint("2A", orientations, 5);
                                    deathTrap.get(x).get(y).addTile(checkPoint);
                                }
                            }

                            if (x == 1 && y == 1) {
                                if (k == 0) {
                                    ArrayList<Orientation> orientations = new ArrayList<>();
                                    orientations.add(Orientation.TOP);
                                    Wall wall = new Wall("2A", orientations);
                                    deathTrap.get(x).get(y).addTile(wall);
                                }
                                if (k == 1) {
                                    ArrayList<Orientation> orientations = new ArrayList<>();
                                    orientations.add(Orientation.BOTTOM);
                                    ArrayList<Integer> registers = new ArrayList<>();
                                    registers.add(1);
                                    registers.add(3);
                                    registers.add(5);
                                    PushPanel pushPanel = new PushPanel("2A", orientations, registers);
                                    deathTrap.get(x).get(y).addTile(pushPanel);
                                }
                            }

                            if (x == 2 && y == 1) {
                                if (k == 0) {
                                    Empty empty = new Empty("2A");
                                    deathTrap.get(x).get(y).addTile(empty);
                                }
                            }


                            if (x == 3 && y == 1) {
                                if (k == 0) {
                                    ArrayList<Orientation> orientations = new ArrayList<>();
                                    orientations.add(Orientation.BOTTOM);
                                    Wall wall = new Wall("2A", orientations);
                                    deathTrap.get(x).get(y).addTile(wall);
                                }
                            }

                            if (x == 4 && y == 1) {
                                if (k == 0) {
                                    ArrayList<Orientation> orientations = new ArrayList<>();
                                    orientations.add(Orientation.TOP);
                                    orientations.add(Orientation.RIGHT);
                                    ConveyorBelt conveyorBelt = new ConveyorBelt("2A", 1, orientations);
                                    deathTrap.get(x).get(y).addTile(conveyorBelt);
                                }
                            }

                            if (x == 5 && y == 1) {
                                if (k == 0) {
                                    ArrayList<Orientation> orientations = new ArrayList<>();
                                    orientations.add(Orientation.LEFT);
                                    orientations.add(Orientation.RIGHT);
                                    ConveyorBelt conveyorBelt = new ConveyorBelt("2A", 1, orientations);
                                    deathTrap.get(x).get(y).addTile(conveyorBelt);
                                }
                            }

                            if (x == 6 && y == 1) {
                                if (k == 0) {
                                    Empty empty = new Empty("2A");
                                    deathTrap.get(x).get(y).addTile(empty);
                                }
                            }

                            if (x == 7 && y == 1) {
                                if (k == 0) {
                                    Pit pit = new Pit("2A");
                                    deathTrap.get(x).get(y).addTile(pit);
                                }
                            }

                            if (x == 8 && y == 1) {
                                if (k == 0) {
                                    ArrayList<Orientation> orientations = new ArrayList<>();
                                    orientations.add(Orientation.RIGHT);
                                    Wall wall = new Wall("2A", orientations);
                                    deathTrap.get(x).get(y).addTile(wall);
                                }
                                if (k == 1) {
                                    ArrayList<Orientation> orientations = new ArrayList<>();
                                    orientations.add(Orientation.LEFT);
                                    ArrayList<Integer> registers = new ArrayList<>();
                                    registers.add(1);
                                    registers.add(3);
                                    registers.add(5);
                                    PushPanel pushPanel = new PushPanel("2A", orientations, registers);
                                    deathTrap.get(x).get(y).addTile(pushPanel);
                                }
                            }

                            if (x == 9 && y == 1) {
                                if (k == 0) {
                                    ArrayList<Orientation> orientations = new ArrayList<>();
                                    orientations.add(Orientation.TOP);
                                    orientations.add(Orientation.BOTTOM);
                                    ConveyorBelt conveyorBelt = new ConveyorBelt("2A", 1, orientations);
                                    deathTrap.get(x).get(y).addTile(conveyorBelt);
                                }
                            }

                            if (x == 10 && y == 1) {
                                if (k == 0) {
                                    Empty empty = new Empty("A");
                                    deathTrap.get(x).get(y).addTile(empty);
                                }
                            }

                            if (x == 11 && y == 1) {
                                if (k == 0) {
                                    StartPoint startPoint = new StartPoint("A");
                                    deathTrap.get(x).get(y).addTile(startPoint);
                                }
                            }

                            if (x == 12 && y == 1) {
                                if (k == 0) {
                                    Empty empty = new Empty("A");
                                    deathTrap.get(x).get(y).addTile(empty);
                                }
                            }

                            if (x == 0 && y == 2) {
                                if (k == 0) {
                                    Empty empty = new Empty("2A");
                                    deathTrap.get(x).get(y).addTile(empty);
                                }
                            }

                            if (x == 1 && y == 2) {
                                if (k == 0) {
                                    Pit pit = new Pit("2A");
                                    deathTrap.get(x).get(y).addTile(pit);
                                }
                            }

                            if (x == 2 && y == 2) {
                                if (k == 0) {
                                    ArrayList<Orientation> orientations = new ArrayList<>();
                                    orientations.add(Orientation.LEFT);
                                    Wall wall = new Wall("2A", orientations);
                                    deathTrap.get(x).get(y).addTile(wall);
                                }
                                if (k == 1) {
                                    ArrayList<Orientation> orientations = new ArrayList<>();
                                    orientations.add(Orientation.RIGHT);
                                    ArrayList<Integer> registers = new ArrayList<>();
                                    registers.add(2);
                                    registers.add(4);
                                    PushPanel pushPanel = new PushPanel("2A", orientations, registers);
                                    deathTrap.get(x).get(y).addTile(pushPanel);
                                }
                            }

                            if (x == 3 && y == 2) {
                                if (k == 0) {
                                    Pit pit = new Pit("2A");
                                    deathTrap.get(x).get(y).addTile(pit);
                                }
                            }

                            if (x == 4 && y == 2) {
                                if (k == 0) {
                                    ArrayList<Orientation> orientations = new ArrayList<>();
                                    orientations.add(Orientation.TOP);
                                    EnergySpace energySpace = new EnergySpace("2A", orientations, 1);
                                    deathTrap.get(x).get(y).addTile(energySpace);
                                }
                            }

                            if (x == 5 && y == 2) {
                                if (k == 0) {
                                    Empty empty = new Empty("2A");
                                    deathTrap.get(x).get(y).addTile(empty);
                                }
                            }

                            //Energyspace ist hier horizontal nicht vertikal
                            if (x == 6 && y == 2) {
                                if (k == 0) {
                                    ArrayList<Orientation> orientations = new ArrayList<>();
                                    orientations.add(Orientation.LEFT);
                                    EnergySpace energySpace = new EnergySpace("2A", orientations, 1);
                                    deathTrap.get(x).get(y).addTile(energySpace);
                                }
                            }

                            if (x == 7 && y == 2) {
                                if (k == 0) {
                                    ArrayList<Orientation> orientations = new ArrayList<>();
                                    orientations.add(Orientation.TOP);
                                    Wall wall = new Wall("2A", orientations);
                                    deathTrap.get(x).get(y).addTile(wall);
                                }
                                if (k == 1) {
                                    ArrayList<Orientation> orientations = new ArrayList<>();
                                    orientations.add(Orientation.BOTTOM);
                                    ArrayList<Integer> registers = new ArrayList<>();
                                    registers.add(2);
                                    registers.add(4);
                                    PushPanel pushPanel = new PushPanel("2A", orientations, registers);
                                    deathTrap.get(x).get(y).addTile(pushPanel);
                                }
                            }

                            if (x == 8 && y == 2) {
                                if (k == 0) {
                                    ArrayList<Orientation> orientations = new ArrayList<>();
                                    orientations.add(Orientation.LEFT);
                                    CheckPoint checkPoint = new CheckPoint("2A", orientations, 4);
                                    deathTrap.get(x).get(y).addTile(checkPoint);
                                }
                            }

                            if (x == 9 && y == 2) {
                                if (k == 0) {
                                    ArrayList<Orientation> orientations = new ArrayList<>();
                                    orientations.add(Orientation.TOP);
                                    orientations.add(Orientation.BOTTOM);
                                    ConveyorBelt conveyorBelt = new ConveyorBelt("2A", 1, orientations);
                                    deathTrap.get(x).get(y).addTile(conveyorBelt);
                                }
                            }

                            if (x == 10 && y == 2) {
                                if (k == 0) {
                                    Empty empty = new Empty("A");
                                    deathTrap.get(x).get(y).addTile(empty);
                                }
                            }

                            if (x == 11 && y == 2) {
                                if (k == 0) {
                                    ArrayList<Orientation> orientations = new ArrayList<>();
                                    orientations.add(Orientation.TOP);
                                    Wall wall = new Wall("A", orientations);
                                    deathTrap.get(x).get(y).addTile(wall);
                                }
                            }

                            if (x == 12 && y == 2) {
                                if (k == 0) {
                                    Empty empty = new Empty("A");
                                    deathTrap.get(x).get(y).addTile(empty);
                                }
                            }

                            if (x == 0 && y == 3) {
                                if (k == 0) {
                                    Empty empty = new Empty("2A");
                                    deathTrap.get(x).get(y).addTile(empty);
                                }
                            }

                            if (x == 1 && y == 3) {
                                if (k == 0) {
                                    Empty empty = new Empty("2A");
                                    deathTrap.get(x).get(y).addTile(empty);
                                }
                            }

                            if (x == 2 && y == 3) {
                                if (k == 0) {
                                    ArrayList<Orientation> orientations = new ArrayList<>();
                                    orientations.add(Orientation.TOP);
                                    EnergySpace energySpace = new EnergySpace("2A", orientations, 1);
                                    deathTrap.get(x).get(y).addTile(energySpace);
                                }
                            }

                            if (x == 3 && y == 3) {
                                if (k == 0) {
                                    Empty empty = new Empty("2A");
                                    deathTrap.get(x).get(y).addTile(empty);
                                }
                            }

                            if (x == 4 && y == 3) {
                                if (k == 0) {
                                    ArrayList<Orientation> orientations = new ArrayList<>();
                                    orientations.add(Orientation.BOTTOM);
                                    Wall wall = new Wall("2A", orientations);
                                    deathTrap.get(x).get(y).addTile(wall);
                                }
                                if (k == 1) {
                                    ArrayList<Orientation> orientations = new ArrayList<>();
                                    orientations.add(Orientation.TOP);
                                    ArrayList<Integer> registers = new ArrayList<>();
                                    registers.add(2);
                                    registers.add(4);
                                    PushPanel pushPanel = new PushPanel("2A", orientations, registers);
                                    deathTrap.get(x).get(y).addTile(pushPanel);
                                }
                            }

                            if (x == 5 && y == 3) {
                                if (k == 0) {
                                    Empty empty = new Empty("2A");
                                    deathTrap.get(x).get(y).addTile(empty);
                                }
                            }

                            if (x == 7 && y == 3) {
                                if (k == 0) {
                                    Pit pit = new Pit("2A");
                                    deathTrap.get(x).get(y).addTile(pit);
                                }
                            }

                            if (x == 8 && y == 3) {
                                if (k == 0) {
                                    ArrayList<Orientation> orientations = new ArrayList<>();
                                    orientations.add(Orientation.LEFT);
                                    Wall wall = new Wall("2A", orientations);
                                    deathTrap.get(x).get(y).addTile(wall);
                                }
                            }

                            if (x == 9 && y == 3) {
                                if (k == 0) {
                                    ArrayList<Orientation> orientations = new ArrayList<>();
                                    orientations.add(Orientation.TOP);
                                    orientations.add(Orientation.BOTTOM);
                                    ConveyorBelt conveyorBelt = new ConveyorBelt("2A", 1, orientations);
                                    deathTrap.get(x).get(y).addTile(conveyorBelt);
                                }
                            }

                            if (x == 10 && y == 3) {
                                if (k == 0) {
                                    Empty empty = new Empty("A");
                                    deathTrap.get(x).get(y).addTile(empty);
                                }
                            }

                            if (x == 11 && y == 3) {
                                if (k == 0) {
                                    Empty empty = new Empty("A");
                                    deathTrap.get(x).get(y).addTile(empty);
                                }
                            }

                            if (x == 12 && y == 3) {
                                if (k == 0) {
                                    StartPoint startPoint = new StartPoint("A");
                                    deathTrap.get(x).get(y).addTile(startPoint);
                                }
                            }

                            if (x == 0 && y == 4) {
                                if (k == 0) {
                                    Empty empty = new Empty("2A");
                                    deathTrap.get(x).get(y).addTile(empty);
                                }
                            }

                            if (x == 1 && y == 4) {
                                if (k == 0) {
                                    ArrayList<Orientation> orientations = new ArrayList<>();
                                    orientations.add(Orientation.BOTTOM);
                                    orientations.add(Orientation.TOP);
                                    ConveyorBelt conveyorBelt = new ConveyorBelt("2A", 1, orientations);
                                    deathTrap.get(x).get(y).addTile(conveyorBelt);
                                }
                            }

                            if (x == 2 && y == 4) {
                                if (k == 0) {
                                    Empty empty = new Empty("2A");
                                    deathTrap.get(x).get(y).addTile(empty);
                                }
                            }

                            if (x == 3 && y == 4) {
                                if (k == 0) {
                                    Pit pit = new Pit("2A");
                                    deathTrap.get(x).get(y).addTile(pit);
                                }
                            }

                            if (x == 4 && y == 4) {
                                if (k == 0) {
                                    ArrayList<Orientation> orientations = new ArrayList<>();
                                    orientations.add(Orientation.LEFT);
                                    CheckPoint checkPoint = new CheckPoint("2A", orientations, 2);
                                    deathTrap.get(x).get(y).addTile(checkPoint);
                                }
                            }

                            if (x == 5 && y == 4) {
                                if (k == 0) {
                                    Empty empty = new Empty("2A");
                                    deathTrap.get(x).get(y).addTile(empty);
                                }
                            }

                            if (x == 6 && y == 4) {
                                if (k == 0) {
                                    ArrayList<Orientation> orientations = new ArrayList<>();
                                    orientations.add(Orientation.TOP);
                                    Wall wall = new Wall("2A", orientations);
                                    deathTrap.get(x).get(y).addTile(wall);
                                }
                                if (k == 1) {
                                    ArrayList<Orientation> orientations = new ArrayList<>();
                                    orientations.add(Orientation.BOTTOM);
                                    ArrayList<Integer> registers = new ArrayList<>();
                                    registers.add(1);
                                    registers.add(3);
                                    registers.add(5);
                                    PushPanel pushPanel = new PushPanel("2A", orientations, registers);
                                    deathTrap.get(x).get(y).addTile(pushPanel);
                                }
                            }

                            if (x == 7 && y == 4) {
                                if (k == 0) {
                                    Empty empty = new Empty("2A");
                                    deathTrap.get(x).get(y).addTile(empty);
                                }
                            }

                            if (x == 8 && y == 4) {
                                if (k == 0) {
                                    ArrayList<Orientation> orientations = new ArrayList<>();
                                    orientations.add(Orientation.RIGHT);
                                    orientations.add(Orientation.BOTTOM);
                                    ConveyorBelt conveyorBelt = new ConveyorBelt("2A", 1, orientations);
                                    deathTrap.get(x).get(y).addTile(conveyorBelt);
                                }
                            }

                            if (x == 9 && y == 4) {
                                if (k == 0) {
                                    ArrayList<Orientation> orientations = new ArrayList<>();
                                    orientations.add(Orientation.TOP);
                                    orientations.add(Orientation.LEFT);
                                    ConveyorBelt conveyorBelt = new ConveyorBelt("2A", 1, orientations);
                                    deathTrap.get(x).get(y).addTile(conveyorBelt);
                                }
                            }

                            if (x == 10 && y == 4) {
                                if (k == 0) {
                                    ArrayList<Orientation> orientations = new ArrayList<>();
                                    orientations.add(Orientation.LEFT);
                                    Wall wall = new Wall("A", orientations);
                                    deathTrap.get(x).get(y).addTile(wall);
                                }
                            }

                            if (x == 11 && y == 4) {
                                if (k == 0) {
                                    StartPoint startPoint = new StartPoint("A");
                                    deathTrap.get(x).get(y).addTile(startPoint);
                                }
                            }

                            if (x == 12 && y == 4) {
                                if (k == 0) {
                                    Empty empty = new Empty("A");
                                    deathTrap.get(x).get(y).addTile(empty);
                                }
                            }

                            if (x == 0 && y == 5) {
                                if (k == 0) {
                                    ArrayList<Orientation> orientations = new ArrayList<>();
                                    orientations.add(Orientation.BOTTOM);
                                    orientations.add(Orientation.RIGHT);
                                    ConveyorBelt conveyorBelt = new ConveyorBelt("2A", 1, orientations);
                                    deathTrap.get(x).get(y).addTile(conveyorBelt);
                                }
                            }

                            if (x == 1 && y == 5) {
                                if (k == 0) {
                                    ArrayList<Orientation> orientations = new ArrayList<>();
                                    orientations.add(Orientation.LEFT);
                                    orientations.add(Orientation.TOP);
                                    ConveyorBelt conveyorBelt = new ConveyorBelt("2A", 1, orientations);
                                    deathTrap.get(x).get(y).addTile(conveyorBelt);
                                }
                            }

                            if (x == 2 && y == 5) {
                                if (k == 0) {
                                    Empty empty = new Empty("2A");
                                    deathTrap.get(x).get(y).addTile(empty);
                                }
                            }

                            if (x == 3 && y == 5) {
                                if (k == 0) {
                                    ArrayList<Orientation> orientations = new ArrayList<>();
                                    orientations.add(Orientation.BOTTOM);
                                    Wall wall = new Wall("2A", orientations);
                                    deathTrap.get(x).get(y).addTile(wall);
                                }
                                if (k == 1) {
                                    ArrayList<Orientation> orientations = new ArrayList<>();
                                    orientations.add(Orientation.TOP);
                                    ArrayList<Integer> registers = new ArrayList<>();
                                    registers.add(1);
                                    registers.add(3);
                                    registers.add(5);
                                    PushPanel pushPanel = new PushPanel("2A", orientations, registers);
                                    deathTrap.get(x).get(y).addTile(pushPanel);
                                }
                            }

                            if (x == 4 && y == 5) {
                                if (k == 0) {
                                    ArrayList<Orientation> orientations = new ArrayList<>();
                                    orientations.add(Orientation.TOP);
                                    Wall wall = new Wall("2A", orientations);
                                    deathTrap.get(x).get(y).addTile(wall);
                                }
                                if (k == 1) {
                                    ArrayList<Orientation> orientations = new ArrayList<>();
                                    orientations.add(Orientation.BOTTOM);
                                    ArrayList<Integer> registers = new ArrayList<>();
                                    registers.add(2);
                                    registers.add(4);
                                    PushPanel pushPanel = new PushPanel("2A", orientations, registers);
                                    deathTrap.get(x).get(y).addTile(pushPanel);
                                }
                            }

                            if (x == 5 && y == 5) {
                                if (k == 0) {
                                    ArrayList<Orientation> orientations = new ArrayList<>();
                                    orientations.add(Orientation.BOTTOM);
                                    Wall wall = new Wall("2A", orientations);
                                    deathTrap.get(x).get(y).addTile(wall);
                                }
                            }

                            if (x == 6 && y == 5) {
                                if (k == 0) {
                                    Pit pit = new Pit("2A");
                                    deathTrap.get(x).get(y).addTile(pit);
                                }
                            }

                            if (x == 7 && y == 5) {
                                if (k == 0) {
                                    Empty empty = new Empty("2A");
                                    deathTrap.get(x).get(y).addTile(empty);
                                }
                            }

                            if (x == 8 && y == 5) {
                                if (k == 0) {
                                    ArrayList<Orientation> orientations = new ArrayList<>();
                                    orientations.add(Orientation.TOP);
                                    orientations.add(Orientation.BOTTOM);
                                    ConveyorBelt conveyorBelt = new ConveyorBelt("2A", 1, orientations);
                                    deathTrap.get(x).get(y).addTile(conveyorBelt);
                                }
                            }

                            if (x == 9 && y == 5) {
                                if (k == 0) {
                                    Empty empty = new Empty("2A");
                                    deathTrap.get(x).get(y).addTile(empty);
                                }
                            }

                            if (x == 10 && y == 5) {
                                if (k == 0) {
                                    ArrayList<Orientation> orientations = new ArrayList<>();
                                    orientations.add(Orientation.LEFT);
                                    Wall wall = new Wall("A", orientations);
                                    deathTrap.get(x).get(y).addTile(wall);
                                }
                            }

                            if (x == 11 && y == 5) {
                                if (k == 0) {
                                    StartPoint startPoint = new StartPoint("A");
                                    deathTrap.get(x).get(y).addTile(startPoint);
                                }
                            }

                            if (x == 12 && y == 5) {
                                if (k == 0) {
                                    ArrayList<Orientation> orientations = new ArrayList<>();
                                    orientations.add(Orientation.LEFT);
                                    Antenna antenna = new Antenna("A", orientations);
                                    deathTrap.get(x).get(y).addTile(antenna);
                                }
                            }

                            if (x == 0 && y == 6) {
                                if (k == 0) {
                                    ArrayList<Orientation> orientations = new ArrayList<>();
                                    orientations.add(Orientation.BOTTOM);
                                    orientations.add(Orientation.TOP);
                                    ConveyorBelt conveyorBelt = new ConveyorBelt("2A", 1, orientations);
                                    deathTrap.get(x).get(y).addTile(conveyorBelt);
                                }
                            }

                            if (x == 1 && y == 6) {
                                if (k == 0) {
                                    ArrayList<Orientation> orientations = new ArrayList<>();
                                    orientations.add(Orientation.RIGHT);
                                    Wall wall = new Wall("2A", orientations);
                                    deathTrap.get(x).get(y).addTile(wall);
                                }
                            }

                            if (x == 2 && y == 6) {
                                if (k == 0) {
                                    Pit pit = new Pit("2A");
                                    deathTrap.get(x).get(y).addTile(pit);
                                }
                            }

                            if (x == 3 && y == 6) {
                                if (k == 0) {
                                    Empty empty = new Empty("2A");
                                    deathTrap.get(x).get(y).addTile(empty);
                                }
                            }

                            if (x == 4 && y == 6) {
                                if (k == 0) {
                                    ArrayList<Orientation> orientations = new ArrayList<>();
                                    orientations.add(Orientation.TOP);
                                    EnergySpace energySpace = new EnergySpace("2A", orientations, 1);
                                    deathTrap.get(x).get(y).addTile(energySpace);
                                }
                            }

                            if (x == 5 && y == 6) {
                                if (k == 0) {
                                    Empty empty = new Empty("2A");
                                    deathTrap.get(x).get(y).addTile(empty);
                                }
                            }

                            if (x == 6 && y == 6) {
                                if (k == 0) {
                                    Empty empty = new Empty("2A");
                                    deathTrap.get(x).get(y).addTile(empty);
                                }
                            }

                            if (x == 7 && y == 6) {
                                if (k == 0) {
                                    ArrayList<Orientation> orientations = new ArrayList<>();
                                    orientations.add(Orientation.TOP);
                                    EnergySpace energySpace = new EnergySpace("2A", orientations, 1);
                                    deathTrap.get(x).get(y).addTile(energySpace);
                                }
                            }

                            if (x == 8 && y == 6) {
                                if (k == 0) {
                                    Empty empty = new Empty("2A");
                                    deathTrap.get(x).get(y).addTile(empty);
                                }
                            }

                            if (x == 9 && y == 6) {
                                if (k == 0) {
                                    Empty empty = new Empty("2A");
                                    deathTrap.get(x).get(y).addTile(empty);
                                }
                            }

                            if (x == 10 && y == 6) {
                                if (k == 0) {
                                    Empty empty = new Empty("A");
                                    deathTrap.get(x).get(y).addTile(empty);
                                }
                            }

                            if (x == 11 && y == 6) {
                                if (k == 0) {
                                    Empty empty = new Empty("A");
                                    deathTrap.get(x).get(y).addTile(empty);
                                }
                            }

                            if (x == 12 && y == 6) {
                                if (k == 1) {
                                    StartPoint startPoint = new StartPoint("A");
                                    deathTrap.get(x).get(y).addTile(startPoint);
                                }
                            }

                            if (x == 0 && y == 7) {
                                if (k == 0) {
                                    ArrayList<Orientation> orientations = new ArrayList<>();
                                    orientations.add(Orientation.BOTTOM);
                                    orientations.add(Orientation.TOP);
                                    ConveyorBelt conveyorBelt = new ConveyorBelt("2A", 1, orientations);
                                    deathTrap.get(x).get(y).addTile(conveyorBelt);
                                }
                            }

                            if (x == 1 && y == 7) {
                                if (k == 0) {
                                    ArrayList<Orientation> orientations = new ArrayList<>();
                                    orientations.add(Orientation.LEFT);
                                    CheckPoint checkPoint = new CheckPoint("2A", orientations, 1);
                                    deathTrap.get(x).get(y).addTile(checkPoint);
                                }
                            }

                            if (x == 2 && y == 7) {
                                if (k == 0) {
                                    ArrayList<Orientation> orientations = new ArrayList<>();
                                    orientations.add(Orientation.BOTTOM);
                                    Wall wall = new Wall("2A", orientations);
                                    deathTrap.get(x).get(y).addTile(wall);
                                }
                                if (k == 1) {
                                    ArrayList<Orientation> orientations = new ArrayList<>();
                                    orientations.add(Orientation.TOP);
                                    ArrayList<Integer> registers = new ArrayList<>();
                                    registers.add(2);
                                    registers.add(4);
                                    PushPanel pushPanel = new PushPanel("2A", orientations, registers);
                                    deathTrap.get(x).get(y).addTile(pushPanel);
                                }
                            }

                            if (x == 3 && y == 7) {
                                if (k == 0) {
                                    ArrayList<Orientation> orientations = new ArrayList<>();
                                    orientations.add(Orientation.LEFT);
                                    EnergySpace energySpace = new EnergySpace("2A", orientations, 1);
                                    deathTrap.get(x).get(y).addTile(energySpace);
                                }
                            }

                            if (x == 4 && y == 7) {
                                if (k == 0) {
                                    Empty empty = new Empty("2A");
                                    deathTrap.get(x).get(y).addTile(empty);
                                }
                            }

                            if (x == 5 && y == 7) {
                                if (k == 0) {
                                    Empty empty = new Empty("2A");
                                    deathTrap.get(x).get(y).addTile(empty);
                                }
                            }

                            if (x == 6 && y == 7) {
                                if (k == 0) {
                                    Pit pit = new Pit("2A");
                                    deathTrap.get(x).get(y).addTile(pit);
                                }
                            }

                            if (x == 7 && y == 7) {
                                if (k == 0) {
                                    ArrayList<Orientation> orientations = new ArrayList<>();
                                    orientations.add(Orientation.RIGHT);
                                    Wall wall = new Wall("2A", orientations);
                                    deathTrap.get(x).get(y).addTile(wall);
                                }
                                if (k == 1) {
                                    ArrayList<Orientation> orientations = new ArrayList<>();
                                    orientations.add(Orientation.LEFT);
                                    ArrayList<Integer> registers = new ArrayList<>();
                                    registers.add(2);
                                    registers.add(4);
                                    PushPanel pushPanel = new PushPanel("2A", orientations, registers);
                                    deathTrap.get(x).get(y).addTile(pushPanel);
                                }
                            }

                            if (x == 8 && y == 7) {
                                if (k == 0) {
                                    Pit pit = new Pit("2A");
                                    deathTrap.get(x).get(y).addTile(pit);
                                }
                            }

                            if (x == 9 && y == 7) {
                                if (k == 0) {
                                    Empty empty = new Empty("2A");
                                    deathTrap.get(x).get(y).addTile(empty);
                                }
                            }

                            if (x == 10 && y == 7) {
                                if (k == 0) {
                                    Empty empty = new Empty("A");
                                    deathTrap.get(x).get(y).addTile(empty);
                                }
                            }

                            if (x == 11 && y == 7) {
                                if (k == 0) {
                                    ArrayList<Orientation> orientations = new ArrayList<>();
                                    orientations.add(Orientation.BOTTOM);
                                    Wall wall = new Wall("A", orientations);
                                    deathTrap.get(x).get(y).addTile(wall);
                                }
                            }

                            if (x == 12 && y == 7) {
                                if (k == 0) {
                                    Empty empty = new Empty("A");
                                    deathTrap.get(x).get(y).addTile(empty);
                                }
                            }

                            if (x == 0 && y == 8) {
                                if (k == 0) {
                                    ArrayList<Orientation> orientations = new ArrayList<>();
                                    orientations.add(Orientation.BOTTOM);
                                    orientations.add(Orientation.TOP);
                                    ConveyorBelt conveyorBelt = new ConveyorBelt("2A", 1, orientations);
                                    deathTrap.get(x).get(y).addTile(conveyorBelt);
                                }
                            }

                            if (x == 1 && y == 8) {
                                if (k == 0) {
                                    ArrayList<Orientation> orientations = new ArrayList<>();
                                    orientations.add(Orientation.LEFT);
                                    Wall wall = new Wall("2A", orientations);
                                    deathTrap.get(x).get(y).addTile(wall);
                                }
                                if (k == 1) {
                                    ArrayList<Orientation> orientations = new ArrayList<>();
                                    orientations.add(Orientation.RIGHT);
                                    ArrayList<Integer> registers = new ArrayList<>();
                                    registers.add(1);
                                    registers.add(3);
                                    registers.add(5);
                                    PushPanel pushPanel = new PushPanel("2A", orientations, registers);
                                    deathTrap.get(x).get(y).addTile(pushPanel);
                                }
                            }

                            if (x == 2 && y == 8) {
                                if (k == 0) {
                                    Pit pit = new Pit("2A");
                                    deathTrap.get(x).get(y).addTile(pit);
                                }
                            }

                            if (x == 3 && y == 8) {
                                if (k == 0) {
                                    Empty empty = new Empty("2A");
                                    deathTrap.get(x).get(y).addTile(empty);
                                }
                            }

                            if (x == 4 && y == 8) {
                                if (k == 0) {
                                    ArrayList<Orientation> orientations = new ArrayList<>();
                                    orientations.add(Orientation.RIGHT);
                                    orientations.add(Orientation.LEFT);
                                    ConveyorBelt conveyorBelt = new ConveyorBelt("2A", 1, orientations);
                                    deathTrap.get(x).get(y).addTile(conveyorBelt);
                                }
                            }

                            if (x == 5 && y == 8) {
                                if (k == 0) {
                                    ArrayList<Orientation> orientations = new ArrayList<>();
                                    orientations.add(Orientation.BOTTOM);
                                    orientations.add(Orientation.LEFT);
                                    ConveyorBelt conveyorBelt = new ConveyorBelt("2A", 1, orientations);
                                    deathTrap.get(x).get(y).addTile(conveyorBelt);
                                }
                            }

                            if (x == 6 && y == 8) {
                                if (k == 0) {
                                    ArrayList<Orientation> orientations = new ArrayList<>();
                                    orientations.add(Orientation.TOP);
                                    Wall wall = new Wall("2A", orientations);
                                    deathTrap.get(x).get(y).addTile(wall);
                                }

                            }

                            if (x == 7 && y == 8) {
                                if (k == 0) {
                                    ArrayList<Orientation> orientations = new ArrayList<>();
                                    orientations.add(Orientation.LEFT);
                                    CheckPoint checkPoint = new CheckPoint("2A", orientations, 3);
                                    deathTrap.get(x).get(y).addTile(checkPoint);
                                }
                            }

                            if (x == 8 && y == 8) {
                                if (k == 0) {
                                    ArrayList<Orientation> orientations = new ArrayList<>();
                                    orientations.add(Orientation.BOTTOM);
                                    Wall wall = new Wall("2A", orientations);
                                    deathTrap.get(x).get(y).addTile(wall);
                                }
                                if (k == 1) {
                                    ArrayList<Orientation> orientations = new ArrayList<>();
                                    orientations.add(Orientation.TOP);
                                    ArrayList<Integer> registers = new ArrayList<>();
                                    registers.add(1);
                                    registers.add(3);
                                    registers.add(5);
                                    PushPanel pushPanel = new PushPanel("2A", orientations, registers);
                                    deathTrap.get(x).get(y).addTile(pushPanel);
                                }
                            }

                            if (x == 9 && y == 8) {
                                if (k == 0) {
                                    Empty empty = new Empty("2A");
                                    deathTrap.get(x).get(y).addTile(empty);
                                }
                            }

                            if (x == 10 && y == 8) {
                                if (k == 0) {
                                    Empty empty = new Empty("A");
                                    deathTrap.get(x).get(y).addTile(empty);
                                }
                            }

                            if (x == 11 && y == 8) {
                                if (k == 0) {
                                    StartPoint startPoint = new StartPoint("A");
                                    deathTrap.get(x).get(y).addTile(startPoint);
                                }
                            }

                            if (x == 12 && y == 8) {
                                if (k == 0) {
                                    Empty empty = new Empty("A");
                                    deathTrap.get(x).get(y).addTile(empty);
                                }
                            }

                            if (x == 0 && y == 9) {
                                if (k == 0) {
                                    Empty empty = new Empty("2A");
                                    deathTrap.get(x).get(y).addTile(empty);
                                }
                            }
                            if (x == 1 && y == 9) {
                                if (k == 0) {
                                    Empty empty = new Empty("2A");
                                    deathTrap.get(x).get(y).addTile(empty);
                                }
                            }

                            if (x == 2 && y == 9) {
                                if (k == 0) {
                                    Empty empty = new Empty("2A");
                                    deathTrap.get(x).get(y).addTile(empty);
                                }
                            }

                            if (x == 3 && y == 9) {
                                if (k == 0) {
                                    Empty empty = new Empty("2A");
                                    deathTrap.get(x).get(y).addTile(empty);
                                }
                            }

                            if (x == 4 && y == 9) {
                                if (k == 0) {
                                    Empty empty = new Empty("2A");
                                    deathTrap.get(x).get(y).addTile(empty);
                                }
                            }

                            if (x == 5 && y == 9) {
                                if (k == 0) {
                                    ArrayList<Orientation> orientations = new ArrayList<>();
                                    orientations.add(Orientation.RIGHT);
                                    orientations.add(Orientation.TOP);
                                    ConveyorBelt conveyorBelt = new ConveyorBelt("2A", 1, orientations);
                                    deathTrap.get(x).get(y).addTile(conveyorBelt);
                                }
                            }

                            if (x == 6 && y == 9) {
                                if (k == 0) {
                                    ArrayList<Orientation> orientations = new ArrayList<>();
                                    orientations.add(Orientation.RIGHT);
                                    orientations.add(Orientation.LEFT);
                                    ConveyorBelt conveyorBelt = new ConveyorBelt("2A", 1, orientations);
                                    deathTrap.get(x).get(y).addTile(conveyorBelt);
                                }
                            }

                            if (x == 7 && y == 9) {
                                if (k == 0) {
                                    ArrayList<Orientation> orientations = new ArrayList<>();
                                    orientations.add(Orientation.RIGHT);
                                    orientations.add(Orientation.LEFT);
                                    ConveyorBelt conveyorBelt = new ConveyorBelt("2A", 1, orientations);
                                    deathTrap.get(x).get(y).addTile(conveyorBelt);
                                }
                            }

                            if (x == 8 && y == 9) {
                                if (k == 0) {
                                    ArrayList<Orientation> orientations = new ArrayList<>();
                                    orientations.add(Orientation.RIGHT);
                                    orientations.add(Orientation.LEFT);
                                    ConveyorBelt conveyorBelt = new ConveyorBelt("2A", 1, orientations);
                                    deathTrap.get(x).get(y).addTile(conveyorBelt);
                                }
                            }

                            if (x == 9 && y == 9) {
                                if (k == 0) {
                                    Empty empty = new Empty("2A");
                                    deathTrap.get(x).get(y).addTile(empty);
                                }
                            }

                            if (x == 10 && y == 9) {
                                if (k == 0) {
                                    ArrayList<Orientation> orientations = new ArrayList<>();
                                    orientations.add(Orientation.LEFT);
                                    orientations.add(Orientation.RIGHT);
                                    ConveyorBelt conveyorBelt = new ConveyorBelt("A", 1, orientations);
                                    deathTrap.get(x).get(y).addTile(conveyorBelt);
                                }
                            }

                            if (x == 11 && y == 9) {
                                if (k == 0) {
                                    Empty empty = new Empty("A");
                                    deathTrap.get(x).get(y).addTile(empty);
                                }
                            }

                            if (x == 12 && y == 9) {
                                if (k == 0) {
                                    ArrayList<Orientation> orientations = new ArrayList<>();
                                    orientations.add(Orientation.LEFT);
                                    RestartPoint restartPoint = new RestartPoint("A", orientations);
                                    deathTrap.get(x).get(y).addTile(restartPoint);
                                }
                            }
                        }
                    }
                }
                return new GameStarted(new Board(deathTrap));
            }
        }
     
  