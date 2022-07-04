package bb.roborally.game.map;

import bb.roborally.game.Orientation;
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
					if(i >= 0 && i <= 2){
						if (k == 0) {
							Empty empty = new Empty("A");
							lostBearings.get(i).get(j).add(empty);
						}
					}

					if(i >= 3 && i <= 12){
						if (k == 0) {
							Empty empty = new Empty("1A");
							lostBearings.get(i).get(j).add(empty);
						}
					}

					if(i == 0 && j == 0){
						if(k==1){
							ArrayList<Orientation> orientations = new ArrayList<>();
							orientations.add(Orientation.RIGHT);
							RestartPoint restartPoint = new RestartPoint("A",orientations);
							lostBearings.get(i).get(j).add(restartPoint);
						}
					}

					if( (i == 0 && j == 3)) {
						if (k == 1) {
							StartPoint startPoint = new StartPoint("A");
							lostBearings.get(i).get(j).add(startPoint);
						}
					}
					if( (i == 0 && j == 6)) {
						if (k == 1) {
							StartPoint startPoint = new StartPoint("A");
							lostBearings.get(i).get(j).add(startPoint);
						}
					}
					if( (i == 1 && j == 1)) {
						if (k == 1) {
							StartPoint startPoint = new StartPoint("A");
							lostBearings.get(i).get(j).add(startPoint);
						}
					}
					if( (i == 1 && j == 4)) {
						if (k == 1) {
							StartPoint startPoint = new StartPoint("A");
							lostBearings.get(i).get(j).add(startPoint);
						}
					}
					if( (i == 1 && j == 5)) {
						if (k == 1) {
							StartPoint startPoint = new StartPoint("A");
							lostBearings.get(i).get(j).add(startPoint);
						}
					}
					if( (i == 1 && j == 8)) {
						if (k == 1) {
							StartPoint startPoint = new StartPoint("A");
							lostBearings.get(i).get(j).add(startPoint);
						}
					}
					if( i == 0 && j == 4){
						if (k == 1) {
							ArrayList<Orientation> orientations = new ArrayList<>();
							orientations.add(Orientation.RIGHT);
							Antenna antenna = new Antenna("A", orientations);
							lostBearings.get(i).get(j).add(antenna);
						}
					}
					if ( i == 1 && j == 2){
						if (k == 1) {
							ArrayList<Orientation> orientations = new ArrayList<>();
							orientations.add(Orientation.TOP);
							Wall wall = new Wall("A", orientations);
							lostBearings.get(i).get(j).add(wall);
						}
					}
					if ( i == 1 && j == 7){
						if (k == 1) {
							ArrayList<Orientation> orientations = new ArrayList<>();
							orientations.add(Orientation.BOTTOM);
							Wall wall = new Wall("A", orientations);
							lostBearings.get(i).get(j).add(wall);
						}
					}
					if ( i == 2 && j == 4){
						if (k == 1) {
							ArrayList<Orientation> orientations = new ArrayList<>();
							orientations.add(Orientation.RIGHT);
							Wall wall = new Wall("A", orientations);
							lostBearings.get(i).get(j).add(wall);
						}
					}
					if ( i == 2 && j == 5){
						if (k == 1) {
							ArrayList<Orientation> orientations = new ArrayList<>();
							orientations.add(Orientation.RIGHT);
							Wall wall = new Wall("A", orientations);
							lostBearings.get(i).get(j).add(wall);
						}
					}
					if ( i == 9 && j == 3){
						if (k == 1) {
							ArrayList<Orientation> orientations = new ArrayList<>();
							orientations.add(Orientation.RIGHT);
							Wall wall = new Wall("1A", orientations);
							lostBearings.get(i).get(j).add(wall);
						}
					}
					if ( i == 9 && j == 6){
						if (k == 1) {
							ArrayList<Orientation> orientations = new ArrayList<>();
							orientations.add(Orientation.RIGHT);
							Wall wall = new Wall("1A", orientations);
							lostBearings.get(i).get(j).add(wall);
						}
					}
					if ( i == 6 && j == 3){
						if (k == 1) {
							ArrayList<Orientation> orientations = new ArrayList<>();
							orientations.add(Orientation.LEFT);
							Wall wall = new Wall("1A", orientations);
							lostBearings.get(i).get(j).add(wall);
						}
					}
					if ( i == 6 && j == 6){
						if (k == 1) {
							ArrayList<Orientation> orientations = new ArrayList<>();
							orientations.add(Orientation.LEFT);
							Wall wall = new Wall("1A", orientations);
							lostBearings.get(i).get(j).add(wall);
						}
					}
					if(i==6 && j == 2){
						if( k == 1){
							Pit pit = new Pit("1A");
							lostBearings.get(i).get(j).add(pit);
						}
					}
					if(i==6 && j == 7){
						if( k == 1){
							Pit pit = new Pit("1A");
							lostBearings.get(i).get(j).add(pit);
						}
					}
					if(i==9 && j == 2){
						if( k == 1){
							Pit pit = new Pit("1A");
							lostBearings.get(i).get(j).add(pit);
						}
					}
					if(i==9 && j == 7){
						if( k == 1){
							Pit pit = new Pit("1A");
							lostBearings.get(i).get(j).add(pit);
						}
					}
					if(i == 6 && j == 3){
						if( k == 1){
							ArrayList<Orientation> orientations = new ArrayList<>();
							orientations.add(Orientation.RIGHT);
							Laser laser = new Laser("1A", orientations,1);
							lostBearings.get(i).get(j).add(laser);
						}
					}
					if(i == 7 && j == 3){
						if( k == 1){
							ArrayList<Orientation> orientations = new ArrayList<>();
							orientations.add(Orientation.RIGHT);
							Laser laser = new Laser("1A", orientations,1);
							lostBearings.get(i).get(j).add(laser);
						}
					}
					if(i == 8 && j == 3){
						if( k == 1){
							ArrayList<Orientation> orientations = new ArrayList<>();
							orientations.add(Orientation.RIGHT);
							Laser laser = new Laser("1A", orientations,1);
							lostBearings.get(i).get(j).add(laser);
						}
					}
					if(i == 9 && j == 3){
						if( k == 1){
							ArrayList<Orientation> orientations = new ArrayList<>();
							orientations.add(Orientation.RIGHT);
							Laser laser = new Laser("1A", orientations,1);
							lostBearings.get(i).get(j).add(laser);
						}
					}
					if(i == 6 && j == 6){
						if( k == 1){
							ArrayList<Orientation> orientations = new ArrayList<>();
							orientations.add(Orientation.LEFT);
							Laser laser = new Laser("1A", orientations,1);
							lostBearings.get(i).get(j).add(laser);
						}
					}
					if(i == 7 && j == 6){
						if( k == 1){
							ArrayList<Orientation> orientations = new ArrayList<>();
							orientations.add(Orientation.LEFT);
							Laser laser = new Laser("1A", orientations,1);
							lostBearings.get(i).get(j).add(laser);
						}
					}
					if(i == 8 && j == 6){
						if( k == 1){
							ArrayList<Orientation> orientations = new ArrayList<>();
							orientations.add(Orientation.LEFT);
							Laser laser = new Laser("1A", orientations,1);
							lostBearings.get(i).get(j).add(laser);
						}
					}
					if(i == 9 && j == 6){
						if( k == 1){
							ArrayList<Orientation> orientations = new ArrayList<>();
							orientations.add(Orientation.LEFT);
							Laser laser = new Laser("1A", orientations,1);
							lostBearings.get(i).get(j).add(laser);
						}
					}
					if( i == 4 && j == 5){
						if( k == 1) {
							ArrayList<Orientation> orientations = new ArrayList<>();
							orientations.add(Orientation.RIGHT);
							CheckPoint checkPoint = new CheckPoint("1A",orientations,2);
							lostBearings.get(i).get(j).add(checkPoint);
						}
					}
					if( i == 8 && j == 2){
						if( k == 1) {
							ArrayList<Orientation> orientations = new ArrayList<>();
							orientations.add(Orientation.RIGHT);
							CheckPoint checkPoint = new CheckPoint("1A",orientations,3);
							lostBearings.get(i).get(j).add(checkPoint);
						}
					}
					if( i == 8 && j == 7){
						if( k == 1) {
							ArrayList<Orientation> orientations = new ArrayList<>();
							orientations.add(Orientation.RIGHT);
							CheckPoint checkPoint = new CheckPoint("1A",orientations,4);
							lostBearings.get(i).get(j).add(checkPoint);
						}
					}
					if( i == 11 && j == 4){
						if( k == 1) {
							ArrayList<Orientation> orientations = new ArrayList<>();
							orientations.add(Orientation.RIGHT);
							CheckPoint checkPoint = new CheckPoint("1A",orientations,1);
							lostBearings.get(i).get(j).add(checkPoint);
						}
					}
					if( i == 5 && j == 2){
						if( k == 1){
							ArrayList<Orientation> orientations = new ArrayList<>();
							orientations.add(Orientation.HORIZONTAL);
							EnergySpace energySpace = new EnergySpace("1A", orientations, 1);
							lostBearings.get(i).get(j).add(energySpace);
						}
					}
					if( i == 5 && j == 7){
						if( k == 1){
							ArrayList<Orientation> orientations = new ArrayList<>();
							orientations.add(Orientation.HORIZONTAL);
							EnergySpace energySpace = new EnergySpace("1A", orientations,1);
							lostBearings.get(i).get(j).add(energySpace);
						}
					}
					if( i == 10 && j == 2){
						if( k == 1){
							ArrayList<Orientation> orientations = new ArrayList<>();
							orientations.add(Orientation.HORIZONTAL);
							EnergySpace energySpace = new EnergySpace("1A", orientations, 1);
							lostBearings.get(i).get(j).add(energySpace);
						}
					}
					if( i == 10 && j == 7){
						if( k == 1){
							ArrayList<Orientation> orientations = new ArrayList<>();
							orientations.add(Orientation.HORIZONTAL);
							EnergySpace energySpace = new EnergySpace("1A", orientations, 1);
							lostBearings.get(i).get(j).add(energySpace);
						}
					}
					if( i == 7 && j == 4){
						if( k == 1){
							ArrayList<Orientation> orientations = new ArrayList<>();
							orientations.add(Orientation.HORIZONTAL);
							EnergySpace energySpace = new EnergySpace("1A", orientations, 1);
							lostBearings.get(i).get(j).add(energySpace);
						}
					}
					if( i == 8 && j == 5){
						if( k == 1){
							ArrayList<Orientation> orientations = new ArrayList<>();
							orientations.add(Orientation.HORIZONTAL);
							EnergySpace energySpace = new EnergySpace("1A", orientations, 1);
							lostBearings.get(i).get(j).add(energySpace);
						}
					}
					if( i == 5 && j ==4){
						if( k == 1){
							Gear gear = new Gear("1A","counterclockwise");
							lostBearings.get(i).get(j).add(gear);
						}
					}
					if( i == 8 && j ==4){
						if( k == 1){
							Gear gear = new Gear("1A","counterclockwise");
							lostBearings.get(i).get(j).add(gear);
						}
					}
					if( i == 10 && j ==4){
						if( k == 1){
							Gear gear = new Gear("1A","clockwise");
							lostBearings.get(i).get(j).add(gear);
						}
					}
					if( i == 5 && j ==5){
						if( k == 1){
							Gear gear = new Gear("1A","clockwise");
							lostBearings.get(i).get(j).add(gear);
						}
					}
					if( i == 7 && j ==5){
						if( k == 1){
							Gear gear = new Gear("1A","clockwise");
							lostBearings.get(i).get(j).add(gear);
						}
					}
					if( i == 10 && j ==5){
						if( k == 1){
							Gear gear = new Gear("1A","counterclockwise");
							lostBearings.get(i).get(j).add(gear);
						}
					}
					// 2/9
					if(i ==2 && j == 0 ){
						if( k == 1){
							ArrayList<Orientation> orientations = new ArrayList<>();
							orientations.add(Orientation.RIGHT);
							orientations.add(Orientation.LEFT);
							ConveyorBelt conveyorBelt = new ConveyorBelt("A", 1, orientations);
							lostBearings.get(i).get(j).add(conveyorBelt);
						}
					}
					if(i ==2 && j == 9 ){
						if( k == 1){
							ArrayList<Orientation> orientations = new ArrayList<>();
							orientations.add(Orientation.RIGHT);
							orientations.add(Orientation.LEFT);
							ConveyorBelt conveyorBelt = new ConveyorBelt("A", 1, orientations);
							lostBearings.get(i).get(j).add(conveyorBelt);
						}
					}
					if(i ==3 && j == 1 ){
						if( k == 1){
							ArrayList<Orientation> orientations = new ArrayList<>();
							orientations.add(Orientation.LEFT);
							orientations.add(Orientation.RIGHT);
							ConveyorBelt conveyorBelt = new ConveyorBelt("1A", 1, orientations);
							lostBearings.get(i).get(j).add(conveyorBelt);
						}
					}
					if(i ==3 && j == 8 ){
						if( k == 1){
							ArrayList<Orientation> orientations = new ArrayList<>();
							orientations.add(Orientation.RIGHT);
							orientations.add(Orientation.LEFT);
							ConveyorBelt conveyorBelt = new ConveyorBelt("1A", 1, orientations);
							lostBearings.get(i).get(j).add(conveyorBelt);
						}
					}
					if(i ==4 && j == 1 ){
						if( k == 1){
							ArrayList<Orientation> orientations = new ArrayList<>();
							orientations.add(Orientation.LEFT);
							orientations.add(Orientation.TOP);
							ConveyorBelt conveyorBelt = new ConveyorBelt("1A", 1, orientations);
							lostBearings.get(i).get(j).add(conveyorBelt);
						}
					}
					if(i ==4 && j == 8 ){
						if( k == 1){
							ArrayList<Orientation> orientations = new ArrayList<>();
							orientations.add(Orientation.BOTTOM);
							orientations.add(Orientation.LEFT);
							ConveyorBelt conveyorBelt = new ConveyorBelt("1A", 1, orientations);
							lostBearings.get(i).get(j).add(conveyorBelt);
						}
					}
					if(i ==4 && j == 0 ){
						if( k == 1){
							ArrayList<Orientation> orientations = new ArrayList<>();
							orientations.add(Orientation.BOTTOM);
							orientations.add(Orientation.TOP);
							ConveyorBelt conveyorBelt = new ConveyorBelt("1A", 1, orientations);
							lostBearings.get(i).get(j).add(conveyorBelt);
						}
					}
					if(i ==4 && j == 9 ){
						if( k == 1){
							ArrayList<Orientation> orientations = new ArrayList<>();
							orientations.add(Orientation.BOTTOM);
							orientations.add(Orientation.TOP);
							ConveyorBelt conveyorBelt = new ConveyorBelt("1A", 1, orientations);
							lostBearings.get(i).get(j).add(conveyorBelt);
						}
					}
					if(i ==5 && j == 3 ){
						if( k == 1){
							ArrayList<Orientation> orientations = new ArrayList<>();
							orientations.add(Orientation.BOTTOM);
							orientations.add(Orientation.TOP);
							ConveyorBelt conveyorBelt = new ConveyorBelt("1A",2, orientations);
							lostBearings.get(i).get(j).add(conveyorBelt);
						}
					}
					if(i ==5 && j == 6 ){
						if( k == 1){
							ArrayList<Orientation> orientations = new ArrayList<>();
							orientations.add(Orientation.TOP);
							orientations.add(Orientation.BOTTOM);
							ConveyorBelt conveyorBelt = new ConveyorBelt("1A", 2, orientations);
							lostBearings.get(i).get(j).add(conveyorBelt);
						}
					}
					if(i == 6 && j == 1 ){
						if( k == 1){
							ArrayList<Orientation> orientations = new ArrayList<>();
							orientations.add(Orientation.LEFT);
							orientations.add(Orientation.RIGHT);
							ConveyorBelt conveyorBelt = new ConveyorBelt("1A", 1, orientations);
							lostBearings.get(i).get(j).add(conveyorBelt);
						}
					}
					if(i == 7 && j == 1 ){
						if( k == 1){
							ArrayList<Orientation> orientations = new ArrayList<>();
							orientations.add(Orientation.LEFT);
							orientations.add(Orientation.RIGHT);
							ConveyorBelt conveyorBelt = new ConveyorBelt("1A",1, orientations);
							lostBearings.get(i).get(j).add(conveyorBelt);
						}
					}
					if(i == 8 && j == 1 ){
						if( k == 1){
							ArrayList<Orientation> orientations = new ArrayList<>();
							orientations.add(Orientation.RIGHT);
							orientations.add(Orientation.LEFT);
							ConveyorBelt conveyorBelt = new ConveyorBelt("1A",1, orientations);
							lostBearings.get(i).get(j).add(conveyorBelt);
						}
					}
					if(i == 9 && j == 1 ){
						if( k == 1){
							ArrayList<Orientation> orientations = new ArrayList<>();
							orientations.add(Orientation.RIGHT);
							orientations.add(Orientation.LEFT);
							ConveyorBelt conveyorBelt = new ConveyorBelt("1A", 1, orientations);
							lostBearings.get(i).get(j).add(conveyorBelt);
						}
					}
					if(i == 10 && j == 3 ){
						if( k == 1){
							ArrayList<Orientation> orientations = new ArrayList<>();
							orientations.add(Orientation.BOTTOM);
							orientations.add(Orientation.TOP);
							ConveyorBelt conveyorBelt = new ConveyorBelt("1A", 2, orientations);
							lostBearings.get(i).get(j).add(conveyorBelt);
						}
					}
					if(i == 10 && j == 6 ){
						if( k == 1){
							ArrayList<Orientation> orientations = new ArrayList<>();
							orientations.add(Orientation.TOP);
							orientations.add(Orientation.BOTTOM);
							ConveyorBelt conveyorBelt = new ConveyorBelt("1A", 2, orientations);
							lostBearings.get(i).get(j).add(conveyorBelt);
						}
					}
					if(i == 11 && j == 0 ){
						if( k == 1){
							ArrayList<Orientation> orientations = new ArrayList<>();
							orientations.add(Orientation.TOP);
							orientations.add(Orientation.BOTTOM);
							ConveyorBelt conveyorBelt = new ConveyorBelt("1A", 1, orientations);
							lostBearings.get(i).get(j).add(conveyorBelt);
						}
					}
					if(i == 11 && j == 1 ){
						if( k == 1){
							ArrayList<Orientation> orientations = new ArrayList<>();
							orientations.add(Orientation.TOP);
							orientations.add(Orientation.RIGHT);
							ConveyorBelt conveyorBelt = new ConveyorBelt("1A", 1, orientations);
							lostBearings.get(i).get(j).add(conveyorBelt);
						}
					}
					if(i == 11 && j == 8 ){
						if( k == 1){
							ArrayList<Orientation> orientations = new ArrayList<>();
							orientations.add(Orientation.RIGHT);
							orientations.add(Orientation.BOTTOM);
							ConveyorBelt conveyorBelt = new ConveyorBelt("1A",1, orientations);
							lostBearings.get(i).get(j).add(conveyorBelt);
						}
					}
					if(i == 11 && j == 9 ){
						if( k == 1){
							ArrayList<Orientation> orientations = new ArrayList<>();
							orientations.add(Orientation.TOP);
							orientations.add(Orientation.BOTTOM);
							ConveyorBelt conveyorBelt = new ConveyorBelt("1A",1, orientations);
							lostBearings.get(i).get(j).add(conveyorBelt);
						}
					}
					if(i == 12 && j == 1 ){
						if( k == 1){
							ArrayList<Orientation> orientations = new ArrayList<>();
							orientations.add(Orientation.LEFT);
							orientations.add(Orientation.RIGHT);
							ConveyorBelt conveyorBelt = new ConveyorBelt("1A",1, orientations);
							lostBearings.get(i).get(j).add(conveyorBelt);
						}
					}
					if(i == 12 && j == 8 ){
						if( k == 1){
							ArrayList<Orientation> orientations = new ArrayList<>();
							orientations.add(Orientation.RIGHT);
							orientations.add(Orientation.LEFT);
							ConveyorBelt conveyorBelt = new ConveyorBelt("1A", 1, orientations);
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