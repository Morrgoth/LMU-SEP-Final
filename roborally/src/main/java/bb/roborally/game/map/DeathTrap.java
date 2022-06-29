package bb.roborally.game.map;

import bb.roborally.game.Orientation;
import bb.roborally.game.Position;
import bb.roborally.game.board.Board;
import bb.roborally.game.tiles.*;
import javafx.geometry.Pos;

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
            }


            }

        }



    }
}
