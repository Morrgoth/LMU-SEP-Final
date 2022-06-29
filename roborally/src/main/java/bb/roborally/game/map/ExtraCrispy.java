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

                        if (k == 0) {
                            Floor floor = new Floor();
                            Position positionFloor = new Position(i, j);
                            floor.setFloorPosition(positionFloor);
                            extraCrispy.get(i).get(j).add(floor);
                    }

                if(i == 0 && j == 0){
                    if(k == 1){
                        RebootPoint rebootPoint = new RebootPoint();
                        Position positionRebootPoint = new Position(0,0);
                        rebootPoint.setPosition(positionRebootPoint);
                        extraCrispy.get(i).get(j).add(rebootPoint);
                    }
                }

                if(i == 2 && j == 0){
                    if(k == 1){
                        ConveyorBelt conveyorBelt = new ConveyorBelt();
                        Position positionBelt = new Position(2,0);
                        conveyorBelt.setBeltPosition(positionBelt);
                        ArrayList<Orientation> orientations = new ArrayList<>();
                        orientations.add(Orientation.RIGHT);
                        conveyorBelt.setBeltOrientation(orientations);
                        conveyorBelt.setActivationOrder(2);
                        conveyorBelt.setSpeed(1);
                        extraCrispy.get(i).get(j).add(conveyorBelt);
                    }
                }

                if(i == 4 && j == 0){
                    if(k == 1) {
                        ConveyorBelt conveyorBelt = new ConveyorBelt();
                        Position positionBelt = new Position(4, 0);
                        conveyorBelt.setBeltPosition(positionBelt);
                        ArrayList<Orientation> orientations = new ArrayList<>();
                        orientations.add(Orientation.BOTTOM);
                        conveyorBelt.setBeltOrientation(orientations);
                        conveyorBelt.setActivationOrder(1);
                        conveyorBelt.setSpeed(2);
                        extraCrispy.get(i).get(j).add(conveyorBelt);
                    }
                }

                if(i == 8 && k == 0){
                    if(k == 1){
                        EnergySpace energySpace = new EnergySpace();
                        Position positionEnergySpace = new Position(8,0);
                        energySpace.setEnergySpace(positionEnergySpace);
                        energySpace.setActivationOrder(7);
                        extraCrispy.get(i).get(j).add(energySpace);
                    }
                }

                if(i == 9 && j == 0){
                    if(k == 1){
                        Wall wall = new Wall();
                        Position positionWall = new Position(9,0);
                        wall.setWallPosition(positionWall);
                        ArrayList<Orientation> orientations = new ArrayList<>();
                        orientations.add(Orientation.LEFT);
                        wall.setWallOrientation(orientations);
                        extraCrispy.get(i).get(j).add(wall);
                    }

                    if(k == 2){
                        BoardLaser boardLaser = new BoardLaser();
                        Position positionBoardLaser = new Position(9,0);
                        boardLaser.setBoardLaserPosition(positionBoardLaser);
                        ArrayList<Orientation> orientations = new ArrayList<>();
                        orientations.add(Orientation.LEFT);
                        boardLaser.setBoardLaserOrientation(orientations);
                        boardLaser.setActivationOrder(5);
                        boardLaser.setCount(1);
                        extraCrispy.get(i).get(j).add(boardLaser);
                    }
                }

                if(i == 11 && j == 0){
                    if(k == 1){
                        Wall wall = new Wall();
                        Position positionWall = new Position(11,0);
                        wall.setWallPosition(positionWall);
                        ArrayList<Orientation> orientations = new ArrayList<>();
                        orientations.add(Orientation.RIGHT);
                        wall.setWallOrientation(orientations);
                        extraCrispy.get(i).get(j).add(wall);
                    }

                    if(k == 2){
                        BoardLaser boardLaser = new BoardLaser();
                        Position positionBoardLaser = new Position(11,0);
                        boardLaser.setBoardLaserPosition(positionBoardLaser);
                        ArrayList<Orientation> orientations = new ArrayList<>();
                        orientations.add(Orientation.RIGHT);
                        boardLaser.setBoardLaserOrientation(orientations);
                        boardLaser.setActivationOrder(5);
                        boardLaser.setCount(1);
                        extraCrispy.get(i).get(j).add(boardLaser);
                    }
                }

                if(i == 1 && j == 1){
                    if(k == 1){
                        StartPoint startPoint = new StartPoint();
                        Position positionStartPoint = new Position(1,1);
                        startPoint.setStartingPoint(positionStartPoint);
                        extraCrispy.get(i).get(j).add(startPoint);
                    }
                }

                if(i == 4 && j == 1){
                    if(k == 1) {
                        ConveyorBelt conveyorBelt = new ConveyorBelt();
                        Position positionBelt = new Position(4, 1);
                        conveyorBelt.setBeltPosition(positionBelt);
                        ArrayList<Orientation> orientations = new ArrayList<>();
                        orientations.add(Orientation.BOTTOM);
                        conveyorBelt.setBeltOrientation(orientations);
                        conveyorBelt.setActivationOrder(1);
                        conveyorBelt.setSpeed(2);
                        extraCrispy.get(i).get(j).add(conveyorBelt);
                    }
                }

                if(i == 6 && j == 1){
                    if(k == 1){
                        ConveyorBelt conveyorBelt = new ConveyorBelt();
                        Position positionBelt = new Position(6,1);
                        conveyorBelt.setBeltPosition(positionBelt);
                        ArrayList<Orientation> orientations = new ArrayList<>();
                        orientations.add(Orientation.LEFT_BOTTOM);
                        conveyorBelt.setBeltOrientation(orientations);
                        conveyorBelt.setActivationOrder(2);
                        conveyorBelt.setSpeed(1);
                        extraCrispy.get(i).get(j).add(conveyorBelt);
                    }
                }

                if(i == 7 && j == 1){
                    if(k == 1){
                        ConveyorBelt conveyorBelt = new ConveyorBelt();
                        Position positionBelt = new Position(7,1);
                        conveyorBelt.setBeltPosition(positionBelt);
                        ArrayList<Orientation> orientations = new ArrayList<>();
                        orientations.add(Orientation.BOTTOM);
                        conveyorBelt.setBeltOrientation(orientations);
                        conveyorBelt.setActivationOrder(2);
                        conveyorBelt.setSpeed(1);
                        extraCrispy.get(i).get(j).add(conveyorBelt);
                    }
                }

                if(i == 11 && j == 1){
                    if(k == 1){
                        ConveyorBelt conveyorBelt = new ConveyorBelt();
                        Position positionBelt = new Position(11,1);
                        conveyorBelt.setBeltPosition(positionBelt);
                        ArrayList<Orientation> orientations = new ArrayList<>();
                        orientations.add(Orientation.BOTTOM);
                        conveyorBelt.setBeltOrientation(orientations);
                        conveyorBelt.setActivationOrder(1);
                        conveyorBelt.setSpeed(2);
                        extraCrispy.get(i).get(j).add(conveyorBelt);
                    }
                }

                if(i == 1 && j == 2){
                    if(k == 1){
                        Wall wall = new Wall();
                        Position positionWall = new Position(1,2);
                        wall.setWallPosition(positionWall);
                        ArrayList<Orientation> orientations = new ArrayList<>();
                        orientations.add(Orientation.TOP);
                        wall.setWallOrientation(orientations);
                        extraCrispy.get(i).get(j).add(wall);
                    }
                }

                if(i == 4 && j == 2){
                    if(k == 1){
                        ConveyorBelt conveyorBelt = new ConveyorBelt();
                        Position positionBelt = new Position(4,2);
                        conveyorBelt.setBeltPosition(positionBelt);
                        ArrayList<Orientation> orientations = new ArrayList<>();
                        orientations.add(Orientation.BOTTOM);
                        conveyorBelt.setBeltOrientation(orientations);
                        conveyorBelt.setActivationOrder(1);
                        conveyorBelt.setSpeed(2);
                        extraCrispy.get(i).get(j).add(conveyorBelt);
                    }
                }

                if(i == 5 && j == 2){
                    if(k == 1){
                        CheckPoint checkPoint = new CheckPoint();
                        Position positionCheckPoint = new Position(5,2);
                        checkPoint.setPosition(positionCheckPoint);
                        checkPoint.setNumber(4);
                        checkPoint.setActivationOrder(8);
                        extraCrispy.get(i).get(j).add(checkPoint);
                    }
                }

                if(i == 6 && j == 2){
                    if(k == 1){
                        BlackHole blackHole = new BlackHole();
                        Position positionBlackHole = new Position(6,2);
                        blackHole.setBlackHolePosition(positionBlackHole);
                        extraCrispy.get(i).get(j).add(blackHole);
                    }
                }

                if(i == 9 && j == 2){
                    if(k == 1){
                        BlackHole blackHole = new BlackHole();
                        Position positionBlackHole = new Position(9,2);
                        blackHole.setBlackHolePosition(positionBlackHole);
                        extraCrispy.get(i).get(j).add(blackHole);
                    }
                }

                if(i == 10 && j == 2){
                    if(k == 1){
                        CheckPoint checkPoint = new CheckPoint();
                        Position positionCheckPoint = new Position(10,2);
                        checkPoint.setPosition(positionCheckPoint);
                        checkPoint.setNumber(1);
                        checkPoint.setActivationOrder(8);
                        extraCrispy.get(i).get(j).add(checkPoint);
                    }
                }

                if(i == 11 && j == 2){
                    if(k == 1){
                        ConveyorBelt conveyorBelt = new ConveyorBelt();
                        Position positionBelt = new Position(11,2);
                        conveyorBelt.setBeltPosition(positionBelt);
                        ArrayList<Orientation> orientations = new ArrayList<>();
                        orientations.add(Orientation.BOTTOM);
                        conveyorBelt.setBeltOrientation(orientations);
                        conveyorBelt.setActivationOrder(1);
                        conveyorBelt.setSpeed(2);
                        extraCrispy.get(i).get(j).add(conveyorBelt);
                    }
                }

                if(i == 0 && j == 3){
                    if(k == 1){
                        StartPoint startPoint = new StartPoint();
                        Position positionStartPoint = new Position(0,3);
                        startPoint.setStartingPoint(positionStartPoint);
                        extraCrispy.get(i).get(j).add(startPoint);
                    }
                }

                if(i == 4 && j == 3){
                    if(k == 1){
                        ConveyorBelt conveyorBelt = new ConveyorBelt();
                        Position positionBelt = new Position(4,3);
                        conveyorBelt.setBeltPosition(positionBelt);
                        ArrayList<Orientation> orientations = new ArrayList<>();
                        orientations.add(Orientation.LEFT_TOP);
                        conveyorBelt.setBeltOrientation(orientations);
                        conveyorBelt.setActivationOrder(1);
                        conveyorBelt.setSpeed(2);
                        extraCrispy.get(i).get(j).add(conveyorBelt);
                    }
                }

                if(i == 5 && j == 3){
                    if(k == 1){
                        ConveyorBelt conveyorBelt = new ConveyorBelt();
                        Position positionBelt = new Position(5,3);
                        conveyorBelt.setBeltPosition(positionBelt);
                        ArrayList<Orientation> orientations = new ArrayList<>();
                        orientations.add(Orientation.RIGHT);
                        conveyorBelt.setBeltOrientation(orientations);
                        conveyorBelt.setActivationOrder(1);
                        conveyorBelt.setSpeed(2);
                        extraCrispy.get(i).get(j).add(conveyorBelt);
                    }
                }

                if(i == 6 && j == 3){
                    if(k == 1){
                        BlackHole blackHole = new BlackHole();
                        Position positionBlackHole = new Position(6,3);
                        blackHole.setBlackHolePosition(positionBlackHole);
                        extraCrispy.get(i).get(j).add(blackHole);
                    }
                }

                if(i == 7 && j == 3){
                    if(k == 1){
                        Wall wall = new Wall();
                        Position positionWall = new Position(7,3);
                        wall.setWallPosition(positionWall);
                        ArrayList<Orientation> orientations = new ArrayList<>();
                        orientations.add(Orientation.LEFT);
                        wall.setWallOrientation(orientations);
                        extraCrispy.get(i).get(j).add(wall);
                    }

                    if(k == 2){
                        BoardLaser boardLaser = new BoardLaser();
                        Position positionBoardLaser = new Position(7,3);
                        boardLaser.setBoardLaserPosition(positionBoardLaser);
                        ArrayList<Orientation> orientations = new ArrayList<>();
                        orientations.add(Orientation.LEFT);
                        boardLaser.setBoardLaserOrientation(orientations);
                        boardLaser.setActivationOrder(5);
                        boardLaser.setCount(1);
                        extraCrispy.get(i).get(j).add(boardLaser);
                    }
                }

                if(i == 8 && j == 3){
                    if(k == 1){
                        Wall wall = new Wall();
                        Position positionWall = new Position(8,3);
                        wall.setWallPosition(positionWall);
                        ArrayList<Orientation> orientations = new ArrayList<>();
                        orientations.add(Orientation.RIGHT);
                        wall.setWallOrientation(orientations);
                        extraCrispy.get(i).get(j).add(wall);
                    }

                    if(k == 2){
                        BoardLaser boardLaser = new BoardLaser();
                        Position positionBoardLaser = new Position(9,3);
                        boardLaser.setBoardLaserPosition(positionBoardLaser);
                        ArrayList<Orientation> orientations = new ArrayList<>();
                        orientations.add(Orientation.RIGHT);
                        boardLaser.setBoardLaserOrientation(orientations);
                        boardLaser.setActivationOrder(5);
                        boardLaser.setCount(1);
                        extraCrispy.get(i).get(j).add(boardLaser);
                    }
                }

                if(i == 9 && j == 3){
                    if(k == 1){
                        BlackHole blackHole = new BlackHole();
                        Position positionBlackHole = new Position(9,3);
                        blackHole.setBlackHolePosition(positionBlackHole);
                        extraCrispy.get(i).get(j).add(blackHole);
                    }
                }

                if(i == 10 && j == 3){
                    if(k == 1){
                        ConveyorBelt conveyorBelt = new ConveyorBelt();
                        Position positionBelt = new Position(10,3);
                        conveyorBelt.setBeltPosition(positionBelt);
                        ArrayList<Orientation> orientations = new ArrayList<>();
                        orientations.add(Orientation.LEFT);
                        conveyorBelt.setBeltOrientation(orientations);
                        conveyorBelt.setActivationOrder(1);
                        conveyorBelt.setSpeed(2);
                        extraCrispy.get(i).get(j).add(conveyorBelt);
                    }
                }

                if(i == 11 && j == 3){
                    if(k == 1){
                        ConveyorBelt conveyorBelt = new ConveyorBelt();
                        Position positionBelt = new Position(11,3);
                        conveyorBelt.setBeltPosition(positionBelt);
                        ArrayList<Orientation> orientations = new ArrayList<>();
                        orientations.add(Orientation.BOTTOM_LEFT); //BOTTOM_LEFT
                        conveyorBelt.setBeltOrientation(orientations);
                        conveyorBelt.setActivationOrder(1);
                        conveyorBelt.setSpeed(2);
                        extraCrispy.get(i).get(j).add(conveyorBelt);
                    }
                }

                if(i == 0 && j == 4){
                    if(k == 1){
                        Antenna antenna = new Antenna();
                        Position positionAntenna = new Position(0,4);
                        antenna.setAntennaPosition(positionAntenna);
                        extraCrispy.get(i).get(j).add(antenna);
                    }
                }

                if(i == 1 && j == 4){
                    if(k == 1){
                        StartPoint startPoint = new StartPoint();
                        Position positionStartPoint = new Position(1,4);
                        startPoint.setStartingPoint(positionStartPoint);
                        extraCrispy.get(i).get(j).add(startPoint);
                    }
                }

                if(i == 2 && j == 4){
                    if(k == 1){
                        Wall wall = new Wall();
                        Position positionWall = new Position(2,4);
                        wall.setWallPosition(positionWall);
                        ArrayList<Orientation> orientations = new ArrayList<>();
                        orientations.add(Orientation.RIGHT);
                        wall.setWallOrientation(orientations);
                        extraCrispy.get(i).get(j).add(wall);
                    }
                }

                if(i == 3 && j == 4){
                    if(k == 1){
                        Wall wall = new Wall();
                        Position positionWall = new Position(3,4);
                        wall.setWallPosition(positionWall);
                        ArrayList<Orientation> orientations = new ArrayList<>();
                        orientations.add(Orientation.TOP);
                        wall.setWallOrientation(orientations);
                        extraCrispy.get(i).get(j).add(wall);
                    }

                    if(k == 2){
                        EnergySpace energySpace = new EnergySpace();
                        Position positionEnergySpace = new Position(3,4);
                        energySpace.setEnergySpace(positionEnergySpace);
                        energySpace.setActivationOrder(7);
                        extraCrispy.get(i).get(j).add(energySpace);
                    }
                }

                if(i == 6 && j == 4){
                    if(k == 1){
                        Gear gear = new Gear();
                        Position positionGear = new Position(6,4);
                        gear.setPosition(positionGear);
                        gear.setDirection("clockwise");
                        gear.setActivationOrder(4);
                        extraCrispy.get(i).get(j).add(gear);
                    }
                }

                if(i == 9 && j == 4){
                    if(k == 1){
                        Wall wall = new Wall();
                        Position positionWall = new Position(9,4);
                        wall.setWallPosition(positionWall);
                        ArrayList<Orientation> orientations = new ArrayList<>();
                        orientations.add(Orientation.RIGHT);
                        wall.setWallOrientation(orientations);
                        extraCrispy.get(i).get(j).add(wall);
                    }
                }

                if(i == 11 && j == 4){
                    if(k == 1){
                        EnergySpace energySpace = new EnergySpace();
                        Position positionEnergySpace = new Position(11,4);
                        energySpace.setEnergySpace(positionEnergySpace);
                        energySpace.setActivationOrder(7);
                        extraCrispy.get(i).get(j).add(energySpace);
                    }
                }

                if(i == 12 && j == 4){
                    if(k == 1){
                        Wall wall = new Wall();
                        Position positionWall = new Position(12,4);
                        wall.setWallPosition(positionWall);
                        ArrayList<Orientation> orientations = new ArrayList<>();
                        orientations.add(Orientation.TOP);
                        wall.setWallOrientation(orientations);
                        extraCrispy.get(i).get(j).add(wall);
                    }
                }

                if(i == 1 && j == 5){
                    if(k == 1){
                        StartPoint startPoint = new StartPoint();
                        Position positionStartPoint = new Position(1,5);
                        startPoint.setStartingPoint(positionStartPoint);
                        extraCrispy.get(i).get(j).add(startPoint);
                    }
                }

                if(i == 2 && j == 5){
                    if(k == 1){
                        Wall wall = new Wall();
                        Position positionWall = new Position(2,5);
                        wall.setWallPosition(positionWall);
                        ArrayList<Orientation> orientations = new ArrayList<>();
                        orientations.add(Orientation.RIGHT);
                        wall.setWallOrientation(orientations);
                        extraCrispy.get(i).get(j).add(wall);
                    }
                }

                if(i == 3 && j == 5){
                    if(k == 1){
                        Wall wall = new Wall();
                        Position positionWall = new Position(3,5);
                        wall.setWallPosition(positionWall);
                        ArrayList<Orientation> orientations = new ArrayList<>();
                        orientations.add(Orientation.BOTTOM);
                        wall.setWallOrientation(orientations);
                        extraCrispy.get(i).get(j).add(wall);
                    }
                }

                if(i == 6 && j == 5){
                    if(k == 1){
                        Wall wall = new Wall();
                        Position positionWall = new Position(6,5);
                        wall.setWallPosition(positionWall);
                        ArrayList<Orientation> orientations = new ArrayList<>();
                        orientations.add(Orientation.LEFT);
                        wall.setWallOrientation(orientations);
                        extraCrispy.get(i).get(j).add(wall);
                    }
                }

                if(i == 7 && j == 5){
                    if(k == 1){
                        EnergySpace energySpace = new EnergySpace();
                        Position positionEnergySpace = new Position(7,5);
                        energySpace.setEnergySpace(positionEnergySpace);
                        energySpace.setActivationOrder(7);
                        extraCrispy.get(i).get(j).add(energySpace);
                    }
                }

                if(i == 9 && j == 5){
                    if(k == 1){
                        Gear gear = new Gear();
                        Position positionGear = new Position(9,5);
                        gear.setPosition(positionGear);
                        gear.setDirection("counter_clockwise");
                        gear.setActivationOrder(4);
                        extraCrispy.get(i).get(j).add(gear);
                    }
                }

                if(i == 12 && j == 5){
                    if(k == 1){
                        Wall wall = new Wall();
                        Position positionWall = new Position(12,5);
                        wall.setWallPosition(positionWall);
                        ArrayList<Orientation> orientations = new ArrayList<>();
                        orientations.add(Orientation.BOTTOM);
                        wall.setWallOrientation(orientations);
                        extraCrispy.get(i).get(j).add(wall);
                    }
                }

                if(i == 0 && j == 6){
                    if(k == 1){
                        StartPoint startPoint = new StartPoint();
                        Position positionStartPoint = new Position(0,6);
                        startPoint.setStartingPoint(positionStartPoint);
                        extraCrispy.get(i).get(j).add(startPoint);
                    }
                }

                if(i == 4 && j == 6){
                    if(k == 1){
                        ConveyorBelt conveyorBelt = new ConveyorBelt();
                        Position positionBelt = new Position(4,6);
                        conveyorBelt.setBeltPosition(positionBelt);
                        ArrayList<Orientation> orientations = new ArrayList<>();
                        orientations.add(Orientation.TOP_RIGHT);
                        conveyorBelt.setBeltOrientation(orientations);
                        conveyorBelt.setActivationOrder(1);
                        conveyorBelt.setSpeed(2);
                        extraCrispy.get(i).get(j).add(conveyorBelt);
                    }
                }

                if(i == 5 && j == 6){
                    if(k == 1){
                        ConveyorBelt conveyorBelt = new ConveyorBelt();
                        Position positionBelt = new Position(5,6);
                        conveyorBelt.setBeltPosition(positionBelt);
                        ArrayList<Orientation> orientations = new ArrayList<>();
                        orientations.add(Orientation.RIGHT);
                        conveyorBelt.setBeltOrientation(orientations);
                        conveyorBelt.setActivationOrder(1);
                        conveyorBelt.setSpeed(2);
                        extraCrispy.get(i).get(j).add(conveyorBelt);
                    }
                }

                if(i == 6 && j == 6){
                    if(k == 1){
                        BlackHole blackHole = new BlackHole();
                        Position positionBlackHole = new Position(6,6);
                        blackHole.setBlackHolePosition(positionBlackHole);
                        extraCrispy.get(i).get(j).add(blackHole);
                    }
                }

                if(i == 7 && j == 6){
                    if(k == 1){
                        Wall wall = new Wall();
                        Position positionWall = new Position(7,6);
                        wall.setWallPosition(positionWall);
                        ArrayList<Orientation> orientations = new ArrayList<>();
                        orientations.add(Orientation.LEFT);
                        wall.setWallOrientation(orientations);
                        extraCrispy.get(i).get(j).add(wall);
                    }

                    if(k == 2){
                        BoardLaser boardLaser = new BoardLaser();
                        Position positionBoardLaser = new Position(7,6);
                        boardLaser.setBoardLaserPosition(positionBoardLaser);
                        ArrayList<Orientation> orientations = new ArrayList<>();
                        orientations.add(Orientation.LEFT);
                        boardLaser.setBoardLaserOrientation(orientations);
                        boardLaser.setActivationOrder(5);
                        boardLaser.setCount(1);
                        extraCrispy.get(i).get(j).add(boardLaser);
                    }
                }

                if(i == 8 && j == 6){
                    if(k == 1){
                        Wall wall = new Wall();
                        Position positionWall = new Position(8,6);
                        wall.setWallPosition(positionWall);
                        ArrayList<Orientation> orientations = new ArrayList<>();
                        orientations.add(Orientation.RIGHT);
                        wall.setWallOrientation(orientations);
                        extraCrispy.get(i).get(j).add(wall);
                    }

                    if(k == 2){
                        BoardLaser boardLaser = new BoardLaser();
                        Position positionBoardLaser = new Position(8,6);
                        boardLaser.setBoardLaserPosition(positionBoardLaser);
                        ArrayList<Orientation> orientations = new ArrayList<>();
                        orientations.add(Orientation.RIGHT);
                        boardLaser.setBoardLaserOrientation(orientations);
                        boardLaser.setActivationOrder(5);
                        boardLaser.setCount(1);
                        extraCrispy.get(i).get(j).add(boardLaser);
                    }
                }

                if(i == 9 && j == 6){
                    if(k == 1){
                        BlackHole blackHole = new BlackHole();
                        Position positionBlackHole = new Position(9,6);
                        blackHole.setBlackHolePosition(positionBlackHole);
                        extraCrispy.get(i).get(j).add(blackHole);
                    }
                }

                if(i == 10 && j == 6){
                    if(k == 1){
                        ConveyorBelt conveyorBelt = new ConveyorBelt();
                        Position positionBelt = new Position(5,6);
                        conveyorBelt.setBeltPosition(positionBelt);
                        ArrayList<Orientation> orientations = new ArrayList<>();
                        orientations.add(Orientation.LEFT);
                        conveyorBelt.setBeltOrientation(orientations);
                        conveyorBelt.setActivationOrder(1);
                        conveyorBelt.setSpeed(2);
                        extraCrispy.get(i).get(j).add(conveyorBelt);
                    }
                }

                if(i == 11 && j == 6){
                    if(k == 1){
                        ConveyorBelt conveyorBelt = new ConveyorBelt();
                        Position positionBelt = new Position(11,6);
                        conveyorBelt.setBeltPosition(positionBelt);
                        ArrayList<Orientation> orientations = new ArrayList<>();
                        orientations.add(Orientation.TOP_LEFT);
                        conveyorBelt.setBeltOrientation(orientations);
                        conveyorBelt.setActivationOrder(1);
                        conveyorBelt.setSpeed(2);
                        extraCrispy.get(i).get(j).add(conveyorBelt);
                    }
                }

                if(i == 1 && j == 7){
                    if(k == 1){
                        Wall wall = new Wall();
                        Position positionWall = new Position(1,7);
                        wall.setWallPosition(positionWall);
                        ArrayList<Orientation> orientations = new ArrayList<>();
                        orientations.add(Orientation.BOTTOM);
                        wall.setWallOrientation(orientations);
                        extraCrispy.get(i).get(j).add(wall);
                    }
                }

                if(i == 4 && j == 7){
                    if(k == 1){
                        ConveyorBelt conveyorBelt = new ConveyorBelt();
                        Position positionBelt = new Position(4,7);
                        conveyorBelt.setBeltPosition(positionBelt);
                        ArrayList<Orientation> orientations = new ArrayList<>();
                        orientations.add(Orientation.TOP);
                        conveyorBelt.setBeltOrientation(orientations);
                        conveyorBelt.setActivationOrder(1);
                        conveyorBelt.setSpeed(2);
                        extraCrispy.get(i).get(j).add(conveyorBelt);
                    }
                }

                if(i == 5 && j == 7){
                    if(k == 1){
                        CheckPoint checkPoint = new CheckPoint();
                        Position positionCheckPoint = new Position(5,7);
                        checkPoint.setPosition(positionCheckPoint);
                        checkPoint.setNumber(2);
                        checkPoint.setActivationOrder(8);
                        extraCrispy.get(i).get(j).add(checkPoint);
                    }
                }

                if(i == 6 && j == 7){
                    if(k == 1){
                        BlackHole blackHole = new BlackHole();
                        Position positionBlackHole = new Position(6,7);
                        blackHole.setBlackHolePosition(positionBlackHole);
                        extraCrispy.get(i).get(j).add(blackHole);
                    }
                }

                if(i == 9 && j == 7){
                    if(k == 1){
                        BlackHole blackHole = new BlackHole();
                        Position positionBlackHole = new Position(9,7);
                        blackHole.setBlackHolePosition(positionBlackHole);
                        extraCrispy.get(i).get(j).add(blackHole);
                    }
                }

                if(i == 10 && j == 7){
                    if(k == 1){
                        CheckPoint checkPoint = new CheckPoint();
                        Position positionCheckPoint = new Position(10,7);
                        checkPoint.setPosition(positionCheckPoint);
                        checkPoint.setNumber(3);
                        checkPoint.setActivationOrder(8);
                        extraCrispy.get(i).get(j).add(checkPoint);
                    }
                }

                if(i == 11 && j == 7){
                    if(k == 1){
                        ConveyorBelt conveyorBelt = new ConveyorBelt();
                        Position positionBelt = new Position(11,7);
                        conveyorBelt.setBeltPosition(positionBelt);
                        ArrayList<Orientation> orientations = new ArrayList<>();
                        orientations.add(Orientation.TOP);
                        conveyorBelt.setBeltOrientation(orientations);
                        conveyorBelt.setActivationOrder(1);
                        conveyorBelt.setSpeed(2);
                        extraCrispy.get(i).get(j).add(conveyorBelt);
                    }
                }

                if(i == 1 && j == 8){
                    if(k == 1) {
                        StartPoint startPoint = new StartPoint();
                        Position positionStartPoint = new Position(1,8);
                        startPoint.setStartingPoint(positionStartPoint);
                        extraCrispy.get(i).get(j).add(startPoint);
                    }
                }

                if(i == 4 && j == 8){
                    if(k == 1){
                        ConveyorBelt conveyorBelt = new ConveyorBelt();
                        Position positionBelt = new Position(4,8);
                        conveyorBelt.setBeltPosition(positionBelt);
                        ArrayList<Orientation> orientations = new ArrayList<>();
                        orientations.add(Orientation.TOP);
                        conveyorBelt.setBeltOrientation(orientations);
                        conveyorBelt.setActivationOrder(1);
                        conveyorBelt.setSpeed(2);
                        extraCrispy.get(i).get(j).add(conveyorBelt);
                    }
                }

                if(i == 8 && j == 8){
                    if(k == 1){
                        ConveyorBelt conveyorBelt = new ConveyorBelt();
                        Position positionBelt = new Position(8,8);
                        conveyorBelt.setBeltPosition(positionBelt);
                        ArrayList<Orientation> orientations = new ArrayList<>();
                        orientations.add(Orientation.RIGHT);
                        conveyorBelt.setBeltOrientation(orientations);
                        conveyorBelt.setActivationOrder(2);
                        conveyorBelt.setSpeed(1);
                        extraCrispy.get(i).get(j).add(conveyorBelt);
                    }
                }

                if(i == 9 && j == 8){
                    if(k == 1){
                        ConveyorBelt conveyorBelt = new ConveyorBelt();
                        Position positionBelt = new Position(9,8);
                        conveyorBelt.setBeltPosition(positionBelt);
                        ArrayList<Orientation> orientations = new ArrayList<>();
                        orientations.add(Orientation.RIGHT_TOP);
                        conveyorBelt.setBeltOrientation(orientations);
                        conveyorBelt.setActivationOrder(2);
                        conveyorBelt.setSpeed(1);
                        extraCrispy.get(i).get(j).add(conveyorBelt);
                    }
                }

                if(i == 11 && j == 8){
                    if(k == 1){
                        ConveyorBelt conveyorBelt = new ConveyorBelt();
                        Position positionBelt = new Position(11,8);
                        conveyorBelt.setBeltPosition(positionBelt);
                        ArrayList<Orientation> orientations = new ArrayList<>();
                        orientations.add(Orientation.TOP);
                        conveyorBelt.setBeltOrientation(orientations);
                        conveyorBelt.setActivationOrder(1);
                        conveyorBelt.setSpeed(2);
                        extraCrispy.get(i).get(j).add(conveyorBelt);
                    }
                }

                if(i == 2 && j == 9){
                    if(k == 1){
                        ConveyorBelt conveyorBelt = new ConveyorBelt();
                        Position positionBelt = new Position(2,9);
                        conveyorBelt.setBeltPosition(positionBelt);
                        ArrayList<Orientation> orientations = new ArrayList<>();
                        orientations.add(Orientation.RIGHT);
                        conveyorBelt.setBeltOrientation(orientations);
                        conveyorBelt.setActivationOrder(2);
                        conveyorBelt.setSpeed(1);
                        extraCrispy.get(i).get(j).add(conveyorBelt);
                    }
                }

                if(i == 3 && j == 9){
                    if(k == 1){
                        EnergySpace energySpace = new EnergySpace();
                        Position positionEnergySpace = new Position(3,9);
                        energySpace.setEnergySpace(positionEnergySpace);
                        energySpace.setActivationOrder(7);
                        extraCrispy.get(i).get(j).add(energySpace);
                    }
                }

                if(i == 4 && j == 9){
                    if(k == 1){
                        Wall wall = new Wall();
                        Position positionWall = new Position(4,9);
                        wall.setWallPosition(positionWall);
                        ArrayList<Orientation> orientations = new ArrayList<>();
                        orientations.add(Orientation.LEFT);
                        wall.setWallOrientation(orientations);
                        extraCrispy.get(i).get(j).add(wall);
                    }

                    if(k == 2){
                        BoardLaser boardLaser = new BoardLaser();
                        Position positionBoardLaser = new Position(4,9);
                        boardLaser.setBoardLaserPosition(positionBoardLaser);
                        ArrayList<Orientation> orientations = new ArrayList<>();
                        orientations.add(Orientation.LEFT);
                        boardLaser.setBoardLaserOrientation(orientations);
                        boardLaser.setActivationOrder(5);
                        boardLaser.setCount(1);
                        extraCrispy.get(i).get(j).add(boardLaser);
                    }
                }

                if(i == 6 && j == 9){
                    if(k == 1){
                        Wall wall = new Wall();
                        Position positionWall = new Position(6,9);
                        wall.setWallPosition(positionWall);
                        ArrayList<Orientation> orientations = new ArrayList<>();
                        orientations.add(Orientation.RIGHT);
                        wall.setWallOrientation(orientations);
                        extraCrispy.get(i).get(j).add(wall);
                    }

                    if(k == 2){
                        BoardLaser boardLaser = new BoardLaser();
                        Position positionBoardLaser = new Position(6,9);
                        boardLaser.setBoardLaserPosition(positionBoardLaser);
                        ArrayList<Orientation> orientations = new ArrayList<>();
                        orientations.add(Orientation.RIGHT);
                        boardLaser.setBoardLaserOrientation(orientations);
                        boardLaser.setActivationOrder(5);
                        boardLaser.setCount(1);
                        extraCrispy.get(i).get(j).add(boardLaser);
                    }
                }

                if(i == 11 && j == 9){
                    if(k == 1){
                        ConveyorBelt conveyorBelt = new ConveyorBelt();
                        Position positionBelt = new Position(11,9);
                        conveyorBelt.setBeltPosition(positionBelt);
                        ArrayList<Orientation> orientations = new ArrayList<>();
                        orientations.add(Orientation.TOP);
                        conveyorBelt.setBeltOrientation(orientations);
                        conveyorBelt.setActivationOrder(1);
                        conveyorBelt.setSpeed(2);
                        extraCrispy.get(i).get(j).add(conveyorBelt);
                        }
                    }
                }
            }
        }
    }
}
