package bb.roborally.game.map;


import bb.roborally.game.Orientation;
import bb.roborally.game.Position;
import bb.roborally.game.board.Board;
import bb.roborally.game.tiles.*;


import java.util.ArrayList;


/**
 * creates Map Dizzy HighWay
 *
 * @author Veronika Heckel
 */
public class DizzyHighway extends Board  {
    private ArrayList<ArrayList<ArrayList<Tile>>> dizzyHighway;


    public DizzyHighway(ArrayList<ArrayList<ArrayList<Tile>>> dizzyHighway) {
        super(dizzyHighway);
        this.dizzyHighway = dizzyHighway;
    }


    public void buildDizzyHighway() {
        int xAxis = 13;
        int yAxis = 10;
        int maxCellContent = 4;

        dizzyHighway = new ArrayList<ArrayList<ArrayList<Tile>>>();

        //for - Schleife x-Koordinaten (äußerste ArrayList)
        for (int i = 0; i < xAxis; i++) {
            dizzyHighway.add(new ArrayList<ArrayList<Tile>>());

            //for - Schleife y-Koordinaten (mittlere ArrayList)
            for (int j = 0; j < yAxis; j++) {
                dizzyHighway.get(i).add(new ArrayList<Tile>());

                //for - Schleife Cells (innerste ArrayList)
               for(int k = 0; k < maxCellContent; k++){
                   if(k == 0){
                       Floor floor = new Floor();
                       Position positionFloor = new Position(i,j);
                       floor.setFloorPosition(positionFloor);
                       dizzyHighway.get(i).get(j).add(floor);
                   }


                   if(i == 2 && j == 0){
                       if(k == 1){
                           ConveyorBelt conveyorBelt = new ConveyorBelt();
                           Position positionBelt = new Position(2,0);
                           conveyorBelt.setBeltPosition(positionBelt);
                           ArrayList<Orientation> orientations =new ArrayList<>();
                           orientations.add(0,Orientation.RIGHT);
                           conveyorBelt.setBeltOrientation(orientations);
                           conveyorBelt.setActivationOrder(2);
                           conveyorBelt.setSpeed(1);
                           dizzyHighway.get(i).get(j).add(conveyorBelt);

                       }
                    }

                    if(i == 4 && j == 0){
                        if(k == 1) {
                            ConveyorBelt conveyorBelt = new ConveyorBelt();
                            Position positionBelt = new Position(4, 0);
                            conveyorBelt.setBeltPosition(positionBelt);
                            ArrayList<Orientation> orientations =new ArrayList<>();
                            orientations.add(1,Orientation.BOTTOM);
                            conveyorBelt.setBeltOrientation(orientations);
                            conveyorBelt.setActivationOrder(1);
                            conveyorBelt.setSpeed(2);
                            dizzyHighway.get(i).get(j).add(conveyorBelt);
                        }
                    }

                    if(i == 5 && j == 0){
                        if(k == 1){
                            ConveyorBelt conveyorBelt = new ConveyorBelt();
                            Position positionBelt = new Position(5,0);
                            conveyorBelt.setBeltPosition(positionBelt);
                            ArrayList<Orientation> orientations =new ArrayList<>();
                            orientations.add(1,Orientation.BOTTOM);
                            conveyorBelt.setBeltOrientation(orientations);
                            conveyorBelt.setActivationOrder(1);
                            conveyorBelt.setSpeed(2);
                            dizzyHighway.get(i).get(j).add(conveyorBelt);
                        }
                    }

                    if(i == 12 && j == 0){
                        if(k == 1){
                            EnergySpace energySpace = new EnergySpace();
                            Position positionEnergySpace = new Position(12,0);
                            energySpace.setPosition(positionEnergySpace);
                            dizzyHighway.get(i).get(j).add(energySpace);
                        }
                    }

                    if(i == 1 && j == 1){
                        if(k == 1) {
                            StartPoint startPoint = new StartPoint();
                            Position positionStartPoint = new Position(1, 1);
                            startPoint.setStartingPoint(positionStartPoint);
                            dizzyHighway.get(i).get(j).add(startPoint);
                        }
                    }


                    if(i == 4 && j == 1){
                        if(k == 1){
                            ConveyorBelt conveyorBelt = new ConveyorBelt();
                            Position positionBelt = new Position(4,1);
                            conveyorBelt.setBeltPosition(positionBelt);
                            ArrayList<Orientation> orientations =new ArrayList<>();
                            orientations.add(Orientation.BOTTOM);
                            orientations.add(Orientation.LEFT);
                            orientations.add(Orientation.TOP);
                            conveyorBelt.setActivationOrder(1);
                            conveyorBelt.setSpeed(2);
                            dizzyHighway.get(i).get(j).add(conveyorBelt);
                        }
                    }

                    if(i == 5 && j == 1){
                        if(k == 1){
                            ConveyorBelt conveyorBelt = new ConveyorBelt();
                            Position positionBelt = new Position(5,1);
                            conveyorBelt.setBeltPosition(positionBelt);
                            ArrayList<Orientation> orientations =new ArrayList<>();
                            orientations.add(Orientation.LEFT);
                            orientations.add(Orientation.TOP);
                            orientations.add(Orientation.RIGHT);
                            conveyorBelt.setActivationOrder(1);
                            conveyorBelt.setSpeed(2);
                            dizzyHighway.get(i).get(j).add(conveyorBelt);
                        }
                    }

                    if(i == 6 && j == 1){
                        if(k == 1){
                            ConveyorBelt conveyorBelt = new ConveyorBelt();
                            Position positionBelt = new Position(6,1);
                            conveyorBelt.setBeltPosition(positionBelt);
                            ArrayList<Orientation> orientations =new ArrayList<>();
                            orientations.add(Orientation.LEFT);
                            conveyorBelt.setBeltOrientation(orientations);
                            conveyorBelt.setActivationOrder(1);
                            conveyorBelt.setSpeed(2);
                            dizzyHighway.get(i).get(j).add(conveyorBelt);
                        }
                    }

                    if(i == 7 && j == 1){
                        if(k == 1){
                            ConveyorBelt conveyorBelt = new ConveyorBelt();
                            Position positionBelt = new Position(7,1);
                            conveyorBelt.setBeltPosition(positionBelt);
                            ArrayList<Orientation> orientations =new ArrayList<>();
                            orientations.add(Orientation.LEFT);
                            conveyorBelt.setBeltOrientation(orientations);
                            conveyorBelt.setActivationOrder(1);
                            conveyorBelt.setSpeed(2);
                            dizzyHighway.get(i).get(j).add(conveyorBelt);
                        }
                    }

                    if(i == 8 && j == 1){
                        if(k == 2){
                            ConveyorBelt conveyorBelt = new ConveyorBelt();
                            Position positionBelt = new Position(8,1);
                            conveyorBelt.setBeltPosition(positionBelt);
                            ArrayList<Orientation> orientations =new ArrayList<>();
                            orientations.add(Orientation.LEFT);
                            conveyorBelt.setBeltOrientation(orientations);
                            conveyorBelt.setActivationOrder(1);
                            conveyorBelt.setSpeed(2);
                            dizzyHighway.get(i).get(j).add(conveyorBelt);
                        }
                    }

                    if(i == 9 && j == 1){
                        if(k == 1){
                            ConveyorBelt conveyorBelt = new ConveyorBelt();
                            Position positionBelt = new Position(9,1);
                            conveyorBelt.setBeltPosition(positionBelt);
                            ArrayList<Orientation> orientations =new ArrayList<>();
                            orientations.add(Orientation.LEFT);
                            conveyorBelt.setBeltOrientation(orientations);
                            conveyorBelt.setActivationOrder(1);
                            conveyorBelt.setSpeed(2);
                            dizzyHighway.get(i).get(j).add(conveyorBelt);
                        }
                    }

                    if(i == 10 && j == 1){
                        if(k == 1){
                            ConveyorBelt conveyorBelt = new ConveyorBelt();
                            Position positionBelt = new Position(10,1);
                            conveyorBelt.setBeltPosition(positionBelt);
                            ArrayList<Orientation> orientations =new ArrayList<>();
                            orientations.add(Orientation.LEFT);
                            conveyorBelt.setBeltOrientation(orientations);
                            conveyorBelt.setActivationOrder(1);
                            conveyorBelt.setSpeed(2);
                            dizzyHighway.get(i).get(j).add(conveyorBelt);
                        }
                    }

                    if(i == 11 && j == 1) {
                        if(k == 1){
                            ConveyorBelt conveyorBelt = new ConveyorBelt();
                            Position positionBelt = new Position(11,1);
                            conveyorBelt.setBeltPosition(positionBelt);
                            ArrayList<Orientation> orientations =new ArrayList<>();
                            orientations.add(Orientation.LEFT);
                            orientations.add(Orientation.TOP);
                            orientations.add(Orientation.RIGHT);
                            conveyorBelt.setBeltOrientation(orientations);
                            conveyorBelt.setActivationOrder(1);
                            conveyorBelt.setSpeed(2);
                            dizzyHighway.get(i).get(j).add(conveyorBelt);
                        }
                    }

                    if(i == 12 && j == 1){
                        if(k == 1){
                            ConveyorBelt conveyorBelt = new ConveyorBelt();
                            Position positionBelt = new Position(12,1);
                            conveyorBelt.setBeltPosition(positionBelt);
                            ArrayList<Orientation> orientations =new ArrayList<>();
                            orientations.add(Orientation.LEFT);
                            conveyorBelt.setBeltOrientation(orientations);
                            conveyorBelt.setActivationOrder(1);
                            conveyorBelt.setSpeed(2);
                            dizzyHighway.get(i).get(j).add(conveyorBelt);
                        }
                    }

                    if(i == 1 && j == 2){
                        if(k == 2){
                            Wall wall = new Wall();
                            Position positionWall = new Position(1,2);
                            wall.setWallPosition(positionWall);
                            wall.setWallPosition(Wall.getWallPosition());
                            ArrayList<Orientation> orientations =new ArrayList<>();
                            orientations.add(Orientation.TOP);
                            wall.setWallOrientation(orientations);
                            dizzyHighway.get(i).get(j).add(wall);
                        }
                    }

                    if(i == 4 && j == 2){
                        if(k == 1){
                            ConveyorBelt conveyorBelt = new ConveyorBelt();
                            Position positionBelt = new Position(4,2);
                            conveyorBelt.setBeltPosition(positionBelt);
                            ArrayList<Orientation> orientations =new ArrayList<>();
                            orientations.add(Orientation.BOTTOM);
                            conveyorBelt.setBeltOrientation(orientations);
                            conveyorBelt.setActivationOrder(1);
                            conveyorBelt.setSpeed(2);
                            dizzyHighway.get(i).get(j).add(conveyorBelt);
                        }
                    }

                    if(i == 11 && j == 2){
                        if(k == 1){
                            ConveyorBelt conveyorBelt = new ConveyorBelt();
                            Position positionBelt = new Position(11,2);
                            conveyorBelt.setBeltPosition(positionBelt);
                            ArrayList<Orientation> orientations =new ArrayList<>();
                            orientations.add(Orientation.TOP);
                            conveyorBelt.setBeltOrientation(orientations);
                            conveyorBelt.setActivationOrder(1);
                            conveyorBelt.setSpeed(2);
                            dizzyHighway.get(i).get(j).add(conveyorBelt);
                        }
                    }

                    if(i == 12 && j == 2){
                        if(k == 1){
                            ConveyorBelt conveyorBelt = new ConveyorBelt();
                            Position positionBelt = new Position(12,2);
                            conveyorBelt.setBeltPosition(positionBelt);
                            ArrayList<Orientation> orientations =new ArrayList<>();
                            orientations.add(Orientation.LEFT);
                            conveyorBelt.setBeltOrientation(orientations);
                            conveyorBelt.setActivationOrder(1);
                            conveyorBelt.setSpeed(2);
                            dizzyHighway.get(i).get(j).add(conveyorBelt);
                        }
                    }

                    if(i == 5 && j == 2){
                        if(k == 1){
                            EnergySpace energySpace = new EnergySpace();
                            Position positionEnergySpace = new Position(5,2);
                            ArrayList<Orientation> orientations = new ArrayList<>();
                            dizzyHighway.get(i).get(j).add(energySpace);
                        }
                    }

                    if(i == 0 && j == 3){
                        if(k == 1){
                            StartPoint startPoint = new StartPoint();
                            Position positionStartPoint = new Position(0,3);
                            startPoint.setStartingPoint(positionStartPoint);
                            dizzyHighway.get(i).get(j).add(startPoint);
                        }
                    }

                    if(i == 4 && j == 3){
                        if(k == 1){
                            ConveyorBelt conveyorBelt = new ConveyorBelt();
                            Position positionBelt = new Position(4,3);
                            conveyorBelt.setBeltPosition(positionBelt);
                            ArrayList<Orientation> orientations =new ArrayList<>();
                            orientations.add(Orientation.BOTTOM);
                            conveyorBelt.setBeltOrientation(orientations);
                            conveyorBelt.setActivationOrder(1);
                            conveyorBelt.setSpeed(2);
                            dizzyHighway.get(i).get(j).add(conveyorBelt);
                        }
                    }

                    if(i == 6 && j == 3){
                        if(k == 1){
                            Wall wall = new Wall();
                            Position positionWall = new Position(6,3);
                            wall.setWallPosition(positionWall);
                            ArrayList<Orientation> orientations =new ArrayList<>();
                            orientations.add(Orientation.TOP);
                            wall.setWallOrientation(orientations);
                            dizzyHighway.get(i).get(j).add(wall);
                        }
                    }

                    if(i == 7 && j == 3){
                        if(k == 1){
                            RebootPoint rebootPoint = new RebootPoint();
                            Position positionRebootPoint = new Position(7,3);
                            rebootPoint.setPosition(positionRebootPoint);
                            dizzyHighway.get(i).get(j).add(rebootPoint);
                        }
                    }

                    if(i == 8 && j == 3){
                        if(k == 1){
                            Wall wall = new Wall();
                            Position positionWall = new Position(8,3);
                            wall.setWallPosition(positionWall);
                            ArrayList<Orientation> orientations =new ArrayList<>();
                            orientations.add(Orientation.LEFT);
                            wall.setWallOrientation(orientations);
                            dizzyHighway.get(i).get(j).add(wall);
                        }

                        if(k == 2){
                            BoardLaser boardLaser = new BoardLaser();
                            Position positionBoardLaser = new Position(8,3);
                            boardLaser.setBoardLaserPosition(positionBoardLaser);
                            ArrayList<Orientation> orientations =new ArrayList<>();
                            orientations.add(Orientation.LEFT);
                            boardLaser.setBoardLaserOrientation(orientations);
                            boardLaser.setCount(1);
                            dizzyHighway.get(i).get(j).add(boardLaser);
                        }
                    }

                    if(i == 9 && j == 3){
                        if(k == 1){
                            Wall wall = new Wall();
                            Position positionWall = new Position(9,3);
                            wall.setWallPosition(positionWall);
                            ArrayList<Orientation> orientations =new ArrayList<>();
                            orientations.add(Orientation.RIGHT);
                            wall.setWallOrientation(orientations);
                            dizzyHighway.get(i).get(j).add(wall);

                        }
                    }

                    if(i == 11 && j == 2){
                        if(k == 1){
                            ConveyorBelt conveyorBelt = new ConveyorBelt();
                            Position positionBelt = new Position(11,3);
                            conveyorBelt.setBeltPosition(positionBelt);
                            ArrayList<Orientation> orientations =new ArrayList<>();
                            orientations.add(Orientation.TOP);
                            orientations.add(Orientation.LEFT);
                            orientations.add(Orientation.BOTTOM);
                            conveyorBelt.setBeltOrientation(orientations);
                            conveyorBelt.setActivationOrder(1);
                            conveyorBelt.setSpeed(2);
                            dizzyHighway.get(i).get(j).add(conveyorBelt);
                        }
                    }

                    if(i == 12 && j == 3){
                        if(k == 1){
                            CheckPoint checkPoint = new CheckPoint();
                            Position positionCheckPoint = new Position(12,3);
                            checkPoint.setPosition(positionCheckPoint);
                            checkPoint.setNumber(1);
                            checkPoint.setActivationOrder(8);
                            dizzyHighway.get(i).get(j).add(checkPoint);
                        }
                    }

                    if(i == 0 && j == 4){
                        if(k == 1){
                            Antenna antenna = new Antenna();
                            Position positionAntenna = new Position(0,4);
                            antenna.setAntennaPosition(positionAntenna);
                            dizzyHighway.get(i).get(j).add(antenna);
                        }
                    }

                    if(i == 1 && j == 4){
                        if(k == 1) {
                            StartPoint startPoint = new StartPoint();
                            Position positionStartPoint = new Position(1,4);
                            startPoint.setStartingPoint(positionStartPoint);
                            dizzyHighway.get(i).get(j).add(startPoint);
                        }
                    }

                    if(i == 2 && j == 4){
                        if(k == 1){
                            Wall wall = new Wall();
                            Position positionWall = new Position(2,4);
                            wall.setWallPosition(positionWall);
                            ArrayList<Orientation> orientations =new ArrayList<>();
                            orientations.add(Orientation.RIGHT);
                            wall.setWallOrientation(orientations);
                            dizzyHighway.get(i).get(j).add(wall);
                        }
                    }

                    if(i == 4 && j == 4) {
                        if(k == 1){
                            ConveyorBelt conveyorBelt = new ConveyorBelt();
                            Position positionBelt = new Position(4,4);
                            conveyorBelt.setBeltPosition(positionBelt);
                            ArrayList<Orientation> orientations =new ArrayList<>();
                            orientations.add(Orientation.BOTTOM);
                            conveyorBelt.setBeltOrientation(orientations);
                            conveyorBelt.setActivationOrder(1);
                            conveyorBelt.setSpeed(2);
                            dizzyHighway.get(i).get(j).add(conveyorBelt);
                        }
                    }

                    if(i == 6 && j == 4){
                        if(k == 1){
                            Wall wall = new Wall();
                            Position positionWall = new Position(6,4);
                            wall.setWallPosition(positionWall);
                            ArrayList<Orientation> orientations =new ArrayList<>();
                            orientations.add(Orientation.BOTTOM);
                            wall.setWallOrientation(orientations);
                            dizzyHighway.get(i).get(j).add(wall);
                        }

                        if(k == 2){
                            BoardLaser boardLaser = new BoardLaser();
                            Position positionBoardLaser = new Position(6,4);
                            boardLaser.setBoardLaserPosition(positionBoardLaser);
                            ArrayList<Orientation> orientations =new ArrayList<>();
                            orientations.add(Orientation.BOTTOM);
                            boardLaser.setBoardLaserOrientation(orientations);
                            boardLaser.setCount(1);
                            dizzyHighway.get(i).get(j).add(boardLaser);
                        }
                    }

                    if(i == 8 && j == 4){
                        if(k == 1){
                            EnergySpace energySpace = new EnergySpace();
                            Position positionEnergySpace = new Position(8,4);
                            ArrayList<Orientation> orientations =new ArrayList<>();
                            dizzyHighway.get(i).get(j).add(energySpace);
                        }
                    }

                    if(i == 11 && j == 4){
                        if(k == 1){
                            ConveyorBelt conveyorBelt = new ConveyorBelt();
                            Position positionBelt = new Position(11,4);
                            conveyorBelt.setBeltPosition(positionBelt);
                            ArrayList<Orientation> orientations =new ArrayList<>();
                            orientations.add(Orientation.TOP);
                            conveyorBelt.setBeltOrientation(orientations);
                            conveyorBelt.setActivationOrder(1);
                            conveyorBelt.setSpeed(2);
                            dizzyHighway.get(i).get(j).add(conveyorBelt);
                        }
                    }

                    if(i == 1 && j == 5){
                        if(k == 1){
                            StartPoint startPoint = new StartPoint();
                            Position positionStartPoint = new Position(1,5);
                            startPoint.setStartingPoint(positionStartPoint);
                            dizzyHighway.get(i).get(j).add(startPoint);
                        }
                    }

                    if(i == 2 && j == 5){
                        if(k == 1){
                            Wall wall = new Wall();
                            Position positionWall = new Position(2,5);
                            wall.setWallPosition(positionWall);
                            ArrayList<Orientation> orientations =new ArrayList<>();
                            orientations.add(Orientation.RIGHT);
                            wall.setWallOrientation(orientations);
                            dizzyHighway.get(i).get(j).add(wall);
                        }
                    }

                    if(i == 4 && j == 5){
                        if(k == 1){
                            ConveyorBelt conveyorBelt = new ConveyorBelt();
                            Position positionBelt = new Position(4,5);
                            conveyorBelt.setBeltPosition(positionBelt);
                            ArrayList<Orientation> orientations =new ArrayList<>();
                            orientations.add(Orientation.BOTTOM);
                            conveyorBelt.setBeltOrientation(orientations);
                            conveyorBelt.setActivationOrder(1);
                            conveyorBelt.setSpeed(2);
                            dizzyHighway.get(i).get(j).add(conveyorBelt);
                        }
                    }

                    if(i == 7 && j == 5){
                        if(k == 1){
                            EnergySpace energySpace = new EnergySpace();
                            Position positionEnergySpace = new Position(7,5);
                            energySpace.setPosition(positionEnergySpace);
                            dizzyHighway.get(i).get(j).add(energySpace);
                        }
                    }

                    if(i == 9 && j == 5){
                        if(k == 1){
                            Wall wall = new Wall();
                            Position positionWall = new Position(9,5);
                            wall.setWallPosition(positionWall);
                            ArrayList<Orientation> orientations =new ArrayList<>();
                            orientations.add(Orientation.TOP);
                            wall.setWallOrientation(orientations);
                            dizzyHighway.get(i).get(j).add(wall);
                        }

                        if(k == 2){
                            BoardLaser boardLaser = new BoardLaser();
                            Position positionBoardLaser = new Position(9,5);
                            boardLaser.setBoardLaserPosition(positionBoardLaser);
                            ArrayList<Orientation> orientations =new ArrayList<>();
                            orientations.add(Orientation.TOP);
                            boardLaser.setBoardLaserOrientation(orientations);
                            boardLaser.setCount(1);
                            dizzyHighway.get(i).get(j).add(boardLaser);
                        }
                    }

                    if(i == 11 && j == 5){
                        if(k == 1){
                            ConveyorBelt conveyorBelt = new ConveyorBelt();
                            Position positionBelt = new Position(11,5);
                            conveyorBelt.setBeltPosition(positionBelt);
                            ArrayList<Orientation> orientations =new ArrayList<>();
                            orientations.add(Orientation.TOP);
                            conveyorBelt.setBeltOrientation(orientations);
                            conveyorBelt.setSpeed(2);
                            dizzyHighway.get(i).get(j).add(conveyorBelt);
                        }
                    }

                    if(i == 0 && j == 6){
                        if(k == 1){
                            StartPoint startPoint = new StartPoint();
                            Position positionStartPoint = new Position(0,6);
                            startPoint.setStartingPoint(positionStartPoint);
                            dizzyHighway.get(i).get(j).add(startPoint);
                        }
                    }

                    if(i == 4 && j == 6){
                        if(k == 1){
                            ConveyorBelt conveyorBelt = new ConveyorBelt();
                            Position positionBelt = new Position(4,6);
                            conveyorBelt.setBeltPosition(positionBelt);
                            ArrayList<Orientation> orientations =new ArrayList<>();
                            orientations.add(Orientation.BOTTOM);
                            conveyorBelt.setBeltOrientation(orientations);
                            conveyorBelt.setActivationOrder(1);
                            conveyorBelt.setSpeed(2);
                            dizzyHighway.get(i).get(j).add(conveyorBelt);
                        }
                    }

                    if(i == 6 && j == 6){
                        if(k == 1){
                            Wall wall = new Wall();
                            Position positionWall = new Position(6,6);
                            wall.setWallPosition(positionWall);
                            ArrayList<Orientation> orientations =new ArrayList<>();
                            orientations.add(Orientation.LEFT);
                            wall.setWallOrientation(orientations);
                            dizzyHighway.get(i).get(j).add(wall);
                        }
                    }

                    if(i == 7 && j == 6){
                        if(k == 1){
                            Wall wall = new Wall();
                            Position positionWall = new Position(7,6);
                            wall.setWallPosition(positionWall);
                            ArrayList<Orientation> orientations =new ArrayList<>();
                            orientations.add(Orientation.RIGHT);
                            wall.setWallOrientation(orientations);
                            dizzyHighway.get(i).get(j).add(wall);
                        }

                        if(k == 2){
                            BoardLaser boardLaser = new BoardLaser();
                            Position positionBoardLaser = new Position(7,6);
                            boardLaser.setBoardLaserPosition(positionBoardLaser);
                            ArrayList<Orientation> orientations =new ArrayList<>();
                            orientations.add(Orientation.RIGHT);
                            boardLaser.setBoardLaserOrientation(orientations);
                            boardLaser.setCount(1);
                            dizzyHighway.get(i).get(j).add(boardLaser);
                        }
                    }

                    if(i == 9 && j == 6){
                        if(k == 1){
                            Wall wall = new Wall();
                            Position positionWall = new Position(9,6);
                            wall.setWallPosition(positionWall);
                            ArrayList<Orientation> orientations =new ArrayList<>();
                            orientations.add(Orientation.BOTTOM);
                            wall.setWallOrientation(orientations);
                            dizzyHighway.get(i).get(j).add(wall);
                        }
                    }

                    if(i == 11 && j == 6){
                        if(k == 1){
                            ConveyorBelt conveyorBelt = new ConveyorBelt();
                            Position positionBelt = new Position(11,6);
                            conveyorBelt.setBeltPosition(positionBelt);
                            ArrayList<Orientation> orientations =new ArrayList<>();
                            orientations.add(Orientation.TOP);
                            conveyorBelt.setBeltOrientation(orientations);
                            conveyorBelt.setActivationOrder(1);
                            conveyorBelt.setSpeed(2);
                            dizzyHighway.get(i).get(j).add(conveyorBelt);
                        }
                    }

                    if(i == 1 && j == 7){
                        if(k == 1){
                            Wall wall = new Wall();
                            Position positionWall = new Position(1,7);
                            wall.setWallPosition(positionWall);
                            ArrayList<Orientation> orientations =new ArrayList<>();
                            orientations.add(Orientation.TOP);
                            wall.setWallOrientation(orientations);
                            dizzyHighway.get(i).get(j).add(wall);
                        }
                    }

                    if(i == 3 && j == 7){
                        if(k == 1){
                            ConveyorBelt conveyorBelt = new ConveyorBelt();
                            Position positionBelt = new Position(3,7);
                            conveyorBelt.setBeltPosition(positionBelt);
                            ArrayList<Orientation> orientations =new ArrayList<>();
                            orientations.add(Orientation.RIGHT);
                            conveyorBelt.setBeltOrientation(orientations);
                            conveyorBelt.setActivationOrder(1);
                            conveyorBelt.setSpeed(2);
                            dizzyHighway.get(i).get(j).add(conveyorBelt);
                        }
                    }

                    if(i == 4 && j == 7){
                        if(k == 1){
                            ConveyorBelt conveyorBelt = new ConveyorBelt();
                            Position positionBelt = new Position(4,7);
                            conveyorBelt.setBeltPosition(positionBelt);
                            ArrayList<Orientation> orientations =new ArrayList<>();
                            orientations.add(Orientation.BOTTOM);
                            orientations.add(Orientation.LEFT);
                            orientations.add(Orientation.TOP);
                            conveyorBelt.setBeltOrientation(orientations);
                            conveyorBelt.setActivationOrder(1);
                            conveyorBelt.setSpeed(2);
                            dizzyHighway.get(i).get(j).add(conveyorBelt);
                        }
                    }

                    if(i == 10 && j == 7){
                        if(k == 1){
                            EnergySpace energySpace = new EnergySpace();
                            Position positionEnergySpace = new Position(10,7);
                            energySpace.setPosition(positionEnergySpace);
                            dizzyHighway.get(i).get(j).add(energySpace);
                        }
                    }

                    if(i == 11 && j == 7){
                        if(k == 2){
                            ConveyorBelt conveyorBelt = new ConveyorBelt();
                            Position positionBelt = new Position(11,7);
                            conveyorBelt.setBeltPosition(positionBelt);
                            ArrayList<Orientation> orientations =new ArrayList<>();
                            orientations.add(Orientation.TOP);
                            conveyorBelt.setBeltOrientation(orientations);
                            conveyorBelt.setActivationOrder(1);
                            conveyorBelt.setSpeed(2);
                            dizzyHighway.get(i).get(j).add(conveyorBelt);
                        }
                    }

                    if(i == 1 && j == 8){
                        if(k == 1){
                            StartPoint startPoint = new StartPoint();
                            Position positionStartPoint = new Position(1,8);
                            startPoint.setStartingPoint(positionStartPoint);
                            dizzyHighway.get(i).get(j).add(startPoint);

                        }
                    }

                    if(i == 3 && j == 8) {
                        if(k == 1){
                            ConveyorBelt conveyorBelt = new ConveyorBelt();
                            Position positionBelt = new Position(3,8);
                            conveyorBelt.setBeltPosition(positionBelt);
                            ArrayList<Orientation> orientations =new ArrayList<>();
                            orientations.add(Orientation.RIGHT);
                            conveyorBelt.setBeltOrientation(orientations);
                            conveyorBelt.setActivationOrder(1);
                            conveyorBelt.setSpeed(2);
                            dizzyHighway.get(i).get(j).add(conveyorBelt);
                        }
                    }

                    if(i == 4 && j == 8){
                        if(k == 1){
                            ConveyorBelt conveyorBelt = new ConveyorBelt();
                            Position positionBelt = new Position(4,8);
                            conveyorBelt.setBeltPosition(positionBelt);
                            ArrayList<Orientation> orientations =new ArrayList<>();
                            orientations.add(Orientation.RIGHT);
                            orientations.add(Orientation.BOTTOM);
                            orientations.add(Orientation.LEFT);
                            conveyorBelt.setBeltOrientation(orientations);
                            conveyorBelt.setActivationOrder(1);
                            conveyorBelt.setSpeed(2);
                            dizzyHighway.get(i).get(j).add(conveyorBelt);
                        }
                    }

                    if(i == 5 && j == 8){
                        if(k == 1){
                            ConveyorBelt conveyorBelt = new ConveyorBelt();
                            Position positionBelt = new Position(5,8);
                            conveyorBelt.setBeltPosition(positionBelt);
                            ArrayList<Orientation> orientations =new ArrayList<>();
                            orientations.add(Orientation.RIGHT);
                            conveyorBelt.setBeltOrientation(orientations);
                            conveyorBelt.setActivationOrder(1);
                            conveyorBelt.setSpeed(2);
                            dizzyHighway.get(i).get(j).add(conveyorBelt);
                        }
                    }

                    if(i == 6 && j == 8){
                        if(k == 1){
                            ConveyorBelt conveyorBelt = new ConveyorBelt();
                            Position positionBelt = new Position(6,8);
                            conveyorBelt.setBeltPosition(positionBelt);
                            ArrayList<Orientation> orientations =new ArrayList<>();
                            orientations.add(Orientation.RIGHT);
                            conveyorBelt.setBeltOrientation(orientations);
                            conveyorBelt.setActivationOrder(1);
                            conveyorBelt.setSpeed(2);
                            dizzyHighway.get(i).get(j).add(conveyorBelt);
                        }
                    }

                    if(i == 7 && j == 8){
                        if(k == 1){
                            ConveyorBelt conveyorBelt = new ConveyorBelt();
                            Position positionBelt = new Position(7,8);
                            conveyorBelt.setBeltPosition(positionBelt);
                            ArrayList<Orientation> orientations =new ArrayList<>();
                            orientations.add(Orientation.RIGHT);
                            conveyorBelt.setBeltOrientation(orientations);
                            conveyorBelt.setActivationOrder(1);
                            conveyorBelt.setSpeed(2);
                            dizzyHighway.get(i).get(j).add(conveyorBelt);
                        }
                    }

                    if(i == 8 && j == 8){
                        if(k == 1){
                            ConveyorBelt conveyorBelt = new ConveyorBelt();
                            Position positionBelt = new Position(8,8);
                            conveyorBelt.setBeltPosition(positionBelt);
                            ArrayList<Orientation> orientations =new ArrayList<>();
                            orientations.add(Orientation.RIGHT);
                            conveyorBelt.setBeltOrientation(orientations);
                            conveyorBelt.setActivationOrder(1);
                            conveyorBelt.setSpeed(2);
                            dizzyHighway.get(i).get(j).add(conveyorBelt);
                        }
                    }

                    if(i == 9 && j == 8){
                        if(k == 1){
                            ConveyorBelt conveyorBelt = new ConveyorBelt();
                            Position positionBelt = new Position(9,8);
                            conveyorBelt.setBeltPosition(positionBelt);
                            ArrayList<Orientation> orientations =new ArrayList<>();
                            orientations.add(Orientation.RIGHT);
                            conveyorBelt.setBeltOrientation(orientations);
                            conveyorBelt.setActivationOrder(1);
                            conveyorBelt.setSpeed(2);
                            dizzyHighway.get(i).get(j).add(conveyorBelt);
                        }
                    }

                    if(i == 10 && j == 8){
                        if(k == 1){
                            ConveyorBelt conveyorBelt = new ConveyorBelt();
                            Position positionBelt = new Position(10,8);
                            conveyorBelt.setBeltPosition(positionBelt);
                            ArrayList<Orientation> orientations =new ArrayList<>();
                            orientations.add(Orientation.RIGHT);
                            orientations.add(Orientation.TOP);
                            orientations.add(Orientation.LEFT);
                            conveyorBelt.setBeltOrientation(orientations);
                            conveyorBelt.setActivationOrder(1);
                            conveyorBelt.setSpeed(2);
                            dizzyHighway.get(i).get(j).add(conveyorBelt);
                        }
                    }

                    if(i == 11 && j == 8 ){
                        if(k == 1){
                            ConveyorBelt conveyorBelt = new ConveyorBelt();
                            Position positionBelt = new Position(11,8);
                            conveyorBelt.setBeltPosition(positionBelt);
                            ArrayList<Orientation> orientations =new ArrayList<>();
                            orientations.add(Orientation.TOP);
                            orientations.add(Orientation.RIGHT);
                            orientations.add(Orientation.BOTTOM);
                            conveyorBelt.setBeltOrientation(orientations);
                            conveyorBelt.setActivationOrder(1);
                            conveyorBelt.setSpeed(2);
                            dizzyHighway.get(i).get(j).add(conveyorBelt);
                        }
                    }

                    if(i == 2 && j == 9){
                        if(k == 1){
                            ConveyorBelt conveyorBelt = new ConveyorBelt();
                            Position positionBelt = new Position(2,9);
                            conveyorBelt.setBeltPosition(positionBelt);
                            ArrayList<Orientation> orientations =new ArrayList<>();
                            orientations.add(Orientation.RIGHT);
                            conveyorBelt.setBeltOrientation(orientations);
                            conveyorBelt.setActivationOrder(2);
                            conveyorBelt.setSpeed(1);
                            dizzyHighway.get(i).get(j).add(conveyorBelt);
                        }
                    }

                    if(i == 3 && j == 9){
                        if(k == 1){
                            EnergySpace energySpace = new EnergySpace();
                            Position positionEnergySpace = new Position(3,9);
                            energySpace.setPosition(positionEnergySpace);
                            dizzyHighway.get(i).get(j).add(energySpace);
                        }
                    }

                    if(i == 10 && j == 9) {
                        if (k == 1) {
                            ConveyorBelt conveyorBelt = new ConveyorBelt();
                            Position positionBelt = new Position(10, 9);
                            conveyorBelt.setBeltPosition(positionBelt);
                            ArrayList<Orientation> orientations =new ArrayList<>();
                            orientations.add(Orientation.TOP);
                            conveyorBelt.setBeltOrientation(orientations);
                            conveyorBelt.setActivationOrder(1);
                            conveyorBelt.setSpeed(2);
                            dizzyHighway.get(i).get(j).add(conveyorBelt);
                        }
                    }

                    if(i == 11 && j == 9){
                        if(k == 1){
                            ConveyorBelt conveyorBelt = new ConveyorBelt();
                            Position positionBelt = new Position(11,9);
                            conveyorBelt.setBeltPosition(positionBelt);
                            ArrayList<Orientation> orientations =new ArrayList<>();
                            orientations.add(Orientation.TOP);
                            conveyorBelt.setBeltOrientation(orientations);
                            conveyorBelt.setActivationOrder(1);
                            conveyorBelt.setSpeed(2);
                            dizzyHighway.get(i).get(j).add(conveyorBelt);
                        }
                    }
                }
            }
        }
    }
}



     /**   dizzyHighway.add(5, 2, new ArrayList<Tile> ());
        Wall wall = new Wall(wallposition, orientation);
        BoardLaser laser = new BoardLaser(laserPosition, orientation, 1);
        for(Tile tile: cell){
            cell.add(floor);
            cell.add(wall);
            cell.add(laser);
        }

        for(int k = 0; k <= 3; k++){


        }
        if(position);
    }

}
         public ArrayList<Position> populatePositions(int maxColumns, int maxRows){
        this.maxRows = maxRows;
        int minColumn = 0;
        int minRow = 0;
        int totalFields = maxColumns * maxColumns;
        for (int i = 0; i <= maxColumns; maxColumns++){
            for (int j = 0 ; j <= maxRows; maxRows++){
                Position position = new Position(i, j);
                field.add(totalFields,position);
                if(position.getColumn() != minColumn && position.getColumn() != maxColumns|| position.getRow() != minRow && position.getRow() != maxRows)
                    Floor floor = new Floor();
                    floor.setPosition(position);

            }
        }
        return field;
    }
      **/
