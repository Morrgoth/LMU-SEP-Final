package bb.roborally.game.map;

import bb.roborally.game.Orientation;
import bb.roborally.game.Position;
import bb.roborally.game.board.Board;
import bb.roborally.game.tiles.*;

import java.util.ArrayList;

public class LostBearings extends Board {
	private ArrayList<ArrayList<ArrayList<Tile>>> lostBearings;

	public LostBearings(ArrayList<ArrayList<ArrayList<Tile>>> lostBearings) {
		super(lostBearings);
		this.lostBearings = lostBearings;
	}

	public void buildLostBearings() {
		int xAxis = 13;
		int yAxis = 10;
		int maxCellContent = 4;

		lostBearings = new ArrayList<ArrayList<ArrayList<Tile>>>();

		//for - Schleife x-Koordinaten (äußerste ArrayList)
		for (int i = 0; i < xAxis; i++) {
			lostBearings.add(new ArrayList<ArrayList<Tile>>());

			//for - Schleife y-Koordinaten (mittlere ArrayList)
			for (int j = 0; j < yAxis; j++) {
				lostBearings.get(i).add(new ArrayList<Tile>());

				//for - Schleife Cells (innerste ArrayList)
				for (int k = 0; k < maxCellContent; k++) {

					if (k == 0) {
						Floor floor = new Floor("Floor", "1A");
						floor.setFloorPosition(new Position(i, j));
						lostBearings.get(i).get(j).add(floor);
					}
					if(i == 0 && j == 0){
						if(k==1){
							ArrayList<Orientation> orientations = new ArrayList<>();
							orientations.add(Orientation.RIGHT);
							RebootPoint rebootPoint = new RebootPoint("RebootPoint","A",orientations);
							lostBearings.get(i).get(j).add(rebootPoint);
						}
					}

					if( (i == 0 && j == 3)) {
						if (k == 1) {
							StartPoint startPoint = new StartPoint("StartPoint", "A");
							startPoint.setPosition(new Position(i, j));
							lostBearings.get(i).get(j).add(startPoint);
						}
					}
					if( (i == 0 && j == 6)) {
						if (k == 1) {
							StartPoint startPoint = new StartPoint("StartPoint", "A");
							startPoint.setPosition(new Position(i, j));
							lostBearings.get(i).get(j).add(startPoint);
						}
					}
					if( (i == 1 && j == 1)) {
						if (k == 1) {
							StartPoint startPoint = new StartPoint("StartPoint", "A");
							startPoint.setPosition(new Position(i, j));
							lostBearings.get(i).get(j).add(startPoint);
						}
					}
					if( (i == 1 && j == 4)) {
						if (k == 1) {
							StartPoint startPoint = new StartPoint("StartPoint", "A");
							startPoint.setPosition(new Position(i, j));
							lostBearings.get(i).get(j).add(startPoint);
						}
					}
					if( (i == 1 && j == 5)) {
						if (k == 1) {
							StartPoint startPoint = new StartPoint("StartPoint", "A");
							startPoint.setPosition(new Position(i, j));
							lostBearings.get(i).get(j).add(startPoint);
						}
					}
					if( (i == 1 && j == 8)) {
						if (k == 1) {
							StartPoint startPoint = new StartPoint("StartPoint", "A");
							startPoint.setPosition(new Position(i, j));
							lostBearings.get(i).get(j).add(startPoint);
						}
					}
					if( i == 0 && j == 4){
						if (k == 1) {
							ArrayList<Orientation> orientations = new ArrayList<>();
							orientations.add(Orientation.RIGHT);
							Antenna antenna = new Antenna("Antenna", "A", orientations);
							antenna.setAntennaPosition(new Position(i, j));
							lostBearings.get(i).get(j).add(antenna);
						}
					}
					if ( i == 1 && j == 2){
						if (k == 1) {
							ArrayList<Orientation> orientations = new ArrayList<>();
							orientations.add(Orientation.TOP);
							Wall wall = new Wall("Wall", "A", orientations);
							wall.setWallPosition(new Position(i,j));
							lostBearings.get(i).get(j).add(wall);
						}
					}
					if ( i == 1 && j == 7){
						if (k == 1) {
							ArrayList<Orientation> orientations = new ArrayList<>();
							orientations.add(Orientation.BOTTOM);
							Wall wall = new Wall("Wall", "A", orientations);
							wall.setWallPosition(new Position(i,j));
							lostBearings.get(i).get(j).add(wall);
						}
					}
					if ( i == 2 && j == 4){
						if (k == 1) {
							ArrayList<Orientation> orientations = new ArrayList<>();
							orientations.add(Orientation.RIGHT);
							Wall wall = new Wall("Wall", "A", orientations);
							wall.setWallPosition(new Position(i,j));
							lostBearings.get(i).get(j).add(wall);
						}
					}
					if ( i == 2 && j == 5){
						if (k == 1) {
							ArrayList<Orientation> orientations = new ArrayList<>();
							orientations.add(Orientation.RIGHT);
							Wall wall = new Wall("Wall", "A", orientations);
							wall.setWallPosition(new Position(i,j));
							lostBearings.get(i).get(j).add(wall);
						}
					}
					if ( i == 9 && j == 3){
						if (k == 1) {
							ArrayList<Orientation> orientations = new ArrayList<>();
							orientations.add(Orientation.RIGHT);
							Wall wall = new Wall("Wall", "1A", orientations);
							wall.setWallPosition(new Position(i,j));
							lostBearings.get(i).get(j).add(wall);
						}
					}
					if ( i == 9 && j == 6){
						if (k == 1) {
							ArrayList<Orientation> orientations = new ArrayList<>();
							orientations.add(Orientation.RIGHT);
							Wall wall = new Wall("Wall", "1A", orientations);
							wall.setWallPosition(new Position(i,j));
							lostBearings.get(i).get(j).add(wall);
						}
					}
					if ( i == 6 && j == 3){
						if (k == 1) {
							ArrayList<Orientation> orientations = new ArrayList<>();
							orientations.add(Orientation.LEFT);
							Wall wall = new Wall("Wall", "1A", orientations);
							wall.setWallPosition(new Position(i,j));
							lostBearings.get(i).get(j).add(wall);
						}
					}
					if ( i == 6 && j == 6){
						if (k == 1) {
							ArrayList<Orientation> orientations = new ArrayList<>();
							orientations.add(Orientation.LEFT);
							Wall wall = new Wall("Wall", "1A", orientations);
							wall.setWallPosition(new Position(i,j));
							lostBearings.get(i).get(j).add(wall);
						}
					}
					if(i==6 && j == 2){
						if( k == 1){
							BlackHole blackHole = new BlackHole("BlackHole", "1A");
							blackHole.setBlackHolePosition(new Position(i,j));
							lostBearings.get(i).get(j).add(blackHole);
						}
					}
					if(i==6 && j == 7){
						if( k == 1){
							BlackHole blackHole = new BlackHole("BlackHole", "1A");
							blackHole.setBlackHolePosition(new Position(i,j));
							lostBearings.get(i).get(j).add(blackHole);
						}
					}
					if(i==9 && j == 2){
						if( k == 1){
							BlackHole blackHole = new BlackHole("BlackHole", "1A");
							blackHole.setBlackHolePosition(new Position(i,j));
							lostBearings.get(i).get(j).add(blackHole);
						}
					}
					if(i==9 && j == 7){
						if( k == 1){
							BlackHole blackHole = new BlackHole("BlackHole", "1A");
							blackHole.setBlackHolePosition(new Position(i,j));
							lostBearings.get(i).get(j).add(blackHole);
						}
					}
					if(i == 6 && j == 3){
						if( k == 1){
							ArrayList<Orientation> orientations = new ArrayList<>();
							orientations.add(Orientation.RIGHT);
							BoardLaser boardLaser = new BoardLaser("Laser", "1A", orientations,1);
							boardLaser.setBoardLaserPosition(new Position(i,j));
							lostBearings.get(i).get(j).add(boardLaser);
						}
					}
					if(i == 6 && j == 6){
						if( k == 1){
							ArrayList<Orientation> orientations = new ArrayList<>();
							orientations.add(Orientation.RIGHT);
							BoardLaser boardLaser = new BoardLaser("Laser", "1A", orientations,1);
							boardLaser.setBoardLaserPosition(new Position(i,j));
							lostBearings.get(i).get(j).add(boardLaser);
						}
					}
					if(i == 9 && j == 3){
						if( k == 1){
							ArrayList<Orientation> orientations = new ArrayList<>();
							orientations.add(Orientation.LEFT);
							BoardLaser boardLaser = new BoardLaser("Laser", "1A", orientations,1);
							boardLaser.setBoardLaserPosition(new Position(i,j));
							lostBearings.get(i).get(j).add(boardLaser);
						}
					}
					if(i == 9 && j == 6){
						if( k == 1){
							ArrayList<Orientation> orientations = new ArrayList<>();
							orientations.add(Orientation.LEFT);
							BoardLaser boardLaser = new BoardLaser("Laser", "1A", orientations,1);
							boardLaser.setBoardLaserPosition(new Position(i,j));
							lostBearings.get(i).get(j).add(boardLaser);
						}
					}
					if( i == 4 && j == 5){
						if( k == 1) {
							ArrayList<Orientation> orientations = new ArrayList<>();
							orientations.add(Orientation.RIGHT);
							CheckPoint checkPoint = new CheckPoint("CheckPoint","1A",orientations,2);
							checkPoint.setPosition(new Position(i, j));
							lostBearings.get(i).get(j).add(checkPoint);
						}
					}
					if( i == 8 && j == 2){
						if( k == 1) {
							ArrayList<Orientation> orientations = new ArrayList<>();
							orientations.add(Orientation.RIGHT);
							CheckPoint checkPoint = new CheckPoint("CheckPoint","1A",orientations,3);
							checkPoint.setPosition(new Position(i, j));
							lostBearings.get(i).get(j).add(checkPoint);
						}
					}
					if( i == 8 && j == 7){
						if( k == 1) {
							ArrayList<Orientation> orientations = new ArrayList<>();
							orientations.add(Orientation.RIGHT);
							CheckPoint checkPoint = new CheckPoint("CheckPoint","1A",orientations,4);
							checkPoint.setPosition(new Position(i, j));
							lostBearings.get(i).get(j).add(checkPoint);
						}
					}
					if( i == 11 && j == 4){
						if( k == 1) {
							ArrayList<Orientation> orientations = new ArrayList<>();
							orientations.add(Orientation.RIGHT);
							CheckPoint checkPoint = new CheckPoint("CheckPoint","1A",orientations,1);
							checkPoint.setPosition(new Position(i, j));
							lostBearings.get(i).get(j).add(checkPoint);
						}
					}
					if( i == 5 && j == 2){
						if( k == 1){
							ArrayList<Orientation> orientations = new ArrayList<>();
							orientations.add(Orientation.HORIZONTAL);
							EnergySpace energySpace = new EnergySpace("EnergySpace","1A", orientations, 1);
							energySpace.setPosition(new Position(i,j));
							lostBearings.get(i).get(j).add(energySpace);
						}
					}
					if( i == 5 && j == 7){
						if( k == 1){
							ArrayList<Orientation> orientations = new ArrayList<>();
							orientations.add(Orientation.HORIZONTAL);
							EnergySpace energySpace = new EnergySpace("EnergySpace","1A", orientations,1);
							energySpace.setPosition(new Position(i,j));
							lostBearings.get(i).get(j).add(energySpace);
						}
					}
					if( i == 10 && j == 2){
						if( k == 1){
							ArrayList<Orientation> orientations = new ArrayList<>();
							orientations.add(Orientation.HORIZONTAL);
							EnergySpace energySpace = new EnergySpace("EnergySpace","1A", orientations, 1);
							energySpace.setPosition(new Position(i,j));
							lostBearings.get(i).get(j).add(energySpace);
						}
					}
					if( i == 10 && j == 7){
						if( k == 1){
							ArrayList<Orientation> orientations = new ArrayList<>();
							orientations.add(Orientation.HORIZONTAL);
							EnergySpace energySpace = new EnergySpace("EnergySpace","1A", orientations, 1);
							energySpace.setPosition(new Position(i,j));
							lostBearings.get(i).get(j).add(energySpace);
						}
					}
					if( i == 5 && j ==4){
						if( k == 1){
							Gear gear = new Gear("Gear","1A","counterclockwise");
							gear.setPosition(new Position(i, j));
							lostBearings.get(i).get(j).add(gear);
						}
					}
					if( i == 8 && j ==4){
						if( k == 1){
							Gear gear = new Gear("Gear","1A","counterclockwise");
							gear.setPosition(new Position(i, j));
							lostBearings.get(i).get(j).add(gear);
						}
					}
					if( i == 10 && j ==4){
						if( k == 1){
							Gear gear = new Gear("Gear","1A","clockwise");
							gear.setPosition(new Position(i, j));
							lostBearings.get(i).get(j).add(gear);
						}
					}
					if( i == 5 && j ==5){
						if( k == 1){
							Gear gear = new Gear("Gear","1A","clockwise");
							gear.setPosition(new Position(i, j));
							lostBearings.get(i).get(j).add(gear);
						}
					}
					if( i == 7 && j ==5){
						if( k == 1){
							Gear gear = new Gear("Gear","1A","clockwise");
							gear.setPosition(new Position(i, j));
							lostBearings.get(i).get(j).add(gear);
						}
					}
					if( i == 10 && j ==5){
						if( k == 1){
							Gear gear = new Gear("Gear","1A","counterclockwise");
							gear.setPosition(new Position(i, j));
							lostBearings.get(i).get(j).add(gear);
						}
					}
					// 2/9
					if(i ==2 && j == 0 ){
						if( k == 1){
							ArrayList<Orientation> orientations = new ArrayList<>();
							orientations.add(Orientation.RIGHT);
							ConveyorBelt conveyorBelt = new ConveyorBelt("ConveyorBelt","1A", 1, orientations);
							conveyorBelt.setBeltPosition(new Position(i,j));
							lostBearings.get(i).get(j).add(conveyorBelt);
						}
					}
					if(i ==3 && j == 1 ){
						if( k == 1){
							ArrayList<Orientation> orientations =new ArrayList<>();
							orientations.add(Orientation.LEFT);
							ConveyorBelt conveyorBelt = new ConveyorBelt(new Position(i,j),"1A",orientations,2,1);
							lostBearings.get(i).get(j).add(conveyorBelt);
						}
					}
					if(i ==3 && j == 8 ){
						if( k == 1){
							ArrayList<Orientation> orientations =new ArrayList<>();
							orientations.add(Orientation.RIGHT);
							ConveyorBelt conveyorBelt = new ConveyorBelt(new Position(i,j),"1A",orientations,2,1);
							lostBearings.get(i).get(j).add(conveyorBelt);
						}
					}
					if(i ==4 && j == 1 ){
						if( k == 1){
							ArrayList<Orientation> orientations =new ArrayList<>();
							orientations.add(Orientation.LEFT);
							orientations.add(Orientation.BOTTOM);
							ConveyorBelt conveyorBelt = new ConveyorBelt(new Position(i,j),"1A",orientations,2,1);
							lostBearings.get(i).get(j).add(conveyorBelt);
						}
					}
					if(i ==4 && j == 8 ){
						if( k == 1){
							ArrayList<Orientation> orientations =new ArrayList<>();
							orientations.add(Orientation.RIGHT);
							orientations.add(Orientation.BOTTOM);
							ConveyorBelt conveyorBelt = new ConveyorBelt(new Position(i,j),"1A",orientations,2,1);
							lostBearings.get(i).get(j).add(conveyorBelt);
						}
					}
					if(i ==4 && j == 0 ){
						if( k == 1){
							ArrayList<Orientation> orientations =new ArrayList<>();
							orientations.add(Orientation.BOTTOM);
							ConveyorBelt conveyorBelt = new ConveyorBelt(new Position(i,j),"1A",orientations,2,1);
							lostBearings.get(i).get(j).add(conveyorBelt);
						}
					}
					if(i ==4 && j == 9 ){
						if( k == 1){
							ArrayList<Orientation> orientations =new ArrayList<>();
							orientations.add(Orientation.BOTTOM);
							ConveyorBelt conveyorBelt = new ConveyorBelt(new Position(i,j),"1A",orientations,2,1);
							lostBearings.get(i).get(j).add(conveyorBelt);
						}
					}
					if(i ==5 && j == 3 ){
						if( k == 1){
							ArrayList<Orientation> orientations =new ArrayList<>();
							orientations.add(Orientation.BOTTOM);
							ConveyorBelt conveyorBelt = new ConveyorBelt(new Position(i,j),"1A",orientations,1,2);
							lostBearings.get(i).get(j).add(conveyorBelt);
						}
					}
					if(i ==5 && j == 6 ){
						if( k == 1){
							ArrayList<Orientation> orientations =new ArrayList<>();
							orientations.add(Orientation.TOP);
							ConveyorBelt conveyorBelt = new ConveyorBelt(new Position(i,j),"1A",orientations,1,2);
							lostBearings.get(i).get(j).add(conveyorBelt);
						}
					}
					if(i == 6 && j == 1 ){
						if( k == 1){
							ArrayList<Orientation> orientations =new ArrayList<>();
							orientations.add(Orientation.LEFT);
							ConveyorBelt conveyorBelt = new ConveyorBelt(new Position(i,j),"1A",orientations,2,1);
							lostBearings.get(i).get(j).add(conveyorBelt);
						}
					}
					if(i == 7 && j == 1 ){
						if( k == 1){
							ArrayList<Orientation> orientations =new ArrayList<>();
							orientations.add(Orientation.LEFT);
							ConveyorBelt conveyorBelt = new ConveyorBelt(new Position(i,j),"1A",orientations,2,1);
							lostBearings.get(i).get(j).add(conveyorBelt);
						}
					}
					if(i == 8 && j == 1 ){
						if( k == 1){
							ArrayList<Orientation> orientations =new ArrayList<>();
							orientations.add(Orientation.RIGHT);
							ConveyorBelt conveyorBelt = new ConveyorBelt(new Position(i,j),"1A",orientations,2,1);
							lostBearings.get(i).get(j).add(conveyorBelt);
						}
					}
					if(i == 9 && j == 1 ){
						if( k == 1){
							ArrayList<Orientation> orientations =new ArrayList<>();
							orientations.add(Orientation.RIGHT);
							ConveyorBelt conveyorBelt = new ConveyorBelt(new Position(i,j),"1A",orientations,2,1);
							lostBearings.get(i).get(j).add(conveyorBelt);
						}
					}
					if(i == 10 && j == 3 ){
						if( k == 1){
							ArrayList<Orientation> orientations =new ArrayList<>();
							orientations.add(Orientation.BOTTOM);
							ConveyorBelt conveyorBelt = new ConveyorBelt(new Position(i,j),"1A",orientations,1,2);
							lostBearings.get(i).get(j).add(conveyorBelt);
						}
					}
					if(i == 10 && j == 6 ){
						if( k == 1){
							ArrayList<Orientation> orientations =new ArrayList<>();
							orientations.add(Orientation.TOP);
							ConveyorBelt conveyorBelt = new ConveyorBelt(new Position(i,j),"1A",orientations,1,2);
							lostBearings.get(i).get(j).add(conveyorBelt);
						}
					}
					if(i == 11 && j == 0 ){
						if( k == 1){
							ArrayList<Orientation> orientations =new ArrayList<>();
							orientations.add(Orientation.TOP);
							ConveyorBelt conveyorBelt = new ConveyorBelt(new Position(i,j),"1A",orientations,2,1);
							lostBearings.get(i).get(j).add(conveyorBelt);
						}
					}
					if(i == 11 && j == 1 ){
						if( k == 1){
							ArrayList<Orientation> orientations =new ArrayList<>();
							orientations.add(Orientation.TOP);
							orientations.add(Orientation.LEFT);
							ConveyorBelt conveyorBelt = new ConveyorBelt(new Position(i,j),"1A",orientations,2,1);
							lostBearings.get(i).get(j).add(conveyorBelt);
						}
					}
					if(i == 11 && j == 8 ){
						if( k == 1){
							ArrayList<Orientation> orientations =new ArrayList<>();
							orientations.add(Orientation.RIGHT);
							orientations.add(Orientation.TOP);
							ConveyorBelt conveyorBelt = new ConveyorBelt(new Position(i,j),"1A",orientations,2,1);
							lostBearings.get(i).get(j).add(conveyorBelt);
						}
					}
					if(i == 11 && j == 9 ){
						if( k == 1){
							ArrayList<Orientation> orientations =new ArrayList<>();
							orientations.add(Orientation.TOP);
							ConveyorBelt conveyorBelt = new ConveyorBelt(new Position(i,j),"1A",orientations,2,1);
							lostBearings.get(i).get(j).add(conveyorBelt);
						}
					}
					if(i == 12 && j == 1 ){
						if( k == 1){
							ArrayList<Orientation> orientations =new ArrayList<>();
							orientations.add(Orientation.LEFT);
							ConveyorBelt conveyorBelt = new ConveyorBelt(new Position(i,j),"1A",orientations,2,1);
							lostBearings.get(i).get(j).add(conveyorBelt);
						}
					}
					if(i == 12 && j == 8 ){
						if( k == 1){
							ArrayList<Orientation> orientations =new ArrayList<>();
							orientations.add(Orientation.RIGHT);
							ConveyorBelt conveyorBelt = new ConveyorBelt(new Position(i,j),"1A",orientations,2,1);
							lostBearings.get(i).get(j).add(conveyorBelt);
						}
					}
				}
			}
		}
	}
}


/*
Antenna 		done	checked
BlackHole		done	checked
BoardLaser		done	checked
CheckPoint		done	checked
ConveyorBelt	done	checked
EnergySpace		done	checked
Floor 			done	checked
Gear			done	checked
PushPanel		none	none
RebootPoint 	done	checked
Wall 			done
 */