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
                        Floor floor = new Floor();
                        Position positionFloor = new Position(i,j);
                        floor.setFloorPosition(positionFloor);
                        deathTrap.get(i).get(j).add(floor);
                    }

                    if(i == 2 && j == 0){
                        if(k == 1){
                            ConveyorBelt conveyorBelt = new ConveyorBelt();
                            Position positionBelt = new Position(2,0);
                            conveyorBelt.setBeltPosition(positionBelt);
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.LEFT);
                            conveyorBelt.setBeltOrientation(orientations);
                            conveyorBelt.setActivationOrder(2);
                            conveyorBelt.setSpeed(1);
                            deathTrap.get(i).get(j).add(conveyorBelt);
                        }
                    }

                    if(i == 3 && j == 0){
                        if(k == 1){
                            ConveyorBelt conveyorBelt = new ConveyorBelt();
                            Position positionBelt = new Position(3,0);
                            conveyorBelt.setBeltPosition(positionBelt);
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.LEFT);
                            conveyorBelt.setBeltOrientation(orientations);
                            conveyorBelt.setActivationOrder(2);
                            conveyorBelt.setSpeed(1);
                            deathTrap.get(i).get(j).add(conveyorBelt);
                        }
                    }

                    if(i == 4 && j == 0){
                        if(k == 1){
                            ConveyorBelt conveyorBelt = new ConveyorBelt();
                            Position positionBelt = new Position(4,0);
                            conveyorBelt.setBeltPosition(positionBelt);
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.TOP_LEFT);
                            conveyorBelt.setBeltOrientation(orientations);
                            conveyorBelt.setActivationOrder(2);
                            conveyorBelt.setSpeed(1);
                            deathTrap.get(i).get(j).add(conveyorBelt);
                        }
                    }

                    if(i == 10 && j == 0){
                        if(k == 1){
                            ConveyorBelt conveyorBelt = new ConveyorBelt();
                            Position positionBelt = new Position(10,0);
                            conveyorBelt.setBeltPosition(positionBelt);
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.LEFT);
                            conveyorBelt.setBeltOrientation(orientations);
                            conveyorBelt.setActivationOrder(2);
                            conveyorBelt.setSpeed(1);
                            deathTrap.get(i).get(j).add(conveyorBelt);
                        }
                    }

                    if(i == 0 && j == 1){
                        if(k == 1){
                            CheckPoint checkPoint = new CheckPoint();
                            Position positionCheckPoint = new Position(0,1);
                            checkPoint.setPosition(positionCheckPoint);
                            checkPoint.setNumber(5);
                            checkPoint.setActivationOrder(8);
                            deathTrap.get(i).get(j).add(checkPoint);
                        }
                    }

                    if (i == 1 && j == 1){
                        if (k == 1){
                            Wall wall = new Wall();
                            Position positionWall = new Position(1,1);
                            wall.setWallPosition(positionWall);
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.TOP);
                            wall.setWallOrientation(orientations);
                            deathTrap.get(i).get(j).add(wall);
                        }
                        if (k == 2){
                            PushPanel pushPanel = new PushPanel();
                            Position positionPushPanel = new Position(1,1);
                            pushPanel.setPosition(positionPushPanel);
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.TOP);
                            pushPanel.setOrientations(orientations);
                            deathTrap.get(i).get(j).add(pushPanel);
                        }
                    }

                    if (i == 3 && j == 1){
                        if (k == 1){
                            Wall wall = new Wall();
                            Position positionWall = new Position(3,1);
                            wall.setWallPosition(positionWall);
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.BOTTOM);
                            wall.setWallOrientation(orientations);
                            deathTrap.get(i).get(j).add(wall);
                        }
                    }

                    if (i == 4 && j == 1){
                        if (k == 1){
                            ConveyorBelt conveyorBelt = new ConveyorBelt();
                            Position positionBelt = new Position(4,1);
                            conveyorBelt.setBeltPosition(positionBelt);
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.LEFT_TOP);
                            conveyorBelt.setBeltOrientation(orientations);
                            conveyorBelt.setActivationOrder(2);
                            conveyorBelt.setSpeed(1);
                            deathTrap.get(i).get(j).add(conveyorBelt);
                        }
                    }

                    if (i == 5 && j == 1){
                        if (k == 1){
                            ConveyorBelt conveyorBelt = new ConveyorBelt();
                            Position positionBelt = new Position(5,1);
                            conveyorBelt.setBeltPosition(positionBelt);
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.LEFT);
                            conveyorBelt.setBeltOrientation(orientations);
                            conveyorBelt.setActivationOrder(2);
                            conveyorBelt.setSpeed(1);
                            deathTrap.get(i).get(j).add(conveyorBelt);
                        }
                    }

                    if (i == 7 && j == 1){
                        if (k == 1){
                            BlackHole blackHole = new BlackHole();
                            Position positionBlackHole = new Position(7,1);
                            blackHole.setBlackHolePosition(positionBlackHole);
                            deathTrap.get(i).get(j).add(blackHole);
                        }
                    }

                    if (i == 8 && j == 1){
                        if (k == 1){
                            Wall wall = new Wall();
                            Position positionWall = new Position(8,1);
                            wall.setWallPosition(positionWall);
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.RIGHT);
                            wall.setWallOrientation(orientations);
                            deathTrap.get(i).get(j).add(wall);
                        }
                        if (k == 2){
                            PushPanel pushPanel = new PushPanel();
                            Position positionPushPanel = new Position(8,1);
                            pushPanel.setPosition(positionPushPanel);
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.RIGHT);
                            pushPanel.setOrientations(orientations);
                            deathTrap.get(i).get(j).add(pushPanel);
                        }
                    }

                    if (i == 9 && j == 1){
                        if (k == 1){
                            ConveyorBelt conveyorBelt = new ConveyorBelt();
                            Position positionBelt = new Position(9,1);
                            conveyorBelt.setBeltPosition(positionBelt);
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.TOP);
                            conveyorBelt.setBeltOrientation(orientations);
                            conveyorBelt.setActivationOrder(2);
                            conveyorBelt.setSpeed(1);
                            deathTrap.get(i).get(j).add(conveyorBelt);
                        }
                    }

                    if(i == 11 && j == 1){
                        if(k == 1){
                            StartPoint startPoint = new StartPoint();
                            Position positionStartPoint = new Position(11,1);
                            startPoint.setStartingPoint(positionStartPoint);
                            deathTrap.get(i).get(j).add(startPoint);
                        }
                    }

                    if (i == 1 && j == 2){
                        if (k == 1){
                            BlackHole blackHole = new BlackHole();
                            Position positionBlackHole = new Position(1,2);
                            blackHole.setBlackHolePosition(positionBlackHole);
                            deathTrap.get(i).get(j).add(blackHole);
                        }
                    }

                    if (i == 2 && j == 2){
                        if (k == 1){
                            Wall wall = new Wall();
                            Position positionWall = new Position(2,2);
                            wall.setWallPosition(positionWall);
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.LEFT);
                            wall.setWallOrientation(orientations);
                            deathTrap.get(i).get(j).add(wall);
                        }
                        if (k == 2){
                            PushPanel pushPanel = new PushPanel();
                            Position positionPushPanel = new Position(2,2);
                            pushPanel.setPosition(positionPushPanel);
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.LEFT);
                            pushPanel.setOrientations(orientations);
                            deathTrap.get(i).get(j).add(pushPanel);
                        }
                    }

                    if (i == 3 && j == 2){
                        if (k == 1){
                            BlackHole blackHole = new BlackHole();
                            Position positionBlackHole = new Position(3,2);
                            blackHole.setBlackHolePosition(positionBlackHole);
                            deathTrap.get(i).get(j).add(blackHole);
                        }
                    }

                    if(i == 4 && j == 2){
                        if(k == 1){
                            EnergySpace energySpace = new EnergySpace();
                            Position positionEnergySpace = new Position(4,2);
                            energySpace.setEnergySpace(positionEnergySpace);
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.VERTICAL);//richtig so?
                            energySpace.setOrientations(orientations);
                            energySpace.setActivationOrder(7);
                            deathTrap.get(i).get(j).add(energySpace);
                        }
                    }

                    //Energyspace ist hier horizontal nicht vertikal
                    if(i == 6 && j == 2){
                        if(k == 1){
                            EnergySpace energySpace = new EnergySpace();
                            Position positionEnergySpace = new Position(6,2);
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.HORIZONTAL);
                            energySpace.setOrientations(orientations);
                            energySpace.setActivationOrder(7);
                            deathTrap.get(i).get(j).add(energySpace);
                        }
                    }

                    if (i == 7 && j == 2){
                        if (k == 1){
                            Wall wall = new Wall();
                            Position positionWall = new Position(7,2);
                            wall.setWallPosition(positionWall);
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.TOP);
                            wall.setWallOrientation(orientations);
                            deathTrap.get(i).get(j).add(wall);
                        }
                        if (k == 2){
                            PushPanel pushPanel = new PushPanel();
                            Position positionPushPanel = new Position(7,2);
                            pushPanel.setPosition(positionPushPanel);
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.TOP);
                            pushPanel.setOrientations(orientations);
                            deathTrap.get(i).get(j).add(pushPanel);
                        }
                    }

                    if(i == 8 && j == 2){
                        if(k == 1){
                            CheckPoint checkPoint = new CheckPoint();
                            Position positionCheckPoint = new Position(8,2);
                            checkPoint.setPosition(positionCheckPoint);
                            checkPoint.setNumber(4);
                            checkPoint.setActivationOrder(8);
                            deathTrap.get(i).get(j).add(checkPoint);
                        }
                    }

                    if (i == 9 && j == 2){
                        if (k == 1){
                            ConveyorBelt conveyorBelt = new ConveyorBelt();
                            Position positionBelt = new Position(9,2);
                            conveyorBelt.setBeltPosition(positionBelt);
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.TOP);
                            conveyorBelt.setBeltOrientation(orientations);
                            conveyorBelt.setActivationOrder(2);
                            conveyorBelt.setSpeed(1);
                            deathTrap.get(i).get(j).add(conveyorBelt);
                        }
                    }

                    if (i == 11 && j == 2){
                        if (k == 1){
                            Wall wall = new Wall();
                            Position positionWall = new Position(11,2);
                            wall.setWallPosition(positionWall);
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.TOP);
                            wall.setWallOrientation(orientations);
                            deathTrap.get(i).get(j).add(wall);
                        }
                    }

                    if(i == 2 && j == 3){
                        if(k == 1){
                            EnergySpace energySpace = new EnergySpace();
                            Position positionEnergySpace = new Position(2,3);
                            energySpace.setEnergySpace(positionEnergySpace);
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.VERTICAL);
                            energySpace.setOrientations(orientations);
                            energySpace.setActivationOrder(7);
                            deathTrap.get(i).get(j).add(energySpace);
                        }
                    }

                    if (i == 4 && j == 3){
                        if (k == 1){
                            Wall wall = new Wall();
                            Position positionWall = new Position(4,3);
                            wall.setWallPosition(positionWall);
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.BOTTOM);
                            wall.setWallOrientation(orientations);
                            deathTrap.get(i).get(j).add(wall);
                        }
                        if (k == 2){
                            PushPanel pushPanel = new PushPanel();
                            Position positionPushPanel = new Position(4,3);
                            pushPanel.setPosition(positionPushPanel);
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.BOTTOM);
                            pushPanel.setOrientations(orientations);
                            deathTrap.get(i).get(j).add(pushPanel);
                        }
                    }

                    if (i == 7 && j == 3){
                        if (k == 1){
                            BlackHole blackHole = new BlackHole();
                            Position positionBlackHole = new Position(7,3);
                            blackHole.setBlackHolePosition(positionBlackHole);
                            deathTrap.get(i).get(j).add(blackHole);
                        }
                    }

                    if (i == 8 && j == 3){
                        if (k == 1){
                            Wall wall = new Wall();
                            Position positionWall = new Position(8,3);
                            wall.setWallPosition(positionWall);
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.LEFT);
                            wall.setWallOrientation(orientations);
                            deathTrap.get(i).get(j).add(wall);
                        }
                    }

                    if (i == 9 && j == 3){
                        if (k == 1){
                            ConveyorBelt conveyorBelt = new ConveyorBelt();
                            Position positionBelt = new Position(9,3);
                            conveyorBelt.setBeltPosition(positionBelt);
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.TOP);
                            conveyorBelt.setBeltOrientation(orientations);
                            conveyorBelt.setActivationOrder(2);
                            conveyorBelt.setSpeed(1);
                            deathTrap.get(i).get(j).add(conveyorBelt);
                        }
                    }

                    if(i == 12 && j == 3){
                        if(k == 1){
                            StartPoint startPoint = new StartPoint();
                            Position positionStartPoint = new Position(12,3);
                            startPoint.setStartingPoint(positionStartPoint);
                            deathTrap.get(i).get(j).add(startPoint);
                        }
                    }

                    if (i == 1 && j == 4){
                        if (k == 1){
                            ConveyorBelt conveyorBelt = new ConveyorBelt();
                            Position positionBelt = new Position(1,4);
                            conveyorBelt.setBeltPosition(positionBelt);
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.BOTTOM);
                            conveyorBelt.setBeltOrientation(orientations);
                            conveyorBelt.setActivationOrder(2);
                            conveyorBelt.setSpeed(1);
                            deathTrap.get(i).get(j).add(conveyorBelt);
                        }
                    }

                    if (i == 3 && j == 4){
                        if (k == 1){
                            BlackHole blackHole = new BlackHole();
                            Position positionBlackHole = new Position(3,4);
                            blackHole.setBlackHolePosition(positionBlackHole);
                            deathTrap.get(i).get(j).add(blackHole);
                        }
                    }

                    if(i == 4 && j == 4){
                        if(k == 1){
                            CheckPoint checkPoint = new CheckPoint();
                            Position positionCheckPoint = new Position(4,4);
                            checkPoint.setPosition(positionCheckPoint);
                            checkPoint.setNumber(2);
                            checkPoint.setActivationOrder(8);
                            deathTrap.get(i).get(j).add(checkPoint);
                        }
                    }

                    if (i == 6 && j == 4){
                        if (k == 1){
                            Wall wall = new Wall();
                            Position positionWall = new Position(6,4);
                            wall.setWallPosition(positionWall);
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.TOP);
                            wall.setWallOrientation(orientations);
                            deathTrap.get(i).get(j).add(wall);
                        }
                        if (k == 2){
                            PushPanel pushPanel = new PushPanel();
                            Position positionPushPanel = new Position(4,3);
                            pushPanel.setPosition(positionPushPanel);
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.TOP);
                            pushPanel.setOrientations(orientations);
                            deathTrap.get(i).get(j).add(pushPanel);
                        }
                    }

                    if (i == 8 && j == 4){
                        if (k == 1){
                            ConveyorBelt conveyorBelt = new ConveyorBelt();
                            Position positionBelt = new Position(8,4);
                            conveyorBelt.setBeltPosition(positionBelt);
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.TOP_RIGHT);
                            conveyorBelt.setBeltOrientation(orientations);
                            conveyorBelt.setActivationOrder(2);
                            conveyorBelt.setSpeed(1);
                            deathTrap.get(i).get(j).add(conveyorBelt);
                        }
                    }

                    if (i == 9 && j == 4){
                        if (k == 1){
                            ConveyorBelt conveyorBelt = new ConveyorBelt();
                            Position positionBelt = new Position(9,4);
                            conveyorBelt.setBeltPosition(positionBelt);
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.RIGHT_TOP);
                            conveyorBelt.setBeltOrientation(orientations);
                            conveyorBelt.setActivationOrder(2);
                            conveyorBelt.setSpeed(1);
                            deathTrap.get(i).get(j).add(conveyorBelt);
                        }
                    }

                    if (i == 10 && j == 4){
                        if (k == 1){
                            Wall wall = new Wall();
                            Position positionWall = new Position(10,4);
                            wall.setWallPosition(positionWall);
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.LEFT);
                            wall.setWallOrientation(orientations);
                            deathTrap.get(i).get(j).add(wall);
                        }
                    }

                    if(i == 11 && j == 4){
                        if(k == 1){
                            StartPoint startPoint = new StartPoint();
                            Position positionStartPoint = new Position(11,4);
                            startPoint.setStartingPoint(positionStartPoint);
                            deathTrap.get(i).get(j).add(startPoint);
                        }
                    }

                    if (i == 0 && j == 5){
                        if (k == 1){
                            ConveyorBelt conveyorBelt = new ConveyorBelt();
                            Position positionBelt = new Position(0,5);
                            conveyorBelt.setBeltPosition(positionBelt);
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.LEFT_BOTTOM);
                            conveyorBelt.setBeltOrientation(orientations);
                            conveyorBelt.setActivationOrder(2);
                            conveyorBelt.setSpeed(1);
                            deathTrap.get(i).get(j).add(conveyorBelt);
                        }
                    }

                    if (i == 1 && j == 5){
                        if (k == 1){
                            ConveyorBelt conveyorBelt = new ConveyorBelt();
                            Position positionBelt = new Position(1,5);
                            conveyorBelt.setBeltPosition(positionBelt);
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.BOTTOM_LEFT);
                            conveyorBelt.setBeltOrientation(orientations);
                            conveyorBelt.setActivationOrder(2);
                            conveyorBelt.setSpeed(1);
                            deathTrap.get(i).get(j).add(conveyorBelt);
                        }
                    }

                    if (i == 3 && j == 5){
                        if (k == 1){
                            Wall wall = new Wall();
                            Position positionWall = new Position(3,5);
                            wall.setWallPosition(positionWall);
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.BOTTOM);
                            wall.setWallOrientation(orientations);
                            deathTrap.get(i).get(j).add(wall);
                        }
                        if (k == 2){
                            PushPanel pushPanel = new PushPanel();
                            Position positionPushPanel = new Position(3,5);
                            pushPanel.setPosition(positionPushPanel);
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.BOTTOM);
                            pushPanel.setOrientations(orientations);
                            deathTrap.get(i).get(j).add(pushPanel);
                        }
                    }

                    if (i == 4 && j == 5){
                        if (k == 1){
                            Wall wall = new Wall();
                            Position positionWall = new Position(4,5);
                            wall.setWallPosition(positionWall);
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.TOP);
                            wall.setWallOrientation(orientations);
                            deathTrap.get(i).get(j).add(wall);
                        }
                        if (k == 2){
                            PushPanel pushPanel = new PushPanel();
                            Position positionPushPanel = new Position(4,5);
                            pushPanel.setPosition(positionPushPanel);
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.TOP);
                            pushPanel.setOrientations(orientations);
                            deathTrap.get(i).get(j).add(pushPanel);
                        }
                    }

                    if (i == 5 && j == 5){
                        if (k == 1){
                            Wall wall = new Wall();
                            Position positionWall = new Position(5,5);
                            wall.setWallPosition(positionWall);
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.BOTTOM);
                            wall.setWallOrientation(orientations);
                            deathTrap.get(i).get(j).add(wall);
                        }
                    }

                    if (i == 6 && j == 5){
                        if (k == 1){
                            BlackHole blackHole = new BlackHole();
                            Position positionBlackHole = new Position(6,5);
                            blackHole.setBlackHolePosition(positionBlackHole);
                            deathTrap.get(i).get(j).add(blackHole);
                        }
                    }

                    if (i == 8 && j == 5){
                        if (k == 1){
                            ConveyorBelt conveyorBelt = new ConveyorBelt();
                            Position positionBelt = new Position(8,5);
                            conveyorBelt.setBeltPosition(positionBelt);
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.TOP);
                            conveyorBelt.setBeltOrientation(orientations);
                            conveyorBelt.setActivationOrder(2);
                            conveyorBelt.setSpeed(1);
                            deathTrap.get(i).get(j).add(conveyorBelt);
                        }
                    }

                    if (i == 10 && j == 5){
                        if (k == 1){
                            Wall wall = new Wall();
                            Position positionWall = new Position(10,5);
                            wall.setWallPosition(positionWall);
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.LEFT);
                            wall.setWallOrientation(orientations);
                            deathTrap.get(i).get(j).add(wall);
                        }
                    }

                    if(i == 11 && j == 5){
                        if(k == 1){
                            StartPoint startPoint = new StartPoint();
                            Position positionStartPoint = new Position(11,5);
                            startPoint.setStartingPoint(positionStartPoint);
                            deathTrap.get(i).get(j).add(startPoint);
                        }
                    }

                    if(i == 12 && j == 5){
                        if(k == 1){
                            Antenna antenna = new Antenna();
                            Position positionAntenna = new Position(12,5);
                            antenna.setAntennaPosition(positionAntenna);
                            deathTrap.get(i).get(j).add(antenna);
                        }
                    }

                    if (i == 0 && j == 6){
                        if (k == 1){
                            ConveyorBelt conveyorBelt = new ConveyorBelt();
                            Position positionBelt = new Position(0,6);
                            conveyorBelt.setBeltPosition(positionBelt);
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.BOTTOM);
                            conveyorBelt.setBeltOrientation(orientations);
                            conveyorBelt.setActivationOrder(2);
                            conveyorBelt.setSpeed(1);
                            deathTrap.get(i).get(j).add(conveyorBelt);
                        }
                    }

                    if (i == 1 && j == 6){
                        if (k == 1){
                            Wall wall = new Wall();
                            Position positionWall = new Position(1,6);
                            wall.setWallPosition(positionWall);
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.RIGHT);
                            wall.setWallOrientation(orientations);
                            deathTrap.get(i).get(j).add(wall);
                        }
                    }

                    if (i == 2 && j == 6){
                        if (k == 1){
                            BlackHole blackHole = new BlackHole();
                            Position positionBlackHole = new Position(2,6);
                            blackHole.setBlackHolePosition(positionBlackHole);
                            deathTrap.get(i).get(j).add(blackHole);
                        }
                    }

                    if(i == 4 && j == 6){
                        if(k == 1){
                            EnergySpace energySpace = new EnergySpace();
                            Position positionEnergySpace = new Position(4,6);
                            energySpace.setEnergySpace(positionEnergySpace);
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.VERTICAL);
                            energySpace.setOrientations(orientations);
                            energySpace.setActivationOrder(7);
                            deathTrap.get(i).get(j).add(energySpace);
                        }
                    }

                    if(i == 7 && j == 6){
                        if(k == 1){
                            EnergySpace energySpace = new EnergySpace();
                            Position positionEnergySpace = new Position(7,6);
                            energySpace.setEnergySpace(positionEnergySpace);
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.VERTICAL);
                            energySpace.setOrientations(orientations);
                            energySpace.setActivationOrder(7);
                            deathTrap.get(i).get(j).add(energySpace);
                        }
                    }

                    if(i == 12 && j == 6){
                        if(k == 1){
                            StartPoint startPoint = new StartPoint();
                            Position positionStartPoint = new Position(12,6);
                            startPoint.setStartingPoint(positionStartPoint);
                            deathTrap.get(i).get(j).add(startPoint);
                        }
                    }

                    if (i == 0 && j == 7){
                        if (k == 1){
                            ConveyorBelt conveyorBelt = new ConveyorBelt();
                            Position positionBelt = new Position(0,7);
                            conveyorBelt.setBeltPosition(positionBelt);
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.BOTTOM);
                            conveyorBelt.setBeltOrientation(orientations);
                            conveyorBelt.setActivationOrder(2);
                            conveyorBelt.setSpeed(1);
                            deathTrap.get(i).get(j).add(conveyorBelt);
                        }
                    }

                    if(i == 1 && j == 7){
                        if(k == 1){
                            CheckPoint checkPoint = new CheckPoint();
                            Position positionCheckPoint = new Position(1,7);
                            checkPoint.setPosition(positionCheckPoint);
                            checkPoint.setNumber(1);
                            checkPoint.setActivationOrder(8);
                            deathTrap.get(i).get(j).add(checkPoint);
                        }
                    }

                    if (i == 2 && j == 7){
                        if (k == 1){
                            Wall wall = new Wall();
                            Position positionWall = new Position(2,7);
                            wall.setWallPosition(positionWall);
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.BOTTOM);
                            wall.setWallOrientation(orientations);
                            deathTrap.get(i).get(j).add(wall);
                        }
                        if (k == 2){
                            PushPanel pushPanel = new PushPanel();
                            Position positionPushPanel = new Position(2,7);
                            pushPanel.setPosition(positionPushPanel);
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.BOTTOM);
                            pushPanel.setOrientations(orientations);
                            deathTrap.get(i).get(j).add(pushPanel);
                        }
                    }

                    if(i == 3 && j == 7){
                        if(k == 1){
                            EnergySpace energySpace = new EnergySpace();
                            Position positionEnergySpace = new Position(3,7);
                            energySpace.setEnergySpace(positionEnergySpace);
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.HORIZONTAL);
                            energySpace.setOrientations(orientations);
                            energySpace.setActivationOrder(7);
                            deathTrap.get(i).get(j).add(energySpace);
                        }
                    }

                    if (i == 6 && j == 7){
                        if (k == 1){
                            BlackHole blackHole = new BlackHole();
                            Position positionBlackHole = new Position(6,7);
                            blackHole.setBlackHolePosition(positionBlackHole);
                            deathTrap.get(i).get(j).add(blackHole);
                        }
                    }

                    if (i == 7 && j == 7){
                        if (k == 1){
                            Wall wall = new Wall();
                            Position positionWall = new Position(7,7);
                            wall.setWallPosition(positionWall);
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.RIGHT);
                            wall.setWallOrientation(orientations);
                            deathTrap.get(i).get(j).add(wall);
                        }
                        if (k == 2){
                            PushPanel pushPanel = new PushPanel();
                            Position positionPushPanel = new Position(7,7);
                            pushPanel.setPosition(positionPushPanel);
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.RIGHT);
                            pushPanel.setOrientations(orientations);
                            deathTrap.get(i).get(j).add(pushPanel);
                        }
                    }

                    if (i == 8 && j == 7){
                        if (k == 1){
                            BlackHole blackHole = new BlackHole();
                            Position positionBlackHole = new Position(8,7);
                            blackHole.setBlackHolePosition(positionBlackHole);
                            deathTrap.get(i).get(j).add(blackHole);
                        }
                    }

                    if (i == 11 && j == 7){
                        if (k == 1){
                            Wall wall = new Wall();
                            Position positionWall = new Position(11,7);
                            wall.setWallPosition(positionWall);
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.BOTTOM);
                            wall.setWallOrientation(orientations);
                            deathTrap.get(i).get(j).add(wall);
                        }
                    }

                    if (i == 0 && j == 8){
                        if (k == 1){
                            ConveyorBelt conveyorBelt = new ConveyorBelt();
                            Position positionBelt = new Position(0,8);
                            conveyorBelt.setBeltPosition(positionBelt);
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.BOTTOM);
                            conveyorBelt.setBeltOrientation(orientations);
                            conveyorBelt.setActivationOrder(2);
                            conveyorBelt.setSpeed(1);
                            deathTrap.get(i).get(j).add(conveyorBelt);
                        }
                    }

                    if (i == 1 && j == 8){
                        if (k == 1){
                            Wall wall = new Wall();
                            Position positionWall = new Position(1,8);
                            wall.setWallPosition(positionWall);
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.LEFT);
                            wall.setWallOrientation(orientations);
                            deathTrap.get(i).get(j).add(wall);
                        }
                        if (k == 2){
                            PushPanel pushPanel = new PushPanel();
                            Position positionPushPanel = new Position(1,8);
                            pushPanel.setPosition(positionPushPanel);
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.LEFT);
                            pushPanel.setOrientations(orientations);
                            deathTrap.get(i).get(j).add(pushPanel);
                        }
                    }

                    if (i == 2 && j == 8){
                        if (k == 1){
                            BlackHole blackHole = new BlackHole();
                            Position positionBlackHole = new Position(2,8);
                            blackHole.setBlackHolePosition(positionBlackHole);
                            deathTrap.get(i).get(j).add(blackHole);
                        }
                    }

                    if (i == 4 && j == 8){
                        if (k == 1){
                            ConveyorBelt conveyorBelt = new ConveyorBelt();
                            Position positionBelt = new Position(4,8);
                            conveyorBelt.setBeltPosition(positionBelt);
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.RIGHT);
                            conveyorBelt.setBeltOrientation(orientations);
                            conveyorBelt.setActivationOrder(2);
                            conveyorBelt.setSpeed(1);
                            deathTrap.get(i).get(j).add(conveyorBelt);
                        }
                    }

                    if (i == 5 && j == 8){
                        if (k == 1){
                            ConveyorBelt conveyorBelt = new ConveyorBelt();
                            Position positionBelt = new Position(5,8);
                            conveyorBelt.setBeltPosition(positionBelt);
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.RIGHT_BOTTOM);
                            conveyorBelt.setBeltOrientation(orientations);
                            conveyorBelt.setActivationOrder(2);
                            conveyorBelt.setSpeed(1);
                            deathTrap.get(i).get(j).add(conveyorBelt);
                        }
                    }

                    if(i == 7 && j == 8){
                        if(k == 1){
                            CheckPoint checkPoint = new CheckPoint();
                            Position positionCheckPoint = new Position(7,8);
                            checkPoint.setPosition(positionCheckPoint);
                            checkPoint.setNumber(3);
                            checkPoint.setActivationOrder(8);
                            deathTrap.get(i).get(j).add(checkPoint);
                        }
                    }

                    if (i == 8 && j == 8){
                        if (k == 1){
                            Wall wall = new Wall();
                            Position positionWall = new Position(8,8);
                            wall.setWallPosition(positionWall);
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.BOTTOM);
                            wall.setWallOrientation(orientations);
                            deathTrap.get(i).get(j).add(wall);
                        }
                        if (k == 2){
                            PushPanel pushPanel = new PushPanel();
                            Position positionPushPanel = new Position(8,8);
                            pushPanel.setPosition(positionPushPanel);
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.BOTTOM);
                            pushPanel.setOrientations(orientations);
                            deathTrap.get(i).get(j).add(pushPanel);
                        }
                    }

                    if(i == 11 && j == 8){
                        if(k == 1) {
                            StartPoint startPoint = new StartPoint();
                            Position positionStartPoint = new Position(11,8);
                            startPoint.setStartingPoint(positionStartPoint);
                            deathTrap.get(i).get(j).add(startPoint);
                        }
                    }

                    if (i == 5 && j == 9){
                        if (k == 1){
                            ConveyorBelt conveyorBelt = new ConveyorBelt();
                            Position positionBelt = new Position(5,9);
                            conveyorBelt.setBeltPosition(positionBelt);
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.BOTTOM_RIGHT);
                            conveyorBelt.setBeltOrientation(orientations);
                            conveyorBelt.setActivationOrder(2);
                            conveyorBelt.setSpeed(1);
                            deathTrap.get(i).get(j).add(conveyorBelt);
                        }
                    }

                    if (i == 6 && j == 9){
                        if (k == 1){
                            ConveyorBelt conveyorBelt = new ConveyorBelt();
                            Position positionBelt = new Position(6,9);
                            conveyorBelt.setBeltPosition(positionBelt);
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.RIGHT);
                            conveyorBelt.setBeltOrientation(orientations);
                            conveyorBelt.setActivationOrder(2);
                            conveyorBelt.setSpeed(1);
                            deathTrap.get(i).get(j).add(conveyorBelt);
                        }
                    }

                    if (i == 7 && j == 9){
                        if (k == 1){
                            ConveyorBelt conveyorBelt = new ConveyorBelt();
                            Position positionBelt = new Position(7,9);
                            conveyorBelt.setBeltPosition(positionBelt);
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.RIGHT);
                            conveyorBelt.setBeltOrientation(orientations);
                            conveyorBelt.setActivationOrder(2);
                            conveyorBelt.setSpeed(1);
                            deathTrap.get(i).get(j).add(conveyorBelt);
                        }
                    }

                    if (i == 8 && j == 9){
                        if (k == 1){
                            ConveyorBelt conveyorBelt = new ConveyorBelt();
                            Position positionBelt = new Position(8,9);
                            conveyorBelt.setBeltPosition(positionBelt);
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.RIGHT);
                            conveyorBelt.setBeltOrientation(orientations);
                            conveyorBelt.setActivationOrder(2);
                            conveyorBelt.setSpeed(1);
                            deathTrap.get(i).get(j).add(conveyorBelt);
                        }
                    }

                    if (i == 10 && j == 9){
                        if (k == 1){
                            ConveyorBelt conveyorBelt = new ConveyorBelt();
                            Position positionBelt = new Position(10,9);
                            conveyorBelt.setBeltPosition(positionBelt);
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            orientations.add(Orientation.LEFT);
                            conveyorBelt.setBeltOrientation(orientations);
                            conveyorBelt.setActivationOrder(2);
                            conveyorBelt.setSpeed(1);
                            deathTrap.get(i).get(j).add(conveyorBelt);
                        }
                    }

                    if(i == 12 && j == 9){
                        if(k == 1){
                            RebootPoint rebootPoint = new RebootPoint();
                            Position positionRebootPoint = new Position(12,9);
                            rebootPoint.setPosition(positionRebootPoint);
                            deathTrap.get(i).get(j).add(rebootPoint);
                        }
                    }

            }


            }

        }



    }
}
